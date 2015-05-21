package au.com.goodbooze.bd.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class StoreOrder{
	
	@Temporal(TemporalType.TIMESTAMP)
    protected Calendar insertDate;
	@Temporal(TemporalType.TIMESTAMP)
    protected Calendar processDate;
    protected String status;
    @Id@GeneratedValue
    protected int storeOrderID;
    @ManyToOne
    @JoinColumn(name = "entryID", nullable = false)
    protected Entry entry;
    @OneToMany
    @JoinColumn(name="storeOrderID")
    protected Collection<ItemStoreOrder> itemStoreOrderList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Calendar getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Calendar insertDate) {
        this.insertDate = insertDate;
    }

    public Calendar getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Calendar processDate) {
        this.processDate = processDate;
    }
    
    public int getStoreOrderID() {
        return storeOrderID;
    }

    public void setStoreOrderID(int storeOrderID) {
        this.storeOrderID = storeOrderID;
    }

    public Entry getEntry(){
		return entry;
	}
	public void setIdClient(Entry entry){
		this.entry = entry;
	}
	
	public ArrayList<ItemStoreOrder> getItemStoreOrder() {
		return (ArrayList<ItemStoreOrder>) this.itemStoreOrderList;
	}
	public void setItemStoreOrder(ArrayList<ItemStoreOrder> itemStoreOrderList) {
		this.itemStoreOrderList = itemStoreOrderList;
	}

    
    

}
