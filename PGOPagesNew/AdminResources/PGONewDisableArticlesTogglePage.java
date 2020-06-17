package AdminResources;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import PGOPages.LoginPage;
import PGOPagesNew.PGONewLoginPage;
import SharedClasses.PGONewBasePage;
import UserClasses.User;

public class PGONewDisableArticlesTogglePage extends PGONewBasePage{
	
	private By databaseBreadcrumb = By.xpath("//*[@id=\"db-crumb\"]/a");
	private By homeBreadcrumb = By.xpath("//*[@id=\"home-crumb\"]/a");
	private By enableAllButton = By.id("enable-all");
	private By disableAllButton = By.id("disable-all");
	private By selectDatabaseButton = By.linkText("Select Database");
	private By logoutButton = By.linkText("Logout");
	
	public PGONewDisableArticlesTogglePage(User u){
		user = u;
		pageDriver = u.getDriver();
	}
	
	public boolean verifyBreadCrumbCount(int count){
		int crumbCount = 0;
		List<WebElement> crumbs = pageDriver.findElements(By.className("bread_image"));
		
		return (crumbCount == crumbs.size());
	}
	
	public PGONewDisableArticlesDatabasePage clickHomeBreadCrumb(){
		click(homeBreadcrumb);
		return new PGONewDisableArticlesDatabasePage(user);
	}
	
	public Boolean isHomeBreadCrumbPresent(){
		return this.isElementPresent(homeBreadcrumb);
	}
	
	public PGONewDisableArticlesTogglePage clickDatabaseBreadcrumb(){
		click(databaseBreadcrumb);
		return this;
	}
	
	public PGONewDisableArticlesTogglePage clickEnableAllButton(){
		click(enableAllButton);
		return this;
	}
	
	public PGONewDisableArticlesTogglePage clickDisableAllButton(){
		click(disableAllButton);
		return this;
	}
	
	public PGONewDisableArticlesTogglePage enableAll(){
		clickDisableAllButton();
		waitImplicitly(1);
		clickDatabaseBreadcrumb();
		waitImplicitly(1);
		clickEnableAllButton();
		clickDatabaseBreadcrumb();
		waitImplicitly(1);
		return this;
	}
	
	public PGONewDisableArticlesTogglePage disableAll(){
		clickEnableAllButton();
		waitImplicitly(1);
		clickDisableAllButton();
		waitImplicitly(1);
		return this;
	}
	
	public PGONewLoginPage clickLogoutButton(){
		click(logoutButton);
		pageDriver.get("https://www.pebblego.com");
		return new PGONewLoginPage(pageDriver);
	}
	
	public PGONewDisableArticlesDatabasePage clickSelectDatabaseButton(){
		click(selectDatabaseButton);
		return new PGONewDisableArticlesDatabasePage(user);
	}
	
	public Boolean isSelectDatabaseButtonPresent(){
		return this.isElementPresent(selectDatabaseButton);
	}
	
	public int getCategoryCount(){
		List<WebElement> elements = pageDriver.findElements(By.className("type_C"));
		return elements.size();
	}
	
	public int getArticleCount(){
		List<WebElement> elements = pageDriver.findElements(By.className("type_A"));
		return elements.size();
	}
	
	public int getItemCount(){
		List<WebElement> elements =  pageDriver.findElements(By.xpath("//*[@id=\"article_listing\"]/tbody/tr"));
		return elements.size();
	}
	
	public PGONewDisableArticlesTogglePage toggleItemByName(String item){ ////*[@id="article_listing"]/tbody/tr[1]/td[1]/text()
		List<WebElement> elements = pageDriver.findElements(By.xpath("//*[@id=\"article_listing\"]/tbody/tr"));
		int i = 1;
		for(WebElement w: elements)
		{
			if(w.getAttribute("class").contains("type_A")){
				w = w.findElement(By.xpath("./td"));
				if(w.getText().contains(item)){
					break;
				}
			}
			else{
				w = w.findElement(By.xpath("./td/a"));
				if(w.getText().contains(item)){
					break;
				}
			}
			i++;
		}
		
		toggleItemByIndex(i);
		return this;
	}
	
	//Only the first doesn't have a form tag
	public PGONewDisableArticlesTogglePage toggleItemByIndex(int i){
		click(By.xpath("//*[@id=\"article_listing\"]/tbody/tr["+ String.valueOf(i) +"]/td[2]/form/div/a")); ///td[2]/form/div/select"));
		return this;
	}
	
	public PGONewDisableArticlesTogglePage clickItemsByIndex(int i){
		click(By.xpath("//*[@id=\"article_listing\"]/tbody/tr["+ i +"]/td/a"));
		return this;
	}
	
	//Adding a scroll to Element because list of articles and categories can extend past visible page
	public PGONewDisableArticlesTogglePage clickItemByName(String item){
		this.scrollToElement(pageDriver.findElement(By.linkText(item)));
		this.waitImplicitly(1);
		click(By.linkText(item));
		return this;
	}
	
	public boolean isItemOnByName(String item){
		List<WebElement> elements = pageDriver.findElements(By.xpath("//*[@id=\"article_listing\"]/tbody/tr"));
		int i = 0;
		for(WebElement w: elements)
		{
			i++;
			if(w.getText().contains(item)){
				break;
			}
		}
		return isItemOnByIndex(i);
	}
	

	/**
	 * Checks to see if an Item is on by index. If checking after toggle, page must be refreshed first.
	 * @param index
	 * @return
	 */
	public boolean isItemOnByIndex(int index){
		boolean toReturn = false;
		Select select = new Select(pageDriver.findElement(By.xpath("//*[@id=\"article_listing\"]/tbody/tr["+ index +"]/td[2]/form/div/select")));

		WebElement option = select.getFirstSelectedOption();

		if(option.getText().equals("On")){
			toReturn = true;
		}
		return toReturn;
	}
	
	public WebElement getLastItemSelectText(){
		List<WebElement> items = pageDriver.findElements(By.xpath("//*[@id=\"article_listing\"]/tbody/tr/td[2]/form/div/select"));
		Select select = new Select(items.get(items.size()-1));
		
		return select.getFirstSelectedOption();
	}
	
	public boolean areAllItemsOff(){
		List<WebElement> elements = pageDriver.findElements(By.xpath("//*[@id=\"article_listing\"]/tbody/tr"));
		boolean allOff = true;
		for(int i = 1; i<= elements.size(); i++){
			allOff = allOff && !isItemOnByIndex(i);
		}
		return allOff;
	}
	
	public boolean areAllItemsOn(){
		List<WebElement> elements = pageDriver.findElements(By.xpath("//*[@id=\"article_listing\"]/tbody/tr"));
		boolean allOn = true;
		for(int i = 1; i < elements.size(); i++){
			allOn = allOn && isItemOnByIndex(i);
		}
		return allOn;
	}
	
	public boolean isItemPresentByName(String itemTitle){
		boolean present = false;
		try{
			present = pageDriver.findElement(By.linkText(itemTitle)).isDisplayed();
		}catch(Exception e){
			present = false;
		}
		return present;
	}
	
	public boolean verifyBreadcrumb(int bCrumb, String bCrumbName){
		WebElement element = pageDriver.findElement(By.xpath("//*[@id=\"breadcrumb\"]/li[" + (3 + (2*bCrumb)) + "]/a/img"));

		bCrumbName = bCrumbName.toLowerCase().replace(" ", "_");
		return element.getAttribute("src").contains(bCrumbName);
	}
	
	public PGONewDisableArticlesTogglePage clickBreadcrumb(int bCrumb){
		click(By.xpath("//*[@id=\"breadcrumb\"]/li[" + (3 + (2*bCrumb)) + "]/a/img"));
		return this;
	}
	
	public WebElement getLastOnSelect(){
		ArrayList<WebElement> selects = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"flip-checkbox\"]/option[1]"));
		return selects.get(selects.size()-1);
	}

	
	public WebElement getLastOffSelect(){
		ArrayList<WebElement> selects = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"flip-checkbox\"]/option[2]"));
		return selects.get(selects.size()-1);
	}
	
	public WebElement getLastToggleDiv(){
		ArrayList<WebElement> selects = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//tr/td[2]/form/div[contains(@class, 'ui-flipswitch')]"));
		return selects.get(selects.size()-1);
	}
}
