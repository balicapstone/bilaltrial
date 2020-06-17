package Tests;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import BookTools.BookModal;
import BookTools.BookReader;
import CITests.CIPages.CILoginPage;
import CITests.CIPages.StudentHomePage;
import UserClasses.User;

public class CILoadTests {
	public static CILoginPage login;
	public static StudentHomePage student;
	public static User user = new User(UserClasses.UserInfo.CISTUDENT);
	
	@BeforeClass
    public static void executeBefore(){
 		login = new CILoginPage(user, "qa");
 		student = (StudentHomePage) login.login(user);
 		student.waitImplicitly(5);
    }
    
    @AfterClass
    public static void executeAfter(){
    	login.quit();
    }
    
    @Test
    public void testReadingAnArticle(){
    	BookModal bookModal = student.clickBookByTitle("10 Little Kittens");
    	bookModal.waitImplicitly(5);
    	BookReader book = bookModal.openBook();
    	book.waitImplicitly(5);
    	book.readBookPageByPage();
    }
}
