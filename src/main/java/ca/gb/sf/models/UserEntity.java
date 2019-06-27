package ca.gb.sf.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
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
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "displayName"))
// @Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "user_type")
public class UserEntity extends AuditedObject implements Comparable<UserEntity>, Serializable {

	private static final long serialVersionUID = 6030006060053082867L;

	// Name that will be visually displayed representing this user.
	@NotNull
	@Column(name="displayName", nullable = false)
	protected String displayName;
	
	// Email address associated with this user.  Used for password recovery.
	@NotNull
	@Column(nullable = false)
	protected String email;
	
	// Password to identify this user.
	@NotNull
	@Column(nullable = false)
	protected String password;

	// // @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	// @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Collection<AssignmentEntity> assignment;
	
	@ManyToMany(fetch = FetchType.EAGER)
	// @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	// @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//	@ManyToMany(fetch = FetchType.EAGER,
//	        cascade =
//	        {
//	                CascadeType.DETACH,
//	                CascadeType.MERGE,
//	                CascadeType.REFRESH,
//	                CascadeType.PERSIST
//	        },
//	        targetEntity = RoleEntity.class)
	@JoinTable(name = "users_roles", 
			joinColumns = { @JoinColumn(name = "user_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "role_id") })
//	@JoinTable(name = "users_roles",
//    inverseJoinColumns = @JoinColumn(name = "role_id",
//            nullable = false,
//            updatable = false),
//    joinColumns = @JoinColumn(name = "user_id",
//            nullable = false,
//            updatable = false),
//    foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
//    inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
	// @OnDelete(action = OnDeleteAction.CASCADE)
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Long.hashCode(id);
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		UserEntity other = (UserEntity) obj;
		if (id != other.id)
			return false;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	**/

	@Override
	public int hashCode() {
		
		return new HashCodeBuilder()
				.append(this.id)
				.append(this.displayName)
				.append(this.email)
				.toHashCode();
		
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof UserEntity == false) {
			
			return false;
			
		}
		
		if (this == obj) {
			
			return true;
			
		}
		
		final UserEntity userEntity = (UserEntity) obj;

		return new EqualsBuilder()
				.append(this.id, userEntity.getId())
				.append(this.displayName, userEntity.getDisplayName())
				.append(this.email, userEntity.getEmail())
				.isEquals();
		
	}
	
	@Override
	public int compareTo(UserEntity userEntity) {
		
		return new CompareToBuilder()
				.append(this.getId(), userEntity.getId())
				.append(this.getDisplayName(), userEntity.getDisplayName())
				.append(this.getEmail(), userEntity.getEmail())
				.toComparison();
		
	}

	/**
	 * String representation of this object. Includes the parent object toString.
	 * Used for debugging purposes. 
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [");
		if (super.toString() != null)
			builder.append(super.toString());
		if (displayName != null) {
			builder.append("displayName=");
			builder.append(displayName);
			builder.append(", ");
		}
		if (email != null) {
			builder.append("email=");
			builder.append(email);
			builder.append(", ");
		}
		if (password != null) {
			builder.append("password=");
			builder.append(password);
			builder.append(", ");
		}
		if (roles != null) {
		 	builder.append("roles=");
		 	builder.append(roles);
		}
		builder.append("]");
		return builder.toString();
	}
	
}
