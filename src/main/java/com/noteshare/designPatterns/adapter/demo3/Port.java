package com.noteshare.designPatterns.adapter.demo3;

/**
 * 定义端口接口，提供通信服务
 *
 */
public interface Port {
	// 远程SSH端口22
	public void SSH();

	// 网络端口80
	public void NET();

	// Tomcat容器端口8080
	public void Tomcat();

	// Mysql数据库端口3306
	public void Mysql();

	// Oracle数据库端口1521
	public void Oracle();

	// 文件传输FTP端口21
	public void FTP();
}
