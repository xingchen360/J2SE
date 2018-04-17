/*package com.szboanda.test;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;

暂时测试的跨域携带cookie没成功，后续有机会再研究下怎么模拟跨域登录
public class CookiLogin  {

	public static String login(HttpServletResponse response) {
		//String domain = ".ekm.powerdata.com.cn";
		// 登陆 Url
		String loginUrl = "http://localhost:9000/ppure/login.do";
		// 需登陆后访问的 Url
		String gotoUrl = "http://localhost:9000/ppure/index.jsp";
		HttpClient httpClient = new HttpClient();
		// 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
		PostMethod postMethod = new PostMethod(loginUrl);
		// 设置登陆时要求的信息，用户名和密码
		// new NameValuePair("org.apache.struts.taglib.html.TOKEN",
		// "a36810e273ae6e93dbd846ad78fa0d25"),
		NameValuePair[] data = { new NameValuePair("needValicode", "false"),new NameValuePair("method", "authenticate"),new NameValuePair("userid", "SYSTEM"), new NameValuePair("password", "123456")};
		postMethod.setRequestBody(data);
		try {
			// 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
			httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
			int statusCode = httpClient.executeMethod(postMethod);
			// 获得登陆后的 Cookie
			Cookie[] cookies = httpClient.getState().getCookies();
			StringBuffer tmpcookies = new StringBuffer();
			for (Cookie c : cookies) {
				javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(c.getName(), c.getValue());
				response.addCookie(cookie);
				tmpcookies.append(c.toString() + ";");
			}
			System.out.println("cookies = " + tmpcookies.toString());
			if (statusCode == 302) {// 重定向到新的URL
				System.out.println("模拟登录成功");
				//重定向到gotoUrl
				response.sendRedirect(gotoUrl);
			} else {
				System.out.println("登录失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + loginUrl;
	}
}
*/