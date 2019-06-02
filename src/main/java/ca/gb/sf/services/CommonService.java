package ca.gb.sf.services;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ca.gb.sf.models.PersistentObject;
import ca.gb.sf.models.UserEntity;
import ca.gb.sf.repositories.UserRepository;

/**
 * Class that provides common services to other service classes. These services
 * involve identifying the current logged in use and audit field persistence.
 */

public abstract class CommonService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Function that returns the current logged in user name.
	 * 
	 * @return
	 */
	public String getCurrentUserName() {

		String userName = SecurityContextHolder.getContext().getAuthentication().getName();

		return userName;

	}

	/**
	 * Function that returns the current logged in user.
	 * 
	 * @return
	 */
	public UserEntity getCurrentUserEntity() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

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
	 * Set fields used to audit changes to the object. Will set the create
	 * fields when these fields are empty. Otherwise will set and/or update the
	 * update fields.
	 * 
	 * @param persistentObject
	 */
	public void setAuditingFields(PersistentObject persistentObject) {

		if (persistentObject.getCreateUser() == null) {

			// Sets the fields for a new object.
			persistentObject.setCreateTimestamp(now());
			persistentObject.setCreateUser(getCurrentUserEntity());

		} else {

			// Sets the fields for an object update.
			persistentObject.setUpdateTimestamp(now());
			persistentObject.setUpdateUser(getCurrentUserEntity());

		}

	}

}
