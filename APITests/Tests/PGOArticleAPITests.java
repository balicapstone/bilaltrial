package Tests;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.http.ParseException;
import org.junit.Before;
import org.junit.Test;
import APIControllers.ArticleAPIController;
import DataClasses.ArticleData;

public class PGOArticleAPITests {
	public ArticleAPIController articleAPI = new ArticleAPIController();
	public static ArticleData article;
	
	@Before
	public void executeBefore(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "400");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testArticleAPIStatusCode() throws ParseException, IOException{
		assertTrue("Asserts that we can get an article with a valid status code", articleAPI.statusCode.equals("HTTP/1.1 200 OK"));
	}
	
	@Test
	public void testPGOArticleTitleKeyExists(){
		assertTrue("Asserts that the article returned has a Fact key", 
				articleAPI.parsedData.has("title"));
	}
	
	@Test
	public void testPGOArticleTitleDataExists(){
		assertTrue("Asserts that the article returned has the correct title data", article.title.equals("Tapirs"));
	}
	
	@Test
	public void testPGOArticleSortKeyExists(){
		assertTrue("Asserts that the article returned has a Fact key", 
				articleAPI.parsedData.has("sort"));
	}
	
	@Test
	public void testPGOArticleSortDataExists(){
		assertTrue("Asserts that the article returned has the correct sort data", article.sort.equals("6"));
	}
	
	@Test
	public void testPGOArticleTypeKeyExists(){
		assertTrue("Asserts that the article returned has a Fact key", 
				articleAPI.parsedData.has("type"));
	}
	
	@Test
	public void testPGOArticleTypeDataExists(){
		assertTrue("Asserts that the article returned has the correct sort data", article.type.equals("A"));
	}
	
	@Test
	public void testPGOArticleVideoKeysExist(){
		assertTrue("Asserts that the article returned has a Fact key", 
				articleAPI.parsedData.get("videos").getAsJsonArray().get(0).getAsJsonObject().has("id"));
		assertTrue("Asserts that the article returned has a Fact key", 
				articleAPI.parsedData.get("videos").getAsJsonArray().get(0).getAsJsonObject().has("video"));
	}
	
	@Test
	public void testPGOArticleVideoDataExists(){
		assertTrue("Asserts that the article returned has the correct video id data", 
				article.videos.get(0).getAsJsonObject().get("id").getAsString().equals("6244"));
		assertTrue("Asserts that the article returned has the correct video title data",
				article.videos.get(0).getAsJsonObject().get("video").getAsString().equals("pgoanimals_tapirs_video_0.mp4"));
	}
	
	@Test
	public void testPGOArticleVideoIsValid(){
		assertTrue("Asserts that the article returned has the correct video title data",
				articleAPI.verifyVideoActive(article.videos.get(0).getAsJsonObject().get("video").getAsString()));
	}
	
	@Test
	public void testPGOArticleUSOnlyKeyExists(){
		assertTrue("Asserts that the article returned has a Fact key", 
				articleAPI.parsedData.has("us_only"));
	}
	
	@Test
	public void testPGOArticleUSOnlyDataExists(){
		assertTrue("Asserts that the article returned has the correct US Only data", 
				article.usOnly.equals("0"));
	}
	
	@Test
	public void testPGOArticleBaseNameKeyExists(){
		assertTrue("Asserts that the article returned has a Fact key", 
				articleAPI.parsedData.has("base_name"));
	}
	
	@Test
	public void testPGOArticleBaseNameDataExists(){
		assertTrue("Asserts that the article returned has the correct BaseName data", 
				article.baseName.equals("pgoanimals_tapirs"));
	}
	
	@Test
	public void testPGOArticlModuleIDKeyExists(){
		assertTrue("Asserts that the article returned has a Fact key", 
				articleAPI.parsedData.has("module_id"));
	}
	
	@Test
	public void testPGOArticleModuleIDDataExists(){
		assertTrue("Asserts that the article returned has the correct Module ID data",
				article.moduleID.equals("1"));
	}
	
	@Test
	public void testPGOArticleArticleIDKeyExists(){
		assertTrue("Asserts that the article returned has a Fact key", 
				articleAPI.parsedData.has("article_id"));
	}
	
	@Test
	public void testPGOArticleArticleIDDataExists(){
		assertTrue("Asserts that the article returned has the correct Article ID data",
				article.articleID.equals("400"));
	}
	
	@Test
	public void testPGOArticleMainImageKeyExists(){
		assertTrue("Asserts that the article returned has a Fact key", 
				articleAPI.parsedData.has("main_image"));
	}
	
	@Test
	public void testPGOArticleMainImageDataExists(){
		assertTrue("Asserts that the article returned has the correct Main Image data",
				article.mainImage.equals("pgoanimals_tapirs.jpg"));
	}
	
	@Test
	public void testPGOArticleMainImageDataValid(){
		assertTrue("Asserts that the article returned has a valid Main Image data",
				articleAPI.verifyImageActive(article.mainImage));
	}
	
	@Test
	public void testPGOArticleMenuAudioKeyExists(){
		assertTrue("Asserts that the article returned has a Fact key", 
				articleAPI.parsedData.has("menu_audio"));
	}
	
	@Test
	public void testPGOArticleMenuAudioDataExists(){
		assertTrue("Asserts that the article returned has the correct Menu Audio data",
				article.menuAudio.equals("pgoanimals_tapirs.mp3"));
	}
	
	@Test
	public void testPGOArticleMenuAudioDataValid(){
		assertTrue("Asserts that the article returned has valid Menu Audio data",
				articleAPI.verifyAudioActive(article.menuAudio));
	}
	
	@Test
	public void testPGOArticleTexasOnlyKeyExists(){
		assertTrue("Asserts that the article returned has a Fact key", 
				articleAPI.parsedData.has("texas_only"));
	}
	
	@Test
	public void testPGOArticleTexasOnlyDataExists(){
		assertTrue("Asserts that the article returned has the correct Texas Only data",
				article.texasOnly.equals("0"));
	}
	
	@Test
	public void testPGOArticleLanguageIDKeyExists(){
		assertTrue("Asserts that the article returned has a Fact key", 
				articleAPI.parsedData.has("language_id"));
	}
	
	@Test
	public void testPGOArticleLanguageIDDataExists(){
		assertTrue("Asserts that the article returned has the correct Language ID data",
				article.languageID.equals("1"));
	}
	
	@Test
	public void testPGOArticleRangeMapFilenameKeyExists(){
		assertTrue("Asserts that the article returned has an Article Range Map key", 
				articleAPI.parsedData.has("article_range_map"));
	}
	
	@Test
	public void testPGOArticleRangeMapAltTextKeyExists(){
		assertTrue("Asserts that the article returned has an Alt Text Key in the Article Range Map Data", 
				articleAPI.parsedData.get("article_range_map").getAsJsonObject().has("alt_text"));
	}
	
	@Test
	public void testPGOArticleRageMapAltTextDataExists(){
		assertTrue("Asserts that the article returned has Alt Text Key data in the Article Range Map Data", 
				article.rangeMapData.altText.equals("null"));
	}
	
	@Test
	public void testPGOArticleRangeMapMapFilenameKeyExists(){
		assertTrue("Asserts that the article returned has a Map Filename Key in the Article Range Map Data", 
				articleAPI.parsedData.get("article_range_map").getAsJsonObject().has("map_filename"));
	}
	
	@Test
	public void testPGOArticleMapFilenameDataExists(){
		String test = article.rangeMapData.mapFilename.toString();
		assertTrue("Asserts that the article returned has the correct Map Filename data",
				article.rangeMapData.mapFilename.equals("pgoanimals_tapirs_map.png"));
	}
	
	@Test
	public void testPGOArticleMapFilenameDataValid(){
		assertTrue("Asserts that the article returned has valid Map Filename data",
				articleAPI.verifyImageActive(article.rangeMapData.mapFilename));
	}
	
	@Test
	public void testPGOArticleRangeMapJSONKeyExists(){
		assertTrue("Asserts that the article returned has a Map Filename Key in the Article Range Map Data", 
				articleAPI.parsedData.get("article_range_map").getAsJsonObject().has("range_map_json"));
	}
	
	@Test
	public void testPGOArticleRangeMapJSONDataExists(){
		assertTrue("Asserts that the article returned has a Map Filename Key in the Article Range Map Data", 
				article.rangeMapData.rangeMapJson.equals("null"));
	}
	
	@Test
	public void testPGOArticleTranslationKeysExist(){
		assertTrue("Asserts that the article returned has a article id key in the translations data", 
				articleAPI.parsedData.get("translations").getAsJsonArray().get(0).getAsJsonObject().has("article_id"));
		assertTrue("Asserts that the article returned has a language id key in the tranlations data", 
				articleAPI.parsedData.get("translations").getAsJsonArray().get(0).getAsJsonObject().has("languageISO3166_2"));
	}
	
	@Test
	public void testPGOArticleTranslationsDataExists(){
		assertTrue("Asserts that the article returned has the correct Translations Article ID",
				article.translations.get(0).getAsJsonObject().get("article_id").getAsString().equals("8399"));
		assertTrue("Asserts that the article returned has the correct Translations languageISO Data",
				article.translations.get(0).getAsJsonObject().get("languageISO3166_2").getAsString().equals("es-US"));
	}
	
	@Test
	public void testPGOArticleScreensKeyExists(){
		assertTrue("Asserts that the article returned a articleScreens key", 
				articleAPI.parsedData.has("article_screens"));
	}
	
	@Test
	public void testPGOArticleScreensTitleKeyExists(){
		assertTrue("Asserts that the article returned has a title key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("title"));
	}
	
	@Test
	public void testPGOArticleScreenTitleDataExists(){
		assertTrue("Asserts that the article returned has the correct Screen Title data",
				article.articleScreens.get(0).title.equals("Body"));
		assertTrue("Asserts that the article returned has the correct Screen Title data",
				article.articleScreens.get(1).title.equals("Habitat"));
		assertTrue("Asserts that the article returned has the correct Screen Title data",
				article.articleScreens.get(2).title.equals("Food"));
		assertTrue("Asserts that the article returned has the correct Screen Title data",
				article.articleScreens.get(3).title.equals("Life Cycle"));
		assertTrue("Asserts that the article returned has the correct Screen Title data",
				article.articleScreens.get(4).title.equals("Fun Facts"));
	}
	
	@Test
	public void testPGOArticleScreenImagesKeyExists(){
		assertTrue("Asserts that the article returned has an images key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("images"));
	}
	
	@Test
	public void testPGOArticleScreenImageCaptionDataExists(){
		assertTrue("Asserts that the article returned has the correct image returned for each screen",
				article.articleScreens.get(0).images.get(0).caption.equals(""));
		assertTrue("Asserts that the article returned has the correct image returned for each screen",
				article.articleScreens.get(1).images.get(0).caption.equals(""));
		assertTrue("Asserts that the article returned has the correct image returned for each screen",
				article.articleScreens.get(2).images.get(0).caption.equals(""));
		assertTrue("Asserts that the article returned has the correct image returned for each screen",
				article.articleScreens.get(3).images.get(0).caption.equals(""));
		assertTrue("Asserts that the article returned has the correct image returned for each screen",
				article.articleScreens.get(4).images.get(0).caption.equals(""));
	}
	
	@Test
	public void testPGOArticleScreenImagesImageKeyExists(){
		assertTrue("Asserts that the article returned has an image key in the images data with the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().get("images").getAsJsonArray().get(0).getAsJsonObject().has("image"));
	}
	
	@Test
	public void testPGOArticleScreenImageDataExists(){
		assertTrue("Asserts that the article returned has the correct image returned for each screen",
				article.articleScreens.get(0).images.get(0).image.equals("pgoanimals_tapirs_scr_1_img_0.jpg"));
		assertTrue("Asserts that the article returned has the correct image returned for each screen",
				article.articleScreens.get(1).images.get(0).image.equals("pgoanimals_tapirs_scr_2_img_0.jpg"));
		assertTrue("Asserts that the article returned has the correct image returned for each screen",
				article.articleScreens.get(2).images.get(0).image.equals("pgoanimals_tapirs_scr_3_img_0.jpg"));
		assertTrue("Asserts that the article returned has the correct image returned for each screen",
				article.articleScreens.get(3).images.get(0).image.equals("pgoanimals_tapirs_scr_4_img_0.jpg"));
		assertTrue("Asserts that the article returned has the correct image returned for each screen",
				article.articleScreens.get(4).images.get(0).image.equals("pgoanimals_tapirs_scr_5_img_0.jpg"));
	}
	
	@Test
	public void testPGOArticleScreenImageDataValid(){
		assertTrue("Asserts that the article returned has a valid image returned for each screen",
				articleAPI.verifyImageActive(article.articleScreens.get(0).images.get(0).image));
		assertTrue("Asserts that the article returned has a valid image returned for each screen",
				articleAPI.verifyImageActive(article.articleScreens.get(1).images.get(0).image));
		assertTrue("Asserts that the article returned has a valid image returned for each screen",
				articleAPI.verifyImageActive(article.articleScreens.get(2).images.get(0).image));
		assertTrue("Asserts that the article returned has a valid image returned for each screen",
				articleAPI.verifyImageActive(article.articleScreens.get(3).images.get(0).image));
		assertTrue("Asserts that the article returned has a valid image returned for each screen",
				articleAPI.verifyImageActive(article.articleScreens.get(4).images.get(0).image));
	}
	
	@Test
	public void testPGOArticleScreenImagesCaptionKeyExists(){
		assertTrue("Asserts that the article returned has a caption key in the images data with the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().get("images").getAsJsonArray().get(0).getAsJsonObject().has("caption"));
	}
	
	
	@Test
	public void testPGOArticleScreenImagesIDKeyExists(){
		assertTrue("Asserts that the article returned has an image key in the images data with the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().get("images").getAsJsonArray().get(0).getAsJsonObject().has("article_screen_image_id"));
	}
	
	@Test
	public void testPGOArticleScreenImageIDDataExists(){
		assertTrue("Asserts that the article returned has the correct image ID returned for each screen",
				article.articleScreens.get(0).images.get(0).screenImageID.equals("54927"));
		assertTrue("Asserts that the article returned has the correct image ID returned for each screen",
				article.articleScreens.get(1).images.get(0).screenImageID.equals("54928"));
		assertTrue("Asserts that the article returned has the correct image ID returned for each screen",
				article.articleScreens.get(2).images.get(0).screenImageID.equals("54929"));
		assertTrue("Asserts that the article returned has the correct image ID returned for each screen",
				article.articleScreens.get(3).images.get(0).screenImageID.equals("54930"));
		assertTrue("Asserts that the article returned has the correct image ID returned for each screen",
				article.articleScreens.get(4).images.get(0).screenImageID.equals("54931"));
	}
	
	@Test
	public void testPGOArticleScreensNumberKeyExists(){
		assertTrue("Asserts that the article returned has a number key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("number"));
	}
	
	@Test
	public void testPGOArticleScreenNumberDataExists(){
		assertTrue("Asserts that the article returned has the correct Screen Number data",
				article.articleScreens.get(0).number.equals("1"));
		assertTrue("Asserts that the article returned has the correct Screen Number data",
				article.articleScreens.get(1).number.equals("2"));
		assertTrue("Asserts that the article returned has the correct Screen Number data",
				article.articleScreens.get(2).number.equals("3"));
		assertTrue("Asserts that the article returned has the correct Screen Number data",
				article.articleScreens.get(3).number.equals("4"));
		assertTrue("Asserts that the article returned has the correct Screen Number data",
				article.articleScreens.get(4).number.equals("5"));
	}
	
	@Test
	public void testPGOArticleScreensContentKeyExists(){
		assertTrue("Asserts that the article returned has a content key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("content"));
	}
	
	@Test
	public void testPGOArticleScreenContentDataExists(){
		assertTrue("Asserts that the article returned has the correct Screen Number data",
				article.articleScreens.get(0).content.equals("Tapirs are mammals.\nTheir fur can be brown,\nred, black, or white.\nTapirs weigh 700 pounds\n(318 kilograms).\nTheir snouts look like\nshort elephant trunks."));
		assertTrue("Asserts that the article returned has the correct Screen Number data",
				article.articleScreens.get(1).content.equals("Tapirs live in Central America\nand South America.\nMost tapirs can be found\nin forests and grasslands.\nSome tapirs live\nin the mountains."));
		assertTrue("Asserts that the article returned has the correct Screen Number data",
				article.articleScreens.get(2).content.equals("Tapirs eat at night.\nThey eat twigs, shrubs,\nleaves, and fruit.\nTapirs use their snouts\nto grab branches and fruit.\nThey have more than\n40 teeth for chewing food."));
		assertTrue("Asserts that the article returned has the correct Screen Number data",
				article.articleScreens.get(3).content.equals("A baby tapir is called a calf.\nIt weighs 15 pounds\n(6.8 kilograms) at birth.\nTapirs finish growing\nin about two years.\nThey live about 30 years."));
		assertTrue("Asserts that the article returned has the correct Screen Number data",
				article.articleScreens.get(4).content.equals("<ul><li> There are four kinds\nof tapirs.</li><li> Baby tapirs are born\nwith stripes and spots.</li><li> Tapirs are good swimmers.</li><li> Tapirs use their coloring\nto hide from predators.</li></ul>"));		
	}
	
	@Test
	public void testPGOArticleScreensLayoutIDKeyExists(){
		assertTrue("Asserts that the article returned has a layout id key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("layout_id"));
	}
	
	@Test
	public void testPGOArticleScreenLayoutIDDataExists(){
		assertTrue("Asserts that the article returned has the correct Screen Layout ID data",
				article.articleScreens.get(0).layoutID.equals("1"));
		assertTrue("Asserts that the article returned has the correct Screen Layout ID data",
				article.articleScreens.get(1).layoutID.equals("1"));
		assertTrue("Asserts that the article returned has the correct Screen Layout ID data",
				article.articleScreens.get(2).layoutID.equals("1"));
		assertTrue("Asserts that the article returned has the correct Screen Layout ID data",
				article.articleScreens.get(3).layoutID.equals("1"));
		assertTrue("Asserts that the article returned has the correct Screen Layout ID data",
				article.articleScreens.get(4).layoutID.equals("1"));
	}
	
	@Test
	public void testPGOArticleScreensScreenIDKeyExists(){
		assertTrue("Asserts that the article returned has a screen id key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("screen_id"));
	}
	
	@Test
	public void testPGOArticleScreenIDDataExists(){
		assertTrue("Asserts that the article returned has the correct Screen ID data",
				article.articleScreens.get(0).screenID.equals("53493"));
		assertTrue("Asserts that the article returned has the correct Screen ID data",
				article.articleScreens.get(1).screenID.equals("53494"));
		assertTrue("Asserts that the article returned has the correct Screen ID data",
				article.articleScreens.get(2).screenID.equals("53495"));
		assertTrue("Asserts that the article returned has the correct Screen ID data",
				article.articleScreens.get(3).screenID.equals("53496"));
		assertTrue("Asserts that the article returned has the correct Screen ID data",
				article.articleScreens.get(4).screenID.equals("53497"));
	}
	
	@Test
	public void testPGOArticleScreensTimeFileKeyExists(){
		assertTrue("Asserts that the article returned has a time file key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("time_file"));
	}
	
	@Test
	public void testPGOArticleScreenTimefileDataExists(){
		assertTrue("Asserts that the article returned has the correct Screen Timefile data",
				article.articleScreens.get(0).timeFile.equals("pgoanimals_tapirs_scr_1.xml"));
		assertTrue("Asserts that the article returned has the correct Screen Timefile data",
				article.articleScreens.get(1).timeFile.equals("pgoanimals_tapirs_scr_2.xml"));
		assertTrue("Asserts that the article returned has the correct Screen Timefile data",
				article.articleScreens.get(2).timeFile.equals("pgoanimals_tapirs_scr_3.xml"));
		assertTrue("Asserts that the article returned has the correct Screen Timefile data",
				article.articleScreens.get(3).timeFile.equals("pgoanimals_tapirs_scr_4.xml"));
		assertTrue("Asserts that the article returned has the correct Screen Timefile data",
				article.articleScreens.get(4).timeFile.equals("pgoanimals_tapirs_scr_5.xml"));
	}
	
	@Test
	public void testPGOArticleScreenTimefileDataValid(){
		assertTrue("Asserts that the article returned has a valid Screen Timefile data",
				articleAPI.verifyXMLActive(article.articleScreens.get(0).timeFile));
		assertTrue("Asserts that the article returned has a valid Screen Timefile data",
				articleAPI.verifyXMLActive(article.articleScreens.get(1).timeFile));
		assertTrue("Asserts that the article returned has a valid Screen Timefile data",
				articleAPI.verifyXMLActive(article.articleScreens.get(2).timeFile));
		assertTrue("Asserts that the article returned has a valid Screen Timefile data",
				articleAPI.verifyXMLActive(article.articleScreens.get(3).timeFile));
		assertTrue("Asserts that the article returned has a valid Screen Timefile data",
				articleAPI.verifyXMLActive(article.articleScreens.get(4).timeFile));
	}
	
	@Test
	public void testPGOArticleScreensAudioFileKeyExists(){
		assertTrue("Asserts that the article returned has a audio file key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("audio_file"));
	}
	
	@Test
	public void testPGOArticleScreenAudiofileDataExists(){
		assertTrue("Asserts that the article returned has a valid Screen Audio File data",
				articleAPI.verifyAudioActive(article.articleScreens.get(0).audioFile));
		assertTrue("Asserts that the article returned has a valid Screen Audio File data",
				articleAPI.verifyAudioActive(article.articleScreens.get(1).audioFile));
		assertTrue("Asserts that the article returned has a valid Screen Audio File data",
				articleAPI.verifyAudioActive(article.articleScreens.get(2).audioFile));
		assertTrue("Asserts that the article returned has a valid Screen Audio File data",
				articleAPI.verifyAudioActive(article.articleScreens.get(3).audioFile));
		assertTrue("Asserts that the article returned has a valid Screen Audio File data",
				articleAPI.verifyAudioActive(article.articleScreens.get(4).audioFile));
	}
	
	@Test
	public void testPGOArticleScreensTitleAudioFileKeyExists(){
		assertTrue("Asserts that the article returned has a title audio file key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("title_audio_file"));
	}
	
	@Test
	public void testPGOArticleScreenTitleAudiofileDataExists(){
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(0).titleAudioFile.equals(""));
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(1).titleAudioFile.equals(""));
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(2).titleAudioFile.equals(""));
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(3).titleAudioFile.equals(""));
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(4).titleAudioFile.equals(""));
	}
	
	@Test
	public void testPGOArticleScreensContentTimecodedKeyExists(){
		assertTrue("Asserts that the article returned has a content timecoded key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("content_timecoded"));
	}
	
	@Test
	public void testPGOArticleScreenTimecode(){
		ArrayList<String> screenOne = new ArrayList<String>(Arrays.asList("Tapirs", "are", "mammals.", "Their", "fur", "can", "be", "brown,", "red,", 
				"black,", "or", "white.", "Tapirs", "weigh", "700", "pounds", "(318", "kilograms).", "Their", "snouts", "look", "like", "short", "elephant", "trunks."));
		ArrayList<String> screenTwo = new ArrayList<String>(Arrays.asList("Tapirs", "live", "in", "Central", "America", "and", "South", "America.", "Most", "tapirs", "can",
				"be", "found", "in", "forests", "and", "grasslands.", "Some", "tapirs", "live", "in", "the", "mountains."));
		ArrayList<String> screenThree = new ArrayList<String>(Arrays.asList("Tapirs", "eat", "at", "night.", "They", "eat", "twigs,", "shrubs,", "leaves,", "and", "fruit.",
				"Tapirs", "use", "their", "snouts", "to", "grab", "branches", "and", "fruit.", "They", "have", "more", "than", "40", "teeth", "for", "chewing", "food."));
		ArrayList<String> screenFour = new ArrayList<String>(Arrays.asList("A", "baby", "tapir", "is", "called", "a", "calf.", "It", "weighs", "15", "pounds", "(6.8", 
				"kilograms)", "at", "birth.", "Tapirs", "finish", "growing", "in", "about", "two", "years.", "They", "live", "about", "30", "years."));
		ArrayList<String> screenFive = new ArrayList<String>(Arrays.asList("There", "are", "four", "kinds", "of", "tapirs.", "Baby", "tapirs", "are", "born", "with", 
				"stripes", "and", "spots.", "Tapirs", "are", "good", "swimmers.", "Tapirs", "use", "their", "coloring", "to",  "hide", "from", "predators."));
		
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(0).screenTimeCode.equals(screenOne));
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(1).screenTimeCode.equals(screenTwo));
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(2).screenTimeCode.equals(screenThree));
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(3).screenTimeCode.equals(screenFour));
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(4).screenTimeCode.equals(screenFive));
	}
	
	@Test
	public void testPGOArticleSymbolsKeyExists(){
		assertTrue("Asserts that the article returned has a article symbols key", 
				articleAPI.parsedData.has("article_symbols"));
	}
	
	@Test
	public void testPGOArticleActivtyTitleKeyExists(){
		assertTrue("Asserts that the article returned has a title tag in the article activity data", 
				articleAPI.parsedData.get("article_activity").getAsJsonObject().has("title"));
	}
	
	@Test
	public void testPGOArticleActivityTitleDataExists(){
		assertTrue("Asserts that the article returned has the correct Activity Title",
				article.activityData.title.equals("Tapirs"));
	}
	
	@Test
	public void testPGOArticleActivtyPromptKeysExists(){
		assertTrue("Asserts that the article returned has a prompt1 tag in the article activity data", 
				articleAPI.parsedData.get("article_activity").getAsJsonObject().has("prompt1"));
		assertTrue("Asserts that the article returned has a prompt2 tag in the article activity data", 
				articleAPI.parsedData.get("article_activity").getAsJsonObject().has("prompt2"));
		assertTrue("Asserts that the article returned has a prompt3 tag in the article activity data", 
				articleAPI.parsedData.get("article_activity").getAsJsonObject().has("prompt3"));
	}
	
	@Test
	public void testPGOArticleActivityPromptsDataExists(){
		assertTrue("Asserts that the article returned has the correct Activity Prompt #1",
				article.activityData.prompt1.equals(""));
		assertTrue("Asserts that the article returned has the correct Activity Prompt #2",
				article.activityData.prompt2.equals(""));
		assertTrue("Asserts that the article returned has the correct Activity Prompt #3",
				article.activityData.prompt3.equals("empty"));
	}
	
	@Test
	public void testPGOArticleActivityDrawTitleKeyExists(){
	assertTrue("Asserts that the article returned has a draw title tag in the article activity data", 
			articleAPI.parsedData.get("article_activity").getAsJsonObject().has("draw_title"));
	}
	
	@Test
	public void testPGOArticleActivityDrawTitle(){
		assertTrue("Asserts that the article returned has the correct Draw Title",
				article.activityData.drawTitle.equals("Draw a picture of a tapir:"));
	}
	
	@Test
	public void testPGOArticleActivityShareFileKeyExists(){
	assertTrue("Asserts that the article returned has a share file tag in the article activity data", 
			articleAPI.parsedData.get("article_activity").getAsJsonObject().has("share_file"));
	}
	
	@Test
	public void testPGOArticleActivityShareFile(){
		assertTrue("Asserts that the article returned has the correct Share File",
				article.activityData.shareFile.equals("pgoanimals_tapirs_share.pdf"));
	}
	
	@Test
	public void testPGOArticleActivityShareValid(){
		assertTrue("Asserts that the article returned has a valid Share File",
				articleAPI.verifyPDFActive(article.activityData.shareFile));
	}
	
	@Test
	public void testPGOArticleActivityFileKeyExists(){
		assertTrue("Asserts that the article returned has an activity file tag in the article activity data", 
			articleAPI.parsedData.get("article_activity").getAsJsonObject().has("activity_file"));
	}
	
	@Test
	public void testPGOArticleActivityFileDataExists(){
		assertTrue("Asserts that the article returned has the correct Activity File",
				article.activityData.activityFile.equals("pgoanimals_tapirs_activity.pdf"));
	}
	
	@Test
	public void testPGOArticleActivityFileDataValid(){
		assertTrue("Asserts that the article returned has a valid Activity File",
				articleAPI.verifyPDFActive(article.activityData.activityFile));
	}
	
	@Test
	public void testPGOArticleQuestionFileKeyExists(){
		assertTrue("Asserts that the article returned has an question file tag in the article activity data", 
			articleAPI.parsedData.get("article_activity").getAsJsonObject().has("question_file"));
	}
	
	@Test
	public void testPGOArticleQuestionFileDataExists(){
		assertTrue("Asserts that the article returned has the correct Question File",
				article.activityData.questionFile.equals(""));
	}
	
	@Test
	public void testPGOArticleAutoGenValueKeysExists(){
		assertTrue("Asserts that the article returned has an auto gen share tag in the article activity data", 
				articleAPI.parsedData.get("article_activity").getAsJsonObject().has("auto_gen_share"));
		assertTrue("Asserts that the article returned has an auto gen activity tag in the article activity data", 
				articleAPI.parsedData.get("article_activity").getAsJsonObject().has("auto_gen_activity"));
		assertTrue("Asserts that the article returned has an auto gen question tag in the article activity data", 
				articleAPI.parsedData.get("article_activity").getAsJsonObject().has("auto_gen_question"));
	}
	
	@Test
	public void testPGOArticleAutoGenValuesKeyExist(){
		assertTrue("Asserts that the article returned has the correct Auto Gen Share Value",
				article.activityData.autoGenShare.equals("0"));
		assertTrue("Asserts that the article returned has the correct Auto Gen Activity Value",
				article.activityData.autoGenActivity.equals("0"));
		assertTrue("Asserts that the article returned has the correct Auto Gen Qustion Value",
				article.activityData.autoGenQuestion.equals("0"));
	}
	
	@Test
	public void testPGOArticleRelatedArticleKeyExists(){
		assertTrue("Asserts that the article returned has a related articles tag in the article activity data", 
				articleAPI.parsedData.has("related_articles"));
	}
	
	@Test
	public void testPGOArticleRelatedArticlesDataExists(){
		assertTrue("Asserts that the article returned has the correct related articles values",
				article.relatedArticles.get(0).getAsString().equals("162"));
		assertTrue("Asserts that the article returned has the correct related articles values",
				article.relatedArticles.get(1).getAsString().equals("405"));
	}
	
	@Test
	public void testPGOArticleLandmarksKeyExists(){
		assertTrue("Asserts that the article returned has an article landmarks key",
				articleAPI.parsedData.has("article_landmarks"));
	}

	@Test
	public void testPGOArticleTimelinesKeyExists(){
		assertTrue("Asserts that the article returned has an article timelines key",
				articleAPI.parsedData.has("article_timelines"));
	}
	
	@Test
	public void testPGOArticleLanguageISOKeyExists(){
		assertTrue("Asserts that the article returned has a LanguageISO key", 
				articleAPI.parsedData.has("languageISO3166_2"));
	}
	
	@Test
	public void testPGOArticleLanguageISODataExists(){
		assertTrue("Asserts that the article returned has the correct languageISO value",
				article.languageString.equals("en-US"));
	}
	
	@Test
	public void testPGOArticleGlossaryKeyExists(){
		assertTrue("Asserts that the article returned has a glossary key", 
				articleAPI.parsedData.has("article_glossaries"));
	}

	@Test
	public void testPGOArticleGlossaryTermsID(){
		assertTrue("Asserts that the article returned has the correct glossary term id", 
				article.glossaries.get(0).id.equals("1264"));
		assertTrue("Asserts that the article returned has the correct glossary term id", 
				article.glossaries.get(1).id.equals("6889"));
		assertTrue("Asserts that the article returned has the correct glossary term id", 
				article.glossaries.get(2).id.equals("14852"));
		assertTrue("Asserts that the article returned has the correct glossary term id", 
				article.glossaries.get(3).id.equals("7017"));
		assertTrue("Asserts that the article returned has the correct glossary term id", 
				article.glossaries.get(4).id.equals("18837"));
	}
	
	@Test
	public void testPGOArticleGlossaryTermsWord(){
		assertTrue("Asserts that the article returned has the correct glossary term word", 
				article.glossaries.get(0).word.equals("forests"));
		assertTrue("Asserts that the article returned has the correct glossary term word", 
				article.glossaries.get(1).word.equals("mammals"));
		assertTrue("Asserts that the article returned has the correct glossary term word", 
				article.glossaries.get(2).word.equals("predators"));
		assertTrue("Asserts that the article returned has the correct glossary term word", 
				article.glossaries.get(3).word.equals("shrubs"));
		assertTrue("Asserts that the article returned has the correct glossary term word", 
				article.glossaries.get(4).word.equals("snouts"));
	}
	
	@Test
	public void testPGOArticleGlossaryTermsBaseWord(){
		assertTrue("Asserts that the article returned has the correct glossary term baseword", 
				article.glossaries.get(0).baseword.equals("forest"));
		assertTrue("Asserts that the article returned has the correct glossary term baseword", 
				article.glossaries.get(1).baseword.equals("mammal"));
		assertTrue("Asserts that the article returned has the correct glossary term baseword", 
				article.glossaries.get(2).baseword.equals("predator"));
		assertTrue("Asserts that the article returned has the correct glossary term baseword", 
				article.glossaries.get(3).baseword.equals("shrub"));
		assertTrue("Asserts that the article returned has the correct glossary term baseword", 
				article.glossaries.get(4).baseword.equals("snout"));
	}
	
	@Test
	public void testPGOArticleAnimalSoundFileExists(){
		assertTrue("Asserts that the article returned has a animal sound file key", 
				articleAPI.parsedData.has("animal_sound_filename"));
	}
	
	@Test
	public void testPGOArticleAnimalSoundFile(){
		assertTrue("Asserts that the article returned has the correct animal sound file",
				article.soundFile.equals("pgoanimals_tapirs_listen.mp3"));
	}
	
	@Test
	public void testPGOArticleAnimalSoundFileValid(){
		assertTrue("Asserts that the article returned has the correct animal sound file",
				articleAPI.verifyAudioActive(article.soundFile));
	}
	
	@Test
	public void testPGOArticleScreenTitleQuestionFileSpecific(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "2045");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has the correct Question File", 
				article.activityData.questionFile.equals("pgoscience_lakes_question.pdf"));
		assertTrue("Asserts that the article returned has a valid Question File", 
				articleAPI.verifyPDFActive(article.activityData.questionFile));
		assertTrue("Asserts that the article returned has the correct Activity File", 
				article.activityData.activityFile.equals("pgoscience_lakes_activity.pdf"));
		assertTrue("Asserts that the article returned has a valid Activity File", 
				articleAPI.verifyPDFActive(article.activityData.activityFile));
		assertTrue("Asserts that the article returned has the correct Activity File", 
				article.activityData.shareFile.equals(""));
	}
	
	@Test
	public void testPGOArticleScreenImageDataSpecific(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "3012");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has the correct image returned for each screen",
				article.articleScreens.get(0).images.get(0).caption.equals("Kadir in 2011"));
		assertTrue("Asserts that the article returned has the correct image returned for each screen",
				article.articleScreens.get(1).images.get(0).caption.equals("Kadir with one of his illustrations in 2001"));
		assertTrue("Asserts that the article returned has the correct image returned for each screen",
				article.articleScreens.get(2).images.get(0).caption.equals("Kadir working on illustrations for a book"));
		assertTrue("Asserts that the article returned has the correct image returned for each screen",
				article.articleScreens.get(3).images.get(0).caption.equals("<i>We Are the Ship</i>"));
		assertTrue("Asserts that the article returned has the correct image returned for each screen",
				article.articleScreens.get(4).images.get(0).caption.equals("Kadir accepting an award"));
	}
	
	@Test
	public void testPGOArticleScreenTitleAudioFileSpecific(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "8379");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(0).titleAudioFile.equals("pgoanimales_necturos_tab_1.mp3"));
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(1).titleAudioFile.equals("pgoanimales_necturos_tab_2.mp3"));
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(2).titleAudioFile.equals("pgoanimales_necturos_tab_3.mp3"));
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(3).titleAudioFile.equals("pgoanimales_necturos_tab_4.mp3"));
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(4).titleAudioFile.equals("pgoanimales_necturos_tab_5.mp3"));
		
		assertTrue("Asserts that the article returned has a valid Screen Title File data",
				articleAPI.verifyAudioActive(article.articleScreens.get(0).titleAudioFile));
		assertTrue("Asserts that the article returned has a valid Screen Title File data",
				articleAPI.verifyAudioActive(article.articleScreens.get(1).titleAudioFile));
		assertTrue("Asserts that the article returned has a valid Screen Title File data",
				articleAPI.verifyAudioActive(article.articleScreens.get(2).titleAudioFile));
		assertTrue("Asserts that the article returned has a valid Screen Title File data",
				articleAPI.verifyAudioActive(article.articleScreens.get(3).titleAudioFile));
		assertTrue("Asserts that the article returned has a valid Screen Title File data",
				articleAPI.verifyAudioActive(article.articleScreens.get(4).titleAudioFile));
	}
	
	@Test
	public void testPGOArticleActivityPromptsDataSpecific(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "3012");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has the correct Activity Prompt #1",
				article.activityData.prompt1.equals("Facts about Kadir Nelson:"));
		assertTrue("Asserts that the article returned has the correct Activity Prompt #2",
				article.activityData.prompt2.equals("Kadir Nelson is important because"));
		assertTrue("Asserts that the article returned has the correct Activity Prompt #3",
				article.activityData.prompt3.equals("empty"));
	}
	
	@Test
	public void testPGOArticleTimelinesStartDateKeyExists(){	
		try {
			article = articleAPI.getPGOArticleJSON("2", "3049");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has a start date key in the timelines data", 
				articleAPI.parsedData.get("article_timelines").getAsJsonArray().get(0).getAsJsonObject().has("date1"));
	}
	
	@Test
	public void testBiographiesTimelinesStartDateDataExists(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "3049");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has the correct Timeline start date",
				article.articleTimelines.get(0).startDate.equals("1895"));
		assertTrue("Asserts that the article returned has the correct Timeline start date",
				article.articleTimelines.get(1).startDate.equals("1902"));
		assertTrue("Asserts that the article returned has the correct Timeline start date",
				article.articleTimelines.get(2).startDate.equals("1914"));
		assertTrue("Asserts that the article returned has the correct Timeline start date",
				article.articleTimelines.get(3).startDate.equals("1914"));
		assertTrue("Asserts that the article returned has the correct Timeline start date",
				article.articleTimelines.get(4).startDate.equals("1920"));
		assertTrue("Asserts that the article returned has the correct Timeline start date",
				article.articleTimelines.get(5).startDate.equals("1927"));
		assertTrue("Asserts that the article returned has the correct Timeline start date",
				article.articleTimelines.get(6).startDate.equals("1935"));
		assertTrue("Asserts that the article returned has the correct Timeline start date",
				article.articleTimelines.get(7).startDate.equals("1948"));
	}
	
	@Test
	public void testPGOArticleTimelinesEndDateKeyExists(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "3049");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has a end date key in the timelines data", 
				articleAPI.parsedData.get("article_timelines").getAsJsonArray().get(0).getAsJsonObject().has("date2"));
	}
	
	@Test
	public void testBiographiesTimelinesEndDateDataExists(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "3049");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has the correct Timeline end date",
				article.articleTimelines.get(0).endDate.equals("1895"));
		assertTrue("Asserts that the article returned has the correct Timeline end date",
				article.articleTimelines.get(1).endDate.equals("1902"));
		assertTrue("Asserts that the article returned has the correct Timeline end date",
				article.articleTimelines.get(2).endDate.equals("1914"));
		assertTrue("Asserts that the article returned has the correct Timeline end date",
				article.articleTimelines.get(3).endDate.equals("1914"));
		assertTrue("Asserts that the article returned has the correct Timeline end date",
				article.articleTimelines.get(4).endDate.equals("1920"));
		assertTrue("Asserts that the article returned has the correct Timeline end date",
				article.articleTimelines.get(5).endDate.equals("1927"));
		assertTrue("Asserts that the article returned has the correct Timeline end date",
				article.articleTimelines.get(6).endDate.equals("1935"));
		assertTrue("Asserts that the article returned has the correct Timeline end date",
				article.articleTimelines.get(7).endDate.equals("1948"));
	}
	
	@Test
	public void testPGOArticleTimelinesAudioFileKeyExists(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "3049");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has an audio file key in the timelines data", 
				articleAPI.parsedData.get("article_timelines").getAsJsonArray().get(0).getAsJsonObject().has("audio_file"));
	}

	@Test
	public void testBiographiesTimelinesAudioFileDataExists(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "3049");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has the correct Timeline audio file",
				article.articleTimelines.get(0).audioFile.equals("pgobiographies_baberuth_timeline_1.mp3"));
		assertTrue("Asserts that the article returned has the correct Timeline audio file",
				article.articleTimelines.get(1).audioFile.equals("pgobiographies_baberuth_timeline_2.mp3"));
		assertTrue("Asserts that the article returned has the correct Timeline audio file",
				article.articleTimelines.get(2).audioFile.equals("pgobiographies_baberuth_timeline_3.mp3"));
		assertTrue("Asserts that the article returned has the correct Timeline audio file",
				article.articleTimelines.get(3).audioFile.equals("pgobiographies_baberuth_timeline_4.mp3"));
		assertTrue("Asserts that the article returned has the correct Timeline audio file",
				article.articleTimelines.get(4).audioFile.equals("pgobiographies_baberuth_timeline_5.mp3"));
		assertTrue("Asserts that the article returned has the correct Timeline audio file",
				article.articleTimelines.get(5).audioFile.equals("pgobiographies_baberuth_timeline_6.mp3"));
		assertTrue("Asserts that the article returned has the correct Timeline audio file",
				article.articleTimelines.get(6).audioFile.equals("pgobiographies_baberuth_timeline_7.mp3"));
		assertTrue("Asserts that the article returned has the correct Timeline audio file",
				article.articleTimelines.get(7).audioFile.equals("pgobiographies_baberuth_timeline_8.mp3"));
	}
	
	@Test
	public void testPGOArticleTimelinesDescriptionKeyExists(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "3049");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has a desciption key in the timelines data", 
				articleAPI.parsedData.get("article_timelines").getAsJsonArray().get(0).getAsJsonObject().has("description"));
	}
	
	@Test
	public void testBiographiesTimelinesDescriptionDataExists(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "3049");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has the correct Timeline description",
				article.articleTimelines.get(0).description.equals("Babe is born in Baltimore, Maryland"));
		assertTrue("Asserts that the article returned has the correct Timeline description",
				article.articleTimelines.get(1).description.equals("sent to boarding school"));
		assertTrue("Asserts that the article returned has the correct Timeline description",
				article.articleTimelines.get(2).description.equals("joins the Baltimore Orioles as a pitcher"));
		assertTrue("Asserts that the article returned has the correct Timeline description",
				article.articleTimelines.get(3).description.equals("traded to the Boston Redsox"));
		assertTrue("Asserts that the article returned has the correct Timeline description",
				article.articleTimelines.get(4).description.equals("traded to the New York Yankees"));
		assertTrue("Asserts that the article returned has the correct Timeline description",
				article.articleTimelines.get(5).description.equals("hits a record 60 home runs"));
		assertTrue("Asserts that the article returned has the correct Timeline description",
				article.articleTimelines.get(6).description.equals("retires from baseball with 714 home runs"));
		assertTrue("Asserts that the article returned has the correct Timeline description",
				article.articleTimelines.get(7).description.equals("Babe dies at age 53"));
	}
	
	@Test
	public void testPGOArticleTimelinesIDKeyExists(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "3049");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has a timeline id key in the timelines data", 
				articleAPI.parsedData.get("article_timelines").getAsJsonArray().get(0).getAsJsonObject().has("timeline_id"));
	}
	
	@Test
	public void testBiographiesTimelinesIDDataExists(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "3049");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(0).timelineID.equals("30718"));
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(1).timelineID.equals("30719"));
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(2).timelineID.equals("30720"));
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(3).timelineID.equals("30721"));
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(4).timelineID.equals("30722"));
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(5).timelineID.equals("30723"));
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(6).timelineID.equals("30724"));
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(7).timelineID.equals("30725"));
	}
	
	@Test
	public void testPGOArticleTimelinesTimecodeKeyExists(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "3049");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has a timecode file key in the timelines data", 
				articleAPI.parsedData.get("article_timelines").getAsJsonArray().get(0).getAsJsonObject().has("timecode_file"));
	}
	
	@Test
	public void testBiographiesTimelinesTimecodeFileDataExists(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "3049");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(0).timecodeFile.equals("pgobiographies_baberuth_timeline_1.xml"));
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(1).timecodeFile.equals("pgobiographies_baberuth_timeline_2.xml"));
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(2).timecodeFile.equals("pgobiographies_baberuth_timeline_3.xml"));
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(3).timecodeFile.equals("pgobiographies_baberuth_timeline_4.xml"));
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(4).timecodeFile.equals("pgobiographies_baberuth_timeline_5.xml"));
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(5).timecodeFile.equals("pgobiographies_baberuth_timeline_6.xml"));
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(6).timecodeFile.equals("pgobiographies_baberuth_timeline_7.xml"));
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(7).timecodeFile.equals("pgobiographies_baberuth_timeline_8.xml"));
	}
	
	@Test
	public void testPGOArticleTimelinesDateTimecodeKeyExists(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "3049");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has a date timecoded key in the timelines data", 
				articleAPI.parsedData.get("article_timelines").getAsJsonArray().get(0).getAsJsonObject().has("date_timecoded"));
	}
	
	@Test
	public void testBiographiesTimelinesDateTimecode(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "3049");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has the correct Date Timecode",
				article.articleTimelines.get(0).dateTimecode.get(0).equals("1895"));
		assertTrue("Asserts that the article returned has the correct Date Timecode",
				article.articleTimelines.get(1).dateTimecode.get(0).equals("1902"));
		assertTrue("Asserts that the article returned has the correct Date Timecode",
				article.articleTimelines.get(2).dateTimecode.get(0).equals("1914"));
		assertTrue("Asserts that the article returned has the correct Date Timecode",
				article.articleTimelines.get(3).dateTimecode.get(0).equals("1914"));
		assertTrue("Asserts that the article returned has the correct Date Timecode",
				article.articleTimelines.get(4).dateTimecode.get(0).equals("1920"));
		assertTrue("Asserts that the article returned has the correct Date Timecode",
				article.articleTimelines.get(5).dateTimecode.get(0).equals("1927"));
		assertTrue("Asserts that the article returned has the correct Date Timecode",
				article.articleTimelines.get(6).dateTimecode.get(0).equals("1935"));
		assertTrue("Asserts that the article returned has the correct Date Timecode",
				article.articleTimelines.get(7).dateTimecode.get(0).equals("1948"));
	}
	
	@Test
	public void testPGOArticleTimelinesDescriptionTimecodeKeyExists(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "3049");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has a description timecoded key in the timelines data", 
				articleAPI.parsedData.get("article_timelines").getAsJsonArray().get(0).getAsJsonObject().has("description_timecoded"));
	}
	
	@Test
	public void testBiographiesTimelinesDescriptionTimecodeDataExists(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "3049");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the article returned has the correct Timecode",
				article.articleTimelines.get(0).descriptionTimecode.equals(new ArrayList<String>(Arrays.asList("Babe", "is", "born", "in", "Baltimore,", "Maryland"))));
		assertTrue("Asserts that the article returned has the correct Timecode",
				article.articleTimelines.get(1).descriptionTimecode.equals(new ArrayList<String>(Arrays.asList("sent", "to", "boarding", "school"))));
		assertTrue("Asserts that the article returned has the correct Timecode",
				article.articleTimelines.get(2).descriptionTimecode.equals(new ArrayList<String>(Arrays.asList("joins", "the", "Baltimore", "Orioles", "as", "a", "pitcher"))));
		assertTrue("Asserts that the article returned has the correct Timecode",
				article.articleTimelines.get(3).descriptionTimecode.equals(new ArrayList<String>(Arrays.asList("traded", "to", "the", "Boston", "Redsox"))));
		assertTrue("Asserts that the article returned has the correct Timecode",
				article.articleTimelines.get(4).descriptionTimecode.equals(new ArrayList<String>(Arrays.asList("traded", "to", "the", "New", "York", "Yankees"))));
		assertTrue("Asserts that the article returned has the correct Timecode",
				article.articleTimelines.get(5).descriptionTimecode.equals(new ArrayList<String>(Arrays.asList("hits", "a", "record", "60", "home", "runs"))));
		assertTrue("Asserts that the article returned has the correct Timecode",
				article.articleTimelines.get(6).descriptionTimecode.equals(new ArrayList<String>(Arrays.asList("retires", "from", "baseball", "with", "714", "home", "runs"))));
		assertTrue("Asserts that the article returned has the correct Timecode",
				article.articleTimelines.get(7).descriptionTimecode.equals(new ArrayList<String>(Arrays.asList("Babe", "dies", "at", "age", "53"))));
	}
	
	@Test
	public void testBadArticleZeroData(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "0");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the data returned for a bad article datapoint is an empty array",
				articleAPI.entity.equals("\"Unauthorized\""));
	}
	
	@Test
	public void testBadArticleTooLargeData(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "213412341234");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the data returned for a bad article datapoint is an empty array",
				articleAPI.entity.equals("[]"));
	}
	
	@Test
	public void testBadArticleCharactersData(){
		try {
			article = articleAPI.getPGOArticleJSON("2", "asdfasdfasdf");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the data returned for a bad article datapoint is an empty array",
				articleAPI.entity.equals("\"Unauthorized\""));
	}
	
	/*
	@Test
	public void testBadAppData(){
		try {
			article = articleAPI.getPGOArticleJSON("0", "38");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the data returned for a bad app datapoint is an internal server error",
				articleAPI.entity.equals("{\"message\": \"Internal server error\"}"));
	}
	
	@Test
	public void testBadAppDataLetters(){
		try {
			article = articleAPI.getPGOArticleJSON("asdfasdfasdf", "38");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the data returned for a bad app datapoint is an internal server error",
				articleAPI.entity.equals("{\"message\": \"Internal server error\"}"));
	}
	*/
	
	@Test
	public void testBadAppDataCharacters(){
		try {
			article = articleAPI.getPGOArticleJSON("$%#$", "38");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue("Asserts that the data returned for a bad app datapoint is an internal server error",
				articleAPI.entity.equals(""));
	}
}
