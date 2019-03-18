package ca.gb.sf.repositories.repo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.SpringContextIntegrationTest;
import ca.gb.sf.models.Manufacturer;
import ca.gb.sf.repositories.ManufacturerRepository;

public class ManufacturerRepoTest extends SpringContextIntegrationTest {
	
	@Autowired
	ManufacturerRepository manufacturerRepo;

	@Test
	public void addTest() {
		
		long countBefore = manufacturerRepo.count();
		
		Manufacturer manufacturer = new Manufacturer("test1");
		manufacturerRepo.save(manufacturer);
		
		long countAfter = manufacturerRepo.count();
		
		long difference = countAfter - countBefore;
		
		assertTrue(difference == 1);
		
		System.out.println("count before = " + countBefore);
		
	}

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

}
