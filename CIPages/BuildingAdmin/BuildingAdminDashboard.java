package BuildingAdmin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CITests.CIPages.CILoginPage;
import SharedClasses.BasePage;

public class BuildingAdminDashboard extends BasePage{
	public By eulaWarningLink = By.xpath("/html/body/header/div[3]/p/span[2]/a");
	public By dashboardTab = By.xpath("/html/body/header/div[2]/div/div/ul/li[1]/a");
	public By reportsTab = By.xpath("/html/body/header/div[2]/div/div/ul/li[2]/span");
	public By studentDisplayOptions = By.xpath("/html/body/header/div[2]/div/div/ul/li[3]/a");
	public By booksAndBooklists = By.xpath("/html/body/header/div[2]/div/div/ul/li[4]/span");
	public By linkAccountsTab = By.xpath("/html/body/header/div[2]/div/div/ul/li[5]/a");
	public By openStudentView = By.xpath("/html/body/header/div[1]/div[1]/a");
	public By adminButton = By.xpath("//li[@class=\"user-menu-li\"]/span");
	public By editMyProfile = By.xpath("//li[@class=\"user-menu-li\"]/ul/li[1]/a");
	public By logout = By.xpath("//li[@class=\"user-menu-li\"]/ul/li[2]/a");
	public By[] initialVisibleElements = {dashboardTab, reportsTab, studentDisplayOptions, booksAndBooklists, linkAccountsTab,
			openStudentView, adminButton};
	
	//Dashboard Links
	public By siteUsageLink = By.linkText("Site Usage");
	public By titleActivityLink = By.linkText("Title Activity");
	public By mostPopularBooksLink = By.linkText("Most Popular Books");
	
	//Report Buttons
	public By siteUsageButton = By.xpath("//ul[@class=\"child-menu\"]/li[1]/a");
	public By titleActivityButton = By.xpath("//ul[@class=\"child-menu\"]/li[2]/a");
	public By mostPopularBooksButton = By.xpath("//ul[@class=\"child-menu\"]/li[3]/a");
	
	//Books And Booklists Buttons
	public By summaryViewButton = By.xpath("//a[@href=\"/admin/all_books/summary\"]"); //"/html/body/header/div[2]/div/div/ul/li[4]/ul/li[1]/a"); //"//ul[@class=\"child-menu\"]/li[1]/a");
	public By allBooksButton = By.xpath("//a[@href=\"/admin/all_books/index\"]"); //By.xpath("//ul[@class=\"child-menu\"]/li[2]/a");
	public By booklistsButton = By.xpath("//a[@href=\"/admin/booklist/index\"]");
	public By bookPosterButton = By.xpath("//a[@href=\"/admin/poster/index\"]");
	
	//Summary View Elements
	public By summaryViewFilterButton = By.id("ui-accordion-accordion-header-0");
	public By summaryViewSelectReportType = By.id("report_type");
	public By summaryViewApplyFilterButton = By.xpath("//*[@id=\"ui-accordion-accordion-panel-0\"]/form/ul/li[2]/input");
	public By summaryViewSelectFormatType = By.id("export-type");
	public By summaryViewExportButton = By.id("bttn-export");
	public By summaryViewReportTable = By.xpath("//div[@class=\"graph-holder\"]");
	public By[] summaryViewElements = {summaryViewFilterButton, summaryViewSelectReportType, summaryViewApplyFilterButton, 
			summaryViewSelectFormatType, summaryViewExportButton, summaryViewReportTable};
	
	//All Books Elements
	public By allBooksFilterButton = By.xpath("//*[@id=\"ui-accordion-accordion-header-0\"]/span");
	public By allBooksSelectTitleDropdown = By.id("show_books");;
	public By allBooksSearchByTitle = By.id("filter-accounts-title");
	public By allBooksApplyFilter = By.xpath("//*[@id=\"ui-accordion-accordion-panel-0\"]/form/ul/li[3]/input");
	public By allBooksResetButton = By.xpath("//*[@id=\"ui-accordion-accordion-panel-0\"]/form/ul/li[3]/a");
	public By allBooksSelectFormat = By.id("export-type");
	public By allBooksExportButton = By.id("export-btn");
	public By allBooksGridTable = By.id("user-list");
	public By allBooksSelectAll = By.xpath("//*[@id=\"user-list\"]/thead/tr/th[1]/label");
	public By allBooksSortBy = By.xpath("//*[@id=\"titles-sort-filter\"]/ul/li/span/a");
	public By allBooksAddSelectedToBooklist = By.id("btnAddSelected");
	public By allBooksPagerHolder = By.xpath("//div[@class=\"pager-holder\"]");
	public By allBooksPagerNumbers = By.xpath("//nav[@class=\"pagerfanta\"]");
	public By[] allBooksElements = {allBooksFilterButton, allBooksSelectTitleDropdown, allBooksSearchByTitle, allBooksApplyFilter, allBooksResetButton,
			allBooksSelectFormat, allBooksExportButton, allBooksGridTable, allBooksSelectAll, allBooksSortBy, allBooksAddSelectedToBooklist, 
			allBooksPagerHolder, allBooksPagerNumbers};
	
	//Booklists Elements
	public By booklistsFilter = By.id("ui-accordion-accordion-header-0");
	public By booklistsNewBooklist = By.xpath("//a[@class=\"bttn-main\"]");
	public By booklistsSelectGrade = By.id("show_grade");
	public By booklistsApplyFilterButton = By.xpath("//input[@value=\"Apply filter\"]");
	public By booklistsResetButton = By.xpath("//*[@id=\"ui-accordion-accordion-panel-0\"]/form/ul/li[2]/a");
	public By booklistsSelectFormat = By.id("export-type");
	public By booklistsExportButton = By.id("export-btn");
	public By booklistTable = By.id("booklist-list");
	public By booklistResultsNumber = By.xpath("//h2[@class=\"total-result\"]");
	public By booklistBooksPagerHolder = By.xpath("//form[@class=\"pager-holder\"]");
	public By booklistBooksPagerNumbers = By.xpath("//nav[@class=\"pagerfanta\"]");
	public By[] booklistsElements = {booklistsFilter, booklistsNewBooklist, booklistsSelectGrade, booklistsApplyFilterButton,
			booklistsResetButton, booklistsSelectFormat, booklistsExportButton, booklistTable, booklistResultsNumber, 
			booklistBooksPagerHolder, booklistBooksPagerNumbers};
	
	//Book Poster Elements
	public By bookPosterFilter = By.id("ui-accordion-accordion-header-0");
	public By bookPosterSearch = By.id("filter-booklist-title");
	public By bookPosterApplyFilter = By.id("booklist-book-filter");
	public By bookPosterApplyReset = By.id("booklist-book-reset");
	public By bookPosterResults = By.xpath("//h2[@class=\"total-result\"]");
	public By bookPosterTable = By.xpath("//table[@class=\"grid-table\"]");
	public By bookPosterPagerHolder = By.xpath("//div[@class=\"pager-holder\"]");
	public By bookPosterPagerNumbers = By.xpath("//nav[@class=\"pagerfanta\"]");
	public By[] bookPosterElements = {bookPosterFilter, bookPosterSearch, bookPosterApplyFilter, bookPosterApplyReset,
			bookPosterResults, bookPosterTable, bookPosterPagerHolder, bookPosterPagerNumbers};
	
	public BuildingAdminDashboard(WebDriver driver){
		pageDriver = driver;
		pageDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	public EULAAcceptancePage clickEulaLink(){
		click(eulaWarningLink);
		return new EULAAcceptancePage(pageDriver);
	}
	
	public CILoginPage logout(){
		click(adminButton);
		waitImplicitly(1);
		click(logout);
		return new CILoginPage(pageDriver);
	}
	
	public BuildingAdminBooksAndBooklists clickBooksAndBooklistsTab(){
		click(booksAndBooklists);
		return new BuildingAdminBooksAndBooklists(pageDriver);
	}
	
	public BuildingAdminReports clickReportsTab(){
		click(reportsTab);
		return new BuildingAdminReports(pageDriver);
	}
	
	public BuildingAdminStudentDisplayOptions clickStudentDisplayOptions(){
		click(studentDisplayOptions);
		return new BuildingAdminStudentDisplayOptions(pageDriver);
	}
	
	public BuildingAdminDashboard clickDashboardTab(){
		click(dashboardTab);
		return new BuildingAdminDashboard(pageDriver);
	}
	
	public LinkAccountsTab clickLinkAccountsTab(){
		click(linkAccountsTab);
		return new LinkAccountsTab(pageDriver);
	}
	
	public void openStudentView(){
		click(openStudentView);
	}
	
	public EditMyProfileBuildingAdminPage editMyProfile(){
		click(adminButton);
		click(editMyProfile);
		return new EditMyProfileBuildingAdminPage(pageDriver);
	}
}
