package ca.gb.sf.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ca.gb.sf.models.Exercise;
import ca.gb.sf.repositories.ExerciseRepository;

@Service
public class ExerciseService {

	@Autowired
	ExerciseRepository exerciseRepository;
	
	private static final String PERCENTAGE = "%";
	
	public Page<Exercise> findPaginated(Pageable pageable, String searchString) {

		Page<Exercise> exercises = null;
		
		if (StringUtils.isEmpty(searchString)) {
			
			exercises = exerciseRepository.findAll(pageable);
			
		} else {

			// TODO - verify search string
			
			StringBuilder sb = new StringBuilder();
			sb.append(PERCENTAGE);
			sb.append(searchString.toLowerCase());
			sb.append(PERCENTAGE);
			
			exercises = exerciseRepository.searchByName(pageable, sb.toString());
			
		}
		
		return exercises;
		
	}	
		
}
