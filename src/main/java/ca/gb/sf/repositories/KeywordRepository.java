package ca.gb.sf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.gb.sf.models.KeywordEntity;

@Repository
public interface KeywordRepository extends JpaRepository<KeywordEntity, Long> {
	
	KeywordEntity findByKeyword(String keyword);

	@Query("Select k from KeywordEntity k where k.keyword like %:partialKeyword%")
	List<KeywordEntity> findByKeywordContaining(@Param("partialKeyword") String partialKeyword);
	
}
