package Tests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;

import CMSPages.ArticlesActivePage;
import CMSPages.AssetPage;
import CMSPages.CMSHomePage;
import CMSPages.CMSLandingPage;
import CMSPages.CMSToolbar;
import CMSPages.EditQuestionOfTheDayModal;
import CMSPages.GlossarySearchPage;
import CMSPages.TemplatesPage;
import CMSPages.QuestionOfTheDayModal;
import CMSPages.QuestionsPage;
import PGNStudentResources.StatesDatabaseHome;
import PGOPages.LoginPage;
import PGOStudentResources.AnimalsContentPage;
import PGOStudentResources.BiographiesContentPage;
import PGOStudentResources.ContentSelectionPage;
import PGOStudentResources.PGONewStudentHomePage;
import PGOStudentResources.StudentHomePage;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;
import UserClasses.UserInfo;

public class CMSTests {
	public static User user = new User(UserInfo.GARAGESTUDENT);
	public static CMSHomePage home;
	public static PGONewStudentHomePage studentLogin;
	
	
	@BeforeClass
	public static void executeBefore(){
		CMSLandingPage cms = new CMSLandingPage(user.getDriver());
		cms.waitImplicitly(2);
		
		home = cms.login(user);
		home.waitImplicitly(10);
		
	}
	
	@AfterClass
	public static void executeAfter(){
		home.quit();
	}
	
	@Rule
	public TestWatcher restart = new TestWatcher(){
		@Override
		public void failed(Throwable e, Description description){
			user.getWatcher().TakeScreenshot(description.getDisplayName());			
			home.closeCurrentWindow();
			
			user.makeNewDriver();
			CMSLandingPage cms = new CMSLandingPage(user.getDriver());
			cms.waitImplicitly(5);
			
			home = cms.login(user);
			home.waitImplicitly(5);
		}
	};
	
	@Rule
	public Retry retry = new Retry(3);
	
	/*
	//@Test
	public void testUser(){
		//GlossarySearchPage glossary = home.toolbar.clickGlossarySearch();
		
		//QuestionsPage questions = glossary.toolbar.clickQuestions();
		
		//ModulesPage modules = questions.toolbar.clickModules();
		
		UploadExportPage upload = home.toolbar.clickUploadExport();
		upload.waitImplicitly(1);
		
		home.sendKeysToElement("//Users/whall/Downloads/2015.12_CI.xlsx", By.id("test-input-file"));
		
		upload.toolbar.clickLogo();
	}
	*/
	
	@Test
	public void testSearchFunctionArticles(){
		ArticlesActivePage articles = home.toolbar.clickArticles();
		articles.waitImplicitly(5);
		
		assertTrue("Assert that created Modules show up in the articles", 
				articles.getStringOfModuleByName("PGO Podcasts").equals("PGO Podcasts"));
		
		assertTrue("Asserts that a made up Module is not shown",
				!articles.isModulePresent("PGO Sandwiches"));
		
		home = articles.toolbar.clickLogo();
	}
	
	
	@Test
	public void testCanTurnOffPGOArticle(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(2);
		
		ArticlesActivePage articles = toolbar.clickArticles();
		articles.waitImplicitly(2);
		
		articles.searchForArticle("Frogs");
		articles.waitImplicitly(5);
		
		if(articles.isArticleActive("Frogs")){
			articles.toggleArticleByName("Frogs");
		}

		articles.waitImplicitly(3);
		
		User student = new User(UserInfo.GARAGESTUDENT);
		LoginPage studentLogin = new LoginPage(student, "editorialqa");
		
		StudentHomePage studentHome = (StudentHomePage) studentLogin.Login(student);
		AnimalsContentPage animals = studentHome.clickAnimalsModule();
		
		animals.clickCategoryByText("Amphibians");
		animals.waitImplicitly(1);
		
		assertTrue("Asserts that the article Frogs is not present visible for this student",
				!animals.isArticlePresentByName("Frogs"));
		
		articles.toggleArticleByName("Frogs");
		articles.waitImplicitly(5);
		
		animals.refresh();
		animals.waitImplicitly(5);
		
		
		assertTrue("Asserts that the article Frogs is visible for this student",
				animals.isArticlePresentByName("Frogs"));
		
		//articles.quit();
		animals.quit();
	}
	
	@Test
	public void testCanTurnOffPGNArticle(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(2);
		
		ArticlesActivePage articles = toolbar.clickArticles();
		articles.waitImplicitly(2);
		
		articles.searchForArticle("California");
		articles.waitImplicitly(5);
		
		if(articles.isArticleActive("California")){
			articles.toggleArticleByName("California");
		}
		
		User student = new User(UserInfo.GARAGESTUDENT);
		PGNPages.LoginPage studentLogin = new PGNPages.LoginPage(student, "editorialqa");
		PGNStudentResources.StudentHomePage studentHome = (PGNStudentResources.StudentHomePage) studentLogin.login(student);
		studentHome.waitImplicitly(1);
		
		StatesDatabaseHome states = studentHome.goToStates();
		states.clickCategory("West");
		states.waitImplicitly(5);
		

		assertTrue("Asserts that the user cannot see the California article",
				!states.isElementPresent(By.linkText("California")));
		
		articles.toggleArticleByName("California");
		articles.waitImplicitly(5);
		
		states.refresh();
		states.waitImplicitly(3);
		
		
		assertTrue("Asserts that the user cannot see the California article",
				states.isElementPresent(By.linkText("California")));
		
		home = articles.toolbar.clickLogo();
		states.quit();
	}
	
	@Test
	public void canTurnOffPGOCategory(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		ArticlesActivePage articles = toolbar.clickArticles();
		articles.waitImplicitly(5);
		
		articles.searchForArticle("Amphibians");
		articles.waitImplicitly(5);
				
		articles.openEditCategoryDiv("Amphibians");
		articles.waitImplicitly(5);
		
		if(articles.isCategoryActive("Amphibians")){
			articles.toggleCategoryActive("Amphibians");
		}
		articles.waitImplicitly(5);
		
		articles.clickSubmitByCategory("Amphibians");
		articles.waitImplicitly(5);
		
		User student = new User(UserInfo.GARAGESTUDENT);
		LoginPage studentLogin = new LoginPage(student, "editorialqa");
		
		StudentHomePage studentHome = (StudentHomePage) studentLogin.Login(student);
		AnimalsContentPage animals = studentHome.clickAnimalsModule();
		
		assertTrue("Asserts that the user cannot see the Amphibians category",
				!animals.isElementPresent(By.linkText("Amphibians")));
		
		articles.toggleCategoryActive("Amphibians");
		articles.waitImplicitly(5);
		
		articles.clickSubmitByCategory("Amphibians");
		articles.waitImplicitly(5);
		
		animals.refresh();
		animals.waitImplicitly(3);
		
		assertTrue("Asserts that the user can now see the Amphibians category",
				animals.isElementPresent(By.linkText("Amphibians")));
		animals.quit();
	}
	
	
	@Test
	public void testCanTurnOffPGNCategory(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		ArticlesActivePage articles = toolbar.clickArticles();
		articles.waitImplicitly(5);
		
		articles.searchForArticle("Midwest");
		articles.waitImplicitly(5);
				
		articles.openEditCategoryDiv("Midwest");
		articles.waitImplicitly(5);
		
		if(articles.isCategoryActive("Midwest")){
			articles.toggleCategoryActive("Midwest");
		}
		articles.waitImplicitly(5);
		
		articles.clickSubmitByCategory("Midwest");
		articles.waitImplicitly(5);
		
		User student = new User(UserInfo.GARAGESTUDENT);
		PGNPages.LoginPage studentLogin = new PGNPages.LoginPage(student, "editorialqa");
		PGNStudentResources.StudentHomePage studentHome = (PGNStudentResources.StudentHomePage) studentLogin.login(student);
		studentHome.waitImplicitly(1);
		
		StatesDatabaseHome states = studentHome.goToStates();
		states.waitImplicitly(5);
		
		assertTrue("Asserts that the user cannot see the Midwest category",
				!states.isElementPresent(By.linkText("Midwest")));
		
		articles.toggleCategoryActive("Midwest");
		articles.waitImplicitly(5);
		
		states.refresh();
		states.waitImplicitly(3);
		
		
		assertTrue("Asserts that the user cannot see the Midwest category",
				states.isElementPresent(By.linkText("Midwest")));
		
		home = articles.toolbar.clickLogo();
		states.quit();
	}
	
	@Test
	public void testUSOnlyPGOCategory(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		ArticlesActivePage articles = toolbar.clickArticles();
		articles.waitImplicitly(5);
		
		articles.searchForArticle("Authors and Artists");
		articles.waitImplicitly(5);
				
		articles.openEditCategoryDiv("Authors and Artists");
		articles.waitImplicitly(5);
		
		if(!articles.isCategoryActive("Authors and Artists")){
			articles.toggleCategoryActive("Authors and Artists");
		}
		
		if(!articles.isCategoryUSOnly("Authors and Artists")){
			articles.toggleCategoryUSOnly("Authors and Artists");
		}
		articles.waitImplicitly(5);
		
		articles.clickSubmitByCategory("Authors and Artists");
		articles.waitImplicitly(5);
		
		User student = new User(UserInfo.GARAGESTUDENT);
		LoginPage studentLogin = new LoginPage(student, "editorialqa");
		
		StudentHomePage studentHome = (StudentHomePage) studentLogin.Login(student);
		BiographiesContentPage bios = studentHome.clickBiographiesModule();
		
		assertTrue("Asserts that the user cannot see the Authors and Artists category",
				!bios.isElementPresent(By.linkText("Authors and Artists")));
		
		articles.toggleCategoryUSOnly("Authors and Artists");
		articles.waitImplicitly(5);
		
		articles.clickSubmitByCategory("Authors and Artists");
		articles.waitImplicitly(5);
		
		bios.refresh();
		bios.waitImplicitly(3);
		
		assertTrue("Asserts that the user can now see the Authors and Artists category",
				bios.isElementPresent(By.linkText("Authors and Artists")));
		bios.quit();
	}
	
	@Test
	public void testUSOnlyPGOArticle(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		ArticlesActivePage articles = toolbar.clickArticles();
		articles.waitImplicitly(5);
		
		articles.searchForArticle("Mudpuppies");
		articles.waitImplicitly(5);
		
		if(!articles.isArticleUsOnly("Mudpuppies")){
			articles.toggleArticleUsOnly("Mudpuppies");
		}
		articles.waitImplicitly(5);
		
		User student = new User(UserInfo.GARAGESTUDENT);
		LoginPage studentLogin = new LoginPage(student, "editorialqa");
		
		StudentHomePage studentHome = (StudentHomePage) studentLogin.Login(student);
		AnimalsContentPage animals = studentHome.clickAnimalsModule();
		animals.clickCategoryByText("Amphibians");
		
		assertTrue("Asserts that the user cannot see the Authors and Artists category",
				!animals.isElementPresent(By.linkText("Mudpuppies")));
		
		articles.toggleArticleUsOnly("Mudpuppies");
		articles.waitImplicitly(5);
		
		animals.refresh();
		animals.waitImplicitly(3);
		
		assertTrue("Asserts that the user can now see the Authors and Artists category",
				animals.isElementPresent(By.linkText("Mudpuppies")));
		animals.quit();
	}
	
	@Test
	public void testUSOnlyPGNCategory(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		ArticlesActivePage articles = toolbar.clickArticles();
		articles.waitImplicitly(5);
		
		articles.searchForArticle("Midwest");
		articles.waitImplicitly(5);
				
		articles.openEditCategoryDiv("Midwest");
		articles.waitImplicitly(5);
		
		if(!articles.isCategoryActive("Midwest")){
			articles.toggleCategoryActive("Midwest");
		}
		
		if(!articles.isCategoryUSOnly("Midwest")){
			articles.toggleCategoryUSOnly("Midwest");
		}
		articles.waitImplicitly(5);
		
		articles.clickSubmitByCategory("Midwest");
		articles.waitImplicitly(5);
		
		User student = new User(UserInfo.GARAGESTUDENT);
		PGNPages.LoginPage studentLogin = new PGNPages.LoginPage(student, "editorialqa");
		PGNStudentResources.StudentHomePage studentHome = (PGNStudentResources.StudentHomePage) studentLogin.login(student);
		studentHome.waitImplicitly(1);
		
		StatesDatabaseHome states = studentHome.goToStates();
		states.waitImplicitly(5);
		
		assertTrue("Asserts that the user cannot see the Midwest category",
				!states.isElementPresent(By.linkText("Midwest")));
		
		articles.toggleCategoryUSOnly("Midwest");
		articles.waitImplicitly(5);
		
		states.refresh();
		states.waitImplicitly(3);
		
		
		assertTrue("Asserts that the user cannot see the Midwest category",
				states.isElementPresent(By.linkText("Midwest")));
		
		home = articles.toolbar.clickLogo();
		states.quit();
	}
	
	@Test
	public void testUSOnlyPGNArticle(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		ArticlesActivePage articles = toolbar.clickArticles();
		articles.waitImplicitly(5);
		
		articles.searchForArticle("California");
		articles.waitImplicitly(5);
		
		if(!articles.isArticleUsOnly("California")){
			articles.toggleArticleUsOnly("California");
		}
		articles.waitImplicitly(5);
		
		
		User student = new User(UserInfo.GARAGESTUDENT);
		PGNPages.LoginPage studentLogin = new PGNPages.LoginPage(student, "editorialqa");
		PGNStudentResources.StudentHomePage studentHome = (PGNStudentResources.StudentHomePage) studentLogin.login(student);
		studentHome.waitImplicitly(1);
		
		StatesDatabaseHome states = studentHome.goToStates();
		states.waitImplicitly(3);
		states.clickCategory("West");
		states.waitImplicitly(3);
		
		assertTrue("Asserts that the user cannot see the California article",
				!states.isElementPresent(By.linkText("California")));
		
		articles.toggleArticleUsOnly("California");
		articles.waitImplicitly(5);
		
		states.refresh();
		states.waitImplicitly(3);
		
		
		assertTrue("Asserts that the user cannot see the California article",
				states.isElementPresent(By.linkText("California")));
		
		home = articles.toolbar.clickLogo();
		states.quit();
	}
	
	@Test
	public void testTexasOnlyPGOArticle(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		ArticlesActivePage articles = toolbar.clickArticles();
		articles.waitImplicitly(5);
		
		articles.searchForArticle("LeBron James");
		articles.waitImplicitly(5);
		
		if(!articles.isArticleTexasOnly("LeBron James")){
			articles.toggleArticleTexasOnly("LeBron James");
		}
		articles.waitImplicitly(5);
		
		User student = new User(UserInfo.GARAGESTUDENT);
		LoginPage studentLogin = new LoginPage(student, "editorialqa");
		
		StudentHomePage studentHome = (StudentHomePage) studentLogin.Login(student);
		BiographiesContentPage bios = studentHome.clickBiographiesModule();
		bios.clickCategoryByText("Athletes");
		bios.ClickTexasToggleOnButton();
		
		assertTrue("Asserts that the user cannot see the Lebron James article",
				bios.isElementPresent(By.linkText("LeBron James")));
		
		articles.toggleArticleTexasOnly("LeBron James");
		articles.waitImplicitly(5);
		
		bios.refresh();
		bios.waitImplicitly(3);
		
		assertTrue("Asserts that the user can now see the Authors and Artists category",
				!bios.isElementPresent(By.linkText("LeBron James")));
		bios.quit();
	}
	
	@Test
	public void testClearSearch(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(2);
		
		ArticlesActivePage articles = toolbar.clickArticles();
		articles.waitImplicitly(3);
		
		articles.searchForArticle("Cali");
		articles.waitImplicitly(5);
		
		assertTrue("Asserts that the user can find the California article toggle button", articles.isArticleToggleButtonPresent("California"));
		
		articles.clearSearchResults();
		articles.waitImplicitly(5);
		
		assertTrue("Asserts that the user can find the California article toggle button", !articles.isArticleToggleButtonPresent("California"));
		home = articles.toolbar.clickLogo();
	}
	
	@Test
	public void testSearchBringUpResults(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(2);
		
		ArticlesActivePage articles = toolbar.clickArticles();
		articles.waitImplicitly(3);

		articles.searchForArticle("bug");
		articles.waitImplicitly(5);
		
		assertTrue("Asserts that more than one result propogates for a simple search", 
				articles.isArticleToggleButtonPresent("Ladybugs") && articles.isArticleToggleButtonPresent("Pillbugs"));
		home = articles.toolbar.clickLogo();
	}
	
	@Test
	public void testSearchForSpecificArticle(){	
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(2);
		
		ArticlesActivePage articles = toolbar.clickArticles();
		articles.waitImplicitly(3);
		
		articles.searchForArticle("Henry Ford");
		articles.waitImplicitly(5);
		
		assertTrue("Asserts that only one result propogates for a simple search", 
				articles.isArticleToggleButtonPresent("Henry Ford"));
	}
	
	@Test
	public void testSearchForSecondWord(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(2);
		
		ArticlesActivePage articles = toolbar.clickArticles();
		articles.waitImplicitly(3);
		
		articles.searchForArticle("ford");
		articles.waitImplicitly(5);
		
		assertTrue("Asserts that only one result propogates for a simple search", 
				articles.isArticleToggleButtonPresent("Henry Ford"));
	}
	
	@Test
	/*
	 * Verifies that a deactivated Module doesn't show up in Question of the Day modals
	 */
	public void testDeactivatedModuleNameQuestionsOfTheDay() throws InterruptedException{
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(2);
		
		TemplatesPage modules = toolbar.clickModules();
		modules.waitImplicitly(5);
		
		modules.makeModuleInactive("PebbleGo Cars");
		modules.waitImplicitly(1);
		
		modules.makeModuleActive("Activities");
		modules.waitImplicitly(1);
		modules.scrollToTopofPage();
		
		toolbar = home.openToolbar();
		QuestionsPage questions = toolbar.clickQuestions();
		questions.waitImplicitly(5);
		

		QuestionOfTheDayModal questionsModal = questions.openModal();
		
		assertTrue("Asserts that a made up module is not present on Questoin Of the Day Modal",
				!questionsModal.isModulePresentInSelect("Not There"));
		
		assertTrue("Asserts that a deactivated module is not present on Question of the Day Modal",
				!questionsModal.isModulePresentInSelect("PebbleGo Cars"));
		
		assertTrue("Asserts that an activated module is present on Question of the Day Modal",
				questionsModal.isModulePresentInSelect("Activities"));
		
		questions = questionsModal.closeModal();
	}
	
	@Test
	public void testActivatedExportModuleName() throws InterruptedException{
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		TemplatesPage modules = toolbar.clickTemplates();
		modules.waitImplicitly(5);
		
		modules.makeModuleInactive("Activities");
		
		modules.makeModuleActive("PebbleGo Cars");
		
		modules.waitImplicitly(1);
		modules.scrollToTopofPage();
		modules.waitImplicitly(1);
		
		toolbar = modules.openToolbar();
		
		/*
		UploadExportPage export = toolbar.clickUploadExport();
		export.waitImplicitly(5);
		
		assertTrue("Asserts that a made up module is not present on Module Export page",
				!export.doesModuleExistOnPage("Activities"));
		
		assertTrue("Asserts that a deactivated module is not present on Module Export page",
				!export.doesModuleExistOnPage("PebbleGo Sandwiches"));
		
		assertTrue("Asserts that an activated module is present on Question of the Module Export page",
				export.doesModuleExistOnPage("PebbleGo Cars"));
				*/
	}
	
	@Test
	public void testGlossaryTermSearchAnimals(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		GlossarySearchPage glossary = toolbar.clickGlossarySearch();
		glossary.waitImplicitly(5);
		
		glossary.searchTerm("barbel");
		
		assertTrue("Asserts that a search term will populate from a search", glossary.doesGlossaryTermExist("barbels"));
		
		home = glossary.toolbar.clickLogo();
	}
	
	@Test
	public void testGlossaryTermSearchScience(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		GlossarySearchPage glossary = toolbar.clickGlossarySearch();
		glossary.waitImplicitly(5);
		
		glossary.searchTerm("forests");
		
		assertTrue("Asserts that a search term will populate from a search", glossary.doesGlossaryTermExist("rain forests"));
		
		home = glossary.toolbar.clickLogo();
	}
	
	@Test
	public void testGlossaryTermSearchBiographiesSecondWord(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		GlossarySearchPage glossary = toolbar.clickGlossarySearch();
		glossary.waitImplicitly(5);
		
		glossary.searchTerm("war");
		
		assertTrue("Asserts that a search term will populate from a search", glossary.doesGlossaryTermExist("Civil War"));
		
		home = glossary.toolbar.clickLogo();
	}
	
	@Test
	public void testGlossaryTermSearchSocialStudies(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		GlossarySearchPage glossary = toolbar.clickGlossarySearch();
		glossary.waitImplicitly(5);
		
		glossary.searchTerm("centers");
		
		assertTrue("Asserts that a search term will populate from a search", glossary.doesGlossaryTermExist("child care centers"));
		
		home = glossary.toolbar.clickLogo();
	}
	
	@Test
	public void testGlossaryTermSearchMiddleOfThreeSearch(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		GlossarySearchPage glossary = toolbar.clickGlossarySearch();
		glossary.waitImplicitly(5);
		
		glossary.searchTerm("care");
		
		assertTrue("Asserts that a search term will populate from a search", glossary.doesGlossaryTermExist("child care centers"));
		
		home = glossary.toolbar.clickLogo();
	}
	
	@Test
	public void testGlossaryTermSearchDinosaurs(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		GlossarySearchPage glossary = toolbar.clickGlossarySearch();
		glossary.waitImplicitly(5);
		
		glossary.searchTerm("herbivore");
		
		assertTrue("Asserts that a search term will populate from a search", glossary.doesGlossaryTermExist("herbivore"));
		
		home = glossary.toolbar.clickLogo();
	}
	
	@Test
	public void testGlossaryTermSearchAnimales(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		GlossarySearchPage glossary = toolbar.clickGlossarySearch();
		glossary.waitImplicitly(5);
		
		glossary.searchTerm("cría");
		
		assertTrue("Asserts that a search term will populate from a search", glossary.doesGlossaryTermExist("cría"));
		
		home = glossary.toolbar.clickLogo();
	}
	
	@Test
	public void testDeactivateModulePGO() throws InterruptedException{
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(3);
		
		TemplatesPage modules = home.toolbar.clickModules();
		
		
		modules.waitImplicitly(5);
		
		modules.makeModuleInactive("PebbleGo Social Studies");

		
		User student = new User(UserInfo.GARAGESTUDENT);
		LoginPage studentLogin = new LoginPage(student, "editorialqa");
		
		StudentHomePage studentHome = (StudentHomePage) studentLogin.Login(student);

		assertTrue("Asserts that the modules Animals is not present visible for this student",
				!studentHome.isModulePresentByName("Social Studies"));
		
		modules.makeModuleActive("PebbleGo Social Studies");
		modules.waitImplicitly(2);
		
		studentLogin = studentHome.clickLogOutButton();
		studentLogin.waitImplicitly(2);
		studentHome = (StudentHomePage) studentLogin.Login(student);
		
		studentHome.waitImplicitly(2);
		
		
		assertTrue("Asserts that the article Frogs is not present visible for this student",
				studentHome.isModulePresentByName("Social Studies"));

		home = modules.toolbar.clickLogo();
		studentHome.quit();
	}
	
	@Test
	public void testDeactivateModulePGN() throws InterruptedException{
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		TemplatesPage modules = home.toolbar.clickModules();
		modules.waitImplicitly(5);
		
		modules.makeModuleInactive("PebbleGo Next States");
		
		User student = new User(UserInfo.GARAGESTUDENT);
		PGNPages.LoginPage studentLogin = new PGNPages.LoginPage(student, "editorialqa");
		PGNStudentResources.StudentHomePage studentHome = (PGNStudentResources.StudentHomePage) studentLogin.login(student);
		studentHome.waitImplicitly(1);
		

		assertTrue("Asserts that the user cannot see the States Module",
				!studentHome.isModulePresentByName("States"));
		
		modules.makeModuleActive("PebbleGo Next States");
		
		modules.waitImplicitly(1);
		studentHome.waitImplicitly(1);
		studentHome.refresh();
		
		assertTrue("Asserts that the user cannot see the States Module",
				!studentHome.isModulePresentByName("States"));
		
		home = modules.toolbar.clickLogo();
		studentHome.quit();
	}
	
	@Test
	public void testAllAssetsPGOArticle(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		AssetPage assets = toolbar.clickAssets();
		assets.waitImplicitly(3);
		
		assets.search("frogs");
		assets.waitImplicitly(120);
		
		assertTrue("Asserts that the pgoanimals frogs listen mp3 file is present", assets.isMP3Present("pgoanimals_frogs_listen.mp3")
				&& assets.verifyAsset(assets.getMP3src("pgoanimals_frogs_listen.mp3")));
		assertTrue("Asserts that the pgoanimals frogs map image file is present", assets.isImagePresent("pgoanimals_frogs_map.png")
				&& assets.verifyAsset(assets.getImageSRC("pgoanimals_frogs_map.png")));
		assertTrue("Asserts that the pgoanimals frogs video file is present", assets.isVideoPresent("pgoanimals_frogs_video_0.mp4")
				&& assets.verifyAsset(assets.getVideoSrc("pgoanimals_frogs_video_0.mp4")));
		assertTrue("Asserts that the pgoanimals frogs share pdf file is present", assets.isPDFPresent("pgoanimals_frogs_share.pdf")
				&& assets.verifyAsset(assets.getPDFSrc("pgoanimals_frogs_share.pdf")));
		assertTrue("Asserts that the pgoanimals frogs screen xml is present", assets.isXMLPresent("pgoanimals_frogs_scr_3.xml")
				&& assets.verifyAsset(assets.getXMLSrc("pgoanimals_frogs_scr_3.xml")));
	}
	
	@Test
	public void testAllAssetsPGNStatesArticle(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		AssetPage assets = toolbar.clickAssets();
		assets.waitImplicitly(3);
		
		assets.search("california");
		assets.waitImplicitly(120);
		
		assertTrue("Asserts that the pgnstates california listen mp3 fact file is present", assets.isMP3Present("pgnstates_california_fact_11.mp3")
				&& assets.verifyAsset(assets.getMP3src("pgnstates_california_fact_11.mp3")));
		assertTrue("Asserts that the pgnstates california listen mp3 landmard file is present", assets.isMP3Present("pgnstates_california_landmark_2.mp3")
				&& assets.verifyAsset(assets.getMP3src("pgnstates_california_landmark_2.mp3")));
		assertTrue("Asserts that the pgnstates california listen mp3 person file is present", assets.isMP3Present("pgnstates_california_person_3.mp3")
				&& assets.verifyAsset(assets.getMP3src("pgnstates_california_person_3.mp3")));
		assertTrue("Asserts that the pgnstates california listen mp3 screen file is present", assets.isMP3Present("pgnstates_california_scr_4.mp3")
				&& assets.verifyAsset(assets.getMP3src("pgnstates_california_scr_4.mp3")));
		assertTrue("Asserts that the pgnstates california listen mp3 symbol file is present", assets.isMP3Present("pgnstates_california_symbol_5.mp3")
				&& assets.verifyAsset(assets.getMP3src("pgnstates_california_symbol_5.mp3")));
		assertTrue("Asserts that the pgnstates california listen mp3 timeline file is present", assets.isMP3Present("pgnstates_california_timeline_6.mp3")
				&& assets.verifyAsset(assets.getMP3src("pgnstates_california_timeline_6.mp3")));
		assertTrue("Asserts that the pgnstates california listen mp3 fact file is present", assets.isMP3Present("pgnstates_california_fact_7.mp3")
				&& assets.verifyAsset(assets.getMP3src("pgnstates_california_fact_7.mp3")));
		assertTrue("Asserts that the pgnstates california listen mp3 landmark file is present", assets.isMP3Present("pgnstates_california_landmark_4.mp3")
				&& assets.verifyAsset(assets.getMP3src("pgnstates_california_landmark_4.mp3")));
		assertTrue("Asserts that the pgnstates california article image file is present", assets.isImagePresent("pgnstates_california.jpg")
				&& assets.verifyAsset(assets.getImageSRC("pgnstates_california.jpg")));
		assertTrue("Asserts that the pgnstates california document image file is present", assets.isImagePresent("pgnstates_california_document_1.jpg")
				&& assets.verifyAsset(assets.getImageSRC("pgnstates_california_document_1.jpg")));
		assertTrue("Asserts that the pgnstates california document image file is present", assets.isImagePresent("pgnstates_california_document_2.jpg")
				&& assets.verifyAsset(assets.getImageSRC("pgnstates_california_document_2.jpg")));
		assertTrue("Asserts that the pgnstates california document image file is present", assets.isImagePresent("pgnstates_california_document_3.jpg")
				&& assets.verifyAsset(assets.getImageSRC("pgnstates_california_document_3.jpg")));
		assertTrue("Asserts that the pgnstates california fact image file is present", assets.isImagePresent("pgnstates_california_fact_10.jpg")
				&& assets.verifyAsset(assets.getImageSRC("pgnstates_california_fact_10.jpg")));
		assertTrue("Asserts that the pgnstates california fact image file is present", assets.isImagePresent("pgnstates_california_landmark_0.jpg")
				&& assets.verifyAsset(assets.getImageSRC("pgnstates_california_landmark_0.jpg")));
		assertTrue("Asserts that the pgnstates california fact image file is present", assets.isImagePresent("pgnstates_california_person_2.jpg")
				&& assets.verifyAsset(assets.getImageSRC("pgnstates_california_person_2.jpg")));
		assertTrue("Asserts that the pgnstates california fact image file is present", assets.isImagePresent("pgnstates_california_recipe.jpg")
				&& assets.verifyAsset(assets.getImageSRC("pgnstates_california_recipe.jpg")));
		assertTrue("Asserts that the pgnstates california fact image file is present", assets.isImagePresent("pgnstates_california_scr_0_img_1.jpg")
				&& assets.verifyAsset(assets.getImageSRC("pgnstates_california_scr_0_img_1.jpg")));
		assertTrue("Asserts that the pgnstates california fact image file is present", assets.isImagePresent("pgnstates_california_symbol_3.jpg")
				&& assets.verifyAsset(assets.getImageSRC("pgnstates_california_symbol_3.jpg")));
		assertTrue("Asserts that the pgnstates california video file is present", assets.isVideoPresent("pgnstates_california_video_0.mp4")
				&& assets.verifyAsset(assets.getVideoSrc("pgnstates_california_video_0.mp4")));
		assertTrue("Asserts that the pgnstates california share pdf file is present", assets.isPDFPresent("pgnstates_california_document_2.pdf")
				&& assets.verifyAsset(assets.getPDFSrc("pgnstates_california_document_2.pdf")));
		assertTrue("Asserts that the pgnstates california fact xml is present", assets.isXMLPresent("pgnstates_california_fact_2.xml")
				&& assets.verifyAsset(assets.getXMLSrc("pgnstates_california_fact_2.xml")));
		assertTrue("Asserts that the pgnstates california landmark xml is present", assets.isXMLPresent("pgnstates_california_landmark_4.xml")
				&& assets.verifyAsset(assets.getXMLSrc("pgnstates_california_landmark_4.xml")));
		assertTrue("Asserts that the pgnstates california person xml is present", assets.isXMLPresent("pgnstates_california_person_4.xml")
				&& assets.verifyAsset(assets.getXMLSrc("pgnstates_california_person_4.xml")));
		assertTrue("Asserts that the pgnstates california screen xml is present", assets.isXMLPresent("pgnstates_california_scr_1.xml")
				&& assets.verifyAsset(assets.getXMLSrc("pgnstates_california_scr_1.xml")));
		assertTrue("Asserts that the pgnstates california symbol xml is present", assets.isXMLPresent("pgnstates_california_symbol_4.xml")
				&& assets.verifyAsset(assets.getXMLSrc("pgnstates_california_symbol_4.xml")));
		assertTrue("Asserts that the pgnstates california timeline xml is present", assets.isXMLPresent("pgnstates_california_timeline_16.xml")
				&& assets.verifyAsset(assets.getXMLSrc("pgnstates_california_timeline_16.xml")));
	}
	
	@Test
	public void testAllAssetsPGNScienceArticle(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		AssetPage assets = toolbar.clickAssets();
		assets.waitImplicitly(3);
		
		assets.search("organs");
		assets.waitImplicitly(120);
		
		assertTrue("Asserts that the pgnscience organs screen mp3 fact file is present", assets.isMP3Present("pgnscience_organs_scr_6.mp3")
				&& assets.verifyAsset(assets.getMP3src("pgnscience_organs_scr_6.mp3")));
		assertTrue("Asserts that the pgnscience organs article image file is present", assets.isImagePresent("pgnscience_organs.jpg")
				&& assets.verifyAsset(assets.getImageSRC("pgnscience_organs.jpg")));
		assertTrue("Asserts that the pgnscience organs document image file is present", assets.isImagePresent("pgnscience_organs_document_4.jpg")
				&& assets.verifyAsset(assets.getImageSRC("pgnscience_organs_document_4.jpg")));
		assertTrue("Asserts that the pgnscience organs screen image file is present", assets.isImagePresent("pgnscience_organs_scr_2_img_2.jpg")
				&& assets.verifyAsset(assets.getImageSRC("pgnscience_organs_scr_2_img_2.jpg")));
		assertTrue("Asserts that the pgnscience organs video file is present", assets.isVideoPresent("pgnscience_organs_video_0.mp4")
				&& assets.verifyAsset(assets.getVideoSrc("pgnscience_organs_video_0.mp4")));
		assertTrue("Asserts that the pgnscience organs share pdf file is present", assets.isPDFPresent("pgnscience_organs_document_4.pdf")
				&& assets.verifyAsset(assets.getPDFSrc("pgnscience_organs_document_4.pdf")));
		assertTrue("Asserts that the pgnscience organs fact xml is present", assets.isXMLPresent("pgnscience_organs_scr_4.xml")
				&& assets.verifyAsset(assets.getXMLSrc("pgnscience_organs_scr_4.xml")));
	}
	
	@Test
	public void testDeactivateQuestionOfTheDay(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		QuestionsPage questions = toolbar.clickQuestions();
		questions.waitImplicitly(5);
		
		
		if(questions.isQuestionActivated("PebbleGo Questions: What is your favorite question?")){
			questions.toggleQuestionOfTheDayModal("PebbleGo Questions: What is your favorite question?");
		}
		
		User student = new User(UserInfo.GARAGESTUDENT);
		LoginPage studentLogin = new LoginPage(student, "editorialqa");
		
		StudentHomePage studentHome = (StudentHomePage) studentLogin.Login(student);
		
		ContentSelectionPage qPage = studentHome.clickNewModule("Questions");
		
		ArticleModals.PGOQuestionOfTheDayModal question = qPage.openQuestionOfTheDay();
		
		assertTrue("Asserts that the question of the day modal doesn't open with no active questions", !question.isActive());
		
		questions.toggleQuestionOfTheDayModal("PebbleGo Questions: What is your favorite question?");
		questions.waitImplicitly(5);
		
		qPage.refresh();
		qPage.waitImplicitly(3);
		
		question = qPage.openQuestionOfTheDay();
		question.waitImplicitly(5);
		
		assertTrue("Asserts that the question of the day modal doesn't open with no active questions", question.isActive());
		qPage.quit();
	}
	
	@Test
	public void testDeactivatedAnswer(){
		CMSToolbar toolbar = home.openToolbar();
		toolbar.waitImplicitly(5);
		
		QuestionsPage questions = toolbar.clickQuestions();
		questions.waitImplicitly(5);
		
		if(!questions.isQuestionActivated("PebbleGo Questions: What is your favorite question?")){
			questions.toggleQuestionOfTheDayModal("PebbleGo Questions: What is your favorite question?");
			questions.waitImplicitly(5);
		}
		
		EditQuestionOfTheDayModal edit = questions.editQuestion("PebbleGo Questions: What is your favorite question?");
		edit.waitImplicitly(5);
		edit.toggleAnswerByInt(4);
		
		
		User student = new User(UserInfo.GARAGESTUDENT);
		LoginPage studentLogin = new LoginPage(student, "editorialqa");
		
		StudentHomePage studentHome = (StudentHomePage) studentLogin.Login(student);
		
		ContentSelectionPage qPage = studentHome.clickNewModule("Questions");
		
		ArticleModals.PGOQuestionOfTheDayModal question = qPage.openQuestionOfTheDay();
		
		assertTrue("Asserts that the question of the day modal doesn't open with no active questions", question.getAnswerTextByInt(4).equals("Hidden Questions:"));
		edit.toggleAnswerByInt(4);
		edit.waitImplicitly(5);
		edit.clickUpdateQuestionOfTheDay();
		questions.waitImplicitly(5);
		
		qPage.refresh();
		qPage.waitImplicitly(3);
		
		question = qPage.openQuestionOfTheDay();
		question.waitImplicitly(5);
		
		assertTrue("Asserts that the question of the day modal doesn't open with no active questions", question.getAnswerTextByInt(4).equals(""));
		qPage.quit();
	}
}

