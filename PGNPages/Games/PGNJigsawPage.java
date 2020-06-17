package Games;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import PGNStudentResources.StudentHeader;
import PGNStudentResources.StudentHomePage;
import SharedClasses.BasePage;

public class PGNJigsawPage extends BasePage{
	public By startButton = By.xpath("//*[@id=\"next-puzzle\"]/div");
	public By closeButton = By.id("close-wrapper");
	public By gameInstructions = By.id("game-instruction");
	public By gamePlaceholder = By.id("jigsaw-placeholder");
	private By homeBreadcrumb = By.xpath("//*[@id=\"breadcrumb\"]/ul/a[1]/li/span");
	public By[] initialVisibleElements = {startButton, closeButton, gameInstructions, gamePlaceholder};
	public StudentHeader header;
	
	public PGNJigsawPage(WebDriver driver){
		pageDriver = driver;
		header = new StudentHeader(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public PGNChooseGamePage clickCloseButton(){
		click(closeButton);
		return new PGNChooseGamePage(pageDriver);
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
