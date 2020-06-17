package PGNStudentResources;


import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import MasterAccount.LinkAccountsToolbar;
import Modals.ActivityModal;
import Modals.CitationsModal;
import Modals.DictionaryModal;
import Modals.ImageModal;
import Modals.RelatedArticlesModal;
import Modals.StatesActivityModal;
import Modals.TerminologyModal;
import PGNModals.PGNActivityModal;
import PGNModals.PGNCitationsModal;
import PGNModals.PGNDictionaryModal;
import PGNModals.PGNRelatedArticlesModal;
import PGNModals.PGNStatesActivityModal;
import PGNModals.PGNTerminologyModal;
import SharedClasses.BasePage;

public class PGNArticlePage extends BasePage{
	public PGNStudentHeader header;
	public By title = By.xpath("//*[@id='breadcrumb']/ul/li[contains(@class, 'current')]");
	public PGNArticleType type;
//	public By activityButton = By.id("activity");
	
	public By activityButton = By.xpath("//*[@id=\"footer-activity-button-pgn\"]");
	
	public By dictionaryButton = By.id("dictionary");
	public By citationsButton = By.id("citation");
	public By relatedArticlesButton = By.id("related-articles");
	public By terminologyLink = By.id("american-indians");
	public By teacherResourcesLink = By.linkText("Teacher Resources");
	public LinkAccountsToolbar laToolbar;
	
	public By MapText = By.xpath("//*[@id=\"root\"]/div/div[2]/main/div/div[1]/div[2]/div");
	
	//ActivityModals will not be active unless needed, so I will be using only one Article Type
	//not separate for Indians, States, Science
	public PGNActivityModal activity;
	public PGNCitationsModal citations;
	public PGNDictionaryModal dictionary;
	public PGNRelatedArticlesModal relatedArticles;
	public PGNTerminologyModal terminology;
	
	
	public PGNArticlePage(WebDriver driver){
		pageDriver = driver;
		header = new PGNStudentHeader(pageDriver);
		type = PGNArticleType.GENERIC;
		activity = new PGNActivityModal(pageDriver);
		citations = new PGNCitationsModal(pageDriver);
		laToolbar = new LinkAccountsToolbar(pageDriver);
	}
	
	
	public PGNArticlePage(WebDriver driver, PGNArticleType t){
		pageDriver = driver;
		header = new PGNStudentHeader(pageDriver);
		type = t;
	}
	
	//American Indians and Science Activites are the same
	public PGNActivityModal openActivity(){
		if(!this.isElementPresent(By.id("modal-container"))){
			pageDriver.findElement(activityButton).click();
		}
		
		if(type.equals(PGNArticleType.STATES)){
			activity = new PGNStatesActivityModal(pageDriver);
		}
		else{
			activity = new PGNActivityModal(pageDriver);
		}
		return activity;
	}
	
	public String getTitle(){
		waitForElement(title);
		return pageDriver.findElement(title).getText();
	}
	
	public PGNArticlePage clickNavigationTab(String tab){
		click(By.xpath("//*[@id='article-nav']/nav//div[contains(text(), '"+tab+"')]"));		
		return this;
	}
	
	public boolean isTabActive(String tab){
		WebElement tabDiv = pageDriver.findElement(By.xpath("//*[@id='article-nav']/nav//div[contains(text(), '"+tab+"')]"));
		return tabDiv.getAttribute("class").toString().contains("active");
	}
	
	public String getActiveTab(){
		return pageDriver.findElement(By.xpath("//*[@id='article-nav']/nav//div[contains(@class, 'active')]")).getText().toString();
	}
	
	public boolean clickBreadcrumbByName(){
		
		return true;
	}
	
	public PGNStudentHomePage clickHomeBreadcrumb(){
	//	click(By.xpath("//*[@class='icon-icon-home']"));
		click(By.id("home"));
		return new PGNStudentHomePage(pageDriver);
	}
	
	public void readArticle(){
		ArrayList<WebElement> elements = (ArrayList<WebElement>) this.getDriver().findElements(By.className("nav-item"));
		
		for(WebElement w: elements){
			String screen = w.getAttribute("data-screen-id").toString();
			w.click();
			waitImplicitly(5);
			
			if(!screen.equals("video")){
				this.getDriver().findElement(By.xpath("//*[@id='screen-id-"+screen+"']/..//div[contains(@class, 'play-btn')]")).click();
				waitImplicitly(30);
			}
			else{
				return;
			}
		}
	}
	
	public PGNDictionaryModal openDictionary(){
		pageDriver.findElement(dictionaryButton).click();
		dictionary = new PGNDictionaryModal(pageDriver);
		return dictionary;
	}
	
	public PGNCitationsModal openCitations(){
		pageDriver.findElement(citationsButton).click();
		citations = new PGNCitationsModal(pageDriver);
		return citations;
	}
	
	public PGNRelatedArticlesModal openRelatedArticles(){
		pageDriver.findElement(relatedArticlesButton).click();
		relatedArticles = new PGNRelatedArticlesModal(pageDriver);
		return relatedArticles;
	}
	
	public PGNTerminologyModal openTerminologyModal(){
		pageDriver.findElement(terminologyLink).click();
		terminology = new PGNTerminologyModal(pageDriver);	
		return terminology;
	}
	
	public void goToTeacherResources(){
		click(teacherResourcesLink);
	}
	
	public PGNDatabasePage clickBreadcrumbByText(String breadcrumb){
		//*[@id="breadcrumb"]/ul/a[2]/li
		click(By.xpath("//*[@id=\"breadcrumb\"]/ul/a/li[contains(text(), '"+breadcrumb+"')]"));
		return new PGNDatabasePage(pageDriver);
	}
	
	public ImageModal openImageModal(int section, int picture){
		click(By.xpath("//*[@id=\"screens\"]/div["+section+"]/div[1]/div["+picture+"]"));
		
		return new ImageModal(pageDriver);
	}
	
	public ImageModal clickImageButton(int section, int picture){
		//moveToElement(By.xpath("//*[@id=\"screens\"]/div["+section+"]/div[1]/div["+picture+"]/div"));
		click(getModalElement(By.xpath("//*[@id=\"screens\"]/div["+section+"]/div[1]/div["+picture+"]/div")));
		//*[@id="screens"]/div[1]/div[1]/div[2]/div
		return new ImageModal(pageDriver);
	}
}
