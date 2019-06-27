package ca.gb.sf.models;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DiscriminatorOptions;

/**
 * Students extend the User class and are associated with an Educator. Educators
 * will normally create the students and assign exercises.
 */

@Entity(name = "Student")
@DiscriminatorValue("Student")
@DiscriminatorOptions(force=true)
public class StudentEntity extends UserEntity implements Serializable {

	private static final long serialVersionUID = -8482710214219617164L;
	
	// To whom belongs this student.
	@ManyToOne(fetch = FetchType.LAZY) // , cascade = CascadeType.ALL)
	// Must allow for null column.  Do not implement with "nullable=false" 
	@JoinColumn(name = "educator_id")
	private EducatorEntity educator;

	// Constructor
	public StudentEntity() {
	}

	// Constructor
	public StudentEntity(String displayName, String email, String password, EducatorEntity educator) {
		this.displayName = displayName;
		this.email = email;
		this.password = password;
		this.educator = educator;
		// educator.addStudent(this);
	}

	/**
	 * Getter for the Educator
	 * 
	 * @return
	 */
	public EducatorEntity getEducator() {
		return educator;
	}

	/**
	 * Setter for the Educator
	 * 
	 * @param educator
	 */
	public void setEducator(EducatorEntity educator) {
		this.educator = educator;
	}

	@Override
	public int hashCode() {
		
		return new HashCodeBuilder()
				.append(this.id)
				.append(this.displayName)
				.append(this.email)
				.toHashCode();
		
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof StudentEntity == false) {
			
			return false;
			
		}
		
		if (this == obj) {
			
			return true;
			
		}
		
		final StudentEntity studentEntity = (StudentEntity) obj;

		return new EqualsBuilder()
				.append(this.id, studentEntity.getId())
				.append(this.displayName, studentEntity.getDisplayName())
				.append(this.email, studentEntity.getEmail())
				.isEquals();
		
	}
	
	/**
	 * String representation of this object. Includes the parent object toString.
	 * Used for debugging purposes.  Present the educator's display name only
	 * to prevent circular references.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [");
		if (super.toString() != null)
			builder.append(super.toString());
		if (educator != null) {
			builder.append("educator=");
			builder.append(educator.getId());
		}
		builder.append("]");
		return builder.toString();
	}



}
