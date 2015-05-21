package au.com.goodbooze.bd.model;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Entry {

	@Temporal(TemporalType.TIMESTAMP)
    protected Calendar insertDate;
	@Temporal(TemporalType.TIMESTAMP)
    protected Calendar processDate;
    @Id@GeneratedValue
    protected int entryID;
    @OneToMany
    @JoinColumn(name="entryID")
    protected Collection<StoreOrder> storeOrderList;

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
    
    public int getEntryID() {
        return entryID;
    }

    public void setEntryID(int value) {
        this.entryID = value;
    }

	public ArrayList<StoreOrder> getStoreOrder() {
		return (ArrayList<StoreOrder>) this.storeOrderList;
	}
	public void setStoreOrder(ArrayList<StoreOrder> storeOrderList) {
		this.storeOrderList = storeOrderList;
	}
}
