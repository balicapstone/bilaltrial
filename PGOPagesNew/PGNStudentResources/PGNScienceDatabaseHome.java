package PGNStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PGNScienceDatabaseHome extends PGNDatabasePage{	
	public By earthScienceLink = By.xpath("//*[@id='content-box']/div[1]/a"); //*span[contains(.,'Earth Science')]"); ////*[@id=\"content-box\"]
	public By lifeScienceLink = By.xpath("//*[@id=\"content-box\"]/div[2]/a/span"); //By.linkText("Life Science");
	public By physicalScienceLink = By.xpath("//*[@id=\"content-box\"]/div[3]/a/span"); //By.linkText("Physical Science");
	public By fieldOfScienceLink = By.xpath("//*[@id=\"content-box\"]/div[4]/a/span"); //By.linkText("The Field of Science");
	public By experimentsLink = By.id("trigger-video-experiments");
	public By[] initialVisibleElements = {earthScienceLink, lifeScienceLink, physicalScienceLink, fieldOfScienceLink};
	public By gamesLink = By.xpath("//*[@id=\"entertainment-buttons\"]/a[1]");
	
	public PGNScienceDatabaseHome(WebDriver driver){
		super(driver);
	}

	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}		
	
	public PGNExperimentsPage goToExperimentsPage(){
		click(experimentsLink);
		return new PGNExperimentsPage(pageDriver);
	}
	
	public Boolean verifyImage(String term){
		String  entry = getDatabaseEntryImageSrc(term);
		return this.verifyImageActive(entry);
	}
}
