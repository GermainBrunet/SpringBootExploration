package ca.gb.sf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import ca.gb.sf.SpringContextIntegrationTest;
import ca.gb.sf.web.form.StudentForm;
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
	
	@WithMockUser("educator")
	@Test
	public void createStudent() {
		
		Random rand = new Random();
		
		int n = rand.nextInt(100000);
		
		StudentForm studentForm = buildStudentForm(String.valueOf(n));

		List<String> exerciseIds = new ArrayList<String>();
		
		exerciseIds.add("15");
		exerciseIds.add("16");
		exerciseIds.add("17");
		
		studentForm.setExerciseIds(exerciseIds);
		
		userService.saveStudent(studentForm);
		
		
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
	
	private StudentForm buildStudentForm(String seed) {
		
		StudentForm studentForm = new StudentForm();
		
		studentForm.setDisplayName("student_" + seed);
		studentForm.setPassword("password");
		studentForm.setConfirmPassword("password");
		
		return studentForm;
		
	}
	
}
