package ArticleModals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import SharedClasses.BasePage;

public class RangeMapModal extends BasePage{
	public By mapText = By.id("habitat-text");
	public By mapImage = By.id("habitat-frame");
	public By closeButton = By.id("btn-close-listen");
	
	public RangeMapModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public void closeModal(){
		click(closeButton);
		this.waitImplicitly(2);
	}
	
	public String getImageSource(){
		WebElement pic = pageDriver.findElement(By.xpath("//*[@id=\"habitat-frame\"]/img"));
		return pic.getAttribute("src").toString();		
	}
}
