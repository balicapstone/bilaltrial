package Tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;

import PGOPages.DocumentsPage;
import PGOPages.LoginPage;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;
import UserClasses.UserInfo;
import static org.junit.Assert.*;

public class DocumentsTests{
	static LoginPage home;
	static User user;
	static DocumentsPage docs;
	
	@BeforeClass
	public static void executeBefore(){
		user = new User(UserInfo.GARAGESTUDENT);

		try{
			home = new LoginPage(user);
			docs = home.navBar.clickDocumentsButton();
		} catch(Exception e){
			user.getWatcher().TakeScreenshot("FailureToSetUpDocumentTests");
		}
	}
	
	
	@AfterClass
	public static void executeAfter(){
		home.closeCurrentWindow();
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
    		
    		user = new User(UserInfo.GARAGESTUDENT);
    		
    		home = new LoginPage(user);
    		docs = home.navBar.clickDocumentsButton();
    	}
    };
	
	@Rule
	public Retry retry = new Retry(3);
    
	/**
     * Tests that the Animals Content link opens a new window and that the correct animals.pdf document loads.
     * @throws InterruptedException
     */ 
	@Test
    public void testAnimalsPDF() throws InterruptedException{
    	assertTrue("Asserts that the Animals Content link opens a new window and that the correct animals.pdf document loads",
    			docs.verifyLinkByElement(By.linkText("Animals Content"))
    			&& docs.verifyLinkContainsURLByElement(By.linkText("Animals Content"), "pebblego.com/docs/animals.pdf"));
	}

    /**
     * Tests that the Science Content link opens a new window and that the correct science_v2.pdf document loads.
     * @throws InterruptedException
     */
    @Test
    public void testSciencePDF() throws InterruptedException{
    	assertTrue("Asserts that the Science Content link opens a new window and that the correct science_v2.pdf document loads.",
    			docs.verifyLinkByElement(By.linkText("Science Content"))
    			&& docs.verifyLinkContainsURLByElement(By.linkText("Science Content"), "pebblego.com/docs/science_v2.pdf"));
    }

    /**
     * Tests that the Biographies Content link opens a new window and that the correct biographies.pdf document loads.
     * @throws InterruptedException
     */
    @Test
    public void testBiographiesPDF() throws InterruptedException{
    	assertTrue("Asserts that the Biographies Content link opens a new window and that the correct biographies.pdf document loads", 
    			docs.verifyLinkByElement(By.linkText("Biographies Content"))
    			&& docs.verifyLinkContainsURLByElement(By.linkText("Biographies Content"), "pebblego.com/docs/biographies.pdf"));
    }
    
    /**
     * Tests that the Social Studies Content link opens a new window and that the correct socialstudies.pdf document loads.
     * @throws InterruptedException
     */
    @Test
    public void testSocialStudiesPDF() throws InterruptedException{
    	assertTrue("Asserts that the Social Studies Content link opens a new window and that the correct socialstudies.pdf document loads.",
    			docs.verifyLinkByElement(By.linkText("Social Studies Content"))
    			&& docs.verifyLinkContainsURLByElement(By.linkText("Social Studies Content"), "pebblego.com/docs/socialstudies.pdf"));
    }
    
    /**
     * Tests that the Dinosaurs Content link opens a new window and that the correct pebblego_dinos_articles.pdf document loads.
     * @throws InterruptedException
     */
    @Test
    public void testDinosaursPDF() throws InterruptedException{
    	assertTrue("Asserts that the Dinosaurs Content link opens a new window and that the correct pebblego_dinos_articles.pdf document loads.",
    			docs.verifyLinkByElement(By.linkText("Dinosaurs Content"))
    			&& docs.verifyLinkContainsURLByElement(By.linkText("Dinosaurs Content"), "pebblego.com/docs/dinosaurs/pebblego_dinos_articles.pdf"));
    }
    
    /**
     * Tests that the Animales Content link opens a new window and that the correct animales.pdf document loads.
     * @throws InterruptedException
     */
    @Test
    public void testAnimalesPDF() throws InterruptedException{    	
    	assertTrue("Asserts that the Animales Content link opens a new window and that the correct animales.pdf document loads.",
    			docs.verifyLinkByElement(By.linkText("Animales Content"))
    			&& docs.verifyLinkContainsURLByElement(By.linkText("Animales Content"), "pebblego.com/docs/animales.pdf"));
    }
    
    /**
     * Tests that the Ciencia Content link opens a new window and that the correct ciencia.pdf document loads.
     * @throws InterruptedException
     */
    @Test
    public void testCienciaPDF() throws InterruptedException{    	
    	assertTrue("Asserts that the Ciencia Content link opens a new window and that the correct animales.pdf document loads.",
    			docs.verifyLinkByElement(By.linkText("Ciencia Content")));
    	assertTrue("Asserts that the Ciencia Content link is the correct document",
    			docs.verifyLinkContainsURLByElement(By.linkText("Ciencia Content"), "pebblego.com/docs/ciencia.pdf"));
    }
    
    /**
     * Tests that the Biografias Content link opens a new window and that the correct ciencia.pdf document loads.
     * @throws InterruptedException
     */
    @Test
    public void testBiografiasPDF() throws InterruptedException{    	
    	assertTrue("Asserts that the Biografias Content link opens a new window and that the correct animales.pdf document loads.",
    			docs.verifyLinkByElement(By.linkText("Biografías Content")));
    	assertTrue("Asserts that the Biografias content link contents the correct document",
    			docs.verifyLinkContainsURLByElement(By.linkText("Biografías Content"), "pebblego.com/docs/biografias.pdf"));
    }
    
    /**
     * Tests that the Estudios Scoiales Content link opens a new window and that the correct ciencia.pdf document loads.
     * @throws InterruptedException
     */
    @Test
    public void testEstudiosSocialesPDF() throws InterruptedException{    	
    	assertTrue("Asserts that the Estudios Sociales Content link opens a new window and that the correct animales.pdf document loads.",
    			docs.verifyLinkByElement(By.linkText("Estudios Sociales Content"))
    			&& docs.verifyLinkContainsURLByElement(By.linkText("Estudios Sociales Content"), "pebblego.com/docs/estudiossociales.pdf"));
    }
    
    /**
     * Tests that the Multimodal Literacy link opens a new window and that the correct whitepaper_literacy.pdf document loads.
     * @throws InterruptedException
     */
    @Test
    public void testMultimodalLiteracyLink() throws InterruptedException{
    	assertTrue("Asserts that the Multimodal Literacy link opens a new window and that the correct whitepaper_literacy.pdf document loads.",
    			docs.verifyLinkByElement(By.linkText("PebbleGo and Multimodal Literacy"))
    			&& docs.verifyLinkContainsURLByElement(By.linkText("PebbleGo and Multimodal Literacy"), "pebblego.com/docs/whitepaper_literacy.pdf"));
    }
    @Rule
    public TestRule watcher = new TestWatcher() {
       protected void starting(Description description) {
          System.out.println("Starting test: " + description.getMethodName());
        
          
       }
    };
    
}
