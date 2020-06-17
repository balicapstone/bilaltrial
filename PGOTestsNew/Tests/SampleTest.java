package Tests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AdminResources.PGONewEULAAcceptancePage;
import AdminResources.PGONewTeacherHomeScreen;
import PGOPagesNew.PGONewLoginPage;
import PGOStudentResources.PGONewArticlePage;
import PGOStudentResources.PGONewContentSelectionPage;
import PGOStudentResources.StudentHomePageNew;
import SharedClasses.PGONewGooglePage;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.User;
import UserClasses.UserInfo;

public class SampleTest{
    static PGONewLoginPage loginPage;
    static User user;
    
    @BeforeClass
    public static void executeBefore(){
    	user = new User(UserInfo.GARAGESTUDENT);
    	
    	try{
    		loginPage = new PGONewLoginPage(user);
    //		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.googleButton));
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
    	//	executeBefore();
    	}
    };
    
	//@Rule
	//public Retry retry = new Retry(3);
	
    /**
     * Tests that expired credentials will result in the expiration message appearing.
     */
    @Test
    public void testGoogleTitle(){
    	
    	user.getDriver().get("https://www.google.com/");
    	loginPage.waitImplicitly(5);
    	
    	assertTrue("Tests that google search is visible",user.getDriver().findElement(By.name("q")).isDisplayed());
    	
    	
    }   
    
    @Test
    public void testGoogleTitleNotPresent(){
    	
    	user.getDriver().get("https://www.google.com/");
    	loginPage.waitImplicitly(5);
    	
    	assertTrue("Tests that google search is visible",user.getDriver().findElement(By.name("q132")).isDisplayed());
    	
    	
    }   
    
   
}

