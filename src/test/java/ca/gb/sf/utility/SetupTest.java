package ca.gb.sf.utility;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import ca.gb.sf.web.form.StudentForm;
import ca.gb.sf.web.form.UserRegistrationForm;
import ca.gb.sf.web.service.UserService;

@SpringBootTest(classes = Start.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations="classpath:application.test.yml")
// @TestPropertySource(locations="classpath:application.yml")
//@Transactional
public class SetupTest extends SpringContextIntegrationTest {

	// @Autowired
	// ExerciseRepository exerciseRepository;
	
	@Autowired
	ExerciseService exerciseService;

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
		
		deleteAllData();
		
		createExercise1();
		createExercise2();
		createExercise3();
		createExercise4();
		
		createUsers();
		
		createAssignments();
		
		loadExercise();
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
		// userRepository.deleteAll();
		assignmentService.deleteAll();
		
		
	}
	
	public void createExercise1() {

		ExerciseGroupEntity exerciseGroup = exerciseGroupService.create("ma 1.1");
		
		exerciseService.create("ma", "mi", "transforme le mot", 1, exerciseGroup);
		exerciseService.create("mi", "mo", "transforme le mot", 2, exerciseGroup);
		exerciseService.create("mo", "me", "transforme le mot", 3, exerciseGroup);
		exerciseService.create("me", "mu", "transforme le mot", 4, exerciseGroup);
		exerciseService.create("mu", "lu", "transforme le mot", 5, exerciseGroup);
		exerciseService.create("lu", "li", "transforme le mot", 6, exerciseGroup);
		exerciseService.create("li", "la", "transforme le mot", 7, exerciseGroup);
		exerciseService.create("la", "lo", "transforme le mot", 8, exerciseGroup);
		exerciseService.create("lo", "le", "transforme le mot", 9, exerciseGroup);
		exerciseService.create("le", "me", "transforme le mot", 10, exerciseGroup);

	}
	
	public void createExercise2() {

		ExerciseGroupEntity exerciseGroup = exerciseGroupService.create("la 1.2");

		List<ExerciseEntity> exerciseList = new ArrayList<ExerciseEntity>();
		
		exerciseService.create("la", "ma", "transforme le mot", 1, exerciseGroup);
		exerciseService.create("ma", "fa", "transforme le mot", 2, exerciseGroup);
		exerciseService.create("fa", "fi", "transforme le mot", 3, exerciseGroup);
		exerciseService.create("fi", "mi", "transforme le mot", 4, exerciseGroup);
		exerciseService.create("mi", "li", "transforme le mot", 5, exerciseGroup);
		exerciseService.create("li", "lo", "transforme le mot", 6, exerciseGroup);
		exerciseService.create("lo", "fo", "transforme le mot", 7, exerciseGroup);
		exerciseService.create("fo", "mo", "transforme le mot", 8, exerciseGroup);
		exerciseService.create("mo", "lo", "transforme le mot", 9, exerciseGroup);
		exerciseService.create("lo", "li", "transforme le mot", 10, exerciseGroup);

	}

	public void createExercise3() {

		ExerciseGroupEntity exerciseGroup = exerciseGroupService.create("lafo 1.3");
		
		List<ExerciseEntity> exerciseList = new ArrayList<ExerciseEntity>();
		
		exerciseService.create("lafo", "lafa", "transforme le mot", 1, exerciseGroup);
		exerciseService.create("lafa", "lafi", "transforme le mot", 2, exerciseGroup);
		exerciseService.create("lafi", "lami", "transforme le mot", 3, exerciseGroup);
		exerciseService.create("lami", "lali", "transforme le mot", 4, exerciseGroup);
		exerciseService.create("lali", "lalo", "transforme le mot", 5, exerciseGroup);
		exerciseService.create("lalo", "lamo", "transforme le mot", 6, exerciseGroup);
		exerciseService.create("lamo", "lamu", "transforme le mot", 7, exerciseGroup);
		exerciseService.create("lamu", "amu", "transforme le mot", 8, exerciseGroup);
		exerciseService.create("amu", "afu", "transforme le mot", 9, exerciseGroup);
		exerciseService.create("afu", "afo", "transforme le mot", 10, exerciseGroup);

	}

	public void createExercise4() {

		ExerciseGroupEntity exerciseGroup = exerciseGroupService.create("malo 1.4");

		List<ExerciseEntity> exerciseList = new ArrayList<ExerciseEntity>();
		
		exerciseService.create("malo", "malu", "transforme le mot", 1, exerciseGroup);
		exerciseService.create("malu", "mala", "transforme le mot", 2, exerciseGroup);
		exerciseService.create("mala", "mala", "transforme le mot", 3, exerciseGroup);
		exerciseService.create("mala", "mafa", "transforme le mot", 4, exerciseGroup);
		exerciseService.create("mafa", "mafi", "transforme le mot", 5, exerciseGroup);
		exerciseService.create("mafi", "mafo", "transforme le mot", 6, exerciseGroup);
		exerciseService.create("mafo", "mafu", "transforme le mot", 7, exerciseGroup);
		exerciseService.create("mafu", "afu", "transforme le mot", 8, exerciseGroup);
		exerciseService.create("afu", "afi", "transforme le mot", 9, exerciseGroup);
		exerciseService.create("afi", "mafi", "transforme le mot", 10, exerciseGroup);

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
