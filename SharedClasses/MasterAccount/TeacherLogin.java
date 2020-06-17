package MasterAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import SharedClasses.BasePage;
import UserClasses.User;

public class TeacherLogin extends BasePage{
	public By username = By.id("username");
	public By password = By.id("password");
	public By submit = By.xpath("//*[@id=\"login\"]/footer/form/input[3]");
	public By pgButton = By.xpath("//*[@id=\"select-product\"]/div[1]");//("PebbleGo / PebbleGo Next");
	public By ciButton = By.xpath("//*[@id=\"select-product\"]/div[2]");
	public By[] initialVisibleElements = {username, password, submit, pgButton, ciButton};
	String url = "https://account.mycapstone.com/linkaccounts";
	
	public TeacherLogin(User u){
		//System.setProperty("webdriver.chrome.driver", "/Users/whall/Documents/workspace/chromedriver");
		///String path =  "/Users/whall/Documents/workspace/chromedriver";
		//ChromeOptions optionsChrome = new ChromeOptions();
		//optionsChrome.setBinary("/Applications/chrome.exe");
		//optionsChrome.addArguments("--disable-impl-side-painting");
		//pageDriver = new ChromeDriver();
		pageDriver = u.getDriver();
		
		if(u.getEnvironment().equals("www")){
			pageDriver.get("https://account.mycapstone.com/linkaccounts");
		} else if(u.getEnvironment().equals("qa")){
			pageDriver.get("https://linkedaccountsqa.pebblego.com/linkaccounts");
		}
	}
	
	public TeacherLogin(User u, String env){
		//System.setProperty("webdriver.chrome.driver", "/Users/whall/Documents/workspace/chromedriver");
		///String path =  "/Users/whall/Documents/workspace/chromedriver";
		//ChromeOptions optionsChrome = new ChromeOptions();
		//optionsChrome.setBinary("/Applications/chrome.exe");
		//optionsChrome.addArguments("--disable-impl-side-painting");
		//pageDriver = new ChromeDriver();
		pageDriver = u.getDriver();
		
		if(env.equals("www")){
			pageDriver.get("https://account.mycapstone.com/linkaccounts");
		} else if(env.equals("qa")){
			pageDriver.get("https://linkedaccountsqa.pebblego.com/linkaccounts");
		}
	}
	
	public TeacherLogin(WebDriver driver){
		pageDriver = driver;
	}
	
	
	public TeacherLogin choosePebbleGo(){
		click(pgButton);
		return this;
	}
	
	public TeacherLogin chooseCI(){
		click(ciButton);
		return this;
	}
	
	public LinkAccountsPage login(User user){
		if(user.hasCISub()){
			chooseCI();
		}
		else{
			choosePebbleGo();
		}
		
		sendKeysToElement(user.getUsername(),username);
		sendKeysToElement(user.getPassword(), password);
		click(submit);
		
		this.waitImplicitly(3);
		
		String site = pageDriver.getCurrentUrl();
		
		if(site.contains("http://")){
			String URL = pageDriver.getCurrentUrl();
			pageDriver.get(URL.replace("http://", "https://"));
		}
		return new LinkAccountsPage(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
