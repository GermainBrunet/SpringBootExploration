package ca.gb.sf.service;

import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.SpringContextIntegrationTest;
import ca.gb.sf.web.form.UserRegistrationForm;
import ca.gb.sf.web.service.UserService;
import ca.gb.sf.web.service.UserServiceImpl;

public class UserServiceTest extends SpringContextIntegrationTest {

	@Autowired
	UserService userService;
	
	@Test
	public void createUser() {
		
		Random rand = new Random();
		
		int n = rand.nextInt(100000);
		
		UserRegistrationForm userRegistrationForm = buildUserRegistrationForm(String.valueOf(n), UserServiceImpl.USER_TYPE_USER);
		
		userService.save(userRegistrationForm);
		
	}

	@Test
	public void createEducator() {
		
		Random rand = new Random();
		
		int n = rand.nextInt(100000);

		UserRegistrationForm userRegistrationForm = buildUserRegistrationForm(String.valueOf(n), UserServiceImpl.USER_TYPE_EDUCATOR);
		
		userService.save(userRegistrationForm);
		
	}

	private UserRegistrationForm buildUserRegistrationForm(String seed, String userType) {
		
		UserRegistrationForm userRegistrationForm = new UserRegistrationForm();
		
		userRegistrationForm.setDisplayName("display name " + seed);
		userRegistrationForm.setEmail("email" + seed + "@bla.com");
		userRegistrationForm.setConfirmEmail("email" + seed + "@bla.com");
		userRegistrationForm.setPassword("password");
		userRegistrationForm.setConfirmPassword("password");
		
		userRegistrationForm.setTerms(true);
		userRegistrationForm.setUserType(userType);
		
		return userRegistrationForm;
		
	}
	
}
