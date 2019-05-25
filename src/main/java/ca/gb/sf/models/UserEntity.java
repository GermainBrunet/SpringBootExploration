package ca.gb.sf.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * User represents an individual using this system. Can be assigned different
 * roles that represent different levels of access. Is extended by the Educator
 * and Student classes.
 * 
 * Normal users that register with the system will be registered with this class
 * and have access to publicly available assignments.
 */

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "user_type")
public class UserEntity extends PersistentObject {

	// Name that will be visually displayed representing this user.
	protected String displayName;
	
	// Email address associated with this user.  Used for password recovery.
	protected String email;
	
	// Password to identify this user.
	protected String password;

	@OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Collection<AssignmentEntity> assignment;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "users_roles", 
			joinColumns = { @JoinColumn(name = "user_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "role_id") })
		// joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
		// inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<RoleEntity> roles = new HashSet<RoleEntity>();

	// Constructor
	public UserEntity() {
	}

	// Constructor
	public UserEntity(String displayName, String email, String password) {
		this.displayName = displayName;
		this.email = email;
		this.password = password;
	}

	// Constructor
	public UserEntity(String displayName, String email, String password, Set<RoleEntity> roles) {
		this.displayName = displayName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	/**
	 * Getter for the display name.
	 * 
	 * @return
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Setter for the display name.
	 * 
	 * @param displayName
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Getter for the email.
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter for the email.
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter for the password.
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter for the password.
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter for the roles.
	 * 
	 * @return
	 */
	public Collection<RoleEntity> getRoles() {
		return roles;
	}

	/**
	 * Setter for the roles.
	 * 
	 * @param roles
	 */
	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	/**
	 * Getter for assignments
	 * 
	 * @return
	 */
	public Collection<AssignmentEntity> getAssignment() {
		return assignment;
	}

	/**
	 * Setter for assignments
	 * 
	 * @param assignment
	 */
	public void setAssignment(Collection<AssignmentEntity> assignment) {
		this.assignment = assignment;
	}
	
	/**
	 * String representation of this object. Includes the parent object toString.
	 * Used for debugging purposes. 
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [");
		if (displayName != null)
			builder.append("displayName=").append(displayName).append(", ");
		if (email != null)
			builder.append("email=").append(email).append(", ");
		if (password != null)
			builder.append("password=").append(password).append(", ");
		// if (roles != null)
		// 	builder.append("roles=").append(roles).append(", ");
		if (super.toString() != null)
			builder.append("toString()=").append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
