package Tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.support.ui.ExpectedConditions;

import PGOPages.LoginPage;
import PGOPages.TrialPage;
import SharedClasses.ThirdPartyPage;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.User;
import UserClasses.UserInfo;

public class PGONewHomepageTests{
	static LoginPage home;
	static User user;
	
	@BeforeClass
	public static void executeBefore(){
		user = new User(UserInfo.GARAGESTUDENT);
		
		try{
			home = new LoginPage(user);
			user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.footerMenu.aboutUsLink));
		} catch(Exception e){
			user.getWatcher().TakeScreenshot("FailureToRunExecuteBeforeForHomepageTests");
		}
	}
	
	
	@AfterClass
	public static void executeAfter(){
		home.closeCurrentWindow();
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
    		
    		home.closeCurrentWindow();
    		user.makeNewDriver();
    		home = new LoginPage(user);
    		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(home.footerMenu.aboutUsLink));
    	}
    };
    
	@Rule
	public Retry retry = new Retry(3);
	
    /**
     * Tests that all Login Page elements are present.
     */
	@Test
	@Category(SmokeTests.class)
    public void testLoginPageElementsPresent(){
    	assertTrue("Asserts that all Login Screen Elements are present", 
    			home.verifyElementsVisibility(home.getInitialVisibleElements()));
	}
    
	@Test
    public void testFooterMenuElementsPresent(){
    	assertTrue("Asserts that all Footer Elements are present on the login screen",
    			home.verifyElementsVisibility(home.footerMenu.getInitialVisibleElements()));
    }
    
	@Test
    public void testNavigationBarElementsPresent(){
    	assertTrue("Asserts that all Navigation Bar Elements are present on the login screen",
    			home.navBar.verifyElementsVisibility(home.navBar.getInitialVisibleElements()));
    }
    
    /**
     * Tests that the Animals pop up element appears during hovering and the text is accurate.
     */
	@Test
    public void testHoverOverAnimals(){
    	home.hoverOverElement(home.animalsLink);
    	
    	assertEquals("Asserts that the Animals pop up element appears during hovering and that the text is accurate",
    			home.getElementText(home.animalsInfo), "Hundreds of articles that support animal classification, behavior, and habitat lessons!");
	}
	
	/**
	 * Tests that the Animales pop up element appears and the text is accurate.
	 */
	@Test
	public void testHoverOverAnimales(){
		home.hoverOverElement(home.animalesLink);

		assertEquals("Asserts that the Animales pop up element appears during hovering and that the text is accurate",
				home.getElementText(home.animalesInfo), "Cientos de artículos que respaldan lecciones sobre clasificación, comportamiento y hábitat de animales.");
	}
    
    /**
     * Tests that the Science pop up element appears during hovering and the text is accurate.
     */
	@Test
    public void testHoverOverScience(){
    	home.hoverOverElement(home.scienceLink);
    	
    	assertEquals("Asserts that the Science pop up element appears during hovering and that the text is accurate",
    			home.getElementText(home.scienceInfo), "Aligned to Next Generation Science Standards! Covers life, physical, earth and space sciences plus technology and engineering!");
    }
	
    /**
     * Tests that the Ciencia pop up element appears during hovering and the text is accurate.
     */
	@Test
    public void testHoverOverCienciaElement(){
    	home.hoverOverElement(home.cienciaLink);
    	
    	assertEquals("Asserts that the Ciencia pop up element appears during hovering and that the text is accurate",
    			home.getElementText(home.cienciaInfo), "Se ajusta a los estándares de ciencia de última generación. Cubre ciencias biológicas, físicas, de la Tierra y del espacio, además de tecnología e ingeniería.");
    }
    
    /**
     * Tests that the Biographies pop up element appears during hovering and the text is accurate.
     */
	@Test
    public void testHoverOverBiographies(){
    	home.hoverOverElement(home.biographiesLink);
    	
    	assertEquals("Asserts that the Biographies pop up element appears during hovering and that the text is accurate",
    			home.getElementText(home.biographiesInfo), "Research the lives of important inventors, explorers, African Americans, Hispanic Americans, women, and more!");
    }
	
	/**
     * Tests that the Biografias pop up element appears during hovering and the text is accurate.
     */
	@Test
    public void testHoverOverBiografias(){
    	home.hoverOverElement(home.biografiasLink);
    	
    	assertEquals("Asserts that the Biografias pop up element appears during hovering and that the text is accurate",
    			home.getElementText(home.biografiasInfo), "Para investigar la vida de importantes inventores, exploradores, afroamericanos, hispanoestadounidenses, mujeres y mucho más.");
    }
    
    /**
     * Tests that the Social Studies pop up element appears during hovering and the text is accurate.
     */
	@Test
    public void testHoverOverSocialStudies(){
		home.hoverOverElement(home.socialStudiesLink);
    	
    	assertEquals("Asserts that the Social Studies pop up element appears during hovering and that the text is accurate",
    			home.getElementText(home.socialStudiesInfo), "Learn about the world around you with the diverse array of topics including families, maps, and holidays!");
    }
	
	/**
     * Tests that the Estudios Sociales pop up element appears during hovering and the text is accurate.
     */
	@Test
    public void testHoverOverEstudiosSociales(){
		home.hoverOverElement(home.estudiosSocialesLink);
    	
    	assertEquals("Asserts that the Social Studies pop up element appears during hovering and that the text is accurate",
    			home.getElementText(home.estudiosSocialesInfo), "Para aprender sobre el mundo que nos rodea con una amplia variedad de temas, entre ellos: familias, mapas y días festivos.");
    }
    
    /**
     * Tests that the Dinosaurs pop up element appears during hovering and the text is accurate.
     */
	@Test
	@Category(SmokeTests.class)
    public void testHoverOverDinosaurs(){
		home.hoverOverElement(home.dinosaursLink);
    	
    	assertEquals("Asserts that the Dinosaurs pop up element appears during hovering and that the text is accurate",
    			home.getElementText(home.dinosaursInfo), "Keep up-to-date with the latest dinosaur science with articles that explore dinosaur habitats, behaviors and body types. Includes range maps and comparisons to present-day animals.");
    }
	
	/**
     * Tests that the Animals Content link opens a new window and that the correct animals.pdf document loads.
     * @throws InterruptedException
     */ 
	@Test
    public void testAnimalsPDF() throws InterruptedException{
    	assertTrue("Asserts that the Animals content modal links to the animals.pdf asset",
    			home.verifyLinkByElement(home.animalsLink)
    			&& home.verifyLinkContainsURLByElement(home.animalsLink, "pebblego.com/docs/animals.pdf"));
	}

    /**
     * Tests that the Animales Content link opens a new window and that the correct animales.pdf document loads.
     * @throws InterruptedException
     */
    @Test
    public void testAnimalesPDF() throws InterruptedException{
    	assertTrue("Asserts that the Animales content modal links to the animales.pdf asset",
    			home.verifyLinkByElement(home.animalesLink)
    			&& home.verifyLinkContainsURLByElement(home.animalesLink, "pebblego.com/docs/animales.pdf"));
    }
	
    /**
     * Tests that the Biographies Content link opens a new window and that the correct biographies.pdf document loads.
     * @throws InterruptedException
     */
    @Test
    public void testBiographiesPDF() throws InterruptedException{
    	assertTrue("Asserts that the Animals content modal links to the animals.pdf asset",
    			home.verifyLinkByElement(home.biographiesLink)
    			&& home.verifyLinkContainsURLByElement(home.biographiesLink, "pebblego.com/docs/biographies.pdf"));
    }
    
    /**
     * Tests that the Biographies Content link opens a new window and that the correct biographies.pdf document loads.
     * @throws InterruptedException
     */
    @Test
    public void testBiografiasPDF() throws InterruptedException{
    	assertTrue("Asserts that the biografias content modal links to the animals.pdf asset",
    			home.verifyLinkByElement(home.biografiasLink)
    			&& home.verifyLinkContainsURLByElement(home.biografiasLink, "pebblego.com/docs/biografias.pdf"));
    }
    
    /**
     * Tests that the Science Content link opens a new window and that the correct science_v2.pdf document loads.
     * @throws InterruptedException
     */
    @Test
    @Category(SmokeTests.class)
    public void testSciencePDF() throws InterruptedException{
    	assertTrue("Asserts that the Science content modal links to the science_v2.pdf asset",
    			home.verifyLinkByElement(home.scienceLink)
    			&& home.verifyLinkContainsURLByElement(home.scienceLink, "pebblego.com/docs/science_v2.pdf"));
    }

    /**
     * Tests that the Ciencia Content link opens a new window and that the correct science_v2.pdf document loads.
     * @throws InterruptedException
     */
    @Test
    @Category(SmokeTests.class)
    public void testCienciaPDF() throws InterruptedException{
    	assertTrue("Asserts that the Ciencia content modal links to the ciencia.pdf asset",
    			home.verifyLinkByElement(home.cienciaLink)
    			&& home.verifyLinkContainsURLByElement(home.cienciaLink, "pebblego.com/docs/ciencia.pdf"));
    }

    /**
     * Tests that the Social Studies Content link opens a new window and that the correct socialstudies.pdf document loads.
     * @throws InterruptedException
     */
    @Test
    public void testSocialStudiesPDF() throws InterruptedException{
    	assertTrue("Asserts that the Social Studies content modal links to the socialstudies.pdf asset",
    			home.verifyLinkByElement(home.socialStudiesLink) 
    			&& home.verifyLinkContainsURLByElement(home.socialStudiesLink, "pebblego.com/docs/socialstudies.pdf"));
    }
    
    /**
     * Tests that the Estudios Sociales Content link opens a new window and that the correct socialstudies.pdf document loads.
     * @throws InterruptedException
     */
    @Test
    public void testEstudiosSocialesPDF() throws InterruptedException{
    	assertTrue("Asserts that the Social Studies content modal links to the socialstudies.pdf asset",
    			home.verifyLinkByElement(home.estudiosSocialesLink) 
    			&& home.verifyLinkContainsURLByElement(home.estudiosSocialesLink, "pebblego.com/docs/estudiossociales.pdf"));
    }
    
    /**
     * Tests that the Dinosaurs Content link opens a new window and that the correct pebblego_dinos_articles.pdf document loads.
     * @throws InterruptedException
     */
    @Test
    public void testDinosaursLinkPDF() throws InterruptedException{
    	assertTrue("Asserts that the Dinosaurs content modal links to the pebblego_dinos_articles.pdf asset",
    			home.verifyLinkByElement(home.dinosaursLink)
    			&& home.verifyLinkContainsURLByElement(home.dinosaursLink, "pebblego.com/docs/dinosaurs/pebblego_dinos_articles.pdf"));
    }
    

    
    /**
     * Tests that the Free Trial link redirects to the Trial Page
     */
	@Test
	@Category(SmokeTests.class)
    public void testFreeTrialLink(){
		TrialPage trial = home.clickFreeTrialButton();
    	
    	assertTrue("Asserts that the Free Trial content link redirects to the trial page",
    			trial.verifyElementsVisibility(trial.getInitialVisibleElements()));
    	home = trial.navBar.clickPebbleGoLogo();
    }
    
    /**
     * Tests that the PebbleGo Next link redirects to the PebbleGo Next page and that the free-trial element is present.
     * @throws InterruptedException
     */
	@Test
	@Category(SmokeTests.class)
    public void testPebbleGoNextLink() throws InterruptedException{
    	ThirdPartyPage tPP = home.clickPebbleGoNextButton();
    	    	
    	assertTrue("Asserts that the PebbleGo Next link redirects to the PebbleGo Next page and that the free-trial element is present",
    			tPP.verifySameWindowNewPage("https://www.pebblegonext.com/"));
    	tPP.getDriver().get("https://www.pebblego.com");
    }
	
    @Test
    public void testHomePageBottomCommunityLink() throws InterruptedException{
    	assertTrue("Asserts the bottom communities link is correct",
    			home.verifyLinkContainsURLByElement(home.bottomCommunityLink, "http://community.mycapstone.com/"));
    }
    @Rule
    public TestRule watcher = new TestWatcher() {
       protected void starting(Description description) {
          System.out.println("Starting test: " + description.getMethodName());
        
          
       }
    };
}