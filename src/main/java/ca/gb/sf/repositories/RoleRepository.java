package ca.gb.sf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ca.gb.sf.models.Educator;
import ca.gb.sf.models.Role;
import ca.gb.sf.models.Student;
import ca.gb.sf.models.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByName(String name);
	
}
