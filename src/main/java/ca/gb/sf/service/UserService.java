package ca.gb.sf.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ca.gb.sf.dto.UserRegistrationDTO;
import ca.gb.sf.models.User;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDTO registration);
}
