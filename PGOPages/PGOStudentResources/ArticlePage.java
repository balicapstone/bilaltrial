package PGOStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ArticleModals.ActivityModal;
import ArticleModals.CitationModal;
import ArticleModals.GlossaryModal;
import ArticleModals.PrintModal;
import ArticleModals.RangeMapModal;
import ArticleModals.SoundModal;
import ArticleModals.TimelineModal;
import ArticleModals.VideoModal;
import MasterAccount.LinkAccountsToolbar;
import PGOPages.ContentHeader;
import SharedClasses.BasePage;

public class ArticlePage extends BasePage{
	//public By tabOne = By.xpath("//*[@id=\"tabs\"]/table/tbody/tr/td[1]");
//	public By tabTwo = By.xpath("//*[@id=\"tabs\"]/table/tbody/tr/td[2]");
//	public By tabThree = By.xpath("//*[@id=\"tabs\"]/table/tbody/tr/td[3]");
//	public By tabFour = By.xpath("//*[@id=\"tabs\"]/table/tbody/tr/td[4]");
//	public By tabFive = By.xpath("//*[@id=\"tabs\"]/table/tbody/tr/td[5]");
//	public By tabSix = By.xpath("//*[@id=\"tabs\"]/table/tbody/tr/td[6]");
	
	public By tabOne = By.id("screen-tab-0");
	public By tabTwo = By.id("screen-tab-1");
	public By tabThree = By.id("screen-tab-2");
	public By tabFour = By.id("screen-tab-3");
	public By tabFive = By.id("screen-tab-4");
	public By tabSix = By.id("screen-tab-5");
	
	
	public By tabOneAudio = By.xpath("//*[@id=\"tab1\"]/div[1]");
	public By tabTwoAudio = By.xpath("//*[@id=\"tab2\"]/div[1]");
	public By tabThreeAudio = By.xpath("//*[@id=\"tab3\"]/div[1]");
	public By tabFourAudio = By.xpath("//*[@id=\"tab4\"]/div[1]");
	public By tabFiveAudio = By.xpath("//*[@id=\"tab5\"]/div[1]");
	public By searchTextField = By.xpath("//*[@id=\"header-search\"]/input[1]");
	public By logo = By.id("siteTitle");
//	public By citeButton = By.id("btn-cite");
	public By citeButton = By.id("footer-citation-button");
	
//	public By printButton = By.id("btn-print");
	public By printButton = By.id("btn-print");
	
//	public By activityButton = By.id("btn-activity");
	public By activityButton = By.id("footer-activities-button");
	
	public By videoButton;
	public By listenButton;
	public By habitatButton;
	public By timelineButton;
	public TopModuleMenu topModuleMenu;
	public ContentHeader header;
	public LinkAccountsToolbar laToolbar;
	
	public ArticlePage(WebDriver driver){
		pageDriver = driver;
		topModuleMenu = new TopModuleMenu(pageDriver);
		header = new ContentHeader(pageDriver);
		laToolbar = new LinkAccountsToolbar(pageDriver);
	}
	
	public Boolean readTabOne(){
		return click(tabOneAudio);
	}
	
	public Boolean readTabTwo(){
		return click(tabTwoAudio);
	}
	
	public Boolean readTabThree(){
		return click(tabThreeAudio);
	}
	
	public Boolean readTabFour(){
		return click(tabFourAudio);
	}
	
	public Boolean readTabFive(){
		return click(tabFiveAudio);
	}
	
	public ArticlePage clickTabOne(){
		click(tabOne);
		return this;
	}
	
	public ArticlePage clickTabTwo(){
		click(tabTwo);
		return this;
	}
	
	public ArticlePage clickTabThree(){
		click(tabThree);
		return this;
	}
	
	public ArticlePage clickTabFour(){
		click(tabFour);
		return this;
	}
	
	public ArticlePage clickTabFive(){
		click(tabFive);
		return this;
	}
	
	public ArticlePage clickTabSix(){
		click(tabSix);
		return this;
	}
	
	public ArticlePage clickRelatedArticles(){
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
			printButton = By.id("btn-print");
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
			habitatButton = By.id("btn-habitat");
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
	
	public ActivityModal clickActivityButton(){
		isActivityButtonPresent();
		
		click(activityButton);
		return new ActivityModal(pageDriver);
	}
	
	public CitationModal clickCiteButton(){
		isCiteButtonPresent();
		
		click(citeButton);
		return new CitationModal(pageDriver);
	}
	
	public PrintModal clickPrintButton(){
		isPrintButtonPresent();
		
		click(printButton);
		return new PrintModal(pageDriver);
	}
	
	public RangeMapModal clickHabitatButton(){
		isHabitatButtonPresent();
		
		click(habitatButton);
		return new RangeMapModal(pageDriver);
	}
	
	public SoundModal clickListenButton(){
		isListenButtonPresent();
		
		click(listenButton);
		return new SoundModal(pageDriver);
	}
	
	public VideoModal clickVideoButton(){
		isVideoButtonPresent();
		click(videoButton);
		return new VideoModal(pageDriver);		
	}
	
	public TimelineModal clickTimelineButton(){
		isTimelineButtonPresent();
		click(timelineButton);
		
		return new TimelineModal(pageDriver);
	}
	
	public ArticlePage searchWithinDatabase(){
		
		
		return new ArticlePage(pageDriver);
	}
	
	public StudentHomePage clickLogo(){
		click(logo);
		return new StudentHomePage(pageDriver);
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
	
	public GlossaryModal clickGlossaryTerm(String term){
		click(By.xpath("//span[@data-glossary-lookup='"+term+"']"));
		return new GlossaryModal(pageDriver);
	}
	
	public String getArticleName(){
		return pageDriver.findElement(By.xpath("//div[contains(@class, 'title-text')]/h3/span")).getText(); //*[@id="breadcrumb"]/li[10]/div //*[@id="breadcrumb"]/li[10]/div/h3/span
	} 
	
	public ContentSelectionPage clickBreadcrumbByIndex(int i){
		//int index = 3 + (2*i);
		//pageDriver.findElement(By.xpath("//*[@id=\"breadcrumb\"]/li["+ index +"]/a")).click();
		
		int index = 2 + i;
		pageDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/header/div/nav[2]/ol/li["+ index +"]/a")).click();
		
		
		return new ContentSelectionPage(pageDriver);
	}
	
	public ArticlePage searchForArticle(String result){
		pageDriver.findElement(searchTextField).sendKeys(result);
		this.closeSendKeysTab();
		this.waitImplicitly(5);
		
		click(By.xpath("//*[@id=\"search_result\"]/ul/li/a[contains(text(),'"+result+"')]"));
		return new ArticlePage(pageDriver);
	}
}
