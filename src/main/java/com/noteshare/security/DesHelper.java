package com.noteshare.security;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * DES算法帮组类 提供DES算法帮助 包括生成密钥，加密和解密
 * DES算法简介：
 * DES算法入口参数:
 * DES算法的入口参数有三个:Key、Data、Mode。其中Key为7个字节共56位,是DES算法的工作密钥;Data为8个字节64位,
 * 是要被加密或被解密的数据;Mode为DES的工作方式,有两种:加密或解密。
 * DES基本原则编辑：
 * DES设计中使用了分组密码设计的两个原则：混淆（confusion）和扩散(diffusion)，其目的是抗击敌手对密码系统的统计分析。
 * 混淆是使密文的统计特性与密钥的取值之间的关系尽可能复杂化，以使密钥和明文以及密文之间的依赖性对密码分析者来说是无法利用的。
 * 扩散的作用就是将每一位明文的影响尽可能迅速地作用到较多的输出密文位中，以便在大量的密文中消除明文的统计结构，
 * 并且使每一位密钥的影响尽可能迅速地扩展到较多的密文位中，以防对密钥进行逐段破译。
 * 算法步骤：
 * DES算法把64位的明文输入块变为64位的密文输出块,它所使用的密钥也是64位（实际用到了56位，第8、16、24、32、40、48、56、64位是校验位， 
 * 使得每个密钥都有奇数个1）
 * @since V0.1.0
 */
public class DesHelper {

	/** 运算法则标示 */
	public static final String ALGORITHM = "DES";

	/** 是否支持.net加密方式 ,默认是否*/
	private boolean isNet = false;

	/** 私人密钥 */
	private String privateKey = "1234567893333";
	
	/** 算法提供对象 */
	private Provider provider = null;

	/**
	 * 构造方法，初始化算法提供对象
	 * @since V0.1.0
	 */
	public DesHelper() {
		this.provider = new BouncyCastleProvider();
	}

	/**
	 * 指定算法提供器
	 * @param provider 算法提供类对象
	 * @since V0.1.0
	 */
	public DesHelper(Provider provider) {
		this.provider = provider;
	}

	/**
	 * @Description: 提供秘钥构造器
	 * @param privateKey 秘钥
	 * @author     : 12345678
	 * Create Date : 2016年11月10日 下午7:29:07
	 * @throws
	 */
	public DesHelper(String privateKey) {
		this.privateKey = privateKey;
		this.provider = new BouncyCastleProvider();
	}
	
	/**
	 * @Description: 是否是.net加密方式对象
	 * @param isNet 是否是.net加密方式
	 * @author     : 陈海新
	 * Create Date : 2016年11月10日 下午7:39:32
	 * @throws
	 */
	public DesHelper(boolean isNet) {
		this.isNet = isNet;
		this.provider = new BouncyCastleProvider();
	}

	/**
	 * 加密
	 * @param src  数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 * @since V0.1.0
	 */
	public byte[] encrypt(byte[] src, Key key) {
		if (key == null) {
			throw new IllegalArgumentException("不能使用空密钥对数据进行加密");
		}
		if (src == null) {
			throw new IllegalArgumentException("不能对空数据进行加密");
		}
		try {
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance(ALGORITHM, this.provider);
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, key);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(src);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 加密
	 * @param src 数据源
	 * @param key  密钥，长度必须是8的倍数
	 * @return 返回加密后的数据，但src或key为null时，抛异常
	 * @since V0.1.0
	 */
	public byte[] encrypt(String src, Key key) {
		if (key == null) {
			throw new IllegalArgumentException("不能使用空密钥对数据进行加密");
		}
		if (src == null) {
			throw new IllegalArgumentException("不能对空数据进行加密");
		}
		return encrypt(src.getBytes(), key);
	}

	/**
	 * 加密
	 * @param src  数据源
	 * @param key  密钥，长度必须是8的倍数
	 * @return 返回加密后的字符串
	 * @since V0.1.0
	 */
	public String encryptString(String src, Key key) {
		return encryptString(src, key, null);
	}

	/**
	 * 加密
	 * @param src  数据源
	 * @return 返回加密后的字符串，如果isNet配置为true，返回.NET方式加密后数据。
	 * @since V0.1.0
	 */
	public String encryptString(String src) {
		return encryptString(src, this.isNet);
	}

	/**
	 * 加密
	 * @param src 源数据
	 * @param isDotNet 不是Net加密方式，boolean类型
	 * @return 返回加密后数据
	 * @since V0.1.0
	 */
	public String encryptString(String src, boolean isDotNet) {
		if (!isDotNet) {
			return encryptString(src, this.generateKey(), null);
		} else {
			return new DesNetHelper().encryptString(src);
		}
	}

	/**
	 * 
	 * @param src 数据源
	 * @param key  密钥，长度必须是8的倍数
	 * @param encoding 字符集（如：UTF-8）
	 * @return 返回加密后数据
	 * @since V0.1.0
	 */
	public String encryptString(String src, Key key, String encoding) {
		// 调用DES进行加密
		byte[] passbyties = encrypt(src, key);
		// 进行Base64编码
		String passText = SecurityHelper.toBase64(passbyties);
		// 转码操作
		if (null != encoding && !"".equals(encoding)) {
			try {
				passText = new String(passText.getBytes(), encoding);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return passText;
	}

	/**
	 * 生成密钥
	 * @return 生成后的密钥对象
	 * @since V0.1.0
	 */
	public SecretKey generateKey() {
		KeyGenerator keyGenerator;
		try {
			keyGenerator = KeyGenerator.getInstance(ALGORITHM, this.provider);
			// 修复SecureRandom在linux下使用相同的种子生成的随机数不同的问题
			// keyGenerator.init(new SecureRandom(privateKey.getBytes()));
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.setSeed(this.privateKey.getBytes());
			keyGenerator.init(sr);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		SecretKey key = keyGenerator.generateKey();
		return key;
	}

	/**
	 * 解密
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 * @since V0.1.0
	 */
	public byte[] decrypt(byte[] src, Key key) {
		if (key == null) {
			throw new IllegalArgumentException("不能使用空密钥对数据进行解密");
		}
		if (src == null) {
			throw new IllegalArgumentException("不能对空数据进行解密");
		}
		try {
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance(ALGORITHM, this.provider);
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, key);
			// 现在，获取数据并解密
			// 正式执行解密操作
			return cipher.doFinal(src);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 密码解密
	 * @param data  被解密数据
	 * @param key   密钥，长度必须是8的倍数
	 * @return 返回解密后数据
	 * @throws Exception
	 */
	public byte[] decrypt(String data, Key key) {
		if (key == null) {
			throw new IllegalArgumentException("不能使用空密钥对数据进行解密");
		}
		if (data == null) {
			throw new IllegalArgumentException("不能对空数据进行解密");
		}
		return decrypt(data.getBytes(), key);
	}

	/**
	 * 密码解密
	 * @param data  密码数据
	 * @param key  密钥，长度必须是8的倍数
	 * @param encoding   字符集（如：UTF-8）
	 * @return 返回解密后数据
	 * @throws Exception
	 * @since V0.1.0
	 */
	public String decryptString(String data, Key key, String encoding) {
		// 进行转码
		if (null != encoding && !"".equals(encoding)) {
			try {
				data = new String(data.getBytes(), encoding);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 进行Base64编码解密
		byte[] btdate = SecurityHelper.base64ToByte(data);
		// 进行DES解密
		byte[] textPass = decrypt(btdate, key);
		return new String(textPass);
	}

	/**
	 * 密码解密
	 * @param data  密码数据
	 * @return key 密钥
	 * @return 返回解密后数据
	 * @throws Exception
	 * @since V0.1.0
	 */
	public String decryptString(String data, Key key) {
		return decryptString(data, key, null);
	}
	
	public String decryptString(String data) {
		if (!this.isNet) {
			return decryptString(data, this.generateKey());
		} else {
			return new DesNetHelper().decryptString(data);
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		// DesHelper helper = new DesHelper();
		// SecurityHelper.genKeyFile(helper.generateKey(),
		// "E:\\NOTESHARE_DES_KEY.keystore");
		// Key key = SecurityHelper.readKeyFromFile(Key.class,
		// "E:\\NOTESHARE_DES_KEY.keystore");
		// Key key = helper.generateKey();
		//
		// byte[] s = helper.encrypt("测试".getBytes(),key);
		//
		// String passTest = SecurityHelper.toBase64(s);
		// System.out.println("加密："+passTest);
		//
		// byte[] t = helper.decrypt(s,key);
		// System.out.println(new String(t));
		//
		// String pass = helper.encryptString("1");
		String netPass = new DesNetHelper().encryptString("C4CA4238A0B923820DCC509A6F75849B");
		// System.out.println("密码："+pass);
		// System.out.println("NET密码："+netPass);
		// String test = helper.decryptString(pass);
		// String netTest = new DesNetHelper().decryptString(netPass);
		//
		// System.out.println("原密码："+test);
		System.out.println("NET原密码：" + netPass);
		java.security.SecureRandom sr = java.security.SecureRandom.getInstance("SHA1PRNG");
		sr.setSeed("NOTESHARE".getBytes());
		System.out.println(sr.nextInt());
	}

	public boolean isNet() {
		return isNet;
	}

	public void setNet(boolean isNet) {
		this.isNet = isNet;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
}
