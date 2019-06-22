package ca.gb.sf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.gb.sf.models.AssignmentStatusEntity;

@Repository
public interface AssignmentStatusRepository extends JpaRepository<AssignmentStatusEntity, Long> {
	
	AssignmentStatusEntity findByCode(String code);
	
}
