package ca.gb.sf.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import ca.gb.sf.H2IntegrationTest;
import ca.gb.sf.models.EducatorEntity;
import ca.gb.sf.models.RoleEntity;
import ca.gb.sf.models.StudentEntity;
import ca.gb.sf.models.UserEntity;
import ca.gb.sf.repositories.RoleRepository;
import ca.gb.sf.repositories.UserRepository;
import ca.gb.sf.services.RoleService;
import ca.gb.sf.services.UserService;

public abstract class CommonServiceTest  extends H2IntegrationTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
		
		String educatorName = "educator1";
		
		//UserEntity educatorCheck = userService.findByDisplayName(educatorName);
		//EducatorEntity educator = null;
		
		//if (educatorCheck == null) {
		EducatorEntity educator = (EducatorEntity) userService.save(new EducatorEntity("educator1", "email0", "password"));
		//	userService.save(educator);
		//}

		userService.save(new StudentEntity("student1", "email11", "password", educator));
		userService.save(new StudentEntity("student2", "email21", "password", educator));
		userService.save(new StudentEntity("student3", "email31", "password", educator));

		//		String studentName1 = "student1";
//		UserEntity studentCheck1 = userService.findByDisplayName(studentName1);
//		
//		if (studentCheck1 == null) {
//			StudentEntity student1 = new StudentEntity("student1", "email1", "password", educator);
//			userService.save(student1);
//		}
//		
//		String studentName2 = "student2";
//		UserEntity studentCheck2 = userRepository.findByDisplayName(studentName2);
//		
//		if (studentCheck2 == null) {
//			StudentEntity student2 = new StudentEntity("student2", "email2", "password", educator);
//			userRepository.save(student2);
//		}
//		
//		String studentName3 = "student3";
//		UserEntity studentCheck3 = userRepository.findByDisplayName(studentName3);
//		
//		if (studentCheck3 == null) {
//			StudentEntity student3 = new StudentEntity("student3", "email3", "password", educator);
//			userRepository.save(student3);
//		}
		
		roleService.save("ROLE_USER");
		roleService.save("ROLE_EDUCATOR");
		
		
		
		Authentication authentication = Mockito.mock(Authentication.class);
		Mockito.when(authentication.getName()).thenReturn("educator1");
		
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		
		SecurityContextHolder.setContext(securityContext);
		Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(educator);
		
	}
	
}
