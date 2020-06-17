package Tests;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import PGOPages.LoginPage;
import UserClasses.User;
import UserClasses.UserInfo;

public class PGNAllContentTests{
	static LoginPage loginQA;
	static LoginPage loginProd;
	FileWriter output; 
	StringBuilder content = new StringBuilder();
	static User qa = new User(UserInfo.GARAGESTUDENT);
	public int waitTime = 1;
	
	@Test
	public void testAllModulePicsPGNStates() throws ClientProtocolException, IOException{
		loginQA = new LoginPage(qa);
		JsonArray response = loginQA.getArticlesFromModule(6);
		int x = 0;
		
		for(JsonElement j : response){
			String image = j.getAsJsonObject().get("main_image").getAsString();

			Boolean imageActive = loginQA.verifyImageActive(image);
			
			if(!(imageActive)){
				System.out.println(image + " " + imageActive);	
			}
			
			x++;
		}
		System.out.println(x*2 + " assets checked");
	}
	
	@Test
	public void testAllModulePicsPGNScience() throws ClientProtocolException, IOException{
		loginQA = new LoginPage(qa);
		JsonArray response = loginQA.getArticlesFromModule(9);
		int x = 0;
		
		for(JsonElement j : response){
			String image = j.getAsJsonObject().get("main_image").getAsString();

			Boolean imageActive = loginQA.verifyImageActive(image);
			
			if(!(imageActive)){
				System.out.println(image + " " + imageActive);	
			}
			x++;
		}
		System.out.println(x*2 + " assets checked");
	}
	
	@Test
	public void testAllModulePicsPGNAmericanIndians() throws ClientProtocolException, IOException{
		loginQA = new LoginPage(qa);
		JsonArray response = loginQA.getArticlesFromModule(10);
		int x = 0;
		
		for(JsonElement j : response){
			String image = j.getAsJsonObject().get("main_image").getAsString();

			Boolean imageActive = loginQA.verifyImageActive(image);
			
			if(!(imageActive)){
				System.out.println(image + " " + imageActive);	
			}
			x++;
		}
		System.out.println(x + " assets checked");
	}
	
	@Test
	public void testAllModulePicsPGNSocialStudies() throws ClientProtocolException, IOException{
		loginQA = new LoginPage(qa);
		JsonArray response = loginQA.getArticlesFromModule(11);
		int x = 0;
		
		for(JsonElement j : response){
			String image = j.getAsJsonObject().get("main_image").getAsString();

			Boolean imageActive = loginQA.verifyImageActive(image);
			
			if(!(imageActive)){
				System.out.println(image + " " + imageActive);	
			}
			x++;
		}
		System.out.println(x + " assets checked");
	}
	
	@Test
	public void testVerifyPGNContentStates() throws IOException{
		LoginPage login = new LoginPage(qa, "qa");
		FileWriter output = new FileWriter("statestestForDemo.csv");
		
		content = new StringBuilder();
		content.append("Article,");
		content.append("Error,");
		content.append("File, \n");

		output.write(content.toString());
		output.flush();
		
		content = new StringBuilder();
		
		JsonArray response = login.getArticlesFromModule(6);
		
		for(JsonElement r : response){
			content = new StringBuilder();
			
			String articleName = r.getAsJsonObject().get("title").getAsString();
			String articleID = r.getAsJsonObject().get("article_id").getAsString();
		
			JsonArray docs = r.getAsJsonObject().get("article_documents").getAsJsonArray();
		
			for(JsonElement d : docs){
				if(d.getAsJsonObject().get("filename").isJsonNull()){
					content.append(articleName + ",");
					content.append("Activity File,");
					content.append(articleID + " is null, \n");
				}
				else{
					String file = d.getAsJsonObject().get("filename").getAsString();
					System.out.println(file);
					if(!login.verifyPDFActive(file)){
						content.append(articleName + ",");
						content.append("Activity File,");
						content.append(file + ", \n");
					}
				}
				
				if(d.getAsJsonObject().get("thumbnail").isJsonNull()){
					content.append(articleName + ",");
					content.append("Activity Image,");
					content.append(articleID + " is null, \n");
				}
				else{
					String thumbnail = d.getAsJsonObject().get("thumbnail").getAsString();
					System.out.println(thumbnail);
					
					if(!login.verifyImageActive(thumbnail)){
						content.append(articleName + ",");
						content.append("Activity Image,");
						content.append(thumbnail + ", \n");
					}
				}			

			}
		
			JsonArray facts = r.getAsJsonObject().get("article_facts").getAsJsonArray();
		
			for(JsonElement f : facts){
				if(f.getAsJsonObject().get("audio_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Fact Audio File,");
					content.append("Has a null file, \n");	
				}
				else{
					String audio = f.getAsJsonObject().get("audio_file").getAsString();
					System.out.println(audio);
					
					if(!login.verifyAudioActive(audio)){
						content.append(articleName + ",");
						content.append("Fact Audio File,");
						content.append(audio + ", \n");				
					}
				}
				
				if(f.getAsJsonObject().get("time_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Fact Time File,");
					content.append("Has a null File, \n");
				}
				else{
					String time = f.getAsJsonObject().get("time_file").getAsString();
					System.out.println(time);
					
					if(!login.verifyXMLActive(time)){
						content.append(articleName + ",");
						content.append("Fact Time File,");
						content.append(time + ", \n");
					}
				}			

			}
		
			JsonArray landmarks = r.getAsJsonObject().get("article_landmarks").getAsJsonArray();
		
			for(JsonElement l : landmarks){
				String id = l.getAsJsonObject().get("article_id").getAsString();
				
				if(l.getAsJsonObject().get("audio_file").isJsonNull()){	
					content.append(articleName + ",");
					content.append("Audio File,");
					content.append(id + " is null, \n");
				}
				else{
					String audio = l.getAsJsonObject().get("audio_file").getAsString();
					System.out.println(audio);			
					if(!login.verifyAudioActive(audio)){
						content.append(articleName + ",");
						content.append("Landmark Audio File,");
						content.append(audio + ", \n");
					}
				}

				if(l.getAsJsonObject().get("time_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Landmark Time File,");
					content.append(id + " is null, \n");
				}
				else{
					String time = l.getAsJsonObject().get("time_file").getAsString();
					System.out.println(time);
					if(!login.verifyXMLActive(time)){
						content.append(articleName + ",");
						content.append("Landmark Time File,");
						content.append(time + ", \n");
					}
				}
			}
			
			JsonArray persons = r.getAsJsonObject().get("article_persons_by_active").getAsJsonArray();
			for(JsonElement p : persons){
				if(p.getAsJsonObject().get("audio_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Persons Audio File,");
					content.append("Null, \n");
				}
				else{
					String audio = p.getAsJsonObject().get("audio_file").getAsString();
					System.out.println(audio);			
					if(!login.verifyAudioActive(audio)){
						content.append(articleName + ",");
						content.append("Persons Audio File,");
						content.append(audio + ", \n");
					}
				}
				
				if(p.getAsJsonObject().get("time_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Persons Time File,");
					content.append("Null, \n");
				}
				else{
					String time = p.getAsJsonObject().get("time_file").getAsString();
					System.out.println(time);
					if(!login.verifyXMLActive(time)){
						content.append(articleName + ",");
						content.append("Persons Time File,");
						content.append(time + ", \n");
					}
				}
				
				if(p.getAsJsonObject().get("image").isJsonNull()){
					content.append(articleName + ",");
					content.append("Persons Image File,");
					content.append("Null, \n");
				}
				else{
					String image = p.getAsJsonObject().get("image").getAsString();
					System.out.println(image);
					if(!login.verifyImageActive(image)){
						content.append(articleName + ",");
						content.append("Persons Image File,");
						content.append(image + ", \n");
					}
				}
			}
			
			JsonArray recipes = r.getAsJsonObject().get("article_recipes").getAsJsonArray();
		
			for(JsonElement rec : recipes){		
				if(rec.getAsJsonObject().get("image").isJsonNull()){
					content.append(articleName + ",");
					content.append("Recipe Image File,");
					content.append("is null, \n");
				}
				else{
					String image = rec.getAsJsonObject().get("image").getAsString();
					System.out.println(image);
					if(!login.verifyImageActive(image)){
						content.append(articleName + ",");
						content.append("Recipe Image File,");
						content.append(image + ", \n");
					}
				}
			}
		
			JsonArray screens = r.getAsJsonObject().get("screens_enabled").getAsJsonArray();
		
			for(JsonElement s : screens){
				if(!(s.getAsJsonObject().get("image").isJsonNull() && s.getAsJsonObject().get("audio_file").isJsonNull() && s.getAsJsonObject().get("time_file").isJsonNull())){
					

				if(s.getAsJsonObject().get("audio_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Screen Audio File,");
					content.append("Null, \n");
				}
				else{
					String audio = s.getAsJsonObject().get("audio_file").getAsString();
					System.out.println(audio);
					
					if(!login.verifyAudioActive(audio)){
						content.append(articleName + ",");
						content.append("Screen Audio File,");
						content.append(audio + ", \n");
					}
				}
					
				if(s.getAsJsonObject().get("time_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Screen Time File,");
					content.append("Null, \n");
				}
				else{
					String time = s.getAsJsonObject().get("time_file").getAsString();
					System.out.println(time);
					
					if(!login.verifyXMLActive(time)){
						content.append(articleName + ",");
						content.append("Screen Time File,");
						content.append(time + ", \n");
					}
				}
							
				JsonArray screenImages = s.getAsJsonObject().get("screen_images").getAsJsonArray();
				for(JsonElement sI : screenImages){
					if(sI.getAsJsonObject().get("image").isJsonNull()){
						content.append(articleName + ",");
						content.append("Screen Image File,");
						content.append("Null, \n");
					}
					else{
						String image = sI.getAsJsonObject().get("image").getAsString();
						System.out.println(image);
					
						if(!login.verifyImageActive(image)){
							content.append(articleName + ",");
							content.append("Screen Image File,");
							content.append(image + ", \n");
						}
					}

				}
			}
			}
		
			JsonArray symbols = r.getAsJsonObject().get("article_symbols").getAsJsonArray();
		
			for(JsonElement s : symbols){
				if(s.getAsJsonObject().get("audio_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Symbol Audio File,");
					content.append("Null, \n");
				}
				else{
					String audio = s.getAsJsonObject().get("audio_file").getAsString();
					System.out.println(audio);
					if(!login.verifyAudioActive(audio)){
						content.append(articleName + ",");
						content.append("Symbol Audio File,");
						content.append(audio + ", \n");
					}
				}
				
				if(s.getAsJsonObject().get("time_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Symbol Time File,");
					content.append("Null, \n");
				}
				else{
					String time = s.getAsJsonObject().get("time_file").getAsString();
					System.out.println(time);
					
					if(!login.verifyXMLActive(time)){
						content.append(articleName + ",");
						content.append("Symbol Time File,");
						content.append(time + ", \n");
					}
				}

				if(s.getAsJsonObject().get("image").isJsonNull()){
					content.append(articleName + ",");
					content.append("Symbol Image File,");
					content.append("Null, \n");
				}
				else{
					String image = s.getAsJsonObject().get("image").getAsString();
					System.out.println(image);
					
					if(!login.verifyImageActive(image)){
						content.append(articleName + ",");
						content.append("Symbol Image File,");
						content.append(image + ", \n");
					}
				}
			}
		
			JsonArray timelines = r.getAsJsonObject().get("article_timelines_sorted").getAsJsonArray();
		
			for(JsonElement t : timelines){
				if(t.getAsJsonObject().get("audio_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Timeline Audio File,");
					content.append(t.getAsJsonObject().get("timeline_id").getAsString() + " is null, \n");
				}
				else{
					String audio = t.getAsJsonObject().get("audio_file").getAsString();
					System.out.println(audio);
					
					if(!login.verifyAudioActive(audio)){
						content.append(articleName + ",");
						content.append("Timeline Audio File,");
						content.append(audio + ", \n");
					}
				}
					
				if(t.getAsJsonObject().get("timecode_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Timeline Audio File,");
					content.append(t.getAsJsonObject().get("timeline_id").getAsString() + " is null, \n");
				}
				else{
					String time = t.getAsJsonObject().get("timecode_file").getAsString();
					System.out.println(time);
							
					if(!login.verifyXMLActive(time)){
						content.append(articleName + ",");
						content.append("Timline Time File,");
						content.append(time + ", \n");
					}
				}
			}
		
		
			JsonArray videos = r.getAsJsonObject().get("videos").getAsJsonArray();
			for(JsonElement v : videos){
				if(v.getAsJsonObject().get("video").isJsonNull()){
					content.append(articleName + ",");
					content.append("Video File File,");
					content.append("Null, \n");
				}
				else{
					String video = v.getAsJsonObject().get("video").getAsString();
					System.out.println(video);
				
					if(!login.verifyVideoActive(video)){
						content.append(articleName + ",");
						content.append("Video File File,");
						content.append(video + ", \n");
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
	public void testPGNSocialStudiesModule() throws IOException{
		LoginPage login = new LoginPage(qa, "qa");
		FileWriter output = new FileWriter("socialstudiesAssets.csv");
		
		content = new StringBuilder();
		content.append("Article,");
		content.append("Error,");
		content.append("File, \n");
		
		output.write(content.toString());
		output.flush();
		
		content = new StringBuilder();
		JsonArray response = login.getArticlesFromModule(11);
		
		for(JsonElement r : response){
			content = new StringBuilder();
			
			String articleName = r.getAsJsonObject().get("title").getAsString();
			String articleID = r.getAsJsonObject().get("article_id").getAsString();
			
			
			JsonArray docs = r.getAsJsonObject().get("article_documents").getAsJsonArray();
			
			for(JsonElement d : docs){
				if(d.getAsJsonObject().get("filename").isJsonNull()){
					content.append(articleName + ",");
					content.append("Activity File,");
					content.append(articleID + " is null, \n");
				}
				else{
					String file = d.getAsJsonObject().get("filename").getAsString();
					System.out.println(file);
					if(!login.verifyPDFActive(file)){
						content.append(articleName + ",");
						content.append("Activity File,");
						content.append(file + ", \n");
					}
				}
				
				if(d.getAsJsonObject().get("thumbnail").isJsonNull()){
					content.append(articleName + ",");
					content.append("Activity Image,");
					content.append(articleID + " is null, \n");
				}
				else{
					String thumbnail = d.getAsJsonObject().get("thumbnail").getAsString();
					System.out.println(thumbnail);
					
					if(!login.verifyImageActive(thumbnail)){
						content.append(articleName + ",");
						content.append("Activity Image,");
						content.append(thumbnail + ", \n");
					}
				}			
			}
			
			JsonArray screens = r.getAsJsonObject().get("screens_enabled").getAsJsonArray();

			
			if(screens.size() > 0){		
				for(JsonElement s : screens){
					if(!(s.getAsJsonObject().get("image").getAsString().isEmpty() 
							&& s.getAsJsonObject().get("audio_file").getAsString().isEmpty() 
							&& s.getAsJsonObject().get("time_file").getAsString().isEmpty())){
						

					if(s.getAsJsonObject().get("audio_file").isJsonNull()){
						content.append(articleName + ",");
						content.append("Screen Audio File,");
						content.append("Null, \n");
					}
					else{
						String audio = s.getAsJsonObject().get("audio_file").getAsString();
						System.out.println(audio);
						
						if(!login.verifyAudioActive(audio)){
							content.append(articleName + ",");
							content.append("Screen Audio File,");
							content.append(audio + ", \n");
						}
					}
						
					if(s.getAsJsonObject().get("time_file").isJsonNull()){
						content.append(articleName + ",");
						content.append("Screen Time File,");
						content.append("Null, \n");
					}
					else{
						String time = s.getAsJsonObject().get("time_file").getAsString();
						System.out.println(time);
						
						if(!login.verifyXMLActive(time)){
							content.append(articleName + ",");
							content.append("Screen Time File,");
							content.append(time + ", \n");
						}
					}
								
					JsonArray screenImages = s.getAsJsonObject().get("screen_images").getAsJsonArray();
						for(JsonElement sI : screenImages){
							if(sI.getAsJsonObject().get("image").isJsonNull()){
								content.append(articleName + ",");
								content.append("Screen Image File,");
								content.append("Null, \n");
							}
							else{
								String image = sI.getAsJsonObject().get("image").getAsString();
								System.out.println(image);
						
								if(!login.verifyImageActive(image)){
									content.append(articleName + ",");
									content.append("Screen Image File,");
									content.append(image + ", \n");
								}
							}
						
						}
					}
				}
				
				JsonArray timelines = r.getAsJsonObject().get("article_timelines_sorted").getAsJsonArray();
				
				for(JsonElement t : timelines){
					if(t.getAsJsonObject().get("audio_file").isJsonNull()){
						content.append(articleName + ",");
						content.append("Timeline Audio File,");
						content.append(t.getAsJsonObject().get("timeline_id").getAsString() + " is null, \n");
					}
					else{
						String audio = t.getAsJsonObject().get("audio_file").getAsString();
						System.out.println(audio);
						
						if(!login.verifyAudioActive(audio)){
							content.append(articleName + ",");
							content.append("Timeline Audio File,");
							content.append(audio + ", \n");
						}
					}
						
					if(t.getAsJsonObject().get("timecode_file").isJsonNull()){
						content.append(articleName + ",");
						content.append("Timeline Audio File,");
						content.append(t.getAsJsonObject().get("timeline_id").getAsString() + " is null, \n");
					}
					else{
						String time = t.getAsJsonObject().get("timecode_file").getAsString();
						System.out.println(time);
								
						if(!login.verifyXMLActive(time)){
							content.append(articleName + ",");
							content.append("Timline Time File,");
							content.append(time + ", \n");
						}
					}
				}
				
				JsonArray videos = r.getAsJsonObject().get("videos").getAsJsonArray();
				for(JsonElement v : videos){
					if(v.getAsJsonObject().get("video").isJsonNull()){
						content.append(articleName + ",");
						content.append("Video File File,");
						content.append("Null, \n");
					}
					else{
						String video = v.getAsJsonObject().get("video").getAsString();
						System.out.println(video);
						
						if(!login.verifyVideoActive(video)){
							content.append(articleName + ",");
							content.append("Video File File,");
							content.append(video + ", \n");
						}
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
	public void testPGNScienceModule() throws IOException{
		LoginPage login = new LoginPage(qa, "qa");
		FileWriter output = new FileWriter("scienceAssets.csv");
		
		content = new StringBuilder();
		content.append("Article,");
		content.append("Error,");
		content.append("File, \n");
		
		output.write(content.toString());
		output.flush();
		
		content = new StringBuilder();
		
		JsonArray response = login.getArticlesFromModule(9);
		
		for(JsonElement r : response){
			content = new StringBuilder();
			
			int articleNumber = r.getAsJsonObject().get("article_id").getAsInt();
			String articleName = r.getAsJsonObject().get("title").getAsString().toLowerCase();
			
			JsonArray docs = r.getAsJsonObject().get("article_documents").getAsJsonArray();
			
			for(JsonElement d : docs){
				if(d.getAsJsonObject().get("filename").isJsonNull()){
					content.append(articleName + ",");
					content.append("Activity File,");
					content.append(articleNumber + " is null, \n");
				}
				else{
					String file = d.getAsJsonObject().get("filename").getAsString();
					System.out.println(file);
					if(!login.verifyPDFActive(file)){
						content.append(articleName + ",");
						content.append("Activity File,");
						content.append(file + ", \n");
					}
				}
				
				if(d.getAsJsonObject().get("thumbnail").isJsonNull()){
					content.append(articleName + ",");
					content.append("Activity Image,");
					content.append(articleNumber + " is null, \n");
				}
				else{
					String thumbnail = d.getAsJsonObject().get("thumbnail").getAsString();
					System.out.println(thumbnail);
					
					if(!login.verifyImageActive(thumbnail)){
						content.append(articleName + ",");
						content.append("Activity Image,");
						content.append(thumbnail + ", \n");
					}
				}			
			}
			
			JsonArray persons = r.getAsJsonObject().get("article_persons_by_active").getAsJsonArray();
			for(JsonElement p : persons){
				if(p.getAsJsonObject().get("audio_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Persons Audio File,");
					content.append("Null, \n");
				}
				else{
					String audio = p.getAsJsonObject().get("audio_file").getAsString();
					System.out.println(audio);			
					if(!login.verifyAudioActive(audio)){
						content.append(articleName + ",");
						content.append("Persons Audio File,");
						content.append(audio + ", \n");
					}
				}
				
				if(p.getAsJsonObject().get("time_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Persons Time File,");
					content.append("Null, \n");
				}
				else{
					String time = p.getAsJsonObject().get("time_file").getAsString();
					System.out.println(time);
					if(!login.verifyXMLActive(time)){
						content.append(articleName + ",");
						content.append("Persons Time File,");
						content.append(time + ", \n");
					}
				}
				
				if(p.getAsJsonObject().get("image").isJsonNull()){
					content.append(articleName + ",");
					content.append("Persons Image File,");
					content.append("Null, \n");
				}
				else{
					String image = p.getAsJsonObject().get("image").getAsString();
					System.out.println(image);
					if(!login.verifyImageActive(image)){
						content.append(articleName + ",");
						content.append("Persons Image File,");
						content.append(image + ", \n");
					}
				}
			}
			
			JsonArray screens = r.getAsJsonObject().get("screens_enabled").getAsJsonArray();
			if(screens.size() > 0){		
				for(JsonElement s : screens){
					if(!(s.getAsJsonObject().get("image").getAsString().isEmpty() 
							&& s.getAsJsonObject().get("audio_file").getAsString().isEmpty() 
							&& s.getAsJsonObject().get("time_file").getAsString().isEmpty())){
						

					if(s.getAsJsonObject().get("audio_file").isJsonNull()){
						content.append(articleName + ",");
						content.append("Screen Audio File,");
						content.append("Null, \n");
					}
					else{
						String audio = s.getAsJsonObject().get("audio_file").getAsString();
						System.out.println(audio);
						
						if(!login.verifyAudioActive(audio)){
							content.append(articleName + ",");
							content.append("Screen Audio File,");
							content.append(audio + ", \n");
						}
					}
						
					if(s.getAsJsonObject().get("time_file").isJsonNull()){
						content.append(articleName + ",");
						content.append("Screen Time File,");
						content.append("Null, \n");
					}
					else{
						String time = s.getAsJsonObject().get("time_file").getAsString();
						System.out.println(time);
						
						if(!login.verifyXMLActive(time)){
							content.append(articleName + ",");
							content.append("Screen Time File,");
							content.append(time + ", \n");
						}
					}
								
					JsonArray screenImages = s.getAsJsonObject().get("screen_images").getAsJsonArray();
						for(JsonElement sI : screenImages){
							if(sI.getAsJsonObject().get("image").isJsonNull()){
								content.append(articleName + ",");
								content.append("Screen Image File,");
								content.append("Null, \n");
							}
							else{
								String image = sI.getAsJsonObject().get("image").getAsString();
								System.out.println(image);
						
								if(!login.verifyImageActive(image)){
									content.append(articleName + ",");
									content.append("Screen Image File,");
									content.append(image + ", \n");
								}
							}
						
						}
					}
				}
			}
			
			JsonArray videos = r.getAsJsonObject().get("videos").getAsJsonArray();
			for(JsonElement v : videos){
				if(v.getAsJsonObject().get("video").isJsonNull()){
					content.append(articleName + ",");
					content.append("Video File File,");
					content.append("Null, \n");
				}
				else{
					String video = v.getAsJsonObject().get("video").getAsString();
					System.out.println(video);
				
					if(!login.verifyVideoActive(video)){
						content.append(articleName + ",");
						content.append("Video File File,");
						content.append(video + ", \n");
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
	public void testPGNAmericanIndians() throws IOException{
		LoginPage login = new LoginPage(qa, "qa");
		FileWriter output = new FileWriter("AmericanIndians.csv");
		
		content = new StringBuilder();
		content.append("Article,");
		content.append("Error,");
		content.append("File, \n");

		output.write(content.toString());
		output.flush();
		
		content = new StringBuilder();
		
		JsonArray response = login.getArticlesFromModule(10);
		
		for(JsonElement r : response){
			content = new StringBuilder();
			
			String articleName = r.getAsJsonObject().get("title").getAsString();
			String articleID = r.getAsJsonObject().get("article_id").getAsString();
		
			JsonArray docs = r.getAsJsonObject().get("article_documents").getAsJsonArray();
		
			for(JsonElement d : docs){
				if(d.getAsJsonObject().get("filename").isJsonNull()){
					content.append(articleName + ",");
					content.append("Activity File,");
					content.append(articleID + " is null, \n");
				}
				else{
					String file = d.getAsJsonObject().get("filename").getAsString();
					System.out.println(file);
					if(!login.verifyPDFActive(file)){
						content.append(articleName + ",");
						content.append("Activity File,");
						content.append(file + ", \n");
					}
				}
				
				if(d.getAsJsonObject().get("thumbnail").isJsonNull()){
					content.append(articleName + ",");
					content.append("Activity Image,");
					content.append(articleID + " is null, \n");
				}
				else{
					String thumbnail = d.getAsJsonObject().get("thumbnail").getAsString();
					System.out.println(thumbnail);
					
					if(!login.verifyImageActive(thumbnail)){
						content.append(articleName + ",");
						content.append("Activity Image,");
						content.append(thumbnail + ", \n");
					}
				}			

			}
			
			JsonArray persons = r.getAsJsonObject().get("article_persons_by_active").getAsJsonArray();
			for(JsonElement p : persons){
				if(p.getAsJsonObject().get("audio_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Persons Audio File,");
					content.append("Null, \n");
				}
				else{
					String audio = p.getAsJsonObject().get("audio_file").getAsString();
					System.out.println(audio);			
					if(!login.verifyAudioActive(audio)){
						content.append(articleName + ",");
						content.append("Persons Audio File,");
						content.append(audio + ", \n");
					}
				}
				
				if(p.getAsJsonObject().get("time_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Persons Time File,");
					content.append("Null, \n");
				}
				else{
					String time = p.getAsJsonObject().get("time_file").getAsString();
					System.out.println(time);
					if(!login.verifyXMLActive(time)){
						content.append(articleName + ",");
						content.append("Persons Time File,");
						content.append(time + ", \n");
					}
				}
				
				if(p.getAsJsonObject().get("image").isJsonNull()){
					content.append(articleName + ",");
					content.append("Persons Image File,");
					content.append("Null, \n");
				}
				else{
					String image = p.getAsJsonObject().get("image").getAsString();
					System.out.println(image);
					if(!login.verifyImageActive(image)){
						content.append(articleName + ",");
						content.append("Persons Image File,");
						content.append(image + ", \n");
					}
				}
			}
		
			JsonArray screens = r.getAsJsonObject().get("screens_enabled").getAsJsonArray();
		
			for(JsonElement s : screens){
				if(!(s.getAsJsonObject().get("image").isJsonNull() && s.getAsJsonObject().get("audio_file").isJsonNull() && s.getAsJsonObject().get("time_file").isJsonNull())){
					

				if(s.getAsJsonObject().get("audio_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Screen Audio File,");
					content.append("Null, \n");
				}
				else{
					String audio = s.getAsJsonObject().get("audio_file").getAsString();
					System.out.println(audio);
					
					if(!login.verifyAudioActive(audio)){
						content.append(articleName + ",");
						content.append("Screen Audio File,");
						content.append(audio + ", \n");
					}
				}
					
				if(s.getAsJsonObject().get("time_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Screen Time File,");
					content.append("Null, \n");
				}
				else{
					String time = s.getAsJsonObject().get("time_file").getAsString();
					System.out.println(time);
					
					if(!login.verifyXMLActive(time)){
						content.append(articleName + ",");
						content.append("Screen Time File,");
						content.append(time + ", \n");
					}
				}
							
				JsonArray screenImages = s.getAsJsonObject().get("screen_images").getAsJsonArray();
					for(JsonElement sI : screenImages){
						if(sI.getAsJsonObject().get("image").isJsonNull()){
							content.append(articleName + ",");
							content.append("Screen Image File,");
							content.append("Null, \n");
						}
						else{
							String image = sI.getAsJsonObject().get("image").getAsString();
							System.out.println(image);
					
							if(!login.verifyImageActive(image)){
								content.append(articleName + ",");
								content.append("Screen Image File,");
								content.append(image + ", \n");
							}
						}
					}
				}
			}
		
			JsonArray timelines = r.getAsJsonObject().get("article_timelines_sorted").getAsJsonArray();
		
			for(JsonElement t : timelines){
				if(t.getAsJsonObject().get("audio_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Timeline Audio File,");
					content.append(t.getAsJsonObject().get("timeline_id").getAsString() + " is null, \n");
				}
				else{
					String audio = t.getAsJsonObject().get("audio_file").getAsString();
					System.out.println(audio);
					
					if(!login.verifyAudioActive(audio)){
						content.append(articleName + ",");
						content.append("Timeline Audio File,");
						content.append(audio + ", \n");
					}
				}
					
				if(t.getAsJsonObject().get("timecode_file").isJsonNull()){
					content.append(articleName + ",");
					content.append("Timeline Audio File,");
					content.append(t.getAsJsonObject().get("timeline_id").getAsString() + " is null, \n");
				}
				else{
					String time = t.getAsJsonObject().get("timecode_file").getAsString();
					System.out.println(time);
							
					if(!login.verifyXMLActive(time)){
						content.append(articleName + ",");
						content.append("Timline Time File,");
						content.append(time + ", \n");
					}
				}
			}
		
		
			JsonArray videos = r.getAsJsonObject().get("videos").getAsJsonArray();
			for(JsonElement v : videos){
				if(v.getAsJsonObject().get("video").isJsonNull()){
					content.append(articleName + ",");
					content.append("Video File File,");
					content.append("Null, \n");
				}
				else{
					String video = v.getAsJsonObject().get("video").getAsString();
					System.out.println(video);
				
					if(!login.verifyVideoActive(video)){
						content.append(articleName + ",");
						content.append("Video File File,");
						content.append(video + ", \n");
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
}
