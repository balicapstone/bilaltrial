package PGNPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class PGNGeneralQuestionsTab extends BasePage{
	public By placingAnOrderTab;
	public By customerServiceLink = By.linkText("customer service");
	public By pebbleGoNextLink = By.linkText("www.pebblegonext.com");
	public By articlesIncludedLink = By.linkText("See them here");
	public By salesRepLink = By.linkText("contact your local sales rep");
	public By firstSupportLink = By.xpath("//*[@id=\"container\"]/div/div/div/div[3]/p[10]/a");
	public By secondSupportLink = By.xpath("//*[@id=\"container\"]/div/div/div/div[3]/p[11]/a");
	
	public PGNGeneralQuestionsTab(WebDriver driver){
		pageDriver = driver;
	}
}
