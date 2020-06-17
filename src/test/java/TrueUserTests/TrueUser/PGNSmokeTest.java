package TrueUserTests.TrueUser;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;


import Tests.ArticleTests;
import Tests.ContentTests;
import Tests.DatabaseTests;
import Tests.LoginScreenTests;
import Tests.PGNGameTests;
import Tests.PGOTeacherResourcesTests;
import Tests.PGNTrialPageTests;


@RunWith(Categories.class)
@IncludeCategory(PGNSmokeTestSuite.class)
@SuiteClasses({LoginScreenTests.class, ArticleTests.class, ContentTests.class, DatabaseTests.class, 
	PGNGameTests.class, PGOTeacherResourcesTests.class, PGNTrialPageTests.class})


public class PGNSmokeTest {

}
