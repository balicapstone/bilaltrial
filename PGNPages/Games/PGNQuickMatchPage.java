package Games;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import PGNStudentResources.StudentHeader;
import PGNStudentResources.StudentHomePage;
import SharedClasses.BasePage;

public class PGNQuickMatchPage extends BasePage{
	public By gameInstruction = By.id("game-instruction");
	public By emptyAnswerOne = By.id("matched-pair-image-1");
	public By emptyAnswerTwo = By.id("matched-pair-image-2");
	public By emptyAnswerThree = By.id("matched-pair-image-3");
	public By emptyAnswerFour = By.id("matched-pair-image-4");
	public By emptyAnswerFive = By.id("matched-pair-image-5");
	public By emptyAnswerSix = By.id("matched-pair-image-6");
	public By startButton = By.xpath("//*[@id=\"start-button-link\"]/div");//By.id("start-button-link");
	public By leftAnswerBox = By.id("left-big-holder");
	public By rightAnswerBox = By.id("right-big-holder");
	public By roundCount = By.id("current-round");
	public By failsCount = By.id("total-round");
	public By backToAllGames = By.id("close-wrapper");
	private By homeBreadcrumb = By.xpath("//*[@id=\"home-crumb\"]/a");
	public By[] initialVisibleElements = {gameInstruction, emptyAnswerOne, emptyAnswerTwo, emptyAnswerThree,
			emptyAnswerFour, emptyAnswerFive, emptyAnswerSix, startButton, 
			roundCount, failsCount, backToAllGames};
	public By matchButton = By.id("match-button-link");
	public By playingLeftHolder = By.id("left-big-holder");
	public By playingRightHolder = By.id("right-big-holder");
	public By[] gameStartedElements = {gameInstruction, emptyAnswerOne, emptyAnswerTwo, emptyAnswerThree,
			emptyAnswerFour, emptyAnswerFive, emptyAnswerSix, playingLeftHolder, playingRightHolder, 
			roundCount, failsCount, matchButton};
	public StudentHeader header;
	public By rightAnswerBoxImage = By.xpath("//*[@id='right-big-holder']/img");
	
	public PGNQuickMatchPage(WebDriver driver){
		pageDriver = driver;
		header = new StudentHeader(pageDriver);
	}
	
	public void clickStartButton(){
		click(startButton);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public By[] getGameStartedElements(){
		return gameStartedElements;
	}
	
	public PGNChooseGamePage clickCloseButton(){
		click(backToAllGames);
		return new PGNChooseGamePage(pageDriver);
	}
	
	public StudentHomePage clickHomeBradcrumb(){
		click(homeBreadcrumb);
		return new StudentHomePage(pageDriver);
	}
	
	public String getRightImage(){
		String attribute = pageDriver.findElement(rightAnswerBoxImage).getAttribute("src");
		String image = attribute.substring(attribute.lastIndexOf("/") + 1);
		
		return image;
	}
	
	public String getRightImageAsset(){
		return pageDriver.findElement(rightAnswerBoxImage).getAttribute("src");
	}
}
