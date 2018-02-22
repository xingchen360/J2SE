package com.noteshare.security;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.io.*;
import java.math.BigInteger;

/**
 * Rsa安全算法帮助类 <br/>
 * 包含了密钥生成、加密、解密的方法
 * @since          V0.1.0
 */
public class RsaHelper {

	/**密钥字节数 默认为1024*/
	public static final int KEY_SIZE = 1024;

	/**加密使用的算法*/
	public static final String ALGORITHM = "RSA";

	/**签名*/
	public static final String SIGN_ALGORITHM = "SHA1withRSA";

	/**默认的安全提供程序*/
	private Provider provider = new BouncyCastleProvider();

	public RsaHelper() {
	}

	/**
	 * 指定安全提供程序
	 * @param provider 提供程序对象
	 */
	public RsaHelper(Provider provider) {
		this.provider = provider;
	}

	/**
	* 生成密钥对
	* @return   返回密钥对   
	* @since     V0.1.0
	*/
	public KeyPair genKeyPair() {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM, this.provider);
			// 没什么好说的了，这个值关系到块加密的大小，可以更改，但是不要太大，否则效率会低
			keyPairGen.initialize(KEY_SIZE, new SecureRandom());
			KeyPair keyPair = keyPairGen.genKeyPair();
			return keyPair;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 生成公钥 
	 * @param modulus 
	 * @param publicExponent
	 * @return RSAPublicKey
	 * @throws EncryptException 
	 */
	public RSAPublicKey genRsaPublicKey(byte[] modulus, byte[] publicExponent) {
		KeyFactory keyFacctory = null;
		try {
			keyFacctory = KeyFactory.getInstance(ALGORITHM, new BouncyCastleProvider());
		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
		try {
			return (RSAPublicKey) keyFacctory.generatePublic(pubKeySpec);
		} catch (InvalidKeySpecException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}

	/**
	 * 生成私钥 
	 * @param modulus
	 * @param privateExponent
	 * @return RSAPrivateKey  
	 * @throws EncryptException 
	 */
	public RSAPrivateKey genRsaPrivateKey(byte[] modulus, byte[] privateExponent) {
		KeyFactory keyFactory = null;
		try {
			keyFactory = KeyFactory.getInstance(ALGORITHM, new BouncyCastleProvider());
		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException(ex.getMessage());
		}

		RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));
		try {
			return (RSAPrivateKey) keyFactory.generatePrivate(priKeySpec);
		} catch (InvalidKeySpecException ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * 加密操作 
	 * @param key
	 * @param data
	 * @since     V0.1.0
	 */
	public byte[] encrypt(PublicKey key, byte[] data) {
		if (key == null) {
			throw new IllegalArgumentException("无法执行加密操作,请指定公钥");
		}
		if (data == null) {
			throw new IllegalArgumentException("无法执行加密操作,不能对空数据进行加密");
		}
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM, new BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, key);
			// 获得加密块大小，如：加密前数据为128个byte，而key_size=1024
			// 加密块大小为127
			// byte,加密后为128个byte;因此共有2个加密块，第一个127
			// byte第二个为1个byte
			int blockSize = cipher.getBlockSize();
			// 获得加密块加密后块大小
			int outputSize = cipher.getOutputSize(data.length);

			int leavedSize = data.length % blockSize;

			int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
			byte[] raw = new byte[outputSize * blocksSize];
			int i = 0;
			while (data.length - i * blockSize > 0) {
				if (data.length - i * blockSize > blockSize) {
					cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
				} else {
					cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
				}
				// 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]
				// 放到ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，
				// 可是到了此时加密块大小很可能已经超出了OutputSize所以只好用dofinal方法。
				i++;
			}
			return raw;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public byte[] encrypt(PublicKey publicKey, String data) {
		if (publicKey == null) {
			throw new IllegalArgumentException("无法执行加密操作,请指定公钥");
		}
		if (data == null) {
			throw new IllegalArgumentException("无法执行加密操作,不能对空数据进行加密");
		}
		return encrypt(publicKey, data.getBytes());
	}

	/**
	 * 解密
	 * @param key 解密的密钥
	 * @param raw 已经加密的数据
	 * @return 解密后的明文
	 * @throws EncryptException
	 * @since    V0.1.0  
	 */
	public byte[] decrypt(PrivateKey key, byte[] raw) {
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM, new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, key);
			int blockSize = cipher.getBlockSize();
			ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
			int j = 0;
			while (raw.length - j * blockSize > 0) {
				bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
				j++;
			}
			return bout.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public byte[] sign(byte[] bytes, PrivateKey privateKey) {
		if (privateKey == null) {
			throw new RuntimeException("无法使用空的私钥对内容进行签名");
		}
		try {
			Signature signalg = Signature.getInstance(SIGN_ALGORITHM, this.provider);
			signalg.initSign(privateKey);
			signalg.update(bytes);
			return signalg.sign();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String signHexString(byte[] bytes, PrivateKey privateKey) {
		return SecurityHelper.toHex(sign(bytes, privateKey));
	}

	public String signBase64String(byte[] bytes, PrivateKey privateKey) {
		return SecurityHelper.toBase64(sign(bytes, privateKey));
	}

	public boolean verify(byte[] bytes, byte[] content, PublicKey publicKey) {
		if (publicKey == null) {
			throw new IllegalArgumentException("请指定验证使用的公钥");
		}
		try {
			Signature verifyalg = Signature.getInstance(SIGN_ALGORITHM, this.provider);
			verifyalg.initVerify(publicKey);
			verifyalg.update(content);
			return verifyalg.verify(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean verifyHexSign(String strHexSign, String content, PublicKey publicKey) {
		if (strHexSign == null) {
			throw new IllegalArgumentException("无法验证：签名为空!");
		}
		return verify(SecurityHelper.hexToByte(strHexSign), content.getBytes(), publicKey);
	}

	public boolean verifyBase64Sign(String strBase64Sign, String content, PublicKey publicKey) {
		if (strBase64Sign == null) {
			throw new IllegalArgumentException("无法验证：签名为空!");
		}
		return verify(SecurityHelper.base64ToByte(strBase64Sign), content.getBytes(), publicKey);
	}

	public boolean verifyHexSign(String strHexSign, byte[] content, PublicKey publicKey) {
		if (strHexSign == null) {
			throw new IllegalArgumentException("无法验证：签名为空!");
		}
		return verify(SecurityHelper.hexToByte(strHexSign), content, publicKey);
	}

	public boolean verifyBase64Sign(String strBase64Sign, byte[] content, PublicKey publicKey) {
		if (strBase64Sign == null) {
			throw new IllegalArgumentException("无法验证：签名为空!");
		}
		return verify(SecurityHelper.base64ToByte(strBase64Sign), content, publicKey);
	}

	public static void main(String[] args) throws Exception {
		testAll();
	}

	private static void testAll() {
		RsaHelper helper = new RsaHelper();
		// PublicKey publicKey =
		// SecurityHelper.readKeyFromFile(RSAPublicKey.class,
		// "E:\\rsa_public_20090425.keystore");
		// PrivateKey privateKey =
		// SecurityHelper.readKeyFromFile(RSAPrivateKey.class,
		// "E:\\rsa_private_20090425.keystore");
		PublicKey publicKey = SecurityHelper.loadCertificate("E:\\证书\\server.cer").getPublicKey();
		PrivateKey privateKey = SecurityHelper.loadPrivateKey("E:\\证书\\server.keystore","server", "noteshare_2009");
		// 客户端请求部分
		String clientMessage = "你好，我想获得XX的信息";
		String base64ClientMessage = SecurityHelper.toBase64(helper.encrypt(publicKey, clientMessage));
		System.out.println("解密前的消息是：" + base64ClientMessage);
		String recivedClientMessage = new String(helper.decrypt(privateKey, SecurityHelper.base64ToByte(base64ClientMessage)));
		System.out.println("服务器端接受到的消息是:" + recivedClientMessage);
		// 服务器响应部分
		String serverMessage = "这是服务器端的消息：你好，返回您XX的信息：男xx岁";
		String sign = SecurityHelper.toBase64(helper.sign(serverMessage.getBytes(), privateKey));
		// serverMessage = "这是服务器端的消息：你好，返回您XX的信息：男xx岁 ";
		System.out.println("服务器响应的数字签名是：" + sign);
		if (helper.verifyBase64Sign(sign, serverMessage, publicKey)) {
			System.out.println("通过验证，服务器返回的消息是：\n\t" + serverMessage);
		} else {
			System.out.println("没有通过验证，服务器返回的消息是：\n\t" + serverMessage);
		}
	}
}
