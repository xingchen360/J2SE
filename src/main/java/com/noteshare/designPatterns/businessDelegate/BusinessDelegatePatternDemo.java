package com.noteshare.designPatterns.businessDelegate;

/**
 * 感觉很狗屎的一个模式，改一个地方需要改好多地方，不应该是说这个示例代码不怎么样，希望后面能够深入理解找到一个更能体现出该模式的例子出来。
 */
public class BusinessDelegatePatternDemo {
	public static void main(String[] args) {
		// 创建业务代表对象
		BusinessDelegate businessDelegate = new BusinessDelegate();
		// 设置要代表的业务类型
		businessDelegate.setServiceType("EJB");
		// 创建客户端对象，并绑定业务代表对象，同时也明确了代表关系
		Client client = new Client(businessDelegate);
		// 客户端调用处理方法
		client.doTask();
		// 改变代表的业务类型
		businessDelegate.setServiceType("JMS");
		// 客户端调用处理方法
		client.doTask();
	}
}
