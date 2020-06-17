package NoNonsense.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import SharedClasses.BasePage;
import SharedClasses.ThirdPartyPage;

public class ContentPopup extends BasePage{
	private By title = By.xpath("//*[@id=\"doc-modal\"]/div/div[1]"); 
	private By author = By.xpath("//*[@id=\"doc-modal\"]/div/div[2]");
	private By description = By.xpath("//*[@id=\"doc-modal\"]/div/div[3]");
	private By keyLearningOutcome = By.xpath("//*[@id=\"doc-modal\"]/div/div[4]");
	private By docxButton = By.xpath("//*[@id=\"doc-modal\"]/div/div[5]/div/a[2]/img");
	private By pdfButton = By.xpath("//*[@id=\"doc-modal\"]/div/div[5]/div/a[1]/img");
	private By keywords = By.xpath("//*[@id=\"doc-modal\"]/div/div[6]");
	private By modalContainer = By.id("modal-container");
	
	public ContentPopup(WebDriver driver){
		pageDriver = driver;
	}
	
	public String getModalTitle(){
		return pageDriver.findElement(title).getText();
	}
	
	public String getAuthor(){
		return pageDriver.findElement(author).getText();
	}
	
	public String getDescription(){
		return pageDriver.findElement(description).getText();
	}
	
	public String getKeyLearningOutcome(){
		return pageDriver.findElement(keyLearningOutcome).getText();
	}
	
	public ContentPopup clickDocxButton(){
		click(docxButton);
		return this;
	}
	
	public ThirdPartyPage clickPDFButton(){
		click(pdfButton);
		return new ThirdPartyPage(pageDriver);
	}
	
	public String getKeywords(){
		return pageDriver.findElement(keywords).getText();
	}
	
	public SearchPage clickOffModal(){
		click(modalContainer);
		
		return new SearchPage(pageDriver);
	}
	
}
