package PGOStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Games.PGOChooseGamePage;
import MasterAccount.LinkAccountsToolbar;
import PGOPages.ContentHeader;
import PGOPages.LoginPage;
import SharedClasses.BasePage;
import SharedClasses.ThirdPartyPage;

public class ContentSelectionPage extends BasePage{
	public By gamesLink = By.id("games-button");
	//public By questionOfTheDayLink = By.id("btn-poll");
	
	public By questionOfTheDayLink = By.id("question-button");
	
//	private By homeBreadcrumb = By.xpath("//*[@id=\"home-crumb\"]/a/img");
	
	private By homeBreadcrumb = By.id("home");
	// private By databaseBreadcrumb = By.xpath("//*[@id=\"db-crumb\"]/a");
	
	private By databaseBreadcrumb = By.xpath("//*[@id='root']/div/div[2]/header/div/nav[2]/ol/li[2]");
	
	
	
	private By logOutButton = By.id("sign-out-button"); 
	private By randomArticleButton = By.id("random-article");
//	public By searchTextField = By.xpath("//*[@id=\"header-search\"]/input[1]");
	
	public By searchTextField = By.id("search");
	
	private By pebbleGoLogo = By.id("logo-pgo");
	private TopModuleMenu topModuleMenu;
	private LinkAccountsToolbar laToolbar;
	private ContentHeader contentHeader;
	private By bannedArticlesText = By.xpath("//*[@id=\"content\"]/div/p"); //("banned_articles");
	public By communitiesLink = By.id("bottom-community"); //("Teacher Resources / Community");
	
	private By[] initialVisibleElements = {gamesLink, questionOfTheDayLink, databaseBreadcrumb,
			randomArticleButton, searchTextField, homeBreadcrumb, pebbleGoLogo};
	
	public ContentSelectionPage(WebDriver driver){
		pageDriver = driver;
		topModuleMenu = new TopModuleMenu(driver);
		contentHeader = new ContentHeader(driver);
		laToolbar = new LinkAccountsToolbar(driver);
	}
	
	public LoginPage clickLogOutButton(){
		click(logOutButton);
		return new LoginPage(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public StudentHomePage clickHomeBreadcrumb(){
		click(homeBreadcrumb);
		return new StudentHomePage(pageDriver);
	}
	
	public ContentSelectionPage clickDatabaseBreadcrumb(){
		click(databaseBreadcrumb);
		return this;
	}
	
	public TopModuleMenu getTopMenu(){
		return topModuleMenu;
	}
	
	public ContentHeader getContentHeader(){
		return contentHeader;
	}
	
	public Boolean isAllContentBanned(){
		boolean displayed = false;
		try{
			displayed = pageDriver.findElement(bannedArticlesText).isDisplayed();
		} catch(Exception e){
			System.out.println(e.toString());
		}
		return displayed;
	}
	
	public Boolean isArticlePresentByName(String article){
		boolean displayed = false;
		try{
			displayed = pageDriver.findElement(By.linkText(article)).isDisplayed();
		} catch(Exception e){
			System.out.println(e.toString());
		}
		return displayed;
	}
	
	public Boolean isAllContentPresent(String[] content){
		boolean AllPresent = true;
		
		for(String s : content){
			try{
				AllPresent = AllPresent && pageDriver.findElement(By.linkText(s)).isDisplayed();
			} catch(Exception e){
				AllPresent = false;
			}
		}
		return AllPresent;
	}
	
	public ContentSelectionPage clickBreadcrumbByIndex(int i){
		//int index = 3 + (2*i);
		int index = 2 + i;
		
	//	pageDriver.findElement(By.xpath("//*[@id=\"breadcrumb\"]/li["+ index +"]/a")).click();
		
		pageDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/header/div/nav[2]/ol/li["+ index +"]/a")).click();
		
		return this;
	}
	
	public ContentSelectionPage clickCategoryByText(String text){
		click(By.linkText(text));
		return this;
	}
	
	public ArticlePage clickArticleByText(String text){
		click(By.linkText(text));
		return new ArticlePage(pageDriver);
	}
	
	public LinkAccountsToolbar getLinkAccountsToolbar(){
		return laToolbar;
	}
	
	public ThirdPartyPage clickCommunitiesLink(){
		click(communitiesLink);
		return new ThirdPartyPage(pageDriver);
	}
	
	
	public ArticleModals.PGOQuestionOfTheDayModal openQuestionOfTheDay(){
		click(questionOfTheDayLink);
		return new ArticleModals.PGOQuestionOfTheDayModal(pageDriver);
	}
	
	public PGOChooseGamePage clickGamesButton(){
		click(gamesLink);
		return new PGOChooseGamePage(pageDriver);
	}
	
	public ArticlePage searchForArticle(String result){
		pageDriver.findElement(searchTextField).sendKeys(result);
		this.closeSendKeysTab();
		this.waitImplicitly(5);
		
		click(By.xpath("//*[@id=\"search_result\"]/ul/li/a[contains(text(),'"+result+"')]"));
		return new ArticlePage(pageDriver);
	}
	
	public Boolean isContentPresent(String content){
		return this.isElementPresent(By.linkText(content));
	}
	
	public Boolean isContentDuplicated(String content){
		return (pageDriver.findElements(By.linkText(content)).size() > 1);
	}
	
	public Boolean isBaseNameConnectedToTitle(String basename, String title){
		try{
			return this.isElementPresent(By.xpath("//*[contains(@data-article-name, \""+ basename+"\")]/a/span[contains(text(), \""+title+"\")]"));
		}catch( Exception e){
			return false;
		}
	}
	
	public String getTitleForBaseName(String basename){
		WebElement web;
		
		try{
			web =  pageDriver.findElement(By.xpath("//*[contains(@data-article-name, \""+ basename+"\")]/a/span"));
		}catch( Exception e){
			return "";
		}
		
		return web.getText();
	}
	
	public Boolean isMainImageCorrect(String mainImage, String basename){
		WebElement web;
		try{
			web = pageDriver.findElement(By.xpath("//*[contains(@data-article-name, \""+ basename+"\")]/a/img"));
		}catch( Exception e){
			return false;
		}
		
		String image = web.getAttribute("src");
		image = image.substring(image.lastIndexOf("/") + 1, image.length());
		
		return image.equals(mainImage);
	}
	
	public String getMainImage(String basename){
		WebElement web;
		try{
			web = pageDriver.findElement(By.xpath("//*[contains(@data-article-name, \""+ basename+"\")]/a/img"));
		}catch( Exception e){
			return "";
		}
		
		String image = web.getAttribute("src");
		return image.substring(image.lastIndexOf("/") + 1, image.length());
	}
	
	public Boolean isMenuAudioCorrect(String menuAudio, String baseName){
		WebElement web;
		try{
			web = pageDriver.findElement(By.xpath("//*[contains(@id, \"" + "audio_" + baseName + "\")]/source"));
		}catch( Exception e){
			return false;
		}	
		String audio = web.getAttribute("src");
		
		return audio.substring(audio.lastIndexOf("/") + 1, audio.length()).equals(menuAudio);
	}
	
	public String getMenuAudio(String baseName){
		WebElement web;
		try{
			web = pageDriver.findElement(By.xpath("//*[contains(@id, \"" + "audio_" + baseName + "\")]/source"));
		}catch( Exception e){
			return "";
		}	
		String audio = web.getAttribute("src");
		
		return audio.substring(audio.lastIndexOf("/") + 1, audio.length());
	}
	
	public Boolean isTitleCorrect(String baseName, String title){
		WebElement web;
		try{
			web = pageDriver.findElement(By.xpath("//*[contains(@data-article-name, \""+ baseName+"\")]/a/span[contains(text(), \""+title+"\")]"));
		}catch( Exception e){
			return false;
		}
		
		return web.getText().equals(title);
	}
	
	public Boolean isTypeRight(String type, String baseName){
		WebElement web;
		
		try{
			web = pageDriver.findElement(By.xpath("//*[contains(@data-article-name, \""+ baseName +"\")]"));
		} catch(Exception e){
			return false;
		}
		
		String classes = web.getAttribute("class");
		Boolean Correct = false;
	
		if(type.contains("C")){
			Correct = classes.contains("category");
		}
		else if(type.contains("A")){
			Correct = classes.contains("article");
		}
			
		return Correct;
	}
	
	public String getType(String baseName){
		WebElement web;
		
		try{
			web = pageDriver.findElement(By.xpath("//*[contains(@data-article-name, \""+ baseName +"\")]"));
		} catch(Exception e){
			return "";
		}
		
		String classes = web.getAttribute("class");
		String type = "";
	
		if(classes.contains("category")){
			type = "C";
		}
		else if(classes.contains("article")){
			type = "A";
		}
			
		return type;
	}
	
	public String getIDForBaseName(String basename){
		WebElement web;
		
		try{
			web = pageDriver.findElement(By.xpath("//*[contains(@data-article-name,\""+ basename+"\")]/a"));
		}catch(Exception e){
			return "";
		}
		String ID = web.getAttribute("href");		
		
		return ID.substring(ID.lastIndexOf("/") + 1, ID.length());
	}
}
