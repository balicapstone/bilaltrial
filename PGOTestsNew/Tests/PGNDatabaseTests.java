package Tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import PGNModals.PGNVideoModal;
import PGNPages.PGNLoginPage;
import PGNStudentResources.PGNDatabasePage;
import PGNStudentResources.PGNExperimentsPage;
import PGNStudentResources.PGNIndiansDatabaseHome;
import PGNStudentResources.PGNScienceDatabaseHome;
import PGNStudentResources.PGNSocialStudiesDatabasePage;
import PGNStudentResources.PGNStatesDatabaseHome;
import PGNStudentResources.PGNStudentHomePage;
import PGNStudentResources.ScienceDatabaseHome;
import TrueUserTests.TrueUser.PGNSmokeTest;
import UserClasses.User;

public class PGNDatabaseTests {
	static PGNLoginPage login;
	static PGNStudentHomePage home;
	static User user;
	
	@BeforeClass
    public static void executeBefore(){
		user = new User(UserClasses.UserInfo.GARAGESTUDENT);
 		login = new PGNLoginPage(user);
 		home = (PGNStudentHomePage) login.login(user);
 		
 		home.waitImplicitly(10);
    	home.clickPebbleGoNextTab();
    	home.waitImplicitly(5);
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
    		
    		login = new PGNLoginPage(user);
    		home = (PGNStudentHomePage) login.login(user);
    		
    		home.waitImplicitly(10);
        	home.clickPebbleGoNextTab();
        	home.waitImplicitly(5);
   
    	}
    };
    
    /*
	@Rule
	public Retry retry = new Retry(3);*/
    
    @Test
    @Category(PGNSmokeTest.class)
    public void testStatesQuestionOfTheDay(){
    	PGNStatesDatabaseHome states = home.goToStates();
    	states.openQuestionOfTheDay();
    	states.waitImplicitly(2);
    	
    	int answersCount = states.questionOfTheDay.getNumberOfAnwers();
    	
    	states.questionOfTheDay.clickAnswerByINT(2);
    	assertTrue(states.questionOfTheDay.isAnswerActive(2) && !states.questionOfTheDay.isAnswerActive(1));
    	
    	states.questionOfTheDay.clickAnswerByINT(1);
    	assertTrue(states.questionOfTheDay.isAnswerActive(1) && !states.questionOfTheDay.isAnswerActive(2));
    	states.questionOfTheDay.vote();
    	states.waitImplicitly(2);
    	
    	assertTrue(states.questionOfTheDay.getNumberofResponses() == answersCount); 
    	states.questionOfTheDay.closeModal();
    	states.waitImplicitly(2);
    	home = (PGNStudentHomePage) states.header.clickHomeBreadcrumb();
    }
    
    @Test
    public void testScienceQuestionOfTheDay(){
    	PGNScienceDatabaseHome science = home.goToScience();
    	science.openQuestionOfTheDay();
    	science.waitImplicitly(2);
    	
    	int answersCount = science.questionOfTheDay.getNumberOfAnwers();
    	
    	science.questionOfTheDay.clickAnswerByINT(2);
    	assertTrue(science.questionOfTheDay.isAnswerActive(2) && !science.questionOfTheDay.isAnswerActive(1));
    	
    	science.questionOfTheDay.clickAnswerByINT(1);
    	assertTrue(science.questionOfTheDay.isAnswerActive(1) && !science.questionOfTheDay.isAnswerActive(2));
    	science.questionOfTheDay.vote();
    	science.waitImplicitly(2);
    	
    	assertTrue(science.questionOfTheDay.getNumberofResponses() == answersCount);  
    	science.questionOfTheDay.closeModal();
    	home = (PGNStudentHomePage) science.header.clickHomeBreadcrumb();
    }
    
    @Test
    public void testIndiansQuestionOfTheDay(){
    	PGNIndiansDatabaseHome indians = home.goToIndians();
    	indians.openQuestionOfTheDay();
    	indians.waitImplicitly(2);
    	
    	int answersCount = indians.questionOfTheDay.getNumberOfAnwers();
    	
    	indians.questionOfTheDay.clickAnswerByINT(2);
    	assertTrue(indians.questionOfTheDay.isAnswerActive(2) && !indians.questionOfTheDay.isAnswerActive(1));
    	
    	indians.questionOfTheDay.clickAnswerByINT(1);
    	assertTrue(indians.questionOfTheDay.isAnswerActive(1) && !indians.questionOfTheDay.isAnswerActive(2));
    	indians.questionOfTheDay.vote();
    	indians.waitImplicitly(2);
    	
    	assertTrue(indians.questionOfTheDay.getNumberofResponses() == answersCount);  
    	indians.questionOfTheDay.closeModal();
    	home = (PGNStudentHomePage) indians.header.clickHomeBreadcrumb();
    }
    
    
    @Test
    public void testSocialStudiesQuestionOfTheDay(){
    	PGNSocialStudiesDatabasePage social = home.goToSocialStudies();
    	social.openQuestionOfTheDay();
    	social.waitImplicitly(2);
    	
    	int answersCount = social.questionOfTheDay.getNumberOfAnwers();
    	
    	social.questionOfTheDay.clickAnswerByINT(2);
    	assertTrue(social.questionOfTheDay.isAnswerActive(2) && !social.questionOfTheDay.isAnswerActive(1));
    	
    	social.questionOfTheDay.clickAnswerByINT(1);
    	assertTrue(social.questionOfTheDay.isAnswerActive(1) && !social.questionOfTheDay.isAnswerActive(2));
    	social.questionOfTheDay.vote();
    	social.waitImplicitly(2);
    	
    	assertTrue(social.questionOfTheDay.getNumberofResponses() == answersCount);  
    	social.questionOfTheDay.closeModal();
    	home = (PGNStudentHomePage) social.header.clickHomeBreadcrumb();
    }
    
    @Test
    public void testBiographiesQuestionOfTheDay(){
    	PGNDatabasePage social = home.goToBiographies();
    	social.openQuestionOfTheDay();
    	social.waitImplicitly(2);
    	
    	int answersCount = social.questionOfTheDay.getNumberOfAnwers();
    	
    	social.questionOfTheDay.clickAnswerByINT(2);
    	assertTrue(social.questionOfTheDay.isAnswerActive(2) && !social.questionOfTheDay.isAnswerActive(1));
    	
    	social.questionOfTheDay.clickAnswerByINT(1);
    	assertTrue(social.questionOfTheDay.isAnswerActive(1) && !social.questionOfTheDay.isAnswerActive(2));
    	social.questionOfTheDay.vote();
    	social.waitImplicitly(2);
    	
    	assertTrue(social.questionOfTheDay.getNumberofResponses() == answersCount);  
    	social.questionOfTheDay.closeModal();
    	home = (PGNStudentHomePage) social.header.clickHomeBreadcrumb();
    }
    
    //@Test
    @Category(PGNSmokeTest.class)
    public void testQuestionOfTheDaySecondLevelIsTheSame(){
    	PGNScienceDatabaseHome science = home.goToScience();
    	science.waitImplicitly(1);
    	science.openQuestionOfTheDay();
    	science.waitImplicitly(1);
    	
    	ArrayList<String> answers = science.questionOfTheDay.getAnswers();
    	
    	int answersCount = science.questionOfTheDay.getNumberOfAnwers();
    	
    	science.questionOfTheDay.closeModal();
    	science.waitImplicitly(1);
    	science.clickCategory("Earth Science");
    	science.waitImplicitly(1);
    	science.openQuestionOfTheDay();
    	science.waitImplicitly(1);
    	
    	assertTrue(science.questionOfTheDay.AnswersMatch(answers));
    	
    	science.questionOfTheDay.clickAnswerByINT(2);
    	science.waitImplicitly(1);
    	assertTrue(science.questionOfTheDay.isAnswerActive(2) && !science.questionOfTheDay.isAnswerActive(1));
    	
    	science.questionOfTheDay.clickAnswerByINT(1);
    	science.waitImplicitly(1);
    	assertTrue(science.questionOfTheDay.isAnswerActive(1) && !science.questionOfTheDay.isAnswerActive(2));
    	science.questionOfTheDay.vote();
    	science.waitImplicitly(2);
    	
    	assertTrue(science.questionOfTheDay.getNumberofResponses() == answersCount);  
    	science.questionOfTheDay.closeModal();
    	home = (PGNStudentHomePage) science.header.clickHomeBreadcrumb();
    }
    
    @Test
    public void testQuestionOfTheDayThirdLevelIsTheSame(){
    	PGNScienceDatabaseHome science = home.goToScience();
    	science.waitImplicitly(1);
    	science.openQuestionOfTheDay();
    	science.waitImplicitly(1);
    	
    	ArrayList<String> answers = science.questionOfTheDay.getAnswers();
    	
    	int answersCount = science.questionOfTheDay.getNumberOfAnwers();
    	
    	science.questionOfTheDay.closeModal();
    	science.waitImplicitly(1);
    	science.clickCategory("Earth Science");
    	science.waitImplicitly(1);
    	science.clickCategory("Atmosphere");
    	science.waitImplicitly(1);
    	
    	science.openQuestionOfTheDay();
    	science.waitImplicitly(1);
    	
    	assertTrue(science.questionOfTheDay.AnswersMatch(answers));
    	
    	science.questionOfTheDay.clickAnswerByINT(2);
    	science.waitImplicitly(1);
    	assertTrue(science.questionOfTheDay.isAnswerActive(2) && !science.questionOfTheDay.isAnswerActive(1));
    	
    	science.questionOfTheDay.clickAnswerByINT(1);
    	science.waitImplicitly(1);
    	assertTrue(science.questionOfTheDay.isAnswerActive(1) && !science.questionOfTheDay.isAnswerActive(2));
    	science.questionOfTheDay.vote();
    	science.waitImplicitly(2);
    	
    	assertTrue(science.questionOfTheDay.getNumberofResponses() == answersCount);  
    	science.questionOfTheDay.closeModal();
    	science.waitImplicitly(1);
    	home = (PGNStudentHomePage) science.header.clickHomeBreadcrumb();
    }
    
    
    @Test
    @Category(PGNSmokeTest.class)
    public void testQuestionOfTheDayFourthLevelIsTheSame(){
    	PGNScienceDatabaseHome science = home.goToScience();
    	science.openQuestionOfTheDay();
    	science.waitImplicitly(1);
    	
    	ArrayList<String> answers = science.questionOfTheDay.getAnswers();
    	
    	int answersCount = science.questionOfTheDay.getNumberOfAnwers();
    	
    	science.questionOfTheDay.closeModal();
    	science.waitImplicitly(2);
    	
    	science.clickCategory("Earth Science");
    	science.waitImplicitly(1);
    	science.clickCategory("Atmosphere");
    	science.waitImplicitly(1);
    	science.clickCategory("Weather and Climate");
    	science.waitImplicitly(1);
    	
    	science.openQuestionOfTheDay();
    	science.waitImplicitly(1);
    	
    	assertTrue(science.questionOfTheDay.AnswersMatch(answers));
    	
    	science.questionOfTheDay.clickAnswerByINT(2);
    	science.waitImplicitly(1);
    	assertTrue(science.questionOfTheDay.isAnswerActive(2) && !science.questionOfTheDay.isAnswerActive(1));
    	
    	science.questionOfTheDay.clickAnswerByINT(1);
    	science.waitImplicitly(1);
    	assertTrue(science.questionOfTheDay.isAnswerActive(1) && !science.questionOfTheDay.isAnswerActive(2));
    	science.questionOfTheDay.vote();
    	science.waitImplicitly(1);
    	
    	assertTrue(science.questionOfTheDay.getNumberofResponses() == answersCount);  
    	science.waitImplicitly(1);
    	science.questionOfTheDay.closeModal();
    	science.waitImplicitly(1);
    	
    	home = (PGNStudentHomePage) science.header.clickHomeBreadcrumb();
    }
    
    @Test
    @Category(PGNSmokeTest.class)
    public void testScienceExperimentVolcanoTranscript() throws InterruptedException{
    	PGNScienceDatabaseHome science = home.goToScience();
    	PGNExperimentsPage experiments = science.goToExperimentsPage();
    	
    	PGNVideoModal video = experiments.clickVideoByTitle("Volcano");
    	
		assertTrue(video.verifyLinkByElement(video.downloadTranscriptButton)
				&& video.verifyLinkContainsURLByElement(video.downloadTranscriptButton, "/pdf/pgnsciencevolcano_transcript.pdf"));
		
		experiments.video.closeModal();
		experiments.header.clickHomeBreadcrumb();
    }
    
    @Test
    public void testScienceExperimentMagnetismTranscript() throws InterruptedException{
    	PGNScienceDatabaseHome science = home.goToScience();
    	PGNExperimentsPage experiments = science.goToExperimentsPage();

    	PGNVideoModal video = experiments.clickVideoByTitle("Magnetism");
    	
		
		assertTrue(video.verifyLinkByElement(video.downloadTranscriptButton)
				&& video.verifyLinkContainsURLByElement(video.downloadTranscriptButton, "/pdf/pgnsciencemagnetism_transcript.pdf"));
		experiments.video.closeModal();
		experiments.header.clickHomeBreadcrumb();
    }
    
    @Test
    public void testScienceExperimentErosionTranscript() throws InterruptedException{
    	PGNScienceDatabaseHome science = home.goToScience();
    	PGNExperimentsPage experiments = science.goToExperimentsPage();

    	PGNVideoModal video = experiments.clickVideoByTitle("Erosion");
    	
		
		assertTrue(video.verifyLinkByElement(video.downloadTranscriptButton)
				&& video.verifyLinkContainsURLByElement(video.downloadTranscriptButton, "/pdf/pgnscienceerosion_transcript.pdf"));
		experiments.video.closeModal();
		experiments.header.clickHomeBreadcrumb();
    }
    
    @Test
    public void testScienceExperimentAirPressureTranscript() throws InterruptedException{
    	PGNScienceDatabaseHome science = home.goToScience();
    	PGNExperimentsPage experiments = science.goToExperimentsPage();
    	
    	PGNVideoModal video = experiments.clickVideoByTitle("Air Pressure");
    	
		
		assertTrue(video.verifyLinkByElement(video.downloadTranscriptButton)
				&& video.verifyLinkContainsURLByElement(video.downloadTranscriptButton, "/pdf/pgnscienceairpressure_transcript.pdf"));
		experiments.video.closeModal();
		experiments.header.clickHomeBreadcrumb();
    }
    
    @Test
    public void testScienceExperimentBubblingBlobsTranscript() throws InterruptedException{
    	PGNScienceDatabaseHome science = home.goToScience();
    	PGNExperimentsPage experiments = science.goToExperimentsPage();
    	
    	PGNVideoModal video = experiments.clickVideoByTitle("Bubbling Blobs");
    	
		assertTrue(video.verifyLinkByElement(video.downloadTranscriptButton)
				&& video.verifyLinkContainsURLByElement(video.downloadTranscriptButton, "/pdf/pgnscienceblobs_transcript.pdf"));
		experiments.video.closeModal();
		experiments.header.clickHomeBreadcrumb();
    }
    
    @Test
    public void testScienceExperimentMonsterFoamTranscript() throws InterruptedException{
    	PGNScienceDatabaseHome science = home.goToScience();
    	PGNExperimentsPage experiments = science.goToExperimentsPage();
    	
    	PGNVideoModal video = experiments.clickVideoByTitle("Monster Foam");
		
		assertTrue(video.verifyLinkByElement(video.downloadTranscriptButton)
				&& video.verifyLinkContainsURLByElement(video.downloadTranscriptButton, "/pdf/pgnsciencemonsterfoam_transcript.pdf"));
		experiments.video.closeModal();
		experiments.header.clickHomeBreadcrumb();
    }
    
    @Test
    public void testScienceExperimentOceansAsAWaterSourceTranscript() throws InterruptedException{
    	PGNScienceDatabaseHome science = home.goToScience();
    	PGNExperimentsPage experiments = science.goToExperimentsPage();
    	
    	PGNVideoModal video = experiments.clickVideoByTitle("Oceans as a Water Source");
    	
		assertTrue(video.verifyLinkByElement(video.downloadTranscriptButton)
				&& video.verifyLinkContainsURLByElement(video.downloadTranscriptButton, "/pdf/pgnscienceoceans_transcript.pdf"));
		experiments.video.closeModal();
		experiments.header.clickHomeBreadcrumb();
    }
    
    @Test
    public void testScienceExperimentBalloonRocketTranscript() throws InterruptedException{
    	PGNScienceDatabaseHome science = home.goToScience();
    	PGNExperimentsPage experiments = science.goToExperimentsPage();

    	PGNVideoModal video = experiments.clickVideoByTitle("Balloon Rocket");
    	
		assertTrue(video.verifyLinkByElement(video.downloadTranscriptButton)
				&& video.verifyLinkContainsURLByElement(video.downloadTranscriptButton, "/pdf/pgnsciencerocket_transcript.pdf"));
		experiments.video.closeModal();
		experiments.header.clickHomeBreadcrumb();
    }
    
    @Test
    public void testScienceExperimentTornadoInABottleTranscript() throws InterruptedException{
    	PGNScienceDatabaseHome science = home.goToScience();
    	PGNExperimentsPage experiments = science.goToExperimentsPage();
    	
    	experiments.moveToVideo("Tornado in a Bottle");
    	
    	PGNVideoModal video = experiments.clickVideoByTitle("Tornado in a Bottle");
		
		assertTrue(video.verifyLinkByElement(video.downloadTranscriptButton)
				&& video.verifyLinkContainsURLByElement(video.downloadTranscriptButton, "pdf/pgnsciencetornado_transcript.pdf"));
		experiments.video.closeModal();
		experiments.header.clickHomeBreadcrumb();
    }
    
    @Test
    @Category(PGNSmokeTest.class)
    public void testHomeBreadcrumbScience(){
    	PGNScienceDatabaseHome science = home.goToScience();
    	science = (PGNScienceDatabaseHome) science.clickCategory("Earth Science");
    	science = (PGNScienceDatabaseHome) science.clickCategory("Geosphere");
    	science.clickBreadcrumbByText("Earth Science");
    	assertTrue(science.getTitle().equals("Earth Science"));
    	
    	home = science.clickHomeBreadcrumb();
    	assertTrue("Asserts that the user can navigate back to the home page from the science database.", home.isAMIndiansDBPresent() && home.isScienceDBPresent() && home.isStatesDBPresent());
    }
    
    @Test
    public void testHomeBreadcrumbIndians(){
    	PGNIndiansDatabaseHome indians = home.goToIndians();
    	indians.clickCategory("Arctic Tribal Nations");
    	assertTrue(indians.getTitle().equals("Arctic Tribal Nations"));
    	
    	home = indians.clickHomeBreadcrumb();
    	assertTrue("Asserts that the user can navigate back to the home page from the indians database.", home.isAMIndiansDBPresent() && home.isScienceDBPresent() && home.isStatesDBPresent());
    }
    
    @Test
    public void testHomeBreadcrumbStates(){
       	PGNStatesDatabaseHome states = home.goToStates();
    	states.clickCategory("Midwest");
    	assertTrue(states.getTitle().equals("Midwest"));
    	
    	home = states.clickHomeBreadcrumb();
    	assertTrue("Asserts that the user can navigate back to the home page from the states database.", home.isAMIndiansDBPresent() && home.isScienceDBPresent() && home.isStatesDBPresent());
    }
    
    @Test
    public void testHomeBreadcrumbSocialStudies(){
    	PGNSocialStudiesDatabasePage socialStudies = home.goToSocialStudies();
       	socialStudies.clickCategory("Geography");
    	assertTrue(socialStudies.getTitle().equals("Geography"));
    	
    	home = socialStudies.clickHomeBreadcrumb();
    	assertTrue("Asserts that the user can navigate back to the home page from the social studies database.", home.isAMIndiansDBPresent() && home.isScienceDBPresent() && home.isStatesDBPresent());
    }
    
    @Test
    public void testHomeBreadcrumbBiographies(){
    	PGNDatabasePage biographies = home.goToBiographies();
       	biographies.clickCategory("Artists and Authors");
    	assertTrue(biographies.getTitle().equals("Artists and Authors"));
    	
    	home = biographies.clickHomeBreadcrumb();
    	assertTrue("Asserts that the user can navigate back to the home page from the biographies database.", home.isAMIndiansDBPresent() && home.isScienceDBPresent() && home.isStatesDBPresent());
    }
    
    @Test
    @Category(PGNSmokeTest.class)
    public void testBreadCrumbFirstLevel(){
    	PGNScienceDatabaseHome science = home.goToScience();
    	science.clickCategory("Earth Science");
    	assertTrue(science.getTitle().equals("Earth Science"));
    	
    	science.clickBreadcrumbByText("Science");
    	assertTrue(science.getTitle().equals("Science"));
    }
    
    @Test
    public void testBreadCrumbSecondLevel(){
    	PGNScienceDatabaseHome science = home.goToScience();
    	science.clickCategory("Earth Science").clickCategory("Atmosphere");
    	assertTrue(science.getTitle().equals("Atmosphere"));
    	
    	science.clickBreadcrumbByText("Earth Science");
    	assertTrue(science.getTitle().equals("Earth Science"));
    }
    
    @Test
    @Category(PGNSmokeTest.class)
    public void testBreadCrumbThirdLevel(){
    	PGNScienceDatabaseHome science = home.goToScience();
    	science = (PGNScienceDatabaseHome) science.clickCategory("Earth Science").clickCategory("Atmosphere").clickCategory("Weather and Climate");
    	assertTrue(science.getTitle().equals("Weather and Climate"));
    	
    	science.clickBreadcrumbByText("Atmosphere");
    	assertTrue(science.getTitle().equals("Atmosphere"));
    }
}
