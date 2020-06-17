package SharedClasses;

import java.util.Set;


import org.openqa.selenium.WebDriver;

public class ThirdPartyPage extends BasePage{
	
	public ThirdPartyPage(WebDriver driver){
		pageDriver = driver;
	}
	
	/**
	 * Verifies that a new window has been opened, that it is unique, and that it is the window we are expecting.
	 * @param oldWindow
	 * @param newWindow
	 * @param by
	 * @return boolean
	 * @throws InterruptedException
	 */
	public boolean verifyNewWindow(String oldWindow, String newWindow) throws InterruptedException
	{		
		Set<String> windows = pageDriver.getWindowHandles();
		boolean windowsPresent = (windows.size() == 2);
		boolean verification = false;
		String switchBack = null;
		
    	for (String s : windows)
    	{
    		if(!s.equals(oldWindow))
    		{
    			pageDriver.switchTo().window(s);
    			waitImplicitly(5);
    			
    			boolean newWindowPresent = pageDriver.getCurrentUrl().contains(newWindow);

    			
    			verification = windowsPresent && newWindowPresent;
    			pageDriver.close();
    		}
    		else
    		{
    			switchBack = s;
    		}
    	}
    	
    	pageDriver.switchTo().window(switchBack);
    	return verification;
	}
	
	/**
	 * Verifies that we are still in the same menu, but that it is a different page and that it is the different page we are expecting.
	 * @param oldTitle
	 * @param newTitle
	 * @param by
	 * @return boolean
	 * @throws InterruptedException
	 */
	public boolean verifySameWindowNewPage(String newTitle) throws InterruptedException{
		waitImplicitly(1);
		
		boolean differentPage = pageDriver.getCurrentUrl().contains(newTitle);
		
		return differentPage;
	}
}
