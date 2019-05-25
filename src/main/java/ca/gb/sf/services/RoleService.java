package ca.gb.sf.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.gb.sf.models.RoleEntity;
import ca.gb.sf.repositories.RoleRepository;

@Component
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public RoleEntity find(String roleName) {
		
		return roleRepository.findByName(roleName);
		
	}
	
	public RoleEntity save(String roleName) {
		
		RoleEntity roleEntity = find(roleName); 
		
		if (roleEntity == null) {
			
			roleEntity = roleRepository.save(new RoleEntity(roleName));
			
		}
		
		return roleEntity;
		
	}
	
}
