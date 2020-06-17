package LinkedAccounts.Pages;

import org.openqa.selenium.By;

import PGOPagesNew.PGONewLoginPage;
import PGOStudentResources.StudentHomePageNew;
import SharedClasses.PGONewBasePage;
import UserClasses.ProductType;
import UserClasses.User;

public class PGONewLinkAccountsToolbar extends PGONewBasePage {
	public By pgoLink = By.xpath("//*[@id=\"accounts\"]/li[1]/a");
	public By pgnLink = By.xpath("//*[@id=\"accounts\"]/li[2]/a");
	public By ciLink = By.xpath("//*[@id=\"accounts\"]/li[3]/a");
	public By logoutButton = By.id("logout");
	public By[] initialVisibleElements = {pgoLink, pgnLink, ciLink, logoutButton};
	public By[] unlinkedElements = {pgoLink, pgnLink, ciLink};
	
	public PGONewLinkAccountsToolbar(User u){
		user = u;
		pageDriver = u.getDriver();
	}

	public PGONewBasePage clickCILink(Boolean active){
		PGONewBasePage base = null;
		if(active){
			click(ciLink);
			base = new PGONewCIStudentHomePage(user);
		}
		else{
			click(ciLink);
			waitImplicitly(2);
			base = new PGONewSorryModal(user);
		}	
		
		return base;
	}
	
	public PGONewBasePage clickPGOLink(Boolean active){
		PGONewBasePage base = null;
		if(active){
			click(pgoLink);
			base = new StudentHomePageNew(user);
		}
		else{
			click(pgoLink);

			waitImplicitly(2);
			base = new PGONewSorryModal(user);
		}
		
		
		return base;
	}
	
	public PGONewBasePage clickPGNLink(Boolean active){
		PGONewBasePage base = null;
		if(active){
			click(pgnLink);
			base = new PGONewPGNStudentHomePage(user);
		}
		else{
			click(pgnLink);
			waitImplicitly(2);
			base = new PGONewSorryModal(user);

		}
		return base;
	}
	
	public PGONewBasePage logout(ProductType product){
		PGONewBasePage base = null;
		click(logoutButton);
		
		switch(product){
			case PGO:
				base = new PGONewLoginPage(this.getDriver());
				break;
			case PGN:
				base = new PGONewPGNLoginPage(this.getDriver());
				break;
			case CI:
				base = new PGONewCILoginPage(this.getDriver());
				break;
			default:
				base = new PGONewLoginPage(this.getDriver());
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
