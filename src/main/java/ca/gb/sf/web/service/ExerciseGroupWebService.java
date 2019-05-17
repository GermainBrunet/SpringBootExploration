package ca.gb.sf.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.StudentEntity;
import ca.gb.sf.repositories.ExerciseGroupRepository;

/**
 * Service that returns a list of exercise group for educator selection.
 * Supports the ability to search for a specific exercise group.
 */
@Service
public class ExerciseGroupWebService {

	@Autowired
	ExerciseGroupRepository exerciseGroupRepository;

	private static final String PERCENTAGE = "%";

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
	
	public Page<ExerciseGroupEntity> findPaginatedByStudent(StudentEntity student, Pageable pageable, String searchString) {

		Page<ExerciseGroupEntity> exerciseGroups = null;

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

}
