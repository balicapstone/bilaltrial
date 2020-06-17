package PGOStudentResources;

import org.openqa.selenium.By;

import SharedClasses.PGONewBasePage;
import UserClasses.User;

public class PGONewContentFooter extends PGONewBasePage{
	public By privacyPolicyLink = By.linkText("Privacy Policy");
	public By termsOfUseLink = By.linkText("Terms of Use");
	public By copyrightLink = By.linkText("© 2020 Capstone. All Rights Reserved.");
	public By educatorsResourcesLink = By.linkText("Educator Resources");
	
	public PGONewContentFooter(User u){
		user = u;
		pageDriver = u.getDriver();
	}
}
