package ca.gb.sf.util;

public class UnitOfWork {

	String manufacturerName;
	
	String productName;
	
	String productNumber;
	
	String sourceURL;
	
	Double price;

	public UnitOfWork() {}
	
	public UnitOfWork(String manufacturerName, String productName, String productNumber, String sourceURL,
			Double price) {
		super();
		this.manufacturerName = manufacturerName;
		this.productName = productName;
		this.productNumber = productNumber;
		this.sourceURL = sourceURL;
		this.price = price;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getSourceURL() {
		return sourceURL;
	}

	public void setSourceURL(String sourceURL) {
		this.sourceURL = sourceURL;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UnitOfWork [");
		if (manufacturerName != null)
			builder.append("manufacturerName=").append(manufacturerName).append(", ");
		if (productName != null)
			builder.append("productName=").append(productName).append(", ");
		if (productNumber != null)
			builder.append("productNumber=").append(productNumber).append(", ");
		if (sourceURL != null)
			builder.append("sourceURL=").append(sourceURL).append(", ");
		if (price != null)
			builder.append("price=").append(price);
		builder.append("]");
		return builder.toString();
	}
	
}
