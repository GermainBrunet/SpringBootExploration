package ca.gb.sf;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ca.gb.sf.models.AssignmentStatusEntity;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.KeywordEntity;
import ca.gb.sf.models.LevelEntity;
import ca.gb.sf.models.UserEntity;
import ca.gb.sf.repositories.AssignmentStatusRepository;
import ca.gb.sf.repositories.ExerciseGroupRepository;
import ca.gb.sf.repositories.KeywordRepository;
import ca.gb.sf.repositories.LevelRepository;
import ca.gb.sf.repositories.UserRepository;

@SpringBootTest(classes = Start.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class H2IntegrationTest extends SpringContextIntegrationTest {

	@Autowired
	protected AssignmentStatusRepository assignmentStatusRepository;
	
	@Autowired
	protected ExerciseGroupRepository exerciseGroupRepository;

	@Autowired
	protected KeywordRepository keywordRepository;

	@Autowired
	protected LevelRepository levelRepository;
	
	@Autowired
	protected UserRepository userRepository;

	protected AssignmentStatusEntity findAssignmentStatusEntity(String assignmentStatusCode) {
		
		return assignmentStatusRepository.findByCode(assignmentStatusCode);
		
	}
	
	protected ExerciseGroupEntity createExerciseGroupEntity(String exerciseGroupName) {

		ExerciseGroupEntity exerciseGroupEntity = new ExerciseGroupEntity(exerciseGroupName, "description", "author",
				"source", null, null, null, null);

		return exerciseGroupRepository.save(exerciseGroupEntity);

	}
	
	protected ExerciseGroupEntity createExerciseGroupEntity(String exerciseGroupName, LevelEntity level, List<KeywordEntity> keywords) {

		ExerciseGroupEntity exerciseGroupEntity = new ExerciseGroupEntity(exerciseGroupName, "description", "author",
				"source", null, keywords, null, level);

		return exerciseGroupRepository.save(exerciseGroupEntity);

	}

	protected KeywordEntity createKeywordEntity(String keyword) {
		
		KeywordEntity keywordEntity = new KeywordEntity(keyword);
		
		return keywordRepository.save(keywordEntity);
		
	}
	
	protected LevelEntity createLevelEntity(String name) {
		
		LevelEntity levelEntity = new LevelEntity(name);
		
		return levelRepository.save(levelEntity);
		
	}
	
	protected UserEntity createUserEntity(String userName) {

		UserEntity userEntity = new UserEntity();

		userEntity.setDisplayName(userName);
		userEntity.setEmail(userName);
		userEntity.setPassword(userName);

		return userRepository.save(userEntity);

	}

	
}
