package CMSPages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditQuestionOfTheDayModal extends QuestionOfTheDayModal{
	public By updateQuestionsButton = By.xpath("//*[@id=\"poll-modal\"]/button[1]");
	public By addAnswersButton = By.xpath("//*[@id=\"poll-modal\"]/button[2]");
	public By closeButton = By.id("test-close-button-update");
	
	public EditQuestionOfTheDayModal(WebDriver driver){
		super(driver);
	}
	
	
	public void clickUpdateQuestionOfTheDay(){
		//this.scrollToElement(getModalElement(By.linkText("Update Question/Answers")));
		pageDriver.findElement(updateQuestionsButton).click();
	}
	
	public void clickAddAnswer(){
		pageDriver.findElement(addAnswersButton).click();
	}
	
	public void toggleAnswerByInt(int i){
		int realPosition = 3 + i;
		getModalElement(By.xpath("//*[@id=\"poll-modal\"]/div["+ realPosition +"]/div")).click(); 
	}
	
	public QuestionsPage closeModal(){
		click(closeButton);
		return new QuestionsPage(pageDriver);
	}
	
	public void toggleAnswerByName(String text){
		ArrayList<WebElement> answers = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"poll-modal\"]/div/input"));
		
		int i = 0;
		for(WebElement w : answers){
			if(w.getText().equals(text)){
				click(By.xpath("//*[@id=\"poll-modal\"]/div["+4+i+"]/div"));
				break;
			}
			i++;
		}
	}
}
