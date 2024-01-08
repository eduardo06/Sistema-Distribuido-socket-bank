package com.soap.ws;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.jws.WebService;

@WebService(endpointInterface="com.soap.ws.Bank_B")

public class Bank_B implements RemoteInterface {
	
	private ArrayList<Usuario> lista_usuarios = new ArrayList<Usuario>();			
	static Scanner scan = new Scanner(System.in);

	protected Bank_B() throws RemoteException {
		super();		
	}
	
    
	public void crearCuenta (String nombre) {
		Usuario user = new Usuario();
		
		//PEDIDA DE DATOS		
		
		user.setNombre(nombre);		
		user.setN_cuenta(this.generarCodigo());	
		
		lista_usuarios.add(user);
	}
	
	//Se genera un codigo de 13 digitos, con inicial del banco
	public String generarCodigo() {		
		
		int num =  (int)(100000 * Math.random());					
		
		return "B-" + num;
	}
	
	public void listaUsuarios() {
		
		System.out.println("Lista de Usuarios");
		System.out.println("-----------------");
		
		for(int i = 0; i < lista_usuarios.size(); i++) {
			System.out.println(lista_usuarios.get(i).getN_cuenta() + " " + lista_usuarios.get(i).getNombre());
		}
	}

	//---------------------- FUNCIONES DE TRANSACCION ----------------------
	@Override
	public void deposito(String nCuenta, float x)  throws RemoteException {
		
		for(int i = 0 ; i <lista_usuarios.size(); i++){
			//Primero obtenemos al usuario de dicha cuenta
			if(lista_usuarios.get(i).getN_cuenta().equals(nCuenta)){				
				//suma el saldo actual
				lista_usuarios.get(i).setSaldo(lista_usuarios.get(i).getSaldo() + x);
			}
		}
	}

	@Override
	public void retiro(String nCuenta, float x)  throws RemoteException {
		
		for(int i = 0 ; i <lista_usuarios.size(); i++){
			//Primero obtenemos al usuario de dicha cuenta
			if(lista_usuarios.get(i).getN_cuenta().equals(nCuenta)){
				//resta el saldo actual
				lista_usuarios.get(i).setSaldo(lista_usuarios.get(i).getSaldo() - x);
			}
		}
	}

	@Override
	public String detalle(String nCuenta)  throws RemoteException {
		
		String msg="";

		for(int i = 0 ; i <lista_usuarios.size(); i++){
			//Primero obtenemos al usuario de dicha cuenta
			if(lista_usuarios.get(i).getN_cuenta().equals(nCuenta)){								
				msg  = "\t"+lista_usuarios.get(i).getN_cuenta() + " | " + lista_usuarios.get(i).getNombre() + " | Saldo: " + lista_usuarios.get(i).getSaldo()+"\n";
			}			
		}
		return msg;		
	}
	
	@Override
	public void transferencia(String nCuentaX, float x, String nCuentaY) throws RemoteException {

	}	
}
