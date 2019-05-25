package ca.gb.sf.repositories;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ca.gb.sf.H2IntegrationTest;
import ca.gb.sf.models.EducatorEntity;
import ca.gb.sf.models.StudentEntity;
import ca.gb.sf.models.UserEntity;
import ca.gb.sf.repositories.UserRepository;

public class UserRepoTest extends H2IntegrationTest {
	
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
		
		EducatorEntity educator = new EducatorEntity("displayName4", "email5", "password");
		
		userRepo.save(educator);

		StudentEntity student1 = new StudentEntity("displayName41", "email51", "password", educator);
		
		userRepo.save(student1);

		StudentEntity student2 = new StudentEntity("displayName42", "email52", "password", educator);
		
		userRepo.save(student2);

		StudentEntity student3 = new StudentEntity("displayName43", "email53", "password", educator);
		
		userRepo.save(student3);

		Pageable pageable = PageRequest.of(0, 100, Sort.by("displayName"));
		
		Page<UserEntity> userPage = userRepo.findByEducator(pageable, educator);
		
		List<UserEntity> userList = userPage.getContent();
		
		long countAfter = userRepo.count();
		
		long countDifference = countAfter - countBefore;
		
		System.out.println(countDifference);
		
		assertTrue(countDifference == 4);
		assertTrue(userList.size() == 3);
	}



}
