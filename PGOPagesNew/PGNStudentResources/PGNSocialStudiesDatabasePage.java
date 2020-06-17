package PGNStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PGNSocialStudiesDatabasePage extends PGNDatabasePage{
	public PGNStudentHeader header;
	public By gamesLink = By.xpath("//*[@id=\"entertainment-buttons\"]/a[1]");
	
	public PGNSocialStudiesDatabasePage(WebDriver driver){
		super(driver);
		header = new PGNStudentHeader(driver);
	}	
}
