package PGNModals;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PGNDictionaryModal extends PGNBaseModal{
	public By search = By.id("wordsearch");
	public By pronunciationButton = By.xpath("//*[@class='dict_audio_button']");
	public By title = By.xpath("//*[@id=\"modal-container\"]/div/h3");
	
	public PGNDictionaryModal(WebDriver driver){
		super(driver);
	}
	
	public PGNDictionaryModal search(String text){
		waitForElement(search);
		this.sendKeysToElement(text, search);
		return this;
	}
	
	public String getSearch(){
		return getElementText(search);
	}
	
	//If the user can click the pronunciation Button, this function works properly and return true, if not, error
	public Boolean pronounce(){
		click(pronunciationButton);
		return true;
	}
	
	public Boolean verifyResult(String text){
		ArrayList<WebElement> results = (ArrayList<WebElement>) pageDriver.findElements(By.tagName("h6"));
		
		for(WebElement r : results){
			if(r.getText().equals(text)){
				return true;
			}
		}
		
		return false;
	}
	
	public PGNDictionaryModal clickSuggestedWord(String suggested){
		click(By.xpath("//*[text()[contains(., '"+suggested+"')]]"));
		return this;
	}
	
	public String getTitle(){
		return getElementText(title);
	}
}
