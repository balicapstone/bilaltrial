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

import BookTools.BookModal;
import CITests.CIPages.CILoginPage;
import CITests.CIPages.StudentHomePage;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.User;
import UserClasses.UserInfo;

public class SearchTests {
	public static CILoginPage login;
	public static StudentHomePage student;
	public static User user;
	
	@BeforeClass
	public static void executeBefore(){
		try{
			user =  new User(UserInfo.VISUALSEARCH);
			login = new CILoginPage(user);
			student = (StudentHomePage) login.login(user);
			user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		}catch(Exception e){
			System.out.println("Failure to set up Search Tests");
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
	public Retry retry = new Retry(3);
	
	@Test
	@Category(SmokeTests.class)
	public void testSeries(){
		String series = "Fantasy Field Guides";
		
		student.search(series);
		assertTrue("Assert that all books in the Let's Take a Field Trip series are returned after a search",
				student.getResults() == 3);
		
		BookModal book;
		
		for(int i = 2; i <= 4; i++){
			book = student.clickBookByIndex(i);
			user.customWait().until(ExpectedConditions.elementToBeClickable(book.bookInfoTab));
			
			book.click(book.bookInfoTab);
			user.customWait().until(ExpectedConditions.attributeContains(book.bookInfoTab, "class", "active"));
			book.getBookInformationFromTextFields();
			assertTrue("Asserts that all books from search are from the Let's Take a Field Trip series",
					book.seriesText.equals(series));
			book.click(book.closeModalButton);
			user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(book.closeModalButton));
		}
		student.clearField(student.searchTextField);
		student.click(student.viewAllBooksButton);
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testWordinDescription(){
		String word = "consequences";
		
		student.search(word);
		user.customWait().until(ExpectedConditions.textToBe(student.bookResults, "3 book results"));
		
		assertTrue("Asserts the word consequences only returns three books",
				student.getResults() == 3);
	
		BookModal book;
		
		for(int i = 2; i <= 4; i++){
			book = student.clickBookByIndex(i);
			user.customWait().until(ExpectedConditions.elementToBeClickable(book.bookInfoTab));
			
			assertTrue("Asserts that all books from search are from the Let's Take a Field Trip series",
					book.getDriver().findElement(book.description).getText().contains(word));
			book.click(book.closeModalButton);
			user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(book.closeModalButton));
		}
		student.clearField(student.searchTextField);
		student.click(student.viewAllBooksButton);
	}
	
	@Test
	public void testWholeDesciptionMultiple(){
		String description = "Amaze your friends with these fun, easy magic tricks. Step-by-step instructions teach astounding tricks like how to make a coin disappear, restore a cut rope, and read minds! Clear photos of each trick will have your audience stumped faster than you can say abracadabra!";
		
		student.search(description);
		user.customWait().until(ExpectedConditions.textToBe(student.bookResults, "3 book results"));
		assertTrue("Asserts all books with the same description are returned after a search",
				student.getResults() == 3);
		BookModal book;
		
		//index for books starts at 2
		for(int i = 2; i <= 4; i++){
			book = student.clickBookByIndex(i);
			user.customWait().until(ExpectedConditions.elementToBeClickable(book.bookInfoTab));
			
			assertTrue("Asserts that all books returned have the same description",
					book.getBookDescription().equals(description));
			book.click(book.closeModalButton);
			user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(book.closeModalButton));
		}	
		student.clearField(student.searchTextField);
		student.click(student.viewAllBooksButton);
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testWholeDescriptionSpecific(){		
		String description = "This book gives 101 fun and fascinating facts about the human body.";
		
		student.search(description);
		user.customWait().until(ExpectedConditions.textToBe(student.bookResults, "1 book results"));
		
		assertTrue("Asserts that a book with a unique description is the only book returned when the whole description is searched",
				student.getResults() == 1);
		BookModal book = student.clickBookByTitle("101 Things You Didn't Know About Your Body");
		user.customWait().until(ExpectedConditions.elementToBeClickable(book.bookInfoTab));
		
		assertTrue("Asserts that the book description of the book returned by the search is the same as the searched description",
				book.getBookDescription().equals(description));
		
		book.click(book.closeModalButton);
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(book.closeModalButton));
		student.clearField(student.searchTextField);
		student.click(student.viewAllBooksButton);
	}
	
	@Test
	@Category(SmokeTests.class)
	public void testSameWordsDifferentResults(){
		String all = "All";
		String about = "about";
		String beagles = "Beagles";
		
		student.search(all);
		user.customWait().until(ExpectedConditions.textToBe(student.bookResults, "75 book results"));
		assertTrue("Asserts that the correct amount of books are returned when 'All' is searched",
				student.getResults() == 75);
		
		student.clearField(student.searchTextField);
		
		student.search(all + " " + about);
		user.customWait().until(ExpectedConditions.textToBe(student.bookResults, "13 book results"));
		assertTrue("Asserts that the less books are returned when 'All about' is searched rather than 'math",
				student.getResults() == 13);
		
		student.clearField(student.searchTextField);
		
		student.search(all + " " + about + " " + beagles);
		user.customWait().until(ExpectedConditions.textToBe(student.bookResults, "1 book results"));
		assertTrue("Asserts that the correct amount of books are returned when 'All about Beagles' is searched",
				student.getResults() == 1);
		BookModal book = student.clickBookByTitle("All about Beagles");
		
		user.customWait().until(ExpectedConditions.elementToBeClickable(book.bookInfoTab));
		assertTrue("Asserts that 'All about Beagles' is returned when the phrase is searched",
				book.getTitleHeader().equals("All about Beagles"));
		book.click(book.closeModalButton);
		student.clearField(student.searchTextField);
		student.click(student.viewAllBooksButton);
	}
	
	@Test
	public void testStarsOfFootball(){
		student.search("stars");
		assertTrue("Asserts that the correct amount of books are returned when 'stars' is searched", student.getResults() == 6);
		student.clearField(student.searchTextField);
		
		student.search("stars of football");
		user.customWait().until(ExpectedConditions.textToBe(student.bookResults, "1 book results"));
		assertTrue("Asserts that the correct amount of books are returned when 'money in' is searched",
				student.getResults() == 1);
		
		BookModal book = student.clickBookByTitle("Stars of Football");
		user.customWait().until(ExpectedConditions.elementToBeClickable(book.bookInfoTab));
		
		assertTrue("Asserts that 'Stars of Football' is returned when the phrase is searched",
				book.getTitleHeader().equals("Stars of Football"));
		book.click(book.closeModalButton);
		student.clearField(student.searchTextField);
		student.click(student.viewAllBooksButton);
	}
	
	@Test
	public void testAllBooksA(){
		student.search("a");
		user.customWait().until(ExpectedConditions.textToBe(student.bookResults, "218 book results"));
		assertTrue("Tests that all books are returned when 'a' is searched",
				student.getResults() == 218);
		student.clearField(student.searchTextField);
		student.click(student.viewAllBooksButton);
	}
}
