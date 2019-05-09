package ca.gb.sf.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "exercise_groups")
public class ExerciseGroup extends PersistentObject {

	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "exerciseGroup", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Exercise> excercises;

	@OneToMany(mappedBy = "exerciseGroup", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Assignment> assignments;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Exercise> getExcercises() {
		return excercises;
	}

	public void setExcercises(List<Exercise> excercises) {
		this.excercises = excercises;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
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
		/**
		if (excercises != null) {
			builder.append("excercises=");
			builder.append(excercises);
		}
		**/
		builder.append("]");
		return builder.toString();
	}
	
}
