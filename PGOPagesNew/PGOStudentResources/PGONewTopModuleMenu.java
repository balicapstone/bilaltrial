package PGOStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.PGONewBasePage;

public class PGONewTopModuleMenu extends PGONewBasePage{
	
	public By menuButton = By.id("hamburger-icon");
	public By animalsButton = By.xpath("//a[@data-siteid=\"1\"]"); //By.xpath("//*[@id=\"drop-menu\"]/li[1]/a");
	public By scienceButton = By.xpath("//a[@data-siteid=\"2\"]"); //By.xpath("//*[@id=\"drop-menu\"]/li[2]/a");
	public By cienciaButton = By.xpath("//a[@data-siteid=\"12\"]");
	public By biographiesButton = By.xpath("//a[@data-siteid=\"3\"]");//By.xpath("//*[@id=\"drop-menu\"]/li[3]/a");
	public By biografiasButton = By.xpath("//a[@data-siteid=\"13\"]");
	public By socialStudiesButton = By.xpath("//a[@data-siteid=\"5\"]");//By.xpath("//*[@id=\"drop-menu\"]/li[4]/a");
	public By estudiosSociales = By.xpath("//a[@data-siteid=\"14\"]");
	public By dinosaursButton = By.xpath("//a[@data-siteid=\"7\"]"); // By.xpath("//*[@id=\"drop-menu\"]/li[5]/a");
	public By animalesButton = By.xpath("//a[@data-siteid=\"8\"]");
	public By[] initialVisibleElements = {menuButton, animalsButton, scienceButton, 
			biographiesButton, socialStudiesButton, dinosaursButton};
	
	public PGONewTopModuleMenu(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public AnimalsContentPage clickAnimalsButton(){
		pageDriver.findElement(animalsButton).click();
		return new AnimalsContentPage(pageDriver);
	}
	
	public ScienceContentPage clickScienceButton(){
		pageDriver.findElement(scienceButton).click();
		return new ScienceContentPage(pageDriver);
	}
	
	public BiographiesContentPage clickBiographiesButton(){
		pageDriver.findElement(biographiesButton).click();
		return new BiographiesContentPage(pageDriver);
	}
	
	public SocialStudiesContentPage clickSocialStudiesButton(){
		pageDriver.findElement(socialStudiesButton).click();
		return new SocialStudiesContentPage(pageDriver);
	}
	
	public DinosaursContentPage clickDinosaursButton(){
		pageDriver.findElement(dinosaursButton).click();
		return new DinosaursContentPage(pageDriver);
	}
}
