package ca.gb.sf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.KeywordEntity;
import ca.gb.sf.models.LevelEntity;
import ca.gb.sf.models.StudentEntity;
import ca.gb.sf.repositories.ExerciseGroupRepository;

@Component
public class ExerciseGroupService extends CommonService {

	private static final String PERCENTAGE = "%";

	@Autowired
	ExerciseGroupRepository exerciseGroupRepository;

	/**
	 * Function that will try to c
	 * 
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

	public ExerciseGroupEntity findById(long exerciseGroupId) {

		Optional<ExerciseGroupEntity> optionalExerciseGroupEntity = exerciseGroupRepository.findById(exerciseGroupId);

		return optionalExerciseGroupEntity.get();

	}

	public Page<ExerciseGroupEntity> findPaginated(Pageable pageable, String searchString) {

		Page<ExerciseGroupEntity> exerciseGroups = null;

		if (StringUtils.isEmpty(searchString)) {

			exerciseGroups = exerciseGroupRepository.findAll(pageable);

		} else {

			// TODO - verify search string

			StringBuilder sb = new StringBuilder();
			sb.append(PERCENTAGE);
			sb.append(searchString.toLowerCase());
			sb.append(PERCENTAGE);

			exerciseGroups = exerciseGroupRepository.searchByName(pageable, sb.toString());

		}

		return exerciseGroups;

	}

	public Page<ExerciseGroupEntity> findPaginatedByStudent(StudentEntity student, Pageable pageable,
			String searchString) {

		Page<ExerciseGroupEntity> exerciseGroups = null;

		if (student == null) {

			return exerciseGroups;

		}

		if (StringUtils.isEmpty(searchString)) {

			exerciseGroups = exerciseGroupRepository.findByStudentId(student.getId(), pageable);

		} else {

			// TODO - verify search string

			StringBuilder sb = new StringBuilder();
			sb.append(PERCENTAGE);
			sb.append(searchString.toLowerCase());
			sb.append(PERCENTAGE);

			exerciseGroups = exerciseGroupRepository.findByStudentIdAndName(student.getId(), pageable, sb.toString());

		}

		return exerciseGroups;

	}

	/**
	 * Returns a pageable groups based on a search string, level and keyword.
	 * Supports the Exercise Group search interface.
	 * 
	 * @param pageable
	 * @param searchString
	 * @param level
	 * @param keywords
	 * @return
	 */
	public List<ExerciseGroupEntity> findPaginated(String searchString, LevelEntity level,
			List<KeywordEntity> keywords) {
		
		return exerciseGroupRepository.findByLevelKeywordsAndName(searchString, level, keywords);
		
	};

	// @Transactional
	// public List<ExerciseEntity> fetchExerciseEntities(Long
	// exerciseGroupEntityId) {
	//
	// Optional<ExerciseGroupEntity> exerciseGroupEntityOptional =
	// exerciseGroupRepository.findById(exerciseGroupEntityId);
	//
	// ExerciseGroupEntity exerciseGroupEntity =
	// exerciseGroupEntityOptional.get();
	//
	// Hibernate.initialize(exerciseGroupEntity.getExercises());
	//
	// List<ExerciseEntity> exercises = exerciseGroupEntity.getExercises();
	//
	// return exercises;
	//
	// }

}
