package com.noteshare.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


@SuppressWarnings("deprecation")
public class HttpClientUtils_DownDataAndFileDemo {

	public static void main(String[] args) {
		//httpPost();
		download("http://localhost/xmsp/datasend/datasendcontroller/downloadfilebyid/9155180005605470208");
	}

	public static void httpPost() {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost/xmsp/datasend/datasendcontroller/data/datainfo");
		// 设置post请求传递参数
		List<NameValuePair> list = new ArrayList<>();
		list.add(new BasicNameValuePair("username", "anshun"));
		list.add(new BasicNameValuePair("password", "123456"));
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list);
			post.setEntity(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 执行请求并处理响应
		try {
			CloseableHttpResponse response = httpClient.execute(post);
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity != null) {
				System.out.println(EntityUtils.toString(httpEntity, "UTF-8"));
			}
			response.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据url下载文件，保存到filepath中
	 * @param url
	 * @param filepath
	 * @return
	 */
	public static String download(String url) {
		HttpClient client = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			// 设置post请求传递参数
			List<NameValuePair> list = new ArrayList<>();
			list.add(new BasicNameValuePair("username", "anshun"));
			list.add(new BasicNameValuePair("password", "123456"));
			client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			try {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list);
				post.setEntity(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			File file = new File("D:\\test5.rar");
			bis = new BufferedInputStream(is);
			FileOutputStream fis = new FileOutputStream(file);
			bos = new BufferedOutputStream(fis);
			/**
			 * 根据实际运行效果 设置缓冲区大小
			 */
			byte[] buffer = new byte[4096];
			int ch = 0;
			while ((ch = is.read(buffer)) != -1) {
				bos.write(buffer, 0, ch);
			}
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				bos.close();
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
