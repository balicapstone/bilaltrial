package DataClasses;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import SpecificGameData.SpecificGameData;

public class GamesData {
	public String appID;
	public String gameID;
	public String gameType;
	public String moduleID;
	public String gameTypeID;
	public JsonObject gameJSON;
	public ArrayList<SpecificGameData> assetData;
	
	public GamesData(String app, String game, String type, String module, String typeID, JsonObject json, ArrayList<SpecificGameData> a){
		appID = app;
		gameID = game;
		gameType = type;
		moduleID = module;
		gameTypeID = typeID;
		gameJSON = json;
		assetData = a;
	}
	
	public GamesData(){
		
	}
	
	public void setAppID(String a){
		appID = a;
	}
	
	public void setGameID(String g){
		gameID = g;
	}
	
	public void setGameType(String g){
		gameType = g;
	}
	
	public void setModuleID(String m){
		moduleID = m;
	}
	
	public void setGameTypeID(String g){
		gameTypeID = g;
	}
	
	public void setGameJSON(JsonObject g){
		gameJSON = g;
	}
	
	public void setAssetData(ArrayList<SpecificGameData> s){
		assetData = s;
	}	
}
