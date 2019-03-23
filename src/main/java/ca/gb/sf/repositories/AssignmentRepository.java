package ca.gb.sf.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.gb.sf.models.Assignment;
import ca.gb.sf.models.Student;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "assignment", path = "assignment")
public interface AssignmentRepository extends CrudRepository<Assignment, Long> {
    
	@Query("SELECT a FROM Assignment a WHERE a.student = :student")
	Page<Assignment> findByStudent(Pageable pageable, Student student);
	
}
