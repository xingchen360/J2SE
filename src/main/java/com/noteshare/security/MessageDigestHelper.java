package com.noteshare.security;

import java.security.MessageDigest;
import java.security.Provider;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * 消息摘要帮助类<br/>
 * 默认使用SHA-1算法 BouncyCastleProvider的算法实现
 * @since V0.1.0
 */
public class MessageDigestHelper {

	/** 使用的默认摘要算法 */
	private String algorithm = "SHA-1";

	/** 默认的安全提供程序实现 */
	private Provider provider = new BouncyCastleProvider();

	/** 摘要对象 */
	private MessageDigest messageDigest = null;

	public MessageDigestHelper() {
		init();
	}

	/**
	 * 初始化消息摘要对象
	 * 
	 * @since V0.1.0
	 */
	private void init() {
		try {
			this.messageDigest = MessageDigest.getInstance(algorithm, provider);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 使用指定的算法初始化消息摘要对象
	 * 
	 * @param algorithm
	 */
	public MessageDigestHelper(String algorithm) {
		this.algorithm = algorithm;
		this.init();
	}

	/**
	 * 使用指定的安全提供程序初始化消息摘要对象
	 * 
	 * @param provider
	 */
	public MessageDigestHelper(Provider provider) {
		this.provider = provider;
		this.init();
	}

	/**
	 * 使用指定的算法和安全提供程序初始化消息摘要对象
	 * 
	 * @param algorithm
	 * @param provider
	 */
	public MessageDigestHelper(String algorithm, Provider provider) {
		this.algorithm = algorithm;
		this.provider = provider;
		this.init();
	}

	/**
	 * 以字节数组的方式获得消息的摘要
	 * 
	 * @param message
	 * @since V0.1.0
	 */
	public byte[] getBytesDigest(String message) {
		if (message == null) {
			throw new IllegalArgumentException("无法对空文本提取消息摘要");
		}
		messageDigest.update(message.getBytes());
		return messageDigest.digest();
	}

	/**
	 * 获得字节数组的字节形式的摘要信息
	 * 
	 * @param message
	 * @since V0.1.0
	 */
	public byte[] getBytesDigest(byte[] message) {
		if (message == null) {
			throw new IllegalArgumentException("无法对空文本提取消息摘要");
		}
		messageDigest.update(message);
		return messageDigest.digest();
	}

	/**
	 * 以十六进制字符串的形式获得消息摘要
	 * 
	 * @param message
	 * @since V0.1.0
	 */
	public String getHexStringDigest(String message) {
		return SecurityHelper.toHex(getBytesDigest(message));
	}

	/**
	 * 以十六进制字符串的形式获得消息摘要
	 * 
	 * @param message
	 * @return 返回摘要信息
	 * @since V0.1.0
	 */
	public String getHexStringDigest(byte[] message) {
		return SecurityHelper.toHex(getBytesDigest(message));
	}

	/**
	 * 以Base64编码的方式获得消息摘要
	 * 
	 * @param message
	 * @since V0.1.0
	 */
	public String getBase64StringDigest(String message) {
		return SecurityHelper.toBase64(getBytesDigest(message));
	}

	/**
	 * 以Base64编码的方式获得消息摘要
	 * 
	 * @param message
	 * @since V0.1.0
	 */
	public String getBase64StringDigest(byte[] message) {
		return SecurityHelper.toBase64(getBytesDigest(message));
	}

	public static void main(String[] args) throws Exception {
		MessageDigestHelper helper = new MessageDigestHelper();
		System.out.println(helper.getBase64StringDigest("weblogic"));
		System.out.println(helper.getHexStringDigest("weblogic"));
	}
}
