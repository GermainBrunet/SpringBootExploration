package ca.gb.sf;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ca.gb.sf.models.AllTestSuite.class,
	ca.gb.sf.repositories.AllTestSuite.class,
	ca.gb.sf.services.AllTestSuite.class
})

public class AllTestSuite {

}
