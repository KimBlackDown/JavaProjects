package pe.edu.cibertec.managedBean;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pe.edu.cibertec.beans.Usuario;
import pe.edu.cibertec.cnx.JPAUtil;

public class UsuarioMB {
	
	/*
	 * Insert a la tabla usuario
	 * */
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
	public void actualizar(Usuario obj) {

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
	
	public Usuario getUserLogin(String user,String password){
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Usuario> consulta=em.createNamedQuery("Usuario.login", Usuario.class);
		consulta.setParameter("v_user", user);
		consulta.setParameter("v_password", password);
		return consulta.getSingleResult();	
	}
	
	public List<Usuario> getAllUsers(){
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Usuario> consulta=em.createNamedQuery("Usuario.getAllUsers", Usuario.class);
		return consulta.getResultList();	
	}
	
	
	
	public List<Usuario> getUserForName(String name){
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Usuario> consulta=em.createNamedQuery("Usuario.getUserForName", Usuario.class);
		consulta.setParameter("p_nombre", name);
		return consulta.getResultList();	
	}
	public List<Usuario> getCourierActivo() {
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Usuario> consulta=em.createNamedQuery("Usuario.getCourierActivo", Usuario.class);
		consulta.setParameter("p_tipo","COURIER");
		return consulta.getResultList();	
	}
	public Usuario getUserForId(int idUsuario) {
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Usuario> consulta=em.createNamedQuery("Usuario.getUserForId", Usuario.class);
		consulta.setParameter("p_id", idUsuario);
		return consulta.getSingleResult();
	}

	
	

	/*
	 * 	public List	<Venta> obtenerVentasByIdEmpleado(Long idEmpleado){
		EntityManager em = JPAUtil.getEntityManager();
		
		Query query = em.createQuery ("Select v FROM Venta v WHERE v.empleado.id = :p_idEmpleado order by v.fecha asc"); //JPQL
		query.setParameter ("p_idEmpleado", idEmpleado);
		
		List<Venta> ventas;
		
		ventas  = query.getResultList();
		
		return ventas;	
	}
	 * */

}
