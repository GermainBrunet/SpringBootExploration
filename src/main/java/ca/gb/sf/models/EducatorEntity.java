package ca.gb.sf.models;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DiscriminatorOptions;

/**
 * Extends the User class and are associated with students. An educator can have
 * zero to many students. The educator class will normally register the students
 * and assign specific tasks. The educator also has full access to review the
 * students work.
 */

@Entity(name = "Educator")
@DiscriminatorValue("Educator")
@DiscriminatorOptions(force=true)
public class EducatorEntity extends UserEntity implements Serializable {

	private static final long serialVersionUID = 6879403070239445214L;
	
	// Students associated to this Educator.
	// @OneToMany(mappedBy = "educator", cascade = CascadeType.ALL, orphanRemoval = true)
	// @OneToMany(cascade = CascadeType.ALL)
	// Do not implement the mappedBy because we have the JoinColumn.
	// @OneToMany //(orphanRemoval = true, cascade = CascadeType.ALL)
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "educator_id")
	// @OnDelete(action = OnDeleteAction.CASCADE)
	private Set<StudentEntity> students = new TreeSet<StudentEntity>();

	// Constructor
	public EducatorEntity() {};
	
	// Constructor
	public EducatorEntity(String displayName, String email, String password) {
		this.displayName = displayName;
		this.email = email;
		this.password = password;
	}

	/**
	 * Getter for the list of students associated with this Educator.
	 * 
	 * @return
	 */
	public Set<StudentEntity> getStudents() {
		return students;
	}

	/**
	 * Setter for the list of students associated with this Educator.
	 * 
	 * @param students
	 */
	public void setStudents(Set<StudentEntity> students) {
		this.students = students;
	}

	/**
	 * Function that allows the addition of a student.
	 * 
	 * @param studentEntity
	 */
	public void addStudent(StudentEntity studentEntity) {
		
		this.students.add(studentEntity);
		
	}
	
	/**
	 * Function that allows the removal of a student.
	 * 
	 * @param studentEntity
	 */
	public void removeStudent(StudentEntity studentEntity) {
		
		this.students.remove(studentEntity);
		
	}
	
	/**
	 * String representation of this object. Includes the parent object toString.
	 * Used for debugging purposes.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Educator [");
		if (super.toString() != null)
			builder.append("toString()=").append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
