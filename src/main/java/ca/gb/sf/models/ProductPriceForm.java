package ca.gb.sf.models;

public class ProductPriceForm {

	private String url;
	
	private String price;
	
	private String productId;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductPriceForm [");
		if (url != null)
			builder.append("url=").append(url).append(", ");
		if (price != null)
			builder.append("price=").append(price).append(", ");
		if (productId != null)
			builder.append("productId=").append(productId);
		builder.append("]");
		return builder.toString();
	}
	
}
