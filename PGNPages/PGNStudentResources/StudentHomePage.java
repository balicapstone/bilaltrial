package PGNStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import MasterAccount.LinkAccountsToolbar;
import PGNPages.FooterMenu;
import SharedClasses.BasePage;

public class StudentHomePage extends BasePage{

	public By goToStatesLink = By.linkText("States");
	public By goToScienceLink = By.linkText("Science");
	public By goToAmericanIndians = By.linkText("American Indian History");
	public By goToSocialStudies = By.linkText("Social Studies");
	public By goToBiographies = By.linkText("Biographies");
	public By[] initialVisibleElements = {goToStatesLink, goToAmericanIndians, goToScienceLink, goToSocialStudies};
	public StudentHeader header;
	public FooterMenu footer;
	public LinkAccountsToolbar laToolbar;
	
	public StudentHomePage(WebDriver driver){
		pageDriver = driver;
		header = new StudentHeader(driver);
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
	
	public ScienceDatabaseHome goToScience(){
		click(goToScienceLink);
		return new ScienceDatabaseHome(pageDriver);
	}
	
	public StatesDatabaseHome goToStates(){
		click(goToStatesLink);
		return new StatesDatabaseHome(pageDriver);
	}
	
	public IndiansDatabaseHome goToIndians(){
		click(goToAmericanIndians);
		return new IndiansDatabaseHome(pageDriver);
	}
	
	public SocialStudiesDatabasePage goToSocialStudies(){
		click(goToSocialStudies);
		return new SocialStudiesDatabasePage(pageDriver);
	}
	
	public DatabasePage goToBiographies(){
		click(goToBiographies);
		return new DatabasePage(pageDriver);
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
	
	public DatabasePage clickModuleByName(String name){
		click(By.linkText(name));
		
		return new DatabasePage(pageDriver);
	}
}
