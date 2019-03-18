package ca.gb.sf.repositories.repo;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.SpringContextIntegrationTest;
import ca.gb.sf.models.Manufacturer;
import ca.gb.sf.models.Product;
import ca.gb.sf.repositories.ManufacturerRepository;
import ca.gb.sf.repositories.ProductRepository;

public class ProductRepoTest extends SpringContextIntegrationTest {
	
	@Autowired
	ManufacturerRepository manufacturerRepo;

	@Autowired
	ProductRepository productRepo;
	
	Manufacturer manufacturer;
	
	@Before
	public void before() {
		
		manufacturer = new Manufacturer("test1");
		manufacturerRepo.save(manufacturer);
		
	}
	
	@Test
	public void createTest() {
		
		long countBefore = productRepo.count();

		Product product = new Product("test product");
		product.setModelNumber("test model number");
		product.setManufacturer(manufacturer);
		product = productRepo.save(product);
		
		long countAfter = productRepo.count();
		
		long difference = countAfter - countBefore;
		
		assertTrue(difference == 1);
		
		System.out.println("count before = " + countBefore);
		
	}

	/**
	@Test
	public void updateTest() {
		
		long countBefore = manufacturerRepo.count();
		
		Manufacturer manufacturer = new Manufacturer("test1");
		manufacturer = manufacturerRepo.save(manufacturer);
		
		long id = manufacturer.getId();
		
		long countAfter = manufacturerRepo.count();
		
		long difference = countAfter - countBefore;
		
		assertTrue(difference == 1);
		
	}
	**/

}
