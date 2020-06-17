package PGOReadMoreNew;

import org.openqa.selenium.By;

import PGOStudentResources.PGONewArticlePage;
import SharedClasses.PGONewBasePage;
import UserClasses.User;

public class PGONewReadMoreLandingPage extends PGONewBasePage{
	public By playBookButton = By.id("play-book-button");
	public By closeButton = By.className("close-button-circle-folder-tab");
	public By bookButton = By.id("description-button");
	public By citeButton = By.id("cite-button");
	public By infoButton = By.id("info-button");
	
	public By printCitationButton = By.className("print-button-content");
	
	public PGONewReadMoreLandingPage(User u){
		user = u;
		pageDriver = u.getDriver();
	}
	
	public PGONewReadMorePlayer playBook(){
		click(playBookButton);
		return new PGONewReadMorePlayer(user);
	}
	
	public PGONewArticlePage closeLandingPage(){
		click(closeButton);
		return new PGONewArticlePage(user);
	}
}
