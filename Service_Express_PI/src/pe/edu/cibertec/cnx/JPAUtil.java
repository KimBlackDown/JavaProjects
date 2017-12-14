package pe.edu.cibertec.cnx;

import javax.persistence.EntityManager; 
import javax.persistence.EntityManagerFactory; 
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("integrador2017");
	
	
	//Obtiene la conexion a la base de datos dawii2017
	public static EntityManager getEntityManager(){
		return FACTORY.createEntityManager();
	}
	
}
