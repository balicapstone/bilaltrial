package CMSPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ArticlesActivePage extends CMSHomePage{
	public By searchBar = By.id("test-search-input");
	public By searchButton = By.id("test-search-button");
	
	public ArticlesActivePage(WebDriver driver){
		super(driver);
	}
	
	public void clickModuleByName(String text){
		pageDriver.findElement(By.id("test-" + text)).click();
	}
	
	public String getStringOfModuleByName(String text){
		return pageDriver.findElement(By.id("test-" + text)).getText();
	}
	
	public void clickCategoryByName(String text){
		pageDriver.findElement(By.id("test-" + text)).click();
	}
	
	public Boolean isModulePresent(String text){
		return this.isElementPresent(By.id("test-" + text));
	}
	
	public Boolean isCategoryPresent(String text){
		return this.isElementPresent(By.id("test-" + text));
	}
	
	public Boolean isArticleActive(String text){
		WebElement article = pageDriver.findElement(By.id("test-"+text+"-active"));	
		
		if(article.getText().equals("ACTIVE")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void toggleArticleByName(String text){
		pageDriver.findElement(By.id("test-"+text+"-active")).click();
	}
	
	public Boolean isArticleToggleButtonPresent(String text){
		return this.isElementPresent(By.id("test-"+text+"-active"));
	}
	
	public void searchForArticle(String text){
		clearField(searchBar);
		this.getDriver().findElement(searchBar).sendKeys(text);
		this.closeSendKeysTab();
		
		
		this.waitImplicitly(2);
		
		click(searchButton);
	}
	
	public void clearSearchResults(){
		pageDriver.findElement(By.id("test-clear")).click();
	}
	
	public void openEditCategoryDiv(String article){
		click(By.id("test-edit-"+article+"-button"));
	}
	
	public Boolean isCategoryActive(String text){
		WebElement category = this.getDriver().findElement(By.id("test-"+text+"-active-category"));
		
		if(category.getText().equals("ACTIVE")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void toggleCategoryActive(String text){
		click(By.id("test-"+text+"-active-category"));
	}
	
	public Boolean isCategoryPublished(String text){
		WebElement category = this.getDriver().findElement(By.id("test-"+text+"-published-category"));
		
		if(category.getText().equals("PUBLISHED")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void toggleCategoryPublished(String text){
		click(By.id("test"+text+"-published-category"));
	}
	
	public Boolean isCategoryUSOnly(String text){
		WebElement usOnly = this.getDriver().findElement(By.id("test-"+text+"-us-category"));
		
		if(usOnly.getText().equals("US ONLY ACTIVE")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void toggleCategoryUSOnly(String text){
		click(By.id("test-"+text+"-us-category"));
	}
	
	public Boolean isCategoryTexasOnly(String text){
		WebElement texas = this.getDriver().findElement(By.id("test-"+text+"-texas-category"));
		
		if(texas.getText().equals("TEXAS ONLY ACTIVE")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void toggleCategoryTexasOnly(String text){
		click(By.id("test-"+text+"-texas-category"));
	}
	
	public void clickSubmitByArticle(String text){
		click(By.id("test-"+text+"-article-submit"));
	}
	
	public void clickSubmitByCategory(String text){
		click(By.id("test-"+text+"-category-submit"));
	}
	
	public Boolean isArticleUsOnly(String text){
		WebElement usOnly = this.getDriver().findElement(By.id("test-"+text+"-us"));
		
		if(usOnly.getText().equals("US ONLY ACTIVE")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void toggleArticleUsOnly(String text){
		click(By.id("test-"+text+"-us"));
	}
	
	public Boolean isArticleTexasOnly(String text){
		WebElement usOnly = this.getDriver().findElement(By.id("test-"+text+"-texas"));
		
		if(usOnly.getText().equals("US ONLY ACTIVE")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void toggleArticleTexasOnly(String text){
		click(By.id("test-"+text+"-texas"));
	}
}
