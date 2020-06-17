package Tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.ParseException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AdminResources.DisableArticlesDatabasePage;
import AdminResources.DisableArticlesTogglePage;
import AdminResources.TeacherHomeScreen;
import Modals.RelatedArticlesModal;
import PGNPages.LoginPage;
import PGNStudentResources.ArticlePage;
import PGNStudentResources.DatabasePage;
import PGNStudentResources.IndiansDatabaseHome;
import PGNStudentResources.StudentHomePage;
import SharedClasses.ContentTreeAPI;
import TrueUserTests.TrueUser.PGNSmokeTest;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;
import UserClasses.UserInfo;

public class PGNDisableArticlesTests {
	static LoginPage loginPage;
	static LoginPage studentLogin;
	static User user;
	static User student; //= new User (UserInfo.GARAGESTUDENT);
	static TeacherHomeScreen home;
	static DisableArticlesDatabasePage databasePage;
	static StudentHomePage studentHome;
	
	
	@BeforeClass
	public static void executeBefore(){
		user = new User(UserInfo.DISABLETEACHER);
		student = new User(UserInfo.DISABLESTUDENT);
		
		loginPage = new LoginPage(user);
		studentLogin = new LoginPage(student);
		
		home = (TeacherHomeScreen) loginPage.login(user);
		databasePage = home.clickDisableArticlesButton();
		
		DisableArticlesTogglePage togglePage = databasePage.clickPGNStatesDatabaseLink();
		togglePage.clickEnableAllButton();
		togglePage.waitImplicitly(2);
		togglePage.clickItemByName("West");
		user.customWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(),'Montana')]")));
		if(togglePage.isItemOnByName("Montana")){
			togglePage.toggleItemByName("Montana");
		}
		databasePage = togglePage.clickSelectDatabaseButton();
		
		togglePage = databasePage.clickPGNAmericanIndianHistoryDatabaseLink();
		togglePage.clickEnableAllButton();
		togglePage.waitImplicitly(2);
		togglePage.clickItemByName("Great Plains Tribal Nations");
		user.customWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(),'Arapaho')]")));
		if(togglePage.isItemOnByName("Pawnee")){
			togglePage.toggleItemByName("Pawnee");
		}
		databasePage = togglePage.clickSelectDatabaseButton();
		
		togglePage = databasePage.clickPGNSocialStudiesDatabaseLink();
		togglePage.clickEnableAllButton();
		togglePage.waitImplicitly(2);
		togglePage.clickItemByName("Culture");
		user.customWait().until(ExpectedConditions.presenceOfElementLocated(By.linkText("Community and Self")));
		togglePage.clickItemByName("Community and Self");
		user.customWait().until(ExpectedConditions.presenceOfElementLocated(By.linkText("About the Community")));
		togglePage.clickItemByName("About the Community");
		user.customWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(),'Bullying')]")));
		if(togglePage.isItemOnByName("Bullying")){
			togglePage.toggleItemByName("Bullying");
		}
		databasePage = togglePage.clickSelectDatabaseButton();
		
		togglePage = databasePage.clickPGNScienceDatabaseLink();
		togglePage.clickEnableAllButton();
		togglePage.waitImplicitly(2);
		togglePage.clickItemByName("Earth Science");
		user.customWait().until(ExpectedConditions.presenceOfElementLocated(By.linkText("Geosphere")));
		togglePage.clickItemByName("Geosphere");
		user.customWait().until(ExpectedConditions.presenceOfElementLocated(By.linkText("Earth Materials")));
		if(togglePage.isItemOnByName("Earth Materials")){
			togglePage.toggleItemByName("Earth Materials");
		}
		databasePage = togglePage.clickSelectDatabaseButton();
		
		togglePage = databasePage.clickPGNBiographiesDatabaseLink();
		togglePage.clickDisableAllButton();
		togglePage.waitImplicitly(2);
		databasePage = togglePage.clickSelectDatabaseButton();
		
		databasePage = togglePage.clickHomeBreadCrumb();
		
		databasePage.waitImplicitly(30);
		studentHome = (StudentHomePage) studentLogin.login(student);
	}
	
	@AfterClass
	public static void executeAfter(){
		user.quit();
		student.quit();
	}
	
	
	@Rule
    public TestWatcher restart = new TestWatcher(){
    	
		@Override
    	public void failed(Throwable e, Description description){
    		try{
    			user.getWatcher().TakeScreenshot(description.getDisplayName()+"Teacher");
    		} catch(Exception t){
    			System.out.print("There was a failure while trying to take a screenshot for the Teacher screen of " + description.getDisplayName());
    		}
    		
    		try{
    			student.getWatcher().TakeScreenshot(description.getDisplayName()+"Student");
    		} catch(Exception t){
    			System.out.print("There was a failure while trying to take a screenshot for the Student screen of " + description.getDisplayName());
    		}
    		
    		databasePage.closeCurrentWindow();
    		studentHome.closeCurrentWindow();
    		
    		user.makeNewDriver();
    		student.makeNewDriver();
    		
    		loginPage = new LoginPage(user);
    		studentLogin = new LoginPage(student);
    		
    		home = (TeacherHomeScreen) loginPage.login(user);
    		databasePage = home.clickDisableArticlesButton();
    		studentHome = (StudentHomePage) studentLogin.login(student);
    	}
    };
    
	@Rule
	public Retry retry = new Retry(3);
	
	@Test
	public void testSetUpArticlesBeforeTests(){

	}
	
	/*
	@Test
	public void testStatesDatabaseLink() throws ParseException, IOException{	
		DisableArticlesTogglePage togglePage = databasePage.clickPGNStatesDatabaseLink();
		
		ContentTreeAPI content = new ContentTreeAPI("6", user.getEnvironment());
		ArrayList<String> topLevel = content.getTopLevelModuleArticleNames();
		
		Boolean allAvailable = true;
		ArrayList<String> cantFind = new ArrayList<String>();
		
		for(int i = 0; i < topLevel.size(); i++){
			if(!togglePage.getDriver().findElement(By.linkText(topLevel.get(i).toString())).isDisplayed()){
				cantFind.add(topLevel.get(i).toString());
				allAvailable = false;
			}
		}
		
		for(int j = 0; j < cantFind.size(); j++){
			System.out.println("Can't find " + cantFind.get(j));
		}
		
		assertTrue("Tests that the Disable Articles for States link leads to the States database page", 
				allAvailable);
		
		databasePage = togglePage.clickSelectDatabaseButton();
	}
	
	@Test
	public void testScienceDatabaseLink() throws ParseException, IOException{	
		DisableArticlesTogglePage togglePage = databasePage.clickPGNScienceDatabaseLink();
		
		ContentTreeAPI content = new ContentTreeAPI("9", user.getEnvironment());
		ArrayList<String> topLevel = content.getTopLevelModuleArticleNames();
		
		Boolean allAvailable = true;
		ArrayList<String> cantFind = new ArrayList<String>();
		
		for(int i = 0; i < topLevel.size(); i++){
			if(!togglePage.getDriver().findElement(By.linkText(topLevel.get(i))).isDisplayed()){
				cantFind.add(topLevel.get(i).toString());
				allAvailable = false;
			}
		}
		
		for(int j = 0; j < cantFind.size(); j++){
			System.out.println("Can't find " + cantFind.get(j));
		}
		
		assertTrue("Tests that the Disable Articles for Science link leads to the States database page", 
				allAvailable);
		
		databasePage = togglePage.clickSelectDatabaseButton();
	}
	
	@Test
	public void testAmericanIndianHistoryDatabaseLink() throws ParseException, IOException{	
		DisableArticlesTogglePage togglePage = databasePage.clickPGNAmericanIndianHistoryDatabaseLink();
		
		ContentTreeAPI content = new ContentTreeAPI("10", user.getEnvironment());
		ArrayList<String> topLevel = content.getTopLevelModuleArticleNames();
		
		Boolean allAvailable = true;
		ArrayList<String> cantFind = new ArrayList<String>();
		
		for(int i = 0; i < topLevel.size(); i++){
			String current = topLevel.get(i);
			
			if(current.equals("American Indians Today") || current.equals("Native Hawaiians")){
				if(!togglePage.getDriver().findElement(By.xpath("//td[contains(text(),'"+ topLevel.get(i).toString() +"')]")).isDisplayed()){ //(topLevel.get(i).toString())).isDisplayed()){
					cantFind.add(topLevel.get(i).toString());
					allAvailable = false;
				}
			}else{
				if(!togglePage.getDriver().findElement(By.linkText(topLevel.get(i))).isDisplayed()){
					cantFind.add(topLevel.get(i).toString());
					allAvailable = false;
				}
			}
		}
		
		for(int j = 0; j < cantFind.size(); j++){
			System.out.println("Can't find " + cantFind.get(j));
		}
		
		assertTrue("Tests that the Disable Articles for American Indian History link leads to the States database page", 
				allAvailable);
		
		databasePage = togglePage.clickSelectDatabaseButton();
	}
	
	@Test
	public void testSocialStudiesDatabaseLink() throws ParseException, IOException{	
		DisableArticlesTogglePage togglePage = databasePage.clickPGNSocialStudiesDatabaseLink();
		
		ContentTreeAPI content = new ContentTreeAPI("11", user.getEnvironment());
		ArrayList<String> topLevel = content.getTopLevelModuleArticleNames();
		
		Boolean allAvailable = true;
		ArrayList<String> cantFind = new ArrayList<String>();
		
		for(int i = 0; i < topLevel.size(); i++){//*[@id="article_listing"]/tbody/tr[1]/td[1]/text()
			if(!togglePage.getDriver().findElement(By.linkText(topLevel.get(i).toString())).isDisplayed()){ //(topLevel.get(i).toString())).isDisplayed()){
				cantFind.add(topLevel.get(i).toString());
				allAvailable = false;
			}
		}
		
		for(int j = 0; j < cantFind.size(); j++){
			System.out.println("Can't find " + cantFind.get(j));
		}
		
		assertTrue("Tests that the Disable Articles for Social Studies link leads to the States database page", 
				allAvailable);
		
		databasePage = togglePage.clickSelectDatabaseButton();
	}
	
	@Test
	public void testBiographiesDatabaseLink() throws ParseException, IOException{	
		DisableArticlesTogglePage togglePage = databasePage.clickPGNBiographiesDatabaseLink();
		
		ContentTreeAPI content = new ContentTreeAPI("15", user.getEnvironment());
		ArrayList<String> topLevel = content.getTopLevelModuleArticleNames();
		
		Boolean allAvailable = true;
		ArrayList<String> cantFind = new ArrayList<String>();
		
		for(int i = 0; i < topLevel.size(); i++){//*[@id="article_listing"]/tbody/tr[1]/td[1]/text()
			if(!togglePage.getDriver().findElement(By.linkText(topLevel.get(i).toString())).isDisplayed()){ //(topLevel.get(i).toString())).isDisplayed()){
				cantFind.add(topLevel.get(i).toString());
				allAvailable = false;
			}
		}
		
		for(int j = 0; j < cantFind.size(); j++){
			System.out.println("Can't find " + cantFind.get(j));
		}
		
		assertTrue("Tests that the Disable Articles for Social Studies link leads to the States database page", 
				allAvailable);
		
		databasePage = togglePage.clickSelectDatabaseButton();
	}
	
	@Test
	@Category(PGNSmokeTest.class)
	public void testDatabaseOffForStudent(){
		DatabasePage database = studentHome.clickModuleByName("Biographies");
		
		database =  studentHome.clickModuleByName("Biographies");
		database.waitImplicitly(3);
		
		assertTrue("Asserts that the Social Studies database is turned on",
				database.isDatabaseInactiveMessagePresent());
		
		studentHome = database.clickHomeBreadcrumb();
	}
	*/
	
	@Test
	public void testCategoryOffForStudent(){
		DatabasePage database = studentHome.clickModuleByName("Science");
		
		student.customWait().until(ExpectedConditions.elementToBeClickable(By.linkText("Earth Science")));
		
		database = database.clickCategory("Earth Science");
		student.customWait().until(ExpectedConditions.elementToBeClickable(By.linkText("Geosphere")));
		
		database = database.clickCategory("Geosphere");
		student.customWait().until(ExpectedConditions.elementToBeClickable(By.linkText("Natural Disasters")));
		
		assertTrue("Asserts that the category is turned off for the student", !database.isContentPresent("Earth Materials"));
		studentHome = database.clickHomeBreadcrumb();
	}
	
	@Test
	public void testArticleOffForStudent(){
		DatabasePage database = studentHome.clickModuleByName("Social Studies");
		
		database = database.clickCategory("Culture");
		student.customWait().until(ExpectedConditions.elementToBeClickable(By.linkText("Community and Self")));
		
		database = database.clickCategory("Community and Self");
		student.customWait().until(ExpectedConditions.elementToBeClickable(By.linkText("About the Community")));
		
		database = database.clickCategory("About the Community");
		student.customWait().until(ExpectedConditions.elementToBeClickable(By.linkText("Social Norms and Taboos")));
		
		assertTrue("Asserts that the category is turned off for the student", !database.isContentPresent("Bullying"));
		studentHome = database.clickHomeBreadcrumb();
	}
	
	@Test
	public void testCannotNavigateToTurnedOffArticle(){
		studentHome.getDriver().get("https://"+student.getEnvironment()+".pebblegonext.com/modules/11/articles/8688");
		
		DatabasePage database = new DatabasePage(studentHome.getDriver());
		
		student.customWait().until(ExpectedConditions.visibilityOfElementLocated(database.gamesLink));
		
		assertTrue("Asserts that the user cannot directly link into an article and is on the Module Select Page",
				database.isContentPresent("Culture") &&
				database.isContentPresent("Economics") &&
				database.isContentPresent("Fields of Study") &&
				database.isContentPresent("Geography") &&
				database.isContentPresent("Technology and Society"));
		
		studentHome = database.clickHomeBreadcrumb();
	}
	
	@Test
	public void testCannotNavigateToArticleThroughRelatedArticles(){
		IndiansDatabaseHome indians = studentHome.goToIndians();
		indians.clickCategory("Subarctic Tribal Nations");
		ArticlePage article = indians.clickArticle("Cree");
		
		student.customWait().until(ExpectedConditions.elementToBeClickable(article.terminologyLink));
		
		assertTrue("Asserts that the user cannot see Montana in the related Articles for Cree", 
				!article.isElementPresent(article.relatedArticlesButton));
		
		studentHome = article.clickHomeBreadcrumb();
		
		indians = studentHome.goToIndians();
		indians.clickCategory("Great Basin Tribal Nations");
		article = indians.clickArticle("Shoshone");
		
		student.customWait().until(ExpectedConditions.elementToBeClickable(article.relatedArticlesButton));
		RelatedArticlesModal related = article.openRelatedArticles();
		
		assertTrue("Asserts that the user cannot see Montana in the related Articles for Shoshone", 
				!related.isElementPresent(By.linkText("Montana")));
		
		related.closeModal();
		student.customWait().until(ExpectedConditions.elementToBeClickable(article.relatedArticlesButton));
		studentHome = article.clickHomeBreadcrumb();
	}
	
	@Test
	public void testCannotNavigateToArticleThroughMapStates(){
		String originalURL = student.getDriver().getCurrentUrl();
		studentHome.header.openHamburgerMenu();
		
		student.customWait().until(ExpectedConditions.elementToBeClickable(studentHome.header.hamburgerMenu.StatesDiv));
		studentHome.header.hamburgerMenu.openStatesDiv().switchToMap();
		
		student.customWait().until(ExpectedConditions.elementToBeClickable(By.id("west")));
		
		studentHome.header.hamburgerMenu.clickStatesRegion("West");
		student.customWait().until(ExpectedConditions.elementToBeClickable(By.id("Montana")));
		
		studentHome.header.hamburgerMenu.clickMapItemIntoArticle("Montana");
		studentHome.waitImplicitly(5);
		
		assertTrue("asserts that the user has not navigated to the states article from the hamburger menu", 
				originalURL.equals(student.getDriver().getCurrentUrl()));
		
	}
	
	@Test
	public void testCannotNavigateToArticleThroughMapAmericanIndianHistory(){
		String originalURL = student.getDriver().getCurrentUrl();
		studentHome.header.openHamburgerMenu();
		
		student.customWait().until(ExpectedConditions.elementToBeClickable(studentHome.header.hamburgerMenu.StatesDiv));
		studentHome.header.hamburgerMenu.openAmericanIndiansDiv().switchToMap();
		
		student.customWait().until(ExpectedConditions.elementToBeClickable(By.id("Arctic")));
		
		studentHome.click(By.id("Plains"));
		student.customWait().until(ExpectedConditions.elementToBeClickable(By.id("Pawnee")));
		
		studentHome.header.hamburgerMenu.clickMapItemIntoArticle("Pawnee");
		studentHome.click(By.id("Pawnee"));
		studentHome.waitImplicitly(5);
		
		assertTrue("asserts that the user has not navigated to the states article from the hamburger menu", 
				originalURL.equals(student.getDriver().getCurrentUrl()));
		
		studentHome.header.openHamburgerMenu();
	}
	
	@Test
	public void testCannotNavigateToArticleThroughMenu(){
		studentHome.header.openHamburgerMenu();
		
		student.customWait().until(ExpectedConditions.elementToBeClickable(studentHome.header.hamburgerMenu.SocialStudiesDiv));
		studentHome.header.hamburgerMenu.openSocialStudiesDiv();
		
		student.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hamburger_list_container\"]/ul/li[contains(text(), 'Culture')]")));
		
		studentHome.header.hamburgerMenu.clickListSubItem("Culture");
		student.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hamburger_list_container\"]/ul[2]/li[contains(text(), 'Community and Self')]")));
		
		studentHome.header.hamburgerMenu.clickListSubItem("Community and Self");
		student.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hamburger_list_container\"]/ul[3]/li[contains(text(), 'About the Community')]")));
		
		studentHome.header.hamburgerMenu.clickListSubItem("About the Community");
		student.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hamburger_list_container\"]/ul[4]/li[contains(text(), 'Social Norms and Taboos')]")));
		
		assertTrue("asserts that the user has not navigated to the states article from the hamburger menu", 
				!studentHome.header.hamburgerMenu.isElementPresent(By.xpath("//*[@id=\"hamburger_list_container\"]/ul[4]/li[contains(text(), 'Bullying')]")));
		studentHome.header.openHamburgerMenu();
	}
	
	@Test
	public void testCannotNavigateToArticleThroughSearch(){
		String originalURL = student.getDriver().getCurrentUrl();
		
		studentHome.header.searchFor("Alexander Hamilton");
		
		try{
			studentHome.header.clickResult("Alexander Hamilton");
		} catch(Exception e){
			
		}
		
		assertTrue("asserts that a user cannot search into a turned off article", 
				originalURL.equals(student.getDriver().getCurrentUrl()));
	}
	
	@Test
	public void testCannotNavigateToArticlesUnderTurnedOffCategory(){
		ArrayList<String> articles = new ArrayList<String>(Arrays.asList("8495", "8496", "8497", "8498", "8499", "8500", "8501")); 
		
		Boolean cantLinkInto = true;
		
		for(String a : articles){
			studentHome.getDriver().get("https://"+student.getEnvironment()+".pebblegonext.com/modules/9/articles/"+a);
			
			DatabasePage database = new DatabasePage(studentHome.getDriver());
			
			student.customWait().until(ExpectedConditions.visibilityOfElementLocated(database.gamesLink));
			
			cantLinkInto = cantLinkInto && 
					database.isContentPresent("Earth Science") &&
					database.isContentPresent("Life Science") &&
					database.isContentPresent("Physical Science") &&
					database.isContentPresent("The Field of Science");
			
			studentHome = database.clickHomeBreadcrumb();
		}
		
		assertTrue("asserts that the user cannot link into any of the articles under the category Earth Materials", cantLinkInto);
	}
	
	@Test
	public void testCannotSearchIntoarticlesUnderTurnedOffCategory(){
		String originalURL = student.getDriver().getCurrentUrl();
		Boolean samePage = true;
		ArrayList<String> articles = new ArrayList<String>(Arrays.asList("Fossils", "Igneous Rocks", "Metamorphic Rocks", "Minerals",
				"Rock Layers and the Rock Cycle", "Sedimentary Rocks", "Soil"));
		
		for(String s : articles){
			try{
				studentHome.header.clearField(studentHome.header.searchBar);
				studentHome.header.searchFor(s);
				studentHome.header.clickResult(s);
			} catch(Exception e){
				
			}
			
			samePage = samePage && originalURL.equals(student.getDriver().getCurrentUrl());
		}

		
		assertTrue("asserts that a user cannot search into a turned off article", samePage);
	}
	
	@Test
	public void testTurnOnAllArticlesBeforeTests(){
		DisableArticlesTogglePage togglePage = databasePage.clickPGNStatesDatabaseLink();
		togglePage.clickEnableAllButton();
		togglePage.waitImplicitly(2);
		databasePage = togglePage.clickSelectDatabaseButton();
		togglePage.waitImplicitly(2);
		
		togglePage = databasePage.clickPGNAmericanIndianHistoryDatabaseLink();
		togglePage.clickEnableAllButton();
		togglePage.waitImplicitly(2);
		databasePage = togglePage.clickSelectDatabaseButton();
		togglePage.waitImplicitly(2);
		
		togglePage = databasePage.clickPGNSocialStudiesDatabaseLink();
		togglePage.waitImplicitly(2);
		togglePage.clickEnableAllButton();
		togglePage.waitImplicitly(2);
		databasePage = togglePage.clickSelectDatabaseButton();
		togglePage.waitImplicitly(2);
		
		togglePage = databasePage.clickPGNScienceDatabaseLink();
		togglePage.waitImplicitly(2);
		togglePage.clickEnableAllButton();
		togglePage.waitImplicitly(2);
		databasePage = togglePage.clickSelectDatabaseButton();
		togglePage.waitImplicitly(2);
		
		togglePage = databasePage.clickPGNBiographiesDatabaseLink();
		togglePage.waitImplicitly(2);
		togglePage.clickEnableAllButton();
		togglePage.waitImplicitly(2);
		databasePage = togglePage.clickSelectDatabaseButton();
		togglePage.waitImplicitly(2);
		
		databasePage = togglePage.clickHomeBreadCrumb();
	}
}
