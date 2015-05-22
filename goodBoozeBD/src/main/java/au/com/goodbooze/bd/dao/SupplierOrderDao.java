package au.com.goodbooze.bd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import au.com.goodbooze.bd.model.SupplierOrder;

public class SupplierOrderDao{
	EntityManager manager;

	public SupplierOrderDao(EntityManager manager) {
		this.manager = manager;
	}

	public int persist(Object o) {
		SupplierOrder supplierOrder = (SupplierOrder)o;
		try {
			manager.getTransaction().begin();
			manager.persist(supplierOrder);
			manager.getTransaction().commit();

			return supplierOrder.getSupplierOrderID();
		} catch (Exception e) {
			System.out.println("exception" + e);
			return 0;
		}
	}

	public boolean update(Object o) {
		SupplierOrder supplierOrder = (SupplierOrder)o;
		try {
			manager.getTransaction().begin();
			manager.merge(supplierOrder);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("exception" + e);
			return false;
		}
	}

	public Object findById(int id) {
		try {
			return manager.find(SupplierOrder.class, id);
		} catch (Exception e) {
			System.out.println("exception" + e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object> listAll() {
		try {
			return (ArrayList<Object>) manager.createQuery(
					"FROM " + SupplierOrder.class.getName()).getResultList();
		} catch (Exception e) {
			System.out.println("exception" + e);
			return null;
		}

	}
}
