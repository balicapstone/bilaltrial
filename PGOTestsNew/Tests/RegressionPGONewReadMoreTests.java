package Tests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import PGOPagesNew.PGONewLoginPage;
import PGOReadMoreNew.PGONewReadMoreLandingPage;
import PGOReadMoreNew.PGONewReadMorePlayer;
import PGOStudentResources.PGONewArticlePage;
import PGOStudentResources.PGONewContentSelectionPage;
import PGOStudentResources.StudentHomePageNew;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;
import UserClasses.UserInfo;

public class RegressionPGONewReadMoreTests {
	static PGONewLoginPage loginPage;
	static StudentHomePageNew home;
	static PGONewArticlePage article;
	static PGONewReadMoreLandingPage landingPage;
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
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(content.header.randomArticleButton));
    		
    		article = content.header.clickRandomArticleButton();
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.tabSix));
    		
    		article.clickTabSix();
    		Boolean foundBook = false;
    		
    		while(!foundBook){
	    		try{
	        		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("related-book-0")));
	        		
	        		landingPage = article.clickBookByInt("0");
	        		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(landingPage.playBookButton));
	        		foundBook = true;
	    		}
	    		catch(Exception e){
	    			article = article.header.clickRandomArticleButton();
	        		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.tabSix));
	        		
	        		article.clickTabSix();
	    		}
    		}
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
    public void findBook(){
    	assertTrue("Asserts that the user can navigate to a book", landingPage.isElementPresent(landingPage.playBookButton));
    	
    	assertTrue("Asserts that the book button is unclickable", landingPage.click(landingPage.bookButton));
    	assertTrue("Asserts that the cite button is selectable", 
    			landingPage.getDriver().findElement(landingPage.citeButton).getAttribute("class").contains("book-layout-button-hover"));
    	assertTrue("Asserts that the book button is selectable",
    			landingPage.getDriver().findElement(landingPage.bookButton).getAttribute("class").contains("book-layout-button-off"));
    	assertTrue("Asserts that the info button is selectable",
    			landingPage.getDriver().findElement(landingPage.infoButton).getAttribute("class").contains("book-layout-button-hover"));
    }
    
    @Test
    public void testCiteButton(){
    	landingPage.click(landingPage.citeButton);
    	
    	user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(landingPage.playBookButton));
    	
    	assertTrue("Asserts that the user can navigate to the cite part of the book landing page and see the print button",
    			landingPage.isElementPresent(landingPage.printCitationButton));
    	
    	assertTrue("Asserts that the cite button is unclickable", landingPage.click(landingPage.citeButton));
    	assertTrue("Asserts that the cite button is dimmed to the user", 
    			landingPage.getDriver().findElement(landingPage.citeButton).getAttribute("class").contains("book-layout-button-off"));
    	assertTrue("Asserts that the book button is selectable",
    			landingPage.getDriver().findElement(landingPage.bookButton).getAttribute("class").contains("book-layout-button-hover"));
    	assertTrue("Asserts that the info button is selectable",
    			landingPage.getDriver().findElement(landingPage.infoButton).getAttribute("class").contains("book-layout-button-hover"));
    	
    	landingPage.click(landingPage.bookButton);
    }
    
    @Test
    public void testInfoButton(){
    	landingPage.click(landingPage.infoButton);
    	
    	assertTrue("Asserts that the cite button is selectable", 
    			landingPage.getDriver().findElement(landingPage.citeButton).getAttribute("class").contains("book-layout-button-hover"));
    	assertTrue("Asserts that the book button is selectable",
    			landingPage.getDriver().findElement(landingPage.bookButton).getAttribute("class").contains("book-layout-button-hover"));
    	assertTrue("Asserts that the info button is dimmed",
    			landingPage.getDriver().findElement(landingPage.infoButton).getAttribute("class").contains("book-layout-button-off"));
    	assertTrue("Asserts that the info button is unclickable", landingPage.click(landingPage.infoButton));
    	
    	landingPage.click(landingPage.bookButton);
    }
    
    @Test
    public void testPlayThroughBook(){
    	PGONewReadMorePlayer book = landingPage.playBook();
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(book.rightPage));
    	
    	assertTrue("Asserts that the right arrow element is present", book.isElementPresent(book.rightArrow));
    	assertTrue("Asserts that the left arrow element is not present", !book.isElementPresent(book.leftArrow));

    	Boolean onLastPage = false;
    	while(!onLastPage){
     		try {
				book.moveToElement(book.rightArrow);
	    		book.click(book.rightArrow);
	    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("epub-view")));
			} catch (Exception e) {
				onLastPage = true;
			}
    	}

    	assertTrue("Asserts that the only page present is the left page",
    			book.getDriver().findElement(book.lastPage).isEnabled());
    	assertTrue("Asserts that the slider thumbnail is at 100%",
    			book.getProgressBarPercentage() == 100); //.getDriver().findElement(book.scrollBarProgress).getAttribute("style").equals("width: 100%;"));
    	assertTrue("Asserts that the play button is not present",
    			!book.isElementPresent(book.playButton));
    	
    	landingPage = book.closeBookReader();
    }
    
    @Test
    public void testPlayThroughBookUsingKeyboard(){
    	PGONewReadMorePlayer book = landingPage.playBook();
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(book.rightPage));
    	
    	assertTrue("Asserts that the right arrow element is present", book.isElementPresent(book.rightArrow));
    	assertTrue("Asserts that the left arrow element is not present", !book.isElementPresent(book.leftArrow));
    	
    	while(!book.isElementEnabled(book.lastPage)){
     		try {
				book.turnPageRight();
			} catch (Exception e) {
				System.out.print(e);
			}
    	}

    	assertTrue("Asserts that the slider thumbnail is at 100%",
    			book.getProgressBarPercentage() == 100);
    	assertTrue("Asserts that the play button is not present",
    			!book.isElementPresent(book.playButton));
    	
    	landingPage = book.closeBookReader();
    }
	
    @Test
    public void testProgressBarWorksMovingRight(){
    	PGONewReadMorePlayer book = landingPage.playBook();
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(book.rightPage));
    	
     	book.turnPageRight();

    	Float progress = book.getProgressBarPercentage();
    	
    	book.turnPageRight();
    	
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(book.bookPage));

    	Float progressMoved = book.getProgressBarPercentage();
    	
    	assertTrue("Asserts that the progress bar is moving so that it takes up more of the screen",
    			progressMoved > progress);
    	
    	landingPage = book.closeBookReader();
    }
    
    @Test
    public void testProgressBarWorksMovingLeft(){
    	PGONewReadMorePlayer book = landingPage.playBook();
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(book.rightPage));
    	
    	book.turnPageRight();
    	book.turnPageRight();
    	book.turnPageRight();
    	
    	Float progress = book.getProgressBarPercentage();
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(book.playButton));
    	book.turnPageLeft();

    	Float progressMoved = book.getProgressBarPercentage();
    	
    	assertTrue("Asserts that the progress bar is moving so that it takes up less of the screen",
    			progressMoved < progress);
    	
    	landingPage = book.closeBookReader();
    }
    
    @Test
    public void testProgressPlayButtonTurnsPauseButtonAndBack(){
    	PGONewReadMorePlayer book = landingPage.playBook();
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(book.rightPage));
    	
    	book.turnPageRight();
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(book.playButton));
    	
    	book.pressPlay();
    	user.customWait().until(ExpectedConditions.attributeContains(book.playButton, "aria-pressed", "true"));
    	
    	assertTrue("Asserts that the play button image does not have the 'play' class value",
    			!book.getDriver().findElement(book.playButtonImage).getAttribute("class").contains("book-reader-play-button-icon play"));
    	
    	user.customWait().until(ExpectedConditions.attributeContains(book.playButton, "aria-pressed", "false"));
    	
    	assertTrue("Asserts that the play button image has the 'play' class value",
    			book.getDriver().findElement(book.playButtonImage).getAttribute("class").contains("book-reader-play-button-icon play"));
    	
    	landingPage = book.closeBookReader();
    }
}
