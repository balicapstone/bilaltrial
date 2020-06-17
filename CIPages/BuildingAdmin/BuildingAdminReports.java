package BuildingAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BuildingAdminReports extends BuildingAdminDashboard{
	//Site Usage Report
	public By siteUsageStartMonth = By.id("filter-date-begin");
	public By siteUsageEndMonth = By.id("filter-date-end");
	public By siteUsageShowDetailedView = By.id("show-detailed-view");
	public By siteUsageApplyFilterButton = By.id("submit-filter-data");
	public By siteUsageResetButton = By.xpath("//*[@id=\"site-usage-filters\"]/ul/li[4]/a");
	public By siteUsageSelectFormat = By.id("export-type");
	public By siteUsageExportButton = By.id("bttn-export");
	public By siteUsageGraph = By.xpath("//div[@class=\"graph-holder\"]");
	public By[] siteUsageElements = {siteUsageStartMonth, siteUsageEndMonth, siteUsageShowDetailedView, siteUsageApplyFilterButton,
			siteUsageResetButton, siteUsageSelectFormat, siteUsageExportButton, siteUsageGraph};
	
	//Title Activity Report
	public By titleActivityFromDate = By.id("from_date");
	public By titleActivityToDate = By.id("to_date");
	public By titleActivityApplyFilterButton = By.xpath("//*[@id=\"ui-accordion-accordion-panel-0\"]/form/ul/li[3]/input");
	public By titleActivityResetButton = By.xpath("//*[@id=\"ui-accordion-accordion-panel-0\"]/form/ul/li[3]/a");
	public By titleActivitySelectFormatButton = By.id("export-type");
	public By titleActivityExportButton = By.id("export-btn");
	public By titleActivityTable = By.xpath("//table[@class=\"grid-table\"]");
	public By titleActivityPagerHolder = By.xpath("//form[@class=\"pager-holder\"]");
	public By titleActivityPagerNumbers = By.xpath("//nav[@class=\"pagerfanta\"]");
	public By[] titleActivityElements = {titleActivityFromDate, titleActivityToDate, titleActivityApplyFilterButton, titleActivityResetButton,
			titleActivitySelectFormatButton, titleActivityExportButton, titleActivityTable, titleActivityPagerHolder, titleActivityPagerNumbers};
	
	//Most Popular Books Report
	public By mostPopularBooksDropdown = By.id("report_id");
	public By mostPopularBooksFilterButton = By.xpath("//*[@id=\"ui-accordion-accordion-panel-0\"]/form/ul/li[2]/input");
	public By mostPopularBooksResetButton = By.xpath("//*[@id=\"ui-accordion-accordion-panel-0\"]/form/ul/li[2]/a");
	public By mostPopularBooksSelectFormat = By.id("export-type");
	public By mostPopularBooksExportButton = By.id("export-btn");
	public By mostPopularBooksTable = By.xpath("//table[@class=\"grid-table\"]");
	public By[] mostPopularBooksElements = {mostPopularBooksDropdown, mostPopularBooksFilterButton, mostPopularBooksResetButton, 
			mostPopularBooksSelectFormat, mostPopularBooksExportButton, mostPopularBooksTable};
	
	
	public BuildingAdminReports(WebDriver driver){
		super(driver);
	}
	
	
}
