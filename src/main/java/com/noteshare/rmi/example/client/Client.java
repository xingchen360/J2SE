package com.noteshare.rmi.example.client;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.noteshare.rmi.example.services.HelloService;
/**
 * @ClassName			: Client 
 * @Description			: 客户端调用只需把服务端提供的接口定义下然后编写客户端调用程序即可调用服务端代码，注意接口的包需要和服务端一致否则会报错
 * @author 				： NoteShare 
 * @date 				： 2018年4月15日 下午10:27:19
 */
public class Client {
	public static void main(String[] args) {
		//Auto-generated method stub
		String url = "rmi://localhost:1099/";
		try {
			Context namingContext = new InitialContext();
			HelloService serv = (HelloService) namingContext.lookup(url + "HelloService1");
			String data = "This is RMI Client.";
			System.out.println(serv.service(data));
		} catch (NamingException | RemoteException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
	}
}