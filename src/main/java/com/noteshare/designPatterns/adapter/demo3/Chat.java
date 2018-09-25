package com.noteshare.designPatterns.adapter.demo3;

/**
 * 提供聊天服务 需要网络和文件传输功能
 */
public class Chat extends Wrapper {
	@Override
	public void NET() {
		System.out.println("Hello world!");
	};

	@Override
	public void FTP() {
		System.out.println("File upload succeddful!");
	};
}
