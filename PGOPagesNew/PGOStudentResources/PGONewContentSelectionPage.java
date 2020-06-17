package PGOStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Games.PGOChooseGamePage;
import MasterAccount.LinkAccountsToolbar;
import PGOPages.ContentHeader;
import PGOPages.LoginPage;
import SharedClasses.PGONewBasePage;
import SharedClasses.PGONewThirdPartyPage;
import SharedClasses.ThirdPartyPage;
import UserClasses.User;

public class PGONewContentSelectionPage extends PGONewBasePage{
	public By randomArticleButton = By.id("random-article");
	public By texasOnlyButton = By.id("texas-button");
	
	public By gamesButton = By.id("games-button");
	public By questionOfTheDayButton = By.id("question-button");
	
	public By closeButton = By.id("close-button");
	public By folderText = By.id("folder-text");
	public By questionText = By.id("question-title-text");
	public By voteButton = By.id("poll-button");
	public By pollDisclaimerText = By.id("question-disclaimer-text");
	
	public By copyrightLink = By.linkText("© 2019 Capstone. All Rights Reserved.");
	
	public By educatorsResourcesLink = By.linkText("Educator Resources");
	
	public PGONewContentHeader header;
	
	private By[] initialVisibleElements = {gamesButton, questionOfTheDayButton, randomArticleButton};
	
	public PGONewContentSelectionPage(User u){
		user = u;
		pageDriver = u.getDriver();
		header = new PGONewContentHeader(u);
	}
	
	public PGONewContentSelectionPage clickCategoryByName(String name){
		click(pageDriver.findElement(By.id(name)));
		
		return new PGONewContentSelectionPage(user);
	}
	
	public PGONewArticlePage clickArticleByName(String name){
		click(pageDriver.findElement(By.id(name)));
		
		return new PGONewArticlePage(user);
	}
	
	public void clickGamesButton(){
		click(gamesButton);
	}
	
	public void clickQuestionOfTheDayButton(){
		click(questionOfTheDayButton);
	}
	
	public StudentHomePageNew clickHomeBreadrumb(){
		click(header.homeBreadcrumb);
		return new StudentHomePageNew(user);
	}
	
	public PGONewContentSelectionPage clickBreadcrumbByName(String name){
		click(By.id(name));
		return new PGONewContentSelectionPage(user);
	}
	
	public PGONewArticlePage clickRandomArticleButton(){
		click(randomArticleButton);
		return new PGONewArticlePage(user);
	}
	
	public void searchForArticle(String name){
		sendKeysToElement(name, header.searchBar);
	}
	
	public PGONewArticlePage clickResultForSearch(String name){
		click(By.id(name + "_result"));
		
		return new PGONewArticlePage(user);
	}
	
	public PGONewArticlePage searchForArticleAndClickResult(String name){
		sendKeysToElement(name, header.searchBar);
		click(By.id(name + "_result"));
		
		return new PGONewArticlePage(user);
	}
	
	public StudentHomePageNew clickLogo(){
		click(header.logo);
		return new StudentHomePageNew(user);
	}
	
	public void clickGameByName(String name){
		click(By.xpath("//*[contains(text(),'"+name+"')]"));
	}
	
	public void clickCapstoneDropdown(){
		click(header.capstoneDropdown);
	}
	
	public PGONewThirdPartyPage clickPebbleGoNextLink(){
		click(header.pebbleGoNextLink);
		return new PGONewThirdPartyPage(user);
	}
	
	public PGONewThirdPartyPage clickCapstoneInteractiveLink(){
		click(header.capstoneInteractiveLink);
		return new PGONewThirdPartyPage(user);
	}
	
	public LoginPage logOut(){
		click(header.capstoneDropdown);
		click(header.signOutLink);
		
		return new LoginPage(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public StudentHomePageNew clickHomeBreadcrumb(){
		click(header.homeBreadcrumb);
		return new StudentHomePageNew(user);
	}

	public PGONewThirdPartyPage clickEducatorResourcesLink(){
		click(educatorsResourcesLink);
		return new PGONewThirdPartyPage(user);
	}
	
	public Boolean isContentPresent(String content){
		Boolean present = false;
		
		try{
			present = this.isElementPresent(By.id(content));
		}catch(Exception e){
			
		}
		
		return present;
	}
	
	public Boolean isContentDuplicated(String content){
		return (pageDriver.findElements(By.id(content)).size() > 1);
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
	
	public void clickQuestionOfTheDayAnswerByName(String answer){
		click(By.xpath("//p[@class=\"answer-title\" && contains(text(), \""+answer+"\")]"));
	}
	
	public void clickQuestionOfTheDayAnswerByInt(int i){
		click(By.xpath("//ul[@class=\"answer-container-ul\"]/li["+i+"]/button"));
	}
	
	public String getQuestionOfTheDayAnswerByInt(int i){
		return pageDriver.findElement(By.xpath("//ul[@class=\"answer-container-ul\"]/li["+i+"]//p[@class=\"answer-title\"]")).getText();
	}
	
	public void clickQuestionOfTheDayAnswer(){
		click(By.className("answer-title"));
	}
	
	public void clickQuestionOfTheDayVoteButton(){
		click(voteButton);
	}
	
	public void clickTexasOnlyButton(){
		click(texasOnlyButton);
	}
}
