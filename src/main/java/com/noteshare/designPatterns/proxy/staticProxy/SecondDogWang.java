package com.noteshare.designPatterns.proxy.staticProxy;
/**
 * @Title:SecondDogWang.java
 * @Description:真实角色：王二狗诉讼
 * @author:NoteShare
 * @date:2019年4月30日 上午11:21:44
 */
public class SecondDogWang implements ILawSuit{

	@Override
	public boolean submit(String proof) {
		System.out.println(String.format("王二狗提起诉讼：老板欠薪跑路，证据如下：%s",proof));
		return true;
	}
}
