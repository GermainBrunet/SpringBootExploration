package ca.gb.sf;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.gb.sf.Start;
import ca.gb.sf.models.AssignmentStatusEntity;
import ca.gb.sf.repositories.AssignmentStatusRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Start.class)
public class SpringContextIntegrationTest {

	@Autowired
	AssignmentStatusRepository assignmentStatusRepository;
	
	@Before
	public void repoSetup() {
		
		createAssignmentStatus(AssignmentStatusEntity.ASSIGNED, "FR-Assigned", "Assigned");
		createAssignmentStatus(AssignmentStatusEntity.WORK_IN_PROGRESS, "FR-WorkInProgress", "WorkInProgress");
		createAssignmentStatus(AssignmentStatusEntity.COMPLETED, "FR-Completed", "Completed");
		
	}
	
	private void createAssignmentStatus(String code, String nameFrench, String nameEnglish) {
		
		AssignmentStatusEntity assignmentStatusEntity = assignmentStatusRepository.findByCode(code);
		
		if (assignmentStatusEntity == null) {
			
			assignmentStatusRepository.save(new AssignmentStatusEntity(code, nameFrench, nameEnglish));
			
		}
		
	}
	
    
}
