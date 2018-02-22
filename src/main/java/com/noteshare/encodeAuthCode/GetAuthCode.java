package com.noteshare.encodeAuthCode;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
@SuppressWarnings("deprecation")
public class GetAuthCode {
	private static final HttpClient client = new DefaultHttpClient();
	/**
	  * @Title: getCode 
	  * @Description: 下载验证码
	  * @param imagePath 验证请求路径
	  * @param params 验证码请求参数
	  * @param path 验证码保存文件夹路径
	  * @param postfix 验证码后缀
	  * @throws Exception void
	  * @throws
	 */
	public void getCode(String imagePath,List<NameValuePair> params,String path,String postfix) throws Exception{
		HttpPost httppost = null;
		try {
			httppost = new HttpPost(imagePath);
			httppost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
			HttpResponse response = client.execute(httppost);
			InputStream is = response.getEntity().getContent();
			FileOutputStream fos = new FileOutputStream(path + File.separator +new Date().getTime() + postfix);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			byte[] b = new byte[1024];
			int len = 0;
			while((len = is.read(b, 0, 1024)) != -1){
				bos.write(b, 0, len);
			}
			is.close();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			httppost.releaseConnection();
		}
	}
	
	
	
	
	public static void main(String[] args) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("method", "code"));
		params.add(new BasicNameValuePair("temp", new Date().getTime() + ""));
		String imagePath = "http://218.93.18.218:8011/xmsb/initIndex.do?method=code&temp=";
		try {
			new GetAuthCode().getCode(imagePath,params,"d:\\code",".jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
