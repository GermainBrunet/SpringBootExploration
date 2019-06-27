package ca.gb.sf.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AssignmentServiceTest.class,
	EducatorServiceTest.class,
	ExerciseGroupServiceTest.class,
	ExerciseServiceTest.class,
	UserServiceTest.class
})

public class AllTestSuite {

}
