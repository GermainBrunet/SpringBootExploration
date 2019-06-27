package ca.gb.sf.repositories;

import ca.gb.sf.H2IntegrationTest;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.KeywordEntity;
import ca.gb.sf.models.LevelEntity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ExerciseGroupRepositoryCustomTest extends H2IntegrationTest {

	static ExerciseGroupEntity savedExerciseGroupEntity1 = null;
	static ExerciseGroupEntity savedExerciseGroupEntity2 = null;
	static ExerciseGroupEntity savedExerciseGroupEntity3 = null;
	static ExerciseGroupEntity savedExerciseGroupEntity4 = null;
	
	private static boolean setUpIsDone = false;
	
	@Before
	public void createSampleData() {

		if (setUpIsDone) {
			return;
		}
		setUpIsDone = true;
		
		System.out.println("HERE!!!");
		
		savedExerciseGroupEntity1 = createSampleData(NAME_1, LEVEL_1, new String[] {KEYWORD_1});
		savedExerciseGroupEntity2 = createSampleData(NAME_2, LEVEL_1, new String[] {KEYWORD_1, KEYWORD_3});
		savedExerciseGroupEntity3 = createSampleData(NAME_3, LEVEL_1, new String[] {KEYWORD_1, KEYWORD_2});
		savedExerciseGroupEntity4 = createSampleData(NAME_4, LEVEL_2, new String[] {KEYWORD_3});
		
	}
	
	@Test
	public void testFindByName() {
		
		// Find by name.
		List<ExerciseGroupEntity> exerciseGroupEntities = exerciseGroupRepository.findByLevelKeywordsAndName(NAME_1, null, null);
		
		assertTrue(exerciseGroupEntities.contains(savedExerciseGroupEntity1));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity2));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity3));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity4));
		
		
	}
	
	@Test
	public void testFindByNameLike() {
		
		// Find by name.
		List<ExerciseGroupEntity> exerciseGroupEntities = exerciseGroupRepository.findByLevelKeywordsAndName("apple", null, null);

		System.out.println(exerciseGroupEntities);

		assertTrue(exerciseGroupEntities.contains(savedExerciseGroupEntity1));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity2));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity3));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity4));
		
		
	}
	
	@Test
	public void testFindByLevel() {
		
		// Find by level.
		List<ExerciseGroupEntity> exerciseGroupEntities = exerciseGroupRepository.findByLevelKeywordsAndName(null, levelRepository.findByName(LEVEL_1), null);
		
		assertTrue(exerciseGroupEntities.contains(savedExerciseGroupEntity1));
		assertTrue(exerciseGroupEntities.contains(savedExerciseGroupEntity2));
		assertTrue(exerciseGroupEntities.contains(savedExerciseGroupEntity3));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity4));
		
	}
	
	@Test
	public void testFindByKeyword() {
		
		// Find by keyword.
		List<ExerciseGroupEntity> exerciseGroupEntities = exerciseGroupRepository.findByLevelKeywordsAndName(null, null, toKeywordEntities(new String[] {KEYWORD_1}));

		assertTrue(exerciseGroupEntities.contains(savedExerciseGroupEntity1));
		assertTrue(exerciseGroupEntities.contains(savedExerciseGroupEntity2));
		assertTrue(exerciseGroupEntities.contains(savedExerciseGroupEntity3));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity4));
		
	}
	
	@Test
	public void testFindByKeyword2() {
		
		List<ExerciseGroupEntity> exerciseGroupEntities = exerciseGroupRepository.findByLevelKeywordsAndName(null, null, toKeywordEntities(new String[] {KEYWORD_3}));
		
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity1));
		assertTrue(exerciseGroupEntities.contains(savedExerciseGroupEntity2));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity3));
		assertTrue(exerciseGroupEntities.contains(savedExerciseGroupEntity4));
		
	}
	
	@Test
	public void testFindByKeywordAndLevel() {
		
		List<ExerciseGroupEntity> exerciseGroupEntities = exerciseGroupRepository.findByLevelKeywordsAndName(null, levelRepository.findByName(LEVEL_2), toKeywordEntities(new String[] {KEYWORD_3}));

		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity1));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity2));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity3));
		assertTrue(exerciseGroupEntities.contains(savedExerciseGroupEntity4));
		
	}

	@Test
	public void testFindByNameAndLevel() {
		
		List<ExerciseGroupEntity> exerciseGroupEntities = exerciseGroupRepository.findByLevelKeywordsAndName(NAME_2, levelRepository.findByName(LEVEL_1), null);

		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity1));
		assertTrue(exerciseGroupEntities.contains(savedExerciseGroupEntity2));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity3));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity4));
		
	}
	
	@Test
	public void testFindByNameLikeAndLevel() {
		
		List<ExerciseGroupEntity> exerciseGroupEntities = exerciseGroupRepository.findByLevelKeywordsAndName("pear", levelRepository.findByName(LEVEL_1), null);

		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity1));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity2));
		assertTrue(exerciseGroupEntities.contains(savedExerciseGroupEntity3));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity4));
		
	}
	
	@Test
	public void testFindByNameLikeAndKeyword() {
		
		List<ExerciseGroupEntity> exerciseGroupEntities = exerciseGroupRepository.findByLevelKeywordsAndName("pear", null, toKeywordEntities(new String[] {KEYWORD_2}));
		
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity1));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity2));
		assertTrue(exerciseGroupEntities.contains(savedExerciseGroupEntity3));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity4));
		
	}

	@Test
	public void testFindByNameLikeLevelAndKeyword() {
		
		List<ExerciseGroupEntity> exerciseGroupEntities = exerciseGroupRepository.findByLevelKeywordsAndName("strawberry", levelRepository.findByName(LEVEL_2), toKeywordEntities(new String[] {KEYWORD_3}));

		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity1));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity2));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity3));
		assertTrue(exerciseGroupEntities.contains(savedExerciseGroupEntity4));
		
	}

	@Test
	public void testFindByNameLikeUpperCaseLevelAndKeyword() {
		
		List<ExerciseGroupEntity> exerciseGroupEntities = exerciseGroupRepository.findByLevelKeywordsAndName("Strawberry", levelRepository.findByName(LEVEL_2), toKeywordEntities(new String[] {KEYWORD_3}));

		System.out.println(exerciseGroupEntities);

		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity1));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity2));
		assertFalse(exerciseGroupEntities.contains(savedExerciseGroupEntity3));
		assertTrue(exerciseGroupEntities.contains(savedExerciseGroupEntity4));
		
	}


	private ExerciseGroupEntity createSampleData(String exerciseGroupNameValue, String levelValue, String[] keywordValues) {

		LevelEntity levelEntity = levelRepository.findByName(levelValue);
		
		List<KeywordEntity> keywords = new ArrayList<KeywordEntity>();
		for (int i = 0; i < keywordValues.length; i++) {
			keywords.add(keywordRepository.findByKeyword(keywordValues[i]));
		}
		
		return createExerciseGroupEntity(exerciseGroupNameValue, levelEntity, keywords);
		
	}

	private List<KeywordEntity> toKeywordEntities(String[] keywordValues) {

		List<KeywordEntity> keywords = new ArrayList<KeywordEntity>();
		
		for (int i = 0; i < keywordValues.length; i++) {
			KeywordEntity keyword = keywordRepository.findByKeyword(keywordValues[i]);
			keywords.add(keyword);
		}
		
		return keywords;
		
	}
	
}
