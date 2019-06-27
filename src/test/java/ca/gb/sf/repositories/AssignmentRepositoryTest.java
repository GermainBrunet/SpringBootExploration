package ca.gb.sf.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.H2IntegrationTest;
import ca.gb.sf.models.AssignmentEntity;
import ca.gb.sf.models.AssignmentStatusEntity;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.UserEntity;

public class AssignmentRepositoryTest extends H2IntegrationTest {

	@Autowired
	AssignmentStatusRepository assignmentStatusRepository;
	
	@Autowired
	AssignmentRepository assignmentRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ExerciseGroupRepository exerciseGroupRepository;

	@Test
	public void crudTest() {

		String userName = "user name";

		String exerciseGroupName = "exercise group name";

		UserEntity userEntity = createUserEntity(userName);

		ExerciseGroupEntity exerciseGroupEntity = createExerciseGroupEntity(exerciseGroupName);

		// Setup CREATE
		long countBefore = assignmentRepository.count();

		AssignmentStatusEntity status = assignmentStatusRepository.findByCode(AssignmentStatusEntity.ASSIGNED);
		
		AssignmentEntity entity = new AssignmentEntity(userEntity, exerciseGroupEntity, status);

		AssignmentEntity savedEntity = assignmentRepository.save(entity);

		long countAfter = assignmentRepository.count();

		long difference = countAfter - countBefore;

		// Verify CREATE
		assertTrue(difference == 1);

		// Setup READ
		Optional<AssignmentEntity> optionalReadEntity = assignmentRepository.findById(savedEntity.getId());

		AssignmentEntity readEntity = optionalReadEntity.get();

		// Verify READ
		assertEquals(userEntity.getId(), readEntity.getUser().getId());
		assertEquals(exerciseGroupEntity.getId(), readEntity.getExerciseGroup().getId());
		assertEquals("ASSIGNED", readEntity.getAssignmentStatus().getCode());

		// Setup UPDATE
		AssignmentStatusEntity statusCompleted = assignmentStatusRepository.findByCode(AssignmentStatusEntity.COMPLETED);
		
		readEntity.setAssignmentStatus(statusCompleted);

		assignmentRepository.save(readEntity);

		Optional<AssignmentEntity> optionalUpdatedEntity = assignmentRepository.findById(savedEntity.getId());

		AssignmentEntity updatedEntity = optionalUpdatedEntity.get();

		// Verify UPDATE
		assertEquals("COMPLETED", readEntity.getAssignmentStatus().getCode());

		// Setup DELETE
		assignmentRepository.delete(updatedEntity);

		long countAfterDelete = assignmentRepository.count();

		// Verify DELETE
		assertTrue(countAfterDelete == countBefore);

	}

	@Test
	public void cascadeDeleteUserTest() {

		String userName = "cascadeDeleteUserTest user name";

		String exerciseGroupName = "cascadeDeleteUserTest exercise group name";

		UserEntity userEntity = createUserEntity(userName);

		ExerciseGroupEntity exerciseGroupEntity = createExerciseGroupEntity(exerciseGroupName);
		
		AssignmentStatusEntity status = assignmentStatusRepository.findByCode(AssignmentStatusEntity.ASSIGNED);

		AssignmentEntity entity = new AssignmentEntity(userEntity, exerciseGroupEntity, status);

		long countBefore = assignmentRepository.count();
		
		assignmentRepository.save(entity);

		// Delete the user.
		userRepository.delete(userEntity);

		long countAfter = assignmentRepository.count();

		// Make sure that the assignment to this user was removed when the user
		// was deleted.
		assertTrue(countBefore == countAfter);

	}
	
	@Test
	public void cascadeDeleteExerciseGroupTest() {

		String userName = "cascadeDeleteExerciseGroupTest user name";

		String exerciseGroupName = "cascadeDeleteExerciseGroupTest exercise group name";

		UserEntity userEntity = createUserEntity(userName);

		ExerciseGroupEntity exerciseGroupEntity = createExerciseGroupEntity(exerciseGroupName);

		AssignmentStatusEntity status = assignmentStatusRepository.findByCode(AssignmentStatusEntity.ASSIGNED);
		
		AssignmentEntity entity = new AssignmentEntity(userEntity, exerciseGroupEntity, status);

		long countBefore = assignmentRepository.count();
		
		assignmentRepository.save(entity);

		exerciseGroupRepository.delete(exerciseGroupEntity);

		long countAfter = assignmentRepository.count();

		// Make sure that the assignment to this user was removed when the user
		// was deleted.
		assertTrue(countBefore == countAfter);

	}
	



}
