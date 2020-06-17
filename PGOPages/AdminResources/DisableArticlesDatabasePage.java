package AdminResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import PGOPages.*;
import SharedClasses.BasePage;

public class DisableArticlesDatabasePage extends BasePage{
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
	
	public DisableArticlesDatabasePage(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public DisableArticlesTogglePage clickDatabaseByName(String module){
		pageDriver.findElement(By.linkText(module)).click();
		return new DisableArticlesTogglePage(pageDriver);
	}
	
	public DisableArticlesTogglePage clickAnimalsDatabaseLink(){
		pageDriver.findElement(animalsDatabaseLink).click();
		return new DisableArticlesTogglePage(pageDriver);
	}
	
	public DisableArticlesTogglePage clickScienceDatabaseLink(){
		pageDriver.findElement(scienceDatabaseLink).click();
		return new DisableArticlesTogglePage(pageDriver);
	}
	
	public DisableArticlesTogglePage clickBiographiesDatabaseLink(){
		pageDriver.findElement(biographiesDatabaseLink).click();
		return new DisableArticlesTogglePage(pageDriver);
	}
	
	public DisableArticlesTogglePage clickSocialStudiesDatabaseLink(){
		pageDriver.findElement(socialStudiesDatabaseLink).click();
		return new DisableArticlesTogglePage(pageDriver);
	}
	
	public DisableArticlesTogglePage clickDinosaursDatabaseLink(){
		pageDriver.findElement(dinosaursDatabaseLink).click();
		return new DisableArticlesTogglePage(pageDriver);
	}
	
	public DisableArticlesTogglePage clickAnimalesDatabaseLink(){
		pageDriver.findElement(animalesDatabaseLink).click();
		return new DisableArticlesTogglePage(pageDriver);
	}
	
	public DisableArticlesTogglePage clickCienciaDatabaseLink(){
		pageDriver.findElement(cienciaDatabaseLink).click();
		return new DisableArticlesTogglePage(pageDriver);
	}
	
	public DisableArticlesTogglePage clickPGNStatesDatabaseLink(){
		pageDriver.findElement(pgnStatesDatabaseLink).click();
		return new DisableArticlesTogglePage(pageDriver);
	}
	
	public DisableArticlesTogglePage clickPGNScienceDatabaseLink(){
		pageDriver.findElement(pgnScienceDatabaseLink).click();
		return new DisableArticlesTogglePage(pageDriver);
	}
	
	public DisableArticlesTogglePage clickPGNAmericanIndianHistoryDatabaseLink(){
		this.scrollToElement(pageDriver.findElement(pgnAmericanIndianHistoryDatabaseLink));
		pageDriver.findElement(pgnAmericanIndianHistoryDatabaseLink).click();
		return new DisableArticlesTogglePage(pageDriver);
	}
	
	public DisableArticlesTogglePage clickPGNSocialStudiesDatabaseLink(){
		this.scrollToElement(pageDriver.findElement(pgnSocialStudiesDatabaseLink));
		pageDriver.findElement(pgnSocialStudiesDatabaseLink).click();
		return new DisableArticlesTogglePage(pageDriver);
	}
	
	public DisableArticlesTogglePage clickPGNBiographiesDatabaseLink(){
		this.scrollToElement(pageDriver.findElement(pgnBiographiesDatabaseLink));
		pageDriver.findElement(pgnBiographiesDatabaseLink).click();
		return new DisableArticlesTogglePage(pageDriver);
	}
	
	public DisableArticlesDatabasePage clickSelectDatabasePage(){
		pageDriver.findElement(selectDatabaseButton).click();
		return new DisableArticlesDatabasePage(pageDriver);
	}
	
	public TeacherHomeScreen clickBackToAdminButton(){
		pageDriver.findElement(backToAdminButton).click();
		return new TeacherHomeScreen(pageDriver);
	}
	
	public TeacherLoginPage clickLogOutButton(){
		pageDriver.findElement(logOutButton).click();
		pageDriver.get("https://www.pebblego.com");
		return new TeacherLoginPage(pageDriver);
	}
}
