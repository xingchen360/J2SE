package com.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Title:BaseJunitTest.java
 * @Description:spring测试信息加载类
 * @author:NoteShare
 * @date:2019年3月13日 上午11:35:04
 */
@RunWith(SpringJUnit4ClassRunner.class)    
@ContextConfiguration(locations = {"classpath*:conf/spring-system-config.xml","classpath*:conf/spring-mybatis.xml","classpath*:conf/platform/spring-base-platform.xml","classpath*:conf/spring-mvc-test.xml"})    
public class BaseJunitTest {

}
