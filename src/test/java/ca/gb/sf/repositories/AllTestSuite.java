package ca.gb.sf.repositories;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AssignmentRepositoryTest.class,
	AssignmentStatusRepositoryTest.class,
	ExerciseGroupRepositoryTest.class,
	ExerciseGroupRepositoryCustomTest.class,
	ExerciseRepositoryTest.class,
	KeywordRepositoryTest.class,
	LevelRepositoryTest.class,
	UserRepositoryTest.class,
	UserStatsRepositoryTest.class
})

public class AllTestSuite {

}
