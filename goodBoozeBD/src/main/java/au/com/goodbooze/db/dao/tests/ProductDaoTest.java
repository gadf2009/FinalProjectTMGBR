package au.com.goodbooze.db.dao.tests;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.goodbooze.bd.dao.ProductDao;
import au.com.goodbooze.bd.dao.SupplierDao;
import au.com.goodbooze.bd.model.Product;
import au.com.goodbooze.bd.model.Supplier;


public class ProductDaoTest extends TestCase{
	String persistenceUnitName = "liquor_store_tests";
	
	EntityManagerFactory factory;
	EntityManager manager;
	SupplierDao supplierDao;
	ProductDao productDao;
	Supplier supplier1;
	
	int initalNumberOfProducts;
	
	@Before
	public void setUp() {
		factory = Persistence.createEntityManagerFactory(persistenceUnitName);
		manager = factory.createEntityManager();
		supplierDao = new SupplierDao(manager);
		productDao = new ProductDao(manager);
		
		//Initializing DB with 1 Supplier
		supplier1 = new Supplier();
		supplier1.setName("Supplier1");
		
		supplierDao.persist(supplier1);
		
		//Initializing DB with 3 Products
		
		Product product1 = new Product();
		product1.setName("Product 1");
		product1.setProductSupplierID(10);
		product1.setSupplier(supplier1);
		
		Product product2 = new Product();
		product2.setName("Product 2");
		product2.setProductSupplierID(11);
		product2.setSupplier(supplier1);
		
		Product product3 = new Product();
		product3.setName("Product 3");
		product3.setProductSupplierID(12);
		product3.setSupplier(supplier1);
		
		supplierDao.persist(supplier1);
		productDao.persist(product1);
		productDao.persist(product2);
		productDao.persist(product3);


		initalNumberOfProducts = 3;
	}
	@After
	public void tearDown() {
		manager.close();
		factory.close();
	}
	
	@Test
	public void testPersist() throws SQLException {
		
		Product product4 = new Product();
		product4.setName("Product 4");
		product4.setProductSupplierID(10);
		product4.setSupplier(supplier1);
		productDao.persist(product4);
		
		int newNumberOfProducts = productDao.listAll().size();
		
	    assertEquals(initalNumberOfProducts + 1, newNumberOfProducts);
	}
	
	@Test
	public void testUpdate() throws SQLException {
		
		Product product = (Product)productDao.listAll().get(1);
		String new_name = "New Product";
		//old name is not equal to new name
		assertFalse(product.getName().equals(new_name));
	
		product.setName(new_name);
		productDao.update(product);
		
		Product product1 = (Product)productDao.findById(product.getInternalID());
		
	    assertEquals(product1.getName(), new_name);
	}
	
	@Test
	public void testFindById() throws SQLException {
		//Get first Product
		Product product1 = (Product)productDao.listAll().get(1);
		//Look for Product 1 id
		Product product2 = (Product)productDao.findById(product1.getInternalID());
		//Check if they are equal
	    assertEquals(product1, product2);
	}
	
	@Test
	public void testListAll() throws SQLException {
		int initalNumberOfProducts = productDao.listAll().size();
		
		//Find one Supplier
		
		Supplier supplier = (Supplier)supplierDao.listAll().get(0);
		
		//Add one Product
		Product product4 = new Product();
		product4.setName("Product 4");
		product4.setProductSupplierID(20);
		product4.setSupplier(supplier);
		
		productDao.persist(product4);
		
		
		int newQuantityOfProducts = productDao.listAll().size();

	    assertEquals(initalNumberOfProducts+1, newQuantityOfProducts);
	}
	
	@Test
	public void testDelete() throws SQLException {
		int initalNumberOfProducts = productDao.listAll().size();
		
		//Get first Product
		Product product = (Product)productDao.listAll().get(0);
		
		productDao.delete(product.getInternalID());
		
		int newQuantityOfProducts = productDao.listAll().size();

	    assertEquals(initalNumberOfProducts-1, newQuantityOfProducts);
	}

	
}
