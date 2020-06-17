package Tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import PGOStudentResources.ContentSelectionPage;
import SharedClasses.ContentNode;
import SharedClasses.ContentTreeAPI;
import TrueUserTests.TrueUser.ModuleComparisonRunner;
import UserClasses.User;
import UserClasses.UserInfo;

public class PGOModuleVerificationTests{
	public static User correctUser;
	public static User testingUser;
	public static ContentTreeAPI correctContent;
	public static ContentTreeAPI testingContent;
	
	public static JsonArray correctTopLevel;
	public static JsonArray testingTopLevel;
	
	public static ArrayList<ContentNode> foundArticlesCorrect;
	public static ArrayList<ContentNode> foundArticlesTesting;
	
	public static ArrayList<ContentNode> missingArticlesCorrect;
	public static ArrayList<ContentNode> duplicatedArticlesCorrect;
	
	public static ArrayList<ContentNode> missingArticlesTesting;
	public static ArrayList<ContentNode> duplicatedArticlesTesting;		
	
	public static ArrayList<ContentNode> differences;
	
	public StringBuilder currentError = new StringBuilder();
	public StringBuilder currentSuccess = new StringBuilder();
	
	public static String module = ModuleComparisonRunner.module;
	public static String correctEnvironment = ModuleComparisonRunner.correctEnvironment;
	public static String testingEnvironment = ModuleComparisonRunner.testingEnvironment;
	public static String product = ModuleComparisonRunner.product;
	
	@BeforeClass
    public static void executeBefore(){
		foundArticlesCorrect = new ArrayList<ContentNode>();
		foundArticlesTesting = new ArrayList<ContentNode>();;
		
		missingArticlesCorrect = new ArrayList<ContentNode>();
		duplicatedArticlesCorrect = new ArrayList<ContentNode>();
		
		missingArticlesTesting = new ArrayList<ContentNode>();
		duplicatedArticlesTesting = new ArrayList<ContentNode>();	
		
		differences = new ArrayList<ContentNode>();
		
		if(module == null){
			module = "5";
		}
		
		if(correctEnvironment == null){
			correctEnvironment = "staging";
		}
		
		if(testingEnvironment == null){
			testingEnvironment = "www";
		}
		
		if(product == null){
			product = "pgo";
		}
    }
    
    @AfterClass
    public static void executeAfter(){
    	//loginPage.closeCurrentWindow();
    }
	    
    @Rule
    public TestWatcher restart = new TestWatcher(){
    	@Override
    	public void failed(Throwable e, Description description){
    		
    	}
    };
	
	//@Rule
	//public Retry retry = new Retry(3);
	
	
	
	@Test
	public void testPGOModule(){
		User userCorrect = new User(UserInfo.GARAGESTUDENT, correctEnvironment);
		User userTesting = new User(UserInfo.GARAGESTUDENT, testingEnvironment);
		
		//String module = "3";
		
		ContentTreeAPI content1 = new ContentTreeAPI(module, correctEnvironment);
		//ContentTreeAPI content2 = new ContentTreeAPI(module);
		
		JsonArray topLevelCorrect = content1.getTopLevelJsonElements();
		//JsonArray topLevel2 = content1.getTopLevelJsonElements();
		
		PGOPages.LoginPage loginCorrect = new PGOPages.LoginPage(userCorrect);
		PGOPages.LoginPage loginTesting = new PGOPages.LoginPage(userTesting);
		PGOStudentResources.StudentHomePage homeCorrect = (PGOStudentResources.StudentHomePage) loginCorrect.Login(userCorrect);
		PGOStudentResources.StudentHomePage homeTesting = (PGOStudentResources.StudentHomePage) loginTesting.Login(userTesting);
		
		homeCorrect.getDriver().get("https://"+userCorrect.getEnvironment()+".pebblego.com/modules/"+module+"/categories/0");
		homeTesting.getDriver().get("https://"+userTesting.getEnvironment()+".pebblego.com/modules/" + module + "/categories/0");
		
		ContentSelectionPage studentContentCorrect = new ContentSelectionPage(homeCorrect.getDriver());
		ContentSelectionPage studentContentTesting = new ContentSelectionPage(homeTesting.getDriver());
			
		ArrayList<ContentNode> content = new ArrayList<ContentNode>();
		
		for(int i = 0; i < topLevelCorrect.size(); i++){	
			homeCorrect.getDriver().get("https://"+userCorrect.getEnvironment()+".pebblego.com/modules/"+module+"/categories/0");
			homeTesting.getDriver().get("https://"+userTesting.getEnvironment()+".pebblego.com/modules/" + module + "/categories/0");
			
			String path = module;
			JsonObject topLevelContent = topLevelCorrect.get(i).getAsJsonObject();
			String title = topLevelContent.getAsJsonObject().get("title").toString().replace("\\", "");
			title = title.substring(1, title.length()-1);
			String id = topLevelContent.getAsJsonObject().get("article_id").toString().replace("\"", "");
			String baseName = topLevelContent.getAsJsonObject().get("base_name").toString().replace("\"", "");
			String mainImage = topLevelContent.getAsJsonObject().get("main_image").toString().replace("\"", "");
			String menuAudio = topLevelContent.getAsJsonObject().get("menu_audio").toString().replace("\"", "");
			String type = topLevelContent.get("type").toString().replace("\"", "");
			
			
			ContentNode correctNode = new ContentNode();
			
			correctNode.setID(id);
			correctNode.setBaseName(baseName);
			correctNode.setMainImage(mainImage);
			correctNode.setType(type);
			correctNode.setTitle(title);
			correctNode.setMenuAudio(menuAudio);
			correctNode.setPath(path + "->" + title);
			content.add(correctNode);
			
			ContentNode testingNode = correctNode.copyNode();
			
			correctNode.setBaseNameCorrect(studentContentCorrect.isBaseNameConnectedToTitle(baseName, title));
			correctNode.checkTitle(studentContentCorrect.getTitleForBaseName(baseName));
			correctNode.checkID(studentContentCorrect.getIDForBaseName(baseName));
			correctNode.checkType(studentContentCorrect.getType(baseName));
			correctNode.checkMenuAudio(studentContentCorrect.getMenuAudio(baseName));
			correctNode.checkMainImage(studentContentCorrect.getMainImage(baseName));
			
			testingNode.setBaseNameCorrect(studentContentTesting.isBaseNameConnectedToTitle(baseName, title));
			testingNode.checkTitle(studentContentTesting.getTitleForBaseName(baseName));
			testingNode.checkID(studentContentTesting.getIDForBaseName(baseName));
			testingNode.checkType(studentContentTesting.getType(baseName));
			testingNode.checkMenuAudio(studentContentTesting.getMenuAudio(baseName));
			testingNode.checkMainImage(studentContentTesting.getMainImage(baseName));
			
			if(studentContentCorrect.isContentPresent(title)){
				foundArticlesCorrect.add(correctNode);
			}
			else{
				missingArticlesCorrect.add(correctNode);
			}
			
			if(studentContentCorrect.isContentDuplicated(title)){
				duplicatedArticlesCorrect.add(correctNode);
			}
			
			if(studentContentTesting.isContentPresent(title)){
				foundArticlesTesting.add(testingNode);
			}
			else{
				missingArticlesTesting.add(testingNode);
			}
			
			if(studentContentTesting.isContentDuplicated(title)){
				duplicatedArticlesTesting.add(testingNode);
			}
			
			if(studentContentCorrect.isContentPresent(title) && !studentContentTesting.isContentPresent(title)){
				differences.add(correctNode);
			}
			
			if(type.contains("C")){
				correctNode.setChildrenJson(topLevelContent.getAsJsonObject().get("categories").getAsJsonArray());
				
				ArrayList<ContentNode> categoriesToCheck = new ArrayList<ContentNode>();
				categoriesToCheck.add(correctNode);
				
				
				while(categoriesToCheck.size() > 0){
					ArrayList<ContentNode> categoriesFoundWhileSearching = new ArrayList<ContentNode>();
					
					for(int j = 0; j < categoriesToCheck.size(); j++){				
						ContentNode currentParent = categoriesToCheck.get(j);
						
						
						String URLCorrect = "https://"+userCorrect.getEnvironment()+".pebblego.com/modules/" + module + "/categories/"+currentParent.getID();
						String URLTesting = "https://"+userTesting.getEnvironment()+".pebblego.com/modules/" + module + "/categories/"+currentParent.getID();
	
						studentContentCorrect.getDriver().get(URLCorrect);
						studentContentTesting.getDriver().get(URLTesting);
						
						JsonArray articlesToCheck = currentParent.getChildrenJson();
							
						//if we are on the same page for both browsers, we can check all articles/categories under the current category	
						if(homeCorrect.getDriver().getCurrentUrl().equals(URLCorrect) && homeTesting.getDriver().getCurrentUrl().equals(URLTesting)){
							for(int k = 0; k < articlesToCheck.size(); k++){	
								JsonObject currentContent = articlesToCheck.get(k).getAsJsonObject();
								String currentTitle = currentContent.getAsJsonObject().get("title").toString().replace("\\", "");
								currentTitle = currentTitle.substring(1, currentTitle.length()-1);
								String currentId = currentContent.getAsJsonObject().get("article_id").toString().replace("\"", "");
								
								String currentBaseName = currentContent.getAsJsonObject().get("base_name").toString().replace("\"", "");
								String currentMainImage = currentContent.getAsJsonObject().get("main_image").toString().replace("\"", "");
								String currentAudio = currentContent.getAsJsonObject().get("menu_audio").toString().replace("\"", "");
								String currentType = currentContent.get("type").toString().replace("\"", "");
								
								
								ContentNode newCorrectNode = new ContentNode();
								
								newCorrectNode.setID(currentId);
								newCorrectNode.setBaseName(currentBaseName);
								newCorrectNode.setMainImage(currentMainImage);
								newCorrectNode.setType(currentType);
								newCorrectNode.setTitle(currentTitle);
								newCorrectNode.setMenuAudio(currentAudio);
								newCorrectNode.setPath(currentParent.getPath() + "->" + currentTitle);
								currentParent.addContentToChildren(newCorrectNode);
								
								ContentNode newTestingNode = newCorrectNode.copyNode();
								
								newCorrectNode.setBaseNameCorrect(studentContentCorrect.isBaseNameConnectedToTitle(currentBaseName, currentTitle));
								newCorrectNode.checkTitle(studentContentCorrect.getTitleForBaseName(currentBaseName));
								newCorrectNode.checkID(studentContentCorrect.getIDForBaseName(currentBaseName));
								newCorrectNode.checkType(studentContentCorrect.getType(currentBaseName));
								newCorrectNode.checkMenuAudio(studentContentCorrect.getMenuAudio(currentBaseName));
								newCorrectNode.checkMainImage(studentContentCorrect.getMainImage(currentBaseName));
								
								newTestingNode.setBaseNameCorrect(studentContentTesting.isBaseNameConnectedToTitle(currentBaseName, currentTitle));
								newTestingNode.checkTitle(studentContentTesting.getTitleForBaseName(currentBaseName));
								newTestingNode.checkID(studentContentTesting.getIDForBaseName(currentBaseName));
								newTestingNode.checkType(studentContentTesting.getType(currentBaseName));
								newTestingNode.checkMenuAudio(studentContentTesting.getMenuAudio(currentBaseName));
								newTestingNode.checkMainImage(studentContentTesting.getMainImage(currentBaseName));
								
							
								if(studentContentCorrect.isContentPresent(currentTitle)){
									foundArticlesCorrect.add(newCorrectNode);
								}
								else{
									missingArticlesCorrect.add(newCorrectNode);
								}
								
								if(studentContentTesting.isContentPresent(currentTitle)){
									foundArticlesTesting.add(newTestingNode);
								}
								else{
									missingArticlesTesting.add(newTestingNode);
								}
								
								if(studentContentCorrect.isContentPresent(currentTitle) && !studentContentTesting.isContentPresent(currentTitle)){
									differences.add(newCorrectNode);
								}

								
								//Checking the validity of each article on production
								if(studentContentCorrect.isContentDuplicated(currentTitle)){
									duplicatedArticlesCorrect.add(newCorrectNode);
								}
								
								if(studentContentTesting.isContentDuplicated(currentTitle)){
										duplicatedArticlesTesting.add(newTestingNode);
								}
									
								if(currentType.contains("C")){
									newCorrectNode.setChildrenJson(currentContent.getAsJsonObject().get("categories").getAsJsonArray());
									categoriesFoundWhileSearching.add(newCorrectNode);
								}
							}
						}
						
						//if home1 is not equal to it's own URL, we've been redirected, and it doesn't exist on staging
						//if it exists on home2, it is a difference and we check the rest of the tree
						else if(!homeCorrect.getDriver().getCurrentUrl().equals(URLCorrect) && homeTesting.getDriver().getCurrentUrl().equals(URLTesting)){														
							for(int k = 0; k < articlesToCheck.size(); k++){
								JsonObject currentContent = articlesToCheck.get(k).getAsJsonObject();
								String currentTitle = currentContent.getAsJsonObject().get("title").toString().replace("\\", "");
								currentTitle = currentTitle.substring(1, currentTitle.length()-1);
								String currentId = currentContent.getAsJsonObject().get("article_id").toString().replace("\"", "");
								
								String currentBaseName = currentContent.getAsJsonObject().get("base_name").toString().replace("\"", "");
								String currentMainImage = currentContent.getAsJsonObject().get("main_image").toString().replace("\"", "");
								String currentAudio = currentContent.getAsJsonObject().get("menu_audio").toString().replace("\"", "");
								String currentType = currentContent.get("type").toString().replace("\"", "");
								
								
								ContentNode newCorrectNode = new ContentNode();
								
								newCorrectNode.setID(currentId);
								newCorrectNode.setBaseName(currentBaseName);
								newCorrectNode.setMainImage(currentMainImage);
								newCorrectNode.setType(currentType);
								newCorrectNode.setTitle(currentTitle);
								newCorrectNode.setMenuAudio(currentAudio);
								newCorrectNode.setPath(currentParent.getPath() + "->" + currentTitle);
								currentParent.addContentToChildren(newCorrectNode);
								
								ContentNode newTestingNode = newCorrectNode.copyNode();
								
								newCorrectNode.setBaseNameCorrect(studentContentCorrect.isBaseNameConnectedToTitle(currentBaseName, currentTitle));
								newCorrectNode.checkTitle(studentContentCorrect.getTitleForBaseName(currentBaseName));
								newCorrectNode.checkID(studentContentCorrect.getIDForBaseName(currentBaseName));
								newCorrectNode.checkType(studentContentCorrect.getType(currentBaseName));
								newCorrectNode.checkMenuAudio(studentContentCorrect.getMenuAudio(currentBaseName));
								newCorrectNode.checkMainImage(studentContentCorrect.getMainImage(currentBaseName));
								
								newTestingNode.setBaseNameCorrect(studentContentTesting.isBaseNameConnectedToTitle(currentBaseName, currentTitle));
								newTestingNode.checkTitle(studentContentTesting.getTitleForBaseName(currentBaseName));
								newTestingNode.checkID(studentContentTesting.getIDForBaseName(currentBaseName));
								newTestingNode.checkType(studentContentTesting.getType(currentBaseName));
								newTestingNode.checkMenuAudio(studentContentTesting.getMenuAudio(currentBaseName));
								newTestingNode.checkMainImage(studentContentTesting.getMainImage(currentBaseName));
								
							
								if(studentContentCorrect.isContentPresent(currentTitle)){
									foundArticlesCorrect.add(newCorrectNode);
								}
								else{
									missingArticlesCorrect.add(newCorrectNode);
								}
								
								if(studentContentTesting.isContentPresent(currentTitle)){
									foundArticlesTesting.add(newTestingNode);
								}
								else{
									missingArticlesTesting.add(newTestingNode);
								}
								
								if(studentContentCorrect.isContentPresent(currentTitle) && !studentContentTesting.isContentPresent(currentTitle)){
									differences.add(newCorrectNode);
								}

								
								//Checking the validity of each article on production
								if(studentContentCorrect.isContentDuplicated(currentTitle)){
									duplicatedArticlesCorrect.add(newCorrectNode);
								}
								
								if(studentContentTesting.isContentDuplicated(currentTitle)){
										duplicatedArticlesTesting.add(newTestingNode);
								}
									
								if(currentType.contains("C")){
									newCorrectNode.setChildrenJson(currentContent.getAsJsonObject().get("categories").getAsJsonArray());
									categoriesFoundWhileSearching.add(newCorrectNode);
								}
							}
							
						}
						//if home2 is not equal to it's own URL, we've been redirected, and it doesn't exist on staging
						else if(!homeTesting.getDriver().getCurrentUrl().equals(URLTesting)){
							missingArticlesTesting.add(currentParent);
						}
					} 					
					
					categoriesToCheck = categoriesFoundWhileSearching;
				}
			}		
		}
		
		try {
			buildReports();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		userCorrect.quit();
		userTesting.quit();
	}
	
	@Test
	public void testAllModules() throws ParseException, IOException{
		JsonArray listOfModules = getJsonForModuleList();
		
		for(JsonElement j : listOfModules){
			System.out.println(j.toString());
			JsonObject parts = j.getAsJsonObject();
			//String part = parts.get("module_id").getAsString();
			String name = parts.get("name").getAsString();
			
			if(name.contains("PebbleGo Next")){
				
			}
			
			System.out.println(name);
		}	
	}
	
	
	public void buildReports() throws IOException{
		Path localPath = Paths.get("//Users/whall/Desktop/ContentVerification/");
		String folderPath = "//Users/whall/Desktop/ContentVerification/" + product + module + "/";
		
		if(Files.exists(localPath)){
			if(new File("//Users/whall/Desktop/ContentVerification/" + product + module).mkdirs()){
				System.out.println("Folder created at "+ folderPath);
			}
		}
		else if (new File("./ContentVerification/" + product + module).mkdirs()){
			folderPath = "./ContentVerification/"+ product + module + "/";
			System.out.println("folder created at " + folderPath);
		}
		else{
			System.out.print("Tried but could not create a folder");
		}
		
		System.out.println("Reports are saved to: " + folderPath);
		
		FileWriter errorsSavedHere = new FileWriter(folderPath+ "verificationErrors.csv");
		FileWriter successSavedHere = new FileWriter(folderPath + "verificationSuccesses.csv");
		
		currentError = new StringBuilder();
		currentSuccess = new StringBuilder();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		currentError.append("This report generated at "+ dateFormat.format(date) +"\n");
		currentSuccess.append("This report generated at "+ dateFormat.format(date) +"\n");
		
		errorsSavedHere.write(currentError.toString());
		errorsSavedHere.flush();
		
		successSavedHere.write(currentSuccess.toString());
		successSavedHere.flush();
		
		if(foundArticlesTesting.size() > 0){
			currentSuccess = new StringBuilder();
			currentSuccess.append("The following content is correct on " + testingEnvironment + ":\n");
			
			currentSuccess.append("Title,");
			currentSuccess.append("Path To Title,");
			currentSuccess.append(",");
			currentSuccess.append("Basename Correct,");
			currentSuccess.append("Main Image Correct,");
			currentSuccess.append("Menu Audio Correct,");
			currentSuccess.append("Type Correct,");
			currentSuccess.append("Title Correct,");
			currentSuccess.append("ID Correct, \n");
			
			
			for(ContentNode c : foundArticlesTesting){
				currentSuccess.append("\"" + c.getTitle().toString() + "\"" + ",");
				currentSuccess.append("\"" + c.getPath().toString() + "\"" + ",");
				currentSuccess.append(",");
				currentSuccess.append(c.isBaseNameCorrect() + ",");
				currentSuccess.append(c.isMainImageCorrect() + ",");
				currentSuccess.append(c.isMenuAudioCorrect() + ",");
				currentSuccess.append(c.isTypeCorrect() + ",");
				currentSuccess.append(c.isTitleCorrect() + ",");
				currentSuccess.append(c.isIDCorrect() + ",\n");
			}
			
			currentSuccess.append(",\n");
			
			successSavedHere.write(currentSuccess.toString());
			successSavedHere.flush();
		}
		
		if(foundArticlesCorrect.size() > 0){
			currentSuccess = new StringBuilder();
			currentSuccess.append("The following content is correct on " + correctEnvironment + ":\n");
			
			currentSuccess.append("Title,");
			currentSuccess.append("Path To Title,");
			currentSuccess.append(",");
			currentSuccess.append("Basename Correct,");
			currentSuccess.append("Main Image Correct,");
			currentSuccess.append("Menu Audio Correct,");
			currentSuccess.append("Type Correct,");
			currentSuccess.append("Title Correct,");
			currentSuccess.append("ID Correct, \n");
			
			
			for(ContentNode c : foundArticlesTesting){
				currentSuccess.append("\"" + c.getTitle().toString() + "\"" + ",");
				currentSuccess.append("\"" + c.getPath().toString() + "\"" + ",");
				currentSuccess.append(",");
				currentSuccess.append(c.isBaseNameCorrect() + ",");
				currentSuccess.append(c.isMainImageCorrect() + ",");
				currentSuccess.append(c.isMenuAudioCorrect() + ",");
				currentSuccess.append(c.isTypeCorrect() + ",");
				currentSuccess.append(c.isTitleCorrect() + ",");
				currentSuccess.append(c.isIDCorrect() + ",\n");
			}
			
			currentSuccess.append(",\n");
			
			successSavedHere.write(currentSuccess.toString());
			successSavedHere.flush();
		}
		
		
		if(missingArticlesCorrect.size() > 0){
			currentError = new StringBuilder();
			currentError.append("The following content is missing on " + correctEnvironment + ":\n");
			
			currentError.append("Title,");
			currentError.append("Path To Title,");
			currentError.append(",");
			currentError.append("Basename Correct,");
			currentError.append("Main Image Correct,");
			currentError.append("Menu Audio Correct,");
			currentError.append("Type Correct,");
			currentError.append("Title Correct,");
			currentError.append("ID Correct, \n");
			
			
			for(ContentNode c : missingArticlesCorrect){
				currentError.append("\"" + c.getTitle().toString() + "\"" + ",");
				currentError.append("\"" + c.getPath().toString() + "\"" + ",");
				currentError.append(",");
				currentError.append(c.isBaseNameCorrect() + ",");
				currentError.append(c.isMainImageCorrect() + ",");
				currentError.append(c.isMenuAudioCorrect() + ",");
				currentError.append(c.isTypeCorrect() + ",");
				currentError.append(c.isTitleCorrect() + ",");
				currentError.append(c.isIDCorrect() + ",\n");
			}
			
			currentSuccess.append(",\n");
			
			errorsSavedHere.write(currentError.toString());
			errorsSavedHere.flush();
		}
		
		if(duplicatedArticlesCorrect.size() > 0){
			currentError = new StringBuilder();
			currentError.append("The following content is duplicated on " + correctEnvironment + ":\n");
			
			currentError.append("Title,");
			currentError.append("Path To Title,");
			currentError.append(",");
			currentError.append("Basename Correct,");
			currentError.append("Main Image Correct,");
			currentError.append("Menu Audio Correct,");
			currentError.append("Type Correct,");
			currentError.append("Title Correct,");
			currentError.append("ID Correct, \n");
			
			
			for(ContentNode c : duplicatedArticlesCorrect){
				currentError.append("\"" + c.getTitle().toString() + "\"" + ",");
				currentError.append("\"" + c.getPath().toString() + "\"" + ",");
				currentError.append(",");
				currentError.append(c.isBaseNameCorrect() + ",");
				currentError.append(c.isMainImageCorrect() + ",");
				currentError.append(c.isMenuAudioCorrect() + ",");
				currentError.append(c.isTypeCorrect() + ",");
				currentError.append(c.isTitleCorrect() + ",");
				currentError.append(c.isIDCorrect() + ",\n");
			}
			
			currentError.append(",\n");
			
			errorsSavedHere.write(currentError.toString());
			errorsSavedHere.flush();
		}
		
		if(missingArticlesTesting.size() > 0){
			currentError = new StringBuilder();
			currentError.append("The following content is missing on " + testingEnvironment + ":\n");
			
			currentError.append("Title,");
			currentError.append("Path To Title,");
			currentError.append(",");
			currentError.append("Basename Correct,");
			currentError.append("Main Image Correct,");
			currentError.append("Menu Audio Correct,");
			currentError.append("Type Correct,");
			currentError.append("Title Correct,");
			currentError.append("ID Correct, \n");
			
			
			for(ContentNode c : missingArticlesTesting){
				currentError.append("\"" + c.getTitle().toString() + "\"" + ",");
				currentError.append("\"" + c.getPath().toString() + "\"" + ",");
				currentError.append(",");
				currentError.append(c.isBaseNameCorrect() + ",");
				currentError.append(c.isMainImageCorrect() + ",");
				currentError.append(c.isMenuAudioCorrect() + ",");
				currentError.append(c.isTypeCorrect() + ",");
				currentError.append(c.isTitleCorrect() + ",");
				currentError.append(c.isIDCorrect() + ",\n");
			}
			
			currentError.append(",\n");
			
			errorsSavedHere.write(currentError.toString());
			errorsSavedHere.flush();
		}

		if(duplicatedArticlesTesting.size() > 0){
			currentError = new StringBuilder();
			currentError.append("The following content is duplicated on " + testingEnvironment + ":\n");
			
			currentError.append("Title,");
			currentError.append("Path To Title,");
			currentError.append(",");
			currentError.append("Basename Correct,");
			currentError.append("Main Image Correct,");
			currentError.append("Menu Audio Correct,");
			currentError.append("Type Correct,");
			currentError.append("Title Correct,");
			currentError.append("ID Correct, \n");
			
			
			for(ContentNode c : duplicatedArticlesTesting){
				currentError.append("\"" + c.getTitle().toString() + "\"" + ",");
				currentError.append("\"" + c.getPath().toString() + "\"" + ",");
				currentError.append(",");
				currentError.append(c.isBaseNameCorrect() + ",");
				currentError.append(c.isMainImageCorrect() + ",");
				currentError.append(c.isMenuAudioCorrect() + ",");
				currentError.append(c.isTypeCorrect() + ",");
				currentError.append(c.isTitleCorrect() + ",");
				currentError.append(c.isIDCorrect() + ",\n");
			}
			
			currentError.append(",\n");
			
			errorsSavedHere.write(currentError.toString());
			errorsSavedHere.flush();
		}
		
		if(differences.size() > 0){
			currentError = new StringBuilder();
			currentError.append("The following content is on " + correctEnvironment + " but not on " +testingEnvironment + ":\n");
			
			currentError.append("Title,");
			currentError.append("Path To Title,");
			currentError.append(",");
			currentError.append("Basename Correct,");
			currentError.append("Main Image Correct,");
			currentError.append("Menu Audio Correct,");
			currentError.append("Type Correct,");
			currentError.append("Title Correct,");
			currentError.append("ID Correct, \n");
			
			
			for(ContentNode c : differences){
				currentError.append("\"" + c.getTitle().toString() + "\"" + ",");
				currentError.append("\"" + c.getPath().toString() + "\"" + ",");
				currentError.append(",");
				currentError.append(c.isBaseNameCorrect() + ",");
				currentError.append(c.isMainImageCorrect() + ",");
				currentError.append(c.isMenuAudioCorrect() + ",");
				currentError.append(c.isTypeCorrect() + ",");
				currentError.append(c.isTitleCorrect() + ",");
				currentError.append(c.isIDCorrect() + ",\n");
			}
			
			currentError.append(",\n");
			
			errorsSavedHere.write(currentError.toString());
			errorsSavedHere.flush();
		}	
		
		try {
			errorsSavedHere.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		
		try {
			successSavedHere.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public JsonArray getJsonForModuleList() throws ParseException, IOException{
		String token = getTokenForUsername("garage", "capdig", "7", "cms");
		
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet("https://contentqa.pebblego.com/api/v1/modules/list");
		
		httpGet.setHeader("Authorization", "Bearer " + token);

		HttpResponse response = client.execute(httpGet);
		
		String test = EntityUtils.toString(response.getEntity());
		JsonArray modulesList = (JsonArray) new JsonParser().parse(test);
		
		return modulesList;
	}
	
	public JsonObject getJsonForModule(String module, String environment) throws ParseException, IOException{
		URL url = new URL("https://"+environment+".pebblegonext.com/module/1/customer/20662?sqs=$2y$10$O0S60.9z0G8xKmEIrQf46.tE9n6GA13xrx.qG5KFgH.yNvR8AqyHm");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		
		con = (HttpURLConnection) url.openConnection();
		
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	    
	     //Read JSON response and print
	     in.close();
	     
	     JsonArray jsonElement = (JsonArray) new JsonParser().parse(response.toString());
	     JsonObject myResponse = jsonElement.get(5).getAsJsonObject();
		
		return myResponse;
	}
	

	public String getTokenForUsername(String username, String password, String appID, String loginType) throws ParseException, IOException{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("https://api.pebblego.com/api/v1/auth/check");
		
		ArrayList<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair("username", username));
		formparams.add(new BasicNameValuePair("password", password));
		formparams.add(new BasicNameValuePair("app_id", appID));
		formparams.add(new BasicNameValuePair("loginType", loginType));
		
		//Adds the form parameters and sets the Form as the entity for our post
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams);
		httpPost.setEntity(entity);
		
		HttpResponse response = client.execute(httpPost);
		
		//Handle the response and parse it into a searchable Json object
		String responseJson = EntityUtils.toString(response.getEntity());
		JsonObject testing = (JsonObject) new JsonParser().parse(responseJson);
		
		return testing.get("token").getAsString();
	}
}
