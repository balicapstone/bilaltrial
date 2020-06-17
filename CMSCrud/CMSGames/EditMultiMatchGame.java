package CMSGames;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CMSPages.CMSHomePage;
import CMSPages.EditGamesPage;

public class EditMultiMatchGame extends CMSHomePage{
	public By nameOneInput = By.id("test-input-label");
	public By nameOneImage = By.id("test-input-image-one");
	public By nameTwoInput = By.id("test-input-name");
	public By nameTwoImage = By.id("test-input-image-two");
	public By selectARound = By.id("test-input-round");
	public By submitButton = By.id("test-submit-entry");
	public By clearButton = By.id("test-clear-entry");
	public By editRoundsLink = By.linkText("Edit Rounds");
	
	public By addRoundButton = By.id("test-submit-entry");
	public By returnToMainLink = By.linkText("Go back to Main Form");
	
	public EditMultiMatchGame(WebDriver driver){
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
		click(By.id("test-edit-entry-"+i));
	}
	
	public void deleteEntryByInt(int i){
		click(By.id("test-delete-entry-"+i));
	}
	
	public void addRound(){
		click(addRoundButton);
	}
	
	public void deleteRoundByInt(int i){
		click(By.id("test-delete-entry-"+i));
	}
	
	public void setNameOne(String label){
		pageDriver.findElement(nameOneInput).clear();
		pageDriver.findElement(nameOneInput).sendKeys(label);
	}
	
	public void setImageOne(String image){
		pageDriver.findElement(nameOneImage).clear();
		pageDriver.findElement(nameOneImage).sendKeys(image);
	}
	
	public void setNameTwo(String name){
		pageDriver.findElement(nameTwoInput).clear();
		pageDriver.findElement(nameTwoInput).sendKeys(name);
	}
	
	public void setImageTwo(String image){
		pageDriver.findElement(nameTwoImage).clear();
		pageDriver.findElement(nameTwoImage).sendKeys(image);
	}
	
	public String getNameOne(String label){
		return pageDriver.findElement(nameOneInput).getText();
	}
	
	public String getImageOne(String image){
		return pageDriver.findElement(nameOneImage).getText();
	}
	
	public String getNameTwo(String name){
		return pageDriver.findElement(nameTwoInput).getText();
	}
	
	public String getImageTwo(String image){
		return pageDriver.findElement(nameTwoImage).getText();
	}
	
	public void clickSubmit(){
		click(submitButton);
	}
}
