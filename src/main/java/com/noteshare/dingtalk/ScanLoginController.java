/******************************************************************************
* Copyright (C) 2019 NoteShare 
* 网站：itnoteshare.com
*****************************************************************************/
package com.noteshare.dingtalk;
/**
 * @Title:ScanLoginController.java
 * @Description:钉钉扫码登录
 * @author:NoteShare
 * @date:2019年10月25日 上午10:54:48
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/dingsanlogin")
public class ScanLoginController {
	/*
	@RequestMapping("/auth")
	@ResponseBody
	public Map<String,String> dingsanlogin(HttpServletRequest request, HttpServletResponse response) throws Exception, SdkInitException{
		HashMap<String,String> resultMap = new HashMap<String,String>();
		String code = request.getParameter("code");
		DefaultDingTalkClient  client = new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/getuserinfo_bycode");
		OapiSnsGetuserinfoBycodeRequest req = new OapiSnsGetuserinfoBycodeRequest();
		req.setTmpAuthCode(code);
		OapiSnsGetuserinfoBycodeResponse res;
		try {
			res = client.execute(req,"dingoabf0oypnnndltfwdc","a23wgr75EM2COfkj1CeFtXnr-r6dXY1YaPKfdlml6ip_4SW8_UEpchOewIuG1zHc");
			String body = res.getBody();
			resultMap.put("result", "success");
			resultMap.put("msg1", body);
			JSONObject json = JSON.parseObject(body);
			JSONObject userInfo = (JSONObject) json.get("user_info");
			String unionid = userInfo.getString("unionid");
			{ 
			    "errcode": 0,
			    "errmsg": "ok",
			    "user_info": {
			        "nick": "张三",
			        "openid": "liSii8KCxxxxx",
			        "unionid": "7Huu46kk"
			    }
			}
			// nick：用户在钉钉上面的昵称
			//unionid 用户在当前开放应用所属企业内的唯一标识
			//openid 用户在当前开放应用内的唯一标识
			
			// 方案一：https://blog.csdn.net/h_x_h_/article/details/82986644
			// 获取access_token
			Map<String, Object> accessTokenMap = AuthHelper.getAccessToken();
			String accessToken = (String) accessTokenMap.get("ACCESSTOKEN");
			
			//根据unionid获取userid
			DingTalkClient client2 = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getUseridByUnionid");
			OapiUserGetUseridByUnionidRequest request2 = new OapiUserGetUseridByUnionidRequest();
			request2.setUnionid(unionid);
			request2.setHttpMethod("GET");
			OapiUserGetUseridByUnionidResponse response2 = client2.execute(request2, accessToken);
			String body2 = response2.getBody();
			resultMap.put("body2", body2);
			System.out.println(body2);
			JSONObject userIdJson = (JSONObject) JSON.parse(body2);
			String userId = userIdJson.getString("userid");
			//使用access_token和userid获取用户详情信息
			CorpUserDetail corpUserDetail = UserHelper.getUser(accessToken, userId);
			System.out.println(corpUserDetail.getName());
			resultMap.put("corpUserDetailName", corpUserDetail.getName());
			
			// 方案二：https://www.cnblogs.com/vicky1018/p/9086171.html
			
			
		} catch (ApiException e) {
			resultMap.put("result", "fail");
			resultMap.put("msg", "授权登录异常");
		}
		return resultMap;
	}*/
}
