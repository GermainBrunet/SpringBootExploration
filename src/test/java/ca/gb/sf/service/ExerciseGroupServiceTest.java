package ca.gb.sf.service;

import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.services.ExerciseGroupService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ExerciseGroupServiceTest extends CommonServiceTest {

	@Autowired
	ExerciseGroupService exerciseGroupService;
	
	@Test
	public void createUsingStringTest() {
		
		String name = "abc123create";
		
		exerciseGroupService.createUsingName(name);
		
		ExerciseGroupEntity exerciseGroupEntity = exerciseGroupService.findByName(name);
		
		assertTrue(exerciseGroupEntity.getId() != 0);
		assertEquals(exerciseGroupEntity.getName(), name);
		assertNotNull(exerciseGroupEntity.getCreateTimestamp());
		assertNotNull(exerciseGroupEntity.getCreateUser());
		assertNull(exerciseGroupEntity.getUpdateTimestamp());
		assertNull(exerciseGroupEntity.getUpdateUser());
		
		// Must not produce null pointer exception or lazy loading exceptions.
		System.out.println(exerciseGroupEntity);
		
		String newName = "abc123createNew";
		
		exerciseGroupEntity.setName(newName);
		
		ExerciseGroupEntity savedExerciseGroupEntity = exerciseGroupService.save(exerciseGroupEntity);
		
		assertTrue(savedExerciseGroupEntity.getId() != 0);
		assertEquals(savedExerciseGroupEntity.getName(), newName);
		assertNotNull(savedExerciseGroupEntity.getCreateTimestamp());
		assertNotNull(savedExerciseGroupEntity.getCreateUser());
		assertNotNull(savedExerciseGroupEntity.getUpdateTimestamp());
		assertNotNull(savedExerciseGroupEntity.getUpdateUser());
		
		
	}

	@Test
	public void createUsingObjectTest() {
		
		String name = "abc234createUsingObject";
		String description = "description";
		String author = "author";
		String source = "source";
		
		ExerciseGroupEntity testExerciseGroupEntity = new ExerciseGroupEntity();
		testExerciseGroupEntity.setName(name);
		testExerciseGroupEntity.setDescription(description);
		testExerciseGroupEntity.setAuthor(author);
		testExerciseGroupEntity.setSource(source);
		
		exerciseGroupService.save(testExerciseGroupEntity);
		
		ExerciseGroupEntity exerciseGroupEntity = exerciseGroupService.findByName(name);
		
		assertTrue(exerciseGroupEntity.getId() != 0);
		assertEquals(exerciseGroupEntity.getName(), name);
		assertEquals(exerciseGroupEntity.getDescription(), description);
		assertEquals(exerciseGroupEntity.getAuthor(), author);
		assertEquals(exerciseGroupEntity.getSource(), source);
		assertNotNull(exerciseGroupEntity.getCreateTimestamp());
		assertNotNull(exerciseGroupEntity.getCreateUser());
		assertNull(exerciseGroupEntity.getUpdateTimestamp());
		assertNull(exerciseGroupEntity.getUpdateUser());
		

		// Must not produce null pointer exception or lazy loading exceptions.
		System.out.println(exerciseGroupEntity);
		
	}
	
	@Test
	public void countTest() {
		
		String name = "abc123count";
		
		long beforeCount = exerciseGroupService.count();
		
		exerciseGroupService.createUsingName(name);

		long afterCount = exerciseGroupService.count();
		
		long difference = afterCount - beforeCount;
		
		assertTrue(difference == 1);
		
	}
	
	@Test
	public void deleteTest() {
		
		String targetNameForDelete = "nameDeleteTest2";
		
		exerciseGroupService.createUsingName("nameDeleteTest1");
		ExerciseGroupEntity exerciseGroupEntity2 = exerciseGroupService.createUsingName(targetNameForDelete);
		exerciseGroupService.createUsingName("nameDeleteTest3");

		ExerciseGroupEntity exerciseGroupEntityFindBefore = exerciseGroupService.findByName(targetNameForDelete);
		
		assertNotNull(exerciseGroupEntityFindBefore);
		
		exerciseGroupService.delete(exerciseGroupEntity2);
		
		ExerciseGroupEntity exerciseGroupEntityFindAfter = exerciseGroupService.findByName(targetNameForDelete);
		
		assertNull(exerciseGroupEntityFindAfter);
		
	}
	
	@Test
	public void deleteAllTest() {
		
		exerciseGroupService.createUsingName("nameDeleteAllTest1");
		exerciseGroupService.createUsingName("nameDeleteAllTest2");
		exerciseGroupService.createUsingName("nameDeleteAllTest3");

		exerciseGroupService.deleteAll();
		
		long afterCount = exerciseGroupService.count();
		
		assertTrue(afterCount == 0);
		
	}
	
	

}
