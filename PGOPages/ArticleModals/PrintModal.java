package ArticleModals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;
import SharedClasses.ThirdPartyPage;

public class PrintModal extends BasePage{
	public By closeButton = By.id("btn-close-listen");
	public By printArticleButton = By.xpath("//*[@id=\"print-article-panel\"]/div[1]/div/div[1]/a/button");
	public By printImageButton = By.xpath("//*[@id=\"print-article-panel\"]/div[1]/div/div[2]/a/button");
	
	public PrintModal(WebDriver driver){
		pageDriver = driver;
		this.waitImplicitly(2);
	}
	
	public ThirdPartyPage printArticle(){
		click(printArticleButton);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage printImage(){
		click(printImageButton);
		return new ThirdPartyPage(pageDriver);
	}
	
	public void closeModal(){
		click(closeButton);
		this.waitImplicitly(2);
	}
}
