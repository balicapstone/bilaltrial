package CMSPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class ModuleModal extends BasePage{

	By nameField = By.xpath("//*[@id=\"module-modal\"]/div[1]/input");
	By shortNameField = By.xpath("//*[@id=\"module-modal\"]/div[2]/input");
	By captionField = By.xpath("//*[@id=\"module-modal\"]/div[3]/input");
	By moduleButton = By.xpath("//*[@id=\"module-modal\"]/button");
	By closeButton = By.id("test-close-button-create");
	
	public ModuleModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public void enterName(String name){	
		getModalElement(nameField).clear();
		getModalElement(nameField).sendKeys(name);
	}
	
	public void enterShortName(String name){
		getModalElement(shortNameField).clear();
		getModalElement(shortNameField).sendKeys(name);
	}
	
	public void enterModuleCaption(String caption){
		getModalElement(captionField).clear();
		getModalElement(captionField).sendKeys(caption);
	}
	
	public void clickCreateNewModuleButton(){
		getModalElement(moduleButton).click();
	}

	public TemplatesPage closeModal(){
		click(closeButton);
		return new TemplatesPage(pageDriver);
	}
}
