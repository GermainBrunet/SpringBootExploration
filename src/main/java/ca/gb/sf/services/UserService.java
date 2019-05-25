package ca.gb.sf.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ca.gb.sf.models.AssignmentEntity;
import ca.gb.sf.models.AssignmentStatus;
import ca.gb.sf.models.EducatorEntity;
import ca.gb.sf.models.ExerciseEntity;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.RoleEntity;
import ca.gb.sf.models.StudentEntity;
import ca.gb.sf.models.UserEntity;
import ca.gb.sf.repositories.AssignmentRepository;
import ca.gb.sf.repositories.ExerciseGroupRepository;
import ca.gb.sf.repositories.ExerciseRepository;
import ca.gb.sf.repositories.RoleRepository;
import ca.gb.sf.repositories.UserRepository;
import ca.gb.sf.web.form.ExerciseGroupSelectionForm;
import ca.gb.sf.web.form.StudentForm;
import ca.gb.sf.web.form.UserRegistrationForm;

// @Service
@Component
public class UserService implements UserDetailsService {

	public static String USER_TYPE_USER = "USER";

	public static String USER_TYPE_EDUCATOR = "EDUCATOR";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ExerciseGroupRepository exerciseGroupRepository;

	@Autowired
	private AssignmentService assignmentService;

	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public UserEntity findByDisplayName(String displayName) {
		return userRepository.findByDisplayName(displayName);
	}

	public UserEntity save(UserRegistrationForm registration) {

		System.out.println("user type : " + registration.getUserType());

		UserEntity user = null;

		Set<RoleEntity> roles = new TreeSet<RoleEntity>();

		roles.add(findRoleByName("ROLE_USER"));

		if (registration.getUserType().equals("USER")) {

			user = new UserEntity(registration.getDisplayName(), registration.getEmail(),
					passwordEncoder.encode(registration.getPassword()));

		} else if (registration.getUserType().equals("EDUCATOR")) {

			user = new EducatorEntity(registration.getDisplayName(), registration.getEmail(),
					passwordEncoder.encode(registration.getPassword()));
			roles.add(findRoleByName("ROLE_EDUCATOR"));

		}

		user.setRoles(roles);

		return userRepository.save(user);
	}

	public StudentEntity saveStudent(StudentForm studentForm) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		EducatorEntity educator = (EducatorEntity) userRepository.findByEmail(auth.getName());

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

//		// if (studentForm.getExerciseGroupIds() != null && !studentForm.getExerciseGroupIds(.isEmpty()) {
//		if (studentForm.getExerciseGroupIds() != null && !studentForm.getExerciseGroupIds().isEmpty()) {
//		
//			for (String exerciseGroupId : studentForm.getExerciseGroupIds()) {
//
//				Optional<ExerciseGroupEntity> optionalExerciseGroup = exerciseGroupRepository.findById(Long.valueOf(exerciseGroupId));
//
//				ExerciseGroupEntity exerciseGroup = optionalExerciseGroup.get();
//
//				// Assignment assignment = new Assignment();
//
//				// assignment.setExerciseGroup(exerciseGroup);
//
//				// assignment.setStudent(student);
//
//				// assignment.setAssignmentStatus(AssignmentStatus.ASSIGNED);
//
//				assignmentService.create(student, exerciseGroup);
//
//			}
//
//		}

		return student;

	}

	public void saveSelectedExercises(ExerciseGroupSelectionForm exerciseSelectionForm) {

		Optional<UserEntity> optionalStudent = userRepository
				.findById(Long.valueOf(String.valueOf(exerciseSelectionForm.getStudentId())));
		StudentEntity student = (StudentEntity) optionalStudent.get();

		System.out.println(exerciseSelectionForm);

		for (String exerciseGroupId : exerciseSelectionForm.getAllGroupExercises()) {

			Optional<ExerciseGroupEntity> optionalGroupExercise = exerciseGroupRepository.findById(Long.valueOf(exerciseGroupId));

			ExerciseGroupEntity exerciseGroup = optionalGroupExercise.get();

			System.out.println("Student id = " + student.getId());
			System.out.println("Exercise Group id= " + exerciseGroup.getId());

			AssignmentEntity assignment = assignmentService.findByStudentAndExerciseGroup(student, exerciseGroup);

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
//			
//			Optional<Exercise> optionalExercise = exerciseRepository.findById(Long.valueOf(exerciseId));
//
//			Exercise exercise = optionalExercise.get();
//
//			Assignment assignment = new Assignment();
//
//			assignment.setExcercise(exercise);
//
//			assignment.setStudent(student);
//
//			assignment.setAssignmentStatus(AssignmentStatus.ASSIGNED);
//
//			assignmentRepository.save(assignment);

		}

	}

	public UserEntity save(UserEntity userEntity) {
		
		UserEntity currentUserEntity = findByDisplayName(userEntity.getDisplayName());
		
		if (currentUserEntity != null) {
			
			return currentUserEntity;
			
		}
		
		return userRepository.save(userEntity);
		
	}
	
	public boolean contains(String value, String[] arrayList) {

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

	public RoleEntity findRoleByName(String name) {

		if (StringUtils.isEmpty(name)) {
			return null;
		}

		RoleEntity role = roleRepository.findByName(name);

		if (role != null) {
			return role;
		}

		RoleEntity savedRole = roleRepository.save(role);

		return savedRole;

	}

	@Override
	public UserDetails loadUserByUsername(String displayName) throws UsernameNotFoundException {
		// User user = userRepository.findByEmail(email);
		UserEntity user = userRepository.findByDisplayName(displayName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		// return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
		// 		mapRolesToAuthorities(user.getRoles()));
		return new org.springframework.security.core.userdetails.User(user.getDisplayName(), user.getPassword(),
		 		mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
