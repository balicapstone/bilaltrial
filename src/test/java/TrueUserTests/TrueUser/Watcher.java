package TrueUserTests.TrueUser;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;

public class Watcher extends TestWatcher{
	private WebDriver pageDriver;
	private String folder = RegressionSuiteRunner.folderPath;
	private String buildTag = RegressionSuiteRunner.buildTag;
	private String localFolderPath = "/Users/whall/NewQAProject/NewQAProject/SeleniumScreenshots/";
	protected String product = RegressionSuiteRunner.product;
	protected String environment = RegressionSuiteRunner.environment;
	protected Boolean local = RegressionSuiteRunner.local;

	public Watcher(WebDriver driver, String env, String prod){
		pageDriver = driver;
		environment = env;
		product = prod;
		
		checkLocalStatus();
		checkFolderString();
		makeFolder();
	}
	
	public void setDriver(WebDriver driver){
		pageDriver = driver;
	}
	
	public void setEnvironment(String env){
		environment = env;
	}
	
	public void setProduct(String prod){
		product = prod;
	}
	
	//Local status is only set to false in the RegressionSuiteRunner class, if it isn't set there, we can assume we are local
	public void checkLocalStatus(){
		Path path = Paths.get(localFolderPath);
		
		if(Files.exists(path)){
			local = true;
		}
		else{
			local = false;
		}
	}
	
	/*
	@Override
	protected void succeeded(Description description){
		watchedLog += description.getDisplayName() + " " + "ran successfully.\n";
		//System.out.println("Woah I passed!");
	}
	*/
	
	/*
	@Override
	protected void failed(Throwable e, Description description){
		watchedLog += description.getDisplayName() + " " + e.getClass().getSimpleName() + "\n";
		//System.out.println("Woah I failed!");
	}
	*/
	
	/*
	public Statement apply(final Statement base, final Description description){
		return new Statement(){
				public void evaluate() throws Throwable{
					Throwable caughtThrowable = null;			
					for(int i = 0; i < 3; i++){
						try{
							base.evaluate();
							return;
						} catch(Throwable t){
							caughtThrowable = t;
							System.out.println(description.getDisplayName() + " is restarting");
						}
				}						
				throw caughtThrowable;
			}
		};
	}
	*/
	
	/*
	protected void skipped(AssumptionViolatedException e, Description description){
		watchedLog += description.getDisplayName() + " " + e.getClass().getSimpleName();
	}
	*/
	
	/*
	@Override
	protected void starting(Description description){
		super.starting(description);
	}
		
	@Override
	protected void finished(Description description){
		super.finished(description);
	}
	*/
	
	public void checkFolderString(){	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		LocalDate localDate = LocalDate.now();
		String time = dtf.format(localDate);
		
		if(local){
			folder = localFolderPath + environment + product + time;
		}
		else{
			folder = "./" + buildTag;
		}
	}
	
	public void makeFolder(){		
		Path path = Paths.get(folder);
		
		if(!Files.exists(path)){
			if(new File(folder).mkdirs()){
				System.out.println("Folder created at "+ folder);
			}
			else{
				System.out.print("Tried but could not create a folder");
			}
		}
	}
	
	
	public void TakeScreenshot(String description){
		File scr = null;
		try{
			checkLocalStatus();
			checkFolderString();
			makeFolder();
			System.out.println("We set up the folder");
			System.out.println("The value for the webdriver is" + pageDriver.toString());
		
			scr = ((TakesScreenshot)pageDriver).getScreenshotAs(OutputType.FILE);
			System.out.println("Screenshot should be taken for: "+ description);
		} catch(Exception e){
			System.out.println("We tried to make a screenshot at " + folder + "/" + description + ".png");
		}
		
		
		File dest = new File(folder+"/" + description + ".png");
		try {
			FileUtils.copyFile(scr, dest);
		} catch (IOException e1) {
			System.out.println("I couldn't copy the screenshot for "+ description+ " at " + folder);
			e1.printStackTrace();
		}
	}
}
	
