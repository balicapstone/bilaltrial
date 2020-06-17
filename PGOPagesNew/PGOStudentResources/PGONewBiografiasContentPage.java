package PGOStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PGONewBiografiasContentPage extends ContentSelectionPage{
	public By texasToggleOnButton = By.id("texas-toggle-on");
	public By texasToggleOffButton = By.id("texas-toggle-off");
	private String[] initialContentLinks = {"Afroamericanos", "Científicos",
			"Deportistas", "Escritores y Artistas", "Estadounidenses de Origen Asiático", "Exploradores",
			"Hispanoestadounidenses", "Indígenas de los Estados Unidos", "Inventores y Líderes Empresarios", 
			"Jueces de la Corte Suprema", "Mujeres", "Músicos y Artistas", "Personas que Hicieron Historia",
			"Presidentes y Primeras Damas", "Realeza y Nobleza"};
	private String[] texasContentLinks = {"Afroamericanos", "Científicos",
			"Escritores y Artistas", "Exploradores", "Hispanoestadounidenses", "Indígenas de los Estados Unidos", 
			"Inventores y Líderes Empresarios", "Mujeres", "Músicos y Artistas", "Personas que Hicieron Historia",
			"Presidentes y Primeras Damas"};
	private String[] authorsAndArtistsContent = {"Authors", "Artists"};
	private String[] artistsContent = {"Dr. Seuss", "Georgia O'Keeffe", "Frida Kahlo", "Walt Disney",
			"Kadir Nelson", "Leonardo da Vinci", "Maurice Sendak"};
	
	public PGONewBiografiasContentPage(WebDriver driver) {
		super(driver);
	}
	
	public PGONewBiografiasContentPage ClickTexasToggleOffButton(){
		pageDriver.findElement(texasToggleOffButton).click();
		return this;
	}
	
	public PGONewBiografiasContentPage ClickTexasToggleOnButton(){
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
