package PGOPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import SharedClasses.BasePage;

public class SystemRequirementsPage extends BasePage{

	private By checkYourSystemButton = By.linkText("Check your system?");
	private By browserName = By.xpath("//*[@id=\"content\"]/div[3]/div/ul/li[1]/strong[1]/u");
	private By browserVersion = By.xpath("//*[@id=\"content\"]/div[3]/div/ul/li[1]/strong[2]/u");
	public By platformName =  By.xpath("//*[@id=\"content\"]/div[3]/div/ul/li[1]/strong[3]/u");
	private By javascriptDisabledX = By.xpath("//*[@id=\"content\"]/div[2]/div/ul/li[2]/noscript/strong/img");
	private By[] initialVisibleElements = { checkYourSystemButton };
	private Capabilities capabilities; 
	public NavigationBar navBar;
	public FooterMenu footerMenu;
	
	public SystemRequirementsPage(WebDriver driver){
		pageDriver = driver;
		capabilities = ((RemoteWebDriver) pageDriver).getCapabilities(); //new DesiredCapabilities();
		navBar = new NavigationBar(pageDriver);
		footerMenu = new FooterMenu(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	/**
	 * Clicks on the 'Check Your System Requirements' Button and bring you to the Requirements Check Page.
	 * @return RequirementsCheckPage
	 */
	public RequirementsCheckPage clickCheckYourSystem(){
		pageDriver.findElement(checkYourSystemButton).click();
		return new RequirementsCheckPage(pageDriver);
	}
	
	/**
	 * Get the WebDriver browser name as a String.
	 * @return String
	 */
	public String getDriverBrowserName(){
		return capabilities.getBrowserName();
	}
	
	/**
	 * Get the WebDriver browser version as a String.
	 * @return String
	 */
	public String getDriverBrowserVersion(){
		return capabilities.getVersion();
	}
	
	/**
	 * Get the WebDriver platform as a String.
	 * @return String
	 */
	public String getDriverPlatform(){
		return capabilities.getPlatform().toString();
	}
	
	/**
	 * Get the Page browser name as a String.
	 * @return String
	 */
	public String getPageNameElement(){
		return pageDriver.findElement(browserName).getText();
	}
	
	/**
	 * Get the Page version number as a String.
	 * @return String
	 */
	public String getPageVersionElement(){
		return pageDriver.findElement(browserVersion).getText().toString();
	}
	
	/**
	 * Get the Page platform type as a String.
	 * @return String
	 */
	public String getPagePlatformElement(){
		return pageDriver.findElement(platformName).getText();
	}
	
	/**
	 * Verify that Javascript Disabled picture is the not-okay-mark.
	 * @return boolean
	 */
	public boolean verifyJavascriptDisabled(){
		//String test = pageDriver.findElement(javascriptDisabledX).getAttribute("src").toString();
		pageDriver.findElement(javascriptDisabledX).getAttribute("src").toString().equals("http://qa.pebblego.com/img/not-ok-xmark.png");
		return true;
	}
}
