package ca.gb.sf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.gb.sf.models.LevelEntity;

@Repository
public interface LevelRepository extends JpaRepository<LevelEntity, Long> {
	
	LevelEntity findByName(String name);

	@Query("Select l from LevelEntity l where l.name like %:partialKeyword%")
	List<LevelEntity> findByKeywordContaining(@Param("partialKeyword") String partialKeyword);
	
}
