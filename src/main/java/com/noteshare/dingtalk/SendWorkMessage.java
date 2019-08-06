/*package com.noteshare.dingtalk;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.cloud.ServiceFactory;
import org.apache.camel.util.MessageHelper;

import com.taobao.api.internal.toplink.endpoint.MessageType;

*//**
 * 
 * @Title:SendWorkMessage.java
 * @Description:dingtalk的开发还需要阅读下文档
 * @date:2019年4月27日 上午7:14:21
 *//*
public class SendWorkMessage {
	public static boolean sendWorkMessage(String CORPID, String CORPSECRET, String AGENTID, String toUsers)
			throws Exception {
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd");
		String timeStr = sdfTime.format(new Date()).toString();
		String time = timeStr.substring(11, 16);
		// 获取accesstoken
		String accessToken = GetAccessToken.getAccessToken(CORPID, CORPSECRET);
		MessageBody.LinkBody linkBody = new MessageBody.LinkBody();
		linkBody.setMessageUrl("https://www.baidu.com/");
		linkBody.setPicUrl("@lALOACZwe2Rk");
		linkBody.setTitle("今目标考勤异常 " + time);
		linkBody.setText("亲，您的今目标没有打卡信息,系统已经向纪委发送通知消息，请您及时联系纪委哦！");

		// 部门id列表，多个接收者用|分隔，TO_USER和TO_PARTY二者有一个必填
		String TO_PARTY = "";
		// 发送微应用消息
		LightAppMessageDelivery lightAppMessageDelivery = new LightAppMessageDelivery(toUsers, TO_PARTY, AGENTID);

		// 发送普通消息
		lightAppMessageDelivery.withMessage(MessageType.LINK, linkBody);
		MessageHelper.send(accessToken, lightAppMessageDelivery);
		System.out.println("成功发送文本消息");
		return true;
	}

	public static Receipt send(String accessToken, LightAppMessageDelivery delivery) throws Exception {
		MessageService messageService = ServiceFactory.getInstance().getOpenService(MessageService.class);
		MessageSendResult reulst = messageService.sendToCorpConversation(accessToken, delivery.touser, delivery.toparty,
				delivery.agentid, delivery.msgType, delivery.message);
		Receipt receipt = new Receipt();
		receipt.invaliduser = reulst.getInvaliduser();
		receipt.invalidparty = reulst.getInvalidparty();
		return receipt;
	}
}
*/