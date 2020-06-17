package SharedClasses;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.apache.http.ParseException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import UserClasses.UserInfo;

public class ContentTreeAPI{
	public JsonArray jsonModuleBlob;
	public String contentModule;
	public String contentEnvironment;
	
	public ContentTreeAPI(String module, String environment){
		contentModule = module;
		contentEnvironment = environment;
		
		try {
			jsonModuleBlob = getJsonArrayForModule(contentModule);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public JsonArray getJsonArrayForModule(String module) throws IOException{
		URL url = new URL("https://"+contentEnvironment+".pebblego.com/module/"+module+"/customer/20662?sqs="+UserInfo.MASTERACCOUNT.getSQS());
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		
		con = (HttpURLConnection) url.openConnection();
		
	    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    String inputLine;
	    StringBuffer response = new StringBuffer();
	    while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	    }
	    
	     //Read JSON response and print
	    in.close();
	    con.disconnect();
	     
	    JsonArray jsonToReturn = (JsonArray) new JsonParser().parse(response.toString());
	   // JsonObject myResponse = jsonElement.get(5).getAsJsonObject();
		
		//HttpResponse response = con.getResponseMessage();//client.execute(httpPost);
		
		//String test = EntityUtils.toString(response.getEntity());
		//test = test.substring(1, test.length()-1);
		//JsonObject toReturn = (JsonObject) new JsonParser().parse(test);
		
		return jsonToReturn;
	}
	
	public void changeModule(String module){
		
		try {
			jsonModuleBlob = getJsonArrayForModule(module);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public JsonArray getCategoryTreeByName(String category) throws ParseException, IOException{	
		for(int i = 0; i < jsonModuleBlob.size(); i++){
			JsonObject test = jsonModuleBlob.get(i).getAsJsonObject();
			if(test.get("type").toString().contains("C")){
				if(test.get("base_name").toString().equals("\""+category+"\"")){
					return test.getAsJsonArray("categories");
				}
				else{
					JsonArray checkTheseCategories = test.getAsJsonArray("categories");
					
					while(checkTheseCategories.size() > 0){
						JsonArray toCheckLater = new JsonArray();
						for(int j = 0; j < checkTheseCategories.size(); j++){
							JsonObject deeperCategory = checkTheseCategories.get(j).getAsJsonObject();
							if(deeperCategory.get("type").toString().contains("C")){
								if(deeperCategory.get("base_name").toString().equals("\""+category+"\"")){
									return deeperCategory.getAsJsonArray("categories");
								}
								else{
									JsonArray categories = deeperCategory.getAsJsonArray("Categories");
									for(int k = 0; k < categories.size(); k++){
										toCheckLater.add(categories.get(k));
									}
								}
							}
						}
						checkTheseCategories = toCheckLater;
					}
				}
			}
		}
		return new JsonArray();
	}
	
	public JsonArray getCategoryTreeByID(String id) throws ParseException, IOException{	
		for(int i = 0; i < jsonModuleBlob.size(); i++){
			JsonObject current = jsonModuleBlob.get(i).getAsJsonObject();
			if(current.get("type").toString().contains("C")){
				if(current.get("article_id").toString().equals(id)){
					return current.getAsJsonArray("categories");
				}
				else{
					JsonArray checkTheseCategories = current.getAsJsonArray("categories");
					
					while(checkTheseCategories.size() > 0){
						JsonArray toCheckLater = new JsonArray();
						for(int j = 0; j < checkTheseCategories.size(); j++){
							JsonObject deeperCategory = checkTheseCategories.get(j).getAsJsonObject();
							if(deeperCategory.get("type").toString().contains("C")){
								if(deeperCategory.get("article_id").toString().equals(id)){
									return deeperCategory.getAsJsonArray("categories");
								}
								else{
									JsonArray categories = deeperCategory.getAsJsonArray("categories");
									for(int k = 0; k < categories.size(); k++){
										toCheckLater.add(categories.get(k));
									}
								}
							}
						}
						checkTheseCategories = toCheckLater;
					}
				}
			}
		}
		return new JsonArray();
	}
	
	public JsonArray getTopLevelJsonElements(){
		return jsonModuleBlob;
	}
	
	public ArrayList<String> getListOfArticleNamesForCategory(String ID) throws ParseException, IOException{
		ArrayList<String> articles = new ArrayList<String>();
		
		JsonArray category = getCategoryTreeByID(ID);
		
		for(int i = 0; i < category.size(); i++){
			articles.add(category.get(i).getAsJsonObject().get("title").toString().replace("\"", ""));
		}
		
		return articles;
	}
	
	public ArrayList<String> getListOfArticleIDsForCategory(String j) throws ParseException, IOException{
		ArrayList<String> articles = new ArrayList<String>();
		
		JsonArray category = getCategoryTreeByID(j);
		
		for(int i = 0; i < category.size(); i++){
			articles.add(category.get(i).getAsJsonObject().get("article_id").toString().replace("\"", ""));
		}
		
		return articles;
	}
	
	public ArrayList<String> getTopLevelModuleArticleNames() throws ParseException, IOException{
		ArrayList<String> articles = new ArrayList<String>();
		
		for(int i = 0; i < jsonModuleBlob.size(); i++){
			articles.add(jsonModuleBlob.get(i).getAsJsonObject().get("title").toString().replace("\"", ""));
		}
		
		return articles;
	}
	
	public ArrayList<String> getTopLevelModuleArticleIDs() throws ParseException, IOException{
		ArrayList<String> articles = new ArrayList<String>();
		
		for(int i = 0; i < jsonModuleBlob.size(); i++){
			articles.add(jsonModuleBlob.get(i).getAsJsonObject().get("title").toString().replace("\"", ""));
		}
		
		return articles;
	}

}
