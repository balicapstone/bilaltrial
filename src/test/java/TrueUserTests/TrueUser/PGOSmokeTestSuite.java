package TrueUserTests.TrueUser;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import Tests.DisableArticlesTests;
import Tests.DocumentsTests;
import Tests.FAQTests;
import Tests.FooterMenuTests;
import Tests.NavigationBarTests;
import Tests.PGNTrialPageTests;
import Tests.PGOGameTests;
import Tests.PGONEWLoginTests;
import Tests.PGOTeacherResourcesTests;
import Tests.RegressionPGONewHomepageTests;
import Tests.StudentContentTests;
import Tests.SystemRequirementsTests;
import Tests.TeacherHomeScreenTests;



@RunWith(Categories.class)
@IncludeCategory(SmokeTests.class)

@Suite.SuiteClasses({RegressionPGONewHomepageTests.class, DocumentsTests.class,
	 FAQTests.class, FooterMenuTests.class, PGOGameTests.class, PGNTrialPageTests.class, 
	 PGONEWLoginTests.class, SystemRequirementsTests.class, StudentContentTests.class, 
	 TeacherHomeScreenTests.class, DisableArticlesTests.class, NavigationBarTests.class,
	 PGOTeacherResourcesTests.class, PGOTeacherResourcesTests.class })

public class PGOSmokeTestSuite {

}
