package ca.gb.sf.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.gb.sf.models.ExerciseEntity;
import ca.gb.sf.models.ExerciseGroupEntity;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "exercise", path = "exercise")
public interface ExerciseRepository extends CrudRepository<ExerciseEntity, Long> {

    Page<ExerciseEntity> findAll(Pageable pageable);
    
    @Query(value = "SELECT e "
    		+ "     FROM ExerciseEntity e "
    		+ "     WHERE "
    		+ "       lower(e.initialWord) LIKE :searchString OR "
    		+ "       lower(e.targetWord) LIKE :searchString OR "
    		+ "       lower(e.instructions) LIKE :searchString")
    Page<ExerciseEntity> searchByName(Pageable pageable, @Param("searchString") String searchString);

    @Query(value = "SELECT e "
    		+ "     FROM ExerciseEntity e "
    		+ "     WHERE "
    		+ "       e.exerciseGroup = :exerciseGroup "
    		+ "     ORDER BY "
    		+ "       e.exerciseOrder ")
    List<ExerciseEntity> findByExerciseGroup(@Param("exerciseGroup") ExerciseGroupEntity exerciseGroup);

    
}
