package PGNStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class HamburgerMenu extends BasePage{
	
	public By StatesDiv = By.id("module_6");
	public By ScienceDiv = By.id("module_9");
	public By AmericanIndiansDiv = By.id("module_10");
	public By SocialStudiesDiv = By.id("module_11");
	public By BiographiesDiv = By.id("module_15");
	
	public HamburgerMenu(WebDriver driver){
		pageDriver = driver;
	}
	
	public HamburgerMenu backToFullMap(){
		click(By.className("back"));
		return this;
	}
	
	public HamburgerMenu openStatesDiv(){
		click(StatesDiv);
		return this;
	}
	
	public HamburgerMenu openScienceDiv(){
		click(ScienceDiv);
		return this;
	}
	
	public HamburgerMenu openAmericanIndiansDiv(){
		click(AmericanIndiansDiv);
		return this;
	}
	
	public HamburgerMenu openSocialStudiesDiv(){
		click(SocialStudiesDiv);
		return this;
	}
	
	public HamburgerMenu openBiographiesDiv(){
		click(BiographiesDiv);
		return this;
	}
	
	public HamburgerMenu switchToMap(){
		click(By.className("map_btn"));
		return this;
	}
	
	public HamburgerMenu switchToList(){
		click(By.className("list_btn"));
		return this;
	}
	
	public HamburgerMenu clickStatesRegion(String region){
		click(By.id(region.toLowerCase()));
		return this;
	}
	
	public HamburgerMenu clickState(String article){
		click(By.id(article + "_Text"));
		return this;
	}

	public HamburgerMenu clickAmericanIndianRegion(String region){
		click(By.id(region));
		return this;
	}
	
	public HamburgerMenu clickListSubItem(String item){
		//*[@id="hamburger_list_container"]/ul[1]/li[1]
		click(By.xpath("//div[@id='hamburger_list_container']//li[contains(text(),'"+item+"')]")); //(item));
		return this;
	}
	
	public By getListSubItemXpath(String item){
		return By.xpath("//div[@id='hamburger_list_container']//li[contains(text(),'"+item+"')]");
	}
	
	public ArticlePage clickSubItemIntoArticle(String item){
		//*[@id="hamburger_list_container"]/ul[1]/li[1]
		click(By.xpath("//div[@id='hamburger_list_container']//li[contains(text(),'"+item+"')]")); //(item));
		return new ArticlePage(pageDriver);
	}
	
	public ArticlePage clickSubItemIntoArticle(String item, ArticleType t){
		//*[@id="hamburger_list_container"]/ul[1]/li[1]
		click(By.xpath("//div[@id='hamburger_list_container']//li[contains(text(),'"+item+"')]")); //(item));
		return new ArticlePage(pageDriver, t);
	}
	
	public ArticlePage clickMapItemIntoArticle(String item){
		click(By.id(item));
		return new ArticlePage(pageDriver);
	}
	
	public ArticlePage clickMapItemIntoArticle(String item, ArticleType t){
		click(By.id(item));
		return new ArticlePage(pageDriver, t);
	}
}