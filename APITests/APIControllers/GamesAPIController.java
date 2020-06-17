package APIControllers;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DataClasses.GamesData;
import SharedClasses.BasePage;
import SpecificGameData.JigsawAssetData;
import SpecificGameData.MultiMatchAssetData;
import SpecificGameData.QuickMatchAssetData;
import SpecificGameData.SpecificGameData;
import SpecificGameData.WhackAssetData;
import SpecificGameData.WordScrambleAssetData;
import SpecificGameData.ZoomAssetData;

public class GamesAPIController extends BasePage{
	public JsonObject callData;
	public String statusCode;
	String pgoToken;
	String pgnToken;
	String masterSQS = "$2y$10$jzNHLCgrGqxscuDKMJqO3OM82SJCtiRnpHjdIdxzuaHrv93VnYjYy";
	String appNumber;
	String moduleNumber;
	JsonArray games;
	AuthenticationAPIController authAPI;
	
	
	public GamesAPIController(){
		authAPI = new AuthenticationAPIController("authqa");
		
		try {
			pgoToken =	authAPI.getTokenForUsername("garage", "capdig", "2", "username");
		} catch (ParseException e) {
			pgoToken = "***Error getting token***";
			System.out.println(pgoToken + "\n");
			e.printStackTrace();
		} catch (IOException e) {
			pgoToken = "***Error getting token***";
			System.out.println(pgoToken + "\n");
			e.printStackTrace();
		}
		
		try {
			pgnToken =	authAPI.getTokenForUsername("garage", "capdig", "3", "username");
		} catch (ParseException e) {
			pgnToken = "***Error getting token***";
			System.out.println(pgnToken + "\n");
			e.printStackTrace();
		} catch (IOException e) {
			pgnToken = "***Error getting token***";
			System.out.println(pgnToken + "\n");
			e.printStackTrace();
		}
	}
	
	public String getGamesJSON(String app, String module){
		appNumber = app;
		moduleNumber = module;
		CloseableHttpClient client = HttpClientBuilder.create().build();
		//https://3ng5u5zewd.execute-api.us-east-2.amazonaws.com/qa/api/v1/app/2/modules/1/games
		HttpGet httpGet = new HttpGet("https://3ng5u5zewd.execute-api.us-east-2.amazonaws.com/qa/api/v1/app/"+app+"/modules/"+module+"/games");
		
		if(app.equals("2")){
			httpGet.setHeader("Authorization", "Bearer " + pgoToken);
		} else if(app.equals("3")){
			httpGet.setHeader("Authorization", "Bearer " + pgnToken);
		}
	
		
		HttpResponse response = null;
		try {
			response = client.execute(httpGet);
			statusCode = response.getStatusLine().toString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		String entity = "";
		try {
			entity = EntityUtils.toString(response.getEntity());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(entity.contains("\"errorMessage\":")){
			return entity;
		} else if(entity.contains("\"message\":")){
			return entity;
		} /*else if(entity.contains("\"body\":[]")){
			callData = (JsonObject) new JsonParser().parse(entity);

			games = new JsonArray();
			return entity;
		}
		*/
		else if(entity.equals("[]")){
			games = new JsonArray();
			return entity;
		}
		else{
			callData = (JsonObject) new JsonParser().parse(entity);
			games = callData.get("games").getAsJsonArray();
			return statusCode;
		}
	}
	

	public GamesData getGameJSONByName(String Type){
		GamesData thisGame = new GamesData(); //"Error", "Error", "Error", "Error", "Error", new JsonObject());
		thisGame.setAppID("Error");
		thisGame.setGameID("Error");
		thisGame.setGameType("Error");
		thisGame.setModuleID("Error");
		thisGame.setGameTypeID("Error");
		thisGame.setGameJSON(new JsonObject());
		thisGame.setAssetData(new ArrayList<SpecificGameData>());
		
		for(JsonElement s: games){
			if(s.toString().contains(Type)){
				JsonObject gameObject = (JsonObject) new JsonParser().parse(s.toString());
				JsonObject gameConfig = (JsonObject) new JsonParser().parse(gameObject.get("game_json").toString());
				
				thisGame = new GamesData();
				thisGame.setAppID(gameObject.get("app_id").toString());
				thisGame.setGameID(gameObject.get("game_id").toString());
				thisGame.setGameType(gameObject.get("game_type").toString().replace("\"", ""));
				thisGame.setModuleID(gameObject.get("module_id").toString());
				thisGame.setGameTypeID(gameObject.get("game_type_id").toString());
				thisGame.setGameJSON(gameConfig);
				
				if(Type.equals("Word Scramble")){
					thisGame.setAssetData(getWordScrambleData(gameConfig));
				}else if(Type.equals("Zoom")){
					thisGame.setAssetData(getZoomData(gameConfig));
				}else if(Type.equals("Jigsaw")){
					thisGame.setAssetData(getJigsawData(gameConfig));
				}else if(Type.equals("Multi Match")){
					thisGame.setAssetData(getMultiMatchData(gameConfig));
				}else if(Type.equals("Quick Match")){
					thisGame.setAssetData(getQuickMatchData(gameConfig));
				}else if(Type.equals("Whack")){
					thisGame.setAssetData(getWhackData(gameConfig));
				}
			}
		}
		
		return thisGame;
	}
	
	public ArrayList<SpecificGameData> getWordScrambleData(JsonObject gameJSON){
		ArrayList<SpecificGameData> data = new ArrayList<SpecificGameData>();
		
		
		JsonArray iterations = gameJSON.get("iterations").getAsJsonArray();
		
		for(JsonElement i : iterations){
			WordScrambleAssetData asset = new WordScrambleAssetData();
			asset.setImage(i.getAsJsonObject().get("image").getAsString());
			asset.setCorrectAnswer(i.getAsJsonObject().get("correct").getAsString());
			asset.setSuggestion(i.getAsJsonObject().get("suggestion").getAsString());
			data.add(asset);
		}
		
		return data;
	}
	
	public ArrayList<SpecificGameData> getZoomData(JsonObject gameJSON){
		ArrayList<SpecificGameData> data = new ArrayList<SpecificGameData>();
		
		
		JsonArray iterations = gameJSON.get("iterations").getAsJsonArray();
		
		for(JsonElement i : iterations){
			ZoomAssetData asset = new ZoomAssetData();
			try{
				asset.setImage(i.getAsJsonObject().get("image").getAsString());
			}catch(Exception e){
				asset.setImage("Error");
			}
			
			try{
				asset.setCorrectAnswer(i.getAsJsonObject().get("correct").getAsString());
			}catch(Exception e){
				asset.setCorrectAnswer("Error");
			}
			
			try{
				asset.setQuestion(i.getAsJsonObject().get("question").getAsString());
			}catch(Exception e){
				asset.setQuestion("Error");
			}

			ArrayList<String> answers = new ArrayList<String>();
			try{

				for(JsonElement j : i.getAsJsonObject().get("answers").getAsJsonArray()){
					answers.add(j.getAsString());
				}
			}catch(Exception e){
				
			}
			
			asset.setAnswers(answers);

			
			data.add(asset);
		}
		
		return data;
	}
	
	public ArrayList<SpecificGameData> getJigsawData(JsonObject gameJSON){
		ArrayList<SpecificGameData> data = new ArrayList<SpecificGameData>();
		
		
		JsonArray iterations = gameJSON.get("jigsaw").getAsJsonArray();
		
		for(JsonElement i : iterations){
			JigsawAssetData asset = new JigsawAssetData();
			try{
				asset.setColumn(i.getAsJsonObject().get("col").getAsString());
			}catch(Exception e){
				asset.setColumn("Error");
			}
			
			try{
				asset.setImage(i.getAsJsonObject().get("img").getAsString());
			}catch(Exception e){
				asset.setImage("Error");
			}
			
			try{
				asset.setRow(i.getAsJsonObject().get("row").getAsString());
			}catch(Exception e){
				asset.setRow("Error");
			}
			
			data.add(asset);
		}
		
		return data;
	}
	
	public ArrayList<SpecificGameData> getMultiMatchData(JsonObject gameJSON){
		ArrayList<SpecificGameData> data = new ArrayList<SpecificGameData>();
		
		
		JsonArray pairs = gameJSON.get("pair").getAsJsonArray();
		
		for(JsonElement i : pairs){
			for(JsonElement j: i.getAsJsonArray()){
				MultiMatchAssetData thisAsset = new MultiMatchAssetData();
				
				JsonElement matchOne = j.getAsJsonObject().get("matchOne").getAsJsonObject();
				JsonElement matchTwo = j.getAsJsonObject().get("matchTwo").getAsJsonObject();
				
				try{
					thisAsset.setName(matchOne.getAsJsonObject().get("name").getAsString());
				} catch(Exception e){
					thisAsset.setName("Error");
				}
				try{
					thisAsset.setImage(matchOne.getAsJsonObject().get("image").getAsString());
				} catch(Exception e){
					thisAsset.setImage("Error");
				}	
				
				try{
					thisAsset.setName(matchTwo.getAsJsonObject().get("name").getAsString());
				} catch(Exception e){
					thisAsset.setName("Error");
				}
				try{
					thisAsset.setImage(matchTwo.getAsJsonObject().get("image").getAsString());
				} catch(Exception e){
					thisAsset.setImage("Error");
				}	
				
				data.add(thisAsset);
			}

		}
		
		return data;
	}
	
	public ArrayList<SpecificGameData> getQuickMatchData(JsonObject gameJSON){
		ArrayList<SpecificGameData> data = new ArrayList<SpecificGameData>();
		
		
		JsonArray pairs = gameJSON.get("pair").getAsJsonArray();
		
		for(JsonElement i : pairs){
			for(JsonElement j: i.getAsJsonArray()){
				QuickMatchAssetData thisAsset = new QuickMatchAssetData();
				
				try{
					thisAsset.setName(j.getAsJsonObject().get("name").getAsString());
				} catch(Exception e){
					thisAsset.setName("Error");
				}
				try{
					thisAsset.setImage(j.getAsJsonObject().get("image").getAsString());
				} catch(Exception e){
					thisAsset.setImage("Error");
				}	
				try{
					thisAsset.setLabel(j.getAsJsonObject().get("label").getAsString());
				} catch(Exception e){
					thisAsset.setLabel("Error");
				}
				try{
					thisAsset.setGameRound(j.getAsJsonObject().get("gameRound").getAsString());
				} catch(Exception e){
					thisAsset.setGameRound("Error");
				}	
				
				data.add(thisAsset);
			}

		}
		
		return data;
	}
	
	public ArrayList<SpecificGameData> getWhackData(JsonObject gameJSON){
		ArrayList<SpecificGameData> data = new ArrayList<SpecificGameData>();
		
		
		JsonArray pairs = gameJSON.get("clueImages").getAsJsonArray();
		
		for(JsonElement i : pairs){
			WhackAssetData thisAsset = new WhackAssetData();
				
			try{
				thisAsset.setCategory(i.getAsJsonObject().get("category").getAsString());
			} catch(Exception e){
				thisAsset.setCategory("Error");
			}
			try{
				thisAsset.setImage(i.getAsJsonObject().get("imagePath").getAsString());
			} catch(Exception e){
				thisAsset.setImage("Error");
			}	
				
			data.add(thisAsset);
		}
		
		pairs = gameJSON.get("assetsImages").getAsJsonArray();
		
		for(JsonElement i : pairs){
			WhackAssetData thisAsset = new WhackAssetData();
				
			try{
				thisAsset.setCategory(i.getAsJsonObject().get("category").getAsString());
			} catch(Exception e){
				thisAsset.setCategory("Error");
			}
			try{
				thisAsset.setImageName(i.getAsJsonObject().get("imageName").getAsString());
			} catch(Exception e){
				thisAsset.setImageName("Error");
			}
			try{
				thisAsset.setImage(i.getAsJsonObject().get("imagePath").getAsString());
			} catch(Exception e){
				thisAsset.setImage("Error");
			}	
				
			data.add(thisAsset);
		}
		return data;
	}
}
