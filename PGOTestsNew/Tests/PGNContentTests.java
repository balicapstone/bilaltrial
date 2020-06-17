package Tests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import PGNPages.PGNLoginPage;
import PGNStudentResources.PGNArticlePage;
import PGNStudentResources.PGNArticleType;
import PGNStudentResources.PGNIndiansDatabaseHome;
import PGNStudentResources.PGNScienceDatabaseHome;
import PGNStudentResources.PGNStudentHomePage;
import PGNStudentResources.PGNScienceDatabaseHome;
import PGNStudentResources.PGNStatesDatabaseHome;
import PGNStudentResources.PGNStudentHomePage;
import TrueUserTests.TrueUser.PGNSmokeTest;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;

public class PGNContentTests {
	static PGNLoginPage login;
    static int waitTime = 5;
    static User user; 
    
 	@BeforeClass
    public static void executeBefore(){
    	user = new User(UserClasses.UserInfo.SCIENCESTUDENT);
    	
 		login = new PGNLoginPage(user);
    }
    
    @AfterClass
    public static void executeAfter(){
    	user.quit();
    }
	
    @Rule
    public TestWatcher restart = new TestWatcher(){
    	@Override
    	public void failed(Throwable e, Description description){
    		try{
    			user.getWatcher().TakeScreenshot(description.getDisplayName());
    		} catch(Exception t){
    			System.out.print("There was a failure while trying to take a screenshot for " + description.getDisplayName());
    		}
    		
    		user.quit();
        /*	user.makeNewDriver();
        	
        	login = new PGNLoginPage(user);*/
    	}
    };
    	
/*	@Rule
	public Retry retry = new Retry(3);
    	*/
    @Test
    @Category(PGNSmokeTest.class)
    public void studentOnlySeesScience(){
    	user.setCredentials(UserClasses.UserInfo.SCIENCESTUDENT);
    	
    	login.login(user);
    	PGNScienceDatabaseHome science = new PGNScienceDatabaseHome(user.getDriver());
    	
    	science.waitImplicitly(waitTime);
    	
    	assertTrue("Asserts that this student can only see the science database", science.verifyElementsVisibility(science.getInitialVisibleElements()));
    	login = science.header.logout();
    }
    
    @Test
    @Category(PGNSmokeTest.class)
    public void studentOnlySeesStatesAndIndians(){
    	user.setCredentials(UserClasses.UserInfo.STATESSTUDENT);
    	PGNStudentHomePage home = (PGNStudentHomePage) login.login(user);
    	home.waitImplicitly(waitTime);
    	home.clickPebbleGoNextTab();
    	home.waitImplicitly(5);
		
    	assertTrue("Asserts that this student can only see the States and American Indians dtaabases", home.isStatesDBPresent() && home.isAMIndiansDBPresent() && !home.isScienceDBPresent());
    	login = home.header.logout();
    }
    
    @Test
    @Category(PGNSmokeTest.class)
    public void studentNavigatesToIndianArticleThroughMap(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	PGNStudentHomePage home = (PGNStudentHomePage) login.login(user);
    	
    	home.waitImplicitly(10);
    	home.clickPebbleGoNextTab();
    	home.waitImplicitly(5);
    	
    	home.header.openHamburgerMenu();
    	home.waitImplicitly(2);
    	
    	home.header.hamburgerMenu.openAmericanIndiansDiv().switchToMap();
    	home.waitImplicitly(2);
    	
    	home.header.hamburgerMenu.clickAmericanIndianRegion("Plains");
    	home.waitImplicitly(2);
    	
    	PGNArticlePage article = home.header.hamburgerMenu.clickMapItemIntoArticle("CheyenneTrad", PGNArticleType.INDIANS);
    	
    	assertTrue("Asserts that a user can navigate to the correct American Indians article through the Map", article.getTitle().equals("Cheyenne"));
    	login = home.header.logout();
    }
    
    @Test
    @Category(PGNSmokeTest.class)
    public void studentNavigatesToStatesArticleThroughMap(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	PGNStudentHomePage home = (PGNStudentHomePage) login.login(user);
    	
    	home.header.openHamburgerMenu();
    	home.waitImplicitly(2);
    	
    	home.header.hamburgerMenu.openStatesDiv().switchToMap();
    	home.waitImplicitly(2);
    	
    	home.header.hamburgerMenu.clickStatesRegion("Northeast");
    	home.waitImplicitly(2);
    	
    	PGNArticlePage article = home.header.hamburgerMenu.clickMapItemIntoArticle("New_York_Text", PGNArticleType.STATES);
    	
    	assertTrue("Asserts that a user can navigate to the correct American Indians article through the Map", article.getTitle().equals("New York"));
    	login = home.header.logout();
    }
    
    @Test
    public void studentNavigatesToScienceThroughHamburgerMenuDepthTwo(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	PGNStudentHomePage home = (PGNStudentHomePage) login.login(user);
    	
    	home.header.openHamburgerMenu();
    	home.header.waitImplicitly(2);
    	
    	home.header.hamburgerMenu.openScienceDiv().clickListSubItem("Life Science");
    	home.header.waitImplicitly(2);
    	
    	home.header.hamburgerMenu.clickListSubItem("Life Science");
    	home.header.waitImplicitly(2);
    	
    	home.header.hamburgerMenu.clickListSubItem("Human Anatomy");
    	home.header.waitImplicitly(2);
    	
    	PGNArticlePage article = home.header.hamburgerMenu.clickSubItemIntoArticle("Organs");
    	
    	assertTrue("Asserts that a user can navigate to the correct Science article through the Hamburger Menu", 
    			article.getTitle().equals("Organs"));
    	
    	login = home.header.logout();
    }
    
    @Test
    @Category(PGNSmokeTest.class)
    public void studentNavigatesToScienceThroughHamburgerMenuDepthThree(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	PGNStudentHomePage home = (PGNStudentHomePage) login.login(user);
    	
    	home.header.openHamburgerMenu();
    	home.header.waitImplicitly(2);
    	
    	home.header.hamburgerMenu.openScienceDiv();
    	home.header.waitImplicitly(2);
    	
    	home.header.hamburgerMenu.clickListSubItem("Life Science");
    	home.header.waitImplicitly(2);
    	
    	home.header.hamburgerMenu.clickListSubItem("Human Anatomy");
    	home.header.waitImplicitly(2);
    	
    	home.header.hamburgerMenu.clickListSubItem("Human Body Systems");
    	home.header.waitImplicitly(2);
    	
    	PGNArticlePage article = home.header.hamburgerMenu.clickSubItemIntoArticle("Digestive System");
    	assertTrue("Asserts that a user can navigate to the correct Science article through the Hamburger Menu", 
    			article.getTitle().equals("Digestive System"));
    	
    	login = home.header.logout();
    }
    
    @Test
    @Category(PGNSmokeTest.class)
    public void canSearchIntoArticle(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	PGNStudentHomePage home = (PGNStudentHomePage) login.login(user);
    	
    	PGNScienceDatabaseHome science = home.goToScience();
    	
    	science.header.searchFor("artificial");
    	science.header.sendDeleteKey(science.header.searchBar);
    	science.waitImplicitly(2);
    	
    	PGNArticlePage article = science.header.clickResult("Artificial Selection");

    	assertTrue("Verifies that a user can search into a correct artcile from a content select screen", 
    			article.getTitle().equals("Artificial Selection"));
    	login = home.header.logout();
    }
    
    @Test
    @Category(PGNSmokeTest.class)
    public void crossSearchStatesScience(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	PGNStudentHomePage home = (PGNStudentHomePage) login.login(user);
    	
    	PGNStatesDatabaseHome states = home.goToStates();
    	
    	states.header.searchFor("artificial");
       	states.header.sendDeleteKey(states.header.searchBar);
    	states.waitImplicitly(2);
    	
    	PGNArticlePage article = states.header.clickResult("Artificial Selection");

    	assertTrue("Verifies that a user can search into a correct artcile from a content select screen", 
    			article.getTitle().equals("Artificial Selection"));
    	login = home.header.logout();
    }
    
    @Test
    @Category(PGNSmokeTest.class)
    public void crossSearchStatesIndians(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	PGNStudentHomePage home = (PGNStudentHomePage) login.login(user);
    	
    	PGNStatesDatabaseHome science = home.goToStates();
    	
    	science.header.searchFor("pueblo");
    	science.header.sendDeleteKey(science.header.searchBar);
    	science.waitImplicitly(2);
    	
    	PGNArticlePage article = science.header.clickResult("Pueblo");

    	assertTrue("Verifies that a user can search into a correct artcile from a content select screen", 
    			article.getTitle().equals("Pueblo"));
    	login = home.header.logout();
    }
    
    @Test
    @Category(PGNSmokeTest.class)
    public void crossSearchScienceStates(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	PGNStudentHomePage home = (PGNStudentHomePage) login.login(user);
    	
    	PGNScienceDatabaseHome science = home.goToScience();
    	
    	science.header.searchFor("Minnesota");
    	science.header.sendDeleteKey(science.header.searchBar);
    	science.waitImplicitly(1);
    	
    	PGNArticlePage article = science.header.clickResult("Minnesota");
    	
    	assertTrue("Verifies that a user can search into a correct artcile from a content select screen", 
    			article.getTitle().equals("Minnesota"));
    	login = home.header.logout();
    }
    
    @Test
    @Category(PGNSmokeTest.class)
    public void crossSearchScienceAmericanIndians(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	PGNStudentHomePage home = (PGNStudentHomePage) login.login(user);
    	
    	PGNScienceDatabaseHome science = home.goToScience();
    	
    	science.header.searchFor("navajo").waitImplicitly(2);
    	science.header.sendDeleteKey(science.header.searchBar);
    	science.waitImplicitly(1);
    			
    	PGNArticlePage article = science.header.clickResult("Navajo");

    	assertTrue("Verifies that a user can search into a correct artcile from a content select screen", 
    			article.getTitle().equals("Navajo"));
    	login = home.header.logout();
    }
    
    @Test
    public void crossSearchIndiansStates(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	PGNStudentHomePage home = (PGNStudentHomePage) login.login(user);
    	
    	PGNIndiansDatabaseHome indians = home.goToIndians();
    	
    	indians.header.searchFor("rhod").waitImplicitly(2);
    	indians.header.sendDeleteKey(indians.header.searchBar);
    	indians.waitImplicitly(1);
    	
    	PGNArticlePage article = indians.header.clickResult("Rhode Island");

    	assertTrue("Verifies that a user can search into a correct artcile from a content select screen", 
    			article.getTitle().equals("Rhode Island"));
    	login = home.header.logout();
    }
    
    @Test
    public void crossSearchIndiansScience(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	PGNStudentHomePage home = (PGNStudentHomePage) login.login(user);
    	
    	PGNIndiansDatabaseHome indians = home.goToIndians();
    	
    	indians.header.searchFor("senses").waitImplicitly(2);
    	indians.header.sendDeleteKey(indians.header.searchBar);
    	indians.waitImplicitly(1);
    	
    	PGNArticlePage article = indians.header.clickResult("The Senses");

    	assertTrue("Verifies that a user can search into a correct artcile from a content select screen", 
    			article.getTitle().equals("The Senses"));
    	
    	login = home.header.logout();
    }
    
    @Test
    @Category(PGNSmokeTest.class)
    public void testHomeButtonScience(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	PGNStudentHomePage home = (PGNStudentHomePage) login.login(user);
    	
    	home.waitImplicitly(10);
    	home.clickPebbleGoNextTab();
    	home.waitImplicitly(5);
    	
    	PGNScienceDatabaseHome science = home.goToScience();
    	
    	science.header.searchFor("DNA,");
    	science.header.sendDeleteKey(science.header.searchBar);
    	PGNArticlePage article = science.header.clickResult("DNA, Genes, and Chromosomes");
    	
    	
    	home = article.clickHomeBreadcrumb();
    	assertTrue("Asserts that a user can use the Home Breadcrumb to return to the database homepage", 
    			home.isAMIndiansDBPresent() && home.isScienceDBPresent() && home.isStatesDBPresent());
    	login = home.header.logout();
    }
    
    @Test
    public void testHomeButtonAmericanIndians(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	PGNStudentHomePage home = (PGNStudentHomePage) login.login(user);
    	PGNIndiansDatabaseHome indians = home.goToIndians();
    	
    	indians.header.searchFor("pueblo");
    	indians.header.sendDeleteKey(indians.header.searchBar);
    	indians.waitImplicitly(1);
    	
    	PGNArticlePage article = indians.header.clickResult("Pueblo");
     	
    	home = article.clickHomeBreadcrumb();
    	assertTrue("Asserts that a user can use the Home Breadcrumb to return to the database homepage", 
    			home.isAMIndiansDBPresent() && home.isScienceDBPresent() && home.isStatesDBPresent());
    	login = home.header.logout();
    }
    
    @Test
    public void testHomeButtonStates(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	PGNStudentHomePage home = (PGNStudentHomePage) login.login(user);
    	PGNStatesDatabaseHome states = home.goToStates();
    	
    	states.header.searchFor("Minnesota");
    	states.header.sendDeleteKey(states.header.searchBar);
    	states.waitImplicitly(1);
    	
    	PGNArticlePage article = states.header.clickResult("Minnesota");
     	
    	home = article.clickHomeBreadcrumb();
    	assertTrue("Asserts that a user can use the Home Breadcrumb to return to the database homepage", home.isAMIndiansDBPresent() && home.isScienceDBPresent() && home.isStatesDBPresent());
    	login = home.header.logout();
    }
    
    /*
    @Test
    @Category(PGNSmokeTest.class)
    public void testStatesShortcutsFlag(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	StudentHomePage home = (StudentHomePage) login.login(user);
    	StatesDatabaseHome states = home.goToStates();
    	
    	states.header.searchFor("IA Flag").waitImplicitly(2);
    	states.header.sendDeleteKey(states.header.searchBar);
    	states.waitImplicitly(1);
    	
    	ArticlePage article = states.header.clickResult("Iowa Flag", ArticleType.STATES);
    	article.openActivity();
    	
    	assertTrue("Asserts that the user is brought to the Flag Activity in States", article.activity.getActiveTab().equals("Flag"));
    	article.activity.closeModal();
    	login = home.header.logout();
    }
    
    @Test
    public void testStatesShortcutsMap(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	StudentHomePage home = (StudentHomePage) login.login(user);
    	StatesDatabaseHome states = home.goToStates();
    	
    	states.header.searchFor("CA map").waitImplicitly(2);
    	states.header.sendDeleteKey(states.header.searchBar);
    	states.waitImplicitly(1);
    	
    	ArticlePage article = states.header.clickResult("California Map", ArticleType.STATES);
    	article.openActivity();
    	
    	assertTrue("Asserts that the user is brought to the States Activity in States", article.activity.getActiveTab().equals("Map"));
    	article.activity.closeModal();
    	login = home.header.logout();
    }
    
    @Test
    @Category(PGNSmokeTest.class)
    public void testStatesShortcutsRecipe(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	StudentHomePage home = (StudentHomePage) login.login(user);
    	StatesDatabaseHome states = home.goToStates();
    	
    	states.header.searchFor("KY reci").waitImplicitly(2);
    	states.header.sendDeleteKey(states.header.searchBar);
    	states.waitImplicitly(1);
    	
    	ArticlePage article = states.header.clickResult("Kentucky Recipe");
    	
    	assertTrue("Asserts that the user is brought to the Recipe Tab in a States article", article.getActiveTab().equals("Recipe"));
		
    	article.scrollToTopofPage();
	   
	
    	login = home.header.logout();
    }
    
    @Test
    public void testStatesShortcutsSong(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	StudentHomePage home = (StudentHomePage) login.login(user);
    	StatesDatabaseHome states = home.goToStates();
    	
    	states.header.searchFor("MD So").waitImplicitly(2);
    	states.header.sendDeleteKey(states.header.searchBar);
    	states.waitImplicitly(1);
    	
    	ArticlePage article = states.header.clickResult("Maryland Song");
    	article.waitImplicitly(2);
    	
    	assertTrue("Asserts that a user is brought to the Song Tab in a States Article through Search", article.getActiveTab().equals("Song"));
    	
    	article.scrollToTopofPage();
    	login = home.header.logout();
    }
    
    @Test
    public void testStatesShortcutsVideo(){
    	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	StudentHomePage home = (StudentHomePage) login.login(user);
    	StatesDatabaseHome states = home.goToStates();
    	
    	states.header.searchFor("ID vid").waitImplicitly(2);
    	states.header.sendDeleteKey(states.header.searchBar);
    	states.waitImplicitly(1);
    	
    	ArticlePage article = states.header.clickResult("Idaho Video");
    	
    	article.waitImplicitly(3);
    	
    	assertTrue("Asserts that a user is brought to the Video Tab in a States Article through Search", article.getActiveTab().equals("Video") || article.getActiveTab().equals("Song"));
    	article.scrollToTopofPage();
    	login = home.header.logout();
    }
    */
    

    @Rule
    public TestRule watcher = new TestWatcher() {
       protected void starting(Description description) {
          System.out.println("Starting test: " + description.getMethodName());
          
       }
       };
}
