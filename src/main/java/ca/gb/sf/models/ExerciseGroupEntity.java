package ca.gb.sf.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.CompareToBuilder;

@Entity
@Table(name = "exercise_groups")
public class ExerciseGroupEntity extends PersistentObject implements Comparable<ExerciseGroupEntity> {

	@Column(nullable = false)
	private String name;

	// @OneToMany(mappedBy = "exerciseGroup", cascade = CascadeType.ALL,
	// orphanRemoval = true, fetch = FetchType.LAZY)
	@OneToMany(mappedBy = "exerciseGroup", orphanRemoval = true, fetch = FetchType.LAZY)
	// @OneToMany(mappedBy = "exerciseGroup", cascade = CascadeType.ALL,
	// orphanRemoval = true, fetch = FetchType.EAGER)
	private List<ExerciseEntity> exercises = new ArrayList<ExerciseEntity>();

	@OneToMany(mappedBy = "exerciseGroup", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<AssignmentEntity> assignments;

	public ExerciseGroupEntity() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ExerciseEntity> getExercises() {
		return exercises;
	}

	public void setExercises(List<ExerciseEntity> exercises) {
		this.exercises = exercises;
	}

	public List<AssignmentEntity> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<AssignmentEntity> assignments) {
		this.assignments = assignments;
	}

	public void addExercise(ExerciseEntity exercise) {

		exercises.add(exercise);
		exercise.setExerciseGroup(this);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assignments == null) ? 0 : assignments.hashCode());
		result = prime * result + ((exercises == null) ? 0 : exercises.hashCode());
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
		ExerciseGroupEntity other = (ExerciseGroupEntity) obj;
		if (assignments == null) {
			if (other.assignments != null)
				return false;
		} else if (!assignments.equals(other.assignments))
			return false;
		if (exercises == null) {
			if (other.exercises != null)
				return false;
		} else if (!exercises.equals(other.exercises))
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
		builder.append("ExerciseGroup [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		builder.append("]");
		return builder.toString();
	}

	
	@Override
	public int compareTo(ExerciseGroupEntity o) {

		return new CompareToBuilder()
				.append(this.getId(), o.getId())
				.append(this.getName(), o.getName())
				.toComparison();

	}
	
}
