package ca.gb.sf.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.KeywordEntity;
import ca.gb.sf.models.LevelEntity;

/**
 * The Exercise Group Repository should allow the following searches:
 * <li>By ID (covered by default)
 * <li>By Name
 * <li>By Author
 * <li>By Keyword
 * <li>By Source
 * <li>By Level
 * <li>By Search String (any word in the name, description, author, keyword, source,
 * and level)
 * <li>By Student (via the assignment object)
 * <li>All
 */

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "exerciseGroup", path = "exerciseGroup")
public interface ExerciseGroupRepository extends CrudRepository<ExerciseGroupEntity, Long> {

	// Find by Name
	@Query(value = "SELECT eg " 
			+ "     FROM ExerciseGroupEntity eg " 
			+ "     WHERE "
			+ "       lower(eg.name) LIKE :searchString")
	ExerciseGroupEntity findByName(@Param("searchString") String searchString);

	@Query(value = "SELECT eg " 
			+ "     FROM ExerciseGroupEntity eg " 
			+ "     WHERE "
			+ "       lower(eg.name) LIKE :searchString")
	Page<ExerciseGroupEntity> searchByName(Pageable pageable, @Param("searchString") String searchString);

	// Find by Author
	@Query(value = "SELECT eg " 
			+ "     FROM ExerciseGroupEntity eg " 
			+ "     WHERE "
			+ "       lower(eg.author) LIKE :author")
	List<ExerciseGroupEntity> findByAuthor(@Param("author") String author);

	@Query(value = "SELECT eg " 
			+ "     FROM ExerciseGroupEntity eg " 
			+ "     WHERE "
			+ "       lower(eg.author) LIKE :author")
	Page<ExerciseGroupEntity> findByAuthor(Pageable pageable, @Param("author") String author);
	
	// Find by keyword
	List<ExerciseGroupEntity> findByKeywords(@Param("keywordEntity") KeywordEntity keywordEntity);

	Page<ExerciseGroupEntity> findByKeywords(Pageable pageable, @Param("keywordEntity") KeywordEntity keywordEntity);

	// Find by Source
	@Query(value = "SELECT eg " 
			+ "     FROM ExerciseGroupEntity eg " 
			+ "     WHERE "
			+ "       lower(eg.source) LIKE :source")
	List<ExerciseGroupEntity> findBySource(@Param("source") String source);

	@Query(value = "SELECT eg " 
			+ "     FROM ExerciseGroupEntity eg " 
			+ "     WHERE "
			+ "       lower(eg.source) LIKE :source")
	Page<ExerciseGroupEntity> findBySource(Pageable pageable, @Param("source") String source);
	
	// Find by Level
	List<ExerciseGroupEntity> findByLevel(@Param("levelEntity") LevelEntity levelEntity);

	Page<ExerciseGroupEntity> findByLevel(Pageable pageable, @Param("levelEntity") LevelEntity levelEntity);
	
	// Find by Keyword (Search)
    @Query(value = "SELECT eg "
    		+ "     FROM ExerciseGroupEntity eg "
    		+ "     JOIN eg.keywords k "
    		+ "     JOIN eg.level l "
    		+ "     WHERE "
    		+ "       lower(eg.name) LIKE :searchString OR "
    		+ "       lower(eg.author) LIKE :searchString OR "
    		+ "       lower(eg.description) LIKE :searchString OR "
    		+ "       lower(eg.source) LIKE :searchString OR "
    		+ "       lower(k.keyword) LIKE :searchString OR "
    		+ "       lower(l.name) LIKE :searchString ")
    List<ExerciseGroupEntity> findBySearchString(@Param("searchString") String searchString);

    @Query(value = "SELECT eg "
    		+ "     FROM ExerciseGroupEntity eg "
    		+ "     JOIN eg.keywords k "
    		+ "     JOIN eg.level l "
    		+ "     WHERE "
    		+ "       lower(eg.name) LIKE :searchString OR "
    		+ "       lower(eg.author) LIKE :searchString OR "
    		+ "       lower(eg.description) LIKE :searchString OR "
    		+ "       lower(eg.source) LIKE :searchString OR "
    		+ "       lower(k.keyword) LIKE :searchString OR "
    		+ "       lower(l.name) LIKE :searchString ")
    Page<ExerciseGroupEntity> findBySearchString(Pageable pageable, @Param("searchString") String searchString);
	
	// Find by Student
	@Query(value = "SELECT * " 
				+ "     FROM exercise_groups eg "
				+ "     INNER JOIN Assignments a ON eg.id = a.exercise_group_id " 
				+ "     WHERE "
				+ "       a.user_id = :userId", nativeQuery = true)
	Page<ExerciseGroupEntity> findByStudentId(@Param("userId") Long userId, Pageable pageable);

	@Query(value = "SELECT * " 
				+ "     FROM exercise_groups eg "
				+ "     INNER JOIN Assignments a ON eg.id = a.exercise_group_id " 
				+ "     WHERE "
				+ "       a.user_id = :userId AND " 
				+ "       lower(eg.name) LIKE :searchString", nativeQuery = true)
	Page<ExerciseGroupEntity> findByStudentIdAndName(@Param("userId") Long userId, Pageable pageable,
			@Param("searchString") String searchString);


	// Find All
	Page<ExerciseGroupEntity> findAll(Pageable pageable);
	
}
