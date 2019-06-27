package ca.gb.sf.repositories;

import java.util.List;

import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.KeywordEntity;
import ca.gb.sf.models.LevelEntity;

public interface ExerciseGroupRepositoryCustom {

	List<ExerciseGroupEntity> findByLevelKeywordsAndName(String name, LevelEntity level, List<KeywordEntity> keywords);
	
}
