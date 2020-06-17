package APIControllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import DataClasses.GlossaryData;
import SharedClasses.BasePage;

public class GlossaryTermsAPIController extends BasePage{
	public JsonArray callData;
	public String statusCode;
	public String entity;
	String studentSQS = "7d442e22387688a9ae75e6070f10f28eaf020efbdde3816433ee2622a6b11378";
	String appNumber;
	String moduleNumber;
	String token;
	AuthenticationAPIController authAPI;
	
	public GlossaryTermsAPIController(){
		authAPI = new AuthenticationAPIController("authqa");
		
		try {
			token =	authAPI.getTokenForSQS("2", "sqs", studentSQS);
		} catch (ParseException e) {
			token = "***Error getting token***";
			System.out.println(token + "\n");
			e.printStackTrace();
		} catch (IOException e) {
			token = "***Error getting token***";
			System.out.println(token + "\n");
			e.printStackTrace();
		}
	}
	
	public ArrayList<GlossaryData> getGlossaryTerm(String glossaryTerms){
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("https://3ng5u5zewd.execute-api.us-east-2.amazonaws.com/qa/api/v1/app/2/glossary");
		
		httpPost.setHeader("Authorization", "Bearer " + token);
		
		/*
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("glossary_ids", "[13, 16, 78, 88, 66]"));
		
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		*/
		
		try {
			httpPost.setEntity(new StringEntity("{\"glossary_ids\": ["+glossaryTerms+"]}"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		HttpResponse response = null;
		try {
			response = client.execute(httpPost);
			statusCode = response.getStatusLine().toString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			entity = EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		
		if(entity.contains("\"errorMessage\":")){
			return new ArrayList<GlossaryData>(); //new GlossaryData(entity, entity, entity, entity, entity, entity, new ArrayList<String>());
		}
		else if(entity.contains("\"message\":")){
			return new ArrayList<GlossaryData>();
		}
		else if(entity.equals("[]")){
			return new ArrayList<GlossaryData>();//new GlossaryData(entity, entity, entity, entity, entity, entity, new ArrayList<String>());
		}
		else{
			callData = (JsonArray) new JsonParser().parse(entity);
			return getGlossaryObject();
		}
	}
	
	public ArrayList<GlossaryData> getGlossaryObject(){

		ArrayList<GlossaryData> glossaries = new ArrayList<GlossaryData>();
		
		for(JsonElement j : callData){
			ArrayList<String> descriptionTimecode = new ArrayList<String>();
			GlossaryData thisTerm = new GlossaryData("Error", "Error", "Error", "Error", "Error", "Error", descriptionTimecode);
			
			String xml = j.getAsJsonObject().get("description_timecoded").toString().replace("\"", "").replace("\\", "");
			String[] xmlPieces = xml.split("text=");
			
			for(int i = 1; i < xmlPieces.length; i++){
				descriptionTimecode.add(xmlPieces[i].substring(0, xmlPieces[i].indexOf(">")));
			}
			
			thisTerm = new GlossaryData(j.getAsJsonObject().get("word").toString().replace("\"", ""), 
					j.getAsJsonObject().get("base_word").toString().replace("\"", ""),
					j.getAsJsonObject().get("time_file").toString().replace("\"", ""),
					j.getAsJsonObject().get("audio_file").toString().replace("\"", ""),
					j.getAsJsonObject().get("description").toString().replace("\"", ""),
					j.getAsJsonObject().get("glossary_id").toString(),
					descriptionTimecode);
			
			glossaries.add(thisTerm);
		}
		
		return glossaries;
	}
}
