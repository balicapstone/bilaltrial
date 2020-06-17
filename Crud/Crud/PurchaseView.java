package Crud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PurchaseView extends DatabasePage{
	private By createNewPurchase = By.xpath("/html/body/div/div[2]/button[1]");
	//private By searchPurchases = By.id("text-search");
	//private By filterByRole = By.xpath("/html/body/div/div[2]/button[2]");
	
	public PurchaseView(WebDriver driver){
		super(driver);
	}
	
	public PurchaseModal createNewPurchase(){
		click(createNewPurchase);
		return new PurchaseModal(pageDriver);
	}
	
	public OrderModal editOrderByIndex(int i){
		int index = i + 1;
		click(By.xpath("/html/body/div/table/tbody/tr["+index+"]/td[2]/button"));
		return new OrderModal(pageDriver);
	}
	
	public BuildingsView clickBackButton(){
		click(back);
		return new BuildingsView(pageDriver);
	}
}
