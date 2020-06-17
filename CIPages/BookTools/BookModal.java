package BookTools;


import java.io.PrintStream;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import SharedClasses.BasePage;

public class BookModal extends BasePage{
	public By playBookButton = By.id("play-book");
	public By summaryTab = By.xpath("//*[@id=\"book-information\"]/div/ul/li[2]");
	public By bookInfoTab = By.xpath("//*[@id=\"book-information\"]/div/ul/li[2]");
	public By moreTitlesTab = By.id("more-titles-tab");
	public By closeModalButton = By.className("close");
	public By titleHeader = By.xpath("//*[@id=\"book-information\"]/h2");
	public By description = By.id("description");
	
	public String isbnText;
	public String copyrightText;
	public String authorText;
	public String deweyText;
	public String gradeRangeText;
	public String guidedReadingLevelText; 
	public String lexileText; 
	public String publisherText; 
	public String brandText; 
	public String seriesText; 
	public String languageText; 
	public String runtimeText; 
	public String titleText;
	
	public BookModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public boolean doesInformationMatch(Book b, PrintStream out){
		boolean match = true;

		
		boolean title = titleText.equals(b.getTitle());
		boolean isbn = isbnText.equals(b.getPerpetualLibraryISBN());
		boolean publisher = publisherText.equals(b.getPublisher());
		boolean series = seriesText.equals(b.getSeries());
		boolean brand = brandText.equals(b.getBrand());
		
		match = match && title && isbn && publisher && series && brand;
		
		
		if(!match)
		{
			out.append(titleText +" has incorrect data in the following field(s)\n");
			if(!title){
				out.append("Title: " + "Book data: " + b.getTitle() + " Website data: " + titleText + "\n");
			}
			
			if(!isbn){
				out.append("ISBN: " + "Book data: " + b.getPerpetualLibraryISBN() + " Website data: " + isbnText + "\n");
			}
			
			if(!publisher){
				out.append("Publisher: " + "Book data: " + b.getPublisher() + " Website data: " + publisherText + "\n");
			}
			
			if(!series){
				out.append("Series: " + "Book data: " + b.getSeries() + " Website Data: " + seriesText + "\n");
			}
			
			if(!brand){
				out.append("Brand: " + "Book data: " + b.getBrand() + " Website Data: " + brandText + "\n");
			}
			out.append("\n");
		}
		else{
			out.append(titleText + "'s information is verified. \n\n");
		}
		return match;
	}
	
	public boolean doesInformationMatch(Book b){
		boolean match = true;
		boolean title = titleText.equals(b.getTitle());
		boolean isbn = isbnText.equals(b.getPerpetualLibraryISBN());
		boolean publisher = publisherText.equals(b.getPublisher());
		boolean series = seriesText.equals(b.getSeries());
		boolean brand = brandText.equals(b.getBrand());
		
		match = match && title && isbn && publisher && series && brand;
		
		
		if(!match)
		{
			System.out.println(titleText +" has incorrect data in the following field(s)");
			if(!title){
				System.out.println("Title: " + "Book data: " + b.getTitle() + " Website data: " + titleText);
			}
			
			if(!isbn){
				System.out.println("ISBN: " + "Book data: " + b.getPerpetualLibraryISBN() + " Website data: " + isbnText);
			}
			
			if(!publisher){
				System.out.println("Publisher: " + "Book data: " + b.getPublisher() + " Website data: " + publisherText);
			}
			
			if(!series){
				System.out.println("Series: " + "Book data: " + b.getSeries() + " Website Data: " + seriesText);
			}
			
			if(!brand){
				System.out.println("Brand: " + "Book data: " + b.getBrand() + " Website Data: " + brandText);
			}
			
			System.out.print("\n");
		}
		return match;
	}
	
	public BookModal getBookInformationFromTextFields(){
		List<WebElement> BookInfo = pageDriver.findElements(By.xpath("//*[@id=\"book-specification\"]/ul/li"));
		titleText = pageDriver.findElement(titleHeader).getText();
		 
		String type; 
		String value;
		
		for(WebElement w: BookInfo){
			String[] field = w.getText().split("\\:");
			
			if(Character.isWhitespace(field[1].charAt(0))){
				field[1] = field[1].substring(1, field[1].length());
			}
			
			type = field[0];
			value = field[1];
			
			switch(type){
			case "ISBN":
				isbnText = value;
				break;
			case "Copyright":
				copyrightText = value;
				break;
			case "Author":
				authorText = value;
				break;
			case "Dewey":
				deweyText = value;
				break;
			case "Grade Range (Interest Level)":
				gradeRangeText = value;
				break;
			case "Guided Reading Level":
				guidedReadingLevelText = value;
				break;
			case "Lexile":
				lexileText = value;
				break;
			case "Publisher":
				publisherText = value;
				break;
			case "Brand":
				brandText = value;
				break;
			case "Series":
				seriesText = value;
				break;
			case "Language":
				languageText = value;
				break;
			case "Runtime":
				runtimeText = value;
				break;
			}
		}
		
		return this;
	}
	
	public String getBookDescription(){
		return pageDriver.findElement(description).getText();
	}
	
	public String getTitleHeader(){
		return pageDriver.findElement(titleHeader).getText();
	}
	
	public BookReader openBook(){
		click(playBookButton);
		return new BookReader(pageDriver);
	}
}
