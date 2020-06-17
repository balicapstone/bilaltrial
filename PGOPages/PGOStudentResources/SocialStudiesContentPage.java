package PGOStudentResources;

import org.openqa.selenium.WebDriver;

public class SocialStudiesContentPage extends ContentSelectionPage{
	private String[] initialContentLinks = {"My World", "Long Ago and Today",  
			"Holidays", "Maps", "People and the Environment", "All about Money",
			"Jobs in the Community", "Being a Good Citizen",  
			"Families", "Culture and Awareness"};
	private String[] myWorldContent = {"In My Home", "In My Neighborhood", "In My Town",
			"In My Continent", "In My World"};
	private String[] cultureAndAwarenessContent = {"Customs around the World", "Understanding Disabilities"};
	private String[] understandingDisabilitiesContent = {"ADHD", "Autism", "Blindness", "Deafness", 
			"Down Syndrome", "Dyslexia", "Using Leg Braces", "Using Wheelchairs"};
		
	//"U.S. Symbols", "U.S. Government",
	
	public SocialStudiesContentPage(WebDriver driver){
		super(driver);
	}
	
	public String[] getInitialContentLinks(){
		return initialContentLinks;
	}
	
	public String[] getMyWorldContent(){
		return myWorldContent;
	}
	
	public String[] getCultureAndAwarenessContent(){
		return cultureAndAwarenessContent;
	}
	
	public String[] getUnderstandingDisabilitiesContent(){
		return understandingDisabilitiesContent;
	}
}
