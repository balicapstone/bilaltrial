package CMSPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class CMSToolbar extends BasePage{

	public By logOutLink = By.linkText("Log Out");
	public By glossarySearchLink = By.linkText("Glossary");
	public By questionsLink = By.linkText("Questions");
	public By modulesLink = By.linkText("Modules");
	public By templatesLink = By.linkText("Templates");
	public By articlesLink = By.linkText("Articles");
	public By assetLink = By.linkText("Assets");
	public By gamesLink = By.linkText("Games");
	public By uploadLink = By.linkText("Upload");
	public By languagesLink = By.linkText("Languages");
	public By experimentsLink = By.linkText("Video Experiments");
	public By logo = By.id("logo");
	
	public CMSToolbar(WebDriver driver){
		pageDriver = driver;
	}
	
	public GlossarySearchPage clickGlossarySearch(){
		click(glossarySearchLink);
		return new GlossarySearchPage(pageDriver);
	}
	
	public QuestionsPage clickQuestions(){
		click(questionsLink);
		return new QuestionsPage(pageDriver);
	}
	
	public TemplatesPage clickModules(){
		click(modulesLink);
		return new TemplatesPage(pageDriver);
	}
	
	public TemplatesPage clickTemplates(){
		click(templatesLink);
		return new TemplatesPage(pageDriver);
	}
	
	public UploadExportPage clickUpload(){
		click(uploadLink);
		return new UploadExportPage(pageDriver);
	}
	
	public CMSLandingPage clickLogout(){
		click(logOutLink);
		return new CMSLandingPage(pageDriver);
	}
	
	public ArticlesActivePage clickArticles(){
		click(articlesLink);
		return new ArticlesActivePage(pageDriver);
	}
	
	public CMSHomePage clickLogo(){
		click(logo);
		return new CMSHomePage(pageDriver);
	}
	
	public AssetPage clickAssets(){
		click(assetLink);
		return new AssetPage(pageDriver);
	}
	
	public EditGamesPage clickGames(){
		click(gamesLink);
		return new EditGamesPage(pageDriver);
	}
	
	public EditExperimentsPage clickExperiments(){
		click(experimentsLink);
		return new EditExperimentsPage(pageDriver);
	}
}
