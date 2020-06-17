package Tests;

import java.io.FileWriter;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import ArticleModals.ActivityModal;
import ArticleModals.CitationModal;
import ArticleModals.PrintModal;
import ArticleModals.RangeMapModal;
import ArticleModals.SoundModal;
import ArticleModals.TimelineModal;
import ArticleModals.VideoModal;
import PGOPages.LoginPage;
import PGOStudentResources.ArticlePage;
import PGOStudentResources.StudentHomePage;
import SharedClasses.ThirdPartyPage;
import UserClasses.User;
import UserClasses.UserInfo;

public class PGOAllContentTests {
	static LoginPage loginQA;
	static LoginPage loginProd;
	FileWriter output; 
	StringBuilder content = new StringBuilder();
	static User qa = new User(UserInfo.GARAGESTUDENT);
	public int waitTime = 1;
    	

	public String getJSON(String url){
		
		JavascriptExecutor js = ((JavascriptExecutor) loginQA.getDriver());
		
		String body = (String) js.executeAsyncScript(
					"var url = arguments[0];" + 
				        "var callback = arguments[1];" +
				        "$.ajax({url: url, success: callback});", 
				       url);
		return body;
				    
	}

	
	
	/*
	public void testArticleByID() throws ClientProtocolException, IOException{
		loginQA = new LoginPage(qa);
		JsonObject response = loginQA.getArticleAssets(1);

		String mp3 = "https://assets.pebblego.com/cms_content/audio" + '/' + response.get("menu_audio").getAsString();

		String screen = response.get("screens_enabled").getAsJsonArray().get(0).getAsJsonObject().get("image").getAsString();
		String pic = "https://assets.pebblego.com/cms_content/images" + '/' + screen;
		
		
		
		Boolean active = loginQA.verifyImageActive(pic);
		Boolean active2 = loginQA.verifyAudioActive(mp3);
	}
	*/
	
	@Test
	public void TestAllArticlesInModule() throws ClientProtocolException, IOException{
		loginQA = new LoginPage(qa);
		JsonArray response = loginQA.getArticlesFromModule(1);
		
		for(JsonElement j : response){
			JsonArray screens = j.getAsJsonObject().get("screens_enabled").getAsJsonArray(); 

			if(screens.size() > 0){
				for(JsonElement e : screens){
					String image = e.getAsJsonObject().get("image").getAsString();
					String audio = e.getAsJsonObject().get("audio_file").getAsString();
					String time = e.getAsJsonObject().get("time_file").getAsString();
					
					if(!loginQA.verifyImageActive(image)){
						System.out.println(image);
					}
					
					if(!loginQA.verifyAudioActive(audio)){
						System.out.println(audio);
					}
					
					if(!loginQA.verifyXMLActive(time)){
						System.out.println(time);
					}
				}
			}
		}
		
	}
	
	@Test
	public void testAllModulePicsAnimals() throws ClientProtocolException, IOException{
		loginQA = new LoginPage(qa);
		JsonArray response = loginQA.getArticlesFromModule(1);
		int x = 0;
		
		for(JsonElement j : response){
			String image = j.getAsJsonObject().get("main_image").getAsString();
			String audio = j.getAsJsonObject().get("menu_audio").getAsString();
			
			Boolean imageActive = loginQA.verifyImageActive(image);
			Boolean audioActive = loginQA.verifyAudioActive(audio);
			if(!(imageActive)){
				System.out.println(image + " " + imageActive);	
			}
			if(!audioActive){
				System.out.println(audio + " " + audioActive);	
			}
			x++;
		}
		System.out.println(x*2 + " assets checked");
	}
	
	//@Test
	public void testAllModulePicsScience() throws ClientProtocolException, IOException{
		loginQA = new LoginPage(qa);
		JsonArray response = loginQA.getArticlesFromModule(2);
		int x = 0;
		
		for(JsonElement j : response){
			String image = j.getAsJsonObject().get("main_image").getAsString();
			String audio = j.getAsJsonObject().get("menu_audio").getAsString();
			
			Boolean imageActive = loginQA.verifyImageActive(image);
			Boolean audioActive = loginQA.verifyAudioActive(audio);
			if(!(imageActive)){
				System.out.println(image + " " + imageActive);	
			}
			if(!audioActive){
				System.out.println(audio + " " + audioActive);	
			}
			x++;
		}
		System.out.println(x*2 + " assets checked");
	}
	
	//@Test
	public void testAllModulePicsBiographies() throws ClientProtocolException, IOException{
		loginQA = new LoginPage(qa);
		JsonArray response = loginQA.getArticlesFromModule(3);
		int x = 0;
		
		for(JsonElement j : response){
			String image = j.getAsJsonObject().get("main_image").getAsString();
			String audio = j.getAsJsonObject().get("menu_audio").getAsString();
			
			Boolean imageActive = loginQA.verifyImageActive(image);
			Boolean audioActive = loginQA.verifyAudioActive(audio);
			if(!(imageActive)){
				System.out.println(image + " " + imageActive);	
			}
			if(!audioActive){
				System.out.println(audio + " " + audioActive);	
			}
			x++;
		}
		System.out.println(x*2 + " assets checked");
	}
	
	//@Test
	public void testAllModulePicsSocialStudies() throws ClientProtocolException, IOException{
		loginQA = new LoginPage(qa);
		JsonArray response = loginQA.getArticlesFromModule(5);
		int x = 0;
		
		for(JsonElement j : response){
			String image = j.getAsJsonObject().get("main_image").getAsString();
			String audio = j.getAsJsonObject().get("menu_audio").getAsString();
			
			Boolean imageActive = loginQA.verifyImageActive(image);
			Boolean audioActive = loginQA.verifyAudioActive(audio);
			if(!(imageActive)){
				System.out.println(image + " " + imageActive);	
			}
			if(!audioActive){
				System.out.println(audio + " " + audioActive);	
			}
			x++;
		}
		System.out.println(x*2 + " assets checked");
	}
	
	//@Test
	public void testAllModulePicsDinosaurs() throws ClientProtocolException, IOException{
		loginQA = new LoginPage(qa);
		JsonArray response = loginQA.getArticlesFromModule(7);
		int x = 0;
		
		for(JsonElement j : response){
			String image = j.getAsJsonObject().get("main_image").getAsString();
			String audio = j.getAsJsonObject().get("menu_audio").getAsString();
			
			Boolean imageActive = loginQA.verifyImageActive(image);
			Boolean audioActive = loginQA.verifyAudioActive(audio);
			if(!(imageActive)){
				System.out.println(image + " " + imageActive);	
			}
			if(!audioActive){
				System.out.println(audio + " " + audioActive);	
			}
			x++;
		}
		System.out.println(x*2 + " assets checked");
	}
	
	//@Test
	public void testAllModulePicsAnimales() throws ClientProtocolException, IOException{
		loginQA = new LoginPage(qa);
		JsonArray response = loginQA.getArticlesFromModule(8);
		int x = 0;
		
		for(JsonElement j : response){
			String image = j.getAsJsonObject().get("main_image").getAsString();
			String audio = j.getAsJsonObject().get("menu_audio").getAsString();
			
			Boolean imageActive = loginQA.verifyImageActive(image);
			Boolean audioActive = loginQA.verifyAudioActive(audio);
			if(!(imageActive)){
				System.out.println(image + " " + imageActive);	
			}
			if(!audioActive){
				System.out.println(audio + " " + audioActive);	
			}
			x++;
		}
		System.out.println(x*2 + " assets checked");
	}
	
	/*
	@Test
	public void AllAnimalGlossary() throws IOException{
		//prod.makeNewDriver();
		loginQA = new LoginPage(qa);
		//loginProd = new LoginPage(prod);
		
		loginQA.getDriver().get("https://qa.pebblego.com");
		
		
		
		StudentHomePage qaHome = (StudentHomePage) loginQA.Login(qa);

		
		//output = new FileWriter("test.csv");
		AnimalsContentPage animalsQA = qaHome.clickAnimalsModule();
		//AnimalsContentPage animalsProd = prodHome.clickAnimalsModule();
		
		//animalsQA.clickCategoryByText("Amphibians");
		ArticlePage articleQA = animalsQA.clickArticleByText("Frogs");
		
		
		File file = new File("pgo_glossary_new.csv");
		Scanner scanner = new Scanner(file); //article_id,title
		scanner.useDelimiter("\n");
		String nextLine = "";
		
	
		nextLine = scanner.nextLine();
		if(nextLine.equals("article_id,title,glossary_id,word,base_word,description,audio_file,time_file")){
			nextLine = scanner.nextLine();
		}
		
		
		while(!nextLine.contains("Climate")){
			nextLine = scanner.nextLine();
		}
		
		
		String currentArticle = "";
		//int currentGlossary = 0;
		
		//ArrayList<WebElement> glossaryTerms = new ArrayList<WebElement>();
		//int currentTab = 1;
				
		PrintWriter pw = new PrintWriter(new File("animals.csv"));
		StringBuilder sb = new StringBuilder();
		sb.append("Article Name");
		sb.append(",");
		sb.append("Article Id");
		sb.append(",");
		sb.append("Mp3 File");
		sb.append(",");
		sb.append("Mp3 Correct");
		sb.append(",");
		sb.append("Mp3 Plays");
		sb.append(",");
		sb.append("Xml File");
		sb.append(",");
		sb.append("Xml Correct");
		sb.append(",");
		sb.append("Definition");
		sb.append(",");
		sb.append("Definition Correct");
		sb.append("\n");
		
		
		//while(!nextLine.contains("Animal Communication")){
		//	nextLine = scanner.nextLine();
		//}
		
		
		String articleName = "";
		
		
		while(!nextLine.contains("Wetlands")){
			try{
				String articleID = nextLine.subSequence(nextLine.indexOf('"')+1, nextLine.indexOf(",")-1).toString();
				articleName = nextLine.subSequence(nextLine.indexOf(',')+2, nextLine.length()).toString();
				articleName = articleName.subSequence(0,  articleName.indexOf(",")- 1).toString();
			
			
			//articleName = articleName.subSequence(0,  articleName.indexOf(",")- 1).toString();
			
				if(!articleID.equals(currentArticle)){
					qaHome = articleQA.header.clickHomeBreadcrumb();
					qaHome.waitImplicitly(1);
				
					articleQA.header.searchWithID(articleName, articleID);
			
					//currentGlossary = 0;
					//currentTab = 1;
					currentArticle = articleID;
				
				//glossaryTerms = (ArrayList<WebElement>) articleQA.getDriver().findElements(By.className("glossary"));
				}
			
				String term = getTerm(nextLine);
				String baseTerm = getBaseTerm(nextLine);
			
				String voice = getVoice(nextLine);
			
				String xmlFile = getXML(nextLine);
			
				String text = getGlossaryText(nextLine);
			
				articleQA.LookForGlossary(term);
		
			//asrticleQA.LookForDoubles();
				articleQA.waitImplicitly(waitTime);
				
				Boolean passed = false;
				String voiceData = "";
				String xmlData = "";
				String definition = "";
				Boolean voiceMatch = false;
				Boolean xmlMatch = false;
				Boolean defMatch = false;
			
				try{
					GlossaryModal glossaryQA = new GlossaryModal(qa);
					passed = glossaryQA.pressPlay();
					definition = glossaryQA.getGlossaryText(baseTerm);
				
					voiceData = glossaryQA.getOratorData();
					xmlData = glossaryQA.getXMLData();
				
					voiceMatch = voiceData.equals(voice);
					xmlMatch = xmlData.equals(xmlFile);
					defMatch = definition.equals(text);
				
					glossaryQA.click(glossaryQA.closeButton);
				}catch(Exception e){
					voiceData = voice;
					xmlData = xmlFile;
					definition = text;
				}

				articleQA.waitImplicitly(waitTime);
				articleQA.clickTabOne();
				articleQA.waitImplicitly(waitTime);

			
			sb.append(articleName);
			sb.append(",");
			sb.append(articleID);
			sb.append(",");
			sb.append(voiceData);
			sb.append(",");
			sb.append(voiceMatch);
			sb.append(",");
			sb.append(passed);
			sb.append(",");
			sb.append(xmlData);
			sb.append(",");
			sb.append(xmlMatch);
			sb.append(",");
			sb.append(definition.replaceAll(",", "/"));
			sb.append(",");
			sb.append(defMatch);
			sb.append("\n");

			nextLine = scanner.nextLine();
			//currentGlossary++;
			} catch(Exception e){

			}
		}	
		
		
		pw.write(sb.toString());
		pw.close();
		

		
	}
	*/
	
	public String getTerm(String nextLine){
		String term = "";
		term = nextLine.subSequence(nextLine.indexOf(","), nextLine.length()).toString();
		term = term.subSequence(term.indexOf(",")+2, term.length()).toString();
		term = term.subSequence(term.indexOf(",")+2, term.length()).toString();
		term = term.subSequence(term.indexOf(",")+2, term.length()).toString();
		term = term.subSequence(0, term.indexOf(",")-1).toString();
		return term;
	}
	
	public String getBaseTerm(String nextLine){
		String term = "";
		term = nextLine.subSequence(nextLine.indexOf(","), nextLine.length()).toString();
		term = term.subSequence(term.indexOf(",")+2, term.length()).toString();
		term = term.subSequence(term.indexOf(",")+2, term.length()).toString();
		term = term.subSequence(term.indexOf(",")+2, term.length()).toString();
		term = term.subSequence(term.indexOf(",")+2, term.length()).toString();
		term = term.subSequence(0, term.indexOf(",")-1).toString();
		return term;
	}
	
	
	
	public String getVoice(String nextLine){
		String voice = "";
		voice = nextLine.subSequence(0 , nextLine.lastIndexOf(".mp3")+4).toString();
		voice = voice.substring(voice.lastIndexOf(",")+2, voice.length());
		return voice;
	}
	
	public String getXML(String nextLine){
		String xml = "";
		xml = nextLine.subSequence(nextLine.lastIndexOf(",") + 2, nextLine.length()-1).toString();
		return xml;
	}
	
	public String getGlossaryText(String nextLine){
		String text = "";
		text = nextLine.subSequence(nextLine.indexOf(","), nextLine.length()).toString();
		text = text.subSequence(text.indexOf(",")+2, text.length()).toString();
		text = text.subSequence(text.indexOf(",")+2, text.length()).toString();
		text = text.subSequence(text.indexOf(",")+2, text.length()).toString();
		text = text.subSequence(text.indexOf(",")+2, text.length()).toString();
		text = text.subSequence(text.indexOf(",")+1, text.length()).toString();
		text = text.subSequence(1, text.indexOf("gl_")-3).toString().trim();
 		return text;
	}
	
	
	
	@Test
	public void testPGOArticleAssetsByModule() throws InterruptedException, IOException{
		LoginPage login = new LoginPage(qa);
		//login.getDriver().get("https://qa.pebblego.com");
		//StudentHomePage student = (StudentHomePage) login.Login(qa);
		FileWriter output = new FileWriter("animalstest.csv");
		
		content.append("Article,");
		content.append("Error,");
		content.append("File, \n");

		output.write(content.toString());
		output.flush();
		
		content = new StringBuilder();
		
		JsonArray response = login.getArticlesFromModule("staging", 5);
		
		for(JsonElement j : response){
			JsonArray screens = j.getAsJsonObject().get("screens_enabled").getAsJsonArray(); //.getAsJsonArray();

			content = new StringBuilder();
			
			int articleNumber = j.getAsJsonObject().get("article_id").getAsInt();
			String articleName = j.getAsJsonObject().get("title").getAsString().toLowerCase();
			
			if(screens.size() > 0){		
					ArticlePage article = new ArticlePage(login.getDriver());

					String imageOne = screens.get(0).getAsJsonObject().get("image").toString().replace("\"", " ").trim();
					String audioOne = screens.get(0).getAsJsonObject().get("audio_file").toString().replace("\"", " ").trim();
					String timeOne = screens.get(0).getAsJsonObject().get("time_file").toString().replace("\"", " ").trim();
						
					if(!article.verifyImageActive(imageOne)){
						content.append(articleName + "-" + articleNumber + ",");
						content.append("Tab One Image,");
						content.append(imageOne + ", \n");
					}
						
					if(!article.verifyAudioActive(audioOne)){
						content.append(articleName + "-" + articleNumber + ",");
						content.append("Tab One Audio,");
						content.append(audioOne + ", \n");
					}
						
					if(!article.verifyXMLActive(timeOne)){
						content.append(articleName + "-" + articleNumber + ",");
						content.append("Tab One XML,");
						content.append(timeOne + ", \n");
					}
	
					String imageTwo = screens.get(1).getAsJsonObject().get("image").toString().replace("\"", " ").trim();
					String audioTwo = screens.get(1).getAsJsonObject().get("audio_file").toString().replace("\"", " ").trim();
					String timeTwo = screens.get(1).getAsJsonObject().get("time_file").toString().replace("\"", " ").trim();
						
					if(!article.verifyImageActive(imageTwo)){
						content.append(articleName + "-" + articleNumber + ",");
						content.append("Tab Two Image,");
						content.append(imageTwo + ", \n");
					}
						
					if(!article.verifyAudioActive(audioTwo)){
						content.append(articleName + "-" + articleNumber + ",");
						content.append("Tab Two Audio,");
						content.append(audioTwo + ", \n");
					}
						
					if(!article.verifyXMLActive(timeTwo)){
						content.append(articleName + "-" + articleNumber + ",");
						content.append("Tab Two XML,");
						content.append(timeTwo + ", \n");
					}
					
					String imageThree = screens.get(2).getAsJsonObject().get("image").toString().replace("\"", " ").trim();
					String audioThree = screens.get(2).getAsJsonObject().get("audio_file").toString().replace("\"", " ").trim();
					String timeThree = screens.get(2).getAsJsonObject().get("time_file").toString().replace("\"", " ").trim();
						
					if(!article.verifyImageActive(imageThree)){
						content.append(articleName + "-" + articleNumber + ",");
						content.append("Tab Three Image,");
						content.append(imageThree + ", \n");
					}
						
					if(!article.verifyAudioActive(audioThree)){
						content.append(articleName + "-" + articleNumber + ",");
						content.append("Tab Three Audio,");
						content.append(audioThree + ", \n");
					}
						
					if(!article.verifyXMLActive(timeThree)){
						content.append(articleName + "-" + articleNumber + ",");
						content.append("Tab Three XML,");
						content.append(timeThree + ", \n");
					}
						
					String imageFour = screens.get(3).getAsJsonObject().get("image").toString().replace("\"", " ").trim();
					String audioFour = screens.get(3).getAsJsonObject().get("audio_file").toString().replace("\"", " ").trim();
					String timeFour = screens.get(3).getAsJsonObject().get("time_file").toString().replace("\"", " ").trim();
						
					if(!article.verifyImageActive(imageFour)){
						content.append(articleName + "-" + articleNumber + ",");
						content.append("Tab Four Image,");
						content.append(imageFour + ", \n");
					}
						
					if(!article.verifyAudioActive(audioFour)){
						content.append(articleName + "-" + articleNumber + ",");
						content.append("Tab Four Audio,");
						content.append(audioFour + ", \n");
					}
						
					if(!article.verifyXMLActive(timeFour)){
						content.append(articleName + "-" + articleNumber + ",");
						content.append("Tab Four XML,");
						content.append(timeFour + ", \n");
					}
						
					String imageFive = screens.get(4).getAsJsonObject().get("image").toString().replace("\"", " ").trim();
					String audioFive = screens.get(4).getAsJsonObject().get("audio_file").toString().replace("\"", " ").trim();
					String timeFive = screens.get(4).getAsJsonObject().get("time_file").toString().replace("\"", " ").trim();
						
					if(!article.verifyImageActive(imageFive)){
						content.append(articleName + "-" + articleNumber + ",");
						content.append("Tab Five Image,");
						content.append(imageFive + ", \n");
					}
						
					if(!article.verifyAudioActive(audioFive)){
						content.append(articleName + "-" + articleNumber + ",");
						content.append("Tab Five Audio,");
						content.append(audioFive + ", \n");
					}
						
					if(!article.verifyXMLActive(timeFive)){
						content.append(articleName + "-" + articleNumber + ",");
						content.append("Tab Five XML,");
						content.append(timeFive + ", \n");
					}
														
					if(j.getAsJsonObject().get("videos").getAsJsonArray().size() > 0){
						JsonArray videos = j.getAsJsonObject().get("videos").getAsJsonArray();
						for(JsonElement v : videos){
							String testVideo = v.getAsJsonObject().get("video").getAsString().replace("\"", " ").trim();
							if(!article.verifyVideoActive(testVideo)){
								content.append(articleName + "-" + articleNumber + ",");
								content.append("Video");
								content.append(testVideo +", \n");
							}
						}
					}
						
						

			}
			
			JsonArray glossaries = j.getAsJsonObject().get("glossaries").getAsJsonArray();
			for(JsonElement g : glossaries){
				String glossaryID = g.getAsJsonObject().get("glossary_id").getAsString().replace("\"", " ").trim();
				
				ArticlePage article = new ArticlePage(login.getDriver());
				String audio = g.getAsJsonObject().get("audio_file").getAsString().replace("\"", " ").trim();
				String timeFile = g.getAsJsonObject().get("time_file").getAsString().replace("\"", " ").trim();
				
				if(!article.verifyAudioActive(audio)){
					content.append(articleName + "-" + articleNumber + ",");
					content.append("Glossary ID " + glossaryID + " Audio,");
					content.append(audio + ", \n");
				}
				
				if(!article.verifyXMLActive(timeFile)){
					content.append(articleName + "-" + articleNumber + ",");
					content.append("Glossary ID " + glossaryID + "XML,");
					content.append(timeFile + ", \n");
				}
			}
			
			
			
			if(j.getAsJsonObject().get("article_activity").getAsJsonArray().size() > 0){
				JsonElement activities = j.getAsJsonObject().get("article_activity").getAsJsonArray().get(0);
				
				JsonElement activityFile = activities.getAsJsonObject().get("activity_file"); 
				JsonElement shareFile = activities.getAsJsonObject().get("share_file");
				JsonElement questionFile = activities.getAsJsonObject().get("question_file");
				ArticlePage article = new ArticlePage(login.getDriver());
				
				if(!activityFile.isJsonNull()){
					String activity = activityFile.getAsString().replace("\"", " ").trim();
					if(!article.verifyPDFActive(activity)){
						content.append(articleName + "-" + articleNumber + ",");
						content.append("Activity File,");
						content.append(activityFile.getAsString() + ", \n");
					}
				}
				
				if(!shareFile.isJsonNull()){
					if(!article.verifyPDFActive(shareFile.getAsString().replace("\"", " ").trim())){
						content.append(articleName + "-" + articleNumber + ",");
						content.append("Share File,");
						content.append(shareFile.getAsString() + ", \n");
					}
				}
				
				if(!questionFile.isJsonNull()){
					if(!article.verifyPDFActive(questionFile.getAsString().replace("\"", " ").trim())){
						content.append(articleName + "-" + articleNumber + ",");
						content.append("Question File,");
						content.append(questionFile.getAsString() + ", \n");
					}
				}

			}
			
			output.write(content.toString());
			output.flush();
			
			System.out.println(articleName + " done");
			
		}
		output.write(content.toString());
		output.close();
	}
	
	@Test
	public void testAnimalsSoundAndRangeMaps() throws IOException{
		LoginPage login = new LoginPage(qa);
		login.getDriver().get("https://qa.pebblego.com");
		FileWriter output = new FileWriter("habitatsAndSoundsTestDinosaurs.csv");
		
		int module = 8;
		
		StudentHomePage home = (StudentHomePage) login.Login(qa);
		
		content.append("Article,");
		content.append("Error,");
		content.append("File, \n");

		output.write(content.toString());
		output.flush();
		

		
		JsonArray response = login.getArticlesFromModule("qa", 	module);
		
		for(JsonElement r : response){
			
			JsonArray screens = r.getAsJsonObject().get("screens_enabled").getAsJsonArray();
			
			if(screens.size() > 0){
				
				content = new StringBuilder();
				
				
				int audio = r.getAsJsonObject().get("has_audio").getAsInt();
				int map = r.getAsJsonObject().get("has_map").getAsInt();
				String articleID = r.getAsJsonObject().get("article_id").getAsString();
				String articleName = r.getAsJsonObject().get("title").getAsString();
			
			
			
				home.getDriver().get("https://qa.pebblego.com/modules/" + module + "/articles/" + articleID);
				home.waitImplicitly(2);
				
				ArticlePage article = new ArticlePage(home.getDriver());
				
			
				if(audio == 1){
					if(article.isListenButtonPresent()){
						SoundModal sound = article.clickListenButton();
						sound.waitImplicitly(1);
						
						String listen = sound.getAudioURL();
						
						if(!sound.verifyAsset(listen)){
							content.append(articleName + " ,");
							content.append("Listen audio is not active ,");
							content.append(listen + " , \n");
						}
						
						sound.closeModal();
						article.waitImplicitly(1);
					}
					else{
						content.append(articleName + " ,");
						content.append("Article does not have listen audio ,");
						content.append("File is not present, \n");
					}
				}
				else{
					if(article.isListenButtonPresent()){
						SoundModal sound = article.clickListenButton();
						sound.waitImplicitly(1);
						String error = sound.getAudioURL();
						
						content.append(articleName + " ,");
						content.append("Article given incorrect listen audio ,");
						content.append(error + ", \n");
						
						sound.closeModal();
						article.waitImplicitly(1);
					}
				}
				
				
				if(map == 1){
					if(article.isHabitatButtonPresent()){
						RangeMapModal range = article.clickHabitatButton();
						range.waitImplicitly(2);
						
						String image = range.getImageSource();
						
						if(!range.verifyAsset(image)){
							content.append(articleName + ",");
							content.append("Range Map Image is not active ,");
							content.append(image + ", \n");
						}
						
						range.closeModal();
						article.waitImplicitly(1);
					}
					else{
						content.append(articleName + ",");
						content.append("Article does not have habitat button,");
						content.append("File is not present, \n");
					}
				}
				else{
					if(article.isHabitatButtonPresent()){
						RangeMapModal range = article.clickHabitatButton();
						range.waitImplicitly(2);
						
						String error = range.getImageSource();
						
						content.append(articleName + ",");
						content.append("Article given incorrect habitat image,");
						content.append(error + ", \n");
						
						range.closeModal();
						article.waitImplicitly(1);
					}
				}
				
				output.write(content.toString());
				output.flush();
				
				System.out.println(articleName + " done");
			}
		}
		
		output.write(content.toString());
		output.close();
		
	}
	
	
	@Test
	public void testPGOTimelinesByModule() throws IOException{
		LoginPage login = new LoginPage(qa, "qa");
		FileWriter output = new FileWriter("timelinesByModule.csv");
		
		StudentHomePage student = (StudentHomePage) login.Login(qa);
		
		content = new StringBuilder();
		content.append("Article,");
		content.append("Error,");
		content.append("File, \n");
		output.write(content.toString());
		output.flush();
		
		int module = 3;
		
		content = new StringBuilder();
		
		JsonArray response = login.getArticlesFromModule(module);
		
		for(JsonElement r : response){
			content = new StringBuilder();
			
			ArticlePage article = new ArticlePage(student.getDriver());
			String articleID = r.getAsJsonObject().get("article_id").getAsString();
			String articleName = r.getAsJsonObject().get("title").getAsString();
			
			JsonArray timelines = r.getAsJsonObject().get("article_timelines_sorted").getAsJsonArray();
			TimelineModal timeline;
			
			if(timelines.size() > 0){
				article.getDriver().get("https://qa.pebblego.com/modules/"+module+"/articles/" + articleID);
				
				//int timelineID = t.getAsJsonObject().get("timeline_id").getAsInt();
				
				article.waitImplicitly(2);
				timeline = article.clickTimelineButton();
				timeline.waitImplicitly(2);
				
				int onScreen = timeline.getCountOfTimelineElement();
				int database = timelines.size();
				
				
				timeline.closeModal();
				
				if(onScreen != database){
					content.append(articleName + ",");
					content.append("Article has incorrect number of timeline elements,");	
				}
				
				
			}
			
			output.write(content.toString());
			output.flush();
		}
		
		output.write(content.toString());
		output.close();
	}
	
	
	
	public void testGlossaryTermsForArticle(ArticlePage article, int articleID){
		
		
		
		
		
		
	}
	
	public void testAnimalArticle(ArticlePage article, int articleNumber) throws InterruptedException, IOException{
		content = new StringBuilder();
		
		String articleName = article.getArticleName();
		
		
		Boolean TabOne = article.readTabOne();
		
		article.clickTabTwo().waitImplicitly(2);
		Boolean TabTwo = article.readTabTwo();
		
		article.clickTabThree().waitImplicitly(2);
		Boolean TabThree = article.readTabThree();
		
		article.clickTabFour().waitImplicitly(2);
		Boolean TabFour = article.readTabFour();
		
		article.clickTabFive().waitImplicitly(2);
		Boolean TabFive = article.readTabFive();
		
		article.clickTabOne();
		

		Boolean cite = false;
		String oldWindow = article.getDriver().getWindowHandle();
		ThirdPartyPage tPP;
		
		if(article.isCiteButtonPresent()){
			CitationModal citeModal = article.clickCiteButton();
			citeModal.waitImplicitly(2);		
			
			tPP = citeModal.printCitation();
			cite = tPP.verifyNewWindow(oldWindow, "https://www.pebblego.com/activity/cite.pdf?type=ala&aid="+ articleNumber +"");
			citeModal.close();
		}
		else{
			cite = null;
		}
		
		Boolean printArticle = false;
		Boolean printImage = false;
		
		if(article.isPrintButtonPresent()){
			PrintModal print = article.clickPrintButton();
			print.waitImplicitly(2);
			tPP = print.printArticle();
			printArticle = tPP.verifyNewWindow(oldWindow, "https://www.pebblego.com/activity/article.pdf?aid=38" + articleNumber + "");
			
			tPP = print.printImage();
			printImage =  tPP.verifyNewWindow(oldWindow, "https://www.pebblego.com/activity/image.pdf?sid=screen_1");
			print.closeModal();
		}
		else{
			printArticle = null;
			printImage = null;
		}
		
		Boolean printShareWhatYouKnow = false;
		Boolean printActivity = false;
		
		if(article.isActivityButtonPresent()){
			ActivityModal activity = article.clickActivityButton();
			activity.waitImplicitly(2);
			
			if(activity.isShareWhatYouKnowButtonPresent()){
				tPP = activity.printShareWhatYouKnow();
				printShareWhatYouKnow = tPP.verifyNewWindow(oldWindow, "https://assets.pebblego.com/content/animals/pdf/share_activity_id"+ articleName +".pdf");
			}
			else{
				printShareWhatYouKnow = null;
			}
			
			if(activity.isPrintActivityButtonPresent()){
				tPP = activity.printActivity();
				printActivity = tPP.verifyNewWindow(oldWindow, "https://assets.pebblego.com/content/animals/pdf/"+ articleName +".pdf");
			}
			else{
				printActivity = null;
			}
			
			activity.closeModal();
		}
		else{
			printShareWhatYouKnow = null;
			printActivity = null;
		}
		
		Boolean videoPlayed = false;
		if(article.isVideoButtonPresent()){
			VideoModal video = article.clickVideoButton();
			video.waitImplicitly(2);
			
			videoPlayed = video.PlayVideo();
			video.clickCloseButton();
		}
		else{
			videoPlayed = null;
		}
		
		Boolean soundPlayed = false;
		if(article.isListenButtonPresent()){
			SoundModal listen = article.clickListenButton();
			listen.waitImplicitly(2);
			
			soundPlayed = listen.clickPlayButton();
			listen.closeModal();
		}
		else{
			soundPlayed = null;
		}
		
		Boolean printRangeMap = false;
		if(article.isHabitatButtonPresent()){
			RangeMapModal rangeMaps = article.clickHabitatButton();
			rangeMaps.waitImplicitly(2);
			
			printRangeMap = rangeMaps.getImageSource().contains(articleName);
			rangeMaps.closeModal();
		}
		else{
			printRangeMap = null;
		}
		
		content.append(articleNumber + ",");
		content.append(articleName + ",");
		content.append(TabOne.toString() + ",");
		content.append(TabTwo.toString() + ",");
		content.append(TabThree.toString() + ",");
		content.append(TabFour.toString() + ",");
		content.append(TabFive.toString() + ",");
		content.append("related articles,");
		
		if(cite != null){
			content.append(cite.toString() +",");
		}
		else{
			content.append("N/A,");
		}
		
		if(printArticle != null){
			content.append(printArticle.toString() + ",");
		}
		else{
			content.append("N/A,");
		}
		
		if(printImage != null){
			content.append(printImage.toString() + ",");
		}
		else{
			content.append("N/A,");
		}
		
		if(printShareWhatYouKnow != null){
			content.append(printShareWhatYouKnow.toString() + ",");
		}
		else{
			content.append("N/A,");
		}
		
		if(printActivity != null){
			content.append(printActivity.toString() + ",");
		}
		else{
			content.append("N/A,");
		}
		
		if(videoPlayed != null){
			content.append(videoPlayed.toString() + ",");
		}
		else{
			content.append("N/A,");
		}
		
		if(soundPlayed != null){
			content.append(soundPlayed.toString() + ",");
		}
		else{
			content.append("N/A,");
		}
		
		if(printRangeMap != null){
			content.append(printRangeMap.toString() + ",\n");
		}
		else{
			content.append("N/A,");
		}

	}
	/*
	public void testScienceArticle(ArticlePage article, String articleNumber, String articleName) throws InterruptedException{
		Boolean TabOne = article.readTabOne();
		//assertTrue("User can read the first tab", article.readTabOne());
		
		article.clickTabTwo().waitImplicitly(2);
		Boolean TabTwo = article.readTabTwo();
		//assertTrue("User can read the second tab", article.readTabTwo());
		
		article.clickTabThree().waitImplicitly(2);
		Boolean TabThree = article.readTabThree();
		//assertTrue("User can read the third tab", article.readTabThree());
		
		article.clickTabFour().waitImplicitly(2);
		Boolean TabFour = article.readTabFour();
		//assertTrue("User can read the fourth tab", article.readTabFour());
		
		article.clickTabFive().waitImplicitly(2);
		Boolean TabFive = article.readTabFive();
		//assertTrue("User can read the fifth tab", article.readTabFive());
		article.readTabFive();
		article.clickTabOne();
		
		Boolean cite = false;
		String oldWindow = article.getDriver().getWindowHandle();
		ThirdPartyPage tPP;
		
		if(article.isCiteButtonPresent()){
			CitationModal citeModal = article.clickCiteButton();
			citeModal.waitImplicitly(2);		
			
			tPP = citeModal.printCitation();
			cite = tPP.verifyNewWindow(oldWindow, "https://www.pebblego.com/activity/cite.pdf?type=ala&aid="+ articleNumber +"");
			citeModal.close();
		}
		else{
			cite = null;
		}
		
		Boolean printShareWhatYouKnow = false;
		Boolean printActivity = false;
		Boolean printQuestionsForUnderstanging;
		
		if(article.isActivityButtonPresent()){
			ActivityModal activity = article.clickActivityButton();
			activity.waitImplicitly(2);
			
			if(activity.isShareWhatYouKnowButtonPresent()){
				tPP = activity.printShareWhatYouKnow();
				printShareWhatYouKnow = tPP.verifyNewWindow(oldWindow, "https://assets.pebblego.com/content/animals/pdf/share_"+ articleName +".pdf");
			}
			else{
				printShareWhatYouKnow = null;
			}
			
			if(activity.isPrintActivityButtonPresent()){
				tPP = activity.printActivity();
				printActivity = tPP.verifyNewWindow(oldWindow, "https://assets.pebblego.com/content/animals/pdf/"+ articleName +".pdf");
			}
			else{
				printActivity = null;
			}
			
			activity.closeModal();
		}
		else{
			printShareWhatYouKnow = null;
			printActivity = null;
		}
	}
	*/
	public void testBiographiesArticle(){
		
	}
	
	public void testSocialStudiesArticle(){
		
	}
	
	public void testDinosaursArticle(){
		
	}
	
	public void testAnimalesArticle(){
		
	}
}
