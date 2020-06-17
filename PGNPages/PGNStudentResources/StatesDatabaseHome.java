package PGNStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StatesDatabaseHome extends DatabasePage{
	public StudentHeader header;
	public By gamesLink = By.xpath("//*[@id=\"entertainment-buttons\"]/a[1]");
	
	public StatesDatabaseHome(WebDriver driver){
		super(driver);
		header = new StudentHeader(driver);
	}	
}
