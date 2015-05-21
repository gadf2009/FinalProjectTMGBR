package au.com.goodbooze.bd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import au.com.goodbooze.bd.model.Store;

public class StoreDao{
	EntityManager manager;

	public StoreDao(EntityManager manager) {
		this.manager = manager;
	}

	public int persist(Object o) {
		Store store = (Store) o;
		try {
			manager.getTransaction().begin();
			manager.persist(store);
			manager.getTransaction().commit();

			return store.getStoreID();
		} catch (Exception e) {
			System.out.println("exception" + e);
			return 0;
		}
	}

	public boolean update(Object o) {
		Store store = (Store) o;
		try {
			manager.getTransaction().begin();
			manager.merge(store);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("exception" + e);
			return false;
		}
	}

	public Object findById(int id) {
		try {
			return manager.find(Store.class, id);
		} catch (Exception e) {
			System.out.println("exception" + e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object> listAll() {
		try {
			return (ArrayList<Object>) manager.createQuery("FROM " + Store.class.getName()).getResultList();
		} catch (Exception e) {
			System.out.println("exception" + e);
			return null;
		}

	}

	public void delete(int id) {
		try {
			Store store = (Store)findById(id);
			manager.getTransaction().begin();
			manager.remove(store);
			manager.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

}
