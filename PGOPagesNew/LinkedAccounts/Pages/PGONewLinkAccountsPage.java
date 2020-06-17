package LinkedAccounts.Pages;

import org.openqa.selenium.By;

import SharedClasses.PGONewBasePage;
import SharedClasses.PGONewThirdPartyPage;
import UserClasses.User;

public class PGONewLinkAccountsPage extends PGONewBasePage{

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
	
	public PGONewLinkAccountsPage(User u){
		user = u;
		pageDriver = u.getDriver();
	}
	
	public PGONewLinkAccountsPage goToPGOTab(){
		click(pgoTab);
		return this;
	}
	
	public PGONewLinkAccountsPage goToCITab(){
		click(ciTab);
		return this;		
	}
	
	public PGONewLinkAccountsPage goToPGNTab(){
		click(pgnTab);
		return this;
	}
	
	public PGONewLinkAccountsPage goToMasterTab(){
		click(masterTab);
		return this;
	}
	
	public PGONewLinkAccountsPage linkPGOAccount(User user){
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
	
	public PGONewLinkAccountsPage enterPGOUsername(String username){
		sendKeysToElement(username, pgoUsername);
		return this;
	}
	
	public PGONewLinkAccountsPage enterPGOPassword(String password){
		sendKeysToElement(password, pgoPassword);
		return this;
	}
	
	public PGONewLinkAccountsPage clickPGONext(){
		click(pgoNext);
		return this;
	}
	
	public PGONewLinkAccountsPage linkPGNAccount(User user){
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
	
	public PGONewLinkAccountsPage enterPGNUsername(String username){
		sendKeysToElement(username, pgnUsername);
		return this;
	}
	
	public PGONewLinkAccountsPage enterPGNPassword(String password){
		sendKeysToElement(password, pgnPassword);
		return this;
	}
	
	public PGONewLinkAccountsPage clickPGNNext(){
		click(pgnNext);
		return this;
	}
	
	public PGONewLinkAccountsPage linkCIAccount(User user){
		enterCIUsername(user.getUsername());
		waitImplicitly(3);
		this.pressEnter(this.ciUsername);
		enterCIPassword(user.getPassword());
		waitImplicitly(3);
		this.pressEnter(this.ciPassword);
		clickCINext();
		return this;
	}
	
	public PGONewLinkAccountsPage enterCIUsername(String username){
		sendKeysToElement(username, ciUsername);
		return this;
	}
	public PGONewLinkAccountsPage enterCIPassword(String password){
		sendKeysToElement(password, ciPassword);
		return this;
	}
	
	public PGONewLinkAccountsPage clickCINext(){
		click(ciNext);
		return this;
	}
	
	public PGONewThirdPartyPage viewPGOEula(){
		click(pgoEulaView);
		return new PGONewThirdPartyPage(user);
	}
	
	public PGONewThirdPartyPage viewPGNEula(){
		click(pgnEulaView);
		return new PGONewThirdPartyPage(user);
	}
	
	public PGONewThirdPartyPage viewCIEula(){
		click(ciEulaView);
		return new PGONewThirdPartyPage(user);
	}
	
	public PGONewThirdPartyPage acceptEulaPGO(){
		click(pgoEulaAccept);
		return new PGONewThirdPartyPage(user);
	}

	public PGONewThirdPartyPage acceptEulaPGN(){
		click(pgnEulaAccept);
		return new PGONewThirdPartyPage(user);
	}
	
	public PGONewThirdPartyPage acceptEulaCI(){
		click(ciEulaAccept);
		return new PGONewThirdPartyPage(user);
	}
	
	public PGONewLinkAccountsPage clickNewMasterUsername(){
		click(newUserButton);
		return this;
	}
	
	public PGONewLinkAccountsPage enterNewMasterUsername(String username){
		sendKeysToElement(username, newUserField);
		return this;
	}
	
	public PGONewLinkAccountsPage enterMasterPassword(String password){
		sendKeysToElement(password, masterPassword);
		return this;
	}
	
	public PGONewLinkAccountsPage clickMasterSubmitButton(){
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
	
	public PGONewLinkAccountsPage clickPGOSkip(){
		click(pgoSkip);
		return this;
	}
	
	public PGONewLinkAccountsPage clickPGNSkip(){
		click(pgnSkip);
		return this;
	}
	
	public PGONewLinkAccountsPage clickCISkip(){
		click(ciSkip);
		return this;
	}
	
	public PGONewLinkAccountsPage clickNewMasterPGO(){
		click(masterPGOUsername);
		return this;
	}
	
	public String getNewMasterPGO(){
		return getElementText(masterPGOUsername);
	}
	
	public PGONewLinkAccountsPage clickNewMasterPGN(){
		click(masterPGNUsername);
		return this;
	}
	
	public String getNewMasterPGN(){
		return getElementText(masterPGNUsername);
	}
	
	public PGONewLinkAccountsPage clickNewMasterCI(){
		click(masterCIUsername);
		return this;
	}
	
	public String getNewMasterCI(){
		return getElementText(masterCIUsername);
	}
	
	public PGONewLinkAccountsPage clearPGOUsername(){
		clearField(pgoUsername);
		return this;
	}
	
	public PGONewLinkAccountsPage clearPGNUsername(){
		clearField(pgnUsername);
		return this;
	}
	
	public PGONewLinkAccountsPage clearCIUsername(){
		clearField(ciUsername);
		return this;
	}
	
	public PGONewLinkAccountsPage clearMasterUsername(){
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
