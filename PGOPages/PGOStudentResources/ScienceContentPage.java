package PGOStudentResources;

import org.openqa.selenium.WebDriver;


public class ScienceContentPage extends ContentSelectionPage{
	private String[] initialContentLinks = {"Earth and Space Sciences", "Physical Sciences",
			"Life Sciences", "Science and Engineering Practices"};
	private String[] earthAndSpaceSciencesContent = {"Earth Science",
			"Weather", "Seasons", "Space Science"};
	private String[] earthScienceContent = {"All About Water", "Earth Features", "Earth in Action",
			"Earth Scientists", "Ecosystems", "Landforms", "Natural Resources"}; 
	private String[] allAboutWaterContent = {"Lakes", "Oceans", "Rivers", "The Water Cycle"};
	
	
	public ScienceContentPage(WebDriver driver){
		super(driver);
	}
	
	public String[] getInitialContentLinks(){
		return initialContentLinks; 
	}
	
	public String[] getEarthAndSpaceSciencesContent(){
		return earthAndSpaceSciencesContent;
	}
	
	public String[] getEarthScienceContent(){
		return earthScienceContent;
	}
	
	public String[] getAllAboutWaterContent(){
		return allAboutWaterContent;
	}
}
