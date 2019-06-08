package ca.gb.sf.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.gb.sf.models.LevelEntity;
import ca.gb.sf.repositories.LevelRepository;

@Component
public class LevelService extends CommonService {

	@Autowired
	LevelRepository levelRepository;
	
	public LevelEntity create(String levelValue) {
		
		LevelEntity levelEntity = new LevelEntity(levelValue);

		return save(levelEntity);
		
	}
	
	public LevelEntity save(LevelEntity levelEntity) {
		
		setAuditingFields(levelEntity);
		
		return levelRepository.save(levelEntity);
		
	}
	
	public long count() {
		
		return levelRepository.count();
	}
	
	public void deleteAll() {
		
		levelRepository.deleteAll();
		
	}
	
	public List<LevelEntity> findAll() {
		
		List<LevelEntity> levels = new ArrayList<LevelEntity>();
		
		Iterable<LevelEntity> levelsIterable = levelRepository.findAll();
		
		Iterator<LevelEntity> levelsIterator = levelsIterable.iterator();
		
		while (levelsIterator.hasNext()) {
			
			levels.add(levelsIterator.next());
			
		}
		
		return levels;
		
	}
	
	public LevelEntity find(String name) {
		
		return levelRepository.findByName(name);
		
	}
	
}
