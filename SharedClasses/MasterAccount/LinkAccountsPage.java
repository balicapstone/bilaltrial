package MasterAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import SharedClasses.BasePage;
import SharedClasses.ThirdPartyPage;
import UserClasses.User;

public class LinkAccountsPage extends BasePage{

	private By pgoTab = By.xpath("//*[@id=\"tab-set\"]/li[1]/a"); //linkText("PebbleGo");
	private By pgnTab = By.xpath("//*[@id=\"tab-set\"]/li[2]/a"); //.linkText("PebbleGo Next");
	private By ciTab = By.xpath("//*[@id=\"tab-set\"]/li[3]/a"); //.linkText("My Capstone Library");
	private By masterTab = By.xpath("//*[@id=\"tab-set\"]/li[4]/a"); //.linkText("Create Master Account");
	
	private By pgoUsername = By.id("username");
	private By pgoPassword = By.id("password");
	public By pgoNext = By.xpath("//*[@id=\"form-app-2\"]/div[2]/div[1]/input"); //*[@id="form-app-2"]/div[2]/div[1]/input
	private By pgoSkip = By.xpath("//*[@id=\"form-app-2\"]/div[2]/div[2]/a");
	
	private By pgnUsername = By.id("username-2");
	private By pgnPassword = By.id("password-2");
	public By pgnNext = By.xpath("//*[@id=\"form-app-3\"]/div[2]/div[1]/input");
	private By pgnSkip = By.xpath("//*[@id=\"form-app-3\"]/div[2]/div[2]/a");
	
	private By ciUsername = By.id("username-3");
	private By ciPassword = By.id("password-3");
	public By ciNext = By.xpath("//*[@id=\"form-app-1\"]/div[2]/div[1]/input");
	private By ciSkip = By.xpath("//*[@id=\"form-app-1\"]/div[2]/div[2]/a");
	
	private By masterPGOUsername = By.id("app_2");
	private By masterPGNUsername = By.id("app_3");
	private By masterCIUsername = By.id("app_1");
	private By newUserButton = By.id("new-username");
	private By newUserField = By.id("username-4");
	private By masterPassword = By.id("password-4");
	public By masterSubmit = By.xpath("//*[@id=\"view-user\"]/input");
	
	public By pgoEulaView = By.linkText("View"); //By.xpath("//*[@id=\"tab2\"]/div[2]/a;");
	public By pgoEulaAccept = By.linkText("Accept"); //By.xpath("//*[@id=\"tab2\"]/div[2]/button;");
	public By pgnEulaView = By.xpath("//*[@id=\"tab3\"]/div[2]/a");
	public By pgnEulaAccept = By.xpath("//*[@id=\"tab3\"]/div[2]/button");
	public By ciEulaView = By.xpath("//*[@id=\"tab1\"]/div[2]/a");
	public By ciEulaAccept = By.xpath("//*[@id=\"tab1\"]/div[2]/button");

	public By pgoAccountError = By.xpath("//*[@id=\"tab2\"]/div[1]/span[2]");
	public By pgnAccountError = By.xpath("//*[@id=\"tab3\"]/div[1]/span[2]");
	public By ciAccountError = By.xpath("//*[@id=\"tab1\"]/div[1]/span[2]");
	public By masterAccountError = By.xpath("//*[@id=\"create-user\"]/footer/div/span[2]"); 
	
	public LinkAccountsPage(WebDriver driver){
		pageDriver = driver;
	}
	
	public LinkAccountsPage goToPGOTab(){
		click(pgoTab);
		return this;
	}
	
	public LinkAccountsPage goToCITab(){
		click(ciTab);
		return this;		
	}
	
	public LinkAccountsPage goToPGNTab(){
		click(pgnTab);
		return this;
	}
	
	public LinkAccountsPage goToMasterTab(){
		click(masterTab);
		return this;
	}
	
	public LinkAccountsPage linkPGOAccount(User user){
		enterPGOUsername(user.getUsername());
		waitImplicitly(3);
		this.pressEnter(this.pgoUsername);
		enterPGOPassword(user.getPassword());
		waitImplicitly(3);
		this.pressEnter(this.pgoPassword);
		waitImplicitly(3);
		clickPGONext();
		return this;
	}
	
	public LinkAccountsPage enterPGOUsername(String username){
		sendKeysToElement(username, pgoUsername);
		return this;
	}
	
	public LinkAccountsPage enterPGOPassword(String password){
		sendKeysToElement(password, pgoPassword);
		return this;
	}
	
	public LinkAccountsPage clickPGONext(){
		click(pgoNext);
		return this;
	}
	
	public LinkAccountsPage linkPGNAccount(User user){
		enterPGNUsername(user.getUsername());
		waitImplicitly(3);
		this.pressEnter(this.pgnUsername);
		enterPGNPassword(user.getPassword());
		waitImplicitly(3);
		this.pressEnter(this.pgnPassword);
		waitImplicitly(3);
		clickPGNNext();
		return this;
	}
	
	public LinkAccountsPage enterPGNUsername(String username){
		sendKeysToElement(username, pgnUsername);
		return this;
	}
	
	public LinkAccountsPage enterPGNPassword(String password){
		sendKeysToElement(password, pgnPassword);
		return this;
	}
	
	public LinkAccountsPage clickPGNNext(){
		click(pgnNext);
		return this;
	}
	
	public LinkAccountsPage linkCIAccount(User user){
		enterCIUsername(user.getUsername());
		waitImplicitly(3);
		this.pressEnter(this.ciUsername);
		enterCIPassword(user.getPassword());
		waitImplicitly(3);
		this.pressEnter(this.ciPassword);
		clickCINext();
		return this;
	}
	
	public LinkAccountsPage enterCIUsername(String username){
		sendKeysToElement(username, ciUsername);
		return this;
	}
	public LinkAccountsPage enterCIPassword(String password){
		sendKeysToElement(password, ciPassword);
		return this;
	}
	
	public LinkAccountsPage clickCINext(){
		click(ciNext);
		return this;
	}
	
	public ThirdPartyPage viewPGOEula(){
		click(pgoEulaView);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage viewPGNEula(){
		click(pgnEulaView);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage viewCIEula(){
		click(ciEulaView);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage acceptEulaPGO(){
		click(pgoEulaAccept);
		return new ThirdPartyPage(pageDriver);
	}

	public ThirdPartyPage acceptEulaPGN(){
		click(pgnEulaAccept);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage acceptEulaCI(){
		click(ciEulaAccept);
		return new ThirdPartyPage(pageDriver);
	}
	
	public LinkAccountsPage clickNewMasterUsername(){
		click(newUserButton);
		return this;
	}
	
	public LinkAccountsPage enterNewMasterUsername(String username){
		sendKeysToElement(username, newUserField);
		return this;
	}
	
	public LinkAccountsPage enterMasterPassword(String password){
		sendKeysToElement(password, masterPassword);
		return this;
	}
	
	public LinkAccountsPage clickMasterSubmitButton(){
		click(masterSubmit);
		return this;
	}
	
	public String getPGOAccountErrorText(){
		return getElementText(pgoAccountError);
	}
	
	public String getPGNAccountErrorText(){
		return getElementText(pgnAccountError);
	}
	
	public String getCIAccountErrorText(){
		return getElementText(ciAccountError);
	}
	
	public String getMasterAccountErrorText(){
		return getElementText(masterAccountError);
	}
	
	public LinkAccountsPage clickPGOSkip(){
		click(pgoSkip);
		return this;
	}
	
	public LinkAccountsPage clickPGNSkip(){
		click(pgnSkip);
		return this;
	}
	
	public LinkAccountsPage clickCISkip(){
		click(ciSkip);
		return this;
	}
	
	public LinkAccountsPage clickNewMasterPGO(){
		click(masterPGOUsername);
		return this;
	}
	
	public String getNewMasterPGO(){
		return getElementText(masterPGOUsername);
	}
	
	public LinkAccountsPage clickNewMasterPGN(){
		click(masterPGNUsername);
		return this;
	}
	
	public String getNewMasterPGN(){
		return getElementText(masterPGNUsername);
	}
	
	public LinkAccountsPage clickNewMasterCI(){
		click(masterCIUsername);
		return this;
	}
	
	public String getNewMasterCI(){
		return getElementText(masterCIUsername);
	}
	
	public LinkAccountsPage clearPGOUsername(){
		clearField(pgoUsername);
		return this;
	}
	
	public LinkAccountsPage clearPGNUsername(){
		clearField(pgnUsername);
		return this;
	}
	
	public LinkAccountsPage clearCIUsername(){
		clearField(ciUsername);
		return this;
	}
	
	public LinkAccountsPage clearMasterUsername(){
		clearField(newUserField);
		return this;
	}
	
	public String getPGOUsername(){
		return pageDriver.findElement(pgoUsername).getText();
	}
	
	public void clickPGOUsername(){
		click(pgoUsername);
	}
	
	public void clickPGOPassword(){
		click(pgoPassword);
	}
	
	public void clickPGNUsername(){
		click(pgnUsername);
	}
	
	public void clickPGNPassword(){
		click(pgnPassword);
	}
	
	public void clickCIUsername(){
		click(ciUsername);
	}
	
	public void clickCIPassword(){
		click(ciPassword);
	}
}
