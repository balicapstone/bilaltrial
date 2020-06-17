package CMSPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class CMSHomePage extends BasePage{
	
	public CMSToolbar toolbar;
	public By toolbarHamburger = By.xpath("/html/body/header/div[1]/i");
	
	public CMSHomePage(WebDriver driver){
		pageDriver = driver;
		toolbar = new CMSToolbar(driver);
	}
	
	public CMSToolbar openToolbar(){
		click(toolbarHamburger);
		return new CMSToolbar(this.getDriver());
	}
}
