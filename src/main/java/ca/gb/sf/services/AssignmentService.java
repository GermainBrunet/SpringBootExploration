package ca.gb.sf.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.gb.sf.models.AssignmentEntity;
import ca.gb.sf.models.AssignmentStatusEntity;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.StudentEntity;
import ca.gb.sf.models.UserEntity;
import ca.gb.sf.repositories.AssignmentRepository;
import ca.gb.sf.repositories.AssignmentStatusRepository;

/**
 * Class that provides services for the Assignment Entity.
 */

@Component
public class AssignmentService extends CommonService {

	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Autowired
	AssignmentStatusRepository assignmentStatusRepository;
	
	public AssignmentEntity create(StudentEntity student, ExerciseGroupEntity exerciseGroup) {
		
		AssignmentStatusEntity assignmentStatus = assignmentStatusRepository.findByCode(AssignmentStatusEntity.ASSIGNED);

		AssignmentEntity assignment = new AssignmentEntity(student, exerciseGroup, assignmentStatus);

		return save(assignment);

	}

	public AssignmentEntity save(AssignmentEntity assignment) {

		setAuditingFields(assignment);
		
		return assignmentRepository.save(assignment);

	}

	public long count() {

		return assignmentRepository.count();
	}

	public void deleteAll() {

		assignmentRepository.deleteAll();

	}

	public void delete(AssignmentEntity assignment) {

		assignmentRepository.delete(assignment);

	}

	public List<AssignmentEntity> findListByStudent(StudentEntity student) {

		return assignmentRepository.findByStudent(student);

	}

	public AssignmentEntity findByUserAndExerciseGroup(UserEntity user, ExerciseGroupEntity exerciseGroup) {

		return assignmentRepository.findByUserAndExerciseGroup(user, exerciseGroup);

	}

	public AssignmentEntity findByStudentAndAssignmentId(StudentEntity student, Long assignmentId) {

		return assignmentRepository.findByUserAndAssignmentId(student, assignmentId);

	}
	
	/**
	 * Function that sets the assignment status.
	 * 
	 * @param assignmentEntity
	 * 
	 * @param status
	 * 
	 * @return
	 */
	public AssignmentEntity setAssignmentStatus(AssignmentEntity assignmentEntity, AssignmentStatusEntity status) {
		
		assignmentEntity.setAssignmentStatus(status);
		
		return assignmentRepository.save(assignmentEntity);
		
	}
	
	/**
	 * Function that sets the assignment to work in progress.
	 * 
	 * @param assignmentEntity
	 * 
	 * @return
	 */
	public AssignmentEntity setAssignmentToWorkInProgress(AssignmentEntity assignmentEntity) {
		
		AssignmentStatusEntity assignmentStatus = assignmentStatusRepository.findByCode(AssignmentStatusEntity.WORK_IN_PROGRESS);
		
		return setAssignmentStatus(assignmentEntity, assignmentStatus);
		
	}

	/**
	 * Function that sets the assignment to completed.
	 * 
	 * @param assignmentEntity
	 * 
	 * @return
	 */
	public AssignmentEntity setAssignmentToCompleted(AssignmentEntity assignmentEntity) {
		
		AssignmentStatusEntity assignmentStatus = assignmentStatusRepository.findByCode(AssignmentStatusEntity.COMPLETED);
		
		return setAssignmentStatus(assignmentEntity, assignmentStatus);
		
	}

	/**
	 * Function that sets the assignment to assigned. 
	 * 
	 * @param assignmentEntity
	 * 
	 * @return
	 */
	public AssignmentEntity setAssignmentToAssigned(AssignmentEntity assignmentEntity) {
		
		AssignmentStatusEntity assignmentStatus = assignmentStatusRepository.findByCode(AssignmentStatusEntity.ASSIGNED);
		
		return setAssignmentStatus(assignmentEntity, assignmentStatus);
		
	}

}
