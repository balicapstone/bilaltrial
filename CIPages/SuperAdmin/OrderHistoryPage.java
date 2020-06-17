package SuperAdmin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import SharedClasses.BasePage;

public class OrderHistoryPage extends BasePage{
	public By accountDropdown = By.id("order-filter-account");
	public By buildingDropdown = By.id("order-filter-building");
	public By applyFilterButton = By.xpath("//*[@class=\"bttn-holder\"]/input");
	public By newOrderButton = By.linkText("New Order");
	public By orderNumberField = By.xpath("//*[@id=\"ui-accordion-accordion-panel-0\"]/form/ul/li[5]/input");
	public Toolbar toolbar;
	
	public OrderHistoryPage(WebDriver driver){
		pageDriver = driver;
		pageDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		toolbar = new Toolbar(driver);
	}
	
	public CreateOrderPage clickNewOrder(){
		click(newOrderButton);
		return new CreateOrderPage(pageDriver);
	}
	
	public OrderHistoryPage cancelOrderByNumber(String orderNumber){
		int orderCount = pageDriver.findElements(By.className("odd")).size();
		WebElement current = null;
		for(int i = 1; i <= orderCount; i++){
			current = pageDriver.findElement(By.xpath("//*[@class=\"grid-order\"]/tbody/tr["+i+"]/td[4]"));
			if(current.getText().equals(orderNumber)){
				cancelOrderByIndex(i);
				i= orderCount + 1;
			}
		}		
		return this;
	}
	
	public OrderHistoryPage cancelOrderByIndex(int order){
		WebElement cancel = pageDriver.findElement(By.xpath("//*[@class=\"grid-order\"]/tbody/tr["+order+"]/td[8]/span[1]/span"));	
		///html/body/div[3]/div/table/tbody/tr[2]/td[8]/span[1]/span
		cancel.click();
		
		ConfirmationWidget confirm = new ConfirmationWidget(pageDriver);
		confirm.click(confirm.okButton);	
		return this;
	}
	
	public boolean isOrderPresent(String order){
		boolean present = false;
		
		sendKeysToElement(order, orderNumberField);
		click(applyFilterButton);
		waitImplicitly(1);
		
		WebElement current = null;
		int orderCount = pageDriver.findElements(By.className("odd")).size();
		for(int i = 1; i <= orderCount; i++){
			current = pageDriver.findElement(By.xpath("//*[@class=\"grid-order\"]/tbody/tr["+i+"]/td[4]"));
			if(current.getText().equals(order)){
				present = true;
				//cancelOrderByIndex(i);
				i= orderCount + 1;
			}
		}	
		
		return present;
	}
}
