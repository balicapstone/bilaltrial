package PGNPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class ExpertAdvisersPage extends BasePage{
	public FooterMenu footer;
	public LoginHeader header;
	public By firstParagraph = By.xpath("//*[@id=\"text-container\"]/div[2]/p[1]");
	public By secondParagraph = By.xpath("//*[@id=\"text-container\"]/div[2]/p[2]");
	public By thirdParagraph = By.xpath("//*[@id=\"text-container\"]/div[2]/p[2]");
	public By fourthParagraph = By.xpath("//*[@id=\"text-container\"]/div[2]/p[2]");
	public By[] initialVisibleElements = {};
	
	public ExpertAdvisersPage(WebDriver driver){
		pageDriver = driver;
		footer = new FooterMenu(driver);
		header = new LoginHeader(driver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
}
