package PGOReadMoreNew;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SharedClasses.BasePage;
import SharedClasses.PGONewBasePage;
import UserClasses.User;

public class PGONewReadMorePlayer extends PGONewBasePage{
	public By closeButton = By.id("close-button"); 
	public By rightArrow = By.className("page-flipper-right-arrow");
	public By leftArrow = By.className("page-flipper-left-arrow");
	public By playButton = By.id("screen-audio-button");
	public By playButtonImage = By.xpath("//button[@id=\"screen-audio-button\"]/img");
	public By scrollBar = By.className("book-reader-scroll-groove");
	public By scrollBarProgress = By.xpath("//div[contains(@class, \"book-reader-scroll-progress\")]");
	public By scrollBarSelector = By.xpath("//div[contains(@class, \"book-reader-scroll-thumb\")]");
	public By lastPage = By.xpath("//div[contains(@class, \"book-click-eater\")]/div[contains(@class, \"page-flipper-left cover\")]");
	public By leftPage = By.xpath("//div[contains(@class, \"page-flipper-left\")]");
	public By rightPage = By.xpath("//div[contains(@class, \"page-flipper-right\")]");
	public By bookPage = By.className("epub-view");
	   
	
	//By.className("close-button-circle-book-player"); 
	
	public PGONewReadMorePlayer(User u){
		user = u;
		pageDriver = u.getDriver();
		
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("loading-spinner")));
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading-spinner")));
	}
	
	public PGONewReadMoreLandingPage closeBookReader(){
		click(closeButton);
		return new PGONewReadMoreLandingPage(user);
	}
	
	public void turnPageLeft(){
		this.sendLeftArrow(closeButton);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(bookPage));
	}
	
	public void turnPageRight(){
		this.sendRightArrow(closeButton);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(bookPage));
	}
	
	public float getProgressBarPercentage(){
    	String progressString = pageDriver.findElement(scrollBarProgress).getAttribute("style");
    	return Float.parseFloat(progressString.substring(progressString.indexOf(":")+1, progressString.indexOf("%")));
	}
	
	public void pressPlay(){
		click(playButton);
	}
}
