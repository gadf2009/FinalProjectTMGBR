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
import au.com.goodbooze.bd.dao.SupplierOrderDao;
import au.com.goodbooze.bd.model.Entry;
import au.com.goodbooze.bd.model.ItemStoreOrder;
import au.com.goodbooze.bd.model.Product;
import au.com.goodbooze.bd.model.Store;
import au.com.goodbooze.bd.model.StoreOrder;
import au.com.goodbooze.bd.model.Supplier;
import au.com.goodbooze.bd.model.SupplierOrder;


public class SupplierOrderDaoTest extends TestCase{
	String persistenceUnitName = "liquor_store_tests";
	
	EntityManagerFactory factory;
	EntityManager manager;
	
	SupplierDao supplierDao;
	ProductDao productDao;
	ItemStoreOrderDao itemStoreOrderDao;
	StoreOrderDao storeOrderDao;
	EntryDao entryDao;
	StoreDao storeDao;
	SupplierOrderDao supplierOrderDao;
	
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
		supplierOrderDao = new SupplierOrderDao(manager);
		
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
		
		//Initializing DB with 1 Suppliers
		Supplier supplier1 = new Supplier();
		supplier1.setName("Supplier1");
		supplierDao.persist(supplier1);
		
		//Initializing DB with 1 StoreOrder
		StoreOrder storeOrder = new StoreOrder();
		storeOrder.setStatus("STATUS 1");
		storeOrder.setEntry(entry);
		storeOrder.setStore(store);
		storeOrder.setInsertDate(calendar);
		storeOrder.setUpdateDate(calendar);
		storeOrderDao.persist(storeOrder);
		
		//Initializing BD with 1 SupplierOrder
		SupplierOrder supplierOrder = new SupplierOrder();
		supplierOrder.setInsertDate(calendar);
		supplierOrder.setUpdateDate(calendar);
		supplierOrder.setOrderNumber(12);
		supplierOrder.setStatus("status1");
		supplierOrder.setSupplier(supplier1);
		supplierOrderDao.persist(supplierOrder);

	}
	
	@After
	public void tearDown() {
		manager.close();
		factory.close();
	}

	@Test
	public void testPersist() throws SQLException {
		int oldNumberOfSupplierOrder = supplierOrderDao.listAll().size();
		
		//Getting a calendar instance
		calendar = Calendar.getInstance();
		
		//Getting a storeOrder
		StoreOrder storeOrder = (StoreOrder)storeOrderDao.listAll().get(0);
		
		//Getting a supplier
		Supplier supplier = (Supplier)supplierDao.listAll().get(0);
		
		
		//Creating supplierOrder
		SupplierOrder supplierOrder2 = new SupplierOrder();
		supplierOrder2.setInsertDate(calendar);
		supplierOrder2.setUpdateDate(calendar);
		supplierOrder2.setOrderNumber(12);
		supplierOrder2.setStatus("status1");
		supplierOrder2.setSupplier(supplier);
		supplierOrderDao.persist(supplierOrder2);
		
		int newNumberOfSupplierOrder = supplierOrderDao.listAll().size();
		
	    assertEquals(oldNumberOfSupplierOrder + 1, newNumberOfSupplierOrder);
	}
	
	@Test
	public void testUpdate() throws SQLException {
		SupplierOrder supplierOrder = (SupplierOrder)supplierOrderDao.listAll().get(0);
		
		String newStatus = "Status 2";
		assertFalse(supplierOrder.getStatus().equals(newStatus));
		
		supplierOrder.setStatus(newStatus);

		supplierOrderDao.update(supplierOrder);
		
		assertEquals(supplierOrder.getStatus(), newStatus);	
	}
	
	@Test
	public void testFindById() throws SQLException {
		//Get one SupplierOrder1
		SupplierOrder supplierOrder1 = (SupplierOrder)supplierOrderDao.listAll().get(0);
		//Look for supplierOrder with the same id
		SupplierOrder supplierOrder2 = (SupplierOrder)supplierOrderDao.findById(supplierOrder1.getSupplierOrderID());
		//Check if they are equal

	    assertEquals(supplierOrder1, supplierOrder2);
	}
	
	@Test
	public void testListAll() throws SQLException {
		int oldNumberOfSupplierOrder = supplierOrderDao.listAll().size();
		
		//Getting a calendar instance
		calendar = Calendar.getInstance();
		
		//Getting a storeOrder
		StoreOrder storeOrder = (StoreOrder)storeOrderDao.listAll().get(0);
		
		//Getting a supplier
		Supplier supplier = (Supplier)supplierDao.listAll().get(0);
		
		
		//Creating supplierOrder
		SupplierOrder supplierOrder2 = new SupplierOrder();
		supplierOrder2.setInsertDate(calendar);
		supplierOrder2.setUpdateDate(calendar);
		supplierOrder2.setOrderNumber(12);
		supplierOrder2.setStatus("status1");
		supplierOrder2.setSupplier(supplier);
		supplierOrderDao.persist(supplierOrder2);
		
		int newNumberOfSupplierOrder = supplierOrderDao.listAll().size();
		
	    assertEquals(oldNumberOfSupplierOrder + 1, newNumberOfSupplierOrder);
	}
	
}
