package com.noteshare.security;


/**
 * 密码加密和解密的帮助类<br/>
 * 对密码进行加密，可以通过配置文件或者方法参数的方式来指定加密方式。<br/>
 * 加密方式有这样几种：CLEAR_TEXT,MD5,DES,DES_NET,MD5_DES_MD5<br/>
 * 默认先使用MD5做消息摘要，然后再使用Des(key:NOTESHARE)进行加密，然后再使用md5做消息摘要。
 * @since         V0.1.0
 */
public class PasswordHelper {

	/** MD5加密方式帮助类 */
	private static final MessageDigestHelper MD5_HELPER = new MessageDigestHelper("MD5");

	/** DES加密方式帮助类 */
	private static final DesHelper DES_HELPER = new DesHelper();

	public static final String CLEAR_TEXT = "CLEAR_TEXT";

	/** 系统密码加密方式配置参数 */
	public static final String SYSTEM_ENCRYPT_CONFIG = "system.password.encrypt.type";

	/** MD5加密类型常量*/
	public static final String MD5 = "MD5";

	/** DES_NET加密类型常量*/
	public static final String DES_NET = "DES_NET";

	/** DES加密类型常量*/
	public static final String DES = "DES";

	/** MD5_DES_MD5加密类型常量*/
	public static final String MD5_DES_MD5 = "MD5_DES_MD5";

	/**
	 * 加密
	 * @param password 密码
	 * @return 返回加密后的字符集
	 * @since    V0.1.0
	 */
	/*
	 * public static String encryptString(String password){ String config =
	 * Configuration.getProperty(SYSTEM_ENCRYPT_CONFIG,MD5_DES_MD5); return
	 * encryptString(password,config); }
	 */

	/**
	 * 加密
	 * @param password 密码
	 * @param encryptType 加密方式
	 * @return 返回加密后的字符集
	 * @throws Exception 
	 * @since    V0.1.0
	 */
	public static String encryptString(String password, String encryptType) throws Exception {
		if (CLEAR_TEXT.equals(encryptType)) {
			return password;

		} else if (MD5.equals(encryptType)) {
			return MD5_HELPER.getHexStringDigest(password);

		} else if (DES.equals(encryptType)) {
			return DES_HELPER.encryptString(password, false);

		} else if (DES_NET.equals(encryptType)) {
			return new DesNetHelper().encryptString(password);

		} else if (MD5_DES_MD5.equals(encryptType)) {
			String md5String = MD5_HELPER.getHexStringDigest(password);
			String desString = new DesNetHelper().encryptString(md5String);
			return MD5_HELPER.getHexStringDigest(desString);
		} else {
			throw new Exception("未知的加密类型[" + encryptType + "]!");
		}
	}

	/**
	 * 密码解密
	 * @param password 密码
	 * @param encryptType 加密方式
	 * @return 返回解密后的字符串
	 * @throws Exception 
	 * @since     V0.1.0
	 */
	public static String dencryptString(String password, String encryptType) throws Exception {
		if (CLEAR_TEXT.equals(encryptType)) {
			return password;
		} else if (MD5.equals(encryptType)) {
			throw new Exception(MD5 + "加密方式不可逆，解密失败!");
		} else if (DES.equals(encryptType)) {
			return DES_HELPER.decryptString(password);
		} else if (DES_NET.equals(encryptType)) {
			return new DesNetHelper().decryptString(password);
		} else if (MD5_DES_MD5.equals(encryptType)) {
			throw new Exception(MD5_DES_MD5 + "加密方式不可逆，解密失败!");
		} else {
			throw new Exception("未知的加密类型[" + encryptType + "]，解密失败!");
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println(encryptString("1", MD5));
	}
}