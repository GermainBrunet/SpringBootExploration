package ca.gb.sf.service;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.gb.sf.models.Product;
import ca.gb.sf.models.ProductPriceSource;
import ca.gb.sf.models.WebSite;
import ca.gb.sf.repositories.ProductPriceSourceRepository;

@Service
public class ProductPriceSourceService {

	@Autowired
	ProductPriceSourceRepository productPriceSourceRepo;
	
	@Autowired
	ManufacturerService manufacturerService;
	
	@Autowired
	WebSiteService webSiteService;
	
	@Autowired
	ProductService productService;
	
	/**
	 * Function that will return a manufacture or create one if one does not exist.  Will return anull if the manufacturer name is empty.
	 * 
	 * @param manufacturerName
	 * 
	 * @return
	 */
	public ProductPriceSource getProductPriceSource(String queryString, Product product, WebSite webSite) {
		
		if (product == null || webSite == null) {
		
			return null;
			
		}
		
		ProductPriceSource productPriceSource = productPriceSourceRepo.findByProductWebSite(product.getId(), webSite.getId());
		
		if (productPriceSource != null) {
			
			return productPriceSource;
			
		}
		
		productPriceSource = new ProductPriceSource();
		
		productPriceSource.setQueryString(queryString);
		
		productPriceSource.setProduct(product);
		
		productPriceSource.setWebSite(webSite);

		productPriceSource = productPriceSourceRepo.save(productPriceSource);
		
		return productPriceSource;
		
	}
	
	public ProductPriceSource getProductPriceSource(String urlString, String manufacturerName, String productName, String modelNumber) {
	
		URL url;
		
		try {
			
			url = new URL(urlString);
			
		} catch (MalformedURLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
		
		String host = url.getHost();

		String queryString = url.getPath() + url.getQuery();

		System.out.println("host = " + host);
		System.out.println("queryString = " + queryString);
		
		Product product = productService.getProduct(manufacturerName, productName, modelNumber);
		
		WebSite webSite = webSiteService.getWebSite(host);
		
		if (product == null || webSite == null) {
			
			return null;
			
		}
		
		return getProductPriceSource(queryString, product, webSite);
		
	}
	
	
	
	
}
