package Games;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import SharedClasses.BasePage;

public class PGNChooseGamePage extends BasePage{
	public By stateZoomLink = By.xpath("//img[contains(@src, 'Zoom')]");
	public By stateJigsawLink = By.xpath("//img[contains(@src, 'Jigsaw')]"); 
	public By flagMultiMatchLink = By.xpath("//img[contains(@src, 'Multi_Match')]");
	public By capitalQuickMatchLink = By.xpath("//img[contains(@src, 'Quick_Match')]");
	public By scrambleLink = By.xpath("//img[contains(@src, 'Word_Scramble')]");
	public By communityButton = By.id("bottom-community");

	
	public PGNChooseGamePage(WebDriver driver){
		pageDriver = driver;
	}
	
	public PGNZoomPage clickZoom(){
		click(stateZoomLink);
		return new PGNZoomPage(pageDriver);
	}
	
	public PGNJigsawPage clickJigsaw(){
		click(stateJigsawLink);
		return new PGNJigsawPage(pageDriver);
	}
	
	public PGNMultiMatchPage clickMultiMatch(){
		click(flagMultiMatchLink);
		return new PGNMultiMatchPage(pageDriver);
	}
	
	public PGNQuickMatchPage clickQuickMatch(){
		click(capitalQuickMatchLink);
		return new PGNQuickMatchPage(pageDriver);
	}
	
	public PGNWordScramblePage clickWordScramble(){
		click(scrambleLink);
		return new PGNWordScramblePage(pageDriver);
	}
}
