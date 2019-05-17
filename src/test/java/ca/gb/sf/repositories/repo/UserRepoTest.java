package ca.gb.sf.repositories.repo;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import ca.gb.sf.SpringContextIntegrationTest;
import ca.gb.sf.models.EducatorEntity;
import ca.gb.sf.models.StudentEntity;
import ca.gb.sf.models.UserEntity;
import ca.gb.sf.repositories.UserRepository;

public class UserRepoTest extends SpringContextIntegrationTest {
	
	@Autowired
	UserRepository userRepo;

	@Test
	public void addUserTest() {
		
		long countBefore = userRepo.count();
		
		UserEntity user = new UserEntity("displayName1", "email1", "password");
		
		userRepo.save(user);
		
		long countAfter = userRepo.count();
		
		long difference = countAfter - countBefore;
		
		assertTrue(difference == 1);
		
	}
	
	@Test
	public void addEducatorTest() {
		
		long countBefore = userRepo.count();
		
		EducatorEntity educator = new EducatorEntity("displayName2", "email2", "password");
		
		userRepo.save(educator);
		
		long countAfter = userRepo.count();
		
		long difference = countAfter - countBefore;
		
		assertTrue(difference == 1);
		
	}

	@Test
	public void addStudentTest() {
		
		long countBefore = userRepo.count();
		
		EducatorEntity educator = new EducatorEntity("displayName3", "email3", "password");
		
		userRepo.save(educator);

		StudentEntity student = new StudentEntity("displayName4", "email4", "password", educator);
		
		userRepo.save(student);
		
		long countAfter = userRepo.count();
		
		long difference = countAfter - countBefore;
		
		assertTrue(difference == 2);
		
	}
	
	@Test
	public void findStudentByEducatorTest() {
		
		long countBefore = userRepo.count();
		
		EducatorEntity educator = new EducatorEntity("displayName4", "email4", "password");
		
		userRepo.save(educator);

		StudentEntity student1 = new StudentEntity("displayName41", "email41", "password", educator);
		
		userRepo.save(student1);

		StudentEntity student2 = new StudentEntity("displayName42", "email42", "password", educator);
		
		userRepo.save(student2);

		StudentEntity student3 = new StudentEntity("displayName43", "email43", "password", educator);
		
		userRepo.save(student3);

		// Pageable pageable = new Pageable();
		
		// List<Student> students = userRepo.findByEducator(educator);
		
		// assertTrue(students.size() == 3);
	}



}
