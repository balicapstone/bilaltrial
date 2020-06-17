package NoNonsense.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import SharedClasses.BasePage;
import UserClasses.User;

public class SearchPage extends BasePage{

	private By searchField = By.id("search-text");
	private By showAllButton = By.linkText("Show All");
	public NoNonsenseHeader header;
	
	public SearchPage(WebDriver driver){
		pageDriver = driver;
		header = new NoNonsenseHeader(pageDriver);
	}
	
	public SearchPage(User u){
		pageDriver = u.getDriver();
		header = new NoNonsenseHeader(pageDriver);
	}
	
	
	public SearchPage search(String query){
		clearField(searchField);
		sendKeysToElement(query, searchField);
		waitImplicitly(1);
		return this;
	}
	
	public SearchPage addToSearch(String query){
		sendKeysToElement(query, searchField);
		waitImplicitly(1);
		return this;
	}
	
	public String getTitleByIndex(int num){
		////*[@id="lesson-library"]/div/div[3]/div[2]/div/div[1]
		return pageDriver.findElement(By.xpath("//*[@id='lesson-library']/div/div[3]/div["+num+"]/div/div[1]")).getText();
	}
	
	public SearchPage showAll(){
		click(showAllButton);
		waitImplicitly(1);
		return this;
	}
	
	public Boolean areAllDocumentsLessonPlans(){
		Boolean assessments = true;
		List<WebElement> documents = pageDriver.findElements(By.className("doc-link"));
		
		for(WebElement w : documents){
			assessments = assessments && w.getText().equals("Teaching Sequences for Writing");
		}
		return assessments;
	}
	
	public Boolean areAllDocumentsAssessments(){
		//
		Boolean assessments = true;
		List<WebElement> documents = pageDriver.findElements(By.className("doc-link"));
		
		for(WebElement w : documents){
			assessments = assessments && w.getText().equals("Assessment of Writing");
		}
		
		return assessments;
	}
	
	public ContentPopup getDocumentsForTitle(String title){
		
		List<WebElement> documents = pageDriver.findElements(By.className("doc-title"));
		
		for(WebElement w : documents){
			if(w.getText().equals(title)){
				w.click();
			}
		}
		
		//click(By.xpath());
		
		return new ContentPopup(this.getDriver());
	}
	
}
