package ArticleModals;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import SharedClasses.BasePage;

public class PGOQuestionOfTheDayModal extends BasePage{
		public By VoteButton = By.id("submit-btn");//By.xpath("//*[@id=\"modal-container\"]/div/div/div/button");
		public By FirstAnswerBox = By.xpath("//*[@class=\"poll-answer-container\"]/div[1]");
		public By SecondAnswerBox = By.xpath("//*[@class=\"poll-answer-container\"]/div[2]");
		public By ThirdAnswerBox = By.xpath("//*[@class=\"poll-answer-container\"]/div[3]");
		public By FourthAnswerBox = By.xpath("//*[@class=\"poll-answer-container\"]/div[4]");
		public By FirstResponseBox = By.xpath("//*[@class=\"poll-response-container\"]/div[1]");
		public By SecondResponseBox = By.xpath("//*[@class=\"poll-response-container\"]/div[1]");
		public By ThirdResponseBox = By.xpath("//*[@class=\"poll-response-container\"]/div[1]");
		public By FourthResponseBox = By.xpath("//*[@class=\"poll-response-container\"]/div[1]");
		public By closeButton = By.id("btn-close-listen");
		public By voteTable = By.xpath("//*[@id='pollbox']/div[1]/table");
		
		public PGOQuestionOfTheDayModal(WebDriver driver){
			pageDriver = driver;
		}
		
		public PGOQuestionOfTheDayModal clickAnswerByINT(int answer){
			click(getModalElement(By.xpath("//*[@id=\"pollbox\"]/div[1]/table/tbody/tr["+answer+"]/td/label/span")));
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
		
		public PGOQuestionOfTheDayModal vote(){
			click(getModalElement(VoteButton));
			return this;
		}

		public ArrayList<WebElement> getAnswers(){
			return (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@class=\"poll-answer\"]"));
		}
		
		
		public Boolean AnswersMatch(ArrayList<WebElement> oldAnswers){
			boolean match = true;
			
			for(int i = 0; i< oldAnswers.size(); i++){
				match = oldAnswers.get(i).getText().equals(oldAnswers.get(i).getText()) && match;
			}
			
			return match;
		}
		
		public Boolean isActive(){
			return isElementPresent(closeButton);
		}
		
		public Boolean isAnswerPresent(String text){
			ArrayList<WebElement> answers = getAnswers();
			for(WebElement w: answers){
				if(w.getText().equals(text)){
					return true;
				}
			}
			return false;
		}
		
		public String getAnswerTextByInt(int i){
			String toReturn = "";
			try{
				toReturn = getModalElement(By.xpath("//*[@id=\"pollbox\"]/div[1]/table/tbody/tr["+i+"]/td/label")).getText();
			}catch(Exception e){
				
			}
			return toReturn;
		}
		
		public void closeModal(){
			click(closeButton);
		}
}
