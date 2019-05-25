package ca.gb.sf.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import ca.gb.sf.exceptions.ApplicationRuntimeException;
import ca.gb.sf.models.EducatorEntity;
import ca.gb.sf.models.UserEntity;
import ca.gb.sf.repositories.UserRepository;

@Component
public class EducatorService extends CommonService {

	@Autowired
	private UserRepository userRepository;
	
	public EducatorEntity getCurrentEducator() {
		
		UserEntity userEntity = getCurrentUserEntity();
		
		System.out.println("UserEntity: " + userEntity);
		
		if (userEntity instanceof EducatorEntity) {
			
			EducatorEntity educatorEntity = (EducatorEntity) userEntity;
			
			return educatorEntity;
			
		}
		
		throw new ApplicationRuntimeException("TODO: User is not an educator");
		
	}
	
	public Page<UserEntity> studentsByEducator(Pageable pageable) {
		
    	Page<UserEntity> myStudents = userRepository.findByEducator(pageable, getCurrentEducator());
    	
    	return myStudents;
		
	}
	
}
