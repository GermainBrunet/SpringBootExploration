package ca.gb.sf.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Entity that represents the status of an assignment.
 * 
 * 	1 - ASSIGNED,
 * 	2 - WORK_IN_PROGRESS,
 * 	3 - COMPLETED;
 */
@Entity
@Table(name = "assignment_status", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "code" }) })
public class AssignmentStatusEntity extends AuditedObject implements Comparable<AssignmentStatusEntity>, Serializable {

	private static final long serialVersionUID = 7541039066884580615L;

	public final static String ASSIGNED = "ASSIGNED";
	
	public final static String WORK_IN_PROGRESS = "WORK_IN_PROGRESS";
	
	public final static String COMPLETED = "COMPLETED";
	
	@NotNull
	@Column(nullable = false)
	private String code;
	
	@NotNull
	@Column(nullable = false)
	private String nameFrench;
	
	@NotNull
	@Column(nullable = false)
	private String nameEnglish;

	public AssignmentStatusEntity() {};

	public AssignmentStatusEntity(String code) {
		super();
		this.code = code;
	}

	public AssignmentStatusEntity(String code, String nameFrench, String nameEnglish) {
		super();
		this.code = code;
		this.nameFrench = nameFrench;
		this.nameEnglish = nameEnglish;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNameFrench() {
		return nameFrench;
	}

	public void setNameFrench(String nameFrench) {
		this.nameFrench = nameFrench;
	}

	public String getNameEnglish() {
		return nameEnglish;
	}

	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}

	@Override
	public int compareTo(AssignmentStatusEntity assignmentStatusEntity) {
		
		return new CompareToBuilder()
				.append(this.id, assignmentStatusEntity.getId())
				.append(this.code, assignmentStatusEntity.getCode())
				.toComparison();
		
	}

	@Override
	public int hashCode() {
		
		return new HashCodeBuilder()
				.append(this.id)
				.append(this.code)
				.toHashCode();
		
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof AssignmentStatusEntity == false) {
			
			return false;
			
		}
		
		if (this == obj) {
			
			return true;
			
		}
		
		final AssignmentStatusEntity assignmentStatusEntity = (AssignmentStatusEntity) obj;

		return new EqualsBuilder()
				.append(this.getId(), assignmentStatusEntity.getId())
				.append(this.getCode(), assignmentStatusEntity.getCode())
				.isEquals();
		
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AssignmentStatusEntity [");
		if (super.toString() != null)
			builder.append(super.toString());
		if (code != null) {
			builder.append("code=");
			builder.append(code);
			builder.append(", ");
		}
		if (nameFrench != null) {
			builder.append("nameFrench=");
			builder.append(nameFrench);
			builder.append(", ");
		}
		if (nameEnglish != null) {
			builder.append("nameEnglish=");
			builder.append(nameEnglish);
		}
		builder.append("]");
		return builder.toString();
	}

}
