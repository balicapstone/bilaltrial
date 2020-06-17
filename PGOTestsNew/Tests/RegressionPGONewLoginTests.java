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

public class RegressionPGONewLoginTests{
    static PGONewLoginPage loginPage;
    static User user;
    
    @BeforeClass
    public static void executeBefore(){
    	user = new User(UserInfo.GARAGESTUDENT);
    	
    	try{
    		loginPage = new PGONewLoginPage(user);
    		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.googleButton));
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
    public void testExpiredSiteMessage(){
    	loginPage.Login(user.setCredentials(UserInfo.EXPIRED));
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginErrorText));

    	String message = loginPage.getDriver().findElement(loginPage.loginErrorText).getText();
    	assertTrue("Tests that an expired message appears when a user has expired credentials", 
    			message.equals("Your subscription has expired."));
    	
    	loginPage.refresh();
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }   
    
    /**
     * Tests that wrong student credentials will result in the error message appearing.
     */
    @Test
    @Category(SmokeTests.class)
    public void testIncorrectStudentCredentialsSiteMessage()
    {
    	user = user.setCredentials(UserInfo.GARAGESTUDENT);
    	user.setPassword("Bad");
    	loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginErrorText));

    	String message = loginPage.getDriver().findElement(loginPage.loginErrorText).getText();
    	assertTrue("Tests that an expired message appears when a user has expired credentials", 
    			message.equals("Oops! Incorrect Username Or Password. Please Try Again."));
    	
    	loginPage.refresh();
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }   
    
    /**
     * Tests that wrong teacher credentials will result in the error message appearing.
     */
    @Test
    @Category(SmokeTests.class)
    public void testIncorrectTeacherCredentialsSiteMessage()
    {
    	user = user.setCredentials(UserInfo.GARAGETEACHER);
    	user.setPassword("Bad");
    	loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginErrorText));;

    	String message = loginPage.getDriver().findElement(loginPage.loginErrorText).getText();
    	assertTrue("Tests that an expired message appears when a user has expired credentials", 
    			message.equals("Oops! Incorrect Username Or Password. Please Try Again."));
    	
    	loginPage.refresh();
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }  
    
    @Test
    @Category(SmokeTests.class)
    public void testStudentCredentials(){
    	StudentHomePageNew student = (StudentHomePageNew) loginPage.Login(user.setCredentials(UserInfo.GARAGESTUDENT));
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	assertTrue("Tests that a teacher can sign in and is taken to the teacher homepage",
    			student.verifyElementsVisibility(student.getInitialVisibleElements()));
    	
    	loginPage = student.header.logout();
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }
    
    @Test
    @Category(SmokeTests.class)
    public void testTeacherCredentials(){
    	PGONewTeacherHomeScreen teacherHomeScreen = (PGONewTeacherHomeScreen) loginPage.Login(user.setCredentials(UserInfo.GARAGETEACHER));
    	
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(teacherHomeScreen.logoutButton));
    	assertTrue("Tests that a teacher can sign in and is taken to the teacher homepage",
    			teacherHomeScreen.verifyElementsVisibility(teacherHomeScreen.getInitialVisibleElements()));
    	
    	loginPage = teacherHomeScreen.logout();
    	loginPage.getDriver().get("https://" + user.getEnvironment() + ".pebblego.com");
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }
    
    @Test
    @Category(SmokeTests.class)
    public void testMasterAccountCredentials(){
    	StudentHomePageNew student = (StudentHomePageNew) loginPage.Login(user.setCredentials(UserInfo.MASTERACCOUNT));
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	assertTrue("Tests that a teacher can sign in and is taken to the teacher homepage",
    			student.verifyElementsVisibility(student.getInitialVisibleElements()));
    	
    	loginPage = student.header.logout();
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }
    
    /**
     * PGO-175 Only Building-level admin users should have access to Disable Articles
     */
    @Test
    public void testAccessDistrictAdmin(){
    	PGONewTeacherHomeScreen teacherHomeScreen = (PGONewTeacherHomeScreen) loginPage.Login(user.setCredentials(UserInfo.DISTRICTADMIN));
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(teacherHomeScreen.logoutButton));
    	
    	assertTrue("Tests that a district admin can sign in and is taken to the teacher homepage",
    			teacherHomeScreen.verifyElementsVisibility(teacherHomeScreen.getInitialDistrictAdminElements()));
    	
    	loginPage = teacherHomeScreen.logout();
    	loginPage.getDriver().get("https://" + user.getEnvironment() + ".pebblego.com");
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }   

    @Test
    public void testDirectASPXURLLogin(){
    	String ASPXLink = "https://"+user.getEnvironment()+".pebblego.com/UserLogin.aspx?sqs="+UserInfo.GARAGESTUDENT.getSQS();
    	loginPage.getDriver().get(ASPXLink);
    	StudentHomePageNew student = new StudentHomePageNew(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	assertTrue("Tests that a student can log in through an ASPX SQS string",
    			student.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules"));
    	
    	loginPage = student.header.logout();
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }
    
    @Test
    public void testDirectWWWASPXURLLogin(){
    	String ASPXLink = "https://www.pebblego.com/UserLogin.aspx?sqs="+UserInfo.GARAGESTUDENT.getSQS();
    	loginPage.getDriver().get(ASPXLink);
    	StudentHomePageNew student = new StudentHomePageNew(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	assertTrue("Tests that a student can log in through an ASPX SQS string",
    			student.getDriver().getCurrentUrl().equals("https://site.pebblego.com/modules"));
    	
    	loginPage = student.header.logout();
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }

    @Test
    public void testStudentSQS(){
    	String sqs = "https://"+user.getEnvironment()+".pebblego.com/login/?sqs="+UserInfo.GARAGESTUDENT.getSQS();
    	loginPage.getDriver().get(sqs);
    	StudentHomePageNew student = new StudentHomePageNew(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	assertTrue("Tests that a student can log in through an SQS string",
    			student.verifyElementsVisibility(student.getInitialVisibleElements()));
    	
    	loginPage = student.header.logout();
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }

    @Test
    public void testWWWStudentSQS(){
    	String sqs = "https://www.pebblego.com/login/?sqs="+UserInfo.GARAGESTUDENT.getSQS();
    	loginPage.getDriver().get(sqs);
    	StudentHomePageNew student = new StudentHomePageNew(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	assertTrue("Tests that a student can log in through an SQS string",
    			student.verifyElementsVisibility(student.getInitialVisibleElements()));
    	
    	loginPage = student.header.logout();
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }
    
    @Test
    public void testMackinStudentSQS(){
    	String sqs = "";
    	
    	if(user.getEnvironment().equals("site")){
    		sqs = "http://www.pebblego.com/content/biographies?sqs=A18PXrJFl4guzXllglDLaA==";
    	}else{
    		sqs = "http://"+user.getEnvironment()+".pebblego.com/content/biographies?sqs=A18PXrJFl4guzXllglDLaA==";
    	}
    	
    	loginPage.getDriver().get(sqs);
    	PGONewContentSelectionPage student = new PGONewContentSelectionPage(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Athletes")));
    	
    	assertTrue("Tests that a student can log in through an SQS string",
    			student.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules/3/categories/0"));
    	
    	loginPage = student.header.logout();
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }
    
    @Test
    public void testPercentageCharacterStudentSQS(){
    	String sqs = "";
    	
    	if(user.getEnvironment().equals("site")){
    		sqs = "http://www.pebblego.com/content/biographies?sqs=A18PXrJFl4guzXllglDLaA%3D%3D";
    	}else{
    		sqs = "http://"+user.getEnvironment()+".pebblego.com/content/biographies?sqs=A18PXrJFl4guzXllglDLaA%3D%3D";
    	}
    	
    	loginPage.getDriver().get(sqs);
    	PGONewContentSelectionPage student = new PGONewContentSelectionPage(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Athletes")));
    	
    	assertTrue("Tests that a student can log in through an SQS string",
    			student.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules/3/categories/0"));
    	
    	loginPage = student.header.logout();
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }

    @Test
    public void testLegacyStudentSQS(){
    	String sqs = "";
    
    	if(user.getEnvironment().equals("reactqa")){
    		sqs = "https://wwwqa.pebblego.com/login/index.html?sqs="+UserInfo.GARAGESTUDENT.getSQS();
    	} else if(user.getEnvironment().equals("site")){
    		sqs = "https://www.pebblego.com/login/index.html?sqs="+UserInfo.GARAGESTUDENT.getSQS();
    	} else{
    		sqs = "https://www.pebblego.com/login/index.html?sqs="+UserInfo.GARAGESTUDENT.getSQS();
    	}
    	
    	loginPage.getDriver().get(sqs);
    	StudentHomePageNew student = new StudentHomePageNew(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	assertTrue("Tests that a student can log in through an SQS string",
    			student.verifyElementsVisibility(student.getInitialVisibleElements()));
    	
    	loginPage = student.header.logout();
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }

    @Test
    public void testLegacyPHPStudentSQS(){
    	String sqs = "";
    
    	if(user.getEnvironment().equals("reactqa")){
    		sqs = "https://wwwqa.pebblego.com/login.php?sqs="+UserInfo.GARAGESTUDENT.getSQS();
    	} else if(user.getEnvironment().equals("site")){
    		sqs = "https://www.pebblego.com/login.php?sqs="+UserInfo.GARAGESTUDENT.getSQS();
    	} else{
    		sqs = "https://www.pebblego.com/login.php?sqs="+UserInfo.GARAGESTUDENT.getSQS();
    	}
    	
    	loginPage.getDriver().get(sqs);
    	StudentHomePageNew student = new StudentHomePageNew(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	assertTrue("Tests that a student can log in through an SQS string",
    			student.verifyElementsVisibility(student.getInitialVisibleElements()));
    	
    	loginPage = student.header.logout();
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }

    @Test
    public void testLegacyQuestionMarkStudentSQS(){
    	String sqs = "";
    
    	if(user.getEnvironment().equals("reactqa")){
    		sqs = "https://wwwqa.pebblego.com/?sqs="+UserInfo.GARAGESTUDENT.getSQS();
    	} else if(user.getEnvironment().equals("site")){
    		sqs = "https://www.pebblego.com/?sqs="+UserInfo.GARAGESTUDENT.getSQS();
    	} else{
    		sqs = "https://www.pebblego.com/?sqs="+UserInfo.GARAGESTUDENT.getSQS();
    	}
    	
    	loginPage.getDriver().get(sqs);
    	StudentHomePageNew student = new StudentHomePageNew(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	assertTrue("Tests that a student can log in through an SQS string",
    			student.verifyElementsVisibility(student.getInitialVisibleElements()));
    	
    	loginPage = student.header.logout();
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }

    @Test
    public void testTeacherSQS(){
    	String sqs = "https://" +user.getEnvironment()+ ".pebblego.com/login/?sqs="+UserInfo.GARAGETEACHER.getSQS();
    	loginPage.getDriver().get(sqs);
    	PGONewTeacherHomeScreen teacher = new PGONewTeacherHomeScreen(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacher.logoutButton));
    	
    	assertTrue("Tests that a teacher can log in using an SQS string",
    			teacher.verifyElementsVisibility(teacher.getInitialVisibleElements()));
    	
    	loginPage = teacher.logout();
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(teacher.logoutButton));
    	loginPage.getDriver().get("https://" + user.getEnvironment() + ".pebblego.com");
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }

    @Test
    public void testWWWTeacherSQS(){
    	String sqs = "https://www.pebblego.com/login/?sqs=" + UserInfo.GARAGETEACHER.getSQS(); //6537f6f637db185ecb320f018bb902d7ed63d9f2646322c8ca9a380cee14c9c7";
    	loginPage.getDriver().get(sqs);
    	PGONewTeacherHomeScreen teacher = new PGONewTeacherHomeScreen(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacher.logoutButton));
    	
    	assertTrue("Tests that a teacher can log in using an SQS string",
    			teacher.verifyElementsVisibility(teacher.getInitialVisibleElements()));
    	
    	loginPage = teacher.logout();
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(teacher.logoutButton));
    	loginPage.getDriver().get("https://" + user.getEnvironment() + ".pebblego.com");
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }

    @Test
    public void testLegacyTeacherIndexHTMLSQS(){
    	String sqs = "";
        
    	if(user.getEnvironment().equals("reactqa")){
    		sqs = "https://wwwqa.pebblego.com/login/index.html?sqs="+UserInfo.GARAGETEACHER.getSQS();
    	} else if(user.getEnvironment().equals("site")){
    		sqs = "https://www.pebblego.com/login/index.html?sqs="+UserInfo.GARAGETEACHER.getSQS();
    	} else{
    		sqs = "https://www.pebblego.com/login/index.html?sqs="+UserInfo.GARAGETEACHER.getSQS();
    	}
    	
    	loginPage.getDriver().get(sqs);
    	PGONewTeacherHomeScreen teacher = new PGONewTeacherHomeScreen(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacher.logoutButton));
    	
    	assertTrue("Tests that a teacher can log in using an SQS string",
    			teacher.verifyElementsVisibility(teacher.getInitialVisibleElements()));
    	
    	loginPage = teacher.logout();
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(teacher.logoutButton));
    	loginPage.getDriver().get("https://" + user.getEnvironment() + ".pebblego.com");
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }

    @Test
    public void testLegacyQuestionMarkTeacherSQS(){
    	String sqs = "";
        
    	if(user.getEnvironment().equals("reactqa")){
    		sqs = "https://wwwqa.pebblego.com/?sqs="+UserInfo.GARAGETEACHER.getSQS();
    	} else if(user.getEnvironment().equals("site")){
    		sqs = "https://www.pebblego.com/?sqs="+UserInfo.GARAGETEACHER.getSQS();
    	} else{
    		sqs = "https://www.pebblego.com/?sqs="+UserInfo.GARAGETEACHER.getSQS(); 
    	}
    	
    	loginPage.getDriver().get(sqs);
    	PGONewTeacherHomeScreen teacher = new PGONewTeacherHomeScreen(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacher.logoutButton));
    	
    	assertTrue("Tests that a teacher can log in using an SQS string",
    			teacher.verifyElementsVisibility(teacher.getInitialVisibleElements()));
    	
    	loginPage = teacher.logout();
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(teacher.logoutButton));
    	loginPage.getDriver().get("https://" + user.getEnvironment() + ".pebblego.com");
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }
    
    @Test
    public void testLegacyTeacherPHPSQS(){
    	String sqs = "";
        
    	if(user.getEnvironment().equals("reactqa")){
    		sqs = "https://wwwqa.pebblego.com/login.php?sqs="+UserInfo.GARAGETEACHER.getSQS();
    	} else if(user.getEnvironment().equals("site")){
    		sqs = "https://www.pebblego.com/login.php?sqs="+UserInfo.GARAGETEACHER.getSQS();
    	} else{
    		sqs = "https://www.pebblego.com/login.php?sqs=" + UserInfo.GARAGETEACHER.getSQS();
    	}
    	
    	loginPage.getDriver().get(sqs);
    	PGONewTeacherHomeScreen teacher = new PGONewTeacherHomeScreen(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacher.logoutButton));
    	
    	assertTrue("Tests that a teacher can log in using an SQS string",
    			teacher.verifyElementsVisibility(teacher.getInitialVisibleElements()));
    	
    	loginPage = teacher.logout();
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(teacher.logoutButton));
    	loginPage.getDriver().get("https://" + user.getEnvironment() + ".pebblego.com");
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }
    
    @Test
    public void testLegacySQS(){
    	String sqs = "";
    	
    	if(user.getEnvironment().equals("site")){
    		sqs = "https://www.pebblego.com/login/?sqs=WX83SXAzS6te48ZoULSCbA==";
    	} else {
    		sqs = "https://"+user.getEnvironment()+".pebblego.com/login/?sqs=WX83SXAzS6te48ZoULSCbA==";
    	}
    	
    	loginPage.getDriver().get(sqs);
    	StudentHomePageNew student = new StudentHomePageNew(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	assertTrue("Tests that a student can log in through an SQS string",
    			student.verifyElementsVisibility(student.getInitialVisibleElements()));
    	
    	loginPage = student.header.logout();
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }
    
    
    @Test
    public void testUnsignedEula(){
    	user.setCredentials(UserInfo.PGODONT);
    	loginPage.Login(user);

    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginErrorText));
    	//user.customWait().until(ExpectedConditions.attributeToBe(loginPage.username, "value", "pgodont"));
    	
    	String message = loginPage.getDriver().findElement(loginPage.loginErrorText).getText();
    	assertTrue("Asserts that the correct error message is returned when the user enters credentials for a account without a signed Eula", 
    			message.equals("Our EULA document has been updated, please sign in to your administrator page to read and accept before moving forward."));
    	
    	loginPage.refresh();
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }
    
    @Test
    public void testUnsignedEulaTeacher(){
    	user.setCredentials(UserInfo.PGODONTTEACHER);
    	loginPage.Login(user);
    	
    	PGONewEULAAcceptancePage eula = new PGONewEULAAcceptancePage(user);

    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(eula.eulaContent));

    	assertTrue("Asserts that the teacher was redirected to the Eula acceptance page", 
    			eula.isElementPresent(eula.eulaContent));
    	
    	loginPage = new PGONewLoginPage(user);
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    }

    @Test
    public void testGoogleLogin(){
    	user.setCredentials(UserInfo.MASTERGOOGLE);
    	
    	PGONewGooglePage google = loginPage.clickGoogleLogin();
    	
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(loginPage.googleButton));
    	
    	StudentHomePageNew home;
    	
    	if(user.getLocal()){
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(google.email));
    		
        	google.enterEmail(user.getUsername());
        	google.waitImplicitly(5);
        	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(google.passwordNext));
        	google.enterPassword(user.getPassword());
        	
        	google.clickPasswordNext();
    	} else{
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(google.emailLinux));
    		
        	google.enterEmailLinux(user.getUsername());
        	google.waitImplicitly(5);
        	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(google.passwordNextLinux));
        	google.enterPasswordLinux(user.getPassword());
        	
        	google.clickPasswordNextLinux();
    	}
    	
    	home = new StudentHomePageNew(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	assertTrue("Asserts that the google user is able to login and be redirected to the home page",
    			home.verifyElementsVisibility(home.getInitialVisibleElements()));
    	
    	loginPage = home.header.logout();
    }
    
    
    @Test
    public void testGoogleLoginError(){
    	user.setCredentials(UserInfo.GOOGLEERROR);
    	
    	PGONewGooglePage google = loginPage.clickGoogleLogin();
    	
    	google.waitImplicitly(5);
    	
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(loginPage.googleButton));
    	
    	if(user.getLocal()){
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(google.email));
    		
        	google.enterEmail(user.getUsername());
        	google.waitImplicitly(5);
        	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(google.passwordNext));
        	google.enterPassword(user.getPassword());
        	
        	google.clickPasswordNext();
    	} else{
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(google.emailLinux));
    		
        	google.enterEmailLinux(user.getUsername());
        	google.waitImplicitly(5);
        	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(google.passwordNextLinux));
        	google.enterPasswordLinux(user.getPassword());
        	
        	google.clickPasswordNextLinux();
    	}
    	
    	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.username));
    	
    	assertTrue("Asserts that the google user is able to login and be redirected to the home page",
    			user.getDriver().findElement(loginPage.loginErrorText).getText().equals("Your Google account is not linked with a class with access to this product."));
    }
    
    @Test
    public void testAnimalsOnlyReadmoreData(){
    	user.setCredentials(UserInfo.ANIMALSRMSTUDENT);
    	
    	StudentHomePageNew student = (StudentHomePageNew) loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	PGONewContentSelectionPage content = student.clickModuleByName("Animals");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(content.gamesButton));
    	
    	PGONewArticlePage article = content.header.clickRandomArticleButton();
    
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.tabOne));
    	
    	article.clickTabSix();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("related-book-0")));
    	
    	assertTrue("Asserts that the student can see read more books for this animal article", article.isElementPresent(By.id("related-book-0")));
    	
    	student = article.header.clickHomeBreadcrumb();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	content = student.clickModuleByName("Science");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(content.gamesButton));
    	
    	article = content.header.clickRandomArticleButton();
    
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.tabOne));
    	
    	article.clickTabSix();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("related-article-1")));
    	article.waitImplicitly(5);
    	
    	assertTrue("Asserts that the student can not see read more books for this science article", !article.isElementPresent(By.id("related-book-0")));
    }
    
    @Test
    public void testScienceOnlyReadmoreData(){
    	user.setCredentials(UserInfo.SCIENCERMSTUDENT);
    	
    	StudentHomePageNew student = (StudentHomePageNew) loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	PGONewContentSelectionPage content = student.clickModuleByName("Animals");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(content.gamesButton));
    	
    	PGONewArticlePage article = content.header.clickRandomArticleButton();
    
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.tabOne));
    	
    	article.clickTabSix();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("related-article-1")));
    	article.waitImplicitly(5);

    	
    	assertTrue("Asserts that the student can see read more books for this animal article", !article.isElementPresent(By.id("related-book-0")));
    	
    	student = article.header.clickHomeBreadcrumb();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	content = student.clickModuleByName("Science");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(content.gamesButton));
    	
    	article = content.header.clickRandomArticleButton();
    
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.tabOne));
    	
    	article.clickTabSix();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("related-book-0")));
    	
    	assertTrue("Asserts that the student can not see read more books for this science article", article.isElementPresent(By.id("related-book-0")));
    }
}

