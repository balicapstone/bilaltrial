package Tests;

import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import PGNPages.LoginPage;
import PGNStudentResources.DatabasePage;
import PGNStudentResources.IndiansDatabaseHome;
import PGNStudentResources.ScienceDatabaseHome;
import PGNStudentResources.SocialStudiesDatabasePage;
import PGNStudentResources.StatesDatabaseHome;
import PGNStudentResources.StudentHomePage;
import TrueUserTests.TrueUser.PGNSmokeTest;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;

public class TeacherResourcesTests {
	public static LoginPage login;
	public static StudentHomePage student;
	public static User user;
	static String communitiesLink = "http://community.mycapstone.com/";
		
	@BeforeClass
    public static void executeBefore() throws InterruptedException{
		user = new User(UserClasses.UserInfo.GARAGESTUDENT);
		
		login = new LoginPage(user);
    }
    
    @AfterClass
    public static void executeAfter(){
    	user.quit();
    }
    	  
    @Rule
    public TestWatcher restart = new TestWatcher(){
    	@Override
    	public void failed(Throwable e, Description description){
    		try{
    			user.getWatcher().TakeScreenshot(description.getDisplayName());
    		} catch(Exception t){
    			System.out.print("There was a failure while trying to take a screenshot for " + description.getDisplayName());
    		}
    		
    		user.quit();
    		user.makeNewDriver();
    		
    		login = new LoginPage(user);
    	}
    };
    
	@Rule
	public Retry retry = new Retry(3);
    

    
    @Test
    @Category(PGNSmokeTest.class)
    public void testCommunityLinkStates() throws InterruptedException{ 
 		student = (StudentHomePage) login.login(user);
 		student.waitImplicitly(2);
 		
    	StatesDatabaseHome states = student.goToStates();
    	
       	student.waitImplicitly(1);
    	
    	assertTrue("Asserts the Community link directs to the community capstone site",
    			states.verifyLinkContainsURLByElement(states.communityLink, communitiesLink));
    	
    	student.waitImplicitly(2);
    	student.scrollToTopofPage();
    	student.waitImplicitly(2);
    	
    	login = student.header.logout();
    	login.waitImplicitly(2);
    }
    
    @Test
    public void testCommunityLinkAmericanIndians() throws InterruptedException{ 
 		student = (StudentHomePage) login.login(user);
 		student.waitImplicitly(2);
 		
    	IndiansDatabaseHome indians = student.goToIndians();
    	
       	student.waitImplicitly(1);
    	
    	assertTrue("Asserts the Community link directs to the community capstone site",
    			indians.verifyLinkContainsURLByElement(indians.communityLink, communitiesLink));
       	
    	student.waitImplicitly(2);
    	student.scrollToTopofPage();
    	student.waitImplicitly(2);
    	
    	login = student.header.logout();
    	login.waitImplicitly(2);
    }
    
    @Test
    public void testCommunityLinkScience() throws InterruptedException{
 		student = (StudentHomePage) login.login(user);
 		student.waitImplicitly(2);
    	
    	ScienceDatabaseHome science = student.goToScience();
    	
       	student.waitImplicitly(1);
 
       	assertTrue("Asserts the Community link directs to the community capstone site",
    			science.verifyLinkContainsURLByElement(science.communityLink, communitiesLink));
       	
    	student.waitImplicitly(2);
    	student.scrollToTopofPage();
    	student.waitImplicitly(2);
    	
    	login = student.header.logout();
    	login.waitImplicitly(2);
    }
    
    @Test
    public void testCommunityLinkSocialStudies() throws InterruptedException{
 		student = (StudentHomePage) login.login(user);
 		student.waitImplicitly(2);
    	
    	SocialStudiesDatabasePage socialStudies = student.goToSocialStudies();
       	student.waitImplicitly(1);
    	
       	assertTrue("Asserts the Community link directs to the community capstone site",
    			socialStudies.verifyLinkContainsURLByElement(socialStudies.communityLink, communitiesLink));
       	
    	student.waitImplicitly(2);
    	student.scrollToTopofPage();
    	student.waitImplicitly(2);
    	
    	login = student.header.logout();
    	login.waitImplicitly(2);
    }
    
    @Test
    public void testCommunityLinkBiographies() throws InterruptedException{
 		student = (StudentHomePage) login.login(user);
 		student.waitImplicitly(2);
    	
    	DatabasePage biographies = student.goToBiographies();
       	student.waitImplicitly(1);
    	
       	assertTrue("Asserts the Community link directs to the community capstone site",
       			biographies.verifyLinkContainsURLByElement(biographies.communityLink, communitiesLink));
       	
    	student.waitImplicitly(2);
    	student.scrollToTopofPage();
    	student.waitImplicitly(2);
    	
    	login = student.header.logout();
    	login.waitImplicitly(2);
    }
}
