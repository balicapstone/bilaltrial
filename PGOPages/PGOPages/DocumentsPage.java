package PGOPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;
import SharedClasses.ThirdPartyPage;

public class DocumentsPage extends BasePage{
	
	private By	animalsLink = By.linkText("Animals Content");
	private By	scienceLink = By.linkText("Science Content");
	private By	biographiesLink = By.linkText("Biographies Content");
	private By	socialStudiesLink = By.linkText("Social Studies Content");
	private By	dinosaursLink = By.linkText("Dinosaurs Content");
	private By	animalesLink = By.linkText("Animales Content"); 
	private By	multimodalLiteracyLink = By.linkText("PebbleGo and Multimodal Literacy");
	private By 	releaseNotesLink = By.linkText("Version 2.0 Release Notes");
	private By[] initialVisibleElements = {animalsLink, scienceLink, biographiesLink, socialStudiesLink, dinosaursLink, multimodalLiteracyLink};
	public NavigationBar navBar;
	public FooterMenu footerMenu;
	
	public DocumentsPage(WebDriver driver){
			pageDriver = driver;
			navBar = new NavigationBar(pageDriver);
			footerMenu = new FooterMenu(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
			return initialVisibleElements;
	}
	
	/*
	 * Class click methods.
	 */
	public ThirdPartyPage clickAnimalsLink(){
		pageDriver.findElement(animalsLink).click();
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickScienceLink(){
		pageDriver.findElement(scienceLink).click();
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickBiographiesLink(){
		pageDriver.findElement(biographiesLink).click();
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickSocialStudiesLink(){
		pageDriver.findElement(socialStudiesLink).click();
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickDinosaursLink(){
		pageDriver.findElement(dinosaursLink).click();
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickAnimalesLink(){
		pageDriver.findElement(animalesLink).click();
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickMultimodalLiteracyLink(){
		pageDriver.findElement(multimodalLiteracyLink).click();
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickReleaseNotesLink(){
		pageDriver.findElement(releaseNotesLink).click();
		return new ThirdPartyPage(pageDriver);
	}
}
