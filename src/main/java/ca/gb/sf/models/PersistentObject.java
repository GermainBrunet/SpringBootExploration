package ca.gb.sf.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Parent object from which all other persisted objects are derived. Provides
 * reusable id for all objects.  Should be extended by objects that are outside user control.  (i.e.: Roles, Assignment Status)
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
		builder.append("id=");
		builder.append(id);
		builder.append(", ");
		return builder.toString();
	}

}
