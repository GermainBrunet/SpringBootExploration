package ca.gb.sf.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Parent object from which all other objects are derived. Provides a common id
 * for all objects. Provides Create and Update <code>timestamp</code> and
 * <code>user</code> objects for tracking purposes.
 */

@MappedSuperclass
public class AuditedObject extends PersistentObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
		builder.append("PersistentObject [id=").append(id).append(", ");
		if (createUser != null)
			builder.append("createUser=").append(createUser.getId()).append(", ");
		if (createTimestamp != null)
			builder.append("createTimestamp=").append(createTimestamp).append(", ");
		if (updateUser != null)
			builder.append("updateUser=").append(updateUser.getId()).append(", ");
		if (updateTimestamp != null)
			builder.append("updateTimestamp=").append(updateTimestamp);
		builder.append("]");
		return builder.toString();
	}

}
