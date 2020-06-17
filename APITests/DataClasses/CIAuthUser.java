package DataClasses;

public class CIAuthUser {
	public String userID;
	public String userTypeID;
	public String accountID;
	public String buildingID;
	public String sqs;
	public Boolean linkedAccounts;
	
	public CIAuthUser(){
		
	}
	
	public void setUserID(String id){
		userID = id;
	}
	
	public void setUserTypeID(String id){
		userTypeID = id;
	}
	
	public void setAccountID(String id){
		accountID = id;
	}
	
	public void setBuildingID(String id){
		buildingID = id;
	}
	
	public void setSQS(String s){
		sqs = s;
	}
	
	public void setLinkedAccounts(Boolean linked){
		linkedAccounts = linked;
	}
}
