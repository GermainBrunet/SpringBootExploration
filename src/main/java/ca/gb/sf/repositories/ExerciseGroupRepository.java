package ca.gb.sf.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.StudentEntity;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "exerciseGroup", path = "exerciseGroup")
public interface ExerciseGroupRepository extends CrudRepository<ExerciseGroupEntity, Long> {

	ExerciseGroupEntity findByName(String name);

//	@Query(value = "SELECT * "
//			+ "     FROM exercise_groups eg "
//			+ "     INNER JOIN exercises e ON eg.id = e.exercise_group_id "
//			+ "     WHERE eg.id = :exerciseGroupEntityId", nativeQuery = true)
//	ExerciseGroupEntity initialize(@Param("exerciseGroupEntityId") Long exerciseGroupEntityId);
	
//	@Query(value = "SELECT eg "
//			+ "     FROM ExerciseGroupEntity eg "
//			+ "     JOIN FETCH eg.exercises "
//			+ "     WHERE eg.id = :exerciseGroupEntityId")
//	ExerciseGroupEntity initialize2(@Param("exerciseGroupEntityId") Long exerciseGroupEntityId);
	
    Page<ExerciseGroupEntity> findAll(Pageable pageable);
    
    @Query(value = "SELECT eg "
    		+ "     FROM ExerciseGroupEntity eg "
    		+ "     WHERE "
    		+ "       lower(eg.name) LIKE :searchString")
    Page<ExerciseGroupEntity> searchByName(Pageable pageable, @Param("searchString") String searchString);

    @Query(value = "SELECT * "
    		+ "     FROM exercise_groups eg "
    		+ "     INNER JOIN Assignments a ON eg.id = a.exercise_group_id "
    		+ "     WHERE "
    		+ "       a.student_id = :studentId", nativeQuery = true)
    Page<ExerciseGroupEntity> findByStudentId(@Param("studentId") Long studentId, Pageable pageable);

    @Query(value = "SELECT * "
    		+ "     FROM exercise_groups eg "
    		+ "     INNER JOIN Assignments a ON eg.id = a.exercise_group_id "
    		+ "     WHERE "
    		+ "       a.student_id = :studentId AND "
    		+ "       lower(eg.name) LIKE :searchString", nativeQuery = true)
    Page<ExerciseGroupEntity> findByStudentIdAndName(@Param("studentId") Long studentId, Pageable pageable, @Param("searchString") String searchString);
    
}
