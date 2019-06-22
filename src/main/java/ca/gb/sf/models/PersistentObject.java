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
public class PersistentObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StaticObject [id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

}
