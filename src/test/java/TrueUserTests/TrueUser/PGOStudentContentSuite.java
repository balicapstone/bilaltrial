package TrueUserTests.TrueUser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import Tests.DisableArticlesTests;
import Tests.FooterMenuTests;
import Tests.RegressionPGONewHomepageTests;
import Tests.LoginTests;
import Tests.NavigationBarTests;
import Tests.PGOGameTests;
import Tests.StudentContentTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({StudentContentTests.class,
	                 RegressionPGONewHomepageTests.class,
	                 FooterMenuTests.class, 
	                 PGOGameTests.class,
	                 LoginTests.class, 
	                 NavigationBarTests.class, 
	                 DisableArticlesTests.class
})

//@Suite.SuiteClasses({ LoginTests.class})
// Removing DisableArticlesTests.class until breadcrumbs are fixed 
public class PGOStudentContentSuite { 

}
