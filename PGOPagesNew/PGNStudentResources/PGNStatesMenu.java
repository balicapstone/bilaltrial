package PGNStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;


public class PGNStatesMenu extends BasePage{
	
	public By northeastLink = By.linkText("Northeast");
	public By southeastLink = By.linkText("Southeast");
	public By midwestLink = By.linkText("Midwest");
	public By southwestLink = By.linkText("Southwest");
	public By westLink = By.linkText("West");
	
	public By maineLink;
	public By newHampshireLink;
	public By vermontLink;
	public By massachusettsLink;
	public By connecticuLink;
	public By rhodeIslandLink;
	public By newYorkLink;
	public By pennsylvaniaLink;
	public By newJerseyLink;
	
	public By marylandLink;
	public By delawareLink;
	public By alabamaLink;
	public By arkansasLink;
	public By floridaLink;
	public By georgiaLink;
	public By kentuckyLink;
	public By louisianaLink;
	public By mississippiLink;
	public By northCarolinaLink;
	public By southCarolinaLink;
	public By tennesseeLink;
	public By virginiaLink;
	public By washingtonDCLink;
	public By westVirginiaLink;
	public By puertoRicoLink;
	
	public By northDakotaLink;
	public By southDakotaLink;
	public By nebraskaLink;
	public By kansasLink;
	public By minnesotaLink;
	public By iowaLink;
	public By missouriLink;
	public By wisconsinLink;
	public By illinoisLink;
	public By indianaLink;
	public By michiganLink;
	public By ohioLink;
	
	public By arizonaLink;
	public By newMexicoLink;
	public By texasLink;
	public By oklahomaLink;
	
	public By washingtonLink;
	public By oregonLink;
	public By californiaLink;
	public By idahoLink;
	public By nevadaLink;
	public By utahLink;
	public By montanaLink;
	public By wyomingLink;
	public By coloradoLink;
	public By alaskaLink;
	public By hawaiiLink;
	
	public By[] northeastStates = {maineLink, newHampshireLink, vermontLink, massachusettsLink, connecticuLink,
			rhodeIslandLink, newYorkLink, pennsylvaniaLink, newJerseyLink};
	public By[] southeastStates = {marylandLink, delawareLink, alabamaLink, arkansasLink, floridaLink, georgiaLink, kentuckyLink,
			louisianaLink, mississippiLink, northCarolinaLink, southCarolinaLink, tennesseeLink, virginiaLink, washingtonDCLink,
			westVirginiaLink, puertoRicoLink};
	public By[] midwestStates = {northDakotaLink, southDakotaLink, nebraskaLink, kansasLink, minnesotaLink, iowaLink, missouriLink,
			wisconsinLink, illinoisLink, indianaLink, michiganLink, ohioLink};
	public By[] southwestStates = {arizonaLink, newMexicoLink, texasLink, oklahomaLink};
	public By[] westStates = {washingtonLink, oregonLink, californiaLink, idahoLink, nevadaLink, utahLink, montanaLink,
			wyomingLink, coloradoLink, alaskaLink, hawaiiLink};

	
	public PGNStatesMenu(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getNortheastStates(){
		return northeastStates;
	}
	
	public By[] getSoutheastStates(){
		return southeastStates;
	}
	
	public By[] getMidwestStates(){
		return midwestStates;
	}
	
	public By[] getSouthwestStates(){
		return southwestStates;
	}
	
	public By[] getWestStates(){
		return westStates;
	}
	
	public void clickNortheastLink(){
		click(northeastLink);
		maineLink = By.linkText("Maine");
		newHampshireLink = By.linkText("New Hampshire");
		vermontLink = By.linkText("Vermont");
		massachusettsLink = By.linkText("Massachusetts");
		connecticuLink = By.linkText("Connecticut");
		rhodeIslandLink = By.linkText("Rhode Island");
		newYorkLink = By.linkText("New York");
		pennsylvaniaLink = By.linkText("Pennsylvania");
		newJerseyLink = By.linkText("New Jersey");
	}
	
	public void clickSoutheastLink(){
		click(southeastLink);
		marylandLink = By.linkText("Maryland");
		delawareLink = By.linkText("Delaware");
		alabamaLink = By.linkText("Alabama");
		arkansasLink = By.linkText("Arkansas");
		floridaLink = By.linkText("Florida");
		georgiaLink = By.linkText("Georgia");
		kentuckyLink = By.linkText("Kentucky");
		louisianaLink = By.linkText("Louisiana");
		mississippiLink = By.linkText("Mississippi");
		northCarolinaLink = By.linkText("North Carolina");
		southCarolinaLink = By.linkText("South Carolina");
		tennesseeLink = By.linkText("Tennessee");
		virginiaLink = By.linkText("Virginia");
		washingtonDCLink = By.linkText("Washington, D.C.");
		westVirginiaLink = By.linkText("West Virginia");
		puertoRicoLink = By.linkText("Puerto Rico");
	}
	
	public void clickMidwestLink(){
		click(midwestLink);
		northDakotaLink = By.linkText("North Dakota");
		southDakotaLink = By.linkText("South Dakota");
		nebraskaLink = By.linkText("Nebraska");
		kansasLink = By.linkText("Kansas");
		minnesotaLink = By.linkText("Minnesota");
		iowaLink = By.linkText("Iowa");
		missouriLink = By.linkText("Missouri");
		wisconsinLink = By.linkText("Wisconsin");
		illinoisLink = By.linkText("Illinois");
		indianaLink = By.linkText("Indiana");
		michiganLink = By.linkText("Michigan");
		ohioLink = By.linkText("Ohio");
	}
	
	public void clickSouthwestLink(){
		click(southwestLink);
		arizonaLink = By.linkText("Arizona");
		newMexicoLink = By.linkText("New Mexico");
		texasLink = By.linkText("Texas");
		oklahomaLink = By.linkText("Oklahoma");
	}
	
	public void clickWestLink(){
		click(westLink);
		washingtonLink = By.linkText("Washington");
		oregonLink = By.linkText("Oregon");
		californiaLink = By.linkText("California");
		idahoLink = By.linkText("Idaho");
		nevadaLink = By.linkText("Nevada");
		utahLink = By.linkText("Utah");
		montanaLink = By.linkText("Montana");
		wyomingLink = By.linkText("Wyoming");
		coloradoLink = By.linkText("Colorado");
		alaskaLink = By.linkText("Alaska");
		hawaiiLink = By.linkText("Hawaii");
	}
}
