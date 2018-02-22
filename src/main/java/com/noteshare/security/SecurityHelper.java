package com.noteshare.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;

import org.apache.commons.codec.binary.Base64;

/**
 * 安全助手类 ， 一系列的常用方法 
 * @since     V0.1.0
 */
public class SecurityHelper {
	/**16进制字符集*/
	private static final String HEXINDEX = "0123456789abcdefABCDEF";

	/**
	 * 字节转字符串
	 * @param bytes 字节数组
	 * @return 返回转换后的字符串
	 * @since    V0.1.0
	 */
	public static String toBytesString(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		StringBuilder buffer = new StringBuilder(256);
		for (byte b : bytes) {
			buffer.append((char) b);
		}
		return buffer.toString();
	}

	/**
	 * 字符串转字节
	 * @param str 数据源
	 * @return 返回转换后字节数组
	 * @since    V0.1.0
	 */
	public static byte[] toBytes(String str) {
		if (str == null) {
			return null;
		}
		int length = str.length();
		byte[] bytes = new byte[length];
		for (int i = 0; i < length; i++) {
			bytes[i] = (byte) str.charAt(i);
		}
		return bytes;
	}

	/**
	 * 将字节数组以16进制的方式编码 返回一个16进制字符串
	 * @param  b 字节数组
	 * @return 16进制字符串
	 * @since     V0.1.0
	 */
	public static String toHex(byte[] b) {
		StringBuilder buffer = new StringBuilder(128);
		for (byte i : b) {
			buffer.append(toHex(i));
		}
		return buffer.toString();
	}

	/**
	 * 将单个字节转换为16进制形式字符串
	 * @param in 字节
	 * @return 返回转换后的字符
	 * @since     V0.1.0
	 */
	public static char[] toHex(byte in) {
		char[] out = new char[2];
		int h = 0;
		h = in >> 4 & 0xF;
		out[0] = (char) (h > 9 ? h + 0x37 : h + 0x30);
		h = in & 0xF;
		out[1] = (char) (h > 9 ? h + 0x37 : h + 0x30);
		return out;
	}

	/**
	 * 将字节数组以Base64编码 返回一个字符串 
	 * @param bytes 字节数组
	 * @return 返回编码后的字符串
	 * @since     V0.1.0
	 */
	public static String toBase64(byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}

	/**
	 * 将一个16进制字符串转化为字节数组
	 * @param s 16进制字符串
	 * @return 返回转换后的字节数组
	 * @since     V0.1.0
	 */
	public static byte[] hexToByte(String s) {
		int l = s.length() / 2;
		byte data[] = new byte[l];
		int j = 0;
		for (int i = 0; i < l; i++) {
			char c = s.charAt(j++);
			int n, b;
			n = HEXINDEX.indexOf(c);
			b = (n & 0xf) << 4;
			c = s.charAt(j++);
			n = HEXINDEX.indexOf(c);
			b += (n & 0xf);
			data[i] = (byte) b;
		}
		return data;
	}

	/**
	 * 将一个Base64编码的字符串转化为字节数组
	 * @param s Base64编码字符串
	 * @return 返回转换后的字节数组
	 * @since     V0.1.0
	 */
	public static byte[] base64ToByte(String s) {
		try {
			return Base64.decodeBase64(s);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 从文件中加载密钥 
	 * @param  <T>   密钥类型
	 * @param  cls   
	 * @param  fileName 文件路径和名称
	 * @return   返回密钥
	 * @since     V0.1.0
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Key> T readKeyFromFile(Class<T> cls, String fileName) {
		T key = null;
		File f = new File(fileName);
		if (!f.exists()) {
			throw new IllegalArgumentException("指定的密钥文件[" + fileName + "]不存在");
		}
		ObjectInputStream oos = null;
		try {
			oos = new ObjectInputStream(new FileInputStream(f));
			key = (T) oos.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return key;
	}

	/**
	 * 将密钥写入文件中  
	 * @param  key 密钥
	 * @param  fileName 文件名
	 * @return  返回写入结果
	 * @since     V0.1.0
	 */
	public static boolean genKeyFile(Key key, String fileName) {
		boolean isOk = false;
		ObjectOutputStream oos = null;
		try {
			File f = new File(fileName);
			if (!f.exists()) {
				f.createNewFile();
			}
			oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(key);
			oos.flush();
			isOk = true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isOk;
	}

	/**
	 * 自动生成RSA密钥文件，并以指定的文件名称保存在文件中 
	 * @param pubKeyFileName 公钥文件名称
	 * @param priKeyFileName 私钥文件名
	 * @since      V0.1.0
	 */
	public static boolean genRsaKeyToFile(String pubKeyFileName, String priKeyFileName) {
		RsaHelper helper = new RsaHelper();
		KeyPair pair = helper.genKeyPair();
		boolean v1 = genKeyFile(pair.getPublic(), pubKeyFileName);
		boolean v2 = genKeyFile(pair.getPrivate(), priKeyFileName);
		return v1 && v2;
	}

	/**
	 * 自动生成DSA密钥文件，并以指定的文件名称保存在文件中 
	 * @param   pubKeyFileName 公钥文件名
	 * @param   priKeyFileName 密钥文件名
	 * @since      V0.1.0
	 */
	public static boolean genDsaKeyToFile(String pubKeyFileName, String priKeyFileName) {
		DsaHelper helper = new DsaHelper();
		KeyPair pair = helper.genKeyPair();
		boolean v1 = genKeyFile(pair.getPublic(), pubKeyFileName);
		boolean v2 = genKeyFile(pair.getPrivate(), priKeyFileName);
		return v1 && v2;
	}

	/**
	 * 自动生成DES密钥文件，并以指定的文件名称保存在文件中 
	 * @param  keyFileName  密钥文件名
	 * @return  返回生成的结果
	 * @since     V0.1.0
	 */
	public static boolean genDesKeyToFile(String keyFileName) {
		DesHelper helper = new DesHelper();
		Key key = helper.generateKey();
		return genKeyFile(key, keyFileName);
	}

	public static void main(String[] args) throws Exception {
		Certificate clientCert = loadCertificate("E:\\证书\\client.cer", null);
		Certificate serverCert = loadCertificate("E:\\证书\\server.cer", null);
		System.out.println(SecurityHelper.toBase64(clientCert.getPublicKey().getEncoded()));
		System.out.println(SecurityHelper.toBase64(serverCert.getPublicKey().getEncoded()));
		loadPrivateKey("E:\\证书\\server.keystore", "server", "xx_2009");
	}

	public static Certificate loadCertificate(String path) {
		return loadCertificate(path, "X.509");
	}

	public static Certificate loadCertificate(String path, String format) {
		if ((format == null) || ("".equals(format.trim()))) {
			format = "X.509";
		}
		Certificate cert = null;
		try {
			FileInputStream is = new FileInputStream(path);
			CertificateFactory cf = CertificateFactory.getInstance(format);
			cert = cf.generateCertificate(is);
			is.close();
			return cert;
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从keystore文件中加载PrimaryKey
	 * @param   path 文件路径
	 * @param   alias
	 * @param   password 密码
	 * @return   返回私钥
	 * @since     V0.1.0
	 */
	public static PrivateKey loadPrivateKey(String path, String alias, String password) {
		PrivateKey key = null;
		try {
			FileInputStream fis = new FileInputStream(path);
			KeyStore ks = KeyStore.getInstance("JKS");
			ks.load(fis, password.toCharArray());
			key = (PrivateKey) ks.getKey(alias, password.toCharArray());
			return key;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
