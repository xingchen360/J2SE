/******************************************************************************
* Copyright (C) 2019 NoteShare 
* 网站：itnoteshare.com
*****************************************************************************/
package com.noteshare.web.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

/**
 * @Title:WebUtil.java
 * @Description:WebUtil
 * @author:NoteShare
 * @date:2019年10月22日 上午9:27:15
 */
@SuppressWarnings("rawtypes")
public class WebUtil {
	public static void traverseParas(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<?> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();

			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
					System.out.println("参数：" + paramName + "=" + paramValue);
					map.put(paramName, paramValue);
				}
			}
		}
	}

	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (name.equals(c.getName())) {
					cookie = c;
					break;
				}
			}
		}
		return cookie;
	}

	public static void traverseCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie c : cookies)
				System.out.println("cookie：" + c.getName() + "=" + c.getValue());
	}

	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0)
			cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	public static void addCookie(HttpServletResponse response, String name, String value) {
		addCookie(response, name, value, 2147483647);
	}

	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map cookieMap = new HashMap();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	public static String getValue(HttpServletRequest request, String name) {
		String value = null;
		if (StringUtils.hasText(request.getParameter(name)))
			value = request.getParameter(name);
		else if ((getCookie(request, name) != null) && (StringUtils.hasText(getCookie(request, name).getValue()))) {
			value = getCookie(request, name).getValue();
		}
		return value;
	}
}
