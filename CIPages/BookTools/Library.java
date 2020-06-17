package BookTools;

import java.util.ArrayList;

public class Library {
	ArrayList<Book> books;
	
	public Library(){
		books = new ArrayList<Book>();
	}
	
	public void addBook(Book b){
		books.add(b);
	}
	
	public void removeBook(Book b){
		books.remove(b);
	}
	
	public Book getBookAtIndex(int i){
		return books.get(i);
	}
	
	public ArrayList<Book> getBooks(){
		return books;
	}
}
