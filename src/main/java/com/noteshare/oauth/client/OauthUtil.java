package com.noteshare.oauth.client;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

/**
 * @ClassName: ClientController
 * @Description: Oauth客户端
 * @author NoteShare
 * @date 2018年9月18日
 */

public class OauthUtil {
	String clientId = null;
	String clientSecret = null;
	String accessTokenUrl = null;
	String userInfoUrl = null;
	String redirectUrl = null;
	String response_type = null;
	String code = null;
	// 接受客户端返回的code，提交申请access token的请求，对应上图中的步骤三
	public void getToken() throws OAuthProblemException {
		clientId = "c21a8cb7c70f4edcae802d165543c714";
		clientSecret = "c0be326a7d8d4f08aa983ba2e5d67b59";
		accessTokenUrl = "https://dev.cityworks.cn/gateway/reception-center/oauth/token";
		redirectUrl = "http://172.16.30.29:8080/hjyj_yf/";
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		try {
			OAuthClientRequest accessTokenRequest = OAuthClientRequest.tokenLocation(accessTokenUrl)
					.setGrantType(GrantType.AUTHORIZATION_CODE).setClientId(clientId).setClientSecret(clientSecret).setScope("read")
					.setCode("9ubEhT").setRedirectURI(redirectUrl).buildQueryMessage();
			// 去服务端请求access token，并返回响应
			OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);
			// 获取服务端返回过来的access token
			String accessToken = oAuthResponse.getAccessToken();
			System.out.println(accessToken);
		} catch (OAuthSystemException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		OauthUtil oauthUtil = new OauthUtil();
		try {
			oauthUtil.getToken();
		} catch (OAuthProblemException e) {
			e.printStackTrace();
		}
	}
}