package Games;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PGOStudentResources.StudentHomePage;
import SharedClasses.BasePage;

public class PGOMultiMatchPage extends BasePage{
	public By gameInstruction = By.id("game-instruction");
	public By closeButton = By.id("btn-close");
	public By questionInfo = By.xpath("//*[@id='predator-label']/span/span[1]");
	public By questionInfoPopup = By.xpath("//*[@id='predator-label']/span/span[2]/span");
	public By questionInfoPopupClose = By.xpath("//*[@id='predator-label']/span/span[2]/span/a");
	public By answerInfo = By.xpath("//*[@id='prey-label']/span/span[1]");
	public By answerInfoPopup = By.xpath("//*[@id='prey-label']/span/span[2]/span");
	public By answerInfoPopupClose = By.xpath("//*[@id='prey-label']/span/span[2]/span/a");
	public By roundsIndicator = By.id("rounds-indicator");
	public By predatorOne = By.id("predator-1");
	public By predatorTwo = By.id("predator-2");
	public By predatorThree = By.id("predator-3");
	public By predatorFour = By.id("predator-4");
	public By preyOne = By.id("prey-1");
	public By preyTwo = By.id("prey-2");
	public By preyThree = By.id("prey-3");
	public By preyFour = By.id("prey-4");
	private By homeBreadcrumb = By.xpath("//*[@id=\"home-crumb\"]/a");
	public By[] initialVisibleElements = {gameInstruction, closeButton, questionInfo, answerInfo, 
			roundsIndicator, predatorOne, predatorTwo, predatorThree, predatorFour, preyOne,
			preyTwo, preyThree, preyFour};
	public By[] afterClickElements = {questionInfoPopup, questionInfoPopupClose, answerInfoPopup, answerInfoPopupClose};
	
	public PGOMultiMatchPage(WebDriver driver){
		pageDriver = driver;
	}
	
	public PGOChooseGamePage clickCloseButton(){
		click(closeButton);
		return new PGOChooseGamePage(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public By[] getAfterClickElements(){
		return afterClickElements;
	}
	
	public PGOMultiMatchPage clickQuestionInfo(){
		click(questionInfo);
		return this;
	}
	
	public PGOMultiMatchPage clickAnswerInfo(){
		click(answerInfo);
		return this;
	}
	
	public PGOMultiMatchPage clickQuestionPopupClose(){
		click(questionInfoPopupClose);
		return this;
	}
	
	public PGOMultiMatchPage clickAnswerPopupClose(){
		click(answerInfoPopupClose);
		return this;
	}
	
	public StudentHomePage clickHomeBradcrumb(){
		click(homeBreadcrumb);
		return new StudentHomePage(pageDriver);
	}
	
	public String getPredatorImageByInt(int i){
		WebElement web = pageDriver.findElement(By.xpath("//*[@id=\"predator-"+i+"\"]/img"));
		String image = web.getAttribute("src").toString();
		image = image.substring(image.lastIndexOf("/")+1);
		return image;
	}
	
	public String getPredatorImageAssetByInt(int i){
		WebElement web = pageDriver.findElement(By.xpath("//*[@id=\"predator-"+i+"\"]/img"));
		return web.getAttribute("src").toString();
	}
	
	public String getPredatorNameByInt(int i){
		WebElement web = pageDriver.findElement(By.xpath("//*[@id=\"predator-"+i+"\"]/span"));
		return web.getText();
	}
	
	public String getPreyImageByInt(int i){
		WebElement web = pageDriver.findElement(By.xpath("//*[@id=\"prey-"+i+"\"]/img"));
		String image = web.getAttribute("src").toString();
		image = image.substring(image.lastIndexOf("/")+1);
		return image;
	}
	
	public String getPreyImageAssetByInt(int i){
		WebElement web = pageDriver.findElement(By.xpath("//*[@id=\"prey-"+i+"\"]/img"));
		return web.getAttribute("src").toString();
	}
	
	public String getPreyNameByInt(int i){
		WebElement web = pageDriver.findElement(By.xpath("//*[@id=\"prey-"+i+"\"]/span"));
		return web.getText();
	}
	
	public ArrayList<String> getAssets(){
		ArrayList<String> assets = new ArrayList<String>();
		
		assets.add(getPredatorImageAssetByInt(1));
		assets.add(getPredatorImageAssetByInt(2));
		assets.add(getPredatorImageAssetByInt(3));
		assets.add(getPredatorImageAssetByInt(4));
		assets.add(getPreyImageAssetByInt(1));
		assets.add(getPreyImageAssetByInt(2));
		assets.add(getPreyImageAssetByInt(3));
		assets.add(getPreyImageAssetByInt(4));
		
		return assets;
	}
}
