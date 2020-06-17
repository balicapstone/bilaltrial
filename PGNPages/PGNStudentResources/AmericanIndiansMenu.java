package PGNStudentResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import SharedClasses.BasePage;

public class AmericanIndiansMenu extends BasePage{
	
	public By articCultureAreaLink;
	public By culturalAreasLink;
	public By californiaCultureAreaLink;
	public By greatBasinCultureAreaLink;
	public By greatPlainsCultureAreaLink;
	public By northeastCultureAreaLink;
	public By northwestCultureAreaLink;
	public By plateauCultureAreaLink;
	public By southeastCultureAreaLink;
	public By southwestCultureAreaLink;
	public By subarcticCultureAreaLink;
	
	//For the items under the Cultural Areas category
	public By CA_ArticCultureAreaLink;
	public By CA_CaliforniaCultureAreaLink;
	public By CA_GreatBasinCultureAreaLink;
	public By CA_GreatPlainsCultureAreaLink;
	public By CA_NortheastCultureAreaLink;
	public By CA_NorthwestCoastCultureAreaLink;
	public By CA_PlateauCultureArea;
	public By CA_SoutheastCultureAreaLink;
	public By CA_SouthwestCultureAreaLink;
	public By CA_SubarcticCultureArea;
	
	public By articCultureAreaSubLink;
	public By inuitLink;
	
	public By californiaCultureAreaSubLink;
	public By chumashLink;
	public By coastMiwokLink;
	public By pomoLink;
	
	public By greatBasinCultureAreaSubLink;
	public By shoshoneLink;
	public By uteLink;
	
	public By greatPlainsCultureSubLink;
	public By arapahoLink;
	public By blackfeetLink;
	public By cheyenneLink;
	public By ComancheLink;
	public By crowLink;
	public By lakotaLink;
	public By osageLink;
	public By pawneeLink;
	public By siouxLink;
	
	public By northeastCultureAreaSubLink;
	public By abenakiLink;
	public By algonquinLink;
	public By lenapeLink;
	public By iroqiousLink;
	public By ojibwaLink;
	public By pequotLink;
	public By potawatomiLink;
	public By powhatanLink;
	public By shawneeLink;
	public By wampanoagLink;
	
	public By northwestCoastCultureAreaSubLink;
	public By chinookLink;
	public By tlingitLink;
	
	public By plateauCultureAreaSubLink;
	public By nezPerceLink;
	
	public By southeastCultureAreaSubLink;
	public By cherokeeLink;
	public By chickasawLink;
	public By choctawLink;
	public By creekLink;
	public By seminoleLink;
	public By nativeHawaiiansLink;
	
	public By southwestCultureAreaSubLink;
	public By apacheLink;
	public By hopiLink;
	public By navajoLink;
	public By puebloLink;
	public By zuniLink;
	
	public By subarcticCultureAreaSubLink;
	public By creeLink;
	
	public By[] initialVisibleElements = {articCultureAreaLink, culturalAreasLink, californiaCultureAreaLink, greatBasinCultureAreaLink,
	greatPlainsCultureAreaLink, northeastCultureAreaLink, northwestCultureAreaLink, plateauCultureAreaLink, southeastCultureAreaLink,
	southwestCultureAreaLink, subarcticCultureAreaLink};
	public By[] arcticCultureAreaLinks = {articCultureAreaSubLink, inuitLink};
	public By[] culturalAreaLinks = {CA_ArticCultureAreaLink, CA_CaliforniaCultureAreaLink, CA_GreatBasinCultureAreaLink, 
			CA_GreatPlainsCultureAreaLink, CA_NortheastCultureAreaLink, CA_NorthwestCoastCultureAreaLink, CA_PlateauCultureArea, 
			CA_SoutheastCultureAreaLink, CA_SouthwestCultureAreaLink, CA_SubarcticCultureArea};
	public By[] californiaCultureAreaLinks = {};
	public By[] greatBasinCultureAreaLinks = {};
	public By[] greatPlainsCultureAreaLinks = {};
	public By[] northeastCultureAreaLinks = {};
	public By[] northwestCultureAreaLinks = {};
	public By[] plateauCultureAreaLinks = {};
	public By[] southeastCultureAreaLinks = {};
	public By[] southwestCultureAreaLinks = {};
	public By[] subarcticCultureAreaLinks = {};
	
	public AmericanIndiansMenu(WebDriver driver){
		pageDriver = driver;
		articCultureAreaLink = By.linkText("Arctic Culture Area");
		culturalAreasLink = By.linkText("Cultural Areas");
		californiaCultureAreaLink = By.linkText("California Culture Area");
		greatBasinCultureAreaLink = By.linkText("Great Basin Culture Area");
		greatPlainsCultureAreaLink = By.linkText("Great Plains Culture Area");
		northeastCultureAreaLink = By.linkText("Northeast Culture Area");
		northwestCultureAreaLink = By.linkText("Northwest Coast Culture Area");
		plateauCultureAreaLink = By.linkText("Plateau Culture Area");
		southeastCultureAreaLink = By.linkText("Southeast Culture Area");
		southwestCultureAreaLink = By.linkText("Southwest Culture Area");
		subarcticCultureAreaLink = By.linkText("Subarctic Culture Area");
	}
	
	public void clickArcticCultureAreaLink(){
		articCultureAreaSubLink = By.xpath("");
		inuitLink = By.linkText("Inuit");
	}
	
	public void clickCulturalAreasLink(){
		CA_ArticCultureAreaLink = By.xpath("");
		CA_CaliforniaCultureAreaLink = By.xpath("");
		CA_GreatBasinCultureAreaLink = By.xpath("");
		CA_GreatPlainsCultureAreaLink = By.xpath("");
		CA_NortheastCultureAreaLink = By.xpath("");
		CA_NorthwestCoastCultureAreaLink = By.xpath("");
		CA_PlateauCultureArea = By.xpath("");
		CA_SoutheastCultureAreaLink = By.xpath("");
		CA_SouthwestCultureAreaLink = By.xpath("");
		CA_SubarcticCultureArea = By.xpath("");
	}
	
	public void clickCaliforniaCultureAreaLink(){
		californiaCultureAreaSubLink = By.xpath("");
		chumashLink = By.linkText("");
		coastMiwokLink = By.linkText("");
		pomoLink = By.linkText("");
	}
	
	public void clickGreatBasinCultureAreaLink(){
		greatBasinCultureAreaSubLink = By.xpath("");
		shoshoneLink = By.linkText("");
		uteLink = By.linkText("");
	}
	
	public void clickGreatPlainsCultureAreaLink(){
		greatPlainsCultureSubLink = By.xpath("");
		arapahoLink = By.linkText("Arapho");
		blackfeetLink = By.linkText("Blackfeet");
		cheyenneLink = By.linkText("Cheyenne");
		ComancheLink = By.linkText("Comanche");
		crowLink = By.linkText("Crow");
		lakotaLink = By.linkText("Lakota (Teton Sioux)");
		osageLink = By.linkText("Osage");
		pawneeLink = By.linkText("Pawnee");
		siouxLink = By.linkText("Sioux");
	}
	
	public void clickNorteastCultureAreaLink(){
		northeastCultureAreaSubLink = By.xpath("");
		abenakiLink = By.linkText("Abenaki");
		algonquinLink = By.linkText("Algonquin");
		lenapeLink = By.linkText("Lenape (Delaware)");
		iroqiousLink = By.linkText("Iroquois");
		ojibwaLink = By.linkText("Ojibwa");
		pequotLink = By.linkText("Pequot");
		potawatomiLink = By.linkText("Potawatomi");
		powhatanLink = By.linkText("Powhatan");
		shawneeLink = By.linkText("Shawnee");
		wampanoagLink = By.linkText("Wampanoag");
	}
	
	public void clickNorthwestCultureAreaLink(){
		northwestCoastCultureAreaSubLink = By.xpath("");
		chinookLink = By.linkText("Chinook");
		tlingitLink = By.linkText("Tlingit");
	}
	
	public void clickPlateauCultureArea(){
		plateauCultureAreaSubLink = By.xpath("");
		nezPerceLink = By.linkText("Nez Perce");
	}
	
	public void clickSoutheastCultureArea(){
		southeastCultureAreaSubLink = By.xpath("");
		cherokeeLink = By.linkText("Cherokee");
		chickasawLink = By.linkText("Chickasaw");
		choctawLink = By.linkText("Choctaw");
		creekLink = By.linkText("Creek");
		seminoleLink = By.linkText("Seminole");
		nativeHawaiiansLink = By.linkText("Native Hawaiians");
	}
	
	public void clickSouthwestCultureArea(){
		southwestCultureAreaSubLink = By.xpath("");
		apacheLink = By.linkText("Apache");
		hopiLink = By.linkText("Hopi");
		navajoLink = By.linkText("Navajo");
		puebloLink = By.linkText("Pueblo");
		zuniLink = By.linkText("Zuni");
	}
	
	public void clickSubarcticCultureArea(){
		subarcticCultureAreaSubLink = By.xpath("");
		creeLink = By.linkText("Cree");
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public By[] getArticCultureAreaLinks(){
		return arcticCultureAreaLinks;
	}
	
	public By[] getCulturalAreaLinks(){
		return culturalAreaLinks;
	}
	
	public By[] getCaliforniaCultureAreaLinks(){
		return californiaCultureAreaLinks;
	}
	
	public By[] getGreatBasinCultureAreaLinks(){
		return greatBasinCultureAreaLinks;
	}
	
	public By[] getGreatPlainsCultureAreaLinks(){
		return greatPlainsCultureAreaLinks;
	}
	
	public By[] getNortheastCultureAreaLinks(){
		return northeastCultureAreaLinks;
	}
	
	public By[] getzNorthwestCultureAreaLinks(){
		return northwestCultureAreaLinks;
	}
	
	public By[] getPlateauCultureAreaLinks(){
		return plateauCultureAreaLinks;
	}
	
	public By[] getSoutheastCultureaAreaLinks(){
		return southeastCultureAreaLinks;
	}
	
	public By[] getSouthwestCultureAreaLinks(){
		return southwestCultureAreaLinks;
	}
	
	public By[] getSubarcticCultureAreaLinks(){
		return subarcticCultureAreaLinks;
	}
}
