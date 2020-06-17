package Tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import BookTools.BookModal;
import BookTools.BookReader;
import CITests.CIPages.CILoginPage;
import CITests.CIPages.StudentHomePage;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;
import UserClasses.UserInfo;
import static org.junit.Assert.*;

public class BookReaderTests {
	public static CILoginPage login;
	public static StudentHomePage student;
	public static BookModal bookModal;
	public static BookReader reader;
	public static User user;
	public static WebDriverWait wait;
	
	
	@BeforeClass
	public static void executeBefore(){
		try{
			user = new User(UserInfo.VISUALSEARCH);
			wait = new WebDriverWait(user.getDriver(), 30);
			
			login = new CILoginPage(user);
			student = (StudentHomePage) login.login(user);
			user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
			
			BookModal book = student.clickBookByTitle("10 Little Kittens");
			user.customWait().until(ExpectedConditions.elementToBeClickable(book.bookInfoTab));
			
			reader = book.openBook(); 
			user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
		}catch(Exception e){
			System.out.println("Failure to set up BookReader Tests");
			System.out.println(e);
		}
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
			}
			catch(Exception a){
				System.out.println("We could not take a screenshot at this time");
			}
			
			user.quit();
			user.makeNewDriver();
			login = new CILoginPage(user);
			student = (StudentHomePage) login.login(user);
			user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
			
			BookModal book = student.clickBookByTitle("10 Little Kittens");
			user.customWait().until(ExpectedConditions.elementToBeClickable(book.bookInfoTab));
			
			reader = book.openBook(); 
			user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
		}
	};
	
	@Rule
	public Retry retry = new Retry(3);
	
		
	@Test
	@Category(TrueUserTests.TrueUser.SmokeTests.class)
	public void testPlayButtonAppears(){
		assertTrue("Asserts that the Start button is present when a book loads and that the play button is hidden",
				reader.isElementPresent(reader.readPageByPageButton) && !reader.isElementPresent(reader.playButton));
		
		reader.readBookPageByPage();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(reader.readPageByPageButton));
		assertTrue("Asserts that the start button disappears after clicked and that the play button is now present",
				!reader.isElementPresent(reader.readPageByPageButton) && reader.isElementPresent(reader.playButton));
		
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
	}
	
	@Test
	public void testPressStartShowsPlayButton(){
		reader.readBookPageByPage();
		
		assertTrue("Asserts that the user can play the book and that the start button appears",
				!reader.isBookPaused());
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
	}
	
	@Test
	public void testPauseButtonAppearsWhenPagesNeedsToBeTurned(){
		reader.readBookPageByPage();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(reader.readPageByPageButton));
		
		assertTrue("Asserts that the user can start the book and that the pause button is present",
				!reader.isBookPaused());
		wait.until(ExpectedConditions.attributeContains(reader.playButton, "class", "paused"));
		
		assertTrue("Asserts that the book has paused when the page needs to be turned and that the play button is present",
				reader.isBookPaused());
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
	}
	
	@Test
	public void testPauseButtonChangesWithClicks(){
		reader.startPageByPage();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(reader.readPageByPageButton));
		
		assertTrue("Asserts that the pause button is present after the start button is clicked",
				!reader.isBookPaused());
		
		reader.click(reader.playButton);
		wait.until(ExpectedConditions.attributeContains(reader.playButton, "class", "paused"));
		
		assertTrue("Asserts that the play button is present after the pause button is clicked",
				reader.isBookPaused());
		
		reader.click(reader.playButton);
		wait.until(ExpectedConditions.attributeToBe(reader.playButton, "class", "nav-btn"));
		
		assertTrue("Asserts that the pause button is present after the play button is clicked",
				!reader.isBookPaused());
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
	}
	
	@Test
	public void testContentModalPopsUpAndHides(){
		reader.startPageByPage();
		
		assertTrue("Asserts that the Content Modal is not present when a book is started",
				!reader.isElementPresent(reader.contentModal));
		
		reader.click(reader.contentsButton);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(reader.contentModal));
		
		assertTrue("Asserts that the Content Modal is present after the Contents button is clicked",
				reader.isElementPresent(reader.contentModal));
		
		reader.click(reader.contentsButton);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(reader.contentModal));
		
		assertTrue("Asserts that the Content Modal disappears when the Contents button is clicked again",
				!reader.isElementPresent(reader.contentModal));
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
	}
	
	
	//This test will have to be revisited, as I can not currently click and access the fullscreen button
	/*
	@Test
	public void testFullScreenButtonMaximizes(){
		//reader.click(reader.startButton);
		
		int height = reader.getWindowHeight();
		int width = reader.getWindowWidth();
		
		//reader.click(reader.startButton);
		
		//reader.getDriver().findElement(By.cssSelector("nav-btn"));//By.linkText("Full screen")).click();
		//reader.getDriver().findElement(By.xpath("//*[@id=\"navigation-fullscreen\"]/div")).click();
		
		reader.hoverOverElement(reader.fullScreenButton);
		reader.click(reader.fullScreenButton);
		
		String test = reader.getElementText(By.xpath("//*[@id=\"navigation-fullscreen\"]/div"));
		reader.click(reader.fullScreenButton);
		reader.waitImplicitly(1);
		
		assertTrue((reader.getWindowHeight() > height) && (reader.getWindowWidth() > width));
	}
	*/
	
	@Test
	public void testHideToolBar(){
		reader.startPageByPage();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(reader.readPageByPageButton));
		reader.click(reader.toolbarHandle);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(reader.contentsButton));
		reader.clickNextPage();
		
		assertTrue("Asserts that the toolbar dissappears when the toolbar handle is clicked",
				!reader.isElementPresent(reader.toolbarHandle));
		
		reader.hoverOverElement(reader.playButton);
		reader.hoverOverElement(reader.contentModal);
		
		Boolean present = reader.isElementPresent(reader.toolbarHandle);
		Boolean locked = reader.isToolbarLocked();
		
		assertTrue("Asserts that the toolbar appears when a user hovers over the play button and that the toolbar is still locked",
				present && !locked);
		
		reader.click(reader.toolbarHandle);
		user.customWait().until(ExpectedConditions.attributeContains(reader.toolbarHandle, "class", "pinned"));
		
		assertTrue("Asserts that the toolbar appears when the toolbarHandle is clicked again",
				reader.isElementPresent(reader.toolbarHandle) && reader.isToolbarLocked());
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
	}
	
	@Test
	public void testPageModalPopsUpAndHides(){
		reader.click(reader.pageButton);
		user.customWait().until(ExpectedConditions.attributeToBe(reader.pageModal, "class", "open"));
		//See why this isn't working
		assertTrue("Asserts that the page modal appears after the page button is clicked",
				reader.isPageModalOpen());
		
		reader.click(reader.pageButton);
		user.customWait().until(ExpectedConditions.attributeToBe(reader.pageModal, "class", ""));
		assertTrue("Asserts that the page modal disappears after the page button is clicked again",
				!reader.isPageModalOpen());
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
	}
	
	@Test
	public void testPagesAreReadDuringReadAllAtOnce(){
		reader.startBookAllAtOnce();
		wait = new WebDriverWait(user.getDriver(), 210);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(reader.nextButton));
		wait.until(ExpectedConditions.attributeContains(reader.playButton, "class", "paused"));
		
		boolean allRead = true;
		for(int i = 1; i < 11; i++){
			allRead = allRead && reader.hasPageBeenRead(i);
		}
		
		assertTrue("Asserts that all page divs in the page modal are highlighted after a user has 'read' each page",
				allRead);
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
		wait = new WebDriverWait(user.getDriver(), 30);
	}
	
	@Test
	public void testSkippedPageIsntRead(){
		reader.click(reader.pageButton);
		user.customWait().until(ExpectedConditions.attributeToBe(reader.pageModal, "class", "open"));;
		reader.clickPopUpPageByIndex(1);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(reader.playButton));
		
		//Skips page two and opens page three instead
		for(int i = 3; i < 11; i++){
			reader.clickPopUpPageByIndex(i);
		}
		
		boolean allRead = true;
		for(int i = 1; i < 11; i++){
			allRead = allRead && reader.hasPageBeenRead(i);
		}
		
		assertTrue("Asserts that a user can skip one page and that all remaining page divs in the page modal will be highlighed at the end of the book",
				!allRead && !reader.hasPageBeenRead(2) && reader.hasPageBeenRead(3));
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
	}
	
	@Test
	public void testCloseButton(){
		student = reader.closeBookReader();
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		user.customWait().until(ExpectedConditions.elementToBeClickable(student.filters.spanishCheckbox));
		
		//Check Why these aren't showing up
		assertTrue("Asserts that the close button is present on the toolbar",
				student.verifyElementsVisibility(student.getInitialVisibleElements()));
		bookModal = student.clickBookByTitle("10 Little Kittens");
		user.customWait().until(ExpectedConditions.elementToBeClickable(bookModal.playBookButton));
		reader = bookModal.openBook();
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
	}
	
	@Test
	public void testContentsButton(){
		reader.clickContentsButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(reader.contentModal));
		
		assertTrue("Asserts that the Contents modal appears after the content button is clicked",
				reader.isElementPresent(reader.contentModal));
		
		reader.click(reader.contentsButton);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(reader.contentModal));
		
		assertTrue("Asserts that the Contents modal disappears after the content button is clicked again",
				!reader.isElementPresent(reader.contentModal));
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
	}
	
	@Test
	public void testGlossaryModalChangesPlayButton(){
		reader.click(reader.glossaryButton);
		user.customWait().until(ExpectedConditions.attributeContains(reader.playButton, "class", "resume"));
		
		assertTrue("Asserts that the play button switches to a return to button after the glossay button is clicked",
				reader.getPageID().equals("23_Layer_1"));
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
	}
	
	@Test
	public void testComeBackToPageAfterGlossary(){
		reader.startPageByPage();
		reader.click(reader.nextButton);
		user.customWait().until(ExpectedConditions.attributeToBe(reader.playButton, "class", "nav-btn"));
		
		String page = reader.getPageID();
		reader.click(reader.glossaryButton);
		user.customWait().until(ExpectedConditions.attributeContains(reader.playButton, "class", "resume"));
		
		assertTrue("Asserts that the User is brought to the Glossary page after the Glossary button is clicked",
				!reader.getPageID().equals(page) && reader.getPageID().equals("23_Layer_1"));
		
		reader.click(reader.playButton);
		user.customWait().until(ExpectedConditions.attributeToBe(reader.playButton, "class", "nav-btn"));
		
		assertTrue("Asserts that the user is brought back to the Cover page after the Glossary button is clicked again",
				reader.getPageID().equals(page));
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
	}
	
	@Test
	public void testAudioTurnsOnWhenPlayButtonisPressed(){
		reader.startPageByPage();
		user.customWait().until(ExpectedConditions.attributeToBe(reader.playButton, "class", "nav-btn paused"));
		reader.click(reader.playButton);
		
		reader.click(reader.audioButton);
		//reader.click(reader.wordButton);
		user.customWait().until(ExpectedConditions.attributeToBe(reader.audioButton, "class", "nav-btn pushtoggle"));
		
		assertTrue("Asserts that audio is playing when the play button is clicked",
				!reader.isAudioOn());
		
		reader.click(reader.playButton);
		user.customWait().until(ExpectedConditions.attributeToBe(reader.playButton, "class", "nav-btn"));
		
		assertTrue("Asserts that audio stops playing when the play button is clicked again",
				reader.isAudioOn());
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
	}
	
	@Test
	public void testContentBeginning(){
		reader.startPageByPage();
		reader.click(reader.pageButton);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(reader.pageModal));
		String page = reader.getPageID();
		//This wait is needed because of the reader being wonky
		reader.waitImplicitly(2);
		
		reader.clickPopUpPageByIndex(5);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(reader.backButton));
		
		assertTrue("Asserts that the reader is brought to the fifth page when the fifth page div on the page modal is clicked",
				!reader.getPageID().equals(page));
		
		reader.clickContentsButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(reader.contentModal));
		reader.click(reader.beginning);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(reader.backButton));
		
		reader.click(reader.playButton);
		user.customWait().until(ExpectedConditions.attributeToBe(reader.playButton, "class", "nav-btn paused"));
		
		assertTrue("Asserts that the reader is brought to the beginning page when the beginning button on the content modal is clicked",
				reader.getPageID().equals(page));	
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
	}
	
	@Test
	public void testContentCopyright(){
		reader.startPageByPage();
		reader.click(reader.pageButton);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(reader.pageModal));
		reader.waitImplicitly(1);
		reader.clickPopUpPageByIndex(5);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(reader.backButton));
		String page5 = reader.getPageID();
		//reader.click(reader.contentsButton);
		reader.clickContentsButton();
		
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(reader.contentModal));
		reader.click(reader.copyright);
		user.customWait().until(ExpectedConditions.attributeContains(reader.playButton, "class", "resume"));
		
		assertTrue("Asserts that the user is taken to the Copyright page after the copyright button is clicked",
				!reader.getPageID().equals(page5));
		
		reader.click(reader.playButton);
		user.customWait().until(ExpectedConditions.attributeToBe(reader.playButton, "class", "nav-btn"));
		
		assertTrue("Asserts that the user is taken back to the previous page when the previous page button is clicked",
				reader.getPageID().equals(page5));
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
	}
	
	@Test
	public void testContentGlossaryAndPlayButton(){
		reader.startPageByPage();
		//See why this doesn't work
		reader.click(reader.pageButton);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(reader.pageModal));
		reader.waitImplicitly(1);
		reader.clickPopUpPageByIndex(5);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(reader.backButton));
		String page5 = reader.getPageID();
		//reader.click(reader.contentsButton);
		reader.clickContentsButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(reader.contentModal));
		reader.click(reader.glossary);
		user.customWait().until(ExpectedConditions.attributeContains(reader.playButton, "class", "resume"));
		
		assertTrue("Asserts that the user is brought to the Glossary page when the Glossary button is clicked",
				!reader.getPageID().equals(page5));
		
		reader.click(reader.playButton);
		user.customWait().until(ExpectedConditions.attributeToBe(reader.playButton, "class", "nav-btn"));
		
		assertTrue("Asserts that the user is brought back to the previous page when the play button is clicked",
				reader.getPageID().equals(page5));	
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
	}
	
	@Test
	public void testContentSheetMusic(){
		reader.startPageByPage();
		reader.click(reader.pageButton);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(reader.pageModal));
		reader.waitImplicitly(1);
		
		reader.clickPopUpPageByIndex(5);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(reader.backButton));
		String page5 = reader.getPageID();
		
		reader.clickContentsButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(reader.contentModal));
		reader.click(reader.sheetMusic);
		user.customWait().until(ExpectedConditions.attributeContains(reader.playButton, "class", "resume"));
		
		assertTrue("Asserts that the user is brought to the Music page when the Music button is clicked",
				!reader.getPageID().equals(page5));
		
		reader.click(reader.playButton);
		user.customWait().until(ExpectedConditions.attributeToBe(reader.playButton, "class", "nav-btn"));
		assertTrue("Asserts that the user is brought back to the previous page when the play button is clicked",
				reader.getPageID().equals(page5));
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
	}
	
	@Test
	public void testContentToLearnMore(){
		reader.startPageByPage();
		reader.click(reader.pageButton);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(reader.pageModal));
		reader.waitImplicitly(1);
		
		reader.clickPopUpPageByIndex(5);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(reader.backButton));
		String page5 = reader.getPageID();

		reader.clickContentsButton();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(reader.contentModal));
		reader.click(reader.toLearnMore);
		user.customWait().until(ExpectedConditions.attributeContains(reader.playButton, "class", "resume"));
		
		assertTrue("Asserts that the user is brought to the Learn More page after the Learn More button is clicked",
				!reader.getPageID().equals(page5));
		
		reader.click(reader.playButton);
		user.customWait().until(ExpectedConditions.attributeToBe(reader.playButton, "class", "nav-btn"));
		assertTrue("Asserts that the user is brought back to the previous page after the Play button is clicked again",
				reader.getPageID().equals(page5));
		reader.refresh();
		user.customWait().until(ExpectedConditions.elementToBeClickable(reader.readAllAtOnceButton));
	}
}
