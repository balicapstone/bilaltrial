package LinkedAccounts.Tests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AdminResources.PGONewTeacherHomeScreen;
import LinkedAccounts.Pages.PGONewBuildingAdminDashboard;
import LinkedAccounts.Pages.PGONewCILoginPage;
import LinkedAccounts.Pages.PGONewCIStudentHomePage;
import LinkedAccounts.Pages.PGONewLinkAccountsPage;
import LinkedAccounts.Pages.PGONewLinkAccountsTab;
import LinkedAccounts.Pages.PGONewPGNLoginPage;
import LinkedAccounts.Pages.PGONewPGNStudentHomePage;
import LinkedAccounts.Pages.PGONewSorryModal;
import LinkedAccounts.Pages.PGONewTeacherLogin;
import PGOPages.LoginPage;
import PGOPagesNew.PGONewLoginPage;
import PGOStudentResources.StudentHomePageNew;
import SharedClasses.PGONewGooglePage;
import TrueUserTests.TrueUser.Retry;
import UserClasses.ProductType;
import UserClasses.User;
import UserClasses.UserInfo;

public class RegressionPGONewLinkedAccountTests {
	
	static User user;
	static LoginPage home;
	
	@BeforeClass
	public static void executeBefore(){

	}
	
	@AfterClass
	public static void executeAfter(){

	}
	
	@Rule
    public TestWatcher restart = new TestWatcher(){
    	@Override
    	public void failed(Throwable e, Description description){
    		try{
    			user.getWatcher().TakeScreenshot(description.getDisplayName());
    		} catch(Exception t){
    			System.out.println("Unable to take a screenshot at this time");
    		}
 		
    		user.quit();
    	}
    };
	
    @Rule
    public Retry retry = new Retry(3);
	
	@Test
	public void testUnsignedEULAPGO() throws InterruptedException{
		user = new User(UserInfo.PGODONTTEACHER);
		PGONewTeacherLogin tLogin = new PGONewTeacherLogin(user);
		
		PGONewLinkAccountsPage link = tLogin.login(user);
		
		user.setCredentials(UserInfo.PGODONT);

		link.linkPGOAccount(user);		
		//waiting until the button does not have deactive in class
		user.customWait().until(ExpectedConditions.attributeContains(link.pgoNext, "class", "primary submit rounded-submit rounded-link-button"));

		link.clickPGONext();
	
		assertTrue("Asserts that the user can see the PGO Eula", 
				link.verifyLinkByElement(link.pgoEulaView)
				&& link.verifyLinkContainsURLByElement(link.pgoEulaView, "https://assets.mycapstonelibrary.com/pdf/Eula-1.0.0.pdf"));
		user.quit();
	}
	
	@Test
	public void testUnsignedEULAPGN() throws InterruptedException{
		user = new User(UserInfo.PGNDONTTEACHER);
		PGONewTeacherLogin tLogin = new PGONewTeacherLogin(user);
		
		PGONewLinkAccountsPage link = tLogin.login(user);
		link.goToPGNTab();
		
		user.setCredentials(UserInfo.PGNDONT);
		
		link.linkPGNAccount(user);

		user.customWait().until(ExpectedConditions.attributeContains(link.pgnNext, "class", "primary submit rounded-submit rounded-link-button"));

		link.clickPGNNext();
		
		assertTrue("Asserts that the user can see the PGN Eula", 		
				link.verifyLinkByElement(link.pgnEulaView)
				&& link.verifyLinkContainsURLByElement(link.pgnEulaView, "https://assets.mycapstonelibrary.com/pdf/Eula-1.0.0.pdf"));
		
		user.quit();
	}
	
	@Test
	public void testUnsignedEULACI() throws InterruptedException{
		user = new User(UserInfo.CIDONTTEACHER);		
		PGONewTeacherLogin tLogin = new PGONewTeacherLogin(user);
		
		PGONewLinkAccountsPage link = tLogin.login(user);
		link.goToCITab();
		user.setCredentials(UserInfo.CIDONT);
		link.linkCIAccount(user);

		user.customWait().until(ExpectedConditions.attributeContains(link.pgoNext, "class", "primary submit rounded-submit rounded-link-button"));
		
		link.clickCINext();
		
		assertTrue("Asserts that the user can see the CI Eula", 
				link.verifyLinkByElement(link.ciEulaView)
				&& link.verifyLinkContainsURLByElement(link.ciEulaView, "https://assets.mycapstonelibrary.com/pdf/Eula-1.0.0.pdf"));
		user.quit();
	}
	
	
	@Test
	public void testCantLinkLinkedAccountPGO(){
		user = new User(UserInfo.PGODONTTEACHER);
	
		PGONewTeacherLogin tLogin = new PGONewTeacherLogin(user);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(tLogin.submit));
		
		PGONewLinkAccountsPage link = tLogin.login(user);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(link.pgoNext));
		
		link.enterPGOUsername("pgoonly");
		link.enterPGOPassword("test");
		
		user.customWait().until(ExpectedConditions.attributeContains(link.pgoNext, "class", "primary submit rounded-submit rounded-link-button"));
				
		link.clickPGONext();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(link.pgoAccountError));
		
		assertTrue("Asserts that link accounts recognizes the user credentials are already taken", 
				link.getPGOAccountErrorText().equals("Account is already linked"));
		user.quit();
	}
	
	@Test
	public void testCantLinkLinkedAccountPGN(){
		user = new User(UserInfo.PGODONTTEACHER);

		PGONewTeacherLogin tLogin = new PGONewTeacherLogin(user);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(tLogin.submit));
		
		PGONewLinkAccountsPage link = tLogin.login(user);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(link.pgoNext));
		link.goToPGNTab();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(link.pgnNext));
		
		link.enterPGNUsername("pgnstudent");
		link.enterPGNPassword("test");

		user.customWait().until(ExpectedConditions.attributeContains(link.pgoNext, "class", "primary submit rounded-submit rounded-link-button"));

		link.clickPGNNext();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(link.pgnAccountError));
		
		assertTrue("Asserts that link accounts recognizes the user credentials are already taken",
			link.getPGNAccountErrorText().equals("Account is already linked"));
		user.quit();
	}
	
	@Test
	public void testCantLinkLinkedAccountCI(){
		user = new User(UserInfo.PGODONTTEACHER);
	
		PGONewTeacherLogin tLogin = new PGONewTeacherLogin(user);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(tLogin.submit));
		PGONewLinkAccountsPage link = tLogin.login(user);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(link.pgoNext));
		link.goToCITab();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(link.ciNext));
		
		link.enterCIUsername("cistudent");
		link.enterCIPassword("test");
		
		//waiting until the button does not have deactive in class
		user.customWait().until(ExpectedConditions.attributeContains(link.ciNext, "class", "primary submit rounded-submit rounded-link-button"));

		link.clickCINext();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(link.ciAccountError));
	
		assertTrue("Asserts that link accounts recognizes the user credentials are already taken",
				link.getCIAccountErrorText().equals("Account is already linked"));
		user.quit();
	}
	
	@Test
	public void testUsernamesAreTakenInOtherDatabases(){
		user = new User(UserInfo.PGODONTTEACHER);
		
		PGONewTeacherLogin tLogin = new PGONewTeacherLogin(user);
		PGONewLinkAccountsPage link = tLogin.login(user);
		link.goToMasterTab();
		link.clickNewMasterUsername();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(link.masterSubmit));
		
		link.enterNewMasterUsername("pgoonly");
		link.enterMasterPassword("t");
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(link.masterAccountError));
		
		assertTrue("Asserts that a user cannot create a master account with a username in PGO", 
				link.getMasterAccountErrorText().equals("Username is taken please try a different one."));
		
		link.enterNewMasterUsername("4");
		link.enterMasterPassword("t");
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(link.masterAccountError));

		assertTrue("Asserts that the error message disappears with a valid username", 
				link.getMasterAccountErrorText().equals("Username is available."));
		
		link.clearMasterUsername();
		
		link.enterNewMasterUsername("pgnstudent");
		link.enterMasterPassword("t");
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(link.masterAccountError));
		
		assertTrue("Asserts that a user cannot create a master account with a username in PGN",
				link.getMasterAccountErrorText().equals("Username is taken please try a different one."));
		
		link.enterNewMasterUsername("4");
		link.enterMasterPassword("t");
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(link.masterAccountError));
		
		assertTrue("Asserts that the error message disappears with a valid username",
				link.getMasterAccountErrorText().equals("Username is available."));
		
		link.clearMasterUsername();
		
		link.enterNewMasterUsername("cistudent");
		link.enterMasterPassword("t");
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(link.masterAccountError));
		
		assertTrue("Asserts that a user cannot create a master account with a username in CI",
				link.getMasterAccountErrorText().equals("Username is taken please try a different one."));
		
		link.enterNewMasterUsername("4");
		link.enterMasterPassword("t");
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(link.masterAccountError));
		
		assertTrue("Asserts that the error message disappears with a valid username", 
				link.getMasterAccountErrorText().equals("Username is available."));
		
		link.clearMasterUsername();
		link.enterNewMasterUsername("garagemaster");
		link.enterMasterPassword("t");
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(link.masterAccountError));
		
		assertTrue("Asserts that the user cannot create a master account with a username in master",
				link.getMasterAccountErrorText().equals("Username is taken please try a different one."));
		
		link.enterNewMasterUsername("4");
		link.enterMasterPassword("t");
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(link.masterAccountError));
		
		assertTrue("Asserts that the error message disappears with a valid username",
				link.getMasterAccountErrorText().equals("Username is available."));
		user.quit();		
	}
	
	@Test
	public void testLoginToCIWithMaster(){
		user = new User(UserInfo.MASTERACCOUNT);

		PGONewCILoginPage cilogin = new PGONewCILoginPage(user);
		
		PGONewCIStudentHomePage student = (PGONewCIStudentHomePage) cilogin.login(user);
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(student.viewAllBooksButton));
		
		assertTrue("Asserts that the master account can navigate to the CI student homepage",
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		student.logout();
		student.quit();
	}

	@Test
	public void testLoginToPGNWithMaster(){
		user = new User(UserInfo.MASTERACCOUNT);
		PGONewPGNLoginPage login = new PGONewPGNLoginPage(user);
		PGONewPGNStudentHomePage student = (PGONewPGNStudentHomePage) login.login(user);
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(student.goToScienceLink));
		
		assertTrue("Asserts that the master account can navigate to the PGN student homepage",
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		student.laToolbar.logout(ProductType.PGN);
		user.quit();
	}
	
	@Test
	public void testLoginToPGOWithMaster(){
		user = new User(UserInfo.MASTERACCOUNT);
		PGONewLoginPage login = new PGONewLoginPage(user);
		StudentHomePageNew student = (StudentHomePageNew) login.Login(user);
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("Animals")));
		
		assertTrue("Asserts that the master account can navigate to the PGO student homepage",
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		student.header.logout();
		user.quit();
	}

	@Test
	public void testCantSwitchFromPGN(){
		user = new User(UserInfo.PGNSTUDENT);
	
		PGONewPGNLoginPage login = new PGONewPGNLoginPage(user);
		PGONewPGNStudentHomePage student = (PGONewPGNStudentHomePage) login.login(user);

		if(!student.getDriver().getCurrentUrl().contains("https")){
			student.getDriver().get(student.getDriver().getCurrentUrl().replace("http", "https"));
		}
		
		PGONewSorryModal sorry = (PGONewSorryModal) student.laToolbar.clickPGOLink(user.hasPGOSub());
		user.customWait().until(ExpectedConditions.elementToBeClickable(sorry.takeMeBackLink));
		//sorry.waitImplicitly(2);
		
		assertTrue("Asserts that the correct product names are present on a sorry modal", 
				sorry.getPGNContinueLinkText().equals("Continue to PebbleGo") && 
				sorry.getPGNTakeMeBackLinkText().equals("Take me back to Pebblego Next"));
		sorry.returnToPGN();
		
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(sorry.takeMeBackLink));
		//sorry.waitImplicitly(1);
		
		student.laToolbar.clickCILink(user.hasCISub());
		
		assertTrue("Asserts that the correct product names are present on a sorry modal",
				sorry.getPGNContinueLinkText().equals("Continue to Capstone Interactive") &&
				sorry.getPGNTakeMeBackLinkText().equals("Take me back to Pebblego Next"));
				
		sorry.returnToPGN();
		student.laToolbar.logout(ProductType.PGN);
		user.quit();
	}
	
	@Test
	public void testCantSwitchFromPGO(){
		user = new User(UserInfo.PGOSTUDENT);

		PGONewLoginPage login = new PGONewLoginPage(user);
		StudentHomePageNew student = (StudentHomePageNew) login.Login(user);
		String originalWindow = user.getDriver().getWindowHandle();
		
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(student.header.capstoneDropdown));
		student.click(student.header.capstoneDropdown); 
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(student.header.pebbleGoNextLink));
		student.click(student.header.pebbleGoNextLink);
		
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(2));
		
		student.SwitchToNewPage(originalWindow);
		
		user.customWait().until(ExpectedConditions.urlContains("pebblegonext"));
		
		PGONewPGNLoginPage pgnLogin = new PGONewPGNLoginPage(user);
		
		assertTrue("Asserts that the correct product names are present on a sorry modal",
				pgnLogin.verifyElementsVisibility(pgnLogin.getInitialVisibleElements()));

		student.closeCurrentWindow();
		user.getDriver().switchTo().window(originalWindow);
		
		user.customWait().until(ExpectedConditions.urlContains("pebblego.com"));
		
		student.click(student.header.capstoneInteractiveLink);
		
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(2));
		
		student.SwitchToNewPage(originalWindow);
		
		user.customWait().until(ExpectedConditions.urlContains("mycapstonelibrary"));
		
		PGONewCILoginPage ciLogin = new PGONewCILoginPage(user);
		
		assertTrue("Asserts that the correct product names are present on a sorry modal",
				ciLogin.verifyElementsVisibility(ciLogin.getInitialVisibleElements()));

		student.closeCurrentWindow();
		user.getDriver().switchTo().window(originalWindow);
		
		user.customWait().until(ExpectedConditions.urlContains("pebblego.com"));

		user.quit();
	}
	
	@Test
	public void testCantSwitchFromCI(){
		user = new User(UserInfo.CIONLY);

		PGONewCILoginPage login = new PGONewCILoginPage(user);
		PGONewCIStudentHomePage student = (PGONewCIStudentHomePage) login.login(user);
		
		PGONewSorryModal sorry = (PGONewSorryModal) student.laToolbar.clickPGOLink(user.hasPGOSub());
		user.customWait().until(ExpectedConditions.visibilityOf(sorry.getModalElement(sorry.takeMeBackLink)));
		
		assertTrue("Asserts that the correct product names are present on a sorry modal",
				sorry.getContinueLinkText().equals("Continue to PebbleGo") &&
				sorry.getTakeMeBackLinkText().equals("Take me back to Capstone Interactive"));
		sorry.returnToProduct();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(sorry.takeMeBackLink));
		
		sorry = (PGONewSorryModal) student.laToolbar.clickPGNLink(user.hasPGNSub());
		user.customWait().until(ExpectedConditions.visibilityOf(sorry.getModalElement(sorry.takeMeBackLink)));
		
		assertTrue("Asserts that the correct product names are present on a sorry modal",
				sorry.getContinueLinkText().equals("Continue to PebbleGo Next")
				&& sorry.getTakeMeBackLinkText().equals("Take me back to Capstone Interactive"));
		sorry.returnToProduct();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(sorry.takeMeBackLink));
		
		student.laToolbar.logout(ProductType.CI);
		student.quit();
	}
	
	@Test
	public void testSwitchBetweenAllProducts(){
		user = new User(UserInfo.MASTERACCOUNT);
	
		PGONewCILoginPage login = new PGONewCILoginPage(user);
		PGONewCIStudentHomePage ci = (PGONewCIStudentHomePage) login.login(user);
		user.customWait().until(ExpectedConditions.elementToBeClickable(ci.booklistsButton));
		
		assertTrue(ci.verifyElementsVisibility(ci.getInitialVisibleElements()));
		StudentHomePageNew pgo =  (StudentHomePageNew) ci.laToolbar.clickPGOLink(user.hasPGOSub());
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.id("Animals")));
		
		assertTrue(pgo.verifyElementsVisibility(pgo.getInitialVisibleElements()));
		String originalWindow = user.getDriver().getWindowHandle();
		
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(pgo.header.capstoneDropdown));
		pgo.click(pgo.header.capstoneDropdown);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(pgo.header.pebbleGoNextLink));
		pgo.click(pgo.header.pebbleGoNextLink);
		
		pgo.SwitchToNewPage(originalWindow);
		String newWindow = user.getDriver().getWindowHandle();
		
		pgo.closeAllOtherTabsBut(newWindow);
		
		PGONewPGNStudentHomePage pgn = new PGONewPGNStudentHomePage(user);
		user.customWait().until(ExpectedConditions.elementToBeClickable(pgn.goToScienceLink));
		
		assertTrue(pgn.verifyElementsVisibility(pgn.getInitialVisibleElements()));
		ci = (PGONewCIStudentHomePage) pgn.laToolbar.clickCILink(user.hasCISub());
		user.customWait().until(ExpectedConditions.elementToBeClickable(ci.booklistsButton));
		
		assertTrue(ci.verifyElementsVisibility(ci.getInitialVisibleElements()));
		pgn = (PGONewPGNStudentHomePage) ci.laToolbar.clickPGNLink(user.hasPGNSub());
		user.customWait().until(ExpectedConditions.elementToBeClickable(pgn.goToScienceLink));
		
		assertTrue(pgn.verifyElementsVisibility(pgn.getInitialVisibleElements()));
		pgo = (StudentHomePageNew) pgn.laToolbar.clickPGOLink(user.hasPGOSub());
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.id("Animals")));
		
		assertTrue(pgo.verifyElementsVisibility(pgo.getInitialVisibleElements()));
		
		originalWindow = user.getDriver().getWindowHandle();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(pgo.header.capstoneDropdown));
		pgo.click(pgo.header.capstoneDropdown);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(pgo.header.capstoneInteractiveLink));
		pgo.click(pgo.header.capstoneInteractiveLink);
		
		pgo.SwitchToNewPage(originalWindow);
		newWindow = user.getDriver().getWindowHandle();
		
		pgo.closeAllOtherTabsBut(newWindow);
		
		ci = new PGONewCIStudentHomePage(user);
		user.customWait().until(ExpectedConditions.elementToBeClickable(ci.booklistsButton));
		
		ci.laToolbar.logout(ProductType.CI);
		user.quit();
	}
	
	@Test
	public void testGoogleAccountPGO(){
		user = new User(UserInfo.MASTERGOOGLE);
	
		PGONewLoginPage login = new PGONewLoginPage(user);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(login.googleButton));
		
		PGONewGooglePage google = login.clickGoogleLogin();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(login.googleButton));
		
		google.login(user);
		
		StudentHomePageNew student = new StudentHomePageNew(user);
		
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.id("Animals")));
		
		assertTrue("Asserts User can use google login to log in to PGO", 
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		
		student.header.logout();
		student.quit();
	}
	
	@Test
	public void testGoogleAccountPGN(){
		user = new User(UserInfo.MASTERGOOGLE);

		PGONewPGNLoginPage login = new PGONewPGNLoginPage(user);
		
		PGONewGooglePage google = login.openLoginModal().clickGoogleLogin(user);
		
		google.login(user);
		
		PGONewPGNStudentHomePage pgn = new PGONewPGNStudentHomePage(user); 
		user.customWait().until(ExpectedConditions.elementToBeClickable(pgn.goToScienceLink));
		
		assertTrue("Asserts User can use google login to log in to PGN",
				pgn.verifyElementsVisibility(pgn.getInitialVisibleElements()));
		
		pgn.laToolbar.logout(ProductType.PGN);
		user.quit();
	}
	
	@Test
	public void testGoogleAccountCI(){
		user = new User(UserInfo.MASTERGOOGLE);

		PGONewCILoginPage login = new PGONewCILoginPage(user);
		
		PGONewGooglePage google = login.openLoginModal().clickGoogleLogin(user);
		
		google.login(user);
		PGONewCIStudentHomePage ci = new PGONewCIStudentHomePage(user);
		user.customWait().until(ExpectedConditions.elementToBeClickable(ci.booklistsButton));
		
		assertTrue("Asserts User can use google login to log in to CI", 
				ci.verifyElementsVisibility(ci.getInitialVisibleElements()));
		
		ci.laToolbar.logout(ProductType.CI);
		user.quit();
	}
	
	@Test
	public void testLinkAccountAccessibleCITeacher(){
		user = new User(UserInfo.CITEACHER);
		
		PGONewCILoginPage login = new PGONewCILoginPage(user);
		
		PGONewBuildingAdminDashboard admin = (PGONewBuildingAdminDashboard) login.login(user);
		PGONewLinkAccountsTab linkAccounts = admin.clickLinkAccountsTab();
		
		String currentWindow = linkAccounts.getDriver().getWindowHandle();
		
		PGONewTeacherLogin teach = linkAccounts.clickLinkAccountsButton();
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(2));
		
		teach.SwitchToNewPage(currentWindow);
		user.customWait().until(ExpectedConditions.elementToBeClickable(teach.username));
		
		assertTrue("Asserts that user could navigate to Link Accounts Teacher Page",
				teach.verifyElementsVisibility(teach.initialVisibleElements));
		
		teach.closeCurrentWindow();	
		linkAccounts.getDriver().switchTo().window(currentWindow);
		currentWindow = linkAccounts.getDriver().getWindowHandle();
		
		PGONewGooglePage google = linkAccounts.clickLinkGoogleToAccountButton();
		
		if(user.getLocal()){
			user.customWait().until(ExpectedConditions.elementToBeClickable(google.email));
			By[] test = {google.email, google.nextButton};
			
			assertTrue("Asserts that user could navigate to Link Accounts Teacher Page",
					google.verifyElementsVisibility(test));
		}else{
			user.customWait().until(ExpectedConditions.elementToBeClickable(google.emailLinux));
			By[] test = {google.emailLinux, google.nextButtonLinux};
			
			assertTrue("Asserts that user could navigate to Link Accounts Teacher Page",
					google.verifyElementsVisibility(test));
		}
		
		google.closeCurrentWindow();
		user.quit();
	}
	
	@Test
	public void testLinkAccountsAccessiblePGOTeacher(){
		user = new User(UserInfo.GARAGETEACHER);
	
		PGONewLoginPage login = new PGONewLoginPage(user);
		
		PGONewTeacherHomeScreen teacher = (PGONewTeacherHomeScreen) login.Login(user);
		
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(teacher.logoutButton));
		
		teacher.clickLinkAccountsTab();
		String currentWindow = teacher.getDriver().getWindowHandle();
		
		PGONewTeacherLogin linkAccounts = teacher.clickLinkAccountsButton();
		teacher.waitImplicitly(1);
		
		teacher.SwitchToNewPage(currentWindow);
		
		if(linkAccounts.getDriver().getCurrentUrl().contains("http://")){
			linkAccounts.getDriver().get(linkAccounts.getDriver().getCurrentUrl().replace("http://", "https://"));
			linkAccounts.waitImplicitly(2);
		}
		
		user.customWait().until(ExpectedConditions.elementToBeClickable(linkAccounts.username));
		
		assertTrue("Asserts that user could navigate to Link Accounts Teacher Page",
				linkAccounts.verifyElementsVisibility(linkAccounts.initialVisibleElements));
		
		linkAccounts.closeCurrentWindow();
		
		linkAccounts.getDriver().switchTo().window(currentWindow);
		
		PGONewGooglePage google = teacher.clickGoogleAccountsButton();		
		
		if(user.getLocal()){
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(google.email));
			By[] test = {google.email, google.nextButton};
			
			assertTrue("Asserts that user could navigate to Link Accounts Teacher Page",
					google.verifyElementsVisibility(test));
		}else{
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(google.emailLinux));
			By[] test = {google.emailLinux, google.nextButtonLinux};
			
			assertTrue("Asserts that user could navigate to Link Accounts Teacher Page",
					google.verifyElementsVisibility(test));
		}
		
		google.closeCurrentWindow();
		user.quit();
	}
	
	@Test
	public void testCantAccessGooglePGO(){
		user = new User(UserInfo.NOLINKPGOTEACHER);

		PGONewLoginPage login = new PGONewLoginPage(user);
		
		PGONewTeacherHomeScreen teacher = (PGONewTeacherHomeScreen) login.Login(user);
		user.customWait().until(ExpectedConditions.elementToBeClickable(teacher.linkAccountsTab));
		teacher.clickLinkAccountsTab();

		assertTrue("Asserts that the user cannot access Google account linking without linked account", 
				teacher.getElementText(teacher.getGoogleLinkError()).equals("In order to link your class IDs to your Capstone account, please set up a Capstone account by clicking the ‘Link accounts’ button above."));

		user.quit();
	}
	
	@Test
	public void testCantAccessGooglePGN(){
		user = new User(UserInfo.NOLINKPGNTEACHER);
		
		PGONewPGNLoginPage login = new PGONewPGNLoginPage(user);
		
		PGONewTeacherHomeScreen teacher = (PGONewTeacherHomeScreen) login.login(user);
		user.customWait().until(ExpectedConditions.elementToBeClickable(teacher.linkAccountsTab));
		teacher.clickLinkAccountsTab();

		assertTrue("Asserts that the user cannot access Google account linking without linked account", 
				teacher.getElementText(teacher.getGoogleLinkError()).equals("In order to link your class IDs to your Capstone account, please set up a Capstone account by clicking the ‘Link accounts’ button above."));

		teacher.quit();
	}
	
	@Test
	public void testCantAccessGoogleCI(){
		user = new User(UserInfo.NOLINKCITEACHER);
		
		PGONewCILoginPage login = new PGONewCILoginPage(user);
		
		PGONewBuildingAdminDashboard admin = (PGONewBuildingAdminDashboard) login.login(user);
		user.customWait().until(ExpectedConditions.elementToBeClickable(admin.linkAccountsTab));
		PGONewLinkAccountsTab linkAccounts = admin.clickLinkAccountsTab();
		
		assertTrue("Asserts that the user cannot access Google account linking without linked account",
				linkAccounts.getElementText(linkAccounts.linkGoogleAccountError).equals("In order to link your class IDs to your Capstone account, please set up a Capstone account by clicking the ‘Link accounts’ button above."));
		
		linkAccounts.click(linkAccounts.adminButton);
		linkAccounts.click(linkAccounts.logout);
		linkAccounts.quit();
	}

	@Test
	public void testCantAccessWithMasterNoSubCI(){
		User pgoUser = new User(UserInfo.PGOONLYMASTER);

		PGONewCILoginPage login = new PGONewCILoginPage(pgoUser);
		
		PGONewCIStudentHomePage student = (PGONewCIStudentHomePage) login.login(pgoUser);
		
		assertTrue(student.verifyElementsHidden(student.getInitialVisibleElements()));
		
		pgoUser.setCredentials(UserInfo.PGNONLYMASTER);
		student = (PGONewCIStudentHomePage) login.login(pgoUser);
		
		assertTrue(student.verifyElementsHidden(student.getInitialVisibleElements()));
		pgoUser.quit();
	}
	
	//TODO: Turn back on when fixed
	/*
	@Test
	public void testCantAccessWithMasterNoSubPGO(){
		User ciUser = new User(UserInfo.CIONLYMASTER);
		
		PGOPages.LoginPage login = new PGOPages.LoginPage(ciUser);
		
		login.Login(ciUser);
		
		ciUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(login.loginErrorText));
		
		assertTrue(login.getDriver().findElement(login.loginErrorText).getText().equals("Oops! Incorrect Username Or Password. Please Try Again."));
		
		login.refresh();
		ciUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(login.goButton));
		
		ciUser.setCredentials(UserInfo.PGNONLYMASTER);
		login.Login(ciUser);
		ciUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(login.loginErrorText));
		
		assertTrue(login.getDriver().findElement(login.loginErrorText).getText().equals("Oops! Incorrect Username Or Password. Please Try Again."));
		
		ciUser.quit();
	}
	*/

	@Test
	public void testCantAccessWithMasterNoSubPGN(){
		User pgoUser = new User(UserInfo.PGOONLYMASTER);

		PGONewPGNLoginPage login = new PGONewPGNLoginPage(pgoUser);
		PGONewPGNStudentHomePage student = (PGONewPGNStudentHomePage) login.login(pgoUser);
		
		assertTrue(student.verifyElementsHidden(student.getInitialVisibleElements()));
		
		pgoUser.setCredentials(UserInfo.CIONLYMASTER);
		student = (PGONewPGNStudentHomePage) login.login(pgoUser);
		
		assertTrue(student.verifyElementsHidden(student.getInitialVisibleElements()));
		pgoUser.quit();
	}
	
	@Test
	public void testCantAccessWithDeletedMasterCI(){
		user = new User(UserInfo.DELETEDMASTER);
		
		PGONewCILoginPage login = new PGONewCILoginPage(user);
		PGONewCIStudentHomePage student = (PGONewCIStudentHomePage) login.login(user);
		
		assertTrue(student.verifyElementsHidden(student.getInitialVisibleElements()));
		
		user.quit();
	}
	
	@Test
	public void testCantAccessWithDeletedMasterPGO(){
		user = new User(UserInfo.DELETEDMASTER);
		
		PGONewLoginPage login = new PGONewLoginPage(user);
		login.Login(user);
		
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(login.loginErrorText));
		
		assertTrue(login.getDriver().findElement(login.loginErrorText).getText().equals("Oops! Incorrect Username Or Password. Please Try Again."));

		
		user.quit();	
	}

	@Test
	public void testCantAccessWithDeletedMasterPGN(){
		user = new User(UserInfo.DELETEDMASTER);
		
		PGONewPGNLoginPage login = new PGONewPGNLoginPage(user);
		PGONewPGNStudentHomePage student = (PGONewPGNStudentHomePage) login.login(user);
		
		assertTrue(student.verifyElementsHidden(student.getInitialVisibleElements()));
		
		user.quit();
	}

	@Test
	public void testSameAccountNumberDifferentMasterAccount(){
		user = new User(UserInfo.DIFFERENTTEACHER);
		
		PGONewCILoginPage login = new PGONewCILoginPage(user);
		
		PGONewBuildingAdminDashboard admin = (PGONewBuildingAdminDashboard) login.login(user);
		PGONewLinkAccountsTab linkAccounts = admin.clickLinkAccountsTab();
		
		String currentWindow = linkAccounts.getDriver().getWindowHandle();
		
		PGONewTeacherLogin teach = linkAccounts.clickLinkAccountsButton();
		teach.waitImplicitly(1);
		teach.SwitchToNewPage(currentWindow);
		user.customWait().until(ExpectedConditions.elementToBeClickable(teach.username));
		
		assertTrue("Asserts that user could navigate to Link Accounts Teacher Page",
				teach.verifyElementsVisibility(teach.initialVisibleElements));
		
		PGONewLinkAccountsPage linkAccountsPage = teach.login(user);
		
		assertTrue("Assert that the user can access a clean test account and not be linked to another account",
				linkAccountsPage.getPGOUsername().equals(""));
		
		user.quit();
	}
	
	@Test
	public void testPebbleGoMasterSQS(){
		User user = new User(UserInfo.MASTERACCOUNT);
		
		String sqsURL = "";
		
		if(user.getEnvironment().equals("qa")){
			sqsURL = "https://reactqa.pebblego.com?cappyducky&sqs="+UserInfo.MASTERACCOUNT.getSQS();
		}else{
			sqsURL = "https://"+user.getEnvironment()+".pebblego.com?cappyducky&sqs="+UserInfo.MASTERACCOUNT.getSQS();
		}
		
		LoginPage login = new LoginPage(user);
		
		login.getDriver().get(sqsURL);
		
		StudentHomePageNew student = new StudentHomePageNew(user);
		user.customWait().until(ExpectedConditions.elementToBeClickable(By.id("Animals")));
		
		assertTrue("Asserts that the user can use a master account sqs to navigate to the pebblego student homepage", 
				student.isElementPresent(By.id("Animals")));
		
		user.quit();
	}
	
	@Test
	public void testPebbleGoNextMasterSQS(){	
		User user = new User(UserInfo.MASTERACCOUNT);
		
		String sqsURL = "";
		
		if(user.getEnvironment().equals("reactqa")){
			sqsURL = "https://qa.pebblegonext.com?cappyducky&sqs=$2y$10$jXAExJDUZtc7TbKss/uQ6uwozBaNho6VX2EsmpXEaW5hgyMmhRmqq";
		}else if(user.getEnvironment().equals("site")){
			sqsURL = "https://www.pebblegonext.com?cappyducky&sqs=$2y$10$jXAExJDUZtc7TbKss/uQ6uwozBaNho6VX2EsmpXEaW5hgyMmhRmqq";
		}else{
			sqsURL = "https://"+user.getEnvironment()+".pebblegonext.com?cappyducky&sqs=$2y$10$jXAExJDUZtc7TbKss/uQ6uwozBaNho6VX2EsmpXEaW5hgyMmhRmqq";
		}
		
		PGONewPGNLoginPage login = new PGONewPGNLoginPage(user);
	
		login.getDriver().get(sqsURL);
		
		PGONewPGNStudentHomePage student = new PGONewPGNStudentHomePage(user);
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.goToScienceLink));
		
		assertTrue("Asserts that the user can use a master account sqs to navigate to the pebblegonext student homepage",
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		user.quit();
	}
	
	@Test
	public void testCIMasterSQS(){
		User user = new User(UserInfo.CIGARAGE);
		
		String sqsURL = "";
		
		if(user.getEnvironment().equals("reactqa")){
			sqsURL = "https://qa.mycapstonelibrary.com?cappyducky&sqs=$2y$10$jXAExJDUZtc7TbKss/uQ6uwozBaNho6VX2EsmpXEaW5hgyMmhRmqq";
		}else if(user.getEnvironment().equals("site")){
			sqsURL = "https://www.mycapstonelibrary.com?cappyducky&sqs=$2y$10$jXAExJDUZtc7TbKss/uQ6uwozBaNho6VX2EsmpXEaW5hgyMmhRmqq";
		}else{
			sqsURL = "https://"+user.getEnvironment()+".mycapstonelibrary.com?cappyducky&sqs=$2y$10$jXAExJDUZtc7TbKss/uQ6uwozBaNho6VX2EsmpXEaW5hgyMmhRmqq";
		}

		PGONewCILoginPage login = new PGONewCILoginPage(user);
	
		login.getDriver().get(sqsURL);
		
		PGONewCIStudentHomePage student = new PGONewCIStudentHomePage(user);
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.booklistsButton));
		
		assertTrue("Asserts that the user can use a master account sqs to navigate to the capstone interactive student homepage",
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		user.quit();
	}
}
