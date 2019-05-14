package ca.gb.sf.web.controller;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.gb.sf.models.Assignment;
import ca.gb.sf.models.Educator;
import ca.gb.sf.models.Exercise;
import ca.gb.sf.models.ExerciseGroup;
import ca.gb.sf.models.Student;
import ca.gb.sf.repositories.AssignmentRepository;
import ca.gb.sf.repositories.ExerciseRepository;
import ca.gb.sf.repositories.UserRepository;
import ca.gb.sf.util.PageWrapper;
import ca.gb.sf.web.form.SearchForm;
import ca.gb.sf.web.service.ExerciseGroupService;

@Controller
public class ExerciseController {

	@Autowired
    ExerciseGroupService exerciseGroupService;

	@Autowired
    ExerciseRepository exerciseRepository;
	
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Autowired
	UserRepository userRepository;

	@Transactional
    @GetMapping("/exerciseList")
    public String excerciseList(@PageableDefault(size = 5) Pageable pageable, SearchForm searchForm, Model model) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	Student student = (Student) userRepository.findByEmail(auth.getName());
    	
    	String searchString = searchForm.getSearch();
    	
    	Page<ExerciseGroup> exerciseGroupPage = exerciseGroupService.findPaginatedByStudent(student, pageable, searchString);
    	
    	PageWrapper<ExerciseGroup> exercises = new PageWrapper<ExerciseGroup> (exerciseGroupPage, "/exerciseList");
    	
        model.addAttribute("page", exercises);

    	model.addAttribute("searchForm", new SearchForm());        
        
        return "exerciseList";
    }
    
    @GetMapping("/exercisePage/{assignmentId}/{exerciseId}")
    public String product(@PathVariable("assignmentId") long assignmentId, @PathVariable("exerciseId") int exerciseId, Model model) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	Student student = (Student) userRepository.findByEmail(auth.getName());
    	
    	System.out.println("1: " + student.getId());
    	
    	Assignment assignment = assignmentRepository.findByStudentAndAssignmentId(student, new Long(assignmentId));

    	System.out.println("2");

    	if (assignment == null) {
    		
    		throw new IllegalArgumentException("Invalid assignment Id:" + assignmentId);
    		
    	}

    	System.out.println("3 " + assignment.getExerciseGroup().getId());

    	// List<Exercise> exercises = assignment.getExerciseGroup().getExcercises();
    	List<Exercise> exercises = exerciseRepository.findByExerciseGroup(assignment.getExerciseGroup());

    	// Hibernate.initialize(exercises);
    	
    	System.out.println("4 " + exercises.size() );
    	
    	if (exerciseId > exercises.size()) {
    		
    		throw new IllegalArgumentException("Invalid exercise Id:" + exerciseId);
    		
    	}

    	System.out.println("5");

        model.addAttribute("exercise", exercises.get(exerciseId));
    	
    	// Exercise exercise = exerciseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid exercise Id:" + id));
    	
    	model.addAttribute("searchForm", new SearchForm()); 
    	
        return "exercisePage";
    }
    

    
}
