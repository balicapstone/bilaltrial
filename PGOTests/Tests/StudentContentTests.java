package Tests;


import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import AdminResources.DisableArticlesDatabasePage;
import AdminResources.DisableArticlesTogglePage;
import AdminResources.TeacherHomeScreen;
import PGOPages.LoginPage;
import PGOStudentResources.AnimalesContentPage;
import PGOStudentResources.AnimalsContentPage;
import PGOStudentResources.ArticlePage;
import PGOStudentResources.BiografiasContentPage;
import PGOStudentResources.BiographiesContentPage;
import PGOStudentResources.DinosaursContentPage;
import PGOStudentResources.ScienceContentPage;
import PGOStudentResources.SocialStudiesContentPage;
import PGOStudentResources.StudentHomePage;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.User;
import UserClasses.UserInfo;

public class StudentContentTests{
    static LoginPage loginPage;
    static LoginPage studentLogin;
    static StudentHomePage home;
    static User teacher; 
    static User student;
    
    @BeforeClass
    public static void executeBefore(){
        teacher = new User(UserInfo.GARAGETEACHER);
        
    	loginPage = new LoginPage(teacher);
    	
    	try{
      //   	togglePages();
        	
        	student = teacher.setCredentials(UserInfo.GARAGESTUDENT);
        	
        	loginPage = new LoginPage(student);
        	
       // 	student.customWait().until(ExpectedConditions.elementToBeClickable(loginPage.loginModalButton));
        	
    		home = (StudentHomePage) loginPage.Login(student);
    		
    		home.waitImplicitly(10);
    	} catch(Exception e){
    		teacher.getWatcher().TakeScreenshot("FailureToRunExecuteBeforeForStudentContentTests");
    	}
    }

	private static void togglePages() {
		TeacherHomeScreen teacherhome = (TeacherHomeScreen)loginPage.Login(teacher);
		DisableArticlesDatabasePage databasePage = teacherhome.clickDisableArticlesButton();
		
		DisableArticlesTogglePage togglePage = databasePage.clickAnimalsDatabaseLink();
		togglePage.enableAll();
		teacher.customWait().until(ExpectedConditions.attributeToBe(togglePage.getLastToggleDiv(), "class", 
				"ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all"));
		togglePage.waitImplicitly(2);
		togglePage.clickDatabaseBreadcrumb();
		databasePage = togglePage.clickSelectDatabaseButton();
		
		togglePage = databasePage.clickBiographiesDatabaseLink();
		togglePage.enableAll();
		teacher.customWait().until(ExpectedConditions.attributeToBe(togglePage.getLastToggleDiv(), "class", 
				"ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all"));
		togglePage.waitImplicitly(2);
		togglePage.clickDatabaseBreadcrumb();
		databasePage = togglePage.clickSelectDatabaseButton();

		togglePage = databasePage.clickScienceDatabaseLink();
		togglePage.enableAll();
		teacher.customWait().until(ExpectedConditions.attributeToBe(togglePage.getLastToggleDiv(), "class", 
				"ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all"));
		togglePage.waitImplicitly(2);
		togglePage.clickDatabaseBreadcrumb();
		databasePage = togglePage.clickSelectDatabaseButton();
		
		togglePage = databasePage.clickSocialStudiesDatabaseLink();
		togglePage.enableAll();
		teacher.customWait().until(ExpectedConditions.attributeToBe(togglePage.getLastToggleDiv(), "class", 
				"ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all"));
		togglePage.waitImplicitly(2);
		togglePage.clickDatabaseBreadcrumb();
		databasePage = togglePage.clickSelectDatabaseButton();

		togglePage = databasePage.clickDinosaursDatabaseLink();
		togglePage.enableAll();
		teacher.customWait().until(ExpectedConditions.attributeToBe(togglePage.getLastToggleDiv(), "class", 
				"ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all"));
		togglePage.waitImplicitly(2);
		togglePage.clickDatabaseBreadcrumb();
		databasePage = togglePage.clickSelectDatabaseButton();
		
		togglePage = databasePage.clickAnimalesDatabaseLink();
		togglePage.enableAll();
		teacher.customWait().until(ExpectedConditions.attributeToBe(togglePage.getLastToggleDiv(), "class", 
				"ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all"));
		togglePage.waitImplicitly(2);
		togglePage.clickDatabaseBreadcrumb();
		
		loginPage = togglePage.clickLogoutButton();
	}
    
    @AfterClass
    public static void executeAfter(){
    	student.quit();
    }
	    
    
    @Rule
    public TestWatcher restart = new TestWatcher(){
    	@Override
    	public void failed(Throwable e, Description description){
    		try{
    			student.getWatcher().TakeScreenshot(description.getDisplayName());
    		} catch(Exception t){
    			System.out.print("There was a failure while trying to take a screenshot for " + description.getDisplayName());
    		}
    		
    		student.quit();
    		
    		student.makeNewDriver();
    		
    		studentLogin = new LoginPage(student);
    		home = (StudentHomePage) studentLogin.Login(student);
    		home.waitImplicitly(10);

    	}
    };
	
/*	@Rule
	public Retry retry = new Retry(3);
	*/
    
    @Test
    @Category(SmokeTests.class)
	public void testAllAnimalContentPresent(){
		AnimalsContentPage animals = home.clickAnimalsModule();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(animals.questionOfTheDayLink));
		
		assertTrue("Assert that all functionality is present when loading the Animals Database",
				animals.verifyElementsVisibility(animals.getInitialVisibleElements()));
		assertTrue("Assert that all Animals categories are present when loading the Animals Database",
				animals.isAllContentPresent(animals.getInitialContentLinks()));
		
		home = animals.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalesModule));
	}
	
    @Test
	public void testAllScienceContentPresent(){
		ScienceContentPage science = home.clickScienceModule();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(science.questionOfTheDayLink));
		
		assertTrue("Assert that all functionality is present when loading the Science Database",
				science.verifyElementsVisibility(science.getInitialVisibleElements()));
		assertTrue("Assert that all Science categories are present when loading the Science Database",
				science.isAllContentPresent(science.getInitialContentLinks()));

		home = science.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalesModule));
	}
	
    @Test
	public void testAllBiographiesContentPresent(){
		BiographiesContentPage biographies = home.clickBiographiesModule();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(biographies.questionOfTheDayLink));
		
		assertTrue("Assert that all functionality is present when loading the Biographies Database",
				biographies.verifyElementsVisibility(biographies.getInitialVisibleElements()));
		assertTrue("Assert that all Biographies categories are present when loading the Biographies Database",
				biographies.isAllContentPresent(biographies.getInitialContentLinks()));
		
		home = biographies.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalesModule));
	}
	
    @Test
	public void testAllSocialStudiesContentPresent(){
		SocialStudiesContentPage socialStudies = home.clickSocialStudiesModule();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(socialStudies.questionOfTheDayLink));
		
		assertTrue("Assert that all functionality is present when loading the Social Studies Database",
				socialStudies.verifyElementsVisibility(socialStudies.getInitialVisibleElements()));
		assertTrue("Assert that all Social Studies categories are present when loading the Social Studies Database",
				socialStudies.isAllContentPresent(socialStudies.getInitialContentLinks()));
		
		home = socialStudies.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalesModule));
	}
	
    @Test
	public void testAllDinosaursContentPresent(){
		DinosaursContentPage dinosaurs = home.clickDinosaursModule();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dinosaurs.questionOfTheDayLink));
		
		assertTrue("Assert that all functionality is present when loading the Dinosaur Database",
				dinosaurs.verifyElementsVisibility(dinosaurs.getInitialVisibleElements()));
		assertTrue("Assert that all Dinosaurs categories are present when loading the Dinosaurs Database",
				dinosaurs.isAllContentPresent(dinosaurs.getInitialContentLinks()));
		home = dinosaurs.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalesModule));
	}
	
    @Test
    @Category(SmokeTests.class)
	public void testLogOutButton(){
    //	home = (StudentHomePage) loginPage.Login(student);
    	home.click(home.header.capstoneDropdown);
    	
		loginPage = home.clickLogOutButton();

		home.waitImplicitly(5);
		assertTrue("Assert that a user can click the Log Out button and is redirected to the login page",
				loginPage.verifyElementsVisibility(loginPage.getInitialVisibleElementsLogin()));
		home = (StudentHomePage) loginPage.Login(student);
	}
	
    @Test
    @Category(SmokeTests.class)
	public void testPebbleGoLogo(){
		home.clickPebbleGoLogo();
		
		assertTrue("Assert that the PebbleGo Logo redirects the user to the database select page",
				home.verifyElementsVisibility(home.getInitialVisibleElements()));
	}
	
    @Test
    @Category(SmokeTests.class)
	public void testHomeButton(){
		home.clickHomeBreadcrumb();
		
		assertTrue("Assert that the home breadcrumb works on the database select page without breaking",
				home.verifyElementsVisibility(home.getInitialVisibleElements()));
	}
	
    //working
    @Test
    public void testTexasButtonBiographies(){
	//	home.clickHamburgerButton();
	//	student.customWait().until(ExpectedConditions.elementToBeClickable(home.getTopMenu().animalsButton));
		
		BiographiesContentPage biographies = home.clickBiographiesModule();
		assertTrue("Asserts that the Hamburger Button Top Menu Animal Button redirects to the Animal database",
				biographies.isAllContentPresent(biographies.getInitialContentLinks()));
		
		biographies.ClickTexasToggleOnButton();
		student.customWait().until(ExpectedConditions.visibilityOfElementLocated(biographies.texasToggleOffButton));
		
		assertTrue("Asserts that texas mode is on and the correct categories are off", 
				!biographies.isContentPresent("Athletes") &&
				!biographies.isContentPresent("Royalty and Nobility") &&
				!biographies.isContentPresent("Asian Americans") &&
				!biographies.isContentPresent("Supreme Court Justices") &&
				biographies.isAllContentPresent(biographies.getTexasContentLinks()));
		
		home = biographies.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
    }
    
 //working
    @Test
    public void testTexasButtonBiografias(){
		student.customWait().until(ExpectedConditions.elementToBeClickable(home.animalesModule));
		
		BiografiasContentPage biografias = home.clickBiografiasModule();
		assertTrue("Asserts that the Hamburger Button Top Menu Animal Button redirects to the Animal database",
				biografias.isAllContentPresent(biografias.getInitialContentLinks()));
		
		biografias.ClickTexasToggleOnButton();
		student.customWait().until(ExpectedConditions.visibilityOfElementLocated(biografias.texasToggleOffButton));
		
		assertTrue("Asserts that texas mode is on and the correct categories are off", 
				!biografias.isContentPresent("Deportistas") &&
				!biografias.isContentPresent("Realeza y Nobleza") &&
				!biografias.isContentPresent("Estadounidenses de Origen Asiático") &&
				!biografias.isContentPresent("Jueces de la Corta Suprema") &&
				biografias.isAllContentPresent(biografias.getTexasContentLinks()));
		
		biografias.ClickTexasToggleOffButton();
		home = biografias.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
    }

    
    @Test
    @Ignore
	public void testTopModuleAnimalsButton(){
		home.clickHamburgerButton();
		student.customWait().until(ExpectedConditions.elementToBeClickable(home.getTopMenu().animalsButton));
		
		AnimalsContentPage animals = home.getTopMenu().clickAnimalsButton();
		assertTrue("Asserts that the Hamburger Button Top Menu Animal Button redirects to the Animal database",
				animals.isAllContentPresent(animals.getInitialContentLinks()));
		
		home = animals.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
	}
	
    @Test
    @Ignore
	public void testTopModuleScienceButton(){
		home.clickHamburgerButton();
		student.customWait().until(ExpectedConditions.elementToBeClickable(home.getTopMenu().animalsButton));
		
		ScienceContentPage science = home.getTopMenu().clickScienceButton();
		assertTrue("Asserts that the Hamburger Button Top Menu Science Button redirects to the Science database",
				science.isAllContentPresent(science.getInitialContentLinks()));
		
		home = science.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
	}
	
    @Test
    @Ignore
	public void testTopModuleBiographiesButton(){
		home.clickHamburgerButton();
		student.customWait().until(ExpectedConditions.elementToBeClickable(home.getTopMenu().animalsButton));
	
		BiographiesContentPage biographies = home.getTopMenu().clickBiographiesButton();
		assertTrue("Asserts that the Hamburger Button Top Menu Biographies Button redirects to the Biographies database",
				biographies.isAllContentPresent(biographies.getInitialContentLinks()));
		
		home = biographies.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
	}
	
    @Test
    @Ignore
	public void testTopModuleSocialStudiesButton(){
		home.clickHamburgerButton();
		student.customWait().until(ExpectedConditions.elementToBeClickable(home.getTopMenu().animalsButton));
		
		SocialStudiesContentPage socialStudies = home.getTopMenu().clickSocialStudiesButton();
		assertTrue("Asserts that the Hamburger Button Top Menu Social Studies Button redirects to the Social Studies database",
				socialStudies.isAllContentPresent(socialStudies.getInitialContentLinks()));
		
		home = socialStudies.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
	}
	
    @Test
    @Ignore
    @Category(SmokeTests.class)
	public void testTopModuleDinosaursButton(){
		home.clickHamburgerButton();
		student.customWait().until(ExpectedConditions.elementToBeClickable(home.getTopMenu().animalsButton));
		
		DinosaursContentPage dinosaurs = home.getTopMenu().clickDinosaursButton();
		assertTrue("Asserts that the Hamburger Button Top Menu Dinosaurs Button redirects to the Dinosaurs database",
				dinosaurs.isAllContentPresent(dinosaurs.getInitialContentLinks()));	
		
		home = dinosaurs.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
	}
	
	// working 
    @Test
	public void testClickHomeButtonFromAnimals(){
		AnimalsContentPage animals = home.clickAnimalsModule();
		student.customWait().until(ExpectedConditions.elementToBeClickable(animals.questionOfTheDayLink));
		
		assertTrue("Asserts that the user is now on the Animals Database",
				animals.isAllContentPresent(animals.getInitialContentLinks()));
		home = animals.clickHomeBreadcrumb();
		assertTrue("Asserts that the Home button in the Animals Database redirects to the Database select page",
				home.verifyElementsVisibility(home.getInitialVisibleElements()));
		
		home = animals.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
	}
	
    // working
    @Test
	public void testClickHomeButtonFromScience(){
		ScienceContentPage science = home.clickScienceModule();
		student.customWait().until(ExpectedConditions.elementToBeClickable(science.questionOfTheDayLink));
		
		assertTrue("Asserts that the user is now on the Science Database",
				science.isAllContentPresent(science.getInitialContentLinks()));
		home = science.clickHomeBreadcrumb();
		assertTrue("Asserts that the Home button in the Science Database redirects to the Database select page",
				home.verifyElementsVisibility(home.getInitialVisibleElements()));
		
		home = science.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
	}
	
    // working
    @Test
	public void testClickHomeButtonFromBiographies(){
		BiographiesContentPage biographies = home.clickBiographiesModule();
		student.customWait().until(ExpectedConditions.elementToBeClickable(biographies.questionOfTheDayLink));
		
		assertTrue("Asserts that the user is now on the Biographies Database",
				biographies.isAllContentPresent(biographies.getInitialContentLinks()));
		home = biographies.clickHomeBreadcrumb();
		assertTrue("Asserts that the Home button in the Biographies Database redirects to the Database select page",
				home.verifyElementsVisibility(home.getInitialVisibleElements()));
		
		home = biographies.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
	}
	
    // working
    @Test
	public void testClickHomeButtonFromSocialStudies(){
		SocialStudiesContentPage socialStudies = home.clickSocialStudiesModule();
		student.customWait().until(ExpectedConditions.elementToBeClickable(socialStudies.questionOfTheDayLink));
		
		assertTrue("Asserts that the user is now on the Social Studies Database",
				socialStudies.isAllContentPresent(socialStudies.getInitialContentLinks()));
		home = socialStudies.clickHomeBreadcrumb();
		assertTrue("Asserts that the Home button in the Social Studies Database redirects to the Database select page",
				home.verifyElementsVisibility(home.getInitialVisibleElements()));
	
		home = socialStudies.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
	}
	
    // working
    @Test
	public void testClickHomeButtonFromDinosaurs(){
		DinosaursContentPage dinosaurs = home.clickDinosaursModule();
		student.customWait().until(ExpectedConditions.elementToBeClickable(dinosaurs.questionOfTheDayLink));
		
		assertTrue("Asserts that the user is now on the Dinosaurs Database",
				dinosaurs.isAllContentPresent(dinosaurs.getInitialContentLinks()));
		home = dinosaurs.clickHomeBreadcrumb();
		assertTrue("Asserts that the Home button in the Dinosaurs Database redirects to the Database select page",
				home.verifyElementsVisibility(home.getInitialVisibleElements()));
		
		home = dinosaurs.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
	}
    
    
    // working
    @Test
    public void testClickHomeButtonFromAnimales(){
    	AnimalesContentPage animales = home.clickAnimalesModule();
    	student.customWait().until(ExpectedConditions.elementToBeClickable(animales.questionOfTheDayLink));
    	
    	assertTrue("Asserts that the user is now on the Animales Database",
    			animales.isAllContentPresent(animales.getInitialContentLinks()));
    	home = animales.clickHomeBreadcrumb();
    	assertTrue("Asserts that the Home button in the Animales Database redirects to the Database select page",
    			home.verifyElementsVisibility(home.getInitialVisibleElements()));
		
		home = animales.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
    }
	
    // working
    @Test
	public void testDatabaseBreadcrumbAnimals(){
		AnimalsContentPage animals = home.clickAnimalsModule();
		student.customWait().until(ExpectedConditions.elementToBeClickable(animals.questionOfTheDayLink));
		
		animals.clickCategoryByText("Amphibians");
		assertTrue("Assert that the user is in the Amphibians category in the Animals Database",
				animals.isAllContentPresent(animals.getAmphibiansContent()));
		animals.clickDatabaseBreadcrumb();
		
		assertTrue("Assert that the user is redirected to the Animals database by clicking the Database breadcrumb",
				animals.isAllContentPresent(animals.getInitialContentLinks()));
		
		home = animals.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
	}
	
    // working
    @Test
    public void testDatabaseBreadcrumbAnimales(){
    	AnimalesContentPage animales = home.clickAnimalesModule();
    	student.customWait().until(ExpectedConditions.elementToBeClickable(animales.questionOfTheDayLink));
		
		animales.clickCategoryByText("Anfibios");
		assertTrue("Assert that the user is in the Anfibios category in the Animales Database",
				animales.isAllContentPresent(animales.getAnfibiosContent()));
		animales.clickDatabaseBreadcrumb();
		
		assertTrue("Assert that the user is redirected to the Animales database by clicking the Database breadcrumb",
				animales.isAllContentPresent(animales.getInitialContentLinks()));
		home = animales.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
    }
    
 // working
    @Test
	public void testDatabaseBreadcrumbScience(){
    	ScienceContentPage science = home.clickScienceModule();
    	student.customWait().until(ExpectedConditions.elementToBeClickable(science.questionOfTheDayLink));
    	
		science.clickCategoryByText("Earth and Space Sciences");
		assertTrue("Assert that the user is in the Earth and Space Sciences category in the Science Database",
				science.isAllContentPresent(science.getEarthAndSpaceSciencesContent()));
		science.clickDatabaseBreadcrumb();
		
		assertTrue("Assert that the user is redirected to the Science database by clicking the Database breadcrumb",
				science.isAllContentPresent(science.getInitialContentLinks()));
		
		home = science.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
	}
	
 // working
    @Test
	public void testDatabaseBreadcrumbBiographies(){
    	BiographiesContentPage biographies = home.clickBiographiesModule();
    	student.customWait().until(ExpectedConditions.elementToBeClickable(biographies.questionOfTheDayLink));
    	
		biographies.clickCategoryByText("Authors and Artists");
		assertTrue("Assert that the user is in the Authors and Artists category in the Biographies Database",
				biographies.isAllContentPresent(biographies.getAuthorsAndArtistsContent()));
		biographies.clickDatabaseBreadcrumb();
		
		assertTrue("Assert that the user is redirected to the Biographies database by clicking the Database breadcrumb",
				biographies.isAllContentPresent(biographies.getInitialContentLinks()));
		
		home = biographies.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
	}
	
 // working
    @Test
    @Category(SmokeTests.class)
	public void testDatabaseBreadcrumbSocialStudies(){
		SocialStudiesContentPage socialStudies = home.clickSocialStudiesModule();
		student.customWait().until(ExpectedConditions.elementToBeClickable(socialStudies.questionOfTheDayLink));
		
		socialStudies.clickCategoryByText("My World");
		assertTrue("Assert that the user is in the My World category in the Social Studies Database",
				socialStudies.isAllContentPresent(socialStudies.getMyWorldContent()));
		socialStudies.clickDatabaseBreadcrumb();
		
		assertTrue("Assert that the user is redirected to the Social Studies database by clicking the Database breadcrumb",
				socialStudies.isAllContentPresent(socialStudies.getInitialContentLinks()));

		home = socialStudies.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
    }
	
 // working
    @Test
	public void testDatabaseBreadcrumbDinosaurs(){
		DinosaursContentPage dinosaurs = home.clickDinosaursModule();
		student.customWait().until(ExpectedConditions.elementToBeClickable(dinosaurs.questionOfTheDayLink));
		
		dinosaurs.clickCategoryByText("Beaked Dinosaurs");
		assertTrue("Assert that the user is in the Beaked Dinosaurs category in the Dinosaurs Database",
				dinosaurs.isAllContentPresent(dinosaurs.getBeakedDinosaursContent()));
		dinosaurs.clickDatabaseBreadcrumb();
		
		assertTrue("Assert that the user is redirected to the Dinosaurs database by clicking the Database breadcrumb",
				dinosaurs.isAllContentPresent(dinosaurs.getInitialContentLinks()));
		
		home = dinosaurs.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
	}
	
    // working
    @Test //Pets and Farm Animals
    public void testFirstContentBreadcrumbAnimals(){
    	AnimalsContentPage animals = home.clickAnimalsModule();
    	student.customWait().until(ExpectedConditions.elementToBeClickable(animals.questionOfTheDayLink));
    	
    	animals.clickCategoryByText("Pets and Farm Animals");
    	assertTrue("Assert that the user is on the Pets and Farm Animals category page",
    			animals.isAllContentPresent(animals.getPetsAndFarmAnimalsContent()));
    	animals.clickCategoryByText("Pets");
    	
    	assertTrue("Assert that the user is on the Pets category page",
    			animals.isAllContentPresent(animals.getPetsContent()));
    			
    	animals.clickBreadcrumbByIndex(1);
    	
    	assertTrue("Assert that the user can navigate back to the Pets and Farm Animals page using the first breadcrumb",
    			animals.isAllContentPresent(animals.getPetsAndFarmAnimalsContent()));
    	
		home = animals.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
    }
    
    // working
    @Test //Pets
    public void testSecondContentBreadcrumbAnimals(){
    	AnimalsContentPage animals = home.clickAnimalsModule();
    	student.customWait().until(ExpectedConditions.elementToBeClickable(animals.questionOfTheDayLink));
    	
    	animals.clickCategoryByText("Pets and Farm Animals");
    	animals.clickCategoryByText("Pets");
    	animals.clickCategoryByText("Dogs");
    	
    	assertTrue("Assert that the user can navigate to the Dogs category page",
    			animals.isAllContentPresent(animals.getDogsContent()));

    	animals.clickBreadcrumbByIndex(2);
    	
    	assertTrue("Assert that the user can navigate back to the Pets Category page from the Dogs cateory page using the second breadcrumb",
    			animals.isAllContentPresent(animals.getPetsContent()));

		home = animals.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
    }
    
    // working
    @Test //Dogs
    @Category(SmokeTests.class)
    public void testThirdContentBreadcrumbAnimals(){
    	AnimalsContentPage animals = home.clickAnimalsModule();
    	student.customWait().until(ExpectedConditions.elementToBeClickable(animals.questionOfTheDayLink));
    	
    	animals.clickCategoryByText("Pets and Farm Animals");
    	animals.clickCategoryByText("Pets");
    	animals.clickCategoryByText("Dogs");
    	
    	assertTrue("Assert that the user can navigate to the Dogs category page",
    			animals.isAllContentPresent(animals.getDogsContent()));
    	
    	ArticlePage pugs = animals.clickArticleByText("Pugs");
    	student.customWait().until(ExpectedConditions.visibilityOfElementLocated(pugs.tabFive));
    	
    	pugs.clickBreadcrumbByIndex(3);
    	student.customWait().until(ExpectedConditions.elementToBeClickable(animals.questionOfTheDayLink));
    	
    	assertTrue("Assert that the third breadcrumb brings the user back to the Dogs category page",
    			animals.isAllContentPresent(animals.getDogsContent()));
    	
		home = animals.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
    }
    
    @Test //Earth and Space Science
    public void testFirstContentBreadcrumbScience(){
    	ScienceContentPage science = home.clickScienceModule();
    	student.customWait().until(ExpectedConditions.elementToBeClickable(science.questionOfTheDayLink));
    	science.clickCategoryByText("Earth and Space Sciences");
    	
    	assertTrue("Assert that the user is brought back to the Earth and Space Sciences from the first breadcrumb on the Earth and Space Sciences category page",
    			science.isAllContentPresent(science.getEarthAndSpaceSciencesContent()));
    	science.clickCategoryByText("Earth Science");
    	
    	assertTrue("Assert that the user is on the Earth Science category page",
    			science.isAllContentPresent(science.getEarthScienceContent()));

    	science.clickBreadcrumbByIndex(1);
    	student.customWait().until(ExpectedConditions.elementToBeClickable(science.questionOfTheDayLink));
    	
    	assertTrue("Assert that the user is redirected to the Earth and Space Science category page from the first breadcrumb on the Earth category page",
    			science.isAllContentPresent(science.getEarthAndSpaceSciencesContent()));

		home = science.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
    }
    
    @Test //Earth Science
    public void testSecondContentBreadcrumbScience(){
    	ScienceContentPage science = home.clickScienceModule();
    	student.customWait().until(ExpectedConditions.elementToBeClickable(science.questionOfTheDayLink));
    	
    	science.clickCategoryByText("Earth and Space Sciences");
    	science.clickCategoryByText("Earth Science");
   	
    	assertTrue("Assert that the user is brought back to the Earch Science page when the second breadcrumb is clicked from the Earth Science page",
    			science.isAllContentPresent(science.getEarthScienceContent()));
 
    	science.clickCategoryByText("All About Water");
    	student.customWait().until(ExpectedConditions.elementToBeClickable(science.questionOfTheDayLink));
    	
    	assertTrue("Assert that the user can navigate to the All About Water category page",
    			science.isAllContentPresent(science.getAllAboutWaterContent()));
      	
    	science.clickBreadcrumbByIndex(2);
      	student.customWait().until(ExpectedConditions.elementToBeClickable(science.questionOfTheDayLink));
      	
    	assertTrue("Assert that the user is redirected to the Earth Science Category page from the second breadcrumb of the All About Water page",
    			science.isAllContentPresent(science.getEarthScienceContent()));
		home = science.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule)); 	
    }
    
    @Test //All About Water 
    public void testThirdContentBreadcrumbScience(){
    	ScienceContentPage science = home.clickScienceModule();
    	student.customWait().until(ExpectedConditions.elementToBeClickable(science.questionOfTheDayLink));
    	science.clickCategoryByText("Earth and Space Sciences");
    	science.clickCategoryByText("Earth Science");
    	science.clickCategoryByText("All About Water");
    	
    	assertTrue("Asserts that the user can navigate to the All About Water page",
    			science.isAllContentPresent(science.getAllAboutWaterContent()));
    	
    	ArticlePage lakes = science.clickArticleByText("Lakes");
    	student.customWait().until(ExpectedConditions.elementToBeClickable(lakes.activityButton));
    	
    	lakes.clickBreadcrumbByIndex(3);
    	student.customWait().until(ExpectedConditions.elementToBeClickable(science.questionOfTheDayLink));
    	
    	assertTrue("Asserts that clicking the third breadcrumb on the All About Water page directs the user to the same page",
    			science.isAllContentPresent(science.getAllAboutWaterContent()));
    
		home = science.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule)); 
    }
    
    @Test //Authors and Artists
    public void testFirstContentBreadcrumbBiographies(){
    	BiographiesContentPage biographies = home.clickBiographiesModule();
    	student.customWait().until(ExpectedConditions.elementToBeClickable(biographies.questionOfTheDayLink));
    	
    	biographies.clickCategoryByText("Authors and Artists");
    	assertTrue("Asserts that the user can navigate to the Aruthors and Artists page",
    			biographies.isAllContentPresent(biographies.getAuthorsAndArtistsContent()));
    	
    	biographies.clickCategoryByText("Artists");
    	student.customWait().until(ExpectedConditions.elementToBeClickable(biographies.questionOfTheDayLink));
    	
    	assertTrue("Asserts that the user can navigate to the Artists page",
    			biographies.isAllContentPresent(biographies.getArtistsContent()));
    	biographies.clickBreadcrumbByIndex(1);
    	
    	assertTrue("Asserts that the first breadcrumb on the Artists page redirects to the Authors and Artists page",
    			biographies.isAllContentPresent(biographies.getAuthorsAndArtistsContent()));  	
    	
		home = biographies.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule)); 
    }
    
    @Test //Authors
    public void testSecondContentBreadcrumbBiographies(){
     	BiographiesContentPage biographies = home.clickBiographiesModule();
     	student.customWait().until(ExpectedConditions.elementToBeClickable(biographies.questionOfTheDayLink));
        biographies.clickCategoryByText("Authors and Artists");
        biographies.clickCategoryByText("Artists");
        student.customWait().until(ExpectedConditions.elementToBeClickable(biographies.questionOfTheDayLink));
        
        assertTrue("Asserts that the user can navigate to the Artists page",
        		biographies.isAllContentPresent(biographies.getArtistsContent()));
        
        ArticlePage seuss = biographies.clickArticleByText("Dr. Seuss");
    	student.customWait().until(ExpectedConditions.elementToBeClickable(seuss.activityButton));
        seuss.clickBreadcrumbByIndex(2);
        student.customWait().until(ExpectedConditions.elementToBeClickable(biographies.questionOfTheDayLink));
        
        assertTrue("Asserts that clicking the second breadcrumb on the Artists page redirects to the Artists page",
        		biographies.isAllContentPresent(biographies.getArtistsContent()));

		home = biographies.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule)); 
    }
    
    @Test //Culture and Awareness
    public void testFirstContentBreadcrumbSocialStudies(){
    	SocialStudiesContentPage socialStudies = home.clickSocialStudiesModule();
    	student.customWait().until(ExpectedConditions.elementToBeClickable(socialStudies.questionOfTheDayLink));
    	
    	socialStudies.clickCategoryByText("Culture and Awareness");
    	socialStudies.clickCategoryByText("Understanding Disabilities");
    	assertTrue("Asserts that a user can navigate to the Understanding Disabilities page",
    			socialStudies.isAllContentPresent(socialStudies.getUnderstandingDisabilitiesContent()));
    	socialStudies.clickBreadcrumbByIndex(1);
        student.customWait().until(ExpectedConditions.elementToBeClickable(socialStudies.questionOfTheDayLink));
    	
    	assertTrue("Asserts that clicking the first breadcrumb on the Understanding Disablities page redirects to the Culture and Awareness page",
    			socialStudies.isAllContentPresent(socialStudies.getCultureAndAwarenessContent()));

		home = socialStudies.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule)); 
    }
    
    @Test //Understanding Disabilities
    public void testSecondContentBreadcrumbSocialStudies(){
    	SocialStudiesContentPage socialStudies = home.clickSocialStudiesModule();
    	student.customWait().until(ExpectedConditions.elementToBeClickable(socialStudies.questionOfTheDayLink));
     	socialStudies.clickCategoryByText("Culture and Awareness");
     	socialStudies.clickCategoryByText("Understanding Disabilities");
     	assertTrue("Asserts the user can navigate to the Understanding Disabilities page",
     			socialStudies.isAllContentPresent(socialStudies.getUnderstandingDisabilitiesContent()));
     	
     	ArticlePage adhd = socialStudies.clickArticleByText("ADHD");
    	student.customWait().until(ExpectedConditions.elementToBeClickable(adhd.citeButton));
     	adhd.clickBreadcrumbByIndex(2);
     	
     	assertTrue("Asserts clicking the second breadcrumb on the Understand Disabilities page keeps the user on the Understand Disabilities page",
     			socialStudies.isAllContentPresent(socialStudies.getUnderstandingDisabilitiesContent()));
     	
     	home = socialStudies.clickHomeBreadcrumb();
     	student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule)); 
    }
    
    @Test //Beaked Dinosaurs
    public void testFirstContentBreadcrumbDinosaurs(){
    	DinosaursContentPage dinosaurs = home.clickDinosaursModule();
    	student.customWait().until(ExpectedConditions.elementToBeClickable(dinosaurs.questionOfTheDayLink));
    	
    	dinosaurs.clickCategoryByText("Beaked Dinosaurs");
    	dinosaurs.clickCategoryByText("Plated Dinosaurs");
    	assertTrue("Asserts that the user can navigate to the Plated Dinosaurs page",
    			dinosaurs.isAllContentPresent(dinosaurs.getPlatedDinosaursContent()));
    	dinosaurs.clickBreadcrumbByIndex(1);
    	student.customWait().until(ExpectedConditions.elementToBeClickable(dinosaurs.questionOfTheDayLink));
    	
    	assertTrue("Asserts that the first breadcrumb on the Beaked Dinosaurs page keeps the user on the Beaked Dinosaurs page",
    			dinosaurs.isAllContentPresent(dinosaurs.getBeakedDinosaursContent()));
    	
		home = dinosaurs.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
    }
    
    @Test //Bone-Heads
    public void testSecondContentBreadcrumbDinosaurs(){
    	DinosaursContentPage dinosaurs = home.clickDinosaursModule();
    	student.customWait().until(ExpectedConditions.elementToBeClickable(dinosaurs.questionOfTheDayLink));
    	
    	dinosaurs.clickCategoryByText("Beaked Dinosaurs");
    	dinosaurs.clickCategoryByText("Plated Dinosaurs");
    	assertTrue("Asserts that the user can navigate to the Plated Dinosaurs page",
    			dinosaurs.isAllContentPresent(dinosaurs.getPlatedDinosaursContent()));
    	
    	ArticlePage stegosaurus = dinosaurs.clickArticleByText("Stegosaurus");
    	student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(stegosaurus.activityButton));
    	
    	stegosaurus.clickBreadcrumbByIndex(2);
    	student.customWait().until(ExpectedConditions.elementToBeClickable(dinosaurs.questionOfTheDayLink));
    	
    	assertTrue("Asserts that the second breadcrumb on the Plated Dinosaurs page keeps the user on the Plated Dinosaurs page",
    			dinosaurs.isAllContentPresent(dinosaurs.getPlatedDinosaursContent()));

		home = dinosaurs.clickHomeBreadcrumb();
		student.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.animalsModule));
    }
    
    @Test
    @Ignore
  	public void testAllModulePicsAnimals() throws ClientProtocolException, IOException{
  		loginPage = new LoginPage(student);
  		JsonArray response = loginPage.getArticlesFromModule(1);
  		int x = 0;
  		
  		for(JsonElement j : response){
  			String image = j.getAsJsonObject().get("main_image").getAsString();
  			String audio = j.getAsJsonObject().get("menu_audio").getAsString();
  			
  			Boolean imageActive = loginPage.verifyImageActive(image);
  			Boolean audioActive = loginPage.verifyAudioActive(audio);
  			if(!(imageActive)){
  				System.out.println(image + " " + imageActive);	
  			}
  			if(!audioActive){
  				System.out.println(audio + " " + audioActive);	
  			}
  			x++;
  		}
  		System.out.println(x*2 + " assets checked");
  	}
  	
  	public void testAllModulePicsScience() throws ClientProtocolException, IOException{
  		loginPage = new LoginPage(student);
  		JsonArray response = loginPage.getArticlesFromModule(2);
  		int x = 0;
  		
  		for(JsonElement j : response){
  			String image = j.getAsJsonObject().get("main_image").getAsString();
  			String audio = j.getAsJsonObject().get("menu_audio").getAsString();
  			
  			Boolean imageActive = loginPage.verifyImageActive(image);
  			Boolean audioActive = loginPage.verifyAudioActive(audio);
  			if(!(imageActive)){
  				System.out.println(image + " " + imageActive);	
  			}
  			if(!audioActive){
  				System.out.println(audio + " " + audioActive);	
  			}
  			x++;
  		}
  		System.out.println(x*2 + " assets checked");
  	}
  	
  	public void testAllModulePicsBiographies() throws ClientProtocolException, IOException{
  		loginPage = new LoginPage(student);
  		JsonArray response = loginPage.getArticlesFromModule(3);
  		int x = 0;
  		
  		for(JsonElement j : response){
  			String image = j.getAsJsonObject().get("main_image").getAsString();
  			String audio = j.getAsJsonObject().get("menu_audio").getAsString();
  			
  			Boolean imageActive = loginPage.verifyImageActive(image);
  			Boolean audioActive = loginPage.verifyAudioActive(audio);
  			if(!(imageActive)){
  				System.out.println(image + " " + imageActive);	
  			}
  			if(!audioActive){
  				System.out.println(audio + " " + audioActive);	
  			}
  			x++;
  		}
  		System.out.println(x*2 + " assets checked");
  	}
  	
  	public void testAllModulePicsSocialStudies() throws ClientProtocolException, IOException{
  		loginPage = new LoginPage(student);
  		JsonArray response = loginPage.getArticlesFromModule(5);
  		int x = 0;
  		
  		for(JsonElement j : response){
  			String image = j.getAsJsonObject().get("main_image").getAsString();
  			String audio = j.getAsJsonObject().get("menu_audio").getAsString();
  			
  			Boolean imageActive = loginPage.verifyImageActive(image);
  			Boolean audioActive = loginPage.verifyAudioActive(audio);
  			if(!(imageActive)){
  				System.out.println(image + " " + imageActive);	
  			}
  			if(!audioActive){
  				System.out.println(audio + " " + audioActive);	
  			}
  			x++;
  		}
  		System.out.println(x*2 + " assets checked");
  	}
  	
  	public void testAllModulePicsDinosaurs() throws ClientProtocolException, IOException{
  		loginPage = new LoginPage(student);
  		JsonArray response = loginPage.getArticlesFromModule(7);
  		int x = 0;
  		
  		for(JsonElement j : response){
  			String image = j.getAsJsonObject().get("main_image").getAsString();
  			String audio = j.getAsJsonObject().get("menu_audio").getAsString();
  			
  			Boolean imageActive = loginPage.verifyImageActive(image);
  			Boolean audioActive = loginPage.verifyAudioActive(audio);
  			if(!(imageActive)){
  				System.out.println(image + " " + imageActive);	
  			}
  			if(!audioActive){
  				System.out.println(audio + " " + audioActive);	
  			}
  			x++;
  		}
  		System.out.println(x*2 + " assets checked");
  	}

  	public void testAllModulePicsAnimales() throws ClientProtocolException, IOException{
  		loginPage = new LoginPage(student);
  		JsonArray response = loginPage.getArticlesFromModule(8);
  		int x = 0;
  		
  		for(JsonElement j : response){
  			String image = j.getAsJsonObject().get("main_image").getAsString();
  			String audio = j.getAsJsonObject().get("menu_audio").getAsString();
  			
  			Boolean imageActive = loginPage.verifyImageActive(image);
  			Boolean audioActive = loginPage.verifyAudioActive(audio);
  			if(!(imageActive)){
  				System.out.println(image + " " + imageActive);	
  			}
  			if(!audioActive){
  				System.out.println(audio + " " + audioActive);	
  			}
  			x++;
  		}
  		System.out.println(x*2 + " assets checked");
  	}
  	
  	public void testAllModulePicsCiencia() throws ClientProtocolException, IOException{
  		loginPage = new LoginPage(student);
  		JsonArray response = loginPage.getArticlesFromModule(12);
  		int x = 0;
  		
  		for(JsonElement j : response){
  			String image = j.getAsJsonObject().get("main_image").getAsString();
  			String audio = j.getAsJsonObject().get("menu_audio").getAsString();
  			
  			Boolean imageActive = loginPage.verifyImageActive(image);
  			Boolean audioActive = loginPage.verifyAudioActive(audio);
  			if(!(imageActive)){
  				System.out.println(image + " " + imageActive);	
  			}
  			if(!audioActive){
  				System.out.println(audio + " " + audioActive);	
  			}
  			x++;
  		}
  		System.out.println(x*2 + " assets checked");
  	}
  	
  	public void testAllModulePicsBiografias() throws ClientProtocolException, IOException{
  		loginPage = new LoginPage(student);
  		JsonArray response = loginPage.getArticlesFromModule(13);
  		int x = 0;
  		
  		for(JsonElement j : response){
  			String image = j.getAsJsonObject().get("main_image").getAsString();
  			String audio = j.getAsJsonObject().get("menu_audio").getAsString();
  			
  			Boolean imageActive = loginPage.verifyImageActive(image);
  			Boolean audioActive = loginPage.verifyAudioActive(audio);
  			if(!(imageActive)){
  				System.out.println(image + " " + imageActive);	
  			}
  			if(!audioActive){
  				System.out.println(audio + " " + audioActive);	
  			}
  			x++;
  		}
  		System.out.println(x*2 + " assets checked");
  	}
  	 @Rule
     public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
           System.out.println("Starting test: " + description.getMethodName());
         
           
        }
     };
}
