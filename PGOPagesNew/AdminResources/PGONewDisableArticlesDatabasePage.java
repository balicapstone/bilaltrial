package AdminResources;

import org.openqa.selenium.By;

import PGOPages.LoginPage;
import SharedClasses.PGONewBasePage;
import UserClasses.User;

public class PGONewDisableArticlesDatabasePage extends PGONewBasePage{
	public By selectDatabaseButton = By.linkText("Select Database");
	public By backToAdminButton = By.linkText("Back to Admin");
	public By logOutButton = By.linkText("Logout");
	public By animalsDatabaseLink = By.linkText("PebbleGo Animals");
	public By scienceDatabaseLink = By.linkText("PebbleGo Science");
	public By biographiesDatabaseLink = By.linkText("PebbleGo Biographies");
	public By socialStudiesDatabaseLink = By.linkText("PebbleGo Social Studies");
	public By dinosaursDatabaseLink = By.linkText("PebbleGo Dinosaurs");
	public By animalesDatabaseLink = By.linkText("PebbleGo Animales");
	public By cienciaDatabaseLink = By.linkText("PebbleGo Ciencia");
	public By pgnStatesDatabaseLink = By.linkText("PebbleGo Next States");
	public By pgnScienceDatabaseLink = By.linkText("PebbleGo Next Science");
	public By pgnBiographiesDatabaseLink = By.linkText("PebbleGo Next Biographies");
	public By pgnAmericanIndianHistoryDatabaseLink = By.linkText("PebbleGo Next American Indian History");
	public By pgnSocialStudiesDatabaseLink = By.linkText("PebbleGo Next Social Studies");
	public By pebbleGoLogo = By.id("logo");
	public By headerBanner = By.id("message");
	public By[] initialVisibleElements = {selectDatabaseButton, backToAdminButton, logOutButton, animalsDatabaseLink,
			scienceDatabaseLink, biographiesDatabaseLink, socialStudiesDatabaseLink, dinosaursDatabaseLink, pebbleGoLogo, headerBanner};
	
	public PGONewDisableArticlesDatabasePage(User u){
		user = u;
		pageDriver = u.getDriver();
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public PGONewDisableArticlesTogglePage clickDatabaseByName(String module){
		pageDriver.findElement(By.linkText(module)).click();
		return new PGONewDisableArticlesTogglePage(user);
	}
	
	public PGONewDisableArticlesTogglePage clickAnimalsDatabaseLink(){
		pageDriver.findElement(animalsDatabaseLink).click();
		return new PGONewDisableArticlesTogglePage(user);
	}
	
	public PGONewDisableArticlesTogglePage clickScienceDatabaseLink(){
		pageDriver.findElement(scienceDatabaseLink).click();
		return new PGONewDisableArticlesTogglePage(user);
	}
	
	public PGONewDisableArticlesTogglePage clickBiographiesDatabaseLink(){
		pageDriver.findElement(biographiesDatabaseLink).click();
		return new PGONewDisableArticlesTogglePage(user);
	}
	
	public PGONewDisableArticlesTogglePage clickSocialStudiesDatabaseLink(){
		pageDriver.findElement(socialStudiesDatabaseLink).click();
		return new PGONewDisableArticlesTogglePage(user);
	}
	
	public PGONewDisableArticlesTogglePage clickDinosaursDatabaseLink(){
		pageDriver.findElement(dinosaursDatabaseLink).click();
		return new PGONewDisableArticlesTogglePage(user);
	}
	
	public PGONewDisableArticlesTogglePage clickAnimalesDatabaseLink(){
		pageDriver.findElement(animalesDatabaseLink).click();
		return new PGONewDisableArticlesTogglePage(user);
	}
	
	public PGONewDisableArticlesTogglePage clickCienciaDatabaseLink(){
		pageDriver.findElement(cienciaDatabaseLink).click();
		return new PGONewDisableArticlesTogglePage(user);
	}
	
	public PGONewDisableArticlesTogglePage clickPGNStatesDatabaseLink(){
		pageDriver.findElement(pgnStatesDatabaseLink).click();
		return new PGONewDisableArticlesTogglePage(user);
	}
	
	public PGONewDisableArticlesTogglePage clickPGNScienceDatabaseLink(){
		pageDriver.findElement(pgnScienceDatabaseLink).click();
		return new PGONewDisableArticlesTogglePage(user);
	}
	
	public PGONewDisableArticlesTogglePage clickPGNAmericanIndianHistoryDatabaseLink(){
		this.scrollToElement(pageDriver.findElement(pgnAmericanIndianHistoryDatabaseLink));
		pageDriver.findElement(pgnAmericanIndianHistoryDatabaseLink).click();
		return new PGONewDisableArticlesTogglePage(user);
	}
	
	public PGONewDisableArticlesTogglePage clickPGNSocialStudiesDatabaseLink(){
		this.scrollToElement(pageDriver.findElement(pgnSocialStudiesDatabaseLink));
		pageDriver.findElement(pgnSocialStudiesDatabaseLink).click();
		return new PGONewDisableArticlesTogglePage(user);
	}
	
	public PGONewDisableArticlesTogglePage clickPGNBiographiesDatabaseLink(){
		this.scrollToElement(pageDriver.findElement(pgnBiographiesDatabaseLink));
		pageDriver.findElement(pgnBiographiesDatabaseLink).click();
		return new PGONewDisableArticlesTogglePage(user);
	}
	
	public PGONewDisableArticlesDatabasePage clickSelectDatabasePage(){
		pageDriver.findElement(selectDatabaseButton).click();
		return new PGONewDisableArticlesDatabasePage(user);
	}
	
	public PGONewTeacherHomeScreen clickBackToAdminButton(){
		pageDriver.findElement(backToAdminButton).click();
		return new PGONewTeacherHomeScreen(user);
	}
	
	public LoginPage clickLogOutButton(){
		pageDriver.findElement(logOutButton).click();
		pageDriver.get("https://www.pebblego.com");
		return new LoginPage(pageDriver);
	}
}
