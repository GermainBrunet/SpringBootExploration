package ca.gb.sf.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "product")
public class Product extends PersistentObject {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String modelNumber;

	@Column(nullable = true)
	private Integer year;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "manufacturer_id")
	private Manufacturer manufacturer;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductPriceSource> productPriceSources = new ArrayList<ProductPriceSource>();

	// @Formula("(SELECT min(pp.price) from ProductPrice pp JOIN ProductPriceSource pps ON pp.productPriceSource.id=pps.id WHERE pps.product.id = id)")
	// @Formula("(SELECT min(pp.price) from Product_Price pp JOIN Product_Price_Source pps ON pp.product_price_source_id=pps.id WHERE pps.product_id = id)")
	@Formula("(select min(pp.price) " + 
			"from " + 
			"  Product_Price pp " + 
			"join product_price_source pps on pp.product_price_source_id = pps.id " + 
			"where pps.product_id = id)")
	// @Transient
	private Double minimumPrice;

	public Product() {
	}

	public Product(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<ProductPriceSource> getProductPriceSources() {
		return productPriceSources;
	}

	public void setProductPriceSources(List<ProductPriceSource> productPriceSources) {
		this.productPriceSources = productPriceSources;
	}

	public Double getMinimumPrice() {
		return minimumPrice;
	}

	public void setMinimumPrice(Double minimumPrice) {
		this.minimumPrice = minimumPrice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [");
		if (name != null)
			builder.append("name=").append(name).append(", ");
		if (modelNumber != null)
			builder.append("modelNumber=").append(modelNumber).append(", ");
		if (year != null)
			builder.append("year=").append(year).append(", ");
		if (manufacturer != null)
			builder.append("manufacturer=").append(manufacturer).append(", ");
		if (super.toString() != null)
			builder.append("toString()=").append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
