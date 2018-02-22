package com.noteshare.activeMQ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;

public class Chat implements MessageListener{
	private TopicConnection connection;
	private TopicSession pubSession;
	private TopicPublisher publisher;
	private String username;
	/*用于初始化Chat（聊天）的构造函数*/
	public Chat(String topicFactory,String topicName,String username) throws Exception{
		//使用jndi.properties文件获得一个JNDI连接
		InitialContext ctx = new InitialContext();
		//查找一个JMS连接工厂并创建连接
		TopicConnectionFactory conFactory = (TopicConnectionFactory) ctx.lookup(topicFactory);
		
		TopicConnection connection = conFactory.createTopicConnection();
		
		//创建两个JMS会话对象; boolean值是用来表明session对象是否是事务性的。一个事务性session自动管理着一个事务
		//内部的输出和输入消息。第二个参数表明jms客户端使用的确认模式，确认就是通知消息服务器：客户端已经接收到消息。
		//AUTO_ACKNOWLEDGE这意味着消息将在客户端接收之后自动确认。
		//TODO:需要测试下其他模式
		TopicSession pubSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		TopicSession subSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		
		//查找一个JMS主题
		Topic chatTopic = (Topic) ctx.lookup(topicName);
		
		//创建一个JMS发布者和订阅者。createSubscriber中附加的参数是一个消息
		//选择器（null）和nolocal标记的一个真值。它表明这个发布者生产的消息不
		//应被它自己所消费。
		TopicPublisher publisher = pubSession.createPublisher(chatTopic);
		//持久性消息，默认是就是持久性的。
//		publisher.setDeliveryMode(DeliveryMode.PERSISTENT);
		//第三个参数：false表示自己可以接收自己发送的消息，true表示不接收自己发送的消息
		//第二参数包含了消息选择器，它用于过滤出我们想要接收的那些基于特定标准（creteria）的消息。设置为空表示我们想要接收所有的消息
		TopicSubscriber subscriber = subSession.createSubscriber(chatTopic,null,false);
		
		//设置一个JMS消息侦听器
		subscriber.setMessageListener(this);
		//初始化Chat应用程序变量
		this.connection = connection;
		this.pubSession = pubSession;
		this.publisher = publisher;
		this.username = username;
		//启动JMS连接；允许传递消息
		connection.start();
	}
	/*接收来自TopicSubscriber的消息*/
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			System.out.println(textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	/*使用发送者创建并发送消息*/
	protected void writeMessage(String text) throws JMSException {
		TextMessage message = pubSession.createTextMessage();
		message.setText(username + ":" + text);
		publisher.publish(message);
	}
	/*关闭JMS连接*/
	public void close() throws JMSException{
		connection.close();
	}
	/*运行聊天客户端*/
	public static void main(String[] args) {
		if(args.length !=3){
			System.out.println("Factory,Topic, or username missing");
		}else{
			try {
				//args[0]=topicFatory;args[1]=topicName;args[2]=username
				Chat chat = new Chat(args[0], args[1], args[2]);
				//从命令行读取
				BufferedReader commandLine = new BufferedReader(new InputStreamReader(System.in));
				//循环，直到键入“exit”为止
				while(true){
					String s = commandLine.readLine();
					if(s.equalsIgnoreCase("exit")){
						chat.close();
						System.exit(0);
					}else
						chat.writeMessage(s);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
