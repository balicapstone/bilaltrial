package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Games.PGOChooseGamePage;
import Games.PGOJigsawPage;
import Games.PGOMultiMatchPage;
import Games.PGOQuickMatchPage;
import Games.PGOWhackPage;
import Games.PGOWordScramblePage;
import Games.PGOZoomPage;
import PGOPages.LoginPage;
import PGOStudentResources.AnimalesContentPage;
import PGOStudentResources.AnimalsContentPage;
import PGOStudentResources.BiographiesContentPage;
import PGOStudentResources.ContentSelectionPage;
import PGOStudentResources.DinosaursContentPage;
import PGOStudentResources.ScienceContentPage;
import PGOStudentResources.SocialStudiesContentPage;
import PGOStudentResources.StudentHomePage;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.User;
import UserClasses.UserInfo;


public class PGONewGameTests {
	public static LoginPage login;
	public static StudentHomePage student;
	public static User user;
	
	@BeforeClass
	public static void executeBefore(){
		user = new User(UserInfo.GARAGESTUDENT);
		login = new LoginPage(user);
		try{
			student = (StudentHomePage) login.Login(user);
		} catch(Exception e){
			user.getWatcher().TakeScreenshot("FailureToRunExecuteBeforeForPGOGameTests");
		}
	}
	
	@AfterClass
	public static void executeAfter(){
		student.closeCurrentWindow();
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
			
			login.closeCurrentWindow();
			user.makeNewDriver();
			login = new LoginPage(user);
			student = (StudentHomePage) login.Login(user);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.animalsModule));
		}
	};
	
	@Rule
	public Retry retry = new Retry(3);
	
	@Test
	@Category(SmokeTests.class)
	public void testZoomAnimal(){
		AnimalsContentPage animals = student.clickAnimalsModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(animals.gamesLink));
		
		animals.click(animals.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(animals.getDriver());
		
		PGOZoomPage zoom = choose.clickZoom();	
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(zoom.startButton));
		zoom.waitImplicitly(3);
		//zoom.waitImplicitly(15);
		assertTrue("Asserts that all initial Zoom game elements load",
				zoom.verifyElementsVisibility(zoom.getInitialVisibleElements()));
		zoom.clickStartButton();
		
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(zoom.startButton));
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(zoom.startButton));
		
		assertTrue("Asserts that the image of the first game board is valid", zoom.verifyAsset(zoom.getCurrentPictureAssetLink()));
		
		assertTrue("Asserts that a user can start a Zoom game and see elements for the first round",
				zoom.verifyElementsVisibility(zoom.getAfterOneRoundElements()));
		student = zoom.clickHomeBradcrumb();
	}
	
	@Test
	public void testZoomScience(){
		ScienceContentPage science = student.clickScienceModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(science.gamesLink));
		science.click(science.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(science.getDriver());
		
		PGOZoomPage zoom = choose.clickZoom();	
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
		student = zoom.clickHomeBradcrumb();
	}
	
	@Test
	public void testZoomBiographies(){
		BiographiesContentPage bio = student.clickBiographiesModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(bio.gamesLink));
		bio.click(bio.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(bio.getDriver());
		
		PGOZoomPage zoom = choose.clickZoom();
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
		student = zoom.clickHomeBradcrumb();
	}
	
	@Test
	public void testZoomSocialStudies(){
		SocialStudiesContentPage social = student.clickSocialStudiesModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(social.gamesLink));
		social.click(social.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(social.getDriver());
		
		PGOZoomPage zoom = choose.clickZoom();	
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
		student = zoom.clickHomeBradcrumb();
	}
	
	@Test
	public void testZoomDinosaurs(){
		DinosaursContentPage dinos = student.clickDinosaursModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(dinos.gamesLink));
		dinos.click(dinos.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(dinos.getDriver());
		
		PGOZoomPage zoom = choose.clickZoom();		
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
		student = zoom.clickHomeBradcrumb();
	}
	
	@Test
	public void testZoomAnimales(){
		AnimalesContentPage animales = student.clickAnimalesModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(animales.gamesLink));
		animales.click(animales.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(animales.getDriver());
		
		PGOZoomPage zoom = choose.clickZoom();		
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
		student = zoom.clickHomeBradcrumb();
	}
	
	@Test
	public void testZoomCiencia(){
		ContentSelectionPage ciencia = student.clickCienciaModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(ciencia.gamesLink));
		ciencia.click(ciencia.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(ciencia.getDriver());
		
		PGOZoomPage zoom = choose.clickZoom();		
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
		student = zoom.clickHomeBradcrumb();
	}
	
	@Test
	public void testZoomBiografias(){
		ContentSelectionPage biografias = student.clickBiografiasModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(biografias.gamesLink));
		biografias.click(biografias.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(biografias.getDriver());
		
		PGOZoomPage zoom = choose.clickZoom();		
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
		student = zoom.clickHomeBradcrumb();
	}
	
	@Test
	public void testZoomEstudiosSociales(){
		ContentSelectionPage estudios = student.clickEstudiosSocialesModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(estudios.gamesLink));
		estudios.click(estudios.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(estudios.getDriver());
		
		PGOZoomPage zoom = choose.clickZoom();		
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
		student = zoom.clickHomeBradcrumb();
	}
	
	@Test
	public void testJigsawAnimals(){
		AnimalsContentPage animals = student.clickAnimalsModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(animals.gamesLink));
		animals.click(animals.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(animals.getDriver());
		
		PGOJigsawPage jigsaw = choose.clickJigsaw();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(jigsaw.startButton));
		jigsaw.waitImplicitly(3);
		assertTrue("Asserts that all initial Jigsaw game elements load",
				jigsaw.verifyElementsVisibility(jigsaw.initialVisibleElements));
		
		jigsaw.click(jigsaw.startButton);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(jigsaw.startButton));

		assertTrue("Asserts that a user can start a Jigsaw game and see elements for the first round",
				jigsaw.verifyPiecesLoaded());
		
		student = jigsaw.clickHomeBradcrumb();
	}
	
	/*
	@Test
	public void testJigsawScience(){
		ScienceContentPage science = student.clickScienceModule();
		science.waitImplicitly(3);
		science.click(science.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(science.getDriver());
		
		PGOJigsawPage jigsaw = choose.clickJigsaw();
		jigsaw.waitImplicitly(10);
		assertTrue("Asserts that all initial Jigsaw game elements load",
				jigsaw.verifyElementsVisibility(jigsaw.initialVisibleElements));
		
		jigsaw.click(jigsaw.startButton);
		jigsaw.waitImplicitly(2);
		assertTrue("Asserts that a user can start a Jigsaw game and see elements for the first round",
				jigsaw.verifyPiecesLoaded());
		
		student = jigsaw.clickHomeBradcrumb();
	}
	*/
	
	@Test
	@Category(SmokeTests.class)
	public void testJigsawBiographies(){
		BiographiesContentPage bio = student.clickBiographiesModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(bio.gamesLink));
		
		bio.click(bio.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(bio.getDriver());
		
		PGOJigsawPage jigsaw = choose.clickJigsaw();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(jigsaw.startButton));
		jigsaw.waitImplicitly(3);
		assertTrue("Asserts that all initial Jigsaw game elements load",
				jigsaw.verifyElementsVisibility(jigsaw.initialVisibleElements));
		
		jigsaw.click(jigsaw.startButton);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(jigsaw.startButton));
		
		assertTrue("Asserts that a user can start a Jigsaw game and see elements for the first round",
				jigsaw.verifyPiecesLoaded());
		
		student = jigsaw.clickHomeBradcrumb();
	}
	
	@Test
	public void testJigsawSocialStudies(){
		SocialStudiesContentPage socialStudies = student.clickSocialStudiesModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(socialStudies.gamesLink));
		
		socialStudies.click(socialStudies.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(socialStudies.getDriver());
		
		PGOJigsawPage jigsaw = choose.clickJigsaw();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(jigsaw.startButton));
		jigsaw.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Jigsaw game elements load",
				jigsaw.verifyElementsVisibility(jigsaw.initialVisibleElements));
		
		jigsaw.click(jigsaw.startButton);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(jigsaw.startButton));
		
		assertTrue("Asserts that a user can start a Jigsaw game and see elements for the first round",
				jigsaw.verifyPiecesLoaded());
		
		student = jigsaw.clickHomeBradcrumb();
	}
	
	@Test
	public void testJigsawDinosaurs(){
		DinosaursContentPage dinos = student.clickDinosaursModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(dinos.gamesLink));
		
		dinos.click(dinos.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(dinos.getDriver());
		
		PGOJigsawPage jigsaw = choose.clickJigsaw();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(jigsaw.startButton));
		jigsaw.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Jigsaw game elements load",
				jigsaw.verifyElementsVisibility(jigsaw.initialVisibleElements));
		
		jigsaw.click(jigsaw.startButton);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(jigsaw.startButton));
		
		assertTrue("Asserts that a user can start a Jigsaw game and see elements for the first round",
				jigsaw.verifyPiecesLoaded());
		
		student = jigsaw.clickHomeBradcrumb();
	}
	
	@Test
	public void testJigsawAnimales(){
		AnimalesContentPage animales = student.clickAnimalesModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(animales.gamesLink));
		
		animales.click(animales.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(animales.getDriver());
		
		PGOJigsawPage jigsaw = choose.clickJigsaw();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(jigsaw.startButton));
		jigsaw.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Jigsaw game elements load",
				jigsaw.verifyElementsVisibility(jigsaw.initialVisibleElements));
		
		jigsaw.click(jigsaw.startButton);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(jigsaw.startButton));
		
		assertTrue("Asserts that a user can start a Jigsaw game and see elements for the first round",
				jigsaw.verifyPiecesLoaded());
		
		student = jigsaw.clickHomeBradcrumb();
	}
	
	@Test
	public void testJigsawCiencia(){
		ContentSelectionPage ciencia = student.clickCienciaModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(ciencia.gamesLink));
		
		ciencia.click(ciencia.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(ciencia.getDriver());
		
		PGOJigsawPage jigsaw = choose.clickJigsaw();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(jigsaw.startButton));
		jigsaw.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Jigsaw game elements load",
				jigsaw.verifyElementsVisibility(jigsaw.initialVisibleElements));
		
		jigsaw.click(jigsaw.startButton);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(jigsaw.startButton));
		
		assertTrue("Asserts that a user can start a Jigsaw game and see elements for the first round",
				jigsaw.verifyPiecesLoaded());
		
		student = jigsaw.clickHomeBradcrumb();
	}
	
	@Test
	public void testJigsawBiografias(){
		ContentSelectionPage biografias = student.clickBiografiasModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(biografias.gamesLink));
		
		biografias.click(biografias.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(biografias.getDriver());
		
		PGOJigsawPage jigsaw = choose.clickJigsaw();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(jigsaw.startButton));
		jigsaw.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Jigsaw game elements load",
				jigsaw.verifyElementsVisibility(jigsaw.initialVisibleElements));
		
		jigsaw.click(jigsaw.startButton);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(jigsaw.startButton));
		
		assertTrue("Asserts that a user can start a Jigsaw game and see elements for the first round",
				jigsaw.verifyPiecesLoaded());
		
		student = jigsaw.clickHomeBradcrumb();
	}
	
	@Test
	public void testJigsawEstudiosSociales(){
		ContentSelectionPage estudios = student.clickEstudiosSocialesModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(estudios.gamesLink));
		
		estudios.click(estudios.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(estudios.getDriver());
		
		PGOJigsawPage jigsaw = choose.clickJigsaw();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(jigsaw.startButton));
		jigsaw.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Jigsaw game elements load",
				jigsaw.verifyElementsVisibility(jigsaw.initialVisibleElements));
		
		jigsaw.click(jigsaw.startButton);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(jigsaw.startButton));
		
		assertTrue("Asserts that a user can start a Jigsaw game and see elements for the first round",
				jigsaw.verifyPiecesLoaded());
		
		student = jigsaw.clickHomeBradcrumb();
	}
	
	@Test
	public void testQuickMatchAnimals(){
		AnimalsContentPage animals = student.clickAnimalsModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(animals.gamesLink));

		animals.click(animals.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(animals.getDriver());
		
		PGOQuickMatchPage quickMatch = choose.clickQuickMatch();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(quickMatch.startButton));
		quickMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Quick Match game elements load",
				quickMatch.verifyElementsVisibility(quickMatch.getInitialVisibleElements()));
		
		quickMatch.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(quickMatch.startButton));
		
		String image = "";
		try{
			user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(quickMatch.rightAnswerBoxImage));
			image = quickMatch.getRightImageAsset();
		} catch(Exception e){
			
		}
		
		assertTrue("Asserts that the first image served by the game is valid", quickMatch.verifyAsset(image));
		
		assertTrue("Asserts that a user can start a Quick Match game and see elements for the first round",
				quickMatch.verifyElementsVisibility(quickMatch.getGameStartedElements()));
		
		student = quickMatch.clickHomeBradcrumb();
	}
	
	@Test
	public void testQuickMatchBiographies(){
		BiographiesContentPage bios = student.clickBiographiesModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(bios.gamesLink));
		
		bios.click(bios.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(bios.getDriver());
		
		PGOQuickMatchPage quickMatch = choose.clickQuickMatch();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(quickMatch.startButton));
		quickMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Quick Match game elements load",
				quickMatch.verifyElementsVisibility(quickMatch.getInitialVisibleElements()));
		
		quickMatch.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(quickMatch.startButton));
		
		String image = "";
		try{
			user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(quickMatch.rightAnswerBoxImage));
			image = quickMatch.getRightImageAsset();
		} catch(Exception e){
			
		}
		
		assertTrue("Asserts that the first image served by the game is valid", quickMatch.verifyAsset(image));
		
		assertTrue("Asserts that a user can start a Quick Match game and see elements for the first round",
				quickMatch.verifyElementsVisibility(quickMatch.getGameStartedElements()));
		
		student = quickMatch.clickHomeBradcrumb();
	}
	
	@Test
	public void testQuickMatchScience(){
		ScienceContentPage science = student.clickScienceModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(science.gamesLink));
		
		science.click(science.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(science.getDriver());
		
		PGOQuickMatchPage quickMatch = choose.clickQuickMatch();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(quickMatch.startButton));
		quickMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Quick Match game elements load",
				quickMatch.verifyElementsVisibility(quickMatch.getInitialVisibleElements()));
		
		quickMatch.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(quickMatch.startButton));
		
		String image = "";
		try{
			user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(quickMatch.rightAnswerBoxImage));
			image = quickMatch.getRightImageAsset();
		} catch(Exception e){
			
		}
		
		assertTrue("Asserts that the first image served by the game is valid", quickMatch.verifyAsset(image));
		
		assertTrue("Asserts that a user can start a Quick Match game and see elements for the first round",
				quickMatch.verifyElementsVisibility(quickMatch.getGameStartedElements()));
		
		student = quickMatch.clickHomeBradcrumb();
	}
	
	@Test
	public void testQuickMatchSocialStudies(){
		SocialStudiesContentPage socialStudies = student.clickSocialStudiesModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(socialStudies.gamesLink));
		
		socialStudies.click(socialStudies.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(socialStudies.getDriver());
		
		PGOQuickMatchPage quickMatch = choose.clickQuickMatch();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(quickMatch.startButton));
		quickMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Quick Match game elements load",
				quickMatch.verifyElementsVisibility(quickMatch.getInitialVisibleElements()));
		
		quickMatch.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(quickMatch.startButton));
		
		String image = "";
		try{
			user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(quickMatch.rightAnswerBoxImage));
			image = quickMatch.getRightImageAsset();
		} catch(Exception e){
			
		}
		
		assertTrue("Asserts that the first image served by the game is valid", quickMatch.verifyAsset(image));
		
		assertTrue("Asserts that a user can start a Quick Match game and see elements for the first round",
				quickMatch.verifyElementsVisibility(quickMatch.getGameStartedElements()));
		
		student = quickMatch.clickHomeBradcrumb();
	}
	
	@Test
	public void testQuickMatchDinosaurs(){
		DinosaursContentPage dinos = student.clickDinosaursModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(dinos.gamesLink));
		
		dinos.click(dinos.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(dinos.getDriver());
		
		PGOQuickMatchPage quickMatch = choose.clickQuickMatch();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(quickMatch.startButton));
		quickMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Quick Match game elements load",
				quickMatch.verifyElementsVisibility(quickMatch.getInitialVisibleElements()));
		
		quickMatch.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(quickMatch.startButton));
		
		String image = "";
		try{
			user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(quickMatch.rightAnswerBoxImage));
			image = quickMatch.getRightImageAsset();
		} catch(Exception e){
			
		}
		
		assertTrue("Asserts that the first image served by the game is valid", quickMatch.verifyAsset(image));
		
		assertTrue("Asserts that a user can start a Quick Match game and see elements for the first round",
				quickMatch.verifyElementsVisibility(quickMatch.getGameStartedElements()));
		
		student = quickMatch.clickHomeBradcrumb();
	}
	
	
	@Test
	public void testQuickMatchAnimales(){
		AnimalesContentPage animales = student.clickAnimalesModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(animales.gamesLink));
		
		animales.click(animales.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(animales.getDriver());
		
		PGOQuickMatchPage quickMatch = choose.clickQuickMatch();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(quickMatch.startButton));
		quickMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Quick Match game elements load",
				quickMatch.verifyElementsVisibility(quickMatch.getInitialVisibleElements()));
		
		quickMatch.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(quickMatch.startButton));
		
		String image = "";
		try{
			user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(quickMatch.rightAnswerBoxImage));
			image = quickMatch.getRightImageAsset();
		} catch(Exception e){
			
		}
		
		assertTrue("Asserts that the first image served by the game is valid", quickMatch.verifyAsset(image));
		
		assertTrue("Asserts that a user can start a Quick Match game and see elements for the first round",
				quickMatch.verifyElementsVisibility(quickMatch.getGameStartedElements()));
		
		student = quickMatch.clickHomeBradcrumb();
	}
	
	@Test
	public void testQuickMatchCiencia(){
		ContentSelectionPage ciencia = student.clickCienciaModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(ciencia.gamesLink));
		
		ciencia.click(ciencia.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(ciencia.getDriver());
		
		PGOQuickMatchPage quickMatch = choose.clickQuickMatch();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(quickMatch.startButton));
		quickMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Quick Match game elements load",
				quickMatch.verifyElementsVisibility(quickMatch.getInitialVisibleElements()));
		
		quickMatch.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(quickMatch.startButton));
		
		String image = "";
		try{
			user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(quickMatch.rightAnswerBoxImage));
			image = quickMatch.getRightImageAsset();
		} catch(Exception e){
			
		}
		
		assertTrue("Asserts that the first image served by the game is valid", quickMatch.verifyAsset(image));
		
		assertTrue("Asserts that a user can start a Quick Match game and see elements for the first round",
				quickMatch.verifyElementsVisibility(quickMatch.getGameStartedElements()));
		
		student = quickMatch.clickHomeBradcrumb();
	}
	
	@Test
	public void testQuickMatchBiografias(){
		ContentSelectionPage biografias = student.clickBiografiasModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(biografias.gamesLink));
		
		biografias.click(biografias.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(biografias.getDriver());
		
		PGOQuickMatchPage quickMatch = choose.clickQuickMatch();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(quickMatch.startButton));
		quickMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Quick Match game elements load",
				quickMatch.verifyElementsVisibility(quickMatch.getInitialVisibleElements()));
		
		quickMatch.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(quickMatch.startButton));
		
		String image = "";
		try{
			user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(quickMatch.rightAnswerBoxImage));
			image = quickMatch.getRightImageAsset();
		} catch(Exception e){
			
		}
		
		assertTrue("Asserts that the first image served by the game is valid", quickMatch.verifyAsset(image));
		
		assertTrue("Asserts that a user can start a Quick Match game and see elements for the first round",
				quickMatch.verifyElementsVisibility(quickMatch.getGameStartedElements()));
		
		student = quickMatch.clickHomeBradcrumb();
	}
	
	@Test
	public void testQuickMatchEstudiosSociales(){
		ContentSelectionPage estudios = student.clickEstudiosSocialesModule();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(estudios.gamesLink));
		
		estudios.click(estudios.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(estudios.getDriver());
		
		PGOQuickMatchPage quickMatch = choose.clickQuickMatch();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(quickMatch.startButton));
		quickMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Quick Match game elements load",
				quickMatch.verifyElementsVisibility(quickMatch.getInitialVisibleElements()));
		
		quickMatch.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(quickMatch.startButton));
		
		String image = "";
		try{
			user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(quickMatch.rightAnswerBoxImage));
			image = quickMatch.getRightImageAsset();
		} catch(Exception e){
			
		}
		
		assertTrue("Asserts that the first image served by the game is valid", quickMatch.verifyAsset(image));
		
		assertTrue("Asserts that a user can start a Quick Match game and see elements for the first round",
				quickMatch.verifyElementsVisibility(quickMatch.getGameStartedElements()));
		
		student = quickMatch.clickHomeBradcrumb();
	}
	
	@Test
	public void testMultiMatchAnimals(){
		AnimalsContentPage animals = student.clickAnimalsModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(animals.gamesLink));
		
		animals.click(animals.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(animals.getDriver());
		
		PGOMultiMatchPage multiMatch = choose.clickMultiMatch();
		user.customWait().until(ExpectedConditions.elementToBeClickable(multiMatch.questionInfo));
		multiMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Multimatch game elements load",
				multiMatch.verifyElementsVisibility(multiMatch.getInitialVisibleElements()));
		
		multiMatch.clickQuestionInfo();
		multiMatch.clickAnswerInfo();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(multiMatch.questionInfoPopup));
		
		assertTrue("Asserts that a user can start a Multi Match game and see elements for the first round",
				multiMatch.verifyElementsVisibility(multiMatch.getAfterClickElements()));
		multiMatch.clickQuestionPopupClose();
		multiMatch.clickAnswerPopupClose();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(multiMatch.questionInfoPopup));
		
		ArrayList<String> assets = multiMatch.getAssets();
		Boolean status = true;
		
		for(String s : assets){
			status = status && multiMatch.verifyAsset(s);
		}
		
		assertTrue("Asserts that the first images served by the game are valid", status);
		
		assertTrue("Asserts that a user can not see all initial elements after the game has started",
				multiMatch.verifyElementsHidden(multiMatch.getAfterClickElements()));
		
		student = multiMatch.clickHomeBradcrumb();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testMultiMatchScience(){
		ScienceContentPage science = student.clickScienceModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(science.gamesLink));
		
		science.click(science.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(science.getDriver());
		
		PGOMultiMatchPage multiMatch = choose.clickMultiMatch();
		user.customWait().until(ExpectedConditions.elementToBeClickable(multiMatch.questionInfo));
		multiMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Multimatch game elements load",
				multiMatch.verifyElementsVisibility(multiMatch.getInitialVisibleElements()));
		
		multiMatch.clickQuestionInfo();
		multiMatch.clickAnswerInfo();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(multiMatch.questionInfoPopup));
		
		assertTrue("Asserts that a user can start a Multi Match game and see elements for the first round",
				multiMatch.verifyElementsVisibility(multiMatch.getAfterClickElements()));
		multiMatch.clickQuestionPopupClose();
		multiMatch.clickAnswerPopupClose();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(multiMatch.questionInfoPopup));
		
		ArrayList<String> assets = multiMatch.getAssets();
		Boolean status = true;
		
		for(String s : assets){
			status = status && multiMatch.verifyAsset(s);
		}
		
		assertTrue("Asserts that the first images served by the game are valid", status);
		
		assertTrue("Asserts that a user can not see all initial elements after the game has started",
				multiMatch.verifyElementsHidden(multiMatch.getAfterClickElements()));
		
		student = multiMatch.clickHomeBradcrumb();
	}
	
	@Test
	public void testMultiMatchBiographies(){
		BiographiesContentPage bio = student.clickBiographiesModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bio.gamesLink));
		bio.click(bio.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(bio.getDriver());
		
		PGOMultiMatchPage multiMatch = choose.clickMultiMatch();
		user.customWait().until(ExpectedConditions.elementToBeClickable(multiMatch.questionInfo));
		multiMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Multimatch game elements load",
				multiMatch.verifyElementsVisibility(multiMatch.getInitialVisibleElements()));
		
		multiMatch.clickQuestionInfo();
		multiMatch.clickAnswerInfo();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(multiMatch.questionInfoPopup));
		
		assertTrue("Asserts that a user can start a Multi Match game and see elements for the first round",
				multiMatch.verifyElementsVisibility(multiMatch.getAfterClickElements()));
		multiMatch.clickQuestionPopupClose();
		multiMatch.clickAnswerPopupClose();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(multiMatch.questionInfoPopup));
		
		ArrayList<String> assets = multiMatch.getAssets();
		Boolean status = true;
		
		for(String s : assets){
			status = status && multiMatch.verifyAsset(s);
		}
		
		assertTrue("Asserts that the first images served by the game are valid", status);
		
		assertTrue("Asserts that a user can not see all initial elements after the game has started",
				multiMatch.verifyElementsHidden(multiMatch.getAfterClickElements()));
		
		student = multiMatch.clickHomeBradcrumb();
	}
	
	@Test
	public void testMultiMatchSocialStudies(){
		SocialStudiesContentPage socialStudies = student.clickSocialStudiesModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(socialStudies.gamesLink));
		socialStudies.click(socialStudies.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(socialStudies.getDriver());
		
		PGOMultiMatchPage multiMatch = choose.clickMultiMatch();
		user.customWait().until(ExpectedConditions.elementToBeClickable(multiMatch.questionInfo));
		multiMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Multimatch game elements load",
				multiMatch.verifyElementsVisibility(multiMatch.getInitialVisibleElements()));
		
		multiMatch.clickQuestionInfo();
		multiMatch.clickAnswerInfo();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(multiMatch.questionInfoPopup));
		
		assertTrue("Asserts that a user can start a Multi Match game and see elements for the first round",
				multiMatch.verifyElementsVisibility(multiMatch.getAfterClickElements()));
		multiMatch.clickQuestionPopupClose();
		multiMatch.clickAnswerPopupClose();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(multiMatch.questionInfoPopup));
		
		ArrayList<String> assets = multiMatch.getAssets();
		Boolean status = true;
		
		for(String s : assets){
			status = status && multiMatch.verifyAsset(s);
		}
		
		assertTrue("Asserts that the first images served by the game are valid", status);
		
		assertTrue("Asserts that a user can not see all initial elements after the game has started",
				multiMatch.verifyElementsHidden(multiMatch.getAfterClickElements()));
		
		student = multiMatch.clickHomeBradcrumb();
	}
	
	@Test
	public void testMultiMatchDinosaurs(){
		DinosaursContentPage dinos = student.clickDinosaursModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dinos.gamesLink));
		dinos.click(dinos.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(dinos.getDriver());
		
		PGOMultiMatchPage multiMatch = choose.clickMultiMatch();
		user.customWait().until(ExpectedConditions.elementToBeClickable(multiMatch.questionInfo));
		multiMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Multimatch game elements load",
				multiMatch.verifyElementsVisibility(multiMatch.getInitialVisibleElements()));
		
		multiMatch.clickQuestionInfo();
		multiMatch.clickAnswerInfo();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(multiMatch.questionInfoPopup));
		
		assertTrue("Asserts that a user can start a Multi Match game and see elements for the first round",
				multiMatch.verifyElementsVisibility(multiMatch.getAfterClickElements()));
		multiMatch.clickQuestionPopupClose();
		multiMatch.clickAnswerPopupClose();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(multiMatch.questionInfoPopup));
		
		ArrayList<String> assets = multiMatch.getAssets();
		Boolean status = true;
		
		for(String s : assets){
			status = status && multiMatch.verifyAsset(s);
		}
		
		assertTrue("Asserts that the first images served by the game are valid", status);
		
		assertTrue("Asserts that a user can not see all initial elements after the game has started",
			multiMatch.verifyElementsHidden(multiMatch.getAfterClickElements()));
		
		student = multiMatch.clickHomeBradcrumb();
	}
	
	@Test
	public void testMultiMatchAnimales(){
		AnimalesContentPage animales = student.clickAnimalesModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(animales.gamesLink));
		animales.click(animales.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(animales.getDriver());
		
		PGOMultiMatchPage multiMatch = choose.clickMultiMatch();
		user.customWait().until(ExpectedConditions.elementToBeClickable(multiMatch.questionInfo));
		multiMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Multimatch game elements load",
				multiMatch.verifyElementsVisibility(multiMatch.getInitialVisibleElements()));
		
		multiMatch.clickQuestionInfo();
		multiMatch.clickAnswerInfo();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(multiMatch.questionInfoPopup));
		
		assertTrue("Asserts that a user can start a Multi Match game and see elements for the first round",
				multiMatch.verifyElementsVisibility(multiMatch.getAfterClickElements()));
		multiMatch.clickQuestionPopupClose();
		multiMatch.clickAnswerPopupClose();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(multiMatch.questionInfoPopup));
		
		ArrayList<String> assets = multiMatch.getAssets();
		Boolean status = true;
		
		for(String s : assets){
			status = status && multiMatch.verifyAsset(s);
		}
		
		assertTrue("Asserts that the first images served by the game are valid", status);
		
		assertTrue("Asserts that a user can not see all initial elements after the game has started",
				multiMatch.verifyElementsHidden(multiMatch.getAfterClickElements()));
		
		student = multiMatch.clickHomeBradcrumb();
	}
	
	@Test
	public void testMultiMatchCiencia(){
		ContentSelectionPage ciencia = student.clickCienciaModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ciencia.gamesLink));
		ciencia.click(ciencia.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(ciencia.getDriver());
		
		PGOMultiMatchPage multiMatch = choose.clickMultiMatch();
		user.customWait().until(ExpectedConditions.elementToBeClickable(multiMatch.questionInfo));
		multiMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Multimatch game elements load",
				multiMatch.verifyElementsVisibility(multiMatch.getInitialVisibleElements()));
		
		multiMatch.clickQuestionInfo();
		multiMatch.clickAnswerInfo();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(multiMatch.questionInfoPopup));
		
		assertTrue("Asserts that a user can start a Multi Match game and see elements for the first round",
				multiMatch.verifyElementsVisibility(multiMatch.getAfterClickElements()));
		multiMatch.clickQuestionPopupClose();
		multiMatch.clickAnswerPopupClose();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(multiMatch.questionInfoPopup));
		
		ArrayList<String> assets = multiMatch.getAssets();
		Boolean status = true;
		
		for(String s : assets){
			status = status && multiMatch.verifyAsset(s);
		}
		
		assertTrue("Asserts that the first images served by the game are valid", status);
		
		assertTrue("Asserts that a user can not see all initial elements after the game has started",
				multiMatch.verifyElementsHidden(multiMatch.getAfterClickElements()));
		
		student = multiMatch.clickHomeBradcrumb();
	}
	
	@Test
	public void testMultiMatchBiografias(){
		ContentSelectionPage biografias = student.clickBiografiasModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(biografias.gamesLink));
		biografias.click(biografias.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(biografias.getDriver());
		
		PGOMultiMatchPage multiMatch = choose.clickMultiMatch();
		user.customWait().until(ExpectedConditions.elementToBeClickable(multiMatch.questionInfo));
		multiMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Multimatch game elements load",
				multiMatch.verifyElementsVisibility(multiMatch.getInitialVisibleElements()));
		
		multiMatch.clickQuestionInfo();
		multiMatch.clickAnswerInfo();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(multiMatch.questionInfoPopup));
		
		assertTrue("Asserts that a user can start a Multi Match game and see elements for the first round",
				multiMatch.verifyElementsVisibility(multiMatch.getAfterClickElements()));
		multiMatch.clickQuestionPopupClose();
		multiMatch.clickAnswerPopupClose();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(multiMatch.questionInfoPopup));
		
		ArrayList<String> assets = multiMatch.getAssets();
		Boolean status = true;
		
		for(String s : assets){
			status = status && multiMatch.verifyAsset(s);
		}
		
		assertTrue("Asserts that the first images served by the game are valid", status);
		
		assertTrue("Asserts that a user can not see all initial elements after the game has started",
				multiMatch.verifyElementsHidden(multiMatch.getAfterClickElements()));
		
		student = multiMatch.clickHomeBradcrumb();
	}
	
	@Test
	public void testMultiMatchEstudiosSociales(){
		ContentSelectionPage estudios = student.clickEstudiosSocialesModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(estudios.gamesLink));
		estudios.click(estudios.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(estudios.getDriver());
		
		PGOMultiMatchPage multiMatch = choose.clickMultiMatch();
		user.customWait().until(ExpectedConditions.elementToBeClickable(multiMatch.questionInfo));
		multiMatch.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Multimatch game elements load",
				multiMatch.verifyElementsVisibility(multiMatch.getInitialVisibleElements()));
		
		multiMatch.clickQuestionInfo();
		multiMatch.clickAnswerInfo();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(multiMatch.questionInfoPopup));
		
		assertTrue("Asserts that a user can start a Multi Match game and see elements for the first round",
				multiMatch.verifyElementsVisibility(multiMatch.getAfterClickElements()));
		multiMatch.clickQuestionPopupClose();
		multiMatch.clickAnswerPopupClose();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(multiMatch.questionInfoPopup));
		
		ArrayList<String> assets = multiMatch.getAssets();
		Boolean status = true;
		
		for(String s : assets){
			status = status && multiMatch.verifyAsset(s);
		}
		
		assertTrue("Asserts that the first images served by the game are valid", status);
		
		assertTrue("Asserts that a user can not see all initial elements after the game has started",
				multiMatch.verifyElementsHidden(multiMatch.getAfterClickElements()));
		
		student = multiMatch.clickHomeBradcrumb();
	}
	
	@Test
	public void testWhackAnimals(){
		AnimalsContentPage animals = student.clickAnimalsModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(animals.gamesLink));
		
		animals.click(animals.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(animals.getDriver());
		
		PGOWhackPage whack = choose.clickWhack();
		user.customWait().until(ExpectedConditions.elementToBeClickable(whack.startButton));
		whack.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Whack game elements load",
				whack.verifyElementsVisibility(whack.getInitialVisibleElements()));
		
		whack.clickStartButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("imageAppended")));
		
		ArrayList<String> assets = whack.getImageAssets();
		Boolean status = true;
		
		for(String s : assets){
			status = status && whack.verifyAsset(s);
		}
		
		assertTrue("Asserts that the first images served by the game are valid", status);
		
		assertTrue("Asserts that a user can start a Whack game and see elements for the first round",
				whack.verifyImagesAppearing());
		
		student = whack.clickHomeBradcrumb();
	}
	
	@Test
	public void testWhackScience(){
		ScienceContentPage science = student.clickScienceModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(science.gamesLink));
		
		science.click(science.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(science.getDriver());
		
		PGOWhackPage whack = choose.clickWhack();
		user.customWait().until(ExpectedConditions.elementToBeClickable(whack.startButton));
		whack.waitImplicitly(3);
		
		assertTrue("Asserts that all initial whack game elements load",
				whack.verifyElementsVisibility(whack.getInitialVisibleElements()));
		
		whack.clickStartButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("imageAppended")));
		
		ArrayList<String> assets = whack.getImageAssets();
		Boolean status = true;
		
		for(String s : assets){
			status = status && whack.verifyAsset(s);
		}
		
		assertTrue("Asserts that the first images served by the game are valid", status);
		
		assertTrue("Asserts that a user can start a Whack game and see elements for the first round",
				whack.verifyImagesAppearing());
		
		student = whack.clickHomeBradcrumb();
	}
	
	@Test
	public void testWhackBiographies(){
		BiographiesContentPage bio = student.clickBiographiesModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bio.gamesLink));
		
		bio.click(bio.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(bio.getDriver());
		
		PGOWhackPage whack = choose.clickWhack();
		user.customWait().until(ExpectedConditions.elementToBeClickable(whack.startButton));
		whack.waitImplicitly(3);
		
		assertTrue("Asserts that all initial whack game elements load",
				whack.verifyElementsVisibility(whack.getInitialVisibleElements()));
		
		whack.clickStartButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("imageAppended")));
		
		ArrayList<String> assets = whack.getImageAssets();
		Boolean status = true;
		
		for(String s : assets){
			status = status && whack.verifyAsset(s);
		}
		
		assertTrue("Asserts that the first images served by the game are valid", status);
		
		assertTrue("Asserts that a user can start a Whack game and see elements for the first round",
				whack.verifyImagesAppearing());
		
		student = whack.clickHomeBradcrumb();
	}
	
	@Test
	public void testWhackSocialStudies(){
		SocialStudiesContentPage socialStudies = student.clickSocialStudiesModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(socialStudies.gamesLink));

		socialStudies.click(socialStudies.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(socialStudies.getDriver());
		
		PGOWhackPage whack = choose.clickWhack();
		user.customWait().until(ExpectedConditions.elementToBeClickable(whack.startButton));
		whack.waitImplicitly(3);
		
		assertTrue("Asserts that all initial whack game elements load",
				whack.verifyElementsVisibility(whack.getInitialVisibleElements()));
		
		whack.clickStartButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("imageAppended")));
		
		ArrayList<String> assets = whack.getImageAssets();
		Boolean status = true;
		
		for(String s : assets){
			status = status && whack.verifyAsset(s);
		}
		
		assertTrue("Asserts that the first images served by the game are valid", status);
		
		assertTrue("Asserts that a user can start a Whack game and see elements for the first round",
				whack.verifyImagesAppearing());
		
		student = whack.clickHomeBradcrumb();
	}
	
	@Test
	public void testWhackDinosaurs(){
		DinosaursContentPage dinos = student.clickDinosaursModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dinos.gamesLink));

		dinos.click(dinos.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(dinos.getDriver());
		
		PGOWhackPage whack = choose.clickWhack();
		user.customWait().until(ExpectedConditions.elementToBeClickable(whack.startButton));
		whack.waitImplicitly(3);
		
		assertTrue("Asserts that all initial whack game elements load",
				whack.verifyElementsVisibility(whack.getInitialVisibleElements()));
		
		whack.clickStartButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("imageAppended")));
		
		ArrayList<String> assets = whack.getImageAssets();
		Boolean status = true;
		
		for(String s : assets){
			status = status && whack.verifyAsset(s);
		}
		
		assertTrue("Asserts that the first images served by the game are valid", status);
		
		assertTrue("Asserts that a user can start a Multi Match game and see elements for the first round",
				whack.verifyImagesAppearing());
		
		student = whack.clickHomeBradcrumb();
	}
	
	@Test
	public void testWhackAnimales(){
		AnimalesContentPage animales = student.clickAnimalesModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(animales.gamesLink));
		
		animales.click(animales.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(animales.getDriver());
		
		PGOWhackPage whack = choose.clickWhack();
		user.customWait().until(ExpectedConditions.elementToBeClickable(whack.startButton));
		whack.waitImplicitly(3);
		
		assertTrue("Asserts that all initial whack game elements load",
				whack.verifyElementsVisibility(whack.getInitialVisibleElements()));
		
		whack.clickStartButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("imageAppended")));
		
		ArrayList<String> assets = whack.getImageAssets();
		Boolean status = true;
		
		for(String s : assets){
			status = status && whack.verifyAsset(s);
		}
		
		assertTrue("Asserts that the first images served by the game are valid", status);
		
		assertTrue("Asserts that a user can start a Multi Match game and see elements for the first round",
				whack.verifyImagesAppearing());
		
		student = whack.clickHomeBradcrumb();
	}
	
	@Test
	public void testWhackCiencia(){
		ContentSelectionPage ciencia = student.clickCienciaModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ciencia.gamesLink));
		
		ciencia.click(ciencia.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(ciencia.getDriver());
		
		PGOWhackPage whack = choose.clickWhack();
		user.customWait().until(ExpectedConditions.elementToBeClickable(whack.startButton));
		whack.waitImplicitly(3);
		
		assertTrue("Asserts that all initial whack game elements load",
				whack.verifyElementsVisibility(whack.getInitialVisibleElements()));
		
		whack.clickStartButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("imageAppended")));
		
		ArrayList<String> assets = whack.getImageAssets();
		Boolean status = true;
		
		for(String s : assets){
			status = status && whack.verifyAsset(s);
		}
		
		assertTrue("Asserts that the first images served by the game are valid", status);
		
		assertTrue("Asserts that a user can start a Multi Match game and see elements for the first round",
				whack.verifyImagesAppearing());
		
		student = whack.clickHomeBradcrumb();
	}
	
	@Test
	public void testWhackBiografias(){
		ContentSelectionPage biografias = student.clickBiografiasModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(biografias.gamesLink));
		
		biografias.click(biografias.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(biografias.getDriver());
		
		PGOWhackPage whack = choose.clickWhack();
		user.customWait().until(ExpectedConditions.elementToBeClickable(whack.startButton));
		whack.waitImplicitly(3);
		
		assertTrue("Asserts that all initial whack game elements load",
				whack.verifyElementsVisibility(whack.getInitialVisibleElements()));
		
		whack.clickStartButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("imageAppended")));
		
		ArrayList<String> assets = whack.getImageAssets();
		Boolean status = true;
		
		for(String s : assets){
			status = status && whack.verifyAsset(s);
		}
		
		assertTrue("Asserts that the first images served by the game are valid", status);
		
		assertTrue("Asserts that a user can start a Multi Match game and see elements for the first round",
				whack.verifyImagesAppearing());
		
		student = whack.clickHomeBradcrumb();
	}
	
	@Test
	public void testWhackEstudiosSciales(){
		ContentSelectionPage biografias = student.clickEstudiosSocialesModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(biografias.gamesLink));
		
		biografias.click(biografias.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(biografias.getDriver());
		
		PGOWhackPage whack = choose.clickWhack();
		user.customWait().until(ExpectedConditions.elementToBeClickable(whack.startButton));
		whack.waitImplicitly(3);
		
		assertTrue("Asserts that all initial whack game elements load",
				whack.verifyElementsVisibility(whack.getInitialVisibleElements()));
		
		whack.clickStartButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("imageAppended")));
		
		ArrayList<String> assets = whack.getImageAssets();
		Boolean status = true;
		
		for(String s : assets){
			status = status && whack.verifyAsset(s);
		}
		
		assertTrue("Asserts that the first images served by the game are valid", status);
		
		assertTrue("Asserts that a user can start a Multi Match game and see elements for the first round",
				whack.verifyImagesAppearing());
		
		student = whack.clickHomeBradcrumb();
	}
	
	@Test
	public void testWordScrambleAnimals(){
		AnimalsContentPage animals = student.clickAnimalsModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(animals.gamesLink));
		
		animals.click(animals.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(animals.getDriver());
		
		PGOWordScramblePage scramble = choose.clickWordScramble();
		user.customWait().until(ExpectedConditions.elementToBeClickable(scramble.startButton));
		scramble.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Word Scramble elements load",
				scramble.verifyElementsVisibility(scramble.getInitialVisibleElements()));
		
		scramble.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(1);
		
		assertTrue("Asserts that the first image served by the game is valid", scramble.verifyAsset(scramble.getImageAsset()));
		
		assertTrue("Asserts that a user can start a Scramble game and see elements for the first round",
				scramble.verifyElementsVisibility(scramble.getAfterStartElements()));
		
		student = scramble.clickHomeBradcrumb();
	}
	
	@Test
	public void testWordScrambleScience(){
		ScienceContentPage science = student.clickScienceModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(science.gamesLink));
		
		science.click(science.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(science.getDriver());
		
		PGOWordScramblePage scramble = choose.clickWordScramble();
		user.customWait().until(ExpectedConditions.elementToBeClickable(scramble.startButton));
		scramble.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Word Scramble elements load",scramble.verifyElementsVisibility(scramble.getInitialVisibleElements()));
		
		scramble.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(1);
		
		assertTrue("Asserts that the first image served by the game is valid", scramble.verifyAsset(scramble.getImageAsset()));
		
		assertTrue("Asserts that a user can start a Scramble game and see elements for the first round",
				scramble.verifyElementsVisibility(scramble.getAfterStartElements()));
		
		student = scramble.clickHomeBradcrumb();
	}
	
	@Test
	public void testWordScrambleBiographies(){
		BiographiesContentPage bio = student.clickBiographiesModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bio.gamesLink));
		
		bio.click(bio.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(bio.getDriver());
		
		PGOWordScramblePage scramble = choose.clickWordScramble();
		user.customWait().until(ExpectedConditions.elementToBeClickable(scramble.startButton));
		scramble.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Word Scramble elements load",
				scramble.verifyElementsVisibility(scramble.getInitialVisibleElements()));
		
		scramble.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(1);
		
		assertTrue("Asserts that the first image served by the game is valid", scramble.verifyAsset(scramble.getImageAsset()));
		
		assertTrue("Asserts that a user can start a Scramble game and see elements for the first round",
				scramble.verifyElementsVisibility(scramble.getAfterStartElements()));
		
		student = scramble.clickHomeBradcrumb();
	}
	
	@Test
	public void testWordScrambleSocialStudies(){
		SocialStudiesContentPage social = student.clickSocialStudiesModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(social.gamesLink));
		
		social.click(social.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(social.getDriver());
		
		PGOWordScramblePage scramble = choose.clickWordScramble();
		user.customWait().until(ExpectedConditions.elementToBeClickable(scramble.startButton));
		scramble.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Word Scramble elements load",
				scramble.verifyElementsVisibility(scramble.getInitialVisibleElements()));
		
		scramble.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(1);
		
		assertTrue("Asserts that the first image served by the game is valid", scramble.verifyAsset(scramble.getImageAsset()));
		
		assertTrue("Asserts that a user can start a Scramble game and see elements for the first round",
				scramble.verifyElementsVisibility(scramble.getAfterStartElements()));
		
		student = scramble.clickHomeBradcrumb();
	}
	
	@Test
	public void testWordScrambleDinosaurs(){
		DinosaursContentPage dinos = student.clickDinosaursModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dinos.gamesLink));
		
		dinos.click(dinos.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(dinos.getDriver());
		
		PGOWordScramblePage scramble = choose.clickWordScramble();
		user.customWait().until(ExpectedConditions.elementToBeClickable(scramble.startButton));
		scramble.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Word Scramble elements load",
				scramble.verifyElementsVisibility(scramble.getInitialVisibleElements()));
		
		scramble.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(1);
		
		assertTrue("Asserts that the first image served by the game is valid", scramble.verifyAsset(scramble.getImageAsset()));
		
		assertTrue("Asserts that a user can start a Scramble game and see elements for the first round",
				scramble.verifyElementsVisibility(scramble.getAfterStartElements()));
		
		student = scramble.clickHomeBradcrumb();
	}
	
	@Test
	public void testWordScrambleAnimales(){
		AnimalesContentPage animales = student.clickAnimalesModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(animales.gamesLink));
		
		animales.click(animales.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(animales.getDriver());
		
		PGOWordScramblePage scramble = choose.clickWordScramble();
		user.customWait().until(ExpectedConditions.elementToBeClickable(scramble.startButton));
		scramble.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Word Scramble elements load",
				scramble.verifyElementsVisibility(scramble.getInitialVisibleElements()));
		
		scramble.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(1);
		
		assertTrue("Asserts that the first image served by the game is valid", scramble.verifyAsset(scramble.getImageAsset()));
		
		assertTrue("Asserts that a user can start a Scramble game and see elements for the first round",
				scramble.verifyElementsVisibility(scramble.getAfterStartElements()));
		
		student = scramble.clickHomeBradcrumb();
	}
	
	@Test
	public void testWordScrambleCiencia(){
		ContentSelectionPage ciencia = student.clickCienciaModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ciencia.gamesLink));
		
		ciencia.click(ciencia.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(ciencia.getDriver());
		
		PGOWordScramblePage scramble = choose.clickWordScramble();
		user.customWait().until(ExpectedConditions.elementToBeClickable(scramble.startButton));
		scramble.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Word Scramble elements load",
				scramble.verifyElementsVisibility(scramble.getInitialVisibleElements()));
		
		scramble.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(1);
		
		assertTrue("Asserts that the first image served by the game is valid", scramble.verifyAsset(scramble.getImageAsset()));
		
		assertTrue("Asserts that a user can start a Scramble game and see elements for the first round",
				scramble.verifyElementsVisibility(scramble.getAfterStartElements()));
		
		student = scramble.clickHomeBradcrumb();
	}
	
	@Test
	public void testWordScrambleBiografias(){
		ContentSelectionPage biografias = student.clickBiografiasModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(biografias.gamesLink));
		
		biografias.click(biografias.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(biografias.getDriver());
		
		PGOWordScramblePage scramble = choose.clickWordScramble();
		user.customWait().until(ExpectedConditions.elementToBeClickable(scramble.startButton));
		scramble.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Word Scramble elements load",
				scramble.verifyElementsVisibility(scramble.getInitialVisibleElements()));
		
		scramble.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(1);
		
		assertTrue("Asserts that the first image served by the game is valid", scramble.verifyAsset(scramble.getImageAsset()));
		
		assertTrue("Asserts that a user can start a Scramble game and see elements for the first round",
				scramble.verifyElementsVisibility(scramble.getAfterStartElements()));
		
		student = scramble.clickHomeBradcrumb();
	}
	
	@Test
	public void testWordScrambleEstudioSociales(){
		ContentSelectionPage estudios = student.clickEstudiosSocialesModule();
		user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(estudios.gamesLink));
		
		estudios.click(estudios.gamesLink);
		PGOChooseGamePage choose = new PGOChooseGamePage(estudios.getDriver());
		
		PGOWordScramblePage scramble = choose.clickWordScramble();
		user.customWait().until(ExpectedConditions.elementToBeClickable(scramble.startButton));
		scramble.waitImplicitly(3);
		
		assertTrue("Asserts that all initial Word Scramble elements load",
				scramble.verifyElementsVisibility(scramble.getInitialVisibleElements()));
		
		scramble.clickStartButton();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(scramble.startButton));
		scramble.waitImplicitly(1);
		
		assertTrue("Asserts that the first image served by the game is valid", scramble.verifyAsset(scramble.getImageAsset()));
		
		assertTrue("Asserts that a user can start a Scramble game and see elements for the first round",
				scramble.verifyElementsVisibility(scramble.getAfterStartElements()));
		
		student = scramble.clickHomeBradcrumb();
	}
}
