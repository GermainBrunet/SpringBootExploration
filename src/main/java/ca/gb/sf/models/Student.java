package ca.gb.sf.models;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Students extend the User class and are associated with an Educator. Educators
 * will normally create the students and assign exercises.
 */

@Entity(name = "Student")
@DiscriminatorValue("Student")
public class Student extends User {

	// To whom belongs this student.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "educator_id")
	private Educator educator;

    @OneToMany(mappedBy="student", cascade = CascadeType.ALL)
    private Collection<Assignment> assignment;
	
	// Constructor
	public Student() {
	}

	// Constructor
	public Student(String displayName, String email, String password, Educator educator) {
		this.displayName = displayName;
		this.email = email;
		this.password = password;
		this.educator = educator;
	}

	/**
	 * Getter for the Educator
	 * 
	 * @return
	 */
	public Educator getEducator() {
		return educator;
	}

	/**
	 * Setter for the Educator
	 * 
	 * @param educator
	 */
	public void setEducator(Educator educator) {
		this.educator = educator;
	}

	/**
	 * Getter for assignments
	 * 
	 * @return
	 */
	public Collection<Assignment> getAssignment() {
		return assignment;
	}

	/**
	 * Setter for assignments
	 * 
	 * @param assignment
	 */
	public void setAssignment(Collection<Assignment> assignment) {
		this.assignment = assignment;
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
		if (educator != null)
			builder.append("educator=").append(educator).append(", ");
		if (assignment != null)
			builder.append("assignment=").append(assignment).append(", ");
		if (super.toString() != null)
			builder.append("toString()=").append(super.toString());
		builder.append("]");
		return builder.toString();
	}



}
