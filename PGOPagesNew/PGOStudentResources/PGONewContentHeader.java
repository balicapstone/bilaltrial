package PGOStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import PGOPagesNew.PGONewLoginPage;
//import PGOPages.LoginPage;
import SharedClasses.PGONewBasePage;
import UserClasses.User;

public class PGONewContentHeader extends PGONewBasePage{
	public By searchBar = By.id("search");
	public By searchResults = By.id("search-results");
	public By searchComboBox = By.xpath("//div[@role=\"combobox\"]");
	public By searchCommand = By.xpath("//div[@class=\"search-container\"]/span");
	
	public By randomArticleButton = By.id("random-article");
	public By languageButton = By.className("language-toggle");
	public By logo = By.className("header-logo-link"); // By.id("logo");
	
	public By capstoneDropdown = By.id("drop-button");//("drop-button");
	public By capstoneDropdownStatus = By.xpath("//button[@id=\"drop-button\"]/img[contains(@class, \"chevron\")]");
	public By capstoneLogo = By.xpath("//button[@id=\"drop-button\"]/img[contains(@class, \"capstone\")]");
	public By pebbleGoNextLink = By.id("PebbleGo Next");
	public By capstoneInteractiveLink = By.xpath("//*[@id='root']/div/div[2]/header/div/nav/button[2]");
	public By signOutLink = By.id("sign-out-button");
	
	public By homeBreadcrumb = By.id("home");
	
	
	public PGONewContentHeader(User u){
		user = u;
		pageDriver = u.getDriver();
	}
	
	public PGONewLoginPage logout(){
		click(capstoneDropdown);
		user.customWait().until(ExpectedConditions.elementToBeClickable(signOutLink));
		click(signOutLink);
		
		return new PGONewLoginPage(user);
	}
	
	public void searchForArticle(String name){
		sendKeysToElement(name, searchBar);
	}
	
	public PGONewArticlePage clickResultForSearch(String name){
		click(By.id(name + "_result"));
		
		return new PGONewArticlePage(user);
	}
	
	public PGONewArticlePage searchForArticleAndClickResult(String name){
		sendKeysToElement(name, searchBar);
		this.waitImplicitly(3);
		
		this.getDriver().findElement(By.id(name.replace(" ", "_") + "_result")).click();
		
		return new PGONewArticlePage(user);
	}
	
	public PGONewContentSelectionPage clickBreadcrumbByName(String name){
		click(By.id(name));
		return new PGONewContentSelectionPage(user);
	}
	
	public StudentHomePageNew clickHomeBreadcrumb(){
		click(homeBreadcrumb);
		return new StudentHomePageNew(user);
	}
	
	public PGONewArticlePage clickRandomArticleButton(){
		click(randomArticleButton);
		return new PGONewArticlePage(user);
	}
}
