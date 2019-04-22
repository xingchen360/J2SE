package com.noteshare.springcore.core;

import org.springframework.core.SpringProperties;
/**
 * SpringProperties一加载就会加载classpath根目录下的spring.properties文件
 */
public class SpringPropertiesTest {
	public static void main(String[] args) {
		ClassLoader cl = SpringProperties.class.getClassLoader();
		System.out.println(cl.getResource("spring.properties").toString());
		String name = SpringProperties.getProperty("name");
		System.out.println(name);
		boolean flag = SpringProperties.getFlag("flag");
		System.out.println(flag);
		SpringProperties.setProperty("sex", "男");
		String sex = SpringProperties.getProperty("sex");
		System.out.println(sex);
	}
}
