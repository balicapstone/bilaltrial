package Tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import PGNPages.PGNLoginPage;
import PGNStudentResources.PGNScienceDatabaseHome;
import PGNStudentResources.PGNStudentHomePage;
import TrueUserTests.TrueUser.PGNSmokeTest;
import UserClasses.User;

public class PGNContentImageTests {
	static PGNLoginPage login;
    static int waitTime = 5;
    static User user; 
    static PGNStudentHomePage home;
    
 	@BeforeClass
    public static void executeBefore(){
    	user = new User(UserClasses.UserInfo.GARAGESTUDENT);
    	
 		login = new PGNLoginPage(user);
 		
    	home = (PGNStudentHomePage) login.login(user);
    	
    	home.waitImplicitly(10);
    //	home.clickPebbleGoNextTab();
    	home.waitImplicitly(15);
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
        /*	user.makeNewDriver();
        	
        	login = new PGNLoginPage(user);*/
    	}
    };
    	
/*	@Rule
	public Retry retry = new Retry(3);
    	*/
    @Test
    @Category(PGNSmokeTest.class)
    public void studentOnlySeesScience(){
	
		assertTrue(verifyImage("animal_category.PNG"));
		
		home.clickPebbleGoNextTab();
    	home.waitImplicitly(15);
    	
    	assertTrue(verifyImage("states_category.PNG"));
    	
		
		
		
	
	}
    
    private boolean verifyImage(String imageName) {
    	
    	String folderPath;
    	Path localPath = Paths.get("/Users/whall/NewQAProject/NewQAProject/");
    	if(Files.exists(localPath)){
			folderPath = localPath+"\\contentImages\\";
			
		}
		else {
			folderPath = "./"+"contentImages/";
		}
    	System.out.println("folderPath: "+ folderPath);
    	Screen s = new Screen();
		Pattern fileInputTextBox = new Pattern(folderPath + imageName);
		
		Pattern p1 = fileInputTextBox.similar(0.7f);
		Match m = s.exists(p1);
		
		if(s.exists(p1) != null) {
			return true;
		}
		return false;
    }

    @Rule
    public TestRule watcher = new TestWatcher() {
       protected void starting(Description description) {
          System.out.println("Starting test: " + description.getMethodName());
          
       }
       };
}
