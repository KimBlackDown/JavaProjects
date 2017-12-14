package pe.edu.cibertec.main;

import java.util.List;

import javax.persistence.EntityManager;

import pe.edu.cibertec.beans.Cliente;
import pe.edu.cibertec.beans.Distrito;
import pe.edu.cibertec.beans.Pedido;
import pe.edu.cibertec.cnx.JPAUtil;
import pe.edu.cibertec.managedBean.ClienteMB;
import pe.edu.cibertec.managedBean.DistritoMB;
import pe.edu.cibertec.managedBean.PedidoMB;

public class MainEjecutar {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		listado();
			
	}

	
	private static void invocarEntityManager(){
		
		EntityManager manager =  JPAUtil.getEntityManager();
		
		System.out.println("BASE DE DATOS GENERADA!");
		
	}


	public static void listado(){

		ClienteMB clienteMB=new ClienteMB();
		DistritoMB distritoMB=new DistritoMB();
		PedidoMB pedidoMB=new PedidoMB();
		
		List<Cliente>lstCliente=clienteMB.getAllClientes();
		
		for (Cliente cliente2 : lstCliente) {
			System.out.println(cliente2.getApellido());
		}
		
		System.err.println("*********************************************");
		
		List<Distrito>lstDistrito=distritoMB.getAllDistritos();
		
		for (Distrito distrito2 : lstDistrito) {
			
			System.out.println(distrito2.getDescripcion());
		}
		
		System.err.println("*********************************************");
		
		List<Pedido>lstPedido=pedidoMB.getAllPedidos();
		for (Pedido pedido : lstPedido) {
			System.out.println(pedido.getDireccion_ini());
		}
		
		
	}
	
	
	
}
