package com.noteshare.apache;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import javax.sound.midi.Soundbank;

public class CommonsCodec {
	
	@Test
	public void Base64() throws UnsupportedEncodingException {//UTF-8
		String encode = Base64.encodeBase64String("noteshare罂粟花".getBytes("GBK"));
		System.out.println("Base64 编码后：" + encode);

		System.out.println("=============================");
		byte[] decodeBase64 = Base64.decodeBase64(encode);
		System.out.println(new String(decodeBase64,"GBK"));
		System.out.println("-------------------");
		
		byte[] decode = Base64.decodeBase64("U29tbnVz");
		String decodestr = new String(decode);  
        System.out.println("Base64 解码后："+decodestr); 
	}
	
	@Test
	public void Hex() throws DecoderException{
		byte[] buff = "noteshare罂粟花".getBytes(/*"utf-8"*/);
        System.out.println(Arrays.toString(buff));
        
        String byte2hex = Hex.encodeHexString(buff);
        System.out.println(byte2hex);

		System.out.println("================");
		System.out.println(byte2hex.toCharArray());
        byte[] hex2byte = Hex.decodeHex(byte2hex.toCharArray());
        System.out.println(Arrays.toString(hex2byte));
        System.out.println(new String(hex2byte));
	}
	
	@Test
	public void DigestUtils(){
		System.out.println(DigestUtils.md5Hex("admin"));
		/*System.out.println(DigestUtils.shaHex("admin"));*/
	}
}
