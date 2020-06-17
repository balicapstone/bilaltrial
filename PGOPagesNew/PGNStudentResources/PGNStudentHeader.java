package PGNStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import PGNPages.PGNLoginPage;
import SharedClasses.PGONewBasePage;

public class PGNStudentHeader extends PGONewBasePage{
	
	public By pGNLogo = By.xpath("//*[@id=\"pgo-logo\"]/a");
	//public By searchBar = By.xpath("//*[@id=\"search\"]/input[1]");
	public By searchBar = By.xpath("//*[@id=\"search\"]");
	
	
	public By hamburgerMenuButton = By.xpath("//*[@id=\"burger\"]/a");
	public By logoutButton = By.xpath("//*[@id=\"logout\"]/a");
	public By homeButton = By.xpath("//*[@id='breadcrumb']/ul/a[1]/li/span");
	public PGNHamburgerMenu hamburgerMenu;
	
	public By[] initialVisibleElements = {pGNLogo, logoutButton, hamburgerMenuButton};
	
	public PGNStudentHeader(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public PGNHamburgerMenu openHamburgerMenu(){
		click(hamburgerMenuButton);
		hamburgerMenu = new PGNHamburgerMenu(pageDriver);
		return hamburgerMenu;
	}
	
	public PGNLoginPage logout(){		
		click(logoutButton);
		return new PGNLoginPage(pageDriver);
	}
	
	public PGNStudentHomePage clickLogo(){
		click(pGNLogo);
		return new PGNStudentHomePage(pageDriver);
	}

	public PGNStudentHeader searchFor(String string) {
		sendKeysToElement(string, searchBar);
		return this;
	}
	
	public By getXpathForResult(String result){
		return By.xpath("//div[@id='search_result']/ul/li/a[contains(text(),'"+result+"')]");
	}
	
	public PGNArticlePage clickResult(String result) {
		click(By.xpath("//div[@id='search_result']/ul/li/a[contains(text(),'"+result+"')]"));
	
		return new PGNArticlePage(pageDriver);
	}

	public PGNArticlePage clickResult(String result, PGNArticleType t) {
		click(By.xpath("//*[@id='search_result']/ul/li/a[contains(text(),'"+result+"')]"));
		return new PGNArticlePage(pageDriver, t);
	}

	public PGONewBasePage clickHomeBreadcrumb() {
		click(homeButton);
		return new PGNStudentHomePage(pageDriver);
	}	
}
