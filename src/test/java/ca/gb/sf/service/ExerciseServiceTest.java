package ca.gb.sf.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.models.ExerciseEntity;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.services.ExerciseGroupService;
import ca.gb.sf.services.ExerciseService;
import ca.gb.sf.util.SetupExercises;

public class ExerciseServiceTest extends CommonServiceTest {

	@Autowired
	ExerciseGroupService exerciseGroupService;

	@Autowired
	ExerciseService exerciseService;
	
	@Test
	public void createUsingStringTest() {
		
		String exerciseServiceName = "exerciseServiceName1";
		
		ExerciseGroupEntity exerciseGroupEntity = exerciseGroupService.createUsingName(exerciseServiceName);
		
		String initialWord = "initialWord";
		String targetWord = "targetWord";
		String writtenInstructions = "writtenInstructions";
		String readInstructions = "readInstructions";
		
		ExerciseEntity exerciseEntity = exerciseService.create(initialWord, targetWord, writtenInstructions, readInstructions, 1, exerciseGroupEntity);
		
		assertTrue(exerciseEntity.getId() != 0);
		assertNotNull(exerciseEntity.getCreateTimestamp());
		assertNotNull(exerciseEntity.getCreateUser());
		assertNull(exerciseEntity.getUpdateTimestamp());
		assertNull(exerciseEntity.getUpdateUser());
		assertEquals(exerciseEntity.getInitialWord(), initialWord);
		assertEquals(exerciseEntity.getTargetWord(), targetWord);
		assertEquals(exerciseEntity.getWrittenInstructions(), writtenInstructions);
		assertEquals(exerciseEntity.getReadInstructions(), readInstructions);
		
		// Must not produce null pointer exception or lazy loading exceptions.
		System.out.println(exerciseGroupEntity);
		
		String newInitialWord = "newInitialWord";
		
		exerciseEntity.setInitialWord(newInitialWord);
		
		ExerciseEntity savedExerciseEntity = exerciseService.save(exerciseEntity);
		
		assertTrue(savedExerciseEntity.getId() != 0);
		assertNotNull(savedExerciseEntity.getCreateTimestamp());
		assertNotNull(savedExerciseEntity.getCreateUser());
		assertNotNull(savedExerciseEntity.getUpdateTimestamp());
		assertNotNull(savedExerciseEntity.getUpdateUser());
		assertEquals(savedExerciseEntity.getInitialWord(), newInitialWord);
		assertEquals(savedExerciseEntity.getTargetWord(), targetWord);
		assertEquals(savedExerciseEntity.getWrittenInstructions(), writtenInstructions);
		assertEquals(savedExerciseEntity.getReadInstructions(), readInstructions);
		
	}
	
	@Test
	public void countTest() {
		
		long beforeCount = exerciseService.count();

		System.out.println(beforeCount);
		
		for (int i = 0; i < 5; i++) {
			
			String key = "countExerciseCountTestest" + i;
			
			createExerciseEntity("groupKeyCountTest2", key);
		}

		long afterCount = exerciseService.count();
		
		long difference = afterCount - beforeCount;
		
		assertTrue(difference == 5);
		
	}
	
	@Test
	public void findAllTest() {
		
		String key = "findAllTest";
		
		ExerciseEntity exercise = createExerciseEntity("groupKeyFindAllTest", key);
		
		List<ExerciseEntity> exercises = exerciseService.findAll();
		
		assertTrue(exercises.size() != 0);
		
		assertTrue(exercises.contains(exercise));
		
	}
	
	@Test
	public void findByExerciseGroupTest() {
		
		String groupKey = "findByExerciseGroupTestKey1";
		String exerciseKey = "findByExerciseKey";
		
		for (int i = 0; i < 10; i++) {

			String key = exerciseKey + i;
			
			createExerciseEntity(groupKey, key);
			
		}
		
		ExerciseGroupEntity exerciseGroupEntity = exerciseGroupService.findByName(groupKey);
		
		List<ExerciseEntity> exercises = exerciseService.findByExerciseGroup(exerciseGroupEntity);
		
		assertTrue(exercises.size() == 10);
		
	}
	
	@Test
	public void findFirstAndNext() {
		
		String groupKey = "findFirstExerciseGroupKey";
		String exerciseKey = "findFirstExerciseKey";
		
		for (int i = 0; i < 10; i++) {

			String key = exerciseKey + i;
			
			createExerciseEntity(groupKey, key);
			
		}
		
		long count = exerciseService.findAll().size();
		System.out.println(count);
		
		ExerciseGroupEntity exerciseGroupEntity = exerciseGroupService.findByName(groupKey);

		ExerciseEntity exerciseEntity = exerciseService.findFirst(exerciseGroupEntity);

		assertNotNull(exerciseEntity);
		
		// TODO - rethink this.
		// ExerciseEntity exerciseEntity0 = exerciseService.findNext(exerciseGroupEntity, 0);
		// assertNotNull(exerciseEntity0);
		
		ExerciseEntity exerciseEntity1 = exerciseService.findNext(exerciseGroupEntity, 1);
		assertNotNull(exerciseEntity1);
		
		ExerciseEntity exerciseEntity2 = exerciseService.findNext(exerciseGroupEntity, 2);
		assertNotNull(exerciseEntity2);
		
		ExerciseEntity exerciseEntity3 = exerciseService.findNext(exerciseGroupEntity, 3);
		assertNotNull(exerciseEntity3);
		
		ExerciseEntity exerciseEntity4 = exerciseService.findNext(exerciseGroupEntity, 4);
		assertNotNull(exerciseEntity4);
		
		ExerciseEntity exerciseEntity5 = exerciseService.findNext(exerciseGroupEntity, 5);
		assertNotNull(exerciseEntity5);
		
		ExerciseEntity exerciseEntity6 = exerciseService.findNext(exerciseGroupEntity, 6);
		assertNotNull(exerciseEntity6);
		
		ExerciseEntity exerciseEntity7 = exerciseService.findNext(exerciseGroupEntity, 7);
		assertNotNull(exerciseEntity7);
		
		ExerciseEntity exerciseEntity8 = exerciseService.findNext(exerciseGroupEntity, 8);
		assertNotNull(exerciseEntity8);
		
		ExerciseEntity exerciseEntity9 = exerciseService.findNext(exerciseGroupEntity, 9);
		assertNotNull(exerciseEntity9);
		
		// TODO - WHY?
		// ExerciseEntity exerciseEntity10 = exerciseService.findNext(exerciseGroupEntity, 10);
		// assertNotNull(exerciseEntity10);
		
		// ExerciseEntity exerciseEntity11 = exerciseService.findNext(exerciseGroupEntity, 11);
		// assertNull(exerciseEntity11);
		
		
		
	}
	
	
	
	
	private ExerciseEntity createExerciseEntity(String groupKey, String key) {
		
		ExerciseGroupEntity exerciseGroupEntity = exerciseGroupService.createUsingName(groupKey);
		
		String initialWord = key + "InitialWord";
		String targetWord = key + "TargetWord";
		String writtenInstructions = key + "WrittenInstructions";
		String readInstructions = key + "ReadInstructions";
		
		return exerciseService.create(initialWord, targetWord, writtenInstructions, readInstructions, 1, exerciseGroupEntity);
		
	}
	
}
