package ca.gb.sf.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Object that describes a exercise for a student. The student will be expected
 * to start with the initial word and correct it to arrive at the target word.
 * Instructions are provided on an exercise basis.
 */

@Entity
@Table(name = "exercises")
public class Exercise extends PersistentObject {

	@Column(nullable = false)
	private String initialWord;

	@Column(nullable = false)
	private String targetWord;

	@Column(nullable = false)
	private String instructions;
	
	@Column(nullable = false)
	private Integer exerciseOrder;

	@ManyToOne(fetch = FetchType.LAZY)
	private ExerciseGroup exerciseGroup;
	
	public Exercise() {
	}

	public Exercise(String initialWord, String targetWord, String instructions, Integer exerciseOrder) {
		super();
		this.initialWord = initialWord;
		this.targetWord = targetWord;
		this.instructions = instructions;
		this.exerciseOrder = exerciseOrder;
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

	public ExerciseGroup getExerciseGroup() {
		return exerciseGroup;
	}

	public void setExerciseGroup(ExerciseGroup exerciseGroup) {
		this.exerciseGroup = exerciseGroup;
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
		}
		if (exerciseGroup != null && exerciseGroup.getName() != null) {
			builder.append("exerciseGroup=");
			builder.append(exerciseGroup.getName());
		}
		builder.append("]");
		return builder.toString();
	}


}
