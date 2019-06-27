package ca.gb.sf;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.gb.sf.Start;
import ca.gb.sf.models.AssignmentStatusEntity;
import ca.gb.sf.models.KeywordEntity;
import ca.gb.sf.models.LevelEntity;
import ca.gb.sf.repositories.AssignmentStatusRepository;
import ca.gb.sf.repositories.KeywordRepository;
import ca.gb.sf.repositories.LevelRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Start.class)
public class SpringContextIntegrationTest {

	protected static final String LEVEL_1 = "Level 1";
	protected static final String LEVEL_2 = "Level 2";
	protected static final String LEVEL_3 = "Level 3";
	
	protected static final String KEYWORD_1 = "Keyword 1";
	protected static final String KEYWORD_2 = "Keyword 2";
	protected static final String KEYWORD_3 = "Keyword 3";
	
	protected static final String NAME_1 = "name 1 apple";
	protected static final String NAME_2 = "name 2 banana";
	protected static final String NAME_3 = "name 3 pear";
	protected static final String NAME_4 = "name 4 strawberry";
	protected static final String NAME_5 = "name 5 raspberry";
	
	@Autowired
	protected AssignmentStatusRepository assignmentStatusRepository;

	@Autowired
	protected LevelRepository levelRepository;

	@Autowired
	protected KeywordRepository keywordRepository;
	
	@Before
	public void repoSetup() {
		
		createAssignmentStatus(AssignmentStatusEntity.ASSIGNED, "FR-Assigned", "Assigned");
		createAssignmentStatus(AssignmentStatusEntity.WORK_IN_PROGRESS, "FR-WorkInProgress", "WorkInProgress");
		createAssignmentStatus(AssignmentStatusEntity.COMPLETED, "FR-Completed", "Completed");
		
		createLevel(LEVEL_1);
		createLevel(LEVEL_2);
		createLevel(LEVEL_3);
		
		createKeyword(KEYWORD_1);
		createKeyword(KEYWORD_2);
		createKeyword(KEYWORD_3);
		
	}
	
	private void createAssignmentStatus(String code, String nameFrench, String nameEnglish) {
		
		AssignmentStatusEntity assignmentStatusEntity = assignmentStatusRepository.findByCode(code);
		
		if (assignmentStatusEntity == null) {
			
			assignmentStatusRepository.save(new AssignmentStatusEntity(code, nameFrench, nameEnglish));
			
		}
		
	}
	
	private void createLevel(String name) {
		
		LevelEntity level = levelRepository.findByName(name);
		
		if (level == null) {
			
			levelRepository.save(new LevelEntity(name));
			
		}
		
	}
	
	private void createKeyword(String keyword) {
		
		KeywordEntity keywordObject = keywordRepository.findByKeyword(keyword);
		
		if (keywordObject == null) {
			
			keywordRepository.save(new KeywordEntity(keyword));
			
		}
		
	}
	
    
}
