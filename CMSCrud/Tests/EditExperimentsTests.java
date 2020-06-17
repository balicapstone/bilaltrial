package Tests;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import CMSPages.CMSHomePage;
import CMSPages.CMSLandingPage;
import CMSPages.EditExperimentsPage;
import Modals.VideoModal;
import PGNPages.LoginPage;
import PGNStudentResources.ExperimentsPage;
import PGNStudentResources.ScienceDatabaseHome;
import PGNStudentResources.StudentHomePage;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;
import UserClasses.UserInfo;

public class EditExperimentsTests {
	public static User user = new User(UserInfo.GARAGESTUDENT);
	public static User student = new User(UserInfo.GARAGESTUDENT);
	public static CMSHomePage home;
	public static StudentHomePage studentHome;
	public static LoginPage pgnLoginPage;
	public static ExperimentsPage experiments;
	public static EditExperimentsPage editExperiments;
	
	@BeforeClass
	public static void executeBefore(){
		CMSLandingPage cms = new CMSLandingPage(user.getDriver());
		cms.waitImplicitly(2);
		
		home = cms.login(user);
		home.waitImplicitly(5);
		home.scrollToTopofPage();
		home.openToolbar();
		home.waitImplicitly(2);
		editExperiments = home.toolbar.clickExperiments();
		editExperiments.scrollToTopofPage();
		
 		pgnLoginPage = new LoginPage(student, "editorialqa");
 		studentHome = (StudentHomePage) pgnLoginPage.login(student);
    	ScienceDatabaseHome science = studentHome.goToScience();
    	experiments = science.goToExperimentsPage();
	}
	
	@AfterClass
	public static void executeAfter(){
		user.quit();
		student.quit();
	}
	
	@Rule
	public TestWatcher restart = new TestWatcher(){
		@Override
		public void failed(Throwable e, Description description){
			user.getWatcher().TakeScreenshot(description.getDisplayName());
			student.getWatcher().TakeScreenshot(description.getDisplayName());
			
			user.quit();
			student.quit();
			
			user.makeNewDriver();
			student.makeNewDriver();
			
			CMSLandingPage cms = new CMSLandingPage(user.getDriver());
			cms.waitImplicitly(2);
			
			home = cms.login(user);
			home.waitImplicitly(5);
			home.scrollToTopofPage();
			home.openToolbar();
			home.waitImplicitly(2);
			editExperiments = home.toolbar.clickExperiments();
			editExperiments.scrollToTopofPage();
			
	 		pgnLoginPage = new LoginPage(student, "editorialqa");
	 		studentHome = (StudentHomePage) pgnLoginPage.login(student);
	    	ScienceDatabaseHome science = studentHome.goToScience();
	    	experiments = science.goToExperimentsPage();
		}
	};
	
	@Rule
	public Retry retry = new Retry(3);
	
	
	@Test
	public void testAddingExperiment(){
		editExperiments.scrollToTopofPage();
		editExperiments.waitImplicitly(5);
		editExperiments.clickCreateExperiment();
		
		Date date = new Date();
		String time = new Timestamp(date.getTime()).toString().substring(11, 23).replace(":", "");
		String experimentName =  "Test"+time;
		
		assertTrue("Asserts that the created experiment is not currently displayed", !experiments.isVideoPresent(experimentName));
		
		
		editExperiments.enterTitle(experimentName);
		editExperiments.enterVideoFile("pgnscienceblobs_experiment_video_blobs.mp4");
		editExperiments.enterTranscriptFile("pgnscienceblobs_transcript.pdf");
		editExperiments.enterImageFile("pgnscienceblobs_image.png");
		editExperiments.clickActive();
		editExperiments.clickPublish();
		editExperiments.clickSubmit();
		editExperiments.waitImplicitly(5);
		
		experiments.refresh();
		
		assertTrue("Asserts that the created experiment is not currently displayed", experiments.isVideoPresent(experimentName));
		
		assertTrue("Asserts that the experiment image is correct is correct", 
				experiments.getExperimentImage(experimentName).equals("https://pgassets-qa.pebblego.com/cms_content/images/pgnscienceblobs_image.png"));
		assertTrue("Asserts that the image is currently displayed",
				experiments.verifyAsset(experiments.getExperimentImage(experimentName)));
		
		assertTrue("Asserts that the created experiment is displayed", 
				experiments.isVideoPresent(experimentName));
		
		experiments.moveToVideo(experimentName);
		
		VideoModal modal = experiments.clickVideoByTitle(experimentName);
		modal.waitImplicitly(2);
		
		assertTrue("Asserts that the created experiment is not currently displayed", 
				modal.getVideoString().equals("https://pgassets-qa.pebblego.com/cms_content/video/pgnscienceblobs_experiment_video_blobs.mp4"));
		assertTrue("Asserts that the created experiment has a valid video",
				modal.verifyAsset(modal.getVideoString()));
		
		assertTrue("Asserts that the Erosion experiment has the changed transcpript",
				modal.getTranscriptString().equals("https://pgassets-qa.pebblego.com/cms_content/pdf/pgnscienceblobs_transcript.pdf"));
		assertTrue("Asserts that the video in the Erosion modal is valid",
				modal.verifyAsset(modal.getTranscriptString()));
		modal.closeModal();
	}
	
	@Test
	public void testChangeExperimentTitle(){
		editExperiments.scrollToTopofPage();
		editExperiments.waitImplicitly(5);
		
		editExperiments.editExperimentByName("Erosion");
		editExperiments.waitImplicitly(2);
		
		assertTrue("Asserts that the created experiment is not currently displayed", 
				experiments.isVideoPresent("Erosion") && !experiments.isVideoPresent("Less Stuff"));
		
		editExperiments.enterTitle("Less Stuff");
		editExperiments.clickSubmit();
		editExperiments.clickClose();
		editExperiments.waitImplicitly(2);
		
		experiments.refresh();
		
		assertTrue("Asserts that the created experiment is not currently displayed", 
				!experiments.isVideoPresent("Erosion") && experiments.isVideoPresent("Less Stuff"));
		
		
		editExperiments.editExperimentByName("Less Stuff");
		editExperiments.waitImplicitly(2);
		editExperiments.enterTitle("Erosion");
		
		editExperiments.clickSubmit();
		editExperiments.clickClose();
		editExperiments.waitImplicitly(2);
		
		experiments.refresh();
		
		assertTrue("Asserts that the created experiment is not currently displayed", 
				experiments.isVideoPresent("Erosion") && !experiments.isVideoPresent("Less Stuff"));
	}
	
	@Test
	public void testChangeVideoFile(){
		editExperiments.scrollToTopofPage();
		editExperiments.waitImplicitly(5);
		
		editExperiments.editExperimentByName("Erosion");
		editExperiments.waitImplicitly(2);
		
		VideoModal modal = experiments.clickVideoByTitle("Erosion");
		modal.waitImplicitly(2);
		
		String erosionVideo = "https://pgassets-qa.pebblego.com/cms_content/video/pgnscienceerosion_experiment_video_erosion.mp4";
		
		assertTrue("Asserts that the Erosion experiment has the correct video",
				modal.getVideoString().equals(erosionVideo));
		assertTrue("Asserts that the video in the Erosion modal is valid",
				modal.verifyAsset(modal.getVideoString()));
		
		editExperiments.enterVideoFile("pgnsciencemagnetism_experiment_video_magnetism.mp4");
		editExperiments.clickSubmit();
		editExperiments.clickClose();
		editExperiments.waitImplicitly(2);
		
		experiments.refresh();
		experiments.waitImplicitly(2);
		modal = experiments.clickVideoByTitle("Erosion");
		
		String newVideo = "https://pgassets-qa.pebblego.com/cms_content/video/pgnsciencemagnetism_experiment_video_magnetism.mp4";
		assertTrue("Asserts that the created experiment is not currently displayed", 
				modal.getVideoString().equals(newVideo)
				&& modal.verifyAsset(modal.getVideoString()));
		
		
		editExperiments.editExperimentByName("Erosion");
		editExperiments.waitImplicitly(2);
		editExperiments.enterVideoFile("pgnscienceerosion_experiment_video_erosion.mp4");
		
		editExperiments.clickSubmit();
		editExperiments.clickClose();
		editExperiments.waitImplicitly(2);
		
		experiments.refresh();
		experiments.waitImplicitly(2);
		modal = experiments.clickVideoByTitle("Erosion");
		
		assertTrue("Asserts that the Erosion experiment has the correct video", 
				modal.getVideoString().equals(erosionVideo)
				&& modal.verifyAsset(modal.getVideoString()));
		modal.closeModal();
	}
	
	@Test
	public void testChangeTranscriptFile(){
		editExperiments.scrollToTopofPage();
		editExperiments.waitImplicitly(5);
		
		editExperiments.editExperimentByName("Erosion");
		editExperiments.waitImplicitly(2);
		
		VideoModal modal = experiments.clickVideoByTitle("Erosion");
		modal.waitImplicitly(2);
		
		String erosionTranscript = "https://pgassets-qa.pebblego.com/cms_content/pdf/pgnscienceerosion_transcript.pdf";
		
		assertTrue("Asserts that the Erosion experiment has the correct transcript",
				modal.getTranscriptString().equals(erosionTranscript));
		assertTrue("Asserts that the video in the Erosion modal is valid",
				modal.verifyAsset(modal.getTranscriptString()));
		
		editExperiments.enterTranscriptFile("pgnsciencemagnetism_transcript.pdf");
		editExperiments.clickSubmit();
		editExperiments.clickClose();
		editExperiments.waitImplicitly(2);
		
		experiments.refresh();
		experiments.waitImplicitly(2);
		modal = experiments.clickVideoByTitle("Erosion");
		
		String newTranscript = "https://pgassets-qa.pebblego.com/cms_content/pdf/pgnsciencemagnetism_transcript.pdf";
		assertTrue("Asserts that the Erosion experiment has the changed transcpript",
				modal.getTranscriptString().equals(newTranscript));
		assertTrue("Asserts that the video in the Erosion modal is valid",
				modal.verifyAsset(modal.getTranscriptString()));
		
		
		editExperiments.editExperimentByName("Erosion");
		editExperiments.waitImplicitly(2);
		editExperiments.enterTranscriptFile("pgnscienceerosion_transcript.pdf");
		
		editExperiments.clickSubmit();
		editExperiments.clickClose();
		editExperiments.waitImplicitly(2);
		
		experiments.refresh();
		experiments.waitImplicitly(2);
		modal = experiments.clickVideoByTitle("Erosion");
		modal.waitImplicitly(2);
		
		assertTrue("Asserts that the Erosion experiment has the correct video", 
				modal.getTranscriptString().equals(erosionTranscript)
				&& modal.verifyAsset(modal.getTranscriptString()));
		modal.closeModal();
	}
	
	@Test
	public void testChangeImageFileName(){
		editExperiments.scrollToTopofPage();
		editExperiments.waitImplicitly(5);
		
		editExperiments.editExperimentByName("Erosion");
		editExperiments.waitImplicitly(2);
		
		String erosionString = "https://pgassets-qa.pebblego.com/cms_content/images/pgnscienceerosion_image.png";
		
		assertTrue("Asserts that the Erosion experiment image is correct is not currently displayed", 
				experiments.getExperimentImage("Erosion").equals(erosionString));
		assertTrue("Asserts that the image is currently displayed",
				experiments.verifyAsset(experiments.getExperimentImage("Erosion")));
		
		editExperiments.enterImageFile("pgnscienceblobs_image.png");
		editExperiments.clickSubmit();
		editExperiments.clickClose();
		editExperiments.waitImplicitly(2);
		
		experiments.refresh();

		String newImage = "https://pgassets-qa.pebblego.com/cms_content/images/pgnscienceblobs_image.png";
		assertTrue("Asserts that the Erosion experiment image is correct is not currently displayed", 
				experiments.getExperimentImage("Erosion").equals(newImage));
		assertTrue("Asserts that the image is currently displayed",
				experiments.verifyAsset(experiments.getExperimentImage("Erosion")));
		
		editExperiments.enterImageFile("pgnscienceerosion_image.png");
		editExperiments.waitImplicitly(2);
		editExperiments.enterTitle("Erosion");
		
		editExperiments.clickSubmit();
		editExperiments.clickClose();
		editExperiments.waitImplicitly(2);
		
		experiments.refresh();
		
		assertTrue("Asserts that the Erosion experiment image is correct is not currently displayed", 
				experiments.getExperimentImage("Erosion").equals(erosionString));
		assertTrue("Asserts that the image is currently displayed",
				experiments.verifyAsset(experiments.getExperimentImage("Erosion")));
	}
	
	@Test
	public void testInactiveFlags(){
		editExperiments.scrollToTopofPage();
		editExperiments.waitImplicitly(5);
		
		editExperiments.editExperimentByName("Magnetism");
	
		assertTrue("Asserts that the created experiment is not currently displayed", experiments.isVideoPresent("Magnetism"));
		
		editExperiments.clickInactive();
		editExperiments.clickSubmit();
		editExperiments.clickClose();
		editExperiments.waitImplicitly(2);
		
		experiments.refresh();
		
		assertTrue("Asserts that the created experiment is not currently displayed", !experiments.isVideoPresent("Magnetism"));
		
		
		editExperiments.editExperimentByName("Magnetism");
		
		editExperiments.clickActive();
		editExperiments.clickSubmit();
		editExperiments.clickClose();
		editExperiments.waitImplicitly(2);
		
		experiments.refresh();
		
		assertTrue("Asserts that the created experiment is not currently displayed", experiments.isVideoPresent("Magnetism"));
	}
}
