package au.com.goodbooze.db.dao.tests;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.goodbooze.bd.dao.EntryDao;
import au.com.goodbooze.bd.dao.ItemStoreOrderDao;
import au.com.goodbooze.bd.dao.ProductDao;
import au.com.goodbooze.bd.dao.StoreDao;
import au.com.goodbooze.bd.dao.StoreOrderDao;
import au.com.goodbooze.bd.dao.SupplierDao;
import au.com.goodbooze.bd.model.Entry;
import au.com.goodbooze.bd.model.ItemStoreOrder;
import au.com.goodbooze.bd.model.Product;
import au.com.goodbooze.bd.model.Store;
import au.com.goodbooze.bd.model.StoreOrder;
import au.com.goodbooze.bd.model.Supplier;


public class StoreOrderDaoTest extends TestCase{
	String persistenceUnitName = "liquor_store_tests";
	
	EntityManagerFactory factory;
	EntityManager manager;
	
	SupplierDao supplierDao;
	ProductDao productDao;
	ItemStoreOrderDao itemStoreOrderDao;
	StoreOrderDao storeOrderDao;
	EntryDao entryDao;
	StoreDao storeDao;
	
	Calendar calendar;
		
	@Before
	public void setUp() {
		factory = Persistence.createEntityManagerFactory(persistenceUnitName);
		manager = factory.createEntityManager();
		
		supplierDao = new SupplierDao(manager);
		productDao = new ProductDao(manager);
		itemStoreOrderDao = new ItemStoreOrderDao(manager);
		storeOrderDao = new StoreOrderDao(manager);
		entryDao = new EntryDao(manager);
		storeDao = new StoreDao(manager);
		
		calendar = Calendar.getInstance();
		
		//Initializing DB with 1 Entry
		Entry entry = new Entry();
		entry.setInsertDate(calendar);
		entry.setProcessDate(calendar);
		entryDao.persist(entry);
		
		//Initializing DB with 1 Store
		Store store = new Store();
		store.setName("STORE 1");
		storeDao.persist(store);
		
		//Initializing DB with 2 Suppliers
		Supplier supplier1 = new Supplier();
		supplier1.setName("Supplier1");
		supplierDao.persist(supplier1);
				
		Supplier supplier2 = new Supplier();
		supplier2.setName("Supplier2");
		supplierDao.persist(supplier2);
				
		//Initializing DB with 2 Products
		Product product1 = new Product();
		product1.setName("Product 1");
		product1.setProductSupplierID(10);
		product1.setSupplier(supplier1);
		productDao.persist(product1);
		
		Product product2 = new Product();
		product2.setName("Product 2");
		product2.setProductSupplierID(11);
		product2.setSupplier(supplier2);
		productDao.persist(product2);
		
		//Initializing DB with 1 StoreOrder
		StoreOrder storeOrder = new StoreOrder();
		storeOrder.setStatus("STATUS 1");
		storeOrder.setEntry(entry);
		storeOrder.setStore(store);
		storeOrder.setInsertDate(calendar);
		storeOrder.setUpdateDate(calendar);
		storeOrderDao.persist(storeOrder);
		
		//Initializing BD with 2 items
		ItemStoreOrder item1 = new ItemStoreOrder();
		item1.setProduct(product1);
		item1.setQuantity(20);
		item1.setStoreOrder(storeOrder);
		itemStoreOrderDao.persist(item1);
		
		ItemStoreOrder item2 = new ItemStoreOrder();
		item2.setProduct(product2);
		item2.setQuantity(20);
		item2.setStoreOrder(storeOrder);
		itemStoreOrderDao.persist(item2);

	}
	
	@After
	public void tearDown() {
		manager.close();
		factory.close();
	}

	@Test
	public void testPersist() throws SQLException {
		int oldNumberOfStoreOrder = storeOrderDao.listAll().size();
		
		//Getting a calendar instance
		calendar = Calendar.getInstance();
		
		//Getting a entry
		Entry entry = (Entry)entryDao.listAll().get(0);
		
		//Getting a store
		Store store = (Store)storeDao.listAll().get(0);
		
		//Getting a new product
		Product product= (Product)productDao.listAll().get(0);
		
		//Creating storeOrder
		StoreOrder storeOrder = new StoreOrder();
		storeOrder.setEntry(entry);
		storeOrder.setStore(store);
		storeOrder.setInsertDate(calendar);
		storeOrder.setUpdateDate(calendar);
		storeOrder.setStatus("STATUS1");
		storeOrderDao.persist(storeOrder);
		
		//Creating a itemStoreOrder
		ItemStoreOrder item = new ItemStoreOrder();
		item.setProduct(product);
		item.setQuantity(10);
		item.setStoreOrder(storeOrder);
		itemStoreOrderDao.persist(item);
		
		int newNumberOfStoreOrder = storeOrderDao.listAll().size();
		
	    assertEquals(oldNumberOfStoreOrder + 1, newNumberOfStoreOrder);
	}
	
	@Test
	public void testUpdate() throws SQLException {
		StoreOrder storeOder = (StoreOrder)storeOrderDao.listAll().get(0);
		
		String newStatus = "Status 2";
		assertFalse(storeOder.getStatus().equals(newStatus));
		
		storeOder.setStatus(newStatus);

		storeOrderDao.update(storeOder);
		
		assertEquals(storeOder.getStatus(), newStatus);	
	}
	
	@Test
	public void testFindById() throws SQLException {
		//Get one StoreOrder
		StoreOrder storeOrder1 = (StoreOrder)storeOrderDao.listAll().get(0);
		//Look for storeOrder with the same id
		StoreOrder storeOrder2 = (StoreOrder)storeOrderDao.findById(storeOrder1.getStoreOrderID());
		//Check if they are equal

	    assertEquals(storeOrder1, storeOrder2);
	}
	
	@Test
	public void testListAll() throws SQLException {
		int oldNumberOfStoreOrder = storeOrderDao.listAll().size();
		
		//Getting a calendar instance
		calendar = Calendar.getInstance();
		
		//Getting a entry
		Entry entry = (Entry)entryDao.listAll().get(0);
		
		//Getting a store
		Store store = (Store)storeDao.listAll().get(0);
		
		//Getting a new product
		Product product= (Product)productDao.listAll().get(0);
		
		//Creating storeOrder
		StoreOrder storeOrder = new StoreOrder();
		storeOrder.setEntry(entry);
		storeOrder.setStore(store);
		storeOrder.setInsertDate(calendar);
		storeOrder.setUpdateDate(calendar);
		storeOrder.setStatus("STATUS1");
		storeOrderDao.persist(storeOrder);
		
		//Creating a itemStoreOrder
		ItemStoreOrder item = new ItemStoreOrder();
		item.setProduct(product);
		item.setQuantity(10);
		item.setStoreOrder(storeOrder);
		itemStoreOrderDao.persist(item);
		
		int newNumberOfStoreOrder = storeOrderDao.listAll().size();
		
	    assertEquals(oldNumberOfStoreOrder + 1, newNumberOfStoreOrder);
	}
	
}
