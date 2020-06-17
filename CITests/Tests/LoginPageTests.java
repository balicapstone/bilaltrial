package Tests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.support.ui.ExpectedConditions;
import CITests.CIPages.CILoginPage;
import CITests.CIPages.StudentHomePage;
import LearnMorePages.EducatorsPage;
import LearnMorePages.LearnMoreAboutPage;
import LearnMorePages.PrivacyPolicyPage;
import LearnMorePages.TermsOfUsePage;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.User;
import UserClasses.UserInfo;

public class LoginPageTests {
	public static CILoginPage login;
	public static StudentHomePage student;
	public static User user;
	
	@BeforeClass
	public static void executeBefore(){
		try{
			user = new User(UserInfo.GARAGESTUDENT);
			login = new CILoginPage(user);
		}catch(Exception e){
			System.out.println("Failure to set up Login Page Tests");
			System.out.println(e);
		}
	}
	
	@AfterClass
	public static void executeAfter(){
		login.quit();
	}
	
	@Rule
	public TestWatcher restart = new TestWatcher(){
		@Override
		public void failed(Throwable e, Description description){
			try{
				user.getWatcher().TakeScreenshot(description.getDisplayName());
			}
			catch(Exception a){
				System.out.println("We could not take a screenshot at this time");
			}
			
			user.quit();
			user.makeNewDriver();
			
			login = new CILoginPage(user);
			login.waitForElement(login.aboutLink);
		}
	};
	
	@Rule
	public Retry retry = new Retry(3);
	
	
	@Test
	@Category(SmokeTests.class)
	public void testLearnMoreButton(){
    	LearnMoreAboutPage learnMore = login.clickLearnMoreButton();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(learnMore.capstoneLogoBottom));
    	assertTrue("Asserts that the Learn More button redirects to the Learn More About page",
    			learnMore.verifyElementsVisibility(learnMore.getInitialVisibleElements()));
    	learnMore.clickCapstoneLogoTop();
	}
	
	@Test
	public void testAboutCapstoneLink() throws InterruptedException{		
		assertTrue("Asserts that the About Capstone link redirects to the correct page",
				login.verifyLinkContainsURLByElement(login.aboutLink, "http://mycapstone.com/"));
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testPrivacyPolicyLink(){
		PrivacyPolicyPage privacy = login.clickPrivacyPolicyLink();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(privacy.contactUsTop));
		assertTrue("Asserts that the Privacy Policy link redirects to the correct page",
				privacy.verifyElementsVisibility(privacy.getInitialVisibleElements()));
		privacy.clickCapstoneLogoTop();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testTermsOfUseLink(){
		TermsOfUsePage terms = login.clickTermsOfUseLink();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(terms.contactUsTop));
		assertTrue("Asserts that the Terms of Ues link redirects to the correct page",
				terms.verifyElementsVisibility(terms.getInitialVisibleElements()));
		terms.clickCapstoneLogoTop();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testLicenseAgreementLink() throws InterruptedException{
		assertTrue("Asserts that the License Agreement link redirects to the correct page",
				login.verifyLinkByElement(login.licenseAgreementLink)
				&& login.verifyLinkContainsURLByElement(login.licenseAgreementLink, "/license-agreement.pdf"));
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testContactUsLink() throws InterruptedException{
		assertTrue("Asserts that the Contact Us link redirects to the correct page",
				login.verifyLinkContainsURLByElement(login.contactUsLink, "http://www.capstonepub.com/library/service/contact-ci/"));		
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testEducatorsLink(){
		EducatorsPage educators = login.clickEducatorsLink();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(educators.contactUsTop));
		assertTrue("Asserts that the Educators link leads to the correct page",
				educators.verifyElementsVisibility(educators.getInitialVisibleElements()));
		educators.clickCapstoneLogoTop();
	}
	
	@Test
	public void testCapstoneLogo() throws InterruptedException{
		assertTrue("Asserts the Capstone logo redirects the user to the Login Page",
			login.verifyLinkContainsURLByElement(login.capstoneLogo, "http://mycapstone.com/"));
	}
	
	@Test
	public void testStudentLogInNoEula(){
		user.setCredentials(UserInfo.CIDONT);
		login.login(user);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(login.studentEula.errorMessage));
		
		String message = login.getModalElement(login.studentEula.errorMessage).getText();
    	assertTrue("Asserts that the correct error message is returned when the user enters credentials for a account without a signed Eula", 
    			message.equals("You can’t read this book until your librarian accepts the license agreement with the administrator login."));
    	
    	login.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(login.getloginButton()));
	}
	
	@Test
	public void testTeacherLogInNoEula(){
		user.setCredentials(UserInfo.CIDONTTEACHER);
		
		login.login(user);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(login.adminEula.messageText));
		
		String message = login.getModalElement(login.adminEula.messageText).getText();
    	assertTrue("Asserts that the correct error message is returned when the user enters credentials for a account without a signed Eula", 
    			message.equals("In order to use this product, you must review and accept the terms below."));
    	
    	login.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(login.getloginButton()));
	}
	
	@Test
	public void testStudentSQSNoEula(){
		login.getDriver().get("https://staging.mycapstonelibrary.com/login/?sqs=194ee1d5ae926f02847f5a6edb875003f1514cde54be2767b00f283ffd7477a2");
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(login.adminEula.messageText));
		
		
		String message = login.getModalElement(login.adminEula.messageText).getText();
    	assertTrue("Asserts that the correct error message is returned when the user enters credentials for a account without a signed Eula", 
    			message.equals("You can’t read this book until your librarian accepts the license agreement with the administrator login."));
    	
    	login.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(login.getloginButton()));
	}
	
	/*
	@Test
	public void testStudentLoginSQS(){
		//String sqs = "qa.mycapstonelibrary.com/login/?sqs=VFIyyLqHfi4oJXS/iqyP0g==";
		//mycapstonelibrary.com/login/?sqs=VFIyyLqHfi4oJXS/iqyP0g== 
	}
	
	@Test
	public void testTeacherLogin(){
		
	}
	
	@Test
	public void testSuperAdmin(){
		
	}
	
	@Test
	public void testDistrictAdmin(){
		
	}
	
	@Test
	public void testAccountAdmin(){
		
	
	*/
}
