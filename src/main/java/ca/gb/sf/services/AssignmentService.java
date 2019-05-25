package ca.gb.sf.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.gb.sf.models.AssignmentEntity;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.StudentEntity;
import ca.gb.sf.repositories.AssignmentRepository;

@Component
public class AssignmentService {

	@Autowired
	AssignmentRepository assignmentRepository;
	
	public AssignmentEntity create(StudentEntity student, ExerciseGroupEntity exerciseGroup) {
		
		AssignmentEntity assignment = new AssignmentEntity(student, exerciseGroup);
		
		return create(assignment);
		
	}
	
	public AssignmentEntity create(AssignmentEntity assignment) {
		
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
	
	public AssignmentEntity findByStudentAndExerciseGroup(StudentEntity student, ExerciseGroupEntity exerciseGroup) {
		
		return assignmentRepository.findByStudentAndExerciseGroup(student, exerciseGroup);
		
	}
	
	public AssignmentEntity findByStudentAndAssignmentId(StudentEntity student, Long assignmentId) {
		
		return assignmentRepository.findByStudentAndAssignmentId(student, assignmentId);
		
	}

	
}
