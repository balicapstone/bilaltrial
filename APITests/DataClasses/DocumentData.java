package DataClasses;

public class DocumentData {
	public String filename;
	public String thumbnail;
	public String documentID;
	public String documentTypeID;
	
	public DocumentData(){
		
	}
	
	public void setFilename(String file){
		filename = file;
	}
	
	public void setThumbnail(String thumb){
		thumbnail = thumb;
	}
	
	public void setDocumentID(String id){
		documentID = id;
	}
	
	public void setDocumentTypeID(String type){
		documentTypeID = type;
	}
}
