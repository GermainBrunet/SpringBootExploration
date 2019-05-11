package ca.gb.sf.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.gb.sf.models.ExerciseGroup;
import ca.gb.sf.models.Student;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "exerciseGroup", path = "exerciseGroup")
public interface ExerciseGroupRepository extends CrudRepository<ExerciseGroup, Long> {

	ExerciseGroup findByName(String name);
	
    Page<ExerciseGroup> findAll(Pageable pageable);
    
    @Query(value = "SELECT eg "
    		+ "     FROM ExerciseGroup eg "
    		+ "     WHERE "
    		+ "       lower(eg.name) LIKE :searchString")
    Page<ExerciseGroup> searchByName(Pageable pageable, @Param("searchString") String searchString);

    @Query(value = "SELECT * "
    		+ "     FROM exercise_groups eg "
    		+ "     INNER JOIN Assignments a ON eg.id = a.exercise_group_id "
    		+ "     WHERE "
    		+ "       a.student_id = :studentId", nativeQuery = true)
    Page<ExerciseGroup> findByStudentId(@Param("studentId") Long studentId, Pageable pageable);

    @Query(value = "SELECT * "
    		+ "     FROM exercise_groups eg "
    		+ "     INNER JOIN Assignments a ON eg.id = a.exercise_group_id "
    		+ "     WHERE "
    		+ "       a.student_id = :studentId AND "
    		+ "       lower(eg.name) LIKE :searchString", nativeQuery = true)
    Page<ExerciseGroup> findByStudentIdAndName(@Param("studentId") Long studentId, Pageable pageable, @Param("searchString") String searchString);
}
