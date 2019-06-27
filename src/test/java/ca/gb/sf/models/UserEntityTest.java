package ca.gb.sf.models;

import ca.gb.sf.H2IntegrationTest;
import ca.gb.sf.repositories.UserRepository;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserEntityTest extends H2IntegrationTest {

	@Autowired
	UserRepository userRepository;
	
	@Test(expected = ConstraintViolationException.class)
	public void createEmtpy() {
		
		UserEntity user = new UserEntity();
		
		userRepository.save(user);
		
	}

	@Test(expected = ConstraintViolationException.class)
	public void createWithoutDisplayName() {
		
		UserEntity user = new UserEntity();
		
		user.setEmail("email");
		user.setPassword("password");
		
		userRepository.save(user);
		
	}

	@Test(expected = ConstraintViolationException.class)
	public void createWithoutEmailName() {
		
		UserEntity user = new UserEntity();
		
		user.setDisplayName("displayName");
		user.setPassword("password");
		
		userRepository.save(user);
		
	}

	@Test(expected = ConstraintViolationException.class)
	public void createWithoutPasswordName() {
		
		UserEntity user = new UserEntity();
		
		user.setDisplayName("displayName");
		user.setEmail("email");
		
		userRepository.save(user);
		
	}

}
