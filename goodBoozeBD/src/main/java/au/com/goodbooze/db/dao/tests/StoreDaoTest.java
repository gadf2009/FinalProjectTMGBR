package au.com.goodbooze.db.dao.tests;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.goodbooze.bd.dao.StoreDao;
import au.com.goodbooze.bd.model.Store;


public class StoreDaoTest extends TestCase{
	String persistenceUnitName = "liquor_store_tests";
	
	EntityManagerFactory factory;
	EntityManager manager;
	StoreDao storeDao;
	
	int initalNumberOfStores;
	
	@Before
	public void setUp() {
		factory = Persistence.createEntityManagerFactory(persistenceUnitName);
		manager = factory.createEntityManager();
		storeDao = new StoreDao(manager);
		
		//Initializing DB with 3 Stores
		
		Store store1 = new Store();
		store1.setName("Store 1");
		
		Store store2 = new Store();
		store2.setName("Store 2");
		
		Store store3 = new Store();
		store3.setName("Store 3");
		
		storeDao.persist(store1);
		storeDao.persist(store2);
		storeDao.persist(store3);

		initalNumberOfStores = 3;
	}
	@After
	public void tearDown() {
		manager.close();
		factory.close();
	}
	
	@Test
	public void testPersist() throws SQLException {
		
		Store store4 = new Store();
		store4.setName("Store4");
		storeDao.persist(store4);
		
		int newNumberOfStores = storeDao.listAll().size();
		
	    assertEquals(initalNumberOfStores + 1, newNumberOfStores);
	}
	
	
	@Test
	public void testUpdate() throws SQLException {
		
		Store oldStore = (Store)storeDao.listAll().get(1);
		String new_name = "New Store";
		
		//old name is not equal to new name
		assertFalse(oldStore.getName().equals(new_name));
	
		oldStore.setName(new_name);
		storeDao.update(oldStore);
		
		Store updatedStore = (Store)storeDao.findById(oldStore.getStoreID());
		
	    assertEquals(updatedStore.getName(), new_name);
	}
	
	@Test
	public void testFindById() throws SQLException {
		//Get first Store
		Store Store1 = (Store)storeDao.listAll().get(1);
		//Look for Store 1 id
		Store Store2 = (Store)storeDao.findById(Store1.getStoreID());
		//Check if they are equal
	    assertEquals(Store1, Store2);
	}
	
	@Test
	public void testListAll() throws SQLException {
		int oldQuantityOfStores = storeDao.listAll().size();
		
		//Add one Store
		
		Store store4 = new Store();
		store4.setName("Store4");
		storeDao.persist(store4);
		int newQuantityOfStores = storeDao.listAll().size();

	    assertEquals(oldQuantityOfStores+1, newQuantityOfStores);
	}
	
	@Test
	public void testDelete() throws SQLException {
		int oldQuantityOfStores = storeDao.listAll().size();
		
		//Get first Store
		Store store = (Store)storeDao.listAll().get(0);
		
		storeDao.delete(store.getStoreID());
		
		int newQuantityOfStores = storeDao.listAll().size();

	    assertEquals(oldQuantityOfStores-1, newQuantityOfStores);
	}

	
}
