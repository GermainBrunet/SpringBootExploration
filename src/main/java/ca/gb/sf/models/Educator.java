package ca.gb.sf.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Extends the User class and are associated with students. An educator can have
 * zero to many students. The educator class will normally register the students
 * and assign specific tasks. The educator also has full access to review the
 * students work.
 */

@Entity(name = "Educator")
@Table(name = "educator")
@DiscriminatorValue("Educator")
public class Educator extends User {

	// Students associated to this Educator.
	@OneToMany(mappedBy = "educator", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Student> students;

	// Constructor
	public Educator() {};
	
	// Constructor
	public Educator(String displayName, String email, String password) {
		this.displayName = displayName;
		this.email = email;
		this.password = password;
	}

	/**
	 * Getter for the list of students associated with this Educator.
	 * 
	 * @return
	 */
	public List<Student> getStudents() {
		return students;
	}

	/**
	 * Setter for the list of students associated with this Educator.
	 * 
	 * @param students
	 */
	public void setStudents(List<Student> students) {
		this.students = students;
	}

	/**
	 * String representation of this object. Includes the parent object toString.
	 * Used for debugging purposes.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Educator [");
		if (students != null)
			builder.append("students=").append(students).append(", ");
		if (super.toString() != null)
			builder.append("toString()=").append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
