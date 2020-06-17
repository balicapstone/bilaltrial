package APIControllers;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DataClasses.ContentNode;
import DataClasses.ModuleData;
import DataClasses.VideoExperimentData;
import SharedClasses.BasePage;

public class ContentAPIController extends BasePage{
	public JsonObject callData;
	public String appID;
	public String appURL;
	public JsonArray modules;
	public ArrayList<ModuleData> moduleData;
	public String appName;
	String token;
	String masterSQS = "$2y$10$jzNHLCgrGqxscuDKMJqO3OM82SJCtiRnpHjdIdxzuaHrv93VnYjYy";
	AuthenticationAPIController authAPI;
	
	public ContentAPIController(){
		moduleData = new ArrayList<ModuleData>();
		authAPI = new AuthenticationAPIController("authqa");
	}
	
	public String getContentJSON(String app) throws ParseException, IOException{
		if(app.equals("2")){
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
		} else if(app.equals("3")){
			try {
				token =	authAPI.getTokenForUsername("garage", "capdig", "3", "username");
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
		
		moduleData.clear();
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet("https://3ng5u5zewd.execute-api.us-east-2.amazonaws.com/qa/api/v1/contenttree/"+app);
		httpGet.setHeader("Authorization", "Bearer " + token);
	
		
		HttpResponse response = client.execute(httpGet);
		
		String entity = EntityUtils.toString(response.getEntity());
		
		if(entity.contains("\"errorMessage\":")){
			return entity;
		} else if(entity.contains("\"message\": \"Internal server error\"")){
			return entity;
		} else if(entity.contains("\"body\":[]")){
			callData = (JsonObject) new JsonParser().parse(entity);
			getData();
			return entity;
		}
		else{
			callData = (JsonObject) new JsonParser().parse(entity);
			getData();
			return entity;
		}
	}
	
	
	public void getData(){
		appID = callData.get("app_id").getAsString();
		appURL = callData.get("app_url").getAsString();
		appName = callData.get("app_name").getAsString();
		
		modules = callData.get("modules").getAsJsonArray();
		
		for(JsonElement m : modules){
			ModuleData thisModule = new ModuleData();
			
			if(m.getAsJsonObject().get("name").isJsonNull()){
				thisModule.setName("empty");
			}
			else{
				thisModule.setName(m.getAsJsonObject().get("name").getAsString());
			}

			if(m.getAsJsonObject().get("image").isJsonNull()){
				thisModule.setImage("empty");
			}
			else{
				thisModule.setImage(m.getAsJsonObject().get("image").getAsString());
			}
			
			if(m.getAsJsonObject().get("lang_txt").isJsonNull()){
				thisModule.setLanguageText("empty");
			}
			else{
				thisModule.setLanguageText(m.getAsJsonObject().get("lang_txt").getAsString());
			}
			
			if(m.getAsJsonObject().get("module_id").isJsonNull()){
				thisModule.setModuleID("empty");
			}
			else{
				thisModule.setModuleID(m.getAsJsonObject().get("module_id").getAsString());
			}
			
			if(m.getAsJsonObject().get("name_short").isJsonNull()){
				thisModule.setNameShort("empty");
			}
			else{
				thisModule.setNameShort(m.getAsJsonObject().get("name_short").getAsString());
			}
			
			if(m.getAsJsonObject().get("language_id").isJsonNull()){
				thisModule.setLanguageID("empty");
			}
			else{
				thisModule.setLanguageID(m.getAsJsonObject().get("language_id").getAsString());
			}
			
			if(m.getAsJsonObject().get("english_module_id").isJsonNull()){
				thisModule.setEnglishModuleID("empty");
			}
			else{
				thisModule.setEnglishModuleID(m.getAsJsonObject().get("english_module_id").getAsString());
			}
			
			if(m.getAsJsonObject().get("video_experiments").isJsonNull()){
				thisModule.setVideos(new ArrayList<VideoExperimentData>());
			}
			else{
				thisModule.setVideos(generateVideoExperimentData(m.getAsJsonObject().get("video_experiments").getAsJsonArray()));
			}
			
			JsonArray moduleChildren;
			ArrayList<ContentNode> moduleChildrenToReturn = new ArrayList<ContentNode>();
			
			try{
				moduleChildren = m.getAsJsonObject().get("children").getAsJsonArray();
			}catch(Exception e){
				moduleChildren = new JsonArray();
			}
			
			for(JsonElement t : moduleChildren){
				ContentNode thisChild = new ContentNode();
				
				if(t.getAsJsonObject().get("type").isJsonNull()){
					thisChild.setType("empty");
				}else{
					thisChild.setType(t.getAsJsonObject().get("type").getAsString());
				}
				
				if(t.getAsJsonObject().get("audio").isJsonNull()){
					thisChild.setAudio("empty");
				}else{
					thisChild.setAudio(t.getAsJsonObject().get("audio").getAsString());
				}
				
				if(t.getAsJsonObject().get("image").isJsonNull()){
					thisChild.setImage("empty");
				}else{
					thisChild.setImage(t.getAsJsonObject().get("image").getAsString());
				}
				
				if(t.getAsJsonObject().get("type").isJsonNull()){
					thisChild.setTitle("title");
				}else{
					thisChild.setTitle(t.getAsJsonObject().get("title").getAsString());
				}
				
				if(t.getAsJsonObject().get("article_id").isJsonNull()){
					thisChild.setArticleID("empty");
				}else{
					thisChild.setArticleID(t.getAsJsonObject().get("article_id").getAsString());
				}
				
				if(thisChild.type.equals("C")){
					thisChild.setChildren(getChildrenNodes(t.getAsJsonObject()));
				}
				
				moduleChildrenToReturn.add(thisChild);
			}
			
			thisModule.setChildren(moduleChildrenToReturn);
			moduleData.add(thisModule);
		}
	}
	
	
	public ArrayList<ContentNode> getChildrenNodes(JsonObject parent){
		ArrayList<ContentNode> nodes = new ArrayList<ContentNode>();
		JsonArray children = parent.getAsJsonObject().get("children").getAsJsonArray();
		
		for(JsonElement c : children){
			ContentNode newChild = new ContentNode();
			
			if(c.getAsJsonObject().get("type").isJsonNull()){
				newChild.setType("empty");
			}else{
				newChild.setType(c.getAsJsonObject().get("type").getAsString());
			}
			
			if(c.getAsJsonObject().get("audio").isJsonNull()){
				newChild.setAudio("empty");
			}else{
				newChild.setAudio(c.getAsJsonObject().get("audio").getAsString());
			}
			
			if(c.getAsJsonObject().get("image").isJsonNull()){
				newChild.setImage("empty");
			}else{
				newChild.setImage(c.getAsJsonObject().get("image").getAsString());
			}
			
			if(c.getAsJsonObject().get("type").isJsonNull()){
				newChild.setTitle("title");
			}else{
				newChild.setTitle(c.getAsJsonObject().get("title").getAsString());
			}
			
			if(c.getAsJsonObject().get("article_id").isJsonNull()){
				newChild.setArticleID("empty");
			}else{
				newChild.setArticleID(c.getAsJsonObject().get("article_id").getAsString());
			}
			
			if(c.getAsJsonObject().get("type").getAsString().equals("C")){
				newChild.setChildren(getChildrenNodes(c.getAsJsonObject()));
			}
			
			nodes.add(newChild);
		}
		
		return nodes;
	}
	
	public ArrayList<VideoExperimentData> generateVideoExperimentData(JsonArray experiments){
		ArrayList<VideoExperimentData> nodes = new ArrayList<VideoExperimentData>();
		
		for(JsonElement e : experiments){
			VideoExperimentData newVideo = new VideoExperimentData();
			
			if(e.getAsJsonObject().get("image").isJsonNull()){
				newVideo.setImage("empty");
			}else{
				newVideo.setImage(e.getAsJsonObject().get("image").getAsString());
			}
			
			if(e.getAsJsonObject().get("title").isJsonNull()){
				newVideo.setTitle("empty");
			}else{
				newVideo.setTitle(e.getAsJsonObject().get("title").getAsString());
			}
			
			if(e.getAsJsonObject().get("video").isJsonNull()){
				newVideo.setVideo("empty");
			}else{
				newVideo.setVideo(e.getAsJsonObject().get("video").getAsString());
			}
			
			if(e.getAsJsonObject().get("created").isJsonNull()){
				newVideo.setCreated("empty");
			}else{
				newVideo.setCreated(e.getAsJsonObject().get("created").getAsString());
			}
			
			if(e.getAsJsonObject().get("transcript").isJsonNull()){
				newVideo.setTranscript("empty");
			}else{
				newVideo.setTranscript(e.getAsJsonObject().get("transcript").getAsString());
			}
			
			if(e.getAsJsonObject().get("video_experiment_id").isJsonNull()){
				newVideo.setExperimentID("empty");
			}else{
				newVideo.setExperimentID(e.getAsJsonObject().get("video_experiment_id").getAsString());
			}
			
			nodes.add(newVideo);
		}
		
		return nodes;
	}
	
	public ModuleData returnModuleDataByName(String name){
		ModuleData toReturn = new ModuleData();
		for(ModuleData module : moduleData){
			if(module.name.equals(name)){
				toReturn = module;
				break;
			}
		}
			
		return toReturn;
	}
}
