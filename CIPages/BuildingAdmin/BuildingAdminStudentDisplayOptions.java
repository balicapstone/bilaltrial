package BuildingAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BuildingAdminStudentDisplayOptions extends BuildingAdminDashboard{
	public By gradeRangeOn = By.xpath("//div[@class=\"toggle toggle_option_interest\"]/div/div/div[1]"); 
	public By gradeRangeOff = By.xpath("//div[@class=\"toggle toggle_option_interest\"]/div/div/div[3]");
	public By gradeRangeToggle = By.xpath("//div[@class=\"toggle toggle_option_interest\"]/div/div/div[2]");
	public By gradeRangeInner = By.xpath("//div[@class=\"toggle toggle_option_interest\"]/div/div");
	public By guidedReadingOn = By.xpath("//div[@class=\"toggle toggle_option_guided_reading_level\"]/div/div/div[1]"); 
	public By guidedReadingOff = By.xpath("//div[@class=\"toggle toggle_option_guided_reading_level\"]/div/div/div[3]");
	public By guidedReadingToggle = By.xpath("//div[@class=\"toggle toggle_option_guided_reading_level\"]/div/div/div[2]");
	public By guidedReadingInner = By.xpath("//div[@class=\"toggle toggle_option_guided_reading_level\"]/div/div");
	public By lexileReadingOn = By.xpath("//div[@class=\"toggle toggle_option_lexile\"]/div/div/div[1]"); 
	public By lexileReadingOff= By.xpath("//div[@class=\"toggle toggle_option_lexile\"]/div/div/div[3]"); 
	public By lexileReadingToggle = By.xpath("//div[@class=\"toggle toggle_option_lexile\"]/div/div/div[2]");
	public By lexileReadingInner = By.xpath("//div[@class=\"toggle toggle_option_lexile\"]/div/div");
	public By atosLevelOn = By.xpath("//div[@class=\"toggle toggle_atos_level\"]/div/div/div[1]"); 
	public By atosLevelOff = By.xpath("//div[@class=\"toggle toggle_atos_level\"]/div/div/div[3]"); 
	public By atosLevelToggle = By.xpath("//div[@class=\"toggle toggle_atos_level\"]/div/div/div[2]");
	public By atosLevelInner = By.xpath("//div[@class=\"toggle toggle_atos_level\"]/div/div");
	public By updateButton = By.xpath("//input[@type='submit']");
	public By updateMessage = By.className("message-success");
	
	public BuildingAdminStudentDisplayOptions(WebDriver driver){
		super(driver);
	}
	
	
	public boolean isGradeRangeFilterOn(){
		//return pageDriver.findElement(gradeRangeOn).getAttribute("class").contains("active");
		return pageDriver.findElement(gradeRangeInner).getAttribute("style").equals("width: 120px; margin-left: 0px;");
	}
	
	public boolean isGuidedReadingFilterOn(){
		//return pageDriver.findElement(guidedReadingOn).getAttribute("class").contains("active");
		return pageDriver.findElement(guidedReadingInner).getAttribute("style").equals("width: 120px; margin-left: 0px;");
	}
	
	public boolean isLexileFilterOn(){
		//return pageDriver.findElement(lexileReadingOn).getAttribute("class").contains("active");
		return pageDriver.findElement(lexileReadingInner).getAttribute("style").equals("width: 120px; margin-left: 0px;");
	}
	
	public boolean isATOSLevelFilterOn(){
		//return pageDriver.findElement(atosLevelOn).getAttribute("class").contains("active");
		return pageDriver.findElement(atosLevelInner).getAttribute("style").equals("width: 120px; margin-left: 0px;");
	}
	
	
	public BuildingAdminStudentDisplayOptions toggleGradeRange(){
		pageDriver.findElement(gradeRangeToggle).click();
		return this;
	}
	
	public BuildingAdminStudentDisplayOptions toggleGuidedReadingLevel(){
		pageDriver.findElement(guidedReadingToggle).click();
		return this;
	}
	
	public BuildingAdminStudentDisplayOptions toggleLexile(){
		pageDriver.findElement(lexileReadingToggle).click();
		return this;
	}
	
	public BuildingAdminStudentDisplayOptions toggleATOS(){
		pageDriver.findElement(atosLevelToggle).click();
		return this;
	}
	
	public BuildingAdminStudentDisplayOptions clickUpdateButton(){
		click(updateButton);
		waitImplicitly(1);
		return this;
	}
	
	public By getUpdateMessage(){
		return updateMessage;
	}
	
	public BuildingAdminStudentDisplayOptions turnOnAllOptions(){
		if(pageDriver.findElement(gradeRangeOff).getAttribute("class").contains("active")){
			click(gradeRangeToggle);
		}
		if(pageDriver.findElement(guidedReadingOff).getAttribute("class").contains("active")){
			click(guidedReadingToggle);
		}
		if(pageDriver.findElement(lexileReadingOff).getAttribute("class").contains("active")){
			click(lexileReadingToggle);
		}
		if(pageDriver.findElement(atosLevelOff).getAttribute("class").contains("active")){
			click(atosLevelToggle);
		}

		return this;
	}
}
