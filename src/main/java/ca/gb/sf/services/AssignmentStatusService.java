package ca.gb.sf.services;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.gb.sf.models.AssignmentStatusEntity;
import ca.gb.sf.repositories.AssignmentStatusRepository;

/**
 * Class that provides service level functionality for the Role Entity.
 */

@Component
public class AssignmentStatusService extends CommonService implements CommonServiceInterface<AssignmentStatusEntity> {

	@Autowired
	AssignmentStatusRepository assignmentStatusRepository;

	/**
	 * Find the assignment status using the code.
	 * 
	 * @param roleName
	 * 
	 * @return
	 */
	@Override
	public AssignmentStatusEntity find(String assignmentStatusCode) {

		return assignmentStatusRepository.findByCode(assignmentStatusCode);

	}
	
	
	
	public AssignmentStatusEntity find(AssignmentStatusEntity assignmentStatus) {

		return find(assignmentStatus.getCode());

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
	public AssignmentStatusEntity save(String code) {

		AssignmentStatusEntity assignmentStatusEntity = find(code);

		if (assignmentStatusEntity != null) {
			
			return assignmentStatusEntity;
			
		}

		assignmentStatusEntity = new AssignmentStatusEntity(code);
		
		return save(assignmentStatusEntity);

	}
	
	public AssignmentStatusEntity save(String code, String nameFrench, String nameEnglish) {

		AssignmentStatusEntity assignmentStatusEntity = find(code);

		if (assignmentStatusEntity != null) {
			
			return assignmentStatusEntity;
			
		}

		assignmentStatusEntity = new AssignmentStatusEntity(code, nameFrench, nameEnglish);
		
		return save(assignmentStatusEntity);

	}

	/**
	 * Persist the role entity.
	 */
	@Override
	public AssignmentStatusEntity save(AssignmentStatusEntity t) {

		setAuditingFields(t);

		AssignmentStatusEntity savedAssignmentStatusEntity = assignmentStatusRepository.save(t);

		return savedAssignmentStatusEntity;

	}

	/**
	 * Returns the role entity given an id. Will return a null value when the id
	 * is null or the repository can't find the object to return.
	 */
	@Override
	public AssignmentStatusEntity findById(Long id) {

		if (id == null) {

			return null;

		}

		Optional<AssignmentStatusEntity> optionalAssignmentStatusEntity = assignmentStatusRepository.findById(id);

		if (optionalAssignmentStatusEntity == null) {

			return null;

		}

		return optionalAssignmentStatusEntity.get();

	}
	
	public AssignmentStatusEntity findByCode(String code) {
		
		if (StringUtils.isEmpty(code)) {
			
			return null;
			
		}
		
		AssignmentStatusEntity assignmentStatusEntity = assignmentStatusRepository.findByCode(code);
		
		return assignmentStatusEntity;
		
	}

	/**
	 * Returns a count of entities.
	 */
	@Override
	public long count() {

		return assignmentStatusRepository.count();

	}

	/**
	 * Removes all these entities from the database.
	 */
	@Override
	public void deleteAll() {

		assignmentStatusRepository.deleteAll();

	}
	
	/**
	 * Given an id, removes this entity. 
	 */
	public void deleteById(Long id) {
		
		assignmentStatusRepository.deleteById(id);
		
	}

}
