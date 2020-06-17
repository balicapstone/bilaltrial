package PGOStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BiografiasContentPage extends ContentSelectionPage{
	public By texasToggleOnButton = By.id("texas-button");
	public By texasToggleOffButton = By.id("texas-button");
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
	
	public BiografiasContentPage(WebDriver driver) {
		super(driver);
	}
	
	public BiografiasContentPage ClickTexasToggleOffButton(){
		pageDriver.findElement(texasToggleOffButton).click();
		return this;
	}
	
	public BiografiasContentPage ClickTexasToggleOnButton(){
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
