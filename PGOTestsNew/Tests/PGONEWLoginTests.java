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
import AdminResources.TeacherHomeScreen;
import PGOPagesNew.PGONewLoginPage;
import PGOStudentResources.StudentHomePageNew;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.ProductType;
import UserClasses.User;
import UserClasses.UserInfo;

/**
 * Unit test for simple App.
 * Working
 */
public class PGONEWLoginTests {
	static PGONewLoginPage loginPage;
	static User user;

	@BeforeClass
	public static void executeBefore() {
		user = new User(UserInfo.GARAGESTUDENT);

		try {
			loginPage = new PGONewLoginPage(user);
			user.customWait()
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.footerMenu.privacyPolicyLink));
		} catch (Exception e) {
			user.getWatcher().TakeScreenshot("FailureToRunExecuteBeforeForLoginTests");
		}
	}

	@AfterClass
	public static void executeAfter() {
		loginPage.closeCurrentWindow();
	}

	@Rule
	public TestWatcher restart = new TestWatcher() {
		@Override
		public void failed(Throwable e, Description description) {
			try {
				user.getWatcher().TakeScreenshot(description.getDisplayName());
			} catch (Exception t) {
				System.out.print(
						"There was a failure while trying to take a screenshot for " + description.getDisplayName());
			}

			user.quit();
			executeBefore();
		}
	};

	/*
	 * @Rule public Retry retry = new Retry(1);
	 */

	@Test
	@Category(SmokeTests.class)
	public void testStudentCredentials() {

		System.out.println("test");
		StudentHomePageNew student = (StudentHomePageNew) loginPage.Login(user.setCredentials(UserInfo.GARAGESTUDENT));
		student.waitImplicitly(10);

		assertTrue(false);
		
		assertTrue("Tests that a student can log in ",
				student.verifyElementsVisibility(student.getInitialVisibleElements()));

		assertTrue("Tests that PGO items are visible ",
				student.verifyElementsVisibility(student.getInitialPGOElements()));

		// GO to PGO Next tab
		student.clickPebbleGoNextTab();
		student.waitImplicitly(10);
		assertTrue("Tests that PGO Next items are visible ",
				student.verifyElementsVisibility(student.getInitialPGONextElements()));

		loginPage = student.clickLogOutButton();

	}

}
