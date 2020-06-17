package Games;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import CMSPages.EditGamesPage;
import PGOStudentResources.StudentHomePage;
import SharedClasses.BasePage;

public class PGOZoomPage extends BasePage{
	public By countDownClock = By.id("countdownClock");
	public By startButton = By.xpath("//*[@id=\"newZoomButton\"]/div");
	public By gameInstruction = By.id("game-instruction");
	public By gameNotifications = By.id("gameNotifications");
	public By answerOne = By.xpath("//*[@id='gameAnswers']/ul/li[1]");
	//*[@id="gameAnswers"]/ul/li[2]
	public By answerTwo = By.xpath("//*[@id='gameAnswers']/ul/li[2]");
	public By answerThree = By.xpath("//*[@id='gameAnswers']/ul/li[3]");
	public By answerFour = By.xpath("//*[@id='gameAnswers']/ul/li[4]");
	public By closeButton = By.id("btn-close");
	private By homeBreadcrumb = By.xpath("//*[@id=\"home-crumb\"]/a");
	public By[] initialVisibleElements = {countDownClock, startButton, gameInstruction, 
			gameInstruction, closeButton};
	public By[] afterOneRoundElements = {countDownClock, startButton, gameInstruction, gameNotifications,
			answerOne, answerTwo, answerThree, answerFour, closeButton};
	public By zoomPicture = By.xpath("//*[@id=\"pictureFrame\"]/div");
	
	
	public PGOZoomPage(WebDriver driver){
		pageDriver = driver;
	}
	
	public PGOChooseGamePage clickCloseButton(){
		click(closeButton);
		return new PGOChooseGamePage(pageDriver);
	}
	
	public PGOZoomPage clickStartButton(){
		click(startButton);
		return this;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public By[] getAfterOneRoundElements(){
		return afterOneRoundElements;
	}
	
	public StudentHomePage clickHomeBradcrumb(){
		click(homeBreadcrumb);
		return new StudentHomePage(pageDriver);
	}
	
	public String getCurrentPicture(){
		String style = pageDriver.findElement(zoomPicture).getAttribute("style");
		
		String picture = style.substring(style.lastIndexOf("/")+1, style.lastIndexOf("\""));
		return picture;
	}
	
	public String getCurrentPictureAssetLink(){
		String style = pageDriver.findElement(zoomPicture).getAttribute("style");
		
		String picture = style.substring(style.indexOf("url(")+5, style.lastIndexOf("\""));
		return picture;
	}
	
	public String getGameNotifications(){
		return pageDriver.findElement(gameNotifications).getText();
	}
	
	public String getFirstAnswer(){
		return pageDriver.findElement(answerOne).toString();
	}
	
	public String getSecondAnswer(){
		return pageDriver.findElement(answerTwo).toString();
	}
	
	public String getThirdAnswer(){
		return pageDriver.findElement(answerThree).toString();
	}
	
	public String getFourthAnswer(){
		return pageDriver.findElement(answerFour).toString();
	}
	
	public Boolean isAnswerCorrect(String answer){
		WebElement answerElement = pageDriver.findElement(By.xpath("//*[@id=\"gameAnswers\"]/ul/li[contains(text(),\""+answer+"\"]"));
		
		//The two class types are correct and incorrect, so an incorrect class could contain correct
		//So if the class contains incorrect, we will return false and if it doesn't, we can assume it is correct
		return answerElement.getClass().toString().contains("correct") && !answerElement.getClass().toString().contains("incorrect");
	}
	
	public EditGamesPage clickModuleByName(String module){
		By element = By.id("test-" + module);
		try {
			moveToElement(element);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		click(element); 
		
		return new EditGamesPage(pageDriver);
	}
}
