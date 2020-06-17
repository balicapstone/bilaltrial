package CITests.CIPages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BookTools.BookModal;
import MasterAccount.LinkAccountsToolbar;
import SharedClasses.BasePage;
import VisualSearch.VisualSearchMain;

public class StudentHomePage extends BasePage{
	public By bookshelfButton = By.id("icon-bookshelf");
	public By booklistsButton = By.id("booklists-link");
	public By popularButton = By.id("popular-sort");
	public By newestButton = By.id("new-sort");
	public By titleButton = By.id("title-sort");
	public By visualSearch = By.id("visual-search-btn");
	public By searchTextField = By.id("text-search");
	public By searchButton = By.xpath("//*[@id=\"search-btn\"]/div");
	public By logoutButton = By.id("logout");
	public By viewAllBooksButton = By.xpath("//*[@id=\"view-all-books\"]");//("View all books"); // By.id("view-all-books");
	public By bookResults = By.id("book-count");
	public By visualSearchText = By.id("vs-text");
	public LinkAccountsToolbar laToolbar;
	public By[] initialVisibleElements = {bookshelfButton, booklistsButton, popularButton, newestButton,
			titleButton, visualSearch, searchTextField, searchButton, logoutButton, viewAllBooksButton, bookResults};
	public Filters filters;
	public WebDriverWait wait;
	
	public StudentHomePage(WebDriver driver){
		pageDriver = driver;
		pageDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		filters = new Filters(pageDriver);
		laToolbar = new LinkAccountsToolbar(pageDriver);
		wait = new WebDriverWait(pageDriver, 15);
	}
	
	public BookModal clickBookByTitle(String title){
		List<WebElement> titles = pageDriver.findElements(By.className("title"));
		int i = 2;
		BookModal book = null;
		
		for(WebElement t : titles){
			if(t.getText().toString().equals(title)){
				clickBookByIndex(i);
				book = new BookModal(pageDriver);
				return book;
			}
			i++;
		}
		return book;
	}
	
	public BookModal clickBookByIndex(int i){
		String xpath = "//*[@id=\"shelf\"]/div["+i+"]/div/figure";///figcaption";
		//*[@id="shelf"]/div[7]/div/figure
		BookModal book = null;
		
		try{
			pageDriver.findElement(By.xpath(xpath)).click();
			book = new BookModal(pageDriver);
		}catch(Exception e){
			
		}
		return book;
	}
	
	public Boolean isBookPresentByTitle(String title){
		List<WebElement> titles = pageDriver.findElements(By.className("title"));
		Boolean book = false;
		
		for(WebElement t : titles){
			if(t.getText().toString().equals(title)){
				book = t.isDisplayed();
			}
		}
		return book;
	}
	
	public VisualSearchMain clickVisualSearch(){
		click(visualSearch);
		VisualSearchMain visual = new VisualSearchMain(pageDriver);
		wait.until(ExpectedConditions.elementToBeClickable(visual.nonfictionDiv));
		return visual;
	}
	
	public int getResults(){
		String[] results = pageDriver.findElement(bookResults).getText().split(" ");
		return Integer.parseInt(results[0]);
	}
	
	public CILoginPage logout(){
		click(logoutButton);
		return new CILoginPage(pageDriver);
	}
	
	public StudentHomePage search(String search){
		sendKeysToElement(search, searchTextField);
		wait.until(ExpectedConditions.textToBePresentInElementValue(searchTextField, search));
		click(searchButton);
		return this;
	}
	
	public BooklistSelectPage clickBooklistsDiv(){
		click(booklistsButton);
		return new BooklistSelectPage(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}


