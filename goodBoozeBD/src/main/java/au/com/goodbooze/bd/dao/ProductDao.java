package au.com.goodbooze.bd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import au.com.goodbooze.bd.model.Product;

public class ProductDao{
	EntityManager manager;

	public ProductDao(EntityManager manager) {
		this.manager = manager;
	}

	public int persist(Object o) {
		Product product = (Product) o;
		try {
			manager.getTransaction().begin();
			manager.persist(product);
			manager.getTransaction().commit();

			return product.getInternalID();
		} catch (Exception e) {
			System.out.println("exception" + e);
			return 0;
		}
	}

	public boolean update(Object o) {
		Product product = (Product) o;
		try {
			manager.getTransaction().begin();
			manager.merge(product);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("exception" + e);
			return false;
		}
	}

	public Object findById(int id) {
		try {
			return manager.find(Product.class, id);
		} catch (Exception e) {
			System.out.println("exception" + e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object> listAll() {
		try {
			return (ArrayList<Object>) manager.createQuery("FROM " + Product.class.getName()).getResultList();
		} catch (Exception e) {
			System.out.println("exception" + e);
			return null;
		}

	}

	public void delete(int id) {
		try {
			Product product = (Product)findById(id);
			manager.getTransaction().begin();
			manager.remove(product);
			manager.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

}
