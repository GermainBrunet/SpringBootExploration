package ca.gb.sf.web.form;

import java.util.Arrays;

public class ExerciseGroupSelectionForm {

	String studentId;
	
	String[] selectedGroupExercises;

	String[] allGroupExercises;
	
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String[] getSelectedGroupExercises() {
		return selectedGroupExercises;
	}

	public void setSelectedGroupExercises(String[] selectedGroupExercises) {
		this.selectedGroupExercises = selectedGroupExercises;
	}

	public String[] getAllGroupExercises() {
		return allGroupExercises;
	}

	public void setAllGroupExercises(String[] allGroupExercises) {
		this.allGroupExercises = allGroupExercises;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExerciseSelectionForm [");
		if (studentId != null)
			builder.append("studentId=").append(studentId).append(", ");
		if (selectedGroupExercises != null)
			builder.append("selectedGroupExercises=").append(Arrays.toString(selectedGroupExercises)).append(", ");
		if (allGroupExercises != null)
			builder.append("allGroupExercises=").append(Arrays.toString(allGroupExercises));
		builder.append("]");
		return builder.toString();
	}

}
