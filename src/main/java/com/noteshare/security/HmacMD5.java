package com.noteshare.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/***
 * ProjectName : password
 * ClassName   : HmacMD5
 * Description : HMACMD5 是从 MD5 哈希函数构造的一种键控哈希算法，被用作基于哈希的消息验证代码 (HMAC)。此 HMAC 进程将密钥与消息数据混合，使用哈希函数对混合结果进行哈希计算，
 * 将所得哈希值与该密钥混合，然后再次应用哈希函数。输出的哈希值长度为 128 位。
 * 使用 MD5 哈希函数计算基于哈希值的消息验证代码 (HMAC)。
       在发送方和接收方共享机密密钥的前提下，HMAC 可用于确定通过不安全信道发送的消息是否已被篡改。发送方计算原始数据的哈希值，
        并将原始数据和哈希值放在一个消息中同时传送。接收方重新计算所接收消息的哈希值，并检查计算所得的 HMAC 是否与传送的 HMAC 匹配。
        因为更改消息和重新生成正确的哈希值需要密钥，
        所以对数据或哈希值的任何更改都会导致不匹配。因此，如果原始的哈希值与计算得出的哈希值相匹配，则消息通过身份验证。
   MD5（消息摘要算法 5）是 RSA Laboratories 开发的加密哈希算法。HMACMD5 接受任何大小的密钥，并生成长度为 128 位的哈希序列。
 * @version    : v1.0
 * Create Date : 2016年11月16日 下午4:26:57
 */
public class HmacMD5 {

	public HmacMD5() {

	}

	public String byteArrayToString(byte[] tk, int iLen) {
		byte[] tk1;
		tk1 = new byte[iLen * 2 + 1];
		int i, iTemp;
		byte bTemp;
		String strTemp;

		for (i = 0; i < iLen; i++) {
			iTemp = tk[i];
			if (tk[i] < 0) {
				iTemp = tk[i] + 256;
			} else {
				iTemp = tk[i];
			}

			// produce first character
			bTemp = (byte) (iTemp / 16);
			if (bTemp < 10) {
				tk1[i * 2] = (byte) (bTemp + '0');
			} else {
				tk1[i * 2] = (byte) (bTemp - 10 + 'A');
			}

			// produce second character
			bTemp = (byte) (iTemp % 16);
			if (bTemp < 10) {
				tk1[i * 2 + 1] = (byte) (bTemp + '0');
			} else {
				tk1[i * 2 + 1] = (byte) (bTemp - 10 + 'A');
			}
		}
		tk1[iLen * 2] = 0;
		strTemp = new String(tk1);
		return strTemp;
	}

	public byte[] stringToByteArray(String strHex, int iLen) {
		byte[] tk;
		tk = new byte[iLen];
		int i;
		int bTemp, bValue;
		int strLen;
		strLen = strHex.length();
		if (strLen < 2 * iLen) {
			;// throw a exception
		}
		// convert
		for (i = 0; i < iLen; i++) {
			// process first character
			bValue = (int) strHex.charAt(i * 2);
			if (('0' <= bValue) && (bValue <= '9')) {
				bTemp = (bValue - '0') * 16;
			} else if (('a' <= bValue) && (bValue <= 'f')) {
				bTemp = (bValue - 'a' + 10) * 16;
			} else if (('A' <= bValue) && (bValue <= 'F')) {
				bTemp = (bValue - 'A' + 10) * 16;
			} else {
				bTemp = 0;
				;// throw an exception
			}
			// process second character
			bValue = (int) strHex.charAt(i * 2 + 1);

			if (('0' <= bValue) && (bValue <= '9')) {
				bTemp += (bValue - '0');
			} else if (('a' <= bValue) && (bValue <= 'f')) {
				bTemp += (bValue - 'a' + 10);
			} else if (('A' <= bValue) && (bValue <= 'F')) {
				bTemp += ((bValue - 'A' + 10));
			} else {
				;// throw an exception
			}
			if (bTemp > 128) {
				bTemp = bTemp - 256;
			}
			// set to byte array
			tk[i] = (byte) bTemp;
		}
		return tk;
	}
	public String generateDigest(String str, String str1) {
		byte[] KeyBytes;
		byte[] KeyBytes1;
		byte[] Temp;
		MessageDigest Digest, Digest1;

		int key_len = 0;
		int data_len = 0;
		int i = 0;
		byte[] k_ipad, k_opad, tk, tk1;

		k_ipad = new byte[64];
		k_opad = new byte[64];
		tk = new byte[64];
		tk1 = new byte[64];

		key_len = str1.length();
		KeyBytes = new byte[key_len];
		KeyBytes = str1.getBytes();
		Temp = new byte[key_len];

		if (key_len > 64) {
			try {
				Digest = MessageDigest.getInstance("MD5");
				Digest.update(KeyBytes);
				tk = Digest.digest();
				key_len = 16;
				for (i = 0; i < 16; i++) {
					KeyBytes[i] = tk[i];
				}
			} catch (NoSuchAlgorithmException ex) {

				ex.printStackTrace();
				return "";
			}
		}

		for (i = 0; i < key_len; i++) {
			Temp[i] = KeyBytes[i];
			KeyBytes[i] ^= 0x36;
			k_ipad[i] = KeyBytes[i];
			Temp[i] ^= 0x5c;
			k_opad[i] = Temp[i];
		}

		while (i < 64) {
			k_ipad[i] = 0x36;
			k_opad[i] = 0x5c;
			i++;
		}

		/* perform inner MD5 */
		try {
			data_len = str.length() / 2;
			KeyBytes1 = new byte[data_len];
			KeyBytes1 = stringToByteArray(str, data_len);

			Digest = MessageDigest.getInstance("MD5");
			Digest.update(k_ipad);
			Digest.update(KeyBytes1);
			// strTemp = new String(Digest.digest());
			tk = Digest.digest();

		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			return "";
		}

		/* perform outer MD5 */
		try {
			Digest1 = MessageDigest.getInstance("MD5");
			Digest1.update(k_opad); /* start with outer pad */
			Digest1.update(tk); /* start with outer pad */
			tk1 = Digest1.digest();

			return (byteArrayToString(tk1, 16));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			return "";
		}
	}
}