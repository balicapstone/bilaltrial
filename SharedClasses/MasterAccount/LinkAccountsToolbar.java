package MasterAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;
import UserClasses.ProductType;

public class LinkAccountsToolbar extends BasePage {
	public By pgoLink = By.xpath("//*[@id=\"accounts\"]/li[1]/a");
	public By pgnLink = By.xpath("//*[@id=\"accounts\"]/li[2]/a");
	public By ciLink = By.xpath("//*[@id=\"accounts\"]/li[3]/a");
	public By logoutButton = By.id("logout");
	public By[] initialVisibleElements = {pgoLink, pgnLink, ciLink, logoutButton};
	public By[] unlinkedElements = {pgoLink, pgnLink, ciLink};
	
	public LinkAccountsToolbar(WebDriver driver){
		pageDriver = driver;
	}

	public BasePage clickCILink(Boolean active){
		BasePage base = null;
		if(active){
			click(ciLink);
			base = new CITests.CIPages.StudentHomePage(pageDriver);
		}
		else{
			click(ciLink);
			waitImplicitly(2);
			base = new SorryModal(pageDriver);
		}	
		
		return base;
	}
	
	public BasePage clickPGOLink(Boolean active){
		BasePage base = null;
		if(active){
			click(pgoLink);
			base = new PGOStudentResources.StudentHomePage(pageDriver);
		}
		else{
			click(pgoLink);

			waitImplicitly(2);
			base = new SorryModal(pageDriver);
		}
		
		
		return base;
	}
	
	public BasePage clickPGNLink(Boolean active){
		BasePage base = null;
		if(active){
			click(pgnLink);
			base = new PGNStudentResources.StudentHomePage(pageDriver);
		}
		else{
			click(pgnLink);
			waitImplicitly(2);
			base = new SorryModal(pageDriver);

		}
		return base;
	}
	
	public BasePage logout(ProductType product){
		BasePage base = null;
		click(logoutButton);
		
		switch(product){
			case PGO:
				base = new PGOPages.LoginPage(this.getDriver());
				break;
			case PGN:
				base = new PGNPages.LoginPage(this.getDriver());
				break;
			case CI:
				base = new CITests.CIPages.CILoginPage(this.getDriver());
				break;
			default:
				base = new PGOPages.LoginPage(this.getDriver());
				break;
		}
		
		if(!pageDriver.getCurrentUrl().contains("https")){
			pageDriver.get(pageDriver.getCurrentUrl().replace("http", "https"));
		}
		
		return base;	
	}
	
	public By[] getUnlinkedElements(){
		return unlinkedElements;
	}
}
