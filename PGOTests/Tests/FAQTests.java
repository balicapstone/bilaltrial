package Tests;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import PGOPages.FAQPage;
import PGOPages.LoginPage;
import PGOPages.SystemRequirementsPage;
import PGOPages.TrialPage;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.User;
import UserClasses.UserInfo;
import static org.junit.Assert.*;

public class FAQTests{
	static LoginPage home;
	static User user;
	static FAQPage faq;
    
	@BeforeClass
	public static void executeBefore(){
		user = new User(UserInfo.GARAGESTUDENT);
		
		try{
			home = new LoginPage(user);
			faq = home.navBar.clickFAQButton();
		}catch(Exception e){
			user.getWatcher().TakeScreenshot("FailureInRunningExecuteBeforeForFAQTests");
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
    		
    		home.closeCurrentWindow();
    		user.makeNewDriver();
    		home = new LoginPage(user);
    		faq = home.navBar.clickFAQButton();
    	}
    };

	@Rule
	public Retry retry = new Retry(3);
    
	/**
     * Tests that the FAQ Placing an Order Tab works and that all elements are present.
     */
	@Test
	@Category(SmokeTests.class)
    public void testPlacingAnOrderTab(){
    	faq.clickPlacingAnOrderTab();
    	assertTrue("Asserts that the Placing An Order Tab works and that all the correct elements are appearing",
    			faq.verifyElementsVisibility(faq.getPlacingAnOrderTabElements()));
    }
    
    /**
     * Tests that the FAQ Troubleshooting Tab works and that all elements are present.
     */
	@Test
	@Category(SmokeTests.class)
    public void testTroubleshootingTab(){
    	faq.clickTroubleshootingTab();
    	assertTrue("Asserts that the Troubleshooting Tab works and that all the correct elements are appearing",
    			faq.verifyElementsVisibility(faq.getTroubleshootingTabElements()));
    }
    
    /**
     * Tests that the FAQ General Questions Tab works and that all elements are present.
     */
	@Test
	@Category(SmokeTests.class)
    public void testGeneralQuestionsTab(){
    	faq.clickGeneralQuestionsTab();
    	assertTrue("Asserts that the FAQ General Question Tab works and that all the correct elements are appearing",
    			faq.verifyElementsVisibility(faq.getGeneralQuestionTabElements()));
    }
    
    /**
     * Tests that the FAQ Trial Page Link works and that all elements are present.
     */
	@Test
    public void testTrialPageElementsVisible(){
		faq.clickPlacingAnOrderTab();
    	TrialPage trial = faq.clickTrialPageLink();
    	assertTrue("Asserts that the FAQ Trial Page Link works and that all elements are present",
    			trial.verifyElementsVisibility(trial.getInitialVisibleElements()));
    	faq = trial.navBar.clickFAQButton();
    }
    
    /**
     * Tests that the Placing An Order Link works and that the footer element is present.
     * @throws InterruptedException 
     */
	@Test
    public void testPlacingAnOrderLink() throws InterruptedException{
    	assertTrue("Asserts that the Placing and Order link works and that the correct page populates",
    			faq.verifyLinkByElement(faq.placeAnOrderLink)
    			&& faq.verifyLinkContainsURLByElement(faq.placeAnOrderLink, "http://www.capstonepub.com/library/digital/pebble-go/"));
	}
    
    /**
     * Tests that the Find a Sales Representative Link works and that the footerLogo is present on the page.
     * @throws InterruptedException
     */
	@Test
    public void testFindASalesRepLink() throws InterruptedException{
    	assertTrue("Asserts that Find a Sales Representative Link works and brings you to the Find a Sales Representative page",
    			faq.verifyLinkByElement(faq.contactSalesRepLink)
    			&& faq.verifyLinkContainsURLByElement(faq.contactSalesRepLink, "http://www.capstonepub.com/library/service/find-a-sales-representative/"));
    }
    
    /**
     * Tests that the System Requirements Page Link is present and redirects to the System Requirements Page.
     */
	@Test
    public void testSystemRequirementsLink(){
    	faq.clickTroubleshootingTab();
    	
    	SystemRequirementsPage sRPage = faq.clickSystemRequirementsLink();
    	assertTrue("Asserts that the System Requirements Link on the Troubleshooting Tab loads the correct page", 
    			sRPage.verifyElementsVisibility(sRPage.getInitialVisibleElements()));
    	faq = sRPage.navBar.clickFAQButton();
    }
 
    /**
     * Tests that the Pebble Go link on the General Questions tab redirects to the Home Page.
     * @throws InterruptedException
     */
	@Test
    public void testGeneralQuestionsPebbleGoLink() throws InterruptedException{    	
    	faq.clickGeneralQuestionsTab();

    	home = faq.clickPebbleGoLink();
    	Set<String> windows = home.getDriver().getWindowHandles();
    	assertTrue(windows.size() == 1); 	
    	assertTrue("Asserts that the PebbleGo Link under General Questions takes user to the PebbleGo homepage", 
    			home.verifyElementsVisibility(home.getInitialVisibleElements()));
    	faq = home.navBar.clickFAQButton();
    }
    
    /**
     * Tests that the Awards link on the General Questions tab redirects to a third party page.
     * http://www.thedigitalshift.com/2014/04/metadata/stellar-databases-slj-readers-favorites-essential-electronic-resources/
     * @throws InterruptedException
     */
	@Test
    public void testGeneralQuestionsAwardsLink() throws InterruptedException{
		faq.clickGeneralQuestionsTab();
		
    	assertTrue("Asserts that the Awards link on the General Questions tab redirects to the correct third party page",
    			faq.verifyLinkByElement(faq.awardsLink)
    			&& faq.verifyLinkContainsURLByElement(faq.awardsLink, "http://www.thedigitalshift.com/2014/04/metadata/stellar-databases-slj-readers-favorites-essential-electronic-resources/"));
    }
    
    /**
     * Tests that the Animals link in the General Questions tab opens a new window to the animals.pdf file.
     * @throws InterruptedException
     */
	@Test
    public void testGeneralQuestionsAnimalsLink() throws InterruptedException{
		faq.clickGeneralQuestionsTab();
		
    	assertTrue("Asserts that the Animals Content link opens to animals.pdf",
    			faq.verifyLinkByElement(faq.animalsLink)
    			&& faq.verifyLinkContainsURLByElement(faq.animalsLink, "pebblego.com/docs/animals.pdf"));
    }
	
	 /**
     * Tests that the Science link in the General Questions tab opens a new window to the animals.pdf file.
     * @throws InterruptedException
     */
	@Test
    public void testFAQGeneralQuestionsScienceLink() throws InterruptedException{
    	faq.clickGeneralQuestionsTab();
    	
    	assertTrue("Asserts that the Science Content link opens to science.pdf",
    			faq.verifyLinkByElement(faq.scienceLink)
    			&& faq.verifyLinkContainsURLByElement(faq.scienceLink, "pebblego.com/docs/science.pdf"));
    }

    /**
     * Tests that the Biographies link in the General Questions tab opens a new window to the biographies.pdf file.
     * @throws InterruptedException
     */
	@Test
    public void testGeneralQuestionsBiographiesLink() throws InterruptedException{ 	
		faq.clickGeneralQuestionsTab();
		
    	assertTrue("Asserts that the Biographies Content link opens to biographies.pdf",
    			faq.verifyLinkByElement(faq.biographiesLink)
    			&& faq.verifyLinkContainsURLByElement(faq.biographiesLink, "pebblego.com/docs/biographies.pdf"));
    }

    /**
     * Tests that the Social Studies link in the General Questions tab opens a new window to the socialstudies.pdf file.
     * @throws InterruptedException
     */
	@Test
    public void testGeneralQuestionsSocialStudiesLink() throws InterruptedException{
    	faq.clickGeneralQuestionsTab();
    	
    	assertTrue("Asserts that the Social Studies Content link opens to socialstudies.pdf",
    			faq.verifyLinkByElement(faq.socialStudiesLink)
    			&& faq.verifyLinkContainsURLByElement(faq.socialStudiesLink, "pebblego.com/docs/socialstudies.pdf"));
    }
    
    /**
     * Tests that the Dinosaurs link in the General Questions tab opens a new window to the pebble_dinos_articles.pdf file.
     * @throws InterruptedException
     */
	@Test
    public void testGeneralQuestionsDinosaursLink() throws InterruptedException{
    	faq.clickGeneralQuestionsTab();
    	
    	assertTrue("Asserts that the Dinosaurs Content link opens to pebblego_dinos_articles.pdf",
    			faq.verifyLinkByElement(faq.dinosaursLink)
    			&& faq.verifyLinkContainsURLByElement(faq.dinosaursLink, "pebblego.com/docs/dinosaurs/pebblego_dinos_articles.pdf"));
    }
    
    /**
     * Tests that the Animales link in the General Questions tab opens a new window to the pebble_dinos_articles.pdf file.
     * @throws InterruptedException
     */
	@Test
    public void testGeneralQuestionsAnimalesLink() throws InterruptedException{
    	faq.clickGeneralQuestionsTab();
    	
    	assertTrue("Asserts that the Animales Content link opens to pebblego_dinos_articles.pdf",
    			faq.verifyLinkByElement(faq.animalesLink)
    			&& faq.verifyLinkContainsURLByElement(faq.animalesLink, "pebblego.com/docs/animales.pdf"));
    }
	
	 /**
     * Tests that the Ciencia link in the General Questions tab opens a new window to the pebble_dinos_articles.pdf file.
     * @throws InterruptedException
     */
	@Test
    public void testGeneralQuestionsCienciaLink() throws InterruptedException{
    	faq.clickGeneralQuestionsTab();
    	
    	assertTrue("Asserts that the Ciencia Content link opens to ciencia.pdf",
    			faq.verifyLinkByElement(faq.cienciaLink)
    			&& faq.verifyLinkContainsURLByElement(faq.cienciaLink, "pebblego.com/docs/ciencia.pdf"));
    }
	
	 /**
     * Tests that the Ciencia link in the General Questions tab opens a new window to the pebble_dinos_articles.pdf file.
     * @throws InterruptedException
     */
	@Test
    public void testGeneralQuestionsBiografiasLink() throws InterruptedException{
    	faq.clickGeneralQuestionsTab();
    	
    	assertTrue("Asserts that the Animales Content link opens to biografias.pdf",
    			faq.verifyLinkByElement(faq.biografias)
    			&& faq.verifyLinkContainsURLByElement(faq.biografias, "pebblego.com/docs/biografias.pdf"));
    }
	
	 /**
     * Tests that the Ciencia link in the General Questions tab opens a new window to the pebble_dinos_articles.pdf file.
     * @throws InterruptedException
     */
	@Test
    public void testGeneralQuestionsEstudiosSocialesLink() throws InterruptedException{
    	faq.clickGeneralQuestionsTab();
    	
    	assertTrue("Asserts that the Animales Content link opens to estudiossociales.pdf",
    			faq.verifyLinkByElement(faq.estudiosSociales)
    			&& faq.verifyLinkContainsURLByElement(faq.estudiosSociales, "pebblego.com/docs/estudiossociales.pdf"));
    }
	
	
    /**
     * Tests that the first Sale Representative Link in the General Questions tab opens a new window a third party page and that the
     * footerLogo element is present.
     * http://www.capstonepub.com/library/service/find-a-sales-representative/
     * @throws InterruptedException
     */
	@Test
    public void testGeneralQuestionsFirstSalesRepLink() throws InterruptedException{
    	faq.clickGeneralQuestionsTab();
    	
    	assertTrue("Asserts that the first sales rep link on the page opens to the Find a Sales Representative page",
    			faq.verifyLinkByElement(faq.generalQuestionsSalesRepLinkOne)
    			&& faq.verifyLinkContainsURLByElement(faq.generalQuestionsSalesRepLinkOne, "http://www.capstonepub.com/library/service/find-a-sales-representative/"));
    }
    
    /**
     * Tests that the second Sale Representative Link in the General Questions tab opens a third party page in a new window and that the 
     * footerLogo element is present.
     * http://www.capstonepub.com/library/service/find-a-sales-representative/
     * @throws InterruptedException
     */
	@Test
    public void testGeneralQuestionsSecondSalesRepLink() throws InterruptedException{
		faq.clickGeneralQuestionsTab();
		
    	assertTrue("Asserts that the second sales rep link on the page opens to the Find a Sales Representative page",
    			faq.verifyLinkByElement(faq.generalQuestionsSalesRepLinkTwo)
    			&& faq.verifyLinkContainsURLByElement(faq.generalQuestionsSalesRepLinkTwo, "http://www.capstonepub.com/library/service/find-a-sales-representative/"));
    } 
	
	 @Rule
	    public TestRule watcher = new TestWatcher() {
	       protected void starting(Description description) {
	          System.out.println("Starting test: " + description.getMethodName());
	        
	          
	       }
	    };
}
