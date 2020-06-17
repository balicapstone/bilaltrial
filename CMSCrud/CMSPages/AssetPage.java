package CMSPages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import SharedClasses.BasePage;

public class AssetPage extends BasePage{
	public CMSToolbar toolbar;
	public By searchBar = By.id("test-input-search");
	public By submitButton = By.id("test-submit");
	
	
	public AssetPage(WebDriver driver){
		pageDriver = driver;
	}
	
	public void search(String text){
		pageDriver.findElement(searchBar).clear();
		pageDriver.findElement(searchBar).sendKeys(text);
		this.closeSendKeysTab();
		waitImplicitly(2);
		
		click(submitButton);
	}
	
	/*
	 * Verifies that both the mp3 text and mp3 interface is present.
	 */
	public Boolean isMP3Present(String mp3){
		ArrayList<WebElement> assetNames = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"asset\"]/div[3]/div[2]/div/p/strong"));
		Boolean present = false;
		
		int i = 1;
		for(WebElement w : assetNames){
			if(w.getText().equals(mp3)){
				present = true;
				break;
			}
			i++;
		}
		
		WebElement assetFile = pageDriver.findElement(By.xpath("//*[@id=\"asset\"]/div[3]/div[2]/div["+i+"]/audio/source"));
		
		if(assetFile.getAttribute("src").toString().contains(mp3)){
			present = true;
		}
		else{
			return false;
		}
		
		return present;
	}
	
	public String getMP3src(String mp3){
		ArrayList<WebElement> assetNames = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"asset\"]/div[3]/div[2]/div/p/strong"));
		
		int i = 1;
		for(WebElement w : assetNames){
			if(w.getText().equals(mp3)){
				break;
			}
			i++;
		}
		
		WebElement assetFile = pageDriver.findElement(By.xpath("//*[@id=\"asset\"]/div[3]/div[2]/div["+i+"]/audio/source"));
		
		return assetFile.getAttribute("src").toString();
	}
	
	/*
	 * Verifies that both the image text and image interface is present.
	 */
	public Boolean isImagePresent(String image){
		ArrayList<WebElement> assetNames = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"asset\"]/div[3]/div[2]/div/p/strong"));
		Boolean present = false;
		
		int i = 1;
		for(WebElement w : assetNames){
			if(w.getText().equals(image)){
				present = true;
				break;
			}
			i++;
		}
		
		WebElement assetFile = pageDriver.findElement(By.xpath("//*[@id=\"asset\"]/div[3]/div[2]/div["+i+"]/img"));
		
		if(assetFile.getAttribute("src").toString().contains(image)){
			present = true;
		}
		else{
			return false;
		}
		
		return present;
	}
	
	public String getImageSRC(String image){
		ArrayList<WebElement> assetNames = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"asset\"]/div[3]/div[2]/div/p/strong"));

		int i = 1;
		for(WebElement w : assetNames){
			if(w.getText().equals(image)){
				break;
			}
			i++;
		}
		
		WebElement assetFile = pageDriver.findElement(By.xpath("//*[@id=\"asset\"]/div[3]/div[2]/div["+i+"]/img"));
		
		return assetFile.getAttribute("src").toString();
	}
	
	/*
	 * Verifies that both the video text and video interface is present.
	 */
	public Boolean isVideoPresent(String video){
		ArrayList<WebElement> assetNames = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"asset\"]/div[3]/div[2]/div/p/strong"));
		Boolean present = false;
		
		int i = 1;
		for(WebElement w : assetNames){
			if(w.getText().equals(video)){
				present = true;
				break;
			}
			i++;
		}
		
		WebElement assetFile = pageDriver.findElement(By.xpath("//*[@id=\"asset\"]/div[3]/div[2]/div["+i+"]/video/source"));

		if(assetFile.getAttribute("src").toString().contains(video)){
			present = true;
		}
		else{
			return false;
		}
		
		return present;
	}
	
	public String getVideoSrc(String video){
		ArrayList<WebElement> assetNames = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"asset\"]/div[3]/div[2]/div/p/strong"));
		
		int i = 1;
		for(WebElement w : assetNames){
			if(w.getText().equals(video)){
				break;
			}
			i++;
		}
		
		WebElement assetFile = pageDriver.findElement(By.xpath("//*[@id=\"asset\"]/div[3]/div[2]/div["+i+"]/video/source"));
		
		return assetFile.getAttribute("src").toString();
	}
	
	/*
	 * Verifies that both the pdf link.
	 */
	public Boolean isPDFPresent(String pdf){
		ArrayList<WebElement> assetNames = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"asset\"]/div[3]/div[2]/div/p/strong"));
		Boolean present = false;

		for(WebElement w : assetNames){
			if(w.getText().equals(pdf)){
				present = true;
				break;
			}
		}		
		return present;
	}
	
	public String getPDFSrc(String pdf){
		ArrayList<WebElement> assetNames = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"asset\"]/div[3]/div[2]/div/p/strong"));
		
		int i = 1;
		for(WebElement w : assetNames){
			if(w.getText().equals(pdf)){
				break;
			}
			i++;
		}		
		
		WebElement assetFile = pageDriver.findElement(By.xpath("//*[@id=\"asset\"]/div[3]/div[2]/div["+i+"]/p/strong/a"));
		
		return assetFile.getAttribute("href").toString();
	}
	
	/*
	 * Verifies that both the xml link.
	 */
	public Boolean isXMLPresent(String xml){
		ArrayList<WebElement> assetNames = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"asset\"]/div[3]/div[2]/div/p/strong"));
		Boolean present = false;
		
		for(WebElement w : assetNames){
			if(w.getText().equals(xml)){
				present = true;
				break;
			}
		}		
		return present;
	}
	
	public String getXMLSrc(String pdf){
		ArrayList<WebElement> assetNames = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"asset\"]/div[3]/div[2]/div/p/strong"));
		
		int i = 1;
		for(WebElement w : assetNames){
			if(w.getText().equals(pdf)){
				break;
			}
			i++;
		}		
		
		WebElement assetFile = pageDriver.findElement(By.xpath("//*[@id=\"asset\"]/div[3]/div[2]/div["+i+"]/p/strong/a"));
		
		return assetFile.getAttribute("href").toString();
	}
}
