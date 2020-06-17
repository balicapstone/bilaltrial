package BookTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class LibraryLoader {
	
	
	public LibraryLoader(){
		
	}
	
	public Library createLibrary(String books){
		Library lib = new Library();
		String cellDelimiter = ",";
		try{
			Scanner scanner = new Scanner(new File(books));
			
			//Splits input into rows
			scanner.useDelimiter("\r");
			
			while(scanner.hasNext()){
				String bookInfo = scanner.next();
				//Replaces empty fields with a space so that there are no errors.
				bookInfo = bookInfo.replaceAll("\"\"", " ");
				//Removes all unneccessary " characters
				bookInfo = bookInfo.replaceAll("\"", "");
				
				//Splits rows into cells
				String[] info = bookInfo.split(cellDelimiter);
				
				Book temp = new Book(info);
				lib.addBook(temp);
			}
			scanner.close();
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} 
		
		return lib;
	}
}
