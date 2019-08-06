package com.noteshare.webhook;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/**
 * @Title:ChatbotSend.java
 * @Description:webhook聊天测试
 * @author:NoteShare
 * @date:2019年4月25日 上午9:04:39
 */
public class ChatbotSend {
	//webhook 地址
	public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=da4a195c9a7f4c1f534aa983737626a80399587d7fd780319ffc4ae7a75bc797";
	
	public static void main(String[] args) throws Exception, IOException {
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(WEBHOOK_TOKEN);
		httpPost.addHeader("Content-Type","application/json;charset=utf-8");
		//构建一个json格式字符串textMSG，其内容是接收方需要的参数和消息内容
		// atMobiles:根据手机号 @人
		// isAtAll:是否@所有
		String textMSG = "{\"msgtype\":\"text\",\"text\":{\"content\":\"你好，我是机器人webhook\"},\"at\":{\"atMobiles\":[\"18173920525\"],\"isAtAll\":true}}";
		StringEntity se = new StringEntity(textMSG,"utf-8");
		httpPost.setEntity(se);
		HttpResponse response = httpclient.execute(httpPost);
		if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			String result = EntityUtils.toString(response.getEntity(),"utf-8");
			System.out.println(result);
		}
	}
}
