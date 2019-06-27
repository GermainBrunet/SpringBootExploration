package ca.gb.sf.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Object that represents an exercise that has been assigned to a user.
 * Requires that both a user and an exercise group be present.
 */

@Entity
@Table(name = "assignments", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "user_id", "exercise_group_id" }) })
public class AssignmentEntity extends AuditedObject implements Comparable<AssignmentEntity>, Serializable {

	private static final long serialVersionUID = 8766191881287121581L;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	UserEntity user;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "exercise_group_id", nullable = false)
	ExerciseGroupEntity exerciseGroup;

	Long speedToComplete;

	Long stepsToComplete;

	Integer stars;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "assignment_status_id", nullable = false)
	AssignmentStatusEntity assignmentStatus;

	public AssignmentEntity() {
	};

	public AssignmentEntity(UserEntity user, ExerciseGroupEntity exerciseGroup, AssignmentStatusEntity assignmentStatus) {
		super();
		this.assignmentStatus = assignmentStatus;
		this.user = user;
		this.exerciseGroup = exerciseGroup;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
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

	public AssignmentStatusEntity getAssignmentStatus() {
		return assignmentStatus;
	}

	public void setAssignmentStatus(AssignmentStatusEntity assignmentStatus) {
		this.assignmentStatus = assignmentStatus;
	}

	@Override
	public int compareTo(AssignmentEntity assignmentEntity) {
		
		return new CompareToBuilder()
				.append(this.id, assignmentEntity.getId())
				.append(this.exerciseGroup, assignmentEntity.getExerciseGroup())
				.append(this.user, assignmentEntity.getUser())
				.append(this.assignmentStatus, assignmentEntity.getAssignmentStatus())
				.toComparison();
		
	}

	@Override
	public int hashCode() {
		
		return new HashCodeBuilder()
				.append(this.id)
				.append(this.exerciseGroup)
				.append(this.user)
				.append(this.assignmentStatus)
				.toHashCode();
		
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof AssignmentEntity == false) {
			
			return false;
			
		}
		
		if (this == obj) {
			
			return true;
			
		}
		
		final AssignmentEntity assignmentEntity = (AssignmentEntity) obj;

		return new EqualsBuilder()
				.append(this.id, assignmentEntity.getId())
				.append(this.exerciseGroup, assignmentEntity.getExerciseGroup())
				.append(this.user, assignmentEntity.getUser())
				.append(this.assignmentStatus, assignmentEntity.getAssignmentStatus())
				.isEquals();
		
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Assignment [");
		if (super.toString() != null)
			builder.append(super.toString());
		if (exerciseGroup != null) {
			builder.append("exerciseGroup=");
			builder.append(exerciseGroup);
			builder.append(", ");
		}
		if (user != null) {
			builder.append("user=");
			builder.append(user);
			builder.append(", ");
		}
		if (speedToComplete != null) {
			builder.append("speedToComplete=");
			builder.append(speedToComplete);
			builder.append(", ");
		}
		if (stepsToComplete != null) {
			builder.append("stepsToComplete=");
			builder.append(stepsToComplete);
			builder.append(", ");
		}
		if (stars != null) {
			builder.append("stars=");
			builder.append(stars);
			builder.append(", ");
		}
		if (assignmentStatus != null) {
			builder.append("assignmentStatus=");
			builder.append(assignmentStatus);
		}
		builder.append("]");
		return builder.toString();
	}

}
