package ca.gb.sf.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ca.gb.sf.models.EducatorEntity;
import ca.gb.sf.models.UserEntity;
import ca.gb.sf.services.EducatorService;
import ca.gb.sf.services.UserService;

public class EducatorServiceTest extends CommonServiceTest {

	@Autowired
	public EducatorService educatorService;

	@Autowired
	public UserService userService;

	@Test
	public void currentEducatorTest() {

		assertNotNull(educatorService.getCurrentEducator());
		
		assertEquals(educatorService.getCurrentEducator().getDisplayName(), "educator1");
		
	}
	
	@Test
	public void getStudentsTest() {
		
		Pageable pageable = PageRequest.of(0, 100, Sort.by("displayName"));

		EducatorEntity educator = (EducatorEntity) userService.getCurrentUserEntity();
		
		// Page<UserEntity> page = educatorService.studentsByEducator(pageable);
		Page<UserEntity> page = userService.educatorStudentsPage(pageable, educator);
		
		List<UserEntity> students = page.getContent();
		
		System.out.println(students.size());
		
		assertTrue(students.size() == 3);
		
		
	}
	
}
