package ca.gb.sf.models;

import ca.gb.sf.H2IntegrationTest;
import ca.gb.sf.repositories.UserRepository;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentEntityTest extends H2IntegrationTest {

	@Autowired
	UserRepository userRepository;
	
	@Test(expected = ConstraintViolationException.class)
	public void createEmtpy() {
		
		StudentEntity student = new StudentEntity();
		
		userRepository.save(student);
		
	}

	@Test(expected = ConstraintViolationException.class)
	public void createWithoutDisplayName() {
		
		StudentEntity student = new StudentEntity();
		
		student.setEmail("email");
		student.setPassword("password");
		
		userRepository.save(student);
		
	}

	@Test(expected = ConstraintViolationException.class)
	public void createWithoutEmailName() {
		
		StudentEntity student = new StudentEntity();
		
		student.setDisplayName("displayName");
		student.setPassword("password");
		
		userRepository.save(student);
		
	}

	@Test(expected = ConstraintViolationException.class)
	public void createWithoutPasswordName() {
		
		StudentEntity student = new StudentEntity();
		
		student.setDisplayName("displayName");
		student.setEmail("email");
		
		userRepository.save(student);
		
	}

}
