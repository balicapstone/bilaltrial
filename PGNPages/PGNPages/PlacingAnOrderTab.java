package PGNPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class PlacingAnOrderTab extends BasePage{
	public By trialPageLink = By.linkText("PebbleGo Next trial page");
	public By placeAnOrderLink = By.linkText("click here");
	public By localSalesRepLink = By.linkText("contact your local sales rep");
	public By[] initialVisibleElements = {trialPageLink, placeAnOrderLink, localSalesRepLink};
	
	public PlacingAnOrderTab(WebDriver driver){
		pageDriver = driver;
	}
	
	public TrialPage clickTrialPageLink(){
		click(trialPageLink);
		return new TrialPage(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
