package MasterCrudTests;


import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import AdminResources.TeacherHomeScreen;
import BuildingAdmin.BuildingAdminDashboard;
import BuildingAdmin.LinkAccountsTab;
import CITests.CIPages.CILoginPage;
import CITests.CIPages.StudentHomePage;
import MasterAccount.LinkAccountsPage;
import MasterAccount.SorryModal;
import MasterAccount.TeacherLogin;
import PGOPages.LoginPage;
import SharedClasses.GooglePage;
import TrueUserTests.TrueUser.Retry;
import UserClasses.ProductType;
import UserClasses.User;
import UserClasses.UserInfo;

public class MasterAccountTests {
	
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
		TeacherLogin tLogin = new TeacherLogin(user);
		
		LinkAccountsPage link = tLogin.login(user);
		
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
		TeacherLogin tLogin = new TeacherLogin(user);
		
		LinkAccountsPage link = tLogin.login(user);
		link.goToPGNTab();
		
		user.setCredentials(UserInfo.PGNDONT);
		
		link.linkPGNAccount(user);
		//waiting until the button does not have deactive in class
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
		TeacherLogin tLogin = new TeacherLogin(user);
		
		LinkAccountsPage link = tLogin.login(user);
		link.goToCITab();
		user.setCredentials(UserInfo.CIDONT);
		link.linkCIAccount(user);
		//waiting until the button does not have deactive in class
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
	
		TeacherLogin tLogin = new TeacherLogin(user);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(tLogin.submit));
		
		LinkAccountsPage link = tLogin.login(user);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(link.pgoNext));
		
		link.enterPGOUsername("pgoonly");
		link.enterPGOPassword("test");
		
		//waiting until the button does not have deactive in class
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

		TeacherLogin tLogin = new TeacherLogin(user);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(tLogin.submit));
		
		LinkAccountsPage link = tLogin.login(user);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(link.pgoNext));
		link.goToPGNTab();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(link.pgnNext));
		
		link.enterPGNUsername("pgnstudent");
		link.enterPGNPassword("test");
		
		//waiting until the button does not have deactive in class
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
	
		TeacherLogin tLogin = new TeacherLogin(user);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(tLogin.submit));
		LinkAccountsPage link = tLogin.login(user);
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
		
		TeacherLogin tLogin = new TeacherLogin(user);
		LinkAccountsPage link = tLogin.login(user);
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

		CILoginPage cilogin = new CILoginPage(user);
		
		StudentHomePage student = (StudentHomePage) cilogin.login(user);
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(student.viewAllBooksButton));
		
		assertTrue("Asserts that the master account can navigate to the CI student homepage",
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		student.logout();
		student.quit();
	}
	
	@Test
	public void testLoginToPGNWithMaster(){
		user = new User(UserInfo.MASTERACCOUNT);
		PGNPages.LoginPage login = new PGNPages.LoginPage(user);
		PGNStudentResources.StudentHomePage student = (PGNStudentResources.StudentHomePage) login.login(user);
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(student.goToScienceLink));
		
		assertTrue("Asserts that the master account can navigate to the PGN student homepage",
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		student.header.logout();
		user.quit();
	}
	
	@Test
	public void testLoginToPGOWithMaster(){
		user = new User(UserInfo.MASTERACCOUNT);
		PGOPages.LoginPage login = new PGOPages.LoginPage(user);
		PGOStudentResources.StudentHomePage student = (PGOStudentResources.StudentHomePage) login.Login(user);
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(student.animalsModule));
		
		assertTrue("Asserts that the master account can navigate to the PGO student homepage",
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		student.clickLogOutButton();
		user.quit();
	}
	
	@Test
	public void testCantSwitchFromPGN(){
		user = new User(UserInfo.PGNSTUDENT);
	
		PGNPages.LoginPage login = new PGNPages.LoginPage(user);
		PGNStudentResources.StudentHomePage student = (PGNStudentResources.StudentHomePage) login.login(user);

		if(!student.getDriver().getCurrentUrl().contains("https")){
			student.getDriver().get(student.getDriver().getCurrentUrl().replace("http", "https"));
		}
		
		SorryModal sorry = (SorryModal) student.laToolbar.clickPGOLink(user.hasPGOSub());
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

		PGOPages.LoginPage login = new PGOPages.LoginPage(user);
		PGOStudentResources.StudentHomePage student = (PGOStudentResources.StudentHomePage) login.Login(user);
		
		
		SorryModal sorry = (SorryModal) student.laToolbar.clickPGNLink(user.hasPGNSub());
		user.customWait().until(ExpectedConditions.visibilityOf(sorry.getModalElement(sorry.takeMeBackLink)));
		
		assertTrue("Asserts that the correct product names are present on a sorry modal",
				sorry.getContinueLinkText().equals("Continue to PebbleGo Next") &&
				sorry.getTakeMeBackLinkText().equals("Take me back to Pebblego"));
		sorry.returnToProduct();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(sorry.takeMeBackLink));
		
		student.laToolbar.clickCILink(user.hasCISub());
		
		assertTrue("Asserts that the correct product names are present on a sorry modal",
				sorry.getContinueLinkText().equals("Continue to Capstone Interactive") &&
				sorry.getTakeMeBackLinkText().equals("Take me back to Pebblego"));

		sorry.returnToProduct();
		
		student.laToolbar.logout(ProductType.PGO);
		user.quit();
	}
	
	@Test
	public void testCantSwitchFromCI(){
		user = new User(UserInfo.CIONLY);

		CITests.CIPages.CILoginPage login = new CITests.CIPages.CILoginPage(user);
		CITests.CIPages.StudentHomePage student = (StudentHomePage) login.login(user);
		
		SorryModal sorry = (SorryModal) student.laToolbar.clickPGOLink(user.hasPGOSub());
		user.customWait().until(ExpectedConditions.visibilityOf(sorry.getModalElement(sorry.takeMeBackLink)));
		
		assertTrue("Asserts that the correct product names are present on a sorry modal",
				sorry.getContinueLinkText().equals("Continue to PebbleGo") &&
				sorry.getTakeMeBackLinkText().equals("Take me back to Capstone Interactive"));
		sorry.returnToProduct();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(sorry.takeMeBackLink));
		
		sorry = (SorryModal) student.laToolbar.clickPGNLink(user.hasPGNSub());
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
	
		
		CITests.CIPages.CILoginPage login = new CITests.CIPages.CILoginPage(user);
		CITests.CIPages.StudentHomePage ci = (StudentHomePage) login.login(user);
		user.customWait().until(ExpectedConditions.elementToBeClickable(ci.booklistsButton));
		
		assertTrue(ci.verifyElementsVisibility(ci.getInitialVisibleElements()));
		PGOStudentResources.StudentHomePage pgo =  (PGOStudentResources.StudentHomePage) ci.laToolbar.clickPGOLink(user.hasPGOSub());
		user.customWait().until(ExpectedConditions.elementToBeClickable(pgo.animalsModule));
		
		assertTrue(pgo.verifyElementsVisibility(pgo.getInitialVisibleElements()));
		PGNStudentResources.StudentHomePage pgn = (PGNStudentResources.StudentHomePage) pgo.laToolbar.clickPGNLink(user.hasPGNSub());
		user.customWait().until(ExpectedConditions.elementToBeClickable(pgn.goToScienceLink));
		
		assertTrue(pgn.verifyElementsVisibility(pgn.getInitialVisibleElements()));
		ci = (StudentHomePage) pgn.laToolbar.clickCILink(user.hasCISub());
		user.customWait().until(ExpectedConditions.elementToBeClickable(ci.booklistsButton));
		
		assertTrue(ci.verifyElementsVisibility(ci.getInitialVisibleElements()));
		pgn = (PGNStudentResources.StudentHomePage) ci.laToolbar.clickPGNLink(user.hasPGNSub());
		user.customWait().until(ExpectedConditions.elementToBeClickable(pgn.goToScienceLink));
		
		assertTrue(pgn.verifyElementsVisibility(pgn.getInitialVisibleElements()));
		pgo = (PGOStudentResources.StudentHomePage) pgn.laToolbar.clickPGOLink(user.hasPGOSub());
		user.customWait().until(ExpectedConditions.elementToBeClickable(pgo.animalsModule));
		
		assertTrue(pgo.verifyElementsVisibility(pgo.getInitialVisibleElements()));
		ci = (StudentHomePage) pgo.laToolbar.clickCILink(user.hasCISub());
		user.customWait().until(ExpectedConditions.elementToBeClickable(ci.booklistsButton));
		
		ci.laToolbar.logout(ProductType.CI);
		user.quit();
	}
	
	@Test
	public void testGoogleAccountPGO(){
		user = new User(UserInfo.MASTERGOOGLE);
	
		PGOPages.LoginPage login = new PGOPages.LoginPage(user);
		
		PGOStudentResources.StudentHomePage pgo = (PGOStudentResources.StudentHomePage) login.Login(user);
		user.customWait().until(ExpectedConditions.elementToBeClickable(pgo.animalsModule));
		
		assertTrue("Asserts User can use google login to log in to PGO", 
				pgo.verifyElementsVisibility(pgo.getInitialVisibleElements()));
		
		pgo.laToolbar.logout(ProductType.PGO);
		pgo.quit();
	}
	
	@Test
	public void testGoogleAccountPGN(){
		user = new User(UserInfo.MASTERGOOGLE);

		PGNPages.LoginPage login = new PGNPages.LoginPage(user);
		
		PGNStudentResources.StudentHomePage pgn = (PGNStudentResources.StudentHomePage) login.login(user);
		user.customWait().until(ExpectedConditions.elementToBeClickable(pgn.goToScienceLink));
		
		assertTrue("Asserts User can use google login to log in to PGN",
				pgn.verifyElementsVisibility(pgn.getInitialVisibleElements()));
		
		pgn.laToolbar.logout(ProductType.PGN);
		user.quit();
	}
	
	@Test
	public void testGoogleAccountCI(){
		user = new User(UserInfo.MASTERGOOGLE);

		CITests.CIPages.CILoginPage login = new CITests.CIPages.CILoginPage(user);
		
		CITests.CIPages.StudentHomePage ci = (CITests.CIPages.StudentHomePage) login.login(user);
		user.customWait().until(ExpectedConditions.elementToBeClickable(ci.booklistsButton));
		
		assertTrue("Asserts User can use google login to log in to CI", 
				ci.verifyElementsVisibility(ci.getInitialVisibleElements()));
		
		ci.laToolbar.logout(ProductType.CI);
		user.quit();
	}
	
	@Test
	public void testLinkAccountAccessibleCITeacher(){
		user = new User(UserInfo.CITEACHER);
		
		CITests.CIPages.CILoginPage login = new CITests.CIPages.CILoginPage(user);
		
		BuildingAdmin.BuildingAdminDashboard admin = (BuildingAdminDashboard) login.login(user);
		LinkAccountsTab linkAccounts = admin.clickLinkAccountsTab();
		
		String currentWindow = linkAccounts.getDriver().getWindowHandle();
		
		TeacherLogin teach = linkAccounts.clickLinkAccountsButton();
		//This wait is needed because the tab won't load correctly if you switch to it too quickly, not sure why
		teach.waitImplicitly(1);
		teach.SwitchToNewPage(currentWindow);
		user.customWait().until(ExpectedConditions.elementToBeClickable(teach.username));
		
		assertTrue("Asserts that user could navigate to Link Accounts Teacher Page",
				teach.verifyElementsVisibility(teach.initialVisibleElements));
		
		teach.closeCurrentWindow();	
		linkAccounts.getDriver().switchTo().window(currentWindow);
		currentWindow = linkAccounts.getDriver().getWindowHandle();
		
		GooglePage google = linkAccounts.clickLinkGoogleToAccountButton();
		user.customWait().until(ExpectedConditions.elementToBeClickable(google.email));
		
		By[] test = {google.email, google.nextButton};
		
		assertTrue("Asserts that user could navigate to Google Accounts Teacher Page",
				google.verifyElementsVisibility(test));
		
		google.closeCurrentWindow();
		user.quit();
	}
	
	@Test
	public void testLinkAccountsAccessiblePGOTeacher(){
		user = new User(UserInfo.GARAGETEACHER);
	
		PGOPages.LoginPage login = new PGOPages.LoginPage(user);
		
		TeacherHomeScreen teacher = (TeacherHomeScreen) login.Login(user);
		
		teacher.clickLinkAccountsTab();
		String currentWindow = teacher.getDriver().getWindowHandle();
		
		TeacherLogin linkAccounts = teacher.clickLinkAccountsButton();
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
		
		GooglePage google = teacher.clickGoogleAccountsButton();
		user.customWait().until(ExpectedConditions.elementToBeClickable(google.email));
		
		By[] test = {google.email, google.nextButton};
		
		assertTrue("Asserts that user could navigate to Link Accounts Teacher Page",
				google.verifyElementsVisibility(test));
		
		google.closeCurrentWindow();
		user.quit();
	}
	
	@Test
	public void testCantAccessGooglePGO(){
		user = new User(UserInfo.NOLINKPGOTEACHER);

		LoginPage login = new PGOPages.LoginPage(user);
		
		TeacherHomeScreen teacher = (TeacherHomeScreen) login.Login(user);
		user.customWait().until(ExpectedConditions.elementToBeClickable(teacher.linkAccountsTab));
		teacher.clickLinkAccountsTab();

		assertTrue("Asserts that the user cannot access Google account linking without linked account", 
				teacher.getElementText(teacher.getGoogleLinkError()).equals("In order to link your class IDs to your Capstone account, please set up a Capstone account by clicking the ‘Link accounts’ button above."));

		user.quit();
	}
	
	@Test
	public void testCantAccessGooglePGN(){
		user = new User(UserInfo.NOLINKPGNTEACHER);
		
		PGNPages.LoginPage login = new PGNPages.LoginPage(user);
		
		TeacherHomeScreen teacher = (TeacherHomeScreen) login.login(user);
		user.customWait().until(ExpectedConditions.elementToBeClickable(teacher.linkAccountsTab));
		teacher.clickLinkAccountsTab();

		assertTrue("Asserts that the user cannot access Google account linking without linked account", 
				teacher.getElementText(teacher.getGoogleLinkError()).equals("In order to link your class IDs to your Capstone account, please set up a Capstone account by clicking the ‘Link accounts’ button above."));

		teacher.quit();
	}
	
	@Test
	public void testCantAccessGoogleCI(){
		user = new User(UserInfo.NOLINKCITEACHER);
		
		CITests.CIPages.CILoginPage login = new CITests.CIPages.CILoginPage(user);
		
		BuildingAdmin.BuildingAdminDashboard admin = (BuildingAdminDashboard) login.login(user);
		user.customWait().until(ExpectedConditions.elementToBeClickable(admin.linkAccountsTab));
		LinkAccountsTab linkAccounts = admin.clickLinkAccountsTab();
		
		assertTrue("Asserts that the user cannot access Google account linking without linked account",
				linkAccounts.getElementText(linkAccounts.linkGoogleAccountError).equals("In order to link your class IDs to your Capstone account, please set up a Capstone account by clicking the ‘Link accounts’ button above."));
		
		linkAccounts.click(linkAccounts.adminButton);
		linkAccounts.click(linkAccounts.logout);
		linkAccounts.quit();
	}
	
	@Test
	public void testCantAccessWithMasterNoSubCI(){
		User pgoUser = new User(UserInfo.PGOONLYMASTER);

		CITests.CIPages.CILoginPage login = new CITests.CIPages.CILoginPage(pgoUser);
		
		CITests.CIPages.StudentHomePage student = (StudentHomePage) login.login(pgoUser);
		
		assertTrue(student.verifyElementsHidden(student.getInitialVisibleElements()));
		
		user.setCredentials(UserInfo.PGNONLYMASTER);
		student = (StudentHomePage) login.login(user);
		
		assertTrue(student.verifyElementsHidden(student.getInitialVisibleElements()));
		pgoUser.quit();
	}
	
	@Test
	public void testCantAccessWithMasterNoSubPGO(){
		User ciUser = new User(UserInfo.CIONLYMASTER);
		
		PGOPages.LoginPage login = new PGOPages.LoginPage(ciUser);
		
		PGOStudentResources.StudentHomePage student = (PGOStudentResources.StudentHomePage) login.Login(ciUser);
		
		assertTrue(student.verifyElementsHidden(student.getInitialVisibleElements()));
		
		user.setCredentials(UserInfo.PGNONLYMASTER);
		student = (PGOStudentResources.StudentHomePage) login.Login(user);
		
		assertTrue(student.verifyElementsHidden(student.getInitialVisibleElements()));
		ciUser.quit();
	}
	
	@Test
	public void testCantAccessWithMasterNoSubPGN(){
		User pgoUser = new User(UserInfo.PGOONLYMASTER);

		PGNPages.LoginPage login = new PGNPages.LoginPage(pgoUser);
		PGNStudentResources.StudentHomePage student = (PGNStudentResources.StudentHomePage) login.login(pgoUser);
		
		assertTrue(student.verifyElementsHidden(student.getInitialVisibleElements()));
		
		pgoUser.setCredentials(UserInfo.CIONLYMASTER);
		student = (PGNStudentResources.StudentHomePage) login.login(pgoUser);
		
		assertTrue(student.verifyElementsHidden(student.getInitialVisibleElements()));
		pgoUser.quit();
	}
	
	@Test
	public void testCantAccessWithDeletedMasterCI(){
		user = new User(UserInfo.DELETEDMASTER);
		
		CITests.CIPages.CILoginPage login = new CITests.CIPages.CILoginPage(user);
		CITests.CIPages.StudentHomePage student = (StudentHomePage) login.login(user);
		
		assertTrue(student.verifyElementsHidden(student.getInitialVisibleElements()));
		
		user.quit();
	}
	
	@Test
	public void testCantAccessWithDeletedMasterPGN(){
		user = new User(UserInfo.DELETEDMASTER);
		
		PGOPages.LoginPage login = new PGOPages.LoginPage(user);
		PGOStudentResources.StudentHomePage student = (PGOStudentResources.StudentHomePage) login.Login(user);
		assertTrue(student.verifyElementsHidden(student.getInitialVisibleElements()));
		
		user.quit();	
	}
	
	@Test
	public void testCantAccessWithDeletedMasterPGO(){
		user = new User(UserInfo.DELETEDMASTER);
		
		PGNPages.LoginPage login = new PGNPages.LoginPage(user);
		PGNStudentResources.StudentHomePage student = (PGNStudentResources.StudentHomePage) login.login(user);
		
		assertTrue(student.verifyElementsHidden(student.getInitialVisibleElements()));
		
		user.quit();
	}
	
	@Test
	public void testSameAccountNumberDifferentMasterAccount(){
		user = new User(UserInfo.DIFFERENTTEACHER);
		
		CITests.CIPages.CILoginPage login = new CITests.CIPages.CILoginPage(user, "staging");
		
		BuildingAdmin.BuildingAdminDashboard admin = (BuildingAdminDashboard) login.login(user);
		LinkAccountsTab linkAccounts = admin.clickLinkAccountsTab();
		
		String currentWindow = linkAccounts.getDriver().getWindowHandle();
		
		TeacherLogin teach = linkAccounts.clickLinkAccountsButton();
		teach.waitImplicitly(1);
		teach.SwitchToNewPage(currentWindow);
		user.customWait().until(ExpectedConditions.elementToBeClickable(teach.username));
		
		assertTrue("Asserts that user could navigate to Link Accounts Teacher Page",
				teach.verifyElementsVisibility(teach.initialVisibleElements));
		
		LinkAccountsPage linkAccountsPage = teach.login(user);
		
		assertTrue("Assert that the user can access a clean test account and not be linked to another account",
				linkAccountsPage.getPGOUsername().equals(""));
		
		user.quit();
	}
	
	@Test
	public void testPebbleGoMasterSQS(){
		User user = new User(UserInfo.GARAGESTUDENT);
		String sqsURL = "https://"+user.getEnvironment()+".pebblego.com?cappyducky&sqs=$2y$10$jXAExJDUZtc7TbKss/uQ6uwozBaNho6VX2EsmpXEaW5hgyMmhRmqq";
		
		PGOPages.LoginPage login = new PGOPages.LoginPage(user);
		
		login.getDriver().get(sqsURL);
		
		PGOStudentResources.PGONewStudentHomePage student = new PGOStudentResources.PGONewStudentHomePage(login.getDriver());
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.animalsModule));
		
		assertTrue("Asserts that the user can use a master account sqs to navigate to the pebblego student homepage", 
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		user.quit();
	}
	
	@Test
	public void testPebbleGoNextMasterSQS(){	
		User user = new User(UserInfo.GARAGESTUDENT);
		String sqsURL = "https://"+user.getEnvironment()+".pebblegonext.com?cappyducky&sqs=$2y$10$jXAExJDUZtc7TbKss/uQ6uwozBaNho6VX2EsmpXEaW5hgyMmhRmqq";
		
		PGNPages.LoginPage login = new PGNPages.LoginPage(user);
	
		login.getDriver().get(sqsURL);
		
		PGNStudentResources.StudentHomePage student = new PGNStudentResources.StudentHomePage(login.getDriver());
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.goToScienceLink));
		
		assertTrue("Asserts that the user can use a master account sqs to navigate to the pebblegonext student homepage",
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		user.quit();
	}
	
	@Test
	public void testCIMasterSQS(){
		User user = new User(UserInfo.CIGARAGE);
		String sqsURL = "https://"+user.getEnvironment()+".mycapstonelibrary.com?cappyducky&sqs=$2y$10$jXAExJDUZtc7TbKss/uQ6uwozBaNho6VX2EsmpXEaW5hgyMmhRmqq";
		
		
		CITests.CIPages.CILoginPage login = new CITests.CIPages.CILoginPage(user);
	
		login.getDriver().get(sqsURL);
		
		CITests.CIPages.StudentHomePage student = new CITests.CIPages.StudentHomePage(login.getDriver());
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.booklistsButton));
		
		assertTrue("Asserts that the user can use a master account sqs to navigate to the capstone interactive student homepage",
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		user.quit();
	}
}
