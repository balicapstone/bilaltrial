package PGOStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import PGOReadMoreNew.PGONewReadMoreLandingPage;
import SharedClasses.PGONewBasePage;
import UserClasses.User;

public class PGONewArticlePage extends PGONewBasePage{
	public By tabOne = By.id("screen-tab-0"); 
	public By tabTwo = By.id("screen-tab-1");
	public By tabThree = By.id("screen-tab-2");
	public By tabFour = By.id("screen-tab-3"); 
	public By tabFive = By.id("screen-tab-4");
	public By tabSix = By.id("screen-tab-5");
	public By playAudioButton = By.id("screen-audio-button");
	public By tabOneImage = By.id("screen-image-0");
	public By tabTwoImage = By.id("screen-image-1");
	public By tabThreeImage = By.id("screen-image-2");
	public By tabFourImage = By.id("screen-image-3");
	public By tabFiveImage = By.id("screen-image-4");
	public By searchTextField = By.id("search");
	public By randomArticleText = By.id("random-article");
	public By logo = By.xpath("//a[@class=\"header-logo-link\"]");
	public By printHeader = By.id("print-title");
	public By citeButton = By.id("print-footer-citation-button");
	public By printCitationButton = By.id("print-citation-button");
	public By printArticleButton = By.id("print-footer-article-button");
	public By printWholeArticleButton = By.id("print-article-button");
	
	public By printPageButton = By.id("print-article-screen-button");
	public By activityButton = By.id("print-footer-activities-button");
	
	//TODO: Switch to IDs when updated on site
	public By printShareWhatYouKnowButton = By.xpath("//button[@aria-label=\"print share what you know worksheet\"]"); //By.id("print-share-button");
	public By printActivityButton = By.xpath("//button[@aria-label=\"print activities worksheet\"]");//By.id("print-activity-button");
	public By questionsForUnderstanding = By.xpath("//button[@aria-label=\"print questions worksheet\"]");//By.id("print-question-button");
	
	public By videoButton = By.id("media-footer-video-button");
	public By listenButton = By.id("media-footer-listen-button");
	public By pauseListenButton = By.id("media-footer-pause-button");
	public By rangeMapButton = By.id("media-footer-range-map-button");
	public By timelineButton = By.id("media-footer-timeline-button");
	public By languageButton = By.className("language-toggle");
	public By randomArticleButton = By.id("random-article");
	
	public By folderText = By.id("folder-text");
	public By closeButton = By.id("close-button");
	
	public By screenReaderFooterText = By.xpath("//footer/span"); // By.cssSelector("#main_content > div > footer > span");
	public By footerCloseButton = By.id("close-button");
	
	public By copyrightLink = By.linkText("© 2019 Capstone. All Rights Reserved.");
	public By educatorsLink = By.linkText("Educator Resources");
	
	//TimelineElements
	public By timelineImage = By.id("timeline-head-image");
	public By timelineTitle = By.className("timeline-title");	
	
	public By videoDiv = By.xpath("//*[@class=\"video-frame\"]/div[@class=\"video-container\"]/video");
	
	public PGONewContentHeader header;
	
	public By[] initialVisibleElements = {citeButton, printArticleButton, activityButton, logo, tabOne, tabTwo, tabThree,
			tabFour, tabFive, tabSix, playAudioButton};
	
	public PGONewArticlePage(User u){
		user = u;
		pageDriver = u.getDriver();
		header = new PGONewContentHeader(u);
	}
	
	public StudentHomePageNew clickLogo(){
		click(logo);
		return new StudentHomePageNew(user);
	}
	
	public void playAudio(){
		click(playAudioButton);
	}
	
	public void clickTabByInt(int i){
		click(By.xpath("//div[@class=\"tab-bar\"]/button["+i+"]"));
	}
	
	public PGONewArticlePage clickTabOne(){
		click(tabOne);
		return this;
	}
	
	public PGONewArticlePage clickTabTwo(){
		click(tabTwo);
		return this;
	}
	
	public PGONewArticlePage clickTabThree(){
		click(tabThree);
		return this;
	}
	
	public PGONewArticlePage clickTabFour(){
		click(tabFour);
		return this;
	}
	
	public PGONewArticlePage clickTabFive(){
		click(tabFive);
		return this;
	}
	
	public PGONewArticlePage clickTabSix(){
		click(tabSix);
		return this;
	}
	
	public PGONewArticlePage clickRelatedArticles(){
		click(By.linkText("Related Articles"));
		return this;
	}
	
	public Boolean areThereRelatedArticles(){
		return this.isElementPresent(tabSix);
	}
	
	public Boolean isCiteButtonPresent(){
		Boolean present = isElementPresent(By.id("btn-cite"));
		if(present){
			citeButton = By.id("btn-cite");
		}
		return present;
	}

	public Boolean isPrintButtonPresent(){
		Boolean present = isElementPresent(By.id("btn-print"));
		if(present){
			printArticleButton = By.id("btn-print");
		}
		return present;
	}
	
	public Boolean isActivityButtonPresent(){
		Boolean present = isElementPresent(By.id("btn-activity"));
		if(present){
			activityButton = By.id("btn-activity");
		}
		return present;
	}
	
	public Boolean isVideoButtonPresent(){
		Boolean present = isElementPresent(By.id("btn-video"));
		if(present){
			videoButton = By.id("btn-video");
		}
		return present;
	}
	
	public Boolean isListenButtonPresent(){
		Boolean present = isElementPresent(By.id("btn-listen"));
		if(present){
			listenButton = By.id("btn-listen");
		}
		return present;
	}
	
	public Boolean isHabitatButtonPresent(){
		Boolean present = isElementPresent(By.id("btn-habitat"));
		if(present){
			rangeMapButton = By.id("btn-habitat");
		}
		return present;
	}
	
	public Boolean isTimelineButtonPresent(){
		Boolean present = isElementPresent(By.id("btn-timeline"));
		if(present){
			timelineButton = By.id("btn-timeline");
		}
		return present;
	}
	
	
	public void clickActivityButton(){
		click(activityButton);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(footerCloseButton));
	}
	
	public void clickCiteButton(){
		click(citeButton);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(footerCloseButton));
	}
	
	public void clickPrintButton(){
		click(printArticleButton);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(footerCloseButton));
	}
	
	public void clickRangeMapButton(){	
		click(rangeMapButton);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(footerCloseButton));
	}
	
	public void clickListenButton(){
		click(listenButton);
	}
	
	public void clickPauseListenButton(){
		click(pauseListenButton);
	}
	
	public void clickVideoButton(){
		click(videoButton);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(footerCloseButton));
	}
	
	public void clickTimelineButton(){
		click(timelineButton);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(footerCloseButton));
	}
	
	public Boolean LookForGlossary(String term){
		By currentTerm = By.xpath("//span[@data-glossary-lookup='"+term+"']");
		String upper = term.substring(0,1).toUpperCase() + term.substring(1);
		By upperTerm = By.xpath("//span[@data-glossary-lookup='"+ upper +"']");
		clickTabOne();
		waitImplicitly(1);
		int currentTab = 1;
		
		Boolean looking = true;
		while(looking){
			if(!click(currentTerm) && !click(upperTerm)){
				if(currentTab == 1){
					clickTabTwo();
					waitImplicitly(1);
				}		
				else if(currentTab == 2){
					clickTabThree();
					waitImplicitly(1);
				}
				else if(currentTab == 3){
					clickTabFour();
					waitImplicitly(1);
				}
				else if(currentTab == 4){
					clickTabFive();
					waitImplicitly(1);
				}
				else if(currentTab == 5){
						clickTabOne();
						waitImplicitly(1);
						currentTab = 1;
						looking = false;
					}
					currentTab++;
				}
			else{
				looking = false;
				currentTab = 1;
			}
		}
			return looking;
	}
	
	
	public void clickGlossaryTerm(String term){
		click(By.xpath("//span[@data-glossary-lookup='"+term+"']"));
	}
	
	public String getArticleName(){
		return pageDriver.findElement(By.xpath("//div[contains(@class, 'title-text')]/h3/span")).getText(); //*[@id="breadcrumb"]/li[10]/div //*[@id="breadcrumb"]/li[10]/div/h3/span
	} 
	
	public PGONewContentSelectionPage clickBreadcrumbByIndex(int i){
		int index = 3 + (2*i);
		pageDriver.findElement(By.xpath("//*[@id=\"breadcrumb\"]/li["+ index +"]/a")).click();
		return new PGONewContentSelectionPage(user);
	}
	
	public PGONewArticlePage searchForArticle(String title){
		pageDriver.findElement(searchTextField).sendKeys(title);
		this.closeSendKeysTab();
		
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.id(title+"_result")));
		
		click(By.id(title + "_result"));
		return new PGONewArticlePage(user);
	}
	
	public Boolean PlayVideo(){
		WebElement video = pageDriver.findElement(videoDiv);
		Boolean play = false;
		
		
		//if exectureScript does not return an object, the comman was not valid
		try{
		JavascriptExecutor js = (JavascriptExecutor) pageDriver;
		if(js.executeScript("arguments[0].play();", video) != null){
			play = true;
		}
		} catch (Exception e){
		
		}	
		return play;
	}
	
	public PGONewArticlePage clickLanguageButton(){
		click(languageButton);
		return new PGONewArticlePage(user);
	}
	
	public PGONewArticlePage clickRelatedArticleByName(String articleName){
		click(By.xpath("//img[@alt='"+articleName+"']"));
		return new PGONewArticlePage(user);
	}
	
	public PGONewReadMoreLandingPage clickBookByName(String bookName){
		WebElement element = pageDriver.findElement(By.xpath("//div[contains(text(), '"+bookName+"')]")).findElement(By.xpath("..")).findElement(By.xpath(".."));
		try {
			this.moveToElement(element);
			click(element);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return new PGONewReadMoreLandingPage(user);
	}
	
	public PGONewReadMoreLandingPage clickBookByInt(String number){
		click(By.id("related-book-"+number));
		return new PGONewReadMoreLandingPage(user);
	}
}
