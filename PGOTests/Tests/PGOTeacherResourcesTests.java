package Tests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.support.ui.ExpectedConditions;
import PGOPages.LoginPage;
import PGOStudentResources.AnimalesContentPage;
import PGOStudentResources.AnimalsContentPage;
import PGOStudentResources.BiographiesContentPage;
import PGOStudentResources.ContentSelectionPage;
import PGOStudentResources.DinosaursContentPage;
import PGOStudentResources.ScienceContentPage;
import PGOStudentResources.SocialStudiesContentPage;
import PGOStudentResources.StudentHomePage;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;
import UserClasses.UserInfo;

public class PGOTeacherResourcesTests{
	static LoginPage loginPage;
	static StudentHomePage studentHome;
	static AnimalesContentPage animals;
	static User student;
	static String communitiesLink = "http://community.mycapstone.com/";
	
	@BeforeClass
	public static void executeBefore(){
		student = new User(UserInfo.GARAGESTUDENT);
		
		loginPage = new LoginPage(student);
		
		try{
			studentHome = (StudentHomePage) loginPage.Login(student);
		} catch(Exception e){
			student.getWatcher().TakeScreenshot("FailureToRunExecuteBeforeForPGOTeacherResourcesTests");
		}
	}
	
	
	@AfterClass
	public static void executeAfter(){
		student.quit();
	}
		
	@Rule
    public TestWatcher restart = new TestWatcher(){
    	@Override
    	public void failed(Throwable e, Description description){
    		try{
    			student.getWatcher().TakeScreenshot(description.getDisplayName());
    		} catch(Exception t){
    			System.out.print("There was a failure while trying to take a screenshot for " + description.getDisplayName());
    		}
    		
    		student.quit();
    		student.makeNewDriver();
    		
    		loginPage = new LoginPage(student);
    		studentHome = (StudentHomePage) loginPage.Login(student);
    	}
    };
    
	@Rule
	public Retry retry = new Retry(3);
    
    @Test
    public void testAnimalsCommunityLink() throws InterruptedException{
		student.customWait().until(ExpectedConditions.visibilityOfElementLocated(studentHome.animalsModule));
		
		AnimalsContentPage animals = studentHome.clickAnimalsModule();
		student.customWait().until(ExpectedConditions.visibilityOfElementLocated(animals.communitiesLink));
		
    	assertTrue("Asserts the user can navigate to the Communities page through the Animals Database",
    			animals.verifyLinkContainsURLByElement(animals.communitiesLink, communitiesLink));
    		
    	studentHome = animals.clickHomeBreadcrumb();
    	student.customWait().until(ExpectedConditions.visibilityOfElementLocated(studentHome.animalsModule));
    }
    
    @Test
    public void testScienceCommunityLink() throws InterruptedException{		
		ScienceContentPage science = studentHome.clickScienceModule();
		student.customWait().until(ExpectedConditions.visibilityOfElementLocated(science.communitiesLink));
		
    	assertTrue("Asserts the user can navigate to the Communities page through the Science Database",
    			science.verifyLinkContainsURLByElement(science.communitiesLink, communitiesLink));

    	studentHome = science.clickHomeBreadcrumb();
    	student.customWait().until(ExpectedConditions.visibilityOfElementLocated(studentHome.scienceModule));
    }
    
    @Test
    public void testBiographiesCommunityLink() throws InterruptedException{		
		BiographiesContentPage biographies = studentHome.clickBiographiesModule();
		student.customWait().until(ExpectedConditions.visibilityOfElementLocated(biographies.communitiesLink));

    	assertTrue("Asserts the user can navigate to the Communities page through the Biographies Database",
    			biographies.verifyLinkContainsURLByElement(biographies.communitiesLink, communitiesLink));

    	studentHome = biographies.clickHomeBreadcrumb();
    	student.customWait().until(ExpectedConditions.visibilityOfElementLocated(studentHome.biographiesModule));
    }
    
    @Test
    public void testSocialStudiesCommunityLink() throws InterruptedException{
		SocialStudiesContentPage socialStudies = studentHome.clickSocialStudiesModule();
		student.customWait().until(ExpectedConditions.visibilityOfElementLocated(socialStudies.communitiesLink));
		
    	assertTrue("Asserts the user can navigate to the Communities page through the Social Studies Database",
    			socialStudies.verifyLinkContainsURLByElement(socialStudies.communitiesLink, "http://community.mycapstone.com/"));
    	
    	studentHome = socialStudies.clickHomeBreadcrumb();
    	student.customWait().until(ExpectedConditions.visibilityOfElementLocated(studentHome.socialStudiesModule));
    }
    
    @Test
    public void testDinosaursCommunityLink() throws InterruptedException{		
		DinosaursContentPage dinosaurs = studentHome.clickDinosaursModule();
		student.customWait().until(ExpectedConditions.visibilityOfElementLocated(dinosaurs.communitiesLink));
		
    	assertTrue("Asserts the user can navigate to the Communities page through the Dinosaurs Database",
    			dinosaurs.verifyLinkContainsURLByElement(dinosaurs.communitiesLink, communitiesLink));

    	studentHome = dinosaurs.clickHomeBreadcrumb();
       	student.customWait().until(ExpectedConditions.visibilityOfElementLocated(studentHome.dinosaursModule));
    }
    
    @Test
    public void testAnimalesCommunityLink() throws InterruptedException{		
		AnimalesContentPage animales = studentHome.clickAnimalesModule();
		student.customWait().until(ExpectedConditions.visibilityOfElementLocated(animales.communitiesLink));
		
    	assertTrue("Asserts the user can navigate to the Communities page through the Animales Database",
    			animales.verifyLinkContainsURLByElement(animales.communitiesLink, communitiesLink));

    	studentHome = animales.clickHomeBreadcrumb();
       	student.customWait().until(ExpectedConditions.visibilityOfElementLocated(studentHome.animalesModule));
    }
    
    @Test
    public void testCienciaCommunityLink() throws InterruptedException{		
		ContentSelectionPage ciencia = studentHome.clickCienciaModule();
		student.customWait().until(ExpectedConditions.visibilityOfElementLocated(ciencia.communitiesLink));
		
    	assertTrue("Asserts the user can navigate to the Communities page through the Animales Database",
    			ciencia.verifyLinkContainsURLByElement(ciencia.communitiesLink, communitiesLink));

    	studentHome = ciencia.clickHomeBreadcrumb();
       	student.customWait().until(ExpectedConditions.visibilityOfElementLocated(studentHome.cienciaModule));
    }
    
    @Test
    public void testBiografiasCommunityLink() throws InterruptedException{		
		ContentSelectionPage biografias = studentHome.clickBiografiasModule();
		student.customWait().until(ExpectedConditions.visibilityOfElementLocated(biografias.communitiesLink));
		
    	assertTrue("Asserts the user can navigate to the Communities page through the Animales Database",
    			biografias.verifyLinkContainsURLByElement(biografias.communitiesLink, communitiesLink));

    	studentHome = biografias.clickHomeBreadcrumb();
       	student.customWait().until(ExpectedConditions.visibilityOfElementLocated(studentHome.biografiasModule));
    }
    
    @Test
    public void testEstusiosSocialesCommunityLink() throws InterruptedException{		
		ContentSelectionPage estudios = studentHome.clickEstudiosSocialesModule();
		student.customWait().until(ExpectedConditions.visibilityOfElementLocated(estudios.communitiesLink));
		
    	assertTrue("Asserts the user can navigate to the Communities page through the Animales Database",
    			estudios.verifyLinkContainsURLByElement(estudios.communitiesLink, communitiesLink));

    	studentHome = estudios.clickHomeBreadcrumb();
       	student.customWait().until(ExpectedConditions.visibilityOfElementLocated(studentHome.estudiosSocialesModule));
    }
    @Rule
    public TestRule watcher = new TestWatcher() {
       protected void starting(Description description) {
          System.out.println("Starting test: " + description.getMethodName());
        
          
       }
    };
}
