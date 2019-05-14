package ca.gb.sf.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.gb.sf.models.Assignment;
import ca.gb.sf.models.ExerciseGroup;
import ca.gb.sf.models.Student;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "assignment", path = "assignment")
public interface AssignmentRepository extends CrudRepository<Assignment, Long> {
    
	@Query("SELECT a FROM Assignment a WHERE a.student = :student")
	Page<Assignment> findByStudent(Pageable pageable, @Param("student") Student student);

	@Query("SELECT a FROM Assignment a WHERE a.student = :student")
	List<Assignment> findListByStudent(@Param("student") Student student);
	
	@Query("SELECT a FROM Assignment a WHERE a.student = :student and a.exerciseGroup = :exerciseGroup")
	Assignment findByStudentAndExerciseGroup(@Param("student") Student student, @Param("exerciseGroup") ExerciseGroup exerciseGroup);

	@Query("SELECT a FROM Assignment a WHERE a.student = :student and a.id = :assignmentId")
	Assignment findByStudentAndAssignmentId(@Param("student") Student student, @Param("assignmentId") Long assignmentId);

}
