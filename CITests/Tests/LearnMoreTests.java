package Tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.openqa.selenium.support.ui.ExpectedConditions;

import BookTools.BookReader;
import CITests.CIPages.CILoginPage;
import CITests.CIPages.StudentHomePage;
import LearnMorePages.CompatibilityPage;
import LearnMorePages.EducatorsPage;
import LearnMorePages.LearnMoreAboutPage;
import LearnMorePages.LearnMoreDemoPage;
import LearnMorePages.LearnMoreOrderPage;
import LearnMorePages.PrivacyPolicyPage;
import LearnMorePages.TermsOfUsePage;
import SharedClasses.ThirdPartyPage;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.User;
import UserClasses.UserInfo;

public class LearnMoreTests {
	public static CILoginPage login;
	public static StudentHomePage student;
	public static LearnMoreAboutPage about;
	public static String testbaseURL = "www.mycapstonelibrary.com/about/";
	public static User user;
	
	@BeforeClass
	public static void executeBefore(){
		try{
			user = new User(UserInfo.GARAGESTUDENT);
			login = new CILoginPage(user);
			about = login.clickLearnMoreButton();
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(about.capstoneLogoBottom));
		}catch(Exception e){
			System.out.println("Failure to set up LearnMore Tests");
			System.out.println(e);
		}
	}
	
	@Rule
	public Timeout globalTimeout = new Timeout(300000); 
	
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
			about = login.clickLearnMoreButton();
			user.customWait().until(ExpectedConditions.visibilityOfElementLocated(about.capstoneLogoBottom));
		}
	};
	
	@Rule
	public Retry retry = new Retry(3);
	
	@Test
	@Category(TrueUserTests.TrueUser.SmokeTests.class)
	public void testRequirementsPage(){
		about.clickRequirementsLink();
		CompatibilityPage compatibility = about.requirements.clickCompatibilityLink();
		compatibility.waitImplicitly(1);
		assertTrue("Asserts that the Compatibility link on the Requirements page redirects to the Compatability page",
				compatibility.verifyElementsVisibility(compatibility.getInitialVisibleElements()));
	}
	
	@Test
	public void testRLTechnology() throws InterruptedException{
		about.clickResearchLibraryLink();
	
		assertTrue("Asserts that the Literacy and Tech link redirects to the correct page",
				about.verifyLinkByElement(about.research.literacyAndTechLink)
				&& about.verifyLinkContainsURLByElement(about.research.literacyAndTechLink, "mycapstonelibrary.com/pdf/Interactive_White_Papers.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLMultimodalLearning() throws InterruptedException{
		about.waitForElement(about.researchLibraryLink);
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the Multimodal Learning Link redirects to the correct page",
				about.verifyLinkByElement(about.research.multimodalLearningLink)
				&& about.verifyLinkContainsURLByElement(about.research.multimodalLearningLink, "http://www.cisco.com/web/strategy/docs/education/Multimodal-Learning-Through-Media.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLReviewOfLiterature() throws InterruptedException{
		about.clickResearchLibraryLink();
		assertTrue("Asserts that the Review of Literature link redirects to the correct page",
				about.verifyLinkByElement(about.research.reviewOfLiterature)
				&& about.verifyLinkContainsURLByElement(about.research.reviewOfLiterature, "mycapstonelibrary.com/pdf/ResearchOverview.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLTeachingSuggestions() throws InterruptedException{
		about.clickResearchLibraryLink();
		
		assertTrue("Asserts that the Teaching Suggestions link redirects to the correct page",
				about.verifyLinkByElement(about.research.teachingSuggestionsLink)
				&& about.verifyLinkContainsURLByElement(about.research.teachingSuggestionsLink, "mycapstonelibrary.com/pdf/Teachers_Guide_to_Interactive.pdf"));
		about.clickOverViewLink();
	}	
	
	@Test
	public void testRL12WaysToUseCI() throws InterruptedException{
		about.clickResearchLibraryLink();
		
		assertTrue("Asserts that the Twelve Ways to Use CI link redirects to the correct page",
				about.verifyLinkByElement(about.research.twelveWaysToUseLink)
				&& about.verifyLinkContainsURLByElement(about.research.twelveWaysToUseLink, "mycapstonelibrary.com/pdf/Teacher_Guide_Hi-Design.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLIntroductiontoCILessonPlan() throws InterruptedException{
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the Introduction to CI Lesson Plan link redirects to the correct page",
				about.verifyLinkByElement(about.research.capstoneLessonPlanLink)
				&& about.verifyLinkContainsURLByElement(about.research.capstoneLessonPlanLink, "mycapstonelibrary.com/pdf/IntroductionToCapstoneInteractiveLibrary.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLReadersTheaterLessonPlan() throws InterruptedException{
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the Reader Theater Lesson Plan link redirects to the correct page",
				about.verifyLinkByElement(about.research.readerTheatreLink)
				&& about.verifyLinkContainsURLByElement(about.research.readerTheatreLink, "mycapstonelibrary.com/pdf/ReadersTheatre-LessonPlan.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLEarlyReadersLessonPlan() throws InterruptedException{
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the Early Readers Lesson Plan link redirects to the correct page",
				about.verifyLinkByElement(about.research.earlyReaderLink)
				&& about.verifyLinkContainsURLByElement(about.research.earlyReaderLink, "mycapstonelibrary.com/pdf/EarlyReaders-LessonPlan.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLTimelineLessonPlan() throws InterruptedException{
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the Timeline Lesson Plan link redirects to the correct page",
				about.verifyLinkByElement(about.research.timelineLessonPlan)
				&& about.verifyLinkContainsURLByElement(about.research.timelineLessonPlan, "mycapstonelibrary.com/pdf/Timeline-LessonPlan.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLStrugglingReaderLessonPlan() throws InterruptedException{
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the Struggling Reader Lesson Plan link redirects to the correct page",
				about.verifyLinkByElement(about.research.strugglingReaderLink)
				&& about.verifyLinkContainsURLByElement(about.research.strugglingReaderLink, "mycapstonelibrary.com/pdf/StrugglingReader-LessonPlan.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLBlastToThePast() throws InterruptedException{
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the Blast to the Past link redirects to the correct page",
				about.verifyLinkByElement(about.research.blastToThePastLink)
				&& about.verifyLinkContainsURLByElement(about.research.blastToThePastLink, "mycapstonelibrary.com/pdf/BlastToThePast_kv.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLDragonKiteContest() throws InterruptedException{
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the Dragon Kite link redirects to the correct page",
				about.verifyLinkByElement(about.research.dragonKiteContestLink)
				&& about.verifyLinkContainsURLByElement(about.research.dragonKiteContestLink, "mycapstonelibrary.com/pdf/DragonKiteContest_kv.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLInvadersFromtheGreatGooGalaxy() throws InterruptedException{
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the Invaders From Great Goo link redirects to the correct page",
				about.verifyLinkByElement(about.research.invaderFromGreatGooLink)
				&& about.verifyLinkContainsURLByElement(about.research.invaderFromGreatGooLink, "mycapstonelibrary.com/pdf/GreatGooGalaxy_kv.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLNightOfTheHighschoolZombies() throws InterruptedException{
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the Night of the Highschool Zombies link redirects to the correct page",
				about.verifyLinkByElement(about.research.highschoolZombies)
				&& about.verifyLinkContainsURLByElement(about.research.highschoolZombies, "mycapstonelibrary.com/pdf/NightofZombies_kv.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLTigerMothInsectNinja() throws InterruptedException{
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the Tiger Mother Insect Ninja link redirects to the correct page",
				about.verifyLinkByElement(about.research.tigerMothLink)
				&& about.verifyLinkContainsURLByElement(about.research.tigerMothLink, "mycapstonelibrary.com/pdf/TigerMothInsectNinja_kv.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLAJourneyIntoAdaptation() throws InterruptedException{
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the Journey into Adaptation link redirects to the correct page",
				about.verifyLinkByElement(about.research.aJourneyIntoAdaptationLink)
				&& about.verifyLinkContainsURLByElement(about.research.aJourneyIntoAdaptationLink, "mycapstonelibrary.com/pdf/GLAdaptation.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLExploringEcosystems() throws InterruptedException{
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the Exploring Ecosystems link redirects to the correct page",
				about.verifyLinkByElement(about.research.exploringEcosystemsLink)
				&& about.verifyLinkContainsURLByElement(about.research.exploringEcosystemsLink, "mycapstonelibrary.com/pdf/GLEcosystems.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLTheShockingWorldOfElectricity() throws InterruptedException{
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the Shocking World of Electricity link redirects to the correct page",
				about.verifyLinkByElement(about.research.shockingWorldLink)
				&& about.verifyLinkContainsURLByElement(about.research.shockingWorldLink, "mycapstonelibrary.com/pdf/GLElectricity.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLTheWorldOfFoodChains() throws InterruptedException{
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the World of Food Chains link redirects to the correct page",
				about.verifyLinkByElement(about.research.worldOfFoodChainsLink)
				&& about.verifyLinkContainsURLByElement(about.research.worldOfFoodChainsLink, "mycapstonelibrary.com/pdf/GLFoodChains.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLACrashCourseInForcesAndMotion() throws InterruptedException{
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the Crash Course in Forces and Motion link redirects to the correct page",
				about.verifyLinkByElement(about.research.crashCourseLink)
				&& about.verifyLinkContainsURLByElement(about.research.crashCourseLink, "mycapstonelibrary.com/pdf/GLForceMotion.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLUnderstandingPhotosynthesis() throws InterruptedException{
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the Understanding Photosynthesis link redirects to the correct page",
				about.verifyLinkByElement(about.research.understandingPhotosynthesisLink)
				&& about.verifyLinkContainsURLByElement(about.research.understandingPhotosynthesisLink, "mycapstonelibrary.com/pdf/GLPhotosynthesis.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLLessonsInScienceSafety() throws InterruptedException{
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the Lessons in Science Safety link redirects to the correct page",
				about.verifyLinkByElement(about.research.lessonsInScienceLink)
				&& about.verifyLinkContainsURLByElement(about.research.lessonsInScienceLink, "mycapstonelibrary.com/pdf/GLScienceSafety.pdf"));
		about.clickOverViewLink();
	}
	
	@Test
	public void testRLAdventuresInSound() throws InterruptedException{
		about.clickResearchLibraryLink();

		assertTrue("Asserts that the Adventure in Sound link redirects to the correct page",
				about.verifyLinkByElement(about.research.adventuresInSoundLink)
				&& about.verifyLinkContainsURLByElement(about.research.adventuresInSoundLink, "mycapstonelibrary.com/pdf/GLSound.pdf"));	
		about.clickOverViewLink();
	}
	
	@Test
	public void testFAQOne(){
		about.clickFAQLink();
		
		about.click(about.faq.QuestionOne);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(about.faq.AnswerOne));
		assertTrue("Asserts that the Question One Answer modal opens when the question is clicked",
				about.isElementPresent(about.faq.AnswerOne));
		
		about.clickOverViewLink();
	}
	
	@Test
	public void testFAQTwo(){
		about.clickFAQLink();
		
		about.click(about.faq.QuestionTwo);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(about.faq.AnswerTwo));
		assertTrue("Asserts that the Question Two Answer modal opens when the question is clicked",
				about.isElementPresent(about.faq.AnswerTwo));
		
		about.clickOverViewLink();
	}
	
	@Test
	public void testFAQThree(){
		about.clickFAQLink();
		
		about.click(about.faq.QuestionThree);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(about.faq.AnswerThree));
		assertTrue("Asserts that the Question Three Answer modal opens when the question is clicked",
				about.isElementPresent(about.faq.AnswerThree));
		
		about.clickOverViewLink();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testFAQFour(){
		about.clickFAQLink();
		
		about.click(about.faq.QuestionFour);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(about.faq.AnswerFour));
		assertTrue("Asserts that the Question Four Answer modal opens when the question is clicked",
				about.isElementPresent(about.faq.AnswerFour));
		
		about.clickOverViewLink();
	}
	
	@Test
	public void testFAQFive(){
		about.clickFAQLink();
		
		about.click(about.faq.QuestionFive);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(about.faq.AnswerFive));
		assertTrue("Asserts that the Question Five Answer modal opens when the question is clicked",
				about.isElementPresent(about.faq.AnswerFive));
		
		about.clickOverViewLink();
	}
	
	@Test
	public void testFAQSix(){
		about.clickFAQLink();
		
		about.click(about.faq.QuestionSix);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(about.faq.AnswerSix));
		assertTrue("Asserts that the Question Six Answer modal opens when the question is clicked",
				about.isElementPresent(about.faq.AnswerSix));
		
		about.clickOverViewLink();
	}
	
	@Test
	public void testFAQSeven(){
		about.clickFAQLink();
		
		about.click(about.faq.QuestionSeven);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(about.faq.AnswerSeven));
		assertTrue("Asserts that the Question Seven Answer modal opens when the question is clicked",
				about.isElementPresent(about.faq.AnswerSeven));
	
		about.clickOverViewLink();
	}
	
	@Test
	public void testFAQEight(){
		about.clickFAQLink();
	
		about.click(about.faq.QuestionEight);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(about.faq.AnswerEight));
		assertTrue("Asserts that the Question Eight Answer modal opens when the question is clicked",
				about.isElementPresent(about.faq.AnswerEight));
		
		about.clickOverViewLink();
	}
	
	@Test
	public void testFAQNine(){
		about.clickFAQLink();
		
		about.click(about.faq.QuestionNine);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(about.faq.AnswerNine));
		assertTrue("Asserts that the Question Nine Answer modal opens when the question is clicked",
				about.isElementPresent(about.faq.AnswerNine));
	
		about.clickOverViewLink();
	}
	
	@Test
	public void testFAQTen(){
		about.clickFAQLink();
	
		about.click(about.faq.QuestionTen);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(about.faq.AnswerTen));
		assertTrue("Asserts that the Question Ten Answer modal opens when the question is clicked",
				about.isElementPresent(about.faq.AnswerTen));
		
		about.clickOverViewLink();
	}
	
	@Test
	public void testFAQEleven(){
		about.clickFAQLink();
		
		about.click(about.faq.QuestionEleven);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(about.faq.AnswerEleven));
		assertTrue("Asserts that the Question Eleven Answer modal opens when the question is clicked",
				about.isElementPresent(about.faq.AnswerEleven));
		
		about.clickOverViewLink();
	}
	
	@Test
	public void testFAQTwelve(){
		about.clickFAQLink();
		
		about.click(about.faq.QuestionTwelve);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(about.faq.AnswerTwelve));
		assertTrue("Asserts that the Question Twelve Answer modal opens when the question is clicked",
				about.isElementPresent(about.faq.AnswerTwelve));
		
		about.clickOverViewLink();
	}
	
	@Test
	public void testFAQThirteen(){
		about.clickFAQLink();
		
		about.click(about.faq.QuestionThirteen);
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(about.faq.AnswerThirteen));
		assertTrue("Asserts that the Question Thirteen Answer modal opens when the question is clicked",
				about.isElementPresent(about.faq.AnswerThirteen));
		
		about.clickOverViewLink();
	}
	
	@Test
	public void testFAQFourteen() throws InterruptedException{
		about.clickFAQLink();

		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(about.faq.AnswerFourteen));
		assertTrue("Asserts that the Question Fourteen Answer modal opens when the question is clicked",
				about.verifyLinkByElement(about.faq.AnswerFourteen)
				&& about.verifyLinkContainsURLByElement(about.faq.AnswerFourteen, "mycapstonelibrary.com/pdf/CIL22_Release_Notes.pdf"));
		
		about.clickOverViewLink();
	}
	
	@Test
	public void testHelpDocumentLetterHome() throws InterruptedException{
		about.clickHelpDocumentsLink();
		
		assertTrue("Asserts that the Help link redirects to the correct page",
				about.verifyLinkByElement(about.help.letterHomeLink)
				&& about.verifyLinkContainsURLByElement(about.help.letterHomeLink, "mycapstonelibrary.com/pdf/ci_letter_home_2016.pdf"));
				
		about.clickOverViewLink();
	}
	
	@Test
	public void testHelpDocumentLetterHomeSpanish() throws InterruptedException{
		about.clickHelpDocumentsLink();
		
		assertTrue("Asserts that the Help link redirects to the correct page",
				about.verifyLinkByElement(about.help.letterHomeSpanishLink)
				&& about.verifyLinkContainsURLByElement(about.help.letterHomeSpanishLink, "mycapstonelibrary.com/pdf/ci_letter_home_spanish_2016.pdf"));
				
		about.clickOverViewLink();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testHelpDocumentSearchUpdates() throws InterruptedException{
		about.clickHelpDocumentsLink();
	
		assertTrue("Asserts that the Help link redirects to the correct page",
				about.verifyLinkByElement(about.help.searchUpdateLink)
				&& about.verifyLinkContainsURLByElement(about.help.searchUpdateLink, "mycapstonelibrary.com/pdf/CIL_SearchandDiscoverability.pdf"));
				
		about.clickOverViewLink();
	}
	
	@Test
	public void testOriginalLoginDocument() throws InterruptedException{
		about.clickHelpDocumentsLink();

		assertTrue("Asserts that the Help link redirects to the correct page",
				about.verifyLinkByElement(about.help.originalLoginLink)
				&& about.verifyLinkContainsURLByElement(about.help.originalLoginLink, "mycapstonelibrary.com/pdf/capstoneconnected-studentloginreminder-originallogin.pdf"));
				
		about.clickOverViewLink();
	}
	
	@Test
	public void testCapstoneMasterLogin() throws InterruptedException{
		about.clickHelpDocumentsLink();
		
		assertTrue("Asserts that the Help link redirects to the correct page",
				about.verifyLinkByElement(about.help.masterAccountLoginLink)
				&& about.verifyLinkContainsURLByElement(about.help.masterAccountLoginLink, "mycapstonelibrary.com/pdf/capstoneconnected-studentloginreminder-capstonemasterlogin.pdf"));
				
		about.clickOverViewLink();
	}
	
	@Test
	public void testGoogleLoginLink() throws InterruptedException{
		about.clickHelpDocumentsLink();
		
		assertTrue("Asserts that the Help link redirects to the correct page",
				about.verifyLinkByElement(about.help.googleLoginLink)
				&& about.verifyLinkContainsURLByElement(about.help.googleLoginLink, "mycapstonelibrary.com/pdf/capstoneconnected-studentloginreminder-googleclassroom.pdf"));
				
		about.clickOverViewLink();
	}
	
	@Test
	public void testSalesRepresentativeLink() throws InterruptedException{	
		assertTrue("Asserts that the Help link redirects to the correct page",
				about.verifyLinkByElement(about.salesRepLink)
				&& about.verifyLinkContainsURLByElement(about.salesRepLink, "http://www.capstonepub.com/aspx/salesreplocator.aspx"));
		
		about.clickOverViewLink();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testContactUsLink() throws InterruptedException{
		assertTrue("Asserts that clicking the Contact Us Link redirects to the correct page",
				about.verifyLinkByElement(about.contactUsTop)
				&& about.verifyLinkContainsURLByElement(about.contactUsTop, "capstonepub.com/library/service/contact-ci/"));	
	}
	
	@Test
	public void testInteractiveDemoBigAndSmall() throws InterruptedException{
		LearnMoreDemoPage demos = about.clickDemoButton();
		String docsWindow = demos.getDriver().getWindowHandle();
		
		BookReader book = demos.launchDemoOne();
		demos.SwitchToNewPage(docsWindow);
		user.customWait().until(ExpectedConditions.elementToBeClickable(book.readAllAtOnceButton));
		
		assertTrue("Asserts that the Interactive Demo for Big and Small opens correctly",
				book.verifyElementsVisibility(book.getInitialVisibleElements()));
		
		assertTrue("Asserts that the user can read through the Big and Small Interactive Demo",
				book.verifyUserCanPageThroughBook(14));
		
		book.click(book.closeReaderButton);
		book.closeCurrentWindow();
		demos.getDriver().switchTo().window(docsWindow);

		about = demos.clickAboutButton();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testInteractiveDemoKatieWoo() throws InterruptedException{
		LearnMoreDemoPage demos = about.clickDemoButton();
		String docsWindow = demos.getDriver().getWindowHandle();
		
		BookReader book = demos.launchDemoTwo();
		demos.SwitchToNewPage(docsWindow);
		user.customWait().until(ExpectedConditions.elementToBeClickable(book.readAllAtOnceButton));
		
		assertTrue("Asserts that the Interactive Demo for Katie Woo opens correctly",
				book.verifyElementsVisibility(book.getInitialVisibleElements()));
		
		assertTrue("Asserts that the user can read through the Katie Woo Interactive Demo",
				book.verifyUserCanPageThroughBook(12));
		
		book.click(book.closeReaderButton);
		book.closeCurrentWindow();
		demos.getDriver().switchTo().window(docsWindow);
		
		about = demos.clickAboutButton();
	}
	
	@Test
	public void testInteractiveDemoRedRidingHood() throws InterruptedException{
		LearnMoreDemoPage demos = about.clickDemoButton();
		String docsWindow = demos.getDriver().getWindowHandle();
		
		BookReader book = demos.launchDemoThree();
		demos.SwitchToNewPage(docsWindow);
		user.customWait().until(ExpectedConditions.elementToBeClickable(book.readAllAtOnceButton));
		
		assertTrue("Asserts that the Interactive Demo for Red Riding Hood opens correctly",
				book.verifyElementsVisibility(book.getInitialVisibleElements()));
		
		assertTrue("Asserts that the user can read through the Red Riding Hood Interactive Demo",
				book.verifyUserCanPageThroughBook(12));
		
		book.click(book.closeReaderButton);
		book.closeCurrentWindow();
		demos.getDriver().switchTo().window(docsWindow);
		
		about = demos.clickAboutButton();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testDemo30DayFreeTrial() throws InterruptedException{
		LearnMoreDemoPage demos = about.clickDemoButton();
		
		assertTrue("Asserts that the 30 Day Free Trial link redirects to the correct page",
				about.verifyLinkByElement(demos.freeTrialLink)
				&& about.verifyLinkContainsURLByElement(demos.freeTrialLink, "http://www.capstonepub.com/form/ciltrial_13"));	
		about = demos.clickAboutButton();
	}
	
	@Test
	public void testOrderCapstoneInteractiveeBooksLink() throws InterruptedException{
		LearnMoreOrderPage order = about.clickOrderButton();
			
		assertTrue("Asserts that the Order Capstone Interactive Ebooks link redirects to the correct page",
				about.verifyLinkByElement(order.capstoneInteractiveeBooksLink)
				&& about.verifyLinkContainsURLByElement(order.capstoneInteractiveeBooksLink, "http://www.capstonepub.com/search/cil"));	

		about = order.clickAboutButton();
	}
	
	@Test
	public void testOrderCapstonePubLink() throws InterruptedException{
		LearnMoreOrderPage order = about.clickOrderButton();
		
		assertTrue("Asserts that the Order Capstone Pub link redirects to the correct page",
				about.verifyLinkByElement(order.capstonePubLink)
				&& about.verifyLinkContainsURLByElement(order.capstonePubLink, "http://www.capstonepub.com/library/digital/capstone-interactive/"));	
	
		order.waitForElement(order.aboutButton);
		about = order.clickAboutButton();
	}
	
	@Test
	public void testOrderSalesRepresentative() throws InterruptedException{
		LearnMoreOrderPage order = about.clickOrderButton();
		
		assertTrue("Asserts that the Find a Sales Representative link redirects to the correct page",
				about.verifyLinkByElement(order.salesRepLink)
				&& about.verifyLinkContainsURLByElement(order.salesRepLink, "capstonepub.com/library/service/find-a-sales-representative/"));	

		order.waitForElement(order.aboutButton);
		about = order.clickAboutButton();
	}
	
	@Test
	public void testAboutCapstoneLink() throws InterruptedException{
		about.scrollToElement(about.getDriver().findElement(about.aboutCapstoneLink));
		about.click(about.aboutCapstoneLink);
		ThirdPartyPage tpp = new ThirdPartyPage(about.getDriver());
		
		assertTrue("Asserts that the About Capstone link redirects to the correct page",
				tpp.verifySameWindowNewPage("http://mycapstone.com/"));
		tpp.goBack();
	}
	
	@Test
	public void testPrivacyPolicyLink() throws InterruptedException{
		about.scrollToElement(about.getDriver().findElement(about.privacyPolicyLink));
		PrivacyPolicyPage privacy = about.clickPrivacyPolicyLink();
		assertTrue("Asserts that the Privacy Policy link redirects to the correct page",
				privacy.verifyElementsVisibility(privacy.getInitialVisibleElements()));
		
		privacy.goBack();
	}
	
	@Test
	public void testTermsofUseLink() throws InterruptedException{
		TermsOfUsePage terms = login.clickTermsOfUseLink();
		assertTrue("Asserts that the Terms of Use link redirects to the correct page",
				terms.verifyElementsVisibility(terms.getInitialVisibleElements()));
		
		terms.goBack();
	}
	
	@Test
	public void testLicenseAgreementLink() throws InterruptedException{
		assertTrue("Asserts that the License Agreement link redirects to the correct page",
				about.verifyLinkByElement(about.licenseAgreementLink)
				&& about.verifyLinkContainsURLByElement(about.licenseAgreementLink, "mycapstonelibrary.com/license-agreement.pdf"));

	}
	
	@Test
	public void testContactUsLinkBottom() throws InterruptedException{
		String docsWindow = about.getDriver().getWindowHandle();
		
		about.scrollToElement(about.getDriver().findElement(about.contactUsBottom));
		about.click(about.contactUsBottom);
		ThirdPartyPage tpp = new ThirdPartyPage(about.getDriver());
		
		assertTrue("Asserts that the Contact Us Bottom link redirects to the correct page",
				tpp.verifyNewWindow(docsWindow, "capstonepub.com/library/service/contact-ci/"));		
	}
	
	@Test
	public void testEducatorsLink() throws InterruptedException{
		EducatorsPage educators = about.clickEducatorsLink();
		assertTrue("Asserts that the Educators link redirects to the correct page",
				educators.verifyElementsVisibility(educators.getInitialVisibleElements()));
		
		educators.goBack();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testCapstoneLogoTopLeft(){
		login = about.clickCapstoneLogoTop();
		assertTrue("Asserts that the Capstone Logo in the top left of the page redirects to the Login Screen",
				login.verifyElementsVisibility(login.getInitialVisibleElements()));
		about = login.clickLearnMoreButton();
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testCapstoneLogoBottomRight() throws InterruptedException{
		String docsWindow = about.getDriver().getWindowHandle();
		
		about.scrollToElement(about.getDriver().findElement(about.capstoneLogoBottom));
		about.click(about.capstoneLogoBottom);
		ThirdPartyPage tpp = new ThirdPartyPage(about.getDriver());
		
		assertTrue("Asserts that the Capstone Logo in the bottom right corner of the page redirects to the Login Screen",
				tpp.verifyNewWindow(docsWindow, "http://mycapstone.com/"));
		tpp.goBack();
	}
	
	@Test
	public void testLoginTopRight(){
		login = about.clickLoginButton();
		
		assertTrue("Asserts that the Login button redirects to the Login screen",
				login.verifyElementsVisibility(login.getInitialVisibleElements()));
		about = login.clickLearnMoreButton();
	}
}
