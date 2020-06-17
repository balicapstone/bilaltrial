package UserClasses;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Level;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import SharedClasses.BasePage;
import TrueUserTests.TrueUser.RegressionSuiteRunner;
import TrueUserTests.TrueUser.Watcher;

public class User{
	protected String username;
	protected String password;
	protected UserInfo userInfo;
	protected LoginType loginType;
	protected Boolean linked;
	protected Boolean pgoSubscription;
	protected Boolean pgnSubscription;
	protected Boolean ciSubscription;
	protected BasePage currentPage;
	protected WebDriver pageDriver;
	protected WebDriverWait wait;
	protected Boolean local;
	protected String browser = RegressionSuiteRunner.browser;
	protected String environment = RegressionSuiteRunner.environment;
	protected Boolean headless = RegressionSuiteRunner.headless;
	protected Watcher watcher = RegressionSuiteRunner.watcher;
	protected String product = RegressionSuiteRunner.product;
	protected String localFolderPath = "/Users/whall/NewQAProject/NewQAProject/";
			
	
	public User(){
		username = "trexstudent";
		password = "sm4ll4rms";
		userInfo = UserInfo.GARAGESTUDENT;
		loginType = LoginType.CURRENTPRODUCT;
		linked = false;
		pgoSubscription = true;
		pgnSubscription = true;
		ciSubscription = false;
		setEnvironment();
		setBrowser();
		setHeadless();
	}
	
	public User(WebDriver driver){
		pageDriver = driver;
		username = "garage";
		password = "capdig";
		userInfo = UserInfo.GARAGESTUDENT;
		loginType = LoginType.CURRENTPRODUCT;
		linked = false;
		pgoSubscription = true;
		pgnSubscription = true;
		ciSubscription = false;
		setProduct();
		setEnvironment();
		setBrowser();
		setHeadless();
		setWatcher();
	}
	
	public User(WebDriver driver, UserInfo user){
		pageDriver = driver;
		setCredentials(user);
		setProduct();
		setEnvironment();
		setBrowser();
		setHeadless();
		setWatcher();
	}
	
	public User(UserInfo user){
		setCredentials(user);
		setProduct();
		setEnvironment();
		setBrowser();
		setHeadless();
		makeNewDriver();
		setWatcher();
	}
	
	public User(UserInfo user, Boolean shouldHaveDriver){
		setCredentials(user);
		setProduct();
		setEnvironment();
		setBrowser();
		setHeadless();
		if(shouldHaveDriver){
			makeNewDriver();
		}
		setWatcher();
	}
	
	public User(UserInfo user, String env){
		setCredentials(user);
		environment = env;
		setProduct();
		setBrowser();
		setHeadless();
		makeNewDriver();
		setWatcher();
	}
	
	public void setProduct(){
		if(product == null){
			product = "pgo";
		}
	}
	
	public void setBrowser(){
		if(browser == null){
			browser = "chrome";
		}
	}
	
	public void setEnvironment(){
		if(environment == null){
			 environment = "staging"; 
			// environment = "reactqa"; 
		}
	}
	
	public void setHeadless(){
		if(headless == null){
			headless = false;
		}
	}
	
	public void setWatcher(){
		if(watcher == null){
			watcher = new Watcher(pageDriver, environment, product);
		}
		else{
			watcher.setDriver(pageDriver);
		}
	}
	
	public void checkLocal(){
		Path path = Paths.get(localFolderPath);
			
		if(Files.exists(path)){
			local = true;
		}
		else{
			local = false;
		}
	}
	
	public String getEnvironment(){
		return environment;
	}
	
	public User(String name, String pass, UserInfo userI, LoginType loginT, Boolean student, Boolean link, Boolean pgoSub, Boolean pgnSub, Boolean ciSub){
		username = name;
		password = pass;
		userInfo = userI;
		loginType = loginT;
		linked = link;
		pgoSubscription = pgoSub;
		pgnSubscription = pgnSub;
		ciSubscription = ciSub;
	}
	
	public User(String name, String pass){
		username = name;
		password = pass;
	}
	
	public User setCredentials(UserInfo user){	
		userInfo = user;
		username = user.getUserName();
		password = user.getPassword();
		loginType = user.getType();
		linked = user.isLinked();
		pgoSubscription = user.hasPGOSubscription();
		pgnSubscription = user.hasPGNSubscription();
		ciSubscription = user.hasCISubscription();
		
		return this;
	}
	
	public void makeNewDriver(){
		if(browser == null){
			browser = "chrome";
		}
		
		checkLocal();
		
		if(browser.equals("chrome")){
			System.out.println("Setting up chrome browser");
			System.out.println("The value of local is:" + local);
			
			if(local){
				System.setProperty("webdriver.chrome.driver", "chromedriver");
				System.out.println("I set up the iOS chrome driver");
			}
			else{
				System.setProperty("webdriver.chrome.driver", "chromedriverLinux");
				System.out.println("I set up the linux chrome driver");
			}
			ChromeOptions options = new ChromeOptions();
			
			////////These are added to track network logs/////////////
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			LoggingPreferences logPrefs = new LoggingPreferences();
			
			logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
			cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			//////////////////////////////////////////////////////////
			
			if(headless){
				options.addArguments("--headless");
			}			
			
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("download_restrictions", 0);
		    chromePrefs.put("profile.default_content_settings.popups", 0);
		    chromePrefs.put("download.default_directory", "Downloads");
			chromePrefs.put("browser.set_download_behavior", "{ behavior: 'allow' , downloadPath: 'Downloads'}");
		       
		    options.setExperimentalOption("prefs", chromePrefs);
		    options.addArguments("--disable-extensions");
			options.addArguments("--window-size=1440,900");
			options.merge(cap);
			
			pageDriver = new ChromeDriver(options);
			
			if(!pageDriver.equals(null)){
				System.out.println("I set up a chrome browser");
			}
		}
		else if(browser.equals("safari")){
			
		}
		else{
			System.setProperty("webdriver.firefox.marionette", "geckodriver");
			pageDriver = new FirefoxDriver(); 
			pageDriver.manage().window().maximize();
		}
		
		wait = new WebDriverWait(pageDriver, 15);
		setWatcher();
	}
	
	public WebDriver makeAnExtraDriver(){
		WebDriver newDriver = null;
		
		if(browser == null){
			browser = "chrome";
		}
		
		checkLocal();
		
		if(browser.equals("chrome")){
			System.out.println("Setting up chrome browser");
			System.out.println("The value of local is:" + local);
			
			if(local){
				System.setProperty("webdriver.chrome.driver", "chromedriver");
				System.out.println("I set up the iOS chrome driver");
			}
			else{
				System.setProperty("webdriver.chrome.driver", "chromedriverLinux");
				System.out.println("I set up the linux chrome driver");
			}
			ChromeOptions options = new ChromeOptions();
			
			////////These are added to track network logs/////////////
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			LoggingPreferences logPrefs = new LoggingPreferences();
			
			logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
			cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			//////////////////////////////////////////////////////////
			
			if(headless){
				options.addArguments("--headless");
			}
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		    chromePrefs.put("profile.default_content_settings.popups", 0);
		    chromePrefs.put("download.default_directory", "Downloads");
		       
		    options.setExperimentalOption("prefs", chromePrefs);
		    options.addArguments("--disable-extensions");
			options.addArguments("--window-size=1440,900");
			options.merge(cap);
			
			newDriver = new ChromeDriver(options);
			
			if(!pageDriver.equals(null)){
				System.out.println("I set up a chrome browser");
			}
		}
		else if(browser.equals("safari")){
			
		}
		else{
			System.setProperty("webdriver.firefox.marionette", "geckodriver");
			newDriver = new FirefoxDriver(); 
			newDriver.manage().window().maximize();
		}
		
		wait = new WebDriverWait(pageDriver, 15);
		setWatcher();
		
		return newDriver;
	}
	
	public String getBrowser(){
		return browser;
	}
	
	public WebDriver getDriver(){
		return pageDriver;
	}
	
	public void setPassword(String pass){
		password = pass;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String u){
		username = u;
	}
	
	public UserInfo getUserInfo(){
		return userInfo;
	}
	
	public Boolean isLinked(){
		return linked;
	}
	
	public Boolean hasPGOSub(){
		return pgoSubscription;
	}
	
	public Boolean hasPGNSub(){
		return pgnSubscription;
	}
	
	public Boolean hasCISub(){
		return ciSubscription;
	}
	
	public LoginType getLoginType(){
		return loginType;
	}
	
	public Boolean isHeadless(){
		return headless;
	}
	
	public WebDriverWait customWait(){
		return wait;
	}
	
	public void quit(){
		pageDriver.quit();
	}
	
	public Watcher getWatcher(){
		return watcher;
	}
	public Boolean getLocal(){
		return local;
	}

}
