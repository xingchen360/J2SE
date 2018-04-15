package com.noteshare.rmi.example.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloService extends Remote{
	// Remote method should throw RemoteException  
    public String service(String data) throws RemoteException; 
}
