package ca.gb.sf.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.gb.sf.models.RoleEntity;
import ca.gb.sf.models.UserRole;
import ca.gb.sf.repositories.RoleRepository;

/**
 * Class that provides service level functionality for the Role Entity.
 */

@Component
public class RoleService extends CommonService implements CommonServiceInterface<RoleEntity> {

	@Autowired
	RoleRepository roleRepository;

	/**
	 * Find the role entity using the role name.
	 * 
	 * @param roleName
	 * 
	 * @return
	 */
	@Override
	public RoleEntity find(String roleName) {

		return roleRepository.findByName(roleName);

	}
	
	
	
	public RoleEntity find(UserRole userRole) {

		return find(userRole.getSecurityRoleName());

	}
	

	/**
	 * Persist the entity using an identifier String. Will first check to see if
	 * the entity already exists and will return it if it does. If the entity
	 * does not exists, will create a new entity and persist it.
	 * 
	 * @param roleName
	 * 
	 * @return
	 */
	@Override
	public RoleEntity save(String roleName) {

		RoleEntity roleEntity = find(roleName);

		if (roleEntity != null) {
			
			return roleEntity;
			
		}

		roleEntity = new RoleEntity(roleName);
		
		return save(roleEntity);

	}

	/**
	 * Persist the role entity.
	 */
	@Override
	public RoleEntity save(RoleEntity t) {

		setAuditingFields(t);

		RoleEntity savedRoleEntity = roleRepository.save(t);

		return savedRoleEntity;

	}

	/**
	 * Returns the role entity given an id. Will return a null value when the id
	 * is null or the repository can't find the object to return.
	 */
	@Override
	public RoleEntity findById(Long id) {

		if (id == null) {

			return null;

		}

		Optional<RoleEntity> optionalRoleEntity = roleRepository.findById(id);

		if (optionalRoleEntity == null) {

			return null;

		}

		return optionalRoleEntity.get();

	}

	/**
	 * Returns a count of entities.
	 */
	@Override
	public long count() {

		return roleRepository.count();

	}

	/**
	 * Removes all these entities from the database.
	 */
	@Override
	public void deleteAll() {

		roleRepository.deleteAll();

	}
	
	/**
	 * Given an id, removes this entity. 
	 */
	public void deleteById(Long id) {
		
		roleRepository.deleteById(id);
		
	}

}
