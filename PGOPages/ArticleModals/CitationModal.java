package ArticleModals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;
import SharedClasses.ThirdPartyPage;

public class CitationModal extends BasePage{
	public By closeButton = By.id("btn-close-listen");
	public By printButton = By.linkText("Print");
	
	public CitationModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public ThirdPartyPage printCitation(){
		click(printButton);
		return new ThirdPartyPage(pageDriver);
	}
	
	public void close(){
		click(closeButton);
		this.waitImplicitly(2);
	}
}
