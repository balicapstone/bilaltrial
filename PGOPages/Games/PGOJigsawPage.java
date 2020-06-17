package Games;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PGOStudentResources.StudentHomePage;
import SharedClasses.BasePage;

public class PGOJigsawPage extends BasePage{
	public By startButton = By.xpath("//*[@id=\"next-puzzle\"]/div");
	public By closeButton = By.id("btn-close");
	public By gameInstructions = By.id("game-instruction");
	public By gamePlaceholder = By.id("jigsaw-placeholder");
	private By homeBreadcrumb = By.xpath("//*[@id=\"home-crumb\"]/a");
	public By[] initialVisibleElements = {startButton, closeButton, gameInstructions, gamePlaceholder};
	
	public PGOJigsawPage(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public PGOChooseGamePage clickCloseButton(){
		click(closeButton);
		return new PGOChooseGamePage(pageDriver);
	}
	
	public boolean verifyPiecesLoaded(){
		boolean piecesLoaded = false;
		List<WebElement> pieces = pageDriver.findElements(By.className("jigsaw-image-piece"));
		if(pieces.size() > 0){
			piecesLoaded = true;
		}
		return piecesLoaded;
	}
	
	public StudentHomePage clickHomeBradcrumb(){
		click(homeBreadcrumb);
		return new StudentHomePage(pageDriver);
	}
}
