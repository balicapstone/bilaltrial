package NoNonsense.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class FindOutMorePage extends BasePage{

	public By title = By.xpath("//*[@id=\"main\"]/div/div/h1");
	public By teachingSequencesTitle = By.xpath("//*[@id=\"main\"]/div/div/h3[1]");
	public By assessmentOfWritingTitle = By.xpath("//*[@id=\"main\"]/div/div/h3[2]");
	public By authorTeamTitle = By.xpath("//*[@id=\"main\"]/div/div/h3[3]");
	public By[] initialVisibleElements = {title, teachingSequencesTitle, assessmentOfWritingTitle, authorTeamTitle};
	
	public FindOutMorePage(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
