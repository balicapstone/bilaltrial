package Crud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class OrderModal extends BasePage{

	public By assessmentsCheckbox = By.xpath("/html/body/div/div[6]/div[2]/div[2]/div/div/div[2]/input");
	public By lessonPlansCheckbox = By.xpath("/html/body/div/div[6]/div[2]/div[2]/div/div/div[3]/input");
	public By editOrderButton = By.xpath("/html/body/div/div[6]/div[2]/div[2]/div/div/button");
	
	
	public OrderModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public PurchaseView finishEditingOrder(){
		click(editOrderButton);
		return new PurchaseView(pageDriver);
	}
	
	public OrderModal clickAssessmentsCheckbox(){
		click(assessmentsCheckbox);
		return this;
	}
	
	public OrderModal clickLessonPlansCheckbox(){
		click(lessonPlansCheckbox);
		return this;
	}
}
