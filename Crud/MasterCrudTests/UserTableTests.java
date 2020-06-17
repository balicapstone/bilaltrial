package MasterCrudTests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import Crud.CrudLogin;
import Crud.DatabaseSelect;
import Crud.DistrictsView;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;
import UserClasses.UserInfo;

public class UserTableTests {
	public static User user; 
	
	
	@BeforeClass
	public static void executeBefore(){
		user = new User(UserInfo.CRUDMASTERWILL);	
	}
	
	
	@AfterClass
	public static void executeAfter(){
		user.quit();
	}
	
		
	@Rule
	public Retry retry = new Retry(3);
	
	@Rule
    public TestWatcher restart = new TestWatcher(){
    	@Override
    	public void failed(Throwable e, Description description){

    	}
    };
    
    
    @Test
    public void testLogin(){
    	CrudLogin crud = new CrudLogin(user);
    	
    	DatabaseSelect dataSelect = crud.login(user);
    	
    	assertTrue("", true);
    	//UsersTable userData = dataSelect.goToUsersDatabese();
    	DistrictsView districtData = dataSelect.goToDistrictsDatabase();
    	districtData.logout();
  
    	
    	
    }
    
    @Test
    public void testCantLogin(){
    	
    }
    
    @Test
    public void testSuperUserCanLogin(){
    	
    }
    
    @Test
    public void testCanTurnOffUser(){
    	
    }
    
    @Test
    public void testCanReactivateUser(){
    	
    }
    
    @Test
    public void testCanChangePGOUsername(){
    	
    }
    
    @Test
    public void testCanChangePGNUsername(){
    	
    }
    
    @Test
    public void testTeacherAccountMustLinkToStudent(){
    	
    }
    
    @Test
    public void testFirstNameError(){
    	
    }
    
    @Test
    public void testLastNameError(){
    	
    }
    
    @Test
    public void testUsernameError(){
    	
    }
    
    @Test
    public void testPasswordError(){
    	
    }
    
    @Test
    public void testBuildingNameError(){
    	
    }
    
    @Test
    public void testUsernameExistsInPGO(){
    	
    }
    
    @Test
    public void testUsernameExistsInCI(){
    	
    }
    
    @Test
    public void testUsernameIsAlreadyMasterAccount(){
    	
    }
}
