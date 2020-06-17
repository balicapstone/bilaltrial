package PGOPages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PGOStudentResources.ArticlePage;
import PGOStudentResources.StudentHomePage;
import PGOStudentResources.TopModuleMenu;
import SharedClasses.BasePage;

public class ContentHeader extends BasePage{
	public By gamesLink = By.id("btn-games");
	private By homeBreadcrumb = By.xpath("//*[@id=\"home-crumb\"]/a/img");
	private By databaseBreadcrumb = By.xpath("//*[@id=\"db-crumb\"]/a");
	private By logOutButton = By.xpath("//*[@id=\"logout\"]/a"); 
	private By randomArticleButton = By.id("btn-feelnLucky");
	private By searchTextField = By.xpath("//*[@id=\"header-search\"]/input[1]");
	private TopModuleMenu topModuleMenu;
	
	public By capstoneDropdown = By.id("drop-button");//("drop-button");
	
	public ContentHeader(WebDriver driver){
		pageDriver = driver;
	}
	
	public LoginPage clickLogOutButton(){
		pageDriver.findElement(logOutButton).click();
		return new LoginPage(pageDriver);
	}
	
	public ContentHeader clickContentByName(String link){
		pageDriver.findElement(By.linkText(link)).click();
		return this;
	}
	
	public StudentHomePage clickHomeBreadcrumb(){
		pageDriver.findElement(homeBreadcrumb).click();
		return new StudentHomePage(pageDriver);
	}
	
	public ContentHeader clickDatabaseBreadcrumb(){
		pageDriver.findElement(databaseBreadcrumb).click();
		return this;
	}
	
	public TopModuleMenu getTopMenu(){
		return topModuleMenu;
	}
	
	public ArticlePage search(String searchTerm){
		this.sendKeysToElement(searchTerm, searchTextField);
		this.waitImplicitly(1);
		click(By.linkText(searchTerm));
		this.waitImplicitly(1);
		
		return new ArticlePage(pageDriver);
	}
	
	public ArticlePage clickRandom(){
		pageDriver.findElement(randomArticleButton).click();
		return new ArticlePage(pageDriver);
	}
	
	public ArticlePage searchWithID(String searchTerm, String id){
		this.sendKeysToElement(searchTerm, searchTextField);
		this.waitImplicitly(2);
		
		ArrayList<WebElement> search = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"search_result\"]/ul/li/a"));
		
		for(WebElement s : search){
			String link = s.getAttribute("href");
			if(link.subSequence(link.lastIndexOf("/")+1, link.length()).toString().equals(id)){
				s.click();
				break;
			}
		}
		
/*
		this.waitImplicitly(1);
		click(By.linkText(searchTerm));
		this.waitImplicitly(1);
	*/	
		return new ArticlePage(pageDriver);
	}
}
