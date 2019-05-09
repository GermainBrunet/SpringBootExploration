package ca.gb.sf.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.gb.sf.models.Assignment;
import ca.gb.sf.models.Educator;
import ca.gb.sf.models.Exercise;
import ca.gb.sf.models.Student;
import ca.gb.sf.repositories.AssignmentRepository;
import ca.gb.sf.repositories.ExerciseRepository;
import ca.gb.sf.repositories.UserRepository;
import ca.gb.sf.util.PageWrapper;
import ca.gb.sf.web.form.SearchForm;
import ca.gb.sf.web.form.StudentForm;
import ca.gb.sf.web.form.UserRegistrationForm;
import ca.gb.sf.web.service.ExerciseGroupService;
import ca.gb.sf.web.service.UserService;

@Controller
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @ModelAttribute("student")
    public StudentForm studentDTO() {
        return new StudentForm();
    }
	
    @GetMapping("/studentAdd")
    public String studentAdd(Model model) {
    	
    	return "studentAdd";
        
    }

    @GetMapping("/studentEdit/{id}")
    public String studentEdit(@PathVariable("id") long id, @PageableDefault(size = 10) Pageable pageable, Model model) {
    	
    	Student student = (Student) userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid exercise Id:" + id));
    	
    	StudentForm studentForm = new StudentForm();
    	
    	studentForm.setId(String.valueOf(student.getId()));
    	
    	studentForm.setDisplayName(student.getDisplayName());
    	
    	model.addAttribute("student", studentForm);
    	
    	Page<Assignment> assignmentPage = assignmentRepository.findByStudent(pageable, student);
    	
    	PageWrapper<Assignment> assignments = new PageWrapper<Assignment> (assignmentPage, "/assignmentPage");
    	
    	model.addAttribute("assignments", assignments);

    	return "studentEdit";
        
    }
    
    @PostMapping("/studentSave")
    public String educatorSave(@ModelAttribute("student") @Valid StudentForm studentForm,
            BindingResult result) {

    	userService.saveStudent(studentForm);

        return "redirect:/educatorPage";

    }
    
    @GetMapping("/studentDelete/{id}")
    public String studentDelete(@PathVariable("id") long id, Model model) {
    	
    	Student student = (Student) userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid exercise Id:" + id));
    	
    	userRepository.delete(student);
    	
        return "redirect:/educatorPage";
        
    }
    
    @GetMapping("/studentAssign/{id}")
    public String studentAssign(@PathVariable("id") long id, @PageableDefault(size = 10) Pageable pageable, Model model) {
    	
    	Student student = (Student) userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid exercise Id:" + id));
    	
    	StudentForm studentForm = new StudentForm();
    	
    	studentForm.setId(String.valueOf(student.getId()));
    	
    	studentForm.setDisplayName(student.getDisplayName());
    	
    	model.addAttribute("student", studentForm);
    	
    	Page<Assignment> assignmentPage = assignmentRepository.findByStudent(pageable, student);
    	
    	PageWrapper<Assignment> assignments = new PageWrapper<Assignment> (assignmentPage, "/assignmentPage");
    	
    	model.addAttribute("page", assignments);

    	return "studentAssign";
        
    }


    
}
