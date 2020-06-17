package NoNonsense.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import SharedClasses.BasePage;
import SharedClasses.ThirdPartyPage;
import UserClasses.User;

public class LandingPage extends BasePage {
	
	protected By findOutMoreLink = By.linkText("Find Out More");
	public NoNonsenseHeader header;

	protected By lessonOne = By.xpath("//*[@id=\"main\"]/div/div[6]/div[1]/a/img");
	protected By lessonTwo = By.xpath("//*[@id=\"main\"]/div/div[6]/div[2]/a/img");
	protected By lessonThree = By.xpath("//*[@id=\"main\"]/div/div[6]/div[3]/a/img");
	protected By videoOne = By.xpath("//*[@class=\"video\"]/div[1]/video");
	protected By videoTwo = By.xpath("//*[@class=\"video\"]/div[2]/video");
	protected By videoThree = By.xpath("//*[@class=\"video\"]/div[3]/video");
	protected By videoFour = By.xpath("//*[@class=\"video\"]/div[4]/video");
	protected By subscribeButton = By.xpath("//*[@id=\"main\"]/div/a");
	protected By privacyPolicyLink = By.linkText("Privacy Policy");
	protected By termsOfUseLink = By.linkText("Terms of Use");
	protected By lessonPlansExample = By.xpath("//*[@id=\"main\"]/div/div[5]/img");
	public By[] initialVisibleElements = {lessonOne, lessonTwo, lessonThree, videoOne, videoTwo, videoThree, videoFour, subscribeButton,
			privacyPolicyLink, termsOfUseLink, lessonPlansExample};
	public String browser = "firefox";
	public String environment;
	
	public LandingPage(User u){
		/*
		if(browser == null){
			browser = "chrome";
		}

		if(browser.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "/Users/whall/Documents/workspace/chromedriver");
			String path =  "/Users/whall/Documents/workspace/chromedriver";
			ChromeOptions optionsChrome = new ChromeOptions();
			optionsChrome.setBinary("/Applications/chrome.exe");
			optionsChrome.addArguments("--disable-impl-side-painting --start-maximized");//--log-level=3 --no-sandbox");
			pageDriver = new ChromeDriver();
		}
		else{
			System.setProperty("webdriver.firefox.marionette", "/Users/whall/Documents/workspace/geckodriver");
			pageDriver = new FirefoxDriver(); 
			//pageDriver.manage().window().maximize();
		}
		
		pageDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		//this.maximizeWindow();
		//resizeWindow(1200, 1280);
		*/
		//Adding to run single class tests for debugging without using the JunitTestSuiteRunner
		pageDriver = u.getDriver();
		
		if(u.getEnvironment() == null){
			environment = "www";
		}
		else{
			environment = u.getEnvironment();
		}
		
		
		try{
			pageDriver.get("https://"+environment+".nononsenseliteracy.co.uk/");
			//pageDriver.get("https://www.pebblego.com");
		} catch(Exception e){
			pageDriver.close();
		}
		
		header = new NoNonsenseHeader(pageDriver);
	}
	
	public LandingPage(WebDriver driver){
		pageDriver = driver;
		
		/*if(environment == null){
			environment = "www";
		}
		
		try{
			pageDriver.get("https://"+environment+".nononsenseliteracy.co.uk/");
			//pageDriver.get("https://www.pebblego.com");
		} catch(Exception e){
			pageDriver.close();
		}
		*/
		
		header = new NoNonsenseHeader(pageDriver);
	}
	
	public LandingPage clickFindOutMoreLink(){ 
		click(findOutMoreLink);
		return this;	
	}
	
	public LandingPage clickFAQLink(){
		header.goToFAQPage();
		return this;
	}
	
	public LandingPage clickSubscribeLink(){
		header.goToSubscribePage();
		return this;
	}
	
	public LoginModal clickLoginLink(){
		header.login();
		return new LoginModal(pageDriver);
	}
	
	public LandingPage clickLibraryLink(){
		header.goToLibraryPage();
		return this;
	}
	
	public ThirdPartyPage clickPrivacyPolicyLink(){
		click(privacyPolicyLink);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickTermsOfUseLink(){
		click(termsOfUseLink);
		return new ThirdPartyPage(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
