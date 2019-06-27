package ca.gb.sf.models;

import org.springframework.dao.DataIntegrityViolationException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.H2IntegrationTest;
import ca.gb.sf.repositories.ExerciseRepository;

public class ExerciseEntityTest extends H2IntegrationTest {

	@Autowired
	ExerciseRepository exerciseRepository;
	
	@Test(expected = DataIntegrityViolationException.class)
	public void createEmtpy() {
		
		ExerciseEntity exercise = new ExerciseEntity();
		
		exerciseRepository.save(exercise);
		
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void createWithMissingOrder() {

		ExerciseEntity exercise = new ExerciseEntity();

		ExerciseGroupEntity exerciseGroup = createExerciseGroupEntity("createWithMissingOrder");
		
		exercise.setExerciseGroup(exerciseGroup);
		
		exerciseRepository.save(exercise);
		
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void createWithMissingExerciseGroup() {

		ExerciseEntity exercise = new ExerciseEntity();

		exercise.setExerciseOrder(1);
		
		exerciseRepository.save(exercise);
		
	}

}
