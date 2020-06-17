package TrueUserTests.TrueUser;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import Tests.ArticleTests;
import Tests.ContentTests;
import Tests.DatabaseTests;
import Tests.LoginScreenTests;
import Tests.PGNDisableArticlesTests;
import Tests.PGNGameTests;
import Tests.PGNTrialPageTests;
import Tests.TeacherResourcesTests;

@RunWith(Categories.class)
@IncludeCategory(PGNSmokeTest.class)

@Suite.SuiteClasses({ ArticleTests.class, ContentTests.class, DatabaseTests.class, LoginScreenTests.class, PGNDisableArticlesTests.class,
	PGNGameTests.class, PGNTrialPageTests.class, TeacherResourcesTests.class})

public interface PGNSmokeTestSuite {

}
