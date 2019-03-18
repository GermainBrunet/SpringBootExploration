package ca.gb.sf.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.SpringContextIntegrationTest;
import ca.gb.sf.models.Product;

public class ProductServiceTest extends SpringContextIntegrationTest {

	@Autowired
	ProductService productService;
	
	@Test
	public void getProductTest() {

		Product product1 = productService.getProduct("manu1", "prod1", "mod1");
		
		Product product2 = productService.getProduct("manu1", "prod1", "mod1");
		
		assertEquals(product1.getId(), product2.getId());
		
	}
	
	
}
