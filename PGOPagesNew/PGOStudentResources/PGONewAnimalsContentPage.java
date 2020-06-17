package PGOStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PGONewAnimalsContentPage extends ContentSelectionPage{
	
	private By amphibiansLink = By.linkText("Amphibians");
	private By animalBehaviorLink = By.linkText("Animal Behavior");
	private By animalClassification = By.linkText("Animal Classification");
	private By animalHabitats = By.linkText("Animal Habitats");
	private By birdsLink = By.linkText("Birds");
	private By dinosaursLink = By.linkText("Dinosaurs");
	private By fishLink = By.linkText("Fish");
	private By insectsLink = By.linkText("Insects and Spiders");
	private By invertebratesLink = By.linkText("Invertebrates");
	private By mammalsLink = By.linkText("Mammals");
	private By petsLink = By.linkText("Pets and Farm Animals");
	private By reptilesLink = By.linkText("Reptiles");
	//private By teacherResourcesLink = By.xpath("//*[@id='footer-menu']/ul/li/a");
	private By[] initialVisibleElements = { amphibiansLink, animalBehaviorLink, animalClassification, 
			animalHabitats, birdsLink, dinosaursLink, fishLink, insectsLink, invertebratesLink, 
			mammalsLink, petsLink, reptilesLink};
	private String[] initialLinks = {"Amphibians", "Animal Behavior", "Animal Classification",
			"Animal Habitats", "Birds", "Dinosaurs", "Fish", "Insects and Spiders", "Invertebrates",
			"Mammals", "Pets and Farm Animals", "Reptiles"};
	private String[] amphibiansContent = {"Frogs","Mudpuppies", "Newts", "Salamanders", "Toads"};
	private String[] petsAndFarmAnimalsContent = {"Pets", "Farm Animals"};
	private String[] petsContent = {"Cats", "Dogs", "Ferrets", "Guinea Pigs", 
			"Hamsters", "Hermit Crabs", "Pet Birds", "Pet Fish", "Pet Rabbits"};
	private String[] dogsContent = {"Bichons Frises", "Bulldogs", "Chihuahuas", "Cocker Spaniels", 
			"Collies", "Dachshunds", "German Shepherds", "Golden Retrievers", "Labrador Retrievers",
			"Poodles", "Pugs", "Shih Tzus"};
	
	public PGONewAnimalsContentPage(WebDriver driver){
		super(driver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public String[] getInitialContentLinks(){
		return initialLinks;
	}
	
	public String[] getAmphibiansContent(){
		return amphibiansContent;
	}
	
	public String[] getPetsAndFarmAnimalsContent(){
		return petsAndFarmAnimalsContent;
	}
	
	public String[] getPetsContent(){
		return petsContent;
	}
	
	public String[] getDogsContent(){
		return dogsContent;
	}
}
