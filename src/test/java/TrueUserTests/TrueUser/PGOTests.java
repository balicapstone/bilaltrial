package TrueUserTests.TrueUser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import Tests.AuthentificationTests;
import Tests.DisableArticlesTests;
import Tests.DocumentsTests;
import Tests.FAQTests;
import Tests.FooterMenuTests;
import Tests.NavigationBarTests;
import Tests.PGOGameTests;
import Tests.PGONEWLoginTests;
import Tests.PGOTeacherResourcesTests;
import Tests.PGOTrialPageTests;
import Tests.RegressionPGONewHomepageTests;
import Tests.StudentContentTests;
import Tests.SystemRequirementsTests;
import junit.framework.TestCase;


@RunWith(Suite.class)
@Suite.SuiteClasses({StudentContentTests.class, RegressionPGONewHomepageTests.class, DocumentsTests.class,
	FAQTests.class, FooterMenuTests.class, PGOTrialPageTests.class,
	PGONEWLoginTests.class, SystemRequirementsTests.class, NavigationBarTests.class,
	PGOTeacherResourcesTests.class, AuthentificationTests.class, 
	PGOGameTests.class, DisableArticlesTests.class,
 })

public class PGOTests extends TestCase{
	
}