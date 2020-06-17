package LinkedAccounts.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.PGONewBasePage;
import UserClasses.User;

public class PGONewTeacherLogin extends PGONewBasePage{
	public By username = By.id("username");
	public By password = By.id("password");
	public By submit = By.xpath("//*[@id=\"login\"]/footer/form/input[3]");
	public By pgButton = By.xpath("//*[@id=\"select-product\"]/div[1]");//("PebbleGo / PebbleGo Next");
	public By ciButton = By.xpath("//*[@id=\"select-product\"]/div[2]");
	public By[] initialVisibleElements = {username, password, submit, pgButton, ciButton};
	String url = "https://account.mycapstone.com/linkaccounts";
	
	public PGONewTeacherLogin(User u){
		pageDriver = u.getDriver();
		
		if(u.getEnvironment().equals("www")){
			pageDriver.get("https://account.mycapstone.com/linkaccounts");
		} else if(u.getEnvironment().equals("site")){
			pageDriver.get("https://account.mycapstone.com/linkaccounts");
		} else if(u.getEnvironment().equals("qa")){
			pageDriver.get("https://linkedaccountsqa.pebblego.com/linkaccounts");
		} else if(u.getEnvironment().equals("reactqa")){
			pageDriver.get("https://linkedaccountsqa.pebblego.com/linkaccounts");
		}
	}
	
	public PGONewTeacherLogin(User u, String env){
		pageDriver = u.getDriver();
		
		if(env.equals("www")){
			pageDriver.get("https://account.mycapstone.com/linkaccounts");
		} else if(env.equals("qa")){
			pageDriver.get("https://linkedaccountsqa.pebblego.com/linkaccounts");
		} else if(u.getEnvironment().equals("reactqa")){
			pageDriver.get("https://linkedaccountsqa.pebblego.com/linkaccounts");
		}
	}
	
	public PGONewTeacherLogin(WebDriver driver){
		pageDriver = driver;
	}
	
	
	public PGONewTeacherLogin choosePebbleGo(){
		click(pgButton);
		return this;
	}
	
	public PGONewTeacherLogin chooseCI(){
		click(ciButton);
		return this;
	}
	
	public PGONewLinkAccountsPage login(User user){
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
		return new PGONewLinkAccountsPage(user);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
