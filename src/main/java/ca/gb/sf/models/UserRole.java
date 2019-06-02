package ca.gb.sf.models;

/**
 * Enum used to describe roles within the application.
 */
public enum UserRole {

	User("ROLE_USER", "USER"),
	Educator("ROLE_EDUCATOR", "EDUCATOR"),
	Admin("ROLE_ADMIN", "ADMIN");
	
	UserRole(String securityRoleName, String formName) {
		
		this.securityRoleName = securityRoleName;
		this.formName = formName;
		
	}
	
	private String securityRoleName;
	
	private String formName;

	public String getSecurityRoleName() {
		return securityRoleName;
	}

	public void setSecurityRoleName(String securityRoleName) {
		this.securityRoleName = securityRoleName;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}
	
}
