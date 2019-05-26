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
import ca.gb.sf.services.AssignmentService;
import ca.gb.sf.services.ExerciseGroupService;
import ca.gb.sf.services.ExerciseService;
import ca.gb.sf.util.PageWrapper;
import ca.gb.sf.web.form.SearchForm;
import ca.gb.sf.web.service.ExerciseGroupWebService;

@Controller
public class ExerciseController {

	@Autowired
    ExerciseGroupService exerciseGroupService;

	@Autowired
    ExerciseService exerciseService;
	
	@Autowired
	AssignmentService assignmentService;
	
	@Autowired
	UserRepository userRepository;

	@Transactional
    @GetMapping("/exerciseList")
    public String exerciseList(@PageableDefault(size = 5) Pageable pageable, SearchForm searchForm, Model model) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	StudentEntity student = (StudentEntity) userRepository.findByEmail(auth.getName());
    	
    	String searchString = searchForm.getSearch();
    	
    	Page<ExerciseGroupEntity> exerciseGroupPage = exerciseGroupService.findPaginatedByStudent(student, pageable, searchString);
    	
    	PageWrapper<ExerciseGroupEntity> exerciseGroups = new PageWrapper<ExerciseGroupEntity> (exerciseGroupPage, "/exerciseList");
    	
        model.addAttribute("page", exerciseGroups);

    	model.addAttribute("searchForm", new SearchForm());        
        
        return "exerciseList";
    }
    
    @GetMapping("/exercisePage/{exerciseGroupId}/{exerciseId}")
    public String product(@PathVariable("exerciseGroupId") long exerciseGroupId, @PathVariable("exerciseId") int exerciseId, Model model) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	StudentEntity student = (StudentEntity) userRepository.findByEmail(auth.getName());
    	
    	System.out.println("1: " + student.getId());
    	
    	ExerciseGroupEntity exerciseGroup = exerciseGroupService.findById(exerciseGroupId);
    	
    	// AssignmentEntity assignment = assignmentService.findByStudentAndAssignmentId(student, new Long(assignmentId));

    	// ExerciseGroupEntity exerciseGroup = assignment.getExerciseGroup();
    	
    	System.out.println("2");

    	// if (assignment == null) {
    		
    	//	throw new IllegalArgumentException("Invalid assignment Id:" + assignmentId);
    		
    	// }

    	// System.out.println("3 " + assignment.getExerciseGroup().getId());

    	// ExerciseEntity e1 = exerciseService.findFirst(exerciseGroup);
    	
    	List<ExerciseEntity> exercises = exerciseService.findByExerciseGroup(exerciseGroup);
    	
    	System.out.println("size: " + exercises.size());
    	
    	ExerciseEntity exerciseEntity = null;
    	Boolean lastExercise = false;
    	int previousExercise = exerciseId - 1;
    	int nextExercise = exerciseId + 1;
    	
    	if (previousExercise < 0) {
    		
    		previousExercise = 0;
    		
    	}
    	
    	if (exerciseId <= exercises.size()) {
    		
    		System.out.println("Setting exercise");
    		
    		exerciseEntity = exercises.get(exerciseId);
    		
    	}
    	
    	if ((exerciseId + 1) == exercises.size()) {
    		
    		lastExercise = true;
    		nextExercise = exercises.size() - 1;
    		
    	}
    	
    	model.addAttribute("exercise", exerciseEntity);
        model.addAttribute("lastExercise", lastExercise);
        model.addAttribute("currentExercise", exerciseId);
        model.addAttribute("previousExercise", previousExercise);
        model.addAttribute("nextExercise", nextExercise);
        model.addAttribute("exerciseGroupId", exerciseGroupId);
        model.addAttribute("exerciseGroup", exerciseGroup);
        
        // model.addAttribute("assignmentId", assignmentId);
        
    	model.addAttribute("searchForm", new SearchForm()); 
    	
        return "exercisePage";
    }
    

    
}
