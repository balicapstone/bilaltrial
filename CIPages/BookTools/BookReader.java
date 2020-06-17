package BookTools;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CITests.CIPages.StudentHomePage;
import SharedClasses.BasePage;

public class BookReader extends BasePage{
	public By readPageByPageButton = By.id("start-btn");
	public By readAllAtOnceButton = By.id("auto-start-btn");
	public By closeReaderButton = By.id("navigation-exit");
	public By pageButton = By.id("navigation-pager");
	public By wordButton = By.id("navigation-highlightwords");
	public By audioButton = By.id("navigation-mute");
	public By contentsButton = By.xpath("//*[@id=\"navigation-contents\"]/div"); 
	public By glossaryButton = By.id("navigation-glossary");
	public By fullScreenButton = By.cssSelector("#navigation-fullscreen > div");
	public By toolbarHandle = By.id("handle");
	//Elements that appear after the startButton is pressed.
	public By backButton = By.id("leftCornerHS");
	public By nextButton = By.id("rightCornerHS");
	public By playButton = By.id("navigation-playpause");
	public By[] initialVisibleElements = {readPageByPageButton, closeReaderButton, pageButton, 
			wordButton, audioButton, contentsButton, glossaryButton, fullScreenButton};
	
	public By contentModal = By.id("contents-panel");
	public By glossaryModal = By.id("");
	public By pageModal = By.id("navigation-regional");
	
	//Specific elements for Ten Little Kittens
	public By beginning;
	public By copyright;
	public By glossary;
	public By sheetMusic;
	public By toLearnMore;
	
	public BookReader(WebDriver driver){
		pageDriver = driver;
		pageDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	public StudentHomePage exitReader(){
		click(closeReaderButton);
		return new StudentHomePage(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public BookReader startPageByPage(){
		click(readPageByPageButton);
		return this;
	}
	
	public BookReader readBookPageByPage(){
		click(readPageByPageButton);
		waitForElement(nextButton);
		while(isElementPresent(nextButton)){
			click(nextButton);
			waitImplicitly(1);
		}
		return this;
	}
	
	public BookReader startBookAllAtOnce(){
		click(readAllAtOnceButton);
		return this;
	}
	
	public boolean verifyUserCanPageThroughBook(int numberOfPages){
		boolean canRead = false;
		click(readPageByPageButton);
		waitForElement(nextButton);
		try{
			for(int i = 1; i < numberOfPages; i++){
				click(nextButton);
				waitImplicitly(5);
			}
			canRead = true;
		} catch(Exception e){
			
		}
		
		return canRead;
	}
	
	public StudentHomePage closeBookReader(){
		click(closeReaderButton);
		return new StudentHomePage(pageDriver);
	}
	
	public BookReader clickNextPage(){
		click(nextButton);
		return this;
	}
	
	public boolean isBookPaused(){
		boolean paused = false;

		if(pageDriver.findElement(playButton).getAttribute("class").equals("nav-btn paused")){
			paused = true;
		}
		
		return paused;
	}
	
	public boolean isToolbarLocked(){
		boolean locked = false;
		if(pageDriver.findElement(toolbarHandle).getAttribute("class").contains("pinned")){
			locked = true;
		}
		
		return locked;
	}
	
	public String getPageID(){
		String test = pageDriver.findElement(By.xpath("//*[name()='svg']")).getAttribute("id").toString();
		return test;
	}
	
	public void clickPopUpPageByIndex(int page){
		if(!isPageModalOpen()){
			click(pageButton);
		}
		
		By pageElement = By.xpath("//*[@id=\"pagination\"]/div/div[2]/ol/li["+page+"]/span");
		WebDriverWait wait = new WebDriverWait(pageDriver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(pageElement));
		
		click(pageElement);
	}
	
	public boolean hasPageBeenRead(int pageNumber){
		WebElement page = pageDriver.findElement(By.xpath("//*[@id=\"pagination\"]/div/div[2]/ol/li["+pageNumber+"]"));
		boolean read = false;
		if(!page.getAttribute("class").contains("not-read")){
			read = true;
		}
		
		return read;
	}
	
	public boolean isPageModalOpen(){
		WebElement modal = pageDriver.findElement(pageModal);
		boolean open = false;
		if(modal.getAttribute("class").equals("open")){
			open = true;
		}
		return open;
	}
	
	public boolean isAudioOn(){
		WebElement modal = pageDriver.findElement(audioButton);
		boolean on = false;
		if(modal.getAttribute("class").contains("on")){
			on = true;
		}
		return on;
	}
	
	public boolean isWordHighLightingOn(){
		WebElement modal = pageDriver.findElement(wordButton);
		boolean on = false;
		if(modal.getAttribute("class").contains("on")){
			on = true;
		}
		return on;
	}
	
	public BookReader clickContentsButton(){
		click(contentsButton);		
		beginning = By.xpath("//*[@id=\"contents-panel\"]/div/ul/li[1]");
		copyright = By.xpath("//*[@id=\"contents-panel\"]/div/ul/li[2]");//*[@id="contents-panel"]/div/ul/li[2]
		glossary = By.xpath("//*[@id=\"contents-panel\"]/div/ul/li[3]");
		sheetMusic = By.xpath("//*[@id=\"contents-panel\"]/div/ul/li[4]");
		toLearnMore = By.xpath("//*[@id=\"contents-panel\"]/div/ul/li[5]");
		return this;
	}
}
