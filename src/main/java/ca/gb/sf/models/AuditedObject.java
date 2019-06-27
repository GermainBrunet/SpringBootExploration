package ca.gb.sf.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Audited object that provides the following features: created user and time
 * stamp, update user and time stamp. Should be extended by objects that are
 * under user controlled. (i.e.: Users, Exercises, Assignments, etc.)
 */

@MappedSuperclass
public class AuditedObject extends PersistentObject {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "create_user_id")
	protected UserEntity createUser;

	@Column
	protected Timestamp createTimestamp;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "update_user_id")
	protected UserEntity updateUser;

	@Column
	protected Timestamp updateTimestamp;

	public UserEntity getCreateUser() {
		return createUser;
	}

	public void setCreateUser(UserEntity createUser) {
		this.createUser = createUser;
	}

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public UserEntity getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(UserEntity updateUser) {
		this.updateUser = updateUser;
	}

	public Timestamp getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Timestamp updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (super.toString() != null)
			builder.append(super.toString());
		if (createUser != null)
			builder.append("createUser=").append(createUser.getId()).append(", ");
		if (createTimestamp != null)
			builder.append("createTimestamp=").append(createTimestamp).append(", ");
		if (updateUser != null)
			builder.append("updateUser=").append(updateUser.getId()).append(", ");
		if (updateTimestamp != null)
			builder.append("updateTimestamp=").append(updateTimestamp).append(", ");;
		return builder.toString();
	}

}
