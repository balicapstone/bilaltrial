package CITests.CIPages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import BookTools.BookModal;
import SharedClasses.BasePage;

public class BooklistPage extends BasePage{
	public By viewAllBooklists = By.id("view-all-books");
	
	public BooklistPage(WebDriver driver){
		pageDriver = driver;
		pageDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	public BooklistSelectPage clickViewAllBooklists(){
		click(viewAllBooklists);
		return new BooklistSelectPage(pageDriver);
	}
	
	public BookModal clickBookByName(String title){
		List<WebElement> elements = pageDriver.findElements(By.className("title"));
		
		int i = 1;
		for(WebElement w : elements){
			if(w.getText().equals(title)){
				clickBookByIndex(i);
			}
			else{
				i++;
			}
		}
		
		return new BookModal(pageDriver);
	}
	
	public BookModal clickBookByIndex(int i){
		int index = 2 + i; //Index for xpath starts at 3 so we start at 2 and add 1 for each book
		pageDriver.findElement(By.xpath("//*[@id='shelf']/div["+index+"]/div/div/figure")).click();
		
		return new BookModal(pageDriver);
	}
}
