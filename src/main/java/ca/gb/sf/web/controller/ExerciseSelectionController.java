package ca.gb.sf.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.gb.sf.models.Assignment;
import ca.gb.sf.models.Exercise;
import ca.gb.sf.models.ExerciseGroup;
import ca.gb.sf.models.Student;
import ca.gb.sf.repositories.AssignmentRepository;
import ca.gb.sf.repositories.ExerciseGroupRepository;
import ca.gb.sf.repositories.ExerciseRepository;
import ca.gb.sf.repositories.UserRepository;
import ca.gb.sf.util.PageWrapper;
import ca.gb.sf.web.form.ExerciseGroupSelectionForm;
import ca.gb.sf.web.form.StudentForm;
import ca.gb.sf.web.service.UserService;

@Controller
public class ExerciseSelectionController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ExerciseRepository exerciseRepository;

	@Autowired
	private ExerciseGroupRepository exerciseGroupRepository;

	@Autowired
	private AssignmentRepository assignmentRepository;

	@ModelAttribute("excercises")
	public ExerciseGroupSelectionForm exerciseSelectionForm() {
		return new ExerciseGroupSelectionForm();
	}

	@GetMapping("/studentExercise/{id}")
	public String studentExercise(@PathVariable("id") long id, @PageableDefault(size = 10) Pageable pageable,
			@ModelAttribute("exerciseSelectionForm") ExerciseGroupSelectionForm exerciseSelectionForm, Model model) {

		Student student = (Student) userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid exercise Id:" + id));

		StudentForm studentForm = new StudentForm();

		studentForm.setId(String.valueOf(student.getId()));

		studentForm.setDisplayName(student.getDisplayName());

		// model.addAttribute("excercises", new ExerciseSelectionForm());

		model.addAttribute("student", studentForm);
		
		exerciseSelectionForm.setStudentId(String.valueOf(student.getId()));

		Page<ExerciseGroup> exerciseGroupPage = exerciseGroupRepository.findAll(pageable);

		PageWrapper<ExerciseGroup> exerciseGroups = new PageWrapper<ExerciseGroup>(exerciseGroupPage, "/studentExercise/" + student.getId());

		model.addAttribute("page", exerciseGroups);

		List<Assignment> assignments = assignmentRepository.findListByStudent(student);

		List<String> assignmentIds = new ArrayList<String>();
		
		for (Assignment assignment : assignments) {
			assignmentIds.add(String.valueOf(assignment.getExerciseGroup().getId()));
		}

		model.addAttribute("assignmentIds", assignmentIds);

		return "studentExercise";

	}

	@PostMapping("/exerciseSelectionSave")
	public String save(@ModelAttribute("exerciseSelectionForm") ExerciseGroupSelectionForm exerciseSelectionForm,
			BindingResult result) {

		System.out.println(exerciseSelectionForm);
		
		userService.saveSelectedExercises(exerciseSelectionForm);
		
		return "redirect:/studentExercise/" + exerciseSelectionForm.getStudentId();
		
	}

}
