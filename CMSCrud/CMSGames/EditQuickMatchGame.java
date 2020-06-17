package CMSGames;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import CMSPages.CMSHomePage;
import CMSPages.EditGamesPage;

public class EditQuickMatchGame extends CMSHomePage{
	public By labelInput = By.id("test-input-label");
	public By imageInput = By.id("test-input-image");
	public By imageNameInput = By.id("test-input-name");
	public By roundSelect = By.id("test-input-round");
	public By submitButton = By.id("test-submit-entry");
	public By clearButton = By.id("test-clear-entry");
	public By editRoundsLink = By.linkText("Edit Rounds");
	
	public By backToMainFormLink = By.linkText("Go back to Main Form");
	
	public EditQuickMatchGame(WebDriver driver){
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
	
	public void deleteRoundByInt(int i){
		click(By.id("test-delete-entry-"+i));
	}
	
	public void editEntryByInt(int i){
		click(By.id("test-edit-entry-"+i));
	}
	
	public void deleteEntryByInt(int i){
		click(By.id("test-delete-entry-"+i));
	}
	
	public void setImage(String image){
		pageDriver.findElement(imageInput).clear();
		pageDriver.findElement(imageInput).sendKeys(image);
	}
	
	public void setLabel(String label){
		pageDriver.findElement(labelInput).clear();
		pageDriver.findElement(labelInput).sendKeys(label);
	}
	
	public void setImageName(String name){
		pageDriver.findElement(imageNameInput).clear();
		pageDriver.findElement(imageNameInput).sendKeys(name);
	}
	
	public void selectRound(String name){
		Select round = new Select(pageDriver.findElement(roundSelect));
		round.selectByValue(name);
	}
	
	public void clickSumbit(){
		this.click(submitButton);
	}
}
