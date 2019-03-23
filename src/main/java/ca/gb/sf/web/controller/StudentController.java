package ca.gb.sf.web.controller;

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

import ca.gb.sf.models.Educator;
import ca.gb.sf.models.Exercise;
import ca.gb.sf.models.Student;
import ca.gb.sf.repositories.ExerciseRepository;
import ca.gb.sf.repositories.UserRepository;
import ca.gb.sf.util.PageWrapper;
import ca.gb.sf.web.form.SearchForm;
import ca.gb.sf.web.form.StudentForm;
import ca.gb.sf.web.form.UserRegistrationForm;
import ca.gb.sf.web.service.ExerciseService;
import ca.gb.sf.web.service.UserService;

@Controller
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("student")
    public StudentForm studentDTO() {
        return new StudentForm();
    }
	
    @GetMapping("/studentAdd")
    public String studentAdd(Model model) {
    	
    	return "studentAdd";
        
    }

    @GetMapping("/studentEdit/{id}")
    public String studentEdit(@PathVariable("id") long id, Model model) {
    	
    	Student student = (Student) userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid exercise Id:" + id));
    	
    	StudentForm studentForm = new StudentForm();
    	
    	studentForm.setDisplayName(student.getDisplayName());
    	
    	model.addAttribute("student", studentForm);
    	
    	return "studentEdit";
        
    }
    
    @PostMapping("/studentSave")
    public String educatorSave(@ModelAttribute("student") @Valid StudentForm studentForm,
            BindingResult result) {

    	userService.saveStudent(studentForm);

        return "redirect:/educatorPage";

    }
    

    
}
