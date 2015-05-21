package au.com.goodbooze.bd.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import au.com.goodbooze.bd.dao.SupplierDao;
import au.com.goodbooze.bd.model.Supplier;


public class GenerateTables {
	static String persistenceUnitName = "liquor_store";
	
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnitName);
	static EntityManager manager = factory.createEntityManager();
	
	public static void main(String[] args) {

	}
	
	public static void close() {
		manager.close();
		factory.close();
	}


}
