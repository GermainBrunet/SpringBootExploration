package ca.gb.sf.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.ExerciseGroupEntity_;
import ca.gb.sf.models.KeywordEntity;
import ca.gb.sf.models.LevelEntity;

public class ExerciseGroupRepositoryCustomImpl implements ExerciseGroupRepositoryCustom {

	private static final String PERCENTAGE = "%";
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<ExerciseGroupEntity> findByLevelKeywordsAndName(String name, LevelEntity level,
			List<KeywordEntity> keywords) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ExerciseGroupEntity> query = cb.createQuery(ExerciseGroupEntity.class);
		Root<ExerciseGroupEntity> exerciseGroup = query.from(ExerciseGroupEntity.class);
		// ListJoin<ExerciseGroupEntity, KeywordEntity> keywordJoin = exerciseGroup.join(ExerciseGroupEntity_.keywords);
				
		Path<LevelEntity> levelPath = exerciseGroup.get(ExerciseGroupEntity_.level);
		// Path<KeywordEntity> keywordPath = exerciseGroup.get("keywords");
		// Path<String> searchPath = exerciseGroup.get("name");
		// Expression<KeywordEntity> keywordExpression = exerciseGroup.get("keywords");
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (level != null) {
			predicates.add(cb.equal(levelPath, level));
		}
		
		if (keywords != null && keywords.size() != 0) {
			predicates.add(exerciseGroup.join(ExerciseGroupEntity_.keywords).in(keywords));
			
		}
		
		if (StringUtils.isNotEmpty(name)) {
			
			StringBuilder sb = new StringBuilder();
			sb.append(PERCENTAGE);
			sb.append(name.toLowerCase());
			sb.append(PERCENTAGE);
			
			predicates.add(cb.like(cb.lower(exerciseGroup.get(ExerciseGroupEntity_.name)), sb.toString()));
		}
		
		query.select(exerciseGroup).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return entityManager.createQuery(query).getResultList();
		
		// Page Stuff here: https://stackoverflow.com/questions/4580285/jpa2-case-insensitive-like-matching-anywhere
	}

}
