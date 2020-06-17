package Tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import BuildingAdmin.BuildingAdminDashboard;
import BuildingAdmin.BuildingAdminReports;
import BuildingAdmin.BuildingAdminStudentDisplayOptions;
import BuildingAdmin.CreateBookPosterPage;
import BuildingAdmin.EditMyProfileBuildingAdminPage;
import CITests.CIPages.CILoginPage;
import CITests.CIPages.StudentHomePage;
import SharedClasses.ThirdPartyPage;
import SuperAdmin.BuildingListPage;
import SuperAdmin.CreateOrderPage;
import SuperAdmin.EditBuildingPage;
import SuperAdmin.OrderHistoryPage;
import SuperAdmin.SuperAdminHomePage;
import SuperAdmin.SuperAdminLoginPage;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.User;
import UserClasses.UserInfo;

public class SuperAdminTests{
	static CILoginPage loginPage;
	static String textFile = "testRepairedData.csv";
	public static User adminUser;
	public static User studentUser;
	public static User buildingAdminUser;
	public static BuildingAdminDashboard admin;
	
	@BeforeClass
	public static void executeBefore(){		
		
	}
	
	@AfterClass
	public static void executeAfter(){
		try{
			adminUser.quit();
		}catch(Exception e){
			
		}
		try{
			studentUser.quit();
		}catch(Exception e){
			
		}
		try{
			buildingAdminUser.quit();
		}catch(Exception e){
			
		}
	}
	
	
	@Rule
	public TestWatcher restart = new TestWatcher(){
		@Override
		public void failed(Throwable e, Description description){
			try{
				adminUser.quit();
			}catch(Exception f){
				
			}
			try{
				studentUser.quit();
			}catch(Exception f){
				
			}
			try{
				buildingAdminUser.quit();
			}catch(Exception f){
				
			}
		}
	};
	
	@Rule
	public Retry retry = new Retry(3);
	
	
	@Test
	@Category(SmokeTests.class)
	public void testEulaCheckBuildingAdmin() throws InterruptedException{
		adminUser =  new User(UserInfo.CIDONTTEACHER);
		loginPage = new CILoginPage(adminUser);
		loginPage.login(adminUser);
		String docsWindow = loginPage.getDriver().getWindowHandle();
		
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.adminEula.EulaLink));
		//loginPage.waitImplicitly(2);
		loginPage.adminEula.ClickEULALink();
		
		String correct = "";
		if(adminUser.getEnvironment().equals("qa")){
			correct = "https://ciassets-qa.mycapstonelibrary.com/pdf/Eula-1.0.0.pdf";
		} else if(adminUser.getEnvironment().equals("staging")){
			correct = "https://ciassets-staging.mycapstonelibrary.com/pdf/Eula-1.0.0.pdf";
		} else{
			correct = "https://assets.mycapstonelibrary.com/pdf/Eula-1.0.0.pdf";
		}
		
		ThirdPartyPage tPP = new ThirdPartyPage(loginPage.getDriver());
		
		if(adminUser.isHeadless()){
			assertTrue("Assert that the EULA link redirects to the EULA pdf page",
					loginPage.getDriver().findElement(loginPage.adminEula.EulaLink).getAttribute("href").equals(correct));
			loginPage.closeAllOtherTabsBut(docsWindow);
		}
		else{
			assertTrue("Assert that the EULA link redirects to the EULA pdf page",
					tPP.verifyNewWindow(docsWindow, correct));	
		}
	
		loginPage.goToHomePage(adminUser.getEnvironment());
		adminUser.quit();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testEulaCantAccessBySQS(){
		adminUser =  new User(UserInfo.CITEACHER);	
		loginPage = new CILoginPage(adminUser);
		
		loginPage.getDriver().get("https://"+adminUser.getEnvironment()+".mycapstonelibrary.com/login/?sqs=50c3fbf6a1814a469b579bac3efcd4872a7f6e46034eada1541fc79f3fba131e");
		//loginPage.refreshEULAModals();
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.studentEula.closeButton));
		//loginPage.waitImplicitly(3);
		
		assertTrue("Asserts that the student can see the close button on the Eula modal when accessing with SQS string",
				loginPage.isElementPresent(loginPage.studentEula.closeButton));
		
		loginPage.studentEula.clickClose();
		
		assertTrue("Asserts that the student can not see the close button on the Eula modal after it is clicked",
				!loginPage.isElementPresent(loginPage.studentEula.closeButton));
		adminUser.quit();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testEulaCheckStudent(){
		studentUser =  new User(UserInfo.CIDONT);
		loginPage = new CILoginPage(studentUser);
		loginPage.login(studentUser);

		assertTrue("Asserts that the student can see the close button on the EULA modal when the student tries to log in",
				loginPage.isElementPresent(loginPage.studentEula.closeButton));
		
		loginPage.studentEula.clickClose();
		
		assertTrue("Asserts that the student can not see the close button on the EULA modal after it is clicked",
				!loginPage.isElementPresent(loginPage.studentEula.closeButton));
		studentUser.quit();
	}
	
	//Never Accept
	@Test
	@Category(SmokeTests.class)
	public void testEulaCheckAccountAdmin() throws InterruptedException{
		adminUser =  new User(UserInfo.CIDONTTEACHER);
		loginPage = new CILoginPage(adminUser);
		loginPage.login(adminUser);
		
		String docsWindow = loginPage.getDriver().getWindowHandle();

		ThirdPartyPage tPP = loginPage.clickLink(loginPage.adminEula.EulaLink);
		
		String correct = "";
		if(adminUser.getEnvironment().equals("qa")){
			correct = "https://ciassets-qa.mycapstonelibrary.com/pdf/Eula-1.0.0.pdf";
		} else if(adminUser.getEnvironment().equals("staging")){
			correct = "https://ciassets-staging.mycapstonelibrary.com/pdf/Eula-1.0.0.pdf";
		} else{
			correct = "https://assets.mycapstonelibrary.com/pdf/Eula-1.0.0.pdf";
		}
		
		if(adminUser.isHeadless()){
			assertTrue("Asserts that the Admin can see and be redirected to the EULA page after clicking the Eula link",
					loginPage.getDriver().findElement(loginPage.adminEula.EulaLink).getAttribute("href").equals(correct));
			loginPage.closeAllOtherTabsBut(docsWindow);
		}
		else{
			assertTrue("Asserts that the Admin can see and be redirected to the EULA page after clicking the Eula link",
					tPP.verifyNewWindow(docsWindow, correct));	
		}
	
		loginPage.goToHomePage(adminUser.getEnvironment());
		adminUser.quit();
	}
	
	@Test
	public void testCannotBuyBookAlreadyOwned(){
		adminUser =  new User(UserInfo.CISUPERADMIN);
		loginPage = new CILoginPage(adminUser);
		studentUser = new User(UserInfo.DELETEBUILDINGSTUDENT);
		
		CILoginPage studentLogin = new CILoginPage(studentUser);
	
		StudentHomePage student = (StudentHomePage) studentLogin.login(studentUser);
		adminUser.customWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		student.waitImplicitly(2);
		
		assertTrue("Asserts that the user can see 100th Day of School", student.isBookPresentByTitle("100th Day of School"));
		
		SuperAdminLoginPage superAdminLogin = new SuperAdminLoginPage(adminUser);
		SuperAdminHomePage superAdmin = superAdminLogin.login(adminUser);
		
		OrderHistoryPage orderHistory = superAdmin.toolbar.clickOrderHistoryTab();
				
		CreateOrderPage createOrder = orderHistory.clickNewOrder();
		createOrder.click(createOrder.accountDropdown);
		createOrder.sendKeysToElement("testguy", createOrder.accountDropdown);
		createOrder.sendKeysToElement("Library", createOrder.licenseTypeDropdown);
		createOrder.sendKeysToElement("333", createOrder.orderNumber);
		createOrder.click(createOrder.nextButton);
		
		createOrder.AddBuildingByName("DeleteTestBuilding");
		createOrder.click(createOrder.nextButton);
		
		createOrder.addBookByTitle("100th Day of School");
		
		//clicks next button on stage 3
		createOrder.click(createOrder.chooseBooksNextButton);
		
		orderHistory = createOrder.finishOrder();
		
		student.refresh();
		adminUser.customWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		
		assertTrue("Asserts that the student cannot see the book because it couldn't be purchased", 
				student.isBookPresentByTitle("100th Day of School"));
		
		assertTrue("Asserts that the notification modal is present and that the user cannot re-order a book", 
				orderHistory.getDriver().findElement(createOrder.notificationText).getText().equals("Notifications: \n"
						+ "\"Duplicate perpetual license for the building.\",\"100th Day of School\",\"978-1-5158-2567-8\",\"DeleteTestBuilding\""
								+ "\nYou can't continue with the order, because some of the books are already owned with perpetual license."));
		
		createOrder.click(createOrder.notificationCloseButton);
		
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(createOrder.toolbar.dashboardButton));
		createOrder.click(createOrder.toolbar.dashboardButton);
		
		adminUser.quit();
		studentUser.quit();
	}
	
	@Test
	public void testEULAAcceptanceOverrideNotPresent(){
		adminUser = new User(UserInfo.CISUPERADMIN);
		
		SuperAdminLoginPage superAdminLogin = new SuperAdminLoginPage(adminUser);
		
		SuperAdminHomePage superAdmin = superAdminLogin.login(adminUser);
		
		OrderHistoryPage orderHistory = superAdmin.toolbar.clickOrderHistoryTab();
				
		CreateOrderPage createOrder = orderHistory.clickNewOrder();
		assertTrue("Assert that the Eula Acceptance Override Checkbox is not visible anymore when ordering new books",
				!createOrder.isElementPresent(createOrder.eulaAcceptanceOverrideCheckbox));
		createOrder.closeCurrentWindow();
		adminUser.quit();
	}

	@Test
	public void testOrderBooksDeleteBooks(){
		adminUser = new User(UserInfo.CISUPERADMIN);
		adminUser.setCredentials(UserInfo.CISUPERADMIN);
		SuperAdminLoginPage superAdminLogin = new SuperAdminLoginPage(adminUser);
		
		studentUser = new User(UserInfo.DELETEBUILDINGSTUDENT);
		CILoginPage studentLogin = new CILoginPage(studentUser);
		StudentHomePage student = (StudentHomePage) studentLogin.login(studentUser);
		
		SuperAdminHomePage superAdmin = superAdminLogin.login(adminUser);
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(superAdmin.toolbar.orderHistoryButton));		
		
		OrderHistoryPage orderHistory = superAdmin.toolbar.clickOrderHistoryTab();
		
		CreateOrderPage createOrder = orderHistory.clickNewOrder();
		createOrder.click(createOrder.accountDropdown);
		createOrder.sendKeysToElement("testguy", createOrder.accountDropdown);
		createOrder.sendKeysToElement("Library", createOrder.licenseTypeDropdown);
		createOrder.sendKeysToElement("333", createOrder.orderNumber);
		createOrder.click(createOrder.nextButton);
		
		createOrder.AddBuildingByName("DeleteTestBuilding");
		createOrder.click(createOrder.nextButton);
		
		createOrder.addBookByTitle("10 Little Kittens");
		
		//clicks next button on stage 3
		createOrder.click(createOrder.chooseBooksNextButton);
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(createOrder.nextButton));
		
		orderHistory = createOrder.finishOrder();
		
		student.refresh();
		adminUser.customWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		
		assertTrue("Asserts that the student cannot see the book because it couldn't be purchased", 
				student.isBookPresentByTitle("10 Little Kittens"));
		
		orderHistory.sendKeysToElement("testguy", orderHistory.accountDropdown);
		orderHistory.click(orderHistory.applyFilterButton);
		orderHistory.sendKeysToElement("DeleteTestBuilding", orderHistory.buildingDropdown);
		orderHistory.click(orderHistory.applyFilterButton);
		
		orderHistory.cancelOrderByNumber("333");	
		
		student.refresh();
		adminUser.customWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		
		assertTrue("Asserts that the student cannot see the book because it couldn't be purchased", 
				!student.isBookPresentByTitle("10 Little Kittens"));
		
		studentUser.quit();
		orderHistory.closeCurrentWindow();
		adminUser.quit();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testAdminFilterToggles(){
		adminUser = new User(UserInfo.CITEACHER);
		CILoginPage buildingAdminLogin = new CILoginPage(adminUser);
		BuildingAdminDashboard admin = (BuildingAdminDashboard) buildingAdminLogin.login(adminUser);
		
		BuildingAdminStudentDisplayOptions displayOptions = admin.clickStudentDisplayOptions();
			
		displayOptions.toggleGradeRange();
		displayOptions.toggleGuidedReadingLevel();
		displayOptions.toggleLexile();
		displayOptions.toggleATOS();
		
		displayOptions.clickUpdateButton();
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(displayOptions.updateMessage));
		//String test = displayOptions.getElementText(displayOptions.updateMessage);
		
		assertTrue("Asserts that the update message appears when an Admin changes a Filter toggle",
				displayOptions.getElementText(displayOptions.updateMessage).equals("Data successfully updated!"));
	
		displayOptions.turnOnAllOptions();
		displayOptions.clickUpdateButton();
		loginPage = displayOptions.logout();
		adminUser.quit();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testTurnAllFiltersOff(){
		studentUser = new User(UserInfo.CIGARAGE);
		loginPage = new CILoginPage(studentUser);
		
		StudentHomePage studentHome = (StudentHomePage) loginPage.login(studentUser);
		
		adminUser = new User(UserInfo.CITEACHER);
		CILoginPage adminLogin = new CILoginPage(adminUser);
		BuildingAdminDashboard admin =  (BuildingAdminDashboard) adminLogin.login(adminUser);
		BuildingAdminStudentDisplayOptions displayOptions = admin.clickStudentDisplayOptions();
		
		if(!displayOptions.isGuidedReadingFilterOn()){
			displayOptions.toggleGuidedReadingLevel();
		}
		
		if(!displayOptions.isGradeRangeFilterOn()){
			displayOptions.toggleGradeRange();
		}
		
		if(!displayOptions.isLexileFilterOn()){
			displayOptions.toggleLexile();
		}
		
		if(!displayOptions.isATOSLevelFilterOn()){
			displayOptions.toggleATOS();
		}
		
		displayOptions.clickUpdateButton();
	
		studentHome.refresh();		
		adminUser.customWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		assertTrue("Asserts that the student cannot see any filters once all filters are turned off",
				studentHome.isElementPresent(studentHome.filters.guidedReadingToggle)
				&& studentHome.isElementPresent(studentHome.filters.gradeRangeToggle)
				&& studentHome.isElementPresent(studentHome.filters.aTOSToggle)
				&& studentHome.isElementPresent(studentHome.filters.lexileRangeToggle));
		
		displayOptions.toggleATOS();
		displayOptions.clickUpdateButton();
		
		studentHome.refresh();
		adminUser.customWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		assertTrue("Asserts that the student cannot see any filters once all filters are turned off",
				studentHome.isElementPresent(studentHome.filters.guidedReadingToggle)
				&& studentHome.isElementPresent(studentHome.filters.gradeRangeToggle)
				&& !studentHome.isElementPresent(studentHome.filters.aTOSToggle)
				&& studentHome.isElementPresent(studentHome.filters.lexileRangeToggle));
		
		displayOptions.toggleGuidedReadingLevel();
		displayOptions.toggleGradeRange();
		displayOptions.toggleLexile();
		displayOptions.clickUpdateButton();
		
		studentHome.refresh();
		adminUser.customWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		assertTrue("Asserts that the student cannot see any filters once all filters are turned off",
				!studentHome.isElementPresent(studentHome.filters.guidedReadingToggle)
				&& !studentHome.isElementPresent(studentHome.filters.gradeRangeToggle)
				&& !studentHome.isElementPresent(studentHome.filters.aTOSToggle)
				&& !studentHome.isElementPresent(studentHome.filters.lexileRangeToggle));
	
		displayOptions.toggleGuidedReadingLevel();
		displayOptions.toggleGradeRange();
		displayOptions.toggleATOS();
		displayOptions.toggleLexile();
		displayOptions.clickUpdateButton();
		
		studentUser.quit();
		
		loginPage = displayOptions.logout();
		adminUser.quit();
	}
	
	@Test
	public void testEditAdminUsernameInSuperAdmin(){
		adminUser = new User(UserInfo.CISUPERADMIN);
		SuperAdminLoginPage superAdminLogin = new SuperAdminLoginPage(adminUser);
		String newAdminUsername = "BADSUPERADMINEDIT";
		
		SuperAdminHomePage superAdmin = superAdminLogin.login(adminUser);
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(superAdmin.toolbar.buildingListButton));
		
		BuildingListPage buildingList = superAdmin.toolbar.clickBuildingListTab();
		
		buildingList.searchForBuilding("Edit Account");
		buildingList.clickFilterButton();
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(buildingList.applyFilterButton));
		
		
		EditBuildingPage editBuilding = buildingList.editBuilding();
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(editBuilding.editAdminPassword));
		
		editBuilding.editAdminUsername(newAdminUsername);
		editBuilding.clickSave();
		
		User editedAdminUser = new User(UserInfo.EDITACCOUNTADMIN);
		String oldAdminUsername = editedAdminUser.getUsername();
		
		editedAdminUser.setUsername(newAdminUsername);
		CILoginPage studentLogin = new CILoginPage(editedAdminUser);
		BuildingAdminDashboard newAdmin = (BuildingAdminDashboard) studentLogin.login(editedAdminUser);
		
		assertTrue("Asserts that the admin user can log in with a new Username",
				newAdmin.verifyElementsVisibility(newAdmin.initialVisibleElements));
		
		newAdmin.closeCurrentWindow();
		
		buildingList.searchForBuilding("Edit Account");
		buildingList.clickFilterButton();
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(buildingList.applyFilterButton));
		
		
		editBuilding = buildingList.editBuilding();
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(editBuilding.editAdminPassword));
		
		editBuilding.editAdminUsername(oldAdminUsername);
		editBuilding.clickSave();
		
		editedAdminUser.quit();
		adminUser.quit();
	}
	
	@Test
	public void testEditAdminPasswordInSuperAdmin(){
		adminUser = new User(UserInfo.CISUPERADMIN);
		SuperAdminLoginPage superAdminLogin = new SuperAdminLoginPage(adminUser);
		String newAdminPassword = "WWdLNWh2ERROR";
		
		SuperAdminHomePage superAdmin = superAdminLogin.login(adminUser);
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(superAdmin.toolbar.buildingListButton));
		
		BuildingListPage buildingList = superAdmin.toolbar.clickBuildingListTab();
		
		buildingList.searchForBuilding("Edit Account");
		buildingList.clickFilterButton();
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(buildingList.applyFilterButton));
		
		
		EditBuildingPage editBuilding = buildingList.editBuilding();
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(editBuilding.editAdminPassword));
		
		editBuilding.editAdminPassword(newAdminPassword);
		editBuilding.clickSave();
		
		buildingAdminUser = new User(UserInfo.EDITACCOUNTADMIN);
		String oldAdminPassword = buildingAdminUser.getPassword();
		
		buildingAdminUser.setPassword(newAdminPassword);
		CILoginPage studentLogin = new CILoginPage(buildingAdminUser);
		BuildingAdminDashboard newAdmin = (BuildingAdminDashboard) studentLogin.login(buildingAdminUser);
		
		assertTrue("Asserts that the admin user can log in with a new Username",
				newAdmin.verifyElementsVisibility(newAdmin.initialVisibleElements));
		
		newAdmin.closeCurrentWindow();
		
		buildingList.searchForBuilding("Edit Account");
		buildingList.clickFilterButton();
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(buildingList.applyFilterButton));
		
		
		editBuilding = buildingList.editBuilding();
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(editBuilding.editAdminPassword));
		
		editBuilding.editAdminPassword(oldAdminPassword);
		editBuilding.clickSave();
		
		buildingAdminUser.quit();
		adminUser.quit();
	}
	
	@Test
	public void testEditAdminUsernameAndPasswordFromSuperAdmin(){
		adminUser = new User(UserInfo.CISUPERADMIN);
		adminUser.setCredentials(UserInfo.CISUPERADMIN);
		SuperAdminLoginPage superAdminLogin = new SuperAdminLoginPage(adminUser);
		String newAdminPassword = "WWdLNWh2ERROR";
		String newAdminUsername = "BADSUPERADMINEDIT";
		
		SuperAdminHomePage superAdmin = superAdminLogin.login(adminUser);
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(superAdmin.toolbar.buildingListButton));
		
		BuildingListPage buildingList = superAdmin.toolbar.clickBuildingListTab();
		
		buildingList.searchForBuilding("Edit Account");
		buildingList.clickFilterButton();
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(buildingList.applyFilterButton));
		
		
		EditBuildingPage editBuilding = buildingList.editBuilding();
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(editBuilding.editAdminPassword));
		
		editBuilding.editAdminPassword(newAdminPassword);
		editBuilding.editAdminUsername(newAdminUsername);
		editBuilding.clickSave();
		
		buildingAdminUser = new User(UserInfo.EDITACCOUNTADMIN);
		String oldAdminPassword = buildingAdminUser.getPassword();
		String oldAdminUsername = buildingAdminUser.getUsername();
		
		buildingAdminUser.setPassword(newAdminPassword);
		buildingAdminUser.setUsername(newAdminUsername);
		CILoginPage studentLogin = new CILoginPage(buildingAdminUser);
		BuildingAdminDashboard newAdmin = (BuildingAdminDashboard) studentLogin.login(buildingAdminUser);
		
		assertTrue("Asserts that the admin user can log in with a new Username",
				newAdmin.verifyElementsVisibility(newAdmin.initialVisibleElements));
		
		newAdmin.closeCurrentWindow();
		
		buildingList.searchForBuilding("Edit Account");
		buildingList.clickFilterButton();
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(buildingList.applyFilterButton));
		
		
		editBuilding = buildingList.editBuilding();
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(editBuilding.editAdminPassword));
		
		editBuilding.editAdminPassword(oldAdminPassword);
		editBuilding.editAdminUsername(oldAdminUsername);
		editBuilding.clickSave();
		
		buildingAdminUser.quit();
		adminUser.quit();
	}
	
	@Test
	public void testEditStudentUsernameInSuperAdmin(){
		adminUser = new User(UserInfo.CISUPERADMIN);
		SuperAdminLoginPage superAdminLogin = new SuperAdminLoginPage(adminUser);
		String newStudentUsername = "BADSTUDENTUSERNAMEEDITTEST";
		
		SuperAdminHomePage superAdmin = superAdminLogin.login(adminUser);
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(superAdmin.toolbar.buildingListButton));
		
		BuildingListPage buildingList = superAdmin.toolbar.clickBuildingListTab();
		
		buildingList.searchForBuilding("Edit Account");
		buildingList.clickFilterButton();
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(buildingList.applyFilterButton));
		
		
		EditBuildingPage editBuilding = buildingList.editBuilding();
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(editBuilding.editAdminPassword));
		
		editBuilding.editStudentUsername(newStudentUsername);
		editBuilding.clickSave();
		
		buildingAdminUser = new User(UserInfo.EDITACCOUNTSTUDENT);
		String oldStudentUsername = buildingAdminUser.getUsername();
		
		buildingAdminUser.setUsername(newStudentUsername);
		CILoginPage studentLogin = new CILoginPage(buildingAdminUser);
		StudentHomePage studentHome = (StudentHomePage) studentLogin.login(buildingAdminUser);
		
		assertTrue("Asserts that the admin user can log in with a new Username",
				studentHome.verifyElementsVisibility(studentHome.initialVisibleElements));
		
		studentHome.closeCurrentWindow();
		
		buildingList.searchForBuilding("Edit Account");
		buildingList.clickFilterButton();
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(buildingList.applyFilterButton));
		
		
		editBuilding = buildingList.editBuilding();
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(editBuilding.editAdminPassword));
		
		editBuilding.editStudentUsername(oldStudentUsername);
		editBuilding.clickSave();
		
		buildingAdminUser.quit();
		adminUser.quit();
	}
	
	@Test
	public void testEditStudentPasswordInSuperAdmin(){
		adminUser = new User(UserInfo.CISUPERADMIN);
		SuperAdminLoginPage superAdminLogin = new SuperAdminLoginPage(adminUser);
		String newStudentPassword = "WWdLNWh2ERROR";
		
		SuperAdminHomePage superAdmin = superAdminLogin.login(adminUser);
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(superAdmin.toolbar.buildingListButton));
		
		BuildingListPage buildingList = superAdmin.toolbar.clickBuildingListTab();
		
		buildingList.searchForBuilding("Edit Account");
		buildingList.clickFilterButton();
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(buildingList.applyFilterButton));
		
		
		EditBuildingPage editBuilding = buildingList.editBuilding();
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(editBuilding.editAdminPassword));
		
		editBuilding.editStudentPassword(newStudentPassword);
		editBuilding.clickSave();
		
		studentUser = new User(UserInfo.EDITACCOUNTSTUDENT);
		String oldStudentPassword = studentUser.getPassword();
		
		studentUser.setPassword(newStudentPassword);
		
		CILoginPage studentLogin = new CILoginPage(studentUser);
		StudentHomePage studentHome = (StudentHomePage) studentLogin.login(studentUser);
		
		assertTrue("Asserts that the admin user can log in with a new Username",
				studentHome.verifyElementsVisibility(studentHome.initialVisibleElements));
		
		studentHome.closeCurrentWindow();
		
		buildingList.searchForBuilding("Edit Account");
		buildingList.clickFilterButton();
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(buildingList.applyFilterButton));
		
		
		editBuilding = buildingList.editBuilding();
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(editBuilding.editAdminPassword));

		editBuilding.editStudentPassword(oldStudentPassword);
		editBuilding.clickSave();
		
		studentUser.quit();
		adminUser.quit();
	}
	
	@Test
	public void testEditStudentUsernameAndPasswordInSuperAdmin(){
		adminUser = new User(UserInfo.CISUPERADMIN);
		SuperAdminLoginPage superAdminLogin = new SuperAdminLoginPage(adminUser);
		String newStudentPassword = "WWdLNWh2ERROR";
		String newStudentUsername = "BADSTUDENTUSERNAMEEDITTEST";
		
		SuperAdminHomePage superAdmin = superAdminLogin.login(adminUser);
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(superAdmin.toolbar.buildingListButton));
		
		BuildingListPage buildingList = superAdmin.toolbar.clickBuildingListTab();
		
		buildingList.searchForBuilding("Edit Account");
		buildingList.clickFilterButton();
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(buildingList.applyFilterButton));
		
		
		EditBuildingPage editBuilding = buildingList.editBuilding();
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(editBuilding.editAdminPassword));
		
		editBuilding.editStudentUsername(newStudentUsername);
		editBuilding.editStudentPassword(newStudentPassword);
		editBuilding.clickSave();
		
		studentUser = new User(UserInfo.EDITACCOUNTSTUDENT);
		String oldStudentUsername = studentUser.getUsername();
		String oldStudentPassword = studentUser.getPassword();
		
		studentUser.setUsername(newStudentUsername);
		studentUser.setPassword(newStudentPassword);
		
		CILoginPage studentLogin = new CILoginPage(studentUser);
		StudentHomePage studentHome = (StudentHomePage) studentLogin.login(studentUser);
		
		assertTrue("Asserts that the admin user can log in with a new Username",
				studentHome.verifyElementsVisibility(studentHome.initialVisibleElements));
		
		studentHome.closeCurrentWindow();
		
		buildingList.searchForBuilding("Edit Account");
		buildingList.clickFilterButton();
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(buildingList.applyFilterButton));
		
		
		editBuilding = buildingList.editBuilding();
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(editBuilding.editAdminPassword));
		
		editBuilding.editStudentUsername(oldStudentUsername);
		editBuilding.editStudentPassword(oldStudentPassword);
		editBuilding.clickSave();
		
		studentUser.quit();
		adminUser.quit();
	}
	
	@Test
	public void testDisableBuildingAffectsStudentInSuperAdmin(){
		adminUser = new User(UserInfo.CISUPERADMIN);
		SuperAdminLoginPage superAdminLogin = new SuperAdminLoginPage(adminUser);
		
		SuperAdminHomePage superAdmin = superAdminLogin.login(adminUser);
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(superAdmin.toolbar.buildingListButton));
		
		BuildingListPage buildingList = superAdmin.toolbar.clickBuildingListTab();
		
		buildingList.searchForBuilding("Edit Account");
		buildingList.clickFilterButton();
		buildingList.disableBuilding();
		
		studentUser = new User(UserInfo.EDITACCOUNTSTUDENT);
		CILoginPage studentLogin = new CILoginPage(studentUser);
		StudentHomePage studentHome = (StudentHomePage) studentLogin.login(studentUser);
		
		assertTrue("Asserts that the admin user can log in with a new Username",
				studentHome.verifyElementsHidden(studentHome.initialVisibleElements));
		
		buildingList.enableBuilding();
		
		studentHome = (StudentHomePage) studentLogin.login(studentUser);
		
		assertTrue("Asserts that the admin user can log in with a new Username",
				studentHome.verifyElementsVisibility(studentHome.initialVisibleElements));
		
		studentUser.quit();
		adminUser.quit();
	} 
	
	@Test
	public void testDisableBuildingAffectsAdminInSuperAdmin(){
		adminUser = new User(UserInfo.CISUPERADMIN);
		SuperAdminLoginPage superAdminLogin = new SuperAdminLoginPage(adminUser);
		
		SuperAdminHomePage superAdmin = superAdminLogin.login(adminUser);
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(superAdmin.toolbar.buildingListButton));
		
		BuildingListPage buildingList = superAdmin.toolbar.clickBuildingListTab();
		
		buildingList.searchForBuilding("Edit Account");
		buildingList.clickFilterButton();
		buildingList.disableBuilding();
		
		buildingAdminUser = new User(UserInfo.EDITACCOUNTADMIN);
		CILoginPage loginPage = new CILoginPage(buildingAdminUser);
		BuildingAdminDashboard adminDashboard = (BuildingAdminDashboard) loginPage.login(buildingAdminUser);
		
		assertTrue("Asserts that the admin user can log in with a new Username",
				adminDashboard.verifyElementsHidden(adminDashboard.initialVisibleElements));
		
		buildingList.enableBuilding();
		
		adminDashboard = (BuildingAdminDashboard) loginPage.login(buildingAdminUser);
		
		assertTrue("Asserts that the admin user can log in with a new Username",
				adminDashboard.verifyElementsVisibility(adminDashboard.initialVisibleElements));
		
		buildingAdminUser.quit();
		adminUser.quit();
	} 
	
	@Test
	public void testSiteUsageDashboardLink(){	
		adminUser = new User(UserInfo.CITEACHER);
		loginPage = new CILoginPage(adminUser);
		
		admin = (BuildingAdminDashboard) loginPage.login(adminUser);
		
		admin.click(admin.siteUsageLink);
		
		BuildingAdminReports reports = new BuildingAdminReports(admin.getDriver());
		
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(reports.siteUsageExportButton));
		
		assertTrue("Asserts that the user can see all elements on the Site Usage Report Page",
				reports.verifyElementsVisibility(reports.siteUsageElements));
		
		admin = reports.clickDashboardTab();
		
		adminUser.quit();
	}
	
	@Test
	public void testTitleActivityDashboardLink(){
		adminUser = new User(UserInfo.CITEACHER);
		loginPage = new CILoginPage(adminUser);
		
		admin = (BuildingAdminDashboard) loginPage.login(adminUser);
		
		admin.click(admin.titleActivityLink);
		
		BuildingAdminReports reports = new BuildingAdminReports(admin.getDriver());
		
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(reports.titleActivityExportButton));
		
		assertTrue("Asserts that the user can see all elements on the Title Activity Report Page",
				reports.verifyElementsVisibility(reports.titleActivityElements));
		
		admin = reports.clickDashboardTab();
		adminUser.quit();
	}
	
	@Test
	public void testMostPopularBooksDashboardLink(){
		adminUser = new User(UserInfo.CITEACHER);
		loginPage = new CILoginPage(adminUser);
		
		admin = (BuildingAdminDashboard) loginPage.login(adminUser);
		
		admin.click(admin.mostPopularBooksLink);
		
		BuildingAdminReports reports = new BuildingAdminReports(admin.getDriver());
		
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(reports.mostPopularBooksResetButton));
		
		assertTrue("Asserts that the user can see all elements on the Title Activity Report Page",
				reports.verifyElementsVisibility(reports.mostPopularBooksElements));
		
		admin = reports.clickDashboardTab();
		adminUser.quit();
	}
	
	@Test
	public void testSiteUsageReport(){	
		adminUser = new User(UserInfo.CITEACHER);
		loginPage = new CILoginPage(adminUser);
		
		admin = (BuildingAdminDashboard) loginPage.login(adminUser);
		
		admin.clickReportsTab();
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(admin.siteUsageButton));
		
		admin.click(admin.siteUsageButton);
		
		BuildingAdminReports reports = new BuildingAdminReports(admin.getDriver());
		
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(reports.siteUsageStartMonth));
		
		assertTrue("Asserts that the user can see all elements on the Site Usage Report Page",
				reports.verifyElementsVisibility(reports.siteUsageElements));
		
		admin = reports.clickDashboardTab();
		adminUser.quit();
	}
	
	@Test
	public void testTitleActivityReport(){
		adminUser = new User(UserInfo.CITEACHER);
		loginPage = new CILoginPage(adminUser);
		
		admin = (BuildingAdminDashboard) loginPage.login(adminUser);
		
		admin.clickReportsTab();
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(admin.titleActivityButton));
		
		admin.click(admin.titleActivityButton);
		
		BuildingAdminReports reports = new BuildingAdminReports(admin.getDriver());
		
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(reports.titleActivityFromDate));
		
		assertTrue("Asserts that the user can see all elements on the Title Activity Page",
				reports.verifyElementsVisibility(reports.titleActivityElements));
		
		admin = admin.clickDashboardTab();
		adminUser.quit();
	}
	
	@Test
	public void testMostPopularBooksReport(){
		adminUser = new User(UserInfo.CITEACHER);
		loginPage = new CILoginPage(adminUser);
		
		admin = (BuildingAdminDashboard) loginPage.login(adminUser);
		
		admin.clickReportsTab();
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(admin.mostPopularBooksButton));
		
		admin.click(admin.mostPopularBooksButton);
		
		BuildingAdminReports reports = new BuildingAdminReports(admin.getDriver());
		
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(reports.mostPopularBooksDropdown));
		
		assertTrue("Asserts that the user can see all elements on the Most Popular Books Page",
				reports.verifyElementsVisibility(reports.mostPopularBooksElements));
		
		admin = reports.clickDashboardTab();
		adminUser.quit();
	}
	
	@Test
	public void testSummaryViewPage(){
		adminUser = new User(UserInfo.CITEACHER);
		loginPage = new CILoginPage(adminUser);
		
		admin = (BuildingAdminDashboard) loginPage.login(adminUser);
		
		admin.clickBooksAndBooklistsTab();
		adminUser.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(admin.summaryViewButton));
		
		admin.click(admin.summaryViewButton);
		
		BuildingAdminReports reports = new BuildingAdminReports(admin.getDriver());
		
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(reports.summaryViewExportButton));
		
		assertTrue("Asserts that the user can see all elements on the Summary View Page",
				reports.verifyElementsVisibility(reports.summaryViewElements));
		
		admin = reports.clickDashboardTab();
		adminUser.quit();
	}
	
	@Test
	public void testAllBooksPage(){
		adminUser = new User(UserInfo.CITEACHER);
		loginPage = new CILoginPage(adminUser);
		
		admin = (BuildingAdminDashboard) loginPage.login(adminUser);
		
		admin.clickBooksAndBooklistsTab();
		adminUser.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(admin.allBooksButton));
		
		admin.click(admin.allBooksButton);
		
		BuildingAdminReports reports = new BuildingAdminReports(admin.getDriver());
		
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(reports.allBooksApplyFilter));
		
		assertTrue("Asserts that the user can see all elements on the All Books Page",
				reports.verifyElementsVisibility(reports.allBooksElements));
		
		admin = reports.clickDashboardTab();
		adminUser.quit();
	}
	
	@Test
	public void testBooklistsPage(){
		adminUser = new User(UserInfo.CITEACHER);
		loginPage = new CILoginPage(adminUser);
		
		admin = (BuildingAdminDashboard) loginPage.login(adminUser);
		
		admin.clickBooksAndBooklistsTab();
		adminUser.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(admin.booklistsButton));
		
		admin.click(admin.booklistsButton);
		
		BuildingAdminReports reports = new BuildingAdminReports(admin.getDriver());
		
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(reports.booklistsApplyFilterButton));
		
		assertTrue("Asserts that the user can see all elements on the Booklists Page",
				reports.verifyElementsVisibility(reports.booklistsElements));
		
		admin = reports.clickDashboardTab();
		adminUser.quit();
	}
	
	@Test
	public void testBookPosterPage(){
		adminUser = new User(UserInfo.CITEACHER);
		loginPage = new CILoginPage(adminUser);
		
		admin = (BuildingAdminDashboard) loginPage.login(adminUser);
		
		admin.clickBooksAndBooklistsTab();
		adminUser.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(admin.bookPosterButton));
		
		admin.click(admin.bookPosterButton);
		
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(admin.bookPosterFilter));
		
		assertTrue("Asserts that the user can see all elements on the Book Poster Page",
				admin.verifyElementsVisibility(admin.bookPosterElements));
		
		admin = admin.clickDashboardTab();
		adminUser.quit();
	}
	
	@Test
	public void testCreatePoster(){
		adminUser = new User(UserInfo.CITEACHER);
		loginPage = new CILoginPage(adminUser);
		
		admin = (BuildingAdminDashboard) loginPage.login(adminUser);
		
		admin.clickBooksAndBooklistsTab();
		adminUser.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(admin.bookPosterButton));
		
		admin.click(admin.bookPosterButton);
		
		CreateBookPosterPage bookPoster = new CreateBookPosterPage(admin.getDriver());
		
		adminUser.customWait().until(ExpectedConditions.elementToBeClickable(bookPoster.resest));
		
		bookPoster.createPosterByBookID("1450012");
		
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(bookPoster.createPosterButton));
		
		assertTrue("Asserts that the button to create book posters is clickable",
				bookPoster.isCreatePosterClickable());
		
		admin = admin.clickDashboardTab();
		adminUser.quit();
	}
	
	@Test
	public void testOpenStudentViewReport(){
		adminUser = new User(UserInfo.CITEACHER);
		loginPage = new CILoginPage(adminUser);
		
		admin = (BuildingAdminDashboard) loginPage.login(adminUser);
		
		String oldWindow = adminUser.getDriver().getWindowHandle();
		
		admin.openStudentView();
		
		StudentHomePage student = new StudentHomePage(admin.getDriver());
		admin.SwitchToNewPage(oldWindow);
		
		
		assertTrue("Asserts that the user opened a new tab that's a student view of CI",
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		
		admin.closeAllOtherTabsBut(oldWindow);
		
		admin = admin.clickDashboardTab();
		adminUser.quit();
	}
	
	@Test
	public void testchangeAdminPasswordOnBuildingAdmin(){	
		adminUser = new User(UserInfo.EDITACCOUNTADMIN);
		CILoginPage adminLogin = new CILoginPage(adminUser);
		BuildingAdminDashboard admin =  (BuildingAdminDashboard) adminLogin.login(adminUser);
		String originalPassword = adminUser.getPassword();
		String newPassword = "WWdLNWh2Error";
		
		EditMyProfileBuildingAdminPage editPage = admin.editMyProfile();
		
		editPage.changePassword(newPassword);
		editPage.clickSave();
		
		editPage.click(editPage.adminButton);
		editPage.click(editPage.logout);
		
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(adminLogin.capstoneLogo));
		
		adminUser.setPassword(newPassword);
		
		admin = (BuildingAdminDashboard) adminLogin.login(adminUser);
		assertTrue("Asserts that the user can use the new password to log in",
				admin.verifyElementsVisibility(admin.initialVisibleElements));
		
		editPage = admin.editMyProfile();
		
		assertTrue("Asserts that the new password is in the password field", 
				editPage.getDriver().findElement(editPage.adminPassword).getAttribute("value").equals(newPassword));
		
		editPage.changePassword(originalPassword);
		editPage.clickSave();
		
		editPage.click(editPage.adminButton);
		editPage.click(editPage.logout);
		editPage.closeCurrentWindow();
		adminUser.quit();
	}
	
	@Test
	public void testEditAdminUsername(){
		adminUser = new User(UserInfo.EDITACCOUNTADMIN);
		CILoginPage adminLogin = new CILoginPage(adminUser);
		BuildingAdminDashboard admin =  (BuildingAdminDashboard) adminLogin.login(adminUser);
		
		String originalUsername = adminUser.getUsername();
		String newUsername = "BADTESTNAME";
		
		EditMyProfileBuildingAdminPage editPage = admin.editMyProfile();
		
		editPage.changeUsername(newUsername);
		editPage.clickSave();
		
		editPage.click(editPage.adminButton);
		editPage.click(editPage.logout);
		
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(adminLogin.capstoneLogo));
		
		adminUser.setUsername(newUsername);
		
		admin = (BuildingAdminDashboard) adminLogin.login(adminUser);
		assertTrue("Asserts that the user can use the new username to log in",
				admin.verifyElementsVisibility(admin.initialVisibleElements));
		
		editPage = admin.editMyProfile();
		
		assertTrue("Asserts that the new username is in the password field", 
				editPage.getDriver().findElement(editPage.adminUsername).getAttribute("value").equals(newUsername));
		
		
		editPage.changeUsername(originalUsername);
		editPage.clickSave();
		
		editPage.click(editPage.adminButton);
		editPage.click(editPage.logout);
		editPage.closeCurrentWindow();
		adminUser.quit();
	}
	
	@Test
	public void testEditAdminUsernameAndPassword(){
		adminUser = new User(UserInfo.EDITACCOUNTADMIN);
		CILoginPage adminLogin = new CILoginPage(adminUser);
		BuildingAdminDashboard admin =  (BuildingAdminDashboard) adminLogin.login(adminUser);
		String originalUsername = adminUser.getUsername();
		String newUsername = "BADTESTNAME";
		String originalPassword = adminUser.getPassword();
		String newPassword = "WWdLNWh2Error";
		
		EditMyProfileBuildingAdminPage editPage = admin.editMyProfile();
		
		editPage.changeUsername(newUsername);
		editPage.changePassword(newPassword);
		editPage.clickSave();
		
		editPage.click(editPage.adminButton);
		editPage.click(editPage.logout);
		
		adminUser.customWait().until(ExpectedConditions.visibilityOfElementLocated(adminLogin.capstoneLogo));
		
		adminUser.setUsername(newUsername);
		adminUser.setPassword(newPassword);
		
		admin = (BuildingAdminDashboard) adminLogin.login(adminUser);
		assertTrue("Asserts that the user can use the new username to log in",
				admin.verifyElementsVisibility(admin.initialVisibleElements));
		
		editPage = admin.editMyProfile();
		
		assertTrue("Asserts that the new username is in the password field", 
				editPage.getDriver().findElement(editPage.adminUsername).getAttribute("value").equals(newUsername));
		assertTrue("Asserts that the new password is in the password field", 
				editPage.getDriver().findElement(editPage.adminPassword).getAttribute("value").equals(newPassword));
		
		editPage.changeUsername(originalUsername);
		editPage.changePassword(originalPassword);
		editPage.clickSave();
		
		editPage.click(editPage.adminButton);
		editPage.click(editPage.logout);
		editPage.closeCurrentWindow();
		adminUser.quit();
	}
	
	@Test
	public void testEditAdminPageCancelButton(){
		adminUser = new User(UserInfo.EDITACCOUNTADMIN);
		CILoginPage adminLogin = new CILoginPage(adminUser);
		BuildingAdminDashboard admin =  (BuildingAdminDashboard) adminLogin.login(adminUser);
		
		EditMyProfileBuildingAdminPage editPage = admin.editMyProfile();
		
		assertTrue("Asserts that the user can navigate to the Edit Page", editPage.verifyElementsVisibility(editPage.initialVisibleElements));
		
		admin = editPage.clickCancel();
		
		assertTrue("Asserts that the user is back on the Building Admin Page", admin.verifyElementsVisibility(admin.initialVisibleElements)
				&& !editPage.isElementPresent(editPage.cancelButton));
		
		admin = admin.clickDashboardTab();
		adminUser.quit();
	}
	
	@Test
	public void testEditAdminPageDashboardBreadcrumbButton(){
		adminUser = new User(UserInfo.EDITACCOUNTADMIN);
		CILoginPage adminLogin = new CILoginPage(adminUser);
		BuildingAdminDashboard admin =  (BuildingAdminDashboard) adminLogin.login(adminUser);
		EditMyProfileBuildingAdminPage editPage = admin.editMyProfile();
		
		assertTrue("Asserts that the user can navigate to the Edit Page", editPage.verifyElementsVisibility(editPage.initialVisibleElements));
		
		admin = editPage.clickDashboardBreadcrumb();
		
		assertTrue("Asserts that the user is back on the Building Admin Page", admin.verifyElementsVisibility(admin.initialVisibleElements)
				&& !editPage.isElementPresent(editPage.cancelButton));
		
		admin = admin.clickDashboardTab();
		adminUser.quit();
	}
}
