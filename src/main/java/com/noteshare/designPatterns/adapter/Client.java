package com.noteshare.designPatterns.adapter;

/**
 * 类适配器（采取继承的方式）
 * 通过method1间接的访问method2方法。这是通过类的继承的方式来实现适配。
 * @author Administrator
 * 在junit中体现在runbare方法中调用的runTest()方法。将一个无法使用的test方法，
 * 转换为一个可以直接使用的runTest的方法。使得Junit可以执行我们自己编写的TestCase。
 * 在runTest中首先先获得我们自己编写的testXXX方法所对应的Method对象（不带参数），然后检查该
 * Methood对象所对应的方法是否是public的，如果是则调用Method对象的invoke方法来执行我们自己编写
 * 的testXXX方法。
 */
public class Client {
	public static void main(String[] args) {
		Target target = new Adapter();
		target.method1();
	}
}
