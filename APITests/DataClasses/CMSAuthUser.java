package DataClasses;

public class CMSAuthUser {
	public String userID;
	public String username;
	public String userQueryString;
	public String firstName;
	public String lastName;
	public String createdAt;
	public String deleted;
	
	public CMSAuthUser(){
		
	}
	
	public void setUserID(String u){
		userID = u;
	}
	
	public void setUsername(String u){
		username = u;
	}
	
	public void setUserQueryString(String u){
		userQueryString = u;
	}
	
	public void setFirstName(String f){
		firstName = f;
	}
	
	public void setLastName(String l){
		lastName = l;
	}
	
	public void setCreatedAt(String c){
		createdAt = c;
	}
	
	public void setDeleted(String d){
		deleted = d;
	}
}
