package ca.gb.sf.web.form;

import java.util.Collection;

import javax.validation.constraints.NotEmpty;

import ca.gb.sf.constraint.FieldMatch;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
})
public class StudentForm {

	private String id;
	
    @NotEmpty
    private String displayName;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;
    
    private Collection<String> exerciseGroupIds;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

	public Collection<String> getExerciseGroupIds() {
		return exerciseGroupIds;
	}

	public void setExerciseGroupIds(Collection<String> exerciseGroupIds) {
		this.exerciseGroupIds = exerciseGroupIds;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StudentForm [");
		if (id != null)
			builder.append("id=").append(id).append(", ");
		if (displayName != null)
			builder.append("displayName=").append(displayName).append(", ");
		if (password != null)
			builder.append("password=").append(password).append(", ");
		if (confirmPassword != null)
			builder.append("confirmPassword=").append(confirmPassword).append(", ");
		if (exerciseGroupIds != null)
			builder.append("exerciseGroupIds=").append(exerciseGroupIds);
		builder.append("]");
		return builder.toString();
	}


}
