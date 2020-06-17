package Tests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import PGOPagesNew.PGONewLoginPage;
import PGOStudentResources.PGONewArticlePage;
import PGOStudentResources.PGONewContentSelectionPage;
import PGOStudentResources.StudentHomePageNew;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;
import UserClasses.UserInfo;

public class RegressionPGONewLegacyLinkTests {
	static PGONewLoginPage loginPage;
	static StudentHomePageNew home;
    static User user;
    
    @BeforeClass
    public static void executeBefore(){
    	user = new User(UserInfo.GARAGESTUDENT);
    	
    	try{
    		loginPage = new PGONewLoginPage(user);
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    		
    		/*
    		home = (StudentHomePageNew) loginPage.Login(user);
    		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(home.schoolTitleText));
    		*/
    	} catch(Exception e){
    		user.getWatcher().TakeScreenshot("FailureToRunExecuteBeforeForLoginTests");
    	}
    }
    
    @AfterClass
    public static void executeAfter(){
    	loginPage.closeCurrentWindow();
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
    		executeBefore();
    	}
    };
    
    
	@Rule
	public Retry retry = new Retry(3);
    
    @Test
    public void testLinkLoginASPX(){
    	loginPage.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/UserLogin.aspx");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    	
    	assertTrue("Asserts that the user is redirected to the login page",
    			loginPage.verifyElementsVisibility(loginPage.initialVisibleElements));
    }
    
    @Test
    public void testLinkLoginIndex(){
    	loginPage.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/login/index.html");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    	
    	assertTrue("Asserts that the user is redirected to the login page",
    			loginPage.verifyElementsVisibility(loginPage.initialVisibleElements));
    }
    
    @Test
    public void testLinkChoose(){
    	home = (StudentHomePageNew) loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	home.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/choose");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(home.header.logo));
    	
    	assertTrue("Asserts that the user is redirected to the modules page",
    			home.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules"));
    	assertTrue("Asserts that the user is on the modules page",
    			home.verifyElementsVisibility(home.getInitialVisibleElements()));
    	
    	loginPage = home.header.logout();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    }
    
    @Test
    public void testLinkModuleNameAnimals(){
    	home = (StudentHomePageNew) loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	home.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/content/Animals");
    	PGONewContentSelectionPage content = new PGONewContentSelectionPage(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(content.gamesButton));
  
    	assertTrue("Asserts that the user is redirected to the correct modules page",
    			content.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/0"));
    	assertTrue("Asserts that the user is on the modules page",
    			content.verifyElementsVisibility(content.getInitialVisibleElements()));
    	
    	loginPage = home.header.logout();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    }
    
    @Test
    public void testLinkModuleNameAnimales(){
    	home = (StudentHomePageNew) loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	home.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/content/Animales");
    	PGONewContentSelectionPage content = new PGONewContentSelectionPage(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(content.gamesButton));
  
    	assertTrue("Asserts that the user is redirected to the correct modules page",
    			content.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules/8/categories/0"));
    	assertTrue("Asserts that the user is on the modules page",
    			content.verifyElementsVisibility(content.getInitialVisibleElements()));
    	
    	loginPage = home.header.logout();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    }
    
    @Test
    public void testLinkModuleNameScience(){
    	home = (StudentHomePageNew) loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	home.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/content/Science");
    	PGONewContentSelectionPage content = new PGONewContentSelectionPage(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(content.gamesButton));
  
    	assertTrue("Asserts that the user is redirected to the correct modules page",
    			content.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules/2/categories/0"));
    	assertTrue("Asserts that the user is on the modules page",
    			content.verifyElementsVisibility(content.getInitialVisibleElements()));
    	
    	loginPage = home.header.logout();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    }
    
    @Test
    public void testLinkModuleNameCiencia(){
    	home = (StudentHomePageNew) loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	home.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/content/Ciencia");
    	PGONewContentSelectionPage content = new PGONewContentSelectionPage(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(content.gamesButton));
  
    	assertTrue("Asserts that the user is redirected to the correct modules page",
    			content.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules/12/categories/0"));
    	assertTrue("Asserts that the user is on the modules page",
    			content.verifyElementsVisibility(content.getInitialVisibleElements()));
    	
    	loginPage = home.header.logout();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    }
    
    @Test
    public void testLinkModuleNameBiographies(){
    	home = (StudentHomePageNew) loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	home.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/content/Biographies");
    	PGONewContentSelectionPage content = new PGONewContentSelectionPage(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(content.gamesButton));
  
    	assertTrue("Asserts that the user is redirected to the correct modules page",
    			content.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules/3/categories/0"));
    	assertTrue("Asserts that the user is on the modules page",
    			content.verifyElementsVisibility(content.getInitialVisibleElements()));
    	
    	loginPage = home.header.logout();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    }
    
    @Test
    public void testLinkModuleNameBiografias(){
    	home = (StudentHomePageNew) loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	home.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/content/Biograf");
    	PGONewContentSelectionPage content = new PGONewContentSelectionPage(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(content.gamesButton));
  
    	assertTrue("Asserts that the user is redirected to the correct modules page",
    			content.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules/13/categories/0"));
    	assertTrue("Asserts that the user is on the modules page",
    			content.verifyElementsVisibility(content.getInitialVisibleElements()));
    	
    	loginPage = home.header.logout();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    }
    
    @Test
    public void testLinkModuleNameSocialStudies(){
    	home = (StudentHomePageNew) loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	home.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/content/SocialStudies");
    	PGONewContentSelectionPage content = new PGONewContentSelectionPage(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(content.gamesButton));
  
    	assertTrue("Asserts that the user is redirected to the correct modules page",
    			content.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules/5/categories/0"));
    	assertTrue("Asserts that the user is on the modules page",
    			content.verifyElementsVisibility(content.getInitialVisibleElements()));
    	
    	loginPage = home.header.logout();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    }
    
    @Test
    public void testLinkModuleNameEstudiosSociales(){
    	home = (StudentHomePageNew) loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	home.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/content/EstudiosSociales");
    	PGONewContentSelectionPage content = new PGONewContentSelectionPage(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(content.gamesButton));
  
    	assertTrue("Asserts that the user is redirected to the correct modules page",
    			content.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules/14/categories/0"));
    	assertTrue("Asserts that the user is on the modules page",
    			content.verifyElementsVisibility(content.getInitialVisibleElements()));
    	
    	loginPage = home.header.logout();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    }
    
    @Test
    public void testLinkModuleNameDinosaurs(){
    	home = (StudentHomePageNew) loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	home.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/content/Dinosaurs");
    	PGONewContentSelectionPage content = new PGONewContentSelectionPage(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(content.gamesButton));
  
    	assertTrue("Asserts that the user is redirected to the correct modules page",
    			content.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules/7/categories/0"));
    	assertTrue("Asserts that the user is on the modules page",
    			content.verifyElementsVisibility(content.getInitialVisibleElements()));
    	
    	loginPage = home.header.logout();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    }
    
    @Test
    public void testURLWithoutCategory(){
    	home = (StudentHomePageNew) loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	home.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/articles/135");
    	PGONewArticlePage article = new PGONewArticlePage(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.citeButton));
  
    	assertTrue("Asserts that the user is redirected to the correct modules page",
    			article.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/9/articles/135"));
    	assertTrue("Asserts that the user is on the modules page",
    			article.verifyElementsVisibility(article.initialVisibleElements));
    	
    	loginPage = home.header.logout();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    }
    
    @Test
    public void testURLWithoutModule(){
    	home = (StudentHomePageNew) loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	home.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/categories/9");
    	PGONewContentSelectionPage content = new PGONewContentSelectionPage(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(content.gamesButton));
  
    	assertTrue("Asserts that the user is redirected to the correct modules page",
    			content.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/9"));
    	assertTrue("Asserts that the user is on the modules page",
    			content.verifyElementsVisibility(content.getInitialVisibleElements()));
    	
    	loginPage = home.header.logout();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    }
    
    @Test
    public void testArticleURLWithoutModuleOrCategory(){
    	home = (StudentHomePageNew) loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	home.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/articles/135");
    	PGONewArticlePage article = new PGONewArticlePage(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.citeButton));
  
    	assertTrue("Asserts that the user is redirected to the correct modules page",
    			article.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/9/articles/135"));
    	assertTrue("Asserts that the user is on the modules page",
    			article.verifyElementsVisibility(article.initialVisibleElements));
    	
    	loginPage = home.header.logout();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    }
    
    @Test
    public void testURLWithSlashNext(){
    	home = (StudentHomePageNew) loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	home.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/next/Animals");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
  
    	assertTrue("Asserts that the user is redirected to the select modules page",
    			home.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules"));
    	assertTrue("Asserts that the user is on the modules page",
    			home.verifyElementsVisibility(home.getInitialVisibleElements()));
    	
    	loginPage = home.header.logout();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    }
    
    @Test
    public void testArticleURLWithTab(){
    	home = (StudentHomePageNew) loginPage.Login(user);
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Animals")));
    	
    	home.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/articles/135/1");
    	PGONewArticlePage article = new PGONewArticlePage(user);
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.citeButton));
  
    	assertTrue("Asserts that the user is redirected to the correct modules page",
    			article.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/9/articles/135"));
    	assertTrue("Asserts that the user is on the modules page",
    			article.verifyElementsVisibility(article.initialVisibleElements));
    	
    	home.getDriver().get("https://"+user.getEnvironment()+".pebblego.com/modules/1/articles/135/5");
    	
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(article.citeButton));
  
    	assertTrue("Asserts that the user is redirected to the correct modules page",
    			article.getDriver().getCurrentUrl().equals("https://"+user.getEnvironment()+".pebblego.com/modules/1/categories/9/articles/135"));
    	assertTrue("Asserts that the user is on the modules page",
    			article.verifyElementsVisibility(article.initialVisibleElements));
    	
    	loginPage = home.header.logout();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(loginPage.logo));
    }
}
