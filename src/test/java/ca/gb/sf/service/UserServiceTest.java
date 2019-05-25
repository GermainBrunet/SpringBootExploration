package ca.gb.sf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import ca.gb.sf.H2IntegrationTest;
import ca.gb.sf.SpringContextIntegrationTest;
import ca.gb.sf.services.UserService;
import ca.gb.sf.web.form.StudentForm;
import ca.gb.sf.web.form.UserRegistrationForm;

public class UserServiceTest extends CommonServiceTest {

	@Autowired
	UserService userService;
	
	@Test
	public void createUser() {
		
		UserRegistrationForm userRegistrationForm = buildUserRegistrationForm("createUser", UserService.USER_TYPE_USER);
		
		userService.save(userRegistrationForm);
		
	}

	@Test
	public void createEducator() {
		
		UserRegistrationForm userRegistrationForm = buildUserRegistrationForm("createEducator", UserService.USER_TYPE_EDUCATOR);
		
		userService.save(userRegistrationForm);
		
	}
	
	@Test
	public void createStudent() {
		
		StudentForm studentForm = buildStudentForm("createStudent");

		List<String> exerciseIds = new ArrayList<String>();
		
		exerciseIds.add("15");
		exerciseIds.add("16");
		exerciseIds.add("17");
		
		studentForm.setExerciseGroupIds(exerciseIds);
		
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
