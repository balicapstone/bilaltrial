package PGOPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;
import SharedClasses.ThirdPartyPage;

public class FAQPage extends BasePage{

	public By trialPageLink = By.linkText("PebbleGo trial page");
	public By placeAnOrderLink = By.linkText("click here");
	public By contactSalesRepLink = By.linkText("contact your local sales rep");
	public By placingAnOrderTab = By.id("t1");
	public By troubleshootingTab = By.id("t2");
	public By generalQuestionsTab = By.id("t3");
	public By systemRequirementsLink = By.linkText("system requirements");
	public By pebbleGoLink = By.linkText("www.pebblego.com");
	public By awardsLink = By.linkText("click here");
	public By animalsLink = By.linkText("Animals");
	public By scienceLink = By.linkText("Science");
	public By biographiesLink = By.linkText("Biographies");
	public By socialStudiesLink = By.linkText("Social Studies");
	public By dinosaursLink = By.linkText("Dinosaurs");
	public By animalesLink = By.linkText("Animales");
	public By cienciaLink = By.linkText("Ciencia");
	public By biografias = By.linkText("Biografías");
	public By estudiosSociales = By.linkText("Estudios Sociales");
	public By generalQuestionsSalesRepLinkOne = By.xpath("//*[@id=\"content\"]/div[1]/div/div[3]/p[6]/a"); //"//*[@id=\"content\"]/div[1]/div/div[3]/p[7]/a");
	public By generalQuestionsSalesRepLinkTwo = By.xpath("//*[@id=\"content\"]/div[1]/div/div[3]/p[7]/a");
	public By supportEmailOne = By.xpath("//*[@id=\"content\"]/div[1]/div/div[3]/p[8]/a");
	public By supportEmailTwo = By.xpath("//*[@id=\"content\"]/div[1]/div/div[3]/p[9]/a");
	private By[] initialVisibleElements = {trialPageLink, placeAnOrderLink, contactSalesRepLink, placingAnOrderTab, troubleshootingTab, generalQuestionsTab};
	private By[] placingAnOrderTabElements = {trialPageLink, placeAnOrderLink, contactSalesRepLink};
	private By[] troubleshootingTabElements = {systemRequirementsLink};
	private By[] generalQuestionTabElements = {pebbleGoLink, awardsLink, animalsLink, scienceLink, biographiesLink, socialStudiesLink, animalesLink,
			dinosaursLink,generalQuestionsSalesRepLinkOne,generalQuestionsSalesRepLinkTwo,supportEmailOne,supportEmailTwo};
	public NavigationBar navBar;
	public FooterMenu footerMenu;
	
	public FAQPage(WebDriver driver){
		pageDriver = driver;
		navBar = new NavigationBar(pageDriver);
		footerMenu = new FooterMenu(pageDriver);		
	}
	
	/*
	 * Class click element methods.
	 */
	public FAQPage clickPlacingAnOrderTab(){
		pageDriver.findElement(placingAnOrderTab).click();
		return this;
	}
	
	public FAQPage clickTroubleshootingTab(){
		pageDriver.findElement(troubleshootingTab).click();
		return this;
	}
	
	public FAQPage clickGeneralQuestionsTab(){
		pageDriver.findElement(generalQuestionsTab).click();
		return this;
	}
	
	public TrialPage clickTrialPageLink(){
		pageDriver.findElement(trialPageLink).click();
		return new TrialPage(pageDriver);
	}	
	
	public ThirdPartyPage clickPlaceAnOrderLink(){
		pageDriver.findElement(placeAnOrderLink).click();
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickContactSalesRepLink(){
		pageDriver.findElement(contactSalesRepLink).click();
		return new ThirdPartyPage(pageDriver);
	}
	
	public SystemRequirementsPage clickSystemRequirementsLink(){
		pageDriver.findElement(systemRequirementsLink).click();
		return new SystemRequirementsPage(pageDriver);
	}
	
	public LoginPage clickPebbleGoLink(){
		pageDriver.findElement(pebbleGoLink).click();
		return new LoginPage(pageDriver);
	}
	
	public ThirdPartyPage clickAwardsLink(){
		pageDriver.findElement(awardsLink).click();
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickAnimalsLink(){
		this.scrollToElement(pageDriver.findElement(animalsLink));
		pageDriver.findElement(animalsLink).click();
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickScienceLink(){
		this.scrollToElement(pageDriver.findElement(scienceLink));
		pageDriver.findElement(scienceLink).click();
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickBiographiesLink(){
		this.scrollToElement(pageDriver.findElement(biographiesLink));
		pageDriver.findElement(biographiesLink).click();
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickSocialStudiesLink(){
		this.scrollToElement(pageDriver.findElement(socialStudiesLink));
		pageDriver.findElement(socialStudiesLink).click();
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickDinosaursLink(){
		this.scrollToElement(pageDriver.findElement(dinosaursLink));
		pageDriver.findElement(dinosaursLink).click();
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickAnimalesLink(){
		this.scrollToElement(pageDriver.findElement(animalesLink));
		pageDriver.findElement(animalesLink).click();
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickFirstSalesRepLink(){
		this.scrollToElement(pageDriver.findElement(generalQuestionsSalesRepLinkOne));
		pageDriver.findElement(generalQuestionsSalesRepLinkOne).click();
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickSecondSalesRepLink(){
		this.scrollToElement(pageDriver.findElement(generalQuestionsSalesRepLinkTwo));
		pageDriver.findElement(generalQuestionsSalesRepLinkTwo).click();
		return new ThirdPartyPage(pageDriver);
	}
	
	/*
	 * Different element grouping getters.
	 */
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public By[] getPlacingAnOrderTabElements(){
		return placingAnOrderTabElements;
	}
	
	public By[] getTroubleshootingTabElements(){
		return troubleshootingTabElements;
	}
	
	public By[] getGeneralQuestionTabElements(){
		return generalQuestionTabElements;
	}
}
