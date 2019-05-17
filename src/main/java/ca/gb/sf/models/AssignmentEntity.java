package ca.gb.sf.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Object that represents an exercise that has been assigned to a student.  Requires that both a student and an exercise be present.
 */

@Entity
@Table(name = "assignments", uniqueConstraints={
	    @UniqueConstraint(columnNames = {"student_id", "exercise_group_id"})
})
public class AssignmentEntity extends PersistentObject {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
	StudentEntity student;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "exercise_group_id", nullable = false)
    ExerciseGroupEntity exerciseGroup;
    
    Long speedToComplete;
    
    Long stepsToComplete;
    
    Integer stars;
    
    AssignmentStatus assignmentStatus;
    
    public AssignmentEntity() {};
    
	public AssignmentEntity(StudentEntity student, ExerciseGroupEntity exerciseGroup) {
		super();
		this.assignmentStatus = AssignmentStatus.ASSIGNED;
		this.student = student;
		this.exerciseGroup = exerciseGroup;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public ExerciseGroupEntity getExerciseGroup() {
		return exerciseGroup;
	}

	public void setExerciseGroup(ExerciseGroupEntity exerciseGroup) {
		this.exerciseGroup = exerciseGroup;
	}

	public Long getSpeedToComplete() {
		return speedToComplete;
	}

	public void setSpeedToComplete(Long speedToComplete) {
		this.speedToComplete = speedToComplete;
	}

	public Long getStepsToComplete() {
		return stepsToComplete;
	}

	public void setStepsToComplete(Long stepsToComplete) {
		this.stepsToComplete = stepsToComplete;
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public AssignmentStatus getAssignmentStatus() {
		return assignmentStatus;
	}

	public void setAssignmentStatus(AssignmentStatus assignmentStatus) {
		this.assignmentStatus = assignmentStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Assignment [");
//		if (student != null)
//			builder.append("student=").append(student).append(", ");
		if (exerciseGroup != null)
			builder.append("exercise=").append(exerciseGroup).append(", ");
		if (speedToComplete != null)
			builder.append("speedToComplete=").append(speedToComplete).append(", ");
		if (stepsToComplete != null)
			builder.append("stepsToComplete=").append(stepsToComplete).append(", ");
		if (stars != null)
			builder.append("stars=").append(stars).append(", ");
		if (assignmentStatus != null)
			builder.append("assignmentStatus=").append(assignmentStatus).append(", ");
		if (super.toString() != null)
			builder.append("toString()=").append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
}
