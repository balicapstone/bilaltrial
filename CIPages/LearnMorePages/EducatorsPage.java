package LearnMorePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EducatorsPage extends LearnMoreBasePage{
	public By researchLibraryLink = By.linkText("Research Library");
	public By[] initialVisibleElements = {researchLibraryLink};
	
	public EducatorsPage(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
