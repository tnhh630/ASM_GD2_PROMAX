package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class jpaUtils {
	private static EntityManagerFactory factory ;
	static public EntityManager getEntityManager() {
		
		if(factory == null || !factory.isOpen()) {
			factory=Persistence.createEntityManagerFactory("ASM_GD2_PROMAX");	
		}
		return factory.createEntityManager();
		
		
	}
	
	static public void shutdown() {
		if(factory != null && factory.isOpen()) {
			factory.close();
		}
		factory=null;
	}
}


