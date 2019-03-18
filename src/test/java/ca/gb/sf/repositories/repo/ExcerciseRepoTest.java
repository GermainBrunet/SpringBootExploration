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
		
		excerciseRepository.save(new Exercise("eampxle", "example", "corrige le mot"));
		excerciseRepository.save(new Exercise("aovir", "avoir", "corrige le mot"));
		excerciseRepository.save(new Exercise("fiinr", "finir", "corrige le mot"));
		excerciseRepository.save(new Exercise("toujousr", "toujours", "corrige le mot"));
		excerciseRepository.save(new Exercise("lbanche", "blanche", "corrige le mot"));
		excerciseRepository.save(new Exercise("debon", "bedon", "corrige le mot"));
		excerciseRepository.save(new Exercise("uovir", "ouvir", "corrige le mot"));
		excerciseRepository.save(new Exercise("mina", "main", "corrige le mot"));
		excerciseRepository.save(new Exercise("corir", "courir", "corrige le mot"));
		excerciseRepository.save(new Exercise("cominatoire", "comminatoire", "corrige le mot - cominatoire"));
		excerciseRepository.save(new Exercise("alician", "alliciant", "corrige le mot - alliciant"));
		excerciseRepository.save(new Exercise("haylin", "hyalin", "corrige le mot - hyalin"));
		excerciseRepository.save(new Exercise("ipvide", "impavide", "corrige le mot - impavide"));
		excerciseRepository.save(new Exercise("ocele", "ocelle", "corrige le mot - ocelle"));
		
	}
	
	
}
