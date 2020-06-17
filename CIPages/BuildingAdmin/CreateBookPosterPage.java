package BuildingAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateBookPosterPage extends BuildingAdminDashboard{
	public By createPosterButton = By.id("booklist-book-filter");
	public By resest = By.id("booklist-book-reset");
	
	public CreateBookPosterPage(WebDriver driver){
		super(driver);
	}
	
	public void createPosterByBookID(String id){
		click(By.xpath("//tr[@data-id=\""+id+"\"]/td[3]/input"));
	}
	
	public Boolean isCreatePosterClickable(){
		try{
			ExpectedConditions.elementToBeClickable(createPosterButton);
			return true;
		} catch(Exception e){
			return false;
		}
		
	}
}
