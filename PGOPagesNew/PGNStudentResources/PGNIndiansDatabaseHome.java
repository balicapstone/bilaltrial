package PGNStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PGNIndiansDatabaseHome extends PGNDatabasePage{
	public PGNStudentHeader header;
	public By gamesLink = By.xpath("//*[@id=\"entertainment-buttons\"]/a[1]");
	
	public PGNIndiansDatabaseHome(WebDriver driver){
		super(driver);
		header = new PGNStudentHeader(driver);
	}
}
