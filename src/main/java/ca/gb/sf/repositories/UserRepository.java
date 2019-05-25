package ca.gb.sf.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.gb.sf.models.EducatorEntity;
import ca.gb.sf.models.StudentEntity;
import ca.gb.sf.models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);
	
	UserEntity findByDisplayName(String displayName);
	
	@Query("SELECT u FROM UserEntity u WHERE u.educator = :educator")
	Page<UserEntity> findByEducator(Pageable pageable, @Param("educator") EducatorEntity educator);

}
