package PGOPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class CreditsPage extends BasePage{
	public By animalsHeader = By.xpath("//*[@id=\"content\"]/div[1]/h3[1]");
	public By scienceHeader = By.xpath("//*[@id=\"content\"]/div[1]/h3[2]");
	public By biographiesHeader = By.xpath("//*[@id=\"content\"]/div[1]/h3[3]");
	public By socialStudiesHeader = By.xpath("//*[@id=\"content\"]/div[1]/h3[4]");
	public By[] initialVisibleElements = {animalsHeader, scienceHeader, biographiesHeader, socialStudiesHeader};
	public NavigationBar navBar;
	
	public CreditsPage(WebDriver driver){
		pageDriver = driver;
		navBar = new NavigationBar(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
