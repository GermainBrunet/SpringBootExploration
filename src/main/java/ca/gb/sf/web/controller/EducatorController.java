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
import org.springframework.web.bind.annotation.PathVariable;

import ca.gb.sf.models.Educator;
import ca.gb.sf.models.Exercise;
import ca.gb.sf.repositories.ExerciseRepository;
import ca.gb.sf.repositories.UserRepository;
import ca.gb.sf.util.PageWrapper;
import ca.gb.sf.web.form.SearchForm;
import ca.gb.sf.web.service.ExerciseService;

@Controller
public class EducatorController {

	@Autowired
    ExerciseService exerciseService;

	@Autowired
    ExerciseRepository exerciseRepository;
	
	@Autowired
	UserRepository userRepository;

    @GetMapping("/educatorPage")
    public String product(Model model) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	System.out.println("user name = " + auth.getName());
    	
    	// Educator educator = (Educator) userRepository.findByDisplayName(auth.getName());
    	Educator educator = (Educator) userRepository.findByEmail(auth.getName());
    	
    	model.addAttribute("educator", educator); 
    	
        return "educatorPage";
    }
    

    
}
