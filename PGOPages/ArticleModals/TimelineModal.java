package ArticleModals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class TimelineModal extends BasePage{
	public By closeButton = By.id("btn-close-listen");
	
	public TimelineModal(WebDriver driver){
		pageDriver = driver;
		
	}
	
	public Boolean verifyTimelineElementPresent(){
		Boolean present = false;
		
		return present;
	}
	
	public void closeModal(){
		click(closeButton);
	}	
	
	public int getCountOfTimelineElement(){		
		return this.getDriver().findElements(By.className("plot")).size();
	}
}
