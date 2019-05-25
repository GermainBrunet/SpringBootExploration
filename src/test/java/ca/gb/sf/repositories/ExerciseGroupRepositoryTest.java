package ca.gb.sf.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ca.gb.sf.H2IntegrationTest;
import ca.gb.sf.models.ExerciseEntity;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.KeywordEntity;
import ca.gb.sf.models.LevelEntity;

public class ExerciseGroupRepositoryTest extends H2IntegrationTest {
	
	@Autowired
	ExerciseGroupRepository exerciseGroupRepository;

	@Autowired
	ExerciseRepository exerciseRepository;

	@Autowired
	KeywordRepository keywordRepository;

	@Autowired
	LevelRepository levelRepository;

	@Test
	public void crudTest() {
		
		String name = "test exercise group name";
		
		String updatedName = "test exercise group name updated";
		
		// Setup CREATE
		long countBefore = exerciseGroupRepository.count();
		
		// 	public ExerciseGroupEntity(String name, String description, String author, String source,
		// List<ExerciseEntity> exercises, List<KeywordEntity> keywords, List<AssignmentEntity> assignments,
		// LevelEntity level) {
		
		ExerciseGroupEntity entity = new ExerciseGroupEntity(name, "description", "author", "source", null, null, null, null);
		
		ExerciseGroupEntity savedEntity = exerciseGroupRepository.save(entity);
		
		long countAfter = exerciseGroupRepository.count();
		
		long difference = countAfter - countBefore;
		
		// Verify CREATE
		assertTrue(difference == 1);
		
		// Setup READ
		Optional<ExerciseGroupEntity> optionalReadEntity = exerciseGroupRepository.findById(savedEntity.getId());
		
		ExerciseGroupEntity readEntity = optionalReadEntity.get();
		
		// Verify READ
		assertEquals(name, readEntity.getName()); 
		
		// Setup UPDATE
		readEntity.setName(updatedName);
		
		exerciseGroupRepository.save(readEntity);
		
		Optional<ExerciseGroupEntity> optionalUpdatedEntity = exerciseGroupRepository.findById(savedEntity.getId());

		ExerciseGroupEntity updatedEntity = optionalUpdatedEntity.get(); 
		
		// Verify UPDATE
		assertEquals(updatedName, updatedEntity.getName()); 
		
		// Setup DELETE
		exerciseGroupRepository.delete(updatedEntity);
		
		long countAfterDelete = exerciseGroupRepository.count();
		
		// Verify DELETE
		assertTrue(countAfterDelete == countBefore);
		
	}
	
	@Test
	public void findByAuthorListTest() {
		
		String goodAuthor = "good author";
		
		String badAuthor = "bad author";
		
		ExerciseGroupEntity entity = new ExerciseGroupEntity("findByAuthorListTestName", "description", goodAuthor, "source", null, null, null, null);

		ExerciseGroupEntity savedEntity = exerciseGroupRepository.save(entity);
		
		List<ExerciseGroupEntity> goodEntities = exerciseGroupRepository.findByAuthor(goodAuthor.toLowerCase());

		assertFalse(goodEntities.size() == 0);
		assertTrue(goodEntities.contains(savedEntity));
		
		List<ExerciseGroupEntity> badEntities = exerciseGroupRepository.findByAuthor(badAuthor.toLowerCase());
		
		assertTrue(badEntities.size() == 0);
		assertFalse(badEntities.contains(savedEntity));
		
	}
	
	
	@Test
	public void findByAuthorPageTest() {
		
		String goodAuthor = "good author";
		
		String badAuthor = "bad author";
		
		ExerciseGroupEntity entity = new ExerciseGroupEntity("name", "description", goodAuthor, "source", null, null, null, null);

		ExerciseGroupEntity savedEntity = exerciseGroupRepository.save(entity);

		Pageable pageable = PageRequest.of(0, 100, Sort.by("name"));
		
		Page<ExerciseGroupEntity> goodExerciseGroupEntityPage = exerciseGroupRepository.findByAuthor(pageable, goodAuthor);
		
		List<ExerciseGroupEntity> goodEexerciseGroupEntityList = goodExerciseGroupEntityPage.getContent();
		
		assertFalse(goodEexerciseGroupEntityList.size() == 0);
		assertTrue(goodEexerciseGroupEntityList.contains(savedEntity));

		Page<ExerciseGroupEntity> badExerciseGroupEntityPage = exerciseGroupRepository.findByAuthor(pageable, badAuthor);
		
		List<ExerciseGroupEntity> badEexerciseGroupEntityList = badExerciseGroupEntityPage.getContent();
		
		assertTrue(badEexerciseGroupEntityList.size() == 0);
		assertFalse(badEexerciseGroupEntityList.contains(savedEntity));

	}

	@Test
	public void findByKeywordListTest() {
		
		KeywordEntity keyword1 = createKeywordEntity("keyword A");
		KeywordEntity keyword2 = createKeywordEntity("keyword B");
		KeywordEntity keyword3 = createKeywordEntity("keyword C");
		KeywordEntity keyword4 = createKeywordEntity("keyword D");
		
		List<KeywordEntity> keywords = new ArrayList<KeywordEntity>();
		keywords.add(keyword1);
		keywords.add(keyword2);
		keywords.add(keyword3);
		
		ExerciseGroupEntity entity = new ExerciseGroupEntity("findByKeywordListTestName", "description", "author", "source", null, keywords, null, null);

		ExerciseGroupEntity savedEntity = exerciseGroupRepository.save(entity);
		
		List<ExerciseGroupEntity> goodEntities = exerciseGroupRepository.findByKeywords(keyword1);

		assertFalse(goodEntities.size() == 0);
		assertTrue(goodEntities.contains(savedEntity));
		
		List<ExerciseGroupEntity> badEntities = exerciseGroupRepository.findByKeywords(keyword4);
		
		assertTrue(badEntities.size() == 0);
		assertFalse(badEntities.contains(savedEntity));
		
		
	}

	@Test
	public void findByKeywordPageTest() {
		
		KeywordEntity keyword1 = createKeywordEntity("keyword A");
		KeywordEntity keyword2 = createKeywordEntity("keyword B");
		KeywordEntity keyword3 = createKeywordEntity("keyword C");
		KeywordEntity keyword4 = createKeywordEntity("keyword D");
		
		List<KeywordEntity> keywords = new ArrayList<KeywordEntity>();
		keywords.add(keyword1);
		keywords.add(keyword2);
		keywords.add(keyword3);
		
		ExerciseGroupEntity entity = new ExerciseGroupEntity("findByKeywordPageTestName", "description", "author", "source", null, keywords, null, null);
		
		exerciseGroupRepository.save(entity);

		Pageable pageable = PageRequest.of(0, 100, Sort.by("name"));
		
		Page<ExerciseGroupEntity> exerciseGroupEntityPage = exerciseGroupRepository.findByKeywords(pageable, keyword1);
		
		List<ExerciseGroupEntity> exerciseGroupEntityList = exerciseGroupEntityPage.getContent();
		
		assertFalse(exerciseGroupEntityList.size() == 0);
		assertTrue(exerciseGroupEntityList.contains(entity));

		Page<ExerciseGroupEntity> exerciseGroupEntityPage2 = exerciseGroupRepository.findByKeywords(pageable, keyword4);
		
		List<ExerciseGroupEntity> exerciseGroupEntityList2 = exerciseGroupEntityPage2.getContent();
		
		assertTrue(exerciseGroupEntityList2.size() == 0);
		assertFalse(exerciseGroupEntityList2.contains(entity));
		
	}
	
	@Test
	public void findBySourceListTest() {
		
		String goodSource = "good source";
		
		String badSource = "bad source";
		
		ExerciseGroupEntity entity = new ExerciseGroupEntity("findBySourceListName", "description", "author", goodSource, null, null, null, null);

		ExerciseGroupEntity savedEntity = exerciseGroupRepository.save(entity);
		
		List<ExerciseGroupEntity> goodEntities = exerciseGroupRepository.findBySource(goodSource.toLowerCase());

		assertFalse(goodEntities.size() == 0);
		assertTrue(goodEntities.contains(savedEntity));
		
		List<ExerciseGroupEntity> badEntities = exerciseGroupRepository.findByAuthor(badSource.toLowerCase());
		
		assertTrue(badEntities.size() == 0);
		assertFalse(badEntities.contains(savedEntity));
		
	}
	
	@Test
	public void findBySourcePageTest() {
		
		String goodSource = "good source";
		
		String badSource = "bad source";
		
		ExerciseGroupEntity entity = new ExerciseGroupEntity("findBySourcePageTestName", "description", "author", goodSource, null, null, null, null);

		ExerciseGroupEntity savedEntity = exerciseGroupRepository.save(entity);

		Pageable pageable = PageRequest.of(0, 100, Sort.by("name"));
		
		Page<ExerciseGroupEntity> goodExerciseGroupEntityPage = exerciseGroupRepository.findBySource(pageable, goodSource);
		
		List<ExerciseGroupEntity> goodEexerciseGroupEntityList = goodExerciseGroupEntityPage.getContent();
		
		assertFalse(goodEexerciseGroupEntityList.size() == 0);
		assertTrue(goodEexerciseGroupEntityList.contains(savedEntity));

		Page<ExerciseGroupEntity> badExerciseGroupEntityPage = exerciseGroupRepository.findBySource(pageable, badSource);
		
		List<ExerciseGroupEntity> badEexerciseGroupEntityList = badExerciseGroupEntityPage.getContent();
		
		assertTrue(badEexerciseGroupEntityList.size() == 0);
		assertFalse(badEexerciseGroupEntityList.contains(savedEntity));

	}
	
	@Test
	public void findByLevelListTest() {
		
		LevelEntity level1 = createLevelEntity("level A");
		LevelEntity level2 = createLevelEntity("level B");
		
		ExerciseGroupEntity entity = new ExerciseGroupEntity("findByLevelListName", "description", "author", "source", null, null, null, level1);

		ExerciseGroupEntity savedEntity = exerciseGroupRepository.save(entity);
		
		List<ExerciseGroupEntity> goodEntities = exerciseGroupRepository.findByLevel(level1);

		assertFalse(goodEntities.size() == 0);
		assertTrue(goodEntities.contains(savedEntity));
		
		List<ExerciseGroupEntity> badEntities = exerciseGroupRepository.findByLevel(level2);
		
		assertTrue(badEntities.size() == 0);
		assertFalse(badEntities.contains(savedEntity));
		
		
	}

	@Test
	public void findByLevelPageTest() {
		
		LevelEntity level1 = createLevelEntity("level A");
		LevelEntity level2 = createLevelEntity("level B");
		
		ExerciseGroupEntity entity = new ExerciseGroupEntity("findByLevelPageTestName", "description", "author", "source", null, null, null, level1);

		ExerciseGroupEntity savedEntity = exerciseGroupRepository.save(entity);

		Pageable pageable = PageRequest.of(0, 100, Sort.by("name"));
		
		Page<ExerciseGroupEntity> exerciseGroupEntityPage = exerciseGroupRepository.findByLevel(pageable, level1);
		
		List<ExerciseGroupEntity> exerciseGroupEntityList = exerciseGroupEntityPage.getContent();
		
		assertFalse(exerciseGroupEntityList.size() == 0);
		assertTrue(exerciseGroupEntityList.contains(savedEntity));

		Page<ExerciseGroupEntity> exerciseGroupEntityPage2 = exerciseGroupRepository.findByLevel(pageable, level2);
		
		List<ExerciseGroupEntity> exerciseGroupEntityList2 = exerciseGroupEntityPage2.getContent();
		
		assertTrue(exerciseGroupEntityList2.size() == 0);
		assertFalse(exerciseGroupEntityList2.contains(savedEntity));
		
	}
	
	@Test
	public void findBySearchStringListTest() {

		KeywordEntity keyword1 = createKeywordEntity("keyword A");
		KeywordEntity keyword2 = createKeywordEntity("keyword B");
		KeywordEntity keyword3 = createKeywordEntity("keyword C");
		createKeywordEntity("keyword D");
		
		List<KeywordEntity> keywords = new ArrayList<KeywordEntity>();
		keywords.add(keyword1);
		keywords.add(keyword2);
		keywords.add(keyword3);
		
		LevelEntity level1 = createLevelEntity("level A");
		createLevelEntity("level B");
		
		ExerciseGroupEntity entity = new ExerciseGroupEntity("findBySearchStringListNameTestAB", "description", "author", "source", null, keywords, null, level1);

		ExerciseGroupEntity savedEntity = exerciseGroupRepository.save(entity);
		
		List<ExerciseGroupEntity> entities = exerciseGroupRepository.findBySearchString("findbysearchstringlistnametestab");

		assertFalse(entities.size() == 0);
		assertTrue(entities.contains(savedEntity));
		
		entities = exerciseGroupRepository.findBySearchString("author");

		assertFalse(entities.size() == 0);
		assertTrue(entities.contains(savedEntity));

		entities = exerciseGroupRepository.findBySearchString("description");

		assertFalse(entities.size() == 0);
		assertTrue(entities.contains(savedEntity));

		entities = exerciseGroupRepository.findBySearchString("source");

		assertFalse(entities.size() == 0);
		assertTrue(entities.contains(savedEntity));

		entities = exerciseGroupRepository.findBySearchString("keyword A".toLowerCase());

		assertFalse(entities.size() == 0);
		assertTrue(entities.contains(savedEntity));

		entities = exerciseGroupRepository.findBySearchString("level A".toLowerCase());

		assertFalse(entities.size() == 0);
		assertTrue(entities.contains(savedEntity));

		
		entities = exerciseGroupRepository.findBySearchString("bad");
		
		assertTrue(entities.size() == 0);
		assertFalse(entities.contains(savedEntity));

		entities = exerciseGroupRepository.findBySearchString("keyword D".toLowerCase());
		
		assertTrue(entities.size() == 0);
		assertFalse(entities.contains(savedEntity));

		entities = exerciseGroupRepository.findBySearchString("level B".toLowerCase());

		assertTrue(entities.size() == 0);
		assertFalse(entities.contains(savedEntity));
		
	}
	
	@Test
	public void findBySearchStringPageTest() {

		KeywordEntity keyword1 = createKeywordEntity("keyword A");
		KeywordEntity keyword2 = createKeywordEntity("keyword B");
		KeywordEntity keyword3 = createKeywordEntity("keyword C");
		createKeywordEntity("keyword D");
		
		List<KeywordEntity> keywords = new ArrayList<KeywordEntity>();
		keywords.add(keyword1);
		keywords.add(keyword2);
		keywords.add(keyword3);
		
		LevelEntity level1 = createLevelEntity("level A");
		createLevelEntity("level B");
		
		ExerciseGroupEntity entity = new ExerciseGroupEntity("findBySearchStringPageTestName", "description", "author", "source", null, keywords, null, level1);

		ExerciseGroupEntity savedEntity = exerciseGroupRepository.save(entity);
		
		Pageable pageable = PageRequest.of(0, 100, Sort.by("name"));
		
		Page<ExerciseGroupEntity> exerciseGroupEntityPage = exerciseGroupRepository.findBySearchString(pageable, "findbysearchstringpagetestname");
		
		List<ExerciseGroupEntity> entities = exerciseGroupEntityPage.getContent();
		
		assertFalse(entities.size() == 0);
		assertTrue(entities.contains(savedEntity));
		
		exerciseGroupEntityPage = exerciseGroupRepository.findBySearchString(pageable, "author");
		
		entities = exerciseGroupEntityPage.getContent();
		
		assertFalse(entities.size() == 0);
		assertTrue(entities.contains(savedEntity));

		exerciseGroupEntityPage = exerciseGroupRepository.findBySearchString(pageable, "description");
		
		entities = exerciseGroupEntityPage.getContent();

		assertFalse(entities.size() == 0);
		assertTrue(entities.contains(savedEntity));

		exerciseGroupEntityPage = exerciseGroupRepository.findBySearchString(pageable, "source");
		
		entities = exerciseGroupEntityPage.getContent();
		
		assertFalse(entities.size() == 0);
		assertTrue(entities.contains(savedEntity));

		exerciseGroupEntityPage = exerciseGroupRepository.findBySearchString(pageable, "keyword A".toLowerCase());
		
		entities = exerciseGroupEntityPage.getContent();

		assertFalse(entities.size() == 0);
		assertTrue(entities.contains(savedEntity));

		exerciseGroupEntityPage = exerciseGroupRepository.findBySearchString(pageable, "level A".toLowerCase());
		
		entities = exerciseGroupEntityPage.getContent();

		assertFalse(entities.size() == 0);
		assertTrue(entities.contains(savedEntity));

		exerciseGroupEntityPage = exerciseGroupRepository.findBySearchString(pageable, "bad");
		
		entities = exerciseGroupEntityPage.getContent();
		
		assertTrue(entities.size() == 0);
		assertFalse(entities.contains(savedEntity));

		exerciseGroupEntityPage = exerciseGroupRepository.findBySearchString(pageable, "keyword D".toLowerCase());
		
		entities = exerciseGroupEntityPage.getContent();
		
		assertTrue(entities.size() == 0);
		assertFalse(entities.contains(savedEntity));

		exerciseGroupEntityPage = exerciseGroupRepository.findBySearchString(pageable, "level B".toLowerCase());
		
		entities = exerciseGroupEntityPage.getContent();

		assertTrue(entities.size() == 0);
		assertFalse(entities.contains(savedEntity));
		
	}
	
	@Test
	public void cascadeDeleteExercisesTest() {
		
		ExerciseGroupEntity entity = new ExerciseGroupEntity("cascadeDeleteExercisesTestName", "description", "author", "source", null, null, null, null);
		
		ExerciseGroupEntity savedEntity = exerciseGroupRepository.save(entity);

		long exerciseCountBefore = exerciseRepository.count();
		
		for (int i = 0; i < 5; i++) {
			
			String exerciseName = "name " + i;
			
			ExerciseEntity exerciseEntity = new ExerciseEntity(exerciseName, exerciseName, exerciseName, exerciseName, i, savedEntity);
			
			exerciseRepository.save(exerciseEntity);
			
		}

		long exerciseCountAfter = exerciseRepository.count();
		
		long exerciseCountDifference = exerciseCountAfter - exerciseCountBefore;
		
		assertTrue(exerciseCountDifference == 5);
		
		exerciseGroupRepository.delete(entity);
		
		long exerciseCountAfterDelete = exerciseRepository.count();
		
		long exerciseCountDifferenceAfterDelete = exerciseCountAfterDelete - exerciseCountBefore;
		
		assertTrue(exerciseCountDifferenceAfterDelete == 0);
		
	}
	
	
	
	
	
	private KeywordEntity createKeywordEntity(String keyword) {
		
		KeywordEntity keywordEntity = new KeywordEntity(keyword);
		
		return keywordRepository.save(keywordEntity);
		
	}
	
	private LevelEntity createLevelEntity(String name) {
		
		LevelEntity levelEntity = new LevelEntity(name);
		
		return levelRepository.save(levelEntity);
		
	}
	
	
}
