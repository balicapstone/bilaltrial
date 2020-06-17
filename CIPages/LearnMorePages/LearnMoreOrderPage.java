package LearnMorePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LearnMoreOrderPage extends LearnMoreBasePage{
	public By capstoneInteractiveeBooksLink = By.linkText("Capstone Interactive eBooks");
	public By capstonePubLink = By.linkText("www.CapstonePub.com/CIL");
	public By salesRepLink = By.linkText("sales representative");
	
	public LearnMoreOrderPage(WebDriver driver){
		pageDriver = driver;
	}
	
}
