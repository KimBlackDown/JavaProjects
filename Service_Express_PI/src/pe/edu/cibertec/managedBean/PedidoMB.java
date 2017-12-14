package pe.edu.cibertec.managedBean;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pe.edu.cibertec.beans.Pedido;
import pe.edu.cibertec.cnx.JPAUtil;

public class PedidoMB {

public void insertar(Object obj){
		
		EntityManager manager = null;

		try {
			manager = JPAUtil.getEntityManager();
			manager.getTransaction().begin();
			manager.persist(obj);//Genera el JPQL internamente 
			manager.flush();// Enviar en cola(pueden haber varios SQL)
			manager.getTransaction().commit(); //Envia a la BD
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			manager.close();
		}
	}
	
	public Pedido buscarId(int id) {
		EntityManager manager = null;
	
		try {
			manager = JPAUtil.getEntityManager();
			return manager.find(Pedido.class,id );
			
			
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			manager.close();
		}
		return null;
	}




	public List<Pedido> getAllPedidos(){
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Pedido> consulta=em.createNamedQuery("Pedido.getAllPedidos", Pedido.class);
		
		return consulta.getResultList();
	}

	
	

	public List<Pedido> getAllPedidosForCliente(int cod){
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Pedido> consulta=em.createNamedQuery("Pedido.getAllPedidosForCliente", Pedido.class);
		consulta.setParameter("cod", cod);
		
		
		return consulta.getResultList();
	}
	public List<Pedido> getAllPedidosCancelados(int cod){
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Pedido> consulta=em.createNamedQuery("Pedido.getAllPedidosCancelados", Pedido.class);
		consulta.setParameter("cod", cod);
		consulta.setParameter("estado", "CANCELADO");
		
		return consulta.getResultList();
	}

	public List<Pedido> getAllPedidosCompletados(int cod){
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Pedido> consulta=em.createNamedQuery("Pedido.getAllPedidosCompletados", Pedido.class);
		consulta.setParameter("cod", cod);
		consulta.setParameter("estado", "COMPLETADO");
		return consulta.getResultList();
	}
	
	public void update(Object obj) {
		// TODO Auto-generated method stub
		
		EntityManager manager = null;

		try {
			manager = JPAUtil.getEntityManager();
			manager.getTransaction().begin();
			manager.merge(obj);//Genera el JPQL internamente 
			manager.flush();// Enviar en cola(pueden haber varios SQL)
			manager.getTransaction().commit(); //Envia a la BD
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			manager.close();
		}
		
		
		
	}
	
	
	
	
	
	public List<Pedido> getAllPedidosCanceladoAdmin(){
		
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Pedido> consulta=em.createNamedQuery("Pedido.getAllPedidosCanceladoAdmin", Pedido.class);
		return consulta.getResultList();
		
	}
	public List<Pedido> getAllPedidosCompletadosAdmin(){
			
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Pedido> consulta=em.createNamedQuery("Pedido.getAllPedidosCompletadosAdmin", Pedido.class);
		return consulta.getResultList();
	}
		
	public List<Pedido> getAllPedidosProcesoAdmin(){
		
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Pedido> consulta=em.createNamedQuery("Pedido.getAllPedidosProcesoAdmin", Pedido.class);
		return consulta.getResultList();
	}
	
	public List<Pedido> getAllPedidosPendientesAdmin(){
		
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Pedido> consulta=em.createNamedQuery("Pedido.getAllPedidosPendientesAdmin", Pedido.class);
	//	consulta.setParameter("est", "POR CONFIRMAR");
		return consulta.getResultList();
	}

	
	
	
	
	
	public List<Pedido> getAllPedidosCanceladoCourier(int cod){
		
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Pedido> consulta=em.createNamedQuery("Pedido.getAllPedidosCanceladoCourier", Pedido.class);
		consulta.setParameter("cod", cod);
		return consulta.getResultList();
		
	}
	public List<Pedido> getAllPedidosCompletadosCourier(int cod){
			
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Pedido> consulta=em.createNamedQuery("Pedido.getAllPedidosCompletadosCourier", Pedido.class);
		consulta.setParameter("cod", cod);
		return consulta.getResultList();
	}
		
	public List<Pedido> getAllPedidosProcesoCourier(int cod){
		
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Pedido> consulta=em.createNamedQuery("Pedido.getAllPedidosProcesoCourier", Pedido.class);
		consulta.setParameter("cod", cod);
		return consulta.getResultList();
	}
	
	


	
	
	
	
	
	
	
}
