package ca.gb.sf.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ca.gb.sf.H2IntegrationTest;
import ca.gb.sf.models.ExerciseEntity;
import ca.gb.sf.models.ExerciseGroupEntity;

public class ExerciseRepositoryTest extends H2IntegrationTest {
	
	@Autowired
	ExerciseRepository exerciseRepository;

	@Autowired
	ExerciseGroupRepository exerciseGroupRepository;

	@Test
	public void crudTest() {
		
		String name = "test exercise name";
		
		String updatedName = "test exercise name updated";
		
		ExerciseGroupEntity exerciseGroupEntity = new ExerciseGroupEntity();
		exerciseGroupEntity.setName("test exercise group name");
		ExerciseGroupEntity savedExerciseGroupEntity = exerciseGroupRepository.save(exerciseGroupEntity);
		
		// Setup CREATE
		long countBefore = exerciseRepository.count();
		
		ExerciseEntity entity = new ExerciseEntity(name, 1);
		entity.setExerciseGroup(exerciseGroupEntity);
		
		ExerciseEntity savedEntity = exerciseRepository.save(entity);
		
		long countAfter = exerciseRepository.count();
		
		long difference = countAfter - countBefore;
		
		// Verify CREATE
		assertTrue(difference == 1);
		
		// Setup READ
		Optional<ExerciseEntity> optionalReadEntity = exerciseRepository.findById(savedEntity.getId());
		
		ExerciseEntity readEntity = optionalReadEntity.get();
		
		// Verify READ
		assertEquals(name, readEntity.getTargetWord()); 
		
		// Setup UPDATE
		readEntity.setTargetWord(updatedName);
		
		exerciseRepository.save(readEntity);
		
		Optional<ExerciseEntity> optionalUpdatedEntity = exerciseRepository.findById(savedEntity.getId());

		ExerciseEntity updatedEntity = optionalUpdatedEntity.get(); 
		
		// Verify UPDATE
		assertEquals(updatedName, updatedEntity.getTargetWord()); 
		
		// Setup DELETE
		exerciseRepository.delete(updatedEntity);
		
		long countAfterDelete = exerciseRepository.count();
		
		// Verify DELETE
		assertTrue(countAfterDelete == countBefore);
		
	}
	
	@Test
	public void findAllTest() {

		long countBefore = exerciseRepository.count();

		createExercises("findAllTestGroup", "sample", 10);
		
		long countAfter = exerciseRepository.count();
		
		long difference = countAfter - countBefore;

		assertTrue(difference == 10);
		
	}
	
	@Test
	public void searchBySearchStringTest() {
		
		long startCounter = exerciseRepository.count();
		
		createExercises("searchStringTestFindMeGroup", "find me", 10);
		
		createExercises("searchStringTestHideMeGroup", "hide me", 10);

		Pageable pageable = PageRequest.of(0, 100, Sort.by("targetWord"));
		
		Page<ExerciseEntity> exerciseEntityPage = exerciseRepository.searchBySearchString(pageable, "find");
		
		List<ExerciseEntity> exerciseEntityList = exerciseEntityPage.getContent();
		
		// System.out.println("Size = " + exerciseEntityList.size());

		// System.out.println("Start = " + startCounter);

		assert(exerciseEntityList.size() == 10);

		Page<ExerciseEntity> exerciseEntityPage2 = exerciseRepository.searchBySearchString(pageable, "me");
		
		List<ExerciseEntity> exerciseEntityList2 = exerciseEntityPage2.getContent();
		
		// System.out.println("Size = " + exerciseEntityList2.size());
		
		// System.out.println(exerciseEntityList2.size());
		
		assert(exerciseEntityList2.size() == (20 + startCounter));

	}

	@Test
	public void findByExerciseGroupTest() {
		
		String groupName = "group name test";
		
		createExercisesAndExerciseGroup(groupName, 11);
		
		ExerciseGroupEntity exerciseGroupEntity = exerciseGroupRepository.findByName(groupName);
		
		List<ExerciseEntity> exercises = exerciseRepository.findAllByExerciseGroup(exerciseGroupEntity);
		
		assertTrue(exercises.size() == 11);
		
	}
	
	public void createExercises(String groupName, String key, int count) {
	
		ExerciseGroupEntity exerciseGroupEntity = new ExerciseGroupEntity();
		exerciseGroupEntity.setName(groupName);
		ExerciseGroupEntity exerciseGroup = exerciseGroupRepository.save(exerciseGroupEntity);
		
		for (int i = 0; i < count; i++) {
			
			String exerciseName = key + " name " + i;
		
			ExerciseEntity exercise = new ExerciseEntity(exerciseName, i);
			exercise.setExerciseGroup(exerciseGroupEntity);
			
			exerciseRepository.save(exercise);
			
		}
		

	}
	
	public void createExercisesAndExerciseGroup(String groupName, int count) {
		
		ExerciseGroupEntity exerciseGroupEntity = new ExerciseGroupEntity();
		exerciseGroupEntity.setName(groupName);
		ExerciseGroupEntity exerciseGroup = exerciseGroupRepository.save(exerciseGroupEntity);

		for (int i = 0; i < count; i++) {
			
			String exerciseName = "exercise name " + i;
			
			ExerciseEntity exercise = new ExerciseEntity("initialWord", exerciseName, "writtenInstructions", "readInstructions", i, exerciseGroup);
			
			exerciseRepository.save(exercise);
			
		}
		
	}
		

}
