package ca.gb.sf.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.repositories.ExerciseGroupRepository;

@Component
public class ExerciseGroupService extends CommonService {

	@Autowired
	ExerciseGroupRepository exerciseGroupRepository;

	/**
	 * Function that will try to c
	 * @param name
	 * @return
	 */
	public ExerciseGroupEntity createUsingName(String name) {
		
		ExerciseGroupEntity exerciseGroupEntity = findByName(name);
		
		if (exerciseGroupEntity != null) {
			
			return exerciseGroupEntity;
			
		}
		
		ExerciseGroupEntity exerciseGroup = new ExerciseGroupEntity();
		
		exerciseGroup.setName(name);
		
		return save(exerciseGroup);
		
	}
	
	public ExerciseGroupEntity save(ExerciseGroupEntity exerciseGroup) {
		
		setAuditingFields(exerciseGroup);
		
		return exerciseGroupRepository.save(exerciseGroup);
		
	}
	
	public long count() {
		
		return exerciseGroupRepository.count();
	}
	
	public void delete(ExerciseGroupEntity exerciseGroupEntity) {
		
		exerciseGroupRepository.delete(exerciseGroupEntity);
		
	}
	
	public void deleteAll() {
		
		exerciseGroupRepository.deleteAll();
		
	}
	
	public ExerciseGroupEntity findByName(String name) {
		
		if (name == null) {
			
			return null;
			
		}
		
		return exerciseGroupRepository.findByName(name.toLowerCase());
		
	}
	
//	@Transactional
//	public List<ExerciseEntity> fetchExerciseEntities(Long exerciseGroupEntityId) {
//		
//		Optional<ExerciseGroupEntity> exerciseGroupEntityOptional = exerciseGroupRepository.findById(exerciseGroupEntityId);
//		
//		ExerciseGroupEntity exerciseGroupEntity = exerciseGroupEntityOptional.get();
//		
//		Hibernate.initialize(exerciseGroupEntity.getExercises());
//		
//		List<ExerciseEntity> exercises = exerciseGroupEntity.getExercises();
//		
//		return exercises;
//		
//	}
	
}
