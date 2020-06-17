package Games;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import PGOStudentResources.StudentHomePage;
import PGOStudentResources.TopModuleMenu;
import SharedClasses.BasePage;

public class PGOChooseGamePage extends BasePage{
	public TopModuleMenu topModuleMenu;
	private By topHamburgerButton = By.id("hamburger-icon");
	public By zoom = By.xpath("//*[@id='zoom']/a/div[2]");
	public By jigsaw = By.xpath("//*[@id='puzzle']/a/div[2]");
	public By quickMatch = By.xpath("//*[@id='quick']/a/div[2]");
	public By multiMatch = By.xpath("//*[@id='multi']/a/div[2]");
	public By whack = By.xpath("//*[@id='whack']/a/div[2]");
	public By wordScramble = By.xpath("//*[@id='scrambled']/a/div[2]");
	private By homeBreadcrumb = By.xpath("//*[@id=\"home-crumb\"]/a");
	
	public PGOChooseGamePage(WebDriver driver){
		pageDriver = driver;
		topModuleMenu = new TopModuleMenu(driver);
	}
	
	public void clickHamburgerButton(){
		click(topHamburgerButton);
	}
	
	public PGOZoomPage clickZoom(){
		click(zoom);
		return new PGOZoomPage(pageDriver);
	}
	
	public PGOJigsawPage clickJigsaw(){
		click(jigsaw);
		return new PGOJigsawPage(pageDriver);
	}
	
	public PGOQuickMatchPage clickQuickMatch(){
		click(quickMatch);
		return new PGOQuickMatchPage(pageDriver);
	}
	
	public PGOMultiMatchPage clickMultiMatch(){
		click(multiMatch);
		return new PGOMultiMatchPage(pageDriver);
	}
	
	public PGOWhackPage clickWhack(){
		click(whack);
		return new PGOWhackPage(pageDriver);
	}
	
	public PGOWordScramblePage clickWordScramble(){
		click(wordScramble);
		return new PGOWordScramblePage(pageDriver);
	}
	
	public StudentHomePage clickHomeBradcrumb(){
		click(homeBreadcrumb);
		return new StudentHomePage(pageDriver);
	}
}
