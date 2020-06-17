package PGOStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import MasterAccount.LinkAccountsToolbar;
import PGOPages.ContentHeader;
import PGOPages.LoginPage;
import SharedClasses.BasePage;

public class StudentHomePage extends BasePage {
	
	// public By pebbleGoLogo = By.id("siteTitle");
	public By pebbleGoLogo = By.id("logo-pgo");
	// public By animalsModule = By.xpath("//a[@data-siteid=\"1\"]/span"); 
	public By animalsModule = By.id("Animals"); 
	
	
	public By scienceModule = By.id("Science"); 
	public By cienciaModule = By.id("Ciencia"); 
//	public By biographiesModule = By.xpath("//a[@data-siteid=\"3\"]/span");
	
	public By biographiesModule = By.id("Biographies");
	
	//public By biografiasModule = By.xpath("//a[@data-siteid=\"13\"]/span"); 
	public By biografiasModule = By.id("Biografías");
	
	public By socialStudiesModule = By.id("Social Studies");
	public By estudiosSocialesModule = By.id("Estudios Sociales");
	public By dinosaursModule = By.id("Dinosaurs"); 
	// public By animalesModule = By.xpath("//a[@data-siteid=\"8\"]/span");
	
	public By animalesModule = By.id("Animales");
	
	
	public By searchTextField = By.xpath("//*[@id=\"header-search\"]/input[1]");
//	public By logOutButton = By.id("logout");
	//public By logOutButton = By.id("sign-out-button");
	
	public By logOutButton = By.xpath("/html/body/div/div/div[2]/header/div/nav/button[3]");
	
	
	
	public By topHamburgerButton = By.id("hamburger-icon");
//	public By homeBreadcrumb = By.id("home-crumb");
	private By homeBreadcrumb = By.id("home");
	
	public TopModuleMenu topModuleMenu;
	public LinkAccountsToolbar laToolbar;

	public ContentHeader header;
	
	private By[] initialVisibleElements = {pebbleGoLogo, animalsModule, scienceModule, 
			biographiesModule, socialStudiesModule, dinosaursModule, 
			// logOutButton,
			// topHamburgerButton, 
			// homeBreadcrumb
			};
	
	public StudentHomePage(WebDriver driver){
		pageDriver = driver;
		topModuleMenu = new TopModuleMenu(driver);
		laToolbar = new LinkAccountsToolbar(driver);
		header = new ContentHeader(driver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public StudentHomePage clickHamburgerButton(){
		click(topHamburgerButton);
		return this;
	}
	
	public AnimalsContentPage clickAnimalsModule(){
		click(animalsModule);
		return new AnimalsContentPage(pageDriver);
	}
	
	public ScienceContentPage clickScienceModule(){
		click(scienceModule);
		return new ScienceContentPage(pageDriver);
	}
	
	public BiographiesContentPage clickBiographiesModule(){
		click(biographiesModule);
		return new BiographiesContentPage(pageDriver);
	}
	
	public SocialStudiesContentPage clickSocialStudiesModule(){
		click(socialStudiesModule);
		return new SocialStudiesContentPage(pageDriver);
	}
	
	public DinosaursContentPage clickDinosaursModule(){
		click(dinosaursModule);
		return new DinosaursContentPage(pageDriver);
	}
	
	public AnimalesContentPage clickAnimalesModule(){
		click(animalesModule);
		return new AnimalesContentPage(pageDriver);
	}
	
	public ContentSelectionPage clickCienciaModule(){
		click(cienciaModule);
		return new ContentSelectionPage(pageDriver);
	}
	
	public BiografiasContentPage clickBiografiasModule(){
		click(biografiasModule);
		return new BiografiasContentPage(pageDriver);
	}
	
	public ContentSelectionPage clickEstudiosSocialesModule(){
		click(estudiosSocialesModule);
		return new ContentSelectionPage(pageDriver);
	}
	
	public LoginPage clickLogOutButton(){
		click(logOutButton);
		return new LoginPage(pageDriver);
	}
	
	public StudentHomePage clickHomeBreadcrumb(){
		click(homeBreadcrumb);
		return this;
	}
	
	public StudentHomePage clickPebbleGoLogo(){
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
