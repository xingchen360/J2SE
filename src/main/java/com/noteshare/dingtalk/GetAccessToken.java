package com.noteshare.dingtalk;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;

public class GetAccessToken {
	
	public static void main(String[] args) throws ApiException {
		String access_token = GetAccessToken.getAccessToken("dingyyj4npod1bp5arkp","9B0ab-4IQisnvwK23KuAuIZtYykXopynI5vGj4y5XFwalnGcfqN2kYVUCgDhJE_y");
		System.out.println(access_token);
	}
	
	public static String getAccessToken(String corpid,String corpsecret) throws ApiException{
		DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
		OapiGettokenRequest request = new OapiGettokenRequest();
		request.setAppkey(corpid);
		request.setAppsecret(corpsecret);
		request.setHttpMethod("GET");
		OapiGettokenResponse response = client.execute(request);
		return response.getAccessToken();
	}
}
