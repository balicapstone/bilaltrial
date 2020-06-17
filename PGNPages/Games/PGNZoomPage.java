package Games;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import PGNStudentResources.StudentHeader;
import PGNStudentResources.StudentHomePage;
import SharedClasses.BasePage;

public class PGNZoomPage extends BasePage{
	public By countDownClock = By.id("countdownClock");
	public By startButton = By.id("newZoomButton"); //("//*[@id=\"newZoomButton\"]/div");
	public By gameInstruction = By.id("game-instruction");
	public By gameNotifications = By.id("gameNotifications");
	public By answerOne = By.xpath("//*[@id='gameAnswers']/ul/li[1]");
	//*[@id="gameAnswers"]/ul/li[2]
	public By answerTwo = By.xpath("//*[@id='gameAnswers']/ul/li[2]");
	public By answerThree = By.xpath("//*[@id='gameAnswers']/ul/li[3]");
	public By answerFour = By.xpath("//*[@id='gameAnswers']/ul/li[2]");
	public By closeButton = By.id("close-wrapper");
	private By homeBreadcrumb = By.xpath("//*[@id=\"home-crumb\"]/a");
	public By[] initialVisibleElements = {countDownClock, startButton, gameInstruction, 
			gameInstruction, closeButton};
	public By[] afterOneRoundElements = {countDownClock, startButton, gameInstruction, gameNotifications,
			answerOne, answerTwo, answerThree, answerFour};
	public StudentHeader header;
	public By zoomPicture = By.xpath("//*[@id=\"pictureFrame\"]/div");
	
	public PGNZoomPage(WebDriver driver){
		pageDriver = driver;
		header = new StudentHeader(pageDriver);
	}
	
	public PGNChooseGamePage clickCloseButton(){
		click(closeButton);
		return new PGNChooseGamePage(pageDriver);
	}
	
	public void clickStartButton(){
		click(startButton);
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
}
