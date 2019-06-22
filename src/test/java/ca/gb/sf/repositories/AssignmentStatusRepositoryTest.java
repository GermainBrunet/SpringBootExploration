package ca.gb.sf.repositories;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.H2IntegrationTest;
import ca.gb.sf.models.AssignmentStatusEntity;

public class AssignmentStatusRepositoryTest extends H2IntegrationTest {

	@Autowired
	AssignmentStatusRepository assignmentStatusRepository;
	
	@Test
	public void verifyExistance() {
		
		List<AssignmentStatusEntity> assignmentStatusEntities = assignmentStatusRepository.findAll();
		
		assertTrue(assignmentStatusEntities.size() == 3);
		
		boolean codeAssignedFound = false;
		boolean codeCompletedFound = false;
		boolean codeWorkInProgressFound = false;
		
		for (AssignmentStatusEntity assignmentStatusEntity : assignmentStatusEntities) {
			
			// System.out.println(assignmentStatusEntity);
			
			if (assignmentStatusEntity.getCode().equals(AssignmentStatusEntity.COMPLETED)) {
				
				codeCompletedFound = true;
				
			}
			
			if (assignmentStatusEntity.getCode().equals(AssignmentStatusEntity.WORK_IN_PROGRESS)) {
				
				codeWorkInProgressFound = true;
				
			}
			
			if (assignmentStatusEntity.getCode().equals(AssignmentStatusEntity.ASSIGNED)) {
				
				codeAssignedFound = true;
				
			}

		}
		
		assertTrue(codeAssignedFound);
		assertTrue(codeCompletedFound);
		assertTrue(codeWorkInProgressFound);
		
	}
	
}
