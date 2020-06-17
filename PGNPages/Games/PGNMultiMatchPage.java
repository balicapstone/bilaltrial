package Games;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import PGNStudentResources.StudentHeader;
import PGNStudentResources.StudentHomePage;
import SharedClasses.BasePage;

public class PGNMultiMatchPage extends BasePage{
	public By gameInstruction = By.id("game-instruction");
	public By closeButton = By.id("close-wrapper");
	public By questionInfo = By.xpath("//*[@id='predator-label']/span/span[1]");
	public By questionInfoPopup = By.xpath("//*[@id='predator-label']/span/span[2]/span");
	public By questionInfoPopupClose = By.xpath("//*[@id='predator-label']/span/span[2]/span/a");
	public By answerInfo = By.xpath("//*[@id='prey-label']/span/span[1]");
	public By answerInfoPopup = By.xpath("//*[@id='prey-label']/span/span[2]/span");
	public By answerInfoPopupClose = By.xpath("//*[@id='prey-label']/span/span[2]/span/a");
	public By roundsIndicator = By.id("rounds-indicator");
	public By predatorOne = By.id("predator-1");
	public By predatorTwo = By.id("predator-2");
	public By predatorThree = By.id("predator-3");
	public By predatorFour = By.id("predator-4");
	public By preyOne = By.id("prey-1");
	public By preyTwo = By.id("prey-2");
	public By preyThree = By.id("prey-3");
	public By preyFour = By.id("prey-4");
	private By homeBreadcrumb = By.xpath("//*[@id=\"home-crumb\"]/a");
	public StudentHeader header;
	
	public By[] initialVisibleElements = {gameInstruction, closeButton, questionInfo, answerInfo, 
			roundsIndicator, predatorOne, predatorTwo, predatorThree, predatorFour, preyOne,
			preyTwo, preyThree, preyFour};
	public By[] afterClickElements = {questionInfoPopup, questionInfoPopupClose, answerInfoPopup, answerInfoPopupClose};
	
	public PGNMultiMatchPage(WebDriver driver){
		pageDriver = driver;
		header = new StudentHeader(pageDriver);
	}
	
	public PGNChooseGamePage clickCloseButton(){
		click(closeButton);
		return new PGNChooseGamePage(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public By[] getAfterClickElements(){
		return afterClickElements;
	}
	
	public void clickQuestionInfo(){
		click(questionInfo);
	}
	
	public void clickAnswerInfo(){
		click(answerInfo);
	}
	
	public void clickQuestionPopupClose(){
		click(questionInfoPopupClose);
	}
	
	public void clickAnswerPopupClose(){
		click(answerInfoPopupClose);
	}
	
	public StudentHomePage clickHomeBradcrumb(){
		click(homeBreadcrumb);
		return new StudentHomePage(pageDriver);
	}
}
