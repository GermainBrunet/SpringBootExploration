package ca.gb.sf.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Educator")
@Table(name = "educator")
@DiscriminatorValue("EDUCATOR")
public class Educator extends User {

    @OneToMany(
            mappedBy = "educator",
            cascade = CascadeType.ALL,
            orphanRemoval = true
        )
	private List<Student> students;

    public Educator(String displayName, String email, String password) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
    }
    
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

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
