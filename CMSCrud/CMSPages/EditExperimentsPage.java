package CMSPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditExperimentsPage extends CMSHomePage{
	public By titleInput = By.id("test-input-title");
	public By videoFileInput = By.id("test-input-video");
	public By transcriptInput = By.id("test-input-transcript");
	public By imageFileInput = By.id("test-input-image");
	public By activeFlag = By.id("active");
	public By inactiveFlag = By.id("inactive");
	public By publishFlag = By.id("publish");
	public By unpublishFlag = By.id("unpublish");
	public By submitButton = By.id("test-submit-entry");
	public By closeButton = By.xpath("//*[@data-test=\"close\"]");
	public By createExperimentButton = By.xpath("//button[@data-test='create-video']");
	
	public EditExperimentsPage(WebDriver driver){
		super(driver);
	}
	
	public void editExperimentByName(String experiment){
		pageDriver.findElement(By.xpath("//button[@data-test=\"edit-"+experiment+"\"]")).click();//[@id="games"]/div/div[1]/button[2]
	}
	
	public void clickCreateExperiment(){
		click(createExperimentButton);
	}
	
	public void enterTitle(String title){
		pageDriver.findElement(titleInput).clear();
		pageDriver.findElement(titleInput).sendKeys(title);
	}
	
	public void enterVideoFile(String video){
		pageDriver.findElement(videoFileInput).clear();
		pageDriver.findElement(videoFileInput).sendKeys(video);
	}
	
	public void enterTranscriptFile(String file){
		pageDriver.findElement(transcriptInput).clear();
		pageDriver.findElement(transcriptInput).sendKeys(file);
	}
	
	public void enterImageFile(String file){
		pageDriver.findElement(imageFileInput).clear();
		pageDriver.findElement(imageFileInput).sendKeys(file);
	}
	
	public void clickActive(){
		try {
			moveToElement(activeFlag);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pageDriver.findElement(activeFlag).click();
	}
	
	public void clickInactive(){
		try {
			moveToElement(inactiveFlag);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pageDriver.findElement(inactiveFlag).click();
	}
	
	public void clickPublish(){
		try {
			moveToElement(publishFlag);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pageDriver.findElement(publishFlag).click();
	}
	
	public void clickUnpublish(){
		try {
			moveToElement(unpublishFlag);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pageDriver.findElement(unpublishFlag).click();
	}
	
	public void clickSubmit(){
		try {
			moveToElement(submitButton);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pageDriver.findElement(submitButton).click();
	}
	
	public void clickClose(){
		try {
			moveToElement(closeButton);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pageDriver.findElement(closeButton).click();
	}
}
