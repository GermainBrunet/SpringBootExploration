package ca.gb.sf.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.CompareToBuilder;

/**
 * Keywords are object used to help categorize an Exercise Group. Since trying
 * to categorize an series of exercises to a consistent set of attributes,
 * keywords provides flexibility by allowing any word to describe an exercise.
 * Examples should include: grade or level, exercise type, condition being
 * targeted, creator, etc.
 */

@Entity
@Table(name = "keywords")
public class KeywordEntity extends PersistentObject implements Comparable<KeywordEntity> {

	// Keyword used to describe an exercise group.
	@Column(nullable = false)
	private String keyword;

	@ManyToMany
	private Set<ExerciseGroupEntity> exerciseGroups;

	public KeywordEntity() {
	}

	public KeywordEntity(String keyword) {
		super();
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Set<ExerciseGroupEntity> getExerciseGroups() {
		return exerciseGroups;
	}

	public void setExerciseGroup(Set<ExerciseGroupEntity> exerciseGroups) {
		this.exerciseGroups = exerciseGroups;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
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
		KeywordEntity other = (KeywordEntity) obj;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KeywordEntity [keyword=");
		builder.append(keyword);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(KeywordEntity o) {

		return new CompareToBuilder().append(this.getId(), o.getId()).append(this.getKeyword(), o.getKeyword())
				.toComparison();

	}

}
