package NoNonsense.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class FAQPage extends BasePage{

	public NoNonsenseHeader header;
	public By findOutMoreLink = By.xpath("//*[@id=\"main\"]/div/div/a[1]");
	public By raintreeEmail = By.linkText("feedback@raintree.co.uk");
	public By secondRaintreeEmail = By.xpath("//*[@id=\"main\"]/div/div/a[3]");
	public By thirdRaintreeEmail = By.xpath("//*[@id=\"main\"]/div/div/a[4]");
	public By[] initialVisibleElements = {findOutMoreLink, raintreeEmail, secondRaintreeEmail, thirdRaintreeEmail};
	
	public FAQPage(WebDriver driver){
		pageDriver = driver;
		header = new NoNonsenseHeader(pageDriver);
	}
}
