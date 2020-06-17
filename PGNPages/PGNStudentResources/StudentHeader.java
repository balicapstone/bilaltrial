package PGNStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import PGNPages.LoginPage;
import SharedClasses.BasePage;

public class StudentHeader extends BasePage{
	
	public By pGNLogo = By.xpath("//*[@id=\"pgo-logo\"]/a");
	public By searchBar = By.xpath("//*[@id=\"search\"]/input[1]");
	public By hamburgerMenuButton = By.xpath("//*[@id=\"burger\"]/a");
	public By logoutButton = By.xpath("//*[@id=\"logout\"]/a");
	public By homeButton = By.xpath("//*[@id='breadcrumb']/ul/a[1]/li/span");
	public HamburgerMenu hamburgerMenu;
	
	public By[] initialVisibleElements = {pGNLogo, logoutButton, hamburgerMenuButton};
	
	public StudentHeader(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public HamburgerMenu openHamburgerMenu(){
		click(hamburgerMenuButton);
		hamburgerMenu = new HamburgerMenu(pageDriver);
		return hamburgerMenu;
	}
	
	public LoginPage logout(){		
		click(logoutButton);
		return new LoginPage(pageDriver);
	}
	
	public StudentHomePage clickLogo(){
		click(pGNLogo);
		return new StudentHomePage(pageDriver);
	}

	public StudentHeader searchFor(String string) {
		sendKeysToElement(string, searchBar);
		return this;
	}
	
	public By getXpathForResult(String result){
		return By.xpath("//div[@id='search_result']/ul/li/a[contains(text(),'"+result+"')]");
	}
	
	public ArticlePage clickResult(String result) {
		click(By.xpath("//div[@id='search_result']/ul/li/a[contains(text(),'"+result+"')]"));
	
		return new ArticlePage(pageDriver);
	}

	public ArticlePage clickResult(String result, ArticleType t) {
		click(By.xpath("//*[@id='search_result']/ul/li/a[contains(text(),'"+result+"')]"));
		return new ArticlePage(pageDriver, t);
	}

	public BasePage clickHomeBreadcrumb() {
		click(homeButton);
		return new StudentHomePage(pageDriver);
	}	
}
