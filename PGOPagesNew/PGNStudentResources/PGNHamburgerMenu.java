package PGNStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class PGNHamburgerMenu extends BasePage{
	
	public By StatesDiv = By.id("module_6");
	public By ScienceDiv = By.id("module_9");
	public By AmericanIndiansDiv = By.id("module_10");
	public By SocialStudiesDiv = By.id("module_11");
	public By BiographiesDiv = By.id("module_15");
	
	public PGNHamburgerMenu(WebDriver driver){
		pageDriver = driver;
	}
	
	public PGNHamburgerMenu backToFullMap(){
		click(By.className("back"));
		return this;
	}
	
	public PGNHamburgerMenu openStatesDiv(){
		click(StatesDiv);
		return this;
	}
	
	public PGNHamburgerMenu openScienceDiv(){
		click(ScienceDiv);
		return this;
	}
	
	public PGNHamburgerMenu openAmericanIndiansDiv(){
		click(AmericanIndiansDiv);
		return this;
	}
	
	public PGNHamburgerMenu openSocialStudiesDiv(){
		click(SocialStudiesDiv);
		return this;
	}
	
	public PGNHamburgerMenu openBiographiesDiv(){
		click(BiographiesDiv);
		return this;
	}
	
	public PGNHamburgerMenu switchToMap(){
		click(By.className("map_btn"));
		return this;
	}
	
	public PGNHamburgerMenu switchToList(){
		click(By.className("list_btn"));
		return this;
	}
	
	public PGNHamburgerMenu clickStatesRegion(String region){
		click(By.id(region.toLowerCase()));
		return this;
	}
	
	public PGNHamburgerMenu clickState(String article){
		click(By.id(article + "_Text"));
		return this;
	}

	public PGNHamburgerMenu clickAmericanIndianRegion(String region){
		click(By.id(region));
		return this;
	}
	
	public PGNHamburgerMenu clickListSubItem(String item){
		//*[@id="hamburger_list_container"]/ul[1]/li[1]
		click(By.xpath("//div[@id='hamburger_list_container']//li[contains(text(),'"+item+"')]")); //(item));
		return this;
	}
	
	public By getListSubItemXpath(String item){
		return By.xpath("//div[@id='hamburger_list_container']//li[contains(text(),'"+item+"')]");
	}
	
	public PGNArticlePage clickSubItemIntoArticle(String item){
		//*[@id="hamburger_list_container"]/ul[1]/li[1]
		click(By.xpath("//div[@id='hamburger_list_container']//li[contains(text(),'"+item+"')]")); //(item));
		return new PGNArticlePage(pageDriver);
	}
	
	public PGNArticlePage clickSubItemIntoArticle(String item, PGNArticleType t){
		//*[@id="hamburger_list_container"]/ul[1]/li[1]
		click(By.xpath("//div[@id='hamburger_list_container']//li[contains(text(),'"+item+"')]")); //(item));
		return new PGNArticlePage(pageDriver, t);
	}
	
	public PGNArticlePage clickMapItemIntoArticle(String item){
		click(By.id(item));
		return new PGNArticlePage(pageDriver);
	}
	
	public PGNArticlePage clickMapItemIntoArticle(String item, PGNArticleType t){
		click(By.id(item));
		return new PGNArticlePage(pageDriver, t);
	}
}