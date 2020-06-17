package CMSPages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import SharedClasses.BasePage;

public class QuestionOfTheDayModal extends BasePage{
	public By questionText = By.xpath("//*[@id=\"poll-modal\"]/div[1]/input");
	public By moduleSelect = By.id("moduleSelect");
	public By answerText = By.xpath("//*[@id=\"poll-modal\"]/div[3]/input");
	public By addQuestionsAndAnswersButton = By.xpath("//*[@id=\"poll-modal\"]/button[1]"); //*[@id="poll-modal"]/button[1]//*[@id="poll-modal"]/button[1]
	public By addNewAnswerButton = By.xpath("//*[@id=\"poll-modal\"]/button[2]");
	public By closeButton = By.id("test-close-button-create");
	
	
	public QuestionOfTheDayModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public void enterQuestionText(String text){
		this.getModalElement(questionText).sendKeys(text);
	}
	
	public void enterAnswerText(String text){
		getModalElement(answerText).clear();
		getModalElement(answerText).sendKeys(text);
	}
	
	public void clickAddAnswer(){
		getModalElement(addNewAnswerButton).click();
	}
	
	public void clickAddQuestionsAndAnswersButton(){
		getModalElement(addQuestionsAndAnswersButton).click();
	}
	
	public void removeAnswerByInt(int i){
		int realPosition = 2 + i;
		getModalElement(By.xpath("//*[@id=\"poll-modal\"]/div["+ realPosition +"]/div")).click(); 
	}
	
	public void enterAnswerByInt(String text, int i){
		int realPosition = 2 + i;
		
		getModalElement(By.xpath("//*[@id=\"poll-modal\"]/div["+realPosition+"]/input")).clear();
		getModalElement(By.xpath("//*[@id=\"poll-modal\"]/div["+realPosition+"]/input")).sendKeys(text);
	}
	
	
	public void selectModule(String test){
		Select select = new Select(getModalElement(moduleSelect));
		
		select.selectByVisibleText(test);
	}
	
	public Boolean isModulePresentInSelect(String text){
		Select select = new Select(getModalElement(moduleSelect));
		
		ArrayList<WebElement> options = (ArrayList<WebElement>) select.getOptions();
		
		for(WebElement w : options){
			if(w.getText().equals(text)){
				return true;
			}
		}
		
		return false;
	}
	
	public QuestionsPage closeModal(){
		click(closeButton);
		return new QuestionsPage(pageDriver);
	}
}
