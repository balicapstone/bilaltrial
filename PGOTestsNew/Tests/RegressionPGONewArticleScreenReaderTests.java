package Tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import PGOPagesNew.PGONewLoginPage;
import PGOStudentResources.PGONewArticlePage;
import PGOStudentResources.PGONewContentSelectionPage;
import PGOStudentResources.StudentHomePageNew;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;
import UserClasses.UserInfo;

@FixMethodOrder(MethodSorters.JVM)
public class RegressionPGONewArticleScreenReaderTests {
	static PGONewLoginPage loginPage;
	static StudentHomePageNew home;
	static PGONewArticlePage article;
    static User user;
    
    @BeforeClass
    public static void executeBefore(){
    	user = new User(UserInfo.GARAGESTUDENT);
    	
    	try{
    		loginPage = new PGONewLoginPage(user);
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    		
    		home = (StudentHomePageNew) loginPage.Login(user);
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    		
    		PGONewContentSelectionPage content = home.clickModuleByName("Animals");
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Amphibians")));
    		
    		content = content.clickCategoryByName("Amphibians");
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Toads")));
    		
    		article = content.clickArticleByName("Frogs");
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.educatorsLink));
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
    public void testLogoScreenReaderElements(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
		WebElement logo = home.getDriver().findElement(By.className("header-logo-link"));
		
		assertTrue("Asserts that the cite web element has the appropriate aria-label information", 
				logo.getAttribute("aria-label").toString().equals("Home")); 
		assertTrue("Asserts that the logo web element has an href label", 
				 logo.getAttribute("href").toString().equals("https://"+user.getEnvironment()+".pebblego.com/modules"));
    }
    
    @Test
    public void testRandomArticleButtonScreenReaderElements(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
    	WebElement randomArticleButton = home.getDriver().findElement(home.header.randomArticleButton);
    	
    	assertTrue("Asserts that the random article buttom element has the correct title",
    			randomArticleButton.getAttribute("title").toString().equals("random article"));
    	assertTrue("Asserts that the random article button element has the correct tag",
    			randomArticleButton.getTagName().equals("button"));	
    }
    
    @Test
    public void testSpanishButtonScreenReaderElements(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
    	WebElement spanishButton = home.getDriver().findElement(home.header.languageButton);
    	
    	assertTrue("Asserts that the language button has the correct text", spanishButton.getText().equals("Español"));
    	assertTrue("Asserts that the language button has the correct tag", spanishButton.getTagName().equals("button"));
    	
		article.click(article.languageButton);
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(article.educatorsLink));
		
		WebElement englishButton = home.getDriver().findElement(home.header.languageButton);
		
    	assertTrue("Asserts that the language button has the correct text", englishButton.getText().equals("English"));
    	assertTrue("Asserts that the language button has the correct tag", englishButton.getTagName().equals("button"));
    	
		article.click(article.languageButton);
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(article.educatorsLink));
    }

    @Test
    public void testBreadcrumbsScreenReaderElements(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
    	WebElement breadcrumbsNav = home.getDriver().findElement(By.className("breadcrumbs-nav"));
    	
    	assertTrue("Asserts that the breadcrumbs nav element has the correct aria-label", 
    			breadcrumbsNav.getAttribute("aria-label").toString().equals("breadcrumbs"));
    	assertTrue("Asserts that the breadcrumbs nav element has the correct tag",
    			breadcrumbsNav.getTagName().equals("nav"));
    	
    	WebElement homeBreadcrumb = home.getDriver().findElement(By.id("home"));
    	
    	assertTrue("Asserts that the home breadcrumb has the correct alt attribute", 
    			homeBreadcrumb.getAttribute("alt").toString().equals("home"));
    	
    	WebElement animalsBreadcrumb = home.getDriver().findElement(By.id("Animals"));
    	WebElement animalsCrumbBox = animalsBreadcrumb.findElement(By.xpath("..")).findElement(By.xpath(".."));
    	
    	assertTrue("Asserts that the module breadcrumb has the correct alt attribute", 
    			animalsBreadcrumb.getAttribute("alt").toString().equals("Animals"));
    	assertTrue("Asserts that the crumb box element has the correct aria-current attribute",
    			animalsCrumbBox.getAttribute("aria-current").toString().equals("false"));
    	
    	WebElement amphibiansBreadcrumb = home.getDriver().findElement(By.id("Amphibians"));
    	WebElement amphibiansCrumbBox = amphibiansBreadcrumb.findElement(By.xpath("..")).findElement(By.xpath(".."));
    	
    	assertTrue("Asserts that the category breadcrumb has the correct alt attribute", 
    			amphibiansBreadcrumb.getAttribute("alt").toString().equals("Amphibians"));
    	assertTrue("Asserts that the crumb box element has the correct aria-current attribute",
    			amphibiansCrumbBox.getAttribute("aria-current").toString().equals("false"));
    	
    	/*
    	WebElement frogsBreadcrumb = home.getDriver().findElement(By.id("Frogs")).findElement(By.xpath("..")).findElement(By.xpath(".."));
    	
    	assertTrue("Asserts that the article breadcrumb has the correct alt attribute", 
    			frogsBreadcrumb.getAttribute("aria-hidden").toString().equals("true"));
    			*/
    }
    
    @Test
    public void testCapstoneMenuScreenReaderElementAttributes(){
    	WebElement menu = article.getDriver().findElement(home.header.capstoneDropdown);
    	
    	assertTrue("Asserts that the capstone dropdown menu has the correct aria-label attribute", 
    			menu.getAttribute("aria-label").toString().equals("capstone menu"));
    	assertTrue("Asserts that the capstone dropdown has the correct aria-expanded value",
    			menu.getAttribute("aria-expanded").toString().equals("false"));
    	
    	article.click(article.header.capstoneDropdown);
    	user.customWait().until(ExpectedConditions.elementToBeClickable(home.header.pebbleGoNextLink));
    	
    	assertTrue("Asserts that the capstone dropdown has the correct aria-expanded value",
    			menu.getAttribute("aria-expanded").toString().equals("true"));
    	
    	article.click(article.header.capstoneDropdown);
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(article.header.pebbleGoNextLink));
    }
    
    @Test
    public void testCapstoneMenuLinksScreenReaderElementAttributes(){
    	article.click(article.header.capstoneDropdown);
    	user.customWait().until(ExpectedConditions.elementToBeClickable(article.header.pebbleGoNextLink));
    	
    	assertTrue("Asserts that the capstone dropdown link for PebbleGoNext is in fact a link",
    			article.getDriver().findElement(home.header.pebbleGoNextLink).findElement(By.xpath("..")).getTagName().toString().equals("li"));
    	assertTrue("Asserts that the capstone dropdown link for PebbleGoNext has the correct text",
    			article.getDriver().findElement(home.header.pebbleGoNextLink).getText().toString().equals("PebbleGo Next"));
    	assertTrue("Asserts that the capstone dropdown link for Capstone Interactive is in fact a link",
    			article.getDriver().findElement(home.header.capstoneInteractiveLink).findElement(By.xpath("..")).getTagName().toString().equals("li"));
    	assertTrue("Asserts that the capstone dropdown link for Capstone Interactive has the correct text",
    			article.getDriver().findElement(home.header.capstoneInteractiveLink).getText().toString().equals("Capstone Interactive"));
    	
    	article.click(article.header.capstoneDropdown);
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(article.header.pebbleGoNextLink));
    }
    
    @Test
    public void testCapstoneMenuSignOutButtonElementAttributes(){
    	article.click(article.header.capstoneDropdown);
    	user.customWait().until(ExpectedConditions.elementToBeClickable(article.header.pebbleGoNextLink));
    	
    	assertTrue("Asserts that the capstone dropdown link for PebbleGoNext is in fact a link",
    			article.getDriver().findElement(home.header.pebbleGoNextLink).findElement(By.xpath("..")).getTagName().toString().equals("li"));
    	assertTrue("Asserts that the capstone dropdown link for PebbleGoNext has the correct text",
    			article.getDriver().findElement(home.header.pebbleGoNextLink).getText().toString().equals("PebbleGo Next"));
    	
    	article.click(home.header.capstoneDropdown);
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(article.header.pebbleGoNextLink));
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
    public void testFooterTabScreenReaderElementAttributes(){
    	ArrayList<WebElement> folderCorners = (ArrayList<WebElement>) home.getDriver().findElements(By.className("article-footer-svg"));
    	
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
    	
    	assertTrue("Asserts that all bottom tab corners are hidden from the screenreader", hidden);
    	
    	WebElement printTitle = article.getDriver().findElement(By.id("print-title"));
    	assertTrue("Asserts that the print folder tab has the correct text", 
    			printTitle.getText().toString().equals("Print"));
    	
    	WebElement mediaTitle = article.getDriver().findElement(By.id("media-title"));
    	assertTrue("Asserts that the media folder tab has the correct text", 
    			mediaTitle.getText().toString().equals("Media"));
    }
    
    @Test
    public void testFirstTabScreenReaderElements(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
    	article.click(article.tabOne);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.tabOneImage));
    	
    	WebElement tab = article.getDriver().findElement(article.tabOne);
    	
    	assertTrue("Asserts that the tab element has the correct aria-controls value", 
    			tab.getAttribute("aria-controls").toString().equals("screen-0"));
    	assertTrue("Asserts that the tab element has the correct aria-label value", 
    			tab.getAttribute("aria-label").toString().equals("Body"));
    	assertTrue("Asserts that the tab element has the correct aria-selected value",
    			tab.getAttribute("aria-selected").toString().equals("true"));
    	
    	WebElement audio = article.getDriver().findElement(article.playAudioButton);
    	
    	assertTrue("Asserts that the tab one audio element has the correct aria-label value",
    			audio.getAttribute("aria-label").toString().equals("Play"));
    	assertTrue("Asserts that the tab one audio element has the correct aria-pressed value",
    			audio.getAttribute("aria-pressed").equals("false"));
    	
    	article.click(article.playAudioButton);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("screen-audio-button-playing")));
    	
    	assertTrue("Asserts that the audio button element changes to have the correct aria-pressed value",
    			audio.getAttribute("aria-pressed").equals("true"));
    	
    	article.click(article.playAudioButton);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("screen-audio-button-paused")));
    	
    	assertTrue("Asserts that the audio button element changes to have the correct aria-pressed value",
    			audio.getAttribute("aria-pressed").equals("false"));
    	
    	WebElement image = article.getDriver().findElement(article.tabOneImage);
    	
    	assertTrue("Asserts that the image element has the correct alt value",
    			image.getAttribute("alt").toString().equals("a blue poison dart frog. Click or press enter to toggle expansion."));
    }
    
    @Test
    public void testSecondTabScreenReaderElements(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
    	article.click(article.tabTwo);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.tabTwoImage));
    	
    	WebElement tab = article.getDriver().findElement(article.tabTwo);
    	
    	assertTrue("Asserts that the tab element has the correct aria-controls value", 
    			tab.getAttribute("aria-controls").toString().equals("screen-1"));
    	assertTrue("Asserts that the tab element has the correct aria-label value", 
    			tab.getAttribute("aria-label").toString().equals("Habitat"));
    	assertTrue("Asserts that the tab element has the correct aria-selected value",
    			tab.getAttribute("aria-selected").toString().equals("true"));
    	
    	WebElement audio = article.getDriver().findElement(article.playAudioButton);
    	
    	assertTrue("Asserts that the tab one audio element has the correct aria-label value",
    			audio.getAttribute("aria-label").toString().equals("Play"));
    	assertTrue("Asserts that the tab one audio element has the correct aria-pressed value",
    			audio.getAttribute("aria-pressed").equals("false"));
    	
    	article.click(article.playAudioButton);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("screen-audio-button-playing")));
    	
    	assertTrue("Asserts that the audio button element changes to have the correct aria-pressed value",
    			audio.getAttribute("aria-pressed").equals("true"));
    	
    	article.click(article.playAudioButton);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("screen-audio-button-paused")));
    	
    	assertTrue("Asserts that the audio button element changes to have the correct aria-pressed value",
    			audio.getAttribute("aria-pressed").equals("false"));
    	
    	WebElement image = article.getDriver().findElement(article.tabTwoImage);
    	
    	assertTrue("Asserts that the image element has the correct alt value",
    			image.getAttribute("alt").toString().equals("four frogs in a pond. Click or press enter to toggle expansion."));
    }
    
    @Test
    public void testThirdTabScreenReaderElements(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
    	article.click(article.tabThree);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.tabThreeImage));
    	
    	WebElement tab = article.getDriver().findElement(article.tabThree);

    	assertTrue("Asserts that the tab element has the correct aria-controls value", 
    			tab.getAttribute("aria-controls").toString().equals("screen-2"));
    	assertTrue("Asserts that the tab element has the correct aria-label value", 
    			tab.getAttribute("aria-label").toString().equals("Food"));
    	assertTrue("Asserts that the tab element has the correct aria-selected value",
    			tab.getAttribute("aria-selected").toString().equals("true"));
    	
    	WebElement audio = article.getDriver().findElement(article.playAudioButton);
    	
    	assertTrue("Asserts that the tab one audio element has the correct aria-label value",
    			audio.getAttribute("aria-label").toString().equals("Play"));
    	assertTrue("Asserts that the tab one audio element has the correct aria-pressed value",
    			audio.getAttribute("aria-pressed").equals("false"));
    	
    	article.click(article.playAudioButton);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("screen-audio-button-playing")));
    	
    	assertTrue("Asserts that the audio button element changes to have the correct aria-pressed value",
    			audio.getAttribute("aria-pressed").equals("true"));
    	
    	article.click(article.playAudioButton);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("screen-audio-button-paused")));
    	
    	assertTrue("Asserts that the audio button element changes to have the correct aria-pressed value",
    			audio.getAttribute("aria-pressed").equals("false"));
    	
    	WebElement image = article.getDriver().findElement(article.tabThreeImage);
    	
    	assertTrue("Asserts that the image element has the correct alt value",
    			image.getAttribute("alt").toString().equals("a frog eating a worm. Click or press enter to toggle expansion."));
    }

    @Test
    public void testFourthTabScreenReaderElements(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
    	article.click(article.tabFour);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.tabFourImage));
    	
    	WebElement tab = article.getDriver().findElement(article.tabFour);
    	
    	assertTrue("Asserts that the tab element has the correct aria-controls value", 
    			tab.getAttribute("aria-controls").toString().equals("screen-3"));
    	assertTrue("Asserts that the tab element has the correct aria-label value", 
    			tab.getAttribute("aria-label").toString().equals("Life Cycle"));
    	assertTrue("Asserts that the tab element has the correct aria-selected value",
    			tab.getAttribute("aria-selected").toString().equals("true"));
    	
    	WebElement audio = article.getDriver().findElement(article.playAudioButton);
    	
    	assertTrue("Asserts that the tab one audio element has the correct aria-label value",
    			audio.getAttribute("aria-label").toString().equals("Play"));
    	assertTrue("Asserts that the tab one audio element has the correct aria-pressed value",
    			audio.getAttribute("aria-pressed").equals("false"));
    	
    	article.click(article.playAudioButton);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("screen-audio-button-playing")));
    	
    	assertTrue("Asserts that the audio button element changes to have the correct aria-pressed value",
    			audio.getAttribute("aria-pressed").equals("true"));
    	
    	article.click(article.playAudioButton);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("screen-audio-button-paused")));
    	
    	assertTrue("Asserts that the audio button element changes to have the correct aria-pressed value",
    			audio.getAttribute("aria-pressed").equals("false"));
    	
    	WebElement image = article.getDriver().findElement(article.tabFourImage);
    	
    	assertTrue("Asserts that the image element has the correct alt value",
    			image.getAttribute("alt").toString().equals("a tadpole. Click or press enter to toggle expansion."));
    }

    @Test
    public void testFifthTabScreenReaderElements(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
    	article.click(article.tabFive);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.tabFiveImage));
    	
    	WebElement tab = article.getDriver().findElement(article.tabFive);
    	
    	assertTrue("Asserts that the tab element has the correct aria-controls value", 
    			tab.getAttribute("aria-controls").toString().equals("screen-4"));
    	assertTrue("Asserts that the tab element has the correct aria-label value", 
    			tab.getAttribute("aria-label").toString().equals("Fun Facts"));
    	assertTrue("Asserts that the tab element has the correct aria-selected value",
    			tab.getAttribute("aria-selected").toString().equals("true"));
    	
    	WebElement audio = article.getDriver().findElement(article.playAudioButton);
    	
    	assertTrue("Asserts that the tab one audio element has the correct aria-label value",
    			audio.getAttribute("aria-label").toString().equals("Play"));
    	assertTrue("Asserts that the tab one audio element has the correct aria-pressed value",
    			audio.getAttribute("aria-pressed").equals("false"));
    	
    	article.click(article.playAudioButton);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("screen-audio-button-playing")));
    	
    	assertTrue("Asserts that the audio button element changes to have the correct aria-pressed value",
    			audio.getAttribute("aria-pressed").equals("true"));
    	
    	article.click(article.playAudioButton);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("screen-audio-button-paused")));
    	
    	assertTrue("Asserts that the audio button element changes to have the correct aria-pressed value",
    			audio.getAttribute("aria-pressed").equals("false"));
    	
    	WebElement image = article.getDriver().findElement(article.tabFiveImage);
    	
    	assertTrue("Asserts that the image element has the correct alt value",
    			image.getAttribute("alt").toString().equals("a frog on a leaf. Click or press enter to toggle expansion."));
    }
    
    @Test
    public void testPrintHeaderText(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
    	assertTrue("Asserts that the print header has the correct text", article.getDriver().findElement(article.printHeader).getText().equals("Print"));
    }
    
    @Test
    public void testCiteButtonScreenReaderAttributes(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
    	WebElement citeButton = article.getDriver().findElement(article.citeButton);
    	WebElement citeLabel = article.getDriver().findElement(By.xpath("//button[@id=\"print-footer-citation-button\"]/p"));
    	
    	
    	assertTrue("Asserts that the cite button has the correct aria-describedby attribute value",
    			citeButton.getAttribute("aria-describedby").toString().equals("print-title"));
    	assertTrue("Asserts that the cite button has the correct tag", 
    			citeButton.getTagName().toString().equals("button"));
    	assertTrue("Asserts that the cite label has the correct text",
    			citeLabel.getText().toString().equals("Cite"));
    }
	
    @Test
    public void testPrintArticleButtonScreenReaderAttributes(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
    	WebElement citeButton = article.getDriver().findElement(article.printArticleButton);
    	WebElement citeLabel = article.getDriver().findElement(By.xpath("//button[@id=\"print-footer-article-button\"]/p"));
    	
    	
    	assertTrue("Asserts that the cite button has the correct aria-describedby attribute value",
    			citeButton.getAttribute("aria-describedby").toString().equals("print-title"));
    	assertTrue("Asserts that the cite button has the correct tag", 
    			citeButton.getTagName().toString().equals("button"));
    	assertTrue("Asserts that the cite label has the correct text",
    			citeLabel.getText().toString().equals("Article"));
    }

    @Test
    public void testActivitiesButtonScreenReaderAttributes(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
    	WebElement citeButton = article.getDriver().findElement(article.printArticleButton);
    	WebElement citeLabel = article.getDriver().findElement(By.xpath("//button[@id=\"print-footer-activities-button\"]/p"));
    	
    	assertTrue("Asserts that the cite button has the correct aria-describedby attribute value",
    			citeButton.getAttribute("aria-describedby").toString().equals("print-title"));
    	assertTrue("Asserts that the cite button has the correct tag", 
    			citeButton.getTagName().toString().equals("button"));
    	assertTrue("Asserts that the cite label has the correct text",
    			citeLabel.getText().toString().equals("Activities"));
    }

    @Test
    public void testClickCiteButtonChangesSpanLabelForScreenReader(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
    	WebElement screenReaderFooterText = article.getDriver().findElement(article.screenReaderFooterText);
    	
    	assertTrue("Asserts that the footer screen reader text has no text initially", screenReaderFooterText.getAttribute("innerText").toString().equals(""));
    	
    	article.click(article.citeButton);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.footerCloseButton));

    	assertTrue("Asserts that the footer screen reader text has changed to be the appropriate text for the cite button",
    			screenReaderFooterText.getAttribute("innerText").toString().equals("Print citation options now displaying in main content area. Press close button to return to the article."));
    	
    	article.click(article.footerCloseButton);
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(article.footerCloseButton));
    	
    	assertTrue("Asserts that the footer screen reader text has no text after the close button has been clicked",
    			screenReaderFooterText.getAttribute("innerText").toString().equals(""));
    }

    @Test
    public void testClickArticleButtonChangesSpanLabelForScreenReader(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
    	WebElement screenReaderFooterText = article.getDriver().findElement(article.screenReaderFooterText);
    	
    	assertTrue("Asserts that the footer screen reader text has no text initially", screenReaderFooterText.getAttribute("innerText").toString().equals(""));
    	
    	article.click(article.printArticleButton);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.footerCloseButton));
    	
    	assertTrue("Asserts that the footer screen reader text has changed to be the appropriate text for the article button",
    			screenReaderFooterText.getAttribute("innerText").toString().equals("Print article options now displaying in main content area. Press close button to return to the article."));
    	
    	article.click(article.footerCloseButton);
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(article.footerCloseButton));
    	
    	assertTrue("Asserts that the footer screen reader text has no text after the close button has been clicked",
    			screenReaderFooterText.getAttribute("innerText").toString().equals(""));
    }
    
    @Test
    public void testClickActivitiesButtonChangesSpanLabelForScreenReader(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
    	WebElement screenReaderFooterText = article.getDriver().findElement(article.screenReaderFooterText);
    	
    	assertTrue("Asserts that the footer screen reader text has no text initially", screenReaderFooterText.getAttribute("innerText").toString().equals(""));
    	
    	article.click(article.activityButton);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.footerCloseButton));
    	
    	assertTrue("Asserts that the footer screen reader text has changed to be the appropriate text for the activity button",
    			screenReaderFooterText.getAttribute("innerText").equals("Print activities options now displaying in main content area. Press close button to return to the article."));
    	
    	article.click(article.footerCloseButton);
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(article.footerCloseButton));
    	
    	assertTrue("Asserts that the footer screen reader text has no text after the close button has been clicked",
    			screenReaderFooterText.getAttribute("innerText").toString().equals(""));
    }
    
    @Test
    public void testScreenReaderLabelsForCiteElements(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
    	article.click(article.citeButton);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.footerCloseButton));
    	
    	WebElement printCiteButton = article.getDriver().findElement(article.printCitationButton);
    	WebElement closeButton = article.getDriver().findElement(article.footerCloseButton);
    	
    	assertTrue("Asserts that the print button has the correct aria-label attribute", printCiteButton.getAttribute("aria-label").toString().equals("print citation"));
    	assertTrue("Asserts that the print button has the correct tag", printCiteButton.getTagName().toString().equals("button"));
    
    	assertTrue("Asserts that the close button has the correct aria-label attribute", closeButton.getAttribute("aria-label").toString().equals("Close"));
    	assertTrue("Asserts that the close button has the correct tag", closeButton.getTagName().toString().equals("button"));
    	
    	article.click(article.footerCloseButton);
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(article.footerCloseButton));
    }
    
    @Test
    public void testScreenReaderLabelsForArticleButtonElements(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
    	article.click(article.printArticleButton);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.footerCloseButton));
    	
    	WebElement closeButton = article.getDriver().findElement(article.footerCloseButton);
    	
    	WebElement printArticleButton = article.getDriver().findElement(article.printWholeArticleButton);
    	WebElement printArticleText = article.getDriver().findElement(By.xpath("//div[@class=\"print-article-section\"]/div[1]/h2"));
    	
    	WebElement printPageButton = article.getDriver().findElement(article.printPageButton);
    	WebElement printPageText = article.getDriver().findElement(By.xpath("//div[@class=\"print-article-section\"]/div[2]/h2"));
    	
    	assertTrue("Asserts that the print entire article button has the correct header text", printArticleText.getText().toString().equals("Article"));
    	assertTrue("Asserts that the print whole article button has the correct aria-label attribute", printArticleButton.getAttribute("aria-label").toString().equals("print entire article"));
    	assertTrue("Asserts that the print whole article button has the correct tag", printArticleButton.getTagName().toString().equals("button"));
    
    	assertTrue("Asserts that the print by page button has the correct header text", printPageText.getText().toString().equals("Page"));
    	assertTrue("Asserts that the print the current page button has the correct aria-label attribute", printPageButton.getAttribute("aria-label").toString().equals("print current article tab"));
    	assertTrue("Asserts that the print by page button has the correct tag", printPageButton.getTagName().toString().equals("button"));
    	
    	assertTrue("Asserts that the close button has the correct aria-label attribute", closeButton.getAttribute("aria-label").toString().equals("Close"));
    	assertTrue("Asserts that the close button has the correct tag", closeButton.getTagName().toString().equals("button"));
    	
    	article.click(article.footerCloseButton);
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(article.footerCloseButton));
    }
    
    @Test
    public void testScreenReaderLabelsForActivitiesButtonElements(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/2/categories/2980/articles/2001");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.activityButton));
    	
    	article.click(article.activityButton);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.footerCloseButton));
    	
    	WebElement closeButton = article.getDriver().findElement(article.footerCloseButton);
    	
    	WebElement shareWhatYouKnowButton = article.getDriver().findElement(article.printShareWhatYouKnowButton);
    	WebElement printShareWhatYouKnowText = article.getDriver().findElement(By.xpath("//div[@class=\"print-activities-section\"]/div[1]/h2"));
    	
    	WebElement printActivityButton = article.getDriver().findElement(article.printActivityButton);
    	WebElement printActivityText = article.getDriver().findElement(By.xpath("//div[@class=\"print-activities-section\"]/div[2]/h2"));
    	
    	WebElement questionsForUnderstanding = article.getDriver().findElement(article.questionsForUnderstanding);
    	WebElement questionsForUnderstandingText = article.getDriver().findElement(By.xpath("//div[@class=\"print-activities-section\"]/div[3]/h2"));
    	
    	assertTrue("Asserts that the Share What You Know print button has the correct header text", printShareWhatYouKnowText.getText().toString().equals("Share What You Know"));
    	assertTrue("Asserts that the Share What You Know print button has the correct aria-label attribute", shareWhatYouKnowButton.getAttribute("aria-label").toString().equals("print share what you know worksheet"));
    	assertTrue("Asserts that the Share What You Know print button has the correct tag", shareWhatYouKnowButton.getTagName().toString().equals("button"));
    
    	assertTrue("Asserts that the Activity print button has the correct header text", printActivityText.getText().toString().equals("Activity"));
    	assertTrue("Asserts that the Activity print button has the correct aria-label attribute", printActivityButton.getAttribute("aria-label").toString().equals("print activities worksheet"));
    	assertTrue("Asserts that the Activity print button has the correct tag", printActivityButton.getTagName().toString().equals("button"));
    	
    	assertTrue("Asserts that the Activity print button has the correct header text", questionsForUnderstandingText.getText().toString().equals("Questions for Understanding"));
    	assertTrue("Asserts that the Activity print button has the correct aria-label attribute", questionsForUnderstanding.getAttribute("aria-label").toString().equals("print questions worksheet"));
    	assertTrue("Asserts that the Activity print button has the correct tag", questionsForUnderstanding.getTagName().toString().equals("button"));
    	
    	assertTrue("Asserts that the close button has the correct aria-label attribute", closeButton.getAttribute("aria-label").toString().equals("Close"));
    	assertTrue("Asserts that the close button has the correct tag", closeButton.getTagName().toString().equals("button"));
    	
    	article.click(article.footerCloseButton);
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(article.footerCloseButton));
    }
    
    @Test
    public void testScreenReaderLabelsForListenButton(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/38");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.listenButton));
    	
    	WebElement listenButton = article.getDriver().findElement(article.listenButton);
    	WebElement listenTitle = article.getDriver().findElement(By.xpath("//button[@id=\"media-footer-listen-button\"]/p"));
    	WebElement listenImage = article.getDriver().findElement(By.xpath("//button[@id=\"media-footer-listen-button\"]//img[@class=\"article-footer-icon-image\"]"));
    	
    	assertTrue("Asserts that the listen button has the correct aria-describedby label", listenButton.getAttribute("aria-describedby").toString().equals("media-title"));
    	assertTrue("Asserts that the listen button has the correct tag name label", listenButton.getTagName().toString().equals("button"));
    	assertTrue("Asserts that the listen title has the correct text", listenTitle.getText().toString().equals("Listen"));
    	assertTrue("Asserts that the listen image has the correct src file", listenImage.getAttribute("src").toString().contains(".pebblego.com/static/media/listen_button"));
    	
    	article.click(article.listenButton);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("media-footer-pause-button")));
    	
    	listenImage = article.getDriver().findElement(By.xpath("//button[@id=\"media-footer-pause-button\"]//img[@class=\"article-footer-icon-image\"]"));
    	
    	assertTrue("Asserts that when the listen button is pressed that the src image file changes", listenImage.getAttribute("src").toString().contains("pebblego.com/static/media/play_button"));
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("media-footer-listen-button")));
    }
    
    @Test
    public void testScreenReaderLabelsForRangeMapButton(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/380");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.rangeMapButton));
    	
    	WebElement rangeButton = article.getDriver().findElement(article.rangeMapButton);
    	WebElement rangeTitle = article.getDriver().findElement(By.xpath("//button[@id=\"media-footer-range-map-button\"]/p"));
    	
    	assertTrue("Asserts that the range button has the correct tag name label", rangeButton.getTagName().equals("button"));
    	assertTrue("Asserts that the range title has the correct text", rangeTitle.getText().equals("Range"));
    }
    
    @Test
    public void testScreenReaderRangeMapButtonElements(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/1/articles/380");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.rangeMapButton));
    	
    	article.clickRangeMapButton();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.closeButton));
    	
    	WebElement folderTab = article.getDriver().findElement(By.id("folder-text"));
    	WebElement rangeMapText = article.getDriver().findElement(By.className("range-map-caption-text"));
    	WebElement closeButton = article.getDriver().findElement(article.closeButton);
    	
    	assertTrue("Asserts that the folder tab has the correct text", folderTab.getText().equals("Range Map"));
    	assertTrue("Asserts that the range map text has the correct text", rangeMapText.getText().equals("This is where Mudpuppies can be found"));
    	assertTrue("Asserts that the close button has the correct tag name", closeButton.getTagName().equals("button"));
    	assertTrue("Asserts that the close button has the aria-label value", closeButton.getAttribute("aria-label").toString().equals("close"));
    }
    
    @Test
    public void testScreenReaderLabelsForTimeLineButton(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/3/categories/3047/articles/3049");
    	
    	user.customWait().until(ExpectedConditions.elementToBeClickable(article.timelineButton));
    	
    	WebElement timelineButton = article.getDriver().findElement(article.timelineButton);
    	WebElement timelineTitle = article.getDriver().findElement(By.xpath("//button[@id=\"media-footer-timeline-button\"]/p"));
    	
    	assertTrue("Asserts that the timeline button has the correct aria-describedby", timelineButton.getAttribute("aria-describedby").toString().equals("media-title"));
    	assertTrue("Asserts that the timeline button has the correct tag name label", timelineButton.getTagName().toString().equals("button"));
    	assertTrue("Asserts that the timeline button has the correct title", timelineTitle.getText().toString().equals("Timeline"));
    }
    
    @Test
    public void testScreenReaderTimeLineHeadingElements(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/3/categories/3047/articles/3049");
    	
    	user.customWait().until(ExpectedConditions.elementToBeClickable(article.timelineButton));
    	
    	article.click(article.timelineButton);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("timeline-layout")));
    	
    	WebElement folderHeader = article.getDriver().findElement(article.folderText);
    	WebElement closeButton = article.getDriver().findElement(article.closeButton);
    	WebElement timelineImage = article.getDriver().findElement(article.timelineImage);
    	WebElement timelineTitle = article.getDriver().findElement(article.timelineTitle);
    	WebElement timelineLayout = article.getDriver().findElement(By.className("timeline-layout"));
    	
    	assertTrue("Asserts that the Timeline Folder Title has the correct text", folderHeader.getText().toString().equals("Timeline"));
    	assertTrue("Asserts that the Timeline Folder Title element has the correct tag name label", folderHeader.getTagName().toString().equals("h1"));
    	
    	assertTrue("Asserts that the Close button element has aria-label attribute", closeButton.getAttribute("aria-label").toString().equals("close"));
    	assertTrue("Asserts that the Close button element has the correct tag name label", closeButton.getTagName().toString().equals("button"));
    	
    	assertTrue("Asserts that the timeline image has the correct alt attribute", timelineImage.getAttribute("alt").toString().equals("Babe Ruth"));
    	assertTrue("Asserts that the timeline image has the correct tag name label", timelineImage.getTagName().equals("img"));

    	assertTrue("Asserts that the timeline screen has the correct aria-label attribute", timelineLayout.getAttribute("aria-label").toString().equals("timeline"));
    	
    	assertTrue("Asserts that the timeline title has the correct text", timelineTitle.getText().toString().equals("Babe Ruth"));
    	assertTrue("Asserts that the timeline title has the correct tag name label", timelineTitle.getTagName().toString().equals("h2"));
    }
    
    @Test
    public void testScreenReaderTimeLineItemNodeElements(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/3/categories/3047/articles/3049");
    	
    	user.customWait().until(ExpectedConditions.elementToBeClickable(article.timelineButton));
    	
    	article.click(article.timelineButton);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("timeline-item-date")));
    	
    	ArrayList<WebElement> nodes = (ArrayList<WebElement>) article.getDriver().findElements(By.className("timeline-item-node"));
    	
    	Boolean allHidden = true;
    	
    	for(WebElement n : nodes){
    		Boolean hidden;
    		try{
    			hidden = n.getAttribute("aria-hidden").toString().equals("true");
    		}catch(Exception e){
    			hidden = false;
    		}
    		
    		allHidden = allHidden && hidden;
    	}
    	
    	assertTrue("Asserts that all nodes have the aria-hidden value of true", allHidden);
    }

    @Test
    public void testScreenReaderTimeLineSpacersElements(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/3/categories/3047/articles/3049"); //.searchForArticle("Babe Ruth");
    	user.customWait().until(ExpectedConditions.elementToBeClickable(article.timelineButton));
    	
    	article.click(article.timelineButton);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("timeline-layout-spacer")));
    	
    	ArrayList<WebElement> nodes = (ArrayList<WebElement>) article.getDriver().findElements(By.className("timeline-layout-spacer"));
    	
    	Boolean allHidden = true;
    	
    	for(WebElement n : nodes){
    		Boolean hidden;
    		try{
    			hidden = n.getAttribute("aria-hidden").toString().equals("true");
    		}catch(Exception e){
    			hidden = false;
    		}
    		
    		allHidden = allHidden && hidden;
    	}
    	
    	assertTrue("Asserts that all nodes have the aria-hidden value of true", allHidden);
    }
    
    @Test
    public void testScreenReaderTimeLineTextsAreNonEmpty(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/3/categories/3047/articles/3049"); //.searchForArticle("Babe Ruth");
    	user.customWait().until(ExpectedConditions.elementToBeClickable(article.timelineButton));
    	
    	article.click(article.timelineButton);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("timeline-item-text")));
    	
    	ArrayList<WebElement> timelineText = (ArrayList<WebElement>) article.getDriver().findElements(By.className("timeline-item-text"));
    	
    	Boolean allNotEmpty = true;
    	
    	for(WebElement t : timelineText){
    		Boolean empty;
    		try{
    			empty = t.getText().isEmpty();
    		}catch(Exception e){
    			empty = true;
    		}
    		
    		allNotEmpty = allNotEmpty && !empty;
    	}
    	
    	assertTrue("Asserts that all nodes have the aria-hidden value of true", allNotEmpty);
    }

    @Test
    public void testScreenReaderTimeLineDatesAreNonEmpty(){
    	article.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/3/categories/3047/articles/3049"); //.searchForArticle("Babe Ruth");
    	user.customWait().until(ExpectedConditions.elementToBeClickable(article.timelineButton));
    	
    	article.click(article.timelineButton);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("timeline-item-date")));
    	
    	ArrayList<WebElement> timelineDate = (ArrayList<WebElement>) article.getDriver().findElements(By.className("timeline-item-date"));
    	
    	Boolean allNotEmpty = true;
    	
    	for(WebElement t : timelineDate){
    		Boolean empty;
    		try{
    			empty = t.getText().isEmpty();
    		}catch(Exception e){
    			empty = true;
    		}
    		
    		allNotEmpty = allNotEmpty && !empty;
    	}
    	
    	assertTrue("Asserts that all nodes have the aria-hidden value of true", allNotEmpty);
    }
    
    @Test
    public void testCapstoneTrademarkLink(){
    	assertTrue("asserts that the terms of use link contains the correct url", 
    			article.verifyLinkContainsURLByElement(home.footer.copyrightLink, "https://www.pebblego.com/copyright"));
    	
    	article.click(article.copyrightLink);
    	
    	assertTrue("asserts that the terms of use link takes the user to the correct page", 
    			article.getDriver().getCurrentUrl().equals("https://www.pebblego.com/copyright"));
    	
    	article.goBack();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.copyrightLink));
    }
    
    @Test
    public void testCommunityAndTeacherResourcesLink(){
    	assertTrue("asserts that the terms of use link contains the correct url", 
    			article.verifyLinkContainsURLByElement(home.footer.educatorsResourcesLink, "https://www.pebblego.com/resources"));
    	
    	article.click(article.educatorsLink);
    	
    	assertTrue("asserts that the terms of use link takes the user to the correct page", 
    			article.getDriver().getCurrentUrl().equals("https://www.pebblego.com/resources"));
    	
    	article.goBack();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.educatorsLink));
    }
    
    @Test
    public void testPebbleGoLogoLink(){
    	assertTrue("Asserts that the pebbleGo logo has the correct href value",
    			article.getDriver().findElement(article.header.logo).getAttribute("href").equals("https://"+user.getEnvironment()+".pebblego.com/modules"));
    }
    
    @Test
    public void testEscapeKeyClosesCapstoneMenu(){
    	article.click(article.header.capstoneDropdown);
    	
    	assertTrue("Asserts that the pebbleGo Capstone Menu is open", user.getDriver().findElement(article.header.capstoneLogo).getAttribute("class").contains("open"));
    
    	article.getDriver().findElement(article.header.capstoneDropdown).sendKeys(Keys.ESCAPE);
    	
    	assertTrue("Asserts that the pebbleGo Capstone Menu is open", !user.getDriver().findElement(article.header.capstoneLogo).getAttribute("class").contains("open"));
    
    	article.click(article.header.capstoneDropdown);
    	
    	assertTrue("Asserts that the pebbleGo Capstone Menu is open", user.getDriver().findElement(article.header.capstoneLogo).getAttribute("class").contains("open"));
    
    	article.getDriver().findElement(article.header.pebbleGoNextLink).sendKeys(Keys.ESCAPE);
    	
    	assertTrue("Asserts that the pebbleGo Capstone Menu is open", !user.getDriver().findElement(article.header.capstoneLogo).getAttribute("class").contains("open"));
 
    	article.click(article.header.capstoneDropdown);
    	
    	assertTrue("Asserts that the pebbleGo Capstone Menu is open", user.getDriver().findElement(article.header.capstoneLogo).getAttribute("class").contains("open"));
    
    	article.getDriver().findElement(article.header.capstoneInteractiveLink).sendKeys(Keys.ESCAPE);
    	
    	assertTrue("Asserts that the pebbleGo Capstone Menu is open", !user.getDriver().findElement(article.header.capstoneLogo).getAttribute("class").contains("open"));
    	
    	article.click(article.header.capstoneDropdown);
    	
    	assertTrue("Asserts that the pebbleGo Capstone Menu is open", user.getDriver().findElement(article.header.capstoneLogo).getAttribute("class").contains("open"));
    
    	article.getDriver().findElement(article.header.signOutLink).sendKeys(Keys.ESCAPE);
    	
    	assertTrue("Asserts that the pebbleGo Capstone Menu is open", !user.getDriver().findElement(article.header.capstoneLogo).getAttribute("class").contains("open"));
    }
}
