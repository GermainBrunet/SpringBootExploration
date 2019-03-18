package ca.gb.sf.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "productPrice" )
public class ProductPrice extends PersistentObject {

    @Column(nullable = false)
    private Timestamp date;
    
    @Column(nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_price_source_id")
    private ProductPriceSource productPriceSource;
    
    public ProductPrice() {
    }

    public ProductPrice(Double price) {
    	super();
    	this.price = price;
    }

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ProductPriceSource getProductPriceSource() {
		return productPriceSource;
	}

	public void setProductPriceSource(ProductPriceSource productPriceSource) {
		this.productPriceSource = productPriceSource;
	}

}
