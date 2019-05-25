package ca.gb.sf.services;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ca.gb.sf.models.EducatorEntity;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.PersistentObject;
import ca.gb.sf.models.UserEntity;
import ca.gb.sf.repositories.UserRepository;

public abstract class CommonService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Function that returns the current user name.
	 * 
	 * @return
	 */
	public String getCurrentUserName() {

		String userName = SecurityContextHolder.getContext().getAuthentication().getName();

		return userName;

	}

	/**
	 * Function that returns the current user.  
	 * @return
	 */
	public UserEntity getCurrentUserEntity() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println("authentication name: " + auth.getName());

		UserEntity userEntity = (UserEntity) userRepository.findByDisplayName(auth.getName());

		return userEntity;

	}

	/**
	 * Function that returns the current date and time. Used to set the create
	 * and update date when persisting to the database.
	 * 
	 * @return current date time
	 */
	public Timestamp now() {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		return timestamp;

	}

	/**
	 * Set fields used to audit changes to the object.
	 * 
	 * @param persistentObject
	 */
	public void setAuditingFields(PersistentObject persistentObject) {
		
		if (persistentObject.getCreateUser() == null) {
			
			persistentObject.setCreateTimestamp(now());
			persistentObject.setCreateUser(getCurrentUserEntity());
			
		} else {
			
			persistentObject.setUpdateTimestamp(now());
			persistentObject.setUpdateUser(getCurrentUserEntity());
			
		}
		
	}
	

}
