package PGNStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import MasterAccount.LinkAccountsToolbar;
import PGNPages.FooterMenu;
import PGOStudentResources.StudentHomePageNew;
import SharedClasses.PGONewBasePage;

public class PGNStudentHomePage extends PGONewBasePage{

	public By goToStatesLink = By.linkText("States");
	public By goToScienceLink = By.linkText("Science");
	public By goToAmericanIndians = By.linkText("American Indian History");
	public By goToSocialStudies = By.linkText("Social Studies");
	public By goToBiographies = By.linkText("Biographies");
	public By[] initialVisibleElements = {goToStatesLink, goToAmericanIndians, goToScienceLink, goToSocialStudies};
	public PGNStudentHeader header;
	public FooterMenu footer;
	public LinkAccountsToolbar laToolbar;
	
	public By pebblegoTab = By.xpath("//*[@id=\"main_content\"]/div[1]");
	public By pebblegoNextTab = By.xpath("//*[@id=\"main_content\"]/div[2]");
	
	public PGNStudentHomePage(WebDriver driver){
		pageDriver = driver;
		header = new PGNStudentHeader(driver);
		footer = new FooterMenu(driver);
		laToolbar = new LinkAccountsToolbar(driver);
		
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
	
	public PGNScienceDatabaseHome goToScience(){
		click(goToScienceLink);
		return new PGNScienceDatabaseHome(pageDriver);
	}
	
	public PGNStudentHomePage clickPebbleGoNextTab(){
		click(pebblegoNextTab);
		return this;
	}
	
	
	public PGNStatesDatabaseHome goToStates(){
		click(goToStatesLink);
		return new PGNStatesDatabaseHome(pageDriver);
	}
	
	public PGNIndiansDatabaseHome goToIndians(){
		click(goToAmericanIndians);
		return new PGNIndiansDatabaseHome(pageDriver);
	}
	
	public PGNSocialStudiesDatabasePage goToSocialStudies(){
		click(goToSocialStudies);
		return new PGNSocialStudiesDatabasePage(pageDriver);
	}
	
	public PGNDatabasePage goToBiographies(){
		click(goToBiographies);
		return new PGNDatabasePage(pageDriver);
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
	
	public PGNDatabasePage clickModuleByName(String name){
		click(By.linkText(name));
		
		return new PGNDatabasePage(pageDriver);
	}
}
