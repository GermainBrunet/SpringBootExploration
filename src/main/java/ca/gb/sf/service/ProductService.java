package ca.gb.sf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ca.gb.sf.models.Manufacturer;
import ca.gb.sf.models.Product;
import ca.gb.sf.repositories.ManufacturerRepository;
import ca.gb.sf.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	ManufacturerService manufacturerService;
	
	/**
	 * Function that will return a manufacture or create one if one does not exist.  Will return anull if the manufacturer name is empty.
	 * 
	 * @param manufacturerName
	 * 
	 * @return
	 */
	public Product getProduct(Manufacturer manufacturer, String productName, String modelNumber) {
		
		if (manufacturer == null || StringUtils.isEmpty(productName) || StringUtils.isEmpty(modelNumber)) {
		
			return null;
			
		}
		
		Product product = productRepo.findByManufacturerNameNumber(manufacturer.getId(), productName, modelNumber);
		
		if (product != null) {
			
			return product;
			
		}
		
		product = new Product();
		
		product.setName(productName);

		product.setModelNumber(modelNumber);
		
		product.setManufacturer(manufacturer);

		product = productRepo.save(product);
		
		return product;
		
	}
	
	public Product getProduct(String manufacturerName, String productName, String modelNumber) {
	
		Manufacturer manufacturer = manufacturerService.getManufacturer(manufacturerName);
		
		if (manufacturer == null) {
			
			return null;
			
		}
		
		return getProduct(manufacturer, productName, modelNumber);
		
	}
	
}
