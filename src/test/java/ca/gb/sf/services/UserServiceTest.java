package ca.gb.sf.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ca.gb.sf.models.EducatorEntity;
import ca.gb.sf.models.RoleEntity;
import ca.gb.sf.models.StudentEntity;
import ca.gb.sf.models.UserEntity;
import ca.gb.sf.models.UserRole;
import ca.gb.sf.services.RoleService;
import ca.gb.sf.services.UserService;
import ca.gb.sf.web.form.StudentForm;
import ca.gb.sf.web.form.UserRegistrationForm;

public class UserServiceTest extends CommonServiceTest {

	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;

	/**
	 * UserEntity Tests
	 **/

	@Test(expected = ConstraintViolationException.class)
	public void createUserEmtpy() {

		UserEntity userEntity = new UserEntity();

		userService.save(userEntity);

	}

	@Test
	public void createUser() {

		String displayName = "userServiceTest-createUser-DisplayName";
		String email = "userServiceTest-createUser-Email";
		String password = "userServiceTest-createUser-Password";

		UserEntity userEntity = new UserEntity();
		userEntity.setDisplayName(displayName);
		userEntity.setEmail(email);
		userEntity.setPassword(password);

		// Create
		UserEntity savedUserEntity = userService.save(userEntity);

		// Read
		UserEntity findUserEntity = userService.find(displayName);
		assertEquals(findUserEntity.getDisplayName(), displayName);
		assertEquals(findUserEntity.getEmail(), email);
		assertEquals(findUserEntity.getPassword(), password);
		
		RoleEntity userRoleEntity = roleService.find(UserRole.User); 
		assertTrue(findUserEntity.getRoles().contains(userRoleEntity));

		// Update
		String updatedEmail = "userServiceTest-createUser-Email-Update";
		savedUserEntity.setEmail(updatedEmail);
		userService.save(savedUserEntity);

		// CheckUpdate
		UserEntity updatedUserEntity = userService.find(displayName);
		assertEquals(updatedUserEntity.getEmail(), updatedEmail);

		// Delete
		userService.deleteById(updatedUserEntity.getId());

		UserEntity deletedUserEntity = userService.find(displayName);

		assertNull(deletedUserEntity);

	}

	@Test
	public void createUserUsingResistrationForm() {

		String userName = "createUserUsingRegistrationForm";
		
		// Create the user
		UserRegistrationForm userRegistrationForm = buildUserRegistrationForm(userName, UserRole.User);

		// Verify persistence
		UserEntity savedUserEntity = userService.save(userRegistrationForm);
		assertNotNull(savedUserEntity);
		assertTrue(savedUserEntity.getId() != 0);
		
		// Check the user
		UserEntity checkUserEntity = userService.find(userName);
		assertNotNull(checkUserEntity);
		assertEquals(checkUserEntity.getDisplayName(), userName);
		RoleEntity userRoleEntity = roleService.find(UserRole.User); 
		assertTrue(checkUserEntity.getRoles().contains(userRoleEntity));

	}

	/**
	 * EducatorEntity Tests
	 **/
	
	@Test(expected = ConstraintViolationException.class)
	public void createEducatorEmtpy() {

		EducatorEntity educatorEntity = new EducatorEntity();

		userService.save(educatorEntity);

	}

	@Test
	public void createEducator() {

		String displayName = "userServiceTest-createEducator-DisplayName";
		String email = "userServiceTest-createEducator-Email";
		String password = "userServiceTest-createEducator-Password";

		EducatorEntity educatorEntity = new EducatorEntity();
		educatorEntity.setDisplayName(displayName);
		educatorEntity.setEmail(email);
		educatorEntity.setPassword(password);

		// Create
		EducatorEntity savedEducatorEntity = (EducatorEntity) userService.save(educatorEntity);

		// Read
		EducatorEntity findEducatorEntity = (EducatorEntity) userService.find(displayName);
		assertEquals(findEducatorEntity.getDisplayName(), displayName);
		assertEquals(findEducatorEntity.getEmail(), email);
		assertEquals(findEducatorEntity.getPassword(), password);
		
		// Role
		RoleEntity userRoleEntity = roleService.find(UserRole.Educator); 
		assertTrue(findEducatorEntity.getRoles().contains(userRoleEntity));
		
		// Update
		String updatedEmail = "userServiceTest-createEducator-Email-Update";
		savedEducatorEntity.setEmail(updatedEmail);
		userService.save(savedEducatorEntity);

		// CheckUpdate
		EducatorEntity updatedEducatorEntity = (EducatorEntity) userService.find(displayName);
		assertEquals(updatedEducatorEntity.getEmail(), updatedEmail);

		// Delete
		userService.deleteById(updatedEducatorEntity.getId());

		EducatorEntity deletedEducatorEntity = (EducatorEntity) userService.find(displayName);

		assertNull(deletedEducatorEntity);

	}
	
	@Test
	public void createEducatorUsingRegistrationForm() {

		String educatorName = "createEducatorUsingRegistrationForm";
		
		UserRegistrationForm userRegistrationForm = buildUserRegistrationForm(educatorName, UserRole.Educator);

		userService.save(userRegistrationForm);
		
		EducatorEntity educatorEntity = (EducatorEntity) userService.find(educatorName);
		
		assertNotNull(educatorEntity);
		
		assertEquals(educatorEntity.getDisplayName(), educatorName);

	}

	/**
	 * StudentEntity Tests
	 **/
	
	@Test(expected = ConstraintViolationException.class)
	public void createStudentEmpty() {

		StudentEntity studentEntity = new StudentEntity();

		userService.save(studentEntity);

	}

	@Test
	public void createStudent() {

		String displayName = "userServiceTest-createStudent-DisplayName";
		String email = "userServiceTest-createStudent-Email";
		String password = "userServiceTest-createStudent-Password";

		EducatorEntity educatorEntity = (EducatorEntity) userService.getCurrentUserEntity();
		
		System.out.println("Educator id: " + educatorEntity.getId());
		
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setDisplayName(displayName);
		studentEntity.setEmail(email);
		studentEntity.setPassword(password);
		studentEntity.setEducator(educatorEntity);

		// Create
		StudentEntity savedStudentEntity = (StudentEntity) userService.save(studentEntity);

		// Read
		StudentEntity findStudentEntity = (StudentEntity) userService.find(displayName);
		assertEquals(findStudentEntity.getDisplayName(), displayName);
		assertEquals(findStudentEntity.getEmail(), email);
		assertEquals(findStudentEntity.getPassword(), password);
		
		// Role
		RoleEntity userRoleEntity = roleService.find(UserRole.User); 
		assertTrue(findStudentEntity.getRoles().contains(userRoleEntity));
		
		// Update
		String updatedEmail = "userServiceTest-createStudent-Email-Update";
		savedStudentEntity.setEmail(updatedEmail);
		userService.save(savedStudentEntity);

		// CheckUpdate
		StudentEntity updatedStudentEntity = (StudentEntity) userService.find(displayName);
		assertEquals(updatedStudentEntity.getEmail(), updatedEmail);

		// Find the user using the educator.
		Pageable pageable = PageRequest.of(0, 100, Sort.by("displayName"));
		List<StudentEntity> students = userService.educatorStudentsList(pageable, educatorEntity);
		
		System.out.println("Display a list of students");
		for (StudentEntity s : students) {
			
			System.out.println(s);
			
		}
		
		assertTrue(students.contains(updatedStudentEntity));
				
		// Delete
		userService.deleteById(updatedStudentEntity.getId());
		EducatorEntity deletedEducatorEntity = (EducatorEntity) userService.find(displayName);
		assertNull(deletedEducatorEntity);

	}
	
	@Test
	public void createStudentUsingStudentForm() {

		String studentName = "createStudentUsingStudentForm";
		
		StudentForm studentForm = buildStudentForm(studentName);

		UserEntity userEntity = userService.saveStudentForm(studentForm);
		assertTrue(userEntity.getId() != 0);
		
		for (UserEntity user : userService.findAll()) {
			
			System.out.println(user.getDisplayName());
			
		}
		
		StudentEntity studentEntity = (StudentEntity) userService.find(studentName);
		
		assertNotNull(studentEntity);
		assertEquals(studentEntity.getDisplayName(), studentName);

	}

	private UserRegistrationForm buildUserRegistrationForm(String seed, UserRole userRole) {

		UserRegistrationForm userRegistrationForm = new UserRegistrationForm();

		userRegistrationForm.setDisplayName(seed);
		userRegistrationForm.setEmail("email" + seed + "@bla.com");
		userRegistrationForm.setConfirmEmail("email" + seed + "@bla.com");
		userRegistrationForm.setPassword("password");
		userRegistrationForm.setConfirmPassword("password");

		userRegistrationForm.setTerms(true);
		userRegistrationForm.setUserType(userRole.getFormName());

		return userRegistrationForm;

	}

	private StudentForm buildStudentForm(String seed) {

		StudentForm studentForm = new StudentForm();

		studentForm.setDisplayName(seed);
		studentForm.setPassword("password");
		studentForm.setConfirmPassword("password");

		return studentForm;

	}
}
