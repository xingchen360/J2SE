package com.noteshare.designPatterns.adapter.demo3;

public class Start {
	private static Port chatPort = new Chat();
	private static Port serverPort = new Server();

	public static void main(String[] args) {
		// 聊天服务
		chatPort.FTP();
		chatPort.NET();

		// 服务器
		serverPort.Mysql();
		serverPort.SSH();
		serverPort.Tomcat();
		serverPort.NET();
	}
}
