/******************************************************************************
* Copyright (C) 2019 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/
package com.noteshare.thread.threadlocal;
/**
 * @Title:TesA.java
 * @Description:TODO
 * @author:陈海新
 * @date:2019年4月22日 下午5:01:11
 */
public class TesA {
	public static void main(String[] args) {
		TesA te = new TesA();
		te.test2();
	}
	public void test2(){
		A a = new A();
		a.test(this);
	}
}

class A{
	public void test(Object obj){
		System.out.println(obj.toString());
	}
}
