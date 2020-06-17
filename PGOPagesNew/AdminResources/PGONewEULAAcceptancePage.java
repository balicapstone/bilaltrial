package AdminResources;

import org.openqa.selenium.By;

import SharedClasses.PGONewBasePage;
import UserClasses.User;

public class PGONewEULAAcceptancePage extends PGONewBasePage{
	public By logo = By.id("logo");
	public By eulaContent = By.id("content-eula");
	
	public PGONewEULAAcceptancePage(User u){
		pageDriver = u.getDriver();
	}
}
