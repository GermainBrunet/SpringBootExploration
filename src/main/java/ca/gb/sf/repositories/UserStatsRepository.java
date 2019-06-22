package ca.gb.sf.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ca.gb.sf.models.UserStats;


public interface UserStatsRepository extends JpaRepository<UserStats, Long> {

	@Query(nativeQuery = true, name = "reportEducatorWithCountQuery")
	List<UserStats> reportEducatorWithCountList(@Param("educatorId") long educatorId);

	@Query(nativeQuery = true, name = "reportEducatorWithCountQuery")
	Page<UserStats> reportEducatorWithCountPage(Pageable pageable, @Param("educatorId") long educatorId);

}
