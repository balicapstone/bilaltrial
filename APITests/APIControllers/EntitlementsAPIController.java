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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DataClasses.EntitlementsData;

public class EntitlementsAPIController {
	public JsonObject callData;
	public String statusCode;
	public EntitlementsData entitlements;
	public AuthenticationAPIController authAPI;
	String token;
	
	
	public EntitlementsAPIController(){
		
	}
	
	public String getEntitlementsForApp(String token){
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet("http://authqa.pebblego.com/api/v1/entitlements");
		httpGet.setHeader("Authorization", "Bearer " + token);
	
		
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
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		
		if(entity.contains("\"error\":")){
			return entity;
		} 
		else{
			callData = (JsonObject) new JsonParser().parse(entity);
			
			ArrayList<String> ownedContent = new ArrayList<String>();
			if(callData.has("ownedContent")){
				for(JsonElement j : callData.get("ownedContent").getAsJsonArray()){
					ownedContent.add(j.getAsString());
				}
			}

			
			ArrayList<String> bannedArticles = new ArrayList<String>();
			if(callData.has("bannedArticles")){
				for(JsonElement j : callData.get("bannedArticles").getAsJsonArray()){
					bannedArticles.add(j.getAsString());
				}
			}

			
			ArrayList<String> bannedCategories = new ArrayList<String>();
			if(callData.has("bannedCategories")){
				for(JsonElement j : callData.get("bannedCategories").getAsJsonArray()){
					bannedCategories.add(j.getAsString());
				}
			}

			
			entitlements = new EntitlementsData(ownedContent, bannedArticles, bannedCategories);
			
			return entity;
		}
	}
}
