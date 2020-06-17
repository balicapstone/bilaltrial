package Games;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PGOStudentResources.StudentHomePage;
import SharedClasses.BasePage;

public class PGOWhackPage extends BasePage{
	public By closeButton = By.id("btn-close");
	public By startButton = By.xpath("//*[@id=\"start-screen\"]/div/div"); //*[@id='start-screen']/div/div");
	public By gameInstruction = By.id("game-instruction");
	public By clueImage = By.xpath("//*[@id='display-wrapper']/div[1]/div");
	public By roundWrapper = By.id("rounds-wrapper");
	public By scoreContainer = By.className("scoreContainer"); 
	private By homeBreadcrumb = By.xpath("//*[@id=\"home-crumb\"]/a");
	public By[] initialVisibleElements = {closeButton, startButton, gameInstruction, 
			clueImage, roundWrapper, scoreContainer};
	
	public PGOWhackPage(WebDriver driver){
		pageDriver = driver;
	}
	
	public PGOChooseGamePage clickCloseButton(){
		click(closeButton);
		return new PGOChooseGamePage(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public PGOWhackPage clickStartButton(){
		click(startButton);
		return this;
	}
	
	public boolean verifyImagesAppearing(){
		List<WebElement> elements = pageDriver.findElements(By.className("imageAppended"));
		boolean answersAppearing = false;
		if(elements.size() > 0){
			answersAppearing = true;
		}
		return answersAppearing;
	}
	
	public StudentHomePage clickHomeBradcrumb(){
		click(homeBreadcrumb);
		return new StudentHomePage(pageDriver);
	}
	
	public Boolean doesImageAppear(String image){
		List<WebElement> elements = pageDriver.findElements(By.className("imageAppended"));
		boolean answerAppearing = false;
		for(WebElement w : elements){
			String style = w.getAttribute("style").toString();
			style = style.substring(style.lastIndexOf("/")+1, style.lastIndexOf("jpg")+3);
			if(style.equals(image)){
				answerAppearing = true;
				w.click();
				break;
			}
		}
		return answerAppearing;
	}
	
	public ArrayList<String> getImageAssets(){
		ArrayList<String> assets = new ArrayList<String>();
		
		List<WebElement> elements = pageDriver.findElements(By.className("imageAppended"));

		for(WebElement w : elements){
			String assetName = w.getAttribute("style").toString();
			assetName = assetName.substring(assetName.lastIndexOf("url")+5, assetName.lastIndexOf("\""));
			assets.add(assetName);
		}
		
		return assets;
	}
	
	public String getImageNameByInt(int i){
		int index = 1 + (2*i);
		WebElement web = pageDriver.findElement(By.xpath("//div[@class='scoreContainer']/div["+index+"]"));
		String image = web.getAttribute("style").toString();
		image = image.substring(image.lastIndexOf("/")+1, image.lastIndexOf("jpg")+3);
		return image;
	}
	
	public String getCorrectImageByInt(int i){
		int index = 2 + (2*i);
		WebElement web = pageDriver.findElement(By.xpath("//div[@class='scoreContainer']/div["+index+"]/span"));
		String name = web.getText();
		//*[@id="display-wrapper"]/div[2]/div[2]/span
		return name;
	}
}
