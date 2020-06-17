package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import PGOPagesNew.PGONewLoginPage;
import PGOStudentResources.PGONewArticlePage;
//import PGOPages.LoginPage;
//import PGOStudentResources.ArticlePage;
//import PGOStudentResources.StudentHomePage;
import PGOStudentResources.StudentHomePageNew;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;
import UserClasses.UserInfo;
// working
public class RegressionPGONewHomepageTests {
	static PGONewLoginPage loginPage;
	static StudentHomePageNew home;
    static User user;
    
    @BeforeClass
    public static void executeBefore(){
    	user = new User(UserInfo.GARAGESTUDENT);
    	
    	try{
    		loginPage = new PGONewLoginPage(user);
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    		
    		home = (StudentHomePageNew) loginPage.Login(user);
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	} catch(Exception e){
    		user.getWatcher().TakeScreenshot("FailureToRunExecuteBeforeForLoginTests");
    	}
    }
    
    @AfterClass
    public static void executeAfter(){
    	loginPage.closeCurrentWindow();
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
    		executeBefore();
    	}
    };
    
    
	/*@Rule
	public Retry retry = new Retry(3);
	*/
    
    @Test
    public void canSearchIntoArticle(){
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Dinosaurs")));
    	
    	home.header.searchForArticle("Lion");
    	
    	PGONewArticlePage article = home.header.clickResultForSearch("Lions");
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.logo));
    	
    	assertTrue("Asserts that the student user is taken to the Lions article in Animals", 
    			article.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/30/articles/160"));
    	
    	home = article.clickLogo();
    }
    
    @Test
    public void testPebbleGoLogoLink(){
    	assertTrue("Asserts that the pebbleGo logo has the correct href value",
    			home.getDriver().findElement(home.header.logo).getAttribute("href").equals("https://"+user.getEnvironment()+".pebblego.com/modules"));
    }
    
    @Test
    public void testCapstoneTrademarkLink(){
    	assertTrue("asserts that the terms of use link contains the correct url", 
    			home.verifyLinkContainsURLByElement(home.footer.copyrightLink, "https://www.pebblego.com/copyright"));
    	
    	home.click(home.footer.copyrightLink);
    	
    	assertTrue("asserts that the terms of use link takes the user to the correct page", 
    			home.getDriver().getCurrentUrl().equals("https://www.pebblego.com/copyright"));
    	
    	home.goBack();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(home.footer.copyrightLink));
    }
    
    @Test
    public void testCommunityAndTeacherResourcesLink(){
    	assertTrue("asserts that the terms of use link contains the correct url", 
    			home.verifyLinkContainsURLByElement(home.footer.educatorsResourcesLink, "https://www.pebblego.com/resources"));
    	
    	home.click(home.footer.educatorsResourcesLink);
    	
    	assertTrue("asserts that the terms of use link takes the user to the correct page", 
    			home.getDriver().getCurrentUrl().equals("https://www.pebblego.com/resources"));
    	
    	home.goBack();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(home.footer.educatorsResourcesLink));
    }
    
    @Test
    public void testLogoScreenReaderElements(){
		WebElement logo = home.getDriver().findElement(home.header.logo);
		
		assertTrue("Asserts that the logo web element has the appropriate aria-label information", 
				logo.getAttribute("aria-label").toString().equals("Home")); 
		assertTrue("Asserts that the logo web element has an href label", 
				 logo.getAttribute("href").toString().equals("https://"+user.getEnvironment()+".pebblego.com/modules"));
    }
    
    @Test
    public void testSearchScreenReaderElementAttributes(){
    	WebElement search = home.getDriver().findElement(home.header.searchBar);
    	WebElement comboBox = home.getDriver().findElement(By.xpath("//div[@class=\"search-container\"]/div[@role=\"combobox\"]"));
		
    	assertTrue("Asserts that the search combobox container has the appropriate aria-owns label", 
    			comboBox.getAttribute("aria-owns").toString().equals("search-results"));
    	assertTrue("Asserts that the search combobox container has the appropriate aria-haspopup label", 
    			comboBox.getAttribute("aria-haspopup").toString().equals("listbox"));
    	assertTrue("Asserts that the search combobox container has the appropriate aria-controls label", 
    			comboBox.getAttribute("aria-controls").toString().equals("search-results"));
    	assertTrue("Asserts that the search combobox container has the appropriate aria-expanded label", 
    			comboBox.getAttribute("aria-expanded").toString().equals("false"));
    	
		assertTrue("Asserts that the search field web element has the appropriate aria-label information", 
				search.getAttribute("aria-label").toString().equals("Article search"));  
		assertTrue("Asserts that the search field web element has the appropriate aria-autocomplete information",
				search.getAttribute("aria-autocomplete").toString().equals("list"));
		assertTrue("Asserts that the search field web element has the appropriate placeholder information", 
				search.getAttribute("placeholder").toString().equals("Search PebbleGo")); 
		assertTrue("Asserts that the search field has a aria-activedescendant label",
				search.getAttribute("aria-activedescendant") != null);
		assertTrue("Asserts that the search field web element has an href label", 
				search.getTagName().toString().equals("input"));
    }
    
    @Test
    public void testSearchResultsScreenReaderElementAttributes(){
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	home.sendKeysToElement("dogs", home.header.searchBar);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("search-result-item")));
    	
    	WebElement resultsSpan = home.getDriver().findElement(By.xpath("//div[@class=\"search-container\"]/span"));
    	
    	assertTrue("Asserts that the span that reads search results has the correct aria-live value", resultsSpan.getAttribute("aria-live").equals("assertive"));
    	assertTrue("Asserts that the span that reads search results has the correct text value", resultsSpan.getText().equals("3 articles found. Use the up and down arrow keys to navigate the list"));
    	
    	ArrayList<WebElement> searchResults = (ArrayList<WebElement>) user.getDriver().findElements(By.className("search-results-item"));
    	
    	Boolean listItems = true;
    	
    	for(WebElement w : searchResults){
    		listItems = listItems && w.getTagName().equals("li");
    	}
    	
    	assertTrue("Asserts that all search results objects are list items", listItems);
    	
    	Boolean roleItems = true;
    	
    	for(WebElement s : searchResults){
    		roleItems = roleItems && s.getAttribute("role").equals("option");
    	}
    	
    	assertTrue("Asserts that all search result objects have the role of option", roleItems);
    	
    	Boolean ariaSelectedItems = true;
    	
    	for(WebElement s : searchResults){
    		ariaSelectedItems = ariaSelectedItems && s.getAttribute("ariaSelected").equals("false");
    	}
    	
    	assertTrue("Asserts that all search result objects have the aria selected value of false", ariaSelectedItems);
    }
    
    @Test
    public void testCapstoneMenuScreenReaderElementAttributes(){
    	WebElement menu = home.getDriver().findElement(home.header.capstoneDropdown);
    	
    	assertTrue("Asserts that the capstone dropdown menu has the correct aria-label attribute", 
    			menu.getAttribute("aria-label").toString().equals("capstone menu"));
    	assertTrue("Asserts that the capstone dropdown has the correct aria-expanded value",
    			menu.getAttribute("aria-expanded").toString().equals("false"));
    	
    	home.click(home.header.capstoneDropdown);
  //  	user.customWait().until(ExpectedConditions.elementToBeClickable(home.header.pebbleGoNextLink));
    	
    	assertTrue("Asserts that the capstone dropdown has the correct aria-expanded value",
    			menu.getAttribute("aria-expanded").toString().equals("true"));
    	
    	home.click(home.header.capstoneDropdown);
   //	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(home.header.pebbleGoNextLink));
    }
    
    @Test
    public void testCapstoneMenuLinksScreenReaderElementAttributes(){
    	home.click(home.header.capstoneDropdown);
    	user.customWait().until(ExpectedConditions.elementToBeClickable(home.header.capstoneInteractiveLink));
    	
    //	assertTrue("Asserts that the capstone dropdown link for PebbleGoNext is in fact a link",
  //  			home.getDriver().findElement(home.header.pebbleGoNextLink).findElement(By.xpath("..")).getTagName().toString().equals("li"));
   // 	assertTrue("Asserts that the capstone dropdown link for PebbleGoNext has the correct text",
   // 			home.getDriver().findElement(home.header.pebbleGoNextLink).getText().toString().equals("PebbleGo Next"));
 //   	assertTrue("Asserts that the capstone dropdown link for Capstone Interactive is in fact a link",
   // 			home.getDriver().findElement(home.header.capstoneInteractiveLink).findElement(By.xpath("..")).getTagName().toString().equals("li"));
 //   	assertTrue("Asserts that the capstone dropdown link for Capstone Interactive has the correct text",
   // 			home.getDriver().findElement(home.header.capstoneInteractiveLink).getText().toString().equals("Capstone Interactive"));
    	
    	home.click(home.header.capstoneDropdown);
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(home.header.capstoneInteractiveLink));
    }
    
    // No more valid test
    @Test
    @Ignore
    public void testCapstoneMenuSignOutButtonElementAttributes(){
    	home.click(home.header.capstoneDropdown);
    	user.customWait().until(ExpectedConditions.elementToBeClickable(home.header.pebbleGoNextLink));
    	
    	assertTrue("Asserts that the capstone dropdown link for PebbleGoNext is in fact a link",
    			home.getDriver().findElement(home.header.pebbleGoNextLink).findElement(By.xpath("..")).getTagName().toString().equals("li"));
    	assertTrue("Asserts that the capstone dropdown link for PebbleGoNext has the correct text",
    			home.getDriver().findElement(home.header.pebbleGoNextLink).getText().toString().equals("PebbleGo Next"));
    	
    	home.click(home.header.capstoneDropdown);
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(home.header.pebbleGoNextLink));
    }
    
    // This test is not valid for new merged site
    @Ignore
    public void testFolderTextScreenReaderElementAttributes(){
    	WebElement header = home.getDriver().findElement(home.folderText);
    	
    	assertTrue("Asserts that the folder tab for the Student Home Page has the correct text",
    			header.getText().equals("Home"));
    	
    	ArrayList<WebElement> folderCorners = (ArrayList<WebElement>) home.getDriver().findElements(By.className("folder-svg"));
    	
    	Boolean hidden = true;
    	for(WebElement f: folderCorners){
    		try{
    			if(f.getAttribute("aria-hidden").equals("true")){
	    			hidden = hidden && true;
	    		}else{
	    			hidden = hidden && false;
	    		}
    		}catch(Exception e){
    			hidden = hidden && false;
    		}
    	}
    	
    	assertTrue("Asserts that all folder corners are hidden from the screenreader", hidden);
    }
    
    
    @Test
    public void testEnglishModulesLanguageElementAttributes(){
    	WebElement animals = home.getDriver().findElement(By.id("Animals"));
    	assertTrue("Assert that the animals module has a lang attribute set to english",
    			animals.getAttribute("lang").toString().equals("en"));
    	
    	WebElement biographies = home.getDriver().findElement(By.id("Biographies"));
    	assertTrue("Assert that the biographies module has a lang attribute set to english",
    			biographies.getAttribute("lang").toString().equals("en"));
    	
    	WebElement science = home.getDriver().findElement(By.id("Science"));
    	assertTrue("Assert that the science module has a lang attribute set to english",
    			science.getAttribute("lang").toString().equals("en"));
    	
    	WebElement socialStudies = home.getDriver().findElement(By.id("Social Studies"));
    	assertTrue("Assert that the social studies module has a lang attribute set to english",
    			socialStudies.getAttribute("lang").toString().equals("en"));
    	
    	WebElement dinosaurs = home.getDriver().findElement(By.id("Dinosaurs"));
    	assertTrue("Assert that the dinosaurs module has a lang attribute set to english",
    			dinosaurs.getAttribute("lang").toString().equals("en"));
    }
    
    @Test
    public void testSpanishModulesLanguageElementAttributes(){
    	
    	
    	home.getDriver().findElement(By.id("home")).click();
    	home.waitImplicitly(10);
    	WebElement animales = home.getDriver().findElement(By.id("Animales"));
    	assertTrue("Assert that the animales module has a lang attribute set to spanish",
    			animales.getAttribute("lang").toString().equals("es"));
    	
    	WebElement biografias = home.getDriver().findElement(By.id("Biografías"));
    	assertTrue("Assert that the biographies module has a lang attribute set to spanish",
    			biografias.getAttribute("lang").toString().equals("es"));
    	
    	WebElement ciencia = home.getDriver().findElement(By.id("Ciencia"));
    	assertTrue("Assert that the ciencia module has a lang attribute set to spanish",
    			ciencia.getAttribute("lang").toString().equals("es"));
    	
    	WebElement estudiosSociales = home.getDriver().findElement(By.id("Estudios Sociales"));
    	assertTrue("Assert that the estudio sociales module has a lang attribute set to spanish",
    			estudiosSociales.getAttribute("lang").toString().equals("es"));
    }
}
