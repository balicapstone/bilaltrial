package PGOStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import MasterAccount.LinkAccountsToolbar;
import PGOPagesNew.PGONewLoginPage;
import SharedClasses.PGONewBasePage;
import UserClasses.User;

public class StudentHomePageNew extends PGONewBasePage {
	
	public By pebbleGoLogo = By.id("logo");
	public By folderText = By.id("folder-text");
	
	public By pebblegoTab = By.xpath("//*[@id=\"main_content\"]/div[1]");
	public By pebblegoNextTab = By.xpath("//*[@id='main_content']/div[2]/img");
	
	public By logOutButton = By.id("sign-out-button");
	
	public PGONewContentHeader header;
	public PGONewContentFooter footer;
	
	public By animalsModule = By.xpath("//*[@id='main_content']/main[1]/nav/div/div[1]"); 
	public By scienceModule = By.xpath("//*[@id='main_content']/main[1]/nav/div/div[2]"); 
	
	
	public By statesModule = By.xpath("//*[@id='main_content']/main[2]/nav/div/a[1]");
	
	//private By[] initialVisibleElements = {pebbleGoLogo};

	private By[] initialVisibleElements = {pebblegoTab,pebblegoNextTab};
	private By[] initialPGOElements = {animalsModule,scienceModule};
	private By[] initialPGONextElements = {statesModule};
	
	public StudentHomePageNew(User u){
		user = u;
		pageDriver = u.getDriver();
		header = new PGONewContentHeader(u);
		footer = new PGONewContentFooter(u);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public By[] getInitialPGOElements(){
		return initialPGOElements;
	}
	
	public By[] getInitialPGONextElements(){
		return initialPGONextElements;
	}
	
	public StudentHomePageNew clickPebbleGoNextTab(){
		click(pebblegoNextTab);
		return this;
	}
	
	public PGONewLoginPage clickLogOutButton(){
		click(logOutButton);
		return new PGONewLoginPage(pageDriver);
	}
	
	public PGONewContentSelectionPage clickModuleByName(String name){
		click(By.id(name));
		return new PGONewContentSelectionPage(user);
	}
	
	public StudentHomePageNew clickPebbleGoLogo(){
		click(pebbleGoLogo);
		return this;
	}
	
	public Boolean isModulePresentByName(String module){
		boolean displayed = false;
		try{
			displayed = pageDriver.findElement(By.id(module)).isDisplayed();
		} catch(Exception e){
			System.out.println(e.toString());
		}
		return displayed;
	}
}
