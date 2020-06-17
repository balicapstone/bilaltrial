package NoNonsense.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;
import SharedClasses.ThirdPartyPage;

public class EulaModal extends BasePage{
	protected By eulaLink = By.linkText("EULA"); ////*[@id="eula"]/a
	protected By acceptButton = By.xpath("//*[@id=\"eula\"]/button");
	
	public EulaModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public ThirdPartyPage clickEulaLink(){
		click(eulaLink);
		return new ThirdPartyPage(pageDriver);
	}
	
	public SearchPage acceptEula(){
		click(acceptButton);
		return new SearchPage(pageDriver);
	}
}
