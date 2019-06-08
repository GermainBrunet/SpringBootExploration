package ca.gb.sf.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.gb.sf.models.ExerciseGroupEntity;
import ca.gb.sf.models.KeywordEntity;
import ca.gb.sf.models.LevelEntity;
import ca.gb.sf.services.AssignmentService;
import ca.gb.sf.services.ExerciseGroupService;
import ca.gb.sf.services.ExerciseService;
import ca.gb.sf.services.KeywordService;
import ca.gb.sf.services.LevelService;

@Component
public class SetupExercises {

	private static final String KEYWORD_ORTHOPEDAGOGIE = "Orthopédagogie";
	
	private static final String KEYWORD_DICTEE = "Dictée";

	private static final String KEYWORD_CORRECTION = "Correction";
	
	private static final String KEYWORD_PHRASE = "Phrase";

	private static final String LEVEL_1 = "Première Année";
	
	private static final String LEVEL_2 = "Deuxième Année";
	
	private static final String LEVEL_3 = "Troisième Année";
	
	private static final String LEVEL_4 = "Quatrième Année";
	
	private static final String LEVEL_5 = "Cinquième Année";
	
	private static final String LEVEL_6 = "Sixième Année";
	
	
	@Autowired
	ExerciseGroupService exerciseGroupService;
	
	@Autowired
	ExerciseService exerciseService;
	
	@Autowired
	KeywordService keywordService;

	@Autowired
	AssignmentService assignmentService;

	@Autowired
	LevelService levelService;

	public void deleteAllExercises() {
		
		assignmentService.deleteAll();
		exerciseService.deleteAll();
		exerciseGroupService.deleteAll();
		keywordService.deleteAll();
		levelService.deleteAll();
		
	}
	
	public void createAllExercises() {
	
		createKeywords();
		createLevels();
		
		createExercise1();
		createExercise2();
		createExercise3();
		createExercise4();
		createExercise5();
		createExercise6();
		createExercise7();
		
	}
	
	public void createKeywords() {
		
		KeywordEntity keyword1 = new KeywordEntity(KEYWORD_DICTEE);
		keywordService.save(keyword1);

		KeywordEntity keyword2 = new KeywordEntity(KEYWORD_ORTHOPEDAGOGIE);
		keywordService.save(keyword2);

		KeywordEntity keyword3 = new KeywordEntity(KEYWORD_CORRECTION);
		keywordService.save(keyword3);

		KeywordEntity keyword4 = new KeywordEntity(KEYWORD_PHRASE);
		keywordService.save(keyword4);

	}
	
	public void createLevels() {
		
		LevelEntity level1 = new LevelEntity(LEVEL_1);
		levelService.save(level1);
		
		LevelEntity level2 = new LevelEntity(LEVEL_2);
		levelService.save(level2);

		LevelEntity level3 = new LevelEntity(LEVEL_3);
		levelService.save(level3);

		LevelEntity level4 = new LevelEntity(LEVEL_4);
		levelService.save(level4);

		LevelEntity level5 = new LevelEntity(LEVEL_5);
		levelService.save(level5);

		LevelEntity level6 = new LevelEntity(LEVEL_6);
		levelService.save(level6);
	}
	
	public void createExercise1() {

		ExerciseGroupEntity exerciseGroup = exerciseGroupService.createUsingName("ma 1.1");
		
		List<KeywordEntity> keywords = new ArrayList<KeywordEntity>();
		keywords.add(keywordService.findByKeyword(KEYWORD_ORTHOPEDAGOGIE));
		exerciseGroup.setKeywords(keywords);
		
		LevelEntity levelEntity = levelService.find(LEVEL_1);
		exerciseGroup.setLevel(levelEntity);
		
		String writtenInstructions = "Transforme le mot";
		String readTitle = "transforme le mot pour faire"; 
		
		exerciseService.create("ma", "mi", writtenInstructions, readTitle + " mi", 1, exerciseGroup);
		exerciseService.create("mi", "mo", writtenInstructions, readTitle + " mo",  2, exerciseGroup);
		exerciseService.create("mo", "me", writtenInstructions, readTitle + " me",  3, exerciseGroup);
		exerciseService.create("me", "mu", writtenInstructions, readTitle + " mu",  4, exerciseGroup);
		exerciseService.create("mu", "lu", writtenInstructions, readTitle + " lu",  5, exerciseGroup);
		exerciseService.create("lu", "li", writtenInstructions, readTitle + " li",  6, exerciseGroup);
		exerciseService.create("li", "la", writtenInstructions, readTitle + " la",  7, exerciseGroup);
		exerciseService.create("la", "lo", writtenInstructions, readTitle + " lo",  8, exerciseGroup);
		exerciseService.create("lo", "le", writtenInstructions, readTitle + " le",  9, exerciseGroup);
		exerciseService.create("le", "me", writtenInstructions, readTitle + " me", 10, exerciseGroup);

	}
	
	public void createExercise2() {

		ExerciseGroupEntity exerciseGroup = exerciseGroupService.createUsingName("la 1.2");

		List<KeywordEntity> keywords = new ArrayList<KeywordEntity>();
		keywords.add(keywordService.findByKeyword(KEYWORD_ORTHOPEDAGOGIE));
		exerciseGroup.setKeywords(keywords);
		
		LevelEntity levelEntity = levelService.find(LEVEL_1);
		exerciseGroup.setLevel(levelEntity);

		String writtenInstructions = "Transforme le mots";
		String readTitle = "transforme le mots pour faire"; 

		exerciseService.create("la", "ma", writtenInstructions,readTitle + " ma", 1, exerciseGroup);
		exerciseService.create("ma", "fa", writtenInstructions,readTitle + " fa", 2, exerciseGroup);
		exerciseService.create("fa", "fi", writtenInstructions,readTitle + " fi", 3, exerciseGroup);
		exerciseService.create("fi", "mi", writtenInstructions,readTitle + " mi", 4, exerciseGroup);
		exerciseService.create("mi", "li", writtenInstructions,readTitle + " li", 5, exerciseGroup);
		exerciseService.create("li", "lo", writtenInstructions,readTitle + " lo", 6, exerciseGroup);
		exerciseService.create("lo", "fo", writtenInstructions,readTitle + " fo", 7, exerciseGroup);
		exerciseService.create("fo", "mo", writtenInstructions,readTitle + " mo", 8, exerciseGroup);
		exerciseService.create("mo", "lo", writtenInstructions,readTitle + " lo", 9, exerciseGroup);
		exerciseService.create("lo", "li", writtenInstructions,readTitle + " li", 10, exerciseGroup);

	}

	public void createExercise3() {

		ExerciseGroupEntity exerciseGroup = exerciseGroupService.createUsingName("lafo 1.3");

		List<KeywordEntity> keywords = new ArrayList<KeywordEntity>();
		keywords.add(keywordService.findByKeyword(KEYWORD_ORTHOPEDAGOGIE));
		exerciseGroup.setKeywords(keywords);
		
		LevelEntity levelEntity = levelService.find(LEVEL_1);
		exerciseGroup.setLevel(levelEntity);

		String writtenInstructions = "Transforme le mot";
		String readTitle = "transforme le mot pour faire"; 

		exerciseService.create("lafo", "lafa", writtenInstructions,readTitle + " lafa", 1, exerciseGroup);
		exerciseService.create("lafa", "lafi", writtenInstructions,readTitle + " lafi", 2, exerciseGroup);
		exerciseService.create("lafi", "lami", writtenInstructions,readTitle + " lami", 3, exerciseGroup);
		exerciseService.create("lami", "lali", writtenInstructions,readTitle + " lali", 4, exerciseGroup);
		exerciseService.create("lali", "lalo", writtenInstructions,readTitle + " lalo", 5, exerciseGroup);
		exerciseService.create("lalo", "lamo", writtenInstructions,readTitle + " lamo", 6, exerciseGroup);
		exerciseService.create("lamo", "lamu", writtenInstructions,readTitle + " lamu", 7, exerciseGroup);
		exerciseService.create("lamu", "amu", writtenInstructions,readTitle + " amu", 8, exerciseGroup);
		exerciseService.create("amu", "afu", writtenInstructions,readTitle + " afu", 9, exerciseGroup);
		exerciseService.create("afu", "afo", writtenInstructions,readTitle + " afo", 10, exerciseGroup);

	}

	public void createExercise4() {

		ExerciseGroupEntity exerciseGroup = exerciseGroupService.createUsingName("malo 1.4");

		List<KeywordEntity> keywords = new ArrayList<KeywordEntity>();
		keywords.add(keywordService.findByKeyword(KEYWORD_ORTHOPEDAGOGIE));
		exerciseGroup.setKeywords(keywords);
		
		LevelEntity levelEntity = levelService.find(LEVEL_1);
		exerciseGroup.setLevel(levelEntity);
		
		String writtenInstructions = "Transforme le mot";
		String readTitle = "transforme le mot pour faire"; 

		exerciseService.create("malo", "malu", writtenInstructions,readTitle + " malo", 1, exerciseGroup);
		exerciseService.create("malu", "mala", writtenInstructions,readTitle + " malu", 2, exerciseGroup);
		exerciseService.create("mala", "mala", writtenInstructions,readTitle + " mala", 3, exerciseGroup);
		exerciseService.create("mala", "mafa", writtenInstructions,readTitle + " mafa", 4, exerciseGroup);
		exerciseService.create("mafa", "mafi", writtenInstructions,readTitle + " mafi", 5, exerciseGroup);
		exerciseService.create("mafi", "mafo", writtenInstructions,readTitle + " mafo", 6, exerciseGroup);
		exerciseService.create("mafo", "mafu", writtenInstructions,readTitle + " mafu", 7, exerciseGroup);
		exerciseService.create("mafu", "afu", writtenInstructions,readTitle + " afu", 8, exerciseGroup);
		exerciseService.create("afu", "afi", writtenInstructions,readTitle + " afi", 9, exerciseGroup);
		exerciseService.create("afi", "mafi", writtenInstructions,readTitle + " mafa", 10, exerciseGroup);

	}

	public void createExercise5() {

		ExerciseGroupEntity exerciseGroup = exerciseGroupService.createUsingName("Dictée 1.1");

		List<KeywordEntity> keywords = new ArrayList<KeywordEntity>();
		keywords.add(keywordService.findByKeyword(KEYWORD_DICTEE));
		exerciseGroup.setKeywords(keywords);
		
		LevelEntity levelEntity = levelService.find(LEVEL_1);
		exerciseGroup.setLevel(levelEntity);
		
		String writtenInstructions = "Épelle le mot";
		String readTitle = "épelle"; 

		exerciseService.create("", "aile", writtenInstructions, readTitle + " aile", 1, exerciseGroup);
		exerciseService.create("", "air", writtenInstructions, readTitle + " air", 2, exerciseGroup);
		exerciseService.create("", "ami", writtenInstructions, readTitle + " ami", 3, exerciseGroup);
		exerciseService.create("", "arbre", writtenInstructions, readTitle + " arbre", 4, exerciseGroup);
		exerciseService.create("", "bébé", writtenInstructions, readTitle + " bébé", 5, exerciseGroup);
		exerciseService.create("", "blanc", writtenInstructions, readTitle + " blanc", 6, exerciseGroup);
		exerciseService.create("", "carte", writtenInstructions, readTitle + " carte", 7, exerciseGroup);
		exerciseService.create("", "chambre", writtenInstructions, readTitle + " chambre", 8, exerciseGroup);
		exerciseService.create("", "chercher", writtenInstructions, readTitle + " chercher", 9, exerciseGroup);
		exerciseService.create("", "chemin", writtenInstructions, readTitle + " chemin", 10, exerciseGroup);

	}

	public void createExercise6() {

		ExerciseGroupEntity exerciseGroup = exerciseGroupService.createUsingName("Corrige 1.1");

		List<KeywordEntity> keywords = new ArrayList<KeywordEntity>();
		keywords.add(keywordService.findByKeyword(KEYWORD_CORRECTION));
		exerciseGroup.setKeywords(keywords);
		
		LevelEntity levelEntity = levelService.find(LEVEL_1);
		exerciseGroup.setLevel(levelEntity);
		
		String writtenInstructions = "Corrige le mot";
		String readTitle = "Corrige"; 

		exerciseService.create("elai", "aile", writtenInstructions, readTitle + " aile", 1, exerciseGroup);
		exerciseService.create("ria", "air", writtenInstructions, readTitle + " air", 2, exerciseGroup);
		exerciseService.create("am", "ami", writtenInstructions, readTitle + " ami", 3, exerciseGroup);
		exerciseService.create("arreb", "arbre", writtenInstructions, readTitle + " arbre", 4, exerciseGroup);
		exerciseService.create("ébéb", "bébé", writtenInstructions, readTitle + " bébé", 5, exerciseGroup);
		exerciseService.create("blenc", "blanc", writtenInstructions, readTitle + " blanc", 6, exerciseGroup);
		exerciseService.create("karte", "carte", writtenInstructions, readTitle + " carte", 7, exerciseGroup);
		exerciseService.create("chombre", "chambre", writtenInstructions, readTitle + " chambre", 8, exerciseGroup);
		exerciseService.create("chercher", "chercher", writtenInstructions, readTitle + " chercher", 9, exerciseGroup);
		exerciseService.create("cheminer", "chemin", writtenInstructions, readTitle + " chemin", 10, exerciseGroup);

	}

	public void createExercise7() {

		ExerciseGroupEntity exerciseGroup = exerciseGroupService.createUsingName("Phrase 1.1");

		List<KeywordEntity> keywords = new ArrayList<KeywordEntity>();
		keywords.add(keywordService.findByKeyword(KEYWORD_DICTEE));
		exerciseGroup.setKeywords(keywords);
		
		LevelEntity levelEntity = levelService.find(LEVEL_1);
		exerciseGroup.setLevel(levelEntity);
		
		String writtenInstructions = "Corrige la phrase";
		String readTitle = ""; 

		exerciseService.create("la balle rouge", "la balle est rouge", writtenInstructions, readTitle + "la balle est rouge", 1, exerciseGroup);

	}
}
