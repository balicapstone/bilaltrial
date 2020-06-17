package MasterAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class SorryModal extends BasePage{
	public By continueLink = By.xpath("//*[@id=\"note-bottom\"]/a");///span //*[@id="note-bottom"]/a 
	public By continueProduct = By.className("this-product");//("//*[@id=\"note-bottom\"]/a/span/text()"); ////*[@id="note-bottom"]/div/span/
	public By takeMeBackLink = By.xpath("//div[@class='close-sorry']");//*[@id=\"note-bottom\"]/div"); //*[@id="note-bottom"]/div
	public By takeMeBackProduct = By.className("clicked-product"); //.xpath("//*[@id=\"note-bottom\"]/div/span/text()"); //*[@id="note-bottom"]/a/span //*[@id="note-bottom"]/div/span //*[@id="note-bottom"]/div/span
	
	public SorryModal(WebDriver driver){
		pageDriver = driver; //*[@id="note-bottom"]/div/text()
	}
	
	public String getContinueLinkText(){
		return getModalElement(continueLink).getText();
	}
	
	public String getContinueProductText(){
		return getModalElement(continueProduct).getText();
	}
	
	public String getTakeMeBackLinkText(){
		return getModalElement(takeMeBackLink).getText();
	}
	
	public String getTakeMeBackProductText(){
		return getModalElement(takeMeBackProduct).getText();
	}
	
	public String getPGNContinueLinkText(){
		return pageDriver.findElement(continueLink).getText();
	}
	
	public String getPGNTakeMeBackLinkText(){
		return pageDriver.findElement(takeMeBackLink).getText();
	}
	
	/*
	public BasePage continueToProduct(ProductType product){
		click(continueLink);
		BasePage base = null;
		switch(product){
			case PGN:
				base = new PGNStudentResources.StudentHomePage(pageDriver);
				break;
			case PGO:
				base = new PGOStudentResources.StudentHomePage(pageDriver);
				break;
			case CI:
				base = new CITests.CIPages.StudentHomePage(pageDriver);
				break;
			default:
				base = new PGNStudentResources.StudentHomePage(pageDriver);
		}	
		return new BasePage();
	}
	*/
	
	public void returnToProduct(){
		getModalElement(takeMeBackLink).click();
		waitImplicitly(2);
	}
	
	public void returnToPGN(){
		pageDriver.findElement(takeMeBackLink).click();
		waitImplicitly(2);
	}
}
