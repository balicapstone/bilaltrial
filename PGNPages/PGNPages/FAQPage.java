package PGNPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class FAQPage extends BasePage{
	public PlacingAnOrderTab placingAnOrderTab;
	public TroubleshootingTab troubleShootingTab;
	public GeneralQuestionsTab generalQuestionsTab;
	public By placingAnOrderLink = By.linkText("Placing an Order");
	public By troubleshootingLink = By.linkText("Troubleshooting");
	public By generalQuestionsLink = By.linkText("General Questions");
	public LoginHeader header;
	public FooterMenu footer;
	
	public By[] initialVisibleElements = {placingAnOrderLink,troubleshootingLink, generalQuestionsLink};
	
	public FAQPage(WebDriver driver){
		pageDriver = driver;
		placingAnOrderTab = new PlacingAnOrderTab(driver);
		header = new LoginHeader(driver);
		footer = new FooterMenu(driver);
	}
	
	public void clickPlacingAnOrderTab(){
		click(placingAnOrderLink);
		placingAnOrderTab = new PlacingAnOrderTab(pageDriver);
	}
	
	public void clickTroubleshootingTab(){
		click(troubleshootingLink);
		troubleShootingTab = new TroubleshootingTab(pageDriver);
	}
	
	public void clickGeneralQuestionsTab(){
		click(generalQuestionsLink);
		generalQuestionsTab = new GeneralQuestionsTab(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
