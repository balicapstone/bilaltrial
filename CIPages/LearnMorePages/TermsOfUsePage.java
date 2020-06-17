package LearnMorePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TermsOfUsePage extends LearnMoreBasePage{
	public By copyrightInfringmentLink = By.xpath("//*[@id='middle']/div[2]/ol[1]/li[7]/a");
	public By firstPrivacyPolicyLink = By.xpath("//*[@id='middle']/div[2]/ol[1]/li[11]/a");
	public By secondPrivacyPolicyLink = By.xpath("//*[@id='middle']/div[2]/ol[1]/li[13]/a");
	public By infoEmailLink = By.xpath("//*[@id='middle']/div[2]/ol[2]/li[1]/a");
	public By[] initialVisibleElements = {copyrightInfringmentLink, firstPrivacyPolicyLink, 
			secondPrivacyPolicyLink, infoEmailLink};
	
	public TermsOfUsePage(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
