package com.test.annotation;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Title:ClassB.java
 * @Description:注解测试
 * @author:NoteShare
 * @date:2019年3月29日 上午9:21:25
 * 标记为非单例
 */
@Component
@Scope(scopeName=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClassB {
	public void printClass() {
		System.out.println("This is Class B: " + this);
	}
}
