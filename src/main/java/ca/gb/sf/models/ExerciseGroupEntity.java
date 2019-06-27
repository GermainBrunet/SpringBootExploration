package ca.gb.sf.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Object that will group one or more exercises for a student to complete. An
 * exercise group can be assigned to a student via the
 * <code>AssignmentEntity</code> object. An exercise group will contain one or
 * more exercises. An exercise group can have one <code>LevelEntity</code> and
 * multiple <code>KeywordEntity</code>.
 */

@Entity
@Table(name = "exercise_groups", uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class ExerciseGroupEntity extends AuditedObject implements Comparable<ExerciseGroupEntity>, Serializable {

	private static final long serialVersionUID = -7909842360957380488L;

	@Column(nullable = false)
	private String name;

	@Column(nullable = true)
	private String description;

	@Column(nullable = true)
	private String author;

	@Column(nullable = true)
	private String source;

	@OneToMany(mappedBy = "exerciseGroup",  orphanRemoval = true, fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<ExerciseEntity> exercises = new ArrayList<ExerciseEntity>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "exercise_groups_keywords", joinColumns = @JoinColumn(name = "exercise_group_id") , inverseJoinColumns = @JoinColumn(name = "keyword_id") )
	private List<KeywordEntity> keywords = new ArrayList<KeywordEntity>();

	@OneToMany(mappedBy = "exerciseGroup", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<AssignmentEntity> assignments;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "level_id")
	private LevelEntity level;

	public ExerciseGroupEntity() {
	}
	
	public ExerciseGroupEntity(String name) {
		super();
		this.name = name;
	}
	
	public ExerciseGroupEntity(String name, String description, String author, String source,
			List<ExerciseEntity> exercises, List<KeywordEntity> keywords, List<AssignmentEntity> assignments,
			LevelEntity level) {
		super();
		this.name = name;
		this.description = description;
		this.author = author;
		this.source = source;
		this.exercises = exercises;
		this.keywords = keywords;
		this.assignments = assignments;
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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

	/**
	public void addExercise(ExerciseEntity exercise) {
		
		if (exercises == null) {
			exercises = new ArrayList<ExerciseEntity>();
		}
		
		exercises.add(exercise);
		exercise.setExerciseGroup(this);
	}
	**/

	public List<KeywordEntity> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<KeywordEntity> keywords) {
		this.keywords = keywords;
	}

	public LevelEntity getLevel() {
		return level;
	}

	public void setLevel(LevelEntity level) {
		this.level = level;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
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
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExerciseGroupEntity [");
		if (super.toString() != null)
			builder.append(super.toString());
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
			builder.append(", ");
		}
		if (author != null) {
			builder.append("author=");
			builder.append(author);
			builder.append(", ");
		}
		if (source != null) {
			builder.append("source=");
			builder.append(source);
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(ExerciseGroupEntity o) {

		return new CompareToBuilder().append(this.getId(), o.getId()).append(this.getName(), o.getName())
				.toComparison();

	}

}
