package ca.gb.sf.web.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ca.gb.sf.models.RoleEntity;
import ca.gb.sf.models.StudentEntity;
import ca.gb.sf.models.UserEntity;
import ca.gb.sf.web.form.ExerciseGroupSelectionForm;
import ca.gb.sf.web.form.StudentForm;
import ca.gb.sf.web.form.UserRegistrationForm;

public interface UserService extends UserDetailsService {

    UserEntity findByEmail(String email);

    UserEntity findByDisplayName(String displayName);

    UserEntity save(UserRegistrationForm registration);
    
    RoleEntity findRoleByName(String name);
    
    StudentEntity saveStudent(StudentForm studentForm);
    
    void saveSelectedExercises(ExerciseGroupSelectionForm exerciseSelectionForm);
    
}
