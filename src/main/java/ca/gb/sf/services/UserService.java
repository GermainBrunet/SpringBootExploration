package ca.gb.sf.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import ca.gb.sf.exceptions.ApplicationRuntimeException;
import ca.gb.sf.models.AssignmentEntity;
import ca.gb.sf.models.EducatorEntity;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.RoleEntity;
import ca.gb.sf.models.StudentEntity;
import ca.gb.sf.models.UserEntity;
import ca.gb.sf.models.UserRole;
import ca.gb.sf.repositories.ExerciseGroupRepository;
import ca.gb.sf.repositories.UserRepository;
import ca.gb.sf.web.form.ExerciseGroupSelectionForm;
import ca.gb.sf.web.form.StudentForm;
import ca.gb.sf.web.form.UserRegistrationForm;

@Component
public class UserService extends CommonService implements CommonServiceInterface<UserEntity>, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ExerciseGroupRepository exerciseGroupRepository;

	@Autowired
	private AssignmentService assignmentService;

	public UserEntity getAdminUser() {

		UserEntity userEntity = find("Admin");

		if (userEntity != null) {

			return userEntity;

		}

		userEntity = new UserEntity();
		userEntity.setDisplayName("Admin");
		userEntity.setEmail("admin@admin.admin");
		userEntity.setPassword("TODO-ChangeMe-Password");
		userEntity.setCreateUser(userEntity);
		userEntity.setCreateTimestamp(now());

		// RoleEntity adminRole = roleService.find(UserRole.Admin);
		// Set<RoleEntity> adminRoles = new TreeSet<RoleEntity>();
		// adminRoles.add(adminRole);
		// userEntity.setRoles(adminRoles);

		// Save the first user using the repository to avoid the automatic setting of
		// fields.
		return userRepository.save(userEntity);

	}

	@Override
	public long count() {

		return userRepository.count();

	}

	public void deleteByUserType(String userType) {
		
		List<UserEntity> studentEntities = userRepository.findByUserType(userType);
		
		for (UserEntity userEntity : studentEntities) {
			
			Optional<UserEntity> optionalUserEntity = userRepository.findById(userEntity.getId());
			
			if (optionalUserEntity != null) {
			
				UserEntity foundUserEntity = optionalUserEntity.get();

				if (foundUserEntity != null) {
					
					deleteById(foundUserEntity.getId());
					
				}
			
			}
			
		}

		userRepository.flush();
		
	}
	
	@Override
	public void deleteAll() {
		
		deleteByUserType("Student");
		deleteByUserType("Educator");
		deleteByUserType("UserEntity");

	}

	@Override
	public void deleteById(Long id) {

		if (id == null) {

			return;

		}

		userRepository.deleteById(id);

	}

	public List<UserEntity> findAll() {

		return userRepository.findAll();

	}

	public UserEntity findByEmail(String email) {

		if (email == null) {

			return null;

		}

		return userRepository.findByEmail(email);

	}

	@Override
	public UserEntity findById(Long id) {

		if (id == null) {

			return null;

		}

		Optional<UserEntity> optionalUserEntity = userRepository.findById(id);

		if (optionalUserEntity == null) {

			return null;

		}

		return optionalUserEntity.get();

	}

	@Override
	public UserEntity find(String name) {

		if (name == null) {

			return null;

		}

		UserEntity userEntity = userRepository.findByDisplayName(name);

		if (userEntity == null) {

			return null;

		}

		// Initialize the collection of roles.
		userEntity.getRoles();

		return userEntity;

	}

	@Override
	public UserDetails loadUserByUsername(String displayName) throws UsernameNotFoundException {

		UserEntity user = userRepository.findByDisplayName(displayName);

		if (user == null) {

			throw new UsernameNotFoundException("Invalid username or password.");

		}

		return new org.springframework.security.core.userdetails.User(user.getDisplayName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	@Override
	public UserEntity save(String s) {

		throw new ApplicationRuntimeException("Method not implemented.");

	}

	public UserEntity save(UserEntity userEntity) {

		Collection<RoleEntity> roles = userEntity.getRoles();

		if (userEntity instanceof EducatorEntity) {

			RoleEntity educatorRole = roleService.find(UserRole.Educator.getSecurityRoleName());

			if (!roles.contains(educatorRole)) {

				roles.add(educatorRole);

			}

		}

		RoleEntity userRole = roleService.find(UserRole.User.getSecurityRoleName());

		if (!roles.contains(userRole)) {

			roles.add(userRole);

		}

		UserEntity currentUserEntity = find(userEntity.getDisplayName());

		if (currentUserEntity != null && currentUserEntity.getId() != 0 && userEntity.getId() == 0) {

			userEntity.setId(currentUserEntity.getId());

		}

		setAuditingFields(userEntity);

		return userRepository.saveAndFlush(userEntity);

	}

	public UserEntity save(UserRegistrationForm registration) {

		UserEntity user = null;

		Set<RoleEntity> roles = new TreeSet<RoleEntity>();

		roles.add(roleService.find(UserRole.User.getSecurityRoleName()));

		if (registration.getUserType().equals(UserRole.User.getFormName())) {

			user = new UserEntity(registration.getDisplayName(), registration.getEmail(),
					passwordEncoder.encode(registration.getPassword()));

		} else if (registration.getUserType().equals(UserRole.Educator.getFormName())) {

			user = new EducatorEntity(registration.getDisplayName(), registration.getEmail(),
					passwordEncoder.encode(registration.getPassword()));

			roles.add(roleService.find(UserRole.Educator.getSecurityRoleName()));

		}

		user.setRoles(roles);

		return save(user);
	}

	public void saveSelectedExercises(ExerciseGroupSelectionForm exerciseSelectionForm) {

		Optional<UserEntity> optionalStudent = userRepository
				.findById(Long.valueOf(String.valueOf(exerciseSelectionForm.getStudentId())));
		StudentEntity student = (StudentEntity) optionalStudent.get();

		System.out.println(exerciseSelectionForm);

		for (String exerciseGroupId : exerciseSelectionForm.getAllGroupExercises()) {

			Optional<ExerciseGroupEntity> optionalGroupExercise = exerciseGroupRepository
					.findById(Long.valueOf(exerciseGroupId));

			ExerciseGroupEntity exerciseGroup = optionalGroupExercise.get();

			System.out.println("Student id = " + student.getId());
			System.out.println("Exercise Group id= " + exerciseGroup.getId());

			AssignmentEntity assignment = assignmentService.findByUserAndExerciseGroup(student, exerciseGroup);

			System.out.println(assignment);

			if (contains(exerciseGroupId, exerciseSelectionForm.getSelectedGroupExercises())) {

				System.out.println("select : " + exerciseGroupId);

				// This exercise is selected.

				if (assignment == null) {

					assignmentService.create(student, exerciseGroup);

				}

			} else {

				// This exercise is not selected.

				if (assignment != null) {

					assignmentService.delete(assignment);

				}

			}

		}

	}

	public Page<UserEntity> educatorStudentsPage(Pageable pageable, EducatorEntity educator) {

		Page<UserEntity> page = userRepository.findByEducator(pageable, educator);

		return page;

	}

	public List<StudentEntity> educatorStudentsList(Pageable pageable, EducatorEntity educator) {

		List<StudentEntity> students = new ArrayList<StudentEntity>();

		for (UserEntity userEntity : educatorStudentsPage(pageable, educator)) {

			StudentEntity studentEntity = (StudentEntity) userEntity;

			students.add(studentEntity);

		}

		return students;

	}

	public StudentEntity saveStudentForm(StudentForm studentForm) {

		String currentUserName = getCurrentUserName();

		EducatorEntity educator = (EducatorEntity) userRepository.findByDisplayName(currentUserName);

		StudentEntity student = null;

		if (!StringUtils.isEmpty(studentForm.getId())) {

			new StudentEntity();

			Optional<UserEntity> optionalStudent = userRepository.findById(Long.valueOf(studentForm.getId()));

			student = (StudentEntity) optionalStudent.get();

		} else {

			student = new StudentEntity();

		}

		student.setDisplayName(studentForm.getDisplayName());

		student.setPassword(passwordEncoder.encode(studentForm.getPassword()));

		student.setEducator(educator);

		student.setEmail(studentForm.getDisplayName());

		userRepository.save(student);

		return student;

	}

	private boolean contains(String value, String[] arrayList) {

		if (value == null || arrayList == null) {

			return false;

		}

		for (String arrayListValue : arrayList) {

			if (value.contentEquals(arrayListValue)) {

				return true;

			}

		}

		return false;

	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
