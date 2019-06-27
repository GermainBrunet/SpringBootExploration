package ca.gb.sf.models;

import ca.gb.sf.H2IntegrationTest;
import ca.gb.sf.repositories.UserRepository;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EducatorEntityTest extends H2IntegrationTest {

	@Autowired
	UserRepository userRepository;
	
	@Test(expected = ConstraintViolationException.class)
	public void createEmtpy() {
		
		EducatorEntity educator = new EducatorEntity();
		
		userRepository.save(educator);
		
	}

	@Test(expected = ConstraintViolationException.class)
	public void createWithoutDisplayName() {
		
		EducatorEntity educator = new EducatorEntity();
		
		educator.setEmail("email");
		educator.setPassword("password");
		
		userRepository.save(educator);
		
	}

	@Test(expected = ConstraintViolationException.class)
	public void createWithoutEmailName() {
		
		EducatorEntity educator = new EducatorEntity();
		
		educator.setDisplayName("displayName");
		educator.setPassword("password");
		
		userRepository.save(educator);
		
	}

	@Test(expected = ConstraintViolationException.class)
	public void createWithoutPasswordName() {
		
		EducatorEntity educator = new EducatorEntity();
		
		educator.setDisplayName("displayName");
		educator.setEmail("email");
		
		userRepository.save(educator);
		
	}

}
