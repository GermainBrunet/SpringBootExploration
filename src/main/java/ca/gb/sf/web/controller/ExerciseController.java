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

import ca.gb.sf.models.AssignmentEntity;
import ca.gb.sf.models.EducatorEntity;
import ca.gb.sf.models.ExerciseEntity;
import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.StudentEntity;
import ca.gb.sf.repositories.AssignmentRepository;
import ca.gb.sf.repositories.ExerciseRepository;
import ca.gb.sf.repositories.UserRepository;
import ca.gb.sf.util.PageWrapper;
import ca.gb.sf.web.form.SearchForm;
import ca.gb.sf.web.service.ExerciseGroupWebService;

@Controller
public class ExerciseController {

	@Autowired
    ExerciseGroupWebService exerciseGroupService;

	@Autowired
    ExerciseRepository exerciseRepository;
	
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Autowired
	UserRepository userRepository;

	@Transactional
    @GetMapping("/exerciseList")
    public String exerciseList(@PageableDefault(size = 5) Pageable pageable, SearchForm searchForm, Model model) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	StudentEntity student = (StudentEntity) userRepository.findByEmail(auth.getName());
    	
    	String searchString = searchForm.getSearch();
    	
    	Page<ExerciseGroupEntity> exerciseGroupPage = exerciseGroupService.findPaginatedByStudent(student, pageable, searchString);
    	
    	PageWrapper<ExerciseGroupEntity> exercises = new PageWrapper<ExerciseGroupEntity> (exerciseGroupPage, "/exerciseList");
    	
        model.addAttribute("page", exercises);

    	model.addAttribute("searchForm", new SearchForm());        
        
        return "exerciseList";
    }
    
    @GetMapping("/exercisePage/{assignmentId}/{exerciseId}")
    public String product(@PathVariable("assignmentId") long assignmentId, @PathVariable("exerciseId") int exerciseId, Model model) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	StudentEntity student = (StudentEntity) userRepository.findByEmail(auth.getName());
    	
    	System.out.println("1: " + student.getId());
    	
    	AssignmentEntity assignment = assignmentRepository.findByStudentAndAssignmentId(student, new Long(assignmentId));

    	System.out.println("2");

    	if (assignment == null) {
    		
    		throw new IllegalArgumentException("Invalid assignment Id:" + assignmentId);
    		
    	}

    	System.out.println("3 " + assignment.getExerciseGroup().getId());

    	// List<Exercise> exercises = assignment.getExerciseGroup().getExcercises();
    	List<ExerciseEntity> exercises = exerciseRepository.findByExerciseGroup(assignment.getExerciseGroup());

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
