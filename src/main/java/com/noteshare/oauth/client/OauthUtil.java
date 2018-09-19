package com.noteshare.oauth.client;

import java.security.NoSuchAlgorithmException;

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
 * @Description: Oauth客户端 调用测试案例 问题点记录： 1.需要依赖json包，否则会解析错误
 *               2.如果报关于ssl的问题需要添加处理ssl相关代码，代码中有做说明
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
	public void getToken() throws OAuthProblemException, Exception {
		// 处理ssl问题开始
		javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
		javax.net.ssl.TrustManager tm = new miTM();
		trustAllCerts[0] = tm;
		javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		// 处理ssl问题结束

		clientId = "c21a8cb7c70f4edcae802d165543c714";
		clientSecret = "c0be326a7d8d4f08aa983ba2e5d67b59";
		accessTokenUrl = "https://dev.cityworks.cn/gateway/reception-center/oauth/token";
		redirectUrl = "http://172.16.30.215:8087/BaoAn_YJCL/";
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		try {
			OAuthClientRequest accessTokenRequest = OAuthClientRequest.tokenLocation(accessTokenUrl)
					.setGrantType(GrantType.AUTHORIZATION_CODE).setClientId(clientId).setClientSecret(clientSecret)
					.setScope("read").setCode("FQ7FrY").setRedirectURI(redirectUrl).buildQueryMessage();
			// 去服务端请求access token，并返回响应
			OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);
			// 获取服务端返回过来的access token
			String accessToken = oAuthResponse.getAccessToken();
			System.out.println(accessToken);
		} catch (OAuthSystemException e) {
			e.printStackTrace();
		}
	}

	static class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}

		public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}
	}

	public static void main(String[] args) throws Exception {
		OauthUtil oauthUtil = new OauthUtil();
		try {
			oauthUtil.getToken();
		} catch (OAuthProblemException e) {
			e.printStackTrace();
		}
	}
}