package ca.gb.sf.utility;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.SpringContextIntegrationTest;
import ca.gb.sf.models.Exercise;
import ca.gb.sf.models.ExerciseGroup;
import ca.gb.sf.repositories.ExerciseGroupRepository;
import ca.gb.sf.repositories.ExerciseRepository;

public class SetupTest extends SpringContextIntegrationTest {

	@Autowired
	ExerciseRepository exerciseRepository;

	@Autowired
	ExerciseGroupRepository exerciseGroupRepository;
	
	@Test
	public void setup() {
		
		deleteAllData();
		createExercise1();
		createExercise2();
		createExercise3();
		createExercise4();
		
	}
	
	public void deleteAllData() {
		
		exerciseRepository.deleteAll();
		exerciseGroupRepository.deleteAll();
		
	}
	
	public void createExercise1() {
		
		List<Exercise> exerciseList = new ArrayList<Exercise>();
		
		exerciseList.add(new Exercise("ma", "mi", "transforme le mot", 1));
		exerciseList.add(new Exercise("mi", "mo", "transforme le mot", 2));
		exerciseList.add(new Exercise("mo", "me", "transforme le mot", 3));
		exerciseList.add(new Exercise("me", "mu", "transforme le mot", 4));
		exerciseList.add(new Exercise("mu", "lu", "transforme le mot", 5));
		exerciseList.add(new Exercise("lu", "li", "transforme le mot", 6));
		exerciseList.add(new Exercise("li", "la", "transforme le mot", 7));
		exerciseList.add(new Exercise("la", "lo", "transforme le mot", 8));
		exerciseList.add(new Exercise("lo", "le", "transforme le mot", 9));
		exerciseList.add(new Exercise("le", "me", "transforme le mot", 10));

		ExerciseGroup exerciseGroup = new ExerciseGroup();
		exerciseGroup.setName("ma 1.1");
		exerciseGroup.setExcercises(exerciseList);
		exerciseGroupRepository.save(exerciseGroup);
		
	}
	
	public void createExercise2() {
		
		List<Exercise> exerciseList = new ArrayList<Exercise>();
		
		exerciseList.add(new Exercise("la", "ma", "transforme le mot", 1));
		exerciseList.add(new Exercise("ma", "fa", "transforme le mot", 2));
		exerciseList.add(new Exercise("fa", "fi", "transforme le mot", 3));
		exerciseList.add(new Exercise("fi", "mi", "transforme le mot", 4));
		exerciseList.add(new Exercise("mi", "li", "transforme le mot", 5));
		exerciseList.add(new Exercise("li", "lo", "transforme le mot", 6));
		exerciseList.add(new Exercise("lo", "fo", "transforme le mot", 7));
		exerciseList.add(new Exercise("fo", "mo", "transforme le mot", 8));
		exerciseList.add(new Exercise("mo", "lo", "transforme le mot", 9));
		exerciseList.add(new Exercise("lo", "li", "transforme le mot", 10));

		ExerciseGroup exerciseGroup = new ExerciseGroup();
		exerciseGroup.setName("la 1.2");
		exerciseGroup.setExcercises(exerciseList);
		exerciseGroupRepository.save(exerciseGroup);
		
	}

	public void createExercise3() {
		
		List<Exercise> exerciseList = new ArrayList<Exercise>();
		
		exerciseList.add(new Exercise("lafo", "lafa", "transforme le mot", 1));
		exerciseList.add(new Exercise("lafa", "lafi", "transforme le mot", 2));
		exerciseList.add(new Exercise("lafi", "lami", "transforme le mot", 3));
		exerciseList.add(new Exercise("lami", "lali", "transforme le mot", 4));
		exerciseList.add(new Exercise("lali", "lalo", "transforme le mot", 5));
		exerciseList.add(new Exercise("lalo", "lamo", "transforme le mot", 6));
		exerciseList.add(new Exercise("lamo", "lamu", "transforme le mot", 7));
		exerciseList.add(new Exercise("lamu", "amu", "transforme le mot", 8));
		exerciseList.add(new Exercise("amu", "afu", "transforme le mot", 9));
		exerciseList.add(new Exercise("afu", "afo", "transforme le mot", 10));

		ExerciseGroup exerciseGroup = new ExerciseGroup();
		exerciseGroup.setName("lafo 1.3");
		exerciseGroup.setExcercises(exerciseList);
		exerciseGroupRepository.save(exerciseGroup);
		
	}

	public void createExercise4() {
		
		List<Exercise> exerciseList = new ArrayList<Exercise>();
		
		exerciseList.add(new Exercise("malo", "malu", "transforme le mot", 1));
		exerciseList.add(new Exercise("malu", "mala", "transforme le mot", 2));
		exerciseList.add(new Exercise("mala", "mala", "transforme le mot", 3));
		exerciseList.add(new Exercise("mala", "mafa", "transforme le mot", 4));
		exerciseList.add(new Exercise("mafa", "mafi", "transforme le mot", 5));
		exerciseList.add(new Exercise("mafi", "mafo", "transforme le mot", 6));
		exerciseList.add(new Exercise("mafo", "mafu", "transforme le mot", 7));
		exerciseList.add(new Exercise("mafu", "afu", "transforme le mot", 8));
		exerciseList.add(new Exercise("afu", "afi", "transforme le mot", 9));
		exerciseList.add(new Exercise("afi", "mafi", "transforme le mot", 10));

		ExerciseGroup exerciseGroup = new ExerciseGroup();
		exerciseGroup.setName("malo 1.4");
		exerciseGroup.setExcercises(exerciseList);
		exerciseGroupRepository.save(exerciseGroup);
		
	}

}
