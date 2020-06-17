package CMSGames;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CMSPages.CMSHomePage;
import CMSPages.EditGamesPage;

public class EditWordScrambleGame extends CMSHomePage{
	public By answerInput = By.id("test-input-answer");
	public By imageInput = By.id("test-input-image");
	public By hintInput = By.id("test-input-hint");
	public By submitButton = By.id("test-submit-entry");
	public By clearButton = By.id("test-clear-entry");
	
	public EditWordScrambleGame(WebDriver driver){
		super(driver);
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
	
	public void editEntryByInt(int i){
		click(By.id("test-edit-entry-" + i));
	}
	
	public void deleteEntryByInt(int i){
		click(By.id("test-delete-entry-" + i));
	}
	
	public void setAnswer(String answer){
		pageDriver.findElement(answerInput).clear();
		pageDriver.findElement(answerInput).sendKeys(answer);
	}
	
	public void setImage(String image){
		pageDriver.findElement(imageInput).clear();
		pageDriver.findElement(imageInput).sendKeys(image);
	}
	
	public void setHint(String hint){
		pageDriver.findElement(hintInput).clear();
		pageDriver.findElement(hintInput).sendKeys(hint);
	}
	
	public void getAnswer(){
		pageDriver.findElement(answerInput).getText();
	}
	
	public void getImage(){
		pageDriver.findElement(imageInput).getText();
	}
	
	public void getHint(){
		pageDriver.findElement(hintInput).getText();
	}
	
	public void clickSubmit(){
		click(submitButton);
	}
}
