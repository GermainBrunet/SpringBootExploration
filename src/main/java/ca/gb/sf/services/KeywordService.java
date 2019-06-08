package ca.gb.sf.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.gb.sf.models.KeywordEntity;
import ca.gb.sf.repositories.KeywordRepository;

@Component
public class KeywordService extends CommonService {

	@Autowired
	KeywordRepository keywordRepository;
	
	public KeywordEntity create(String keywordValue) {
		
		KeywordEntity keywordEntity = new KeywordEntity(keywordValue);

		return save(keywordEntity);
		
	}
	
	public KeywordEntity save(KeywordEntity keywordEntity) {
		
		setAuditingFields(keywordEntity);
		
		return keywordRepository.save(keywordEntity);
		
	}
	
	public long count() {
		
		return keywordRepository.count();
	}
	
	public void deleteAll() {
		
		keywordRepository.deleteAll();
		
	}
	
	public List<KeywordEntity> findAll() {
		
		List<KeywordEntity> keywords = new ArrayList<KeywordEntity>();
		
		Iterable<KeywordEntity> keywordsIterable = keywordRepository.findAll();
		
		Iterator<KeywordEntity> keywordsIterator = keywordsIterable.iterator();
		
		while (keywordsIterator.hasNext()) {
			
			keywords.add(keywordsIterator.next());
			
		}
		
		return keywords;
		
	}
	
	public KeywordEntity findByKeyword(String keyword) {
		
		return keywordRepository.findByKeyword(keyword);
		
	}
	
}
