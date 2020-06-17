package CMSGames;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CMSPages.CMSHomePage;
import CMSPages.EditGamesPage;

public class EditWhackGame extends CMSHomePage{
	public By imageInput = By.id("test-input-image");
	public By nameInput = By.id("test-input-name");
	public By categorySelect = By.id("test-input-category");
	public By submitButton = By.id("test-submit-entry");
	public By clearButton = By.id("test-entry-clear");
	
	public By editCategoriesLink = By.linkText("Edit Categories");
	public By goBackToMainFormLink = By.linkText("Go back to Main Form");
	
	
	public EditWhackGame(WebDriver driver){
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
	
	public void editCategoryByInt(int i){
		click(By.id("test-edit-entry-"+i));
	}
	
	public void deleteCategoryByInt(int i){
		click(By.id("test-delete-entry-"+i));
	}
	
	public void setImage(String image){
		pageDriver.findElement(imageInput).clear();
		pageDriver.findElement(imageInput).sendKeys(image);
	}
	
	public void setImageName(String image){
		pageDriver.findElement(nameInput).clear();
		pageDriver.findElement(nameInput).sendKeys(image);
	}
	
	public void clickSubmit(){
		pageDriver.findElement(submitButton).click();
	}
}
