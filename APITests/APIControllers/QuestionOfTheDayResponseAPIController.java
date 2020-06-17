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

import DataClasses.QOTDAnswersData;
import DataClasses.QOTDQuestionsData;

public class QuestionOfTheDayResponseAPIController {
	public JsonObject callData;
	public String status;
	public String token;
	public AuthenticationAPIController authAPI;
	
	public QuestionOfTheDayResponseAPIController(){
		status = "empty";
		authAPI = new AuthenticationAPIController("authqa");
		try {
			token = authAPI.getTokenForUsername("garage", "capdig", "2", "username");
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
	
	public QOTDQuestionsData getQuestionOfTheDayResponseData(String questionNumber, String beginData, String endDate){
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet("https://esapiqa.pebblego.com/api/v1/pollanswer/question/"+questionNumber+"/begin_date/"+beginData+"/end_date/"+endDate);
		httpGet.setHeader("Authorization", "Bearer " + token);
	
		
		HttpResponse response = null;
		try {
			response = client.execute(httpGet);
			status = response.getStatusLine().toString();
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
		

		
		if(entity.contains("\"errorMessage\":")){
			callData = (JsonObject) new JsonParser().parse(entity);
			return new QOTDQuestionsData("\"errorMessage\":","\"errorMessage\":");
		}else if(entity.equals("404 Not Found.")){
			callData = new JsonObject();
			return new QOTDQuestionsData("404 Not Found.","404 Not Found.");
		}else if(entity.contains("\"message\":\"Record not Found\"")){
			callData = (JsonObject) new JsonParser().parse(entity);
			return new QOTDQuestionsData("\"message\":\"Record not Found\"","\"message\":\"Record not Found\"");
		}else if(entity.contains("\"message\":\"Elasticsearch error.\"")){
			callData = (JsonObject) new JsonParser().parse(entity);
			return new QOTDQuestionsData("\"message\":\"Elasticsearch error.\"","\"message\":\"Elasticsearch error.\"");
		}
		else{
			callData = (JsonObject) new JsonParser().parse(entity);
			return getQuestionAndAnswersData();
		}	
	}
	
	public QOTDQuestionsData getQuestionAndAnswersData(){
		ArrayList<QOTDAnswersData> answers = new ArrayList<QOTDAnswersData>();
		
		String questionID;
		if(callData.getAsJsonObject().get("question_id").isJsonNull()){
			questionID = "empty";
		}
		else{
			questionID = callData.getAsJsonObject().get("question_id").getAsString();
		}
		
		String questionText;
		if(callData.getAsJsonObject().get("question_text").isJsonNull()){
			questionText = "empty";
		}
		else{
			questionText = callData.getAsJsonObject().get("question_text").getAsString();
		}
		
		JsonArray answersJSON;
		if(callData.getAsJsonObject().get("answers").isJsonNull()){
			answersJSON = new JsonArray();
		}
		else{
			answersJSON = callData.getAsJsonObject().get("answers").getAsJsonArray();
		}
		
		for(JsonElement j : answersJSON){
			QOTDAnswersData thisAnswer = new QOTDAnswersData(j.getAsJsonObject().get("answer_id").getAsString(), 
					j.getAsJsonObject().get("answer_text").getAsString(), j.getAsJsonObject().get("answer_count").getAsString());
			answers.add(thisAnswer);
		}
		
		return new QOTDQuestionsData(questionID, questionText, answers);
	}
}
