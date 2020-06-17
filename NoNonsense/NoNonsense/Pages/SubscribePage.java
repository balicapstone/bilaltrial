package NoNonsense.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;
import SharedClasses.ThirdPartyPage;

public class SubscribePage extends BasePage{

	public NoNonsenseHeader header = new NoNonsenseHeader(pageDriver);
	public By subscribeTitle = By.xpath("//*[@id=\"main\"]/div/div/h1"); 
	public By orderNoNonsenseButton = By.xpath("//*[@id=\"main\"]/div/div/input[1]");
	public By orderTeachingSequencesButton = By.xpath("//*[@id=\"main\"]/div/div/input[2]");
	public By assessmentOfWritingButton = By.xpath("//*[@id=\"main\"]/div/div/input[3]");
	public By raintreeEmailLink = By.linkText("feedback@raintree.co.uk");
	public By[] initialVisibleElements = {subscribeTitle, orderNoNonsenseButton, orderTeachingSequencesButton, assessmentOfWritingButton, raintreeEmailLink};
	
	public SubscribePage(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public ThirdPartyPage orderNoNonsense(){
		click(orderNoNonsenseButton);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage orderTeachingSequences(){
		click(orderTeachingSequencesButton);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage orderAssessmentsOfWriting(){
		click(assessmentOfWritingButton);
		return new ThirdPartyPage(pageDriver);
	}
}
