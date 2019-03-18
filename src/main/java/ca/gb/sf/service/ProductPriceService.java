package ca.gb.sf.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ca.gb.sf.models.Manufacturer;
import ca.gb.sf.models.ProductPrice;
import ca.gb.sf.models.ProductPriceSource;
import ca.gb.sf.models.WebSite;
import ca.gb.sf.repositories.ManufacturerRepository;
import ca.gb.sf.repositories.ProductPriceRepository;
import ca.gb.sf.repositories.WebSiteRepository;

@Service
public class ProductPriceService {

	@Autowired
	ProductPriceRepository productPriceRepo;
	
	/**
	 * Function that will return a manufacture or create one if one does not exist.  Will return anull if the manufacturer name is empty.
	 * 
	 * @param manufacturerName
	 * 
	 * @return
	 */
	public ProductPrice getProductPrice(ProductPriceSource productPriceSource, Timestamp priceTimestamp, Double price) {
		
		if (productPriceSource == null || priceTimestamp == null || price == null) {
		
			return null;
			
		}
		
		ProductPrice productPrice = productPriceRepo.findByProductPriceSourceTimestamp(productPriceSource.getId(), priceTimestamp);
		
		if (productPrice != null) {
			
			return productPrice;
			
		}
		
		productPrice = new ProductPrice();
		
		productPrice.setDate(priceTimestamp);
		
		productPrice.setPrice(price);
		
		productPrice.setProductPriceSource(productPriceSource);
		
		productPrice = productPriceRepo.save(productPrice);
		
		return productPrice;
		
	}
	
	
}
