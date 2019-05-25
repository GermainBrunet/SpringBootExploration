package ca.gb.sf.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.H2IntegrationTest;
import ca.gb.sf.models.LevelEntity;

public class LevelRepositoryTest extends H2IntegrationTest {
	
	@Autowired
	LevelRepository levelRepository;

	@Test
	public void crudTest() {
		
		String name = "test level name";
		
		String updatedName = "test level name updated";
		
		// Setup CREATE
		long countBefore = levelRepository.count();
		
		LevelEntity entity = new LevelEntity(name);
		
		LevelEntity savedEntity = levelRepository.save(entity);
		
		long countAfter = levelRepository.count();
		
		long difference = countAfter - countBefore;
		
		// Verify CREATE
		assertTrue(difference == 1);
		
		// Setup READ
		Optional<LevelEntity> optionalReadEntity = levelRepository.findById(savedEntity.getId());
		
		LevelEntity readEntity = optionalReadEntity.get();
		
		// Verify READ
		assertEquals(name, readEntity.getName()); 
		
		// Setup UPDATE
		readEntity.setName(updatedName);
		
		levelRepository.save(readEntity);
		
		Optional<LevelEntity> optionalUpdatedEntity = levelRepository.findById(savedEntity.getId());

		LevelEntity updatedEntity = optionalUpdatedEntity.get(); 
		
		// Verify UPDATE
		assertEquals(updatedName, updatedEntity.getName()); 
		
		// Setup DELETE
		levelRepository.delete(updatedEntity);
		
		long countAfterDelete = levelRepository.count();
		
		// Verify DELETE
		assertTrue(countAfterDelete == countBefore);
		
	}
	
	@Test
	public void findByKeywordTest() {
		
		String keywordFind = "keyword being tested";
		
		String keywordNotFind = "keyword being tested not found!";
		
		// Setup
		LevelEntity entity = new LevelEntity(keywordFind);
		
		levelRepository.save(entity);
		
		// Verify Find
		LevelEntity foundEntity = levelRepository.findByName(keywordFind);
		
		assertNotNull(foundEntity);
		
		// Verify not found
		LevelEntity notFoundEntity = levelRepository.findByName(keywordNotFind);
		
		assertNull(notFoundEntity);
		
	}
	
	@Test
	public void findByKeywordContainingTest() {
		
		String keyword = "level";
		
		String searchWordToFind = "lev";
		
		String searchWordNotToFind = "door";
		
		// Setup
		LevelEntity entity = new LevelEntity(keyword);
		
		levelRepository.save(entity);
		
		// Verify Find
		List<LevelEntity> foundEntities = levelRepository.findByKeywordContaining(searchWordToFind);
		
		assertFalse(foundEntities.size() == 0);
		assertTrue(foundEntities.contains(entity));
		
		// Verify not found
		List<LevelEntity> notFoundEntities = levelRepository.findByKeywordContaining(searchWordNotToFind);
		
		assertTrue(notFoundEntities.size() == 0);
		assertFalse(notFoundEntities.contains(entity));
		
	}


}
