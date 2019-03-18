package ca.gb.sf.repositories.repo;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.SpringContextIntegrationTest;
import ca.gb.sf.models.Manufacturer;
import ca.gb.sf.models.ProductView;
import ca.gb.sf.repositories.ManufacturerRepository;
import ca.gb.sf.repositories.ProductViewRepository;

public class ProductViewRepoTest extends SpringContextIntegrationTest {
	
	@Autowired
	ProductViewRepository productViewRepo;

	@Test
	public void viewTest() {
		
		
		List<ProductView> productsByPrice = productViewRepo.findByProductId(1L);
		
		for (ProductView productView : productsByPrice) {
			
			System.out.println(productView.getPrice());
			
		}
		
				
	}

}
