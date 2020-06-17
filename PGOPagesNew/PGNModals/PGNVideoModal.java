package PGNModals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.ThirdPartyPage;

public class PGNVideoModal extends PGNBaseModal{
	public By downloadTranscriptButton = By.xpath("//*[@id=\"video-experiments\"]/div/a"); //By.linkText("Download Transcript");
	public By videoSource = By.xpath("//*[@id=\"video-experiments\"]/video/source");
	
	public PGNVideoModal(WebDriver driver){
		super(driver);
	}
	
	public ThirdPartyPage downloadTranscript(){
		click(downloadTranscriptButton);
		return new ThirdPartyPage(pageDriver);
	}
	
	public String getVideoString(){
		return pageDriver.findElement(videoSource).getAttribute("src");
	}
	
	public String getTranscriptString(){
		return pageDriver.findElement(downloadTranscriptButton).getAttribute("href");
	}
}
