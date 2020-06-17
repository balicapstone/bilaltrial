package Tests;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import PGNPages.LoginPage;
import PGNStudentResources.ArticlePage;
import PGNStudentResources.ScienceDatabaseHome;
import PGNStudentResources.StudentHomePage;
import UserClasses.User;

public class PGNLoadTests {
	public static LoginPage login;
	public static StudentHomePage student;
	public static User user = new User(UserClasses.UserInfo.GARAGESTUDENT);
	
	@BeforeClass
    public static void executeBefore(){
 		login = new LoginPage(user, "newqa");
 		student = (StudentHomePage) login.login(user);
    }
    
    @AfterClass
    public static void executeAfter(){
    	login.quit();
    }
    
    @Test
    public void testReadingAnArticle(){
    	ScienceDatabaseHome science = student.goToScience();
    	science.clickCategory("Life Science");
    	science.clickCategory("Genetics");
    	
    	ArticlePage article = science.clickArticle("Animal Cells");
    	
    	article.readArticle();
    }
}
