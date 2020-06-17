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

import DataClasses.ActivityData;
import DataClasses.ArticleData;
import DataClasses.ArticleGlossaryData;
import DataClasses.DocumentData;
import DataClasses.Image;
import DataClasses.LandmarkData;
import DataClasses.PGNFact;
import DataClasses.PGNPersonsData;
import DataClasses.PGNRecipeData;
import DataClasses.PGOScreen;
import DataClasses.RangeMapData;
import DataClasses.SongData;
import DataClasses.SymbolData;
import DataClasses.TimelineData;
import SharedClasses.BasePage;

public class ArticleAPIController extends BasePage{
	public String statusCode;
	public JsonObject parsedData;
	public String entity;
	JsonArray games;
	String pgoToken;
	String pgnToken;
	AuthenticationAPIController authAPI;
	
	public ArticleAPIController(){
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
	
	public ArticleData getPGOArticleJSON(String app, String article){
		CloseableHttpClient client = HttpClientBuilder.create().build();
		
		HttpGet httpGet= null;
		try{
			httpGet = new HttpGet("https://3ng5u5zewd.execute-api.us-east-2.amazonaws.com/qa/api/v1/app/"+app+"/article/" + article);
		} catch(Exception h){
			entity = "";
			return new ArticleData();
		}
		httpGet.setHeader("Authorization", "Bearer " + pgoToken);
	
		
		HttpResponse response = null;
		try {
			response = client.execute(httpGet);
			statusCode = response.getStatusLine().toString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		entity = "";
		try {
			entity = EntityUtils.toString(response.getEntity());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try{
			parsedData = (JsonObject) new JsonParser().parse(entity);
		} catch(Exception p){
			parsedData = new JsonObject();
		}
		
		ArticleData currentData = new ArticleData();
		
		if(entity.equals("[]")){
			return currentData;
		} else if(entity.contains("Internal server error")){
			return currentData;
		} else if(entity.contains("Unauthorized")){
			return currentData;
		}

		if(parsedData.get("sort").isJsonNull()){
			currentData.setSort("empty");
		}else{
			currentData.setSort(parsedData.get("sort").getAsString());
		}
		
		if(parsedData.get("type").isJsonNull()){
			currentData.setType("empty");
		}else{
			currentData.setType(parsedData.get("type").getAsString());
		}
		
		if(parsedData.get("title").isJsonNull()){
			currentData.setTitle("empty");
		}else{
			currentData.setTitle(parsedData.get("title").getAsString());
		}
		
		if(parsedData.get("videos").isJsonNull()){
			currentData.setVideos(new JsonArray());
		}else{
			currentData.setVideos(parsedData.get("videos").getAsJsonArray());
		}
		
		if(parsedData.get("us_only").isJsonNull()){
			currentData.setUSOnly("empty");
		}else{
			currentData.setUSOnly(parsedData.get("us_only").getAsString());
		}
		
		if(parsedData.get("base_name").isJsonNull()){
			currentData.setBaseName("empty");
		}else{
			currentData.setBaseName(parsedData.get("base_name").getAsString());
		}
		
		if(parsedData.get("module_id").isJsonNull()){
			currentData.setModuleID("empty");
		}else{
			currentData.setModuleID(parsedData.get("module_id").getAsString());
		}
		
		if(parsedData.get("article_id").isJsonNull()){
			currentData.setArticleID("empty");
		}else{
			currentData.setArticleID(parsedData.get("article_id").getAsString());
		}
		
		if(parsedData.get("main_image").isJsonNull()){
			currentData.setMainImage("empty");
		}else{
			currentData.setMainImage(parsedData.get("main_image").getAsString());
		}
		
		if(parsedData.get("menu_audio").isJsonNull()){
			currentData.setMenuAudio("empty");
		}else{
			currentData.setMenuAudio(parsedData.get("menu_audio").getAsString());
		}
		
		if(parsedData.get("texas_only").isJsonNull()){
			currentData.setTexasOnly("empty");
		}else{
			currentData.setTexasOnly(parsedData.get("texas_only").getAsString());
		}
		
		if(parsedData.get("language_id").isJsonNull()){
			currentData.setLanduageID("empty");
		}else{
			currentData.setLanduageID(parsedData.get("language_id").getAsString());
		}
		
		try{
			if(parsedData.get("article_range_map").isJsonNull()){
				currentData.setMapFileName(new RangeMapData());
			} else{
				RangeMapData range = new RangeMapData();
				
				if(parsedData.get("article_range_map").getAsJsonObject().get("alt_text").isJsonNull()){
					range.setAltText("null");
				}
				else{
					range.setAltText(parsedData.get("article_range_map").getAsJsonObject().get("alt_text").toString());
				}
				
				if(parsedData.get("article_range_map").getAsJsonObject().get("map_filename").isJsonNull()){
					range.setMapFilename("null");
				}
				else{
					range.setMapFilename(parsedData.get("article_range_map").getAsJsonObject().get("map_filename").toString().replace("\"", ""));
				}
				
				if(parsedData.get("article_range_map").getAsJsonObject().get("range_map_json").isJsonNull()){
					range.setRangeMapJson("null");
				}
				else{
					range.setRangeMapJson(parsedData.get("article_range_map").getAsJsonObject().get("range_map_json").toString());
				}
				
				currentData.setMapFileName(range);
			}
		}
		catch(Exception e){
			currentData.setMapFileName(new RangeMapData());
		}

		//Handle Translations better
		if(parsedData.get("translations").isJsonNull()){
			currentData.setTranslations(new JsonArray());
		}else{
			currentData.setTranslations(parsedData.get("translations").getAsJsonArray());
		}
		
		if(parsedData.get("article_persons").isJsonNull()){
			currentData.setArticlePGOPersons(new JsonArray());
		}else{
			currentData.setArticlePGOPersons(parsedData.get("article_persons").getAsJsonArray());
		}

		//Handle Article Screens better
		if(parsedData.get("article_screens").isJsonNull()){
			currentData.setArticleScreens(new ArrayList<PGOScreen>());
		}else{
			currentData.setArticleScreens(getPGOScreens(parsedData.get("article_screens").getAsJsonArray()));
		}
		
		if(parsedData.get("article_symbols").isJsonNull()){
			currentData.setArticleSymbols(new ArrayList<SymbolData>());
		}else{
			currentData.setArticleSymbols(generateSymbolData(parsedData.get("article_symbols").getAsJsonArray()));
		}

		//HandleArticleActivities
		currentData.setActivityData(generatePGOActivityData(parsedData.get("article_activity").getAsJsonObject()));
		
		if(parsedData.get("related_articles").isJsonNull()){
			currentData.setRelatedArticles(new JsonArray());
		}else{
			currentData.setRelatedArticles(parsedData.get("related_articles").getAsJsonArray());
		}
		
		if(parsedData.get("article_landmarks").isJsonNull()){
			currentData.setArticleLandmarks(new ArrayList<LandmarkData>());
		}else{
			currentData.setArticleLandmarks(generateLandmarkData(parsedData.get("article_landmarks").getAsJsonArray()));
		}
		
		if(parsedData.get("article_timelines").isJsonNull()){
			currentData.setArticleTimelines(new ArrayList<TimelineData>());
		}else{
			currentData.setArticleTimelines(getTimelineDataArray(parsedData.get("article_timelines").getAsJsonArray()));
		}
		
		if(parsedData.get("languageISO3166_2").isJsonNull()){
			currentData.setLanguageISOString("empty");
		}else{
			currentData.setLanguageISOString(parsedData.get("languageISO3166_2").getAsString());
		}
		
		if(parsedData.get("article_glossaries")==null){
			currentData.setGlossaries(new ArrayList<ArticleGlossaryData>());
		}else{
			currentData.setGlossaries(generateGlossaryData(parsedData.get("article_glossaries").getAsJsonArray()));
		}
		
		if(parsedData.get("animal_sound_filename").isJsonNull()){
			currentData.setSoundFile("empty");
		}else{
			currentData.setSoundFile(parsedData.get("animal_sound_filename").getAsString());
		}
		
		return currentData;
	}
	
	public ArticleData getPGNArticleJSON(String app, String article){
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet("https://3ng5u5zewd.execute-api.us-east-2.amazonaws.com/qa/api/v1/app/"+app+"/article/" + article);
		httpGet.setHeader("Authorization", "Bearer " + pgnToken);
	
		
		HttpResponse response = null;
		try {
			response = client.execute(httpGet);
			statusCode = response.getStatusLine().toString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		entity = "";
		try {
			entity = EntityUtils.toString(response.getEntity());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try{
			parsedData = (JsonObject) new JsonParser().parse(entity);
		} catch(Exception p){
			parsedData = new JsonObject();
		}
		
		ArticleData currentData = new ArticleData();
		
		if(entity.equals("[]")){
			return currentData;
		} else if(entity.contains("Internal server error")){
			return currentData;
		} else if(entity.equals("")){
			return currentData;
		}

		if(parsedData.get("sort").isJsonNull()){
			currentData.setSort("empty");
		}else{
			currentData.setSort(parsedData.get("sort").getAsString());
		}
		
		if(parsedData.get("type").isJsonNull()){
			currentData.setType("empty");
		}else{
			currentData.setType(parsedData.get("type").getAsString());
		}
		
		if(parsedData.get("title").isJsonNull()){
			currentData.setTitle("empty");
		}else{
			currentData.setTitle(parsedData.get("title").getAsString());
		}
		
		if(parsedData.get("videos").isJsonNull()){
			currentData.setVideos(new JsonArray());
		}else{
			currentData.setVideos(parsedData.get("videos").getAsJsonArray());
		}
		
		if(parsedData.get("us_only").isJsonNull()){
			currentData.setUSOnly("empty");
		}else{
			currentData.setUSOnly(parsedData.get("us_only").getAsString());
		}
		
		if(parsedData.get("base_name").isJsonNull()){
			currentData.setBaseName("empty");
		}else{
			currentData.setBaseName(parsedData.get("base_name").getAsString());
		}
		
		if(parsedData.get("module_id").isJsonNull()){
			currentData.setModuleID("empty");
		}else{
			currentData.setModuleID(parsedData.get("module_id").getAsString());
		}
		
		if(parsedData.get("article_id").isJsonNull()){
			currentData.setArticleID("empty");
		}else{
			currentData.setArticleID(parsedData.get("article_id").getAsString());
		}
		
		if(parsedData.get("main_image").isJsonNull()){
			currentData.setMainImage("empty");
		}else{
			currentData.setMainImage(parsedData.get("main_image").getAsString());
		}
		
		if(parsedData.get("menu_audio").isJsonNull()){
			currentData.setMenuAudio("empty");
		}else{
			currentData.setMenuAudio(parsedData.get("menu_audio").getAsString());
		}
		
		if(parsedData.get("texas_only").isJsonNull()){
			currentData.setTexasOnly("empty");
		}else{
			currentData.setTexasOnly(parsedData.get("texas_only").getAsString());
		}
		
		if(parsedData.get("language_id").isJsonNull()){
			currentData.setLanduageID("empty");
		}else{
			currentData.setLanduageID(parsedData.get("language_id").getAsString());
		}
		
		if(parsedData.get("article_song").isJsonNull()){
			currentData.setPGNSong(new SongData("empty", "empty", "empty", "empty", "empty"));
		}else{
			JsonObject song = parsedData.get("article_song").getAsJsonObject();
			currentData.setPGNSong(new SongData(song.get("info").getAsString(), song.get("text").getAsString().replace("\u2028", ""),
					song.get("title").getAsString(), song.get("song_id").getAsString(), song.get("article_id").getAsString()));
		}
		
		try{
			if(parsedData.get("article_range_map").isJsonNull()){
				currentData.setMapFileName(new RangeMapData());
			}else{
				RangeMapData range = new RangeMapData();
				
				if(parsedData.get("article_range_map").getAsJsonObject().get("alt_text").isJsonNull()){
					range.setAltText("null");
				}
				else{
					range.setAltText(parsedData.get("article_range_map").getAsJsonObject().get("alt_text").toString());
				}
				
				if(parsedData.get("article_range_map").getAsJsonObject().get("map_filename").isJsonNull()){
					range.setMapFilename("null");
				}
				else{
					range.setMapFilename(parsedData.get("article_range_map").getAsJsonObject().get("map_filename").toString().replace("\"", ""));
				}
				
				if(parsedData.get("article_range_map").getAsJsonObject().get("range_map_json").isJsonNull()){
					range.setRangeMapJson("null");
				}
				else{
					range.setRangeMapJson(parsedData.get("article_range_map").getAsJsonObject().get("range_map_json").toString());
				}
				
				currentData.setMapFileName(range);
			}
		} catch(Exception e){
			currentData.setMapFileName(new RangeMapData());
		}


		//Handle Translations better
		if(parsedData.get("translations").isJsonNull()){
			currentData.setTranslations(new JsonArray());
		}else{
			currentData.setTranslations(parsedData.get("translations").getAsJsonArray());
		}
		
		if(parsedData.get("article_facts").isJsonNull()){
			currentData.setTranslations(new JsonArray());
		}else{
			currentData.setPGNFacts(generatePGNFactData(parsedData.get("article_facts").getAsJsonArray()));
		}
		
		if(parsedData.get("article_recipe").isJsonNull()){
			currentData.setTranslations(new JsonArray());
		}else{
			currentData.setPGNRecipe(generatePGNRecipeData(parsedData.get("article_recipe").getAsJsonObject()));
		}
		
		
		if(parsedData.get("article_persons").isJsonNull()){
			currentData.setPGNPersonsData(new ArrayList<PGNPersonsData>());
		}else{
			currentData.setPGNPersonsData(generatePGNPersonsData(parsedData.get("article_persons").getAsJsonArray()));
		}

		//Handle Article Screens better
		if(parsedData.get("article_screens").isJsonNull()){
			currentData.setArticleScreens(new ArrayList<PGOScreen>());
		}else{
			currentData.setArticleScreens(getPGNScreens(parsedData.get("article_screens").getAsJsonArray()));
		}
		
		if(parsedData.get("article_symbols").isJsonNull()){
			currentData.setArticleSymbols(new ArrayList<SymbolData>());
		}else{
			currentData.setArticleSymbols(generateSymbolData(parsedData.get("article_symbols").getAsJsonArray()));
		}

		//HandleArticleActivities
		currentData.setPGNDocumentData(generatePGNDocumentData(parsedData.get("article_documents").getAsJsonArray()));
		
		if(parsedData.get("related_articles").isJsonNull()){
			currentData.setRelatedArticles(new JsonArray());
		}else{
			currentData.setRelatedArticles(parsedData.get("related_articles").getAsJsonArray());
		}
		
		if(parsedData.get("article_landmarks").isJsonNull()){
			currentData.setArticleLandmarks(new ArrayList<LandmarkData>());
		}else{
			currentData.setArticleLandmarks(generateLandmarkData(parsedData.get("article_landmarks").getAsJsonArray()));
		}
		
		if(parsedData.get("article_timelines").isJsonNull()){
			currentData.setArticleTimelines(new ArrayList<TimelineData>());
		}else{
			currentData.setArticleTimelines(getTimelineDataArray(parsedData.get("article_timelines").getAsJsonArray()));
		}
		
		if(parsedData.get("languageISO3166_2").isJsonNull()){
			currentData.setLanguageISOString("empty");
		}else{
			currentData.setLanguageISOString(parsedData.get("languageISO3166_2").getAsString());
		}
		
		if(parsedData.get("animal_sound_filename").isJsonNull()){
			currentData.setSoundFile("empty");
		}else{
			currentData.setSoundFile(parsedData.get("animal_sound_filename").getAsString());
		}
		
		return currentData;
	}
	
	public ActivityData generatePGOActivityData(JsonObject activityData){
		ActivityData data = new ActivityData();
		
		if(activityData.get("title").isJsonNull()){
			data.setTitle("empty");
		}else{
			data.setTitle(activityData.get("title").getAsString());
		}
		
		if(activityData.get("prompt1").isJsonNull()){
			data.setPrompt1("empty");
		}else{
			data.setPrompt1(activityData.get("prompt1").getAsString());
		}
		
		if(activityData.get("prompt2").isJsonNull()){
			data.setPrompt2("empty");
		}else{
			data.setPrompt2(activityData.get("prompt2").getAsString());
		}
		
		if(activityData.get("prompt3").isJsonNull()){
			data.setPrompt3("empty");
		}else{
			data.setPrompt3(activityData.get("prompt3").getAsString());
		}
		
		if(activityData.get("draw_title").isJsonNull()){
			data.setDrawTitle("empty");
		}else{
			data.setDrawTitle(activityData.get("draw_title").getAsString());
		}
		
		if(activityData.get("share_file").isJsonNull()){
			data.setShareFile("empty");
		}else{
			data.setShareFile(activityData.get("share_file").getAsString());
		}
		
		if(activityData.get("activity_file").isJsonNull()){
			data.setActivityFile("empty");
		}else{
			data.setActivityFile(activityData.get("activity_file").getAsString());
		}
		
		if(activityData.get("question_file").isJsonNull()){
			data.setQuestionFile("empty");
		}else{
			data.setQuestionFile(activityData.get("question_file").getAsString());
		}
		
		if(activityData.get("auto_gen_share").isJsonNull()){
			data.setAutoGenShare("empty");
		}else{
			data.setAutoGenShare(activityData.get("auto_gen_share").getAsString());
		}
		
		if(activityData.get("auto_gen_activity").isJsonNull()){
			data.setAutoGenActivity("empty");
		}else{
			data.setAutoGenActivity(activityData.get("auto_gen_activity").getAsString());
		}
		
		if(activityData.get("auto_gen_question").isJsonNull()){
			data.setAutoGenQuestion("empty");
		}else{
			data.setAutoGenQuestion(activityData.get("auto_gen_question").getAsString());
		}
		
		return data;
	}
	
	public ArrayList<PGOScreen> getPGOScreens(JsonArray screens){
		ArrayList<PGOScreen> articleScreens = new ArrayList<PGOScreen>();
		
		for(JsonElement j : screens){
			ArrayList<String> timecodeWords = new ArrayList<String>();
			
			String xml = j.getAsJsonObject().get("content_timecoded").toString().replace("\"", "").replace("\\", "");
		
			String[] xmlPieces = xml.split("</span>");
			int endNumber = xmlPieces.length-1;
			
			for(int i = 0; i <= endNumber; i++){
				if(!xmlPieces[i].equals("n</li></ul>")){
					String thisXML = xmlPieces[i].replace("<br>", "");

					if(!thisXML.equals(" ")){
						try{
							timecodeWords.add(thisXML.substring(thisXML.indexOf("highlight>")+10, thisXML.length()));
						}catch(Exception e){
						}
						
					}
				}
			}
			
			PGOScreen thisScreen = new PGOScreen();
			
			if(j.getAsJsonObject().get("title").isJsonNull()){
				thisScreen.setTitle("empty");
			}else{
				thisScreen.setTitle(j.getAsJsonObject().get("title").getAsString());
			}
			
			if(j.getAsJsonObject().get("images").isJsonNull()){
				thisScreen.setImages(new ArrayList<Image>());
			}
			else{
				JsonArray images = j.getAsJsonObject().get("images").getAsJsonArray();
				
				ArrayList<Image> imagesData = new ArrayList<Image>();
				
				for(JsonElement i : images){
					Image currentImage = new Image();
					currentImage.setImage(i.getAsJsonObject().get("image").getAsString());
					currentImage.setCaption(i.getAsJsonObject().get("caption").getAsString());
					currentImage.setScreenImageID(i.getAsJsonObject().get("article_screen_image_id").getAsString());
					
					imagesData.add(currentImage);
				}
				
				thisScreen.setImages(imagesData);
			}
			
			if(j.getAsJsonObject().get("number").isJsonNull()){
				thisScreen.setNumber("empty");
			}else{
				thisScreen.setNumber(j.getAsJsonObject().get("number").getAsString());
			}
			
			if(j.getAsJsonObject().get("content").isJsonNull()){
				thisScreen.setContent("empty");
			}else{
				thisScreen.setContent(j.getAsJsonObject().get("content").getAsString());
			}
			
			if(j.getAsJsonObject().get("layout_id").isJsonNull()){
				thisScreen.setLayoutID("empty");
			}else{
				thisScreen.setLayoutID(j.getAsJsonObject().get("layout_id").getAsString());
			}
			
			if(j.getAsJsonObject().get("screen_id").isJsonNull()){
				thisScreen.setScreenID("empty");
			}else{
				thisScreen.setScreenID(j.getAsJsonObject().get("screen_id").getAsString());
			}
			
			if(j.getAsJsonObject().get("time_file").isJsonNull()){
				thisScreen.setTimeFile("empty");
			}else{
				thisScreen.setTimeFile(j.getAsJsonObject().get("time_file").getAsString());
			}
			
			if(j.getAsJsonObject().get("audio_file").isJsonNull()){
				thisScreen.setAudioFile("empty");
			}else{
				thisScreen.setAudioFile(j.getAsJsonObject().get("audio_file").getAsString());
			}
			
			if(j.getAsJsonObject().get("title_audio_file").isJsonNull()){
				thisScreen.setTitleAudioFile("empty");
			}else{
				thisScreen.setTitleAudioFile(j.getAsJsonObject().get("title_audio_file").getAsString());
			}
			
			thisScreen.setScreenTimeCode(timecodeWords);
			
			articleScreens.add(thisScreen);
		}
		
		return articleScreens;
	}
	
	public ArrayList<PGOScreen> getPGNScreens(JsonArray screens){
		ArrayList<PGOScreen> articleScreens = new ArrayList<PGOScreen>();
		
		for(JsonElement j : screens){
			ArrayList<String> timecodeWords = new ArrayList<String>();
			
			String xml = j.getAsJsonObject().get("content_timecoded").toString().replace("\"", "").replace("\\", "");
		
			String[] xmlPieces = xml.split("</span>");
			int endNumber = xmlPieces.length-1;
			
			for(int i = 0; i <= endNumber; i++){
				if(!xmlPieces[i].equals("n</li></ul>")){
					String thisXML = xmlPieces[i].replace("<br>", "");

					if(!thisXML.equals(" ")){
						try{
							timecodeWords.add(thisXML.substring(thisXML.indexOf(">")+1, thisXML.length()));
						}catch(Exception e){
						}
						
					}
				}
			}
			
			PGOScreen thisScreen = new PGOScreen();
			
			if(j.getAsJsonObject().get("title").isJsonNull()){
				thisScreen.setTitle("empty");
			}else{
				thisScreen.setTitle(j.getAsJsonObject().get("title").getAsString());
			}
			
			if(j.getAsJsonObject().get("number").isJsonNull()){
				thisScreen.setNumber("empty");
			}else{
				thisScreen.setNumber(j.getAsJsonObject().get("number").getAsString());
			}
			
			if(j.getAsJsonObject().get("content").isJsonNull()){
				thisScreen.setContent("empty");
			}else{
				thisScreen.setContent(j.getAsJsonObject().get("content").getAsString());
			}
			
			if(j.getAsJsonObject().get("layout_id").isJsonNull()){
				thisScreen.setLayoutID("empty");
			}else{
				thisScreen.setLayoutID(j.getAsJsonObject().get("layout_id").getAsString());
			}
			
			if(j.getAsJsonObject().get("screen_id").isJsonNull()){
				thisScreen.setScreenID("empty");
			}else{
				thisScreen.setScreenID(j.getAsJsonObject().get("screen_id").getAsString());
			}
			
			if(j.getAsJsonObject().get("time_file").isJsonNull()){
				thisScreen.setTimeFile("empty");
			}else{
				thisScreen.setTimeFile(j.getAsJsonObject().get("time_file").getAsString());
			}
			
			if(j.getAsJsonObject().get("audio_file").isJsonNull()){
				thisScreen.setAudioFile("empty");
			}else{
				thisScreen.setAudioFile(j.getAsJsonObject().get("audio_file").getAsString());
			}
			
			if(j.getAsJsonObject().get("title_audio_file").isJsonNull()){
				thisScreen.setTitleAudioFile("empty");
			}else{
				thisScreen.setTitleAudioFile(j.getAsJsonObject().get("title_audio_file").getAsString());
			}
			
			thisScreen.setScreenTimeCode(timecodeWords);
			
			articleScreens.add(thisScreen);
		}
		
		return articleScreens;
	}
	
	public ArrayList<TimelineData> getTimelineDataArray(JsonArray data){
		ArrayList<TimelineData> timelineData = new ArrayList<TimelineData>();
		
		for(JsonElement j : data){
			ArrayList<String> timecodeWords = new ArrayList<String>();
			
			String xml = j.getAsJsonObject().get("description_timecoded").toString().replace("\"", "").replace("\\", "");
		
			String[] xmlPieces = xml.split("</span>");
			int endNumber = xmlPieces.length-1;
			
			for(int i = 0; i <= endNumber; i++){
				if(!xmlPieces[i].equals("n</li></ul>")){
					String thisXML = xmlPieces[i];

					try{
						timecodeWords.add(thisXML.substring(thisXML.indexOf(">")+1, thisXML.length()));
					}catch(Exception e){
							System.out.println(thisXML);
					}
				}
			}
			
			ArrayList<String> dateTimeCode = new ArrayList<String>();
			xml = j.getAsJsonObject().get("date_timecoded").toString().replace("\"", "").replace("\\", "");
			
			xmlPieces = xml.split("</span>");
			endNumber = xmlPieces.length-1;
			
			for(int i = 0; i <= endNumber; i++){
				if(!xmlPieces[i].equals("n</li></ul>")){
					String thisXML = xmlPieces[i];

					try{
						dateTimeCode.add(thisXML.substring(thisXML.indexOf(">")+1, thisXML.length()));
					}catch(Exception e){
							System.out.println(thisXML);
					}
				}
			}
			
			TimelineData currentTimeline = new TimelineData(j.getAsJsonObject().get("date1").getAsString(),
					j.getAsJsonObject().get("date2").getAsString(),
					j.getAsJsonObject().get("timeline_id").getAsString(),
					j.getAsJsonObject().get("description").getAsString(),
					j.getAsJsonObject().get("audio_file").getAsString(),
					j.getAsJsonObject().get("timecode_file").getAsString(),
					dateTimeCode,
					timecodeWords);
			
			timelineData.add(currentTimeline);
		}
		
		return timelineData;
	}
	
	public ArrayList<DocumentData> generatePGNDocumentData(JsonArray documents){
		ArrayList<DocumentData> documentsToReturn = new ArrayList<DocumentData>();
		
		for(JsonElement j : documents){
			try{
				DocumentData currentDoc = new DocumentData();
				
				if(j.getAsJsonObject().get("filename").isJsonNull()){
					currentDoc.setFilename("empty");
				}else{
					currentDoc.setFilename(j.getAsJsonObject().get("filename").getAsString());
				}
				
				if(j.getAsJsonObject().get("thumbnail").isJsonNull()){
					currentDoc.setThumbnail("empty");
				}else{
					currentDoc.setThumbnail(j.getAsJsonObject().get("thumbnail").getAsString());
				}
				
				if(j.getAsJsonObject().get("document_id").isJsonNull()){
					currentDoc.setDocumentID("empty");
				}else{
					currentDoc.setDocumentID(j.getAsJsonObject().get("document_id").getAsString());
				}
				
				if(j.getAsJsonObject().get("document_type_id").isJsonNull()){
					currentDoc.setDocumentTypeID("empty");
				}else{
					currentDoc.setDocumentTypeID(j.getAsJsonObject().get("document_type_id").getAsString());
				}
				documentsToReturn.add(currentDoc);
			}catch(Exception e){
				
			}
		}
		
		
		return documentsToReturn;
	}
	
	public ArrayList<PGNFact> generatePGNFactData(JsonArray facts){
		ArrayList<PGNFact> factsToReturn = new ArrayList<PGNFact>();
		
		for(JsonElement j : facts){
			try{
				PGNFact currentFact = new PGNFact();
				
				currentFact.setSort(j.getAsJsonObject().get("sort").getAsString());
				currentFact.setText(j.getAsJsonObject().get("text").getAsString());
				if(j.getAsJsonObject().get("image").isJsonNull()){
					currentFact.setImage("null");
				}else{
					currentFact.setImage(j.getAsJsonObject().get("image").getAsString());
				}
				currentFact.setTitle(j.getAsJsonObject().get("title").getAsString());
				currentFact.setFactID(j.getAsJsonObject().get("fact_id").getAsString());
				currentFact.setTimefile(j.getAsJsonObject().get("time_file").getAsString());
				currentFact.setAudioFile(j.getAsJsonObject().get("audio_file").getAsString());
				
				ArrayList<String> textTimecoded = new ArrayList<String>();
				String xml = j.getAsJsonObject().get("text_timecoded").toString().replace("\"", "").replace("\\", "");
				
				String[] xmlPieces = xml.split("</span>");
				int endNumber = xmlPieces.length-1;
				
				for(int i = 0; i <= endNumber; i++){
					if(!(xmlPieces[i].equals("n</li></ul>") || xmlPieces[i].equals("</li></ol>"))){
						String thisXML = xmlPieces[i].replace("<ol><li>", "");
						thisXML = thisXML.replace("</li><li>", "");

						try{
							textTimecoded.add(thisXML.substring(thisXML.indexOf(">")+1, thisXML.length()));
						}catch(Exception e){
								System.out.println(thisXML);
						}
					}
				}
				
				ArrayList<String> titleTimecoded = new ArrayList<String>();
				xml = j.getAsJsonObject().get("title_timecoded").toString().replace("\"", "").replace("\\", "");
				
				xmlPieces = xml.split("</span>");
				endNumber = xmlPieces.length-1;
				
				for(int i = 0; i <= endNumber; i++){
					if(!xmlPieces[i].equals("n</li></ul>")){
						String thisXML = xmlPieces[i];

						try{
							titleTimecoded.add(thisXML.substring(thisXML.indexOf(">")+1, thisXML.length()));
						}catch(Exception e){
								System.out.println(thisXML);
						}
					}
				}
				
				currentFact.setTextTimecoded(textTimecoded);
				currentFact.setTitleTimecoded(titleTimecoded);
				
				factsToReturn.add(currentFact);
			}catch(Exception e){
				
			}
		}
		
		return factsToReturn;
	}
	
	public PGNRecipeData generatePGNRecipeData(JsonObject recipe){
		PGNRecipeData newRecipe = new PGNRecipeData();
		
		newRecipe.setText(recipe.get("text").getAsString());
		newRecipe.setAudio(recipe.get("audio").getAsString());
		newRecipe.setIntro(recipe.get("intro").getAsString());
		newRecipe.setImage(recipe.get("image").getAsString());
		newRecipe.setTitle(recipe.get("title").getAsString());
		newRecipe.setRecipeID(recipe.get("recipe_id").getAsString());
		
		return newRecipe;
	}
	public ArrayList<PGNPersonsData> generatePGNPersonsData(JsonArray persons){
		ArrayList<PGNPersonsData> newPersonsData = new ArrayList<PGNPersonsData>();
		
		for(JsonElement j : persons){
			PGNPersonsData currentPerson = new PGNPersonsData();
		
			if(j.getAsJsonObject().get("text").isJsonNull()){
				currentPerson.setText("empty");
			}else{
				currentPerson.setText(j.getAsJsonObject().get("text").getAsString());
			}
			
			if(j.getAsJsonObject().get("image").isJsonNull()){
				currentPerson.setImage("empty");
			}else{
				currentPerson.setImage(j.getAsJsonObject().get("image").getAsString());
			}
			
			if(j.getAsJsonObject().get("person").isJsonNull()){
				currentPerson.setPerson("empty");
			}else{
				currentPerson.setPerson(j.getAsJsonObject().get("person").getAsString());
			}
			
			if(j.getAsJsonObject().get("person_id").isJsonNull()){
				currentPerson.setPersonID("empty");
			}else{
				currentPerson.setPersonID(j.getAsJsonObject().get("person_id").getAsString());
			}
			
			if(j.getAsJsonObject().get("time_file").isJsonNull()){
				currentPerson.setTimeFile("empty");
			}else{
				currentPerson.setTimeFile(j.getAsJsonObject().get("time_file").getAsString());
			}
			
			if(j.getAsJsonObject().get("audio_file").isJsonNull()){
				currentPerson.setAudioFile("empty");
			}else{
				currentPerson.setAudioFile(j.getAsJsonObject().get("audio_file").getAsString());
			}
			
			if(j.getAsJsonObject().get("text_timecoded").isJsonNull()){
				currentPerson.setTextTimecoded( new ArrayList<String>());
			}else{
				ArrayList<String> text = new ArrayList<String>();
				String xml = j.getAsJsonObject().get("text_timecoded").toString().replace("\"", "").replace("\\", "");
				
				String[] xmlPieces = xml.split("</span>");
				int endNumber = xmlPieces.length-1;
				
				for(int i = 0; i <= endNumber; i++){
					if(!xmlPieces[i].equals("n</li></ul>")){
						String thisXML = xmlPieces[i];

						try{
							text.add(thisXML.substring(thisXML.indexOf(">")+1, thisXML.length()));
						}catch(Exception e){
								System.out.println(thisXML);
						}
					}
				}
				
				
				currentPerson.setTextTimecoded(text);
			}
			
			newPersonsData.add(currentPerson);
		}	
		
		return newPersonsData;
	}
	
	public ArrayList<SymbolData> generateSymbolData(JsonArray facts){
		ArrayList<SymbolData> symbolToReturn = new ArrayList<SymbolData>();
		
		for(JsonElement j : facts){
			try{
				SymbolData currentSymbol = new SymbolData();
				
				if(j.getAsJsonObject().get("text").isJsonNull()){
					currentSymbol.setText("null");
				}else{
					currentSymbol.setText(j.getAsJsonObject().get("text").getAsString());
				}
				
				if(j.getAsJsonObject().get("image").isJsonNull()){
					currentSymbol.setImage("null");
				}else{
					currentSymbol.setImage(j.getAsJsonObject().get("image").getAsString());
				}
				
				if(j.getAsJsonObject().get("title").isJsonNull()){
					currentSymbol.setTitle("null");
				}else{
					currentSymbol.setTitle(j.getAsJsonObject().get("title").getAsString());
				}

				if(j.getAsJsonObject().get("symbol_id").isJsonNull()){
					currentSymbol.setSymbolID("null");
				}else{
					currentSymbol.setSymbolID(j.getAsJsonObject().get("symbol_id").getAsString());
				}

				if(j.getAsJsonObject().get("time_file").isJsonNull()){
					currentSymbol.setTimeFile("null");
				}else{
					currentSymbol.setTimeFile(j.getAsJsonObject().get("time_file").getAsString());
				}
				
				if(j.getAsJsonObject().get("audio_file").isJsonNull()){
					currentSymbol.setAudioFile("null");
				}else{
					currentSymbol.setAudioFile(j.getAsJsonObject().get("audio_file").getAsString());
				}
				
				ArrayList<String> textTimecoded = new ArrayList<String>();
				String xml = j.getAsJsonObject().get("text_timecoded").toString().replace("\"", "").replace("\\", "");
				
				String[] xmlPieces = xml.split("</span>");
				int endNumber = xmlPieces.length-1;
				
				for(int i = 0; i <= endNumber; i++){
					if(!xmlPieces[i].equals("n</li></ul>")){
						String thisXML = xmlPieces[i];

						try{
							textTimecoded.add(thisXML.substring(thisXML.indexOf(">")+1, thisXML.length()));
						}catch(Exception e){
								System.out.println(thisXML);
						}
					}
				}
				
				ArrayList<String> titleTimecoded = new ArrayList<String>();
				xml = j.getAsJsonObject().get("title_timecoded").toString().replace("\"", "").replace("\\", "");
				
				xmlPieces = xml.split("</span>");
				endNumber = xmlPieces.length-1;
				
				for(int i = 0; i <= endNumber; i++){
					if(!xmlPieces[i].equals("n</li></ul>")){
						String thisXML = xmlPieces[i];

						try{
							titleTimecoded.add(thisXML.substring(thisXML.indexOf(">")+1, thisXML.length()));
						}catch(Exception e){
								System.out.println(thisXML);
						}
					}
				}
				
				currentSymbol.setTextTimecoded(textTimecoded);
				currentSymbol.setTitleTimecoded(titleTimecoded);
				
				symbolToReturn.add(currentSymbol);
			}catch(Exception e){
				
			}
		}
		
		return symbolToReturn;
	}
	
	public ArrayList<LandmarkData> generateLandmarkData(JsonArray landmarks){
		ArrayList<LandmarkData> landmarksToReturn = new ArrayList<LandmarkData>();
		
		for(JsonElement j : landmarks){
			try{
				LandmarkData currentLandmark = new LandmarkData();
				
				if(j.getAsJsonObject().get("text").isJsonNull()){
					currentLandmark.setText("null");
				}else{
					currentLandmark.setText(j.getAsJsonObject().get("text").getAsString());
				}
				
				if(j.getAsJsonObject().get("image").isJsonNull()){
					currentLandmark.setImage("null");
				}else{
					currentLandmark.setImage(j.getAsJsonObject().get("image").getAsString());
				}
				
				if(j.getAsJsonObject().get("title").isJsonNull()){
					currentLandmark.setTitle("null");
				}else{
					currentLandmark.setTitle(j.getAsJsonObject().get("title").getAsString());
				}
				
				if(j.getAsJsonObject().get("time_file").isJsonNull()){
					currentLandmark.setTimefile("null");
				}else{
					currentLandmark.setTimefile(j.getAsJsonObject().get("time_file").getAsString());
				}
				
				if(j.getAsJsonObject().get("audio_file").isJsonNull()){
					currentLandmark.setAudioFile("null");
				}else{
					currentLandmark.setAudioFile(j.getAsJsonObject().get("audio_file").getAsString());
				}

				if(j.getAsJsonObject().get("landmark_id").isJsonNull()){
					currentLandmark.setLandmarkID("null");
				}else{
					currentLandmark.setLandmarkID(j.getAsJsonObject().get("landmark_id").getAsString());
				}

				ArrayList<String> textTimecoded = new ArrayList<String>();
				String xml = j.getAsJsonObject().get("text_timecoded").toString().replace("\"", "").replace("\\", "");
				
				String[] xmlPieces = xml.split("</span>");
				int endNumber = xmlPieces.length-1;
				
				for(int i = 0; i <= endNumber; i++){
					if(!xmlPieces[i].equals("n</li></ul>")){
						String thisXML = xmlPieces[i];

						try{
							textTimecoded.add(thisXML.substring(thisXML.indexOf(">")+1, thisXML.length()));
						}catch(Exception e){
								System.out.println(thisXML);
						}
					}
				}
				
				currentLandmark.setTextTimeCoded(textTimecoded);
				
				landmarksToReturn.add(currentLandmark);
			}catch(Exception e){
				
			}
		}
		
		return landmarksToReturn;
	}
	
	public ArrayList<ArticleGlossaryData> generateGlossaryData(JsonArray glossaryTerms){
		ArrayList<ArticleGlossaryData> data = new ArrayList<ArticleGlossaryData>();
		
		for(JsonElement j : glossaryTerms){
			ArticleGlossaryData glossary = new ArticleGlossaryData();
			
			try{
				glossary.setID(j.getAsJsonObject().get("id").getAsString());
			}
			catch(Exception e){
				glossary.setID("Error");
			}
			
			try{
				glossary.setWord(j.getAsJsonObject().get("word").getAsString());
			}
			catch(Exception e){
				glossary.setWord("Error");
			}
			
			try{
				glossary.setBaseWord(j.getAsJsonObject().get("base_word").getAsString());
			}
			catch(Exception e){
				glossary.setBaseWord("Error");
			}
			data.add(glossary);
		}
		return data;
	}
}
