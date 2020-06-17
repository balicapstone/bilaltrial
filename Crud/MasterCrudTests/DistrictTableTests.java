package MasterCrudTests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import Crud.BuildingModal;
import Crud.BuildingsView;
import Crud.CrudLogin;
import Crud.DatabaseSelect;
import Crud.DistrictModal;
import Crud.DistrictsView;
import Crud.OrderModal;
import Crud.PurchaseModal;
import Crud.PurchaseView;
import Crud.UserModal;
import Crud.UserView;
import UserClasses.User;
import UserClasses.UserInfo;

public class DistrictTableTests {
	
	static DatabaseSelect dataSelect;
	static User user;
	static CrudLogin crud;
	static int wait = 1;
	
	@BeforeClass
	public static void executeBefore(){
		user = new User(UserInfo.CRUDMASTERWILL);
		user.makeNewDriver();
		
		crud = new CrudLogin(user);
    	
    	dataSelect = crud.login(user);
	}
	
	
	@AfterClass
	public static void executeAfter(){
		user.quit();
	}
	
	@Rule
    public TestWatcher restart = new TestWatcher(){
    	@Override
    	public void failed(Throwable e, Description description){

    	}
    };
    
    //@Rule
  	//public Retry retry = new Retry(3);
    
    @Test
    public void thing(){
    	String teacher = "playteach14";
    	String student = "playstudent14";
    	String school = "playtime14 school";
    	String district = "playtime14";
    	
    	
    	createDistrict(district);
    	
    	createUser("Teacher", teacher, school);
    	crud.waitImplicitly(2);
    	createUser("Student", student, school);	
    	
    	
    	//LandingPage landing = new LandingPage(user);
    	//User u = new User(teacher, "test");
    	/*
    	LoginModal lModal = landing.clickLoginLink();
    	lModal.waitImplicitly(wait);
    	EulaModal eula = lModal.loginToSignEula(u);
    	eula.waitImplicitly(wait);
    	SearchPage search = eula.acceptEula();
    	search.waitImplicitly(wait);
    	*/
    	//assertTrue("Asserts that the user can log in and is redirected to the search page", search.isElementPresent(By.id("search-button")));
    }
    
    public void createUser(String type, String username, String building){
    	UserView userTable = dataSelect.goToUsersDatabese();
    	userTable.waitImplicitly(wait);
    	UserModal uModal = userTable.createNewUser();
    	uModal.waitImplicitly(wait);
    	
    	uModal.selectUserType(type);
    	uModal.enterUsername(username);
    	uModal.enterPassword("test");
    	uModal.enterFirstName("test");
    	uModal.enterlastName("test");
    	uModal.selectBuildingName(building);
    	
    	userTable = uModal.createNewUser();
    	userTable.waitImplicitly(wait);
    	
    	dataSelect = userTable.goBackToSelectPage();
    }
    
    public void createDistrict(String district){
    	String buildingName = district + " school";
    	DistrictsView districts = dataSelect.goToDistrictsDatabase();
    	DistrictModal dModal = districts.openNewDistrictModal();
    	dModal.waitImplicitly(wait);
    	
    	dModal.enterDistrictName(district);
    	dModal.enterZipCode("55555");
    	dModal.enterSalesForceID("12345");
    	districts = dModal.createDistrict();
    	districts.waitImplicitly(wait);
    	
    	districts.search(district);
    	BuildingsView buildings = districts.showBuildingsByIndex(1);
    	buildings.waitImplicitly(wait);
    	
    	BuildingModal bModal = buildings.createNewBuilding();
    	bModal.waitImplicitly(wait);
    	bModal.enterBuildingName(buildingName);
    	bModal.enterSalesforceId("12345");
    	buildings = bModal.createNewBulding();
    	buildings.waitImplicitly(wait);
    	
    	buildings.searchDB(buildingName);
    	PurchaseView purchases = buildings.editPurchaseByInt(1);
    	purchases.waitImplicitly(wait);
    	PurchaseModal pModal = purchases.createNewPurchase();    	
    	pModal.waitImplicitly(wait);
    	
    	pModal.selectEULA("1.0.0");
    	pModal.selectAppType("No Nonsense Literacy");
    	pModal.selectPurchaseType("Perpetual");
    	pModal.enterExpirationDate("2020-01-01");
    	pModal.enterActivationDate("2017-01-01");
    	pModal.enterOrderNumber("12345");
    	pModal.waitImplicitly(wait);
    	
    	purchases = pModal.createNewPurchaseButton();
    	purchases.waitImplicitly(wait);
    	
    	OrderModal order = purchases.editOrderByIndex(1);
    	order.waitImplicitly(wait);
    	order.clickAssessmentsCheckbox();
    	order.clickLessonPlansCheckbox();
    	purchases = order.finishEditingOrder();
    	purchases.waitImplicitly(wait);
    	
    	buildings = purchases.clickBackButton();
    	districts = buildings.clickBackButton();
    	dataSelect = districts.goBackToDatabaseSelect();
    }
}
