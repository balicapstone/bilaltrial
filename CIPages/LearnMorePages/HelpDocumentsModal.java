package LearnMorePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class HelpDocumentsModal extends BasePage{
	public By searchUpdateLink = By.linkText("Student Search and Discoverability Updates, September 2015.");
	public By letterHomeLink = By.linkText("School-to-Home Letter");
	public By letterHomeSpanishLink = By.linkText("School-to-Home Letter (in Spanish)");
	public By originalLoginLink = By.linkText("Login Student Reminder: Original Login");
	public By masterAccountLoginLink = By.linkText("Login Student Reminder: Capstone Master Login");
	public By googleLoginLink = By.linkText("Login Student Reminder: Google Classroom");
	
	public HelpDocumentsModal(WebDriver driver){
		pageDriver = driver;
	}
}
