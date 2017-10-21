package com.noteshare.robot;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
public class TestQQSendMsg {
	static Desktop deskapp = Desktop.getDesktop();

	public static void main(String[] args) throws AWTException {
		openQQ();
		inputQQ();
	}

	public static void openQQ() {
		// 判断当前系统释放支持Desktop提供的接口
		if (Desktop.isDesktopSupported()) {
			try {
				deskapp.open(new File("C:\\Program Files (x86)\\Tencent\\QQ\\Bin\\QQScLauncher.exe"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void inputQQ() throws AWTException {
		Robot robot = new Robot();
		//等待3秒后开始执行下面的自动键盘事件
		robot.delay(3000);
		//按下确认键进行qq登录
		TestInput.keyPress(robot, KeyEvent.VK_ENTER);
		robot.delay(5000);
		// 点击鼠标左键(目的是让光标放到QQ上)
		//TestInput.mouseLeftHit(robot);
		//打开QQ聊天窗口
		//TestInput.keyPressAtlWithCtrlWithZ(robot);
		robot.delay(1000);
		// 搜索我的手机
		TestInput.keyPressString(robot, "我的手机");
		robot.delay(1000);
		// 按下回车
		TestInput.keyPress(robot, KeyEvent.VK_ENTER);
		robot.delay(1000);
		//输入要发送的消息
		for (int i = 0; i < 10; i++) {
			TestInput.keyPressString(robot, "你好，打扰到你了，我是自动聊天机器人！");
			TestInput.keyPress(robot, KeyEvent.VK_ENTER);
		}
		//点击enter键进行发送
		TestInput.keyPress(robot, KeyEvent.VK_ENTER);
	}
}
