package ca.gb.sf.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Object that defines the roles associated to users, educators and students.
 * Used for security purposes.
 */

@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(columnNames = "name") )
public class RoleEntity extends PersistentObject implements Comparable<RoleEntity> {

	private String name;

	public RoleEntity() {
	}

	public RoleEntity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleEntity other = (RoleEntity) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role{" + "id=" + getId() + ", name='" + name + '\'' + '}';
	}

	@Override
	public int compareTo(RoleEntity arg0) {

		return getName().compareTo(arg0.getName());

	}
}
