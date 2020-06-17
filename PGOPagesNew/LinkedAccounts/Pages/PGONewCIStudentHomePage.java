package LinkedAccounts.Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import SharedClasses.PGONewBasePage;
import UserClasses.User;

public class PGONewCIStudentHomePage extends PGONewBasePage{
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
	public PGONewLinkAccountsToolbar laToolbar;
	public By[] initialVisibleElements = {bookshelfButton, booklistsButton, popularButton, newestButton,
			titleButton, visualSearch, searchTextField, searchButton, logoutButton, viewAllBooksButton, bookResults};
	public WebDriverWait wait;
	
	public PGONewCIStudentHomePage(User u){
		user = u;
		pageDriver = u.getDriver();
		pageDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		laToolbar = new PGONewLinkAccountsToolbar(user);
		wait = new WebDriverWait(pageDriver, 15);
	}
	
	public PGONewCILoginPage logout(){
		click(logoutButton);
		return new PGONewCILoginPage(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}


