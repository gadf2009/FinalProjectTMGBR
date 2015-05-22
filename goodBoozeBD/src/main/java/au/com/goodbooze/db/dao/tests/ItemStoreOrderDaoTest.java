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


public class ItemStoreOrderDaoTest extends TestCase{
	String persistenceUnitName = "liquor_store_tests";
	
	EntityManagerFactory factory;
	EntityManager manager;
	
	SupplierDao supplierDao;
	ProductDao productDao;
	ItemStoreOrderDao itemStoreOrderDao;
	StoreOrderDao storeOrderDao;
	EntryDao entryDao;
	StoreDao storeDao;
		
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
		
		Calendar calendar = Calendar.getInstance();
		
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
		int oldNumberOfItemsStoreOrder = itemStoreOrderDao.listAll().size();
		
		//Getting a supplier
		Supplier supplier1 = (Supplier)supplierDao.listAll().get(0);
		
		//Creating a new product
		Product product3 = new Product();
		product3.setName("Product 3");
		product3.setProductSupplierID(30);
		product3.setSupplier(supplier1);
		productDao.persist(product3);
		
		//Getting a storeOrder
		StoreOrder storeOrder = (StoreOrder)storeOrderDao.listAll().get(0);
		
		//Creating a itemStoreOrder
		ItemStoreOrder item3 = new ItemStoreOrder();
		item3.setProduct(product3);
		item3.setQuantity(10);
		item3.setStoreOrder(storeOrder);
		itemStoreOrderDao.persist(item3);
		
		int newNumberOfItemsStoreOrder = itemStoreOrderDao.listAll().size();
		
	    assertEquals(oldNumberOfItemsStoreOrder + 1, newNumberOfItemsStoreOrder);
	}
	
	@Test
	public void testUpdate() throws SQLException {
		ItemStoreOrder item = (ItemStoreOrder)itemStoreOrderDao.listAll().get(0);
		long newQuantityOfProducts = 50;
		assertFalse(item.getQuantity() == newQuantityOfProducts);
		
		item.setQuantity(newQuantityOfProducts);
		itemStoreOrderDao.update(item);
		
		assertEquals(item.getQuantity(), newQuantityOfProducts);	
	}
	
	@Test
	public void testFindById() throws SQLException {
		//Get one ItemStoreOrder
		ItemStoreOrder item1 = (ItemStoreOrder)itemStoreOrderDao.listAll().get(0);
		//Look for product with the same id
		ItemStoreOrder item2 = (ItemStoreOrder)itemStoreOrderDao.findById(item1.getItemStoreOrderID());
		//Check if they are equal

	    assertEquals(item1, item2);
	}
	
	@Test
	public void testListAll() throws SQLException {
		int oldNumberOfItemsStoreOrder = itemStoreOrderDao.listAll().size();
		
		//Getting a supplier
		Supplier supplier1 = (Supplier)supplierDao.listAll().get(0);
		
		//Creating a new product
		Product product3 = new Product();
		product3.setName("Product 3");
		product3.setProductSupplierID(30);
		product3.setSupplier(supplier1);
		productDao.persist(product3);
		
		//Getting a storeOrder
		StoreOrder storeOrder = (StoreOrder)storeOrderDao.listAll().get(0);
		
		//Creating a itemStoreOrder
		ItemStoreOrder item3 = new ItemStoreOrder();
		item3.setProduct(product3);
		item3.setQuantity(10);
		item3.setStoreOrder(storeOrder);
		itemStoreOrderDao.persist(item3);
		
		int newNumberOfItemsStoreOrder = itemStoreOrderDao.listAll().size();
		
	    assertEquals(oldNumberOfItemsStoreOrder + 1, newNumberOfItemsStoreOrder);
	}
	
	@Test
	public void testDelete() throws SQLException {
		int oldNumberOfItemsStoreOrder = itemStoreOrderDao.listAll().size();
		
		//Get one itemStoreOrder
		ItemStoreOrder item = (ItemStoreOrder)itemStoreOrderDao.listAll().get(0);
		
		itemStoreOrderDao.delete(item.getItemStoreOrderID());
		
		int newNumberOfItemsStoreOrder = itemStoreOrderDao.listAll().size();

	    assertEquals(oldNumberOfItemsStoreOrder-1, newNumberOfItemsStoreOrder);
	}
	
}
