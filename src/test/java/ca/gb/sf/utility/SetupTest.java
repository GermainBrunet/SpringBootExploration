package ca.gb.sf.utility;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ca.gb.sf.SpringContextIntegrationTest;
import ca.gb.sf.Start;
import ca.gb.sf.models.AssignmentEntity;
import ca.gb.sf.models.EducatorEntity;
import ca.gb.sf.models.ExerciseEntity;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.StudentEntity;
import ca.gb.sf.repositories.AssignmentRepository;
import ca.gb.sf.repositories.ExerciseGroupRepository;
import ca.gb.sf.repositories.ExerciseRepository;
import ca.gb.sf.repositories.UserRepository;
import ca.gb.sf.services.AssignmentService;
import ca.gb.sf.services.ExerciseGroupService;
import ca.gb.sf.services.ExerciseService;
import ca.gb.sf.services.UserService;
import ca.gb.sf.web.form.StudentForm;
import ca.gb.sf.web.form.UserRegistrationForm;

@SpringBootTest(classes = Start.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations="classpath:application-prod.yml")
@ActiveProfiles("prod")
public class SetupTest extends SpringContextIntegrationTest {

	// @Autowired
	// ExerciseRepository exerciseRepository;
	
	@Autowired
	ExerciseService exerciseService;
	
	@Autowired
	UserService userService;

	// @Autowired
	// ExerciseGroupRepository exerciseGroupRepository;
	@Autowired
	ExerciseGroupService exerciseGroupService;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AssignmentService assignmentService;

	@Before
	public void setup() {
		
		// Mock login with an administrative user.
		MockitoAnnotations.initMocks(this);
		
		String educatorName = "educator1";
		
		EducatorEntity educator = (EducatorEntity) userService.save(new EducatorEntity("educator1", "email0", "password"));
		
		Authentication authentication = Mockito.mock(Authentication.class);
		Mockito.when(authentication.getName()).thenReturn("edu22");
		
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		
		SecurityContextHolder.setContext(securityContext);
		Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(educator);

		// Update the data
		deleteAllData();
		
		createExercise1();
		createExercise2();
		createExercise3();
		createExercise4();

		
	}
	
	@Test
	public void count() {
		
		System.out.println(exerciseGroupService.count());
		System.out.println(exerciseService.count());
		System.out.println(userRepository.count());
		System.out.println(assignmentService.count());
		
		
	}
	
	public void deleteAllData() {
		
		exerciseService.deleteAll();
		exerciseGroupService.deleteAll();
		assignmentService.deleteAll();
		
	}
	
	public void createExercise1() {

		ExerciseGroupEntity exerciseGroup = exerciseGroupService.createUsingName("ma 1.1");
		
		String writtenInstructions = "Transforme le mots";
		String readTitle = "transforme le mots pour faire"; 
		
		exerciseService.create("ma", "mi", writtenInstructions, readTitle + " mi", 1, exerciseGroup);
		exerciseService.create("mi", "mo", writtenInstructions, readTitle + " mo",  2, exerciseGroup);
		exerciseService.create("mo", "me", writtenInstructions, readTitle + " me",  3, exerciseGroup);
		exerciseService.create("me", "mu", writtenInstructions, readTitle + " mu",  4, exerciseGroup);
		exerciseService.create("mu", "lu", writtenInstructions, readTitle + " lu",  5, exerciseGroup);
		exerciseService.create("lu", "li", writtenInstructions, readTitle + " li",  6, exerciseGroup);
		exerciseService.create("li", "la", writtenInstructions, readTitle + " la",  7, exerciseGroup);
		exerciseService.create("la", "lo", writtenInstructions, readTitle + " lo",  8, exerciseGroup);
		exerciseService.create("lo", "le", writtenInstructions, readTitle + " le",  9, exerciseGroup);
		exerciseService.create("le", "me", writtenInstructions, readTitle + " me", 10, exerciseGroup);

	}
	
	public void createExercise2() {

		ExerciseGroupEntity exerciseGroup = exerciseGroupService.createUsingName("la 1.2");

		String writtenInstructions = "Transforme le mots";
		String readTitle = "transforme le mots pour faire"; 

		exerciseService.create("la", "ma", writtenInstructions,readTitle + " ma", 1, exerciseGroup);
		exerciseService.create("ma", "fa", writtenInstructions,readTitle + " fa", 2, exerciseGroup);
		exerciseService.create("fa", "fi", writtenInstructions,readTitle + " fi", 3, exerciseGroup);
		exerciseService.create("fi", "mi", writtenInstructions,readTitle + " mi", 4, exerciseGroup);
		exerciseService.create("mi", "li", writtenInstructions,readTitle + " li", 5, exerciseGroup);
		exerciseService.create("li", "lo", writtenInstructions,readTitle + " lo", 6, exerciseGroup);
		exerciseService.create("lo", "fo", writtenInstructions,readTitle + " fo", 7, exerciseGroup);
		exerciseService.create("fo", "mo", writtenInstructions,readTitle + " mo", 8, exerciseGroup);
		exerciseService.create("mo", "lo", writtenInstructions,readTitle + " lo", 9, exerciseGroup);
		exerciseService.create("lo", "li", writtenInstructions,readTitle + " li", 10, exerciseGroup);

	}

	public void createExercise3() {

		ExerciseGroupEntity exerciseGroup = exerciseGroupService.createUsingName("lafo 1.3");
		
		String writtenInstructions = "Transforme le mots";
		String readTitle = "transforme le mots pour faire"; 

		exerciseService.create("lafo", "lafa", writtenInstructions,readTitle + " lafa", 1, exerciseGroup);
		exerciseService.create("lafa", "lafi", writtenInstructions,readTitle + " lafi", 2, exerciseGroup);
		exerciseService.create("lafi", "lami", writtenInstructions,readTitle + " lami", 3, exerciseGroup);
		exerciseService.create("lami", "lali", writtenInstructions,readTitle + " lali", 4, exerciseGroup);
		exerciseService.create("lali", "lalo", writtenInstructions,readTitle + " lalo", 5, exerciseGroup);
		exerciseService.create("lalo", "lamo", writtenInstructions,readTitle + " lamo", 6, exerciseGroup);
		exerciseService.create("lamo", "lamu", writtenInstructions,readTitle + " lamu", 7, exerciseGroup);
		exerciseService.create("lamu", "amu", writtenInstructions,readTitle + " amu", 8, exerciseGroup);
		exerciseService.create("amu", "afu", writtenInstructions,readTitle + " afu", 9, exerciseGroup);
		exerciseService.create("afu", "afo", writtenInstructions,readTitle + " afo", 10, exerciseGroup);

	}

	public void createExercise4() {

		ExerciseGroupEntity exerciseGroup = exerciseGroupService.createUsingName("malo 1.4");

		String writtenInstructions = "Transforme le mots";
		String readTitle = "transforme le mots pour faire"; 

		exerciseService.create("malo", "malu", writtenInstructions,readTitle + " malo", 1, exerciseGroup);
		exerciseService.create("malu", "mala", writtenInstructions,readTitle + " malu", 2, exerciseGroup);
		exerciseService.create("mala", "mala", writtenInstructions,readTitle + " mala", 3, exerciseGroup);
		exerciseService.create("mala", "mafa", writtenInstructions,readTitle + " mafa", 4, exerciseGroup);
		exerciseService.create("mafa", "mafi", writtenInstructions,readTitle + " mafi", 5, exerciseGroup);
		exerciseService.create("mafi", "mafo", writtenInstructions,readTitle + " mafo", 6, exerciseGroup);
		exerciseService.create("mafo", "mafu", writtenInstructions,readTitle + " mafu", 7, exerciseGroup);
		exerciseService.create("mafu", "afu", writtenInstructions,readTitle + " afu", 8, exerciseGroup);
		exerciseService.create("afu", "afi", writtenInstructions,readTitle + " afi", 9, exerciseGroup);
		exerciseService.create("afi", "mafi", writtenInstructions,readTitle + " mafa", 10, exerciseGroup);

	}

	public void createUsers() {
		
		EducatorEntity educator = new EducatorEntity();
		educator.setDisplayName("educator1");
		educator.setPassword("Password#1");
		educator.setEmail("abc@abc123.com");
		// educator.setRoles(roles);
		
		userRepository.save(educator);
		
		StudentEntity student = new StudentEntity();
		student.setDisplayName("student1");
		student.setPassword("Password#1");
		student.setEmail("abc2@abc123.com");
		student.setEducator(educator);
		
		userRepository.save(student);
		
	}
	
	public void createAssignments() {
	
		StudentEntity student = (StudentEntity) userRepository.findByDisplayName("student1");
		ExerciseGroupEntity exerciseGroup1 = exerciseGroupService.findByName("la 1.2");
		ExerciseGroupEntity exerciseGroup2 = exerciseGroupService.findByName("lafo 1.3");
		ExerciseGroupEntity exerciseGroup3 = exerciseGroupService.findByName("malo 1.4");
		
		AssignmentEntity assignment1 = assignmentService.create(student, exerciseGroup1);
		AssignmentEntity assignment2 = assignmentService.create(student, exerciseGroup2);
		
		List<AssignmentEntity> assignments = assignmentService.findListByStudent(student);
		
		assertTrue(assignments.size() == 2);

		AssignmentEntity assignment = assignmentService.findByStudentAndExerciseGroup(student, exerciseGroup3);
		assertNull(assignment);

		assignment = assignmentService.findByStudentAndExerciseGroup(student, exerciseGroup1);
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
		
		StudentEntity student = (StudentEntity) userRepository.findByDisplayName("student1");
		ExerciseGroupEntity exerciseGroup1 = exerciseGroupService.findByName("la 1.2");

		// List<Exercise> exercises = exerciseRepository.findExercisesByStudentAndExerciseGroup(student, exerciseGroup1);
		AssignmentEntity assignment = assignmentService.findByStudentAndExerciseGroup(student, exerciseGroup1);
		
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
	
}
