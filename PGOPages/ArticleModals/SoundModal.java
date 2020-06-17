package ArticleModals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import SharedClasses.BasePage;

public class SoundModal extends BasePage{
	public By closeButton = By.id("btn-close-listen");
	public By audioFile = By.xpath("//*[@id=\"listen-frame\"]/audio");
	
	public SoundModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public Boolean clickPlayButton(){
		WebElement audio = pageDriver.findElement(audioFile);
		Boolean play = false;
		
		
		//if exectureScript does not return an object, the comman was not valid
		try{
		JavascriptExecutor js = (JavascriptExecutor) pageDriver;
		if(js.executeScript("arguments[0].play();", audio) != null){
			play = true;
		}
		} catch (Exception e){
		
		}	
		return play;
	}
	
	public String getAudioURL(){
		return pageDriver.findElement(By.xpath("//*[@id=\"listen-frame\"]/audio/source[2]")).getAttribute("src").toString();
	}
	
	public void closeModal(){
		click(closeButton);
		this.waitImplicitly(2);
	}
}
