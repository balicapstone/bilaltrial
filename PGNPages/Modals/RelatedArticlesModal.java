package Modals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import PGNStudentResources.ArticlePage;
import PGNStudentResources.ArticleType;

public class RelatedArticlesModal extends BaseModal{
	
	public RelatedArticlesModal(WebDriver driver){
		super(driver);
	}
	
	public ArticlePage clickRelatedArticleByName(String name, ArticleType t){
		click(By.linkText(name));
		return new ArticlePage(pageDriver, t);
	}
	
	
}
