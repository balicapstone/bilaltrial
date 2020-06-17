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
import SharedClasses.BasePage;

public class ArticlePage extends BasePage{
	public StudentHeader header;
	public By title = By.xpath("//*[@id='breadcrumb']/ul/li[contains(@class, 'current')]");
	public ArticleType type;
	public By activityButton = By.id("activity");
	public By dictionaryButton = By.id("dictionary");
	public By citationsButton = By.id("citation");
	public By relatedArticlesButton = By.id("related-articles");
	public By terminologyLink = By.id("american-indians");
	public By teacherResourcesLink = By.linkText("Teacher Resources");
	public LinkAccountsToolbar laToolbar;
	
	//ActivityModals will not be active unless needed, so I will be using only one Article Type
	//not separate for Indians, States, Science
	public ActivityModal activity;
	public CitationsModal citations;
	public DictionaryModal dictionary;
	public RelatedArticlesModal relatedArticles;
	public TerminologyModal terminology;
	
	
	public ArticlePage(WebDriver driver){
		pageDriver = driver;
		header = new StudentHeader(pageDriver);
		type = ArticleType.GENERIC;
		activity = new ActivityModal(pageDriver);
		citations = new CitationsModal(pageDriver);
		laToolbar = new LinkAccountsToolbar(pageDriver);
	}
	
	
	public ArticlePage(WebDriver driver, ArticleType t){
		pageDriver = driver;
		header = new StudentHeader(pageDriver);
		type = t;
	}
	
	//American Indians and Science Activites are the same
	public ActivityModal openActivity(){
		if(!this.isElementPresent(By.id("modal-container"))){
			pageDriver.findElement(activityButton).click();
		}
		
		if(type.equals(ArticleType.STATES)){
			activity = new StatesActivityModal(pageDriver);
		}
		else{
			activity = new ActivityModal(pageDriver);
		}
		return activity;
	}
	
	public String getTitle(){
		waitForElement(title);
		return pageDriver.findElement(title).getText();
	}
	
	public ArticlePage clickNavigationTab(String tab){
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
	
	public StudentHomePage clickHomeBreadcrumb(){
		click(By.xpath("//*[@class='icon-icon-home']"));
		return new StudentHomePage(pageDriver);
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
	
	public DictionaryModal openDictionary(){
		pageDriver.findElement(dictionaryButton).click();
		dictionary = new DictionaryModal(pageDriver);
		return dictionary;
	}
	
	public CitationsModal openCitations(){
		pageDriver.findElement(citationsButton).click();
		citations = new CitationsModal(pageDriver);
		return citations;
	}
	
	public RelatedArticlesModal openRelatedArticles(){
		pageDriver.findElement(relatedArticlesButton).click();
		relatedArticles = new RelatedArticlesModal(pageDriver);
		return relatedArticles;
	}
	
	public TerminologyModal openTerminologyModal(){
		pageDriver.findElement(terminologyLink).click();
		terminology = new TerminologyModal(pageDriver);	
		return terminology;
	}
	
	public void goToTeacherResources(){
		click(teacherResourcesLink);
	}
	
	public DatabasePage clickBreadcrumbByText(String breadcrumb){
		//*[@id="breadcrumb"]/ul/a[2]/li
		click(By.xpath("//*[@id=\"breadcrumb\"]/ul/a/li[contains(text(), '"+breadcrumb+"')]"));
		return new DatabasePage(pageDriver);
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
