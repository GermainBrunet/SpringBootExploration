package ca.gb.sf.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.gb.sf.models.Exercise;
import ca.gb.sf.repositories.ExerciseRepository;
import ca.gb.sf.util.PageWrapper;
import ca.gb.sf.web.form.SearchForm;
import ca.gb.sf.web.service.ExerciseService;

@Controller
public class ExerciseController {

	@Autowired
    ExerciseService exerciseService;

	@Autowired
    ExerciseRepository exerciseRepository;

    @GetMapping("/exerciseList")
    public String excerciseList(@PageableDefault(size = 5) Pageable pageable, SearchForm searchForm, Model model) {
    	
    	System.out.println("here!");
    	
    	String searchString = searchForm.getSearch();
    	
    	Page<Exercise> exercisePage = exerciseService.findPaginated(pageable, searchString);
    	
    	PageWrapper<Exercise> exercises = new PageWrapper<Exercise> (exercisePage, "/exerciseList");
    	
        model.addAttribute("page", exercises);

    	model.addAttribute("searchForm", new SearchForm());        
        
        return "exerciseList";
    }
    
    @GetMapping("/exercisePage/{id}")
    public String product(@PathVariable("id") long id, Model model) {
    	
    	Exercise exercise = exerciseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid exercise Id:" + id));
    	model.addAttribute("exercise", exercise);
    	
    	model.addAttribute("searchForm", new SearchForm()); 
    	
        return "exercisePage";
    }
    

    
}
