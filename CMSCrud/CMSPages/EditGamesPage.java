package CMSPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class EditGamesPage extends BasePage{	
	
	public EditGamesPage(WebDriver driver){
		pageDriver = driver;
	}
	
	public void clickModuleByName(String module){
		By element = By.id("test-" + module);
		try {
			moveToElement(element);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		click(element); 
	}
	
	public void clickGameByName(String game){
		By element = By.id("test-" + game);
		click(element);
	}
}
