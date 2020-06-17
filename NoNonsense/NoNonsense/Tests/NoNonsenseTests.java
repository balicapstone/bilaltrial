package NoNonsense.Tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import NoNonsense.Pages.ContentPopup;
import NoNonsense.Pages.EulaModal;
import NoNonsense.Pages.LandingPage;
import NoNonsense.Pages.LoginModal;
import NoNonsense.Pages.SearchPage;
import SharedClasses.ThirdPartyPage;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;
import UserClasses.UserInfo;

public class NoNonsenseTests {
	
	static User u;
	static LandingPage landing;
	static LoginModal login;
	static SearchPage search;
	
	@BeforeClass
	public static void executeBefore(){
		u = new User(UserInfo.NONOASSESSMENTS);

		landing = new LandingPage(u);
		login = landing.clickLoginLink();
		
		search = login.login(u);
		search.waitImplicitly(5);
	}
	
	
	@AfterClass
	public static void executeAfter(){
		u.quit();
	}
	
	@Rule
    public TestWatcher restart = new TestWatcher(){
    	@Override
    	public void failed(Throwable e, Description description){
    		u.getDriver().close();
    		u.makeNewDriver();
    		
    		landing = new LandingPage(u);
    		landing.waitImplicitly(2);
    		LoginModal login = landing.clickLoginLink();
    		
    		search = login.login(u);
    		search.waitImplicitly(5);
    	}
    };
    
	@Rule
	public Retry retry = new Retry(3);
    
    
	
	@Test
	public void testSearchTitle(){
		search.search("paddington");
		search.waitImplicitly(1);
		
		assertTrue("Asserts that the search will filter results so that 'Paddington Goes To Town' appears as the first search",
				search.getTitleByIndex(1).equals("Paddington Goes to Town"));
	}
	
	@Test
	public void testSearchDescription(){
		search.search("creating");
		search.waitImplicitly(1);
		
		assertTrue("Asserts that the search will filter results so that 'Paddington Goes To Town' appears as the first search",
				search.getTitleByIndex(1).equals("It's My Birthday"));
	}
	
	@Test
	public void testSearchKeyword(){
		search.search("rhymes");
		search.waitImplicitly(1);

		assertTrue("Asserts that the search will filter results so that 'Paddington Goes To Town' appears as the first search",
				search.getTitleByIndex(1).equals("Dear Mother Goose"));
	}
	
	@Test
	public void testUserCantHardLinkIntoLessonPlanDoc(){
		landing = search.header.logout();
		landing.getDriver().get("https://s3.us-east-2.amazonaws.com/qa.nononsenseliteracy.com/documents/ks1faugustusandhissmile.pdf");
		
		ThirdPartyPage tPP = new ThirdPartyPage(landing.getDriver());
				
		assertTrue("Asserts that the user has found themselves on an error page", tPP.getDriver().getPageSource().contains("Access Denied"));
		landing.goBack();
		
		landing.getDriver().get("https://s3.us-east-2.amazonaws.com/qa.nononsenseliteracy.com/documents/ks1faugustusandhissmile.docx");
		
		assertTrue("Asserts that the user has found themselves on an error page", tPP.getDriver().getPageSource().contains("Access Denied"));
		landing.goBack();
		
		login = landing.clickLoginLink();
		
		search = login.login(u);
		search.waitImplicitly(2);
	}
	
	@Test
	public void testUserCantHardLinkIntoAssessmentDoc(){
		landing = search.header.logout();
		ThirdPartyPage tPP = new ThirdPartyPage(landing.getDriver());
		
		landing.getDriver().get("https://s3.us-east-2.amazonaws.com/qa.nononsenseliteracy.com/documents/ks1nfhowtowashawoollymammoth.pdf?");
		assertTrue("Asserts that the user has found themselves on an error page", tPP.getDriver().getPageSource().contains("Access Denied"));
		landing.goBack();
		
		landing.getDriver().get("https://s3.us-east-2.amazonaws.com/qa.nononsenseliteracy.com/documents/ks1nfhowtowashawoollymammoth.docx?");
		assertTrue("Asserts that the user has found themselves on an error page", tPP.getDriver().getPageSource().contains("Access Denied"));
		
		landing.goBack();
		landing.waitImplicitly(1);
		
		login = landing.clickLoginLink();
		
		search = login.login(u);
		search.waitImplicitly(2);
	}
	
	@Test
	public void testClearSearch(){
		search.search("easter");
		
		assertTrue("Asserts that the search will filter for Easter lesson plan",
				search.getTitleByIndex(1).equals("Easter Holiday Letter Assessment"));
		
		
		search.showAll();
		
		assertTrue("Asserts that multiple search results are showing and the show all button is clicked",
				search.getTitleByIndex(1).equals("A Dog's Day") && search.getTitleByIndex(5).equals("A River"));
	}
	
	@Test
	public void testReactiveSearch(){
		search.search("fair");
		
		assertTrue("Asserts that the first search result for Fair is Classic Fairy Tales",
				search.getTitleByIndex(1).equals("Dear Mother Goose"));
		
		search.addToSearch("'s");
		
		assertTrue("Asserts that the added characters 's change the first search result title to Fair's Fair",
				search.getTitleByIndex(1).equals("Fair's Fair"));
		
		search.showAll();
	}
	
	@Test
	public void testOnlyLessonPlans(){
		landing = search.header.logout();
		u.setCredentials(UserInfo.NONOLESSONPLANS);
		LoginModal login = landing.clickLoginLink();
		
		search = login.login(u);
		search.waitImplicitly(2);
		
		assertTrue("Asserts that all documents in the library are lesson plans", search.areAllDocumentsLessonPlans());
		landing = search.header.logout();
		login = landing.clickLoginLink();
		u.setCredentials(UserInfo.GARAGESTUDENT);
		search = login.login(u);
	}
	
	@Test
	public void testOnlyAssessments(){
		//user TeacherA/test
		
		landing = search.header.logout();
		u.setCredentials(UserInfo.NONOASSESSMENTS);
		
		LoginModal login = landing.clickLoginLink();
		
		search = login.login(u);
		search.waitImplicitly(2);
		
		assertTrue("Asserts that all documents in the library are assessments", search.areAllDocumentsAssessments());
		landing = search.header.logout();
		login = landing.clickLoginLink();
		u.setCredentials(UserInfo.GARAGESTUDENT);
		search = login.login(u);
	}
	
	@Test
	public void testContentTitle(){
		ContentPopup content = search.getDocumentsForTitle("Ask Dr K Fisher about Animals");
		content.waitImplicitly(1);
		assertTrue("Asserts that the title on the ContentPopup is correct", content.getModalTitle().equals("Ask Dr K Fisher about Animals"));

		search = content.clickOffModal();		
	}
	
	@Test
	public void testContentAuthor(){
		ContentPopup content = search.getDocumentsForTitle("Ask Dr K Fisher about Animals");
		content.waitImplicitly(1);
		assertTrue("Asserts that the title on the ContentPopup is correct", content.getAuthor().equals("Claire Llewellyn, Author"));
		search.showAll();
		search = content.clickOffModal();
	}
	
	
	@Test
	public void testContentKeyLearningOutcome(){
		ContentPopup content = search.getDocumentsForTitle("Ask Dr K Fisher about Animals");
		content.waitImplicitly(1);
		assertTrue("Asserts that the title on the ContentPopup is correct", content.getKeyLearningOutcome().equals("Key Learning Outcome: To write a letter to an agony aunt and the reply"));
	
		search = content.clickOffModal();
	}
	
	@Test
	public void testKeywords(){
		ContentPopup content = search.getDocumentsForTitle("Ask Dr K Fisher about Animals");
		content.waitImplicitly(1);
		assertTrue("Asserts that the title on the ContentPopup is correct", 
				content.getKeywords().equals("Keywords: KS2, Year 3, Year 4, P3, P4, non-fiction, letters, subordination, clauses, Claire Llewellyn, non-fiction"));
		search = content.clickOffModal();
	}
	
	@Test
	public void testPDFLessonPlans() throws InterruptedException{
		String currentWindow = search.getDriver().getWindowHandle();
		ContentPopup content = search.getDocumentsForTitle("Augustus and His Smile");
		content.waitImplicitly(1);
		ThirdPartyPage pdf = content.clickPDFButton();
		
		content.SwitchToNewPage(currentWindow);
		
		assertTrue("Asserts that the user is able to get to the correct pdf for 'Augustus and His Smile'", pdf.getDriver().getCurrentUrl().contains("https://s3.us-east-2.amazonaws.com/qa.nononsenseliteracy.com/documents/ks1faugustusandhissmile.pdf?"));
		
		pdf.closeCurrentWindow();
		content.getDriver().switchTo().window(currentWindow);
		//close modal when function is usable
	}
	
	@Test
	public void testPDFAssessments(){
		String currentWindow = search.getDriver().getWindowHandle();
		ContentPopup content = search.getDocumentsForTitle("Augustus and His Smile");
		content.waitImplicitly(1);
		ThirdPartyPage pdf = content.clickPDFButton();
		
		content.SwitchToNewPage(currentWindow);
		
		assertTrue("Asserts that the user is able to get to the correct pdf for 'Augustus and His Smile'", pdf.getDriver().getCurrentUrl().contains("https://s3.us-east-2.amazonaws.com/qa.nononsenseliteracy.com/documents/ks1faugustusandhissmile.pdf?"));
		
		pdf.closeCurrentWindow();
		content.getDriver().switchTo().window(currentWindow);
		content.click(By.id("modal-container"));
		//close modal when function is usable
	}
	
	@Test
	public void testTeacherCanSeeEula() throws InterruptedException{
		landing = search.header.logout();
		u.setCredentials(UserInfo.NONODONTSIGN);
		LoginModal login = landing.clickLoginLink();
		
		String old = login.getDriver().getCurrentUrl();
		
		EulaModal eula = login.loginToSignEula(u);
		ThirdPartyPage tPP = eula.clickEulaLink();
		
		eula.SwitchToNewPage(old);
		
		assertTrue(old, tPP.getDriver().getCurrentUrl().equals("https://www.nononsenseliteracy.co.uk/public/pdfs/eula-nononsense.pdf"));
		//tPP.closeCurrentWindow();
		
		tPP.quit();
		
		//eula.getDriver().close();
		u.makeNewDriver();
		landing = new LandingPage(u);
		landing.waitImplicitly(2);
		login = landing.clickLoginLink();
		
		search = login.login(u);
	}
		
	//Fill Out Once it is decided how to handle no Results
	public void testNoResults(){
		
	}
	
	public void testALLLinksWithOutput(){
		
	}
}
