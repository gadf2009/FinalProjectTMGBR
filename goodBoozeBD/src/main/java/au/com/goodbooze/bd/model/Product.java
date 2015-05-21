package au.com.goodbooze.bd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Product {
	@Column(nullable = false)
	private String name;
	@Id@GeneratedValue
	private int internalID;
	private int productSupplierID;
	@ManyToOne
	@JoinColumn(name = "supplierID", nullable = false)
	private Supplier supplier; 

	public int getInternalID() {
		return internalID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProductSupplierID() {
		return productSupplierID;
	}

	public void setProductSupplierID(int productSupplierID) {
		this.productSupplierID = productSupplierID;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier value) {
		this.supplier = value;
	}





}
