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

@MappedSuperclass
public class PersistentObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="create_user_id")
    private UserEntity createUser;

    @Column
    private Timestamp createTimestamp;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="update_user_id")
    private UserEntity updateUser;

    @Column
    private Timestamp updateTimestamp;

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
			builder.append("createUser=").append(createUser).append(", ");
		if (createTimestamp != null)
			builder.append("createTimestamp=").append(createTimestamp).append(", ");
		if (updateUser != null)
			builder.append("updateUser=").append(updateUser).append(", ");
		if (updateTimestamp != null)
			builder.append("updateTimestamp=").append(updateTimestamp);
		builder.append("]");
		return builder.toString();
	}
	
}
