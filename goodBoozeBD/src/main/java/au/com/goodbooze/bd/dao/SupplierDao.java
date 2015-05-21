package au.com.goodbooze.bd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import au.com.goodbooze.bd.model.Supplier;

public class SupplierDao{
	EntityManager manager;

	public SupplierDao(EntityManager manager) {
		this.manager = manager;
	}

	public long persist(Object o) {
		Supplier sup = (Supplier) o;
		try {
			manager.getTransaction().begin();
			manager.persist(sup);
			manager.getTransaction().commit();

			return sup.getSupplierId();
		} catch (Exception e) {
			System.out.println("exception" + e);
			return 0;
		}
	}

	public boolean update(Object o) {
		Supplier sup = (Supplier) o;
		try {
			manager.getTransaction().begin();
			manager.merge(sup);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("exception" + e);
			return false;
		}
	}

	public Object findById(long id) {
		try {
			return manager.find(Supplier.class, id);
		} catch (Exception e) {
			System.out.println("exception" + e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object> listAll() {
		try {
			return (ArrayList<Object>) manager.createQuery(
					"FROM " + Supplier.class.getName()).getResultList();
		} catch (Exception e) {
			System.out.println("exception" + e);
			return null;
		}

	}

	public void delete(long id) {
		try {
			Supplier sup = (Supplier)findById(id);
			manager.getTransaction().begin();
			manager.remove(sup);
			manager.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

}
