package APIControllers;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DataClasses.ReadMoreBookData;

public class ReadMoreContentAPIController {
	public JsonObject callData;
	public String statusCode;
	public String entity;
	String token;
	AuthenticationAPIController authAPI;
	
	public ReadMoreContentAPIController(){
		authAPI = new AuthenticationAPIController("authqa");
		
		try {
			token =	authAPI.getTokenForUsername("garage", "capdig", "2", "username");
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
	
	public ReadMoreBookData getReadMoreBookData(String isbn){
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet("https://3ng5u5zewd.execute-api.us-east-2.amazonaws.com/qa/api/v1/app/2/book/" + isbn);
		
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
			return new ReadMoreBookData();
		}
		else if(entity.equals("[]")){
			return new ReadMoreBookData();
		}
		else{
			callData = (JsonObject) new JsonParser().parse(entity);
			return getBookObject();
		}
	}
	
	public ReadMoreBookData getBookObject(){
		ReadMoreBookData book = new ReadMoreBookData();

		try{
			book.setAtos(callData.getAsJsonObject().get("atos").getAsString());
		} catch(Exception e){
			book.setAtos("null");
		}
		
		try{
			book.setBrand(callData.getAsJsonObject().get("brand").getAsString());
		} catch(Exception e){
			book.setBrand("null");
		}
		
		try{
			book.setTitle(callData.getAsJsonObject().get("title").getAsString());
		} catch(Exception e){
			book.setTitle("null");
		}
		
		try{
			book.setAuthor(callData.getAsJsonObject().get("author").getAsString());
		} catch(Exception e){
			book.setAuthor("null");
		}
		
		try{
			book.setSeries(callData.getAsJsonObject().get("series").getAsString());
		} catch(Exception e){
			book.setSeries("null");
		}
		
		try{
			book.setLanguage(callData.getAsJsonObject().get("language").getAsString());
		} catch(Exception e){
			book.setLanguage("null");
		}
		
		try{
			book.setCopyright(callData.getAsJsonObject().get("copyright").getAsString());
		} catch(Exception e){
			book.setCopyright("null");
		}
		
		try{
			book.setEpubISBN(callData.getAsJsonObject().get("epub_isbn").getAsString());
		} catch(Exception e){
			book.setEpubISBN("null");
		}
		
		try{
			book.setPublisher(callData.getAsJsonObject().get("publisher").getAsString());
		} catch(Exception e){
			book.setPublisher("null");
		}
		
		try{
			book.setPrintISBN(callData.getAsJsonObject().get("print_isbn").getAsString());
		} catch(Exception e){
			book.setPrintISBN("null");
		}
		
		try{
			book.setDescription(callData.getAsJsonObject().get("description").getAsString());
		} catch(Exception e){
			book.setDescription("null");
		}
		
		try{
			book.setLexileLabel(callData.getAsJsonObject().get("lexile_label").getAsString());
		} catch(Exception e){
			book.setLexileLabel("null");
		}
		
		try{
			book.setMaxGradeLevel(callData.getAsJsonObject().get("max_grade_level").getAsString());
		} catch(Exception e){
			book.setMaxGradeLevel("null");
		}
		
		try{
			book.setMinGradeLevel(callData.getAsJsonObject().get("min_grade_level").getAsString());
		} catch(Exception e){
			book.setMinGradeLevel("null");
		}
		
		try{
			book.setAudioRuntimeSecs(callData.getAsJsonObject().get("audio_runtime_secs").getAsString());
		} catch(Exception e){
			book.setAudioRuntimeSecs("null");
		}
		
		try{
			book.setGuidedReadingLevel(callData.getAsJsonObject().get("guided_reading_level").getAsString());
		} catch(Exception e){
			book.setGuidedReadingLevel("null");
		}
		
		try{
			book.setARReaderQuizID(callData.getAsJsonObject().get("accelerated_reader_quiz_id").getAsString());
		} catch(Exception e){
			book.setARReaderQuizID("null");
		}
		
		return book;
	}
}
