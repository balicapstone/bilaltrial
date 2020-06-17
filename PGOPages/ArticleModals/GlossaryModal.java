package ArticleModals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;
import UserClasses.User;

public class GlossaryModal extends BasePage{
	
	public By closeButton = By.id("btn-close-listen");
	public By playButton = By.xpath("//*[@id=\"glossary-panel\"]/div[1]/div/h3/div/div");
	
	
	public GlossaryModal(User u){
		pageDriver = u.getDriver();
	}
	
	public GlossaryModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public Boolean pressPlay(){
		return click(playButton);
	}
	
	public String getOratorData(){
		String data = pageDriver.findElement(By.xpath("//*[@id=\"glossary-panel\"]/div[1]/div/h3/div")).getAttribute("data-orator_audio");
		
		return data.substring(data.lastIndexOf("/")+1, data.length()-2);
	}
	
	public String getXMLData(){
		String data = pageDriver.findElement(By.xpath("//*[@id=\"glossary-panel\"]/div[1]/div/h3/div")).getAttribute("data-orator_timecode");
		
		return data.substring(0, data.lastIndexOf("?"));
	}
	
	public String getGlossaryText(String term){
		String text = pageDriver.findElement(By.xpath("//*[@id=\"glossary-panel\"]/div[1]/div/h3/div")).getText();
		
		return text.substring(term.length() + 2, text.length()).toString().trim();
	}
	
	public void closeModal(){
		click(closeButton);
	}
}
