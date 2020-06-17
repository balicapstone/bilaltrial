package SharedClasses;

import java.io.IOException;

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
import UserClasses.User;

public class CurlCallController extends BasePage{

	public JsonArray loggingEntries;
	public String sessionID;
	public static String qaCurlURL = "search-cap-es-domain-zvroe5umzpb2dkvgxlmzjorkly.us-east-2";
	public static String stagingCurlURL = "search-es-data-lake-log-staging-3vdlpaoxrt4xryrba336ucalva.us-east-2";
	public static String productionCurlURL = "search-es-datalake-logs-45fqefehpz5wapdvdwhtjlrmwy.us-east-2";
	public static String urlToUse;
	
	public CurlCallController(){
		loggingEntries = new JsonArray();
	}
	
	public CurlCallController(User u, String session){
		pageDriver = u.getDriver();
		loggingEntries = new JsonArray();
		sessionID = session;
		
		if(u.getEnvironment().equals("qa")){
			urlToUse = qaCurlURL;
		}
		else if(u.getEnvironment().equals("staging")){
			urlToUse = stagingCurlURL;
		}
		else if(u.getEnvironment().equals("www")){
			urlToUse = productionCurlURL;
		}
		else if(u.getEnvironment().equals("editorial")){
			urlToUse = qaCurlURL;
		}
	}
	
	public void setSessionID(String session){
		sessionID = session;
	}
	
	public void getLogs() throws ParseException, IOException{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet("https://"+urlToUse+".es.amazonaws.com/_search?q="+sessionID+"&pretty");		
		//https://staging.pebblego.com/log/number/50/customer/20662/?sqs=$2y$10$O0S60.9z0G8xKmEIrQf46.tE9n6GA13xrx.qG5KFgH.yNvR8AqyHm
		HttpResponse response = client.execute(httpGet);
		
		String test = EntityUtils.toString(response.getEntity());
		JsonObject logging = (JsonObject) new JsonParser().parse(test);
		
		logging = logging.getAsJsonObject("hits");
		
		loggingEntries = logging.getAsJsonArray("hits");
	}
	
	public void getLoggingEventsForType(LogTypes log) throws ParseException, IOException{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet("https://"+urlToUse+".es.amazonaws.com/"+log.getName()+"/_search?q="+sessionID+"&pretty");		

		HttpResponse response = client.execute(httpGet);
		
		String test = EntityUtils.toString(response.getEntity());
		JsonObject logging = (JsonObject) new JsonParser().parse(test);
		
		logging = logging.getAsJsonObject("hits");
		
		loggingEntries = logging.getAsJsonArray("hits");
	}
	
	public void getLogsForSearch(LogTypes log, String search) throws IOException{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet("https://"+urlToUse+".es.amazonaws.com/"+log.getName()+"/_search?q="+search+"&pretty");		

		HttpResponse response = client.execute(httpGet);
		
		String test = "";
		try {
			test = EntityUtils.toString(response.getEntity());
		} catch (ParseException e) {
			System.out.println("I could not parse the httpGet response");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("I found this exception when trying to get log data:");
			e.printStackTrace();
		}
		JsonObject logging = (JsonObject) new JsonParser().parse(test);
		
		logging = logging.getAsJsonObject("hits");
		
		loggingEntries = logging.getAsJsonArray("hits");
	}
	
	public Boolean doesUsernameLoginLogExist(String user){
		String search = sessionID + "%20AND%20" + LogTypes.LOGINUSERNAME.searchTerms;
		
		try {
			getLogsForSearch(LogTypes.LOGINUSERNAME, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.LOGINUSERNAME.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String found = j.getAsJsonObject().get("_source").getAsJsonObject().get("username").toString().replace("\"", "");
			if(found.equals(user)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesModuleViewLogExist(String module){
		String search = sessionID + "%20AND%20" + LogTypes.MODULEVIEW.searchTerms;
		
		try {
			getLogsForSearch(LogTypes.MODULEVIEW, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.MODULEVIEW.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundModule;
			try{
				foundModule = j.getAsJsonObject().get("_source").getAsJsonObject().get("module_name").toString().replace("\"", "");
			} catch (Exception e){
				foundModule = "";
			}
			if(foundModule.equals(module)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesModuleClickLogExist(String module){
		String search = sessionID + "%20AND%20" + LogTypes.MODULECLICK.searchTerms;
		
		try {
			getLogsForSearch(LogTypes.MODULECLICK, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.MODULECLICK.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundModule;
			try{
				foundModule = j.getAsJsonObject().get("_source").getAsJsonObject().get("module_name").toString().replace("\"", "");
			} catch (Exception e){
				foundModule = "";
			}
			if(foundModule.equals(module)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesCategoriesClickLogExist(String category, String module){
		String search = sessionID + "%20AND%20" + LogTypes.CATEGORYCLICK.searchTerms;
		
		try {
			getLogsForSearch(LogTypes.CATEGORYCLICK, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.CATEGORYCLICK.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundCategory = j.getAsJsonObject().get("_source").getAsJsonObject().get("category").toString().replace("\"", "");
			String foundModule = j.getAsJsonObject().get("_source").getAsJsonObject().get("module_name").toString().replace("\"", "");
			if(foundCategory.equals(category) && foundModule.equals(module)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesCategoriesViewLogExist(String category, String module){
		String search = sessionID + "%20AND%20" + LogTypes.CATEGORYVIEW.searchTerms;
		
		try {
			getLogsForSearch(LogTypes.CATEGORYVIEW, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.CATEGORYVIEW.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundCategory = j.getAsJsonObject().get("_source").getAsJsonObject().get("category").toString().replace("\"", "");
			String foundModule = j.getAsJsonObject().get("_source").getAsJsonObject().get("module_name").toString().replace("\"", "");
			if(foundCategory.equals(category) && foundModule.equals(module)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesArticleClickLogExist(String article, String module){
		String search = sessionID + "%20AND%20" + LogTypes.ARTICLECLICK.searchTerms;
		
		try {
			getLogsForSearch(LogTypes.ARTICLECLICK, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.ARTICLECLICK.getName() + " " + search);
			e.printStackTrace();
		}
		
		String foundArticle = "";
		String foundModule = "";
		
		for(JsonElement j: loggingEntries){
			try{
				foundArticle = j.getAsJsonObject().get("_source").getAsJsonObject().get("article").toString().replace("\"", "");
				foundModule = j.getAsJsonObject().get("_source").getAsJsonObject().get("module_name").toString().replace("\"", "");
			}catch(Exception e){
				foundArticle = "";
				foundModule = "";
			}
			if(foundArticle.equals(article) && foundModule.equals(module)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesArticleViewLogExist(String article, String module){
		String search = sessionID + "%20AND%20" + LogTypes.ARTICLEVIEW.searchTerms;
		
		try {
			getLogsForSearch(LogTypes.ARTICLEVIEW, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.ARTICLEVIEW.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundArticle = j.getAsJsonObject().get("_source").getAsJsonObject().get("article_name").toString().replace("\"", "");
			String foundModule = j.getAsJsonObject().get("_source").getAsJsonObject().get("module_name").toString().replace("\"", "");
			if(foundArticle.equals(article) && foundModule.equals(module)){
				return true;
			}
		}
		return false;
	}
	
	
	public Boolean doesBreadcrumbsLogExist(String type){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.BREADCRUMBS, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.BREADCRUMBS.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String found = j.getAsJsonObject().get("_source").getAsJsonObject().get("breadcrumb_type").toString().replace("\"", "");
			if(found.equals(type)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesHamburgerButtonClickLogExist(){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.HAMBURGER, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.HAMBURGER.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String found = j.getAsJsonObject().get("_source").getAsJsonObject().get("log_type").toString().replace("\"", "");
			String action = j.getAsJsonObject().get("_source").getAsJsonObject().get("action_name").toString().replace("\"", "");
			if(found.equals("hamburger") && action.equals("click")){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesModuleClickedFromHamburgerButtonLogExist(String module){
		String search = sessionID + "%20AND%20" + LogTypes.MODULECLICKFROMHAMBURGER.getSearch();
		
		try {
			getLogsForSearch(LogTypes.MODULECLICKFROMHAMBURGER, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.MODULECLICKFROMHAMBURGER.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String found = "";
			try{
				found = j.getAsJsonObject().get("_source").getAsJsonObject().get("module_name").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(found.equals(module)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesArticleClickFromSearchLogExist(String articleID){
		String search = sessionID + "%20AND%20" + LogTypes.ARTICLECLICK.getSearch();
		
		try {
			getLogsForSearch(LogTypes.ARTICLECLICK, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.ARTICLECLICK.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundFrom = "";
			String foundArticleID = "";
			try{
				foundFrom = j.getAsJsonObject().get("_source").getAsJsonObject().get("from").toString().replace("\"", "");
				foundArticleID = j.getAsJsonObject().get("_source").getAsJsonObject().get("article_id").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundFrom.equals("search") && foundArticleID.equals(articleID)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesArticleClickFromPGNHamburgerLogExist(String articleName){
		String search = sessionID + "%20AND%20" + LogTypes.ARTICLECLICK.getSearch();
		
		try {
			getLogsForSearch(LogTypes.ARTICLECLICK, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.ARTICLECLICK.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundFrom = "";
			String foundArticleID = "";
			try{
				foundFrom = j.getAsJsonObject().get("_source").getAsJsonObject().get("from").toString().replace("\"", "");
				foundArticleID = j.getAsJsonObject().get("_source").getAsJsonObject().get("article_name").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundFrom.equals("hamburger") && foundArticleID.equals(articleName)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesGlossaryClickLogExist(String articleName, String glossaryTerm){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.GLOSSARY, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.GLOSSARY.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundGlossary = "";
			String foundArticle = "";
			try{
				foundGlossary = j.getAsJsonObject().get("_source").getAsJsonObject().get("glossary_term").toString().replace("\"", "");
				foundArticle = j.getAsJsonObject().get("_source").getAsJsonObject().get("article_name").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundGlossary.equals(glossaryTerm) && foundArticle.equals(articleName)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesPrintClickLogExist(String printType){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.PRINT, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.PRINT.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundPrint = "";
			try{
				foundPrint = j.getAsJsonObject().get("_source").getAsJsonObject().get("print_type").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundPrint.equals(printType)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesCitationClickLogExist(String articleName){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.CITATION, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.CITATION.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundArticle = "";
			try{
				foundArticle = j.getAsJsonObject().get("_source").getAsJsonObject().get("article_name").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundArticle.equals(articleName)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesActivityClickLogExist(String articleName){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.ACTIVITY, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.ACTIVITY.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundArticle = "";
			try{
				foundArticle = j.getAsJsonObject().get("_source").getAsJsonObject().get("article_name").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundArticle.equals(articleName)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesVideoClickLogExist(String articleName){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.VIDEOEXPERIMENTMODAL, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.VIDEOEXPERIMENTMODAL.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundArticle = "";
			try{
				foundArticle = j.getAsJsonObject().get("_source").getAsJsonObject().get("article_name").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundArticle.equals(articleName)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesPGNVideoClickLogExist(String videoName){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.VIDEOEXPERIMENTMODAL, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.VIDEOEXPERIMENTMODAL.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundArticle = "";
			try{
				foundArticle = j.getAsJsonObject().get("_source").getAsJsonObject().get("video_name").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundArticle.equals(videoName)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesPGNVideoPageLogExist(String moduleName){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.VIDEOEXPERIMENTSPAGE, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.VIDEOEXPERIMENTSPAGE.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundArticle = "";
			try{
				foundArticle = j.getAsJsonObject().get("_source").getAsJsonObject().get("module_name").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundArticle.equals(moduleName)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesNavigationTabLogExist(String tabName){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.ARTICLENAV, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.ARTICLENAV.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundScreen = "";
			try{
				foundScreen = j.getAsJsonObject().get("_source").getAsJsonObject().get("screen_name").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundScreen.equals(tabName)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesRelatedArticleClickLogExist(String articleName){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.RELATEDARTICLE, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.RELATEDARTICLE.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundArticle = "";
			try{
				foundArticle = j.getAsJsonObject().get("_source").getAsJsonObject().get("article_name").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundArticle.equals(articleName)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesArticleClickedFromRelatedArticleLogExist(String articleName, String priorArticle){
		String search = sessionID + "%20AND%20" + LogTypes.ARTICLECLICK.searchTerms;
		
		try {
			getLogsForSearch(LogTypes.ARTICLECLICK, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.ARTICLECLICK.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundArticle = "";
			String foundFrom = "";
			String foundPrior = "";
			try{
				foundArticle = j.getAsJsonObject().get("_source").getAsJsonObject().get("article_name").toString().replace("\"", "");
				foundFrom = j.getAsJsonObject().get("_source").getAsJsonObject().get("from").toString().replace("\"", "");
				foundPrior = j.getAsJsonObject().get("_source").getAsJsonObject().get("from_name").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundArticle.equals(articleName) && foundPrior.equals(priorArticle) && foundFrom.equals("article")){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesLogoClickLogExist(String product){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.LOGO, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.LOGO.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundProduct = "";
			try{
				foundProduct = j.getAsJsonObject().get("_source").getAsJsonObject().get("product_name").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundProduct.equals(product)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesTeacherResourcesClickLogExist(String product){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.TEACHERRESOURCES, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.TEACHERRESOURCES.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundProduct = "";
			String foundLink = "";
			try{
				foundProduct = j.getAsJsonObject().get("_source").getAsJsonObject().get("product_name").toString().replace("\"", "");
				foundLink = j.getAsJsonObject().get("_source").getAsJsonObject().get("link").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundProduct.equals(product) && foundLink.equals("http://community.mycapstone.com/")){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesPollClickLogExist(String module){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.POLL, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.POLL.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundModule = "";
			try{
				foundModule = j.getAsJsonObject().get("_source").getAsJsonObject().get("module_name").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundModule.equals(module)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesGameClickLogExist(String game, String module){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.GAMES, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.GAMES.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundModule = "";
			String foundGame = "";
			try{
				foundModule = j.getAsJsonObject().get("_source").getAsJsonObject().get("module_name").toString().replace("\"", "");
				foundGame = j.getAsJsonObject().get("_source").getAsJsonObject().get("game_name").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundModule.equals(module) && foundGame.equals(game)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesTerminologyClickLogExist(String articleName){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.TERMINOLOGY, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.TERMINOLOGY.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundModule = "";
			try{
				foundModule = j.getAsJsonObject().get("_source").getAsJsonObject().get("article_name").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundModule.equals(articleName)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesDictionaryClickLogExist(String articleName){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.DICTIONARY, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.DICTIONARY.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundArticle = "";
			try{
				foundArticle = j.getAsJsonObject().get("_source").getAsJsonObject().get("article_name").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundArticle.equals(articleName)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesImageModalButtonClickLogExist(String articleName, String imageID){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.IMAGEMODALBUTTON, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.IMAGEMODALBUTTON.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundArticle = "";
			String foundImage = "";
			try{
				foundArticle = j.getAsJsonObject().get("_source").getAsJsonObject().get("article_name").toString().replace("\"", "");
				foundImage = j.getAsJsonObject().get("_source").getAsJsonObject().get("image_id").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundArticle.equals(articleName) && foundImage.equals(imageID)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean doesImageClickLogExist(String articleName, String imageID){
		String search = sessionID;
		
		try {
			getLogsForSearch(LogTypes.IMAGEMODALIMAGE, search);
		} catch (IOException e) {
			System.out.println("I could not find logs for this search: " + LogTypes.IMAGEMODALIMAGE.getName() + " " + search);
			e.printStackTrace();
		}
		
		for(JsonElement j: loggingEntries){
			String foundArticle = "";
			String foundImage = "";
			try{
				foundArticle = j.getAsJsonObject().get("_source").getAsJsonObject().get("article_name").toString().replace("\"", "");
				foundImage = j.getAsJsonObject().get("_source").getAsJsonObject().get("image_id").toString().replace("\"", "");
			} catch(Exception e){
			}
			if(foundArticle.equals(articleName) && foundImage.equals(imageID)){
				return true;
			}
		}
		return false;
	}
}
