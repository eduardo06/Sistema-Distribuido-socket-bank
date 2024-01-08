package com.soap.ws;
import java.rmi.RemoteException;

import javax.xml.ws.Endpoint;

public class ServerBank_C {
	public static void main(String[] args) throws RemoteException {
		
		Endpoint.publish("http://192.168.72.97:1515/WS/Bank_C", new Bank_C());
		System.out.println("Servidor conectado...");
	}
}
	