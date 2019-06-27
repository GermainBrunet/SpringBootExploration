package ca.gb.sf.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.CompareToBuilder;

/**
 * Object that describes an exercise for a student. The student will be expected
 * to start with the initial word and correct it to arrive at the target word.
 * Written and audible instructions can be provided on an exercise per exercise
 * basis. Multiple exercises will be grouped together as part of an exercise
 * group. Once the student has finished modifying the initial word into the
 * target word, the software will move the student to the next exercise. Once
 * all the exercises in a group are completed, the assignment will be considered
 * completed.
 */

@Entity
@Table(name = "exercises")
public class ExerciseEntity extends AuditedObject implements Comparable<ExerciseEntity>, Serializable {

	private static final long serialVersionUID = -418793221726443549L;

	// Initial word displayed to the student.
	@Column(nullable = true)
	private String initialWord;

	// Target word the student must generate to complete this exercise.
	@Column(nullable = false)
	private String targetWord;

	// Written instructions displayed on the page for the student.
	@Column(nullable = true)
	private String writtenInstructions;

	// Audible instructions that the student will hear upon arriving on the
	// page.
	@Column(nullable = true)
	private String readInstructions;

	// Order of this exercise.
	@Column(nullable = false)
	private Integer exerciseOrder;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exercise_group_id", nullable = false)
	private ExerciseGroupEntity exerciseGroup;

	public ExerciseEntity() {
	}

	public ExerciseEntity(String targetWord, Integer exerciseOrder) {
		super();
		this.targetWord = targetWord;
		this.exerciseOrder = exerciseOrder;
	}

	public ExerciseEntity(String initialWord, String targetWord, String writtenInstructions, String readInstructions,
			Integer exerciseOrder, ExerciseGroupEntity exerciseGroup) {
		super();
		this.initialWord = initialWord;
		this.targetWord = targetWord;
		this.writtenInstructions = writtenInstructions;
		this.readInstructions = readInstructions;
		this.exerciseOrder = exerciseOrder;
		this.exerciseGroup = exerciseGroup;
		// exerciseGroup.addExercise(this);
	}

	public String getInitialWord() {
		return initialWord;
	}

	public void setInitialWord(String initialWord) {
		this.initialWord = initialWord;
	}

	public String getTargetWord() {
		return targetWord;
	}

	public void setTargetWord(String targetWord) {
		this.targetWord = targetWord;
	}

	public String getWrittenInstructions() {
		return writtenInstructions;
	}

	public void setWrittenInstructions(String writtenInstructions) {
		this.writtenInstructions = writtenInstructions;
	}

	public String getReadInstructions() {
		return readInstructions;
	}

	public void setReadInstructions(String readInstructions) {
		this.readInstructions = readInstructions;
	}

	public Integer getExerciseOrder() {
		return exerciseOrder;
	}

	public void setExerciseOrder(Integer exerciseOrder) {
		this.exerciseOrder = exerciseOrder;
	}

	public ExerciseGroupEntity getExerciseGroup() {
		return exerciseGroup;
	}

	public void setExerciseGroup(ExerciseGroupEntity exerciseGroup) {
		this.exerciseGroup = exerciseGroup;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exerciseOrder == null) ? 0 : exerciseOrder.hashCode());
		result = prime * result + ((initialWord == null) ? 0 : initialWord.hashCode());
		result = prime * result + ((writtenInstructions == null) ? 0 : writtenInstructions.hashCode());
		result = prime * result + ((readInstructions == null) ? 0 : readInstructions.hashCode());
		result = prime * result + ((targetWord == null) ? 0 : targetWord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExerciseEntity other = (ExerciseEntity) obj;
		if (exerciseOrder == null) {
			if (other.exerciseOrder != null)
				return false;
		} else if (!exerciseOrder.equals(other.exerciseOrder))
			return false;
		if (initialWord == null) {
			if (other.initialWord != null)
				return false;
		} else if (!initialWord.equals(other.initialWord))
			return false;
		if (writtenInstructions == null) {
			if (other.writtenInstructions != null)
				return false;
		} else if (!writtenInstructions.equals(other.writtenInstructions))
			return false;
		if (readInstructions == null) {
			if (other.readInstructions != null)
				return false;
		} else if (!readInstructions.equals(other.readInstructions))
			return false;
		if (targetWord == null) {
			if (other.targetWord != null)
				return false;
		} else if (!targetWord.equals(other.targetWord))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Exercise [");
		if (super.toString() != null)
			builder.append(super.toString());
		if (initialWord != null) {
			builder.append("initialWord=");
			builder.append(initialWord);
			builder.append(", ");
		}
		if (targetWord != null) {
			builder.append("targetWord=");
			builder.append(targetWord);
			builder.append(", ");
		}
		if (writtenInstructions != null) {
			builder.append("writtenInstructions=");
			builder.append(writtenInstructions);
			builder.append(", ");
		}
		if (readInstructions != null) {
			builder.append("readInstructions=");
			builder.append(readInstructions);
			builder.append(", ");
		}
		if (exerciseOrder != null) {
			builder.append("exerciseOrder=");
			builder.append(exerciseOrder);
			builder.append(", ");
		}
		if (exerciseGroup != null) {
			builder.append("exerciseGroupId=");
			builder.append(exerciseGroup.getId());
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(ExerciseEntity o) {

		return new CompareToBuilder().append(this.getId(), o.getId()).append(this.getInitialWord(), o.getInitialWord())
				.toComparison();

	}

}
