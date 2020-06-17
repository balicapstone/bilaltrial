package Tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import CITests.CIPages.BooklistSelectPage;
import CITests.CIPages.CILoginPage;
import CITests.CIPages.StudentHomePage;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;
import UserClasses.UserInfo;

public class BooklistsTests {
	public static CILoginPage login;
	public static StudentHomePage student;
	public static BooklistSelectPage booklistSelect;
	public static By thirdBook = By.xpath("//*[@id=\"shelf\"]/div[4]/div/figure/div/img");
	public static User user;
	
	@BeforeClass
	public static void executeBefore(){
		try{
			user = new User(UserInfo.CIGARAGE);
			
			login = new CILoginPage(user);
			student = (StudentHomePage) login.login(user);
			user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
			booklistSelect = student.clickBooklistsDiv();
			booklistSelect.waitImplicitly(1);
		} catch(Exception e){
			System.out.println("Failure to set up Booklists Tests");
			System.out.println(e);
		}
	}
	
	@AfterClass
	public static void executeAfter(){
		user.quit();
	}
	
	@Rule
	public TestWatcher restart = new TestWatcher(){
		@Override
		public void failed(Throwable e, Description description){
			user.getWatcher().TakeScreenshot(description.getDisplayName());
			
			user.quit();
			user.makeNewDriver();
			login = new CILoginPage(user);
			student = (StudentHomePage) login.login(user);
			student.waitForElement(By.xpath("//*[@id=\"shelf\"]/div[4]/div/figure/div/img"));
		}
	};
	
	@Rule
	public Retry retry = new Retry(3);
	
	
	public void testLogOutButton(){
		login = booklistSelect.logOut();
		login.waitImplicitly(3);
		assertTrue(login.verifyElementsVisibility(login.getInitialVisibleElements()));
		student.waitForElement(thirdBook);
		
		student = (StudentHomePage) login.login(user); 
		booklistSelect = student.clickBooklistsDiv();
		booklistSelect.waitImplicitly(1);
	}
	
	@Test
	public void createBookList(){
		
	}
	
	@Test
	public void hideBooklist(){
		
	}
	
	@Test
	public void testMakeBooklistInvisible(){
		
	}
	
	@Test
	public void testBooklistCopyLink(){
		
	}
	
	@Test
	public void testAddToBooklist(){
		
	}
	
	@Test
	public void testDeleteBooklist(){
		
	}
}
