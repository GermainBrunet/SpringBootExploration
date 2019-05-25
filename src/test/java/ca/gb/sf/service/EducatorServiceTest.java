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

import ca.gb.sf.models.UserEntity;
import ca.gb.sf.services.EducatorService;

public class EducatorServiceTest extends CommonServiceTest {

	@Autowired
	public EducatorService educatorService;
	
	@Test
	public void currentEducatorTest() {

		assertNotNull(educatorService.getCurrentEducator());
		
		assertEquals(educatorService.getCurrentEducator().getDisplayName(), "educator1");
		
	}
	
	@Test
	public void getStudentsTest() {
		
		Pageable pageable = PageRequest.of(0, 100, Sort.by("displayName"));
		
		Page<UserEntity> page = educatorService.studentsByEducator(pageable);
		
		List<UserEntity> students = page.getContent();
		
		assertTrue(students.size() == 3);
		
		
	}
	
}
