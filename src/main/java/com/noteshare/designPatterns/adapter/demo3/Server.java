package com.noteshare.designPatterns.adapter.demo3;

/**
 * 网站服务器 需要Tomcat容器，Mysql数据库，网络服务，远程服务
 */
public class Server extends Wrapper {
	@Override
	public void SSH() {
		System.out.println("Connect success!");
	};

	@Override
	public void NET() {
		System.out.println("Hello WWW!");
	};

	@Override
	public void Tomcat() {
		System.out.println("Tomcat 9 is running!");
	};

	@Override
	public void Mysql() {
		System.out.println("Mysql is running!");
	};
}
