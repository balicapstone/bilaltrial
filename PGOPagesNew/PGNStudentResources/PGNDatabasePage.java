package PGNStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Games.PGNChooseGamePage;
import MasterAccount.LinkAccountsToolbar;
import Modals.QuestionOfTheDayModal;
import SharedClasses.PGONewBasePage;
import SharedClasses.ThirdPartyPage;

public class PGNDatabasePage extends PGONewBasePage{
	public PGNStudentHeader header;
	public QuestionOfTheDayModal questionOfTheDay;
	public By communityLink = By.id("bottom-community"); //("Community & Teacher Resources");bottom-community
	// public By questionOfTheDayLink = By.id("trigger-polls");
	
	public By questionOfTheDayLink = By.id("question-button");
	public By homeBreadcrumb = By.className("icon-icon-home");
	public By pageTitle = By.xpath("//*[@id=\"content-box\"]/h1");
	public By gamesLink = By.xpath("//*[@id=\"entertainment-buttons\"]/a[1]");
	public LinkAccountsToolbar laToolbar;
	
	public PGNDatabasePage(WebDriver driver){
		pageDriver = driver;
		header = new PGNStudentHeader(driver);
		laToolbar = new LinkAccountsToolbar(driver);
	}
	
	public PGNDatabasePage clickCategory(String text){
	//	getModalElement(By.xpath("//*[@data-tile-name=\""+text+"\"]/a")).click();
		getModalElement(By.xpath("//*[@id=\""+text+"\"]/div[1]/div")).click();
		return this;
	}
	
	public PGNArticlePage clickArticle(String text){
		getModalElement(By.xpath("//*[@data-tile-name=\""+text+"\"]/a")).click();
		return new PGNArticlePage(pageDriver);
	}
	
	public PGNArticlePage clickArticle(String text, PGNArticleType type){
		click(By.linkText(text));
		return new PGNArticlePage(pageDriver, type);
	}
	
	public Boolean isContentPresent(String content){
		Boolean test = this.isElementPresent(By.xpath("//*[@id=\"content-box\"]/div/a/span[contains(text(), \""+ content +"\")]"));
		return test;
	}
	
	public Boolean isContentDuplicated(String content){
		return (this.pageDriver.findElements(By.xpath("//*[@data-title-name=\"" + content + "\"]/a")).size() > 1);
	}
	
	public ThirdPartyPage clickBottomCommunityLink(){
		click(communityLink);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickCommunityLink(){
		click(communityLink);
		return new ThirdPartyPage(pageDriver);
	}
	
	public QuestionOfTheDayModal openQuestionOfTheDay(){
		click(questionOfTheDayLink);
		questionOfTheDay = new QuestionOfTheDayModal(pageDriver);
		return questionOfTheDay;
	}
	
	public PGNDatabasePage clickBreadcrumbByText(String breadcrumb){
		click(By.xpath("//*[@id=\"breadcrumb\"]//*[contains(text(), '"+breadcrumb+"')]"));
		return new PGNDatabasePage(pageDriver);
	}
	
	public PGNStudentHomePage clickHomeBreadcrumb(){
		click(homeBreadcrumb);
		return new PGNStudentHomePage(pageDriver);
	}
	
	public String getTitle(){
		return pageDriver.findElement(pageTitle).getText();
	}
	

	
	public String getDatabaseEntryImageSrc(String entry){
		String imageSrc = pageDriver.findElement(By.xpath("//*[@id=\"content-box\"]/div[@data-tile-name='"+entry+"']/a/img")).getAttribute("src");
		return imageSrc;
	}
	
	public Boolean isDatabaseInactiveMessagePresent(){
		Boolean present = false;
		
		if(isElementPresent(By.xpath("//*[@id=\"content-box\"]/div[1]/p"))){
			String message = pageDriver.findElement(By.xpath("//*[@id=\"content-box\"]/div[1]/p")).getText();
			
			if(message.equals("Content not available. Please contact your teacher, librarian, or administrator.")){
				present = true;
			}
		}	
		return present;
	}
	
	public PGNChooseGamePage openGames(){
		click(gamesLink);
		return new PGNChooseGamePage(pageDriver);
	}	
	
	public String getBaseNameForTitle(String title){
		WebElement web;
		
		try{
			web =  pageDriver.findElement(By.xpath("//*[contains(@data-tile-name, \""+ title+"\")]/a/img"));
		}catch( Exception e){
			return "";
		}
		
		String baseName = web.getAttribute("src");
		baseName = baseName.substring(baseName.lastIndexOf("/")+1, baseName.indexOf(".jpg"));
		
		return baseName;
	}
	
	public String getMainImage(String title){
		WebElement web;
		try{
			web = pageDriver.findElement(By.xpath("//*[contains(@data-tile-name, \""+ title+"\")]/a/img"));
		}catch( Exception e){
			return "";
		}
		
		String image = web.getAttribute("src");
		return image.substring(image.lastIndexOf("/") + 1, image.length());
	}
	
	public String getDisplayedTitle(String title){
		WebElement web;
		try{
			web = pageDriver.findElement(By.xpath("//*[contains(@data-tile-name, \""+ title+"\")]/a/span"));
		}catch( Exception e){
			return "";
		}
		
		return web.getText();
	}
	
	public String getType(String title){
		WebElement web;
		
		try{
			web = pageDriver.findElement(By.xpath("//*[contains(@data-tile-name, \""+ title +"\")]"));
		} catch(Exception e){
			return "";
		}
			
		return web.getAttribute("data-tile-type").toString();
	}
	
	public String getIDForTitle(String title){
		WebElement web;
		
		try{
			web = pageDriver.findElement(By.xpath("//*[contains(@data-tile-name,\""+ title+"\")]"));
		}catch(Exception e){
			return "";
		}
		
		return web.getAttribute("data-tile-id").toString();	
	}
}
