package ca.gb.sf.repositories;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AssignmentRepositoryTest.class,
	ExerciseGroupRepositoryTest.class,
	ExerciseRepositoryTest.class,
	KeywordRepositoryTest.class,
	LevelRepositoryTest.class,
	UserRepositoryTest.class
})

public class AllTestSuite {

}
