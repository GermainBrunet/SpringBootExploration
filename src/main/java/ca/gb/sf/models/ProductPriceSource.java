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

@Entity
@Table( name = "productPriceSource" )
public class ProductPriceSource extends PersistentObject {

    @Column(nullable = false, length=1000)
    private String queryString;
    
    @Column(nullable = true)
    private String xPathToPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "website_id")
    private WebSite webSite;
    
    @OneToMany(
            mappedBy = "productPriceSource",
            cascade = CascadeType.ALL,
            orphanRemoval = true
        )
    private List<ProductPrice> productPrices = new ArrayList<ProductPrice>();
    
	// @Formula("(select pp from product_price pp where pp.date = (select max(date) from product_price) and pp.product_price_source_id = id)")
    // @Formula("(select pp from ProductPrice pp where pp.date = (select max(date) from ProductPrice) and pp.product_price_source_id = id)")
	// private ProductPrice mostRecentProductPrice;
    
    public ProductPriceSource() {
    }

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	
	public String getxPathToPrice() {
		return xPathToPrice;
	}

	public void setxPathToPrice(String xPathToPrice) {
		this.xPathToPrice = xPathToPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

	public List<ProductPrice> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(List<ProductPrice> productPrices) {
		this.productPrices = productPrices;
	}

}
