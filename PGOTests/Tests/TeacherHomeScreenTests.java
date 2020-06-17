package Tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AdminResources.DisableArticlesDatabasePage;
import AdminResources.TeacherHomeScreen;
import AdminResources.TeacherLoginPage;
import PGOPages.LoginPage;
import PGOStudentResources.StudentHomePage;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.ProductType;
import UserClasses.User;
import UserClasses.UserInfo;

public class TeacherHomeScreenTests{
	static TeacherLoginPage loginPage;
	static TeacherHomeScreen teacherHome;
	static User teacher;
	static User teacherAccess;
	static User studentAccess;
	
	@BeforeClass
	public static void executeBefore(){
		teacher = new User(UserInfo.PGOTESTINGTEACHER);

		loginPage = new TeacherLoginPage(teacher);
		try{
			teacherHome = (TeacherHomeScreen) loginPage.Login(teacher);
		} catch(Exception e){
		//	teacher.getWatcher().TakeScreenshot("Failure to run executeBefore for TeacherHomeScreenTests");
		}
	}
	
	
	@AfterClass
	public static void executeAfter(){
		teacher.quit();
		
		try{
			teacherAccess.quit();
		}catch(Exception x){
			System.out.println("Cannot quit Teacher Access Driver");
		}
		
		try{
			studentAccess.quit();
		}catch(Exception y){
			System.out.println("Cannot quit Student Access Driver");
		}
	}

	
	//@Rule
    public TestWatcher restart = new TestWatcher(){
    	@Override
    	public void failed(Throwable e, Description description){
    		teacher = new User(UserInfo.PGOTESTINGTEACHER);

    		loginPage = new TeacherLoginPage(teacher);
    		try{
    			teacherHome = (TeacherHomeScreen) loginPage.Login(teacher);
    		} catch(Exception f){
    			teacher.getWatcher().TakeScreenshot("Failure to run restart for TeacherHomeScreenTests");
    		}
    	}
    };
	
	
	//@Rule
	public Retry retry = new Retry(3);
	
	
	@Test
	@Category(SmokeTests.class)
	public void testDisableArticlesLink(){
		DisableArticlesDatabasePage databasePage = teacherHome.clickDisableArticlesButton();
		
		assertTrue("Asserts that a teacher can access the Disabled Articles page and that all correct elements are present",
				databasePage.verifyElementsVisibility(databasePage.getInitialVisibleElements()));
		teacherHome = databasePage.clickBackToAdminButton();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testLogOutButton(){
		loginPage = (TeacherLoginPage) teacherHome.clickLogoutButton(ProductType.PGO);
		
		assertTrue("Asserts that the logout button on the Teacher Home page brings the user to the Login page",
				loginPage.verifyElementsVisibility(loginPage.getInitialVisibleElements()));
		teacherHome = (TeacherHomeScreen) loginPage.Login(teacher);
	}
	
	@Test
	public void testArticleViewsTab(){
		teacherHome.click(teacherHome.articleViewsTab);
		
		WebElement button = teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.downloadArticleUsageButton));
		
		assertTrue("Asserts that the Download Article Usage button is present on the Article Views tab",
				teacherHome.isElementPresent(button));
	}
	
	@Test
	public void testDownloadArticleUsageButton(){
		teacherHome.click(teacherHome.articleViewsTab);
		
		WebElement button = teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.downloadArticleUsageButton));
		
		assertTrue("Asserts that the Download Article Usage button is active and the url within is valid",
			teacherHome.verifyLinkByElement(teacherHome.downloadArticleUsageButton) && 
			teacherHome.isElementPresent(button));
	}
	
	@Test
	public void testArticlePopularityTab(){
		teacherHome.click(teacherHome.articlePopularityTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.usageByTopicDownloadButton));
		
		assertTrue("Asserts that the Download Article Usage button is present on the Article Views tab",
				teacherHome.isElementPresent(teacherHome.usageByTopicDownloadButton));
		assertTrue("Asserts that other elements are present on the Article Popularity tab", 
				teacherHome.isElementPresent(teacherHome.paginatorDiv) &&
				teacherHome.isElementPresent(teacherHome.articlePopularityTable));
	}
	
	@Test
	public void testArticlePopularityDownloadButton(){
		teacherHome.click(teacherHome.articlePopularityTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.usageByTopicDownloadButton));
		
		assertTrue("Asserts that the Download Article Usage button is present on the Article Views tab",
				teacherHome.isElementPresent(teacherHome.usageByTopicDownloadButton));
	}
	
	@Test
	public void testArticleUsageByTopicDownloadButton(){
		teacherHome.click(teacherHome.articlePopularityTab);
		
		WebElement button = teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.usageByTopicDownloadButton));

		assertTrue("Asserts that the Article Usage By Topic Download button is active and the url within is valid",
			teacherHome.verifyLinkByElement(teacherHome.downloadArticleUsageButton) && teacherHome.isElementPresent(button));
	}
	
	@Test
	public void testGameUsageElements(){
		teacherHome.click(teacherHome.gamesTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.gameUsageDownloadButton));
		
		assertTrue("Asserts that other elements are present on the Game Usage tab", 
				teacherHome.isElementPresent(teacherHome.gameUsageDownloadButton) &&
				teacherHome.isElementPresent(teacherHome.gameUsageGamePlaysByMonthButton) &&
				teacherHome.isElementPresent(teacherHome.gameUsageTable));
	}
	
	@Test
	public void testGameUsageDownloadButton(){
		teacherHome.click(teacherHome.gamesTab);
		
		WebElement button = teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.gameUsageDownloadButton));
		
		assertTrue("Asserts that the Download Game Usage button is active and the url within is valid",
			teacherHome.verifyLinkByElement(teacherHome.downloadArticleUsageButton) && teacherHome.isElementPresent(button));
	}
	
	@Test
	public void testGameUsageDownloadByMonthButton(){
		teacherHome.click(teacherHome.gamesTab);
		
		WebElement button = teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.gameUsageGamePlaysByMonthButton));
		
		assertTrue("Asserts that the Download Game Plays By Month button is active and the url within is valid",
			teacherHome.verifyLinkByElement(teacherHome.downloadArticleUsageButton) && teacherHome.isElementPresent(button));
	}
	
	@Test
	public void testTopContentButtons(){
		teacherHome.click(teacherHome.topContentTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.displayTopArticlesButton));
		
		teacherHome.click(teacherHome.displayTopArticlesButton);
		
		teacher.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacherHome.displayTopArticlesTable));
		
		assertTrue("asserts that the top articles table is present and the top articles button is not present",
				teacherHome.isElementPresent(teacherHome.displayTopArticlesTable) &&
				!teacherHome.isElementPresent(teacherHome.displayTopArticlesButton));
	}
	
	@Test
	public void testDisplayAnimalsReportButton(){
		teacherHome.click(teacherHome.topContentTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.displayAnimalsReportButton));
		
		teacherHome.click(teacherHome.displayAnimalsReportButton);
		
		teacher.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacherHome.animalsReportTable));
		
		assertTrue("asserts that the animals report table is present and the top articles button is not present",
				teacherHome.isElementPresent(teacherHome.animalsReportTable) &&
				!teacherHome.isElementPresent(teacherHome.displayAnimalsReportButton));
	}
	
	@Test
	public void testDisplayPGOScienceReportButton(){
		teacherHome.click(teacherHome.topContentTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.displayScienceReportButton));
		
		teacherHome.click(teacherHome.displayScienceReportButton);
		
		teacher.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacherHome.scienceReportTable));
		
		assertTrue("asserts that the science report table is present and the top articles button is not present",
				teacherHome.isElementPresent(teacherHome.scienceReportTable) &&
				!teacherHome.isElementPresent(teacherHome.displayScienceReportButton));
	}
	
	@Test
	public void testDisplayPGOBiographiesReportButton(){
		teacherHome.click(teacherHome.topContentTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.displayBiographiesReportButton));
		
		teacherHome.click(teacherHome.displayBiographiesReportButton);
		
		teacher.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacherHome.biographiesReportTable));
		
		assertTrue("asserts that the biographies report table is present and the top articles button is not present",
				teacherHome.isElementPresent(teacherHome.biographiesReportTable) &&
				!teacherHome.isElementPresent(teacherHome.displayBiographiesReportButton));
	}
	
	@Test
	public void testDisplaySocialStudiesReportButton(){
		teacherHome.click(teacherHome.topContentTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.displaySocialStudiesReportButton));
		
		teacherHome.click(teacherHome.displaySocialStudiesReportButton);
		
		teacher.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacherHome.socialStudiesReportTable));
		
		assertTrue("asserts that the social studies report table is present and the top articles button is not present",
				teacherHome.isElementPresent(teacherHome.socialStudiesReportTable) &&
				!teacherHome.isElementPresent(teacherHome.displaySocialStudiesReportButton));
	}
	
	@Test
	public void testDisplayPGNStatesReportButton(){
		teacherHome.click(teacherHome.topContentTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.displayPGNStatesReportButton));
		
		teacherHome.click(teacherHome.displayPGNStatesReportButton);
		
		teacher.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacherHome.pgnStatesReportTable));
		
		assertTrue("asserts that the pgn states report table is present and the top articles button is not present",
				teacherHome.isElementPresent(teacherHome.pgnStatesReportTable) &&
				!teacherHome.isElementPresent(teacherHome.displayPGNStatesReportButton));
	}
	
	@Test
	public void testDisplayDinosaursReportButton(){
		teacherHome.click(teacherHome.topContentTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.displayDinosaursReportButton));
		
		teacherHome.click(teacherHome.displayDinosaursReportButton);
		
		teacher.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacherHome.dinosaursReportTable));
		
		assertTrue("asserts that the dinosaurs report table is present and the top articles button is not present",
				teacherHome.isElementPresent(teacherHome.dinosaursReportTable) &&
				!teacherHome.isElementPresent(teacherHome.displayDinosaursReportButton));
	}
	
	@Test
	public void testDisplayAnimalesReportButton(){
		teacherHome.click(teacherHome.topContentTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.displayAnimalesReportButton));
		
		teacherHome.click(teacherHome.displayAnimalesReportButton);
		
		teacher.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacherHome.animalesReportTable));
		
		assertTrue("asserts that the animales report table is present and the top articles button is not present",
				teacherHome.isElementPresent(teacherHome.animalesReportTable) &&
				!teacherHome.isElementPresent(teacherHome.displayAnimalesReportButton));
	}
	
	@Test
	public void testDisplayPGNScienceReportButton(){
		teacherHome.click(teacherHome.topContentTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.displayPGNScienceReportButton));
		
		teacherHome.click(teacherHome.displayPGNScienceReportButton);
		
		teacher.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacherHome.pgnScienceReportTable));
		
		assertTrue("asserts that the pgn science report table is present and the top articles button is not present",
				teacherHome.isElementPresent(teacherHome.pgnScienceReportTable) &&
				!teacherHome.isElementPresent(teacherHome.displayPGNScienceReportButton));
	}
	
	@Test
	public void testDisplayPGNAmericanIndianHistoryReportButton(){
		teacherHome.click(teacherHome.topContentTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.displayPGNAIHReportButton));
		
		teacherHome.click(teacherHome.displayPGNAIHReportButton);
		
		teacher.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacherHome.pgnAIHReportTable));
		
		assertTrue("asserts that the pgn american indian history report table is present and the top articles button is not present",
				teacherHome.isElementPresent(teacherHome.pgnAIHReportTable) &&
				!teacherHome.isElementPresent(teacherHome.displayPGNAIHReportButton));
	}
	
	@Test
	public void testDisplayPGNSocialStudiesReportButton(){
		teacherHome.click(teacherHome.topContentTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.displayPGNSocialStudiesReportButton));
		
		teacherHome.click(teacherHome.displayPGNSocialStudiesReportButton);
		
		teacher.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacherHome.pgnSocialStudiesReportTable));
		
		assertTrue("asserts that the pgn social studies report table is present and the top articles button is not present",
				teacherHome.isElementPresent(teacherHome.pgnSocialStudiesReportTable) &&
				!teacherHome.isElementPresent(teacherHome.displayPGNSocialStudiesReportButton));
	}
	
	@Test
	public void testDisplayCienciaReportButton(){
		teacherHome.click(teacherHome.topContentTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.displayCienciaReportButton));
		
		teacherHome.click(teacherHome.displayCienciaReportButton);
		
		teacher.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacherHome.cienciaReportTable));
		
		assertTrue("asserts that ciencia report table is present and the top articles button is not present",
				teacherHome.isElementPresent(teacherHome.cienciaReportTable) &&
				!teacherHome.isElementPresent(teacherHome.displayCienciaReportButton));
	}
	
	@Test
	public void testDisplayBiografiasReportButton(){
		teacherHome.click(teacherHome.topContentTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.displayBiografiasReportButton));
		
		teacherHome.click(teacherHome.displayBiografiasReportButton);
		
		teacher.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacherHome.biografiasReportTable));
		
		assertTrue("asserts that biograafias report table is present and the top articles button is not present",
				teacherHome.isElementPresent(teacherHome.biografiasReportTable) &&
				!teacherHome.isElementPresent(teacherHome.displayBiografiasReportButton));
	}
	
	@Test
	public void testDisplayEstudiosSocialesReportButton(){
		teacherHome.click(teacherHome.topContentTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.displayEstudiosSocialesReportButton));
		
		teacherHome.click(teacherHome.displayEstudiosSocialesReportButton);
		
		teacher.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacherHome.estudiosSocialesReportTable));
		
		assertTrue("asserts that estudios sociales report table is present and the top articles button is not present",
				teacherHome.isElementPresent(teacherHome.estudiosSocialesReportTable) &&
				!teacherHome.isElementPresent(teacherHome.displayEstudiosSocialesReportButton));
	}
	
	@Test
	public void testDisplayPGNBiographiesReportButton(){
		teacherHome.click(teacherHome.topContentTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.displayPGNBiographiesReportButton));
		
		teacherHome.click(teacherHome.displayPGNBiographiesReportButton);
		
		teacher.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacherHome.pgnBiographiesReportTable));
		
		assertTrue("asserts that estudios sociales report table is present and the top articles button is not present",
				teacherHome.isElementPresent(teacherHome.pgnBiographiesReportTable) &&
				!teacherHome.isElementPresent(teacherHome.displayPGNBiographiesReportButton));
	}
	
	@Test
	public void testSubscriptionsUSOnlyText(){
		teacherHome.click(teacherHome.subscriptionsTab);
		
		assertTrue("Asserts that the subscriptions tab has us only excluded test", 
				teacherHome.isElementPresent(teacherHome.usContentExcludedHeader));
		assertTrue("Asserts that the subscription tab has the correct us only excluded text",
				teacherHome.getElementText(teacherHome.usContentExcludedHeader).equals("U.S. Content Excluded for all PebbleGo and PebbleGo Next Modules."));
	}
	
	@Test
	public void testSubscriptionUSOnlyNotPresent(){
		User teacherAccess = new User(UserInfo.ACCESSTABTEACHER);
		
		TeacherLoginPage teacherLoginPage = new TeacherLoginPage(teacherAccess);
		TeacherHomeScreen extraTeacherHome = (TeacherHomeScreen) teacherLoginPage.Login(teacherAccess);
		
		assertTrue("Asserts that the subscription tab does not have excluded test", !extraTeacherHome.isElementPresent(extraTeacherHome.usContentExcludedHeader));
		teacherAccess.quit();
	}
	
	
	@Test
	public void testSubscriptionsElements(){
		teacherHome.click(teacherHome.subscriptionsTab);
		
		teacher.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(teacherHome.animalsSubscription));

		assertTrue("Asserts that the pgo animals subscription is present", 
				teacherHome.getDriver().findElement(teacherHome.animalsSubscription).getText().equals("PebbleGo Animals"));
		assertTrue("Asserts that the pgo animals subscription has the correct active dates",
				teacherHome.getDriver().findElement(teacherHome.animalsActiveDates).getText().equals("Activation Date: 2018-01-01 00:00:00\nExpiration Date: 2025-01-01 00:00:00"));
		
		assertTrue("Asserts that the pgo science subscription is present", 
				teacherHome.getDriver().findElement(teacherHome.scienceSubscription).getText().equals("PebbleGo Science"));
		assertTrue("Asserts that the pgo biographies subscription has the correct active dates",
				teacherHome.getDriver().findElement(teacherHome.scienceActiveDates).getText().equals("Activation Date: 2018-01-01 00:00:00\nExpiration Date: 2025-01-01 00:00:00"));
		
		assertTrue("Asserts that the pgo biographies subscription is present", 
				teacherHome.getDriver().findElement(teacherHome.biographiesSubscription).getText().equals("PebbleGo Biographies"));
		assertTrue("Asserts that the pgo biographies subscription has the correct active dates",
				teacherHome.getDriver().findElement(teacherHome.biographiesActiveDates).getText().equals("Activation Date: 2018-01-01 00:00:00\nExpiration Date: 2025-01-01 00:00:00"));
		
		assertTrue("Asserts that the pgo social studies subscription is present", 
				teacherHome.getDriver().findElement(teacherHome.socialStudiesSubscription).getText().equals("PebbleGo Social Studies"));
		assertTrue("Asserts that the pgo social studies subscription has the correct active dates",
				teacherHome.getDriver().findElement(teacherHome.socialStudiesActiveDates).getText().equals("Activation Date: 2018-01-01 00:00:00\nExpiration Date: 2025-01-01 00:00:00"));
		
		assertTrue("Asserts that the pgn states subscription is present", 
				teacherHome.getDriver().findElement(teacherHome.pgnStatesSubscription).getText().equals("PebbleGo Next States"));
		assertTrue("Asserts that the pgn states subscription has the correct active dates",
				teacherHome.getDriver().findElement(teacherHome.pgnStatesActiveDates).getText().equals("Activation Date: 2018-01-01 00:00:00\nExpiration Date: 2025-01-01 00:00:00"));
		
		assertTrue("Asserts that the pgo dinosaurs subscription is present", 
				teacherHome.getDriver().findElement(teacherHome.dinosaursSubscription).getText().equals("PebbleGo Dinosaurs"));
		assertTrue("Asserts that the pgo dinosaurs subscription has the correct active dates",
				teacherHome.getDriver().findElement(teacherHome.dinosaursActiveDates).getText().equals("Activation Date: 2018-01-01 00:00:00\nExpiration Date: 2025-01-01 00:00:00"));
		
		assertTrue("Asserts that the pgo animales subscription is present", 
				teacherHome.getDriver().findElement(teacherHome.animalesSubscription).getText().equals("PebbleGo Animales"));
		assertTrue("Asserts that the pgo animales subscription has the correct active dates",
				teacherHome.getDriver().findElement(teacherHome.animalesActiveDates).getText().equals("Activation Date: 2018-01-01 00:00:00\nExpiration Date: 2025-01-01 00:00:00"));
		
		assertTrue("Asserts that the pgn science subscription is present", 
				teacherHome.getDriver().findElement(teacherHome.pgnScienceSubscription).getText().equals("PebbleGo Next Science"));
		assertTrue("Asserts that the pgn science subscription has the correct active dates",
				teacherHome.getDriver().findElement(teacherHome.pgnScienceActiveDates).getText().equals("Activation Date: 2018-01-01 00:00:00\nExpiration Date: 2025-01-01 00:00:00"));
		
		assertTrue("Asserts that the pgn american indians history subscription is present", 
				teacherHome.getDriver().findElement(teacherHome.pgnAIHSubscription).getText().equals("PebbleGo Next American Indian History"));
		assertTrue("Asserts that the pgn american idnian history subscription has the correct active dates",
				teacherHome.getDriver().findElement(teacherHome.pgnAIHActiveDates).getText().equals("Activation Date: 2018-01-01 00:00:00\nExpiration Date: 2025-01-01 00:00:00"));
		
		assertTrue("Asserts that the pgn social studies subscription is present", 
				teacherHome.getDriver().findElement(teacherHome.pgnSocialStudiesSubscription).getText().equals("PebbleGo Next Social Studies"));
		assertTrue("Asserts that the pgo ciencia subscription has the correct active dates",
				teacherHome.getDriver().findElement(teacherHome.socialStudiesActiveDates).getText().equals("Activation Date: 2018-01-01 00:00:00\nExpiration Date: 2025-01-01 00:00:00"));
		
		assertTrue("Asserts that the pgo ciencia subscription is present", 
				teacherHome.getDriver().findElement(teacherHome.cienciaSubscription).getText().equals("PebbleGo Ciencia"));
		assertTrue("Asserts that the pgo ciencia subscription has the correct active dates",
				teacherHome.getDriver().findElement(teacherHome.cienciaActiveDates).getText().equals("Activation Date: 2018-01-01 00:00:00\nExpiration Date: 2025-01-01 00:00:00"));
		
		assertTrue("Asserts that the pgo biografias subscription is present", 
				teacherHome.getDriver().findElement(teacherHome.biografiasSubscription).getText().equals("PebbleGo Biografías"));
		assertTrue("Asserts that the pgo biografias subscription has the correct active dates",
				teacherHome.getDriver().findElement(teacherHome.biografiasActiveDates).getText().equals("Activation Date: 2018-01-01 00:00:00\nExpiration Date: 2025-01-01 00:00:00"));
		
		assertTrue("Asserts that the pgo estudios sociales subscription is present", 
				teacherHome.getDriver().findElement(teacherHome.estudiosSocialesSubscription).getText().equals("PebbleGo Estudios Sociales"));
		assertTrue("Asserts that the pgo estudios sociales subscription has the correct active dates",
				teacherHome.getDriver().findElement(teacherHome.estudiosSocialesActiveDates).getText().equals("Activation Date: 2018-01-01 00:00:00\nExpiration Date: 2025-01-01 00:00:00"));
		
		assertTrue("Asserts that the pgn biographies subscription is present", 
				teacherHome.getDriver().findElement(teacherHome.pgnBiographiesSubscription).getText().equals("PebbleGo Next Biographies"));
		assertTrue("Asserts that the pgn biographies subscription has the correct active dates",
				teacherHome.getDriver().findElement(teacherHome.pgnBiographiesActiveDates).getText().equals("Activation Date: 2018-01-01 00:00:00\nExpiration Date: 2025-01-01 00:00:00"));
	}
	
	@Test
	public void testLinkAccountsElements(){
		teacherHome.click(teacherHome.linkAccountsTab);
		
		teacher.customWait().until(ExpectedConditions.elementToBeClickable(teacherHome.linkAccountsButton));
		
		assertTrue("Asserts that the elements of the Link Accounts Tab are present",
				teacherHome.isElementPresent(teacherHome.linkAccountsButton) &&
				teacherHome.isElementPresent(teacherHome.linkGoogleAccountsButton));
	}
	
	//TODO:Fix
	//Access Tab Tests
	@Test
	public void testAccessTabCanChangePGOStudentPassword(){
		studentAccess = new User(UserInfo.ACCESSTABSTUDENT);
		String oldPassword = "6ivJjj4X";
		String newPassword = "errorjj4X";
		
		LoginPage studentLoginPage = new LoginPage(studentAccess);
		
		teacherAccess = new User(UserInfo.ACCESSTABTEACHER);
		TeacherLoginPage teacherLoginPage = new TeacherLoginPage(teacherAccess);
		
		TeacherHomeScreen teacherHomeScreen = (TeacherHomeScreen) teacherLoginPage.Login(teacherAccess);
		
		teacherHomeScreen.click(teacherHomeScreen.userAccessTab);
		teacherAccess.customWait().until(ExpectedConditions.elementToBeClickable(teacherHomeScreen.studentUpdateButton));
		String before = teacherHomeScreen.getPGNStudentSQS();
		
		teacherHomeScreen.updateStudentPassword(newPassword);
		teacherAccess.customWait().until(ExpectedConditions.textToBe(teacherHomeScreen.studentUpdateButton, ""));
		String after = teacherHomeScreen.getPGNStudentSQS();
		
		assertTrue("Asserts that the sqs string has changed", before != after);
		System.out.println("We tested that the sqs string changed");
		
		String pgoSQS = teacherHomeScreen.getPGOStudentSQS();
		
		studentLoginPage.getDriver().get("https://"+teacherAccess.getEnvironment()+pgoSQS);
		
		StudentHomePage student = new StudentHomePage(studentAccess.getDriver());
		studentAccess.customWait().until(ExpectedConditions.elementToBeClickable(student.animalsModule));
		
		assertTrue("Asserts that a student can use the new SQS string to log in directly", 
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		System.out.println("We tested that the sqs string can be used to log in");
		
		studentLoginPage = student.clickLogOutButton();
		
		studentAccess.setPassword(newPassword);
		student = (StudentHomePage) studentLoginPage.Login(studentAccess);
		studentAccess.customWait().until(ExpectedConditions.elementToBeClickable(student.animalsModule));
		
		assertTrue("Asserts that a student can use the new password to log in directly", 
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		System.out.println("We tested that the username and password can be used to log in");
		
		
		student.quit();
		
		teacherHomeScreen.updateStudentPassword(oldPassword);
		teacherAccess.customWait().until(ExpectedConditions.textToBe(teacherHomeScreen.studentPassword, ""));
		teacherHomeScreen.quit();
	}
	
	//TODO:Fix
	@Test
	public void testAccessTabCanChangePGNStudentPassword(){
		studentAccess = new User(UserInfo.ACCESSTABSTUDENT);
		String oldPassword = "6ivJjj4X";
		String newPassword = "errorjj4X";
		
		PGNPages.LoginPage studentLoginPage = new PGNPages.LoginPage(studentAccess);
		
		teacherAccess = new User(UserInfo.ACCESSTABTEACHER);
		TeacherLoginPage teacherLoginPage = new TeacherLoginPage(teacherAccess);
		
		TeacherHomeScreen teacherHomeScreen = (TeacherHomeScreen) teacherLoginPage.Login(teacherAccess);
			
		teacherHomeScreen.click(teacherHomeScreen.userAccessTab);
		teacherAccess.customWait().until(ExpectedConditions.elementToBeClickable(teacherHomeScreen.studentUpdateButton));
		String before = teacherHomeScreen.getPGNStudentSQS();
		
		teacherHomeScreen.updateStudentPassword(newPassword);
		teacherAccess.customWait().until(ExpectedConditions.textToBe(teacherHomeScreen.studentUpdateButton, ""));
		
		String after = teacherHomeScreen.getPGNStudentSQS();
		assertTrue("Asserts that the sqs string has changed", before != after);
		System.out.println("We tested that the sqs string changed");
		
		String pgnSQS = teacherHomeScreen.getPGNStudentSQS();
		
		studentLoginPage.getDriver().get("https://"+teacherAccess.getEnvironment()+pgnSQS);
		
		PGNStudentResources.StudentHomePage student = new PGNStudentResources.StudentHomePage(studentAccess.getDriver());
		studentAccess.customWait().until(ExpectedConditions.elementToBeClickable(student.goToStatesLink));
		
		assertTrue("Asserts that a student can use the new SQS string to log in directly", 
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		System.out.println("We tested that the sqs string can be used to log in");
		
		studentLoginPage = student.header.logout();
		
		studentAccess.setPassword(newPassword);
		student = (PGNStudentResources.StudentHomePage) studentLoginPage.login(studentAccess);
		studentAccess.customWait().until(ExpectedConditions.elementToBeClickable(student.goToStatesLink));
		
		assertTrue("Asserts that a student can use the new password to log in directly", 
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		System.out.println("We tested that the username and password can be used to log in");
		
		student.quit();
		
		teacherHomeScreen.updateStudentPassword(oldPassword);
		teacherAccess.customWait().until(ExpectedConditions.textToBe(teacherHomeScreen.studentPassword, ""));
		teacherHomeScreen.quit();
	}
	
	//TODO:Fix
	@Test
	public void testAccessTabCanChangePGOTeacherPassword(){
		teacherAccess = new User(UserInfo.ACCESSTABTEACHER);
		String oldPassword = "WAkkcPM8";
		String newPassword = "errorPM8";	

		TeacherLoginPage teacherLoginPage = new TeacherLoginPage(teacherAccess);
		
		TeacherHomeScreen teacherHomeScreen = (TeacherHomeScreen) teacherLoginPage.Login(teacherAccess);
		
		teacherHomeScreen.click(teacherHomeScreen.userAccessTab);
		teacherAccess.customWait().until(ExpectedConditions.elementToBeClickable(teacherHomeScreen.teacherUpdateButton));
		String before = teacherHomeScreen.getPGOTeacherSQS();
		
		teacherHomeScreen.updateTeacherPassword(newPassword);
		teacherAccess.customWait().until(ExpectedConditions.textToBe(teacherHomeScreen.teacherPassword, ""));
		
		String after = teacherHomeScreen.getPGOTeacherSQS();
		assertTrue("Asserts that the sqs string has changed", before != after);
		System.out.println("We tested that the sqs string changed");
		
		teacherLoginPage = (TeacherLoginPage) teacherHomeScreen.clickLogoutButton(ProductType.PGO);
		teacherLoginPage.getDriver().get("https://" + teacherAccess.getEnvironment() + ".pebblego.com");
		
		// We can turn this test on when Teacher SQS is fixed
		/*
		teacherLoginPage = (LoginPage) teacherHomeScreen.clickLogoutButton(ProductType.PGO);
		
		teacherLoginPage.getDriver().get("https://"+after);
		
		teacherAccess.customWait().until(ExpectedConditions.elementToBeClickable(teacherHomeScreen.userAccessTab));
		
		assertTrue("Asserts that a student can use the new sqs to log in directly", 
				teacherHomeScreen.verifyElementsVisibility(teacherHomeScreen.getInitialVisibleElements()));
		
		teacherLoginPage = (LoginPage) teacherHomeScreen.clickLogoutButton(ProductType.PGO);
		*/
	
		teacherAccess.setPassword(newPassword);
		teacherHomeScreen = (TeacherHomeScreen) teacherLoginPage.Login(teacherAccess);
		
		teacherAccess.customWait().until(ExpectedConditions.elementToBeClickable(teacherHomeScreen.userAccessTab));
		
		assertTrue("Asserts that a student can use the new password to log in directly", 
				teacherHomeScreen.verifyElementsVisibility(teacherHomeScreen.getInitialVisibleElements()));
		System.out.println("We tested that the username and password can be used to log in");
		
		teacherHomeScreen.click(teacherHomeScreen.userAccessTab);
		teacherAccess.customWait().until(ExpectedConditions.elementToBeClickable(teacherHomeScreen.teacherUpdateButton));
		
		teacherHomeScreen.updateTeacherPassword(oldPassword);
		teacherAccess.customWait().until(ExpectedConditions.textToBe(teacherHomeScreen.teacherPassword, ""));
		teacherHomeScreen.quit();
	}
	
	//TODO:Fix
	@Test
	public void testAccessTabCanChangePGNTeacherPassword(){
		teacherAccess = new User(UserInfo.ACCESSTABTEACHER);
		String oldPassword = "WAkkcPM8";
		String newPassword = "errorPM8";
		
		TeacherLoginPage teacherLoginPage = new TeacherLoginPage(teacherAccess);
		
		TeacherHomeScreen teacherHomeScreen = (TeacherHomeScreen) teacherLoginPage.Login(teacherAccess);
			
		teacherHomeScreen.click(teacherHomeScreen.userAccessTab);
		teacherAccess.customWait().until(ExpectedConditions.elementToBeClickable(teacherHomeScreen.teacherUpdateButton));
		String before = teacherHomeScreen.getPGNTeacherSQS();
		
		teacherHomeScreen.updateTeacherPassword(newPassword);
		teacherAccess.customWait().until(ExpectedConditions.textToBe(teacherHomeScreen.teacherPassword, ""));
		
		String after = teacherHomeScreen.getPGNTeacherSQS();
		assertTrue("Asserts that the sqs string has changed", before != after);
		System.out.println("We tested that the sqs string changed");
		//String pgnSQS = teacherHomeScreen.getPGNTeacherSQS();
		
		// We can turn this test on when Teacher SQS is fixed
		/*
		studentLoginPage.getDriver().get("https://"+pgnSQS);
		
		PGNStudentResources.StudentHomePage student = new PGNStudentResources.StudentHomePage(studentAccess.getDriver());
		studentAccess.customWait().until(ExpectedConditions.elementToBeClickable(student.goToStatesLink));
		
		assertTrue("Asserts that a student can use the new SQS string to log in directly", 
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		
		studentLoginPage = student.header.logout();
		*/
		
		teacherLoginPage = (TeacherLoginPage) teacherHomeScreen.clickLogoutButton(ProductType.PGN);
		teacherLoginPage.getDriver().get("https://" + teacherAccess.getEnvironment() + ".pebblegonext.com");
		
		teacherAccess.setPassword(newPassword);
		teacherHomeScreen = (TeacherHomeScreen) teacherLoginPage.Login(teacherAccess);
		teacherAccess.customWait().until(ExpectedConditions.elementToBeClickable(teacherHomeScreen.userAccessTab));
		
		assertTrue("Asserts that a student can use the new password to log in directly", 
				teacherHomeScreen.verifyElementsVisibility(teacherHomeScreen.getInitialVisibleElements()));
		System.out.println("We tested that the username and password can be used to log in");
		
		teacherHomeScreen.click(teacherHomeScreen.userAccessTab);
		teacherAccess.customWait().until(ExpectedConditions.elementToBeClickable(teacherHomeScreen.teacherUpdateButton));
		teacherHomeScreen.updateTeacherPassword(oldPassword);
		
		teacherAccess.customWait().until(ExpectedConditions.textToBe(teacherHomeScreen.teacherPassword, ""));
		teacherHomeScreen.quit();
	}
	
	//TODO:Fix
	@Test
	public void testLinkGoogleIDButtonAndWarning(){
		User teacherAccess = new User(UserInfo.ACCESSTABTEACHER);

		LoginPage teacherLoginPage = new LoginPage(teacherAccess);
		
		TeacherHomeScreen teacherHomeScreen = (TeacherHomeScreen) teacherLoginPage.Login(teacherAccess);
		
		teacherAccess.customWait().until(ExpectedConditions.elementToBeClickable(teacherHomeScreen.linkAccountsTab));
		teacherHomeScreen.click(teacherHomeScreen.linkAccountsTab);
		
		teacherAccess.customWait().until(ExpectedConditions.elementToBeClickable(teacherHomeScreen.linkAccountsButton));
		
		assertTrue("Asserts that the link my class IDs button is not active", teacherAccess.getDriver().findElement(teacherHomeScreen.linkGoogleAccountsButton).getAttribute("class").equals("yellow_button_disabled"));
		assertTrue("Asserts that the link my class IDs error is present", teacherHomeScreen.isElementPresent(teacherHomeScreen.linkGoogleError));
		
		teacherAccess.quit();
	}
	 @Rule
	    public TestRule watcher = new TestWatcher() {
	       protected void starting(Description description) {
	          System.out.println("Starting test: " + description.getMethodName());
	        
	          
	       }
	    };
}
