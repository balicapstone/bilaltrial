package Tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;

import CMSGames.EditMultiMatchGame;
import CMSGames.EditQuickMatchGame;
import CMSGames.EditWhackGame;
import CMSGames.EditWordScrambleGame;
import CMSGames.EditZoomGame;
import CMSPages.CMSHomePage;
import CMSPages.CMSLandingPage;
import CMSPages.EditGamesPage;
import Games.PGNChooseGamePage;
import Games.PGNQuickMatchPage;
import Games.PGNWordScramblePage;
import Games.PGNZoomPage;
import Games.PGOChooseGamePage;
import Games.PGOMultiMatchPage;
import Games.PGOQuickMatchPage;
import Games.PGOWhackPage;
import Games.PGOWordScramblePage;
import Games.PGOZoomPage;
import MasterAccount.TeacherLogin;
import PGNStudentResources.DatabasePage;
import PGOStudentResources.AnimalsContentPage;
import PGOStudentResources.ContentSelectionPage;
import PGOStudentResources.PGONewStudentHomePage;
import PGOStudentResources.StudentHomePage;
import UserClasses.User;
import UserClasses.UserInfo;

public class EditGamesTests {
	static User user = new User(UserInfo.GARAGESTUDENT);
	static User student = new User(UserInfo.GAMESTEST);
	static TeacherLogin tLogin;
	static CMSHomePage home;
	static StudentHomePage pgoStudent;
	static PGOPages.LoginPage pgoLoginPage;
	static PGNPages.LoginPage pgnLoginPage;
	static EditGamesPage editGames;
	
	
	@BeforeClass
	public static void executeBefore(){
		CMSLandingPage cms = new CMSLandingPage(user.getDriver(), "qams");
		cms.waitImplicitly(10);
		
		home = cms.login(user);
		home.waitImplicitly(10);
		
		home.openToolbar();
		editGames = home.toolbar.clickGames();
		editGames.waitImplicitly(2);
	}
	
	
	@AfterClass
	public static void executeAfter(){
		user.quit();
		student.quit();
	}
	
	//@Rule
    public TestWatcher restart = new TestWatcher(){
    	@Override
    	public void failed(Throwable e, Description description){
    		user.getWatcher().TakeScreenshot(description.getDisplayName());
    		user.quit();
    	}
    	
    };
	
    //@Rule
	//public Retry retry = new Retry(3);
	
    @Test
    public void testChangePGOZoomImage(){   	
 		pgoLoginPage = new PGOPages.LoginPage(student, "editorialqa");
 		pgoStudent = (StudentHomePage) pgoLoginPage.Login(student);
    	
    	editGames.scrollToElement(editGames.getDriver().findElement(By.id("test-PebbleGo Social Studies")));
    	editGames.waitImplicitly(3);
    	
    	editGames.scrollToTopofPage();
    	editGames.waitImplicitly(3);
    	
    	editGames.clickModuleByName("PebbleGo Animals");
    	editGames.waitImplicitly(1);
    	editGames.clickGameByName("Zoom");
    	
    	EditZoomGame editZoom = new EditZoomGame(editGames.getDriver());
    	editZoom.waitImplicitly(2);
    	
    	editZoom.EditEntryByInt(0);
    	editZoom.waitImplicitly(1);
    	String oldImage = editZoom.getImage();	
    	String newImage = "error.jpg";
    	
    	editZoom.setImage(newImage);
    	
    	editZoom.clickSubmit();
    	editZoom.waitImplicitly(2);
    	
    	editZoom.clickThroughAlert();
    	
    	AnimalsContentPage animals = pgoStudent.clickAnimalsModule();
    	animals.waitImplicitly(2);
    	
    	PGOChooseGamePage games = animals.clickGamesButton();
    	games.waitImplicitly(2);
    	
    	PGOZoomPage zoom = games.clickZoom();
    	zoom.waitImplicitly(10);
    	
    	zoom.clickStartButton();
    	zoom.waitImplicitly(5);
    	
    	assertTrue("Asserts that the first image in the zoom game was changed", 
    		!zoom.getCurrentPicture().equals(oldImage)
    			&& zoom.getCurrentPicture().equals(newImage));
    	
    	
    	editZoom.EditEntryByInt(0);
    	editZoom.waitImplicitly(3);
    	editZoom.setImage(oldImage);
    	editZoom.clickSubmit();
    	editZoom.waitImplicitly(3);
    	editZoom.clickThroughAlert();
    	
    	zoom.refresh();
    	zoom.waitImplicitly(10);
    	zoom.clickStartButton();
    	zoom.waitImplicitly(5);
    	
    	assertTrue("Asserts that the first image in the zoom game was changed", 
    			zoom.getCurrentPicture().equals(oldImage)
    			&& !zoom.getCurrentPicture().equals(newImage));
    	
    	
    	zoom.quit();
    }
    
    @Test
    public void testChangeWordScrambleImagePGO(){	
 		pgoLoginPage = new PGOPages.LoginPage(student, "editorialqa");
 		pgoStudent = (StudentHomePage) pgoLoginPage.Login(student);
 		
    	editGames.scrollToElement(editGames.getDriver().findElement(By.id("test-PebbleGo Social Studies")));
    	editGames.waitImplicitly(5);
    	
    	editGames.scrollToTopofPage();
    	editGames.waitImplicitly(3);
    	
    	editGames.clickModuleByName("PebbleGo Games");
    	editGames.waitImplicitly(3);
    	editGames.clickGameByName("Word Scramble");
    	editGames.waitImplicitly(3);
    	
    	EditWordScrambleGame editWS = new EditWordScrambleGame(editGames.getDriver());
    	editWS.editEntryByInt(1);
    	editWS.waitImplicitly(5);
    	
    	String newAnswer = "abcde fgh ijkl";
    	String newImage = "error.jpg";
    	String newHint = "This was changed";
    	String oldAnswer = "Great White Shark";
    	String oldImage = "game_greatwhitesharks_1.jpg";
    	String oldHint = "I have more than 3,000 teeth.";
    	
    	editWS.setAnswer(newAnswer);
    	editWS.setImage(newImage);
    	editWS.setHint(newHint);
    	
    	editWS.clickSubmit();
    	editWS.waitImplicitly(2);
    	editWS.clickThroughAlert();
    	editWS.waitImplicitly(2);
    	
    	ContentSelectionPage gamesModule = pgoStudent.clickModuleByName("Games");
    	gamesModule.waitImplicitly(3);
    	
    	PGOChooseGamePage games = gamesModule.clickGamesButton();
    	games.waitImplicitly(3);
    	
    	PGOWordScramblePage wordScramble = games.clickWordScramble();
    	wordScramble.waitImplicitly(10);
    	wordScramble.clickStartButton();
    	wordScramble.waitImplicitly(3);
    	
    	assertTrue("Asserts that all letters in the answer are present on the page", wordScramble.areAllAnswerLettersPresent(newAnswer));
    	assertTrue("Asserts that the image has been changed to the new image", wordScramble.getImage().equals(newImage));
    	assertTrue("Asserts that the hint text has been changed", wordScramble.getHintText().equals(newHint));
    	
    	editWS.editEntryByInt(1);
    	editWS.waitImplicitly(5);
    	
    	editWS.setAnswer(oldAnswer);
    	editWS.setImage(oldImage);
    	editWS.setHint(oldHint);
    	
    	editWS.clickSubmit();
    	editWS.waitImplicitly(2);
    	editWS.clickThroughAlert();
    	editWS.waitImplicitly(2);
    	
    	wordScramble.refresh();
    	wordScramble.waitImplicitly(10);
    	wordScramble.clickStartButton();
    	wordScramble.waitImplicitly(3);
    	
    	assertTrue("Asserts that all letters in the answer are present on the page", wordScramble.areAllAnswerLettersPresent(oldAnswer));
    	assertTrue("Asserts that the image has been changed to the new image", wordScramble.getImage().equals(oldImage));
    	assertTrue("Asserts that the hint text has been changed", wordScramble.getHintText().equals(oldHint));
    	
    	wordScramble.quit();
    }
    
    
    //Can't write until I can find image
    @Test
    public void testChangeJigsawImage(){
    	
    }
    
    @Test
    public void testChangeMultiMatchInfoPGO(){
 		pgoLoginPage = new PGOPages.LoginPage(student, "editorialqa");
 		pgoStudent = (StudentHomePage) pgoLoginPage.Login(student);
    	
    	editGames.scrollToElement(editGames.getDriver().findElement(By.id("test-PebbleGo Social Studies")));
    	editGames.waitImplicitly(3);
    	
    	editGames.scrollToTopofPage();
    	editGames.waitImplicitly(3);
    	
    	editGames.clickModuleByName("PebbleGo Games");
    	editGames.waitImplicitly(1);
    	editGames.clickGameByName("Multi Match");
    	
    	EditMultiMatchGame editMultiMatch = new EditMultiMatchGame(editGames.getDriver());
    	editMultiMatch.waitImplicitly(2);
    	
    	editMultiMatch.editEntryByInt(0);
    	editMultiMatch.waitImplicitly(2);
    	String oldFirstAnswer = "Emperor Penguin";
    	String oldFirstImage = "game_emperorpenguins_1.jpg";
    	String oldSecondAnswer = "Squid";
    	String oldSecondImage = "game_squid_1.jpg";
    	String newFirstAnswer = "silly error";
    	String newFirstImage = "error.jpg";
    	String newSecondAnswer = "Another Error";
    	String newSecondImage = "error2.jpg";
    	
    	editMultiMatch.setNameOne(newFirstAnswer);
    	editMultiMatch.setImageOne(newFirstImage);
    	editMultiMatch.setNameTwo(newSecondAnswer);
    	editMultiMatch.setImageTwo(newSecondImage);
    	
    	editMultiMatch.clickSubmit();
    	editMultiMatch.waitImplicitly(2);
    	
    	editMultiMatch.clickThroughAlert();
    	editMultiMatch.waitImplicitly(2);
    	
    	ContentSelectionPage gamesModule = pgoStudent.clickModuleByName("Games");
    	gamesModule.waitImplicitly(3);
    	PGOChooseGamePage choose = gamesModule.clickGamesButton();
    	
    	PGOMultiMatchPage multiMatch = choose.clickMultiMatch();
    	multiMatch.waitImplicitly(10);
    	
    	String firstPredatorImage = multiMatch.getPredatorImageByInt(1);
    	String firstPredatorName = multiMatch.getPredatorNameByInt(1);
    	String firstPreyImage = multiMatch.getPreyImageByInt(1);
    	String firstPreyName = multiMatch.getPreyNameByInt(1);
    	
    	assertTrue("Asserts that the first predator image was changed", firstPredatorImage.equals(newFirstImage));
    	assertTrue("Asserts that the first predator name was changed", firstPredatorName.equals(newFirstAnswer));
    	assertTrue("Asserts that the first prey image was changed", firstPreyImage.equals(newSecondImage));
    	assertTrue("Asserts that the first predator image was changed", firstPreyName.equals(newSecondAnswer));
    	
    	editMultiMatch.editEntryByInt(0);
    	editMultiMatch.setNameOne(oldFirstAnswer);
    	editMultiMatch.setImageOne(oldFirstImage);
    	editMultiMatch.setNameTwo(oldSecondAnswer);
    	editMultiMatch.setImageTwo(oldSecondImage);
    	
    	editMultiMatch.clickSubmit();
    	editMultiMatch.waitImplicitly(2);
    	
    	editMultiMatch.clickThroughAlert();
    	editMultiMatch.waitImplicitly(2);
    	
    	multiMatch.refresh();
    	multiMatch.waitImplicitly(5);
    	
    	firstPredatorImage = multiMatch.getPredatorImageByInt(1);
    	firstPredatorName = multiMatch.getPredatorNameByInt(1);
    	firstPreyImage = multiMatch.getPreyImageByInt(1);
    	firstPreyName = multiMatch.getPreyNameByInt(1);
    	
    	assertTrue("Asserts that the first predator image was changed", firstPredatorImage.equals(oldFirstImage));
    	assertTrue("Asserts that the first predator name was changed", firstPredatorName.equals(oldFirstAnswer));
    	assertTrue("Asserts that the first prey image was changed", firstPreyImage.equals(oldSecondImage));
    	assertTrue("Asserts that the first predator image was changed", firstPreyName.equals(oldSecondAnswer));
    	
    	multiMatch.quit();
    }
    
    @Test
    public void testChangeQuickMatchImagePGO(){
 		pgoLoginPage = new PGOPages.LoginPage(student, "editorialqa");
 		pgoStudent = (StudentHomePage) pgoLoginPage.Login(student);
    	
    	editGames.scrollToElement(editGames.getDriver().findElement(By.id("test-PebbleGo Social Studies")));
    	editGames.waitImplicitly(3);
    	
    	editGames.scrollToTopofPage();
    	editGames.waitImplicitly(3);
    	
    	editGames.clickModuleByName("PebbleGo Animals");
    	editGames.waitImplicitly(1);
    	editGames.clickGameByName("Quick Match");
    	
    	EditQuickMatchGame editQM = new EditQuickMatchGame(editGames.getDriver());
    	editQM.waitImplicitly(2);
    	
    	editQM.editEntryByInt(0);
    	editQM.waitImplicitly(2);
    	
    	String newImage = "error.jpg";
    	editQM.setImage(newImage);
    	editQM.clickSumbit();
    	editQM.waitImplicitly(2);
    	editQM.clickThroughAlert();  
    	
    	AnimalsContentPage animals = pgoStudent.clickAnimalsModule();
    	
    	PGOChooseGamePage games = animals.clickGamesButton();
    	
    	PGOQuickMatchPage quick = games.clickQuickMatch();
    	quick.waitImplicitly(10);
    	
    	quick.clickStartButton();
    	quick.waitImplicitly(2);
    	
    	
    	String oldImage = "game_ladybugs_1.jpg";
    	Boolean newImagePresent = false;
    	Boolean oldImagePresent = false;
    	
    	while(!quick.isElementPresent(By.xpath("//*[@id=\"btn_yesButton\"]"))){
    		try{
    			String checkNewImage = quick.getRightImage();
    			if(checkNewImage.equals(newImage)){
    				newImagePresent = true;
    			}
    		}catch(Exception e){
    			
    		}
    		
    		try{
    			String checkOldImage = quick.getRightImage();
    			if(checkOldImage.equals(oldImage)){
    				oldImagePresent = true;
    			}
    		}catch(Exception e){
    			
    		}
    	}
    	
    	assertTrue("Asserts that the old image does not appear but the new image does",
    			!oldImagePresent && newImagePresent);
    	
    	
    	editQM.editEntryByInt(0);
    	editQM.waitImplicitly(2);
    	
    	editQM.setImage(oldImage);
    	editQM.clickSumbit();
    	editQM.waitImplicitly(2);
    	editQM.clickThroughAlert();  
    	editQM.waitImplicitly(3);
    	
    	quick.refresh();
    	quick.waitImplicitly(5);
    	
    	quick.clickStartButton();
    	quick.waitImplicitly(2);
   
    	newImagePresent = false;
    	oldImagePresent = false;
    	
    	while(!quick.isElementPresent(By.xpath("//*[@id=\"btn_yesButton\"]"))){
    		try{
    			String checkNewImage = quick.getRightImage();
    			if(checkNewImage.equals(newImage)){
    				newImagePresent = true;
    			}
    			
    			String checkOldImage = quick.getRightImage();
    			if(checkOldImage.equals(oldImage)){
    				oldImagePresent = true;
    			}
    		}catch(Exception e){
    			
    		}
    	}
    	
    	assertTrue("Asserts that the old image does not appear but the new image does",
    			oldImagePresent && !newImagePresent);
    	
    	pgoStudent = quick.clickHomeBradcrumb();
    	quick.waitImplicitly(5);
    }
    
    @Test
    public void testChangeWhackInfoPGO(){
 		pgoLoginPage = new PGOPages.LoginPage(student, "editorialqa");
 		pgoStudent = (StudentHomePage) pgoLoginPage.Login(student);
    	
    	editGames.scrollToElement(editGames.getDriver().findElement(By.id("test-PebbleGo Social Studies")));
    	editGames.waitImplicitly(3);
    	
    	editGames.scrollToTopofPage();
    	editGames.waitImplicitly(3);
    	
    	editGames.clickModuleByName("PebbleGo Games");
    	editGames.waitImplicitly(1);
    	editGames.clickGameByName("Whack");
    	
    	EditWhackGame editQM = new EditWhackGame(editGames.getDriver());
    	editQM.waitImplicitly(2);
    	
    	editQM.editEntryByInt(0);
    	editQM.waitImplicitly(2);
    	
    	String oldImage = "game_gorillas_2.jpg";
    	String oldImageName = "Gorilla";
    	String newImage = "error.jpg";
    	String newImageName = "Big Error";
    	
    	editQM.setImage(newImage);
    	editQM.setImageName(newImageName);
    	editQM.clickSubmit();
    	editQM.waitImplicitly(3);
    	
    	editQM.clickThroughAlert();
    	editQM.waitImplicitly(2);
    	
    	ContentSelectionPage gamesModule = pgoStudent.clickModuleByName("Games");
    	gamesModule.waitImplicitly(3);
    	PGOChooseGamePage choose = gamesModule.clickGamesButton();
    	
    	PGOWhackPage whack = choose.clickWhack();
    	whack.waitImplicitly(10);
    	
    	whack.clickStartButton();
    	whack.waitImplicitly(5);
    	
    	Boolean appear = false;
    	
    	try{	
    		appear = whack.doesImageAppear(newImage);
    	}catch(Exception e){
    			
    	}
    	whack.waitImplicitly(2);
    	
    	String image = whack.getImageNameByInt(0);
    	String name = whack.getCorrectImageByInt(0);
    	
    	assertTrue("Asserts that the test image appears", appear);
    	assertTrue("Asserts that the test image can be clicked and appear as correct", image.equals(newImage));
    	assertTrue("Asserts that the test image name shows up as a correct answer", name.equals(newImageName));
    	
    	editQM.editEntryByInt(0);
    	editQM.waitImplicitly(2);
    	
    	editQM.setImage(oldImage);
    	editQM.setImageName(oldImageName);
    	editQM.clickSubmit();
    	editQM.waitImplicitly(3);
    	
    	editQM.clickThroughAlert();
    	editQM.waitImplicitly(2);
    	
    	whack.refresh();
    	whack.waitImplicitly(10);
    	
    	whack.clickStartButton();
    	whack.waitImplicitly(5);
    	
    	appear = false;
    	
    	try{	
    		appear = whack.doesImageAppear(oldImage);
    	}catch(Exception e){
    			
    	}
    	whack.waitImplicitly(2);
    	
    	image = whack.getImageNameByInt(0);
    	name = whack.getCorrectImageByInt(0);
    	
    	assertTrue("Asserts that the old image reappears", appear);
    	assertTrue("Asserts that the old image can be clicked and appear as correct", image.equals(oldImage));
    	assertTrue("Asserts that the old image name shows up as a correct answer", name.equals(oldImageName));
    	
    	whack.quit();
    }
    
    @Test
    public void testPGNZoom(){
    	pgnLoginPage = new PGNPages.LoginPage(student, "editorialqa");
    	PGNStudentResources.StudentHomePage pgnStudent = (PGNStudentResources.StudentHomePage) pgnLoginPage.login(student);
    	
    	editGames.scrollToElement(editGames.getDriver().findElement(By.id("test-PebbleGo Social Studies")));
    	editGames.waitImplicitly(3);
    	
    	editGames.scrollToTopofPage();
    	editGames.waitImplicitly(3);
    	
    	editGames.clickModuleByName("PebbleGo Next Games");
    	editGames.waitImplicitly(1);
    	editGames.clickGameByName("Zoom");
    	
    	EditZoomGame editZoom = new EditZoomGame(editGames.getDriver());
    	editZoom.waitImplicitly(2);
    	
    	editZoom.EditEntryByInt(0);
    	editZoom.waitImplicitly(1);
    	String oldImage = "game_dolphin_1.jpg";
    	String newImage = "error.jpg";
    	
    	editZoom.setImage(newImage);
    	
    	editZoom.clickSubmit();
    	editZoom.waitImplicitly(2);
    	
    	editZoom.clickThroughAlert();
    	
    	DatabasePage gamesModule = pgnStudent.clickModuleByName("Games");
    	gamesModule.waitImplicitly(2);
    	
    	PGNChooseGamePage games = gamesModule.openGames();
    	games.waitImplicitly(2);
    	
    	PGNZoomPage zoom = games.clickZoom();
    	zoom.waitImplicitly(10);
    	
    	zoom.clickStartButton();
    	zoom.waitImplicitly(5);
    	
    	assertTrue("Asserts that the first image in the zoom game was changed", 
    		!zoom.getCurrentPicture().equals(oldImage)
    			&& zoom.getCurrentPicture().equals(newImage));
    	
    	
    	editZoom.EditEntryByInt(0);
    	editZoom.waitImplicitly(3);
    	editZoom.setImage(oldImage);
    	editZoom.clickSubmit();
    	editZoom.waitImplicitly(3);
    	editZoom.clickThroughAlert();
    	
    	zoom.refresh();
    	zoom.waitImplicitly(10);
    	zoom.clickStartButton();
    	zoom.waitImplicitly(5);
    	
    	assertTrue("Asserts that the first image in the zoom game was changed", 
    			zoom.getCurrentPicture().equals(oldImage)
    			&& !zoom.getCurrentPicture().equals(newImage));
    	
    	
    	zoom.quit();
    }
    
    @Test
    public void testPGNQuickMatch(){
    	pgnLoginPage = new PGNPages.LoginPage(student, "editorialqa");
    	PGNStudentResources.StudentHomePage pgnStudent = (PGNStudentResources.StudentHomePage) pgnLoginPage.login(student);
    	
    	editGames.scrollToElement(editGames.getDriver().findElement(By.id("test-PebbleGo Social Studies")));
    	editGames.waitImplicitly(3);
    	
    	editGames.scrollToTopofPage();
    	editGames.waitImplicitly(3);
    	
    	editGames.clickModuleByName("PebbleGo Animals");
    	editGames.waitImplicitly(1);
    	editGames.clickGameByName("Quick Match");
    	
    	EditQuickMatchGame editQM = new EditQuickMatchGame(editGames.getDriver());
    	editQM.waitImplicitly(2);
    	
    	editQM.editEntryByInt(0);
    	editQM.waitImplicitly(2);
    	
    	String newImage = "error.jpg";
    	editQM.setImage(newImage);
    	editQM.clickSumbit();
    	editQM.waitImplicitly(2);
    	editQM.clickThroughAlert();  
    	
    	DatabasePage gamesModule = pgnStudent.clickModuleByName("Games");
    	gamesModule.waitImplicitly(2);
    	
    	PGNChooseGamePage games = gamesModule.openGames();
    	games.waitImplicitly(2);
    	
    	PGNQuickMatchPage quick = games.clickQuickMatch();
    	quick.waitImplicitly(10);
    	
    	quick.clickStartButton();
    	quick.waitImplicitly(2);
    	
    	
    	String oldImage = "game_ladybugs_1.jpg";
    	Boolean newImagePresent = false;
    	Boolean oldImagePresent = false;
    	
    	while(!quick.isElementPresent(By.xpath("//*[@id=\"btn_yesButton\"]"))){
    		try{
    			String checkNewImage = quick.getRightImage();
    			if(checkNewImage.equals(newImage)){
    				newImagePresent = true;
    			}
    		}catch(Exception e){
    			
    		}
    		
    		try{
    			String checkOldImage = quick.getRightImage();
    			if(checkOldImage.equals(oldImage)){
    				oldImagePresent = true;
    			}
    		}catch(Exception e){
    			
    		}
    	}
    	
    	assertTrue("Asserts that the old image does not appear but the new image does",
    			!oldImagePresent && newImagePresent);
    	
    	
    	editQM.editEntryByInt(0);
    	editQM.waitImplicitly(2);
    	
    	editQM.setImage(oldImage);
    	editQM.clickSumbit();
    	editQM.waitImplicitly(2);
    	editQM.clickThroughAlert();  
    	editQM.waitImplicitly(3);
    	
    	quick.refresh();
    	quick.waitImplicitly(5);
    	
    	quick.clickStartButton();
    	quick.waitImplicitly(2);
   
    	newImagePresent = false;
    	oldImagePresent = false;
    	
    	while(!quick.isElementPresent(By.xpath("//*[@id=\"btn_yesButton\"]"))){
    		try{
    			String checkNewImage = quick.getRightImage();
    			if(checkNewImage.equals(newImage)){
    				newImagePresent = true;
    			}
    			
    			String checkOldImage = quick.getRightImage();
    			if(checkOldImage.equals(oldImage)){
    				oldImagePresent = true;
    			}
    		}catch(Exception e){
    			
    		}
    	}
    	
    	assertTrue("Asserts that the old image does not appear but the new image does",
    			oldImagePresent && !newImagePresent);
    	
    	quick.quit();
    }
    
    //Can't write until I can find image
    @Test
    public void testPGNJigsaw(){
    	
    }
    
    @Test
    public void testPGNWordScramble(){
    	pgnLoginPage = new PGNPages.LoginPage(student, "editorialqa");
    	PGNStudentResources.StudentHomePage pgnStudent = (PGNStudentResources.StudentHomePage) pgnLoginPage.login(student);
    	
    	editGames.scrollToElement(editGames.getDriver().findElement(By.id("test-PebbleGo Social Studies")));
    	editGames.waitImplicitly(5);
    	
    	editGames.scrollToTopofPage();
    	editGames.waitImplicitly(3);
    	
    	editGames.clickModuleByName("PebbleGo Games");
    	editGames.waitImplicitly(3);
    	editGames.clickGameByName("Word Scramble");
    	editGames.waitImplicitly(3);
    	
    	EditWordScrambleGame editWS = new EditWordScrambleGame(editGames.getDriver());
    	editWS.editEntryByInt(1);
    	editWS.waitImplicitly(5);
    	
    	String newAnswer = "abcde fgh ijkl";
    	String newImage = "error.jpg";
    	String newHint = "This was changed";
    	String oldAnswer = "Great White Shark";
    	String oldImage = "game_greatwhitesharks_1.jpg";
    	String oldHint = "I have more than 3,000 teeth.";
    	
    	editWS.setAnswer(newAnswer);
    	editWS.setImage(newImage);
    	editWS.setHint(newHint);
    	
    	editWS.clickSubmit();
    	editWS.waitImplicitly(2);
    	editWS.clickThroughAlert();
    	editWS.waitImplicitly(2);
    	
    	DatabasePage gamesModule = pgnStudent.clickModuleByName("Games");
    	gamesModule.waitImplicitly(3);
    	
    	PGNChooseGamePage games = gamesModule.openGames();
    	games.waitImplicitly(3);
    	
    	PGNWordScramblePage wordScramble = games.clickWordScramble();
    	wordScramble.waitImplicitly(10);
    	wordScramble.clickStartButton();
    	wordScramble.waitImplicitly(3);
    	
    	assertTrue("Asserts that all letters in the answer are present on the page", wordScramble.areAllAnswerLettersPresent(newAnswer));
    	assertTrue("Asserts that the image has been changed to the new image", wordScramble.getImage().equals(newImage));
    	assertTrue("Asserts that the hint text has been changed", wordScramble.getHintText().equals(newHint));
    	
    	editWS.editEntryByInt(1);
    	editWS.waitImplicitly(5);
    	
    	editWS.setAnswer(oldAnswer);
    	editWS.setImage(oldImage);
    	editWS.setHint(oldHint);
    	
    	editWS.clickSubmit();
    	editWS.waitImplicitly(2);
    	editWS.clickThroughAlert();
    	editWS.waitImplicitly(2);
    	
    	wordScramble.refresh();
    	wordScramble.waitImplicitly(10);
    	wordScramble.clickStartButton();
    	wordScramble.waitImplicitly(3);
    	
    	assertTrue("Asserts that all letters in the answer are present on the page", wordScramble.areAllAnswerLettersPresent(oldAnswer));
    	assertTrue("Asserts that the image has been changed to the new image", wordScramble.getImage().equals(oldImage));
    	assertTrue("Asserts that the hint text has been changed", wordScramble.getHintText().equals(oldHint));
    	
    	wordScramble.quit();
    }
}
