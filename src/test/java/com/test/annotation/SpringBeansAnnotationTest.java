package com.test.annotation;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.BaseJunitTest;

/**
 * @Title:SpringBeansAnnotationTest.java
 * @Description:spring 注解测试
 * @author：NoteShare
 * @date:2019年3月29日 上午9:29:35
 */
public class SpringBeansAnnotationTest extends BaseJunitTest{

	@Autowired
	private ClassA classA;

	@Test
	public void simpleTest() {
		for (int i = 0; i < 3; i++) {
			classA.printClass();
		}
	}
}
