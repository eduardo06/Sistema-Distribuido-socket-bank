package com.soap.ws;
import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.jws.WebService;

@WebService(endpointInterface="com.soap.ws.Bank_A")

public class Bank_A implements RemoteInterface {
	
	private ArrayList<Usuario> lista_usuarios = new ArrayList<Usuario>();			
	static Scanner scan = new Scanner(System.in);

	protected Bank_A() throws RemoteException {
		super();		
	}
	
    
	public void crearCuenta (String nombre) {
		Usuario user = new Usuario();
		
		//PEDIDA DE DATOS		
		
		user.setNombre(nombre);		
		user.setN_cuenta(this.generarCodigo());					
		lista_usuarios.add(user);

		//##############  Creamos un fichero con el numero de cuenta
		FileWriter fichero = null;	
		PrintWriter pw = null;
		FileWriter ficheroList = null;
		PrintWriter pwList = null;
        try
        {
			//
			ficheroList = new FileWriter("/home/user/Documentos/Universidad/SD/Proyecto/Sistema_C/Bank_A/src/com/soap/ws/Archivos/ListaDeCuentas.txt",true);
			pwList = new PrintWriter(ficheroList);
			pwList.print(user.getNombre() + " | ");
			pwList.println(user.getN_cuenta());

            fichero = new FileWriter("/home/user/Documentos/Universidad/SD/Proyecto/Sistema_C/Bank_A/src/com/soap/ws/Archivos/" + 
			user.getN_cuenta() + ".txt");
            pw = new PrintWriter(fichero);
			pw.println("Nombre: " + user.getNombre());
			pw.println("Saldo: " + user.getSaldo());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {           
           if (null != fichero)
              fichero.close();
			  ficheroList.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
		//#########################################################	
	}
	
	//Se genera un codigo de 13 digitos, con inicial del banco
	public String generarCodigo() {		
		
		int num =  (int)(100000 * Math.random());					
		
		return "A-" + num;
	}
	
	public void listaUsuarios() {
		
		System.out.println("---------------------------------------");
		System.out.println("|          Lista de Usuarios          |");
		System.out.println("---------------------------------------");
		
		for(int i = 0; i < lista_usuarios.size(); i++) {
			System.out.print("|");
			System.out.print(lista_usuarios.get(i).getN_cuenta() + " " + lista_usuarios.get(i).getNombre());
			System.out.println("|");
		}
		System.out.println("---------------------------------------");
	}

	//---------------------- FUNCIONES DE TRANSACCION ----------------------
	@Override
	public void deposito(String nCuenta, float x)  throws RemoteException {
		
		for(int i = 0 ; i <lista_usuarios.size(); i++){
			//Primero obtenemos al usuario de dicha cuenta
			if(lista_usuarios.get(i).getN_cuenta().equals(nCuenta)){				
				//suma el saldo actual
				lista_usuarios.get(i).setSaldo(lista_usuarios.get(i).getSaldo() + x);

				//##############  Creamos un fichero con el numero de cuenta
				FileWriter fichero = null;	
				PrintWriter pw = null;
				try{
					fichero = new FileWriter("/home/user/Documentos/Universidad/SD/Proyecto/Sistema_C/Bank_A/src/com/soap/ws/Archivos/" + 
					lista_usuarios.get(i).getN_cuenta() + ".txt");
					pw = new PrintWriter(fichero);
					pw.println("Nombre: " + lista_usuarios.get(i).getNombre());
					pw.println("Saldo: " + lista_usuarios.get(i).getSaldo());
				
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {           
						if (null != fichero)
						fichero.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				//#########################################################
				
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

				//##############  Creamos un fichero con el numero de cuenta
				FileWriter fichero = null;	
				PrintWriter pw = null;
				try{
					fichero = new FileWriter("/home/user/Documentos/Universidad/SD/Proyecto/Sistema_C/Bank_A/src/com/soap/ws/Archivos/" + 
					lista_usuarios.get(i).getN_cuenta() + ".txt");
					pw = new PrintWriter(fichero);
					pw.println("Nombre: " + lista_usuarios.get(i).getNombre());
					pw.println("Saldo: " + lista_usuarios.get(i).getSaldo());
				
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {           
						if (null != fichero)
						fichero.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				//#########################################################
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
		//AQUI 
	}
}
