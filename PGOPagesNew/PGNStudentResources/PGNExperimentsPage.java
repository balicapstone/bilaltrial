package PGNStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import MasterAccount.LinkAccountsToolbar;
import PGNModals.PGNVideoModal;
import SharedClasses.BasePage;

public class PGNExperimentsPage extends BasePage{

	public PGNVideoModal video;
	public PGNStudentHeader header;
	public LinkAccountsToolbar laToolbar;
	
	public PGNExperimentsPage(WebDriver driver){
		pageDriver = driver;
		header = new PGNStudentHeader(driver);
		laToolbar = new LinkAccountsToolbar(driver);
	}
	
	public PGNVideoModal clickVideoByTitle(String title){
		getModalElement(By.xpath("//*[@data-title=\""+title+"\"]")).click();
		video = new PGNVideoModal(pageDriver);
		return video;
	}
	
	public Boolean isVideoPresent(String title){
		return this.isElementPresent(By.xpath("//*[@data-title=\""+title+"\"]"));
	}
	
	public String getExperimentImage(String title){
		return pageDriver.findElement(By.xpath("//*[@data-title=\""+title+"\"]/img")).getAttribute("src");
	}
	
	public void moveToVideo(String title){
		try {
			moveToElement(By.xpath("//*[@data-title=\""+title+"\"]"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
