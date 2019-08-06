package com.test.annotation;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * @Title:ClassA.java
 * @Description:注解测试
 * @author:NoteShare
 * @date:2019年3月29日 上午9:20:54
 */
@Component
public class ClassA {
	public void printClass() {
		System.out.println("This is Class A: " + this);
		getClassB().printClass();
	}

	@Lookup
	public ClassB getClassB() {
		return null;
	}
}
