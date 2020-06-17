package CMSPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditModuleModal extends ModuleModal{
	By closeButton = By.id("test-close-button-update");
	
	public EditModuleModal(WebDriver pageDriver){
		super(pageDriver);
	}
	
	public TemplatesPage closeModal(){
		click(closeButton);
		return new TemplatesPage(pageDriver);
	}
}
