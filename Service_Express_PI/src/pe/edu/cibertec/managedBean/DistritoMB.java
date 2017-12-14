package pe.edu.cibertec.managedBean;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pe.edu.cibertec.beans.Distrito;
import pe.edu.cibertec.cnx.JPAUtil;

public class DistritoMB {

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
	
	
	public List<Distrito> getAllDistritos(){
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Distrito> consulta=em.createNamedQuery("Distrito.getAllDistrito", Distrito.class);
		
		return consulta.getResultList();
	}
	
	public Distrito getDistritoById(int cod_dis){
		EntityManager em = JPAUtil.getEntityManager(); 
		TypedQuery<Distrito> consulta=em.createNamedQuery("Distrito.getDistritoById", Distrito.class);
		consulta.setParameter("cod_dis", cod_dis);
		return consulta.getSingleResult();
	}
}
