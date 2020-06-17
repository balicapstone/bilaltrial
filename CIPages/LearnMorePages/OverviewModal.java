package LearnMorePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class OverviewModal extends BasePage{
	public By previewAlbum = By.xpath("//*[@id=\"info-content\"]/div[1]/div[2]/img");
	
	public OverviewModal(WebDriver driver){
		pageDriver = driver;
	}
}
