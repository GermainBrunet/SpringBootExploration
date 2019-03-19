package ca.gb.sf.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ca.gb.sf.models.User;
import ca.gb.sf.web.form.UserRegistrationForm;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationForm registration);
    
}
