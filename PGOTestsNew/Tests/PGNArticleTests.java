package Tests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import PGNModals.PGNDictionaryModal;
import PGNPages.PGNLoginPage;
import PGNStudentResources.PGNArticlePage;
import PGNStudentResources.PGNArticleType;
import PGNStudentResources.PGNDatabasePage;
import PGNStudentResources.PGNIndiansDatabaseHome;
import PGNStudentResources.PGNScienceDatabaseHome;
import PGNStudentResources.PGNStatesDatabaseHome;
import PGNStudentResources.PGNStudentHomePage;
import SharedClasses.ThirdPartyPage;
import TrueUserTests.TrueUser.PGNSmokeTest;
import UserClasses.User;
import UserClasses.UserInfo;

public class PGNArticleTests {
	public static PGNLoginPage login;
	public static PGNStudentHomePage student;
	public static User user;
	
	@BeforeClass
    public static void executeBefore(){
		user = new User(UserInfo.GARAGESTUDENT);
		
 		login = new PGNLoginPage(user);
 		student = (PGNStudentHomePage) login.login(user);

 		student.waitImplicitly(10);
		student.clickPebbleGoNextTab();
		student.waitImplicitly(5);
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
    		
    		login = new PGNLoginPage(user);
    		student = (PGNStudentHomePage) login.login(user);
    		
    		student.waitImplicitly(10);
    		student.clickPebbleGoNextTab();
    		student.waitImplicitly(5);*/
    	}
    };
    /*
    @Rule
    public Retry retry = new Retry(3);*/
	
	@Test
	@Category(PGNSmokeTest.class)
	public void testDictionaryModalPronounce(){
		PGNStatesDatabaseHome states = student.goToStates();
		PGNArticlePage article = states.clickCategory("Midwest").clickArticle("Minnesota");
		
		PGNDictionaryModal dictionary = article.openDictionary(); 
		dictionary.search("Dog");
		
		assertTrue("Asserts that webdriver can find and interact with the pronounce button", article.dictionary.pronounce());
		article.waitImplicitly(2);
		article.dictionary.closeModal();
		student = article.clickHomeBreadcrumb();
	}
	
	@Test
	public void testDictionaryMultipleResults(){
		PGNStatesDatabaseHome states = student.goToStates();
		PGNArticlePage article = states.clickCategory("Midwest").clickArticle("Minnesota");
		
		article.openDictionary().search("Dog ");
		article.waitImplicitly(3);
		
		assertTrue("Asserts that the dictionary can display multiple results for the same word",
				article.dictionary.verifyResult("dog: Noun") && article.dictionary.verifyResult("dog: Transitive verb"));//article.dictionary);
		article.dictionary.closeModal();
		student = article.clickHomeBreadcrumb();
	}
	
	@Test
	@Category(PGNSmokeTest.class)
	public void testDictionaryModalOpensBlank(){
		PGNStatesDatabaseHome states = student.goToStates();
		PGNArticlePage article = states.clickCategory("Midwest").clickArticle("Minnesota");
		
		article.openDictionary();
		
		String test = article.dictionary.getSearch();
		assertTrue("Asserts that the search field populates without text in it", test.equals(""));
		article.dictionary.closeModal();
		student = article.clickHomeBreadcrumb();
	}
	
	@Test
	public void testDictionarySuggestedWords(){
		PGNStatesDatabaseHome states = student.goToStates();
		PGNArticlePage article = states.clickCategory("Midwest").clickArticle("Minnesota");
		
		article.openDictionary().search("fe").search("as");
		article.waitImplicitly(3);
		
		article.dictionary.clickSuggestedWord("feta");
		article.waitImplicitly(3);
		
		assertTrue("Asserts thats suggested words appear and that a user can navigate to a suggested word", 
				article.dictionary.verifyResult("feta: Noun") && article.dictionary.getTitle().equals("feta"));
		article.dictionary.closeModal();
		student = article.clickHomeBreadcrumb();
	}
	
	@Test
	@Category(PGNSmokeTest.class)
	public void testRelatedArticles(){
		PGNStatesDatabaseHome states = student.goToStates();
		PGNArticlePage article = states.clickCategory("Midwest").clickArticle("Minnesota");
		
		article.openRelatedArticles().clickRelatedArticleByName("Sioux", PGNArticleType.INDIANS);
		
		assertTrue("Asserts that Related Articles work and link to the correct Related Articles", article.getTitle().equals("Sioux"));
		student = article.clickHomeBreadcrumb();
	}
	
	@Test
	@Category(PGNSmokeTest.class)
	public void testTerminologyModal(){
		PGNIndiansDatabaseHome indians = student.goToIndians();
		PGNArticlePage article = indians.clickCategory("Subarctic Tribal Nations").clickArticle("Cree");
		article.waitImplicitly(2);
		article.openTerminologyModal();
		
		assertTrue("Asserts that the Terminology modal appears and has the correct title and description",
				article.terminology.getModalText().equals("“American Indian” and “Native American” are often used to describe a person with ancestry related to the indigenous people of North America. The term “American Indian” is used throughout the PebbleGo Next States and American Indian Studies database. “American Indian” is the term used in U.S. federal law and by all major U.S. Indian agencies and organizations, such as the Bureau of Indian Affairs and the National Museum of the American Indian. The term “Native American” could be interpreted as representing anyone born on the continent of North America. The usage of “American Indian” aims to encompass only those belonging to a recognized tribe. Ultimately, most American Indians would prefer to be referred to as their individual tribe name.") &&
				article.terminology.getModalTitle().equals("The Terms \"American Indian\" and “Native American\""));
		
		article.terminology.closeModal();
		student = article.clickHomeBreadcrumb();
	}
	
	@Test
	@Category(PGNSmokeTest.class)
	public void testStatesFlagModal() throws InterruptedException{
		PGNStatesDatabaseHome states = student.goToStates();
		PGNArticlePage article = states.clickCategory("Midwest").clickArticle("Minnesota", PGNArticleType.STATES);
		article.waitImplicitly(2);
		
		article.openActivity();
		
		assertTrue("Asserts that the Acitivity modal opens to the Flag tab", article.activity.getActiveTab().equals("Flag"));
		
		assertTrue("Asserts that the Print Activity button on the Flag modal tab opens the correct activity",
		article.verifyLinkByElement(article.activity.printButton)
		&& article.verifyLinkContainsURLByElement(article.activity.printButton,"/pdf/pgnstates_minnesota_document_2.pdf"));
		
		article.activity.closeModal();
		student = article.clickHomeBreadcrumb();
	}
	
	@Test
	@Category(PGNSmokeTest.class)
	public void testStatesMapModal() throws InterruptedException{
		PGNStatesDatabaseHome states = student.goToStates();
		PGNArticlePage article = states.clickCategory("Midwest").clickArticle("Minnesota", PGNArticleType.STATES);
		article.waitImplicitly(2);
		
		article.openActivity();
	//	article.activity.clickLink("Map");
		
		//*[@id="root"]/div/div[2]/main/div/div[1]/div[2]/div
		assertTrue("Asserts a user can navigate to the Map Tab of the activities modal", article.activity.getElementText(article.MapText).equals("Map"));
		
		
		String docsWindow = article.getDriver().getWindowHandle();
		
		ThirdPartyPage tpp = article.activity.print();
	/*	assertTrue("Asserts that the Print Activity button on the Map Modal tab opens the correct activity",
				article.verifyLinkByElement(article.activity.printButton)
				&& article.verifyLinkContainsURLByElement(article.activity.printButton, "/pdf/pgnstates_minnesota_document_1.pdf"));*/
		
		
		assertTrue("Asserts that the Print Activity button on the Map Modal tab opens the correct activity",
				tpp.verifyNewWindow(docsWindow, "/pdf/pgnstates_minnesota_document_1.pdf"));
		// article.SwitchToNewPage(docsWindow);
		article.activity.closeModal();
		student = article.clickHomeBreadcrumb();
	}
	
	@Test
	@Category(PGNSmokeTest.class)
	public void testStatesCriticalThinkingQuestions() throws InterruptedException{
		PGNStatesDatabaseHome states = student.goToStates();
		PGNArticlePage article = states.clickCategory("Midwest").clickArticle("Minnesota", PGNArticleType.STATES);
		article.waitImplicitly(2);
		
		article.openActivity();
		article.waitImplicitly(2);
		
		article.activity.clickLink("Critical Thinking");
		
		assertTrue("Asserts a user can navigate to the Critical Thinking Questions tab of the activities modal",article.activity.getActiveTab().equals("Critical Thinking"));
		
		assertTrue("Asserts that the Print Activity button on the Critical Thinking Questions tab opens the correct activity", 
				article.verifyLinkByElement(article.activity.printButton)
				&& article.verifyLinkContainsURLByElement(article.activity.printButton, "/pdf/pgnstates_minnesota_document_3.pdf"));
		
		article.activity.closeModal();
		student = article.clickHomeBreadcrumb();
	}
	
	@Test
	public void testScienceCriticalThinkingQuestions() throws InterruptedException{
		PGNScienceDatabaseHome states = student.goToScience();
		PGNArticlePage article = states.clickCategory("Earth Science").clickCategory("Atmosphere").clickArticle("Greenhouse Gases and Global Warming");
		article.waitImplicitly(2);
		
		article.openActivity();
		
		assertTrue("Asserts that a user can navigate to the Science Activity tab", article.activity.getActiveTab().equals("Activity"));
		
		assertTrue("Asserts that the Print Activity button appears on the Science Activity tab and opens the correct activity",
				article.verifyLinkByElement(article.activity.printButton)
				&& article.verifyLinkContainsURLByElement(article.activity.printButton, "/pdf/pgnscience_greenhousegasesandglobalwarming_document_4.pdf"));
		article.activity.closeModal();
		student = article.clickHomeBreadcrumb();
	}
	
	@Test
	public void testIndiansCriticalThinkingQuestions() throws InterruptedException{
		PGNIndiansDatabaseHome states = student.goToIndians();
		states.waitImplicitly(1);
		PGNArticlePage article = states.clickCategory("Arctic Tribal Nations").clickArticle("Inuit");
		article.waitImplicitly(2);
		
		article.openActivity();
		
		assertTrue("Asserts that a user can navigate to the Activity tab on an American Indians article", 
				article.activity.getActiveTab().equals("Critical Thinking"));

		assertTrue("Asserts that the Print Activity button appears on the Activity modal for an American Indians artile and opens the correct activity",
				article.verifyLinkByElement(article.activity.printButton)
				&& article.verifyLinkContainsURLByElement(article.activity.printButton, "/pdf/pgnamericanindianhistory_inuit_document_3.pdf"));
		article.activity.closeModal();
		student = article.clickHomeBreadcrumb();
	}
	
	@Test
	@Category(PGNSmokeTest.class)
	public void testCitationsAPA() throws InterruptedException{
		PGNStatesDatabaseHome states = student.goToStates();
		PGNArticlePage article = states.clickCategory("Midwest").clickArticle("Minnesota");
		article.openCitations().waitImplicitly(2);
		
		assertTrue("Asserts that the APA button directs to a new page with an APA Citing",
				article.verifyLinkByElement(article.citations.printAPAButton)
				&& article.verifyLinkContainsURLByElement(article.citations.printAPAButton, "pebblegonext.com/cite/6023/apa"));
		article.citations.closeModal();
		student = article.clickHomeBreadcrumb();
	}
	
	@Test
	@Category(PGNSmokeTest.class)
	public void testCitationsMLA() throws InterruptedException{	
		PGNStatesDatabaseHome states = student.goToStates();
		PGNArticlePage article = states.clickCategory("Midwest").clickArticle("Minnesota");
		
		article.openCitations().waitImplicitly(2);
	
		assertTrue("Asserts that the MLA button directs to a new page with an MLA Citing",
				article.verifyLinkByElement(article.citations.printMLAButton)
				&& article.verifyLinkContainsURLByElement(article.citations.printMLAButton, "pebblegonext.com/cite/6023/mla"));
		
		article.citations.closeModal();
		student = article.clickHomeBreadcrumb();
	}
	
	@Test
	@Category(PGNSmokeTest.class)
	public void testCitationsCMS() throws InterruptedException{
		PGNStatesDatabaseHome states = student.goToStates();
		PGNArticlePage article = states.clickCategory("Midwest").clickArticle("Minnesota");

		article.openCitations().waitImplicitly(2);
		
		assertTrue("Asserts that the CMS button directs to a new page with an CMS Citing",
				article.verifyLinkByElement(article.citations.printCMSButton)
				&& article.verifyLinkContainsURLByElement(article.citations.printCMSButton, "pebblegonext.com/cite/6023/cms"));
		
		article.citations.closeModal();
		student = article.clickHomeBreadcrumb();	
	}
	
    
    @Test
    @Category(PGNSmokeTest.class)
    public void testBreadCrumbFirstLevel(){
    	PGNScienceDatabaseHome science = student.goToScience();
    	science.header.searchFor("Blizzards");
    	science.sendDeleteKey(science.header.searchBar);
    	science.waitImplicitly(1);
    	
       	PGNArticlePage article = science.header.clickResult("Blizzards");
    	assertTrue("Asserts that a user can navigate to the Blizzards Article", article.getTitle().equals("Blizzards"));
    	
    	PGNDatabasePage database = article.clickBreadcrumbByText("Science");

    	assertTrue("Asserts that the Science database breadcrumb redirects the user to the Science Database", 
    			database.getTitle().equals("Science"));
    }
    
    @Test
    @Category(PGNSmokeTest.class)
    public void testBreadCrumbSecondLevel(){
    	PGNScienceDatabaseHome science = student.goToScience();
    	science.header.searchFor("Blizzards");
    	science.sendDeleteKey(science.header.searchBar);
    	science.waitImplicitly(1);
    	
       	PGNArticlePage article = science.header.clickResult("Blizzards");
       	
    	assertTrue("Asserts that a user can navigate to the Blizzards Article", article.getTitle().equals("Blizzards"));
    	
    	PGNDatabasePage database = article.clickBreadcrumbByText("Earth Science");

    	assertTrue("Asserts that the Earth Science breadcrumb navigates the user back to the Earth Science database page", 
    			database.getTitle().equals("Earth Science"));
    }
    
    @Test
    @Category(PGNSmokeTest.class)
    public void testBreadCrumbThirdLevel(){
    	PGNScienceDatabaseHome science = student.goToScience();
    	science.header.searchFor("Blizzards");
    	science.sendDeleteKey(science.header.searchBar);
    	science.waitImplicitly(1);
    	
       	PGNArticlePage article = science.header.clickResult("Blizzards");
       	
    	assertTrue("Asserts that a user can navigate to the Blizzards Article", article.getTitle().equals("Blizzards"));
    	
    	PGNDatabasePage database = article.clickBreadcrumbByText("Atmosphere");

    	assertTrue("Asserts that the Atmosphere breadcrumb navigates the user back to the Atmosphere database page", database.getTitle().equals("Atmosphere"));
    }
    
    @Test
    @Category(PGNSmokeTest.class)
    public void testBreadCrumbFourthLevel(){
    	PGNScienceDatabaseHome science = student.goToScience();
    	science.header.searchFor("Blizzards");
    	science.sendDeleteKey(science.header.searchBar);
    	science.waitImplicitly(1);
    	
       	PGNArticlePage article = science.header.clickResult("Blizzards");
       	
    	assertTrue("Asserts that a user can navigate to the Blizzards Article", article.getTitle().equals("Blizzards"));
    	
    	PGNDatabasePage database = article.clickBreadcrumbByText("Weather and Climate");

    	assertTrue("Asserts that the Weather and Climate breadcrumb navigates the user back to the Weather and Climate database page", database.getTitle().equals("Weather and Climate"));
    }
    @Rule
    public TestRule watcher = new TestWatcher() {
       protected void starting(Description description) {
          System.out.println("Starting test: " + description.getMethodName());
          
       }
    };
    
}
