package TrueUserTests.TrueUser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Tests.ArticleTests;
import Tests.ContentTests;
import Tests.DatabaseTests;
import Tests.LoginScreenTests;
import Tests.PGNGameTests;

@RunWith(Suite.class)
@SuiteClasses({LoginScreenTests.class, ArticleTests.class, ContentTests.class, DatabaseTests.class, 
	PGNGameTests.class})

public class PGNStudentContent {

}
