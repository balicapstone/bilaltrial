package DataClasses;

public class PGAuthUser {
	public String userID;
	public String customerID;
	public String userTypeID;
	public String username;
	public String userQueryString;
	public Boolean linkedAccounts;
	
	public PGAuthUser(){
		
	}
	
	public void setUserID(String id){
		userID = id;
	}
	
	public void setCustomerID(String id){
		customerID = id;
	}
	
	public void setUserTypeID(String id){
		userTypeID = id;
	}
	
	public void setUsername(String user){
		username = user;
	}
	
	public void setUserQueryString(String query){
		userQueryString = query;
	}
	
	public void setLinkedAccounts(Boolean linked){
		linkedAccounts = linked;
	}
}
