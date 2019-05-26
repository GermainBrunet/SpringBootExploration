package ca.gb.sf.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ca.gb.sf.models.ExerciseEntity;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.repositories.ExerciseRepository;

@Component
public class ExerciseService extends CommonService {

	@Autowired
	ExerciseRepository exerciseRepository;
	
	public ExerciseEntity create(String initialWord, String targetWord, String writtenInstructions, String readInstructions, Integer exerciseOrder, ExerciseGroupEntity exerciseGroup) {
		
		ExerciseEntity exercise = new ExerciseEntity(initialWord, targetWord, writtenInstructions, readInstructions, exerciseOrder, exerciseGroup);

		return save(exercise);
		
	}
	
	public ExerciseEntity save(ExerciseEntity exercise) {
		
		setAuditingFields(exercise);
		
		return exerciseRepository.save(exercise);
		
	}
	
	public long count() {
		
		return exerciseRepository.count();
	}
	
	public void deleteAll() {
		
		exerciseRepository.deleteAll();
		
	}
	
	public List<ExerciseEntity> findAll() {
		
		List<ExerciseEntity> exercises = new ArrayList<ExerciseEntity>();
		
		Iterable<ExerciseEntity> exerciseIterable = exerciseRepository.findAll();
		
		Iterator<ExerciseEntity> exerciseIterator = exerciseIterable.iterator();
		
		while (exerciseIterator.hasNext()) {
			
			exercises.add(exerciseIterator.next());
			
		}
		
		return exercises;
		
	}
	
	public List<ExerciseEntity> findByExerciseGroup(ExerciseGroupEntity exerciseGroupEntity) {
		
		List<ExerciseEntity> exercises = exerciseRepository.findAllByExerciseGroup(exerciseGroupEntity);
		
		return exercises;
		
	}
	
	public ExerciseEntity findFirst(ExerciseGroupEntity exerciseGroupEntity) {
		
		List<ExerciseEntity> exercises = exerciseRepository.findAllByExerciseGroup(exerciseGroupEntity);
		
		return exercises.get(0);
		
	}

	@Transactional
	public ExerciseEntity findNext(ExerciseGroupEntity exerciseGroupEntity, long currentExerciseEntityId) {
		
		List<ExerciseEntity> exercises = exerciseRepository.findAllByExerciseGroup(exerciseGroupEntity);
			
		int current = 0;
		for (int i = 0; i < exercises.size(); i++) {
			
			ExerciseEntity exerciseEntity = exercises.get(i);
			
			if (exerciseEntity.getId() == currentExerciseEntityId) {
				
				current = i;
				
				break;
				
			}
			
		}
		
		if ((current + 1) >= exercises.size()) {
			
			return null;
			
		}
		
		return exercises.get(current + 1);
		
	}


	
}
