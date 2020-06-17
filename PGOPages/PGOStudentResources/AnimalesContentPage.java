package PGOStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AnimalesContentPage extends ContentSelectionPage{
	public By anfibiosLink = By.linkText("Anfibios");
	public By comportamientoAnimalLink = By.linkText("Comportamiento Animal");
	public By clasificacionDeAnimalesLink = By.linkText("Clasificación de Animales");
	public By habitatsDeAnimalesLink = By.linkText("Hábitats de Animales");
	public By avesLink = By.linkText("Aves");
	public By dinosauriosLink = By.linkText("Dinosaurios");
	public By pecesLink = By.linkText("Peces");
	public By insectosYAranasLink = By.linkText("Insectos y Arañas");
	public By invertebradosLink = By.linkText("Invertabrados");
	public By mamiferosLink = By.linkText("Mamiferos");
	public By mascotasLink = By.linkText("Mascotas y Animales de Granja");
	public By reptilesLink = By.linkText("Reptiles");
	
	public By[] initialVisibleElements = {anfibiosLink, comportamientoAnimalLink, clasificacionDeAnimalesLink, habitatsDeAnimalesLink, 
			avesLink, dinosauriosLink, pecesLink, insectosYAranasLink, invertebradosLink, mamiferosLink, mascotasLink, reptilesLink};
	private String[] initialLinks = {"Anfibios", "Comportamiento Animal", "Clasificación de Animales",
			"Hábitats de Animales", "Aves", "Dinosaurios", "Peces", "Insectos y Arañas", "Invertebrados",
			"Mamíferos", "Mascotas y Animales de Granja", "Reptiles"};
	private String[] anfibiosContent = {"Ranas","Necturos", "Salamandras", "Salamandras Comunes", "Sapos"};
	private String[] mascotasYAnimalesDeGranjaContent = {"Asnos", "Cerdos", "Gallinas", "Gansos", "Vacas", "Cabras", "Caballos", "Ovejas"};
	private String[] mascotasContent = {"Gatos", "Perros", "Hurones", "Cobayos", 
			"Hámsteres (esp)", "Cangrejos Ermitaños", "Aves Domésticas", "Peces Domésticos", "Conejos", "Gatos Persas", "Gatos Siameses"};
	private String[] perrosContent = {"Bichón Frisé", "Bulldog", "Chihuahua", "Cocker Spaniel", 
			"Collie", "Perro Salchicha", "Pastor Alemán", "Golden Retriever", "Labrador Retriever",
			"Caniche", "Carlino", "Shih Tzu"};
	
	
	public AnimalesContentPage(WebDriver driver){
		super(driver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public String[] getInitialContentLinks(){
		return initialLinks;
	}
	
	public String[] getAnfibiosContent(){
		return anfibiosContent;
	}
	
	public String[] getMascotasYAnimalesDGranjaContent(){
		return mascotasYAnimalesDeGranjaContent;
	}
	
	public String[] getMascotasContent(){
		return mascotasContent;
	}
	
	public String[] getPerrosContent(){
		return perrosContent;
	}
	

}
