package ArticleModals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import SharedClasses.BasePage;

public class VideoModal extends BasePage{
	public By closeButton = By.id("btn-close-listen"); 
	public By videoDiv = By.xpath("//*[@id=\"video-frame\"]/div/video");
	
	public VideoModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public Boolean isVideoPresent(){
		return isElementPresent(videoDiv);
	}
	
	public void clickCloseButton(){
		click(closeButton);
		this.waitImplicitly(2);
	}
	
	public Boolean PlayVideo(){
		WebElement video = pageDriver.findElement(videoDiv);
		Boolean play = false;
		
		
		//if exectureScript does not return an object, the comman was not valid
		try{
		JavascriptExecutor js = (JavascriptExecutor) pageDriver;
		if(js.executeScript("arguments[0].play();", video) != null){
			play = true;
		}
		} catch (Exception e){
		
		}	
		return play;
	}
}
