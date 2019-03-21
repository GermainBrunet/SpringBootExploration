package ca.gb.sf.repositories;

import java.util.List;

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
	List<Student> findByEducator(Educator educator);
	
}
