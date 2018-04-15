package com.noteshare.rmi.example.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.noteshare.rmi.example.services.HelloService;
import com.noteshare.rmi.example.services.impl.HelloServiceImpl;
/**
 * 
 * @ClassName			: Server 
 * @Description			: Remote Method Invoke 服务器端
 * RMI框架分别为远程对象生成了客户端代理和服务器端代理，位于客户端的代理称为存根（Stub），位于服务器端的代理称为骨架（Skeleton）
 * @author 				： NoteShare 
 * @date 				： 2018年4月15日 下午10:29:22
 */
public class Server {

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);

			HelloService service1 = new HelloServiceImpl("service1");
			Context namingContext = new InitialContext();
			namingContext.rebind("rmi://localhost:1099/HelloService1", service1);
		} catch (RemoteException | NamingException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully register a remote object.");
	}
}