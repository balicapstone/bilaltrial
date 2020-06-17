package Modals;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QuestionOfTheDayModal extends BaseModal{
		public By VoteButton = By.xpath("//*[@class=\"polls-content\"]/button");
		public By FirstAnswerBox = By.xpath("//*[@class=\"poll-answer-container\"]/div[1]");
		public By SecondAnswerBox = By.xpath("//*[@class=\"poll-answer-container\"]/div[2]");
		public By ThirdAnswerBox = By.xpath("//*[@class=\"poll-answer-container\"]/div[3]");
		public By FourthAnswerBox = By.xpath("//*[@class=\"poll-answer-container\"]/div[4]");
		public By FirstResponseBox = By.xpath("//*[@class=\"poll-response-container\"]/div[1]");
		public By SecondResponseBox = By.xpath("//*[@class=\"poll-response-container\"]/div[1]");
		public By ThirdResponseBox = By.xpath("//*[@class=\"poll-response-container\"]/div[1]");
		public By FourthResponseBox = By.xpath("//*[@class=\"poll-response-container\"]/div[1]");
		
		public QuestionOfTheDayModal(WebDriver driver){
			super(driver);
		}
		
		public QuestionOfTheDayModal clickAnswerByINT(int answer){
			click(By.xpath("//*[@class=\"poll-answer-container\"]/div["+answer+"]/div[2]"));
			return this;
		}
		
		public int getNumberOfAnwers(){
			return pageDriver.findElements(By.xpath("//*[@class=\"poll-answer-box\"]")).size();
		}
		
		public int getNumberofResponses(){
			return pageDriver.findElements(By.xpath("//*[@class=\"poll-response\"]")).size();
		}
		
		public Boolean isAnswerActive(int answer){
			WebElement Box = pageDriver.findElement(By.xpath("//*[@class=\"poll-answer-container\"]/div["+answer+"]"));
			if(Box.getAttribute("class").contains("active")){
				return true;
			}
			return false;
		}
		
		public QuestionOfTheDayModal vote(){
			click(VoteButton);
			return this;
		}
		
		public ArrayList<String> getAnswers(){
			ArrayList<String> answers = new ArrayList<String>();
			
			ArrayList<WebElement> answerElements = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@class=\"poll-answer\"]"));
			
			for(WebElement w : answerElements){
				answers.add(w.getText());
			}
			
			return answers;
		}
		
		public Boolean AnswersMatch(ArrayList<String> oldAnswers){
			boolean match = true;
			
			for(String s: oldAnswers){
				Boolean displayed = pageDriver.findElement(By.xpath("//*[@class=\"poll-answer-container\"]//div[contains(text(),'"+s+"')]")).isDisplayed();
				match = match && displayed;
			}
			
			return match;
		}
}
