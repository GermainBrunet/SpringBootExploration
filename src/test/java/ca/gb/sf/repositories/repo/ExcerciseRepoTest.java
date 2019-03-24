package ca.gb.sf.repositories.repo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.SpringContextIntegrationTest;
import ca.gb.sf.models.Exercise;
import ca.gb.sf.repositories.ExerciseRepository;

public class ExcerciseRepoTest extends SpringContextIntegrationTest {

	@Autowired
	ExerciseRepository excerciseRepository;
	
	@Test
	public void createTestData() {
		
		excerciseRepository.save(new Exercise("eempxle", "exemple", "corrige le mot"));
		excerciseRepository.save(new Exercise("aovir", "avoir", "corrige le mot"));
		excerciseRepository.save(new Exercise("fiinr", "finir", "corrige le mot"));
		excerciseRepository.save(new Exercise("toujousr", "toujours", "corrige le mot"));
		excerciseRepository.save(new Exercise("lbanche", "blanche", "corrige le mot"));
		excerciseRepository.save(new Exercise("debon", "bedon", "corrige le mot"));
		excerciseRepository.save(new Exercise("urovir", "ouvrir", "corrige le mot"));
		excerciseRepository.save(new Exercise("mina", "main", "corrige le mot"));
		excerciseRepository.save(new Exercise("rcorir", "courrir", "corrige le mot"));
		excerciseRepository.save(new Exercise("iuuoua'hdrj", "aujourd'hui", "corrige le mot"));
		excerciseRepository.save(new Exercise("niecophofran", "francophonie", "corrige le mot"));
		excerciseRepository.save(new Exercise("lpleaer", "apeller", "corrige le mot"));
		excerciseRepository.save(new Exercise("voircepera", "apercevoir", "corrige le mot"));
		excerciseRepository.save(new Exercise("elleio", "oreille", "corrige le mot"));
		
	}
	
	
}
