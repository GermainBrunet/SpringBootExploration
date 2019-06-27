package ca.gb.sf.models;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.H2IntegrationTest;
import ca.gb.sf.repositories.AssignmentStatusRepository;

import static org.junit.Assert.assertNotNull;

import javax.validation.ConstraintViolationException;

public class AssignmentStatusEntityTest extends H2IntegrationTest {

	@Autowired
	AssignmentStatusRepository assignmentStatusRepository;
	
	@Test(expected = ConstraintViolationException.class)
	public void emptyObjectShouldNotSaveTest() {
		
		AssignmentStatusEntity assignmentStatusEntity = new AssignmentStatusEntity();

		assignmentStatusRepository.save(assignmentStatusEntity);
		
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void emptyCodeShouldNotSaveTest() {
		
		AssignmentStatusEntity assignmentStatusEntity = new AssignmentStatusEntity();
		
		assignmentStatusEntity.setNameFrench("nameFrench");
		assignmentStatusEntity.setNameEnglish("nameEnglish");

		assignmentStatusRepository.save(assignmentStatusEntity);
		
	}

	@Test(expected = ConstraintViolationException.class)
	public void emptyFrenchNameShouldNotSaveTest() {
		
		AssignmentStatusEntity assignmentStatusEntity = new AssignmentStatusEntity();
		
		assignmentStatusEntity.setCode("code");
		assignmentStatusEntity.setNameEnglish("nameEnglish");

		assignmentStatusRepository.save(assignmentStatusEntity);
		
	}

	@Test(expected = ConstraintViolationException.class)
	public void emptyEnglishNameShouldNotSaveTest() {
		
		AssignmentStatusEntity assignmentStatusEntity = new AssignmentStatusEntity();
		
		assignmentStatusEntity.setCode("code");
		assignmentStatusEntity.setNameFrench("nameFrench");

		assignmentStatusRepository.save(assignmentStatusEntity);
		
	}

	@Test
	public void filledRequiredFieldsShouldSaveTest() {
		
		AssignmentStatusEntity assignmentStatusEntity = new AssignmentStatusEntity();
		
		assignmentStatusEntity.setCode("code");
		assignmentStatusEntity.setNameFrench("nameFrench");
		assignmentStatusEntity.setNameEnglish("nameEnglish");

		AssignmentStatusEntity savedAssignmentStatusEntity = assignmentStatusRepository.save(assignmentStatusEntity);
		
		assertNotNull(savedAssignmentStatusEntity.getId());
		
		assignmentStatusRepository.delete(savedAssignmentStatusEntity);
		
		
	}

}
