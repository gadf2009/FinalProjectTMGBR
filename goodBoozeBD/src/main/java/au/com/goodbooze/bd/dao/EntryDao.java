package au.com.goodbooze.bd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import au.com.goodbooze.bd.model.Entry;

public class EntryDao{
	EntityManager manager;

	public EntryDao(EntityManager manager) {
		this.manager = manager;
	}

	public int persist(Object o) {
		Entry entry = (Entry) o;
		try {
			manager.getTransaction().begin();
			manager.persist(entry);
			manager.getTransaction().commit();

			return entry.getEntryID();
		} catch (Exception e) {
			System.out.println("exception" + e);
			return 0;
		}
	}

	public boolean update(Object o) {
		Entry entry = (Entry) o;
		try {
			manager.getTransaction().begin();
			manager.merge(entry);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("exception" + e);
			return false;
		}
	}

	public Object findById(int id) {
		try {
			return manager.find(Entry.class, id);
		} catch (Exception e) {
			System.out.println("exception" + e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object> listAll() {
		try {
			return (ArrayList<Object>) manager.createQuery("FROM " + Entry.class.getName()).getResultList();
		} catch (Exception e) {
			System.out.println("exception" + e);
			return null;
		}

	}

}
