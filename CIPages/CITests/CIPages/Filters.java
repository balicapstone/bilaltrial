package CITests.CIPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import SharedClasses.BasePage;

public class Filters extends BasePage{
	
	public By gradeRangeToggle = By.xpath("//*[@id=\"grade-level\"]/div/span");
	public By guidedReadingToggle = By.xpath("//*[@id=\"guided-reading-range\"]/div/span");
	public By aTOSToggle = By.xpath("//*[@id=\"atos\"]/div/span");
	public By lexileRangeToggle = By.xpath("//*[@id=\"lexile-range\"]/div/span");
	public By scotPLevelsToggle = By.xpath("//*[@id=\"scot-range\"]/div/span");
	public By welshKSLevelToggle = By.xpath("//*[@id=\"welsh-range\"]/div/span");
	
	//Grade Range Filters
	public By preKFilter = By.xpath("//*[@id=\"grade-level\"]/ul/li[1]");
	public By kFilter = By.xpath("//*[@id=\"grade-level\"]/ul/li[2]");	
	public By gradeOneFilter = By.xpath("//*[@id=\"grade-level\"]/ul/li[3]");
	public By gradeTwoFilter = By.xpath("//*[@id=\"grade-level\"]/ul/li[4]");
	public By gradeThreeFilter = By.xpath("//*[@id=\"grade-level\"]/ul/li[5]");
	public By gradeFourFilter = By.xpath("//*[@id=\"grade-level\"]/ul/li[6]");
	public By gradeFiveFilter = By.xpath("//*[@id=\"grade-level\"]/ul/li[7]");
	public By gradeSixPlusFilter = By.xpath("//*[@id=\"grade-level\"]/ul/li[8]");
	
	//Guided Reading Level Filters
	public By ADFilter = By.xpath("//*[@id=\"guided-reading-range\"]/ul/li[1]");
	public By EIFilter = By.xpath("//*[@id=\"guided-reading-range\"]/ul/li[2]");
	public By JNFilter = By.xpath("//*[@id=\"guided-reading-range\"]/ul/li[3]");
	public By OQFilter = By.xpath("//*[@id=\"guided-reading-range\"]/ul/li[4]");
	public By RTFilter = By.xpath("//*[@id=\"guided-reading-range\"]/ul/li[5]");
	public By UWFilter = By.xpath("//*[@id=\"guided-reading-range\"]/ul/li[6]");
	public By XZFilter = By.xpath("//*[@id=\"guided-reading-range\"]/ul/li[7]");
	
	//AR Level Filters
	public By OneOrLessFilter = By.xpath("//*[@id=\"atos\"]/ul/li[1]");
	public By OneTwoFilter = By.xpath("//*[@id=\"atos\"]/ul/li[2]");
	public By TwoThreeFilter = By.xpath("//*[@id=\"atos\"]/ul/li[3]");
	public By ThreeFourFilter = By.xpath("//*[@id=\"atos\"]/ul/li[4]");
	public By FourFiveFilter = By.xpath("//*[@id=\"atos\"]/ul/li[5]");
	public By FiveSixFilter = By.xpath("//*[@id=\"atos\"]/ul/li[6]");
	public By SixPlusFilter = By.xpath("//*[@id=\"atos\"]/ul/li[7]");
	
	//Lexile Range Filters
	public By LexileLessThan450 = By.xpath("//*[@id=\"lexile-range\"]/ul/li[1]");
	public By Lexile450To790 = By.xpath("//*[@id=\"lexile-range\"]/ul/li[2]");
	public By Lexile770To980 = By.xpath("//*[@id=\"lexile-range\"]/ul/li[3]");
	public By Lexile955To1155 = By.xpath("//*[@id=\"lexile-range\"]/ul/li[4]");
	
	//Scot P Filters
	public By nurseryFilter = By.xpath("//*[@id=\"scot-range\"]/ul/li[1]");
	public By p1Filter = By.xpath("//*[@id=\"scot-range\"]/ul/li[2]");
	public By p2Filter = By.xpath("//*[@id=\"scot-range\"]/ul/li[3]");
	public By p3Filter = By.xpath("//*[@id=\"scot-range\"]/ul/li[4]");
	public By p4Filter = By.xpath("//*[@id=\"scot-range\"]/ul/li[5]");
	public By p5Filter = By.xpath("//*[@id=\"scot-range\"]/ul/li[6]");
	public By p6Filter = By.xpath("//*[@id=\"scot-range\"]/ul/li[7]");
	public By p7Filter = By.xpath("//*[@id=\"scot-range\"]/ul/li[8]");
	
	//Welsh KS Filters
	public By foundationFilter = By.xpath("//*[@id=\"welsh-range\"]/ul/li[1]");
	public By kS1Filter = By.xpath("//*[@id=\"welsh-range\"]/ul/li[2]");
	public By kS2Filter = By.xpath("//*[@id=\"welsh-range\"]/ul/li[3]");
	public By kS3Filter = By.xpath("//*[@id=\"welsh-range\"]/ul/li[4]");
	public By kS4Filter = By.xpath("//*[@id=\"welsh-range\"]/ul/li[5]");
	
	public By englishCheckbox = By.id("language-english");
	public By spanishCheckbox = By.id("language-spanish");
	//*[@id="lexile-range"]/ul/li[1]
	public Filters(WebDriver driver){
		pageDriver = driver;
	}
	
	public boolean isGradeRangeOpen(){
		boolean open;
		WebElement element = pageDriver.findElement(By.xpath("//*[@id=\"grade-level\"]/div"));
		
		if(element.getAttribute("class").toString().equals("option active")){
			open = true;
		}
		else{
			open = false;
		}
		
		return open;
	}
	
	public boolean isGuidedReadingOpen(){
		boolean open;
		WebElement element = pageDriver.findElement(By.xpath("//*[@id=\"guided-reading-range\"]/div"));
		
		if(element.getAttribute("class").toString().equals("option active")){
			open = true;
		}
		else{
			open = false;
		}
		
		return open;
	}
	
	public boolean isATOSOpen(){
		boolean open;
		WebElement element = pageDriver.findElement(By.xpath("//*[@id=\"atos\"]/div"));

		if(element.getAttribute("class").toString().equals("option active")){
			open = true;
		}
		else{
			open = false;
		}
		
		return open;
	}
	
	public boolean isLexileRangeOpen(){
		boolean open;
		WebElement element = pageDriver.findElement(By.xpath("//*[@id=\"lexile-range\"]/div"));

		if(element.getAttribute("class").toString().equals("option active")){
			open = true;
		}
		else{
			open = false;
		}
		
		return open;
	}
}
