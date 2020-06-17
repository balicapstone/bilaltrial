package NoNonsense.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class NoNonsenseHeader extends BasePage{

	private By logo = By.id("logo");
	private By findOutMore = By.linkText("Find Out More");
	private By faqLink = By.linkText("FAQ");
	private By subscribe = By.linkText("Subscribe");
	private By loginLink = By.linkText("Login");
	private By logoutLink = By.linkText("Log Out");
	private By libraryLink = By.linkText("Library");
	public By[] initialVisibleElements = {logo, findOutMore, faqLink, subscribe, loginLink, logoutLink, libraryLink};
	
	public NoNonsenseHeader(WebDriver driver){
		pageDriver = driver;
	}
	
	public FindOutMorePage goToFindMorePage(){
		click(findOutMore);
		return new FindOutMorePage(pageDriver);
	}
	
	public FAQPage goToFAQPage(){
		click(faqLink);
		return new FAQPage(pageDriver);
	}
	
	public SubscribePage goToSubscribePage(){
		click(subscribe);
		return new SubscribePage(pageDriver);
	}
	
	public SearchPage goToLibraryPage(){
		click(libraryLink);
		return new SearchPage(pageDriver);
	}
	
	public LandingPage clickLogo(){
		click(logo);
		return new LandingPage(pageDriver);
	}
	
	public LandingPage logout(){
		click(logoutLink);
		return new LandingPage(pageDriver);
	}
	
	public LoginModal login(){
		click(loginLink);
		return new LoginModal(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
