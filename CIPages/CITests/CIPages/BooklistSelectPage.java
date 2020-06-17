package CITests.CIPages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import SharedClasses.BasePage;

public class BooklistSelectPage extends BasePage{
	public By bookshelfButton = By.id("icon-bookshelf");
	public By preKButton = By.id("grade-id-4");
	public By GradeK2Button = By.id("grade-id-5");
	public By Grade35Button = By.id("grade-id-2");
	public By Grade6PlusButton = By.id("grade-id-3");
	public By viewAllBooklists = By.id("view-all-books");
	public By bookCount = By.id("book-count");
	public By logOutButton = By.id("log-out");
	public By[] initialVisibleElements = {bookshelfButton, preKButton, GradeK2Button, Grade35Button, 
			Grade6PlusButton, viewAllBooklists, bookCount, logOutButton};

	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public BooklistSelectPage(WebDriver driver){
		pageDriver = driver;
		pageDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	public BooklistPage clickBooklistByName(String title){
		List<WebElement> elements = pageDriver.findElements(By.className("title"));
		
		int i = 1;
		for(WebElement w : elements){
			if(w.getText().equals(title)){
				clickBooklistByIndex(i);
			}
			else{
				i++;
			}
		}
		
		return new BooklistPage(pageDriver);
	}
	
	public BooklistPage clickBooklistByIndex(int i){
		int index = 1 + i; //Index for xpath starts at 2 so we start at 1 and add 1 for each book
		pageDriver.findElement(By.xpath("//*[@id='shelf']/div["+index+"]/div/figure/figcaption/div/h3")).click();
		
		return new BooklistPage(pageDriver);
	}
	
	public StudentHomePage clickBookshelfButton(){
		click(bookshelfButton);
		return new StudentHomePage(pageDriver);
	}
	
	public CILoginPage logOut(){
		click(logOutButton);
		return new CILoginPage(pageDriver);
	}
}
