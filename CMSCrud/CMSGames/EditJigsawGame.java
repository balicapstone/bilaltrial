package CMSGames;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CMSPages.CMSHomePage;
import CMSPages.EditGamesPage;

public class EditJigsawGame extends CMSHomePage{
	public By rowInput = By.id("test-input-row");
	public By columnInput = By.id("test-input-column");
	public By imageInput = By.id("test-input-image");
	public By submitButton = By.id("test-submit-entry");
	public By clearButton = By.id("test-clear-entry");
	
	public EditJigsawGame(WebDriver driver){
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
		click(By.id("test-edit-entry-"+ i));
	}
	
	public void deleteEntryByInt(int i){
		click(By.id("test-delete-entry-"+ i));
	}
}
