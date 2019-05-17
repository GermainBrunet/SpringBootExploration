package ca.gb.sf.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ca.gb.sf.models.ExerciseEntity;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.repositories.ExerciseRepository;

@Component
public class ExerciseService {

	@Autowired
	ExerciseRepository exerciseRepository;
	
	public ExerciseEntity create(String initialWord, String targetWord, String instructions, Integer exerciseOrder, ExerciseGroupEntity exerciseGroup) {
		
		ExerciseEntity exercise = new ExerciseEntity(initialWord, targetWord, instructions, exerciseOrder, exerciseGroup);

		return create(exercise);
		
	}
	
	public ExerciseEntity create(ExerciseEntity exercise) {
		
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
		
		List<ExerciseEntity> exercises = exerciseRepository.findByExerciseGroup(exerciseGroupEntity);
		
		return exercises;
		
	}
	
	public ExerciseEntity findFirst(ExerciseGroupEntity exerciseGroupEntity) {
		
		List<ExerciseEntity> exercises = exerciseRepository.findByExerciseGroup(exerciseGroupEntity);
		
		return exercises.get(0);
		
	}

	@Transactional
	public ExerciseEntity findNext(ExerciseGroupEntity exerciseGroupEntity, long currentExerciseEntityId) {
		
		List<ExerciseEntity> exercises = exerciseRepository.findByExerciseGroup(exerciseGroupEntity);
			
		int current = 0;
		for (int i = 0; i < exercises.size(); i++) {
			
			ExerciseEntity exerciseEntity = exercises.get(i);
			
			if (exerciseEntity.getId() == currentExerciseEntityId) {
				
				current = i;
				
				break;
				
			}
			
		}
		
		// System.out.println("max size: " + exercises.size());
		// System.out.println("current size pllus 1: " + (current + 1));
		
		if ((current + 1) >= exercises.size()) {
			
			return null;
			
		}
		
		return exercises.get(current + 1);
		
	}

	
}
