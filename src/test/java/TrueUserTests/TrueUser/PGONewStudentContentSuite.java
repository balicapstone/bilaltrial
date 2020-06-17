package TrueUserTests.TrueUser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import Tests.PGONEWLoginTests;
import Tests.PGONewDisableArticlesTests;
import Tests.PGONewFooterMenuTests;
import Tests.PGONewGameTests;
import Tests.PGONewHomepageTests;
import Tests.PGONewNavigationBarTests;
import Tests.RegressionPGONewHomepageTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({PGONEWLoginTests.class,
	RegressionPGONewHomepageTests.class
					/* PGONewStudentContentTests.class,
					 PGONewHomepageTests.class,
					 PGONewFooterMenuTests.class, 
	                 PGONewGameTests.class,
	                 PGONewNavigationBarTests.class, 
	                 PGONewDisableArticlesTests.class*/
})

//@Suite.SuiteClasses({ LoginTests.class})
// Removing DisableArticlesTests.class until breadcrumbs are fixed 
public class PGONewStudentContentSuite { 

}
