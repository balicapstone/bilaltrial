package CMSGames;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CMSPages.CMSHomePage;
import CMSPages.EditGamesPage;

public class EditZoomGame extends CMSHomePage{
	public By answersInput = By.id("test-input-answers");
	public By correctAnswerInput = By.id("test-input-correct");
	public By imageInput = By.id("test-input-image");
	public By questionInput = By.id("test-input-question");
	public By submitButton = By.id("test-submit-entry");
	public By clearButton = By.id("test-clear-entry");
	
	public EditZoomGame(WebDriver driver){
		super(driver);
	}
	
	public void EditEntryByInt(int i){
		click(By.id("test-edit-entry-"+ i));
	}
	
	public void deleteEntryByInt(int i){
		click(By.id("test-delete-entry-"+ i));
	}
	
	public void setAnswers(String answers){
		pageDriver.findElement(answersInput).clear();
		pageDriver.findElement(answersInput).sendKeys(answers);
	}
	
	public void setCorrectAnswer(String correct){
		pageDriver.findElement(correctAnswerInput).clear();
		pageDriver.findElement(correctAnswerInput).sendKeys(correct);
	}
	
	public void setImage(String image){
		pageDriver.findElement(imageInput).clear();
		pageDriver.findElement(imageInput).sendKeys(image);
	}
	
	public void setQuestion(String question){
		pageDriver.findElement(questionInput).clear();
		pageDriver.findElement(questionInput).sendKeys(question);
	}
	
	public void clickClear(){
		pageDriver.findElement(clearButton).click();
	}
	
	public void clickSubmit(){
		pageDriver.findElement(submitButton).click();
	}
	
	public String getImage(){
		return pageDriver.findElement(imageInput).getAttribute("value");
	}
	
	public String getAnswers(){
		return pageDriver.findElement(answersInput).getAttribute("value");
	}
	
	public String getCorrecAnswers(){
		return pageDriver.findElement(correctAnswerInput).getAttribute("value");
	}
	
	public String getQuestion(){
		return pageDriver.findElement(questionInput).getAttribute("value");
	}
	
	public EditGamesPage clickModuleByName(String module){
		By element = By.id("test-" + module);
		try {
			moveToElement(element);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		click(element); 
		
		return new EditGamesPage(pageDriver);
	}
}
