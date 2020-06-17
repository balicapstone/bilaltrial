package CMSPages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UploadExportPage extends CMSHomePage{
	public By uploadModulesButton = By.xpath("//*[@id=\"test-form\"]/fieldset/label/span"); //By.id("test-input-file");
	public By validateAllModulesButton = By.id("test-export-all");
	public By validateSelectedButton = By.id("test-export-selected");
	
	public By uploadTimefileButton = By.id("test-timefile-input");
	public By uploadImagesButton = By.id("test-images-input");
	public By uploadAudioButton = By.id("test-audio-input");
	public By uploadVideoButton = By.id("test-video-input");
	public By uploadPDFButton = By.id("test-pdf-input");
	
	public By submitButton = By.xpath("//div[contains(@class,'pure-control-group')]/button");
	
	public UploadExportPage(WebDriver driver){
		super(driver);
	}
	
	public void clickUploadModulesButton(){
		click(uploadModulesButton);
	}
	
	public void clickValidateAllModules(){
		this.scrollToElement(pageDriver.findElement(validateAllModulesButton));
		click(validateAllModulesButton);
	}
	
	public void clickExportSelectedModules(){
		this.scrollToElement(pageDriver.findElement(validateSelectedButton));
		click(validateSelectedButton);
	}
	
	public void uploadTimefile(String path){
		pageDriver.findElement(uploadTimefileButton).sendKeys(path);
		
		click(submitButton);
	}
	
	public void uploadImage(String path){
		pageDriver.findElement(uploadImagesButton).sendKeys(path);
		
		click(submitButton);
	}
	
	public void uploadAudio(String path){
		pageDriver.findElement(uploadAudioButton).sendKeys(path);
		
		click(submitButton);
	}
	
	public void uploadVideo(String path){
		pageDriver.findElement(uploadVideoButton).sendKeys(path);
		
		click(submitButton);
	}
	
	public void uploadPDF(String path){
		pageDriver.findElement(uploadPDFButton).sendKeys(path);
		
		click(submitButton);
	}
	
	public void clickExportByTitle(String text){
		ArrayList<WebElement> modules = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"worddocx\"]/div[2]/div[2]/table/tbody/tr/td[1]"));
		
		int i = 1;
		for(WebElement w : modules){
			if(w.getText().equals(text)){
				pageDriver.findElement(By.xpath("//*[@id=\"worddocx\"]/div[2]/div[2]/table/tbody/tr["+i+"]/td[2]/input")).click();
			}
			i++;
		}
	}
	
	public Boolean doesModuleExistOnPage(String text){
		ArrayList<WebElement> modules = (ArrayList<WebElement>) pageDriver.findElements(By.xpath("//*[@id=\"worddocx\"]/div[3]/div[2]/table/tbody/tr/td[1]"));
		
		for(WebElement w : modules){
			if(w.getText().equals(text)){
				return true;
			}
		}
		
		return false;
	}
	
	public Boolean isFileUloaded(){
		//*[@id="test-form"]/fieldset/ul/li
		//*[@id="test-form"]/div/div/div
		if(pageDriver.findElement(By.xpath("//*[@id=\"test-form\"]/div/div/div")).getText().equals("100%")){
			return true;
		}
		else{
			return false;
		}
	}
}
