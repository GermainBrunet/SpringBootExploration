package ca.gb.sf.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.CompareToBuilder;

/**
 * Object that describes a exercise for a student. The student will be expected
 * to start with the initial word and correct it to arrive at the target word.
 * Instructions are provided on an exercise basis.
 */

@Entity
@Table(name = "exercises")
public class ExerciseEntity extends PersistentObject implements Comparable<ExerciseEntity> {

	@Column(nullable = false)
	private String initialWord;

	@Column(nullable = false)
	private String targetWord;

	@Column(nullable = false)
	private String instructions;

	@Column(nullable = false)
	private Integer exerciseOrder;

	@ManyToOne(fetch = FetchType.LAZY)
	private ExerciseGroupEntity exerciseGroup;

	public ExerciseEntity() {
	}

	public ExerciseEntity(String initialWord, String targetWord, String instructions, Integer exerciseOrder,
			ExerciseGroupEntity exerciseGroup) {
		super();
		this.initialWord = initialWord;
		this.targetWord = targetWord;
		this.instructions = instructions;
		this.exerciseOrder = exerciseOrder;
		this.exerciseGroup = exerciseGroup;
		exerciseGroup.addExercise(this);
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

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
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
		result = prime * result + ((exerciseGroup == null) ? 0 : exerciseGroup.hashCode());
		result = prime * result + ((exerciseOrder == null) ? 0 : exerciseOrder.hashCode());
		result = prime * result + ((initialWord == null) ? 0 : initialWord.hashCode());
		result = prime * result + ((instructions == null) ? 0 : instructions.hashCode());
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
		if (exerciseGroup == null) {
			if (other.exerciseGroup != null)
				return false;
		} else if (!exerciseGroup.equals(other.exerciseGroup))
			return false;
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
		if (instructions == null) {
			if (other.instructions != null)
				return false;
		} else if (!instructions.equals(other.instructions))
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
		if (instructions != null) {
			builder.append("instructions=");
			builder.append(instructions);
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

		return new CompareToBuilder()
				.append(this.getId(), o.getId())
				.append(this.getInitialWord(), o.getInitialWord())
				.toComparison();

	}

}
