package SharedClasses;

import org.openqa.selenium.By;
import PGOStudentResources.PGONewStudentHomePage;
import PGOStudentResources.StudentHomePageNew;
import UserClasses.User;

public class PGONewGooglePage extends PGONewBasePage{
	public By email = By.id("identifierId");
	public By nextButton = By.id("identifierNext");
	public By password = By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input");
	public By passwordNext = By.id("passwordNext");
	
	public By emailLinux = By.xpath("//*[@type=\"email\"]");
	public By nextButtonLinux = By.id("next");
	public By passwordLinux = By.id("Passwd");
	public By passwordNextLinux = By.id("signIn");
	
	public PGONewGooglePage(User u){
		user = u;
		pageDriver = u.getDriver();
	}
	
	public PGONewGooglePage enterEmail(String username){
		sendKeysToElement(username, email);
		click(nextButton);
		waitImplicitly(3);
		return this;
	}
	
	public PGONewGooglePage enterPassword(String pass){
		click(password);
		sendKeysToElement(pass, password);
		waitImplicitly(3);
		return this;
	}
	
	public StudentHomePageNew clickPasswordNext(){
		click(passwordNext);
		return new StudentHomePageNew(user);
	}
	
	public void login(User user){
		/*if(user.getLocal()){
			enterEmail(user.getUsername());
			enterPassword(user.getPassword());
			click(passwordNext);
		} else{
			enterEmailLinux(user.getUsername());
			enterPasswordLinux(user.getPassword());
			click(passwordNextLinux);
		}*/
	}
	
	public PGONewGooglePage enterEmailLinux(String username){
		sendKeysToElement(username, emailLinux);
		click(nextButtonLinux);
		waitImplicitly(3);
		return this;
	}
	
	public PGONewGooglePage enterPasswordLinux(String pass){
		click(passwordLinux);
		sendKeysToElement(pass, passwordLinux);
		waitImplicitly(3);
		return this;
	}
	
	public PGONewBasePage clickPasswordNextLinux(){
		click(passwordNextLinux);
		return new StudentHomePageNew(user);
	}
}
