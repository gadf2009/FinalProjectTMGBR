package au.com.goodbooze.db.dao.tests;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.goodbooze.bd.dao.SupplierDao;
import au.com.goodbooze.bd.model.Supplier;


public class SupplierDaoTest extends TestCase{
	String persistenceUnitName = "liquor_store_tests";
	
	EntityManagerFactory factory;
	EntityManager manager;
	SupplierDao supplierDao;
	
	int initalNumberOfSuppliers;
	
	@Before
	public void setUp() {
		factory = Persistence.createEntityManagerFactory(persistenceUnitName);
		manager = factory.createEntityManager();
		supplierDao = new SupplierDao(manager);
		
		//Initializing DB with 3 Suppliers
		
		Supplier supplier1 = new Supplier();
		supplier1.setName("Supplier1");
		
		Supplier supplier2 = new Supplier();
		supplier2.setName("Supplier2");
		
		Supplier supplier3 = new Supplier();
		supplier3.setName("Supplier3");
		
		supplierDao.persist(supplier1);
		supplierDao.persist(supplier2);
		supplierDao.persist(supplier3);

		initalNumberOfSuppliers = 3;
	}
	@After
	public void tearDown() {
		manager.close();
		factory.close();
	}
	
	@Test
	public void testPersist() throws SQLException {
		
		Supplier supplier4 = new Supplier();
		supplier4.setName("Supplier4");
		supplierDao.persist(supplier4);
		
		int newNumberOfSuppliers = supplierDao.listAll().size();
		
	    assertEquals(initalNumberOfSuppliers + 1, newNumberOfSuppliers);
	}
	
	
	@Test
	public void testUpdate() throws SQLException {
		
		Supplier supplier = (Supplier)supplierDao.listAll().get(1);
		String new_name = "New Supplier";
		//old name is not equal to new name
		assertFalse(supplier.getName().equals(new_name));
	
		supplier.setName(new_name);
		supplierDao.update(supplier);
		
		Supplier supplier1 = (Supplier)supplierDao.findById(supplier.getSupplierId());
		
	    assertEquals(supplier1.getName(), new_name);
	}
	
	@Test
	public void testFindById() throws SQLException {
		//Get first Supplier
		Supplier supplier1 = (Supplier)supplierDao.listAll().get(1);
		//Look for Supplier 1 id
		Supplier supplier2 = (Supplier)supplierDao.findById(supplier1.getSupplierId());
		//Check if they are equal
	    assertEquals(supplier1, supplier2);
	}
	
	@Test
	public void testListAll() throws SQLException {
		int oldQuantityOfSuppliers = supplierDao.listAll().size();
		
		//Add one Supplier
		
		Supplier supplier4 = new Supplier();
		supplier4.setName("Supplier4");
		supplierDao.persist(supplier4);
		int newQuantityOfSuppliers = supplierDao.listAll().size();

	    assertEquals(oldQuantityOfSuppliers+1, newQuantityOfSuppliers);
	}
	
	@Test
	public void testDelete() throws SQLException {
		int oldQuantityOfSuppliers = supplierDao.listAll().size();
		
		//Get first Supplier
		Supplier supplier = (Supplier)supplierDao.listAll().get(0);
		
		supplierDao.delete(supplier.getSupplierId());
		
		int newQuantityOfSuppliers = supplierDao.listAll().size();

	    assertEquals(oldQuantityOfSuppliers-1, newQuantityOfSuppliers);
	}

	
}
