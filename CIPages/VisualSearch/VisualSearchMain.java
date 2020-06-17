package VisualSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CITests.CIPages.StudentHomePage;
import SharedClasses.BasePage;

public class VisualSearchMain extends BasePage{
	public By closeButton = By.xpath("//*[@id=\"modal\"]/div/div[1]");
	public By searchButton = By.xpath("//*[@id=\"visual-search\"]/div[5]");
	public By nonfictionDiv = By.xpath("//*[@id=\"root-categories\"]/li[1]/div");//*[@id="root-categories"]/li[1]/div
	
	public VisualSearchMain(WebDriver driver){
		pageDriver = driver;
		//nonfictionCategories = new NonfictionCategories(driver);
		//fictionCategories = new FictionCategories(driver);
	}
	
	public VisualSearchMain clickCategoryByName(String s){
		click(By.xpath("//span[text()='"+s+"']"));
		return this;
	}
	
	public StudentHomePage clickSearchButton(){
		click(searchButton);		
		return new StudentHomePage(pageDriver);
	}
	
	public StudentHomePage clickCloseButton(){
		click(closeButton);
		return new StudentHomePage(pageDriver);
	}
	
	public Boolean areCategoriesPresent(String[] categories){
		Boolean present = true;
		
		for(String s: categories){
			present = present && this.isElementPresent(By.xpath("//*[text()='"+s+"']"));
		}
		
		return present;
		
	}
}
