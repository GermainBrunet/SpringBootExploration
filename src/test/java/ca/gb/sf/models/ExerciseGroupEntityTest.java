package ca.gb.sf.models;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import ca.gb.sf.H2IntegrationTest;
import ca.gb.sf.repositories.ExerciseGroupRepository;

public class ExerciseGroupEntityTest extends H2IntegrationTest {

	@Autowired
	ExerciseGroupRepository exerciseGroupRepository;
	
	@Test(expected = DataIntegrityViolationException.class)
	public void createEmtpy() {
		
		ExerciseGroupEntity exerciseGroup = new ExerciseGroupEntity();
		
		exerciseGroupRepository.save(exerciseGroup);
		
	}
	
	@Test
	public void createNotEmtpy() {
		
		ExerciseGroupEntity exerciseGroup = createExerciseGroupEntity("createNotEmptyTestExerciseGroupEntityTest");
		
		assertNotNull(exerciseGroup.getId());
		
	}
	
}
