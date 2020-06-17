package SuperAdmin;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import SharedClasses.BasePage;

public class CreateOrderPage extends BasePage{
	public Toolbar toolbar;
	public By accountDropdown = By.id("order-account");
	public By licenseTypeDropdown = By.id("license-type");
	public By orderNumber = By.id("order-work-number");
	public By eulaAcceptanceOverrideCheckbox = By.id("order-eula");
	public By nextButton = By.xpath("//*[@class=\"bttn-holder\"]/input");//("//*[@id=\"create-order-step1\"]/div[1]/div/input");//("save-added-data");
	public By cancelButton = By.linkText("Cancel");
	public By chooseBooksNextButton = By.xpath("//*[@id=\"form-save-data\"]/div[6]/div/span");
	
	//Choose Books Elements
	public By titleField = By.id("filter-order-order-title");
	public By publisherField = By.id("filter-order-publisher");
	public By iSBNField = By.id("filter-order-isbn");
	public By seriesField = By.id("filter-order-series");
	public By brandField = By.id("filter-order-brand");
	public By applyFilterButton = By.id("book-filter");
	public By resetButton = By.id("book-reset");
	
	//Notification Modal Elements
	public By notificationText = By.xpath("//*[@id=\"ui-id-3\"]/div/textarea");
	public By notificationCloseButton = By.xpath("//div[@class=\"ui-dialog-buttonset\"]/button[2]/span");
	
	//*[@id="save-added-data"]
	
	public By chooseAccountLink = By.linkText("1.Choose Account");
	public By chooseBuildingsLink = By.linkText("2.Choose Buildings");
	public By chooseBooksLink = By.linkText("3.Choose Books");
	public By confirmOrderLink = By.linkText("4.Confirm Order");
	
	public CreateOrderPage(WebDriver driver){
		pageDriver = driver;
		pageDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		toolbar = new Toolbar(pageDriver);
	}
	
	public CreateOrderPage AddBuildingByName(String building){
		List<WebElement> buildings = pageDriver.findElements(By.className("building-name"));
		int index = 1;
		for(WebElement w : buildings){
			if(building.equals(w.getText())){
				AddBuildingByIndex(index);
				return this;
			}
			else{
				index++;
			}
		}
		return this;
	}
	
	public CreateOrderPage AddBuildingByIndex(int index){
		click(By.xpath("//*[@id=\"available-buildings\"]/tbody/tr["+ index + "]/td[6]/span"));
		return this;
	}
	
	public CreateOrderPage addBookByTitle(String title){
		//sendKeysToElement(title, titleField);
		List<WebElement> titles = pageDriver.findElements(By.className("book-title"));
		int index = 1;
		for(WebElement w : titles){
			if(w.getText().toString().equals(title)){
				addBookByIndex(index);
				return this;
			}
			index++;
		}
		return this;
	}
	
	public CreateOrderPage addBookByIndex(int index){
		click(By.xpath("//*[@id=\"list-book-data\"]/tbody/tr["+index+"]/td[7]/span"));
		return this;
	}
	
	//Finish order and send user back to SuperAdminHomePage
	public OrderHistoryPage finishOrder(){
		click(nextButton);
		return new OrderHistoryPage(this.getDriver());
	}
	
}
