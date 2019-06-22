package ca.gb.sf.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import ca.gb.sf.models.UserEntity;
import ca.gb.sf.models.UserStats;
import ca.gb.sf.repositories.UserStatsRepository;

@Component
public class UserStatsService extends CommonService {

	@Autowired
	UserStatsRepository userStatsRepository;
	
	public List<UserStats> reportEducatorWithCountList() {
	
		UserEntity userEntity = getCurrentUserEntity();
		
		if (userEntity == null) {
			
			return null;
			
		}
			
		return userStatsRepository.reportEducatorWithCountList(userEntity.getId());
			
	}
	
	public Page<UserStats> findPaginated(Pageable pageable, String searchString) {

		UserEntity userEntity = getCurrentUserEntity();

		if (userEntity == null) {
			
			return null;
			
		}

		return userStatsRepository.reportEducatorWithCountPage(pageable, userEntity.getId());

	}
	
	
	
}
