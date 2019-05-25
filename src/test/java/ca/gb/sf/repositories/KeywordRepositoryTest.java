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
import ca.gb.sf.models.KeywordEntity;

public class KeywordRepositoryTest extends H2IntegrationTest {
	
	@Autowired
	KeywordRepository keywordRepository;

	@Test
	public void crudTest() {
		
		String name = "test keyword name";
		
		String updatedName = "test keyword name updated";
		
		// Setup CREATE
		long countBefore = keywordRepository.count();
		
		KeywordEntity entity = new KeywordEntity(name);
		
		KeywordEntity savedEntity = keywordRepository.save(entity);
		
		long countAfter = keywordRepository.count();
		
		long difference = countAfter - countBefore;
		
		// Verify CREATE
		assertTrue(difference == 1);
		
		// Setup READ
		Optional<KeywordEntity> optionalReadEntity = keywordRepository.findById(savedEntity.getId());
		
		KeywordEntity readEntity = optionalReadEntity.get();
		
		// Verify READ
		assertEquals(name, readEntity.getKeyword()); 
		
		// Setup UPDATE
		readEntity.setKeyword(updatedName);
		
		keywordRepository.save(readEntity);
		
		Optional<KeywordEntity> optionalUpdatedEntity = keywordRepository.findById(savedEntity.getId());

		KeywordEntity updatedEntity = optionalUpdatedEntity.get(); 
		
		// Verify UPDATE
		assertEquals(updatedName, updatedEntity.getKeyword()); 
		
		// Setup DELETE
		keywordRepository.delete(updatedEntity);
		
		long countAfterDelete = keywordRepository.count();
		
		// Verify DELETE
		assertTrue(countAfterDelete == countBefore);
		
	}
	
	@Test
	public void findByKeywordTest() {
		
		String keywordFind = "keyword being tested";
		
		String keywordNotFind = "keyword being tested not found!";
		
		// Setup
		KeywordEntity entity = new KeywordEntity(keywordFind);
		
		keywordRepository.save(entity);
		
		// Verify Find
		KeywordEntity foundEntity = keywordRepository.findByKeyword(keywordFind);
		
		assertNotNull(foundEntity);
		
		// Verify not found
		KeywordEntity notFoundEntity = keywordRepository.findByKeyword(keywordNotFind);
		
		assertNull(notFoundEntity);
		
	}
	
	@Test
	public void findByKeywordContainingTest() {
		
		String keyword = "keyword";
		
		String searchWordToFind = "key";
		
		String searchWordNotToFind = "house";
		
		// Setup
		KeywordEntity entity = new KeywordEntity(keyword);
		
		keywordRepository.save(entity);
		
		// Verify Find
		List<KeywordEntity> foundEntities = keywordRepository.findByKeywordContaining(searchWordToFind);
		
		assertFalse(foundEntities.size() == 0);
		assertTrue(foundEntities.contains(entity));
		
		// Verify not found
		List<KeywordEntity> notFoundEntities = keywordRepository.findByKeywordContaining(searchWordNotToFind);
		
		assertTrue(notFoundEntities.size() == 0);
		assertFalse(notFoundEntities.contains(entity));
		
	}
	



}
