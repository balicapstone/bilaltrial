package LearnMorePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import BookTools.BookReader;

public class LearnMoreDemoPage extends LearnMoreBasePage{
	public By freeTrialLink = By.xpath("//*[@id='middle']/div[6]/h3/a");
	public By interactiveDemo1 = By.xpath("//*[@id='middle']/div[3]/a/img");
	public By interactiveDemo2 = By.xpath("//*[@id='middle']/div[4]/a/img");
	public By interactiveDemo3 = By.xpath("//*[@id='middle']/div[5]/a/img");
	
	public LearnMoreDemoPage(WebDriver driver){
		pageDriver = driver;
	}
	
	public BookReader launchDemoOne(){
		click(interactiveDemo1);
		return new BookReader(pageDriver);
	}
	
	public BookReader launchDemoTwo(){
		click(interactiveDemo2);
		return new BookReader(pageDriver);
	}
	
	public BookReader launchDemoThree(){
		click(interactiveDemo3);
		return new BookReader(pageDriver);
	}
}
