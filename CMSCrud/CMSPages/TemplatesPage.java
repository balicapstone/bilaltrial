package CMSPages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TemplatesPage extends CMSHomePage{
	public By createNewModuleButton = By.id("test-create-button");
	public By nameField = By.id("test-name-input");
	public By shortNameField = By.id("test-name-input");
	public By captionField = By.id("test-language-text-input");
	public By imageField = By.id("test-image-text-input");
	public By siteDropdown = By.id("test-site-dropdown");
	public By activeDropdown = By.id("test-enable-dropdown");
	public By submitButton = By.id("test-submit-button");
	
	public TemplatesPage(WebDriver driver){
		super(driver);
	}
	
	public ModuleModal openCreateModuleModal(){
		click(createNewModuleButton);
		return new ModuleModal(pageDriver);
	}
	
	public void clickModuleByName(String module){
		By element = By.id("test-" + module);
		click(element); 
	}
	
	public void enterName(String text){
		this.clearField(nameField);
		this.sendKeysToElement(text, nameField);
	}
	
	public void enterShortName(String text){
		this.clearField(shortNameField);
		this.sendKeysToElement(text, shortNameField);
	}
	
	public void enterModuleCaption(String caption){
		this.clearField(captionField);
		this.sendKeysToElement(caption, captionField);
	}
	
	public void enterImageFilename(String filename){
		this.clearField(imageField);
		this.sendKeysToElement(filename, imageField);
	}
	
	public void selectSiteByName(String site){
		Select s = new Select(pageDriver.findElement(siteDropdown));
		s.selectByVisibleText(site);
	}
	
	public Boolean isModuleActive(){
		Select s = new Select(pageDriver.findElement(activeDropdown));
		if(s.getFirstSelectedOption().getText().equals("Active")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void clickSubmit(){
		click(submitButton);
	}
	
	
	public void toggleDatabaseByName(String test){	
		click(pageDriver.findElement(By.id("test-"+ test)));
		waitImplicitly(5);
		
		Select active = new Select(pageDriver.findElement(By.id("test-enable-dropdown")));
		
		if(active.getFirstSelectedOption().toString().equals("Active")){
			active.selectByValue("Inactive");
		}
		else if(active.getFirstSelectedOption().toString().equals("Inactive")){
			active.selectByValue("Active");
		}
		
		clickSubmit();
	}
	
	public Boolean isDatabaseActive(String test) throws InterruptedException{
		moveToElement(By.id("test-"+ test));
		click(pageDriver.findElement(By.id("test-"+ test)));
		waitImplicitly(5);
		
		Select active = new Select(pageDriver.findElement(By.id("test-enable-dropdown")));
		
		if(active.getFirstSelectedOption().toString().equals("Active")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void makeModuleActive(String test) throws InterruptedException{
		moveToElement(By.id("test-"+ test));
		click(pageDriver.findElement(By.id("test-"+ test)));
		waitImplicitly(5);
		
		Select active = new Select(pageDriver.findElement(By.id("test-enable-dropdown")));
		
		active.selectByVisibleText("Active");
		waitImplicitly(3);
		clickSubmit();
		waitImplicitly(3);
	}
	
	public void makeModuleInactive(String test) throws InterruptedException{
		moveToElement(By.id("test-"+ test));
		click(pageDriver.findElement(By.id("test-"+ test)));
		waitImplicitly(5);
		
		Select active = new Select(pageDriver.findElement(By.id("test-enable-dropdown")));
		
		active.selectByVisibleText("Inactive");
		waitImplicitly(3);
		clickSubmit();
		waitImplicitly(3);
	}
	
	public ModuleModal updateExistingModule(String test){
		ArrayList<WebElement> modules = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"module\"]/div/div[2]/div[1]"));
		int index = 2;
		for(WebElement w : modules){
			if(w.getText().contains(test)){
				WebElement button = pageDriver.findElement(By.xpath("//*[@id=\"module\"]/div["+index+"]/div[2]/div[5]"));
				
				this.waitImplicitly(1);
				this.scrollToElement(button);
				this.waitImplicitly(1);
				
				button.click();
			}
			else{
				index++;
			}
		}
		
		return new ModuleModal(pageDriver);
	}
}
