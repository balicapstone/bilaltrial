package Tests;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import AdminResources.TeacherHomeScreen;
import PGOPages.LoginPage;
import PGOStudentResources.StudentHomePage;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.ProductType;
import UserClasses.User;
import UserClasses.UserInfo;

/**
 * Unit test for simple App.
 */
public class LoginTests{
    static LoginPage loginPage;
    static User user;
    
    @BeforeClass
    public static void executeBefore(){
    	user = new User(UserInfo.GARAGESTUDENT);
    	
    	try{
    		loginPage = new LoginPage(user);
    		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.footerMenu.aboutUsLink));
    	} catch(Exception e){
    		user.getWatcher().TakeScreenshot("FailureToRunExecuteBeforeForLoginTests");
    	}
    }
    
    @AfterClass
    public static void executeAfter(){
    	loginPage.closeCurrentWindow();
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
    		executeBefore();
    	}
    };
    
	@Rule
	public Retry retry = new Retry(3);
	
    
    /**
     * Tests that expired credentials will result in the expiration message appearing.
     */
    @Test
    @Category(SmokeTests.class)
    public void testExpiredSiteMessage()
    {
    	loginPage.Login(user.setCredentials(UserInfo.EXPIRED));
    	user.customWait().until(ExpectedConditions.textToBe(loginPage.loginModal.username, ""));

    	loginPage.refreshLoginModal();
    	assertTrue("Tests that an expired message appears when a user has expired credentials",
    			loginPage.loginModal.isErrorMessagePresent()); 
    }   
    
    @Test
    @Category(SmokeTests.class)
    public void testTeacherCredentials(){
    	TeacherHomeScreen teacherHomeScreen = (TeacherHomeScreen) loginPage.Login(user.setCredentials(UserInfo.GARAGETEACHER));
    	assertTrue("Tests that a teacher can sign in and is taken to the teacher homepage",
    			teacherHomeScreen.verifyElementsVisibility(teacherHomeScreen.getInitialVisibleElements()));
    	loginPage = (LoginPage) teacherHomeScreen.clickLogoutButton(ProductType.PGO);
    }
    
    /**
     * PGO-175 Only Building-level admin users should have access to Disable Articles
     */   
    @Test
    public void testDisableArticlesAccessSchoolTeacher(){
    	//LoginPage home = new LoginPage();
    	TeacherHomeScreen teacherHomeScreen = (TeacherHomeScreen) loginPage.Login(user.setCredentials(UserInfo.GARAGETEACHER));
    	assertTrue("Tests that a teacher can access Disable Articles after log in", 
    			teacherHomeScreen.isElementPresent(teacherHomeScreen.getDisableArticlesButton()));
    	loginPage = (LoginPage) teacherHomeScreen.clickLogoutButton(ProductType.PGO);
    }
    
    @Test
    public void testDisableArticlesAccessSchoolAdmin(){
    	TeacherHomeScreen teacherHomeScreen = (TeacherHomeScreen) loginPage.Login(user.setCredentials(UserInfo.ADMIN));
    	assertTrue("Tests that a school admin user can access Disable Articles after log in",
    			teacherHomeScreen.isElementPresent(teacherHomeScreen.getDisableArticlesButton()));
    	loginPage = (LoginPage) teacherHomeScreen.clickLogoutButton(ProductType.PGO);
    }
    
    @Test
    public void testDirectASPXURLLogin(){
    	String ASPXLink = "https://www.pebblego.com/UserLogin.aspx?sqs=4bd11cfa2814d3b160b277101b6c420ec1ad35f70ce92758b50613f4a127d3e0";
    	loginPage.getDriver().get(ASPXLink);
    	StudentHomePage student = new StudentHomePage(loginPage.getDriver());
    	assertTrue("Tests that a student can log in through an ASPX SQS string",
    			student.verifyElementsVisibility(student.getInitialVisibleElements()));
    }
    
    @Test
    public void testStudentSQS(){
    	String sqs = "https://www.pebblego.com/login/?sqs=4bd11cfa2814d3b160b277101b6c420ec1ad35f70ce92758b50613f4a127d3e0";
    	loginPage.getDriver().get(sqs);
    	StudentHomePage student = new StudentHomePage(loginPage.getDriver());
    	assertTrue("Tests that a student can log in through an SQS string",
    			student.verifyElementsVisibility(student.getInitialVisibleElements()));
        loginPage = student.clickLogOutButton();
    	
    }
    
    
    @Test
    public void testUnsignedEula(){
    	user.setCredentials(UserInfo.PGODONT);
    	loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.textToBe(loginPage.loginModal.username, ""));
    	
    	String message = loginPage.getModalElement(By.className("modal-error")).getText();
    	assertTrue("Asserts that the correct error message is returned when the user enters credentials for a account without a signed Eula", 
    			message.equals("You need to have your instructor sign your eula."));
    	
    	loginPage.refresh();
    }
}
