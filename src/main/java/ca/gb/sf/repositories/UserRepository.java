package ca.gb.sf.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ca.gb.sf.models.Educator;
import ca.gb.sf.models.Student;
import ca.gb.sf.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	User findByDisplayName(String displayName);
	
	@Query("SELECT s FROM Student s WHERE s.educator = :educator")
	Page<Student> findByEducator(Pageable pageable, Educator educator);

}
