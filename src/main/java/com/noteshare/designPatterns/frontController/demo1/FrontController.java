package com.noteshare.designPatterns.frontController.demo1;

/**
 * 前端控制器
 */
public class FrontController {
	private Dispatcher dispatcher;

	public FrontController() {
		dispatcher = new Dispatcher();
	}

	private boolean isAuthenticUser() {
		System.out.println("User is authenticated successfully.");
		return true;
	}

	/**
	 * 跟踪请求
	 * 
	 * @param request
	 */
	private void trackRequest(String request) {
		System.out.println("Page requested: " + request);
	}

	/**
	 * 派发请求
	 * 
	 * @param request
	 */
	public void dispatchRequest(String request) {
		// 记录每一个请求
		trackRequest(request);
		// 对用户进行身份验证
		if (isAuthenticUser()) {
			dispatcher.dispatch(request);
		}
	}
}
