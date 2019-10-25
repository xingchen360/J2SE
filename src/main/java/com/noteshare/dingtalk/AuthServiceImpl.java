/******************************************************************************
* Copyright (C) 2019 NoteShare Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为itnoteshare.com开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/
package com.noteshare.dingtalk;

import org.springframework.stereotype.Service;

/**
 * @Title:AuthServiceImpl.java
 * @Description:认证服务
 * @author:NoteShare
 * @date:2019年7月24日 上午9:11:26
 */
@Service
public class AuthServiceImpl {

	/*@Autowired
	private AccessTokenDAO accessTokenDAO;
	
	@Override
	public Map<String, Object> sign(String url,String queryStr) throws AuthException {
		// 获取accessToken对象信息
		Map<String, Object> accessTokenBean = AuthHelper.getAccessToken(accessTokenDAO);
		//获取前端鉴权的tiket
		String ticket = AuthHelper.getJsapiTicket(accessTokenBean, accessTokenDAO);
		//获取签名和前端需要的配置信息
		Map<String, Object> config = AuthHelper.sign(ticket,url,queryStr);
		return config;
	}*/
}
