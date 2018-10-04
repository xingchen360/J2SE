package com.noteshare.designPatterns.frontController.demo1;

/**
 * 前端控制器可能使用一个调度器对象来调度请求到相应的具体处理程序
 */
public class Dispatcher {
	private StudentView studentView;
	private HomeView homeView;

	public Dispatcher() {
		studentView = new StudentView();
		homeView = new HomeView();
	}

	public void dispatch(String request) {
		if (request.equalsIgnoreCase("STUDENT")) {
			studentView.show();
		} else {
			homeView.show();
		}
	}
}
