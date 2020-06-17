package LearnMorePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompatibilityPage extends LearnMoreBasePage{
	public By browserText = By.xpath("//*[@id='middle']/div[2]/p[2]/strong[1]/u");
	public By versionText = By.xpath("//*[@id='middle']/div[2]/p[2]/strong[2]/u");
	public By systemText = By.xpath("//*[@id='middle']/div[2]/p[2]/strong[3]/u");
	public By systemCheck = By.xpath("//*[@id='middle']/div[2]/p[2]/img");
	public By javascriptCheck = By.xpath("//*[@id='middle']/div[2]/p[3]/img");
	public By flashPlayerCheck = By.xpath("//*[@id='middle']/div[2]/p[4]/img");
	public By bookThumbnail = By.id("book-thumbnail");
	public By downloadTime = By.id("download-time");
	public By minimumRequirementsLink = By.id("//*[@id='middle']/div[3]/p/a");
	public By[] initialVisibleElements = {};
	
	public CompatibilityPage(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
