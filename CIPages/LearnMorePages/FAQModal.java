package LearnMorePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;


public class FAQModal extends BasePage{
	public By QuestionOne = By.xpath("//*[@id=\"faq-content\"]/ol/li[1]/a");
	public By QuestionTwo = By.xpath("//*[@id=\"faq-content\"]/ol/li[2]/a");
	public By QuestionThree = By.xpath("//*[@id=\"faq-content\"]/ol/li[3]/a");
	public By QuestionFour = By.xpath("//*[@id=\"faq-content\"]/ol/li[4]/a");
	public By QuestionFive = By.xpath("//*[@id=\"faq-content\"]/ol/li[5]/a");
	public By QuestionSix = By.xpath("//*[@id=\"faq-content\"]/ol/li[6]/a");
	public By QuestionSeven = By.xpath("//*[@id=\"faq-content\"]/ol/li[7]/a");
	public By QuestionEight = By.xpath("//*[@id=\"faq-content\"]/ol/li[8]/a");
	public By QuestionNine = By.xpath("//*[@id=\"faq-content\"]/ol/li[9]/a");
	public By QuestionTen = By.xpath("//*[@id=\"faq-content\"]/ol/li[10]/a");
	public By QuestionEleven = By.xpath("//*[@id=\"faq-content\"]/ol/li[11]/a");
	public By QuestionTwelve = By.xpath("//*[@id=\"faq-content\"]/ol/li[12]/a");
	public By QuestionThirteen = By.xpath("//*[@id=\"faq-content\"]/ol/li[13]/a");
	public By QuestionFourteen = By.xpath("//*[@id=\"faq-content\"]/ol/li[14]/a[2]");
	public By AnswerOne = By.id("A1");
	public By AnswerTwo = By.id("A2");
	public By AnswerThree = By.id("A3");
	public By AnswerFour = By.id("A4");
	public By AnswerFive = By.id("A6");
	public By AnswerSix = By.id("A7");
	public By AnswerSeven = By.id("A8");
	public By AnswerEight = By.id("A9");
	public By AnswerNine = By.id("A10");
	public By AnswerTen = By.id("A11");
	public By AnswerEleven = By.id("A12");
	public By AnswerTwelve = By.id("A13");
	public By AnswerThirteen = By.id("A14");
	public By AnswerFourteen = By.xpath("//*[@id='faq-content']/ol/li[14]/a[2]");
	public By[] initialVisibleElements = {QuestionOne, QuestionTwo, QuestionThree, QuestionFour, QuestionFive, QuestionSix,
			QuestionSeven, QuestionEight, QuestionNine, QuestionTen, QuestionEleven, QuestionTwelve, QuestionThirteen, QuestionFourteen,
			AnswerOne, AnswerTwo, AnswerThree, AnswerFour, AnswerFive, AnswerSix, AnswerSeven, AnswerEight, AnswerNine, AnswerTen, 
			AnswerEleven, AnswerTwelve, AnswerThirteen, AnswerFourteen};
	
	public FAQModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
