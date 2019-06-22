package ca.gb.sf.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.models.AssignmentEntity;
import ca.gb.sf.models.AssignmentStatusEntity;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.UserEntity;
import ca.gb.sf.services.AssignmentService;
import ca.gb.sf.services.ExerciseGroupService;
import ca.gb.sf.services.UserService;

public class AssignmentServiceTest extends CommonServiceTest {

	@Autowired
	AssignmentService assignmentService;

	@Autowired
	ExerciseGroupService exerciseGroupService;
	
	@Autowired
	UserService userService;
	
	@Test
	public void createUsingStringTest() {
		
		ExerciseGroupEntity exerciseGroup = exerciseGroupService.findByName("ma 1.1");
		
		UserEntity user = userService.find("user1");

		// Save an assignment.
		
		AssignmentEntity assignment = new AssignmentEntity();
		assignment.setUser(user);
		assignment.setExerciseGroup(exerciseGroup);
		assignmentService.setAssignmentToAssigned(assignment);
		AssignmentEntity savedAssignmentEntity = assignmentService.save(assignment);
		long savedAssignmentId = savedAssignmentEntity.getId();
		
		// Load an assignment.
		AssignmentEntity loadAssignmentEntity = assignmentService.findByUserAndExerciseGroup(user, exerciseGroup);
		long loadAssignmentId = loadAssignmentEntity.getId();
		assertTrue(savedAssignmentId == loadAssignmentId);
		assertEquals(loadAssignmentEntity.getAssignmentStatus().getCode(), "ASSIGNED");
	
		// Change an assignment.
		assignmentService.setAssignmentToWorkInProgress(loadAssignmentEntity);
		assignmentService.save(assignment);
		
		// Verify the change.
		AssignmentEntity changedAssignmentEntity = assignmentService.findByUserAndExerciseGroup(user, exerciseGroup);
		assertEquals(loadAssignmentEntity.getAssignmentStatus().getCode(), "WORK_IN_PROGRESS");
		
		// Delete the assignment.
		assignmentService.delete(changedAssignmentEntity);
		
		AssignmentEntity deletedAssignmentEntity = assignmentService.findByUserAndExerciseGroup(user, exerciseGroup);
		assertNull(deletedAssignmentEntity);
		
	}
	
	
}
