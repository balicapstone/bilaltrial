package Tests;

import static org.junit.Assert.assertTrue;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.google.gson.JsonArray;
import Games.PGNChooseGamePage;
import Games.PGNJigsawPage;
import Modals.ActivityModal;
import Modals.CitationsModal;
import Modals.DictionaryModal;
import Modals.ImageModal;
import Modals.QuestionOfTheDayModal;
import Modals.RelatedArticlesModal;
import Modals.TerminologyModal;
import Modals.VideoModal;
import PGNPages.LoginPage;
import PGNStudentResources.ArticlePage;
import PGNStudentResources.ArticleType;
import PGNStudentResources.DatabasePage;
import PGNStudentResources.ExperimentsPage;
import PGNStudentResources.IndiansDatabaseHome;
import PGNStudentResources.ScienceDatabaseHome;
import PGNStudentResources.StatesDatabaseHome;
import PGNStudentResources.StudentHomePage;
import SharedClasses.CurlCallController;
import SharedClasses.ThirdPartyPage;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;
import UserClasses.UserInfo;

public class PGNLoggingTests {
	static LoginPage loginPage;
	static User user;

	/*
	 * 	x	1  => "login", 		
		x	2  => "module",	 - module clicked from modules screen
							 - module page loaded
		x	3  => "category",- Category clicked on
							 - Category page loaded
		x	4  => "article", - Article clicked from related articles
							 - Article clicked from Category
							 - Article clicked from hamburger menu
							 - Article clicked from search
							 - Article screen loaded
		x	5  => "poll", - polls button clicked
		x	6  => "game", - games button clicked
			7  => "glossary",
			9  => "print", - Activity print button?
		x	12 => "search", 
		x	13 => "Teacher Resources" - click community button within pgo
		x	14 => "breadcrumbs", - breadcrumbs clicked
			15 => "hamburger", - hamburger launch clicked
		x	17 => "video experiments page", - experiments page clicked
		x	18 => "video experiments modal", - experimens modal opened
		x	19 => "image modal button",  - image modal opened
		x	20 => "image modal image", - clicked to expand
		x	21 => "related article", - related article button clicked
		x	22 => "citation", - citation button clicked
		x	23 => "activity", - activity button clicked
		x	24 => "dictionary", - dictionary button clicked
		x	25 => "terminology", - terminology button clicked
		x	26 => "article nav", - click tab on article
		x	27 => "logo", - click logo
	 * 
	 */    
	
	static JsonArray loggingArray;
	static Timestamp currentTime; 
	static StudentHomePage student;
	static CurlCallController curl;
	static String sessionID;
	static String loggingCallURL;
	static String studentWindowHandle;
	static int waitTime = 2;
	
	@BeforeClass
	public static void executeBefore(){
	   	user = new User(UserInfo.GARAGESTUDENT);
	   	
	   	loginPage = new LoginPage(user);
	   	user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.loginModalButton));
	   	
		//This to to correct for the server times for the logging events
		//currentTime = loginPage.getCurrentTimestamp();
		//currentTime.setTime(currentTime.getTime() + (5 * 3600 * 1000));
		
		//Logging 1 - Login
		student = (StudentHomePage) loginPage.login(user);

		if(user.getEnvironment().equals("qa")){
			loggingCallURL = "https://7u8rbsd92a.execute-api.us-east-2.amazonaws.com/qa";//"search-es-datalake-logs-45fqefehpz5wapdvdwhtjlrmwy.us-east-2";
		}else if(user.getEnvironment().equals("staging")){
			loggingCallURL = "logs-staging.pebblego.com/";
		}else if(user.getEnvironment().equals("www")){
			loggingCallURL = "logs.pebblego.com";
		}else if(user.getEnvironment().equals("editorial")){
			loggingCallURL = "https://7u8rbsd92a.execute-api.us-east-2.amazonaws.com/qa";
		}
		else{
			loggingCallURL = "logs.pebblego.com";
		}
		
		sessionID = student.getLoggingSessionID(loggingCallURL);
		curl = new CurlCallController(user, sessionID);
		studentWindowHandle = user.getDriver().getWindowHandle();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.footer.capstoneCopyright));
	}
	    
	@AfterClass
	public static void executeAfter(){
	   	loginPage.closeCurrentWindow();
	   	user.quit();
	}
		    
	@Rule
	public Retry retry = new Retry(3);
		
	@Rule
	public TestWatcher restart = new TestWatcher(){
	  	@Override
	  	public void failed(Throwable e, Description description){
	  		student.closeAllOtherTabsBut(studentWindowHandle);
	  		user.customWait().until(ExpectedConditions.elementToBeClickable(student.header.pGNLogo));
			student.getDriver().get("https://"+user.getEnvironment()+ ".pebblegonext.com"); //.header.clickLogo();
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.footer.capstoneCopyright));
	   	}
	};
	
	@Test
	public void testNavigateToArticle(){
		//Logging 2 - Module clicked from modules screen
		IndiansDatabaseHome indians = student.goToIndians();
		user.customWait().until(ExpectedConditions.elementToBeClickable(indians.gamesLink));
		indians.waitImplicitly(waitTime);
		
		//Logging 3 - Category clicked
		DatabasePage category = indians.clickCategory("Northeast Tribal Nations");
		user.customWait().until(ExpectedConditions.elementToBeClickable(indians.gamesLink));
		category.waitImplicitly(waitTime);
		
		//Logging 4 - Article clicked from category
		ArticlePage article = category.clickArticle("Ojibwa");
		user.customWait().until(ExpectedConditions.elementToBeClickable(article.terminologyLink));
		article.waitImplicitly(waitTime);
		
		student = article.clickHomeBreadcrumb();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.footer.capstoneCopyright));
		student.waitImplicitly(waitTime);
	}
	
	@Test
	public void testQuestionOfTheDay(){
		//Logging 5 - Click question of the day Modal
		IndiansDatabaseHome indians = student.goToIndians();
		user.customWait().until(ExpectedConditions.elementToBeClickable(indians.questionOfTheDayLink));
		indians.waitImplicitly(waitTime);
		
		QuestionOfTheDayModal question = indians.openQuestionOfTheDay();
		user.customWait().until(ExpectedConditions.elementToBeClickable(question.VoteButton));
		question.waitImplicitly(waitTime);
		question.clickAnswerByINT(1);
		user.customWait().until(ExpectedConditions.elementToBeClickable(question.VoteButton));
		question.waitImplicitly(waitTime);
		question.vote();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(question.VoteButton));
		question.waitImplicitly(waitTime);
		question.closeModal();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(question.closeButton));
		student.waitImplicitly(waitTime);
		
		student = indians.clickHomeBreadcrumb();
		student.waitImplicitly(waitTime);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.footer.capstoneCopyright));
		student.waitImplicitly(waitTime);
	}
	
	@Test
	public void testNavigateToGames(){
		StatesDatabaseHome states = student.goToStates();
		user.customWait().until(ExpectedConditions.elementToBeClickable(states.gamesLink));
		states.waitImplicitly(waitTime);
		
		//Logging 6 - Click Games Button
		PGNChooseGamePage games = states.openGames();
		user.customWait().until(ExpectedConditions.elementToBeClickable(games.communityButton));
		games.waitImplicitly(waitTime);
		
		PGNJigsawPage jigsaw = games.clickJigsaw();
		user.customWait().until(ExpectedConditions.elementToBeClickable(jigsaw.startButton));
		
		//I hate doing this but games are garbage and doing this makes it work
		jigsaw.waitImplicitly(3);
		
		jigsaw.click(jigsaw.startButton);
		jigsaw.waitImplicitly(waitTime);
		
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(jigsaw.startButton));
		
		student = (StudentHomePage) jigsaw.header.clickLogo();
		student.waitImplicitly(waitTime);
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.goToSocialStudies));
		student.waitImplicitly(waitTime);
	}
	
	@Test
	public void testNavigateToArticleThroughSearchAndClickBreadcrumb(){
		//Logging 12
		student.header.searchFor("Inuit");
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.header.getXpathForResult("Inuit")));
		student.waitImplicitly(waitTime);
		
		ArticlePage article = student.header.clickResult("Inuit");
		user.customWait().until(ExpectedConditions.elementToBeClickable(article.terminologyLink));
		article.waitImplicitly(waitTime);
		
		//Logging 14 - Click Breadcrumb
		DatabasePage category = article.clickBreadcrumbByText("Arctic Tribal Nations");
		user.customWait().until(ExpectedConditions.elementToBeClickable(category.questionOfTheDayLink));
		category.waitImplicitly(waitTime);
		
		category.clickBreadcrumbByText("American Indian History");
		user.customWait().until(ExpectedConditions.elementToBeClickable(category.homeBreadcrumb));
		category.waitImplicitly(waitTime);
		
		student = category.clickHomeBreadcrumb();
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.goToSocialStudies));
		student.waitImplicitly(waitTime);
	}
	
	@Test
	public void testNavigateToArticleThroughHamburger(){
		//Logging 26 - Hamburger
		student.header.openHamburgerMenu();
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.header.hamburgerMenu.ScienceDiv));
		student.waitImplicitly(waitTime);
    	
		student.header.hamburgerMenu.openScienceDiv();
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.header.hamburgerMenu.getListSubItemXpath("Life Science")));
    	student.waitImplicitly(waitTime);
		
		student.header.hamburgerMenu.clickListSubItem("Life Science");
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.header.hamburgerMenu.getListSubItemXpath("Human Anatomy")));
    	student.waitImplicitly(waitTime);
		
		student.header.hamburgerMenu.clickListSubItem("Human Anatomy");
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.header.hamburgerMenu.getListSubItemXpath("Organs")));
    	student.waitImplicitly(waitTime);
		
		ArticlePage article  = student.header.hamburgerMenu.clickSubItemIntoArticle("Organs");
		user.customWait().until(ExpectedConditions.elementToBeClickable(article.header.homeButton));
    	article.waitImplicitly(waitTime);
		
    	student = article.clickHomeBreadcrumb();
    	user.customWait().until(ExpectedConditions.elementToBeClickable(student.goToSocialStudies));
    	student.waitImplicitly(waitTime);
	}
	
	@Test
	public void testPGNArticleElementLogging(){
		//Logging 12
		student.header.searchFor("Inuit");
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.header.getXpathForResult("Inuit")));
		student.waitImplicitly(waitTime);
		
		ArticlePage article = student.header.clickResult("Inuit");
		user.customWait().until(ExpectedConditions.elementToBeClickable(article.terminologyLink));
		article.waitImplicitly(waitTime);
		
		//Logging 25
		TerminologyModal terminology = article.openTerminologyModal();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(terminology.closeButton));
		terminology.waitImplicitly(waitTime);
		terminology.closeModal();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(terminology.closeButton));
		article.waitImplicitly(waitTime);
				
		//Logging 22
		CitationsModal citations = article.openCitations();
		user.customWait().until(ExpectedConditions.elementToBeClickable(citations.printAPAButton));
		citations.waitImplicitly(waitTime);
		
		citations.click(citations.printAPAButton);
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(2)); 
		citations.waitImplicitly(waitTime);
		
		if(user.isHeadless()){
			citations.closeAllOtherTabsBut(studentWindowHandle);
		}
		else{
			citations.closePrintableContentTab("cite");
		}
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(1)); 
		citations.waitImplicitly(waitTime);
		
		citations.click(citations.printCMSButton);
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(2)); 
		citations.waitImplicitly(waitTime);
		
		if(user.isHeadless()){
			citations.closeAllOtherTabsBut(studentWindowHandle);
		}
		else{
			citations.closePrintableContentTab("cite");
		}
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(1)); 
		citations.waitImplicitly(waitTime);

		citations.click(citations.printMLAButton);
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(2)); 
		citations.waitImplicitly(waitTime);
		
		if(user.isHeadless()){
			citations.closeAllOtherTabsBut(studentWindowHandle);
		}
		else{
			citations.closePrintableContentTab("cite");
		}
		
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(1));
		citations.waitImplicitly(waitTime);
		
		citations.closeModal();
		user.customWait().until(ExpectedConditions.elementToBeClickable(article.activityButton));
		article.waitImplicitly(waitTime);
		
		//Logging 23
		ActivityModal activity = article.openActivity();
		user.customWait().until(ExpectedConditions.elementToBeClickable(activity.printButton));
		activity.waitImplicitly(waitTime);
		
		activity.click(activity.printButton);
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(2));
		activity.waitImplicitly(waitTime);
		
		if(user.isHeadless()){
			citations.closeAllOtherTabsBut(studentWindowHandle);
		}
		else{
			citations.closePrintableContentTab("pdf");
		}
		
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(1)); 
		activity.closeModal();
		article.waitImplicitly(waitTime);
				
		//Logging 24
		user.customWait().until(ExpectedConditions.elementToBeClickable(article.dictionaryButton));
		DictionaryModal dictionary = article.openDictionary();
		user.customWait().until(ExpectedConditions.elementToBeClickable(dictionary.search));
		dictionary.waitImplicitly(waitTime);
		
		dictionary.search("test");
		user.customWait().until(ExpectedConditions.elementToBeClickable(dictionary.closeButton));
		dictionary.waitImplicitly(waitTime);
		
		dictionary.closeModal();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(dictionary.closeButton));
		article.waitImplicitly(waitTime);		
		
		//Logging 21
		RelatedArticlesModal related = article.openRelatedArticles();
		user.customWait().until(ExpectedConditions.elementToBeClickable(related.closeButton));
		related.waitImplicitly(waitTime);
		
		article = related.clickRelatedArticleByName("Alaska", ArticleType.STATES);
		user.customWait().until(ExpectedConditions.urlContains("articles/6002"));	
		article.waitImplicitly(waitTime);
		
		//Logging 20
		user.customWait().until(ExpectedConditions.elementToBeClickable(article.dictionaryButton));
		ImageModal image = article.openImageModal(1, 1);
		user.customWait().until(ExpectedConditions.elementToBeClickable(image.closeButton));
		image.waitImplicitly(waitTime);
		
		image.closeModal();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(image.closeButton));
		article.waitImplicitly(waitTime);		
		
		//Logging 19
		image = article.clickImageButton(1, 2);
		user.customWait().until(ExpectedConditions.elementToBeClickable(image.closeButton));
		image.waitImplicitly(waitTime);
		
		image.closeModal();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(image.closeButton));
		article.waitImplicitly(waitTime);
		
		//Logging 26 - Article Nav
		article.clickNavigationTab("People");
		article.waitImplicitly(waitTime);
		article.clickNavigationTab("History and Government");
		article.waitImplicitly(waitTime);
		
		//Logging 27
		student = article.header.clickLogo();
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.goToSocialStudies));
		student.waitImplicitly(waitTime);
	}
	
	@Test
	public void testPGNCommunityResources(){
		StatesDatabaseHome states = student.goToStates();
		user.customWait().until(ExpectedConditions.elementToBeClickable(states.gamesLink));
		states.waitImplicitly(waitTime);
		
		ThirdPartyPage tPP = states.clickCommunityLink();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(states.gamesLink));
		tPP.waitImplicitly(waitTime);
		tPP.goBack();
		
		student = states.header.clickLogo();
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.goToSocialStudies));
		student.waitImplicitly(waitTime);
	}
		
	@Test
	public void testPGNExperimentsPageAndVideo(){
		//Logging 17
		ScienceDatabaseHome science = student.goToScience();
		user.customWait().until(ExpectedConditions.elementToBeClickable(science.experimentsLink));
		science.waitImplicitly(waitTime);
		
		ExperimentsPage experiments = science.goToExperimentsPage();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(science.gamesLink));
		experiments.waitImplicitly(waitTime);
		
		//Logging 18
		VideoModal modal = experiments.clickVideoByTitle("Monster Foam");
		user.customWait().until(ExpectedConditions.elementToBeClickable(modal.downloadTranscriptButton));
		modal.waitImplicitly(waitTime);
		modal.closeModal();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(modal.closeButton));
		science.waitImplicitly(waitTime);
	}
	
	@Test
	public void testWAITFORDATA(){
		student.waitImplicitly(60);
	}
	
	@Test
	public void testModuleViewLogExists(){
		assertTrue("Asserts that a module view event is found", curl.doesCategoriesViewLogExist("American Indian History", "PebbleGo Next American Indian History"));	}
	
	@Test
	public void testModuleClickExists(){
		assertTrue("Asserts that a module click event is found", curl.doesModuleClickLogExist("PebbleGo Next American Indian History"));
	}
	
	@Test
	public void testCategoryClickedLogExists(){
		assertTrue("Asserts that the category is clicked from a module", curl.doesCategoriesClickLogExist("Northeast Tribal Nations", "PebbleGo Next American Indian History"));
	}
	
	@Test
	public void testCategoryViewLogExists(){
		assertTrue("Asserts that the category is viewed", curl.doesCategoriesClickLogExist("Northeast Tribal Nations", "PebbleGo Next American Indian History"));
	}
	
	@Test
	public void testArticleClickedLogExists(){
		assertTrue("Asserts that the article is clicked", curl.doesArticleClickLogExist("Ojibwa", "PebbleGo Next American Indian History"));
	}
	
	@Test
	public void testArticleViewLogExists(){
		assertTrue("Asserts that the article viewed", curl.doesArticleViewLogExist("Ojibwa", "PebbleGo Next American Indian History"));
	}
	
	@Test
	public void testHomeBreadCrumbLogExists(){
		assertTrue("Asserts that a home breadcrumb event is found", curl.doesBreadcrumbsLogExist("home"));
	}
	
	@Test
	public void testThatPollClickLogExists(){
		assertTrue("Asserts that a Poll Click log exists", curl.doesPollClickLogExist("PebbleGo Next American Indian History"));
	}
	
	@Test
	public void testGamesClickLogExists(){
		assertTrue("Asserts that a Game Click log exists", curl.doesGameClickLogExist("Jigsaw", "PebbleGo Next States"));
	}
	
	@Test
	public void testSearchIntoArticleClickLogExists(){
		assertTrue("Asserts that an article click is registered from the search bar", curl.doesArticleClickFromSearchLogExist("6054"));
	}
	
	@Test
	public void testSearchIntoArticleViewLogExists(){
		assertTrue("Asserts that an article View is registered from search", curl.doesArticleViewLogExist("Inuit", "PebbleGo Next American Indian History"));
	}
	
	@Test
	public void testCategoryBreadCrumbLogExists(){
		assertTrue("Asserts that a home breadcrumb event is found", curl.doesBreadcrumbsLogExist("category"));
	}
	
	@Test
	public void testModuleBreadCrumbLogExists(){
		assertTrue("Asserts that a home breadcrumb event is found", curl.doesBreadcrumbsLogExist("module"));
	}
	
	@Test
	public void testHamburgerButtonLogExists(){
		assertTrue("Asserts that the hamburger button is clicked", curl.doesHamburgerButtonClickLogExist());
	}
	
	@Test
	public void testArticleClickedFromHamburgerButton(){
		assertTrue("Asserts that an Article Clicked From Hamburger log exists", curl.doesArticleClickFromPGNHamburgerLogExist("Organs"));
	}
	
	@Test
	public void testActivityButtonClickLogExists(){
		assertTrue("Asserts that an Activity Button Click log exists", curl.doesActivityClickLogExist("Inuit"));
	}
	
	@Test
	public void testActivityPrintLogExists(){
		assertTrue("Asserts that an Activity Print log exists", curl.doesPrintClickLogExist("activity"));
	}
	
	@Test
	public void testTerminologyClickLogExists(){
		assertTrue("Asserts that a Terminology Click log exists", curl.doesTerminologyClickLogExist("Inuit"));
	}
	
	@Test
	public void testAPACiteButtonPrintLogExists(){
		assertTrue("Asserts that a Print Cite button click log exists", curl.doesPrintClickLogExist("apa"));
	}
	
	@Test
	public void testMLACiteButtonPrintLogExists(){
		assertTrue("Asserts that a Print Cite button click log exists", curl.doesPrintClickLogExist("mla"));
	}
	
	@Test
	public void testCMSCiteButtonPrintLogExists(){
		assertTrue("Asserts that a Print Cite button click log exists", curl.doesPrintClickLogExist("cms"));
	}
	
	@Test
	public void testDictionaryButtonClickLogExists(){
		assertTrue("Asserts that a Dictionary Button Click log exists", curl.doesDictionaryClickLogExist("Inuit"));
	}
	
	@Test
	public void testRelatedArticleButtonLogExists(){
		assertTrue("Asserts that a Related Article Tab Click log exists", curl.doesRelatedArticleClickLogExist("Inuit"));
	}
	
	@Test
	public void testArticleClickedFromRelatedArticlesTabLogExists(){
		assertTrue("Asserts that an Article Click from a Related Article Tab click log exists", curl.doesArticleClickedFromRelatedArticleLogExist("Alaska", "Inuit"));
	}
	
	@Test
	public void testArticleNavClickLogsExist(){
		assertTrue("Asserts that this Article Nav Click log exists", curl.doesNavigationTabLogExist("People"));
		assertTrue("Asserts that this Article Nav Click log exists", curl.doesNavigationTabLogExist("History and Government"));
	}
	
	@Test
	public void testImageButtonClickLogExists(){
		assertTrue("Asserts that the correct Image Click log exists", curl.doesImageClickLogExist("Alaska", "screenImgpgnstates_alaska_scr_0_img_0.jpg"));
	}
	
	@Test
	public void testImageModalButtonClickLogExists(){
		assertTrue("Asserts that the correct Image Modal Button log exists", curl.doesImageModalButtonClickLogExist("Alaska", "screenImgpgnstates_alaska_scr_0_img_1.jpg"));
	}
	
	@Test
	public void testTeacherResourcesLinkClickLogExists(){
		assertTrue("Asserts that a Community Teacher Click log exists", curl.doesTeacherResourcesClickLogExist("Pebble Go Next"));
	}
	
	@Test
	public void testLogoClickLogExists(){
		assertTrue("Asserts that a Logo Click log exists", curl.doesLogoClickLogExist("Pebble Go Next"));
	}
	
	@Test
	public void testVideoButtonClickLogExists(){
		assertTrue("Asserts that a Video Click log exists", curl.doesPGNVideoClickLogExist("Monster Foam"));
	}
	
	@Test
	public void testVideoPageButtonClickLogExists(){
		assertTrue("Asserts that a Video Science Page Click log exists", curl.doesPGNVideoPageLogExist("PebbleGo Next Science"));
	}
	
	
	public Timestamp convertToTimeStamp(String time) throws java.text.ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date parsedDate = dateFormat.parse(time);
		Timestamp timestamp = new Timestamp(parsedDate.getTime());
		return timestamp;
	}
}
