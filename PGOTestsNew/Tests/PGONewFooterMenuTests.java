package Tests;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.support.ui.ExpectedConditions;
import PGOPages.AboutUsPage;
import PGOPages.CopyrightPage;
import PGOPages.CreditsPage;
import PGOPages.LoginPage;
import PGOPages.PrivacyPolicyPage;
import PGOPages.TermsAndConditionsPage;
import SharedClasses.ThirdPartyPage;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.User;
import UserClasses.UserInfo;

public class PGONewFooterMenuTests{
	static LoginPage loginPage;
    static User user;
	
	@BeforeClass
	public static void executeBefore(){
		user = new User(UserInfo.GARAGESTUDENT);
		
		try{
			loginPage = new LoginPage(user);
			user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.footerMenu.aboutUsLink));
		} catch(Exception e){
			user.getWatcher().TakeScreenshot("FailureRunningExecuteBeforeForFooterMenuTests");
		}
	}
	
	
	@AfterClass
	public static void executeAfter(){
		loginPage.closeCurrentWindow();
	}
	
	
	@Rule
    public TestWatcher restart = new TestWatcher(){
    	public void failed(Throwable e, Description description){
    		try{
    			user.getWatcher().TakeScreenshot(description.getDisplayName());
    		} catch(Exception t){
    			System.out.print("There was a failure while trying to take a screenshot for " + description.getDisplayName());
    		}
    		
    		loginPage.closeCurrentWindow();
    		user.makeNewDriver();
    		loginPage = new LoginPage(user);
    		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.footerMenu.aboutUsLink));
    	}
    };
    
	@Rule
	public Retry retry = new Retry(3);

    /*
     * Terms and Conditions footer link tests.
     */
    /**
     * Tests that the Terms and Conditions link redirects to the Terms and Conditions page and that all elements are present.
     */
    @Test
    public void testTermsAndConditionsElements(){
    	TermsAndConditionsPage tACPage = loginPage.footerMenu.clickTermsAndConditionLink();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tACPage.navBar.PebbleGoLogo));
    	
    	assertTrue("Asserts that the Terms and Conditions link redirects to the Terms and COnditions page and that all elements are present",
    			tACPage.verifyElementsVisibility(tACPage.getInitialVisibleElements()));
     	loginPage = tACPage.navBar.clickPebbleGoLogo();
     	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.footerMenu.aboutUsLink));
    }
    
    /**
     * Tests that the Copyright Notice link on the Term and Conditions page redirects to the Copyright Page.
     */
    @Test
    @Category(SmokeTests.class)
    public void testTermsAndConditionsNoticeLink(){
    	TermsAndConditionsPage tACPage = loginPage.footerMenu.clickTermsAndConditionLink();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tACPage.navBar.PebbleGoLogo));
    	
    	CopyrightPage copyrightPage = tACPage.clickCopyrightInfringementLink();
    	assertTrue("Asserts that the Copyright link on the Terms and Conditions page redirects to the Copyright Page",
    			copyrightPage.checkPebbleGoEmailLink());
    	loginPage = tACPage.navBar.clickPebbleGoLogo();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.footerMenu.aboutUsLink));
    }
    
    /**
     * Tests that the first Privacy Policy link on the Terms and Conditions page redirects to the Privacy Policy page.
     */
    @Test
    public void testFirstPrivacyPolicyLink(){
    	TermsAndConditionsPage tACPage = loginPage.footerMenu.clickTermsAndConditionLink();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tACPage.navBar.PebbleGoLogo));
    	
    	PrivacyPolicyPage privacyPolicyPage = tACPage.clickFirstPrivacyPolicyLink();
    	assertTrue("Asserts that the first Privacy Policy link on the Terms and Conditions page redirects to the Privacy Policy page",
    			privacyPolicyPage.verifyElementsVisibility(privacyPolicyPage.getInitialVisibleElements()));
    	loginPage = tACPage.navBar.clickPebbleGoLogo();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.footerMenu.aboutUsLink));
    }
    
    /**
     * Tests that the second Privacy Policy link on the Terms and Conditions page redirects to the Privacy Policy page.
     */
    @Test
    public void testSecondPrivacyPolicyLink(){
    	TermsAndConditionsPage tACPage = loginPage.footerMenu.clickTermsAndConditionLink();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tACPage.navBar.PebbleGoLogo));
    	
    	PrivacyPolicyPage privacyPolicyPage = tACPage.clickSecondPrivacyPolicyLink();
    	assertTrue("Asserts that the second Privacy Policy link on the Terms and Conditions page redirects to the Privacy Policy page",
    			privacyPolicyPage.verifyElementsVisibility(privacyPolicyPage.getInitialVisibleElements()));
    	loginPage = tACPage.navBar.clickPebbleGoLogo();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.footerMenu.aboutUsLink));
    }
    
    /**
     * Tests that the PebbleGo Email link is present at the bottom of the Terms and Conditions page.
     */
    @Test
    public void testPebbleGoEmail(){
    	TermsAndConditionsPage tACPage = loginPage.footerMenu.clickTermsAndConditionLink();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tACPage.navBar.PebbleGoLogo));
    	
    	assertTrue("Asserts that the PebbleGo Email link is present at the bottom of the Terms and Conditions page",
    			tACPage.verifyPebbleGoEmail());
    	loginPage = tACPage.navBar.clickPebbleGoLogo();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.footerMenu.aboutUsLink));
    }
    
    /*
     * Privacy Policy Footer Link Tests
     */
    /**
     * Tests that the Privacy Policy footer menu link redirects to the Privacy Policy page and that all elements are present.
     */
    @Test
    public void testPrivacyPolicyFooterLink(){
    	PrivacyPolicyPage privacyPolicyPage = loginPage.footerMenu.clickPrivacyPolicyLink();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(privacyPolicyPage.navBar.PebbleGoLogo));
    	
    	assertTrue("Asserts that the Privacy Policy Footer Link redirects to the Privacy Policy page and that all elements are present",
    			privacyPolicyPage.verifyElementsVisibility(privacyPolicyPage.getInitialVisibleElements()));
    	loginPage = privacyPolicyPage.navBar.clickPebbleGoLogo();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.footerMenu.aboutUsLink));
    }
    
    /**
     * Tests that the Terms of Use link on the Privacy Policy page redirects to the Terms and Conditions page. 
     */
    @Test
    public void testTermsofUseLink(){
    	PrivacyPolicyPage privacyPolicyPage = loginPage.footerMenu.clickPrivacyPolicyLink();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(privacyPolicyPage.navBar.PebbleGoLogo));
    	
    	TermsAndConditionsPage tACPage = privacyPolicyPage.clickTermsAndConditionsLink();
    	assertTrue("Asserts that the Terms of Use Link on the Privacy Policy page redirects to the Terms and Conditions page",
    			tACPage.verifyElementsVisibility(tACPage.getInitialVisibleElements()));
    	loginPage = tACPage.navBar.clickPebbleGoLogo();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.footerMenu.aboutUsLink));
    }
    
    /*
     * About Us Footer Link Tests
     */
    /**
     * Tests that the About Us footer link redirects to the About Us page and that all elements are present.
     */
    @Test
    public void testAboutUsFooterLink(){
     	AboutUsPage aboutUs = loginPage.footerMenu.clickAboutUsLink();
     	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(aboutUs.navBar.PebbleGoLogo));
     	
    	assertTrue("Asserts that the About Us footer link redirects to the About Us page and that all elements are present", 
    			aboutUs.verifyElementsVisibility(aboutUs.getInitialVisibleElements()));
    	loginPage = aboutUs.navBar.clickPebbleGoLogo();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.footerMenu.aboutUsLink));
    }
    
    /**
     * Tests that the Capstone Logo on the About Us page opens a third party page.
     * http://www.capstonepub.com/library/
     * @throws InterruptedException
     */
    @Test
    @Category(SmokeTests.class)
    public void testCapstoneLogo() throws InterruptedException{
    	AboutUsPage aboutUs = loginPage.footerMenu.clickAboutUsLink();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(aboutUs.navBar.PebbleGoLogo));
    	
    	ThirdPartyPage tPP = aboutUs.clickCapstoneLogo();
    	assertTrue("Asserts that the Capstone Logo on the About Us page opens a third party page.",
    			tPP.verifySameWindowNewPage("http://mycapstone.com/"));
    	tPP.getDriver().get("https://www.pebblego.com");
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.footerMenu.aboutUsLink));
    }
    
    /**
     * Tests that the PebbleGo link on the About Us page redirects to the Pebble Go Log in screen.
     * @throws InterruptedException
     */
    @Test
    public void testPebbleGoLink() throws InterruptedException{
    	AboutUsPage aboutUs = loginPage.footerMenu.clickAboutUsLink();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(aboutUs.navBar.PebbleGoLogo));
    	
    	loginPage = aboutUs.clickPebbleGoLink();
    	assertTrue("Asserts that the PebbleGo link on the About Us page redirects to the Pebble Go Log in screen",
    			loginPage.verifyElementsVisibility(loginPage.getInitialVisibleElements()));
    	assertTrue("Asserts that the PebbleGo link on the About Us page redirects to the Pebble Go Log in screen", loginPage.getDriver().getCurrentUrl().equals("https://www.pebblego.com/"));
  
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.footerMenu.aboutUsLink));
    }
    
    /*
     * Other footer menu tests.
     */
    /**
     * Tests that the Credits footer link redirects to the Credits page. 
     */
    @Test
    public void testCreditsFooterLink(){
    	CreditsPage creditsPage = loginPage.footerMenu.clickCreditsLink();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(creditsPage.navBar.PebbleGoLogo));
    	
    	assertTrue("Asserts that the Credits footer link redirects to the Credits page",
    			creditsPage.verifyElementsVisibility(creditsPage.getInitialVisibleElements()));
    	loginPage = creditsPage.navBar.clickPebbleGoLogo();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.footerMenu.aboutUsLink));
    }
    
    /**
     * Tests that the Copyright footer link redirects to the Copyright page and checks that the PebbleGo email is present.
     */
    @Test
    public void testCopyrightFooterLink(){
    	CopyrightPage copyrightPage = loginPage.footerMenu.clickCopyrightLink();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(copyrightPage.navBar.PebbleGoLogo));
    	
    	assertTrue("Asserts that the Copyright footer link redirects to the Copyright page",
    			copyrightPage.checkPebbleGoEmailLink());
    	loginPage = copyrightPage.navBar.clickPebbleGoLogo();
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.footerMenu.aboutUsLink));
    }
    
    @Rule
    public TestRule watcher = new TestWatcher() {
       protected void starting(Description description) {
          System.out.println("Starting test: " + description.getMethodName());
        
          
       }
    };
}