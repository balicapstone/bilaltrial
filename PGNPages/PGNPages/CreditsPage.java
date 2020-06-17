package PGNPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class CreditsPage extends BasePage{
		public By advisorsLink = By.linkText("Our Advisers");
		public LoginHeader header;
		public FooterMenu footer;
		
		public CreditsPage(WebDriver driver){
			pageDriver = driver;
			header = new LoginHeader(driver);
			footer = new FooterMenu(driver);
		}
}
