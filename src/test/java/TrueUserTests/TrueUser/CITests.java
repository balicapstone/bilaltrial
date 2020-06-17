package TrueUserTests.TrueUser;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import Tests.BookReaderTests;
import Tests.FilterTests;
import Tests.LearnMoreTests;
import Tests.LoginPageTests;
import Tests.SearchTests;
import Tests.VisualSearchTests;




@RunWith(Suite.class)
@Suite.SuiteClasses({FilterTests.class, LearnMoreTests.class, 
	SearchTests.class, BookReaderTests.class, VisualSearchTests.class, LoginPageTests.class})

//	BooklistsTests.class, Removed until tests are written

public class CITests{
	
}                                                                                                                                                                                                                     