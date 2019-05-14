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
import ca.gb.sf.models.Assignment;
import ca.gb.sf.models.Educator;
import ca.gb.sf.models.Exercise;
import ca.gb.sf.models.ExerciseGroup;
import ca.gb.sf.models.Student;
import ca.gb.sf.repositories.AssignmentRepository;
import ca.gb.sf.repositories.ExerciseGroupRepository;
import ca.gb.sf.repositories.ExerciseRepository;
import ca.gb.sf.repositories.UserRepository;
import ca.gb.sf.web.form.StudentForm;
import ca.gb.sf.web.form.UserRegistrationForm;
import ca.gb.sf.web.service.UserService;

@SpringBootTest(classes = Start.class)
@RunWith(SpringJUnit4ClassRunner.class)
//@TestPropertySource(locations="classpath:application.test.yml")
@TestPropertySource(locations="classpath:application.yml")
//@Transactional
public class SetupTest extends SpringContextIntegrationTest {

	@Autowired
	ExerciseRepository exerciseRepository;

	@Autowired
	ExerciseGroupRepository exerciseGroupRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AssignmentRepository assignmentRepository;

	@Before
	public void setup() {
		
		deleteAllData();
		
		createExercise1();
		// createExercise2();
		// createExercise3();
		// createExercise4();
		
		createUsers();
		
		createAssignments();
		
		loadExercise();
	}
	
	@Test
	public void count() {
		
		System.out.println(exerciseGroupRepository.count());
		System.out.println(exerciseRepository.count());
		System.out.println(userRepository.count());
		System.out.println(assignmentRepository.count());
		
		
	}
	
	public void deleteAllData() {
		
		exerciseRepository.deleteAll();
		exerciseGroupRepository.deleteAll();
		// userRepository.deleteAll();
		assignmentRepository.deleteAll();
		
		
	}
	
	public void createExercise1() {

		ExerciseGroup exerciseGroup = new ExerciseGroup();
		exerciseGroup.setName("ma 1.1");
		ExerciseGroup savedExerciseGroup = exerciseGroupRepository.save(exerciseGroup);
		
		List<Exercise> exerciseList = new ArrayList<Exercise>();
		
		exerciseList.add(new Exercise("ma", "mi", "transforme le mot", 1, savedExerciseGroup));
		exerciseList.add(new Exercise("mi", "mo", "transforme le mot", 2, savedExerciseGroup));
		exerciseList.add(new Exercise("mo", "me", "transforme le mot", 3, savedExerciseGroup));
		exerciseList.add(new Exercise("me", "mu", "transforme le mot", 4, savedExerciseGroup));
		exerciseList.add(new Exercise("mu", "lu", "transforme le mot", 5, savedExerciseGroup));
		exerciseList.add(new Exercise("lu", "li", "transforme le mot", 6, savedExerciseGroup));
		exerciseList.add(new Exercise("li", "la", "transforme le mot", 7, savedExerciseGroup));
		exerciseList.add(new Exercise("la", "lo", "transforme le mot", 8, savedExerciseGroup));
		exerciseList.add(new Exercise("lo", "le", "transforme le mot", 9, savedExerciseGroup));
		exerciseList.add(new Exercise("le", "me", "transforme le mot", 10, savedExerciseGroup));

		exerciseGroup.setExcercises(exerciseList);
		exerciseGroupRepository.save(savedExerciseGroup);
		
	}
	
	public void createExercise2() {

		ExerciseGroup exerciseGroup = new ExerciseGroup();
		exerciseGroup.setName("la 1.2");
		exerciseGroupRepository.save(exerciseGroup);

		List<Exercise> exerciseList = new ArrayList<Exercise>();
		
		exerciseList.add(new Exercise("la", "ma", "transforme le mot", 1, exerciseGroup));
		exerciseList.add(new Exercise("ma", "fa", "transforme le mot", 2, exerciseGroup));
		exerciseList.add(new Exercise("fa", "fi", "transforme le mot", 3, exerciseGroup));
		exerciseList.add(new Exercise("fi", "mi", "transforme le mot", 4, exerciseGroup));
		exerciseList.add(new Exercise("mi", "li", "transforme le mot", 5, exerciseGroup));
		exerciseList.add(new Exercise("li", "lo", "transforme le mot", 6, exerciseGroup));
		exerciseList.add(new Exercise("lo", "fo", "transforme le mot", 7, exerciseGroup));
		exerciseList.add(new Exercise("fo", "mo", "transforme le mot", 8, exerciseGroup));
		exerciseList.add(new Exercise("mo", "lo", "transforme le mot", 9, exerciseGroup));
		exerciseList.add(new Exercise("lo", "li", "transforme le mot", 10, exerciseGroup));

		exerciseGroup.setExcercises(exerciseList);
		exerciseGroupRepository.save(exerciseGroup);
		
	}

	public void createExercise3() {

		ExerciseGroup exerciseGroup = new ExerciseGroup();
		exerciseGroup.setName("lafo 1.3");
		exerciseGroupRepository.save(exerciseGroup);

		List<Exercise> exerciseList = new ArrayList<Exercise>();
		
		exerciseList.add(new Exercise("lafo", "lafa", "transforme le mot", 1, exerciseGroup));
		exerciseList.add(new Exercise("lafa", "lafi", "transforme le mot", 2, exerciseGroup));
		exerciseList.add(new Exercise("lafi", "lami", "transforme le mot", 3, exerciseGroup));
		exerciseList.add(new Exercise("lami", "lali", "transforme le mot", 4, exerciseGroup));
		exerciseList.add(new Exercise("lali", "lalo", "transforme le mot", 5, exerciseGroup));
		exerciseList.add(new Exercise("lalo", "lamo", "transforme le mot", 6, exerciseGroup));
		exerciseList.add(new Exercise("lamo", "lamu", "transforme le mot", 7, exerciseGroup));
		exerciseList.add(new Exercise("lamu", "amu", "transforme le mot", 8, exerciseGroup));
		exerciseList.add(new Exercise("amu", "afu", "transforme le mot", 9, exerciseGroup));
		exerciseList.add(new Exercise("afu", "afo", "transforme le mot", 10, exerciseGroup));

		exerciseGroup.setExcercises(exerciseList);
		exerciseGroupRepository.save(exerciseGroup);
		
	}

	public void createExercise4() {

		ExerciseGroup exerciseGroup = new ExerciseGroup();
		exerciseGroup.setName("malo 1.4");
		exerciseGroupRepository.save(exerciseGroup);

		List<Exercise> exerciseList = new ArrayList<Exercise>();
		
		exerciseList.add(new Exercise("malo", "malu", "transforme le mot", 1, exerciseGroup));
		exerciseList.add(new Exercise("malu", "mala", "transforme le mot", 2, exerciseGroup));
		exerciseList.add(new Exercise("mala", "mala", "transforme le mot", 3, exerciseGroup));
		exerciseList.add(new Exercise("mala", "mafa", "transforme le mot", 4, exerciseGroup));
		exerciseList.add(new Exercise("mafa", "mafi", "transforme le mot", 5, exerciseGroup));
		exerciseList.add(new Exercise("mafi", "mafo", "transforme le mot", 6, exerciseGroup));
		exerciseList.add(new Exercise("mafo", "mafu", "transforme le mot", 7, exerciseGroup));
		exerciseList.add(new Exercise("mafu", "afu", "transforme le mot", 8, exerciseGroup));
		exerciseList.add(new Exercise("afu", "afi", "transforme le mot", 9, exerciseGroup));
		exerciseList.add(new Exercise("afi", "mafi", "transforme le mot", 10, exerciseGroup));

		exerciseGroup.setExcercises(exerciseList);
		exerciseGroupRepository.save(exerciseGroup);
		
	}

	public void createUsers() {
		
		Educator educator = new Educator();
		educator.setDisplayName("educator1");
		educator.setPassword("Password#1");
		educator.setEmail("abc@abc123.com");
		// educator.setRoles(roles);
		
		userRepository.save(educator);
		
		Student student = new Student();
		student.setDisplayName("student1");
		student.setPassword("Password#1");
		student.setEmail("abc2@abc123.com");
		student.setEducator(educator);
		
		userRepository.save(student);
		
	}
	
	public void createAssignments() {
	
		Student student = (Student) userRepository.findByDisplayName("student1");
		ExerciseGroup exerciseGroup1 = exerciseGroupRepository.findByName("la 1.2");
		ExerciseGroup exerciseGroup2 = exerciseGroupRepository.findByName("lafo 1.3");
		ExerciseGroup exerciseGroup3 = exerciseGroupRepository.findByName("malo 1.4");
		
		Assignment assignment1 = new Assignment();
		assignment1.setStudent(student);
		assignment1.setExerciseGroup(exerciseGroup1);
		
		assignmentRepository.save(assignment1);

		Assignment assignment2 = new Assignment();
		assignment2.setStudent(student);
		assignment2.setExerciseGroup(exerciseGroup2);
		
		assignmentRepository.save(assignment2);
		
		List<Assignment> assignments = assignmentRepository.findListByStudent(student);
		
		assertTrue(assignments.size() == 2);

		Assignment assignment = assignmentRepository.findByStudentAndExerciseGroup(student, exerciseGroup3);
		assertNull(assignment);

		assignment = assignmentRepository.findByStudentAndExerciseGroup(student, exerciseGroup1);
		assertNotNull(assignment);
		
		ExerciseGroup exerciseGroup = assignment.getExerciseGroup();
		assertNotNull(exerciseGroup);
		
		Exercise exercise = exerciseGroup.getExcercises().get(0);
		
	}
	
	public void loadExercise() {
		
		Student student = (Student) userRepository.findByDisplayName("student1");
		ExerciseGroup exerciseGroup1 = exerciseGroupRepository.findByName("la 1.2");

		// List<Exercise> exercises = exerciseRepository.findExercisesByStudentAndExerciseGroup(student, exerciseGroup1);
		Assignment assignment = assignmentRepository.findByStudentAndExerciseGroup(student, exerciseGroup1);
		ExerciseGroup exerciseGroup = assignment.getExerciseGroup();
		List<Exercise> exercises = exerciseGroup.getExcercises();
		
		assertTrue(exercises.size() == 10);
		
	}
	
}
