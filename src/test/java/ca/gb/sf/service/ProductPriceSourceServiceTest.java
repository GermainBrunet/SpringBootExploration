package ca.gb.sf.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.SpringContextIntegrationTest;
import ca.gb.sf.models.Product;
import ca.gb.sf.models.ProductPriceSource;

public class ProductPriceSourceServiceTest extends SpringContextIntegrationTest {

	@Autowired
	ProductPriceSourceService productPriceSourceService;
	
	@Autowired
	ProductPriceService productPriceService;
	
	@Test
	public void getProductPriceSourceTest() {

		String webLink = "https://www.staples.ca/en/dell-refurbished-e6520-latitude-15-6-inch-notebook-2-3-ghz-intel-core-i5-2410m-128-gb-ssd-8-gb-ddr3-windows-10-professional/product_2929496_1-CA_1_20001";
		
		ProductPriceSource productPriceSource1 = productPriceSourceService.getProductPriceSource(webLink, "manu1", "prod1", "mod1");
		
		ProductPriceSource productPriceSource2 = productPriceSourceService.getProductPriceSource(webLink, "manu1", "prod1", "mod1");
		
		assertEquals(productPriceSource1.getId(), productPriceSource2.getId());
		
		System.out.println(productPriceSource1);
		System.out.println(productPriceSource1.getId());
		
	}
		
}
