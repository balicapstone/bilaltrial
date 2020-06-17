package Tests;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import PGOPages.LoginPage;
import PGOStudentResources.AnimalsContentPage;
import PGOStudentResources.ArticlePage;
import PGOStudentResources.StudentHomePage;
import UserClasses.User;

public class PGOLoadTests {
	public static LoginPage login;
	public static StudentHomePage student;
	public static User user = new User(UserClasses.UserInfo.GARAGESTUDENT);
	
	@BeforeClass
    public static void executeBefore(){
 		login = new LoginPage(user, "newqa");
 		student = (StudentHomePage) login.Login(user);
    }
    
    @AfterClass
    public static void executeAfter(){
    	login.quit();
    }
    
    @Test
    public void testReadingAnArticle(){
    	AnimalsContentPage animals = student.clickAnimalsModule();
    	animals.clickCategoryByText("Amphibians");
    	
    	ArticlePage article = animals.clickArticleByText("Frogs");
    	
    	article.clickTabOne().readTabOne();
    	article.waitImplicitly(2);
    	article.clickTabTwo().readTabTwo();
    	article.waitImplicitly(2);
    	article.clickTabThree().readTabThree();
    	article.waitImplicitly(2);
    	article.clickTabFour().readTabFour();
    	article.waitImplicitly(2);
    	article.clickTabFive().readTabFive();
    }
}
