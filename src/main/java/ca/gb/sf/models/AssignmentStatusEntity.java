package ca.gb.sf.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
public class AssignmentStatusEntity extends PersistentObject implements Serializable {

	public final static String ASSIGNED = "ASSIGNED";
	
	public final static String WORK_IN_PROGRESS = "WORK_IN_PROGRESS";
	
	public final static String COMPLETED = "COMPLETED";
	
	private static final long serialVersionUID = 7541039066884580615L;
	
	
	private String code;
	
	private String nameFrench;
	
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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AssignmentStatusEntity [");
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
