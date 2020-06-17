package Tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.ParseException;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import APIControllers.ContentAPIController;
import DataClasses.ContentNode;

public class ContentAPITests {
	ContentAPIController contentAPI = new ContentAPIController();
	
	@Before
	public void executeBefore(){
		try {
			contentAPI.getContentJSON("3");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testContentAPIAppIDKeyExists(){
		assertTrue("Asserts that the app_id key exists in the data sent back by the Content API",
				contentAPI.callData.has("app_id"));
	}
	
	@Test
	public void testContentAPIAppIDDataExists(){
		assertTrue("Asserts that the app_id data exists in the data sent back by the Content API",
				contentAPI.appID.equals("3"));
	}
	
	@Test
	public void testContentAPIAppURLKeyExists(){
		assertTrue("Asserts that the app_url key exists in the data sent back by the Content API",
				contentAPI.callData.has("app_url"));
	}
	
	@Test
	public void testContentAPIAppURLDataExists(){
		assertTrue("Asserts that the app_url data exists in the data sent back by the Content API",
				contentAPI.appURL.equals("www.pebblegonext.com"));
	}
	
	@Test
	public void testContentAPIAppNameKeyExists(){
		assertTrue("Asserts that the app_name key exists in the data sent back by the Content API",
				contentAPI.callData.has("app_name"));
	}
	
	@Test
	public void testContentAPIAppNameDataExists(){
		assertTrue("Asserts that the app_name data exists in the data sent back by the Content API",
				contentAPI.appName.equals("Pebble Go Next"));
	}
	
	@Test
	public void testContentAPIModuleKeyExists(){
		assertTrue("Asserts that the module key exists in the data sent back by the Content API",
				contentAPI.callData.has("modules"));
	}
	
	@Test
	public void testContentAPIModuleNameKeyExists(){
		assertTrue("Asserts that the module name key exists in the data sent back by the Content API",
				contentAPI.modules.get(0).getAsJsonObject().has("name"));
	}
	
	@Test
	public void testContentAPIModuleNameDataExists(){
		assertTrue("Asserts that the module name data exists in the data sent back by the Content API",
				contentAPI.returnModuleDataByName("PebbleGo Next Science").name.equals("PebbleGo Next Science"));
	}
	
	@Test
	public void testContentAPIModuleImageKeyExists(){
		assertTrue("Asserts that the module image key exists in the data sent back by the Content API",
				contentAPI.modules.get(0).getAsJsonObject().has("image"));
	}
	
	@Test
	public void testContentAPIModuleImageDataExists(){
		assertTrue("Asserts that the module image data exists in the data sent back by the Content API",
				contentAPI.returnModuleDataByName("PebbleGo Next Science").image.equals("pgnscience_main.jpg"));
	}
	
	@Test
	public void testContentAPIModuleImageIsValid(){
		assertTrue("Asserts that the module image exists in the data sent back by the Content API and is valid",
				contentAPI.verifyImageActive(contentAPI.modules.get(0).getAsJsonObject().get("image").getAsString()));
	}
	
	@Test
	public void testContentAPIModuleChildrenKeyExists(){
		assertTrue("Asserts that the module children key exists in the data sent back by the Content API",
				contentAPI.modules.get(0).getAsJsonObject().has("children"));
	}
	
	@Test
	public void testContentAPIModuleChildrenDataExists(){
		ArrayList<ContentNode> test = contentAPI.returnModuleDataByName("PebbleGo Next Science").children;
		
		assertTrue("Asserts that the module children data exists in the data sent back by the Content API",
				contentAPI.returnModuleDataByName("PebbleGo Next Science").children.get(0).title.equals("Earth Science") &&
				contentAPI.returnModuleDataByName("PebbleGo Next Science").children.get(1).title.equals("Life Science") &&
				contentAPI.returnModuleDataByName("PebbleGo Next Science").children.get(2).title.equals("Physical Science") &&
				contentAPI.returnModuleDataByName("PebbleGo Next Science").children.get(3).title.equals("The Field of Science"));
	}
	
	@Test
	public void testContentAPILanguageTextKeyExists(){
		assertTrue("Asserts that the module lang_txt key exists in the module data sent back by the Content API",
				contentAPI.modules.get(0).getAsJsonObject().has("lang_txt"));
	}
	
	@Test
	public void testContentAPILanguageDataKeyExists(){
		assertTrue("Asserts that the module lang_txt data exists in the module data sent back by the Content API",
				contentAPI.moduleData.get(1).langTxt.equals("empty"));
	}
	
	@Test
	public void testContentAPIModuleIDKeyExists(){
		assertTrue("Asserts that the module id key exists in the module data sent back by the Content API",
				contentAPI.modules.get(0).getAsJsonObject().has("module_id"));
	}
	
	@Test
	public void testContentAPIModuleIDDataExists(){
		assertTrue("Asserts that the module id data exists in the module data sent back by the Content API",
				contentAPI.returnModuleDataByName("PebbleGo Next Science").moduleID.equals("9"));
	}
	
	@Test
	public void testContentAPINameShortKeyExists(){
		assertTrue("Asserts that the name short key exists in the module data sent back by the Content API",
				contentAPI.modules.get(0).getAsJsonObject().has("name_short"));
	}
	
	@Test
	public void testContentAPINameShortDataExists(){
		assertTrue("Asserts that the name short key exists in the module data sent back by the Content API",
				contentAPI.returnModuleDataByName("PebbleGo Next Science").nameShort.equals("Science"));
	}
	
	@Test
	public void testContentAPILanguageIDKeyExists(){
		assertTrue("Asserts that the language id key exists in the module data sent back by the Content API",
				contentAPI.modules.get(0).getAsJsonObject().has("language_id"));
	}
	
	@Test
	public void testContentAPILanguageIDDataExists(){
		assertTrue("Asserts that the language id data exists in the module data sent back by the Content API",
				contentAPI.returnModuleDataByName("PebbleGo Next Science").languageID.equals("1"));
	}
	
	@Test
	public void testContentAPIEnglishModuleIDKeyExists(){
		assertTrue("Asserts that the english module id key exists in the module data sent back by the Content API",
				contentAPI.modules.get(0).getAsJsonObject().has("english_module_id"));
	}
	
	@Test
	public void testContentAPIEnglishModuleIDDataExists(){
		assertTrue("Asserts that the english module id key exists in the module data sent back by the Content API",
				contentAPI.returnModuleDataByName("PebbleGo Next Science").englishModuleID.equals("9"));
	}
	
	@Test
	public void testContentAPIVideoExperimentsKeyExists(){
		assertTrue("Asserts that the video experiments key exists in the module data sent back by the Content API",
				contentAPI.modules.get(0).getAsJsonObject().has("video_experiments"));
	}
	
	@Test
	public void testContentAPIVideoExperimentsImageKeyExists(){
		assertTrue("Asserts that the experiment image key exists in the module data sent back by the Content API",
				contentAPI.modules.get(1).getAsJsonObject().get("video_experiments").getAsJsonArray().get(0).getAsJsonObject().has("image"));
	}
	
	@Test
	public void testContentAPIVideoExperimentsImageDataExists(){
		assertTrue("Asserts that video experiments image dada key exists in the module data sent back by the Content API",
				contentAPI.returnModuleDataByName("PebbleGo Next Science").videos.get(0).image.equals("pgnsciencevolcano_image.png"));
	}
	
	@Test
	public void testContentAPIVideoExperimentsImageIsValid(){
		assertTrue("Asserts that video experiments image dada is valid and exists in the module data sent back by the Content API",
				contentAPI.verifyImageActive(contentAPI.moduleData.get(1).videos.get(0).image));
	}
	
	@Test
	public void testContentAPIVideoExperimentsTitleKeyExists(){
		assertTrue("Asserts that the experiment title key exists in the module data sent back by the Content API",
				contentAPI.modules.get(1).getAsJsonObject().get("video_experiments").getAsJsonArray().get(0).getAsJsonObject().has("title"));
	}
	
	@Test
	public void testContentAPIVideoExperimentsTitleDataExists(){
		assertTrue("Asserts that experiments title data exists in the module data sent back by the Content API",
				contentAPI.returnModuleDataByName("PebbleGo Next Science").videos.get(0).title.equals("Volcano"));
	}
	
	@Test
	public void testContentAPIVideoExperimentsVideoKeyExists(){
		assertTrue("Asserts that the experiment video key exists in the module data sent back by the Content API",
				contentAPI.modules.get(1).getAsJsonObject().get("video_experiments").getAsJsonArray().get(0).getAsJsonObject().has("video"));
	}
	
	@Test
	public void testContentAPIVideoExperimentsVideoDataExists(){
		assertTrue("Asserts that experiments video data exists in the module data sent back by the Content API",
				contentAPI.returnModuleDataByName("PebbleGo Next Science").videos.get(0).video.equals("pgnsciencevolcano_experiment_video_volcano.mp4"));
	}
	
	@Test
	public void testContentAPIVideoExperimentsVideoIsValid(){
		assertTrue("Asserts that experiments video data is valid and exists in the module data sent back by the Content API",
				contentAPI.verifyVideoActive(contentAPI.moduleData.get(1).videos.get(0).video));
	}
	
	@Test
	public void testContentAPIVideoExperimentsCreatedKeyExists(){
		assertTrue("Asserts that the experiment created key exists in the module data sent back by the Content API",
				contentAPI.modules.get(1).getAsJsonObject().get("video_experiments").getAsJsonArray().get(0).getAsJsonObject().has("created"));
	}
	
	@Test
	public void testContentAPIVideoExperimentsCreatedDataExists(){
		assertTrue("Asserts that experiments created data exists in the module data sent back by the Content API",
				contentAPI.returnModuleDataByName("PebbleGo Next Science").videos.get(0).created.equals("2016-07-07T20:07:02.000Z"));
	}
	
	@Test
	public void testContentAPIVideoExperimentsTranscriptKeyExists(){
		assertTrue("Asserts that the experiment transcript key exists in the module data sent back by the Content API",
				contentAPI.modules.get(1).getAsJsonObject().get("video_experiments").getAsJsonArray().get(0).getAsJsonObject().has("transcript"));
	}
	
	@Test
	public void testContentAPIVideoExperimentsTranscriptDataExists(){
		assertTrue("Asserts that experiments transcript data exists in the module data sent back by the Content API",
				contentAPI.returnModuleDataByName("PebbleGo Next Science").videos.get(0).transcript.equals("pgnsciencevolcano_transcript.pdf"));
	}
	
	@Test
	public void testContentAPIVideoExpermentsTranscriptDataIsValid(){
		assertTrue("Asserts that experiments transcript data is valid and exists in the module data sent back by the Content API",
				contentAPI.verifyPDFActive(contentAPI.moduleData.get(1).videos.get(0).transcript));
	}
	
	@Test
	public void testContentAPIVideoExperimentsIDKeyExists(){
		assertTrue("Asserts that the experiment transcript key exists in the module data sent back by the Content API",
				contentAPI.modules.get(1).getAsJsonObject().get("video_experiments").getAsJsonArray().get(0).getAsJsonObject().has("video_experiment_id"));
	}
	
	@Test
	public void testContentAPIVideoExpermentsIDDataExists(){
		assertTrue("Asserts that experiments transcript data is valid and exists in the module data sent back by the Content API",
				contentAPI.returnModuleDataByName("PebbleGo Next Science").videos.get(0).videoExperimentID.equals("1"));
	}
	
	@Test
	public void testContentAPIChildrenTypeKeyExists(){
		assertTrue("Asserts that the type key exists in the children data sent back by the Content API",
				contentAPI.modules.get(0).getAsJsonObject().get("children").getAsJsonArray().get(0).getAsJsonObject().has("type"));
	}
	
	@Test
	public void testContentAPIChildrenAudioKeyExists(){
		assertTrue("Asserts that the audio key exists in the children data sent back by the Content API",
				contentAPI.modules.get(0).getAsJsonObject().get("children").getAsJsonArray().get(0).getAsJsonObject().has("audio"));
	}
	
	@Test
	public void testContentAPIChildrenAudioIsValid(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String audio = contentAPI.modules.get(0).getAsJsonObject().get("children").getAsJsonArray().get(0).getAsJsonObject().get("audio").getAsString();
		assertTrue("Asserts that the audio that exists in the children data sent back by the Content API is valid",
				contentAPI.verifyAudioActive(audio));
		
		try {
			contentAPI.getContentJSON("3");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testContentAPIChildrenImageKeyExists(){
		assertTrue("Asserts that the image key exists in the children data sent back by the Content API",
				contentAPI.modules.get(0).getAsJsonObject().get("children").getAsJsonArray().get(0).getAsJsonObject().has("image"));
	}
	
	@Test
	public void testContentAPIChildrenImageIsValid(){
		String image = contentAPI.modules.get(0).getAsJsonObject().get("children").getAsJsonArray().get(0).getAsJsonObject().get("image").getAsString();
		assertTrue("Asserts that the image that exists in the children data sent back by the Content API is valid",
				contentAPI.verifyImageActive(image));
	}
	
	@Test
	public void testContentAPIChildrenTitleKeyExists(){
		assertTrue("Asserts that the title key exists in the children data sent back by the Content API",
				contentAPI.modules.get(0).getAsJsonObject().get("children").getAsJsonArray().get(0).getAsJsonObject().has("title"));
	}
	
	@Test
	public void testContentAPIChildrenChildrenKeyExists(){
		assertTrue("Asserts that the children key exists in the children data sent back by the Content API",
				contentAPI.modules.get(0).getAsJsonObject().get("children").getAsJsonArray().get(0).getAsJsonObject().has("children"));
	}
	
	@Test
	public void testContentAPIChildrenArticleIDKeyExists(){
		assertTrue("Asserts that the children id key exists in the children data sent back by the Content API",
				contentAPI.modules.get(0).getAsJsonObject().get("children").getAsJsonArray().get(0).getAsJsonObject().has("article_id"));
	}
	
	@Test
	public void testContentAPIArticleDoesntHaveChildrenIDKey(){
		JsonArray children = contentAPI.modules.get(0).getAsJsonObject().get("children").getAsJsonArray().get(0).getAsJsonObject().get("children").getAsJsonArray();
		JsonObject article = children.get(0).getAsJsonObject();
		
		assertTrue("Asserts that the children id key exists in the children data sent back by the Content API",
				article.get("type").getAsString().equals("A") && !article.has("children"));
	}
	
	@Test
	public void testPGOAnimalsArticleData(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ContentNode appaloosaHorses = contentAPI.returnModuleDataByName("PebbleGo Animals").getChildByName("Pets and Farm Animals");
		appaloosaHorses = appaloosaHorses.getChildByName("Farm Animals").getChildByName("Horses").getChildByName("Appaloosa Horses");
				
				//contentAPI.moduleData.get(0).children.get(11).children.get(1).children.get(7).children.get(2);
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				appaloosaHorses.type.equals("A"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				appaloosaHorses.audio.equals("pgoanimals_appaloosahorses.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				appaloosaHorses.image.equals("pgoanimals_appaloosahorses.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				appaloosaHorses.title.equals("Appaloosa Horses"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct id", 
				appaloosaHorses.articleID.equals("234"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				appaloosaHorses.children == null);
	}
	
	@Test
	public void testPGOAnimalsCategoryData(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(contentAPI.moduleData.get(0).toString());
		
		ContentNode horses = contentAPI.returnModuleDataByName("PebbleGo Animals").getChildByName("Pets and Farm Animals").getChildByName("Farm Animals").getChildByName("Horses");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				horses.type.equals("C"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				horses.audio.equals("pgoanimals_horses.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				horses.image.equals("pgoanimals_horses.png"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				horses.title.equals("Horses"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				horses.articleID.equals("34"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				horses.getChildByName("American Quarter Horses").articleID.equals("237") &&
				horses.getChildByName("American Saddlebred Horses").articleID.equals("233") &&
				horses.getChildByName("Appaloosa Horses").articleID.equals("234") &&
				horses.getChildByName("Arabian Horses").articleID.equals("235") &&
				horses.getChildByName("Palomino Horses").articleID.equals("236") &&
				horses.getChildByName("Shetland Ponies").articleID.equals("238") &&
				horses.getChildByName("Thoroughbred Horses").articleID.equals("239"));			
	}
	
	@Test
	public void testPGOScienceArticleData(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode oceanAnimals = contentAPI.returnModuleDataByName("PebbleGo Science").getChildByName("Life Sciences").getChildByName("Animals");
		oceanAnimals = oceanAnimals.getChildByName("Animal Habitats").getChildByName("Ocean Animals");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				oceanAnimals.type.equals("A"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				oceanAnimals.audio.equals("pgoscience_oceananimals.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				oceanAnimals.image.equals("pgoscience_oceananimals.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				oceanAnimals.title.equals("Ocean Animals"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct id", 
				oceanAnimals.articleID.equals("2162"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				oceanAnimals.children == null);
	}
	
	@Test
	public void testPGOScienceCategoryData(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ContentNode habitats = contentAPI.returnModuleDataByName("PebbleGo Science").getChildByName("Life Sciences").getChildByName("Animals");
		habitats = habitats.getChildByName("Animal Habitats");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				habitats.type.equals("C"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				habitats.audio.equals("pgoscience_animalhabitats.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				habitats.image.equals("pgoscience_animalhabitats.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				habitats.title.equals("Animal Habitats"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				habitats.articleID.equals("2952"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				habitats.getChildByName("Animals Affect Habitats").articleID.equals("2159") &&
				habitats.getChildByName("Desert Animals").articleID.equals("2160") &&
				habitats.getChildByName("Grassland Animals").articleID.equals("2161") &&
				habitats.getChildByName("Ocean Animals").articleID.equals("2162") &&
				habitats.getChildByName("Polar Animals").articleID.equals("2163") &&
				habitats.getChildByName("Rain Forest Animals").articleID.equals("2164") &&
				habitats.getChildByName("Wetland Animals").articleID.equals("2165") &&
				habitats.getChildByName("Woodland Animals").articleID.equals("2166"));			
	}
	
	@Test
	public void testPGOBiographiesArticleData(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode chris = contentAPI.returnModuleDataByName("PebbleGo Biographies").getChildByName("Explorers").getChildByName("World Explorers").getChildByName("Christopher Columbus");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				chris.type.equals("A"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				chris.audio.equals("pgobiographies_christophercolumbus.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				chris.image.equals("pgobiographies_christophercolumbus.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				chris.title.equals("Christopher Columbus"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct id", 
				chris.articleID.equals("3095"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				chris.children == null);
	}
	
	@Test
	public void testPGOBiographiesCategoryData(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode explorers =contentAPI.returnModuleDataByName("PebbleGo Biographies").getChildByName("Explorers").getChildByName("World Explorers");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				explorers.type.equals("C"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				explorers.audio.equals("pgobiographies_worldexplorers.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				explorers.image.equals("pgobiographies_worldexplorers.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				explorers.title.equals("World Explorers"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				explorers.articleID.equals("3094"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				explorers.getChildByName("Jacques Cartier").articleID.equals("3097") &&
				explorers.getChildByName("Leif Eriksson").articleID.equals("3100") &&
				explorers.getChildByName("Matthew Henson").articleID.equals("3102") &&
				explorers.getChildByName("Francisco Coronado").articleID.equals("3103") &&
				explorers.getChildByName("Sieur de la Salle (René Robert Cavelier)").articleID.equals("3104") &&
				explorers.getChildByName("Christopher Columbus").articleID.equals("3095") &&
				explorers.getChildByName("Ernest Shackleton").articleID.equals("3096") &&
				explorers.getChildByName("Jacques Cousteau").articleID.equals("3098") &&
				explorers.getChildByName("Juan Ponce de León").articleID.equals("3099") &&
				explorers.getChildByName("Marco Polo").articleID.equals("3101"));			
	}
	
	@Test
	public void testPGOSocialStudiesArticleData(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode litter = contentAPI.returnModuleDataByName("PebbleGo Social Studies").getChildByName("People and the Environment").getChildByName("Helping the Environment").getChildByName("Cleaning Up Litter");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				litter.type.equals("A"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				litter.audio.equals("pgosocialstudies_cleaninguplitter.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				litter.image.equals("pgosocialstudies_cleaninguplitter.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				litter.title.equals("Cleaning Up Litter"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct id", 
				litter.articleID.equals("5172"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				litter.children == null);
	}
	
	@Test
	public void testPGOSocialStudiesCategoryData(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode environment = contentAPI.returnModuleDataByName("PebbleGo Social Studies").getChildByName("People and the Environment").getChildByName("Helping the Environment");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				environment.type.equals("C"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				environment.audio.equals("pgosocialstudies_helpingtheenvironment.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				environment.image.equals("pgosocialstudies_helpingtheenvironment.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				environment.title.equals("Helping the Environment"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				environment.articleID.equals("5021"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				environment.children.get(0).title.equals("Caring for Nature") &&
				environment.children.get(1).title.equals("Cleaning Up Litter") &&
				environment.children.get(2).title.equals("Reusing and Recycling") &&
				environment.children.get(3).title.equals("Saving Energy") &&
				environment.children.get(4).title.equals("Saving Water"));		
	}
	
	@Test
	public void testPGODinosaursArticleData(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode magyarosaurus = contentAPI.returnModuleDataByName("PebbleGo Dinosaurs").getChildByName("Long-Necks").getChildByName("Titanosaurs").getChildByName("Magyarosaurus");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				magyarosaurus.type.equals("A"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				magyarosaurus.audio.equals("pgodinosaurs_magyarosaurus.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				magyarosaurus.image.equals("pgodinosaurs_magyarosaurus.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				magyarosaurus.title.equals("Magyarosaurus"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct id", 
				magyarosaurus.articleID.equals("7137"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				magyarosaurus.children == null);
	}
	
	@Test
	public void testPGODinosaursCategoryData(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode titanosaurs = contentAPI.returnModuleDataByName("PebbleGo Dinosaurs").getChildByName("Long-Necks").getChildByName("Titanosaurs");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				titanosaurs.type.equals("C"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				titanosaurs.audio.equals("pgodinosaurs_titanosaurs.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				titanosaurs.image.equals("pgodinosaurs_titanosaurs.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				titanosaurs.title.equals("Titanosaurs"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				titanosaurs.articleID.equals("7973"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				titanosaurs.getChildByName("Ampelosaurus").articleID.equals("7134") &&
				titanosaurs.getChildByName("Argentinosaurus").articleID.equals("7135") &&
				titanosaurs.getChildByName("Futalognkosaurus").articleID.equals("7136") &&
				titanosaurs.getChildByName("Magyarosaurus").articleID.equals("7137") &&
				titanosaurs.getChildByName("Paralititan").articleID.equals("7138") &&
				titanosaurs.getChildByName("Saltasaurus").articleID.equals("7139"));		
	}
	
	@Test
	public void testPGOAnimalesArticleData(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode hippo = contentAPI.returnModuleDataByName("PebbleGo Animales").getChildByName("Mamíferos").getChildByName("Mamíferos de Humedal").getChildByName("Hipopótamos (esp)");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				hippo.type.equals("A"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				hippo.audio.equals("pgoanimales_hipoptamos.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				hippo.image.equals("pgoanimales_hipoptamos.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				hippo.title.equals("Hipopótamos (esp)"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct id", 
				hippo.articleID.equals("8187"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				hippo.children == null);
	}
	
	@Test
	public void testPGOAnimalesCategoryData(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode mamiferos = contentAPI.returnModuleDataByName("PebbleGo Animales").getChildByName("Mamíferos").getChildByName("Mamíferos de Humedal");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				mamiferos.type.equals("C"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				mamiferos.audio.equals("pgoanimales_mamferosdehumedal.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				mamiferos.image.equals("pgoanimales_mamferosdehumedal.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				mamiferos.title.equals("Mamíferos de Humedal"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				mamiferos.articleID.equals("8028"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				mamiferos.getChildByName("Carpinchos").articleID.equals("8184") &&
				mamiferos.getChildByName("Castores").articleID.equals("8183") &&
				mamiferos.getChildByName("Nutrias de Río").articleID.equals("8186") &&
				mamiferos.getChildByName("Ratas Almizcleras").articleID.equals("8185") &&
				mamiferos.getChildByName("Hipopótamos (esp)").articleID.equals("8187"));		
	}
	
	@Test
	public void testPGOCienciaArticleData(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode bacteriaESP = contentAPI.returnModuleDataByName("PebbleGo Ciencia").getChildByName("Ciencias Biológicas").getChildByName("Seres Humanos").getChildByName("Mantenerse Saludable").getChildByName("Sentirse Enfermo").getChildByName("Bacterias (esp)");
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				bacteriaESP.type.equals("A"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				bacteriaESP.audio.equals("pgociencia_bacterias.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				bacteriaESP.image.equals("pgoscience_bacteria.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				bacteriaESP.title.equals("Bacterias (esp)"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct id", 
				bacteriaESP.articleID.equals("8984"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				bacteriaESP.children == null);
	}
	
	@Test
	public void testPGOCienciaCategoryData(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode sentirseEnfermo = contentAPI.returnModuleDataByName("PebbleGo Ciencia").getChildByName("Ciencias Biológicas").getChildByName("Seres Humanos").getChildByName("Mantenerse Saludable").getChildByName("Sentirse Enfermo");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				sentirseEnfermo.type.equals("C"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				sentirseEnfermo.audio.equals("pgociencia_sentirseenfermo.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				sentirseEnfermo.image.equals("pgoscience_feelingsick.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				sentirseEnfermo.title.equals("Sentirse Enfermo"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				sentirseEnfermo.articleID.equals("8987"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				sentirseEnfermo.getChildByName("Bacterias (esp)").articleID.equals("8984") &&
				sentirseEnfermo.getChildByName("¿Qué Son los Gérmenes?").articleID.equals("9225") &&
				sentirseEnfermo.getChildByName("Virus (esp)").articleID.equals("9214"));	
	}
	
	@Test
	public void testPGOBiografiasArticleData(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode eleanorRoosevelt = contentAPI.returnModuleDataByName("PebbleGo Biografías").getChildByName("Presidentes y Primeras Damas").getChildByName("Primeras Damas").getChildByName("Eleanor Roosevelt (esp)");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				eleanorRoosevelt.type.equals("A"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				eleanorRoosevelt.audio.equals("pgobiografas_eleanorroosevelt.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				eleanorRoosevelt.image.equals("pgobiographies_eleanorroosevelt.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				eleanorRoosevelt.title.equals("Eleanor Roosevelt (esp)"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct id", 
				eleanorRoosevelt.articleID.equals("9357"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				eleanorRoosevelt.children == null);
	}
	
	@Test
	public void testPGOBiografiasCategoryData(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode PrimerasDamas = contentAPI.returnModuleDataByName("PebbleGo Biografías").getChildByName("Presidentes y Primeras Damas").getChildByName("Primeras Damas");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				PrimerasDamas.type.equals("C"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				PrimerasDamas.audio.equals("pgobiografas_primerasdamas.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				PrimerasDamas.image.equals("pgobiographies_firstladies.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				PrimerasDamas.title.equals("Primeras Damas"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				PrimerasDamas.articleID.equals("9273"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				PrimerasDamas.getChildByName("Melania Trump (esp)").articleID.equals("9454") &&
				PrimerasDamas.getChildByName("Barbara Bush (esp)").articleID.equals("10086") &&
				PrimerasDamas.getChildByName("Laura Bush (esp)").articleID.equals("10089") &&
				PrimerasDamas.getChildByName("Michelle Obama (esp)").articleID.equals("9456") &&
				PrimerasDamas.getChildByName("Hillary Clinton (esp)").articleID.equals("9388") &&	
				PrimerasDamas.getChildByName("Abigail Adams (esp)").articleID.equals("9271") &&
				PrimerasDamas.getChildByName("Dolley Madison (esp)").articleID.equals("9347") &&
				PrimerasDamas.getChildByName("Eleanor Roosevelt (esp)").articleID.equals("9357") &&
				PrimerasDamas.getChildByName("Jacqueline Kennedy (esp)").articleID.equals("9393") &&
				PrimerasDamas.getChildByName("Martha Washington (esp)").articleID.equals("9445"));
	}
	
	@Test
	public void testPGOEstudiosSocialesArticleData(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode oceanos = contentAPI.returnModuleDataByName("PebbleGo Estudios Sociales").getChildByName("Mapas").getChildByName("El Mundo en Mapas").getChildByName("Accidentes Geográficos en Mapas");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				oceanos.type.equals("A"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				oceanos.audio.equals("pgoestudiossociales_accidentesgeogrficosdeeeuuenmapas.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				oceanos.image.equals("pgosocialstudies_uslandformsonmaps.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				oceanos.title.equals("Accidentes Geográficos en Mapas"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct id", 
				oceanos.articleID.equals("9695"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				oceanos.children == null);
	}
	
	@Test
	public void testPGOEstudiosSocialesCategoryData(){
		try {
			contentAPI.getContentJSON("2");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode enMapas = contentAPI.returnModuleDataByName("PebbleGo Estudios Sociales").getChildByName("Mapas").getChildByName("El Mundo en Mapas");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				enMapas.type.equals("C"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				enMapas.audio.equals("pgoestudiossociales_elmundoenmapas.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				enMapas.image.equals("pgosocialstudies_theworldonmaps.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				enMapas.title.equals("El Mundo en Mapas"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				enMapas.articleID.equals("9579"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				enMapas.getChildByName("América del Norte en Mapas").articleID.equals("9652") &&
				enMapas.getChildByName("Continentes en Mapas").articleID.equals("9577") &&
				enMapas.getChildByName("Accidentes Geográficos en Mapas").articleID.equals("9695") &&	
				enMapas.getChildByName("Océanos en los Mapas").articleID.equals("9654"));
	}
	
	@Test
	public void testPGNStatesArticleData(){
		try {
			contentAPI.getContentJSON("3");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode california = contentAPI.returnModuleDataByName("PebbleGo Next States").getChildByName("West").getChildByName("California");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				california.type.equals("A"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				california.audio.equals("title_california.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				california.image.equals("pgnstates_california.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				california.title.equals("California"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct id", 
				california.articleID.equals("6005"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				california.children == null);
	}
	
	@Test
	public void testPGNStatesCategoryData(){
		try {
			contentAPI.getContentJSON("3");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode west = contentAPI.returnModuleDataByName("PebbleGo Next States").getChildByName("West");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				west.type.equals("C"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				west.audio.equals(""));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				west.image.equals("pgnstates_west.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				west.title.equals("West"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				west.articleID.equals("6989"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				west.getChildByName("Alaska").articleID.equals("6002") &&
				west.getChildByName("California").articleID.equals("6005") &&
				west.getChildByName("Colorado").articleID.equals("6006") &&	
				west.getChildByName("Idaho").articleID.equals("6012") &&
				west.getChildByName("Montana").articleID.equals("6026") &&
				west.getChildByName("Nevada").articleID.equals("6028") &&
				west.getChildByName("Oregon").articleID.equals("6037") &&
				west.getChildByName("Utah").articleID.equals("6045") &&
				west.getChildByName("Washington").articleID.equals("6048") &&
				west.getChildByName("Wyoming").articleID.equals("6052") &&
				west.getChildByName("Hawaii").articleID.equals("6011"));
	}
	
	@Test
	public void testPGNScienceArticleData(){
		try {
			contentAPI.getContentJSON("3");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode patterns = contentAPI.returnModuleDataByName("PebbleGo Next Science").getChildByName("Earth Science").getChildByName("Atmosphere").getChildByName("Weather and Climate").getChildByName("Weather Patterns");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				patterns.type.equals("A"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				patterns.audio.equals("pgnsci_011_weatherpatterns.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				patterns.image.equals("pgnscience_weatherpatterns.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				patterns.title.equals("Weather Patterns"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct id", 
				patterns.articleID.equals("8490"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				patterns.children == null);
	}
	
	@Test
	public void testPGNScienceCategoryData(){
		try {
			contentAPI.getContentJSON("3");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode weather = contentAPI.returnModuleDataByName("PebbleGo Next Science").getChildByName("Earth Science").getChildByName("Atmosphere").getChildByName("Weather and Climate");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				weather.type.equals("C"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				weather.audio.equals("weather_and_climate.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				weather.image.equals("pgnscience_weatherandclimate.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				weather.title.equals("Weather and Climate"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				weather.articleID.equals("8450"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				weather.getChildByName("Blizzards").articleID.equals("8483") &&	
				weather.getChildByName("Climate Types").articleID.equals("8486") &&
				weather.getChildByName("Droughts").articleID.equals("8481") &&
				weather.getChildByName("Forecasting Weather").articleID.equals("8488") &&
				weather.getChildByName("Hurricanes").articleID.equals("8484") &&
				weather.getChildByName("Clouds").articleID.equals("8487") &&
				weather.getChildByName("Tornadoes").articleID.equals("8485") &&
				weather.getChildByName("Floods").articleID.equals("8482") &&
				weather.getChildByName("Measuring and Mapping Weather").articleID.equals("8489") &&
				weather.getChildByName("Weather Patterns").articleID.equals("8490"));
	}
	
	@Test
	public void testPGNamericanIndianHistoryArticleData(){
		try {
			contentAPI.getContentJSON("3");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ContentNode nezPerce = contentAPI.returnModuleDataByName("PebbleGo Next American Indian History").getChildByName("Plateau Tribal Nations").getChildByName("Nez Perce");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				nezPerce.type.equals("A"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				nezPerce.audio.equals("title_nez_perce.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				nezPerce.image.equals("pgnamericanindianhistory_nezperce.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				nezPerce.title.equals("Nez Perce"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct id", 
				nezPerce.articleID.equals("6058"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				nezPerce.children == null);
	}
	
	@Test
	public void testPGNAmericanIndianHistoryCategoryData(){
		try {
			contentAPI.getContentJSON("3");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode plateau =  contentAPI.returnModuleDataByName("PebbleGo Next American Indian History").getChildByName("Plateau Tribal Nations");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				plateau.type.equals("C"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				plateau.audio.equals("title_plateau_culture_area.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				plateau.image.equals("pgnamericanindianhistory_plateautribalnations.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				plateau.title.equals("Plateau Tribal Nations"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				plateau.articleID.equals("6982"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				plateau.getChildByName("Plateau Tribal Nations").articleID.equals("6057") &&
				plateau.getChildByName("Nez Perce").articleID.equals("6058"));
	}
	
	@Test
	public void testPGNSocialStudiesArticleData(){
		try {
			contentAPI.getContentJSON("3");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ContentNode medicine = contentAPI.returnModuleDataByName("PebbleGo Next Social Studies").getChildByName("Technology and Society").getChildByName("History of Technology").getChildByName("Medicine");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				medicine.type.equals("A"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				medicine.audio.equals("pgnsoc_086_medicine.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				medicine.image.equals("pgnsocialstudies_medicine.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				medicine.title.equals("Medicine"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct id", 
				medicine.articleID.equals("8773"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				medicine.children == null);
	}
	
	@Test
	public void testPGNSocialStudiesCategoryData(){
		try {
			contentAPI.getContentJSON("3");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode technology = contentAPI.returnModuleDataByName("PebbleGo Next Social Studies").getChildByName("Technology and Society").getChildByName("History of Technology");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				technology.type.equals("C"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				technology.audio.equals("pgnsoc_historyoftechnology.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				technology.image.equals("pgnsocialstudies_historyoftechnology.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				technology.title.equals("History of Technology"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				technology.articleID.equals("8851"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				technology.getChildByName("Cameras").articleID.equals("8770") &&	
				technology.getChildByName("Cars").articleID.equals("8771") &&
				technology.getChildByName("Medicine").articleID.equals("8773") &&
				technology.getChildByName("Printers").articleID.equals("8774") &&
				technology.getChildByName("Telephones").articleID.equals("8775") &&
				technology.getChildByName("Computers").articleID.equals("8772"));
	}
	
	@Test
	public void testPGNBiographiesArticleData(){
		try {
			contentAPI.getContentJSON("3");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ContentNode wilma = contentAPI.returnModuleDataByName("PebbleGo Next Biographies").getChildByName("Athletes").getChildByName("Wilma Rudolph");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				wilma.type.equals("A"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				wilma.audio.equals("empty"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				wilma.image.equals("pgnbiographies_wilmarudolph.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				wilma.title.equals("Wilma Rudolph"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct id", 
				wilma.articleID.equals("9269"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				wilma.children == null);
	}
	
	@Test
	public void testPGNBiographiesCategoryData(){
		try {
			contentAPI.getContentJSON("3");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ContentNode athletes = contentAPI.returnModuleDataByName("PebbleGo Next Biographies").getChildByName("Athletes");
		
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct type",
				athletes.type.equals("C"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct audio", 
				athletes.audio.equals("pgnbiographies_athletes.mp3"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct image", 
				athletes.image.equals("pgnbiographies_athletes.jpg"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				athletes.title.equals("Athletes"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct title", 
				athletes.articleID.equals("9926"));
		assertTrue("Asserts that we can drill down to the correct Content Node with the correct children", 
				athletes.getChildByName("Lindsey Vonn").articleID.equals("9780") &&	
				athletes.getChildByName("Cristiano Ronaldo").articleID.equals("9853") &&
				athletes.getChildByName("Danica Patrick").articleID.equals("9254") &&
				athletes.getChildByName("Mia Hamm").articleID.equals("9805") &&
				athletes.getChildByName("Gabby Douglas").articleID.equals("9774") &&
				athletes.getChildByName("Mo'ne Davis").articleID.equals("9763") &&
				athletes.getChildByName("Serena Williams").articleID.equals("9879") &&
				athletes.getChildByName("Jackie Joyner-Kersee").articleID.equals("9881") &&
				athletes.getChildByName("Lisa Leslie").articleID.equals("9260") &&
				athletes.getChildByName("Jackie Robinson").articleID.equals("9754") &&
				athletes.getChildByName("Michael Jordan").articleID.equals("9768") &&
				athletes.getChildByName("Michael Phelps").articleID.equals("9264") &&
				athletes.getChildByName("Wayne Gretzky").articleID.equals("9788") &&
				athletes.getChildByName("Wilma Rudolph").articleID.equals("9269"));
	}
}
