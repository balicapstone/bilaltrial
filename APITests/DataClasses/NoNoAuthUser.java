package DataClasses;

public class NoNoAuthUser {
	public String userID;
	public String username;
	public String userQueryString;
	public String firstname;
	public String lastname;
	public String createdAt;
	public String deleted;
	public String sharedAccount;
	public Boolean linkedAccount;
	
	public NoNoAuthUser(){
		
	}
	
	public void setUserID(String user){
		userID = user;
	}
	
	public void setUsername(String u){
		username = u;
	}
	
	public void setUserQueryString(String q){
		userQueryString = q;
	}
	
	public void setFirstName(String f){
		firstname = f;
	}
	
	public void setLastName(String l){
		lastname = l;
	}
	
	public void setCreatedAt(String c){
		createdAt = c;
	}
	
	public void setDeleted(String d){
		deleted = d;
	}
	
	public void setSharedAccount(String shared){
		sharedAccount = shared;
	}
	
	public void setLinkedAccounts(Boolean linked){
		linkedAccount = linked;
	}
}
