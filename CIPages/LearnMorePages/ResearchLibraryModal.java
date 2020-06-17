package LearnMorePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class ResearchLibraryModal extends BasePage{
	public By literacyAndTechLink = By.linkText("Literacy & Technology");
	public By multimodalLearningLink = By.linkText("Multimodal Learning");
	public By reviewOfLiterature = By.linkText("Review of Literature");
	public By teachingSuggestionsLink = By.linkText("Teaching Suggestions");
	public By twelveWaysToUseLink = By.linkText("12 ways to use Capstone Interactive");
	public By capstoneLessonPlanLink = By.linkText("Introduction to Capstone Interactive lesson plan");
	public By readerTheatreLink = By.linkText("Reader's Theatre lesson plan");
	public By earlyReaderLink = By.linkText("Early Readers lesson plan");
	public By timelineLessonPlan = By.linkText("Timeline lesson plan");
	public By strugglingReaderLink = By.linkText("Struggling reader lesson plan");
	//placeholder for second struggling reader lesson plan
	public By blastToThePastLink = By.linkText("Stone Arch Books: Blast to the Past");
	public By dragonKiteContestLink = By.linkText("Stone Arch Books: Dragon Kite Contest");
	public By invaderFromGreatGooLink = By.linkText("Stone Arch Books: Invaders from the Great Goo Galaxy");
	public By highschoolZombies = By.linkText("Stone Arch Books: Night of the Highschool Zombies");
	public By tigerMothLink = By.linkText("Stone Arch Books: Tiger Moth, Insect Ninja");
	public By aJourneyIntoAdaptationLink = By.linkText("Graphic Library: A Journey into Adaptation");
	public By exploringEcosystemsLink = By.linkText("Graphic Library: Exploring Ecosystems");
	public By shockingWorldLink = By.linkText("Graphic Library: The Shocking World of Electricity");
	public By worldOfFoodChainsLink = By.linkText("Graphic Library: The World of Food Chains");
	public By crashCourseLink = By.linkText("Graphic Library: A Crash Course in Forces and Motion");
	public By understandingPhotosynthesisLink = By.linkText("Graphic Library: Understanding Photosynthesis");
	public By lessonsInScienceLink = By.linkText("Graphic Library: Lessons in Science Safety");
	public By adventuresInSoundLink = By.linkText("Graphic Library: Adventures in Sound");
	
	public By[] initialVisibleElements = {literacyAndTechLink, multimodalLearningLink, reviewOfLiterature, 
			teachingSuggestionsLink, twelveWaysToUseLink, capstoneLessonPlanLink, readerTheatreLink, earlyReaderLink,
			strugglingReaderLink, blastToThePastLink, dragonKiteContestLink, invaderFromGreatGooLink,highschoolZombies,
			tigerMothLink, aJourneyIntoAdaptationLink, exploringEcosystemsLink, shockingWorldLink, worldOfFoodChainsLink,
			crashCourseLink,understandingPhotosynthesisLink,lessonsInScienceLink, adventuresInSoundLink};
	
	public ResearchLibraryModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
