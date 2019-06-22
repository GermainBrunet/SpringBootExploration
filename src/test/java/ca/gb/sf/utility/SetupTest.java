package ca.gb.sf.utility;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ca.gb.sf.SpringContextIntegrationTest;
import ca.gb.sf.Start;
import ca.gb.sf.models.AssignmentEntity;
import ca.gb.sf.models.AssignmentStatusEntity;
import ca.gb.sf.models.EducatorEntity;
import ca.gb.sf.models.ExerciseEntity;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.RoleEntity;
import ca.gb.sf.models.StudentEntity;
import ca.gb.sf.models.UserEntity;
import ca.gb.sf.repositories.AssignmentRepository;
import ca.gb.sf.repositories.ExerciseGroupRepository;
import ca.gb.sf.repositories.ExerciseRepository;
import ca.gb.sf.repositories.UserRepository;
import ca.gb.sf.services.AssignmentService;
import ca.gb.sf.services.AssignmentStatusService;
import ca.gb.sf.services.ExerciseGroupService;
import ca.gb.sf.services.ExerciseService;
import ca.gb.sf.services.RoleService;
import ca.gb.sf.services.UserService;
import ca.gb.sf.util.SetupExercises;
import ca.gb.sf.web.form.StudentForm;
import ca.gb.sf.web.form.UserRegistrationForm;

@SpringBootTest(classes = Start.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations="classpath:application-prod.yml")
@ActiveProfiles("prod")
// @Transactional
public class SetupTest extends SpringContextIntegrationTest {

	@Autowired
	ExerciseService exerciseService;
	
	@Autowired
	UserService userService;

	@Autowired
	ExerciseGroupService exerciseGroupService;

	@Autowired
	RoleService roleService;
	
	@Autowired
	AssignmentService assignmentService;

	@Autowired
	AssignmentStatusService assignmentStatusService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	SetupExercises setupExercises;
	
	
	
	@Before
	public void setup() {
				
	}
	
	@Test
	@Commit
	@Rollback(false)
	public void count() {
		
		MockitoAnnotations.initMocks(this);

		// Update the data
		deleteAllData();

		// Load the admin user and make sure he exists.
		UserEntity adminUser = userService.getAdminUser();
		assertNotNull(adminUser);

		// Simulate the admin user for spring security purposes.
		simulateLogin(adminUser.getDisplayName());

		// Setup Roles
		roleService.save("ROLE_USER");
		roleService.save("ROLE_EDUCATOR");
		roleService.save("ROLE_ADMIN");
		
		assignmentStatusService.save(AssignmentStatusEntity.ASSIGNED, "FR-Assigned", "Assigned");
		assignmentStatusService.save(AssignmentStatusEntity.WORK_IN_PROGRESS, "FR-Work In Progress", "Work In Progress");
		assignmentStatusService.save(AssignmentStatusEntity.COMPLETED, "FR-Completed", "Completed");
		
		String educatorName = "edu22";
		
		EducatorEntity educator = (EducatorEntity) userService.save(new EducatorEntity(educatorName, "email0", passwordEncoder.encode("Password#1")));
		
		Authentication authentication = Mockito.mock(Authentication.class);
		Mockito.when(authentication.getName()).thenReturn(educatorName);
		
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		
		SecurityContextHolder.setContext(securityContext);
		Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(educator);

		setupExercises.deleteAllExercises();
		setupExercises.createAllExercises();
		
		createStudents(educator);
		
		System.out.println(exerciseGroupService.count());
		System.out.println(exerciseService.count());
		System.out.println(userService.count());
		System.out.println(assignmentService.count());
		
		
	}
	
	public void deleteAllData() {
		
		exerciseService.deleteAll();
		exerciseGroupService.deleteAll();
		assignmentService.deleteAll();
		userService.deleteAll();
		
	}

	public void createStudents(EducatorEntity educator) {
		
		Set<StudentEntity> students = new TreeSet<StudentEntity>();
		
		RoleEntity userRole = roleService.find("ROLE_USER");
		Set<RoleEntity> roles = new TreeSet<RoleEntity>();
		roles.add(userRole);
		
		for (int i = 1; i < 100; i++) {
			
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setDisplayName("stu" + i);
			studentEntity.setEmail("stu" + i + "@email.address");
			studentEntity.setPassword(passwordEncoder.encode("Password#1"));
			studentEntity.setRoles(roles);
			
			students.add(studentEntity);
			
		}
		
		// EducatorEntity educator = (EducatorEntity) userService.find("edu22");
		educator.setStudents(students);
		userService.save(educator);
		
	}
	
	public void createUsers() {
		
		EducatorEntity educator = new EducatorEntity();
		educator.setDisplayName("educator1");
		educator.setPassword("Password#1");
		educator.setEmail("abc@abc123.com");
		// educator.setRoles(roles);

		StudentEntity student = new StudentEntity();
		student.setDisplayName("student1");
		student.setPassword("Password#1");
		student.setEmail("abc2@abc123.com");
		student.setEducator(educator);

		Set<StudentEntity> students = new TreeSet<StudentEntity>();
		students.add(student);
		educator.setStudents(students);
		
		userService.save(educator);
		
	}
	
	public void createAssignments() {
	
		StudentEntity student = (StudentEntity) userService.find("student1");
		ExerciseGroupEntity exerciseGroup1 = exerciseGroupService.findByName("la 1.2");
		ExerciseGroupEntity exerciseGroup2 = exerciseGroupService.findByName("lafo 1.3");
		ExerciseGroupEntity exerciseGroup3 = exerciseGroupService.findByName("malo 1.4");
		
		AssignmentEntity assignment1 = assignmentService.create(student, exerciseGroup1);
		AssignmentEntity assignment2 = assignmentService.create(student, exerciseGroup2);
		
		List<AssignmentEntity> assignments = assignmentService.findListByStudent(student);
		
		assertTrue(assignments.size() == 2);

		AssignmentEntity assignment = assignmentService.findByUserAndExerciseGroup(student, exerciseGroup3);
		assertNull(assignment);

		assignment = assignmentService.findByUserAndExerciseGroup(student, exerciseGroup1);
		assertNotNull(assignment);
		
		ExerciseGroupEntity exerciseGroup = assignment.getExerciseGroup();
		assertNotNull(exerciseGroup);
		
		List<ExerciseEntity> exercises = exerciseService.findAll();
		
		assertTrue(exercises.size() == 40);
		
		for (ExerciseEntity exercise : exercises) {
			
			System.out.println(exercise);
			
		}
		
		System.out.println("HERE!!!");
		
		// ExerciseGroupEntity exerciseGroupInit = exerciseGroupService.initialize(exerciseGroup);
		
		List<ExerciseEntity> exercisesUsingGroup = exerciseService.findByExerciseGroup(exerciseGroup);
		
		System.out.println(exercisesUsingGroup.size());
		
		assertTrue(exercisesUsingGroup.size() == 10);
		
		System.out.println("HERE AFTER !!!");
		
		ExerciseEntity exercise = exercisesUsingGroup.get(0);
		
		System.out.println(exercise);
		
	}
	
	public void loadExercise() {
		
		StudentEntity student = (StudentEntity) userService.find("student1");
		ExerciseGroupEntity exerciseGroup1 = exerciseGroupService.findByName("la 1.2");

		// List<Exercise> exercises = exerciseRepository.findExercisesByStudentAndExerciseGroup(student, exerciseGroup1);
		AssignmentEntity assignment = assignmentService.findByUserAndExerciseGroup(student, exerciseGroup1);
		
		List<ExerciseEntity> exercises = exerciseService.findByExerciseGroup(exerciseGroup1);
		
		
		System.out.println("Loading Exercise");
		
		ExerciseEntity e1 = exerciseService.findFirst(exerciseGroup1);
		System.out.println(e1);

		for (int i = 0; i < 15; i++) {
			e1 = exerciseService.findNext(exerciseGroup1, e1.getId());
			System.out.println(e1);
			if (e1 == null) {
				break;
			}
		}
		
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
