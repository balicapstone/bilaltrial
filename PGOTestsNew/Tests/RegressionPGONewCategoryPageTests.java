package Tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import PGOPagesNew.PGONewLoginPage;
import PGOStudentResources.PGONewContentSelectionPage;
import PGOStudentResources.StudentHomePageNew;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;
import UserClasses.UserInfo;

public class RegressionPGONewCategoryPageTests {
	static PGONewLoginPage loginPage;
	static StudentHomePageNew home;
	static PGONewContentSelectionPage category;
    static User user;
    
    @BeforeClass
    public static void executeBefore(){
    	user = new User(UserInfo.GARAGESTUDENT);
    	
    	try{
    		loginPage = new PGONewLoginPage(user);
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    		
    		home = (StudentHomePageNew) loginPage.Login(user);
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    		
    		category = home.clickModuleByName("Animals");
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(category.gamesButton));
    		
    		category = category.clickCategoryByName("Pets and Farm Animals");
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Pets")));
    		
    		category = category.clickCategoryByName("Pets");
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Dogs")));
    		
    		category = category.clickCategoryByName("Dogs");
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Boxers")));
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
    
    
	@Rule
	public Retry retry = new Retry(3);

    
	@Test
	public void testSkipToMainLink(){
		WebElement skipToMain = home.getDriver().findElement(By.className("skip-to-main"));
		
		assertTrue("Asserts that the Skip To Main link has the correct text", skipToMain.getAttribute("innerHTML").equals("Skip to main content"));
		assertTrue("Asserts that the Skip to Main link has the correct tag name", skipToMain.getTagName().toString().equals("a"));
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
    	WebElement searchBar = home.getDriver().findElement(home.header.searchBar);
		
		assertTrue("Asserts that the search bar web element has the appropriate aria-label information", 
				searchBar.getAttribute("aria-label").toString().equals("Article search")); 
		assertTrue("Asserts that the search bar web element has the appropriate placeholder information", 
				searchBar.getAttribute("placeholder").toString().equals("Search")); 
		assertTrue("Asserts that the search bar web element has the appropriate placeholder information", 
				searchBar.getAttribute("aria-autocomplete").toString().equals("list")); 
		assertTrue("Asserts that the search bar web element has an href label", 
				searchBar.getTagName().toString().equals("input"));
    }
    
    @Test
    public void testSearchComboBoxScreenReaderElementAttributes(){
    	WebElement searchComboBox = home.getDriver().findElement(home.header.searchComboBox);
    	
    	assertTrue("Asserts that the search combo box has the correct aria-owns value",
    			searchComboBox.getAttribute("aria-owns").toString().equals("search-results"));
    	assertTrue("Asserts that the search combo box has the correct aria-haspopup value",
    			searchComboBox.getAttribute("aria-haspopup").toString().equals("listbox"));
    	assertTrue("Asserts that the search combo box has the correct aria-controls value",
    			searchComboBox.getAttribute("aria-controls").toString().equals("search-results"));
    	assertTrue("Asserts that the search combo box has the correct aria-expanded value",
    			searchComboBox.getAttribute("aria-expanded").toString().equals("false"));
    }
    
    @Test
    public void testSearchResultsScreenReaderElementAttributes(){
    	category.sendKeysToElement("dogs", home.header.searchBar);
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
    public void testRandomArticleScreenReaderElementAttributesResult(){
    	WebElement randomArticle = category.getDriver().findElement(category.randomArticleButton);
    	
    	assertTrue("Asserts that the random article button has the correct title", randomArticle.getAttribute("title").toString().equals("random article"));
    	assertTrue("Asserts that the random article button has the correct tag name", randomArticle.getTagName().toString().equals("button"));
    }
    
    @Test
    public void testHomeBreadcrumbScreenReaderElementAttributes(){
    	WebElement breadcrumbsNav = home.getDriver().findElement(By.className("breadcrumbs-nav"));
    	
    	assertTrue("Asserts that the breadcrumbs nav element has the correct aria-label", 
    			breadcrumbsNav.getAttribute("aria-label").toString().equals("breadcrumbs"));
    	assertTrue("Asserts that the breadcrumbs nav element has the correct tag",
    			breadcrumbsNav.getTagName().equals("nav"));
    	
    	WebElement homeBreadcrumb = home.getDriver().findElement(By.id("home"));
    	
    	assertTrue("Asserts that the home breadcrumb has the correct alt attribute", 
    			homeBreadcrumb.getAttribute("alt").toString().equals("home"));
    }
    
    @Test
    public void testModuleBreadcrumbScreenReaderElementAttributes(){
    	WebElement animalsBreadcrumb = home.getDriver().findElement(By.id("Animals"));
    	WebElement animalsCrumbBox = animalsBreadcrumb.findElement(By.xpath("..")).findElement(By.xpath(".."));
    	
    	assertTrue("Asserts that the module breadcrumb has the correct alt attribute", 
    			animalsBreadcrumb.getAttribute("alt").toString().equals("Animals"));
    	assertTrue("Asserts that the crumb box element has the correct aria-current attribute",
    			animalsCrumbBox.getAttribute("aria-current").toString().equals("false"));
    }
    
    @Test
    public void testCategoryBreadcrumbsScreenReaderElementAttributes(){
    	WebElement PetsAndFarmAnimalsBreadcrumb = home.getDriver().findElement(By.id("Pets and Farm Animals"));
    	WebElement PetsAndFarmAnimalsCrumbBox = PetsAndFarmAnimalsBreadcrumb.findElement(By.xpath("..")).findElement(By.xpath(".."));
    	
    	assertTrue("Asserts that the category breadcrumb has the correct alt attribute", 
    			PetsAndFarmAnimalsBreadcrumb.getAttribute("alt").toString().equals("Pets and Farm Animals"));
    	assertTrue("Asserts that the crumb box element has the correct aria-current attribute",
    			PetsAndFarmAnimalsCrumbBox.getAttribute("aria-current").toString().equals("false"));
    	
    	WebElement petsBreadcrumb = home.getDriver().findElement(By.id("Pets"));
    	WebElement petsCrumbBox = petsBreadcrumb.findElement(By.xpath("..")).findElement(By.xpath(".."));
    	
    	assertTrue("Asserts that the category breadcrumb has the correct alt attribute", 
    			petsBreadcrumb.getAttribute("alt").toString().equals("Pets"));
    	assertTrue("Asserts that the crumb box element has the correct aria-current attribute",
    			petsCrumbBox.getAttribute("aria-current").toString().equals("false"));
    	
    	WebElement dogsBreadcrumb = home.getDriver().findElement(By.id("Dogs"));
    	WebElement dogsCrumbBox = dogsBreadcrumb.findElement(By.xpath(".."));
    	
    	assertTrue("Asserts that the category breadcrumb has the correct alt attribute", 
    			dogsBreadcrumb.getAttribute("alt").toString().equals("Dogs"));
    	assertTrue("Asserts that the crumb box element has the correct last-crumb class name",
    			dogsCrumbBox.getAttribute("class").toString().contains("last-crumb"));
    }
    
    @Test
    public void testCategoryBreadcrumbArrowsTurnedOff(){
    	ArrayList<WebElement> arrows = (ArrayList<WebElement>) home.getDriver().findElements(By.className("breadcrumbs-carat"));
    	
    	Boolean hidden = true;
    	for(WebElement a: arrows){
    		try{
    			if(a.getAttribute("aria-hidden").equals("true")){
	    			hidden = hidden && true;
	    		}else{
	    			hidden = hidden && false;
	    		}
    		}catch(Exception e){
    			hidden = hidden && false;
    		}
    	}
    	
    	assertTrue("Asserts that all breadcrumb arrows are hidden from the screenreader", hidden);
    }
    
    @Test
    public void testCapstoneMenuScreenReaderElementAttributes(){
    	WebElement menu = home.getDriver().findElement(home.header.capstoneDropdown);
    	
    	assertTrue("Asserts that the capstone dropdown menu has the correct aria-label attribute", 
    			menu.getAttribute("aria-label").toString().equals("capstone menu"));
    	assertTrue("Asserts that the capstone dropdown has the correct aria-expanded value",
    			menu.getAttribute("aria-expanded").toString().equals("false"));
    	
    	home.click(home.header.capstoneDropdown);
    	user.customWait().until(ExpectedConditions.elementToBeClickable(home.header.pebbleGoNextLink));
    	
    	assertTrue("Asserts that the capstone dropdown has the correct aria-expanded value",
    			menu.getAttribute("aria-expanded").toString().equals("true"));
    	
    	home.click(home.header.capstoneDropdown);
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(home.header.pebbleGoNextLink));
    }
    
    @Test
    public void testCapstoneMenuLinksScreenReaderElementAttributes(){
    	home.click(home.header.capstoneDropdown);
    	user.customWait().until(ExpectedConditions.elementToBeClickable(home.header.pebbleGoNextLink));
    	
    	assertTrue("Asserts that the capstone dropdown link for PebbleGoNext is in fact a link",
    			home.getDriver().findElement(home.header.pebbleGoNextLink).findElement(By.xpath("..")).getTagName().toString().equals("li"));
    	assertTrue("Asserts that the capstone dropdown link for PebbleGoNext has the correct text",
    			home.getDriver().findElement(home.header.pebbleGoNextLink).getText().toString().equals("PebbleGo Next"));
    	assertTrue("Asserts that the capstone dropdown link for Capstone Interactive is in fact a link",
    			home.getDriver().findElement(home.header.capstoneInteractiveLink).findElement(By.xpath("..")).getTagName().toString().equals("li"));
    	assertTrue("Asserts that the capstone dropdown link for Capstone Interactive has the correct text",
    			home.getDriver().findElement(home.header.capstoneInteractiveLink).getText().toString().equals("Capstone Interactive"));
    	
    	home.click(home.header.capstoneDropdown);
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(home.header.pebbleGoNextLink));
    }
    
    @Test
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
    
    
    @Test
    public void testFolderTextScreenReaderElementAttributes(){
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
    public void testGamesButtonScreenReaderElementAttributes(){
    	WebElement gamesButton = category.getDriver().findElement(category.gamesButton);
    	WebElement gamesButtonText = category.getDriver().findElement(By.xpath("//button[@id=\"games-button\"]/div"));
    	
    	assertTrue("Asserts that the Games Button has the correct tag name", gamesButton.getTagName().equals("button"));
    	assertTrue("Asserts that the Games Button has the correct text", gamesButtonText.getText().equals("Games"));
    }
    
    @Test
    public void testQuestionOfTheDayButtonScreenReaderElementAttributes(){
    	WebElement QOTDButton = category.getDriver().findElement(category.questionOfTheDayButton);
    	WebElement QOTDButtonText = category.getDriver().findElement(By.xpath("//button[@id=\"question-button\"]/div"));
    	
    	assertTrue("Asserts that the QOTD Button has the correct tag name", QOTDButton.getTagName().equals("button"));
    	assertTrue("Asserts that the QOTD Button has the correct text", QOTDButtonText.getText().equals("Question of the Day"));
    }
    
    @Test
    public void testQuestionOfTheDayPageScreenReaderElementAttributes(){
    	category.click(category.questionOfTheDayButton);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(category.pollDisclaimerText));
    	
    	WebElement folderText = category.getDriver().findElement(category.folderText);
    	WebElement closeButton = category.getDriver().findElement(category.closeButton);
    	WebElement questionText = category.getDriver().findElement(category.questionText);
    	WebElement screenReaderInstructions = category.getDriver().findElement(By.className("answer-container-nav"));
    	WebElement pollDisclaimerText = category.getDriver().findElement(category.pollDisclaimerText);
    	
    	assertTrue("Asserts that the folder text header has the correct text", folderText.getText().equals("Question of the Day"));
    	assertTrue("Asserts that the folder text header has the correct tag name", folderText.getTagName().equals("h1"));
    	
    	assertTrue("Asserts that the close button has the correct aria-label", closeButton.getAttribute("aria-label").equals("Close"));
    	assertTrue("Asserts that the close button has the correcct tag name", closeButton.getTagName().equals("button"));
    	
    	assertTrue("Asserts that the question text is not empty", !questionText.getText().isEmpty());
    	assertTrue("Asserts that the question text has the correct tag name", questionText.getTagName().equals("h2"));
    	
    	assertTrue("Asserts that the screen reader instructions has the correct aria-label", 
    			screenReaderInstructions.getAttribute("aria-label").toString().equals("select an answer then hit the submit button to vote"));
    	
    	assertTrue("Asserts that the disclaimer text has the correct text", pollDisclaimerText.getText().equals("*Poll Results Updated Hourly"));
    	
    	category.clickQuestionOfTheDayAnswer();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(category.voteButton));
    	
    	WebElement voteButton = category.getDriver().findElement(category.voteButton);
    	
    	assertTrue("Asserts that the vote button has the correct text", voteButton.getText().equals("Vote"));
    	assertTrue("Asserts that the vote button has the correct tag name", voteButton.getTagName().equals("button"));
    	
    	category.click(category.closeButton);
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(category.closeButton));
    }
    
    @Test
    public void testQOTDQuestionAndAnswerScreenReaderAttributes(){
    	category.click(category.questionOfTheDayButton);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(category.pollDisclaimerText));
    	
    	ArrayList<WebElement> AnswerButtons = (ArrayList<WebElement>) category.getDriver().findElements(By.className("answer-button"));
    	ArrayList<WebElement> AnswerTitles = (ArrayList<WebElement>) category.getDriver().findElements(By.className("answer-title"));
    	
    	Boolean buttons = true;
    	for(WebElement b : AnswerButtons){
    		buttons = buttons && b.getTagName().equals("button");
    	}
    	
    	assertTrue("Asserts that all answer buttons have the correct tag name", buttons);
    	
    	Boolean titles = true;
    	for(WebElement t : AnswerTitles){
    		titles = titles && !t.getText().isEmpty();
    	}
    	
    	assertTrue("Asserts that all answer titles are not empty", titles);
    	
    	category.clickQuestionOfTheDayAnswer();
    	category.clickQuestionOfTheDayVoteButton();
    	
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(category.voteButton));
    	
    	ArrayList<WebElement> ResultsButtons = (ArrayList<WebElement>) category.getDriver().findElements(By.className("answer-button"));
    	ArrayList<WebElement> ResultsTitles = (ArrayList<WebElement>) category.getDriver().findElements(By.className("answer-title"));
    	
    	buttons = true;
    	for(WebElement b : ResultsButtons){
    		buttons = buttons && b.getTagName().equals("button");
    	}
    	
    	assertTrue("Asserts that all answer buttons have the correct tag name", buttons);
    	
    	titles = true;
    	for(WebElement t : ResultsTitles){
    		titles = titles && !t.getText().isEmpty();
    	}
    	
    	assertTrue("Asserts that all answer titles are not empty", titles);
    	
    	category.click(category.closeButton);
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(category.closeButton));
    }
    
    @Test
    public void testPebbleGoLogoLink(){
    	assertTrue("Asserts that the pebbleGo logo has the correct href value",
    			category.getDriver().findElement(category.header.logo).getAttribute("href").equals("https://"+user.getEnvironment()+".pebblego.com/modules"));
    }
    
    @Test
    public void testCapstoneTrademarkLink(){
    	assertTrue("asserts that the terms of use link contains the correct url", 
    			category.verifyLinkContainsURLByElement(home.footer.copyrightLink, "https://www.pebblego.com/copyright"));
    	
    	category.click(category.copyrightLink);
    	
    	assertTrue("asserts that the terms of use link takes the user to the correct page", 
    			category.getDriver().getCurrentUrl().equals("https://www.pebblego.com/copyright"));
    	
    	category.goBack();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(category.copyrightLink));
    }
    
    @Test
    public void testCommunityAndTeacherResourcesLink(){
    	assertTrue("asserts that the terms of use link contains the correct url", 
    			category.verifyLinkContainsURLByElement(home.footer.educatorsResourcesLink, "https://www.pebblego.com/resources"));
    	
    	category.click(category.educatorsResourcesLink);
    	
    	assertTrue("asserts that the terms of use link takes the user to the correct page", 
    			category.getDriver().getCurrentUrl().equals("https://www.pebblego.com/resources"));
    	
    	category.goBack();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(category.educatorsResourcesLink));
    }
    
    @Test
    public void testTexasToggleButton(){
    	category.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/3/categories/0");
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(category.texasOnlyButton));
    	
    	WebElement texasButton = category.getDriver().findElement(category.texasOnlyButton);
    	
    	WebElement texasButtonOn = texasButton.findElement(By.xpath("./img[contains(@class, \"on\")]"));
    	WebElement texasButtonOff = texasButton.findElement(By.xpath("./img[contains(@class, \"off\")]"));
    	
    	assertTrue("Asserts that the Texas On Button has the correct alt value", 
    			texasButtonOn.getAttribute("alt").toString().equals("Texas Only On"));
    	assertTrue("Asserts that the Texas Off Button has the correct alt value", 
    			texasButtonOff.getAttribute("alt").toString().equals("Texas Only Off"));
    	
    	assertTrue("Asserts that the Texas On Button has the correct class value",
    			texasButtonOn.getAttribute("class").toString().equals("texas-background on texas-button-hidden"));
    	assertTrue("Asserts that the Texas Off Button has the correct class value",
    			texasButtonOff.getAttribute("class").toString().equals("texas-background off texas-button-visible"));
    	
    	category.click(category.texasOnlyButton);
    	user.customWait().until(ExpectedConditions.attributeContains(texasButtonOn, "class", "texas-background on texas-button-visible"));
    	
    	assertTrue("Asserts that the Texas On Button has the correct alt value", 
    			texasButtonOn.getAttribute("alt").toString().equals("Texas Only On"));
    	assertTrue("Asserts that the Texas Off Button has the correct alt value", 
    			texasButtonOff.getAttribute("alt").toString().equals("Texas Only Off"));
    	
    	assertTrue("Asserts that the Texas On Button has the correct class value",
    			texasButtonOn.getAttribute("class").toString().equals("texas-background on texas-button-visible"));
    	assertTrue("Asserts that the Texas Off Button has the correct class value",
    			texasButtonOff.getAttribute("class").toString().equals("texas-background off texas-button-hidden"));
    	
    	category.click(category.texasOnlyButton);
    	user.customWait().until(ExpectedConditions.attributeContains(texasButtonOn, "class", "texas-background on texas-button-hidden"));
    }
}
