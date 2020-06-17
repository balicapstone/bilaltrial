package PGOStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PGONewBiographiesContentPage extends ContentSelectionPage{
	
	public By texasToggleOnButton = By.id("texas-toggle-on");
	public By texasToggleOffButton = By.id("texas-toggle-off");
	private String[] initialContentLinks = {"Authors and Artists", "Royalty and Nobility", "Athletes",
			"Explorers", "Inventors and Business Leaders", "History Makers", "Musicians and Entertainers",
			"Presidents and First Ladies", "Scientists", "Women", "African Americans", "American Indians", 
			"Hispanic Americans", "Asian Americans", "Supreme Court Justices"};
	private String[] texasContentLinks = {"Authors and Artists", "Explorers",
			"Inventors and Business Leaders", "History Makers", "Musicians and Entertainers",
			"Presidents and First Ladies", "Scientists", "Women", "African Americans", "American Indians", 
			"Hispanic Americans"};
	private String[] authorsAndArtistsContent = {"Authors", "Artists"};
	private String[] artistsContent = {"Dr. Seuss", "Georgia O'Keeffe", "Frida Kahlo", "Walt Disney",
			"Kadir Nelson", "Leonardo da Vinci", "Maurice Sendak"};
	
	public PGONewBiographiesContentPage(WebDriver driver) {
		super(driver);
	}
	
	public PGONewBiographiesContentPage ClickTexasToggleOffButton(){
		pageDriver.findElement(texasToggleOffButton).click();
		return this;
	}
	
	public PGONewBiographiesContentPage ClickTexasToggleOnButton(){
		pageDriver.findElement(texasToggleOnButton).click();
		return this;
	}
	
	public String[] getInitialContentLinks(){
		return initialContentLinks;
	}
	
	public String[] getTexasContentLinks(){
		return texasContentLinks;
	}
	
	public String[] getAuthorsAndArtistsContent(){
		return authorsAndArtistsContent;
	}
	
	public String[] getArtistsContent(){
		return artistsContent;
	}
}
