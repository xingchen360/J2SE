package com.noteshare.security;

 
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * DSA算法支持类 
 * 提供生成密钥对、签名和验证的帮助 默认使用BouncyCastleProvider的安全提供程序    
 * @since         V0.1.0
 */
public class DsaHelper {  
    /**使用的算法*/
    public static final String ALGORITHM = "DSA";
    /**密钥大小*/
    public static final int KEY_SIZE = 1024; 
    private Provider provider = new BouncyCastleProvider();
    
    public DsaHelper(){
    	
    }
    
    /**
     * 构造方法，初始化提供程序
     * @param provider
     */
    public DsaHelper(Provider provider){
        this.provider = provider;  
    }
    
    /**
     * 生成密钥对
     * @return 返回密钥对
     * @since     V0.1.0
     */
    public KeyPair genKeyPair() { 
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM,this.provider);  
            //没什么好说的了，这个值关系到块加密的大小，可以更改，但是不要太大，否则效率会低 
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            KeyPair keyPair = keyPairGen.genKeyPair();    
            return keyPair;    
        } catch (Exception e) {   
            throw new RuntimeException(e.getMessage(),e);   
        }  
    }  
    
    /**
     * 签名
     * @param  bytes 数据源
     * @param  privateKey 密钥
     * @return  返回数据签名
     * @since     V0.1.0
     */
    public byte[] sign(byte[] bytes,PrivateKey privateKey){   
        if(privateKey == null){
            throw new RuntimeException("无法使用空的私钥对内容进行签名");  
        }
        try {
            Signature signalg = Signature.getInstance(ALGORITHM,this.provider); 
            signalg.initSign(privateKey);    
            signalg.update(bytes);
            return signalg.sign();  
        } catch (Exception e) {
            throw new RuntimeException(e);  
        } 
    }
    /**
     * @Description: 将字节数组以16进制的方式编码 返回一个16进制字符串
     * @param bytes
     * @param privateKey
     * @return 返回一个16进制字符串
     * @author     : NOTESHARE
     * Create Date : 2016年11月16日 下午3:14:44
     * @throws
     */
    public String signHexString(byte[] bytes,PrivateKey privateKey){   
        return SecurityHelper.toHex(sign(bytes,privateKey));  
    }
    /**
     * @Description: 将字节数组以16进制的方式编码 返回一个16进制字符串
     * @param content
     * @param privateKey
     * @return 将字节数组以16进制的方式编码 返回一个16进制字符串
     * @author     : NOTESHARE
     * Create Date : 2016年11月16日 下午3:16:31
     * @throws
     */
    public String signHexString(String content,PrivateKey privateKey){   
        if(content == null){ 
            throw new IllegalArgumentException("无法对空内容进行签名");
        }  
        return SecurityHelper.toHex(sign(content.getBytes(),privateKey)); 
    } 
    /**
     * @Description: 将字节数组以Base64编码 返回一个字符串
     * @param bytes
     * @param privateKey
     * @return 将字节数组以Base64编码 返回一个字符串
     * @author     : NOTESHARE
     * Create Date : 2016年11月16日 下午3:15:26
     * @throws
     */
    public String signBase64String(byte[] bytes,PrivateKey privateKey){   
        return SecurityHelper.toBase64(sign(bytes,privateKey));  
    }
    /**
     * @Description: 将字符串以Base64编码 返回一个字符串
     * @param content
     * @param privateKey
     * @return 将字符串以Base64编码 返回一个字符串
     * @author     : NOTESHARE
     * Create Date : 2016年11月16日 下午3:16:01
     * @throws
     */
    public String signBase64String(String content,PrivateKey privateKey){     
        if(content == null){
            throw new IllegalArgumentException("无法对空内容进行签名");
        }  
        return SecurityHelper.toBase64(sign(content.getBytes(),privateKey)); 
    }  
    /**
     * 验证
     * @param bytes  核实数据
     * @param content 更新内容
     * @param publicKey 公钥
     * @return 返回验证结果
     * @since     V0.1.0
     */
    public boolean verify(byte[] bytes, byte[] content,PublicKey publicKey) {
        if(publicKey == null){  
            throw new IllegalArgumentException("请指定验证使用的公钥"); 
        }
        try {
            Signature verifyalg = Signature.getInstance(ALGORITHM,this.provider);   
            verifyalg.initVerify(publicKey);  
            verifyalg.update(content);     
            return verifyalg.verify(bytes); 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean verifyHexSign(String strHexSign,String content,PublicKey publicKey){
        if(strHexSign == null){  
            throw new IllegalArgumentException("无法验证：签名为空!");             
        }
        return verify(SecurityHelper.hexToByte(strHexSign),content.getBytes(),publicKey);   
    } 
     
    public boolean verifyBase64Sign(String strBase64Sign,String content,PublicKey publicKey){
        if(strBase64Sign == null){
            throw new IllegalArgumentException("无法验证：签名为空!");      
        }
        return verify(SecurityHelper.base64ToByte(strBase64Sign),content.getBytes(),publicKey);         
    }
    
    public boolean verifyHexSign(String strHexSign,byte[] content,PublicKey publicKey){
        if(strHexSign == null){  
            throw new IllegalArgumentException("无法验证：签名为空!");             
        }
        return verify(SecurityHelper.hexToByte(strHexSign),content,publicKey);   
    } 
     
    public boolean verifyBase64Sign(String strBase64Sign,byte[] content,PublicKey publicKey){
        if(strBase64Sign == null){
            throw new IllegalArgumentException("无法验证：签名为空!");          
        }
        return verify(SecurityHelper.base64ToByte(strBase64Sign),content,publicKey);          
    }
    
    public static void main(String[] args) { 
        Provider[] providers = Security.getProviders();
        for(Provider provider : providers){
            System.out.println(provider.getName()); 
        }
        testSign(); 
    }
    
    
    
    public static void testSign(){
        long s1 = System.currentTimeMillis();
        DsaHelper helper = new DsaHelper();
        PrivateKey privateKey = SecurityHelper.readKeyFromFile(PrivateKey.class, "E:\\dsa_private.keystore");
        PublicKey publicKey = SecurityHelper.readKeyFromFile(PublicKey.class, "E:\\dsa_public.keystore");            
        long s2 = System.currentTimeMillis();
        System.out.println("生成密钥耗时:" + (s2 - s1));
        String content = "测试";
        String hexSign = helper.signHexString(content,privateKey);
        long s3 = System.currentTimeMillis();
        System.out.println("使用Hex方式签名耗时:" + (s3 - s2));
        System.out.println("Hex签名是：" + hexSign ); 
        System.out.println("验证是否通过：" + helper.verifyHexSign(hexSign, content, publicKey));
        long s4 = System.currentTimeMillis();
        System.out.println("使用Hex方式验证耗时:" + (s4 - s3));
        
        
        String base64Sign = helper.signBase64String(content,privateKey);  
        long s5 = System.currentTimeMillis();
        System.out.println("使用Base64方式签名耗时:" + (s5 - s4)); 
        System.out.println("Base64签名是：" + base64Sign );       
        System.out.println("验证是否通过：" + helper.verifyBase64Sign(base64Sign, content, publicKey));
        long s6 = System.currentTimeMillis();
        System.out.println("使用Hex方式验证耗时:" + (s6 - s4));
        System.out.println("共计：" + (s6 - s1)); 
    }
} 
