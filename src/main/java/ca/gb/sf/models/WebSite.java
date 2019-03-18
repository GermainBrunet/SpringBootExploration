package ca.gb.sf.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table( name = "website" )
public class WebSite extends PersistentObject {

    @Column(nullable = false, unique=true, length=1000)
    private String url;

    @OneToMany(
            mappedBy = "webSite",
            cascade = CascadeType.ALL,
            orphanRemoval = true
        )
    private List<ProductPriceSource> productPriceSources = new ArrayList<ProductPriceSource>();
    
    public WebSite() {
    }

    public WebSite(String url) {
    	super();
    	this.url = url;
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<ProductPriceSource> getProductPriceSources() {
		return productPriceSources;
	}

	public void setProductPriceSources(List<ProductPriceSource> productPriceSources) {
		this.productPriceSources = productPriceSources;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WebSite [");
		if (url != null)
			builder.append("url=").append(url).append(", ");
		if (super.toString() != null)
			builder.append("toString()=").append(super.toString());
		builder.append("]");
		return builder.toString();
	}


}
