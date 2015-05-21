package au.com.goodbooze.bd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Store{
	@Column(nullable=false)
	private String name;
	@Id@GeneratedValue
	private int storeID;

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
}
