package BookTools;

public class Book {
	private String iSBN;
	private String key;
	private String title;
	private String publisher;
	private String series;
	private String brand;
	private String player_url;
	private String perpetualLibraryISBN;
	private String subscriptionLibraryISBN;
	private String perpetualClassroomISBN;
	private String subscriptionClassroomISBN;
	private String season;
	private boolean library;
	private boolean classroom;
	
	public Book(){
		
	}
	
	/*
	public Book(String isbn, String bookKey, String bookTitle,
			String bookPublisher, String bookSeries, String bookBrand, 
			String bookPlayer, String perpetualLibrary, String subscriptionLibrary,
			String perpetualClassroom, String subscriptionClassroom, 
			String seasonValue, boolean libraryValue, boolean classroomValue){
	*/
	public Book(String[] info){
		iSBN = info[0].toString();
		key = info[2].toString(); 
		title = info[3].toString();
		publisher = info[4].toString();
		series = info[5].toString();
		brand = info[6].toString();
		player_url = info[7].toString();
		perpetualLibraryISBN = info[8].toString();
		subscriptionLibraryISBN = info[9].toString();
		perpetualClassroomISBN = info[10].toString();
		subscriptionClassroomISBN = info[11].toString();
		season = info[12].toString();
		library = info[13].toString().equals("1");
		classroom = info[14].toString().equals("1");
	}
	
	public String getISBN(){
		return iSBN;
	}
	
	public String getKey(){
		return key;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getPublisher(){
		return publisher;
	}
	
	public String getSeries(){
		return series;
	}
	
	public String getBrand(){
		return brand;
	}
	
	public String getPlayerURL(){
		return player_url;
	}
	
	public String getPerpetualLibraryISBN(){
		return perpetualLibraryISBN;
	}
	
	public String getSubscriptionLibraryISBN(){
		return subscriptionLibraryISBN;
	}
	
	public String getPerpetualClassroomISBN(){
		return perpetualClassroomISBN;
	}
	
	public String getSubscriptionClassroomISBN(){
		return subscriptionClassroomISBN;
	}
	
	public String getSeason(){
		return season;
	}
	
	public boolean getLibrary(){
		return library;
	}
	
	public boolean getClassroom(){
		return classroom;
	}
}
