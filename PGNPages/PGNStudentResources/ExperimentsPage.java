package PGNStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import MasterAccount.LinkAccountsToolbar;
import Modals.VideoModal;
import SharedClasses.BasePage;

public class ExperimentsPage extends BasePage{

	public VideoModal video;
	public StudentHeader header;
	public LinkAccountsToolbar laToolbar;
	
	public ExperimentsPage(WebDriver driver){
		pageDriver = driver;
		header = new StudentHeader(driver);
		laToolbar = new LinkAccountsToolbar(driver);
	}
	
	public VideoModal clickVideoByTitle(String title){
		getModalElement(By.xpath("//*[@data-title=\""+title+"\"]")).click();
		video = new VideoModal(pageDriver);
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
