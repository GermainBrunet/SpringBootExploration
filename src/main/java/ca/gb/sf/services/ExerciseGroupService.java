package ca.gb.sf.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.gb.sf.models.ExerciseEntity;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.repositories.ExerciseGroupRepository;

@Component
public class ExerciseGroupService {

	@Autowired
	ExerciseGroupRepository exerciseGroupRepository;

	public ExerciseGroupEntity create(String name) {
		
		ExerciseGroupEntity exerciseGroup = new ExerciseGroupEntity();
		
		exerciseGroup.setName(name);
		
		return create(exerciseGroup);
		
	}
	
	public ExerciseGroupEntity create(ExerciseGroupEntity exerciseGroup) {
		
		return exerciseGroupRepository.save(exerciseGroup);
		
	}
	
	public long count() {
		
		return exerciseGroupRepository.count();
	}
	
	public void deleteAll() {
		
		exerciseGroupRepository.deleteAll();
		
	}
	
	public ExerciseGroupEntity findByName(String name) {
		
		return exerciseGroupRepository.findByName(name);
		
	}
	
	// @Transactional
//	public ExerciseGroupEntity initialize(ExerciseGroupEntity exerciseGroupEntity) {
//		
//		ExerciseGroupEntity exerciseGroupEntityFull = exerciseGroupRepository.initialize2(exerciseGroupEntity.getId());
//		
//		for (ExerciseEntity exercise : exerciseGroupEntityFull.getExercises()) {
//			
//			exercise.getExerciseOrder();
//		}
//		
//		return exerciseGroupEntityFull;
//		
//	}
	
	@Transactional
	public List<ExerciseEntity> fetchExerciseEntities(Long exerciseGroupEntityId) {
		
		Optional<ExerciseGroupEntity> exerciseGroupEntityOptional = exerciseGroupRepository.findById(exerciseGroupEntityId);
		
		ExerciseGroupEntity exerciseGroupEntity = exerciseGroupEntityOptional.get();
		
		Hibernate.initialize(exerciseGroupEntity.getExercises());
		
		List<ExerciseEntity> exercises = exerciseGroupEntity.getExercises();
		
		return exercises;
		
	}
	
	
}
