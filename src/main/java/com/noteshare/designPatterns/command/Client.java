package com.noteshare.designPatterns.command;

/**
 * 创建具体的命令对象，并且设置命令对象的接收者。注意这个不是我们常规意义上的客户端，而是在组装命令对象和接收者，或许，把这个Client称为装配者会更好理解
 * ，因为真正使用命令的客户端是从Invoker来触发执行。
 */
public class Client {
	public static void main(String[] args) {
		// 1：创建命令接收者
		Receiver receiver = new Receiver();
		// 2：创建命令并将命令和接收者进行关联
		Command command = new ConcreteCommand(receiver);
		// 3：创建命令调用或执行者，并绑定命令
		Invoker invoker = new Invoker(command);
		// 4：执行者调用命令
		invoker.doInvokerAction();
	}
}
