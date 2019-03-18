package ca.gb.sf.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ca.gb.sf.models.Product;
import ca.gb.sf.models.ProductPrice;
import ca.gb.sf.models.ProductPriceSource;
import ca.gb.sf.models.WebSite;
import ca.gb.sf.repositories.ProductRepository;
import ca.gb.sf.util.UnitOfWork;

@Service
public class PrincipalService {

	@Autowired
	ProductPriceSourceService productPriceSourceService;
	
	@Autowired
	ProductPriceService productPriceService;
	
	@Autowired
	ProductService productService;

	@Autowired
	WebSiteService webSiteService;
	
	@Autowired
	ProductRepository productRepository;
	
	public void persist(UnitOfWork unitOfWork) {
		
		if (StringUtils.isEmpty(unitOfWork.getProductName())) {
			
			return;
			
		}
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		persist(unitOfWork, timestamp);
		
	}
	
	public void persist(UnitOfWork unitOfWork, Timestamp timestamp) {
		
		if (StringUtils.isEmpty(unitOfWork.getProductName())) {
			
			return;
			
		}
		
		ProductPriceSource productPriceSource = productPriceSourceService.getProductPriceSource(unitOfWork.getSourceURL(), unitOfWork.getManufacturerName(), unitOfWork.getProductName(), unitOfWork.getProductNumber());
		
		ProductPrice productPrice = productPriceService.getProductPrice(productPriceSource, timestamp, unitOfWork.getPrice());
		
		System.out.println(productPriceSource);
		System.out.println(productPrice);
		
	}
	
	public void saveWebSiteReference(String productId, String urlString, String priceString) {
		
		if (productId == null) {
			
			// throw new Exception("No product id");
			
			// e.printStackTrace();
			
		}
		
		
		URL url = null;
		
		try {
			
			url = new URL(urlString);
			
		} catch (MalformedURLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			// throw new Exception("Cant figure out url: " + urlString);
			
		}
		
		Optional<Product> optionalProduct = productRepository.findById(Long.valueOf(productId));
		Product product = optionalProduct.get();
		
		String host = url.getHost();

		String queryString = url.getPath() + url.getQuery();

		WebSite webSite = webSiteService.getWebSite(host);
		
		ProductPriceSource productPriceSource = productPriceSourceService.getProductPriceSource(queryString, product, webSite);
		
		Double price = Double.valueOf(priceString);
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		ProductPrice productPrice = productPriceService.getProductPrice(productPriceSource, timestamp, price);
		
		System.out.println("productPrice saved!!!");
		
		
		// System.out.println("host = " + host);
		// System.out.println("queryString = " + queryString);
		// Product product = productService.getProduct(manufacturerName, productName, modelNumber);
		
		
	}
	
	
}
