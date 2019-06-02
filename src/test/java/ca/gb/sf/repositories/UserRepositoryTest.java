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

public class UserRepositoryTest extends H2IntegrationTest {
	
	@Autowired
	UserRepository userRepository;

	@Test
	public void addUserTest() {
		
		long countBefore = userRepository.count();
		
		UserEntity user = new UserEntity("displayName1", "email1", "password");
		
		userRepository.save(user);
		
		long countAfter = userRepository.count();
		
		long difference = countAfter - countBefore;
		
		assertTrue(difference == 1);
		
	}
	
	@Test
	public void addEducatorTest() {
		
		long countBefore = userRepository.count();
		
		EducatorEntity educator = new EducatorEntity("displayName2", "email2", "password");
		
		userRepository.save(educator);
		
		long countAfter = userRepository.count();
		
		long difference = countAfter - countBefore;
		
		assertTrue(difference == 1);
		
	}

	@Test
	public void addStudentTest() {
		
		long countBefore = userRepository.count();
		
		EducatorEntity educator = new EducatorEntity("displayName3addStudentTest", "email3addStudentTest", "password");
		educator = userRepository.saveAndFlush(educator);

		StudentEntity studentEntity = new StudentEntity("displayName4addStudentTest", "email4addStudentTest", "password", educator);
		educator.addStudent(studentEntity);
		educator = userRepository.saveAndFlush(educator);
		
		long countAfter = userRepository.count();
		
		long difference = countAfter - countBefore;
		
		assertTrue(difference == 2);
		
	}
	
	@Test
	public void findStudentsByEducatorTest() {
		
		long countBefore = userRepository.count();
		
		EducatorEntity educator = new EducatorEntity("displayName4findStudentsByEducatorTest", "email5findStudentsByEducatorTest", "password");
		educator = userRepository.saveAndFlush(educator);

		StudentEntity student1 = new StudentEntity("displayName41findStudentsByEducatorTest", "email51findStudentsByEducatorTest", "password", educator);
		educator.getStudents().add(student1);
		educator = userRepository.saveAndFlush(educator);

		StudentEntity student2 = new StudentEntity("displayName42findStudentsByEducatorTest", "email52findStudentsByEducatorTest", "password", educator);
		educator.getStudents().add(student2);
		educator = userRepository.saveAndFlush(educator);
		
		StudentEntity student3 = new StudentEntity("displayName43findStudentsByEducatorTest", "email53findStudentsByEducatorTest", "password", educator);
		educator.getStudents().add(student3);
		educator = userRepository.save(educator);
		
		Pageable pageable = PageRequest.of(0, 100, Sort.by("displayName"));
		
		Page<UserEntity> userPage = userRepository.findByEducator(pageable, educator);
		
		List<UserEntity> userList = userPage.getContent();
		
		long countAfter = userRepository.count();
		
		long countDifference = countAfter - countBefore;
		
		assertTrue(countDifference == 4);
		
		assertTrue(userList.size() == 3);
		
	}


	@Test
	public void deleteEducatorCascadesToStudentsTest() {
		
		long countBefore = userRepository.count();
		
		EducatorEntity educator = new EducatorEntity("deleteEducatorCascadesToStudentsTestEducator", "email6", "password");
		
		educator = userRepository.saveAndFlush(educator);
		StudentEntity student1 = new StudentEntity("deleteEducatorCascadesToStudentsTestEducatorStudent1", "email61", "password", educator);
		educator.addStudent(student1);
		educator = userRepository.saveAndFlush(educator);

		StudentEntity student2 = new StudentEntity("deleteEducatorCascadesToStudentsTestEducatorStudent2", "email62", "password", educator);
		educator.addStudent(student2);
		educator = userRepository.saveAndFlush(educator);

		StudentEntity student3 = new StudentEntity("deleteEducatorCascadesToStudentsTestEducatorStudent3", "email63", "password", educator);
		educator.addStudent(student3);
		educator = userRepository.saveAndFlush(educator);

		Pageable pageable = PageRequest.of(0, 100, Sort.by("displayName"));
		
		Page<UserEntity> userPage = userRepository.findByEducator(pageable, educator);
		
		List<UserEntity> userList = userPage.getContent();
		
		long countAfter = userRepository.count();
		
		long countDifference = countAfter - countBefore;
		
		System.out.println(countBefore); // 8
		System.out.println(countAfter);  // 12
		System.out.println(countDifference); // 4
		
		assertTrue(countDifference == 4); // ok
		assertTrue(userList.size() == 3); // ok

		userRepository.delete(educator); 
		
		long countAfterDelete = userRepository.count(); // 11 - did not cascade
		
		System.out.println(countAfterDelete);
		
		long countDifferenceAfterDelete = countAfterDelete - countBefore;
		
		System.out.println(countDifferenceAfterDelete);
		
		assertTrue(countDifferenceAfterDelete == 0);
		
	}
	
	


}
