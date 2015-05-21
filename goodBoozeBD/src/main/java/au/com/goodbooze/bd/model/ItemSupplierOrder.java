package au.com.goodbooze.bd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class ItemSupplierOrder{

	@Id@GeneratedValue
	protected int itemSupplierOrderID;
    protected long quantity;
    @ManyToOne
    @JoinColumn(name = "productID", nullable = false)
    protected Product product;
    @ManyToOne
	@JoinColumn(name = "supplierOrderID", nullable = false)
    protected SupplierOrder supplierOrder;
    

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long value) {
        this.quantity = value;
    }

	public Product getProduct() {
		return this.product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

    public SupplierOrder getSupplierOrder() {
        return supplierOrder;
    }

    public void setSupplierOrder(SupplierOrder value) {
        this.supplierOrder = value;
    }

    public int getItemSupplierOrderID() {
        return itemSupplierOrderID;
    }

    public void setItemSupplierOrderID(int itemSupplierOrderID) {
        this.itemSupplierOrderID = itemSupplierOrderID;
    }

}
