package PGOStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import MasterAccount.LinkAccountsToolbar;
import PGOPages.LoginPage;
import PGOPagesNew.PGONewLoginPage;
import SharedClasses.BasePage;
import SharedClasses.PGONewBasePage;

public class PGONewStudentHomePage extends PGONewBasePage {
	
	public By pebbleGoLogo = By.id("siteTitle");
	public By animalsModule = By.xpath("//a[@data-siteid=\"1\"]/span"); 
	public By scienceModule = By.xpath("//a[@data-siteid=\"2\"]/span"); 
	public By cienciaModule = By.xpath("//a[@data-siteid=\"12\"]/span"); 
	//public By biographiesModule = By.xpath("//a[@data-siteid=\"3\"]/span");

	
	public By biographiesModule = By.id("Biographies");
	
	public By biografiasModule = By.xpath("//a[@data-siteid=\"13\"]/span"); 
	public By socialStudiesModule = By.xpath("//a[@data-siteid=\"5\"]/span");
	public By estudiosSocialesModule = By.xpath("//a[@data-siteid=\"14\"]/span");
	//public By dinosaursModule = By.xpath("//a[@data-siteid=\"7\"]/span"); 
	public By dinosaursModule = By.xpath("//*[@id='Dinosaurs']/div/div");
	public By animalesModule = By.xpath("//a[@data-siteid=\"8\"]/span");
	public By searchTextField = By.xpath("//*[@id=\"header-search\"]/input[1]");
	public By logOutButton = By.id("logout");
	public By topHamburgerButton = By.id("hamburger-icon");
	public By homeBreadcrumb = By.id("home-crumb");
	public TopModuleMenu topModuleMenu;
	public LinkAccountsToolbar laToolbar;
	
	private By[] initialVisibleElements = {pebbleGoLogo, animalsModule, scienceModule, 
			biographiesModule, socialStudiesModule, dinosaursModule, logOutButton,
			topHamburgerButton, homeBreadcrumb};
	
	public PGONewStudentHomePage(WebDriver driver){
		pageDriver = driver;
		topModuleMenu = new TopModuleMenu(driver);
		laToolbar = new LinkAccountsToolbar(driver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public PGONewStudentHomePage clickHamburgerButton(){
		click(topHamburgerButton);
		return this;
	}
	
	public PGONewAnimalsContentPage clickAnimalsModule(){
		click(animalsModule);
		return new PGONewAnimalsContentPage(pageDriver);
	}
	
	public PGONewScienceContentPage clickScienceModule(){
		click(scienceModule);
		return new PGONewScienceContentPage(pageDriver);
	}
	
	public PGONewBiographiesContentPage clickBiographiesModule(){
		click(biographiesModule);
		return new PGONewBiographiesContentPage(pageDriver);
	}
	
	public PGONewSocialStudiesContentPage clickSocialStudiesModule(){
		click(socialStudiesModule);
		return new PGONewSocialStudiesContentPage(pageDriver);
	}
	
	public PGONewDinosaursContentPage clickDinosaursModule(){
		click(dinosaursModule);
		return new PGONewDinosaursContentPage(pageDriver);
	}
	
	public PGONewAnimalesContentPage clickAnimalesModule(){
		click(animalesModule);
		return new PGONewAnimalesContentPage(pageDriver);
	}
	
	public ContentSelectionPage clickCienciaModule(){
		click(cienciaModule);
		return new ContentSelectionPage(pageDriver);
	}
	
	public PGONewBiografiasContentPage clickBiografiasModule(){
		click(biografiasModule);
		return new PGONewBiografiasContentPage(pageDriver);
	}
	
	public ContentSelectionPage clickEstudiosSocialesModule(){
		click(estudiosSocialesModule);
		return new ContentSelectionPage(pageDriver);
	}
	
	public PGONewLoginPage clickLogOutButton(){
		click(logOutButton);
		return new PGONewLoginPage(pageDriver);
	}
	
	public PGONewStudentHomePage clickHomeBreadcrumb(){
		click(homeBreadcrumb);
		return this;
	}
	
	public PGONewStudentHomePage clickPebbleGoLogo(){
		click(pebbleGoLogo);
		return this;
	}
	
	public TopModuleMenu getTopMenu(){
		return topModuleMenu;
	}
	
	public Boolean isModulePresentByName(String module){
		boolean displayed = false;
		try{
			displayed = pageDriver.findElement(By.linkText(module)).isDisplayed();
		} catch(Exception e){
			System.out.println(e.toString());
		}
		return displayed;
	}

	public ContentSelectionPage clickNewModule(String text){
		click(By.linkText(text));
		return new ContentSelectionPage(pageDriver);
	}
	
	public ContentSelectionPage clickModuleByName(String name){
		click(By.xpath("//div[@data-module-name=\"Games\"]//span[text()=\""+name+"\"]"));
		
		return new ContentSelectionPage(pageDriver);
	}
	
	public ArticlePage searchForArticle(String result){
		pageDriver.findElement(searchTextField).sendKeys(result);
		this.closeSendKeysTab();
		this.waitImplicitly(5);
		
		click(By.xpath("//*[@id=\"search_result\"]/ul/li/a[contains(text(),'"+result+"')]"));
		return new ArticlePage(pageDriver);
	}
}
