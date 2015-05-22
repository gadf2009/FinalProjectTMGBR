package au.com.goodbooze.bd.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Store{
	@Column(nullable=false)
	private String name;
	@Id@GeneratedValue
	private int storeID;
    @OneToMany
    @JoinColumn(name="storeID")
    protected Collection<StoreOrder> storeOrderList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getStoreID() {
        return storeID;
    }
    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }
	public ArrayList<StoreOrder> getStoreOrder() {
		return (ArrayList<StoreOrder>) this.storeOrderList;
	}
	public void setStoreOrder(ArrayList<StoreOrder> storeOrderList) {
		this.storeOrderList = storeOrderList;
	}
}
