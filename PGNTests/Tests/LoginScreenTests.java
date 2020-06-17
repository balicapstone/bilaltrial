package Tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.internal.runners.statements.Fail;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import AdminResources.TeacherHomeScreen;
import PGNPages.AboutUsPage;
import PGNPages.CopyrightPage;
import PGNPages.CreditsPage;
import PGNPages.DocumentsPage;
import PGNPages.FAQPage;
import PGNPages.LoginPage;
import PGNPages.PrivacyPolicyPage;
import PGNPages.SystemRequirementsPage;
import PGNPages.TermsAndConditionsPage;
import PGNPages.TrialPage;
import PGNStudentResources.StudentHomePage;
import SharedClasses.ThirdPartyPage;
import TrueUserTests.TrueUser.PGNSmokeTest;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;
import UserClasses.UserInfo;

public class LoginScreenTests {
	 	static LoginPage login;
	    static int waitTime = 5;
	    static User user;
		static String communitiesLink = "https://www.pebblego.com/resources";
	    
	 	@BeforeClass
	    public static void executeBefore(){
	 		user = new User(UserInfo.GARAGESTUDENT);
	 		login = new LoginPage(user);
	    }
	    
	    @AfterClass
	    public static void executeAfter(){
	    	login.closeCurrentWindow();
	    }
	    

		
	    @Rule
	    public TestWatcher restart = new TestWatcher(){
	    	@Override
	    	public void failed(Throwable e, Description description){
	    		try{
	    			user.getWatcher().TakeScreenshot(description.getDisplayName());
	    		} catch(Exception t){
	    			System.out.print("There was a failure while trying to take a screenshot for " + description.getDisplayName());
	    		}
	    		
	    		user.quit();
	    		user.makeNewDriver();
	    		
	    		login = new LoginPage(user);
	   
	    	}
	    };
	    
		/*@Rule
		public Retry retry = new Retry(3);
	    */
	    @Test
	    @Category(PGNSmokeTest.class)
	    public void testAllVisibleElements(){
	    	login.waitImplicitly(waitTime);
	    	assertTrue("Asserts that all header elements are present", 
	    			login.header.verifyElementsVisibility(login.header.getInitialVisibleElements()));
	    	assertTrue("Asserts that all footer elements are present",
	    			login.verifyElementsVisibility(login.footer.getInitialVisibleElements()));
	    	assertTrue("Asserts that all Login Screen elements are present",
	    			login.verifyElementsVisibility(login.getInitialVisibleElements()));
	    }

	    @Test
	    @Category(PGNSmokeTest.class)
	    public void testFreeTrialButton(){
	    	TrialPage trial = login.clickFreeTrialButton();
	    	trial.waitImplicitly(waitTime);
	    	assertTrue("Asserts that the Free Trial Button redirects to the Trial Page",
	    			trial.verifyElementsVisibility(trial.getInitialVisibleElements()));
	    	trial.waitImplicitly(waitTime);
	    	login = trial.header.clickLogo();
	    	
	    }
	    
	    @Test
	    @Category(PGNSmokeTest.class)
	    public void testStudentLogin(){
	    	login.waitImplicitly(waitTime);
	    	StudentHomePage home = (StudentHomePage) login.login(user);
	    	home.waitImplicitly(waitTime);
	    	
	    	assertTrue("Asserts that the student home page elements appear", 
	    			home.verifyElementsVisibility(home.getInitialVisibleElements()));
	    	assertTrue("Asserts that all header elements are present", 
	    			home.header.verifyElementsVisibility(home.header.getInitialVisibleElements()));
	    	assertTrue("Asserts that all footer elements are present",
	    			home.footer.verifyElementsVisibility(home.footer.getInitialVisibleElements()));
	    	home.waitImplicitly(waitTime);
	    	login = home.header.logout();
	    }
	    
	    @Test
	    @Category(PGNSmokeTest.class)
	    public void testFAQButton(){
	    	FAQPage faq = login.header.clickFAQTab();
	    	assertTrue("Asserts that clicking the FAQ tab redirects the user to the FAQ Page",
	    			faq.verifyElementsVisibility(faq.getInitialVisibleElements()) && 
	    			faq.verifyElementsVisibility(faq.placingAnOrderTab.getInitialVisibleElements()));
	    	assertTrue("Asserts that the Header Elements appear", 
	    			faq.verifyElementsVisibility(faq.header.getInitialVisibleElements()));
	    	assertTrue("Asserts that the Footer Elements appear",
	    			faq.verifyElementsVisibility(faq.footer.getInitialVisibleElements()));
	    	faq.waitImplicitly(waitTime);
	    	login = faq.header.clickLogo();
	    }
	    
	    @Test
	    @Category(PGNSmokeTest.class)
	    public void testSystemRequirementsButton(){
	    	SystemRequirementsPage requirements = login.header.clickSystemRequirementsTab();
	    	assertTrue("Asserts that clicking the System Requirements tab redirects the user to the System Requirements Page",
	    			requirements.isElementPresent(requirements.checkYourSystemLink));
	    	assertTrue("Asserts that the Header Elements appear", 
	    			requirements.verifyElementsVisibility(requirements.header.getInitialVisibleElements()));
	    	assertTrue("Asserts that the Footer Elements appear",
	    			requirements.verifyElementsVisibility(requirements.footer.getInitialVisibleElements()));
	    	login = requirements.header.clickLogo();
	    }
	    
	    @Test
	    public void testDocumentsButton(){
	    	DocumentsPage documents = login.header.clickDocumentsTab();
	    	assertTrue("Asserts that clicking the Documents tab redirects the user to the Documents Page",
	    			documents.verifyElementsVisibility(documents.getInitialVisibleElements()));
	    	assertTrue("Asserts that the Header Elements appear", 
	    			documents.verifyElementsVisibility(documents.header.getInitialVisibleElements()));
	    	assertTrue("Asserts that the Footer Elements appear",
	    			documents.verifyElementsVisibility(documents.footer.getInitialVisibleElements()));
	    	documents.waitImplicitly(waitTime);
	    	login = documents.header.clickLogo();
	    }
	    
	    @Test
	    public void testPricingLink() throws InterruptedException{
	       	assertTrue("Asserts that the user is redirected to the PebbleGoNext Pricing page after clicking the Switch To PebbleGo link",
	    			login.verifyLinkByElement(login.header.pricingLink)
					&& login.verifyLinkContainsURLByElement(login.header.pricingLink, "http://www.capstonepub.com/library/digital/pebblego-next/"));

	    }
	    
	    @Test
	    public void testContactUsLink() throws InterruptedException{
	    	assertTrue("Asserts that the user is redirected to the PebbleGoNext Pricing page after clicking the Switch To PebbleGo link",
	    			login.verifyLinkByElement(login.header.contactUsLink)
					&& login.verifyLinkContainsURLByElement(login.header.contactUsLink, "http://www.capstonepub.com/library/service/"));
	    }
	    
	    @Test
	    public void testTermsAndConditionsLink(){
	    	TermsAndConditionsPage terms = login.footer.clickTermsAndConditionsLink();
	    	assertTrue("Asserts that the user is redirected to the Terms and Conditions page after clicking the Terms & Conditions footer link",
	    			terms.verifyElementsVisibility(terms.getInitialVisibleElements()));
	    	terms.waitImplicitly(waitTime);
	    	login = terms.header.clickLogo();
	    }
	    
	    @Test
	    public void testPrivacyPolicyLink(){
	    	PrivacyPolicyPage policy = login.footer.clickPrivacyPolicyLink();
	    	assertTrue("Asserts that the user is redirected to the Privacy Policy page after clicking the Privacy Policy footer link",
	    			policy.verifyElementsVisibility(policy.getInitialVisibleElements()));
	    	policy.waitImplicitly(waitTime);
	    	login = policy.header.clickLogo();
	    }
	    
	    @Test
	    @Category(PGNSmokeTest.class)
	    public void testAboutUsLink(){
	    	AboutUsPage about = login.footer.clickAboutUsLink();
	    	assertTrue("Asserts that the user is redirected to the About Us page after clicking the About Us footer link",
	    			about.verifyElementsVisibility(about.getInitialVisibleElements()));
	    	about.waitImplicitly(waitTime);
	    	login = about.header.clickLogo();
	    }
	    
	    @Test
	    public void testCreditsLink(){
	    	CreditsPage credits = login.footer.clickCreditsLink();
	    	assertTrue("Asserts that the user is redirected to the Credits page after clicking the Credit footer link",
	    			credits.isElementPresent(credits.advisorsLink));
	    	credits.waitImplicitly(waitTime);
	    	login = credits.header.clickLogo();
	    }
	    
	    @Test
	    public void testCopyrightLink(){
	    	CopyrightPage copyright = login.footer.clickCopyrightPage();
	    	assertTrue("Asserts that the user is redirected to the Copyright page after clicking the Copyright footer link",
	    			copyright.isElementPresent(copyright.supportEmailLink));
	    	copyright.waitImplicitly(waitTime);
	    	login = copyright.header.clickLogo();
	    }
	    
	    @Test
	    @Category(PGNSmokeTest.class)
	    public void testBottomCommunityLinkHomepage() throws InterruptedException{
	    	ThirdPartyPage tPP = login.clickBottomCommunitiesLink();
	    	
	    	assertTrue("Asserts the Community link directs to the community capstone site",
	    		tPP.verifySameWindowNewPage(communitiesLink));
	    	tPP.goBack();
	    }   
	    
	    @Test
	    @Category(PGNSmokeTest.class)
	    public void testTopCommunityLinkHomepage() throws InterruptedException{	    	
	    	String oldWindow = login.getDriver().getWindowHandle();
	    	ThirdPartyPage tPP = login.clickTopCommunitiesLink();
	    	
	    	assertTrue("Asserts the Community link directs to the community capstone site",
	    		tPP.verifyNewWindow(oldWindow, communitiesLink));
	    	tPP.goBack();
	    }  
	    
	    @Test
	    @Category(PGNSmokeTest.class)
	    public void testSQSStudent(){
	    	login.getDriver().get("https://"+user.getEnvironment()+".pebblegonext.com/login/?sqs="+UserInfo.GARAGESTUDENT.getSQS());
	    	
	    	StudentHomePage home = new StudentHomePage(login.getDriver()); 
	    	
	    	assertTrue("Asserts that the student home page elements appear", 
	    			home.verifyElementsVisibility(home.getInitialVisibleElements()));
	    	assertTrue("Asserts that all header elements are present", 
	    			home.header.verifyElementsVisibility(home.header.getInitialVisibleElements()));
	    	assertTrue("Asserts that all footer elements are present",
	    			home.footer.verifyElementsVisibility(home.footer.getInitialVisibleElements()));
	    	home.waitImplicitly(waitTime);
	    	login = home.header.logout();
	    }
	    
	    @Test
	    public void testDirectASPXURLLogin(){
	    	String ASPXLink = "https://"+user.getEnvironment()+".pebblegonext.com/UserLogin.aspx?sqs="+UserInfo.GARAGESTUDENT.getSQS();
	    	StudentHomePage home = new StudentHomePage(login.getDriver()); 
	    	
	    	login.getDriver().get(ASPXLink);
	    	
	    	home.waitImplicitly(waitTime);
	    	
	    	assertTrue("Asserts that the student home page elements appear", 
	    			home.verifyElementsVisibility(home.getInitialVisibleElements()));
	    	assertTrue("Asserts that all header elements are present", 
	    			home.header.verifyElementsVisibility(home.header.getInitialVisibleElements()));
	    	assertTrue("Asserts that all footer elements are present",
	    			home.footer.verifyElementsVisibility(home.footer.getInitialVisibleElements()));
	    	home.waitImplicitly(waitTime);
	    	login = home.header.logout();
	    }
	    
	    
	    @Test
	    @Category(PGNSmokeTest.class)
	    public void testUnsignedEulaForPGN(){
	    	user.setCredentials(UserInfo.PGNDONT);
	    	
	    	login.login(user);
	    	login.waitImplicitly(2);
	    	
	    	String message = login.getModalElement(login.loginModal.errorMessage).getText();
	    	assertTrue("Asserts that a student cannot access PGN without a signed EULA", 
	    			message.equals("You need to have your instructor sign your eula."));
	    	
	    	login.refresh();
	    }
	    
	    /*
	    @Test
	    @Category(Fail.class)
	    public void testSQSTeacher(){
	    	login.getDriver().get("https://"+user.getEnvironment()+".pebblegonext.com/login/?sqs="+UserInfo.GARAGETEACHER.getSQS());
	    	TeacherHomeScreen teacher = new TeacherHomeScreen(login.getDriver());
	    	
	    	assertTrue(teacher.verifyElementsVisibility(teacher.getInitialVisibleElements()));
	    	teacher.closeCurrentWindow();
	    	login = new LoginPage(user);
	    }
	    */
}
