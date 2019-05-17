package ca.gb.sf.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.gb.sf.models.AssignmentEntity;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.StudentEntity;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "assignment", path = "assignment")
public interface AssignmentRepository extends CrudRepository<AssignmentEntity, Long> {
    
	@Query("SELECT a FROM AssignmentEntity a WHERE a.student = :student")
	Page<AssignmentEntity> findByStudent(Pageable pageable, @Param("student") StudentEntity student);

	@Query("SELECT a FROM AssignmentEntity a WHERE a.student = :student")
	List<AssignmentEntity> findListByStudent(@Param("student") StudentEntity student);
	
	@Query("SELECT a FROM AssignmentEntity a WHERE a.student = :student and a.exerciseGroup = :exerciseGroup")
	AssignmentEntity findByStudentAndExerciseGroup(@Param("student") StudentEntity student, @Param("exerciseGroup") ExerciseGroupEntity exerciseGroup);

	@Query("SELECT a FROM AssignmentEntity a WHERE a.student = :student and a.id = :assignmentId")
	AssignmentEntity findByStudentAndAssignmentId(@Param("student") StudentEntity student, @Param("assignmentId") Long assignmentId);

}
