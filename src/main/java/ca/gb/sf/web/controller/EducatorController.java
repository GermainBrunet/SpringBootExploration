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
import org.springframework.web.bind.annotation.ModelAttribute;

import ca.gb.sf.models.EducatorEntity;
import ca.gb.sf.models.UserEntity;
import ca.gb.sf.repositories.ExerciseRepository;
import ca.gb.sf.repositories.UserRepository;
import ca.gb.sf.services.EducatorService;
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
	
	@Autowired
	EducatorService educatorService;

	private EducatorEntity educator;
	
	@ModelAttribute("educator") 
	public EducatorEntity EducatorEntity() {
		
		educator = educatorService.getCurrentEducator(); 
		
		return educator;
	}
	
    @GetMapping("/educatorPage")
    public String educatorPage(@PageableDefault(size = 3) Pageable pageable, Model model) {
    	
    	Page<UserEntity> studentPage = educatorService.studentsByEducator(pageable);
    	
    	PageWrapper<UserEntity> students = new PageWrapper<UserEntity> (studentPage, "/educatorPage");
    	
    	model.addAttribute("page", students);
    	
        return "educatorPage";
        
    }
    
    @GetMapping("/educatorEdit")
    public String educatorEdit(Model model) {

        return "educatorEdit";

    }
    

    
}
