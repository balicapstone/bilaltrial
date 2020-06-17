package ArticleModals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;
import SharedClasses.ThirdPartyPage;

public class ActivityModal extends BasePage{
	public By closeButton = By.id("btn-close-listen");
	public By printShareWhatYouKnowButton = By.xpath("//*[@id=\"print-activity-panel\"]/div[1]/div/div[1]/div/a");
	public By printActivityButton = By.xpath("//*[@id=\"print-activity-panel\"]/div[1]/div/div[2]/div/a");
	
	public ActivityModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public void closeModal(){
		click(closeButton);
		this.waitImplicitly(2);
	}
	
	public Boolean isShareWhatYouKnowButtonPresent(){
		return isElementPresent(printShareWhatYouKnowButton);
	}
	
	public Boolean isPrintActivityButtonPresent(){
		return isElementPresent(printActivityButton);
	}
	
	public ThirdPartyPage printShareWhatYouKnow(){
		click(printShareWhatYouKnowButton);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage printActivity(){
		click(printActivityButton);
		return new ThirdPartyPage(pageDriver);
	}
}
