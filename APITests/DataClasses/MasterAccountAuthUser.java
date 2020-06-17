package DataClasses;

public class MasterAccountAuthUser {
	public String userID;
	public String masterID;
	public String customerID;
	public String buildingID;
	public String sqs;
	public String site;
	public String userTypeID;
	public String validSubscription;
	public MasterAccountAuthUser CIUser;
	public MasterAccountAuthUser PGOUser;
	public MasterAccountAuthUser PGNUser;
	
	public MasterAccountAuthUser(){
		
	}
	
	public void setUserID(String u){
		userID = u;
	}
	
	public void setMasterID(String m){
		masterID = m;
	}
	
	public void setCustomerID(String c){
		customerID = c;
	}
	
	public void setBuildingID(String b){
		buildingID = b;
	}
	
	public void setSQS(String s){
		sqs = s;
	}
	
	public void setSite(String s){
		site = s;
	}
	
	public void setUserTypeID(String u){
		userTypeID = u;
	}
	
	public void setValidSubscription(String v){
		validSubscription = v;
	}
	
	public void setCIUser(MasterAccountAuthUser c){
		CIUser = c;
	}
	
	public void setPGOUser(MasterAccountAuthUser p){
		PGOUser = p;
	}
	
	public void setPGNUser(MasterAccountAuthUser p){
		PGNUser = p;
	}
}
