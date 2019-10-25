/******************************************************************************
* Copyright (C) 2019 NoteShare 
* 网站：itnoteshare.com
*****************************************************************************/
package com.noteshare.spring.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Title:SpringContextUtils.java
 * @Description:SpringContextUtils
 * @author:NoteShare
 * @date:2019年10月22日 上午9:30:40
 */
public class SpringContextUtils implements ApplicationContextAware {
	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	public static Object getBean(String name) {
		return context.getBean(name);
	}

	public static HttpServletRequest getCurrentRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static String getRequestIp() {
		HttpServletRequest request = getCurrentRequest();
		return request.getRemoteAddr();
	}

	public static String getRequestIpAndPort() {
		HttpServletRequest request = getCurrentRequest();
		String ip = request.getRemoteAddr();
		int port = request.getRemotePort();
		return ip + ":" + port;
	}

	public static String getServerName() {
		HttpServletRequest request = getCurrentRequest();
		return request.getServerName();
	}
}
