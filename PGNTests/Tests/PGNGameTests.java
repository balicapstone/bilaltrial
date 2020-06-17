package Tests;

import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.support.ui.ExpectedConditions;
import Games.PGNChooseGamePage;
import Games.PGNJigsawPage;
import Games.PGNQuickMatchPage;
import Games.PGNWordScramblePage;
import Games.PGNZoomPage;
import PGNPages.LoginPage;
import PGNStudentResources.DatabasePage;
import PGNStudentResources.IndiansDatabaseHome;
import PGNStudentResources.ScienceDatabaseHome;
import PGNStudentResources.SocialStudiesDatabasePage;
import PGNStudentResources.StatesDatabaseHome;
import PGNStudentResources.StudentHomePage;
import TrueUserTests.TrueUser.PGNSmokeTest;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;

public class PGNGameTests{
	static LoginPage login;
	public static StudentHomePage home;
    static int waitTime = 5;
    public static User user;
    
 	@BeforeClass
    public static void executeBefore(){
 		user = new User(UserClasses.UserInfo.GARAGESTUDENT);
 		
 		login = new LoginPage(user);
 		home = (StudentHomePage) login.login(user);
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
    		user.makeNewDriver();
    		
    		login = new LoginPage(user);
    		home = (StudentHomePage) login.login(user);
    	}
    };
    	
	@Rule
	public Retry retry = new Retry(3);
	
	@Test
	@Category(PGNSmokeTest.class)
	public void testStatesZoom(){
		StatesDatabaseHome states = home.goToStates();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(states.gamesLink));
		
		PGNChooseGamePage games = states.openGames();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(games.stateZoomLink));
		
		PGNZoomPage zoom = games.clickZoom();		
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(zoom.startButton));
		zoom.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Zoom game elements load",
				zoom.verifyElementsVisibility(zoom.getInitialVisibleElements()));

		zoom.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(zoom.startButton));
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(zoom.startButton));
		
		assertTrue("Asserts that the image of the first game board is valid", zoom.verifyAsset(zoom.getCurrentPictureAssetLink()));
		
		assertTrue("Asserts that a user can start a Zoom game and see elements for the first round",
			zoom.verifyElementsVisibility(zoom.getAfterOneRoundElements()));
		home = (StudentHomePage) zoom.header.clickHomeBreadcrumb();
	}
	
	@Test
	public void testScienceZoom(){
		ScienceDatabaseHome science = home.goToScience();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(science.gamesLink));
		
		PGNChooseGamePage games = science.openGames();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(games.stateZoomLink));
		
		PGNZoomPage zoom = games.clickZoom();		
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(zoom.startButton));
		zoom.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Zoom game elements load",
				zoom.verifyElementsVisibility(zoom.getInitialVisibleElements()));

		zoom.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(zoom.startButton));
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(zoom.startButton));
		
		assertTrue("Asserts that the image of the first game board is valid", zoom.verifyAsset(zoom.getCurrentPictureAssetLink()));
		
		assertTrue("Asserts that a user can start a Zoom game and see elements for the first round",
				zoom.verifyElementsVisibility(zoom.getAfterOneRoundElements()));
		home = (StudentHomePage) zoom.header.clickHomeBreadcrumb();
	}
	
	@Test
	public void testSocialStudiesZoom(){
		SocialStudiesDatabasePage socialStudies = home.goToSocialStudies();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(socialStudies.gamesLink));
		
		PGNChooseGamePage games = socialStudies.openGames();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(games.stateZoomLink));
		
		PGNZoomPage zoom = games.clickZoom();		
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(zoom.startButton));
		zoom.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Zoom game elements load",
				zoom.verifyElementsVisibility(zoom.getInitialVisibleElements()));

		zoom.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(zoom.startButton));
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(zoom.startButton));
		
		assertTrue("Asserts that the image of the first game board is valid", zoom.verifyAsset(zoom.getCurrentPictureAssetLink()));
		
		assertTrue("Asserts that a user can start a Zoom game and see elements for the first round",
				zoom.verifyElementsVisibility(zoom.getAfterOneRoundElements()));
		home = (StudentHomePage) zoom.header.clickHomeBreadcrumb();
	}
	
	@Test
	public void testBiographiesZoom(){
		DatabasePage biographies = home.goToBiographies();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(biographies.gamesLink));
		
		PGNChooseGamePage games = biographies.openGames();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(games.stateZoomLink));
		
		PGNZoomPage zoom = games.clickZoom();		
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(zoom.startButton));
		zoom.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Zoom game elements load",
				zoom.verifyElementsVisibility(zoom.getInitialVisibleElements()));

		zoom.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(zoom.startButton));
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(zoom.startButton));
		
		assertTrue("Asserts that the image of the first game board is valid", zoom.verifyAsset(zoom.getCurrentPictureAssetLink()));
		
		assertTrue("Asserts that a user can start a Zoom game and see elements for the first round",
				zoom.verifyElementsVisibility(zoom.getAfterOneRoundElements()));
		home = (StudentHomePage) zoom.header.clickHomeBreadcrumb();
	}
	
	@Test
	public void testStateJigsaw(){
		StatesDatabaseHome states = home.goToStates();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(states.gamesLink));
		PGNChooseGamePage games = states.openGames();
		
		PGNJigsawPage jigsaw = games.clickJigsaw();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(jigsaw.startButton));
		jigsaw.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Jigsaw game elements load",
				jigsaw.verifyElementsVisibility(jigsaw.initialVisibleElements));
		
		jigsaw.click(jigsaw.startButton);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(jigsaw.startButton));
		
		assertTrue("Asserts that a user can start a Jigsaw game and see elements for the first round",
				jigsaw.verifyPiecesLoaded());
		home = (StudentHomePage) jigsaw.header.clickHomeBreadcrumb();
	}
	
	@Test
	@Category(PGNSmokeTest.class)
	public void testScienceJigsaw(){
		ScienceDatabaseHome science = home.goToScience();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(science.gamesLink));
		PGNChooseGamePage games = science.openGames();
		
		PGNJigsawPage jigsaw = games.clickJigsaw();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(jigsaw.startButton));
		jigsaw.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Jigsaw game elements load",
				jigsaw.verifyElementsVisibility(jigsaw.initialVisibleElements));
		
		jigsaw.click(jigsaw.startButton);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(jigsaw.startButton));
		
		assertTrue("Asserts that a user can start a Jigsaw game and see elements for the first round",
				jigsaw.verifyPiecesLoaded());
		home = (StudentHomePage) jigsaw.header.clickHomeBreadcrumb();
	}
	
	@Test
	public void testIndiansJigsaw(){
		IndiansDatabaseHome indians = home.goToIndians();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(indians.gamesLink));
		PGNChooseGamePage games = indians.openGames();
		
		PGNJigsawPage jigsaw = games.clickJigsaw();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(jigsaw.startButton));
		jigsaw.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Jigsaw game elements load",
				jigsaw.verifyElementsVisibility(jigsaw.initialVisibleElements));
		
		jigsaw.click(jigsaw.startButton);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(jigsaw.startButton));
		
		assertTrue("Asserts that a user can start a Jigsaw game and see elements for the first round",
				jigsaw.verifyPiecesLoaded());
		home = (StudentHomePage) jigsaw.header.clickHomeBreadcrumb();
	}
	
	@Test
	public void testSocialStudiesJigsaw(){
		SocialStudiesDatabasePage socialStudies = home.goToSocialStudies();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(socialStudies.gamesLink));
		PGNChooseGamePage games = socialStudies.openGames();
		
		PGNJigsawPage jigsaw = games.clickJigsaw();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(jigsaw.startButton));
		jigsaw.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Jigsaw game elements load",
				jigsaw.verifyElementsVisibility(jigsaw.initialVisibleElements));
		
		jigsaw.click(jigsaw.startButton);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(jigsaw.startButton));
		
		assertTrue("Asserts that a user can start a Jigsaw game and see elements for the first round",
				jigsaw.verifyPiecesLoaded());
		home = (StudentHomePage) jigsaw.header.clickHomeBreadcrumb();
	}
	
	@Test
	public void testBiographiesJigsaw(){
		DatabasePage biographies = home.goToBiographies();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(biographies.gamesLink));
		PGNChooseGamePage games = biographies.openGames();
		
		PGNJigsawPage jigsaw = games.clickJigsaw();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(jigsaw.startButton));
		jigsaw.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Jigsaw game elements load",
				jigsaw.verifyElementsVisibility(jigsaw.initialVisibleElements));
		
		jigsaw.click(jigsaw.startButton);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(jigsaw.startButton));
		
		assertTrue("Asserts that a user can start a Jigsaw game and see elements for the first round",
				jigsaw.verifyPiecesLoaded());
		home = (StudentHomePage) jigsaw.header.clickHomeBreadcrumb();
	}
	
	/*
	@Test
	public void testStatesQuickMatch(){
		StatesDatabaseHome states = home.goToStates();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(states.gamesLink));
		
		PGNChooseGamePage games = states.openGames();
		
		PGNQuickMatchPage quickMatch = games.clickQuickMatch();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(quickMatch.startButton));
		quickMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Quick Match game elements load",
				quickMatch.verifyElementsVisibility(quickMatch.getInitialVisibleElements()));
		
		quickMatch.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(quickMatch.startButton));
		
		String image;
		try{
			user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(quickMatch.rightAnswerBoxImage));
			image = quickMatch.getRightImageAsset();
		} catch(Exception e){
			image = "error";
		}
		
		assertTrue("Asserts that the first image served by the game is valid", quickMatch.verifyAsset(image));
		
		assertTrue("Asserts that a user can start a Quick Match game and see elements for the first round",
				quickMatch.verifyElementsVisibility(quickMatch.getGameStartedElements()));
		home = (StudentHomePage) quickMatch.header.clickHomeBreadcrumb();
	}
	*/
	
	@Test
	public void testScienceQuickMatch(){
		ScienceDatabaseHome science = home.goToScience();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(science.gamesLink));
		
		PGNChooseGamePage games = science.openGames();
		
		PGNQuickMatchPage quickMatch = games.clickQuickMatch();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(quickMatch.startButton));
		quickMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Quick Match game elements load",
				quickMatch.verifyElementsVisibility(quickMatch.getInitialVisibleElements()));
		
		quickMatch.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(quickMatch.startButton));
		
		String image;
		try{
			user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(quickMatch.rightAnswerBoxImage));
			image = quickMatch.getRightImageAsset();
		} catch(Exception e){
			image = "error";
		}
		
		assertTrue("Asserts that the first image served by the game is valid", quickMatch.verifyAsset(image));
		
		assertTrue("Asserts that a user can start a Quick Match game and see elements for the first round",
				quickMatch.verifyElementsVisibility(quickMatch.getGameStartedElements()));
		home = (StudentHomePage) quickMatch.header.clickHomeBreadcrumb();
	}
	
	@Test
	@Category(PGNSmokeTest.class)
	public void testAmericanIndiansQuickMatch(){
		IndiansDatabaseHome indians = home.goToIndians();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(indians.gamesLink));
		
		PGNChooseGamePage games = indians.openGames();
		
		PGNQuickMatchPage quickMatch = games.clickQuickMatch();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(quickMatch.startButton));
		quickMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Quick Match game elements load",
				quickMatch.verifyElementsVisibility(quickMatch.getInitialVisibleElements()));
		
		quickMatch.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(quickMatch.startButton));
		
		String image;
		try{
			user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(quickMatch.rightAnswerBoxImage));
			image = quickMatch.getRightImageAsset();
		} catch(Exception e){
			image = "error";
		}
		
		assertTrue("Asserts that the first image served by the game is valid", quickMatch.verifyAsset(image));
		
		assertTrue("Asserts that a user can start a Quick Match game and see elements for the first round",
				quickMatch.verifyElementsVisibility(quickMatch.getGameStartedElements()));
		home = (StudentHomePage) quickMatch.header.clickHomeBreadcrumb();
	}
	
	@Test
	public void testSocialStudiesQuickMatch(){
		SocialStudiesDatabasePage socialStudies = home.goToSocialStudies();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(socialStudies.gamesLink));
		
		PGNChooseGamePage games = socialStudies.openGames();
		
		PGNQuickMatchPage quickMatch = games.clickQuickMatch();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(quickMatch.startButton));
		quickMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Quick Match game elements load",
				quickMatch.verifyElementsVisibility(quickMatch.getInitialVisibleElements()));
		
		quickMatch.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(quickMatch.startButton));
		
		String image;
		try{
			user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(quickMatch.rightAnswerBoxImage));
			image = quickMatch.getRightImageAsset();
		} catch(Exception e){
			image = "error";
		}
		
		assertTrue("Asserts that the first image served by the game is valid", quickMatch.verifyAsset(image));
		
		assertTrue("Asserts that a user can start a Quick Match game and see elements for the first round",
				quickMatch.verifyElementsVisibility(quickMatch.getGameStartedElements()));
		home = (StudentHomePage) quickMatch.header.clickHomeBreadcrumb();
	}
	
	@Test
	public void testBiographiesQuickMatch(){
		DatabasePage biographies = home.goToBiographies();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(biographies.gamesLink));
		
		PGNChooseGamePage games = biographies.openGames();
		
		PGNQuickMatchPage quickMatch = games.clickQuickMatch();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(quickMatch.startButton));
		quickMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Quick Match game elements load",
				quickMatch.verifyElementsVisibility(quickMatch.getInitialVisibleElements()));
		
		quickMatch.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(quickMatch.startButton));
		
		String image;
		try{
			user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(quickMatch.rightAnswerBoxImage));
			image = quickMatch.getRightImageAsset();
		} catch(Exception e){
			image = "error";
		}
		
		assertTrue("Asserts that the first image served by the game is valid", quickMatch.verifyAsset(image));
		
		assertTrue("Asserts that a user can start a Quick Match game and see elements for the first round",
				quickMatch.verifyElementsVisibility(quickMatch.getGameStartedElements()));
		home = (StudentHomePage) quickMatch.header.clickHomeBreadcrumb();
	}
	
	@Test
	public void testStatesWordScramble(){
		StatesDatabaseHome states = home.goToStates();
		
		PGNChooseGamePage games = states.openGames();
		PGNWordScramblePage scramble = games.clickWordScramble();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Word Scramble elements load",scramble.verifyElementsVisibility(scramble.getInitialVisibleElements()));
		
		scramble.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(1);
		
		assertTrue("Asserts that the first image served by the game is valid", scramble.verifyAsset(scramble.getImageAsset()));
		
		assertTrue("Asserts that a user can start a Scramble game and see elements for the first round",
				scramble.verifyElementsVisibility(scramble.getAfterStartElements()));
		home = (StudentHomePage) scramble.header.clickHomeBreadcrumb();
	}
	
	@Test
	@Category(PGNSmokeTest.class)
	public void testScienceWordScramble(){
		ScienceDatabaseHome science = home.goToScience();
		
		PGNChooseGamePage games = science.openGames();
		PGNWordScramblePage scramble = games.clickWordScramble();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Word Scramble elements load",scramble.verifyElementsVisibility(scramble.getInitialVisibleElements()));
		
		scramble.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(1);
		
		assertTrue("Asserts that the first image served by the game is valid", scramble.verifyAsset(scramble.getImageAsset()));
		
		assertTrue("Asserts that a user can start a Scramble game and see elements for the first round",
				scramble.verifyElementsVisibility(scramble.getAfterStartElements()));
		home = (StudentHomePage) scramble.header.clickHomeBreadcrumb();
	}
	
	@Test
	public void testIndiansWordScramble(){
		IndiansDatabaseHome indians = home.goToIndians();
		
		PGNChooseGamePage games = indians.openGames();
		PGNWordScramblePage scramble = games.clickWordScramble();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Word Scramble elements load",scramble.verifyElementsVisibility(scramble.getInitialVisibleElements()));
		
		scramble.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(1);
		
		assertTrue("Asserts that the first image served by the game is valid", scramble.verifyAsset(scramble.getImageAsset()));
		
		assertTrue("Asserts that a user can start a Scramble game and see elements for the first round",
				scramble.verifyElementsVisibility(scramble.getAfterStartElements()));
		home = (StudentHomePage) scramble.header.clickHomeBreadcrumb();
	}
	
	@Test
	public void testSocialStudiesWordScramble(){
		SocialStudiesDatabasePage socialStudies = home.goToSocialStudies();
		
		PGNChooseGamePage games = socialStudies.openGames();
		PGNWordScramblePage scramble = games.clickWordScramble();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Word Scramble elements load",scramble.verifyElementsVisibility(scramble.getInitialVisibleElements()));
		
		scramble.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(1);
		
		assertTrue("Asserts that the first image served by the game is valid", scramble.verifyAsset(scramble.getImageAsset()));
		
		assertTrue("Asserts that a user can start a Scramble game and see elements for the first round",
				scramble.verifyElementsVisibility(scramble.getAfterStartElements()));
		home = (StudentHomePage) scramble.header.clickHomeBreadcrumb();
	}
	
	@Test
	@Category(PGNSmokeTest.class)
	public void testBiographiesWordScramble(){
		DatabasePage biographies = home.goToBiographies();
		
		PGNChooseGamePage games = biographies.openGames();
		PGNWordScramblePage scramble = games.clickWordScramble();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Word Scramble elements load",scramble.verifyElementsVisibility(scramble.getInitialVisibleElements()));
		
		scramble.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(1);
		
		assertTrue("Asserts that the first image served by the game is valid", scramble.verifyAsset(scramble.getImageAsset()));
		
		assertTrue("Asserts that a user can start a Scramble game and see elements for the first round",
				scramble.verifyElementsVisibility(scramble.getAfterStartElements()));
		home = (StudentHomePage) scramble.header.clickHomeBreadcrumb();
	}
}
