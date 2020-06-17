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
import VisualSearch.VisualSearchMain;

public class VisualSearchTests {
	public static CILoginPage login;
	public static StudentHomePage student;
	public static User user;
	
	
	@BeforeClass
	public static void executeBefore(){
		try{
			user = new User(UserInfo.VISUALSEARCH);
			login = new CILoginPage(user);
			student = (StudentHomePage) login.login(user);
			user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
			user.customWait().until(ExpectedConditions.elementToBeClickable(student.visualSearch));
		}catch(Exception e){
			System.out.println("Failed to set up Visual Search Tests");
			System.out.println(e);
		}
	}
	
	@AfterClass
	public static void executeAfter(){
		login.closeCurrentWindow();
	}
	
	@Rule
	public TestWatcher restart = new TestWatcher(){
		@Override
		public void failed(Throwable e, Description description){
			System.out.println("We are trying to take a screenshot");
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
			user.customWait().until(ExpectedConditions.elementToBeClickable(student.visualSearch));
		}
	};
	
	@Rule
	public Retry retry = new Retry(3);
	
	
	@Test
	@Category(SmokeTests.class)
	public void testNonfictionFirstLevelCategories(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		
		String[] nonFiction = {"All About Me", "Animals", "Hobbies and How-To", "Fairy Tales, Myths & Folklore", "Fun Facts & Trivia", "Ghosts & Unexplained",
		                          "History & Geography", "In The Library", "Language Arts", "Math", "Military", "Science & Technology", "Shapes", "Social Studies", "Sports", "Graphic Nonfiction"};
		assertTrue("Asserts the subcategories are returned when the Nonfiction category is selected",
				vSearch.areCategoriesPresent(nonFiction)); 
		
		student = vSearch.clickCloseButton();
	}
	
	@Test
	public void testFictionCategories(){		
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Fiction");
		
		String[] fiction = {"All Fiction", "Fables", "Folklore", "Scary Stories", "Science Fiction", "Adventure"};

		assertTrue("Asserts the correct subcategories are returned when the Fiction category is selected",
				vSearch.areCategoriesPresent(fiction));
		student = vSearch.clickCloseButton();
	}
	
	@Test
	public void testNonfictionAllAboutMeCategories(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("All About Me");
		
		String[] allAboutMe = {"Health & Safety", "Illnesses and Diseases", "My Body", "Feelings and Manners", 
				"Understanding Differences", "Character Education"};
		
		assertTrue("Asserts the correct subcategories are returned when the All About Me category is selected",
				vSearch.areCategoriesPresent(allAboutMe));
		student = vSearch.clickCloseButton();
	}
	
	@Test
	public void testNonfictionAnimalsCategories(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Animals");

		String[] animals = {"Amphibians", "Birds", "Dinosaurs & Prehistoric Mammals", "Farm Animals", 
				"Fish", "Insects, Spiders and Bugs", "Mammals", "More Animals", "Pets", "Reptiles"};
		
		assertTrue("Asserts the correct subcategories are returned when the Animals category is selected",
				vSearch.areCategoriesPresent(animals));
		student = vSearch.clickCloseButton();
	}
	
	@Test
	public void testNonfictionHobbiesAndHowToCategories(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Hobbies and How-To");

		String[] howToCategories = {"Crafts and How-To", "Dance", "Fashion", "Food and Drink", 
				"Games & Activities", "Magic Tricks", "Movies & Theater", "Music", "Picture Puzzles"};
		
		assertTrue("Asserts the correct subcategories are returned when the Hobbies and How To category is selected",
				vSearch.areCategoriesPresent(howToCategories));
		student = vSearch.clickCloseButton();
	}
	
	@Test
	public void testHistoryAndGeographyCategories(){
		VisualSearchMain vSearch = student.clickVisualSearch();
	
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("History & Geography");
		
		
		String[] historyAndGeography = {"Ancient World", "Geography", "U.S. History", "World History"};
		
		assertTrue("Asserts the correct subcategories are returned when the History and Geography category is selected",
				vSearch.areCategoriesPresent(historyAndGeography));
		student = vSearch.clickCloseButton();
	}
	
	@Test
	public void testUSHistoryCategories(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("History & Geography");
		vSearch.clickCategoryByName("U.S. History");
		
		String[] usHistory = {"General History", "Colonial America", "Revolutionary War Era", "Post-Revolutionary War", 
				"U.S. Civil War Era", "Post-U.S. Civil War", "Modern U.S. History"};
		
		assertTrue("Asserts the correct subcategories are returned when the US History category is selected",
				vSearch.areCategoriesPresent(usHistory));
		student = vSearch.clickCloseButton();
	}
	
	@Test
	public void testScienceAndTechnologyCategories(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Science & Technology");
		
		String[] scienceTechnology = {"Earth Science", "General Science", "Life Science", "Physical Science", 
				"Plants", "Space", "Engineering", "Technology"};
		
		assertTrue("Asserts the correct subcategories are returned when the Science and Technology category is selected",
				vSearch.areCategoriesPresent(scienceTechnology));
		student = vSearch.clickCloseButton();
	}
	
	@Test
	public void testSocialStudiesCategories(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Social Studies");
		
		String[] socialStudies = {"Communication and Transportation", "Customs and Culture", "Fairy Tales and Folk Tales", "Government", 
				"Laws", "Media and Literacy", "Money", "Social Science", "Religions Around the World"};
		
		assertTrue("Asserts the correct subcategories are returned when the Social Studies category is selected",
				vSearch.areCategoriesPresent(socialStudies));
		student = vSearch.clickCloseButton();
	}
	
	@Test
	public void testSportsCategories(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Sports");
	
		String[] sports = {"Football", "Hockey", "Martial Arts", "Monster Trucks and Racing", 
				"Other Sports", "Outdoor Sports", "Soccer", "Wrestling"};
		
		assertTrue("Asserts the correct subcategories are returned when the Sports category is selected",
				vSearch.areCategoriesPresent(sports));
		student = vSearch.clickCloseButton();
	}
	
	@Test
	public void testResultsHealthAndSafety(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("All About Me");
		vSearch.clickCategoryByName("Health & Safety");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		student = vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Health and Safety category is selected",
				student.isBookPresentByTitle("101 Ways to Get in Shape") &&
				student.isBookPresentByTitle("A Balanced Diet") &&
				student.isBookPresentByTitle("Being Active") &&
				student.isBookPresentByTitle("A Day in the Life of a Doctor"));
	}
	
	@Test
	public void testResultsIllnessesAndDiseases(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("All About Me");
		vSearch.clickCategoryByName("Illnesses and Diseases");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		student = vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Illnesses and Diseases category is selected",
				student.isBookPresentByTitle("The 1918 Flu Pandemic") &&
				student.isBookPresentByTitle("A Germ's Journey") &&
				student.isBookPresentByTitle("Anatomy of a Pandemic"));
	}
	
	@Test
	public void testResultsMyBody(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("All About Me");
		vSearch.clickCategoryByName("My Body");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		student = vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the My Body category is selected",
				student.isBookPresentByTitle("101 Things You Didn't Know About Your Body") &&
				student.isBookPresentByTitle("A Tour of Your Circulatory System") &&
				student.isBookPresentByTitle("A Tour of Your Digestive System"));
	}
	
	@Test
	public void testResultsFeelingsAndManners(){
		student.waitForElement(student.visualSearch);
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("All About Me");
		vSearch.clickCategoryByName("Feelings and Manners");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		student = vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Feelings and Manners category is selected",
				student.isBookPresentByTitle("Angry Is ...") &&
				student.isBookPresentByTitle("Animals Big and Small") &&
				student.isBookPresentByTitle("Big and Small"));
	}
	
	@Test
	public void testResultsUnderstandingDifferences(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("All About Me");
		vSearch.clickCategoryByName("Understanding Differences");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		student = vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Understanding Differences category is selected",
				student.isBookPresentByTitle("Assistance Dogs") &&
				student.isBookPresentByTitle("Coping with Domestic Violence") &&
				student.isBookPresentByTitle("Coping with Moving Away"));
	}
	
	@Test
	public void testResultsCharacterEducation(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("All About Me");
		vSearch.clickCategoryByName("Character Education");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		student = vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Characters Education category is selected",
				student.isBookPresentByTitle("Acting Responsibly") &&
				student.isBookPresentByTitle("Being Brave") &&
				student.isBookPresentByTitle("Being Considerate"));
	}
	
	@Test
	public void testResultsAmphibians(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Animals");
		vSearch.clickCategoryByName("Amphibians");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		student = vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Amphibians category is selected",
				student.isBookPresentByTitle("Amphibians: Water-to-Land Animals") &&
				student.isBookPresentByTitle("Amphibians: A Question and Answer Book") &&
				student.isBookPresentByTitle("Salamanders"));
	}
	
	@Test
	public void testResultsBirds(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Animals");
		vSearch.clickCategoryByName("Birds");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		student = vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Birds category is selected",
				student.isBookPresentByTitle("A Baby Duck Story") &&
				student.isBookPresentByTitle("A Baby Penguin Story") &&
				student.isBookPresentByTitle("Barn Owls"));
	}
	
	@Test
	public void testResultsDinosaurs(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Animals");
		vSearch.clickCategoryByName("Dinosaurs & Prehistoric Mammals");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		student = vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Dinosaurs category is selected",
				student.isBookPresentByTitle("American Mastodon") &&
				student.isBookPresentByTitle("Apatosaurus") &&
				student.isBookPresentByTitle("Brachiosaurus"));
	}
	
	@Test
	public void testResultsFarmAnimals(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Animals");
		vSearch.clickCategoryByName("Farm Animals");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		student = vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Farm Animals category is selected",
				student.isBookPresentByTitle("Chickens") &&
				student.isBookPresentByTitle("Cows") &&
				student.isBookPresentByTitle("Llamas"));
	}
	
	@Test
	public void testResultsFish(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Animals");
		vSearch.clickCategoryByName("Fish");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		student = vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Fish category is selected",
				student.isBookPresentByTitle("Betta Fish") &&
				student.isBookPresentByTitle("Clown Fish") &&
				student.isBookPresentByTitle("Clown Fish and Sea Anemones Work Together"));
	}
	
	@Test
	public void testResultsInsects(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Animals");
		vSearch.clickCategoryByName("Insects, Spiders and Bugs");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		student = vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Insects category is selected",
				student.isBookPresentByTitle("BIG Bugs") &&
				student.isBookPresentByTitle("Cicadas") &&
				student.isBookPresentByTitle("Crickets"));
	}
	
	@Test
	public void testResultsMammals(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Animals");
		vSearch.clickCategoryByName("Mammals");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		student = vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Mammals category is selected",
				student.isBookPresentByTitle("American Bison") &&
				student.isBookPresentByTitle("Arctic Shrews") &&
				student.isBookPresentByTitle("Armadillos"));
	}
	
	@Test
	public void testResultsMoreAnimals(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Animals");
		vSearch.clickCategoryByName("More Animals");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		student = vSearch.clickSearchButton();

		assertTrue("Asserts the correct books are returned when the More Animals category is selected",
				student.isBookPresentByTitle("Animals in Danger in Africa") &&
				student.isBookPresentByTitle("Animals in Danger in Asia") &&
				student.isBookPresentByTitle("Animals in Danger in Australia") &&
				student.isBookPresentByTitle("Animals in the Garden") &&
				student.isBookPresentByTitle("BIG Mouths") &&
				student.isBookPresentByTitle("BIG Predators"));
	}
	
	
	@Test
	public void testResultsReptiles(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Animals");
		vSearch.clickCategoryByName("Reptiles");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		student = vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Reptiles category is selected",
				student.isBookPresentByTitle("American Alligators") &&
				student.isBookPresentByTitle("Chameleons") &&
				student.isBookPresentByTitle("Cobras"));
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testResultsPets(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Animals");
		vSearch.clickCategoryByName("Pets");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		student = vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Pets category is selected",
				student.isBookPresentByTitle("All about Beagles") &&
				student.isBookPresentByTitle("All about Pit Bulls") &&
				student.isBookPresentByTitle("All about Poodles"));
	}
	
	@Test
	public void testResultsCraftsAndHowTo(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Hobbies and How-To");
		vSearch.clickCategoryByName("Crafts and How-To");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		student = vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Crafts and How To category is selected",
				student.isBookPresentByTitle("The Adventures of Hercules") &&
				student.isBookPresentByTitle("Animator: The Coolest Jobs on the Planet") &&
				student.isBookPresentByTitle("Nickolas Flux and the Salem Witch Trials"));
	}
	
	@Test
	public void testResultsDance(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Hobbies and How-To");
		vSearch.clickCategoryByName("Dance");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Dance category is selected", 
				student.isBookPresentByTitle("Ballet Dancing") &&
				student.isBookPresentByTitle("Hip-Hop Dancing") &&
				student.isBookPresentByTitle("Jazz Dancing"));
	}
	
	@Test
	public void testResultsFashion(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Hobbies and How-To");
		vSearch.clickCategoryByName("Fashion");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Fashion category is selected",
				student.isBookPresentByTitle("Taking Care of My Hair") &&
				student.isBookPresentByTitle("Taking Care of My Hands and Feet") &&
				student.isBookPresentByTitle("Taking Care of My Skin"));
	}
	
	@Test
	public void testResultsFoodAndDrink(){
		student.waitForElement(student.visualSearch);
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Hobbies and How-To");
		vSearch.clickCategoryByName("Food and Drink");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Food and Drink category is selected",
				student.isBookPresentByTitle("All Kinds of Gardens") &&
				student.isBookPresentByTitle("The Apple Orchard") &&
				student.isBookPresentByTitle("BIG Pets"));
	}
	
	@Test
	public void testResultsGamesAndActivities(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Hobbies and How-To");
		vSearch.clickCategoryByName("Games & Activities");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Games and Activities category is selected",
				student.isBookPresentByTitle("Gaming Safely") &&
				student.isBookPresentByTitle("The Electrifying, Action-Packed, Unusual History of Video Games"));
	}
	
	@Test
	public void testResultsMagicTricks(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Hobbies and How-To");
		vSearch.clickCategoryByName("Magic Tricks");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Magic Tricks category is selected",
				student.isBookPresentByTitle("Amazing Magic Tricks, Apprentice Level") &&
				student.isBookPresentByTitle("Amazing Magic Tricks, Beginner Level") &&
				student.isBookPresentByTitle("Amazing Magic Tricks, Expert Level"));
	}
	
	@Test
	public void testResultsMoviesAndTheater(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Hobbies and How-To");
		vSearch.clickCategoryByName("Movies & Theater");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Movies and Theater category is selected",
				student.isBookPresentByTitle("A Daredevil's Guide to Stunts") &&
				student.isBookPresentByTitle("Epic Stunts") &&
				student.isBookPresentByTitle("Mind-Blowing Movie Stunts"));
	}
	
	@Test
	public void testMusic(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Hobbies and How-To");
		vSearch.clickCategoryByName("Music");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Music category is selected",
				student.isBookPresentByTitle("Jay-Z") &&
				student.isBookPresentByTitle("Jay-Z: Hip-Hop Icon") &&
				student.isBookPresentByTitle("Justin Bieber"));
	}
	
	@Test
	public void testResultsPicturePuzzles(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Hobbies and How-To");
		vSearch.clickCategoryByName("Picture Puzzles");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Picture Puzzles category is selected",
				student.isBookPresentByTitle("Mean Machines: A Spot-It Challenge") &&
				student.isBookPresentByTitle("School Times: A Spot-It Challenge") &&
				student.isBookPresentByTitle("Season Search: A Spot-It Challenge"));
	}
	
	@Test
	public void testResultsFairyTales(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Fairy Tales, Myths & Folklore");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Fairy Tales category is selected",
				student.isBookPresentByTitle("A Field Guide to Dragons, Trolls, and Other Dangerous Monsters") &&
				student.isBookPresentByTitle("A Field Guide to Elves, Dwarves, and Other Magical Folk") &&
				student.isBookPresentByTitle("A Field Guide to Griffins, Unicorns, and Other Mythical Beasts") &&
				student.isBookPresentByTitle("African Myths and Legends") &&
				student.isBookPresentByTitle("American Indian Stories and Legends") &&
				student.isBookPresentByTitle("The Goose that Laid the Golden Egg: A Retelling of Aesop's Fable") &&
				student.isBookPresentByTitle("The Lion and the Mouse: A Retelling of Aesop's Fable"));
	}
	
	@Test
	public void testResultsFunFacts(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Fun Facts & Trivia");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Fun Facts category is selected",
				student.isBookPresentByTitle("The Most Disgusting Places on the Planet") &&
				student.isBookPresentByTitle("The World's Craziest Records") &&
				student.isBookPresentByTitle("The World's Tallest House of Cards and Other Number Records"));
	}
	
	@Test
	public void testResultsGhostsAndUnexplained(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Ghosts & Unexplained");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Ghost and Unexplained category is selected",
				student.isBookPresentByTitle("Ghost Hunters") &&
				student.isBookPresentByTitle("Ghostly Encounters") &&
				student.isBookPresentByTitle("Ghosts: The Unsolved Mystery"));
	}
	
	@Test
	public void testResultsAncientWorld(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("History & Geography");
		vSearch.clickCategoryByName("Ancient World");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Ancient World category is selected",
				student.isBookPresentByTitle("Ancient China: Beyond the Great Wall") &&
				student.isBookPresentByTitle("Ancient Egypt: Beyond the Pyramids") &&
				student.isBookPresentByTitle("Ancient Greece: Birthplace of Democracy"));
	}
	
	@Test
	public void testResultsGeography(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("History & Geography");
		vSearch.clickCategoryByName("Geography");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Geography category is selected",
				student.isBookPresentByTitle("Afghanistan") &&
				student.isBookPresentByTitle("Canada") &&
				student.isBookPresentByTitle("China"));
	}
	
	@Test
	public void testResultsGeneralHistory(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("History & Geography");
		vSearch.clickCategoryByName("U.S. History");
		vSearch.clickCategoryByName("General History");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the General History category is selected",
				student.isBookPresentByTitle("Angel Island") &&
				student.isBookPresentByTitle("Apache Resistance: Causes and Effects of Geronimo's Campaign") &&
				student.isBookPresentByTitle("Pocahontas"));
	}
	
	@Test
	public void testResultsColonialAmerica(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("History & Geography");
		vSearch.clickCategoryByName("U.S. History");
		vSearch.clickCategoryByName("Colonial America");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Colonial America category is selected",
				student.isBookPresentByTitle("A Primary Source History of U.S. Independence") &&
				student.isBookPresentByTitle("The Real Story About Government and Politics in Colonial America") &&
				student.isBookPresentByTitle("The Real Story on the Weapons and Battles of Colonial America"));
	}
	
	@Test
	public void testResultsRevolutionaryWar(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("History & Geography");
		vSearch.clickCategoryByName("U.S. History");
		vSearch.clickCategoryByName("Revolutionary War Era");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Revolutionary War category is selected",
				student.isBookPresentByTitle("The Declaration of Independence in Translation: What It Really Means") &&
				student.isBookPresentByTitle("The Dreadful, Smelly Colonies: The Disgusting Details about Life in Colonial America") &&
				student.isBookPresentByTitle("For Life and Liberty"));
	}
	
	@Test
	public void testResultsPostRevolutionaryWar(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("History & Geography");
		vSearch.clickCategoryByName("U.S. History");
		vSearch.clickCategoryByName("Post-Revolutionary War");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Post Revolutionary War category is selected",
				student.isBookPresentByTitle("Abigail Adams") &&
				student.isBookPresentByTitle("George Washington") &&
				student.isBookPresentByTitle("Martha Washington"));
	}
	
	@Test
	public void testResultsUSCivilWarEra(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("History & Geography");
		vSearch.clickCategoryByName("U.S. History");
		vSearch.clickCategoryByName("U.S. Civil War Era");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the US Civil War category is selected",
				student.isBookPresentByTitle("Great Women of the Civil War") &&
				student.isBookPresentByTitle("Heroes of the Civil War") &&
				student.isBookPresentByTitle("The Biggest Battles of the Civil War"));
	}
	
	@Test
	public void testResultsPostUSCivilWar(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("History & Geography");
		vSearch.clickCategoryByName("U.S. History");
		vSearch.clickCategoryByName("Post-U.S. Civil War");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Post US Civil War category is selected",
				student.isBookPresentByTitle("Frederick Douglass") &&
				student.isBookPresentByTitle("Last Battle: Causes and Effects of the Massacre at Wounded Knee") &&
				student.isBookPresentByTitle("Last Stand: Causes and Effects of the Battle of the Little Bighorn"));
	}
	
	@Test
	public void testResultsModernUSHistory(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("History & Geography");
		vSearch.clickCategoryByName("U.S. History");
		vSearch.clickCategoryByName("Modern U.S. History");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Modern US History category is selected",
				student.isBookPresentByTitle("George W. Bush") &&
				student.isBookPresentByTitle("Hillary Clinton") &&
				student.isBookPresentByTitle("John F. Kennedy"));
	}
	
	@Test
	public void testResultsWorldHistory(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("History & Geography");
		vSearch.clickCategoryByName("World History");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();

		assertTrue("Asserts the correct books are returned when the World History category is selected",
				student.isBookPresentByTitle("Afghanistan") &&
				student.isBookPresentByTitle("China"));
	}
	
	@Test
	public void testResultsInTheLibrary(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("In The Library");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the In the Library category is selected",
				student.isBookPresentByTitle("Let's Go to the Library") &&
				student.isBookPresentByTitle("Librarians Help") &&
				student.isBookPresentByTitle("A Visit to the Library"));
	}
	
	@Test
	public void testResultsLanguageArts(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Language Arts");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Language Arts category is selected",
				student.isBookPresentByTitle("A Backpack Full of Verbs") &&
				student.isBookPresentByTitle("A Big Box of Bananas") &&
				student.isBookPresentByTitle("A Hat Full of Adjectives") &&
				student.isBookPresentByTitle("A Pocket Full of Nouns") &&
				student.isBookPresentByTitle("Football ABC") &&
				student.isBookPresentByTitle("Football Colors") &&
				student.isBookPresentByTitle("Football Opposites"));
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testResultsMath(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Math");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Math category is selected",
				student.isBookPresentByTitle("10 Little Kittens") &&
				student.isBookPresentByTitle("2, 4, Skip Count Some More") &&
				student.isBookPresentByTitle("3, 2, 1, Go!: A Transportation Countdown") &&
				student.isBookPresentByTitle("A Math Hike") &&
				student.isBookPresentByTitle("It's a Shape!") &&
				student.isBookPresentByTitle("Ovals") &&
				student.isBookPresentByTitle("Stars"));
	}
	
	@Test
	public void testResultsMilitary(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Military");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();

		assertTrue("Asserts the correct books are returned when the Military category is selected",
				student.isBookPresentByTitle("If I Were the President") &&
				student.isBookPresentByTitle("Ninja") &&
				student.isBookPresentByTitle("Ninjas: A Guide to the Ancient Assassins"));
	}
	
	@Test
	public void testResultsEarthScience(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Science & Technology");
		vSearch.clickCategoryByName("Earth Science");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Earth Science category is selected",
				student.isBookPresentByTitle("A Raindrop's Journey") &&
				student.isBookPresentByTitle("Avalanches") &&
				student.isBookPresentByTitle("Canyons"));
	}
	
	@Test
	public void testResultsGeneralScience(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Science & Technology");
		vSearch.clickCategoryByName("General Science");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the General Science category is selected",
				student.isBookPresentByTitle("A Windy Day in Spring") &&
				student.isBookPresentByTitle("Exploring Fall") &&
				student.isBookPresentByTitle("Exploring Spring"));
	}
	
	@Test
	public void testResultsLifeScience(){
		student.waitForElement(student.visualSearch);
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Science & Technology");
		vSearch.clickCategoryByName("Life Science");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Life Science category is selected",
				student.isBookPresentByTitle("All Kinds of Gardens") &&
				student.isBookPresentByTitle("Chameleons") &&
				student.isBookPresentByTitle("Salamanders"));
	}
	
	@Test
	public void testResultsPhysicalScience(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Science & Technology");
		vSearch.clickCategoryByName("Physical Science");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Physical Science category is selected",
				student.isBookPresentByTitle("101 Things You Didn't Know About Your Body") &&
				student.isBookPresentByTitle("101 Ways to Get in Shape") &&
				student.isBookPresentByTitle("A Balanced Diet") &&
				student.isBookPresentByTitle("Airplanes") &&
				student.isBookPresentByTitle("All about Matter") &&
				student.isBookPresentByTitle("Anatomy of a Pandemic") &&
				student.isBookPresentByTitle("Animals Big and Small"));
	}
	
	@Test
	public void testResultsPlants(){
		student.waitForElement(student.visualSearch);
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Science & Technology");
		vSearch.clickCategoryByName("Plants");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Plants category is selected",
				student.isBookPresentByTitle("Green and Growing: A Book About Plants") &&
				student.isBookPresentByTitle("Meat-Eating Plants and Other Extreme Plant Life") &&
				student.isBookPresentByTitle("Plants in Spring"));
	}
	
	@Test
	public void testResultsSpace(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Science & Technology");
		vSearch.clickCategoryByName("Space");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Space category is selected",
				student.isBookPresentByTitle("A Raindrop's Journey") &&
				student.isBookPresentByTitle("A World After an Asteroid Strike") &&
				student.isBookPresentByTitle("A World After Fossil Fuels") &&
				student.isBookPresentByTitle("A World After Nuclear Disaster") &&
				student.isBookPresentByTitle("Avalanches") &&
				student.isBookPresentByTitle("Canyons") &&
				student.isBookPresentByTitle("Exploring Fall") &&
				student.isBookPresentByTitle("Exploring Spring") &&
				student.isBookPresentByTitle("Plants in Spring"));
	}
	
	@Test
	public void testResultsEngineering(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Science & Technology");
		vSearch.clickCategoryByName("Engineering");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
				
		assertTrue("Asserts the correct books are returned when the Engineering category is selected",
				student.isBookPresentByTitle("3, 2, 1, Go!: A Transportation Countdown") &&
				student.isBookPresentByTitle("Airplanes") &&
				student.isBookPresentByTitle("Amazing Military Robots") &&
				student.isBookPresentByTitle("Amelia Earhart") &&
				student.isBookPresentByTitle("Animal Robots"));
	}
	
	@Test
	public void testResultsTechnology(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Science & Technology");
		vSearch.clickCategoryByName("Technology");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Technology category is selected",
				student.isBookPresentByTitle("Engineering a Totally Rad Skateboard with Max Axiom, Super Scientist") &&
				student.isBookPresentByTitle("Johann Gutenberg and the Printing Press") &&
				student.isBookPresentByTitle("Mark Zuckerberg"));
	}
	
	@Test
	public void testResultsShapes(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Shapes");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Shapes category is selected",
				student.isBookPresentByTitle("It's a Shape!") &&
				student.isBookPresentByTitle("Ovals") &&
				student.isBookPresentByTitle("Stars"));
	}
	
	@Test
	public void testResultsCommunicationAndTransportation(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Social Studies");
		vSearch.clickCategoryByName("Communication and Transportation");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
				
		assertTrue("Asserts the correct books are returned when the Communication and Transportation category is selected",
				student.isBookPresentByTitle("The Airport") &&
				student.isBookPresentByTitle("An Airport Field Trip") &&
				student.isBookPresentByTitle("Breaking Secret Codes"));
	}
	
	@Test
	public void testResultsCustomsAndCultures(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Social Studies");
		vSearch.clickCategoryByName("Customs and Culture");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Customes and Cultures category is selected",
				student.isBookPresentByTitle("A Short History of Christmas") &&
				student.isBookPresentByTitle("A Short History of Halloween") &&
				student.isBookPresentByTitle("A Short History of Thanksgiving"));
	}
	
	@Test
	public void testResultsFairyTalesAndFolkTales(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Social Studies");
		vSearch.clickCategoryByName("Fairy Tales and Folk Tales");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Fairy Tales and Folk Tales category is selected",
				student.isBookPresentByTitle("A Field Guide to Dragons, Trolls, and Other Dangerous Monsters") &&
				student.isBookPresentByTitle("A Field Guide to Elves, Dwarves, and Other Magical Folk") &&
				student.isBookPresentByTitle("A Field Guide to Griffins, Unicorns, and Other Mythical Beasts") &&
				student.isBookPresentByTitle("African Myths and Legends") &&
				student.isBookPresentByTitle("American Indian Stories and Legends") &&
				student.isBookPresentByTitle("The Goose that Laid the Golden Egg: A Retelling of Aesop's Fable") &&
				student.isBookPresentByTitle("The Lion and the Mouse: A Retelling of Aesop's Fable"));
	}
	
	@Test
	public void testResultsGovernment(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Social Studies");
		vSearch.clickCategoryByName("Government");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Government category is selected",
				student.isBookPresentByTitle("Kings and Queens") &&
				student.isBookPresentByTitle("Learning About Privacy") &&
				student.isBookPresentByTitle("Rosa Parks"));
	}
	
	@Test
	public void testResultsLaws(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Social Studies");
		vSearch.clickCategoryByName("Laws");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Laws category is selected",
				student.isBookPresentByTitle("The Bill of Rights in Translation: What It Really Means") &&
				student.isBookPresentByTitle("The Civil Rights Act of 1964: A Primary Source Exploration of the Landmark Legislation") &&
				student.isBookPresentByTitle("Thurgood Marshall"));
	}
	
	@Test
	public void testResultsMediaAndLiteracy(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Social Studies");
		vSearch.clickCategoryByName("Media and Literacy");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Media and Literacy category is selected",
				student.isBookPresentByTitle("Learning About Ads") &&
				student.isBookPresentByTitle("Rupert Murdoch") &&
				student.isBookPresentByTitle("The Kids' Guide to Sports Media"));
	}
	
	@Test
	public void testResultsMoney(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Social Studies");
		vSearch.clickCategoryByName("Money");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Money category is selected",
				student.isBookPresentByTitle("All About the Green: The Teens' Guide to Finding Work and Making Money") &&
				student.isBookPresentByTitle("Sam Walton") &&
				student.isBookPresentByTitle("The Supermarket"));
	}
	
	@Test
	public void testResultsSocialScience(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Social Studies");
		vSearch.clickCategoryByName("Social Science");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));;
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Social Science category is selected",
				student.isBookPresentByTitle("A World After an Asteroid Strike") &&
				student.isBookPresentByTitle("A World After Nuclear Disaster") &&
				student.isBookPresentByTitle("Adoptive Families") &&
				student.isBookPresentByTitle("Aunts: Revised Edition") &&
				student.isBookPresentByTitle("Blended Families"));
	}
	
	@Test
	public void testResultsReligionsAroundTheWorld(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Social Studies");
		vSearch.clickCategoryByName("Religions Around the World");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Religions Around the World category is selected",
				student.isBookPresentByTitle("Ancient Egyptian Gods and Goddesses") &&
				student.isBookPresentByTitle("Ancient Egyptian Myths") &&
				student.isBookPresentByTitle("Isis and Osiris"));
	}
	
	@Test
	public void testResultsFootball(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Sports");
		vSearch.clickCategoryByName("Football");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Football category is selected",
				student.isBookPresentByTitle("Aaron Rodgers") &&
				student.isBookPresentByTitle("Football Opposites") &&
				student.isBookPresentByTitle("Stars of Football"));
	}
	
	@Test
	public void testResultsHockey(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Sports");
		vSearch.clickCategoryByName("Hockey");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Hockey category is selected",
				student.isBookPresentByTitle("Jonathan Toews") &&
				student.isBookPresentByTitle("Jonathan Toews: Hockey Superstar"));
	}
	
	@Test
	public void testResultsMartialArts(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Sports");
		vSearch.clickCategoryByName("Martial Arts");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Martial Arts category is selected",
				student.isBookPresentByTitle("MMA Greats") &&
				student.isBookPresentByTitle("Muay Thai") &&
				student.isBookPresentByTitle("The Secrets of Martial Arts: An Isabel Soto History Adventure"));
	}
	
	@Test
	public void testResultsMonsterTrucks(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Sports");
		vSearch.clickCategoryByName("Monster Trucks and Racing");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Monster Trucks category is selected",
				student.isBookPresentByTitle("Drag Racing") &&
				student.isBookPresentByTitle("Go-Kart Racing") &&
				student.isBookPresentByTitle("Indy Car Racing"));
	}
	
	@Test
	public void testResultsOtherSports(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Sports");
		vSearch.clickCategoryByName("Other Sports");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Other Sports category is selected",
				student.isBookPresentByTitle("8-Bit Baseball") &&
				student.isBookPresentByTitle("Football ABC") &&
				student.isBookPresentByTitle("Football Colors") &&
				student.isBookPresentByTitle("Sports Rules"));
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testResultsOutdoorSports(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Sports");
		vSearch.clickCategoryByName("Outdoor Sports");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Outdoor Sports category is selected",
				student.isBookPresentByTitle("Bear Hunting for Kids") &&
				student.isBookPresentByTitle("Camping for Kids") &&
				student.isBookPresentByTitle("Deer Hunting for Kids"));
	}
	
	@Test
	public void testResultsSoccer(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Sports");
		vSearch.clickCategoryByName("Soccer");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Soccer category is selected",
				student.isBookPresentByTitle("A Girl's Guide to Soccer") &&
				student.isBookPresentByTitle("Score!: You Can Play Soccer"));
	}
	
	@Test
	public void testResultsWrestling(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Sports");
		vSearch.clickCategoryByName("Wrestling");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Wrestling category is selected",
				student.isBookPresentByTitle("CM Punk: Pro Wrestling Superstar") &&
				student.isBookPresentByTitle("John Cena: Pro Wrestling Superstar") &&
				student.isBookPresentByTitle("Rey Mysterio: Pro Wrestling Superstar"));
	}
	
	@Test
	public void testResultsGraphicNonfiction(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Graphic Nonfiction");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Graphic Nonfiction category is selected",
				student.isBookPresentByTitle("The 1918 Flu Pandemic") &&
				student.isBookPresentByTitle("A Tour of Your Circulatory System") &&
				student.isBookPresentByTitle("A Tour of Your Digestive System") &&
				student.isBookPresentByTitle("Nickolas Flux and the Salem Witch Trials") &&
				student.isBookPresentByTitle("Engineering a Totally Rad Skateboard with Max Axiom, Super Scientist") &&
				student.isBookPresentByTitle("Jay-Z: Hip-Hop Icon") &&
				student.isBookPresentByTitle("Johann Gutenberg and the Printing Press") &&
				student.isBookPresentByTitle("The Secrets of Martial Arts: An Isabel Soto History Adventure") &&
				student.isBookPresentByTitle("A Visit to the Library"));
	}
	
	@Test
	public void testResultsAllFiction(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Fiction");
		vSearch.clickCategoryByName("All Fiction");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the All Fiction category is selected",
				student.isBookPresentByTitle("A Big Box of Bananas") &&
				student.isBookPresentByTitle("A Jar of Eyeballs") &&
				student.isBookPresentByTitle("The Adventures of Hercules") &&
				student.isBookPresentByTitle("Bess and Tess") &&
				student.isBookPresentByTitle("The Book That Ate My Brother") &&
				student.isBookPresentByTitle("The Book That Dripped Blood") &&
				student.isBookPresentByTitle("Captain Cold and the Blizzard Battle") &&
				student.isBookPresentByTitle("Cat Commander") &&
				student.isBookPresentByTitle("Cave of the Bookworms") &&
				student.isBookPresentByTitle("Cheetah and the Purrfect Crime") &&
				student.isBookPresentByTitle("Duel of Dragons") &&
				student.isBookPresentByTitle("Fangs in the Mirror") &&
				student.isBookPresentByTitle("The Goose that Laid the Golden Egg: A Retelling of Aesop's Fable") &&
				student.isBookPresentByTitle("The Lion and the Mouse: A Retelling of Aesop's Fable") &&
				student.isBookPresentByTitle("Monster Hunter"));
	}
	
	@Test
	public void testResultsFables(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Fiction");
		vSearch.clickCategoryByName("Fables");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Fables category is selected",
				student.isBookPresentByTitle("The Goose that Laid the Golden Egg: A Retelling of Aesop's Fable") &&
				student.isBookPresentByTitle("The Lion and the Mouse: A Retelling of Aesop's Fable") &&
				student.isBookPresentByTitle("No Lie, I Acted Like a Beast!: The Story of Beauty and the Beast as Told by the Beast"));
	}
	
	@Test
	public void testResultsFolklore(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Fiction");
		vSearch.clickCategoryByName("Folklore");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Folklore category is selected",
				student.isBookPresentByTitle("20,000 Leguas de Viaje Submarino") &&
				student.isBookPresentByTitle("The Adventures of Hercules") &&
				student.isBookPresentByTitle("African Myths and Legends") &&
				student.isBookPresentByTitle("American Indian Stories and Legends") &&
				student.isBookPresentByTitle("Believe Me, Goldilocks Rocks!: The Story of the Three Bears as Told by Baby Bear"));
	}
	
	@Test
	public void testResultsScaryStories(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Fiction");
		vSearch.clickCategoryByName("Scary Stories");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Scary Stories category is selected",
				student.isBookPresentByTitle("20,000 Leguas de Viaje Submarino") &&
				student.isBookPresentByTitle("A Jar of Eyeballs") &&
				student.isBookPresentByTitle("The Book That Ate My Brother") &&
				student.isBookPresentByTitle("The Book That Dripped Blood") &&
				student.isBookPresentByTitle("Cave of the Bookworms") &&
				student.isBookPresentByTitle("Duel of Dragons") &&
				student.isBookPresentByTitle("Fangs in the Mirror") &&
				student.isBookPresentByTitle("Monster Hunter"));
	}
	
	@Test
	public void testResultsScienceFiction(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Fiction");
		vSearch.clickCategoryByName("Science Fiction");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Science Fiction category is selected",
				student.isBookPresentByTitle("Duel of Dragons") &&
				student.isBookPresentByTitle("Fangs in the Mirror") &&
				student.isBookPresentByTitle("Monster Hunter"));
	}
	
	@Test
	public void testResultsAdventure(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Fiction");
		vSearch.clickCategoryByName("Adventure");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Adventure category is selected",
				student.isBookPresentByTitle("20,000 Leguas de Viaje Submarino") &&
				student.isBookPresentByTitle("A Daredevil's Guide to Stunts") &&
				student.isBookPresentByTitle("The Book That Ate My Brother") &&
				student.isBookPresentByTitle("The Book That Dripped Blood") &&
				student.isBookPresentByTitle("Captain Cold and the Blizzard Battle") &&
				student.isBookPresentByTitle("Cat Commander") &&
				student.isBookPresentByTitle("Cave of the Bookworms") &&
				student.isBookPresentByTitle("Cheetah and the Purrfect Crime") &&
				student.isBookPresentByTitle("Duel of Dragons") &&
				student.isBookPresentByTitle("Fangs in the Mirror") &&
				student.isBookPresentByTitle("Monster Hunter") &&
				student.isBookPresentByTitle("Superman: A Word Adventure!"));
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testResultsGraphicNovels(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Graphic Novels");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
				
		assertTrue("Asserts the correct books are returned when the Graphic Novels category is selected",
				student.isBookPresentByTitle("The 1918 Flu Pandemic") &&
				student.isBookPresentByTitle("20,000 Leguas de Viaje Submarino") &&
				student.isBookPresentByTitle("8-Bit Baseball") &&
				student.isBookPresentByTitle("A Tour of Your Circulatory System") &&
				student.isBookPresentByTitle("A Tour of Your Digestive System") &&
				student.isBookPresentByTitle("The Adventures of Hercules") &&
				student.isBookPresentByTitle("Nickolas Flux and the Salem Witch Trials") &&
				student.isBookPresentByTitle("Captain Cold and the Blizzard Battle") &&
				student.isBookPresentByTitle("Cheetah and the Purrfect Crime") &&
				student.isBookPresentByTitle("Engineering a Totally Rad Skateboard with Max Axiom, Super Scientist") &&
				student.isBookPresentByTitle("Jay-Z: Hip-Hop Icon") &&
				student.isBookPresentByTitle("Johann Gutenberg and the Printing Press") &&
				student.isBookPresentByTitle("The Secrets of Martial Arts: An Isabel Soto History Adventure") &&
				student.isBookPresentByTitle("A Visit to the Library"));
	}
	
	@Test
	public void testResultsRecreationalReading(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Recreational Reading");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct amount of books and certain books are returned when the Recreational Reading category is selected",
				(student.getResults() == 50) &&
				student.isBookPresentByTitle("A Germ's Journey") &&
				student.isBookPresentByTitle("Football Opposites"));
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testResultsFavoriteCharacters(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Favorite Characters");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Favorite Characters category is selected",
				student.isBookPresentByTitle("Captain Cold and the Blizzard Battle") &&
				student.isBookPresentByTitle("Cat Commander") &&
				student.isBookPresentByTitle("Cheetah and the Purrfect Crime") &&
				student.isBookPresentByTitle("No Lie, I Acted Like a Beast!: The Story of Beauty and the Beast as Told by the Beast"));
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testResultsDontDisappear(){
		VisualSearchMain vSearch = student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Fairy Tales, Myths & Folklore");
		user.customWait().until(ExpectedConditions.elementToBeClickable(vSearch.searchButton));
		vSearch.clickSearchButton();
		
		assertTrue("Asserts the correct books are returned when the Fairy Tales category is selected",
				student.isBookPresentByTitle("A Field Guide to Dragons, Trolls, and Other Dangerous Monsters") &&
				student.isBookPresentByTitle("A Field Guide to Elves, Dwarves, and Other Magical Folk") &&
				student.isBookPresentByTitle("A Field Guide to Griffins, Unicorns, and Other Mythical Beasts") &&
				student.isBookPresentByTitle("African Myths and Legends") &&
				student.isBookPresentByTitle("American Indian Stories and Legends") &&
				student.isBookPresentByTitle("The Goose that Laid the Golden Egg: A Retelling of Aesop's Fable") &&
				student.isBookPresentByTitle("The Lion and the Mouse: A Retelling of Aesop's Fable"));
		
		student.clickVisualSearch();
		vSearch.clickCategoryByName("Nonfiction");
		vSearch.clickCategoryByName("Fairy Tales, Myths & Folklore");
		vSearch.waitImplicitly(2);
		vSearch.clickSearchButton();
		student.waitImplicitly(1);
		
		assertTrue("Asserts the same books are returned when the Fairy Tales category is selected again",
				student.isBookPresentByTitle("A Field Guide to Dragons, Trolls, and Other Dangerous Monsters") &&
				student.isBookPresentByTitle("A Field Guide to Elves, Dwarves, and Other Magical Folk") &&
				student.isBookPresentByTitle("A Field Guide to Griffins, Unicorns, and Other Mythical Beasts") &&
				student.isBookPresentByTitle("African Myths and Legends") &&
				student.isBookPresentByTitle("American Indian Stories and Legends") &&
				student.isBookPresentByTitle("The Goose that Laid the Golden Egg: A Retelling of Aesop's Fable") &&
				student.isBookPresentByTitle("The Lion and the Mouse: A Retelling of Aesop's Fable"));
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testVisualSearchButtonShrinks(){
		student.resizeWindow(1000, 1300);
		
		assertTrue("Asserts that the visual search text on its button is present",
				student.isElementPresent(student.visualSearchText));
		
		student.resizeWindow(1000, 1200);
		student.resizeWindow(1000, 1150);
		student.resizeWindow(1000, 1100);
		
		assertFalse("Asserts that the visual search text on its button is not present after the screen size shrinks",
				student.isElementPresent(student.visualSearchText));
	}
	
}
