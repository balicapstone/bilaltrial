package Tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import CITests.CIPages.CILoginPage;
import CITests.CIPages.StudentHomePage;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.User;
import UserClasses.UserInfo;

public class FilterTests {
	public static CILoginPage login;
	public static StudentHomePage student;
	public int allBooksTotal = 218;
	public static User user;
	
	@BeforeClass
	public static void executeBefore(){
		try{
			user = new User(UserInfo.VISUALSEARCH);
			login = new CILoginPage(user);
			user.customWait().until(ExpectedConditions.elementToBeClickable(login.getloginButton()));
			student = (StudentHomePage) login.login(user);
			user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		}
		catch(Exception e){
			System.out.println("Failure to set up Filter Tests");
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
		}
	};
	
	@Rule
	public Retry retry = new Retry(1);
	
	
	@Test 
	@Category(SmokeTests.class)
	public void testAllOtherFiltersCloseGradeRange(){
		student.click(student.filters.gradeRangeToggle);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.gradeSixPlusFilter));
		assertTrue("Asserts that the Grade Range filter is open",
				student.filters.isGradeRangeOpen() && !student.filters.isGuidedReadingOpen() && !student.filters.isATOSOpen() && !student.filters.isLexileRangeOpen());
	}
	
	@Test
	public void testAllOtherFiltersCloseGuidedReadingLevel(){		
		student.click(student.filters.guidedReadingToggle);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.XZFilter));
		assertTrue("Asserts that the Guided Reading filter is opened when the toggle is clicked",
				!student.filters.isGradeRangeOpen() && student.filters.isGuidedReadingOpen() && !student.filters.isATOSOpen() && !student.filters.isLexileRangeOpen());
	}
	
	@Test
	public void testAllOtherFiltersCloseATOS(){
		student.click(student.filters.aTOSToggle);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.SixPlusFilter));
		assertTrue("Asserts that the ATOS filter is opened when the ATOS filter toggle is clicked",
				!student.filters.isGradeRangeOpen() && !student.filters.isGuidedReadingOpen() && student.filters.isATOSOpen() && !student.filters.isLexileRangeOpen());
	}
	
	@Test
	public void testAllOtherFiltersCloseLexile(){
		student.click(student.filters.lexileRangeToggle);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.Lexile955To1155));
		assertTrue("Asserts that the Lexile Range filter is opened when the Lexile Range filter toggle is clicked",
				!student.filters.isGradeRangeOpen() && !student.filters.isGuidedReadingOpen() && !student.filters.isATOSOpen() && student.filters.isLexileRangeOpen());
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testFiltersCloseOnViewAllClick(){
		student.click(student.filters.gradeRangeToggle);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.gradeSixPlusFilter));
		assertTrue("Asserts the Grade Range filter is open when the Grade Range Toggle button is clicked",
				student.filters.isGradeRangeOpen());
		student.click(student.viewAllBooksButton);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(student.filters.preKFilter));
		assertTrue("Asserts the Grade Range filter is closed when the View All Books button is clicked",
				!student.filters.isGradeRangeOpen());
		
		student.click(student.filters.guidedReadingToggle);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.XZFilter));
		assertTrue("Asserts the Guided Reader filter is open when the Guided Reader Toggle button is clicked",
				student.filters.isGuidedReadingOpen());
		student.click(student.viewAllBooksButton);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.ADFilter));
		assertTrue("Asserts the Guided Reader filter is closed when the View All Books button is clicked",
				!student.filters.isGuidedReadingOpen());
		
		student.click(student.filters.aTOSToggle);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.SixPlusFilter));
		assertTrue("Asserts the ATOS filter is open when the ATOS Toggle button is clicked",
				student.filters.isATOSOpen());
		student.click(student.viewAllBooksButton);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.OneOrLessFilter));
		assertTrue("Asserts the ATOS filter is closed when the View All Books button is clicked",
				!student.filters.isATOSOpen());
		
		student.click(student.filters.lexileRangeToggle);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.Lexile955To1155));
		assertTrue("Asserts the Lexile Range filter is open when the Lexile Range button is clicked",
				student.filters.isLexileRangeOpen());
		student.click(student.viewAllBooksButton);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.LexileLessThan450));
		assertTrue("Asserts the Lexile Range filter is closed when the View All Books button is clicked",
				!student.filters.isLexileRangeOpen());
	}
	
	@Test
	public void testFiltersEdgeConditionsGradeRangeAscending(){
		
		student.click(student.filters.gradeRangeToggle);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.gradeSixPlusFilter));
		student.click(student.filters.preKFilter);
		student.click(student.filters.kFilter);
		student.click(student.filters.gradeOneFilter);
		student.click(student.filters.gradeTwoFilter);
		student.click(student.filters.gradeThreeFilter);
		student.click(student.filters.gradeFourFilter);
		student.click(student.filters.gradeFiveFilter);
		int firstResults = student.getResults();

		student.click(student.filters.gradeSixPlusFilter);
		assertTrue("Asserts that the number of books present when all Grade Range filters changes when the last remaining filter is selected",
				firstResults < student.getResults());
		
		student.click(student.filters.preKFilter);
		student.click(student.filters.kFilter);
		student.click(student.filters.gradeOneFilter);
		student.click(student.filters.gradeTwoFilter);
		student.click(student.filters.gradeThreeFilter);
		student.click(student.filters.gradeFourFilter);
		student.click(student.filters.gradeFiveFilter);
		student.click(student.filters.gradeSixPlusFilter);
	}
	
	@Test
	public void testGradeRangePreK(){
		if(!student.filters.isGradeRangeOpen()){
			student.click(student.filters.gradeRangeToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.gradeSixPlusFilter));
		}
		
		student.click(student.filters.preKFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.preKFilter, "class", "active"));
		assertTrue("Asserts that the Pre K Grade Range filter is getting the correct amount of results",
				(student.getResults() == 109) && student.isBookPresentByTitle("10 Little Kittens"));
		student.click(student.filters.preKFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.preKFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the Pre K Grade Range filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testGradeRangeK(){
		if(!student.filters.isGradeRangeOpen()){
			student.click(student.filters.gradeRangeToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.gradeSixPlusFilter));
		}
		
		student.click(student.filters.kFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.kFilter, "class", "active"));
		assertTrue("Asserts that the K Grade Range filter is getting the correct amount of results",
				(student.getResults() == 116) && student.isBookPresentByTitle("A Backpack Full of Verbs"));
		student.click(student.filters.kFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.kFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the K Grade Range filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testGradeRangeOne(){
		if(!student.filters.isGradeRangeOpen()){
			student.click(student.filters.gradeRangeToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.gradeSixPlusFilter));
		}
		
		student.click(student.filters.gradeOneFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.gradeOneFilter, "class", "active"));
		assertTrue("Asserts that the Grade One Grade Range filter is getting the correct amount of results",
				(student.getResults() == 128) && student.isBookPresentByTitle("A Germ's Journey"));
		student.click(student.filters.gradeOneFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.gradeOneFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the Grade One Grade Range filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testGradeRangeTwo(){
		if(!student.filters.isGradeRangeOpen()){
			student.click(student.filters.gradeRangeToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.gradeSixPlusFilter));
		}
		
		student.click(student.filters.gradeTwoFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.gradeTwoFilter, "class", "active"));
		assertTrue("Asserts that the Grade Two Grade Range filter is getting the correct amount of results",
				(student.getResults() == 124) && student.isBookPresentByTitle("A Raindrop's Journey"));
		student.click(student.filters.gradeTwoFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.gradeTwoFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the Grade Two Grade Range filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testGradeRangeThree(){
		if(!student.filters.isGradeRangeOpen()){
			student.click(student.filters.gradeRangeToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.gradeSixPlusFilter));
		}
		
		student.click(student.filters.gradeThreeFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.gradeThreeFilter, "class", "active"));
		assertTrue("Asserts that the Grade Three Grade Range filter is getting the correct amount of results",
				(student.getResults() == 74) && student.isBookPresentByTitle("A Girl's Guide to Soccer"));
		student.click(student.filters.gradeThreeFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.gradeThreeFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the Grade Three Grade Range filter is removed",
				student.getResults() == allBooksTotal);	
	}
	
	@Test
	public void testGradeRangeFour(){
		if(!student.filters.isGradeRangeOpen()){
			student.click(student.filters.gradeRangeToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.gradeSixPlusFilter));
		}
		
		student.click(student.filters.gradeFourFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.gradeFourFilter, "class", "active"));
		assertTrue("Asserts that the Grade Four Grade Range filter is getting the correct amount of results",
				(student.getResults() == 61) && student.isBookPresentByTitle("A Jar of Eyeballs"));
		student.click(student.filters.gradeFourFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.gradeFourFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the Grade Four Grade Range filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testGradeRangeFive(){
		if(!student.filters.isGradeRangeOpen()){
			student.click(student.filters.gradeRangeToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.gradeSixPlusFilter));
		}
		
		student.click(student.filters.gradeFiveFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.gradeFiveFilter, "class", "active"));
		assertTrue("Asserts that the Grade 5 Grade Range filter is getting the correct amount of results",
				(student.getResults() == 74) && student.isBookPresentByTitle("A Daredevil's Guide to Stunts"));
		student.click(student.filters.gradeFiveFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.gradeFiveFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the Grade Five Grade Range filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testGradeRangeSix(){
		if(!student.filters.isGradeRangeOpen()){
			student.click(student.filters.gradeRangeToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.gradeSixPlusFilter));
		}
		
		student.click(student.filters.gradeSixPlusFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.gradeSixPlusFilter, "class", "active"));
		assertTrue("Asserts that the Grade 6 Grade Range filter is getting the correct amount of results",
				(student.getResults() == 85) && student.isBookPresentByTitle("A Field Guide to Dragons, Trolls, and Other Dangerous Monsters"));
		student.click(student.filters.gradeSixPlusFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.gradeSixPlusFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the Grade Six Grade Range filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testGuidedReadingAD(){
		if(!student.filters.isGuidedReadingOpen()){
			student.click(student.filters.guidedReadingToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.XZFilter));
		}
		
		student.click(student.filters.ADFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.ADFilter, "class", "active"));
		assertTrue("Asserts that the AD Guided Reading Level filter is getting the correct amount of books",
				(student.getResults() == 2) && student.isBookPresentByTitle("Big and Small"));
		student.click(student.filters.ADFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.ADFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the AD Guided Reading Level filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testGuidedReadingEI(){
		if(!student.filters.isGuidedReadingOpen()){
			student.click(student.filters.guidedReadingToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.XZFilter));
		}
		
		student.click(student.filters.EIFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.EIFilter, "class", "active"));
		assertTrue("Asserts that the EI Guided Reading Level filter is getting the correct amount of books",
				(student.getResults() == 43) && student.isBookPresentByTitle("Adoptive Families"));
		student.click(student.filters.EIFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.EIFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the EI Guided Reading Level filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testGuidedReadingJN(){
		if(!student.filters.isGuidedReadingOpen()){
			student.click(student.filters.guidedReadingToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.XZFilter));
		}
		
		student.click(student.filters.JNFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.JNFilter, "class", "active"));
		assertTrue("Asserts that the JN Guided Reading Level filter is getting the correct amount of books",
				(student.getResults() == 83) && student.isBookPresentByTitle("A Short History of Thanksgiving"));
		student.click(student.filters.JNFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.JNFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the JN Guided Reading Level filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testGuidedReadingOQ(){
		if(!student.filters.isGuidedReadingOpen()){
			student.click(student.filters.guidedReadingToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.XZFilter));
		}
		
		student.click(student.filters.OQFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.OQFilter, "class", "active"));
		assertTrue("Asserts that the OQ Guided Reading Level filter is getting the correct amount of books",
				(student.getResults() == 23) && student.isBookPresentByTitle("Amazing Magic Tricks, Apprentice Level"));
		student.click(student.filters.OQFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.OQFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the OQ Guided Reading Level filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testGuidedReadingRT(){
		if(!student.filters.isGuidedReadingOpen()){
			student.click(student.filters.guidedReadingToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.XZFilter));
		}
		
		student.click(student.filters.RTFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.RTFilter, "class", "active"));
		assertTrue("Asserts that the RT Guided Reading Level filter is getting the correct amount of books",
				(student.getResults() == 18) && student.isBookPresentByTitle("Ancient Egypt: Beyond the Pyramids"));
		student.click(student.filters.RTFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.RTFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the RT Guided Reading Level filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testGuidedReadingUW(){
		if(!student.filters.isGuidedReadingOpen()){
			student.click(student.filters.guidedReadingToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.XZFilter));
		}
		
		student.click(student.filters.UWFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.UWFilter, "class", "active"));
		assertTrue("Asserts that the UW Guided Reading Level filter is getting the correct amount of books",
				(student.getResults() == 22) && student.isBookPresentByTitle("Apache Resistance: Causes and Effects of Geronimo's Campaign"));
		student.click(student.filters.UWFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.UWFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the UW Guided Reading Level filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testGuidedReadingXZ(){
		if(!student.filters.isGuidedReadingOpen()){
			student.click(student.filters.guidedReadingToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.XZFilter));
		}
		
		student.click(student.filters.XZFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.XZFilter, "class", "active"));
		assertTrue("Asserts that the XZ Guided Reading Level filter is getting the correct amount of books",
				(student.getResults() == 13) && student.isBookPresentByTitle("A World After an Asteroid Strike"));
		student.click(student.filters.XZFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.XZFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the XZ Guided Reading Level filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testATOSPointNineOrLess(){
		if(!student.filters.isATOSOpen()){
			student.click(student.filters.aTOSToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.SixPlusFilter));
		}
		
		student.click(student.filters.OneOrLessFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.OneOrLessFilter, "class", "active"));
		assertTrue("Asserts that the Nine or Less ATOS Point Level filter is getting the correct amount of books",
				(student.getResults() == 1) && student.isBookPresentByTitle("Bess and Tess"));
		student.click(student.filters.OneOrLessFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.OneOrLessFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the Nine or Less ATOS Point Level filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testATOSOnetoTwo(){
		if(!student.filters.isATOSOpen()){
			student.click(student.filters.aTOSToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.SixPlusFilter));
		}
		
		student.click(student.filters.OneTwoFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.OneTwoFilter, "class", "active"));
		assertTrue("Asserts that the One to Two ATOS Point Level filter is getting the correct amount of books",
				(student.getResults() == 24) && student.isBookPresentByTitle("BIG Bugs"));
		student.click(student.filters.OneTwoFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.OneTwoFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the One to Two ATOS Point Level filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testATOSTwoThree(){
		if(!student.filters.isATOSOpen()){
			student.click(student.filters.aTOSToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.SixPlusFilter));
		}
		
		student.click(student.filters.TwoThreeFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.TwoThreeFilter, "class", "active"));
		assertTrue("Asserts that the Two to Three ATOS Point Level filter is getting the correct amount of books",
				(student.getResults() == 65) && student.isBookPresentByTitle("Aaron Rodgers"));
		student.click(student.filters.TwoThreeFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.TwoThreeFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the Two to Three ATOS Point Level filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testATOSThreeFour(){
		if(!student.filters.isATOSOpen()){
			student.click(student.filters.aTOSToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.SixPlusFilter));
		}
		
		student.click(student.filters.ThreeFourFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.ThreeFourFilter, "class", "active"));
		assertTrue("Asserts that the Three to Four ATOS Point Level filter is getting the correct amount of books",
				(student.getResults() == 24) && student.isBookPresentByTitle("A Jar of Eyeballs"));
		student.click(student.filters.ThreeFourFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.ThreeFourFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the Three to Four ATOS Point Level filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testATOSFourFive(){
		if(!student.filters.isATOSOpen()){
			student.click(student.filters.aTOSToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.SixPlusFilter));
		}
		
		student.click(student.filters.FourFiveFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.FourFiveFilter, "class", "active"));
		assertTrue("Asserts that the Four to Five ATOS Point Level filter is getting the correct amount of books",
				(student.getResults() == 25) && student.isBookPresentByTitle("Amphibians: Water-to-Land Animals"));
		student.click(student.filters.FourFiveFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.FourFiveFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the Four to Five ATOS Point Level filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testATOSFiveSix(){
		if(!student.filters.isATOSOpen()){
			student.click(student.filters.aTOSToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.SixPlusFilter));
		}
		
		student.click(student.filters.FiveSixFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.FiveSixFilter, "class", "active"));
		assertTrue("Asserts that the Five to Six ATOS Point Level filter is getting the correct amount of books",
				(student.getResults() == 31) && student.isBookPresentByTitle("A Daredevil's Guide to Stunts"));
		student.click(student.filters.FiveSixFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.FiveSixFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the Five to Six ATOS Point Level filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testATOSSixPlus(){
		if(!student.filters.isATOSOpen()){
			student.click(student.filters.aTOSToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.SixPlusFilter));
		}
	
		student.click(student.filters.SixPlusFilter);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.SixPlusFilter, "class", "active"));
		assertTrue("Asserts that the Six Plus ATOS Point Level filter is getting the correct amount of books",
				(student.getResults() == 10) && student.isBookPresentByTitle("Anatomy of a Pandemic"));
		student.click(student.filters.SixPlusFilter);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.SixPlusFilter, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the Six Plus ATOS Point Level filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testLexileLessThan450(){
		if(!student.filters.isLexileRangeOpen()){
			student.click(student.filters.lexileRangeToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.Lexile955To1155));
		}
	
		student.click(student.filters.LexileLessThan450);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.LexileLessThan450, "class", "active"));
		assertTrue("Asserts that the Less than 450 Lexile Range filter is getting the correct amount of books",
				(student.getResults() == 38) && student.isBookPresentByTitle("All Kinds of Gardens"));
		student.click(student.filters.LexileLessThan450);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.LexileLessThan450, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the Less than 450 Lexile Range filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testLexile450to790(){
		if(!student.filters.isLexileRangeOpen()){
			student.click(student.filters.lexileRangeToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.Lexile955To1155));
		}
	
		student.click(student.filters.Lexile450To790);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.Lexile450To790, "class", "active"));
		assertTrue("Asserts that the 450 to 790 Lexile Range filter is getting the correct amount of books",
				(student.getResults() == 133) && student.isBookPresentByTitle("A Balanced Diet"));
		student.click(student.filters.Lexile450To790);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.Lexile450To790, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the 450 to 790 Lexile Range filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testLexile770to980(){
		if(!student.filters.isLexileRangeOpen()){
			student.click(student.filters.lexileRangeToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.Lexile955To1155));
		}
	
		student.click(student.filters.Lexile770To980);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.Lexile770To980, "class", "active"));
		assertTrue("Asserts that the 770 to 980 Lexile Range filter is getting the correct amount of books",
				(student.getResults() == 47) && student.isBookPresentByTitle("Acting Responsibly"));
		student.click(student.filters.Lexile770To980);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.Lexile770To980, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the 770 to 980 Lexile Range filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	public void testLexile955to1155(){
		if(!student.filters.isLexileRangeOpen()){
			student.click(student.filters.lexileRangeToggle);
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.Lexile955To1155));
		}
	
		student.click(student.filters.Lexile955To1155);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.Lexile955To1155, "class", "active"));
		assertTrue("Asserts that the 955 to 1155 Lexile Range filter is getting the correct amount of books",
				(student.getResults() == 11) && student.isBookPresentByTitle("A World After Fossil Fuels"));
		student.click(student.filters.Lexile955To1155);
		user.customWait().until(ExpectedConditions.attributeToBe(student.filters.Lexile955To1155, "class", "ng-binding"));
		assertTrue("Asserts that the number of books changes when the 955 to 1155 Lexile Range filter is removed",
				student.getResults() == allBooksTotal);
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testFiltersEdgeConditionsGradeRangeDescending(){
		if(!student.filters.isGradeRangeOpen()){
			student.click(student.filters.gradeRangeToggle);
		}
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.gradeSixPlusFilter));
		student.click(student.filters.gradeSixPlusFilter);
		student.click(student.filters.gradeFiveFilter);
		student.click(student.filters.gradeFourFilter);
		student.click(student.filters.gradeThreeFilter);
		student.click(student.filters.gradeTwoFilter);
		student.click(student.filters.gradeOneFilter);
		student.click(student.filters.kFilter);
		int firstResults = student.getResults();

		student.click(student.filters.preKFilter);
		assertTrue("Asserts that selecting the first Grade Range toggle filter affects the book count when all filters are previously enabled",
				student.getResults() > firstResults);
		
		student.click(student.filters.gradeSixPlusFilter);
		student.click(student.filters.gradeFiveFilter);
		student.click(student.filters.gradeFourFilter);
		student.click(student.filters.gradeThreeFilter);
		student.click(student.filters.gradeTwoFilter);
		student.click(student.filters.gradeOneFilter);
		student.click(student.filters.kFilter);
		student.click(student.filters.preKFilter);
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testFiltersEdgeConditionsGuidedReadingDescending(){
		if(!student.filters.isGuidedReadingOpen()){
			student.click(student.filters.guidedReadingToggle);
		}
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.UWFilter));
		student.click(student.filters.ADFilter);
		student.click(student.filters.EIFilter);
		student.click(student.filters.JNFilter);
		student.click(student.filters.OQFilter);
		student.click(student.filters.RTFilter);
		student.click(student.filters.UWFilter);
		int firstResults = student.getResults();

		student.click(student.filters.XZFilter);
		assertTrue("Asserts that selecting the last Guided Reading toggle filter affects the book count when all filters are previously enabled",
				student.getResults() > firstResults);
		
		student.click(student.filters.ADFilter);
		student.click(student.filters.EIFilter);
		student.click(student.filters.JNFilter);
		student.click(student.filters.OQFilter);
		student.click(student.filters.RTFilter);
		student.click(student.filters.UWFilter);
		student.click(student.filters.XZFilter);
	}
	
	@Test
	public void testFiltersEdgeConditionsGuidedReadingAscending(){
		if(!student.filters.isGuidedReadingOpen()){
			student.click(student.filters.guidedReadingToggle);
		}
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.XZFilter));
		student.click(student.filters.XZFilter);
		student.click(student.filters.UWFilter);
		student.click(student.filters.RTFilter);
		student.click(student.filters.OQFilter);
		student.click(student.filters.JNFilter);
		student.click(student.filters.EIFilter);
		int firstResults = student.getResults();
		student.click(student.filters.ADFilter);	
		assertTrue("Asserts that selecting the first Guided Reading toggle filter affects the book count when all filters are previously enabled",
				student.getResults() > firstResults);	
		
		student.click(student.filters.XZFilter);
		student.click(student.filters.UWFilter);
		student.click(student.filters.RTFilter);
		student.click(student.filters.OQFilter);
		student.click(student.filters.JNFilter);
		student.click(student.filters.EIFilter);
		student.click(student.filters.ADFilter);
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testFiltersEdgeConditionsARRangeAscending(){
		if(!student.filters.isATOSOpen()){
			student.click(student.filters.aTOSToggle);
		}
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.FiveSixFilter));
		student.click(student.filters.OneOrLessFilter);
		student.click(student.filters.OneTwoFilter);
		student.click(student.filters.TwoThreeFilter);
		student.click(student.filters.ThreeFourFilter);
		student.click(student.filters.FourFiveFilter);
		student.click(student.filters.FiveSixFilter);
		int firstResults = student.getResults();
		student.click(student.filters.SixPlusFilter);	
		assertTrue("Asserts that selecting the first AR Level toggle filter affects the book count when all filters are previously enabled",
				student.getResults() > firstResults);
		
		student.click(student.filters.OneOrLessFilter);
		student.click(student.filters.OneTwoFilter);
		student.click(student.filters.TwoThreeFilter);
		student.click(student.filters.ThreeFourFilter);
		student.click(student.filters.FourFiveFilter);
		student.click(student.filters.FiveSixFilter);
		student.click(student.filters.SixPlusFilter);
	}
	
	@Test
	public void testFiltersEdgeConditionsARRangeDescending(){
		if(!student.filters.isATOSOpen()){
			student.click(student.filters.aTOSToggle);
		}
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.SixPlusFilter));
		student.click(student.filters.SixPlusFilter);
		student.click(student.filters.FiveSixFilter);
		student.click(student.filters.FourFiveFilter);
		student.click(student.filters.ThreeFourFilter);
		student.click(student.filters.TwoThreeFilter);
		student.click(student.filters.OneTwoFilter);

		int firstResults = student.getResults();
		student.click(student.filters.OneOrLessFilter);
		assertTrue("Asserts that selecting the last AR Level toggle filter affects the book count when all filters are previously enabled",
				student.getResults() > firstResults);
		
		student.click(student.filters.SixPlusFilter);
		student.click(student.filters.FiveSixFilter);
		student.click(student.filters.FourFiveFilter);
		student.click(student.filters.ThreeFourFilter);
		student.click(student.filters.TwoThreeFilter);
		student.click(student.filters.OneTwoFilter);
		student.click(student.filters.OneOrLessFilter);
	}
	
	@Test
	public void testFiltersEdgeConditionsLexileRangeAscending(){
		if(!student.filters.isLexileRangeOpen()){
			student.click(student.filters.lexileRangeToggle);
		}	
		
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.Lexile955To1155));
		student.click(student.filters.LexileLessThan450);
		student.click(student.filters.Lexile450To790);
		student.click(student.filters.Lexile770To980);
		int firstResults = student.getResults();

		student.click(student.filters.Lexile955To1155);
		assertTrue("Asserts that selecting the last Lexile Range toggle filter affects the book count when all filters are previously enabled",
				student.getResults() > firstResults);

		
		student.click(student.filters.LexileLessThan450);
		student.click(student.filters.Lexile450To790);
		student.click(student.filters.Lexile770To980);
		student.click(student.filters.Lexile955To1155);
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testFiltersEdgeConditionsLexileRangeDescending(){
		if(!student.filters.isLexileRangeOpen()){
			student.click(student.filters.lexileRangeToggle);
		}

		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(student.filters.Lexile955To1155));
		student.click(student.filters.Lexile955To1155);
		student.click(student.filters.Lexile770To980);
		student.click(student.filters.Lexile450To790);
		int firstResults = student.getResults();

		student.click(student.filters.LexileLessThan450);
		assertTrue("Asserts that selecting the first Lexile Range toggle filter affects the book count when all filters are previously enabled",
				student.getResults() > firstResults);
		
		student.click(student.filters.Lexile955To1155);
		student.click(student.filters.Lexile770To980);
		student.click(student.filters.Lexile450To790);
		student.click(student.filters.LexileLessThan450);
	}	
	
	@Test
	public void testEnglishCheckbox(){
		student.click(student.filters.englishCheckbox);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.englishCheckbox, "class", "active"));
		assertTrue("Asserts that the correct number of books appear when the English checkbox is clicked",
				student.getResults() == 217);
		student.click(student.filters.englishCheckbox);
	}
	
	@Test
	public void testSpanishCheckbox(){
		student.click(student.filters.spanishCheckbox);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.spanishCheckbox, "class", "active"));
		assertTrue("Asserts that the correct number of books appear when the Spanish checkbox is clicked",
				student.getResults() == 1);
		student.click(student.filters.spanishCheckbox);
	}
	
	@Test
	public void testBothCheckboxes(){
		student.click(student.filters.englishCheckbox);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.englishCheckbox, "class", "active"));
		student.click(student.filters.spanishCheckbox);
		user.customWait().until(ExpectedConditions.attributeContains(student.filters.spanishCheckbox, "class", "active"));
		assertTrue("Asserts that the the total number of books appear when the English and Spanish checkboxes are clicked",
				student.getResults() == allBooksTotal);
		student.click(student.filters.spanishCheckbox);
		student.click(student.filters.englishCheckbox);
	}
}
