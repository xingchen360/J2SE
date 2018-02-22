package com.noteshare.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DesNetHelper {
	
	/**私钥,默认私钥是12345678
	 * Wrong IV length: must be 8 bytes long
	 * */
	private String privateKey = "12345678";
	
	public DesNetHelper() {
		
	}
	/**
	 * @Description: 加密构造器
	 * @param privateKey： Wrong IV length: must be 8 bytes long
	 * @author     : NOTESHARE
	 * Create Date : 2016年11月10日 下午7:43:54
	 * @throws
	 */
	public DesNetHelper(String privateKey){
		this.privateKey = privateKey;
	}

	/**
	 * 密码转Hex进制输出
	 * @param b 密码
	 * @return 返回转换后的编码字符
	 * @since V0.1.0
	 */
	public String toHexString(byte b[]) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String plainText = Integer.toHexString(0xff & b[i]);
			if (plainText.length() < 2)
				plainText = "0" + plainText;
			hexString.append(plainText);
		}
		return hexString.toString();
	}

	/**
	 * 加密操作
	 * @param message 数据源
	 * @param key 密钥
	 * @return 返回加密后的数据
	 * @throws Exception
	 * @since V0.1.0
	 */
	public byte[] encrypt(String message, String key) throws Exception {
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
		return cipher.doFinal(message.getBytes("UTF-8"));
	}

	/**
	 * 解密算法
	 * @param message 密码
	 * @param key 密钥
	 * @return 返回解密后数据
	 * @throws Exception
	 * @since V0.1.0
	 */
	public String decrypt(String message, String key) throws Exception {
		byte[] bytesrc = convertHexString(message);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		byte[] retByte = cipher.doFinal(bytesrc);
		return new String(retByte);
	}

	/**
	 * 将Hex进制转换成字节
	 * @param ss Hex进制字符
	 * @return 返回转换后字节数字
	 * @since V0.1.0
	 */
	public byte[] convertHexString(String ss) {
		byte digest[] = new byte[ss.length() / 2];
		for (int i = 0; i < digest.length; i++) {
			String byteString = ss.substring(2 * i, 2 * i + 2);
			int byteValue = Integer.parseInt(byteString, 16);
			digest[i] = (byte) byteValue;
		}
		return digest;
	}

	/**
	 * 加密 
	 * @param data 数据源
	 * @return 返回加密后的字符
	 * @since V0.1.0
	 */
	public String encryptString(String data) {
		try {
			String jiami = java.net.URLEncoder.encode(data, "utf-8").toLowerCase();
			return toHexString(encrypt(jiami, this.privateKey)).toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("加密[" + data + "]失败！");
		}
	}

	/**
	 * 解密
	 * @param data 数据源
	 * @return 返回解密后数据
	 * @since V0.1.0
	 */
	public String decryptString(String data) {
		try {
			return java.net.URLDecoder.decode(decrypt(data, this.privateKey), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解密[" + data + "]失败！");
		}
	}

	public String getprivateKey() {
		return this.privateKey;
	}
	/**
	 * @Description: privateKey 
	 * @param privateKey：Wrong IV length: must be 8 bytes long
	 * @author     : NOTESHARE
	 * Create Date : 2016年11月10日 下午7:44:51
	 * @throws
	 */
	public void setprivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	
	public static void main(String[] args) {
		DesNetHelper des = new DesNetHelper();
		String password = des.encryptString("123456");
		System.out.println(password);
		DesHelper des2 = new DesHelper(false);
		String password2 = des2.encryptString("123456");
		System.out.println(password2);
		DesHelper des3 = new DesHelper(true);
		String password3 = des3.encryptString("123456");
		System.out.println(password3);
		DesHelper des43 = new DesHelper("我爱你");
		String password4 = des43.encryptString("123456");
		System.out.println(password4 + "===");
		
		String mw = des43.decryptString(password4);
		System.out.println(mw);
	}
}
