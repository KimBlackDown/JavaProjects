package pe.edu.cibertec.managedBean;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pe.edu.cibertec.beans.Cliente;
import pe.edu.cibertec.cnx.JPAUtil;

public class ClienteMB {

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

	public void actualizar(Object obj) {
		EntityManager manager = null;

		try {
			manager = JPAUtil.getEntityManager();
			manager.getTransaction().begin();
			manager.merge(obj);
			manager.flush();
			manager.getTransaction().commit(); 
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			manager.close();
		}
	}
	


	public List<Cliente> getAllClientes(){
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Cliente> consulta=em.createNamedQuery("Cliente.getAllClientes", Cliente.class);
		
		return consulta.getResultList();
	}


	public Cliente loginCliente(String usu,String con){
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Cliente> consulta= null;
		consulta=em.createNamedQuery("Cliente.loginCliente", Cliente.class);
		consulta.setParameter("usuCli", usu);
		consulta.setParameter("conCli", con);

		
		
		return consulta.getSingleResult();
	}
	
	

	public Cliente getClienteById(int cod){
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Cliente> consulta=em.createNamedQuery("Cliente.getClienteById", Cliente.class);
		consulta.setParameter("cli", cod);
		
		return consulta.getSingleResult();
	}

	public List<Cliente> getClienteByName(String nombreParam) {
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Cliente> consulta=em.createNamedQuery("Cliente.getClienteByName", Cliente.class);
		consulta.setParameter("name", nombreParam);
		
		return consulta.getResultList();
	}

	public Cliente buscarCliente(int cod_cliente) {
		EntityManager manager = null;

		try {
			manager = JPAUtil.getEntityManager();
			return manager.find(Cliente.class,cod_cliente );
			
			
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			manager.close();
		}
		return null;

	}

	
	
}
