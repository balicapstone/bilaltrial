package Tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import CMSPages.CMSHomePage;
import CMSPages.CMSLandingPage;
import CMSPages.CMSToolbar;
import CMSPages.UploadExportPage;
import PGOPages.LoginPage;
import UserClasses.User;
import UserClasses.UserInfo;

public class UploadTests {

	public static User user = new User(UserInfo.GARAGESTUDENT);
	public static User student = new User(UserInfo.GARAGESTUDENT);
	public static CMSHomePage home;
	public static LoginPage studentLogin;
	
	
	@BeforeClass
	public static void executeBefore(){
		CMSLandingPage cms = new CMSLandingPage(user.getDriver());
		cms.waitImplicitly(2);
		
		home = cms.login(user);
		home.waitImplicitly(10);
		
	}
	
	@AfterClass
	public static void executeAfter(){
		home.quit();
	}
	
	@Rule
	public TestWatcher restart = new TestWatcher(){
		@Override
		public void failed(Throwable e, Description description){
			user.getWatcher().TakeScreenshot(description.getDisplayName());			
			home.closeCurrentWindow();
			
			user.makeNewDriver();
			CMSLandingPage cms = new CMSLandingPage(user.getDriver());
			cms.waitImplicitly(5);
			
			home = cms.login(user);
			home.waitImplicitly(5);
		}
	};
	
	//@Rule
	//public Retry retry = new Retry(3);
	
	@Test
	public void testImageUpload(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(2);
		
		UploadExportPage upload = toolbar.clickUpload();
		
		upload.uploadImage("//Users/whall/Desktop/capybaras_2.jpg");
		
		studentLogin = new LoginPage(student);
		//StudentHomePage studenthome = (StudentHomePage) studentLogin.Login(student);
	}
	
	@Test
	public void testTimefileUpload(){
		
	}
	
	
	@Test
	public void testAudioUpload(){
		
	}
	
	@Test
	public void testViedoUpload(){
		
	}
	
	@Test
	public void testPDFUpload(){
		
	}
}
