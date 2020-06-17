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
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import AdminResources.DisableArticlesDatabasePage;
import AdminResources.DisableArticlesTogglePage;
import AdminResources.TeacherHomeScreen;
import PGOPages.LoginPage;
import PGOStudentResources.ArticlePage;
import PGOStudentResources.ContentSelectionPage;
import PGOStudentResources.StudentHomePage;
import SharedClasses.ContentTreeAPI;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.User;
import UserClasses.UserInfo;

public class PGONewDisableArticlesTests{
	static LoginPage loginPage;
	static LoginPage studentLogin;
	static User user;
	static User student; 	
	static TeacherHomeScreen home;
	static StudentHomePage studentHome;
	static DisableArticlesDatabasePage databasePage;
	
	
	@BeforeClass
	public static void executeBefore(){
		user = new User(UserInfo.DISABLETEACHER);
		student = new User(UserInfo.DISABLESTUDENT);

		try{
			loginPage = new LoginPage(user);
			user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.loginModalButton));
			loginPage.waitImplicitly(2);
			
			home = (TeacherHomeScreen) loginPage.Login(user);
		
			databasePage = home.clickDisableArticlesButton();
			
			DisableArticlesTogglePage togglePage = databasePage.clickAnimalsDatabaseLink();
			togglePage.disableAll();
			togglePage.waitImplicitly(2);
			togglePage.clickItemByName("Amphibians");
			togglePage.disableAll();
			togglePage.waitImplicitly(2);
			togglePage.toggleItemByName("Toads");
			togglePage.waitImplicitly(2);
			databasePage = togglePage.clickHomeBreadCrumb();
			
			togglePage = databasePage.clickAnimalesDatabaseLink();
			togglePage.disableAll();
			togglePage.waitImplicitly(2);
			togglePage.clickItemByName("Anfibios");
			togglePage.disableAll();
			togglePage.waitImplicitly(2);
			togglePage.toggleItemByName("Sapos");
			togglePage.waitImplicitly(2);
			togglePage.toggleItemByName("Necturos");
			togglePage.waitImplicitly(2);
			databasePage = togglePage.clickHomeBreadCrumb();
			
			togglePage = databasePage.clickScienceDatabaseLink();
			togglePage.clickEnableAllButton();
			togglePage.waitImplicitly(2);
			databasePage = togglePage.clickSelectDatabaseButton();
			
			togglePage = databasePage.clickBiographiesDatabaseLink();
			togglePage.clickEnableAllButton();
			togglePage.waitImplicitly(1);
			togglePage.clickDatabaseBreadcrumb();
			togglePage.toggleItemByName("Athletes");
			togglePage.waitImplicitly(1);
			togglePage.clickItemByName("Athletes");
			
			//Toggle Jack Robinson on and off to turn on the Category but not all other articles.
			togglePage.toggleItemByName("Jackie Robinson");
			togglePage.waitImplicitly(1);
			togglePage.toggleItemByName("Jackie Robinson");
			togglePage.waitImplicitly(1);
			togglePage.clickDatabaseBreadcrumb();
			databasePage = togglePage.clickSelectDatabaseButton();
			
			togglePage = databasePage.clickScienceDatabaseLink();
			togglePage.clickEnableAllButton();
			togglePage.waitImplicitly(2);
			databasePage = togglePage.clickSelectDatabaseButton();
			
			togglePage = databasePage.clickDinosaursDatabaseLink();
			togglePage.clickEnableAllButton();
			togglePage.waitImplicitly(2);
			togglePage.clickItemByName("Beaked Dinosaurs");
			togglePage.waitImplicitly(2);
			togglePage.toggleItemByName("Bone-Heads");
			togglePage.waitImplicitly(2);
			databasePage = togglePage.clickSelectDatabaseButton();
			
			togglePage = databasePage.clickCienciaDatabaseLink();
			togglePage.clickDisableAllButton();
			togglePage.waitImplicitly(2);
			databasePage = togglePage.clickSelectDatabaseButton();
			
			studentLogin = new LoginPage(student);
			studentHome = (StudentHomePage) studentLogin.Login(student);
			
		} catch (Exception e){
			System.out.println("Failure in setting up Disable Articles Tests");
			user.getWatcher().TakeScreenshot("failureToRunExecuteBefore");
		}
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
    			user.getWatcher().TakeScreenshot(description.getDisplayName() + "Teacher");
    		} catch(Exception t){
    			System.out.print("There was a failure while trying to take a teacher screenshot for " + description.getDisplayName());
    		}
    		
    		try {
    			student.getWatcher().TakeScreenshot(description.getDisplayName() + "Student");
    		} catch(Exception t){
    			System.out.print("There was a failure while trying to take a student screenshot for " + description.getDisplayName());
    		}
       		
    		loginPage.closeCurrentWindow();
    		studentLogin.closeCurrentWindow();
    		
    		user.makeNewDriver();
    		student.makeNewDriver();
    		
    		loginPage = new LoginPage(user);
			user.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.loginModalButton));
			loginPage.waitImplicitly(2);
			
    		studentLogin = new LoginPage(student);
			student.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.loginModalButton));
			loginPage.waitImplicitly(2);
			
    		TeacherHomeScreen home = (TeacherHomeScreen) loginPage.Login(user);
    		databasePage = home.clickDisableArticlesButton();
    		
			studentHome = (StudentHomePage) studentLogin.Login(student);
    	}
    };
    
	@Rule
	public Retry retry = new Retry(3);
	
	
	@Test
	public void testAnimalsDatabaseLink() throws ParseException, IOException{
		DisableArticlesTogglePage togglePage = databasePage.clickAnimalsDatabaseLink();
		
		ContentTreeAPI content = new ContentTreeAPI("1", user.getEnvironment());
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
		
		assertTrue("Tests that the Disable Articles for Animals link leads to the animals database page", 
				allAvailable);
		
		databasePage = togglePage.clickSelectDatabaseButton();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testScienceDatabaseLink() throws ParseException, IOException{
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		
		ContentTreeAPI content = new ContentTreeAPI("2", user.getEnvironment());
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
		
		assertTrue("Tests that the Disable Articles for Science link leads to the Science database page", 
				allAvailable);
		
		databasePage = togglePage.clickSelectDatabaseButton();
	}
	
	@Test
	public void testBiographiesDatabaseLink() throws ParseException, IOException{
		DisableArticlesTogglePage togglePage = databasePage.clickBiographiesDatabaseLink();
		
		ContentTreeAPI content = new ContentTreeAPI("3", user.getEnvironment());
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
		
		assertTrue("Tests that the Disable Articles for Biographies link leads to the Biographies database page", 
				allAvailable);
		
		databasePage = togglePage.clickSelectDatabaseButton();
	}
	
	@Test
	public void testSocialStudiesDatabaseLink() throws ParseException, IOException{
		DisableArticlesTogglePage togglePage = databasePage.clickSocialStudiesDatabaseLink();
		
		ContentTreeAPI content = new ContentTreeAPI("5", user.getEnvironment());
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
		assertTrue("Tests that the Disable Articles for Social Studies link leads to the Social Studies database page", 
				allAvailable);
		
		databasePage = togglePage.clickSelectDatabaseButton();
	}
	
	@Test
	public void testDinosaursDatabaseLink() throws ParseException, IOException{
		DisableArticlesTogglePage togglePage = databasePage.clickDinosaursDatabaseLink();
		
		ContentTreeAPI content = new ContentTreeAPI("7", user.getEnvironment());
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
		
		assertTrue("Tests that the Disable Articles for Dinosaurs link leads to the Dinosaurs database page", 
				allAvailable);
		
		databasePage = togglePage.clickSelectDatabaseButton();
	}
	
	@Test
	public void testAnimalesDatabaseLink() throws ParseException, IOException{	
		DisableArticlesTogglePage togglePage = databasePage.clickAnimalesDatabaseLink();
		
		ContentTreeAPI content = new ContentTreeAPI("8", user.getEnvironment());
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
		
		assertTrue("Tests that the Disable Articles for Animales link leads to the Animales database page", 
				allAvailable);
		
		databasePage = togglePage.clickSelectDatabaseButton();
	}
	
	/**
	 * PGO-171 Toggle: Select Database button broken
	 */
	@Test
	@Category(SmokeTests.class)
	public void testSelectDatabaseButtonOnDatabasePage(){
		databasePage = databasePage.clickSelectDatabasePage();
		assertTrue("Tests that the Select Database button does not break when being clicked from the Select Database page",
				databasePage.verifyElementsVisibility(databasePage.getInitialVisibleElements()));
}
	
	/**
	 * PGO-171 Toggle: Select Database button broken
	 */
	@Test
	@Category(SmokeTests.class)
	public void testSelectDatabaseButtonOnFirstLevel(){		
		DisableArticlesTogglePage togglePage = databasePage.clickAnimalsDatabaseLink();

		assertTrue("Asserts that the Select Database Button is present on the first level of the Module",
				togglePage.isSelectDatabaseButtonPresent());
		
		databasePage = togglePage.clickSelectDatabaseButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(databasePage.pgnScienceDatabaseLink));
		assertTrue("Tests that the Select Database page brings a user back to the Select Database page from the Animals database page",
				databasePage.verifyElementsVisibility(databasePage.getInitialVisibleElements()));
	}
	
	/**
	 * PGO-171 Toggle: Select Database button broken
	 */
	@Test
	public void testSelectDatabaseButtonOnSecondLevel(){		
		DisableArticlesTogglePage togglePage = databasePage.clickAnimalsDatabaseLink();
		togglePage.clickItemByName("Pets and Farm Animals");
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='breadcrumb']/li[contains(@class, 'current_crumb')]/a/img")));
		
		assertTrue("Asserts that the Select Database Button is present on the second level of the Module",
				togglePage.isSelectDatabaseButtonPresent());
		
		databasePage = togglePage.clickSelectDatabaseButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(databasePage.pgnScienceDatabaseLink));
		assertTrue("Asserts that the Select Database page brings the user back to the Select Database page from the second level of the Animals database page",
				databasePage.verifyElementsVisibility(databasePage.getInitialVisibleElements()));
	}
	
	/**
	 * PGO-171 Toggle: Select Database button broken
	 */
	@Test
	public void testSelectDatabaseButtonOnThirdLevel(){
		DisableArticlesTogglePage togglePage = databasePage.clickAnimalsDatabaseLink();
		togglePage.clickItemByName("Pets and Farm Animals");
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='breadcrumb']/li[contains(@class, 'current_crumb')]/a/img")));
		togglePage.clickItemByName("Pets");
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='breadcrumb']/li[contains(@class, 'current_crumb')]/a/img")));
		
		assertTrue("Asserts that the Select Database Button is present on the third level of the Module",
				togglePage.isSelectDatabaseButtonPresent());
		
		databasePage = togglePage.clickSelectDatabaseButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(databasePage.pgnScienceDatabaseLink));
		assertTrue("Asserts a user is brought from the a third level Animal Database page to the Select Database Page using the Select Database button",
				databasePage.verifyElementsVisibility(databasePage.getInitialVisibleElements()));	
	}
	
	/**
	 * PGO-171 Toggle: Select Database button broken
	 */
	@Test
	@Category(SmokeTests.class)
	public void testSelectDatabaseButtonOnFourthLevel(){
		DisableArticlesTogglePage togglePage = databasePage.clickAnimalsDatabaseLink();
		togglePage.clickItemByName("Pets and Farm Animals");
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='breadcrumb']/li[contains(@class, 'current_crumb')]/a/img")));
		togglePage.clickItemByName("Pets");
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='breadcrumb']/li[contains(@class, 'current_crumb')]/a/img")));
		togglePage.clickItemByName("Dogs");
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='breadcrumb']/li[contains(@class, 'current_crumb')]/a/img")));
		
		assertTrue("Asserts that the Select Database Button is present on the fourth level of the Module",
				togglePage.isSelectDatabaseButtonPresent());

		databasePage = togglePage.clickSelectDatabaseButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(databasePage.pgnScienceDatabaseLink));
		assertTrue("Asserts a user is brought from a fourth level Animal Database page to the Select Database Page using the Select Database button",
				databasePage.verifyElementsVisibility(databasePage.getInitialVisibleElements()));
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testDatabaseBreadcrumbAnimals(){
		DisableArticlesTogglePage togglePage = databasePage.clickAnimalsDatabaseLink();
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='db-crumb']/a")));
		
		assertTrue("Asserts that the Home Breadcrumb is present in the Animals Database",
				togglePage.isHomeBreadCrumbPresent());
		
		databasePage = togglePage.clickHomeBreadCrumb();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(databasePage.pgnScienceDatabaseLink));
		assertTrue("Asserts the Home Breadcrumb takes the user from the Animals Database to the Select Database page",
				databasePage.verifyElementsVisibility(databasePage.getInitialVisibleElements()));
	}
	
	@Test
	public void testDatabaseBreadcrumbScience(){
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='db-crumb']/a")));
		
		assertTrue("Asserts that the Home Breadcrumb is present in the Science Database",
				togglePage.isHomeBreadCrumbPresent());
		databasePage = togglePage.clickHomeBreadCrumb();
		assertTrue("Asserts that the Home Breadcrumb takes the user from the Science Database to the Select Database page",
				databasePage.verifyElementsVisibility(databasePage.getInitialVisibleElements()));
	}
	
	@Test
	public void testDatabaseBreadcrumbBiographies(){
		DisableArticlesTogglePage togglePage = databasePage.clickBiographiesDatabaseLink();
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='db-crumb']/a")));
		
		assertTrue("Asserts that the Home Breadcrumb is present in the Biographies Database",
				togglePage.isHomeBreadCrumbPresent());
		
		databasePage = togglePage.clickHomeBreadCrumb();
		assertTrue("Asserts that the Home Breadcrumb takes the user from the Biographies Database to the Select Database page",
				databasePage.verifyElementsVisibility(databasePage.getInitialVisibleElements()));
	}
	
	@Test
	public void testDatabaseBreadcrumbSocialStudies(){
		DisableArticlesTogglePage togglePage = databasePage.clickSocialStudiesDatabaseLink();
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='db-crumb']/a")));
		
		assertTrue("Asserts that the Home Breadcrumb is present in the Social Studies Database",
				togglePage.isHomeBreadCrumbPresent());
		databasePage = togglePage.clickHomeBreadCrumb();
		assertTrue("Asserts that the Home Breadcrumb takes the user from the Social Studies Database to the Select Database page",
				databasePage.verifyElementsVisibility(databasePage.getInitialVisibleElements()));
	}
	
	@Test
	public void testDatabaseBreadcrumbDinosaurs(){
		DisableArticlesTogglePage togglePage = databasePage.clickDinosaursDatabaseLink();
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='db-crumb']/a")));
		
		assertTrue("Asserts that the Home Breadcrumb is present in the Dinosaurs Database",
				togglePage.isHomeBreadCrumbPresent());
		databasePage = togglePage.clickHomeBreadCrumb();
		assertTrue("Asserts that the Home Breadcrumb takes the user from the Dinosaurs Database to the Select Database page",databasePage.verifyElementsVisibility(databasePage.getInitialVisibleElements()));
	}
	
	@Test
	public void testDatabaseBreadcrumbAnimales(){
		DisableArticlesTogglePage togglePage = databasePage.clickAnimalesDatabaseLink();
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='db-crumb']/a")));
		
		assertTrue("Asserts that the Home Breadcrumb is present in the Animales Database",
				togglePage.isHomeBreadCrumbPresent());
		databasePage = togglePage.clickHomeBreadCrumb();
		assertTrue("Asserts that the Home Breadcrumb takes the user from the Animales Database to the Select Database page",
				databasePage.verifyElementsVisibility(databasePage.getInitialVisibleElements()));
	}
	
	@Test
	public void testHomeBreadcrumbSecondLevel(){
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		togglePage.clickItemByName("Life Sciences");
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='breadcrumb']/li[contains(@class, 'current_crumb')]/a/img")));
		
		assertTrue("Asserts that the Home Breadcrumb is present on the second level of a Database",
				togglePage.isHomeBreadCrumbPresent());
		
		databasePage = togglePage.clickHomeBreadCrumb();
		assertTrue("Asserts that the Home Breadcrumb takes the user from the second level of the Science Database to the Select Database Page",
				databasePage.verifyElementsVisibility(databasePage.getInitialVisibleElements()));
		//loginPage = togglePage.clickLogoutButton();
	}
	
	@Test
	public void testHomeBreadcrumbThirdLevel(){
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		togglePage.clickItemByName("Life Sciences");
		togglePage.clickItemByName("Humans");
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='breadcrumb']/li[contains(@class, 'current_crumb')]/a/img")));
		
		assertTrue("Asserts that the Home Breadcrumb is present on the third level of a Database",
				togglePage.isHomeBreadCrumbPresent());
		
		databasePage = togglePage.clickHomeBreadCrumb();
		assertTrue("Asserts that the Home Breadcrumb takes the user from the third level of the Science Database to the Select Database Page",
				databasePage.verifyElementsVisibility(databasePage.getInitialVisibleElements()));
	}
	
	@Test
	public void testHomeBreadcrumbFourthLevel(){
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		togglePage.clickItemByName("Life Sciences");
		togglePage.clickItemByName("Humans");
		togglePage.clickItemByName("Body Systems");
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='breadcrumb']/li[contains(@class, 'current_crumb')]/a/img")));
		
		assertTrue("Asserts that the Home Breadcrumb is present on the fourth level of a Database",
				togglePage.isHomeBreadCrumbPresent());
		
		databasePage = togglePage.clickHomeBreadCrumb();
		assertTrue("Asserts that the Home Breadcrumb takes the user from the fourth level of the Science Database to the Select Database Page",
				databasePage.verifyElementsVisibility(databasePage.getInitialVisibleElements()));
	}
	
	@Test
	public void testPreviousBreadcrumbSecondLevel(){
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		togglePage.clickItemByName("Life Sciences");
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='breadcrumb']/li[contains(@class, 'current_crumb')]/a/img")));
		
		assertTrue("Asserts that the first database breadcrumb is set to Life Sciences after the category is clicked", 
				togglePage.verifyBreadcrumb(1, "lifesciences"));
		databasePage = togglePage.clickHomeBreadCrumb();
	}
	
	@Test
	public void testPreviousBreadcrumbThirdLevel(){
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		togglePage.clickItemByName("Life Sciences");
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='breadcrumb']/li[contains(@class, 'current_crumb')]/a/img")));
		togglePage.clickItemByName("Humans");
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='breadcrumb']/li[contains(@class, 'current_crumb')]/a/img")));
		assertTrue("Asserts that the second database breadcrumb is set to Humans after the category is clicked",
				togglePage.verifyBreadcrumb(2, "humans"));
		databasePage = togglePage.clickHomeBreadCrumb();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testPreviousBreadcrumbFourthLevel(){
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		togglePage.clickItemByName("Life Sciences");
		togglePage.clickItemByName("Humans");
		togglePage.clickItemByName("Body Systems");
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='breadcrumb']/li[contains(@class, 'current_crumb')]/a/img")));
		assertTrue("Asserts that the third database breadcrumb is set to Body Systems after the category is clicked",
				togglePage.verifyBreadcrumb(3, "bodysystems"));		
		databasePage = togglePage.clickHomeBreadCrumb();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testDisableAll(){
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.id("disable-all")));
		togglePage.clickDisableAllButton();

		try{
		user.customWait().until(ExpectedConditions.attributeContains(togglePage.getLastToggleDiv(), "class", "ui-flipswitch-active"));
		}
		catch(Exception e){
			System.out.println("Element was already disabled");
		}
		togglePage.waitImplicitly(2);
		
		togglePage.refresh();
		user.customWait().until(ExpectedConditions.visibilityOf(togglePage.getLastToggleDiv()));
		
		assertTrue("Asserts that all Life Science categories are turned off after the Disable All button is clicked on the Main Science Database Page"
				,togglePage.areAllItemsOff());
		togglePage.clickEnableAllButton();
		user.customWait().until(ExpectedConditions.attributeToBe(togglePage.getLastToggleDiv(), "class", "ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all"));
		togglePage.waitImplicitly(2);
		
		togglePage.refresh();
		assertTrue("Asserts that all Science categories are turned on after the Enable All button is clicked",
				togglePage.areAllItemsOn());
		
		togglePage.clickDisableAllButton();
		user.customWait().until(ExpectedConditions.attributeContains(togglePage.getLastToggleDiv(), "class", "ui-flipswitch-active"));
		togglePage.waitImplicitly(2);
		
		togglePage.refresh();
		user.customWait().until(ExpectedConditions.visibilityOf(togglePage.getLastToggleDiv()));
		
		assertTrue("Asserts that all Science categories are turned off after the Enable All button is clicked",
				togglePage.areAllItemsOff());
		databasePage = togglePage.clickHomeBreadCrumb();
	}
	
	@Test
	public void testEnableAllSecondLevel(){
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		togglePage.clickDisableAllButton();
		user.customWait().until(ExpectedConditions.attributeContains(togglePage.getLastToggleDiv(), "class", "ui-flipswitch-active"));
		togglePage.waitImplicitly(2);
		togglePage.toggleItemByName("Life Sciences");
		togglePage.clickDatabaseBreadcrumb();
		togglePage.clickItemByName("Life Sciences");
		
		assertTrue("Asserts that all subcategories of a category are enabled when the category is toggle on", togglePage.areAllItemsOn());
		databasePage = togglePage.clickSelectDatabaseButton();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testDisableAllSecondLevel(){		
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		togglePage.clickDisableAllButton();
		togglePage.clickItemByName("Life Sciences");
		assertTrue("Asserts that all items are disabled for Life Sciences when the Science database Disable All button is clicked",
				togglePage.areAllItemsOff());
		databasePage = togglePage.clickSelectDatabaseButton();
	}
	
	@Test
	public void testDisableAllThirdLevel(){
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		togglePage.clickEnableAllButton();
		user.customWait().until(ExpectedConditions.attributeToBe(togglePage.getLastToggleDiv(), "class", 
				"ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all"));
		togglePage.waitImplicitly(1);
		togglePage.clickDisableAllButton();
		user.customWait().until(ExpectedConditions.attributeContains(togglePage.getLastToggleDiv(), "class", "ui-flipswitch-active"));
		togglePage.waitImplicitly(1);
		
		togglePage.clickItemByName("Life Sciences");
		togglePage.clickItemByName("Plants");
		assertTrue("Asserts that all items in subcategories are disabled when the the main database Disable All button is clicked",
				togglePage.areAllItemsOff());
		togglePage.clickBreadcrumb(1);
		
		togglePage.clickItemByName("Animals");
		assertTrue("Asserts that all items in subcategories are disabled when the the main database Disable All button is clicked",
				togglePage.areAllItemsOff());
		togglePage.clickBreadcrumb(1);
		
		togglePage.clickItemByName("Humans");
		assertTrue("Asserts that all items in subcategories are disabled when the the main database Disable All button is clicked",
				togglePage.areAllItemsOff());
		togglePage.clickBreadcrumb(1);
		databasePage = togglePage.clickHomeBreadCrumb();
	}
	
	@Test
	public void testDisableAllFourthLevel(){
		String details = "Asserts that all items in subcategories are disabled at the fourth level when the main Disable All button is clicked";
		
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		togglePage.clickEnableAllButton();
		user.customWait().until(ExpectedConditions.attributeToBe(togglePage.getLastToggleDiv(), "class", 
				"ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all"));
		togglePage.waitImplicitly(1);
		togglePage.clickDisableAllButton();
		user.customWait().until(ExpectedConditions.attributeContains(togglePage.getLastToggleDiv(), "class", "ui-flipswitch-active"));
		togglePage.waitImplicitly(1);
		togglePage.clickItemByName("Life Sciences");
		togglePage.clickItemByName("Plants");
		togglePage.clickItemByName("Plant Classification");
		assertTrue(details,togglePage.areAllItemsOff());
		togglePage.clickBreadcrumb(2);
		
		togglePage.clickItemByName("Plant Parts");
		assertTrue(details, togglePage.areAllItemsOff());
		togglePage.clickBreadcrumb(2);
		
		togglePage.clickItemByName("Plant Habitats");
		assertTrue(details, togglePage.areAllItemsOff());
		togglePage.clickBreadcrumb(1);
		
		togglePage.clickItemByName("Animals");
		togglePage.clickItemByName("Animal Classifications");
		assertTrue(details, togglePage.areAllItemsOff());
		togglePage.clickBreadcrumb(2);
		
		togglePage.clickItemByName("Animal Behavior");
		assertTrue(details,	togglePage.areAllItemsOff());
		togglePage.clickBreadcrumb(2);
		
		togglePage.clickItemByName("Animal Habitats");
		assertTrue(details,	togglePage.areAllItemsOff());
		togglePage.clickBreadcrumb(1);
		
		togglePage.clickItemByName("Humans");
		togglePage.clickItemByName("Body Systems");
		assertTrue(details,	togglePage.areAllItemsOff());
		togglePage.clickBreadcrumb(2);
		
		togglePage.clickItemByName("Senses");
		assertTrue(details, togglePage.areAllItemsOff());
		togglePage.clickBreadcrumb(1);	
		databasePage = togglePage.clickHomeBreadCrumb();
	}
	
	/**
	 * PGO-169 'On' functionality bubbling up
	 */
	@Test
	public void testBubbleUpFunctionalitySecondLevel(){
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		togglePage.clickEnableAllButton();
		user.customWait().until(ExpectedConditions.attributeToBe(togglePage.getLastToggleDiv(), "class", 
				"ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all"));
		togglePage.waitImplicitly(1);
		togglePage.clickDisableAllButton();
		user.customWait().until(ExpectedConditions.attributeContains(togglePage.getLastToggleDiv(), "class", "ui-flipswitch-active"));
		togglePage.waitImplicitly(1);
		togglePage.clickItemByName("Life Sciences");
		togglePage.toggleItemByName("Living or Nonliving");
		togglePage.clickDatabaseBreadcrumb();
		
		assertTrue("Assert that turning on the Living or Nonliving subcategory only enables the Life Science category",
				togglePage.isItemOnByName("Life Sciences") && !togglePage.isItemOnByName("Earth and Space Sciences")
				&& !togglePage.isItemOnByName("Physical Sciences") && !togglePage.isItemOnByName("Science and Engineering Practices"));
		togglePage.clickItemByName("Life Sciences");
		
		assertTrue("Asserts that the only subcategory of Life Sciences that is enabled is Living or Nonliving",
				!togglePage.areAllItemsOff() && !togglePage.areAllItemsOn() && togglePage.isItemOnByName("Living or Nonliving"));
		
		databasePage = togglePage.clickHomeBreadCrumb();
	}
	
	/**
	 * PGO-169 'On' functionality bubbling up
	 */
	@Test
	public void testBubbleUpFunctionalityThirdLevel(){
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		togglePage.clickEnableAllButton();
		user.customWait().until(ExpectedConditions.attributeToBe(togglePage.getLastToggleDiv(), "class", 
				"ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all"));
		togglePage.waitImplicitly(1);
		togglePage.clickDisableAllButton();
		user.customWait().until(ExpectedConditions.attributeContains(togglePage.getLastToggleDiv(), "class", "ui-flipswitch-active"));
		togglePage.waitImplicitly(1);
		togglePage.clickItemByName("Earth and Space Sciences");
		togglePage.clickItemByName("Space Science");
		togglePage.toggleItemByName("Exploring Space");
		togglePage.waitImplicitly(1);
		togglePage.clickItemByName("Exploring Space");
		assertTrue("Assert that all subitems for Exploring Space are enabled when Exploring Space is enabled",togglePage.areAllItemsOn());
		togglePage.clickBreadcrumb(2);
		
		assertTrue("Assert that the category Exploring Space is enabled when subitem is enabled",togglePage.isItemOnByName("Exploring Space"));
		togglePage.clickBreadcrumb(1);
		
		assertTrue("Assert that the category Space Science is enabled when subitem is enabled", togglePage.isItemOnByName("Space Science"));
		togglePage.clickDatabaseBreadcrumb();
		
		assertTrue("Assert that the category Earth and Space Science is enabled when subitem is enabled", togglePage.isItemOnByName("Earth and Space Sciences"));
		togglePage.clickEnableAllButton();
		user.customWait().until(ExpectedConditions.attributeToBe(togglePage.getLastToggleDiv(), "class", 
				"ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all"));
		togglePage.waitImplicitly(1);
	
		databasePage = togglePage.clickHomeBreadCrumb();
	}
	
	/**
	 * PGO-169 'On' functionality bubbling up
	 */
	@Test
	public void testBubbleUpFunctionalityFourthLevel(){
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		togglePage.clickEnableAllButton();
		user.customWait().until(ExpectedConditions.attributeToBe(togglePage.getLastToggleDiv(), "class", 
				"ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all"));
		togglePage.waitImplicitly(1);
		togglePage.clickDisableAllButton();
		user.customWait().until(ExpectedConditions.attributeContains(togglePage.getLastToggleDiv(), "class", "ui-flipswitch-active"));
		togglePage.waitImplicitly(1);
		togglePage.clickItemByName("Life Sciences");
		togglePage.clickItemByName("Plants");
		togglePage.clickItemByName("Plant Classification");
		
		assertTrue("Asserts that the item Conifers is disabled to start the test", !togglePage.isItemOnByName("Conifers"));
		togglePage.toggleItemByName("Conifers");
		togglePage.waitImplicitly(1);
		togglePage.clickBreadcrumb(3);
		
		assertTrue("Asserts that the item Conifers is toggled on",togglePage.isItemOnByName("Conifers"));
		togglePage.clickBreadcrumb(2);
		
		assertTrue("Asserts that enabling Conifers enables the Plant Classification category", togglePage.isItemOnByName("Plant Classification"));
		togglePage.clickBreadcrumb(1);
		
		assertTrue("Asserts that enabling Conifers enables the Plants category", togglePage.isItemOnByName("Plants"));
		togglePage.clickDatabaseBreadcrumb();
		
		assertTrue("Asserts that enabling Conifers enables the Life Sciences category", togglePage.isItemOnByName("Life Sciences"));
		
		databasePage = togglePage.clickHomeBreadCrumb();
	}
	
	/**
	 * PGO-169 'On' functionality bubbling up
	 */
	@Test
	public void testCategoryStaysOnBubbleUp(){
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		togglePage.clickEnableAllButton();
		user.customWait().until(ExpectedConditions.attributeToBe(togglePage.getLastToggleDiv(), "class", 
				"ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all"));
		togglePage.waitImplicitly(1);
		togglePage.clickDisableAllButton();
		user.customWait().until(ExpectedConditions.attributeContains(togglePage.getLastToggleDiv(), "class", "ui-flipswitch-active"));
		togglePage.waitImplicitly(1);
		togglePage.clickItemByName("Life Sciences");
		togglePage.clickItemByName("Plants");
		togglePage.clickItemByName("Plant Classification");
		
		assertTrue("Asserts that Conifers is initially disabled to start the test so that it can be enabled and then disabled again",
				!togglePage.isItemOnByName("Conifers"));
		togglePage.toggleItemByName("Conifers");
		togglePage.waitImplicitly(1);
		togglePage.toggleItemByName("Conifers");
		togglePage.clickDatabaseBreadcrumb();
		
		assertTrue("Asserts that the Life Science category is enabled after Conifers is enabled", togglePage.isItemOnByName("Life Sciences"));
		togglePage.clickItemByName("Life Sciences");
		
		assertTrue("Asserts that the Plants category is enabled after Conifers is enabled", togglePage.isItemOnByName("Plants"));
		
		togglePage.clickItemByName("Plants");
		assertTrue("Asserts that the Plant Classification category is enabled after Conifers is enabled", togglePage.isItemOnByName("Plant Classification"));
		
		togglePage.clickItemByName("Plant Classification");
		assertTrue("Asserts that Conifers is still disabled", togglePage.areAllItemsOff());
		
		databasePage = togglePage.clickHomeBreadCrumb();
	}
	
	/**
	 * PGO-165 Toggle: Database category On/Off problem.
	 */
	@Test
	public void testCategoryStaysOn(){
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		togglePage.clickDisableAllButton();
		user.customWait().until(ExpectedConditions.attributeContains(togglePage.getLastToggleDiv(), "class", "ui-flipswitch-active"));
		togglePage.waitImplicitly(1);
		togglePage.clickItemByName("Life Sciences");
		togglePage.clickItemByName("Plants");
		
		togglePage.toggleItemByName("Plant Classification");
		togglePage.clickItemByName("Plant Classification");
		
		assertTrue("Asserts that all items under the Plant Classification category are enabled", togglePage.areAllItemsOn());
		
		togglePage.toggleItemByName("Conifers");
		togglePage.clickBreadcrumb(2);
		assertTrue("Asserts that the Plant Classification category is enabled even though Conifers is disabled",togglePage.isItemOnByName("Plant Classification"));

		databasePage = togglePage.clickHomeBreadCrumb();
	}
	
	/**
	 * PGO-162 Can't turn articles back on.
	 */
	@Test
	public void testArticleStaysOn(){
		DisableArticlesTogglePage togglePage = databasePage.clickScienceDatabaseLink();
		togglePage.clickEnableAllButton();
		user.customWait().until(ExpectedConditions.attributeToBe(togglePage.getLastToggleDiv(), "class", 
				"ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all"));
		togglePage.waitImplicitly(1);
		togglePage.clickDisableAllButton();
		user.customWait().until(ExpectedConditions.attributeContains(togglePage.getLastToggleDiv(), "class", "ui-flipswitch-active"));
		togglePage.waitImplicitly(1);
		
		togglePage.clickItemByName("Life Sciences");
		togglePage.clickItemByName("Plants");
		togglePage.clickItemByName("Plant Classification");
		togglePage.toggleItemByName("Conifers");
		
		togglePage.clickBreadcrumb(2);
		
		assertTrue("Asserts that Plant Classification is enabled when Conifers is enabled", togglePage.isItemOnByName("Plant Classification"));
		
		togglePage.clickItemByName("Plant Classification");
		
		assertTrue("Asserts that Conifers is enabled after navigating away from item select screen", togglePage.isItemOnByName("Conifers"));
		togglePage.toggleItemByName("Conifers");
		togglePage.clickBreadcrumb(2);
		
		assertTrue("Asserts that Plant Classification stays enabled after Conifers is disabled and that disabling a sub-item does not disable its group", 
				togglePage.isItemOnByName("Plant Classification"));
		
		togglePage.clickItemByName("Plant Classification");
		
		assertTrue("Asserts that Conifers remains disable after navigating away from the item select screen", !togglePage.isItemOnByName("Conifers"));
		databasePage = togglePage.clickHomeBreadCrumb();
	}
	
	/**
	 * PGO-160 Toggle: All modules disabled, database breaks on student side
	 */
	@Test
	public void testDatabaseOffForStudent(){
		ContentSelectionPage content = studentHome.clickCienciaModule();
		content.waitImplicitly(1);
		assertTrue("Asserts that all Science content is blocked from students viewing it", content.isAllContentBanned());
		
		studentHome = content.clickHomeBreadcrumb();
	}
	
	@Test
	public void testCategoryOffForStudent(){		
		ContentSelectionPage content = studentHome.clickBiographiesModule().clickCategoryByText("Athletes");

		
		assertTrue("Asserts that all items in a category are disabled for a student", content.isAllContentBanned());
		studentHome = content.clickHomeBreadcrumb();
	}
	
	@Test
	public void testCannotSearchIntoArticlesUnderTurnedOffCategory(){
		String originalURL = student.getDriver().getCurrentUrl();
		Boolean samePage = true;
		ArrayList<String> articles = new ArrayList<String>(Arrays.asList("Dracorex", "Micropachycephalosaurus", "Pachycephalosaurus", "Stygimoloch"));
	
		for(String s : articles){
			try{
				studentHome.clearField(studentHome.searchTextField);
				studentHome.searchForArticle(s);
			} catch(Exception e){
				
			}
			
			samePage = samePage && originalURL.equals(student.getDriver().getCurrentUrl());
		}

		
		assertTrue("asserts that a user cannot search into a turned off article", samePage);
	}
	
	@Test
	public void testCannotHardLinkIntoArticlesUnderTurnedOffCategory(){
		Boolean cantLinkInto = true;
		ArrayList<String> articles = new ArrayList<String>(Arrays.asList("7025", "7026", "7027", "7028"));
		ContentSelectionPage content = new ContentSelectionPage(studentHome.getDriver());
		
		for(String s : articles){
			try{
				studentHome.getDriver().get("https://"+student.getEnvironment()+".pebblego.com/modules/7/articles/"+s);
				content = new ContentSelectionPage(studentHome.getDriver());
				
				student.customWait().until(ExpectedConditions.visibilityOfElementLocated(content.gamesLink));
				
				cantLinkInto = cantLinkInto && 
						content.isContentPresent("Beaked Dinosaurs") &&
						content.isContentPresent("Bird-Like Dinosaurs") &&
						content.isContentPresent("Long-Necks");
			} catch(Exception e){
				
			}
			

		}
		assertTrue("asserts that a user cannot search into a turned off article", cantLinkInto);
		studentHome = content.clickHomeBreadcrumb();
	}
	
	/**
	 * PGO-174 Disable Articles: Correct Language
	 * "Content not available. Please contact your teacher, librarian, or administrator."
	 */
	@Test
	public void testArticleOffForStudent(){
		ContentSelectionPage content = studentHome.clickAnimalsModule().clickCategoryByText("Amphibians");
		
		assertTrue("Assert that an article in a category is disabled for a student", !content.isArticlePresentByName("Frogs"));
		studentHome = content.clickHomeBreadcrumb();
	}
	
	@Test
	public void testCannotNavigateToTurnedOffArticle(){
		ContentSelectionPage content = studentHome.clickAnimalesModule().clickCategoryByText("Anfibios");
		assertTrue("Assert that an article in a category is disabled for a student", !content.isArticlePresentByName("Frogs"));
		
		ArticlePage toads = content.clickArticleByText("Sapos");
		
		student.customWait().until(ExpectedConditions.elementToBeClickable(toads.tabSix));
		toads.clickTabSix();
		student.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Necturos")));
		
		assertTrue("Assert that an article in a Related Articles tab is disabled for a student", 
				!toads.isElementPresent(By.linkText("Ranas")) && toads.isElementPresent(By.linkText("Necturos")));
		
		
		toads.getDriver().get("https://"+student.getEnvironment()+".pebblego.com/modules/8/categories/8000/articles/8037");
		student.customWait().until(ExpectedConditions.invisibilityOfElementLocated(By.linkText("Necturos")));
		
		assertTrue("Asserts that the user is now on the Animals content select page",
				toads.getTitle().equals("Animales - PebbleGo | Capstone"));	
		
		studentHome = toads.clickLogo();
	}
	
	@Test
	public void testCannotNavigateToTurnedOffArticleByRandomWheel(){
		ContentSelectionPage content = studentHome.clickAnimalsModule().clickCategoryByText("Amphibians");
		
		ArticlePage article = content.getContentHeader().clickRandom();
		
		for(int i = 0; i < 5; i++){
			article = article.header.clickRandom();
			student.customWait().until(ExpectedConditions.invisibilityOfElementLocated(content.questionOfTheDayLink));
			//article.waitImplicitly(2);
			assertTrue("Assert that an article in a category is disabled for a student", 
					article.getTitle().equals("Toads - PebbleGo | Capstone"));
		}
		
		studentHome = article.clickLogo();
	}
	
	@Test
	public void testRelatedArticlesTabIsTurnedOff(){
		ArticlePage article = studentHome.clickAnimalsModule().clickCategoryByText("Amphibians").clickArticleByText("Toads");
		
		assertTrue("Asserts that the Toads article does not have a related article tab",
				!article.isElementPresent(article.tabSix));
		
		studentHome = article.clickLogo();
	}
	
	@Test
	public void turnOnAllElementsAfterTests(){		
		DisableArticlesTogglePage togglePage = databasePage.clickAnimalsDatabaseLink();
		togglePage.clickEnableAllButton();
		togglePage.waitImplicitly(3);
		databasePage = togglePage.clickSelectDatabaseButton();
		
		togglePage = databasePage.clickAnimalesDatabaseLink();
		togglePage.clickEnableAllButton();
		togglePage.waitImplicitly(3);
		databasePage = togglePage.clickSelectDatabaseButton();
		
		togglePage = databasePage.clickSocialStudiesDatabaseLink();
		togglePage.clickEnableAllButton();
		togglePage.waitImplicitly(3);
		databasePage = togglePage.clickSelectDatabaseButton();
		
		togglePage = databasePage.clickBiographiesDatabaseLink();
		togglePage.clickEnableAllButton();
		togglePage.waitImplicitly(3);
		databasePage = togglePage.clickSelectDatabaseButton();
		
		togglePage = databasePage.clickScienceDatabaseLink();
		togglePage.clickEnableAllButton();
		togglePage.waitImplicitly(3);
		databasePage = togglePage.clickSelectDatabaseButton();
		
		togglePage = databasePage.clickDinosaursDatabaseLink();
		togglePage.clickEnableAllButton();
		togglePage.waitImplicitly(3);
		
		databasePage = togglePage.clickSelectDatabaseButton();
	}
	
	 @Rule
	    public TestRule watcher = new TestWatcher() {
	       protected void starting(Description description) {
	          System.out.println("Starting test: " + description.getMethodName());
	        
	          
	       }
	    };
}
