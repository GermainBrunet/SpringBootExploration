package ca.gb.sf.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table( name = "manufacturer" )
public class Manufacturer extends PersistentObject {

    @Column(nullable = false, unique=true)
    private String name;
    
    @OneToMany(
            mappedBy = "manufacturer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
        )
    private List<Product> products = new ArrayList<Product>();

    public Manufacturer() {
    }

    public Manufacturer(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Manufacturer [");
		if (name != null)
			builder.append("name=").append(name).append(", ");
		if (super.toString() != null)
			builder.append("toString()=").append(super.toString());
		builder.append("]");
		return builder.toString();
	}
    
    
}
