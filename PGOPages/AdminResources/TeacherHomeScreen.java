package AdminResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import MasterAccount.TeacherLogin;
import PGOPages.LoginPage;
import SharedClasses.BasePage;
import SharedClasses.GooglePage;
import UserClasses.ProductType;

public class TeacherHomeScreen extends BasePage{
	public By disableArticlesButton = By.linkText("Disable Articles");//("//*[@id=\"logout\"]/a[1]");//("Disable Articles");
	public By logoutButton = By.linkText("Logout");
	public By articlePopularityTab = By.linkText("Article Popularity");
	public By articleViewsTab = By.linkText("Article Views");
	public By gamesTab = By.linkText("Game Usage");
	public By userAccessTab = By.linkText("Access");
	public By subscriptionsTab = By.linkText("Subscriptions");
	public By topContentTab = By.linkText("Top Content");
	public By linkAccountsTab = By.linkText("Link Accounts");
	
	//Article Views Tab
	public By downloadArticleUsageButton = By.xpath("//*[@id=\"tabs-1\"]/a");
	
	//Article Popularity Tab
	public By usageByTopicDownloadButton = By.xpath("//*[@id=\"tabs-2\"]/div[2]/a");
	public By paginatorDiv = By.xpath("//*[@id=\"tabs-2\"]/div[contains(@class, 'pagination')]");
	public By articlePopularityTable = By.xpath("//*[@id=\"tabs-2\"]/table");
	
	//Games Usage Tab
	public By gameUsageDownloadButton = By.xpath("//*[@id=\"tabs-3\"]/div[2]/a[contains(text(), 'Download')]");
	public By gameUsageGamePlaysByMonthButton = By.xpath("//*[@id=\"tabs-3\"]/div[2]/a[contains(text(), 'Download Game Plays By Month')]");
	public By gameUsageTable = By.xpath("//*[@id=\"tabs-3\"]/table");
	
	//Top Content Tab
	public By displayTopArticlesButton = By.xpath("//*[@id=\"top_content_div\"]/span");
	public By displayTopArticlesTable = By.xpath("//*[@id=\"top_content_div\"]/table");
	public By displayAnimalsReportButton = By.xpath("//*[@id=\"pebblegoanimals_div\"]/span");
	public By animalsReportTable = By.xpath("//*[@id=\"pebblegoanimals_div\"]/table");
	public By displayScienceReportButton = By.xpath("//*[@id=\"pebblegoscience_div\"]/span");
	public By scienceReportTable = By.xpath("//*[@id=\"pebblegoscience_div\"]/table");
	public By displayBiographiesReportButton = By.xpath("//*[@id=\"pebblegobiographies_div\"]/span");
	public By biographiesReportTable = By.xpath("//*[@id=\"pebblegobiographies_div\"]/table");
	public By displaySocialStudiesReportButton = By.xpath("//*[@id=\"pebblegosocialstudies_div\"]/span");
	public By socialStudiesReportTable = By.xpath("//*[@id=\"pebblegosocialstudies_div\"]/table");
	public By displayPGNStatesReportButton = By.xpath("//*[@id=\"pebblegonextstates_div\"]/span");
	public By pgnStatesReportTable = By.xpath("//*[@id=\"pebblegonextstates_div\"]/table");
	public By displayDinosaursReportButton = By.xpath("//*[@id=\"pebblegodinosaurs_div\"]/span");
	public By dinosaursReportTable = By.xpath("//*[@id=\"pebblegodinosaurs_div\"]/table");
	public By displayAnimalesReportButton = By.xpath("//*[@id=\"pebblegoanimales_div\"]/span");
	public By animalesReportTable = By.xpath("//*[@id=\"pebblegoanimales_div\"]/table");
	public By displayPGNScienceReportButton = By.xpath("//*[@id=\"pebblegonextscience_div\"]/span");
	public By pgnScienceReportTable = By.xpath("//*[@id=\"pebblegonextscience_div\"]/table");
	public By displayPGNAIHReportButton = By.xpath("//*[@id=\"pebblegonextamericanindianhistory_div\"]/span");
	public By pgnAIHReportTable = By.xpath("//*[@id=\"pebblegonextamericanindianhistory_div\"]/table");
	public By displayPGNSocialStudiesReportButton = By.xpath("//*[@id=\"pebblegonextsocialstudies_div\"]/span");
	public By pgnSocialStudiesReportTable = By.xpath("//*[@id=\"pebblegonextsocialstudies_div\"]/table");
	public By displayCienciaReportButton = By.xpath("//*[@id=\"pebblegociencia_div\"]/span");
	public By cienciaReportTable = By.xpath("//*[@id=\"pebblegociencia_div\"]/table");
	public By displayBiografiasReportButton = By.xpath("//*[@id=\"pebblegobiografías_div\"]/span");
	public By biografiasReportTable = By.xpath("//*[@id=\"pebblegobiografías_div\"]/table");
	public By displayEstudiosSocialesReportButton = By.xpath("//*[@id=\"pebblegoestudiossociales_div\"]/span");
	public By estudiosSocialesReportTable = By.xpath("//*[@id=\"pebblegoestudiossociales_div\"]/table");
	public By displayPGNBiographiesReportButton = By.xpath("//*[@id=\"pebblegonextbiographies_div\"]/span");
	public By pgnBiographiesReportTable = By.xpath("//*[@id=\"pebblegonextbiographies_div\"]/table");
	
	//Access Tab
	public By studentUsername = By.xpath("//*[@class=\"user_username\"]");
	public By studentPassword = By.xpath("//*[@id=\"tabs-5\"]/div[1]/form/table/tbody/tr[2]/td[2]/input");
	public By studentSQSStrings = By.xpath("//*[@id=\"tabs-5\"]/div[1]/form/ul[1]/li[@class=\"formInput\"]");
	public By studentUpdateButton = By.xpath("//*[@name=\"update_student_login\"]");
	public By teacherUsername = By.xpath("//*[@class=\"teacher_username\"]");
	public By teacherPassword = By.xpath("//*[@id=\"tabs-5\"]/div[2]/form/table/tbody/tr[2]/td[2]/input");
	public By teacherSQSStrings = By.xpath("//*[@id=\"tabs-5\"]/div[2]/form/ul[1]/li[2]");
	public By teacherUpdateButton = By.xpath("//*[@name=\"update_teacher_login\"]");
	
	//Subscriptions Tab
	public By usContentExcludedHeader = By.xpath("//*[@id=\"tabs-6\"]/h1");
	public By animalsSubscription = By.xpath("//*[@id=\"tabs-6\"]/div[1]/h2");
	public By animalsActiveDates = By.xpath("//*[@id=\"tabs-6\"]/div[1]/p");
	public By scienceSubscription = By.xpath("//*[@id=\"tabs-6\"]/div[2]/h2");
	public By scienceActiveDates = By.xpath("//*[@id=\"tabs-6\"]/div[2]/p");
	public By biographiesSubscription = By.xpath("//*[@id=\"tabs-6\"]/div[3]/h2");
	public By biographiesActiveDates = By.xpath("//*[@id=\"tabs-6\"]/div[3]/p");
	public By socialStudiesSubscription = By.xpath("//*[@id=\"tabs-6\"]/div[4]/h2");
	public By socialStudiesActiveDates = By.xpath("//*[@id=\"tabs-6\"]/div[4]/p");
	public By pgnStatesSubscription = By.xpath("//*[@id=\"tabs-6\"]/div[5]/h2");
	public By pgnStatesActiveDates = By.xpath("//*[@id=\"tabs-6\"]/div[5]/p");
	public By dinosaursSubscription = By.xpath("//*[@id=\"tabs-6\"]/div[6]/h2");
	public By dinosaursActiveDates = By.xpath("//*[@id=\"tabs-6\"]/div[6]/p");
	public By animalesSubscription = By.xpath("//*[@id=\"tabs-6\"]/div[7]/h2");
	public By animalesActiveDates = By.xpath("//*[@id=\"tabs-6\"]/div[7]/p");
	public By pgnScienceSubscription = By.xpath("//*[@id=\"tabs-6\"]/div[8]/h2");
	public By pgnScienceActiveDates = By.xpath("//*[@id=\"tabs-6\"]/div[8]/p");
	public By pgnAIHSubscription = By.xpath("//*[@id=\"tabs-6\"]/div[9]/h2");
	public By pgnAIHActiveDates = By.xpath("//*[@id=\"tabs-6\"]/div[9]/p");
	public By pgnSocialStudiesSubscription = By.xpath("//*[@id=\"tabs-6\"]/div[10]/h2");
	public By pgnSocialStudiesActiveDates = By.xpath("//*[@id=\"tabs-6\"]/div[10]/p");
	public By cienciaSubscription = By.xpath("//*[@id=\"tabs-6\"]/div[11]/h2");
	public By cienciaActiveDates = By.xpath("//*[@id=\"tabs-6\"]/div[11]/p");
	public By biografiasSubscription = By.xpath("//*[@id=\"tabs-6\"]/div[12]/h2");
	public By biografiasActiveDates = By.xpath("//*[@id=\"tabs-6\"]/div[12]/p");
	public By estudiosSocialesSubscription = By.xpath("//*[@id=\"tabs-6\"]/div[13]/h2");
	public By estudiosSocialesActiveDates = By.xpath("//*[@id=\"tabs-6\"]/div[13]/p");
	public By pgnBiographiesSubscription = By.xpath("//*[@id=\"tabs-6\"]/div[14]/h2");
	public By pgnBiographiesActiveDates = By.xpath("//*[@id=\"tabs-6\"]/div[14]/p");
	
	//Link Account Tab
	public By linkAccountsButton = By.xpath("//a[contains(text(), 'Link Accounts')]");
	public By linkGoogleAccountsButton = By.xpath("//a[contains(text(), 'Link my class IDs')]");
	public By linkGoogleError = By.xpath("//p[contains(@style, 'font-style: italic; color:#F00;')]");
	
	private By pebbleGoLogo = By.xpath("//*[@id=\"logo\"]/a/img");
	private By[] InitialVisibleElements = {disableArticlesButton, logoutButton, articleViewsTab, articlePopularityTab,
			gamesTab, userAccessTab, subscriptionsTab, topContentTab, linkAccountsTab}; 
	
	public TeacherHomeScreen(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getInitialVisibleElements(){
		return InitialVisibleElements;
	}
	
	public DisableArticlesDatabasePage clickDisableArticlesButton(){
		pageDriver.findElement(disableArticlesButton).click();
		return new DisableArticlesDatabasePage(pageDriver);
	}
	
	public BasePage clickLogoutButton(ProductType type){
		BasePage base = null;

		pageDriver.findElement(logoutButton).click();
		
		if(type.equals(ProductType.PGO)){
			pageDriver.get("https://teacher.pebblego.com");
			base = new TeacherLoginPage(pageDriver);
		}
		else{
			pageDriver.get("https://teacher.pebblegonext.com");
			base = new PGNPages.LoginPage(pageDriver);
		}
		
		return base;
	}
	
	public LoginPage clickPebbleGoLogo(){
		pageDriver.findElement(pebbleGoLogo).click();
		return new LoginPage(pageDriver);
	}
	
	public TeacherHomeScreen clickLinkAccountsTab(){
		click(linkAccountsTab);
		return this;
	}
	
	public By getDisableArticlesButton(){
		return disableArticlesButton;
	}
	
	public TeacherLogin clickLinkAccountsButton(){
		click(linkAccountsButton);
		return new TeacherLogin(pageDriver);
	}
	
	public GooglePage clickGoogleAccountsButton(){
		click(linkGoogleAccountsButton);
		return new GooglePage(pageDriver);
	}
	
	public By getGoogleLinkError(){
		return linkGoogleError;
	}
	
	public String getPGOStudentSQS(){
		String sqsInfo = pageDriver.findElement(studentSQSStrings).getText();
		
		return sqsInfo.split("\\n")[0].substring(sqsInfo.indexOf("."));
	}
	
	public String getPGNStudentSQS(){
		String sqsInfo = pageDriver.findElement(studentSQSStrings).getText();
		
		return sqsInfo.split("\\n")[1].substring(sqsInfo.indexOf("."));
	}
	
	public String getPGOTeacherSQS(){
		String sqsInfo = pageDriver.findElement(teacherSQSStrings).getText();
		
		return sqsInfo.split("\\n")[0].substring(sqsInfo.indexOf("."));
	}
	
	public String getPGNTeacherSQS(){
		String sqsInfo = pageDriver.findElement(teacherSQSStrings).getText();
		
		return sqsInfo.split("\\n")[1].substring(sqsInfo.indexOf("."));
	}
	
	public void updateStudentPassword(String update){
		this.clearField(studentPassword);
		this.sendKeysToElement(update, studentPassword);
		
		this.click(studentUpdateButton);
	}
	
	public void updateTeacherPassword(String update){
		this.clearField(teacherPassword);
		this.sendKeysToElement(update, teacherPassword);
		
		this.click(teacherUpdateButton);
	}
}
