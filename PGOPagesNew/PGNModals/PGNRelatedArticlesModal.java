package PGNModals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import PGNStudentResources.PGNArticlePage;
import PGNStudentResources.PGNArticleType;

public class PGNRelatedArticlesModal extends PGNBaseModal{
	
	public PGNRelatedArticlesModal(WebDriver driver){
		super(driver);
	}
	
	public PGNArticlePage clickRelatedArticleByName(String name, PGNArticleType t){
		click(By.linkText(name));
		return new PGNArticlePage(pageDriver, t);
	}
	
	
}
