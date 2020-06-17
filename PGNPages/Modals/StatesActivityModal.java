package Modals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import SharedClasses.ThirdPartyPage;

public class StatesActivityModal extends ActivityModal{
	public By flagTab = By.linkText("Flag");
	public By mapTab = By.linkText("Map");
	public By criticalThinkingTab = By.linkText("Critical Thinking Questions");
	public By printFlagButton = By.xpath("//*[@id=\"activity-2\"]/a");
	public By printMapButton = By.xpath("//*[@id='activity-1']/a");
	
	public StatesActivityModal(WebDriver driver){
		super(driver);
	}
	
	public Boolean isFlagTabActive(){
		String state = pageDriver.findElement(flagTab).getAttribute("class").toString();
		if(state.contains("active")){
			return true;
		}
		
		return false;
	}
	
	public Boolean isMapTabActive(){
		String state = pageDriver.findElement(mapTab).getAttribute("class").toString();
		if(state.contains("active")){
			return true;
		}
		
		return false;
	}
	
	public Boolean isCriticalThinkingTabActive(){
		String state = pageDriver.findElement(criticalThinkingTab).getAttribute("class").toString();
		if(state.contains("active")){
			return true;
		}
		
		return false;
	}
	
	public ActivityModal clickFlagTab(){
		click(flagTab);
		return this;
	}
	
	public ActivityModal clickMapTab(){
		click(mapTab);
		return this;
	}
	
	public ActivityModal clickCriticalThinkingTab(){
		click(criticalThinkingTab);
		return this;
	}
	
	public ThirdPartyPage printFlag(){
		click(printFlagButton);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage printMap(){
		click(printMapButton);
		return new ThirdPartyPage(pageDriver);
	}
}
