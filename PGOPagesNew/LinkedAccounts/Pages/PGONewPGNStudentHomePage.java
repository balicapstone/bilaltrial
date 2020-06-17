package LinkedAccounts.Pages;

import org.openqa.selenium.By;

import SharedClasses.PGONewBasePage;
import UserClasses.User;

public class PGONewPGNStudentHomePage extends PGONewBasePage{

	public By goToStatesLink = By.linkText("States");
	public By goToScienceLink = By.linkText("Science");
	public By goToAmericanIndians = By.linkText("American Indian History");
	public By goToSocialStudies = By.linkText("Social Studies");
	public By goToBiographies = By.linkText("Biographies");
	public By[] initialVisibleElements = {goToStatesLink, goToAmericanIndians, goToScienceLink, goToSocialStudies};
	public PGONewLinkAccountsToolbar laToolbar;
	
	public PGONewPGNStudentHomePage(User u){
		user = u;
		pageDriver = u.getDriver();
		laToolbar = new PGONewLinkAccountsToolbar(user);
		
		if(!pageDriver.getCurrentUrl().contains("https")){
			pageDriver.get(pageDriver.getCurrentUrl().replace("http", "https"));
		}
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public boolean isStatesDBPresent(){
		return isElementPresent(goToStatesLink);
	}
	
	public boolean isAMIndiansDBPresent(){
		return isElementPresent(goToAmericanIndians);
	}
	
	public boolean isScienceDBPresent(){
		return isElementPresent(goToScienceLink);
	}

	public String getDatabaseEntryImageSrc(String entry){
		return pageDriver.findElement(By.xpath("//*[@id=\"content-box\"]/div/a[@data-module-name='"+entry+"']/img")).getAttribute("src").toString();
	}
	
	public Boolean isModulePresentByName(String module){
		boolean displayed = false;
		try{
			displayed = pageDriver.findElement(By.linkText(module)).isDisplayed();
		} catch(Exception e){
			System.out.println(e.toString());
		}
		return displayed;
	}
}
