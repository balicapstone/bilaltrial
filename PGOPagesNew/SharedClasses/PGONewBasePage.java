package SharedClasses;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import UserClasses.User;

public class PGONewBasePage {
	
	protected WebDriver pageDriver;
	protected User user;
	public By debugMenu = By.xpath("//div[@class=\"debug-info\"]");
	
	/**
	 * Makes Driver wait for a unit of time in seconds
	 * @param time
	 * @throws InterruptedException
	 */
	public void waitImplicitly(int time){
		for(int i = 0; i < 5; i++){	
			try {
				Thread.sleep(time*1000);
				i = 5;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Quits this instance of Driver and closes the current window.
	 */
	public void quit(){
		pageDriver.quit();
	}
	
	public void closeCurrentWindow(){
		pageDriver.close();
	}
	
	/**
	 * Gets the Title of the current window.
	 * @return String
	 */
	public String getTitle(){
		return pageDriver.getTitle();
	}
	
	/**
	 * Takes in a set of By Elements and verifies that each item is visible to the user.
	 * @param elements
	 * @return boolean
	 */
	public boolean verifyElementsVisibility(By[] elements){		
		boolean allElementsPresent = true;
		boolean testCurrent = false;
		
		for(By b : elements)
		{
			try{
				testCurrent = pageDriver.findElement(b).isDisplayed();
			} catch(Exception e) {
				System.out.println(b.toString());
				testCurrent = false;
			}
			allElementsPresent = allElementsPresent && testCurrent;		
		}
		return allElementsPresent;
	}
	
	public boolean verifyElementsHidden(By[] elements){
		boolean allElementsHidden = true;
		boolean testCurrent = false;
		
		for(By b: elements)
		{
			try{
				WebElement testWebElement = pageDriver.findElement(b);
				testCurrent = testWebElement.isDisplayed();
			} catch(Exception e){
				System.out.println(b.toString());
				testCurrent = false;
			}
			allElementsHidden = allElementsHidden && !testCurrent;
		}
		return allElementsHidden;
	}
	
	
	/**
	 * Gets the current WebDriver.
	 * @return WebDriver
	 */
	public WebDriver getDriver() { return pageDriver; }
	
	/**
	 * Hovers over a supplied By element.
	 * @param by
	 */
	public void hoverOverElement(By by){
		Actions action = new Actions(pageDriver);
		WebElement we = pageDriver.findElement(by);
		action.moveToElement(we).build().perform();//.perform();
	}
	
	/**
	 * Gets the text of the supplied By element.
	 * @param by
	 * @return String
	 */
	public String getElementText(By by){
		String text;
		try{
			text = pageDriver.findElement(by).getText();
		} catch(Exception e) {
			text = "";
		}
		
		return text;
	}
	
	public PGONewBasePage sendKeysToElement(String keys, By by){
		WebElement current = pageDriver.findElement(by);
		
		current.sendKeys(keys);
		this.waitImplicitly(1);
		//this.closeSendKeysTab();
		return this;
	}
	
	public void sendKeysClean(String keys, WebElement w){
		JavascriptExecutor myExecutor = ((JavascriptExecutor) pageDriver);
		myExecutor.executeScript("arguments[0].value='"+keys+"'", w);
		w.sendKeys(Keys.ARROW_RIGHT);
	}
	
	public void sendKeysClean(String keys, By b){
		WebElement w = pageDriver.findElement(b);
		
		JavascriptExecutor myExecutor = ((JavascriptExecutor) pageDriver);
		myExecutor.executeScript("arguments[0].value='"+keys+"'", w);
		w.sendKeys(Keys.ARROW_UP);
	}
	
	public void sendDeleteKey(By b){
		pageDriver.findElement(b).sendKeys(Keys.DELETE);
	}
	
	public void sendRightArrow(By b){
		pageDriver.findElement(b).sendKeys(Keys.ARROW_RIGHT);
	}
	
	public void sendLeftArrow(By b){
		pageDriver.findElement(b).sendKeys(Keys.ARROW_LEFT);
	}
	
	public void clearField(By by){
		pageDriver.findElement(by).clear();
	}
	
	public boolean isElementPresent(By by){
		boolean present = false;
		try{
			present = this.pageDriver.findElement(by).isDisplayed();
		}catch(Exception e){
			present = false;
		}
		return present;
	}
	
	public boolean isElementPresent(WebElement element){
		boolean present = false;
		try{
			present = element.isDisplayed();
		}catch(Exception e){
			present = false;
		}
		return present;
	}
	
	public boolean isElementEnabled(By by){
		boolean present = false;
		try{
			present = this.pageDriver.findElement(by).isEnabled();
		}catch(Exception e){
			present = false;
		}
		return present;
	}
	
	public void refresh(){
		pageDriver.navigate().refresh();
		waitImplicitly(1);
	}
	
	public Boolean click(By by){
		Boolean clickable = true;
		try{
			moveToElement(by);
			pageDriver.findElement(by).click();
		} catch(Exception e){
			clickable = false;
		}
		return clickable;
	}
	
	public Boolean click(WebElement web){
		Boolean clickable = true; 
		try{
			web.click();
		} catch(Exception e){
			clickable = false;
		}
		return clickable;
	}
	
	public void resizeWindow(int height, int width){
		//Dimension dim = pageDriver.manage().window().getSize();		
		Dimension newDim = new Dimension(width, height);
		pageDriver.manage().window().setSize(newDim);	
	}
	
	public void maximizeWindow(){
		pageDriver.manage().window().fullscreen();
	}
	
	public void moveToElement(By b) throws InterruptedException{
		WebElement element = pageDriver.findElement(b);
		((JavascriptExecutor) pageDriver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
	}
	
	public void moveToElement(WebElement webElement) throws InterruptedException{
		((JavascriptExecutor) pageDriver).executeScript("arguments[0].scrollIntoView(true);", webElement);
		Thread.sleep(500);
	}
	
	public void waitForElement(By by){
		WebDriverWait wait = new WebDriverWait(pageDriver, 10);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public PGONewThirdPartyPage clickLink(By by){
		click(by);
		return new PGONewThirdPartyPage(user);
	}
	
	public void selectDropdownOptionByText(By dropdown, String text){
		Select current = new Select(pageDriver.findElement(dropdown));
		//current.deselectAll();
		current.selectByVisibleText(text);
	}
	
	public void goToHomePage(String environment){
		pageDriver.get("http://" +environment+ ".mycapstonelibrary.com/login/index.html");
	}
	
	public void goBack(){
		pageDriver.navigate().back();
	}

	
	public void SwitchToNewPage(String oldWindow){
		Set<String> windows = pageDriver.getWindowHandles();
		for(String s : windows){
			if(!oldWindow.contains(s)){
				pageDriver.switchTo().window(s);
			}
		}
	}

	
	public int getWindowHeight(){
		return pageDriver.manage().window().getSize().height;
	}
	
	public int getWindowWidth(){
		return pageDriver.manage().window().getSize().width;
	}

	
	public PGONewBasePage clickLink(String text){
		waitForElement(By.linkText(text)); 
		click(By.linkText(text));
		return this;
	}
	
	public void pressEnter(By by){
		pageDriver.findElement(by).sendKeys(Keys.ENTER);
	}
	
	public void scrollToTopofPage(){
		JavascriptExecutor jse = (JavascriptExecutor) pageDriver;
	    jse.executeScript("window.scrollTo(0,0)", "");
	}
	
	public void scrollDown(){
		JavascriptExecutor jse = (JavascriptExecutor) pageDriver;
	    jse.executeScript("window.scrollTo(0,1000)", "");
	}
	
	public WebElement getModalElement(By by){
		int index = pageDriver.findElements(by).size();
		return pageDriver.findElements(by).get(index-1);
	}
	
	public void scrollToElement(WebElement w){
		((JavascriptExecutor) pageDriver).executeScript("arguments[0].scrollIntoView(true);", w);
	}
	
	public PGONewThirdPartyPage navigateToLinkInSameTab(By by){
		String url = pageDriver.findElement(by).getAttribute("href").toString();
		System.out.println(url);
		pageDriver.get(url);
		
		return new PGONewThirdPartyPage(user);
	}
	
	public Boolean verifyLinkByElement(By by){
		String url = pageDriver.findElement(by).getAttribute("href").toString();
		
		return verifyAsset(url);
	}
	
	public Boolean verifyLinkContainsURLByElement(By by, String url){
		String elementURL = pageDriver.findElement(by).getAttribute("href").toString();
		
		return elementURL.contains(url);
	}
	
	public Boolean verifyAsset(String asset){
		Boolean active = true;
		try{
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(asset);
			HttpResponse response = client.execute(request);
			
			if(response.getStatusLine().getStatusCode() != 200){
				active = false;
			}
			
		} catch (Exception e){
			active = false;
		}
		
		return active;
	}
	
	public Boolean verifyImageActive(String image){
		Boolean active = true;
		try{
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet("https://cms-assets.pebblego.com/cms_content/images/" + image);
			HttpResponse response = client.execute(request);
			
			if(response.getStatusLine().getStatusCode() != 200){
				active = false;
			}
			
		} catch (Exception e){
			active = false;
		}
		
		return active;
	}
	
	public Boolean verifyAudioActive(String audio){
		Boolean active = true;
		try{
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet("https://cms-assets.pebblego.com/cms_content/audio/" + audio);
			HttpResponse response = client.execute(request);
			
			if(response.getStatusLine().getStatusCode() != 200){
				active = false;
			}
			
		} catch (Exception e){
			active = false;
		}
		
		return active;
	}
	
	public Boolean verifyXMLActive(String xml){
		Boolean active = true;
		try{
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet("https://cms-assets.pebblego.com/cms_content/xml/" + xml);
			HttpResponse response = client.execute(request);
			
			if(response.getStatusLine().getStatusCode() != 200){
				active = false;
			}
			
		} catch (Exception e){
			active = false;
		}
		
		return active;
	}
	
	public Boolean verifyVideoActive(String xml){
		Boolean active = true;
		try{
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet("https://cms-assets.pebblego.com/cms_content/video/" + xml);
			HttpResponse response = client.execute(request);
			
			if(response.getStatusLine().getStatusCode() != 200){
				active = false;
			}
			
		} catch (Exception e){
			active = false;
		}
		
		return active;
	}
	
	public Boolean verifyPDFActive(String pdf){
		Boolean active = true;
		try{
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet("https://assets.pebblego.com/cms_content/pdf/" + pdf);
			HttpResponse response = client.execute(request);
			
			if(response.getStatusLine().getStatusCode() != 200){
				active = false;
			}
			
		} catch (Exception e){
			active = false;
		}
		
		return active;
	}
	
	public Boolean verifyPDFActiveOnEnvironment(String environment, String pdf){
		Boolean active = true;
		try{
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet("https://assets.pebblego.com/cms_content/pdf/" + pdf);
			HttpResponse response = client.execute(request);
			
			if(response.getStatusLine().getStatusCode() != 200){
				active = false;
			}
			
		} catch (Exception e){
			active = false;
		}
		
		return active;
	}
	
	/*
	public JsonObject getArticleAssets(int i) throws IOException{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("https://www.pebblego.com/assets/article/" + i + "?sqs=$2y$10$6TnYYqvrRoHf1yqcB9OeC.IM1kHSKVmPIsxdLm7HXBDdet1wn5RPu");
		HttpResponse response = client.execute(request);
	
		String test = EntityUtils.toString(response.getEntity());
		JsonArray testing = (JsonArray) new JsonParser().parse(test);

		return (JsonObject) testing.get(0);
	}
	
	public JsonArray getArticlesFromModule(int i) throws ClientProtocolException, IOException{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("https://www.pebblego.com/assets/module/" + i + "?sqs=$2y$10$6TnYYqvrRoHf1yqcB9OeC.IM1kHSKVmPIsxdLm7HXBDdet1wn5RPu");
		HttpResponse response = client.execute(request);
	
		String test = EntityUtils.toString(response.getEntity());
		JsonArray testing = (JsonArray) new JsonParser().parse(test);

		return testing;
	}
	
	public JsonObject getArticleAssets(String env, int i) throws ClientProtocolException, IOException{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("https://"+ env +".pebblego.com/assets/article/" + i + "?sqs=$2y$10$6TnYYqvrRoHf1yqcB9OeC.IM1kHSKVmPIsxdLm7HXBDdet1wn5RPu");
		HttpResponse response = client.execute(request);
	
		String test = EntityUtils.toString(response.getEntity());
		JsonArray testing = (JsonArray) new JsonParser().parse(test);

		return (JsonObject) testing.get(0);
	}
	
	public JsonArray getArticlesFromModule(String env, int i) throws ClientProtocolException, IOException{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		//HttpGet request = new HttpGet("https://"+ env +".pebblego.com/assets/module/" + i + "?sqs=$2y$10$6TnYYqvrRoHf1yqcB9OeC.IM1kHSKVmPIsxdLm7HXBDdet1wn5RPu");
		HttpGet request = new HttpGet("https://"+ env + ".pebblegonext.com/module/"+i+"/customer/20662?sqs=$2y$10$O0S60.9z0G8xKmEIrQf46.tE9n6GA13xrx.qG5KFgH.yNvR8AqyHm");
		HttpResponse response = client.execute(request);
	
		String test = EntityUtils.toString(response.getEntity());
		JsonArray testing = (JsonArray) new JsonParser().parse(test);

		return testing;
	}
	*/
	
	public JsonArray getArticlesFromModule(int i) throws ClientProtocolException, IOException{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("https://www.pebblego.com/assets/module/" + i + "?sqs=fc60e391d07d9680077f1150481a0b5036eba2d037bb2708f107398217d0655a");
		HttpResponse response = client.execute(request);
	
		String test = EntityUtils.toString(response.getEntity());
		JsonArray testing = (JsonArray) new JsonParser().parse(test);

		return testing;
	}
	
	
	public void closeSendKeysTab(){
		Set<String> windows =  this.getDriver().getWindowHandles();
		String currentPage = "";
		for(String s : windows){
			pageDriver.switchTo().window(s);
			if(pageDriver.getCurrentUrl().contains("settings")){
				pageDriver.close();
			}
			else{
				currentPage = s;
			}
		}
		pageDriver.switchTo().window(currentPage);
	}
	
	public Timestamp getCurrentTimestamp(){
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//Timestamp time = (Timestamp) dateFormat.parse(new Timestamp(System.currentTimeMillis()).toString());
		
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void clickThroughAlert(){
		pageDriver.switchTo().alert().accept();
	}
	
	public String getLoggingSessionID(String loggingCall){
		LogEntries entries = pageDriver.manage().logs().get(LogType.PERFORMANCE);
		
		List<LogEntry> logs = entries.getAll();
		
		String sessionID = "";
		for(LogEntry l : logs){
			if(l.getMessage().contains(loggingCall) && l.getMessage().contains("session_id")){
				String message = l.getMessage();
				sessionID = l.getMessage().substring(message.indexOf("\\\"session_id\\\":\\\"")+17, message.indexOf("\\\"session_data\\\":\\\"")-3);
				System.out.println("I found this session string: " + sessionID);
				break;
			}
		}

		return sessionID;
	}
	
	public void closePrintableContentTab(String type){
		Set<String> windows =  this.getDriver().getWindowHandles();
	
		String currentPage = "";
		for(String s : windows){
			pageDriver.switchTo().window(s);
			if(pageDriver.getCurrentUrl().contains(type)){
				pageDriver.close();
			}
			else{
				currentPage = s;
			}
		}
		
		pageDriver.switchTo().window(currentPage);
	}
	
	public void closeAllOtherTabsBut(String windowHandle){
		Set<String> windows =  this.getDriver().getWindowHandles();

		for(String s : windows){
			pageDriver.switchTo().window(s);
			if(!s.equals(windowHandle)){
				pageDriver.close();
			}
		}
		
		pageDriver.switchTo().window(windowHandle);
	}
	
	public boolean isAlertPresent()
	{
		try{
			pageDriver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e){
			return false;
		}
	}
}
