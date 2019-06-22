package ca.gb.sf.repositories;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.SpringContextIntegrationTest;
import ca.gb.sf.models.AssignmentEntity;
import ca.gb.sf.models.AssignmentStatusEntity;
import ca.gb.sf.models.EducatorEntity;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.StudentEntity;
import ca.gb.sf.models.UserEntity;
import ca.gb.sf.models.UserStats;
import ca.gb.sf.repositories.UserRepository;
import ca.gb.sf.repositories.UserStatsRepository;

public class UserStatsRepositoryTest extends SpringContextIntegrationTest {
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	UserStatsRepository userStatsRepo;

	@Autowired
	ExerciseGroupRepository exerciseGroupRepository;

	@Autowired
	AssignmentStatusRepository assignmentStatusRepository;
	
	@Autowired
	AssignmentRepository assignmentRepository;

	@Test
	public void findByEducatorWithZeroCountTest() {
		
		String key = "findByEducatorWithZeroCountTest";
		
		EducatorEntity educator = createEducator(key);
		
		List<UserStats> userStats = userStatsRepo.reportEducatorWithCountList(educator.getId());
		
		for (UserStats userStat : userStats) {
			
			assertTrue(userStat.getAssignmentCompletedCount() == 0);
			assertTrue(userStat.getAssignmentCount() == 0);
			
		}
		
	}

	@Test
	public void findByEducatorWithCountTest() {
		
		String key = "findByEducatorWithCountTest";
		
		EducatorEntity educator = createEducator(key);
		
		StudentEntity student = createStudent(key, educator);

		createAssignment("1", student, assignmentStatusRepository.findByCode(AssignmentStatusEntity.ASSIGNED));
		createAssignment("2", student, assignmentStatusRepository.findByCode(AssignmentStatusEntity.WORK_IN_PROGRESS));
		createAssignment("3", student, assignmentStatusRepository.findByCode(AssignmentStatusEntity.COMPLETED));

		List<UserStats> userStats = userStatsRepo.reportEducatorWithCountList(educator.getId());

		System.out.println(userStats.size());
		
		// assertTrue(userStats.size() == 3);
		
		for (UserStats userStat : userStats) {
			
			System.out.println(userStat);
			
		}
		
	}

	private EducatorEntity createEducator(String key) {
		
		EducatorEntity educator = new EducatorEntity("educator-" + key, "educator-email-" + key, "password");

		return userRepo.save(educator);
		
	}
	
	private StudentEntity createStudent(String key, EducatorEntity educator) {
		
		StudentEntity student = new StudentEntity("student-" + key, "student-email-" + key, "password", educator);
		
		return userRepo.save(student);
 		
	}
	
	private ExerciseGroupEntity createExerciseGroupEntity(String key) {
		
		ExerciseGroupEntity exerciseGroupEntity = new ExerciseGroupEntity("exercise-group-" + key);
		
		return exerciseGroupRepository.save(exerciseGroupEntity);
		
		
	}

	private AssignmentEntity createAssignment(String key, UserEntity student, AssignmentStatusEntity assignmentStatus) {
		
		ExerciseGroupEntity exerciseGroup = createExerciseGroupEntity(key);
		
		AssignmentEntity assignmentEntity = new AssignmentEntity(student, exerciseGroup, assignmentStatus);
		
		AssignmentEntity savedAssignment = assignmentRepository.save(assignmentEntity); 
		
		System.out.println(savedAssignment);
		
		return savedAssignment;
		
	}
	
}
