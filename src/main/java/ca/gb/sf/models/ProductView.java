package ca.gb.sf.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

@Entity
@Immutable
@Table(name = "product_price_view")
public class ProductView {

	@Column(name = "pr_id")
	private long productId;
	
	@Id
	@Column(name = "pps_id")
	private long productPriceSourceId;
	
	@Column(name = "productname")
	private String productName;
	
	@Column(name = "productmodelnumber")
	private String modelNumber;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "querystring")
	private String queryString;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "date")
	private Timestamp date;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getProductPriceSourceId() {
		return productPriceSourceId;
	}

	public void setProductPriceSourceId(long productPriceSourceId) {
		this.productPriceSourceId = productPriceSourceId;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
}
