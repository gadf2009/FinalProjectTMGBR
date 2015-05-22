package au.com.goodbooze.bd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Supplier{
	@Column(nullable=false)
	private String name;
	@Id@GeneratedValue
	private int supplierId;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}
