package ca.gb.sf.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Student")
@Table(name = "student")
@DiscriminatorValue("STUDENT")
public class Student extends User {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "educator_id")
    private Educator educator;

    public Student(String displayName, String email, String password, Educator educator) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.educator = educator;
    }
    
	public Educator getEducator() {
		return educator;
	}

	public void setEducator(Educator educator) {
		this.educator = educator;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [");
		if (educator != null)
			builder.append("educator=").append(educator).append(", ");
		if (super.toString() != null)
			builder.append("toString()=").append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
}
