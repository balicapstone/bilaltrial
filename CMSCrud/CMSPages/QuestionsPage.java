package CMSPages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QuestionsPage extends CMSHomePage{
	
	By questionOfTheDayButton = By.xpath("//*[@id=\"poll-content\"]/div");		
			
	public QuestionsPage(WebDriver driver){
		super(driver);
	}
	
	public QuestionOfTheDayModal openModal(){
		click(questionOfTheDayButton);
		return new QuestionOfTheDayModal(pageDriver);
	}
	
	public EditQuestionOfTheDayModal editQuestion(String question){
		//*[@id="poll-content"]/div[2]/div/div[1] //*[@id="poll-content"]/div[3]/div/div[1]
		ArrayList<WebElement> questions = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"poll-content\"]/div/div/div[1]"));
		
		int index = 2;
		for(WebElement w : questions){
			if(w.getText().contains(question)){
				WebElement link = pageDriver.findElement(By.xpath("//*[@id=\"poll-content\"]/div["+index+"]/div/div[1]"));
				
				this.waitImplicitly(1);
				this.scrollToElement(link);
				this.waitImplicitly(1);
				
				link.click();
			}
			else{
				index++;
			}
		}
		return new EditQuestionOfTheDayModal(pageDriver);
	}
	
	public void toggleQuestionOfTheDayModal(String question){
		ArrayList<WebElement> questions = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"poll-content\"]/div/div/div[1]"));
		
		int index = 2;
		for(WebElement w : questions){
			if(w.getText().contains(question)){
				WebElement button = pageDriver.findElement(By.xpath("//*[@id=\"poll-content\"]/div["+index+"]/div/div[2]"));
				
				this.waitImplicitly(1);
				this.scrollToElement(button);
				this.waitImplicitly(1);
				
				button.click();
			}
			else{
				index++;
			}
		}
	}
	
	public Boolean isQuestionActivated(String question){
		ArrayList<WebElement> questions = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"poll-content\"]/div/div/div[1]"));
		Boolean activated = false;
		
		int index = 2;
		for(WebElement w : questions){
			if(w.getText().contains(question)){
				WebElement button = pageDriver.findElement(By.xpath("//*[@id=\"poll-content\"]/div["+index+"]/div/div[2]"));
				
				
				if(button.getText().equals("Deactivate")){
					activated = true;
					break;
				}
				else if(button.getText().equals("Activate")){
					activated = false;
					break;
				}
			}
			else{
				index++;
			}
		}
		
		return activated;
	}
}
