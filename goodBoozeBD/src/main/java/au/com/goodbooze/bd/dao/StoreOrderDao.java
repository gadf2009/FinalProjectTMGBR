package au.com.goodbooze.bd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import au.com.goodbooze.bd.model.ItemStoreOrder;
import au.com.goodbooze.bd.model.StoreOrder;

public class StoreOrderDao{
	EntityManager manager;

	public StoreOrderDao(EntityManager manager) {
		this.manager = manager;
	}

	public int persist(Object o) {
		StoreOrder storeOrder = (StoreOrder)o;
		try {
			manager.getTransaction().begin();
			manager.persist(storeOrder);
			manager.getTransaction().commit();

			return storeOrder.getStoreOrderID();
		} catch (Exception e) {
			System.out.println("exception" + e);
			return 0;
		}
	}

	public boolean update(Object o) {
		StoreOrder storeOrder = (StoreOrder)o;
		try {
			manager.getTransaction().begin();
			manager.merge(storeOrder);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("exception" + e);
			return false;
		}
	}

	public Object findById(int id) {
		try {
			return manager.find(StoreOrder.class, id);
		} catch (Exception e) {
			System.out.println("exception" + e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object> listAll() {
		try {
			return (ArrayList<Object>) manager.createQuery(
					"FROM " + StoreOrder.class.getName()).getResultList();
		} catch (Exception e) {
			System.out.println("exception" + e);
			return null;
		}

	}
}
