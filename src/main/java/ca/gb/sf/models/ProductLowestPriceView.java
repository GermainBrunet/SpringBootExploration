package ca.gb.sf.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

@Entity
@Immutable
@Table(name = "product_lowest_price_view")
public class ProductLowestPriceView {

	@Id
	@Column(name = "id")
	private long productId;

	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "model_number")
	private String modelNumber;
	
	@Column(name = "year")
	private String year;
	
	@Column(name = "manufacturer_name")
	private String manufacturerName;
	
	@Column(name = "lowest_price")
	private Double lowestPrice;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public Double getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(Double lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductLowestPriceView [productId=").append(productId).append(", ");
		if (productName != null)
			builder.append("productName=").append(productName).append(", ");
		if (modelNumber != null)
			builder.append("modelNumber=").append(modelNumber).append(", ");
		if (year != null)
			builder.append("year=").append(year).append(", ");
		if (manufacturerName != null)
			builder.append("manufacturerName=").append(manufacturerName).append(", ");
		if (lowestPrice != null)
			builder.append("lowestPrice=").append(lowestPrice);
		builder.append("]");
		return builder.toString();
	}
	
}
