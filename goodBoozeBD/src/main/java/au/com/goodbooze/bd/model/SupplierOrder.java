package au.com.goodbooze.bd.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class SupplierOrder{

	@Id@GeneratedValue
	protected int supplierOrderID;
    protected String status;
    @Temporal(TemporalType.TIMESTAMP)
    protected Calendar insertDate;
    @Temporal(TemporalType.TIMESTAMP)
    protected Calendar updateDate;
    protected int orderNumber;
    @OneToMany
    @JoinColumn(name="supplierOrderID")
    protected Collection<ItemSupplierOrder> itemSupplierOrderList;
    @ManyToOne
    @JoinColumn(name = "supplierID", nullable = false)
    protected Supplier supplier;
    @ManyToMany
	private Collection <StoreOrder> storeOrderList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getSupplierOrderID() {
        return supplierOrderID;
    }

    public void setSupplierOrderID(int supplierOrderID) {
        this.supplierOrderID = supplierOrderID;
    }

    public Calendar getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Calendar insertDate) {
        this.insertDate = insertDate;
    }

    public Calendar getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Calendar updateDate) {
        this.updateDate = updateDate;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier value) {
        this.supplier = value;
    }	
    
	public ArrayList<ItemSupplierOrder> getItemSupplierOrderList() {
		return (ArrayList<ItemSupplierOrder>) this.itemSupplierOrderList;
	}
	public void setItemSupplierOrderList(ArrayList<ItemSupplierOrder> itemSupplierOrderList) {
		this.itemSupplierOrderList = itemSupplierOrderList;
	}

}
