package ca.gb.sf.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.gb.sf.models.EducatorEntity;
import ca.gb.sf.models.ExerciseEntity;
import ca.gb.sf.models.StudentEntity;
import ca.gb.sf.repositories.ExerciseRepository;
import ca.gb.sf.repositories.UserRepository;
import ca.gb.sf.util.PageWrapper;
import ca.gb.sf.web.service.ExerciseGroupWebService;

@Controller
public class EducatorController {

	@Autowired
    ExerciseGroupWebService exerciseService;

	@Autowired
    ExerciseRepository exerciseRepository;
	
	@Autowired
	UserRepository userRepository;

    @GetMapping("/educatorPage")
    public String educatorPage(@PageableDefault(size = 3) Pageable pageable, Model model) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	EducatorEntity educator = (EducatorEntity) userRepository.findByEmail(auth.getName());
    	
    	model.addAttribute("educator", educator);
    	
    	Page<StudentEntity> studentPage = userRepository.findByEducator(pageable, educator);
    	
    	PageWrapper<StudentEntity> students = new PageWrapper<StudentEntity> (studentPage, "/educatorPage");
    	
    	model.addAttribute("page", students);
    	
        return "educatorPage";
        
    }
    
    @GetMapping("/educatorEdit")
    public String educatorEdit(Model model) {

    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	EducatorEntity educator = (EducatorEntity) userRepository.findByEmail(auth.getName());

    	model.addAttribute("educator", educator);

        return "educatorEdit";

    }
    

    
}
