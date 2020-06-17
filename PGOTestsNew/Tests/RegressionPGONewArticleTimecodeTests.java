package Tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import PGOPagesNew.PGONewLoginPage;
import PGOStudentResources.PGONewArticlePage;
import PGOStudentResources.StudentHomePageNew;
import UserClasses.User;
import UserClasses.UserInfo;

public class RegressionPGONewArticleTimecodeTests {
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
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Dinosaurs")));

        	article = home.header.searchForArticleAndClickResult("Lynda Carter");
        	
        	user.customWait().until(ExpectedConditions.elementToBeClickable(article.playAudioButton));
    	} catch(Exception e){
    		user.getWatcher().TakeScreenshot("FailureToRunExecuteBeforeForTimecodeTests");
    	}
    }
    
    @AfterClass
    public static void executeAfter(){
    	user.quit();
    }
    
    @Test
    public void testArticleTextTabOne(){
	    assertTrue("Asserts that all words on the tab are high lighted when the play button is pressed for the first tab", areAllWordsReadInTab(1));
    }
    
    @Test
    public void testArticleTextTabTwo(){
    	assertTrue("Asserts that all words on the tab are high lighted when the play button is pressed for the second tab", areAllWordsReadInTab(2));
    }
    
    @Test
    public void testArticleTextTabThree(){
	    assertTrue("Asserts that all words on the tab are high lighted when the play button is pressed for the third tab", areAllWordsReadInTab(3));
    }
    
    @Test
    public void testArticleTextTabFour(){
    	assertTrue("Asserts that all words on the tab are high lighted when the play button is pressed for the fourth tab", areAllWordsReadInTab(4));
    }
    
    @Test
    public void testArticleTextTabFive(){
	    assertTrue("Asserts that all words on the tab are high lighted when the play button is pressed for the fifth tab", areAllWordsReadInTab(5));
    }
    
    public Boolean areAllWordsReadInTab(int tab){
    	article.clickTabByInt(tab);
    	
    	ArrayList<WebElement> words = (ArrayList<WebElement>) article.getDriver().findElements(By.xpath("//span[@class=\"highlight\"]"));
    	//(By.xpath("//div[@class=\"playback-text\"]//span[@class=\"highlight\"]"));
    	ArrayList<WebElement> foundWords = new ArrayList<WebElement>();
    	
    	article.click(article.playAudioButton);
    	
    	while(user.getDriver().findElement(article.playAudioButton).getAttribute("aria-pressed").equals("true")){
    		try{
    			WebElement word = article.getDriver().findElement(By.xpath("//span[@class=\"highlight active\"]"));
    			if(!foundWords.contains(word)){
            		foundWords.add(word);
    			}
    		} catch(Exception e){
    			
    		}
    	}
    	
    	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"playback-text\"]//span[@class=\"highlight active\"]"))).isDisplayed()
    	
    	Boolean allHighlighted = true;
    	for(int j = 0; j < words.size(); j++){
    		String firstWord = words.get(j).getText();
    		String highlightedWord = "";
    		try{
    			highlightedWord = foundWords.get(j).getText();
    		} catch(Exception e){
    			
    		}

    		
    		allHighlighted = allHighlighted & firstWord.equals(highlightedWord);
    	}
    	
    	return allHighlighted;
    }
}
