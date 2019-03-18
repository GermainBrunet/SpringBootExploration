package ca.gb.sf.models;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	public Exercise() {
	}

	public Exercise(String initialWord, String targetWord, String instructions) {
		super();
		this.initialWord = initialWord;
		this.targetWord = targetWord;
		this.instructions = instructions;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Exercise [");
		if (initialWord != null)
			builder.append("initialWord=").append(initialWord).append(", ");
		if (targetWord != null)
			builder.append("targetWord=").append(targetWord).append(", ");
		if (instructions != null)
			builder.append("instructions=").append(instructions).append(", ");
		builder.append("getId()=").append(getId()).append("]");
		return builder.toString();
	}

}
