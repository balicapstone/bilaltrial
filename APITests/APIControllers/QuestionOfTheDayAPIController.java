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

public class QuestionOfTheDayAPIController {
	public JsonObject callData;
	public JsonObject body;
	public String statusCode;
	public String entity;
	String masterSQS = "$2y$10$jzNHLCgrGqxscuDKMJqO3OM82SJCtiRnpHjdIdxzuaHrv93VnYjYy";
	String questionNumber;
	String token;
	AuthenticationAPIController authAPI;
	
	public QuestionOfTheDayAPIController(){
		authAPI = new AuthenticationAPIController("authqa");
		
		try {
			token =	authAPI.getTokenForUsername("garage", "capdig", "2", "username"); //("2", "cappyducky", masterSQS);
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
	
	public QOTDQuestionsData getQuestionOfTheDayData(String questionNumber){
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet("https://3ng5u5zewd.execute-api.us-east-2.amazonaws.com/qa/api/v1/app/2/question/"+questionNumber);
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
		
		
		try {
			entity = EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		
		if(entity.contains("\"errorMessage\":")){
			callData = (JsonObject) new JsonParser().parse(entity);
			return new QOTDQuestionsData("\"errorMessage\":","\"errorMessage\":");
		} else if(entity.contains("{\"message\": \"Internal server error\"}")){
			callData = (JsonObject) new JsonParser().parse(entity);
			return new QOTDQuestionsData("{\"message\": \"Internal server error\"}","{\"message\": \"Internal server error\"}");
		} else if(entity.contains("\"message\":\"Unauthorized\"")){
			callData = (JsonObject) new JsonParser().parse(entity);
			return new QOTDQuestionsData("\"message\":\"Unauthorized\"","\"message\":\"Unauthorized\"");
		} else if(entity.equals("{}")){
			return new QOTDQuestionsData("entity", "entity");
		}
		else{
			callData = (JsonObject) new JsonParser().parse(entity);
			return getQuestionAndAnswersData();
		}	
	}
	
	
	public QOTDQuestionsData getQuestionAndAnswersData(){
		ArrayList<QOTDAnswersData> answers = new ArrayList<QOTDAnswersData>();
		
		String questionID = callData.getAsJsonObject().get("question_id").getAsString();
		String questionText = callData.getAsJsonObject().get("question_text").getAsString();
		
		JsonArray answersJSON = callData.getAsJsonObject().get("answers").getAsJsonArray();

		for(JsonElement j : answersJSON){
			QOTDAnswersData thisAnswer = new QOTDAnswersData();
			
			thisAnswer.setImage(j.getAsJsonObject().get("image").getAsString());
			thisAnswer.setID(j.getAsJsonObject().get("answer_id").getAsString());
			thisAnswer.setAnswer(j.getAsJsonObject().get("answer_text").getAsString());
			
			answers.add(thisAnswer);
		}
		
		return new QOTDQuestionsData(questionID, questionText, answers);
	}
}
