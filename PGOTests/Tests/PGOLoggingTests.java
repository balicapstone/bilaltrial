package Tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import ArticleModals.GlossaryModal;
import ArticleModals.PGOQuestionOfTheDayModal;
import ArticleModals.PrintModal;
import ArticleModals.VideoModal;
import Games.PGOChooseGamePage;
import Games.PGOJigsawPage;
import ArticleModals.ActivityModal;
import ArticleModals.CitationModal;
import PGOPages.LoginPage;
import PGOStudentResources.AnimalsContentPage;
import PGOStudentResources.ArticlePage;
import PGOStudentResources.ScienceContentPage;
import PGOStudentResources.SocialStudiesContentPage;
import PGOStudentResources.StudentHomePage;
import SharedClasses.CurlCallController;
import SharedClasses.ThirdPartyPage;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;
import UserClasses.UserInfo;

public class PGOLoggingTests {
	
	/*
	 * 	x	1  => "login", 		
		x	2  => "module", - module clicked from hamburger menu
							- module clicked from modules screen
							- modules page loaded
		x	3  => "category"- Category Clicked On
							- Category page loaded
		x	4  => "article",- Article clicked from related articles
							- Article clicked from category
							- Article clicked from search
							- Article screen loaded
		x	5  => "poll",   - button clicked
		x	6  => "game",   - button licked
		x	7  => "glossary"- glossary term clicked
		x	9  => "print",  - clicked
		x	12 => "search"	- clicked/entered characters?
		x	13 => "Teacher Resources" - click community button within pgo
		x	14 => "breadcrumbs" - clicked
		x	15 => "hamburger", - button clicked
			17 => "video experiments page",
		x	18 => "video experiments modal", - click on video modal button
			19 => "image modal button",
			20 => "image modal image",
		x	21 => "related article", -click on tab
		x	22 => "citation", - click on button
		x	23 => "activity", - click on activity button
			24 => "dictionary",
			25 => "terminology",
		x	26 => "article nav", - click on tab in article
		x	27 => "logo",   - click on logo
	 * 
	 */
	
	static LoginPage loginPage;
	static User user;
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
		currentTime = loginPage.getCurrentTimestamp();
		currentTime.setTime(currentTime.getTime() + (5 * 3600 * 1000));
		
		//Logging 1
		student = (StudentHomePage) loginPage.Login(user);

		if(user.getEnvironment().equals("qa")){
			loggingCallURL = "https://7u8rbsd92a.execute-api.us-east-2.amazonaws.com/qa";//"search-es-datalake-logs-45fqefehpz5wapdvdwhtjlrmwy.us-east-2";
		}else if(user.getEnvironment().equals("staging")){
			loggingCallURL = "logs-staging.pebblego.com";
		}else if(user.getEnvironment().equals("www")){
			loggingCallURL = "logs.pebblego.com";
		}else if(user.getEnvironment().equals("editorial")){
			loggingCallURL = "https://7u8rbsd92a.execute-api.us-east-2.amazonaws.com/qa";
		}else{
			loggingCallURL = "logs.pebblego.com";
		}
		
		sessionID = student.getLoggingSessionID(loggingCallURL);
		curl = new CurlCallController(user, sessionID);
		studentWindowHandle = user.getDriver().getWindowHandle();
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
	  		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(1));
	  		student.getDriver().get("https://"+user.getEnvironment()+ ".pebblego.com");
	  		//student.clickPebbleGoLogo();
			user.customWait().until(ExpectedConditions.elementToBeClickable(student.dinosaursModule));
	   	}
	};
	
	@Test
	public void testModuleClickedFromModulesScreen() throws ParseException, IOException{
		//Logging 2 - Module Clicked from modules Screen and Module Page loaded
		SocialStudiesContentPage socialStudies = student.clickSocialStudiesModule();
		user.customWait().until(ExpectedConditions.elementToBeClickable(socialStudies.questionOfTheDayLink));
		socialStudies.waitImplicitly(waitTime);
		
		student = socialStudies.getContentHeader().clickHomeBreadcrumb();	
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.dinosaursModule));
		student.waitImplicitly(waitTime);
	}

	@Test
	public void testModuleClickedFromHamburgerButton(){
		//Logging 15 - Click Hamburger Button
		student.clickHamburgerButton();
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.topModuleMenu.dinosaursButton));
		student.waitImplicitly(waitTime);
		
		//Logging 2 - Module Clicked from modules screen and Module Page Loaded
		AnimalsContentPage animals = student.topModuleMenu.clickAnimalsButton();
		user.customWait().until(ExpectedConditions.elementToBeClickable(animals.questionOfTheDayLink));
		animals.waitImplicitly(waitTime);
		
		student = animals.getContentHeader().clickHomeBreadcrumb();
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.dinosaursModule));
		student.waitImplicitly(waitTime);
	}
	
	@Test
	public void testArticleAndCategoryClickedAndLoaded(){
		AnimalsContentPage animals = student.clickAnimalsModule();
		user.customWait().until(ExpectedConditions.elementToBeClickable(animals.questionOfTheDayLink));
		animals.waitImplicitly(waitTime);
		
		animals.clickCategoryByText("Amphibians");
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.linkText("Frogs")));
		animals.waitImplicitly(waitTime);
		
		//Logging 4 - Article Clicked and Article Loaded
		ArticlePage article = animals.clickArticleByText("Frogs");
		user.customWait().until(ExpectedConditions.elementToBeClickable(article.tabFive));
		article.waitImplicitly(waitTime);
		
		student = article.header.clickHomeBreadcrumb();
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.dinosaursModule));
		student.waitImplicitly(waitTime);
	}	
	
	@Test
	public void testArticleActivitesAndExtrasFunctions(){
		AnimalsContentPage animals = student.clickAnimalsModule();
		user.customWait().until(ExpectedConditions.elementToBeClickable(animals.questionOfTheDayLink));
		animals.waitImplicitly(waitTime);
		
		//Logging 12
		ArticlePage article = animals.searchForArticle("Grizzly Bears");
		article.waitImplicitly(waitTime);
		
		//Logging 23 - Activity Button clicked
		ActivityModal activity = article.clickActivityButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(activity.printActivityButton));
		activity.waitImplicitly(waitTime);
		
		activity.click(activity.printActivityButton);
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(2)); 
		activity.waitImplicitly(waitTime);
		
		if(user.isHeadless()){
			activity.closeAllOtherTabsBut(studentWindowHandle);
		}
		else{
			activity.closePrintableContentTab("content");
		}
				
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(1));
		activity.waitImplicitly(waitTime);
		
		activity.click(activity.printShareWhatYouKnowButton);
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(2));
		activity.waitImplicitly(waitTime);
				
		if(user.isHeadless()){
			activity.closeAllOtherTabsBut(studentWindowHandle);
		}
		else{
			activity.closePrintableContentTab("content");
		}
				
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(1));
		activity.waitImplicitly(waitTime);
		
		activity.closeModal();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(activity.closeButton));
		article.waitImplicitly(waitTime);
						
		//Logging 22 - Citation Button clicked
		CitationModal citation = article.clickCiteButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(citation.printButton));
		citation.waitImplicitly(waitTime);
		
		citation.printCitation();
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(2));
		citation.waitImplicitly(waitTime);		
		
		if(user.isHeadless()){
			activity.closeAllOtherTabsBut(studentWindowHandle);
		}
		else{
			activity.closePrintableContentTab("cite");
		}
				
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(1));
		citation.waitImplicitly(waitTime);
		citation.close();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(citation.closeButton));
		article.waitImplicitly(waitTime);
				
		//Logging 9 - Print button clicked
		PrintModal print = article.clickPrintButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(print.closeButton));
		print.waitImplicitly(waitTime);
		
		print.click(print.printArticleButton);
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(2));
		print.waitImplicitly(waitTime);
		
		if(user.isHeadless()){
			activity.closeAllOtherTabsBut(studentWindowHandle);
		}
		else{
			activity.closePrintableContentTab("activity");
		}
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(1));
		print.waitImplicitly(waitTime);		
		
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(print.closeButton));
		print.click(print.printImageButton);
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(2));
		print.waitImplicitly(waitTime);
		
		if(user.isHeadless()){
			activity.closeAllOtherTabsBut(studentWindowHandle);
		}
		else{
			activity.closePrintableContentTab("activity");
		}
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(1));
		print.waitImplicitly(waitTime);
				
		print.closeModal();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(print.closeButton));
		article.waitImplicitly(waitTime);
				
		//Logging 18 - Videos Modal
		VideoModal video = article.clickVideoButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(video.closeButton));
		video.waitImplicitly(waitTime);
				
		video.PlayVideo();
		video.waitImplicitly(waitTime);
		video.clickCloseButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(video.closeButton));
		video.waitImplicitly(waitTime);
		
		student = article.clickLogo();
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.dinosaursModule));
		student.waitImplicitly(waitTime);
	}
	
	@Test
	public void testArticleClickedAllArticlesFunctions(){
		AnimalsContentPage animals = student.clickAnimalsModule();
		user.customWait().until(ExpectedConditions.elementToBeClickable(animals.questionOfTheDayLink));
		animals.waitImplicitly(waitTime);
		
		//Logging 12
		ArticlePage article = animals.searchForArticle("Grizzly Bears");
		user.customWait().until(ExpectedConditions.elementToBeClickable(article.tabFive));
		article.waitImplicitly(waitTime);
		
		//Logging 7 - Glossary button clicked
		GlossaryModal glossary = article.clickGlossaryTerm("mammals");
		user.customWait().until(ExpectedConditions.elementToBeClickable(glossary.playButton));
		glossary.waitImplicitly(waitTime);
		
		glossary.pressPlay();
		user.customWait().until(ExpectedConditions.elementToBeClickable(glossary.playButton));
		glossary.waitImplicitly(waitTime);
		
		glossary.closeModal();
		user.customWait().until(ExpectedConditions.invisibilityOf(glossary.getDriver().findElement(glossary.closeButton)));
		article.waitImplicitly(waitTime);
		
		//Logging 26 - Article Nav
		article.clickTabFour(); //Life Cycle
		user.customWait().until(ExpectedConditions.elementToBeClickable(article.tabFourAudio));
		article.waitImplicitly(waitTime);
		
		article.clickTabThree(); //Food
		user.customWait().until(ExpectedConditions.elementToBeClickable(article.tabThreeAudio));
		article.waitImplicitly(waitTime);
		
		article.clickTabFive(); //Fun Facts
		user.customWait().until(ExpectedConditions.elementToBeClickable(article.tabFiveAudio));
		article.waitImplicitly(waitTime);
		
		article.clickTabOne(); //Body
		user.customWait().until(ExpectedConditions.elementToBeClickable(article.tabOneAudio));
		article.waitImplicitly(waitTime);
		
		article.clickTabTwo(); //Habitat
		user.customWait().until(ExpectedConditions.elementToBeClickable(article.tabTwoAudio));
		article.waitImplicitly(waitTime);
		
		//Logging 21 - click Related Articles Tab
		article.clickTabSix();
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.linkText("Giant Pandas")));
		article.waitImplicitly(waitTime);
		
		//Logging 2 - click Article from Related Articles Tab
		article.clickLink(By.linkText("Giant Pandas"));
		user.customWait().until(ExpectedConditions.urlContains("articles/146"));
		article.waitImplicitly(waitTime);
		
		student = article.clickLogo();
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.dinosaursModule));
		student.waitImplicitly(waitTime);
	}
	
	@Test
	public void testCommunityTeacherResourcesLink(){
		AnimalsContentPage animals = student.clickAnimalsModule();
		user.customWait().until(ExpectedConditions.elementToBeClickable(animals.questionOfTheDayLink));
		animals.waitImplicitly(waitTime);
		
		ThirdPartyPage tPP = animals.clickCommunitiesLink();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(animals.gamesLink));
		tPP.waitImplicitly(waitTime);
		tPP.goBack();
		
		student = animals.clickHomeBreadcrumb();
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.dinosaursModule));
		student.waitImplicitly(waitTime);
	}
	
	@Test
	public void testQuestionOfTheDay(){
		ScienceContentPage science = student.clickScienceModule();
		user.customWait().until(ExpectedConditions.elementToBeClickable(science.questionOfTheDayLink));
		science.waitImplicitly(waitTime);
		
		//Logging 5 - Polls Button clicked
		PGOQuestionOfTheDayModal question = science.openQuestionOfTheDay();
		user.customWait().until(ExpectedConditions.elementToBeClickable(question.voteTable));
		question.waitImplicitly(waitTime);
		
		question.clickAnswerByINT(1);
		user.customWait().until(ExpectedConditions.elementToBeClickable(question.VoteButton));
		question.waitImplicitly(waitTime);
		
		question.vote();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(question.VoteButton));
		question.waitImplicitly(waitTime);
		
		question.closeModal();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(question.closeButton));
		science.waitImplicitly(waitTime);
		
		student = science.clickHomeBreadcrumb();
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.dinosaursModule));
		student.waitImplicitly(waitTime);
	}
	
	@Test
	public void testGamesClicked(){
		AnimalsContentPage animals = student.clickAnimalsModule();
		user.customWait().until(ExpectedConditions.elementToBeClickable(animals.questionOfTheDayLink));
		animals.waitImplicitly(waitTime);
		
		//Logging 6
		PGOChooseGamePage games = animals.clickGamesButton();
		user.customWait().until(ExpectedConditions.elementToBeClickable(games.jigsaw));
		games.waitImplicitly(waitTime);

		PGOJigsawPage jigsaw = games.clickJigsaw();
		user.customWait().until(ExpectedConditions.elementToBeClickable(jigsaw.startButton));
		jigsaw.waitImplicitly(waitTime);
		
		//I hate doing this but games are garbage and doing this makes it work
		jigsaw.waitImplicitly(3);
		
		jigsaw.click(jigsaw.startButton);
		jigsaw.waitImplicitly(waitTime);
		
		student = jigsaw.clickHomeBradcrumb();
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.dinosaursModule));
		student.waitImplicitly(waitTime);
	}
	
	@Test
	public void testWAITFORDATA(){
		student.waitImplicitly(60);
	}
	
	@Test
	public void testModuleViewLogExists(){
		assertTrue("Asserts that a module view event is found", curl.doesCategoriesViewLogExist("Social Studies", "PebbleGo Social Studies"));
	}
	
	@Test
	public void testModuleClickLogExists(){
		assertTrue("Asserts that a module click event is found", curl.doesModuleClickLogExist("PebbleGo Social Studies"));
	}
	
	@Test
	public void testHomeBreadCrumbLogExists(){
		assertTrue("Asserts that a home breadcrumb event is found", curl.doesBreadcrumbsLogExist("home"));
	}
	
	@Test
	public void testHamburgerButtonLogExists(){
		assertTrue("Asserts that the hamburger button is clicked", curl.doesHamburgerButtonClickLogExist());
	}
	
	@Test
	public void testModuleClickedFromHamburgerButtonLogExists(){
		assertTrue("Asserts that the module is clicked from the hamburger button menu", curl.doesModuleClickedFromHamburgerButtonLogExist("PebbleGo Animals"));
	}
	
	@Test
	public void testCategoryClickedLogExists(){
		assertTrue("Asserts that the category is clicked from a module", curl.doesCategoriesClickLogExist("Amphibians", "PebbleGo Animals"));
	}
	
	@Test
	public void testCategoryViewLogExists(){
		assertTrue("Asserts that the category is viewed", curl.doesCategoriesClickLogExist("Amphibians", "PebbleGo Animals"));
	}
	
	@Test
	public void testArticleClickedLogExists(){
		assertTrue("Asserts that the article is clicked", curl.doesArticleClickLogExist("Frogs", "PebbleGo Animals"));
	}
	
	@Test
	public void testArticleViewLogExists(){
		assertTrue("Asserts that the article viewed", curl.doesArticleViewLogExist("Frogs", "PebbleGo Animals"));
	}
	

	@Test
	public void testSearchIntoArticleClickLogExists(){
		assertTrue("Asserts that an article click is registered from the search bar", curl.doesArticleClickFromSearchLogExist("147"));
	}
	
	@Test
	public void testSearchIntoArticleViewLogExists(){
		assertTrue("Asserts that an article View is registered from search", curl.doesArticleViewLogExist("Grizzly Bears", "PebbleGo Animals"));
	}
	
	@Test
	public void testGlossaryLogExists(){
		assertTrue("Asserts that a Glossary Click log exists for the given article", curl.doesGlossaryClickLogExist("Grizzly Bears", "mammals"));
	}
	
	@Test
	public void testPrintArticleClickLogExists(){
		assertTrue("Asserts that a Print Article log exists", curl.doesPrintClickLogExist("article"));
	}
	
	@Test
	public void testPrintImageClickLogExists(){
		assertTrue("Asserts that a Print Image log exists", curl.doesPrintClickLogExist("image"));
	}
	
	@Test
	public void testActivityButtonClickExists(){
		assertTrue("Asserts that an Activity Button click log exists", curl.doesActivityClickLogExist("Grizzly Bears"));
	}
	
	@Test
	public void testPrintActivityLogExists(){
		assertTrue("Asserts that a Print Image log exists", curl.doesPrintClickLogExist("activity"));
	}
	
	@Test
	public void testPrintShareWhatYouKnowLogExists(){
		assertTrue("Asserts that a Print Share What You Know log exists", curl.doesPrintClickLogExist("share"));
	}
	
	@Test
	public void testCiteButtonLogExists(){
		assertTrue("Asserts that a Cite Button click log exists", curl.doesCitationClickLogExist("Grizzly Bears"));
	}
	
	@Test
	public void testCiteButtonPrintLogExists(){
		assertTrue("Asserts that a Print Cite button click log exists", curl.doesPrintClickLogExist("citations"));
	}
	
	@Test
	public void testVideoButtonClickLogExists(){
		assertTrue("Asserts that a Video Button Click log exists", curl.doesVideoClickLogExist("Grizzly Bears"));
	}
	
	@Test
	public void testNavigationTabLogsExist(){
		assertTrue("Asserts that this Article Nav Click log exists", curl.doesNavigationTabLogExist("Body"));
		assertTrue("Asserts that this Article Nav Click log exists", curl.doesNavigationTabLogExist("Habitat"));
		assertTrue("Asserts that this Article Nav Click log exists", curl.doesNavigationTabLogExist("Food"));
		assertTrue("Asserts that this Article Nav Click log exists", curl.doesNavigationTabLogExist("Life Cycle"));
		assertTrue("Asserts that this Article Nav Click log exists", curl.doesNavigationTabLogExist("Fun Facts"));
	}
	
	@Test
	public void testRelatedArticleTabClickLogExists(){
		assertTrue("Asserts that a Related Article Click log exists", curl.doesRelatedArticleClickLogExist("Grizzly Bears"));
	}
	
	@Test
	public void testArticleClickedFromRelatedArticleLogExists(){
		assertTrue("Asserts that an Article Click from Related Articles log exists", curl.doesArticleClickedFromRelatedArticleLogExist("Giant Pandas", "Grizzly Bears"));
	}
	
	@Test
	public void testLogoClickLogExists(){
		assertTrue("Asserts that a Logo Click log exists", curl.doesLogoClickLogExist("Pebble Go"));
	}
	
	@Test
	public void testTeacherResourcesLinkClickLogExists(){
		assertTrue("Asserts that a Community Teacher Click log exists", curl.doesTeacherResourcesClickLogExist("Pebble Go"));
	}
	
	@Test
	public void testThatPollClickLogExists(){
		assertTrue("Asserts that a Poll Click log exists", curl.doesPollClickLogExist("PebbleGo Science"));
	}
	
	@Test
	public void testGamesClickLogExists(){
		assertTrue("Asserts that a Game Click log exists", curl.doesGameClickLogExist("JigSaw", "PebbleGo Animals"));
	}
	
	
	
	public Boolean isLoggingEventPresent(JsonArray array, String type, Timestamp time) throws java.text.ParseException{
		for(JsonElement j : array){
			if(j.getAsJsonObject().get("log_type").toString().contains(type)){
				String elementTime = j.getAsJsonObject().get("created_at").toString();
				elementTime = elementTime.substring(1, elementTime.lastIndexOf("\""));
				
				Timestamp timeToCheck = convertToTimeStamp(elementTime);
				
				if(timeToCheck.after(time)){
					return true;
				}
			}		
		}

		return false;
	}
	
	public Timestamp convertToTimeStamp(String time) throws java.text.ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date parsedDate = dateFormat.parse(time);
		Timestamp timestamp = new Timestamp(parsedDate.getTime());
		return timestamp;
	}
	
	public JsonArray getRecordsForLogging(String environment, String numberOfLogs, String customerID) throws ParseException, IOException{
		String adminSQS = "$2y$10$O0S60.9z0G8xKmEIrQf46.tE9n6GA13xrx.qG5KFgH.yNvR8AqyHm";
		
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet("https://"+environment+".pebblego.com/log/number/"+numberOfLogs+"/customer/"+customerID+"/?sqs="+adminSQS);		
		//https://staging.pebblego.com/log/number/50/customer/20662/?sqs=$2y$10$O0S60.9z0G8xKmEIrQf46.tE9n6GA13xrx.qG5KFgH.yNvR8AqyHm
		HttpResponse response = client.execute(httpGet);
		
		String test = EntityUtils.toString(response.getEntity());
		JsonArray loggingArray = (JsonArray) new JsonParser().parse(test);
		
		
		return loggingArray;
	}
}
