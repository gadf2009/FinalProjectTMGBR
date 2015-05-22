package au.com.goodbooze.bd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import au.com.goodbooze.bd.model.ItemStoreOrder;

public class ItemStoreOrderDao{
	EntityManager manager;

	public ItemStoreOrderDao(EntityManager manager) {
		this.manager = manager;
	}

	public int persist(Object o) {
		ItemStoreOrder itemStoreOrder = (ItemStoreOrder) o;
		try {
			manager.getTransaction().begin();
			manager.persist(itemStoreOrder);
			manager.getTransaction().commit();

			return itemStoreOrder.getItemStoreOrderID();
		} catch (Exception e) {
			System.out.println("exception" + e);
			return 0;
		}
	}

	public boolean update(Object o) {
		ItemStoreOrder itemStoreOrder = (ItemStoreOrder) o;
		try {
			manager.getTransaction().begin();
			manager.merge(itemStoreOrder);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("exception" + e);
			return false;
		}
	}

	public Object findById(int id) {
		try {
			return manager.find(ItemStoreOrder.class, id);
		} catch (Exception e) {
			System.out.println("exception" + e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object> listAll() {
		try {
			return (ArrayList<Object>) manager.createQuery(
					"FROM " + ItemStoreOrder.class.getName()).getResultList();
		} catch (Exception e) {
			System.out.println("exception" + e);
			return null;
		}

	}

	public void delete(int id) {
		try {
			ItemStoreOrder itemStoreOrder = (ItemStoreOrder)findById(id);
			manager.getTransaction().begin();
			manager.remove(itemStoreOrder);
			manager.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

}
