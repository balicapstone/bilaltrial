package Tests;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.support.ui.ExpectedConditions;
import PGOPages.DocumentsPage;
import PGOPages.FAQPage;
import PGOPages.LoginPage;
import PGOPages.SystemRequirementsPage;
import SharedClasses.ThirdPartyPage;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.User;
import UserClasses.UserInfo;

public class NavigationBarTests{
	static LoginPage home;
	static User user;
	
	@BeforeClass
	public static void executeBefore(){
		user = new User(UserInfo.GARAGESTUDENT);
		home = new LoginPage(user);
	}
	
	
	@AfterClass
	public static void executeAfter(){
		home.closeCurrentWindow();
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
    		
    		home.closeCurrentWindow();
    		user.makeNewDriver();
    		home = new LoginPage(user);
    	}
    };
    
	@Rule
	public Retry retry = new Retry(3);
	
	
    /**
     * Tests that the FAQ button works and that all elements on the page are present.
     */
    @Test
    public void testFAQElementsPresent(){
    	FAQPage faq = home.navBar.clickFAQButton();
    	assertTrue("Asserts that the FAQ button redirects a user to the FAQ page", 
    			faq.verifyElementsVisibility(faq.getInitialVisibleElements()));
    	home = faq.navBar.clickPebbleGoLogo();
    }
    /**
     * Tests that the Pricing Button opens a new Third Party Window for pricing options.
     * http://www.capstonepub.com/library/digital/pebble-go/
     * @throws InterruptedException
     */
    @Test
    public void testPricingButton() throws InterruptedException{
    	String homeWindow = home.getDriver().getWindowHandle();

    	ThirdPartyPage pricing = home.navBar.clickPricingButton();
    	
    	assertTrue("Asserts that the Pricing button redirects a user to the PebbleGo pricing page",
    			pricing.verifyNewWindow(homeWindow, "https://www.capstonepub.com/library/digital/pebble-go/"));
    }
    
    /**
     * Tests that the Contact Us Button opens a new Third Party Window for Customer Service.
     * http://www.capstonepub.com/library/service/
     * @throws InterruptedException
     */
    @Test
    public void testContactUsButton() throws InterruptedException{
    	String homeWindow = home.getDriver().getWindowHandle();
 
    	ThirdPartyPage tPP = home.navBar.clickContactUsButton();
    	
    	assertTrue("Asserts that the Contact Us button redirects a user to the Customer Service page",
    			tPP.verifyNewWindow(homeWindow, "https://www.capstonepub.com/library/service/"));
    }
    
    /**
     * Tests that the PebbleGo Logo redirects to the About Us/Main Login Page and that all elements are present.
     */
    @Test
    @Category(SmokeTests.class)
    public void testPebbleGoLogo(){
    	SystemRequirementsPage systemRequirements = home.navBar.clickSystemRequirementsButton();
    	home = systemRequirements.navBar.clickPebbleGoLogo();
    	
    	assertTrue("Asserts that the PebbleGo Logo redirects to the Main Login page and that all elements are present",
    			home.verifyElementsVisibility(home.getInitialVisibleElements()));
    	assertTrue("Asserts that the PebbleGo Logo redirects to the Main Login page and that all Navigation Bar elements are present",
    			home.navBar.verifyElementsVisibility(home.navBar.getInitialVisibleElements()));
    	assertTrue("Asserts that the PebbleGo Logo redirects to the Main Login page and that all footer elements are present",
    			home.footerMenu.verifyElementsVisibility(home.footerMenu.getInitialVisibleElements()));
    }
    
    /**
     * Tests that the Documents Button redirects to the Documents Page and that all elements are present.
     * @throws InterruptedException
     */
    @Test
    public void testDocumentButton() throws InterruptedException{
    	DocumentsPage docs = home.navBar.clickDocumentsButton();
    	
    	assertTrue("Asserts that the Documents button redirects the user to the Documents page",
    			docs.verifyElementsVisibility(docs.getInitialVisibleElements()));
    	home = docs.navBar.clickPebbleGoLogo();
    }

    /**
     * Tests that the System Requirements Button redirects to the System Requirements Page and that all elements are present.
     */  
    @Test
    @Category(SmokeTests.class)
    public void testSystemRequirementsButton(){
    	SystemRequirementsPage systemRequirements = home.navBar.clickSystemRequirementsButton();
    	systemRequirements.clickCheckYourSystem();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(systemRequirements.platformName));
    	
    	assertTrue("Asserts that the System Requirements button redirects the user to the System Requirements page",
    			systemRequirements.getPageNameElement().toLowerCase().equals(systemRequirements.getDriverBrowserName().toLowerCase()));
    	home = systemRequirements.navBar.clickPebbleGoLogo();
    }
    
    /**
     * Tests that the Login Button that is visible on the FAQ, System Requirements, and Documents pages, redirects to the 
     * About Page and that all elements are present.
     */
    @Test
    @Category(SmokeTests.class)
    public void testLoginButton(){
    	SystemRequirementsPage systemRequirements = home.navBar.clickSystemRequirementsButton();
    	home = systemRequirements.navBar.clickPebbleGoLogo();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.footerMenu.aboutUsLink));
    	boolean LoginOne = home.verifyElementsVisibility(home.getInitialVisibleElements());
    	
    	FAQPage fAQ = home.navBar.clickFAQButton();
    	home = fAQ.navBar.clickPebbleGoLogo();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.footerMenu.aboutUsLink));
    	boolean LoginTwo = home.verifyElementsVisibility(home.getInitialVisibleElements());
    	
    	DocumentsPage doc = home.navBar.clickDocumentsButton();
    	home = doc.navBar.clickPebbleGoLogo();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.footerMenu.aboutUsLink));
    	boolean LoginThree = home.verifyElementsVisibility(home.getInitialVisibleElements());
    	
    	assertTrue("Asserts that the Login Button is visible on the FAQ, System Requirements, and Documents Page and redirect back to the homepage",
    			LoginOne && LoginTwo && LoginThree);
    }
    
    /**
     * Tests that the About Button redirects to the About Page/Login Page and that all elements are present.
     */
    @Test
    public void testAboutButton(){
    	SystemRequirementsPage systemRequirements = home.navBar.clickSystemRequirementsButton();
    	home = systemRequirements.navBar.clickAboutButton();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.footerMenu.aboutUsLink));
    	boolean LoginOne = home.verifyElementsVisibility(home.getInitialVisibleElements());
    	
    	FAQPage fAQ = home.navBar.clickFAQButton();
    	home = fAQ.navBar.clickAboutButton();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.footerMenu.aboutUsLink));
    	boolean LoginTwo = home.verifyElementsVisibility(home.getInitialVisibleElements());
    	
    	DocumentsPage doc = home.navBar.clickDocumentsButton();
    	home = doc.navBar.clickAboutButton();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.footerMenu.aboutUsLink));
    	boolean LoginThree = home.verifyElementsVisibility(home.getInitialVisibleElements());
    	
    	assertTrue("Asserts that the About Button redirects to the AboutPage/LoginPage from the FAQ, Documents, and SystemRequirements Page back to the homepage",
    			LoginOne && LoginTwo && LoginThree);
    }
    
    @Test
    public void testHomePageTopCommunityLink() throws InterruptedException{
    	assertTrue("Asserts the top communities link is correct",
    		home.verifyLinkContainsURLByElement(home.topCommunityLink, "http://community.mycapstone.com/"));
    }
}
