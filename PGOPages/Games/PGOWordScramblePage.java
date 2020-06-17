package Games;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PGOStudentResources.StudentHomePage;
import SharedClasses.BasePage;

public class PGOWordScramblePage extends BasePage{
	public By closeButton = By.id("btn-close");
	public By gameInstruction = By.id("game-instruction");
	public By startButton = By.id("start-button-link"); //By.xpath("//*[@id=\"start-button-link\"]/div"); 
	public By matchedOne = By.id("matched-1");
	public By matchedTwo = By.id("matched-2");
	public By matchedThree = By.id("matched-3");
	public By matchedFour = By.id("matched-4");
	public By matchedFive = By.id("matched-5");
	public By matchedSix = By.id("matched-6");
	private By homeBreadcrumb = By.xpath("//*[@id=\"home-crumb\"]/a");
	public By[] initialVisibleElements = {closeButton, gameInstruction, startButton,
			matchedOne, matchedTwo, matchedThree, matchedFour, matchedFive, matchedSix};
	public By imageContainer = By.xpath("//div[@id=\"picture-border\"]/div");
	public By imageClue = By.className("landscapeSuggestionBubble");
	public By hint = By.xpath("//*[contains(@class, \"suggestionBubble\")]");
	public By[] afterStartElements = {closeButton, gameInstruction, matchedOne, matchedTwo, 
			matchedThree, matchedFour, matchedFive, matchedSix, imageContainer, imageClue};
	
	public PGOWordScramblePage(WebDriver driver){
		pageDriver = driver;
	}
	
	public PGOChooseGamePage clickCloseButton(){
		click(closeButton);
		return new PGOChooseGamePage(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public PGOWordScramblePage clickStartButton(){
		click(startButton);
		return this;
	}
	
	public By[] getAfterStartElements(){
		return afterStartElements;
	}
	
	public boolean verifyLettersAppear(){
		boolean lettersAppear = false;
		List<WebElement> letters = pageDriver.findElements(By.className(""));
		if(letters.size() > 0){
			lettersAppear = true;
		}
		return lettersAppear;
	}
	
	public StudentHomePage clickHomeBradcrumb(){
		click(homeBreadcrumb);
		return new StudentHomePage(pageDriver);
	}
	
	public String getImage(){
		String attribute = pageDriver.findElement(imageContainer).getAttribute("style");
		String image = attribute.substring(attribute.lastIndexOf("/") + 1, attribute.lastIndexOf("g")+1);
		return image;
	}
	
	public String getImageAsset(){
		String attribute = pageDriver.findElement(imageContainer).getAttribute("style");
		return attribute.substring(attribute.lastIndexOf("url") + 5, attribute.lastIndexOf("g")+1);
	}
	
	public String getHintText(){
		return pageDriver.findElement(hint).getText();	
	}
	
	public Boolean areAllAnswerLettersPresent(String present){
		char[] letters = present.replaceAll(" ", "").toCharArray();
		Boolean allPresent = true;
		
		List<WebElement> webLetters = pageDriver.findElements(By.xpath("//li[contains(@class,\"singleLetter\")]"));
		
		
		for(char c : letters){
			boolean found = false;
			for(WebElement w: webLetters){
				if(!w.getText().toString().equals("")){
					if(w.getText().charAt(0)==c){
						found = true;
						webLetters.remove(w);
						break;
					}
				}
			}
			
			allPresent = allPresent && found;
		}
		return allPresent;
	}
}
