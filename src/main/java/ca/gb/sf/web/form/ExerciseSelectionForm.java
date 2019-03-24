package ca.gb.sf.web.form;

import java.util.Arrays;

public class ExerciseSelectionForm {

	String studentId;
	
	String[] selectedExercises;

	String[] allExercises;
	
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String[] getSelectedExercises() {
		return selectedExercises;
	}

	public void setSelectedExercises(String[] selectedExercises) {
		this.selectedExercises = selectedExercises;
	}

	public String[] getAllExercises() {
		return allExercises;
	}

	public void setAllExercises(String[] allExercises) {
		this.allExercises = allExercises;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExerciseSelectionForm [");
		if (studentId != null)
			builder.append("studentId=").append(studentId).append(", ");
		if (selectedExercises != null)
			builder.append("selectedExercises=").append(Arrays.toString(selectedExercises)).append(", ");
		if (allExercises != null)
			builder.append("allExercises=").append(Arrays.toString(allExercises));
		builder.append("]");
		return builder.toString();
	}


	
}
