package LearnMorePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PrivacyPolicyPage extends LearnMoreBasePage{
	private By wholeText = By.className("whole");
	private By infoEmailAddress = By.linkText("support@capstonepub.com");
	private By[] initialVisibleElements = {wholeText, termsOfUseLink, infoEmailAddress};
	
	public PrivacyPolicyPage(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
