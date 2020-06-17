package LearnMorePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class RequirementsModal extends BasePage{
	public By checkSystemCompatibilityLink = By.linkText("click here");
	
	public RequirementsModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public CompatibilityPage clickCompatibilityLink(){
		click(checkSystemCompatibilityLink);
		return new CompatibilityPage(pageDriver);
	}
}
