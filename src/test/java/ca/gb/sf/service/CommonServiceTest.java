package ca.gb.sf.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import ca.gb.sf.H2IntegrationTest;
import ca.gb.sf.models.EducatorEntity;
import ca.gb.sf.models.StudentEntity;
import ca.gb.sf.models.UserEntity;
import ca.gb.sf.services.RoleService;
import ca.gb.sf.services.UserService;

/**
 * Provides common service functionality for testing the application.
 */

public class CommonServiceTest extends H2IntegrationTest {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	private static final String adminName = "Admin";
	private static final String userName = "user1";
	private static final String educatorName = "educator1";

	/**
	 * Service test setup that will create the following common information:
	 * <li>Admin User (Admin)
	 * <li>Educator User (educator1)
	 * <li>Student User (student1, student2, student3)
	 * <li>Roles (User, Educator, Admin)
	 */
	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);

		simulateLogin(adminName);

		// Create a basic user.
		if (userService.find(userName) == null) {
			userService.save(new UserEntity(userName, "email3", "password"));
		}

		if (userService.find(educatorName) == null) {
			EducatorEntity educator = new EducatorEntity(educatorName, "email0", "password");

			// Set the list
			Set<StudentEntity> students = new TreeSet<StudentEntity>();
			students.add(new StudentEntity("student1", "email11", "password", educator));
			students.add(new StudentEntity("student2", "email21", "password", educator));
			educator.setStudents(students);

			// Add a student
			StudentEntity studentEntity = new StudentEntity("student3", "email31", "password", educator);
			educator.addStudent(studentEntity);

			// Save the educator, must persist the students.
			userService.save(educator);

		}

		roleService.save("ROLE_USER");
		roleService.save("ROLE_EDUCATOR");
		roleService.save("ROLE_ADMIN");

		simulateLogin(educatorName);

	}

	/**
	 * Test that will verify that we have an admin user, and that the
	 * application is logged in as the educator at the start of a test cycle.
	 */
	@Test
	public void commonServiceTest() {

		// Make sure admin user exists.
		UserEntity adminUser = userService.getAdminUser();
		assertNotNull(adminUser);

		// Make sure user exists.
		UserEntity user = userService.find(userName);
		assertNotNull(user);

		// Make sure that current user is logged in.
		assertNotNull(userService.getCurrentUserName());

		// Make sure that we are logged in as an educator.
		assertEquals(userService.getCurrentUserName(), educatorName);

		// Make sure that the educator has 3 students.

		List<UserEntity> users = userService.findAll();
		for (UserEntity u : users) {
			System.out.println(u);
		}

		EducatorEntity educator = (EducatorEntity) userService.find(educatorName);
		Pageable pageable = PageRequest.of(0, 100, Sort.by("displayName"));
		List<StudentEntity> students = userService.educatorStudentsList(pageable, educator);
		
		// System.out.println(students.size());
		
		// for(StudentEntity s : students) {
		// 	System.out.println(s);
		//}
		
		// Some tests may add students...  add by form.
		assertTrue(students.size() > 2);

	}

	/**
	 * Allows the application to simulate different users.
	 * 
	 * @param username
	 */
	public void simulateLogin(String username) {

		UserEntity userEntity = userService.find(username);

		Authentication authentication = Mockito.mock(Authentication.class);
		Mockito.when(authentication.getName()).thenReturn(username);

		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

		SecurityContextHolder.setContext(securityContext);
		Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userEntity);

	}

}
