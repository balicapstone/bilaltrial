package CMSPages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GlossarySearchPage extends CMSHomePage{
	public By searchTermBox = By.id("test-input-search");
	public By searchButton = By.id("test-submit");
	
	public GlossarySearchPage(WebDriver driver){
		super(driver);
	}
	
	public void searchTerm(String term){
		pageDriver.findElement(searchTermBox).sendKeys(term);
		this.closeSendKeysTab();
		waitImplicitly(2);
		click(searchButton);	
		waitImplicitly(5);
	}
	
	public Boolean doesGlossaryTermExist(String term){
		Boolean appears = false;
		
		ArrayList<WebElement> elements = (ArrayList<WebElement>) pageDriver.findElements(By.className("glossary-word"));
		for(WebElement w: elements){
			if(w.getText().equals(term)){
				appears = true;
			}
		}
		
		return appears;
	}
}
