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
import ca.gb.sf.models.UserEntity;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "assignment", path = "assignment")
public interface AssignmentRepository extends CrudRepository<AssignmentEntity, Long> {
    
	@Query("SELECT a "
			+ "FROM "
			+ "  AssignmentEntity a "
			+ "WHERE "
			+ "  a.user = :userEntity")
	List<AssignmentEntity> findByStudent(@Param("userEntity") UserEntity userEntity);
	
	@Query("SELECT a "
			+ "FROM "
			+ "  AssignmentEntity a "
			+ "WHERE "
			+ "  a.user = :userEntity")
	Page<AssignmentEntity> findByStudent(Pageable pageable, @Param("userEntity") UserEntity userEntity);

	@Query("SELECT a "
			+ "FROM "
			+ "  AssignmentEntity a "
			+ "WHERE "
			+ "  a.user = :userEntity AND "
			+ "  a.exerciseGroup = :exerciseGroup")
	AssignmentEntity findByUserAndExerciseGroup(@Param("userEntity") UserEntity userEntity, @Param("exerciseGroup") ExerciseGroupEntity exerciseGroup);

	@Query("SELECT a "
			+ "FROM "
			+ "  AssignmentEntity a "
			+ "WHERE "
			+ "  a.user = :userEntity AND "
			+ "  a.id = :assignmentId")
	AssignmentEntity findByUserAndAssignmentId(@Param("userEntity") UserEntity userEntity, @Param("assignmentId") Long assignmentId);

}
