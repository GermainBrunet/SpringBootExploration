package ca.gb.sf.repositories.repo;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.SpringContextIntegrationTest;
import ca.gb.sf.models.Educator;
import ca.gb.sf.models.Manufacturer;
import ca.gb.sf.models.Student;
import ca.gb.sf.models.User;
import ca.gb.sf.repositories.ManufacturerRepository;
import ca.gb.sf.repositories.UserRepository;

public class UserRepoTest extends SpringContextIntegrationTest {
	
	@Autowired
	UserRepository userRepo;

	@Test
	public void addUserTest() {
		
		long countBefore = userRepo.count();
		
		User user = new User("displayName1", "email1", "password");
		
		userRepo.save(user);
		
		long countAfter = userRepo.count();
		
		long difference = countAfter - countBefore;
		
		assertTrue(difference == 1);
		
	}
	
	@Test
	public void addEducatorTest() {
		
		long countBefore = userRepo.count();
		
		Educator educator = new Educator("displayName2", "email2", "password");
		
		userRepo.save(educator);
		
		long countAfter = userRepo.count();
		
		long difference = countAfter - countBefore;
		
		assertTrue(difference == 1);
		
	}

	@Test
	public void addStudentTest() {
		
		long countBefore = userRepo.count();
		
		Educator educator = new Educator("displayName3", "email3", "password");
		
		userRepo.save(educator);

		Student student = new Student("displayName4", "email4", "password", educator);
		
		userRepo.save(student);
		
		long countAfter = userRepo.count();
		
		long difference = countAfter - countBefore;
		
		assertTrue(difference == 2);
		
	}
	
	@Test
	public void findStudentByEducatorTest() {
		
		long countBefore = userRepo.count();
		
		Educator educator = new Educator("displayName4", "email4", "password");
		
		userRepo.save(educator);

		Student student1 = new Student("displayName41", "email41", "password", educator);
		
		userRepo.save(student1);

		Student student2 = new Student("displayName42", "email42", "password", educator);
		
		userRepo.save(student2);

		Student student3 = new Student("displayName43", "email43", "password", educator);
		
		userRepo.save(student3);

		List<Student> students = userRepo.findByEducator(educator);
		
		assertTrue(students.size() == 3);
	}



}
