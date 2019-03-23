package ca.gb.sf.web.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ca.gb.sf.models.Role;
import ca.gb.sf.models.Student;
import ca.gb.sf.models.User;
import ca.gb.sf.web.form.StudentForm;
import ca.gb.sf.web.form.UserRegistrationForm;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User findByDisplayName(String displayName);

    User save(UserRegistrationForm registration);
    
    Role findRoleByName(String name);
    
    Student saveStudent(StudentForm studentForm);
    
}
