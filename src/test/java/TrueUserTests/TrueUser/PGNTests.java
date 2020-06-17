package TrueUserTests.TrueUser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import Tests.ArticleTests;
import Tests.ContentTests;
import Tests.DatabaseTests;
import Tests.LoginScreenTests;
import Tests.PGNDisableArticlesTests;
import Tests.PGNGameTests;
import Tests.TeacherResourcesTests;
import Tests.PGNTrialPageTests;

@RunWith(Suite.class)

@SuiteClasses({ArticleTests.class, ContentTests.class, DatabaseTests.class, LoginScreenTests.class, PGNDisableArticlesTests.class,
	PGNGameTests.class, PGNTrialPageTests.class, TeacherResourcesTests.class})

public class PGNTests{
	
	
}
