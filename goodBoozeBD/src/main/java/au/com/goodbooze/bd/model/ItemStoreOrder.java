package au.com.goodbooze.bd.model;


import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class ItemStoreOrder{

    protected long quantity;
    @ManyToOne
    @JoinColumn(name = "productID", nullable = false)
    protected Product product;
    @ManyToOne
	@JoinColumn(name = "storeOrderID", nullable = false)
    protected StoreOrder storeOrder;
    @Id@GeneratedValue
    protected int itemStoreOrderID;

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
    public StoreOrder getStoreOrder() {
        return storeOrder;
    }
    public void setStoreOrder(StoreOrder value) {
        this.storeOrder = value;
    }

   public int getItemStoreOrderID() {
        return itemStoreOrderID;
    }

    public void setItemStoreOrderID(int itemStoreOrderID) {
        this.itemStoreOrderID = itemStoreOrderID;
    }

}
