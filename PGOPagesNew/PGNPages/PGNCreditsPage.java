package PGNPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class PGNCreditsPage extends BasePage{
		public By advisorsLink = By.linkText("Our Advisers");
		public LoginHeader header;
		public FooterMenu footer;
		
		public PGNCreditsPage(WebDriver driver){
			pageDriver = driver;
			header = new LoginHeader(driver);
			footer = new FooterMenu(driver);
		}
}
