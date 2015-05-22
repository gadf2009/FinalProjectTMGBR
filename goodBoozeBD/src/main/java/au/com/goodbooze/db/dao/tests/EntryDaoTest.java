package au.com.goodbooze.db.dao.tests;
import java.sql.SQLException;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.goodbooze.bd.dao.EntryDao;
import au.com.goodbooze.bd.dao.StoreDao;
import au.com.goodbooze.bd.model.Entry;
import au.com.goodbooze.bd.model.Store;


public class EntryDaoTest extends TestCase{
	String persistenceUnitName = "liquor_store_tests";
	
	EntityManagerFactory factory;
	EntityManager manager;
	
	EntryDao entryDao;
	
	Calendar calendar;
	
	int initalNumberOfStores;
	
	@Before
	public void setUp() {
		factory = Persistence.createEntityManagerFactory(persistenceUnitName);
		manager = factory.createEntityManager();
		entryDao = new EntryDao(manager);
		
		calendar = Calendar.getInstance();
		
		//Initializing DB with 2 Entries
		Entry entry1 = new Entry();
		entry1.setInsertDate(calendar);
		entry1.setProcessDate(calendar);
		entryDao.persist(entry1);
		
		Entry entry2 = new Entry();
		entry2.setInsertDate(calendar);
		entry2.setProcessDate(calendar);
		entryDao.persist(entry2);
	}
	@After
	public void tearDown() {
		manager.close();
		factory.close();
	}
	
	@Test
	public void testPersist() throws SQLException {
		
		int oldNumberOfEntries = entryDao.listAll().size();
		
		Entry entry3 = new Entry();
		entry3.setInsertDate(calendar);
		entry3.setProcessDate(calendar);
		entryDao.persist(entry3);
		
		int newNumberOfEntries = entryDao.listAll().size();
		
	    assertEquals(oldNumberOfEntries + 1, newNumberOfEntries);
	}
	
	
	@Test
	public void testUpdate() throws SQLException {
		
		Entry entry = (Entry)entryDao.listAll().get(0);
		Calendar newProcessDate = calendar.getInstance();
		
		//old calendar instance is not equal to new name
		assertFalse(entry.getProcessDate().equals(newProcessDate));
		
		entry.setProcessDate(newProcessDate);
		
		assertEquals(entry.getProcessDate(), newProcessDate);
	}
	
	@Test
	public void testFindById() throws SQLException {
		//Get one Entry
		Entry entry1 = (Entry)entryDao.listAll().get(0);
		//Look for Store with the same id
		Entry entry2 = (Entry)entryDao.findById(entry1.getEntryID());
		//Check if they are equal
	    assertEquals(entry1, entry2);
	}
	
	@Test
	public void testListAll() throws SQLException {
		int oldNumberOfEntries = entryDao.listAll().size();
		
		Entry entry3 = new Entry();
		entry3.setInsertDate(calendar);
		entry3.setProcessDate(calendar);
		entryDao.persist(entry3);
		
		int newNumberOfEntries = entryDao.listAll().size();
		
	    assertEquals(oldNumberOfEntries + 1, newNumberOfEntries);
	}
	
}
