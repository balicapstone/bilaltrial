package Games;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import PGNStudentResources.StudentHeader;
import PGNStudentResources.StudentHomePage;
import SharedClasses.BasePage;

public class PGNWhackPage extends BasePage{
	public By closeButton = By.id("close-wrapper");
	public By startButton = By.xpath("//*[@id=\"start-screen\"]/div/div"); //*[@id='start-screen']/div/div");
	public By gameInstruction = By.id("game-instruction");
	public By clueImage = By.xpath("//*[@id='display-wrapper']/div[1]/div");
	public By roundWrapper = By.id("rounds-wrapper");
	public By scoreContainer = By.className("scoreContainer"); 
	private By homeBreadcrumb = By.xpath("//*[@id=\"home-crumb\"]/a");
	public By[] initialVisibleElements = {startButton, gameInstruction, 
			clueImage, roundWrapper, scoreContainer};
	public StudentHeader header;
	
	public PGNWhackPage(WebDriver driver){
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
	
	public void clickStartButton(){
		click(startButton);
	}
	
	public boolean verifyImagesAppearing(){
		List<WebElement> elements = pageDriver.findElements(By.className("imageAppended"));
		boolean answersAppearing = false;
		if(elements.size() > 0){
			answersAppearing = true;
		}
		return answersAppearing;
	}
	
	public StudentHomePage clickHomeBradcrumb(){
		click(homeBreadcrumb);
		return new StudentHomePage(pageDriver);
	}
}
