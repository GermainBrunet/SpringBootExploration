package ca.gb.sf.web.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ca.gb.sf.models.Educator;
import ca.gb.sf.models.Role;
import ca.gb.sf.models.User;
import ca.gb.sf.repositories.UserRepository;
import ca.gb.sf.web.form.UserRegistrationForm;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findByDisplayName(String displayName) {
		return userRepository.findByDisplayName(displayName);
	}
	
	public User save(UserRegistrationForm registration) {

		System.out.println("user type : " + registration.getUserType());

		User user = null;
		
		if (registration.getUserType().equals("USER")) {

			user = new User(registration.getDisplayName(), registration.getEmail(), passwordEncoder.encode(registration.getPassword()));

		} else if (registration.getUserType().equals("EDUCATOR")) {

			user = new Educator(registration.getDisplayName(), registration.getEmail(), passwordEncoder.encode(registration.getPassword()));
		}

		user.setRoles(Arrays.asList(new Role("ROLE_USER")));

		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String displayName) throws UsernameNotFoundException {
		// User user = userRepository.findByEmail(email);
		User user = userRepository.findByDisplayName(displayName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
