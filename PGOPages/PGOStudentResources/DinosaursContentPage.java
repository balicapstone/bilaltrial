package PGOStudentResources;

import org.openqa.selenium.WebDriver;

public class DinosaursContentPage extends ContentSelectionPage{
	
	private String[] initialContentLinks = {"Beaked Dinosaurs", 
			"Bird-Like Dinosaurs", "Long-Necks"};
	private String[] beakedDinosaursContent = {"Armored Dinosaurs", "Bird-Foot Dinosaurs",
			"Bone-Heads", "Duckbills", "Horned Dinosaurs", "Plated Dinosaurs"};
	private String[] platedDinosaursContent = {"Chungkingosaurus", "Gigantspinosaurus", "Hesperosaurus", 
			"Huayangosaurus", "Kentrosaurus", "Lexovisaurus", "Stegosaurus", "Tuojiangosaurus",
			"Yingshanosaurus"};
	
	public DinosaursContentPage(WebDriver driver) {
		super(driver);
	}
	
	public String[] getInitialContentLinks(){
		return initialContentLinks;
	}
	
	public String[] getBeakedDinosaursContent(){
		return beakedDinosaursContent;
	}
	
	public String[] getPlatedDinosaursContent(){
		return platedDinosaursContent;
	}
}
