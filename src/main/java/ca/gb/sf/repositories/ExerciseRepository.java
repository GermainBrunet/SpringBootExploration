package ca.gb.sf.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.gb.sf.models.Exercise;
import ca.gb.sf.models.ExerciseGroup;
import ca.gb.sf.models.Student;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "exercise", path = "exercise")
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

    Page<Exercise> findAll(Pageable pageable);
    
    @Query(value = "SELECT e "
    		+ "     FROM Exercise e "
    		+ "     WHERE "
    		+ "       lower(e.initialWord) LIKE :searchString OR "
    		+ "       lower(e.targetWord) LIKE :searchString OR "
    		+ "       lower(e.instructions) LIKE :searchString")
    Page<Exercise> searchByName(Pageable pageable, @Param("searchString") String searchString);

    /**
    @Query(value = "SELECT e "
    		+ "     FROM Exercise e "
    		+ "     INNER JOIN e.exerciseGroup eg "
    		+ "     INNER JOIN eg.assignment a "
    		+ "     WHERE "
    		+ "       a.student = :student AND"
    		+ "       a.exerciseGropu = :exerciseGroup")
    List<Exercise> findExercisesByStudentAndExerciseGroup(@Param("student") Student student, @Param("exerciseGroup") ExerciseGroup exerciseGroup);
    **/
   
    
}
