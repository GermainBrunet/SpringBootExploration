package ca.gb.sf.models;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import ca.gb.sf.H2IntegrationTest;
import ca.gb.sf.repositories.AssignmentRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

public class AssignmentEntityTest extends H2IntegrationTest {

	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Test(expected = DataIntegrityViolationException.class)
	public void emptyObjectShouldNotSaveTest() {
		
		AssignmentEntity assignmentEntity = new AssignmentEntity();

		assignmentRepository.save(assignmentEntity);
		
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void assignmentMissingUserNotPermittedTest() {
		
		AssignmentEntity assignmentEntity = new AssignmentEntity();

		ExerciseGroupEntity exerciseGroup = createExerciseGroupEntity("groupName");
		
		assignmentEntity.setExerciseGroup(exerciseGroup);
		
		AssignmentStatusEntity assignmentStatus = findAssignmentStatusEntity(AssignmentStatusEntity.ASSIGNED);
		
		assignmentEntity.setAssignmentStatus(assignmentStatus);
		
		assignmentRepository.save(assignmentEntity);
		
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void assignmentMissingExerciseGroupNotPermittedTest() {
		
		AssignmentEntity assignmentEntity = new AssignmentEntity();

		UserEntity user = createUserEntity("user");
		
		assignmentEntity.setUser(user);

		AssignmentStatusEntity assignmentStatus = findAssignmentStatusEntity(AssignmentStatusEntity.ASSIGNED);
		
		assignmentEntity.setAssignmentStatus(assignmentStatus);

		assignmentRepository.save(assignmentEntity);
		
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void assignmentMissingAssignmentStatusNotPermittedTest() {
		
		AssignmentEntity assignmentEntity = new AssignmentEntity();

		ExerciseGroupEntity exerciseGroup = createExerciseGroupEntity("groupName");
		
		assignmentEntity.setExerciseGroup(exerciseGroup);

		UserEntity user = createUserEntity("user");
		
		assignmentEntity.setUser(user);
		
		AssignmentEntity savedAssignmentEntity = assignmentRepository.save(assignmentEntity);
		
		assertNotNull(savedAssignmentEntity.getId());
		
	}

	public void assignmentWithUserAndExerciseGroupAndAssigmentStatusPersistedTest() {
		
		AssignmentEntity assignmentEntity = new AssignmentEntity();

		ExerciseGroupEntity exerciseGroup = createExerciseGroupEntity("groupName");
		
		assignmentEntity.setExerciseGroup(exerciseGroup);

		UserEntity user = createUserEntity("user");
		
		assignmentEntity.setUser(user);

		AssignmentStatusEntity assignmentStatus = findAssignmentStatusEntity(AssignmentStatusEntity.ASSIGNED);
		
		assignmentEntity.setAssignmentStatus(assignmentStatus);

		AssignmentEntity savedAssignmentEntity = assignmentRepository.save(assignmentEntity);
		
		assertNotNull(savedAssignmentEntity.getId());
		
	}
	
	@Test
	public void toStringTest() {
		
		AssignmentEntity assignmentEntity = new AssignmentEntity();

		ExerciseGroupEntity exerciseGroup = createExerciseGroupEntity("groupNameToStringTest");
		
		assignmentEntity.setExerciseGroup(exerciseGroup);

		UserEntity user = createUserEntity("userToStringTest");
		
		assignmentEntity.setUser(user);

		AssignmentStatusEntity assignmentStatus = findAssignmentStatusEntity(AssignmentStatusEntity.ASSIGNED);
		
		assignmentEntity.setAssignmentStatus(assignmentStatus);

		AssignmentEntity savedAssignmentEntity = assignmentRepository.save(assignmentEntity);
		
		assertNotNull(savedAssignmentEntity.getId());
		
		String toStringOutput = savedAssignmentEntity.toString();
		
		assertTrue(toStringOutput.contains("id="));
		assertTrue(toStringOutput.contains("exerciseGroup="));
		assertTrue(toStringOutput.contains("user="));
		assertTrue(toStringOutput.contains("assignmentStatus="));
		
		assignmentRepository.delete(savedAssignmentEntity);
		
	}

}
