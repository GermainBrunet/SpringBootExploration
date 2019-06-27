package ca.gb.sf.models;

import java.io.Serializable;

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
@Table(name = "levels")
public class LevelEntity extends AuditedObject implements Comparable<LevelEntity>, Serializable {

	private static final long serialVersionUID = 3057611543488750570L;

	// Level used to describe an exercise group.
	@Column(nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	private ExerciseGroupEntity exerciseGroup;

	public LevelEntity() {
	}
	
	public LevelEntity(String name) {
		super();
		this.name = name;
	}

	public LevelEntity(String name,
			ExerciseGroupEntity exerciseGroup) {
		super();
		this.name = name;
		exerciseGroup.setLevel(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		LevelEntity other = (LevelEntity) obj;
		if (exerciseGroup == null) {
			if (other.exerciseGroup != null)
				return false;
		} else if (!exerciseGroup.equals(other.exerciseGroup))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LevelEntity [");
		if (super.toString() != null)
			builder.append(super.toString());
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (exerciseGroup != null) {
			builder.append("exerciseGroup=");
			builder.append(exerciseGroup);
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(LevelEntity o) {

		return new CompareToBuilder()
				.append(this.getId(), o.getId())
				.append(this.getName(), o.getName())
				.toComparison();

	}

}
