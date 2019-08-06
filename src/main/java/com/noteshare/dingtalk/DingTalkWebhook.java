package com.noteshare.dingtalk;

import java.util.Arrays;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;

/**
 * @Title:DingTalkWebhook.java
 * @Description:webhook测试
 * https://open-doc.dingtalk.com/microapp/serverapi2/qf2nxq
 * @date:2019年4月27日 上午7:20:18
 */
public class DingTalkWebhook {

	public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=da4a195c9a7f4c1f534aa983737626a80399587d7fd780319ffc4ae7a75bc797";

	public static void main(String[] args) throws ApiException {
		webhookTest();
	}

	public static void webhookTest() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient(WEBHOOK_TOKEN);
		OapiRobotSendRequest request = new OapiRobotSendRequest();
		request.setMsgtype("text");
		OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
		text.setContent("测试文本消息");
		request.setText(text);
		OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
		at.setAtMobiles(Arrays.asList("13261303345"));
		request.setAt(at);

		request.setMsgtype("link");
		OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
		link.setMessageUrl("https://www.dingtalk.com/");
		link.setPicUrl("");
		link.setTitle("时代的火车向前开");
		link.setText("这个即将发布的新版本，创始人陈航（花名“无招”）称它为“红树林”。\n" + "而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是“红树林");
		request.setLink(link);

		request.setMsgtype("markdown");
		OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
		markdown.setTitle("杭州天气");
		markdown.setText("#### 杭州天气 @156xxxx8827\n" + "> 9度，西北风1级，空气良89，相对温度73%\n\n"
				+ "> ![screenshot](https://gw.alipayobjects.com/zos/skylark-tools/public/files/84111bbeba74743d2771ed4f062d1f25.png)\n"
				+ "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
		request.setMarkdown(markdown);
		OapiRobotSendResponse response = client.execute(request);
		System.out.println(response.getBody());
	}
}
