package Tests;

import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.ParseException;
import org.junit.Before;
import org.junit.Test;
import APIControllers.ArticleAPIController;
import DataClasses.ArticleData;
import DataClasses.LandmarkData;
import DataClasses.PGNFact;
import DataClasses.PGNPersonsData;
import DataClasses.PGOScreen;
import DataClasses.SymbolData;
import DataClasses.TimelineData;

public class PGNArticleAPITests {
	public ArticleAPIController articleAPI = new ArticleAPIController();
	public ArticleData article;
	
	@Before
	public void executeBefore(){
		try {
			article = articleAPI.getPGNArticleJSON("3", "6023");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testArticleAPIStatusCode() throws ParseException, IOException{
		assertTrue("Asserts that we can get an article with a valid status code", articleAPI.statusCode.equals("HTTP/1.1 200 OK"));
	}
	
	@Test
	public void testTitleKeyExists(){
		assertTrue("Asserts that the article returned has a title key", 
				articleAPI.parsedData.has("title"));
	}
	
	@Test
	public void testPGNArticleTitle(){
		assertTrue("Asserts that the article returned has the correct title data", article.title.equals("Minnesota"));
	}
	
	@Test
	public void testPGNArticleSortKeyExists(){
		assertTrue("Asserts that the article returned has a sort key", 
				articleAPI.parsedData.has("sort"));
	}
	
	@Test
	public void testPGNArticleSort(){
		assertTrue("Asserts that the article returned has the correct sort data", article.sort.equals("23"));
	}
	
	@Test
	public void testPGNArticleTypeKeyExists(){
		assertTrue("Asserts that the article returned has a type key", 
				articleAPI.parsedData.has("type"));
	}
	
	@Test
	public void testPGNArticleType(){
		assertTrue("Asserts that the article returned has the correct sort data", article.type.equals("A"));
	}
	
	@Test
	public void testPGNArticleVideoKeysExist(){
		assertTrue("Asserts that the article returned has a Fact key", 
				articleAPI.parsedData.get("videos").getAsJsonArray().get(0).getAsJsonObject().has("id"));
		assertTrue("Asserts that the article returned has a Fact key", 
				articleAPI.parsedData.get("videos").getAsJsonArray().get(0).getAsJsonObject().has("video"));
	}
	
	@Test
	public void testPGNArticleVideo(){
		assertTrue("Asserts that the article returned has the correct video id data", 
				article.videos.get(0).getAsJsonObject().get("id").getAsString().equals("775"));
		assertTrue("Asserts that the article returned has the correct video title data",
				article.videos.get(0).getAsJsonObject().get("video").getAsString().equals("pgnstates_minnesota_video_0.mp4"));
	}
	
	@Test
	public void testPGNArticleVideoValid(){
		assertTrue("Asserts that the article returned has the correct video title data",
				articleAPI.verifyVideoActive(article.videos.get(0).getAsJsonObject().get("video").getAsString()));
	}
	
	@Test
	public void testPGNArticleUSOnlyKeyExists(){
		assertTrue("Asserts that the article returned has a us only key", 
				articleAPI.parsedData.has("us_only"));
	}
	
	@Test
	public void testPGNArticleUSOnly(){
		assertTrue("Asserts that the article returned has the correct US Only data", 
				article.usOnly.equals("0"));
	}
	
	@Test
	public void testPGNArticleBaseNameKeyExists(){
		assertTrue("Asserts that the article returned has a basename key", 
				articleAPI.parsedData.has("base_name"));
	}
	
	@Test
	public void testPGNArticleBaseName(){
		assertTrue("Asserts that the article returned has the correct BaseName data", 
				article.baseName.equals("pgnstates_minnesota"));
	}
	
	@Test
	public void testPGNArticlModuleIDKeyExists(){
		assertTrue("Asserts that the article returned has a module id key", 
				articleAPI.parsedData.has("module_id"));
	}
	
	@Test
	public void testPGNArticleModuleID(){
		assertTrue("Asserts that the article returned has the correct Module ID data",
				article.moduleID.equals("6"));
	}
	
	@Test
	public void testPGNArticleArticleIDKeyExists(){
		assertTrue("Asserts that the article returned has an article id key", 
				articleAPI.parsedData.has("article_id"));
	}
	
	@Test
	public void testPGNArticleArticleID(){
		assertTrue("Asserts that the article returned has the correct Article ID data",
				article.articleID.equals("6023"));
	}
	
	@Test
	public void testPGNArticleMainImageKeyExists(){
		assertTrue("Asserts that the article returned has a main_image key", 
				articleAPI.parsedData.has("main_image"));
	}
	
	@Test
	public void testPGNArticleMainImage(){
		assertTrue("Asserts that the article returned has the correct Main Image data",
				article.mainImage.equals("pgnstates_minnesota.jpg"));
	}
	
	@Test
	public void testPGNArticleMainImageValid(){
		assertTrue("Asserts that the article returned has the correct Main Image data",
				articleAPI.verifyImageActive(article.mainImage));
	}
	
	@Test
	public void testPGNArticleMenuAudioKeyExists(){
		assertTrue("Asserts that the article returned has a menu_audio key", 
				articleAPI.parsedData.has("menu_audio"));
	}
	
	@Test
	public void testPGNArticleMenuAudio(){
		assertTrue("Asserts that the article returned has the correct Menu Audio data",
				article.menuAudio.equals("title_minnesota.mp3"));
	}
	
	@Test
	public void testPGNArticleTexasOnlyKeyExists(){
		assertTrue("Asserts that the article returned has a texas_only key", 
				articleAPI.parsedData.has("texas_only"));
	}
	
	@Test
	public void testPGNArticleTexasOnly(){
		assertTrue("Asserts that the article returned has the correct Texas Only data",
				article.texasOnly.equals("0"));
	}
	
	@Test
	public void testPGNArticleLanguageIDKeyExists(){
		assertTrue("Asserts that the article returned has a language_id key", 
				articleAPI.parsedData.has("language_id"));
	}
	
	@Test
	public void testPGNArticleLanguageID(){
		assertTrue("Asserts that the article returned has the correct Language ID data",
				article.languageID.equals("1"));
	}
	
	@Test
	public void testPGNArticleSongKeyExists(){
		assertTrue("Asserts that the article returned has an article_song key", 
				articleAPI.parsedData.has("article_song"));
	}
	
	@Test
	public void testPGNArticleSongInfoKeyExists(){
		assertTrue("Asserts that the article returned has an info key in the article_song data", 
				articleAPI.parsedData.get("article_song").getAsJsonObject().has("info"));
	}
	
	@Test
	public void testPGNArticleSongInfo(){
		assertTrue("Asserts that the article returned has the correct song info", 
				article.song.info.equals("by Truman E. Rickard and Arthur E. Upson"));
	}
	
	@Test
	public void testPGNArticleSongTextKeyExists(){
		assertTrue("Asserts that the article returned has a text key in the song data", 
				articleAPI.parsedData.get("article_song").getAsJsonObject().has("text"));
	}
	
	@Test
	public void testPGNArticleSongText(){
		String song = "Minnesota, hail to thee!\nHail to thee, our state so dear! "+
				"\nThy light shall ever be\nA beacon bright and clear.\nThy sons and daughters true\nWill proclaim thee near and far, " +
				"\nThey shall guard thy fame\nAnd adore thy name;\nThou shalt be their Northern Star.\n\nLike the stream that bends to sea, "+
				"\nLike the pine that seeks the blue, \nMinnesota, still for thee\nThy sons are strong and true. "+
				"\nFrom the woods and waters fair, \nFrom the prairies waving far,\nAt thy call they throng\nWith their shout and song, "+
				"\nHailing thee their Northern Star.";
		
		assertTrue("Asserts that the article returned has the correct song text", 
				article.song.text.equals(song));
	}
	
	@Test
	public void testPGNArticleSongTitleKeyExists(){
		assertTrue("Asserts that the article returned has a title key in the song data", 
				articleAPI.parsedData.get("article_song").getAsJsonObject().has("title"));
	}
	
	@Test
	public void testPGNArticleSongTitle(){
		assertTrue("Asserts that the article returned has the correct song title", 
				article.song.title.equals("“Hail! Minnesota”"));
	}
	
	@Test
	public void testPGNArticleSongIDKeyExists(){
		assertTrue("Asserts that the article returned has an ID key in the song data key", 
				articleAPI.parsedData.get("article_song").getAsJsonObject().has("song_id"));
	}
	
	@Test
	public void testPGNArticleSongID(){
		assertTrue("Asserts that the article returned has the correct song id", 
				article.song.songID.equals("38"));
	}
	
	@Test
	public void testPGNArticleSongArticleIDKeyExists(){
		assertTrue("Asserts that the article returned has an Article ID key in the song data key", 
				articleAPI.parsedData.get("article_song").getAsJsonObject().has("article_id"));
	}
	
	@Test
	public void testPGNArticleSongArticleID(){
		assertTrue("Asserts that the article returned has the correct song article id", 
				article.song.articleID.equals("6023"));
	}
	
	@Test
	public void testPGNArticleMapFileNameKeyExists(){
		assertTrue("Asserts that the article returned has a map key", 
				articleAPI.parsedData.has("map_filename"));
	}
	
	@Test
	public void testPGNArticleTranslationsKeyExists(){
		assertTrue("Asserts that the article returned has a translations key", 
				articleAPI.parsedData.has("translations"));
	}
	
	@Test
	public void testPGNArticleFactsKeyExists(){
		assertTrue("Asserts that the article returned has an article facts key", 
				articleAPI.parsedData.has("article_facts"));
	}
	
	@Test
	public void testPGNArticleFactsSortKeyExists(){
		assertTrue("Asserts that the article returned has a sort key in the article facts data", 
				articleAPI.parsedData.get("article_facts").getAsJsonArray().get(0).getAsJsonObject().has("sort"));
	}
	
	@Test
	public void testPGNArticleFactsSortData(){
		assertTrue("Asserts that the article returned has the correct fact sort date",
				article.pgnFacts.get(0).sort.equals("0"));
		assertTrue("Asserts that the article returned has the correct fact sort date",
				article.pgnFacts.get(7).sort.equals("7"));
		assertTrue("Asserts that the article returned has the correct fact sort date",
				article.pgnFacts.get(13).sort.equals("13"));
	}
	
	@Test
	public void testPGNFactTextKeyExists(){
		assertTrue("Asserts that the article returned has a text key in the article facts data", 
				articleAPI.parsedData.get("article_facts").getAsJsonArray().get(0).getAsJsonObject().has("text"));
	}
	
	@Test
	public void testPGNArticleFactsTextData(){
		assertTrue("Asserts that the article returned has the correct fact sort date",
				article.pgnFacts.get(0).text.equals("1858"));
		assertTrue("Asserts that the article returned has the correct fact sort date",
				article.pgnFacts.get(7).text.equals("dairy, soybeans, corn, wheat, wild rice, hogs, beef cattle, sugar beets"));
		assertTrue("Asserts that the article returned has the correct fact sort date",
				article.pgnFacts.get(13).text.equals("<ol><li>The first Super Computer was created in Minneapolis.</li><li>"+
						"President Theodore Roosevelt’s famous phrase “speak softly, and carry a big stick” was first said at"+
						" the Minnesota State Fair.</li><li>No Civil War battles were fought in Minnesota, but when President "+
						"Lincoln asked for volunteers, half of the state’s eligible men signed up to fight.</li><li>The world’s "+
						"first successful open-heart surgery operation was performed at the University of Minnesota in 1952. "+
						"The first successful bone marrow transplant between nontwins was also performed there in 1968.</li><li>"+
						"Frank C. Mars, inventor of the Milky Way, Snickers, and 3 Musketeers candy bars, was born in Minneapolis.</li></ol>"));
	}
	
	@Test
	public void testPGNFactImageKeyExists(){
		assertTrue("Asserts that the article returned has an image key in the article facts data", 
				articleAPI.parsedData.get("article_facts").getAsJsonArray().get(0).getAsJsonObject().has("image"));
	}
	
	@Test
	public void testPGNFactImageDataExists(){
		assertTrue("Asserts that the article returned has the correct fact image",
				article.pgnFacts.get(12).image.equals("pgnstates_minnesota_fact_12.jpg"));
	}
	
	@Test
	public void testPGNFactImages(){
		for(PGNFact p : article.pgnFacts){
			if(!(p.image.equals("") || p.image.equals("null"))){
				if(!articleAPI.verifyImageActive(p.image)){
					System.out.println(p.image + " is not active");
				}
				assertTrue("Asserts that the article returned has a valid fact image",
						articleAPI.verifyImageActive(p.image));
			}
		}
	}
	
	@Test
	public void testPGNFactTitleKeyExists(){
		assertTrue("Asserts that the article returned has a title key in the article fact data", 
				articleAPI.parsedData.get("article_facts").getAsJsonArray().get(0).getAsJsonObject().has("title"));
	}
	
	@Test
	public void testPGNArticleFactsTitleData(){
		assertTrue("Asserts that the article returned has the correct fact title",
				article.pgnFacts.get(0).title.equals("Statehood"));
		assertTrue("Asserts that the article returned has the correct fact title",
				article.pgnFacts.get(7).title.equals("Farm Products"));
		assertTrue("Asserts that the article returned has the correct fact title",
				article.pgnFacts.get(13).title.equals("Fun Facts"));
	}
	
	@Test
	public void testPGNFactFactIDKeyExists(){
		assertTrue("Asserts that the article returned has a fact_id in the article fact data", 
				articleAPI.parsedData.get("article_facts").getAsJsonArray().get(0).getAsJsonObject().has("fact_id"));
	}
	
	@Test
	public void testPGNArticleFactsIDData(){
		assertTrue("Asserts that the article returned has the correct fact id data",
				article.pgnFacts.get(0).factID.equals("480"));
		assertTrue("Asserts that the article returned has the correct fact id data",
				article.pgnFacts.get(7).factID.equals("487"));
		assertTrue("Asserts that the article returned has the correct fact id data",
				article.pgnFacts.get(13).factID.equals("693"));
	}
	
	@Test
	public void testPGNFactFactTimeFileKeyExists(){
		assertTrue("Asserts that the article returned has a timefile key in the article fact data", 
				articleAPI.parsedData.get("article_facts").getAsJsonArray().get(0).getAsJsonObject().has("time_file"));
	}
	
	@Test
	public void testPGNArticleFactsTimeFileData(){
		assertTrue("Asserts that the article returned has the correct fact xml data",
				article.pgnFacts.get(0).timefile.equals("pgnstates_minnesota_fact_0.xml"));
		assertTrue("Asserts that the article returned has the correct fact xml data",
				article.pgnFacts.get(7).timefile.equals("pgnstates_minnesota_fact_7.xml"));
		assertTrue("Asserts that the article returned has the correct fact xml data",
				article.pgnFacts.get(13).timefile.equals("pgnstates_minnesota_fact_13.xml"));
	}
	
	@Test
	public void testPGNArticleFactsTimeFileDataValid(){
		for(PGNFact p : article.pgnFacts){
			if(!(p.timefile.equals("") || p.timefile.equals("null"))){
				if(!articleAPI.verifyXMLActive(p.timefile)){
					System.out.println(p.timefile + " is not active");
				}
				assertTrue("Asserts that the article returned has a valid fact timecode",
						articleAPI.verifyXMLActive(p.timefile));
			}
		}
	}
	
	@Test
	public void testPGNFactFactsAudioFileKeyExists(){
		assertTrue("Asserts that the article returned has a audio file key in the article facts data", 
				articleAPI.parsedData.get("article_facts").getAsJsonArray().get(0).getAsJsonObject().has("audio_file"));
	}
	
	@Test
	public void testPGNArticleFactsAudioFileData(){
		assertTrue("Asserts that the article returned has the correct fact mp3 date",
				article.pgnFacts.get(0).audioFile.equals("pgnstates_minnesota_fact_0.mp3"));
		assertTrue("Asserts that the article returned has the correct fact mp3 date",
				article.pgnFacts.get(7).audioFile.equals("pgnstates_minnesota_fact_7.mp3"));
		assertTrue("Asserts that the article returned has the correct fact mp3 date",
				article.pgnFacts.get(13).audioFile.equals("pgnstates_minnesota_fact_13.mp3"));
	}
	
	@Test
	public void testPGNArticleFactsAudioFileDataValid(){
		for(PGNFact p : article.pgnFacts){
			if(!(p.audioFile.equals("") || p.audioFile.equals("null"))){
				if(!articleAPI.verifyAudioActive(p.audioFile)){
					System.out.println(p.audioFile + " is not active");
				}
				assertTrue("Asserts that the article returned has a valid fact mp3 file",
						articleAPI.verifyAudioActive(p.audioFile));
			}
		}
	}
	
	@Test
	public void testPGNFactFactsTextTimecodedKeyExists(){
		assertTrue("Asserts that the article returned has a text timecoded key in the article facts data", 
				articleAPI.parsedData.get("article_facts").getAsJsonArray().get(0).getAsJsonObject().has("text_timecoded"));
	}
	
	@Test
	public void testPGNArticleTextTimecodedFileData(){
		assertTrue("Asserts that the article returned has the correct fact text timecode data",
				article.pgnFacts.get(0).textTimecoded.equals(new ArrayList<String>(Arrays.asList("1858"))));
		assertTrue("Asserts that the article returned has the correct fact text timecode data",
				article.pgnFacts.get(7).textTimecoded.equals(new ArrayList<String>(Arrays.asList("dairy,", "soybeans,", "corn,", "wheat,", "wild", "rice,", "hogs,", "beef", "cattle,", "sugar", "beets"))));
		assertTrue("Asserts that the article returned has the correct fact text timecode data",
				article.pgnFacts.get(13).textTimecoded.equals(new ArrayList<String>(Arrays.asList("The", "first", "Super", "Computer", "was",
						"created", "in", "Minneapolis.", "President", "Theodore", "Roosevelt’s", "famous", "phrase", "speak", "softly,", 
						"and", "carry", "a", "big", "stick", "was", "first", "said", "at", "the", "Minnesota", "State", "Fair.", "No", 
						"Civil", "War", "battles", "were", "fought", "in", "Minnesota,", "but", "when", "President", "Lincoln", "asked",
						"for", "volunteers,", "half", "of", "the", "state’s", "eligible", "men", "signed", "up", "to", "fight.", "The",
						"world’s", "first", "successful", "open-heart", "surgery", "operation", "was", "performed", "at", "the", "University",
						"of", "Minnesota", "in", "1952.", "The", "first", "successful", "bone", "marrow", "transplant", "between", "nontwins",
						"was", "also", "performed", "there", "in", "1968.", "Frank", "C.", "Mars,", "inventor", "of", "the", "Milky",
						"Way,", "Snickers,", "and", "3", "Musketeers", "candy", "bars,", "was", "born", "in", "Minneapolis."))));
	}
	
	@Test
	public void testPGNFactFactsTitleimecodedKeyExists(){
		assertTrue("Asserts that the article returned has a title timecoded key in the article facts data", 
				articleAPI.parsedData.get("article_facts").getAsJsonArray().get(0).getAsJsonObject().has("title_timecoded"));
	}
	
	@Test
	public void testPGNArticleTitleTimecodedFileData(){
		assertTrue("Asserts that the article returned has the correct fact text timecode data",
				article.pgnFacts.get(0).titleTimecoded.equals(new ArrayList<String>(Arrays.asList("Statehood"))));
		assertTrue("Asserts that the article returned has the correct fact text timecode data",
				article.pgnFacts.get(7).titleTimecoded.equals(new ArrayList<String>(Arrays.asList("Farm", "Products"))));
		assertTrue("Asserts that the article returned has the correct fact text timecode data",
				article.pgnFacts.get(13).titleTimecoded.equals(new ArrayList<String>(Arrays.asList("Fun Facts"))));
	}
	
	@Test
	public void testPGNRecipeKeyExists(){
		assertTrue("Asserts that the article returned has a Recipe key", 
				articleAPI.parsedData.has("article_recipe"));
	}
	
	@Test
	public void testPGNRecipeTextKeyExists(){
		assertTrue("Asserts that the article returned has a text key in the article recipe data", 
				articleAPI.parsedData.get("article_recipe").getAsJsonObject().has("text"));
	}
	
	@Test
	public void testPGNRecipeTextDataExists(){
		assertTrue("Asserts that the article returned has recipe text data", 
				article.recipe.text.equals("<h2>Ingredients</h2>\n1⁄2 cup (120 mL) butter, softened\n1 1⁄4 cups (300 mL) sugar\n2 eggs"+
						"\n2 cups (480 mL) flour\n1⁄2 teaspoon (2.5 mL) salt\n2 teaspoons (10 mL) baking powder\n1⁄2 cup (120 mL) milk"+
						"\n2 cups (480 mL) blueberries\n3 teaspoons (15 mL) sugar\n1 teaspoon (5 mL) orange peel, grated\n\n<h2>Equipment"+
						"</h2>\ndry-ingredient measuring cups\nliquid measuring cups\nmeasuring spoons\nelectric hand mixer\nlarge mixing bowl"+
						"\n2 small mixing bowls\nfork\nmuffin pan\nmuffin cup liners or nonstick\ncooking spray\n\n<h2>What You Do</h2>\n1. "+
						"Preheat oven to 375°F (190°C).\n2. Use a hand mixer to mix the butter and 1 1⁄4 cups sugar in a large bowl. Mix until "+
						"light and creamy. Add eggs one at a time, beating well after each.\n3. Use a spoon to mix the flour, salt, and baking "+
						"powder together in a small bowl.\n4. Add a little of the dry mixture to the egg and butter mixture. Then add a little of "+
						"the milk. Alternate the dry mixture and milk until used.\n5. Crush 1⁄2 cup blueberries with a fork in a small bowl. Mix "+
						"into batter. Mix in the remaining whole berries and add orange peel.\n6. Put muffin cup liners into the muffin pan cups or "+
						"spray muffin pan cups with nonstick cooking spray.\n7. Fill cups about ⅔ full.\n8. Sprinkle tops with 3 teaspoons sugar. "+
						"Bake for 20 to 30 minutes. Remove from oven. Cool for 30 minutes before removing from muffin pan.\nMakes 6 servings"));
	}
	
	@Test
	public void testPGNRecipeAudioKeyExists(){
		assertTrue("Asserts that the article returned has an audio key in the article recipe data", 
				articleAPI.parsedData.get("article_recipe").getAsJsonObject().has("audio"));
	}
	
	@Test
	public void testPGNRecipeAudioDataExists(){
		assertTrue("Asserts that the article returned has recipe audio data", 
				article.recipe.audio.equals("label_blueberry_muffins.wav"));
	}
	
	@Test
	public void testPGNRecipeImageKeyExists(){
		assertTrue("Asserts that the article returned has an image key in the article recipe data", 
				articleAPI.parsedData.get("article_recipe").getAsJsonObject().has("image"));
	}
	
	@Test
	public void testPGNRecipeImageDataExists(){
		assertTrue("Asserts that the article returned has recipe image data", 
				article.recipe.image.equals("pgnstates_minnesota_recipe.jpg"));
	}
	
	@Test
	public void testPGNRecipeImageDataValid(){
		assertTrue("Asserts that the article returned has a valid recipe image", 
				articleAPI.verifyImageActive(article.recipe.image));
	}
	
	@Test
	public void testPGNRecipeIntroKeyExists(){
		assertTrue("Asserts that the article returned has an intro key in the article recipe data", 
				articleAPI.parsedData.get("article_recipe").getAsJsonObject().has("intro"));
	}
	
	@Test
	public void testPGNRecipeIntroDataExists(){
		assertTrue("Asserts that the article returned has recipe intro data", 
				article.recipe.intro.equals("Blueberries grow wild in the northeastern forests of Minnesota. The blueberry muffin is the official state muffin."));
	}
	
	@Test
	public void testPGNRecipeTitleKeyExists(){
		assertTrue("Asserts that the article returned has an title key in the article recipe data", 
				articleAPI.parsedData.get("article_recipe").getAsJsonObject().has("title"));
	}
	
	@Test
	public void testPGNRecipeTitleDataExists(){
		assertTrue("Asserts that the article returned has recipe title data", 
				article.recipe.title.equals("Blueberry Muffins"));
	}
	
	@Test
	public void testPGNRecipeRecipeIDKeyExists(){
		assertTrue("Asserts that the article returned has an title key in the article recipe data", 
				articleAPI.parsedData.get("article_recipe").getAsJsonObject().has("recipe_id"));
	}
	
	@Test
	public void testPGNRecipeRecipeIDDataExists(){
		assertTrue("Asserts that the article returned has recipe title data", 
				article.recipe.recipeID.equals("38"));
	}
	
	@Test
	public void testPGNPersonsKeyExists(){
		assertTrue("Asserts that the article returned has a persons key", 
				articleAPI.parsedData.has("article_persons"));
	}
	
	@Test
	public void testPGNPersonsTextKeyExists(){
		assertTrue("Asserts that the article returned has a text key in the article persons data", 
				articleAPI.parsedData.get("article_persons").getAsJsonArray().get(0).getAsJsonObject().has("text"));
	}
	
	@Test
	public void testPGNPersonsTextDataExists(){
		assertTrue("Asserts that the article returned has persons text data", 
				article.pgnPersons.get(0).text.equals("Prince Rogers Nelson (1958–2016) was a rock music star. He was known as \"Prince.\" "+
						"His albums include Purple Rain, Sign \"O\" the Times, and 20Ten. Prince was born in Minneapolis."));
		assertTrue("Asserts that the article returned has persons text data", 
				article.pgnPersons.get(3).text.equals("Walter Mondale (1928–present) represented Minnesota in the United States Senate from "+
						"1964 to 1976. He was the U.S. vice president under President Jimmy Carter (1977–1981). Mondale ran for president in "+
						"1984 but lost. His running mate was Geraldine Ferraro, the first woman chosen as a vice-presidential candidate. Mondale was born in Ceylon."));
		assertTrue("Asserts that the article returned has persons text data", 
				article.pgnPersons.get(5).text.equals("Sinclair Lewis (1885–1951) was an author whose novels attacked the weaknesses he saw in "+
						"U.S. society. In 1930 he became the first American to win the Nobel Prize for literature. Lewis was born in Sauk Centre."));
	}
	
	@Test
	public void testPGNPersonsImageKeyExists(){
		assertTrue("Asserts that the article returned has a image key in the article persons data", 
				articleAPI.parsedData.get("article_persons").getAsJsonArray().get(0).getAsJsonObject().has("image"));
	}
	
	@Test
	public void testPGNPersonsImageDataExists(){
		assertTrue("Asserts that the article returned has persons image data", 
				article.pgnPersons.get(0).image.equals("pgnstates_minnesota_person_1.jpg"));
		assertTrue("Asserts that the article returned has persons image data", 
				article.pgnPersons.get(3).image.equals("pgnstates_minnesota_person_4.jpg"));
		assertTrue("Asserts that the article returned has persons image data", 
				article.pgnPersons.get(5).image.equals("pgnstates_minnesota_person_6.jpg"));
	}
	
	@Test
	public void testPGNPersonsImageDataValid(){
		for(PGNPersonsData p : article.pgnPersons){
			if(!(p.image.equals("") || p.image.equals("null"))){
				if(!articleAPI.verifyImageActive(p.image)){
					System.out.println(p.image + " is not active");
				}
				assertTrue("Asserts that the article returned has a valid persons image file",
						articleAPI.verifyImageActive(p.image));
			}
		}
	}
	
	@Test
	public void testPGNPersonsPersonKeyExists(){
		assertTrue("Asserts that the article returned has a person key in the article persons data", 
				articleAPI.parsedData.get("article_persons").getAsJsonArray().get(0).getAsJsonObject().has("person"));
	}
	
	@Test
	public void testPGNPersonsPersonDataExists(){
		assertTrue("Asserts that the article returned has person data", 
				article.pgnPersons.get(0).person.equals("empty"));
		assertTrue("Asserts that the article returned has person data", 
				article.pgnPersons.get(3).person.equals("empty"));
		assertTrue("Asserts that the article returned has person data", 
				article.pgnPersons.get(5).person.equals("empty"));
	}
	
	@Test
	public void testPGNPersonsIDKeyExists(){
		assertTrue("Asserts that the article returned has a person id key in the article persons data", 
				articleAPI.parsedData.get("article_persons").getAsJsonArray().get(0).getAsJsonObject().has("person_id"));
	}
	
	@Test
	public void testPGNPersonsPersonIDDataExists(){
		assertTrue("Asserts that the article returned has person id data", 
				article.pgnPersons.get(0).personID.equals("263"));
		assertTrue("Asserts that the article returned has person id data", 
				article.pgnPersons.get(3).personID.equals("266"));
		assertTrue("Asserts that the article returned has person id data", 
				article.pgnPersons.get(5).personID.equals("268"));
	}
	
	@Test
	public void testPGNPersonsTimeFileKeyExists(){
		assertTrue("Asserts that the article returned has a time file key in the article persons data", 
				articleAPI.parsedData.get("article_persons").getAsJsonArray().get(0).getAsJsonObject().has("time_file"));
	}
	
	@Test
	public void testPGNPersonsTimeFileDataExists(){
		assertTrue("Asserts that the article returned has person time file data", 
				article.pgnPersons.get(0).timeFile.equals("pgnstates_minnesota_person_1.xml"));
		assertTrue("Asserts that the article returned has person time file data", 
				article.pgnPersons.get(3).timeFile.equals("pgnstates_minnesota_person_4.xml"));
		assertTrue("Asserts that the article returned has person time file data", 
				article.pgnPersons.get(5).timeFile.equals("pgnstates_minnesota_person_6.xml"));
	}
	
	@Test
	public void testPGNPersonsTimeFileDataValid(){
		for(PGNPersonsData p : article.pgnPersons){
			if(!(p.timeFile.equals("") || p.timeFile.equals("null"))){
				if(!articleAPI.verifyXMLActive(p.timeFile)){
					System.out.println(p.image + " is not active");
				}
				assertTrue("Asserts that the article returned has a valid persons timefile",
						articleAPI.verifyXMLActive(p.timeFile));
			}
		}
	}
	
	@Test
	public void testPGNPersonsAudioFileKeyExists(){
		assertTrue("Asserts that the article returned has a audio file key in the article persons data", 
				articleAPI.parsedData.get("article_persons").getAsJsonArray().get(0).getAsJsonObject().has("audio_file"));
	}
	
	@Test
	public void testPGNPersonsAudioFileDataExists(){
		assertTrue("Asserts that the article returned has person audio file data", 
				article.pgnPersons.get(0).audioFile.equals("pgnstates_minnesota_person_1.mp3"));
		assertTrue("Asserts that the article returned has person audio file data", 
				article.pgnPersons.get(3).audioFile.equals("pgnstates_minnesota_person_4.mp3"));
		assertTrue("Asserts that the article returned has person audio file data", 
				article.pgnPersons.get(5).audioFile.equals("pgnstates_minnesota_person_6.mp3"));
	}
	
	@Test
	public void testPGNPersonsAudioFileDataValid(){
		for(PGNPersonsData p : article.pgnPersons){
			if(!(p.audioFile.equals("") || p.audioFile.equals("null"))){
				if(!articleAPI.verifyAudioActive(p.audioFile)){
					System.out.println(p.audioFile + " is not active");
				}
				assertTrue("Asserts that the article returned has a valid persons timefile",
						articleAPI.verifyAudioActive(p.audioFile));
			}
		}
	}
	
	@Test
	public void testPGNPersonsTextTimecodedKeyExists(){
		assertTrue("Asserts that the article returned has a text timecoded key in the article persons data", 
				articleAPI.parsedData.get("article_persons").getAsJsonArray().get(0).getAsJsonObject().has("text_timecoded"));
	}
	
	@Test
	public void testPGNPersonsTextTimecodedFileData(){
		assertTrue("Asserts that the article returned has the correct fact text timecode data",
				article.pgnPersons.get(0).textTimecoded.equals(new ArrayList<String>(Arrays.asList("Prince", "Rogers", "Nelson", "(1958–2016)", "was", "a", "rock", "music", "star.", "He", "was",
						"known", "as", "Prince.", "His", "albums", "include", "Purple", "Rain,", "Sign", "O", "the", "Times,", "and", "20Ten.", "Prince", "was", "born", "in", "Minneapolis."))));
		assertTrue("Asserts that the article returned has the correct fact text timecode data",
				article.pgnPersons.get(3).textTimecoded.equals(new ArrayList<String>(Arrays.asList("Walter", "Mondale", "(1928–present)", "represented", "Minnesota", "in", "the", 
						"United", "States", "Senate", "from", "1964", "to", "1976.", "He", "was", "the", "U.S.", "vice", "president", "under", "President", "Jimmy", "Carter", "(1977–1981).",
						"Mondale", "ran", "for", "president", "in", "1984", "but", "lost.", "His", "running", "mate", "was", "Geraldine", "Ferraro,", "the", "first", "woman", "chosen", "as",
						"a", "vice-presidential", "candidate.", "Mondale", "was", "born", "in", "Ceylon."))));
		assertTrue("Asserts that the article returned has the correct fact text timecode data",
				article.pgnPersons.get(5).textTimecoded.equals(new ArrayList<String>(Arrays.asList("Sinclair", "Lewis", "(1885–1951)", "was", "an", "author", "whose", "novels", "attacked",
						"the", "weaknesses", "he", "saw", "in", "U.S.", "society.", "In", "1930", "he", "became", "the", "first", "American", "to", "win", "the", "Nobel", "Prize", "for",
						"literature.", "Lewis", "was", "born", "in", "Sauk", "Centre."))));
	}
	
	@Test
	public void testPGNArticleScreensKeyExists(){
		assertTrue("Asserts that the article returned a articleScreens key", 
				articleAPI.parsedData.has("article_screens"));
	}
	
	@Test
	public void testPGNArticleScreensTitleKeyExists(){
		assertTrue("Asserts that the article returned has a title key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("title"));
	}
	
	@Test
	public void testPGNArticleScreenTitleDataExists(){
		assertTrue("Asserts that the article returned has the correct Screen Title data",
				article.articleScreens.get(0).title.equals("Location"));
		assertTrue("Asserts that the article returned has the correct Screen Title data",
				article.articleScreens.get(4).title.equals("People"));
	}
	
	@Test
	public void testPGNArticleScreensNumberKeyExists(){
		assertTrue("Asserts that the article returned has a number key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("number"));
	}
	
	@Test
	public void testPGNArticleScreenNumberDataExists(){
		assertTrue("Asserts that the article returned has the correct Screen Number data",
				article.articleScreens.get(0).number.equals("0"));
		assertTrue("Asserts that the article returned has the correct Screen Number data",
				article.articleScreens.get(4).number.equals("4"));
	}
	
	@Test
	public void testPNOArticleScreensContentKeyExists(){
		assertTrue("Asserts that the article returned has a content key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("content"));
	}
	
	@Test
	public void testPGNArticleScreenContentDataExists(){
		assertTrue("Asserts that the article returned has the correct Screen Number data",
				article.articleScreens.get(0).content.equals("Minnesota sits at the center of the long border between the United States and Canada. "+
						"North Dakota and South Dakota lie to the west. Iowa is on the south. Wisconsin and Lake Superior line Minnesota’s eastern edge. "
						+"Minnesota’s capital, St. Paul, lies on the east bank of the Mississippi River. Minneapolis, St. Paul, and Rochester are Minnesota’s largest cities."));
		assertTrue("Asserts that the article returned has the correct Screen Number data",
				article.articleScreens.get(4).content.equals("White people are the largest population group in Minnesota. More than 4 million Minnesotans are white. "+
						"African-Americans form the second-largest ethnic group. Many African-Americans have come to the state since the 1940s. Today more than 260,000 "+
						"African-Americans live in the state. About 250,000 Hispanics also reside in Minnesota. Asians began moving into the state in the late 1900s. "+
						"Hmong came in the 1980s from Laos, Vietnam, and Thailand. In the late 1990s and early 2000s, many Somalis left their war-torn country in Africa and moved to Minnesota."));
	}
	
	@Test
	public void testPGNArticleScreensLayoutIDKeyExists(){
		assertTrue("Asserts that the article returned has a layout id key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("layout_id"));
	}
	
	@Test
	public void testPGNArticleScreenLayoutIDDataExists(){
		assertTrue("Asserts that the article returned has the correct Screen Layout ID data",
				article.articleScreens.get(0).layoutID.equals("2"));
		assertTrue("Asserts that the article returned has the correct Screen Layout ID data",
				article.articleScreens.get(4).layoutID.equals("7"));
	}
	
	@Test
	public void testPGNArticleScreensScreenIDKeyExists(){
		assertTrue("Asserts that the article returned has a screen id key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("screen_id"));
	}
	
	@Test
	public void testPGNArticleScreenIDDataExists(){
		assertTrue("Asserts that the article returned has the correct Screen ID data",
				article.articleScreens.get(0).screenID.equals("4314"));
		assertTrue("Asserts that the article returned has the correct Screen ID data",
				article.articleScreens.get(4).screenID.equals("4318"));
	}
	
	@Test
	public void testPGNArticleScreensTimeFileKeyExists(){
		assertTrue("Asserts that the article returned has a time file key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("time_file"));
	}
	
	@Test
	public void testPGNArticleScreenTimefileDataExists(){
		assertTrue("Asserts that the article returned has the correct Screen Timefile data",
				article.articleScreens.get(0).timeFile.equals("pgnstates_minnesota_scr_0.xml"));
		assertTrue("Asserts that the article returned has the correct Screen Timefile data",
				article.articleScreens.get(4).timeFile.equals("pgnstates_minnesota_scr_4.xml"));
	}
	
	@Test
	public void testPGNArticleScreenTimefileDataValid(){
		for(PGOScreen p : article.articleScreens){
			if(!(p.timeFile.equals("") || p.timeFile.equals("null") || p.timeFile.equals("empty"))){
				if(!articleAPI.verifyXMLActive(p.timeFile)){
					System.out.println(p.timeFile + " is not active");
				}
				assertTrue("Asserts that the article returned has a valid persons timefile",
						articleAPI.verifyXMLActive(p.timeFile));
			}
		}
	}
	
	@Test
	public void testPGNArticleScreensAudioFileKeyExists(){
		assertTrue("Asserts that the article returned has a audio file key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("audio_file"));
	}
	
	@Test
	public void testPGNArticleScreenAudiofileDataExists(){
		assertTrue("Asserts that the article returned has the correct Screen Audio File data",
				article.articleScreens.get(0).audioFile.equals("pgnstates_minnesota_scr_0.mp3"));
		assertTrue("Asserts that the article returned has the correct Screen Audio File data",
				article.articleScreens.get(4).audioFile.equals("pgnstates_minnesota_scr_4.mp3"));
	}
	
	@Test
	public void testPGNArticleScreenAudiofileDataValid(){
		for(PGOScreen p : article.articleScreens){
			if(!(p.audioFile.equals("") || p.audioFile.equals("null") || p.audioFile.equals("empty"))){
				if(!articleAPI.verifyAudioActive(p.audioFile)){
					System.out.println(p.audioFile + " is not active");
				}
				assertTrue("Asserts that the article returned has a valid persons audio file",
						articleAPI.verifyAudioActive(p.audioFile));
			}
		}
	}
	
	@Test
	public void testPGNArticleScreensTitleAudioFileKeyExists(){
		assertTrue("Asserts that the article returned has a title audio file key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("title_audio_file"));
	}
	
	@Test
	public void testPGNArticleScreenTitleAudiofileDataExists(){
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(0).titleAudioFile.equals("empty"));
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(4).titleAudioFile.equals("empty"));
	}
	
	@Test
	public void testPGNArticleScreensContentTimecodedKeyExists(){
		assertTrue("Asserts that the article returned has a content timecoded key in the articleScreens data", 
				articleAPI.parsedData.get("article_screens").getAsJsonArray().get(0).getAsJsonObject().has("content_timecoded"));
	}
	
	@Test
	public void testPGNArticleScreenTimecodeDataExists(){
		ArrayList<String> screenOne = new ArrayList<String>(Arrays.asList("Minnesota", "sits", "at", "the", "center", "of", "the", "long", "border", "between", 
				"the", "United", "States", "and", "Canada.", "North", "Dakota", "and", "South", "Dakota", "lie", "to", "the", "west.", "Iowa", "is", "on", "the",
				"south.", "Wisconsin", "and", "Lake", "Superior", "line", "Minnesota’s", "eastern", "edge.", "Minnesota’s", "capital,", "St.", "Paul,", "lies", "on", 
				"the", "east", "bank", "of", "the", "Mississippi", "River.", "Minneapolis,", "St.", "Paul,", "and", "Rochester", "are", "Minnesota’s", "largest", "cities."));
		ArrayList<String> screenFive = new ArrayList<String>(Arrays.asList("White", "people", "are", "the", "largest", "population", "group", "in", "Minnesota.", "More", "than",
				"4", "million", "Minnesotans", "are", "white.", "African-Americans", "form", "the", "second-largest", "ethnic", "group.", "Many", "African-Americans", "have", 
				"come", "to", "the", "state", "since", "the", "1940s.", "Today", "more", "than", "260,000", "African-Americans", "live", "in", "the", "state.", "About", "250,000", 
				"Hispanics", "also", "reside", "in", "Minnesota.", "Asians", "began", "moving", "into", "the", "state", "in", "the", "late", "1900s.", "Hmong", "came", "in", "the",
				"1980s", "from", "Laos,", "Vietnam,", "and", "Thailand.", "In", "the", "late", "1990s", "and", "early", "2000s,", "many", "Somalis", "left", "their", "war-torn", 
				"country", "in", "Africa", "and", "moved", "to", "Minnesota."));
		
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(0).screenTimeCode.equals(screenOne));
		assertTrue("Asserts that the article returned has the correct Screen Title File data",
				article.articleScreens.get(4).screenTimeCode.equals(screenFive));
	}
	
	@Test
	public void testPGNArticleSymbolsKeyExists(){
		assertTrue("Asserts that the article returned has a article symbol key", 
				articleAPI.parsedData.has("article_symbols"));
	}
	
	@Test
	public void testPGNArticleSymbolsTextKeyExists(){
		assertTrue("Asserts that the article returned has a text key in the article symbol data", 
				articleAPI.parsedData.get("article_symbols").getAsJsonArray().get(0).getAsJsonObject().has("text"));
	}
	
	@Test
	public void testPGNArticleSymbolTextDataExists(){
		assertTrue("Asserts that the article returned has the correct symbol text data",
				article.articleSymbols.get(0).text.equals("Norway pine"));
		assertTrue("Asserts that the article returned has the correct symbol text data",
				article.articleSymbols.get(8).text.equals("Wild rice"));
		assertTrue("Asserts that the article returned has the correct symbol text data",
				article.articleSymbols.get(11).text.equals("Honeycrisp apple"));
	}
	
	@Test
	public void testPGNArticleSymbolsImageKeyExists(){
		assertTrue("Asserts that the article returned has a image key in the article symbol data", 
				articleAPI.parsedData.get("article_symbols").getAsJsonArray().get(0).getAsJsonObject().has("image"));
	}
	
	@Test
	public void testPGNArticleSymbolImageDataExists(){
		assertTrue("Asserts that the article returned has the correct symbol image data",
				article.articleSymbols.get(0).image.equals("pgnstates_minnesota_symbol_1.jpg"));
		assertTrue("Asserts that the article returned has the correct symbol image data",
				article.articleSymbols.get(8).image.equals("pgnstates_minnesota_symbol_9.jpg"));
		assertTrue("Asserts that the article returned has the correct symbol image data",
				article.articleSymbols.get(11).image.equals("pgnstates_minnesota_symbol_12.jpg"));
	}
	
	@Test
	public void testPGNArticleSymbolImageDataValid(){
		for(SymbolData s : article.articleSymbols){
			if(!(s.image.equals("") || s.image.equals("null") || s.image.equals("empty"))){
				if(!articleAPI.verifyImageActive(s.image)){
					System.out.println(s.timeFile + " is not active");
				}
				assertTrue("Asserts that the article returned has a valid persons image",
						articleAPI.verifyImageActive(s.image));
			}
		}
	}
	
	
	@Test
	public void testPGNArticleSymbolsTitleKeyExists(){
		assertTrue("Asserts that the article returned has a title key in the article symbol data", 
				articleAPI.parsedData.get("article_symbols").getAsJsonArray().get(0).getAsJsonObject().has("title"));
	}
	
	@Test
	public void testPGNArticleSymbolTitleDataExists(){
		assertTrue("Asserts that the article returned has the correct symbol title data",
				article.articleSymbols.get(0).title.equals("Tree"));
		assertTrue("Asserts that the article returned has the correct symbol title data",
				article.articleSymbols.get(8).title.equals("Grain"));
		assertTrue("Asserts that the article returned has the correct symbol title data",
				article.articleSymbols.get(11).title.equals("Fruit"));
	}
	
	@Test
	public void testPGNArticleSymbolsIDKeyExists(){
		assertTrue("Asserts that the article returned has a symbol id key in the article symbol id data", 
				articleAPI.parsedData.get("article_symbols").getAsJsonArray().get(0).getAsJsonObject().has("symbol_id"));
	}
	
	@Test
	public void testPGNArticleSymbolIDDataExists(){
		assertTrue("Asserts that the article returned has the correct symbol id data",
				article.articleSymbols.get(0).symbolID.equals("389"));
		assertTrue("Asserts that the article returned has the correct symbol id data",
				article.articleSymbols.get(8).symbolID.equals("397"));
		assertTrue("Asserts that the article returned has the correct symbol id data",
				article.articleSymbols.get(11).symbolID.equals("400"));
	}
	
	@Test
	public void testPGNArticleSymbolsTimeFileKeyExists(){
		assertTrue("Asserts that the article returned has a timefile key in the article symbol time file data", 
				articleAPI.parsedData.get("article_symbols").getAsJsonArray().get(0).getAsJsonObject().has("time_file"));
	}
	
	@Test
	public void testPGNArticleSymbolTimeFileDataExists(){
		assertTrue("Asserts that the article returned has the correct symbol time file data",
				article.articleSymbols.get(0).timeFile.equals("pgnstates_minnesota_symbol_1.xml"));
		assertTrue("Asserts that the article returned has the correct symbol time file data",
				article.articleSymbols.get(8).timeFile.equals("pgnstates_minnesota_symbol_9.xml"));
		assertTrue("Asserts that the article returned has the correct symbol time file data",
				article.articleSymbols.get(11).timeFile.equals("pgnstates_minnesota_symbol_12.xml"));
	}
	
	@Test
	public void testPGNArticleSymbolTimeFileDataValid(){
		for(SymbolData s : article.articleSymbols){
			if(!(s.timeFile.equals("") || s.timeFile.equals("null") || s.timeFile.equals("empty"))){
				if(!articleAPI.verifyXMLActive(s.timeFile)){
					System.out.println(s.timeFile + " is not active");
				}
				assertTrue("Asserts that the article returned has a valid persons timefile",
						articleAPI.verifyXMLActive(s.timeFile));
			}
		}
	}
	
	@Test
	public void testPGNArticleSymbolsAudioFileKeyExists(){
		assertTrue("Asserts that the article returned has a timefile key in the article symbol audio file data", 
				articleAPI.parsedData.get("article_symbols").getAsJsonArray().get(0).getAsJsonObject().has("audio_file"));
	}
	
	@Test
	public void testPGNArticleSymbolAudioFileDataExists(){
		assertTrue("Asserts that the article returned has the correct symbol audio file data",
				article.articleSymbols.get(0).audioFile.equals("pgnstates_minnesota_symbol_1.mp3"));
		assertTrue("Asserts that the article returned has the correct symbol audio file data",
				article.articleSymbols.get(8).audioFile.equals("pgnstates_minnesota_symbol_9.mp3"));
		assertTrue("Asserts that the article returned has the correct symbol audio file data",
				article.articleSymbols.get(11).audioFile.equals("pgnstates_minnesota_symbol_12.mp3"));
	}
	
	@Test
	public void testPGNArticleSymbolAudioFileDataValid(){
		for(SymbolData s : article.articleSymbols){
			if(!(s.audioFile.equals("") || s.audioFile.equals("null") || s.audioFile.equals("empty"))){
				if(!articleAPI.verifyAudioActive(s.audioFile)){
					System.out.println(s.audioFile + " is not active");
				}
				assertTrue("Asserts that the article returned has a valid symbols audiofile",
						articleAPI.verifyAudioActive(s.audioFile));
			}
		}
	}
	
	@Test
	public void testPGNArticleSymbolsTextTimecodedKeyExists(){
		assertTrue("Asserts that the article returned has a timefile key in the article symbol text timecoded file data", 
				articleAPI.parsedData.get("article_symbols").getAsJsonArray().get(0).getAsJsonObject().has("text_timecoded"));
	}
	
	@Test
	public void testPGNArticleSymbolTextTimecodedDataExists(){
		assertTrue("Asserts that the article returned has the correct symbol text timecoded data",
				article.articleSymbols.get(0).textTimecoded.equals(new ArrayList<String>(Arrays.asList("Norway", "pine"))));
		assertTrue("Asserts that the article returned has the correct symbol text timecoded data",
				article.articleSymbols.get(8).textTimecoded.equals(new ArrayList<String>(Arrays.asList("Wild", "rice"))));
		assertTrue("Asserts that the article returned has the correct symbol text timecoded data",
				article.articleSymbols.get(11).textTimecoded.equals(new ArrayList<String>(Arrays.asList("Honeycrisp", "apple"))));
	}
	
	@Test
	public void testPGNArticleSymbolsTitleTimecodedKeyExists(){
		assertTrue("Asserts that the article returned has a timefile key in the article symbol title timecoded file data", 
				articleAPI.parsedData.get("article_symbols").getAsJsonArray().get(0).getAsJsonObject().has("title_timecoded"));
	}
	
	@Test
	public void testPGNArticleSymbolTitleTimecodedDataExists(){
		assertTrue("Asserts that the article returned has the correct symbol title timecoded data",
				article.articleSymbols.get(0).titleTimecoded.equals(new ArrayList<String>(Arrays.asList("Tree"))));
		assertTrue("Asserts that the article returned has the correct symbol title timecoded data",
				article.articleSymbols.get(8).titleTimecoded.equals(new ArrayList<String>(Arrays.asList("Grain"))));
		assertTrue("Asserts that the article returned has the correct symbol title timecoded data",
				article.articleSymbols.get(11).titleTimecoded.equals(new ArrayList<String>(Arrays.asList("Fruit"))));
	}
	
	@Test
	public void testPGNArticleRelatedArticleKeyExists(){
		assertTrue("Asserts that the article returned has a related articles tag in the article activity data", 
				articleAPI.parsedData.has("related_articles"));
	}
	
	@Test
	public void testPGNArticleRelatedArticlesDataExists(){
		assertTrue("Asserts that the article returned has the correct related articles values",
				article.relatedArticles.get(0).getAsString().equals("6078"));
		assertTrue("Asserts that the article returned has the correct related articles values",
				article.relatedArticles.get(1).getAsString().equals("6081"));
		assertTrue("Asserts that the article returned has the correct related articles values",
				article.relatedArticles.get(2).getAsString().equals("6084"));
		assertTrue("Asserts that the article returned has the correct related articles values",
				article.relatedArticles.get(3).getAsString().equals("6090"));
	}
	
	@Test
	public void testPGNArticleDocumentsKeyExists(){
		assertTrue("Asserts that the article returned has a article documents key", 
				articleAPI.parsedData.has("article_documents"));
	}
	
	@Test
	public void testPGNArticleDocumentsFilenameKeyExists(){
		assertTrue("Asserts that the article returned has a filename key in article documents data", 
				articleAPI.parsedData.get("article_documents").getAsJsonArray().get(0).getAsJsonObject().has("filename"));
	}
	
	@Test
	public void testPGNArticleDocumentFilenamesDataExists(){
		assertTrue("Asserts that the article returned has the correct documents filename values",
				article.pgnDocuments.get(0).filename.equals("pgnstates_minnesota_document_2.pdf"));
		assertTrue("Asserts that the article returned has the correct documents filename values",
				article.pgnDocuments.get(1).filename.equals("pgnstates_minnesota_document_1.pdf"));
		assertTrue("Asserts that the article returned has the correct documents filename values",
				article.pgnDocuments.get(2).filename.equals("pgnstates_minnesota_document_3.pdf"));
	}
	
	@Test
	public void testPGNArticleDocumentFilenamesDataValid(){
		assertTrue("Asserts that the article returned has a valid documents filename values",
				articleAPI.verifyPDFActive(article.pgnDocuments.get(0).filename));
		assertTrue("Asserts that the article returned has a valid documents filename values",
				articleAPI.verifyPDFActive(article.pgnDocuments.get(1).filename));
		assertTrue("Asserts that the article returned has a valid documents filename values",
				articleAPI.verifyPDFActive(article.pgnDocuments.get(2).filename));
	}
	
	@Test
	public void testPGNArticleDocumentsThumbnailKeyExists(){
		assertTrue("Asserts that the article returned has a thumbnail key in article documents data", 
				articleAPI.parsedData.get("article_documents").getAsJsonArray().get(0).getAsJsonObject().has("thumbnail"));
	}
	
	@Test
	public void testPGNArticleDocumentThumbnailDataExists(){
		assertTrue("Asserts that the article returned has the correct documents thumbnail values",
				article.pgnDocuments.get(0).thumbnail.equals("pgnstates_minnesota_document_2.jpg"));
		assertTrue("Asserts that the article returned has the correct documents thumbnail values",
				article.pgnDocuments.get(1).thumbnail.equals("pgnstates_minnesota_document_1.jpg"));
		assertTrue("Asserts that the article returned has the correct documents thumbnail values",
				article.pgnDocuments.get(2).thumbnail.equals("pgnstates_minnesota_document_3.jpg"));
	}
	
	@Test
	public void testPGNArticleDocumentThumbnailDataValid(){
		assertTrue("Asserts that the article returned has a valid documents thumbnail values",
				articleAPI.verifyImageActive(article.pgnDocuments.get(0).thumbnail));
		assertTrue("Asserts that the article returned has a valid documents thumbnail values",
				articleAPI.verifyImageActive(article.pgnDocuments.get(1).thumbnail));
		assertTrue("Asserts that the article returned has the correct documents thumbnail values",
				articleAPI.verifyImageActive(article.pgnDocuments.get(2).thumbnail));
	}
	
	@Test
	public void testPGNArticleDocumentIDKeyExists(){
		assertTrue("Asserts that the article returned has a document id key in article documents data", 
				articleAPI.parsedData.get("article_documents").getAsJsonArray().get(0).getAsJsonObject().has("document_id"));
	}
	
	@Test
	public void testPGNArticleDocumentIDDataExists(){
		assertTrue("Asserts that the article returned has the correct document id values",
				article.pgnDocuments.get(0).documentID.equals("162"));
		assertTrue("Asserts that the article returned has the correct document id values",
				article.pgnDocuments.get(1).documentID.equals("163"));
		assertTrue("Asserts that the article returned has the correct document id values",
				article.pgnDocuments.get(2).documentID.equals("164"));
	}
	
	@Test
	public void testPGNArticleDocumentTypeIDKeyExists(){
		assertTrue("Asserts that the article returned has a document type id key in article documents data", 
				articleAPI.parsedData.get("article_documents").getAsJsonArray().get(0).getAsJsonObject().has("document_type_id"));
	}
	
	@Test
	public void testPGNArticleDocumentTypeIDDataExists(){
		assertTrue("Asserts that the article returned has the correct document type id values",
				article.pgnDocuments.get(0).documentTypeID.equals("2"));
		assertTrue("Asserts that the article returned has the correct document type id values",
				article.pgnDocuments.get(1).documentTypeID.equals("1"));
		assertTrue("Asserts that the article returned has the correct document type id values",
				article.pgnDocuments.get(2).documentTypeID.equals("3"));
	}
	
	
	@Test
	public void testPGNArticleLandmarksKeyExists(){
		assertTrue("Asserts that the article returned has an article landmarks key", 
				articleAPI.parsedData.has("article_landmarks"));
	}
	
	@Test
	public void testPGNArticleLandmarkTextKeyExists(){
		assertTrue("Asserts that the article returned has a landmark text key in article documents data", 
				articleAPI.parsedData.get("article_landmarks").getAsJsonArray().get(0).getAsJsonObject().has("text"));
	}
	
	@Test
	public void testPGNArticleLandmarkTextDataExists(){
		assertTrue("Asserts that the article returned has the correct landmark text values",
				article.articleLandmarks.get(0).text.equals("The Mall of America in Bloomington is the "+
						"largest indoor shopping center in the United States. The mall boasts more than 520 stores "+
						"and attracts 40 million visitors each year."));
		assertTrue("Asserts that the article returned has the correct landmark text id values",
				article.articleLandmarks.get(5).text.equals("The Hull-Rust-Mahoning open pit iron mine is the "+
						"largest open pit mine in the world. It opened in 1895 in the Mesabi Iron Range. During World "+
						"War I and World War II the mine supplied 25 percent of all iron ore mined in the United States."));
	}
	
	@Test
	public void testPGNArticleLandmarkImageKeyExists(){
		assertTrue("Asserts that the article returned has a landmark image key in article documents data", 
				articleAPI.parsedData.get("article_landmarks").getAsJsonArray().get(0).getAsJsonObject().has("image"));
	}
	
	@Test
	public void testPGNArticleLandmarkImageDataExists(){
		assertTrue("Asserts that the article returned has the correct landmark image value",
				article.articleLandmarks.get(0).image.equals("pgnstates_minnesota_landmark_0.jpg"));
		assertTrue("Asserts that the article returned has the correct landmark image value",
				article.articleLandmarks.get(5).image.equals("pgnstates_minnesota_landmark_6.jpg"));
	}
	
	@Test
	public void testPGNArticleLandmarkImageDataValid(){
		for(LandmarkData l : article.articleLandmarks){
			if(!(l.image.equals("") || l.image.equals("null") || l.image.equals("empty"))){
				if(!articleAPI.verifyImageActive(l.image)){
					System.out.println(l.image + " is not active");
				}
				assertTrue("Asserts that the article returned has a valid landmarks image",
						articleAPI.verifyImageActive(l.image));
			}
		}
	}
	
	@Test
	public void testPGNArticleLandmarkTitleKeyExists(){
		assertTrue("Asserts that the article returned has a landmark title key in article documents data", 
				articleAPI.parsedData.get("article_landmarks").getAsJsonArray().get(0).getAsJsonObject().has("title"));
	}
	
	@Test
	public void testPGNArticleLandmarkTitleDataExists(){
		assertTrue("Asserts that the article returned has the correct landmark title value",
				article.articleLandmarks.get(0).title.equals("Mall of America"));
		assertTrue("Asserts that the article returned has the correct landmark title value",
				article.articleLandmarks.get(5).title.equals("Hull-Rust-Mahoning Open Pit Iron Mine"));
	}
	
	@Test
	public void testPGNArticleLandmarkTimeFileKeyExists(){
		assertTrue("Asserts that the article returned has a landmark time file key in article documents data", 
				articleAPI.parsedData.get("article_landmarks").getAsJsonArray().get(0).getAsJsonObject().has("time_file"));
	}
	
	@Test
	public void testPGNArticleLandmarkTimeFileDataExists(){
		assertTrue("Asserts that the article returned has the correct landmark time file value",
				article.articleLandmarks.get(0).timeFile.equals("pgnstates_minnesota_landmark_0.xml"));
		assertTrue("Asserts that the article returned has the correct landmark time file value",
				article.articleLandmarks.get(5).timeFile.equals("pgnstates_minnesota_landmark_6.xml"));
	}
	
	@Test
	public void testPGNArticleLandmarkTimeFileDataValid(){
		for(LandmarkData l : article.articleLandmarks){
			if(!(l.timeFile.equals("") || l.timeFile.equals("null") || l.timeFile.equals("empty"))){
				if(!articleAPI.verifyXMLActive(l.timeFile)){
					System.out.println(l.timeFile + " is not active");
				}
				assertTrue("Asserts that the article returned has a valid landmarks timeFile",
						articleAPI.verifyXMLActive(l.timeFile));
			}
		}
	}
	
	@Test
	public void testPGNArticleLandmarkAudioFileKeyExists(){
		assertTrue("Asserts that the article returned has a landmark audio file key in article documents data", 
				articleAPI.parsedData.get("article_landmarks").getAsJsonArray().get(0).getAsJsonObject().has("audio_file"));
	}
	
	@Test
	public void testPGNArticleLandmarkAudioFileDataExists(){
		assertTrue("Asserts that the article returned has the correct landmark audio file value",
				article.articleLandmarks.get(0).audioFile.equals("pgnstates_minnesota_landmark_0.mp3"));
		assertTrue("Asserts that the article returned has the correct landmark audio file value",
				article.articleLandmarks.get(5).audioFile.equals("pgnstates_minnesota_landmark_6.mp3"));
	}
	
	@Test
	public void testPGNArticleLandmarkAudioFileDataValid(){
		for(LandmarkData l : article.articleLandmarks){
			if(!(l.audioFile.equals("") || l.audioFile.equals("null") || l.audioFile.equals("empty"))){
				if(!articleAPI.verifyAudioActive(l.audioFile)){
					System.out.println(l.audioFile + " is not active");
				}
				assertTrue("Asserts that the article returned has a valid landmarks audiofile",
						articleAPI.verifyAudioActive(l.audioFile));
			}
		}
	}
	
	@Test
	public void testPGNArticleLandmarkIDKeyExists(){
		assertTrue("Asserts that the article returned has a landmark id key in article documents data", 
				articleAPI.parsedData.get("article_landmarks").getAsJsonArray().get(0).getAsJsonObject().has("landmark_id"));
	}
	
	@Test
	public void testPGNArticleLandmarkIDDataExists(){
		assertTrue("Asserts that the article returned has the correct landmark id value",
				article.articleLandmarks.get(0).landmarkID.equals("112"));
		assertTrue("Asserts that the article returned has the correct landmark id value",
				article.articleLandmarks.get(5).landmarkID.equals("213"));
	}
	
	@Test
	public void testPGNArticleLandmarkTextTimecodedKeyExists(){
		assertTrue("Asserts that the article returned has a landmark text timecoded key in article documents data", 
				articleAPI.parsedData.get("article_landmarks").getAsJsonArray().get(0).getAsJsonObject().has("text_timecoded"));
	}
	
	@Test
	public void testPGNArticleLandmarkTextTimecodedDataExists(){
		assertTrue("Asserts that the article returned has the correct landmark id value",
				article.articleLandmarks.get(0).textTimeCoded.equals(new ArrayList<String>(Arrays.asList("The", "Mall", "of", "America", 
						"in", "Bloomington", "is", "the", "largest", "indoor", "shopping", "center", "in", "the", "United", "States.",
						"The", "mall", "boasts", "more", "than", "520", "stores", "and", "attracts", "40", "million", "visitors", "each", "year."))));
		assertTrue("Asserts that the article returned has the correct landmark id value",
				article.articleLandmarks.get(5).textTimeCoded.equals(new ArrayList<String>(Arrays.asList("The", "Hull-Rust-Mahoning", "open", 
						"pit", "iron", "mine", "is", "the", "largest", "open", "pit", "mine", "in", "the", "world.", "It", "opened", "in",
						"1895", "in", "the", "Mesabi", "Iron", "Range.", "During", "World", "War", "I", "and", "World", "War", "II", "the", 
						"mine", "supplied", "25", "percent", "of", "all", "iron", "ore", "mined", "in", "the", "United", "States."))));
	}
	
	@Test
	public void testPGNArticleTimelinesStartDateKeyExists(){	
		assertTrue("Asserts that the article returned has a start date key in the timelines data", 
				articleAPI.parsedData.get("article_timelines").getAsJsonArray().get(0).getAsJsonObject().has("date1"));
	}
	
	@Test
	public void testPGNTimelinesStartDateDataExists(){
		assertTrue("Asserts that the article returned has the correct Timeline start date",
				article.articleTimelines.get(0).startDate.equals("1600"));
		assertTrue("Asserts that the article returned has the correct Timeline start date",
				article.articleTimelines.get(14).startDate.equals("1939"));
		assertTrue("Asserts that the article returned has the correct Timeline start date",
				article.articleTimelines.get(22).startDate.equals("2015"));
	}
	
	@Test
	public void testPGNArticleTimelinesEndDateKeyExists(){
		assertTrue("Asserts that the article returned has a end date key in the timelines data", 
				articleAPI.parsedData.get("article_timelines").getAsJsonArray().get(0).getAsJsonObject().has("date2"));
	}
	
	@Test
	public void testPGNTimelinesEndDateDataExists(){
		assertTrue("Asserts that the article returned has the correct Timeline end date",
				article.articleTimelines.get(0).endDate.equals("1600"));
		assertTrue("Asserts that the article returned has the correct Timeline end date",
				article.articleTimelines.get(14).endDate.equals("1945"));
		assertTrue("Asserts that the article returned has the correct Timeline end date",
				article.articleTimelines.get(22).endDate.equals("2015"));
	}
	
	@Test
	public void testPGNArticleTimelinesAudioFileKeyExists(){
		assertTrue("Asserts that the article returned has an audio file key in the timelines data", 
				articleAPI.parsedData.get("article_timelines").getAsJsonArray().get(0).getAsJsonObject().has("audio_file"));
	}
	
	@Test
	public void testPGNTimelinesAudioFileDataExists(){
		assertTrue("Asserts that the article returned has the correct Timeline audio file",
				article.articleTimelines.get(0).audioFile.equals("pgnstates_minnesota_timeline_0.mp3"));
		assertTrue("Asserts that the article returned has the correct Timeline audio file",
				article.articleTimelines.get(14).audioFile.equals("pgnstates_minnesota_timeline_14.mp3"));
		assertTrue("Asserts that the article returned has the correct Timeline audio file",
				article.articleTimelines.get(22).audioFile.equals("pgnstates_minnesota_timeline_22.mp3"));
	}
	
	@Test
	public void testPGNTimelinesAudioFileDataValid(){
		for(TimelineData t : article.articleTimelines){
			if(!(t.audioFile.equals("") || t.audioFile.equals("null") || t.audioFile.equals("empty"))){
				if(!articleAPI.verifyAudioActive(t.audioFile)){
					System.out.println(t.audioFile + " is not active");
				}
				assertTrue("Asserts that the article returned has a valid timelines audiofile",
						articleAPI.verifyAudioActive(t.audioFile));
			}
		}
	}
	
	@Test
	public void testPGNArticleTimelinesDescriptionKeyExists(){
		assertTrue("Asserts that the article returned has a desciption key in the timelines data", 
				articleAPI.parsedData.get("article_timelines").getAsJsonArray().get(0).getAsJsonObject().has("description"));
	}
	
	@Test
	public void testPGNTimelinesDescriptionDataExists(){
		assertTrue("Asserts that the article returned has the correct Timeline description",
				article.articleTimelines.get(0).description.equals("Dakota and Ojibwa Indians live in the area that is now Minnesota."));
		assertTrue("Asserts that the article returned has the correct Timeline description",
				article.articleTimelines.get(14).description.equals("World War II is fought; the United States enters the war in 1941."));
		assertTrue("Asserts that the article returned has the correct Timeline description",
				article.articleTimelines.get(22).description.equals("Minnesota becomes the second state to create a Military Spouses and Families Day to honor the support and sacrifices made for the betterment of the country."));
	}
	
	@Test
	public void testPGNArticleTimelinesIDKeyExists(){
		assertTrue("Asserts that the article returned has a timeline id key in the timelines data", 
				articleAPI.parsedData.get("article_timelines").getAsJsonArray().get(0).getAsJsonObject().has("timeline_id"));
	}
	
	@Test
	public void testPGNTimelinesIDDataExists(){
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(0).timelineID.equals("3137"));
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(14).timelineID.equals("3151"));
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(22).timelineID.equals("3537"));
	}
	
	@Test
	public void testPGNArticleTimelinesTimecodeKeyExists(){
		assertTrue("Asserts that the article returned has a timecode file key in the timelines data", 
				articleAPI.parsedData.get("article_timelines").getAsJsonArray().get(0).getAsJsonObject().has("timecode_file"));
	}
	
	@Test
	public void testPGNTimelinesTimecodeFileDataExists(){
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(0).timecodeFile.equals("pgnstates_minnesota_timeline_0.xml"));
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(14).timecodeFile.equals("pgnstates_minnesota_timeline_14.xml"));
		assertTrue("Asserts that the article returned has the correct Timeline ID",
				article.articleTimelines.get(22).timecodeFile.equals("pgnstates_minnesota_timeline_22.xml"));
	}
	
	@Test
	public void testPGNTimelinesTimecodeFileDataValid(){
		for(TimelineData t : article.articleTimelines){
			if(!(t.timecodeFile.equals("") || t.timecodeFile.equals("null") || t.timecodeFile.equals("empty"))){
				if(!articleAPI.verifyXMLActive(t.timecodeFile)){
					System.out.println(t.timecodeFile + " is not active");
				}
				assertTrue("Asserts that the article returned has a valid timelines timefile",
						articleAPI.verifyXMLActive(t.timecodeFile));
			}
		}
	}
	
	@Test
	public void testPGNArticleTimelinesDateTimecodeKeyExists(){
		assertTrue("Asserts that the article returned has a date timecoded key in the timelines data", 
				articleAPI.parsedData.get("article_timelines").getAsJsonArray().get(0).getAsJsonObject().has("date_timecoded"));
	}
	
	@Test
	public void testPGNTimelinesDateTimecode(){
		assertTrue("Asserts that the article returned has the correct Date Timecode",
				article.articleTimelines.get(0).dateTimecode.get(0).equals("1600"));
		assertTrue("Asserts that the article returned has the correct Date Timecode",
				article.articleTimelines.get(14).dateTimecode.get(0).equals("1939–1945"));
		assertTrue("Asserts that the article returned has the correct Date Timecode",
				article.articleTimelines.get(22).dateTimecode.get(0).equals("2015"));
	}
	
	@Test
	public void testPGNArticleTimelinesDescriptionTimecodeKeyExists(){
		assertTrue("Asserts that the article returned has a description timecoded key in the timelines data", 
				articleAPI.parsedData.get("article_timelines").getAsJsonArray().get(0).getAsJsonObject().has("description_timecoded"));
	}
	
	@Test
	public void testPGNTimelinesDescriptionTimecodeDataExists(){
		assertTrue("Asserts that the article returned has the correct Timecode",
				article.articleTimelines.get(0).descriptionTimecode.equals(new ArrayList<String>(Arrays.asList("Dakota", "and", "Ojibwa", "Indians", "live", "in", "the", "area",
						"that", "is", "now", "Minnesota."))));
		assertTrue("Asserts that the article returned has the correct Timecode",
				article.articleTimelines.get(14).descriptionTimecode.equals(new ArrayList<String>(Arrays.asList("World", "War", "II", "is", "fought;", "the", "United", "States",
						"enters", "the", "war", "in", "1941."))));
		assertTrue("Asserts that the article returned has the correct Timecode",
				article.articleTimelines.get(22).descriptionTimecode.equals(new ArrayList<String>(Arrays.asList("Minnesota", "becomes", "the", "second", "state", "to", "create",
						"a", "Military", "Spouses", "and", "Families", "Day", "to", "honor", "the", "support", "and", "sacrifices", "made", "for", "the", "betterment", "of",
						"the", "country."))));
	}
	
	
	
	@Test
	public void testPGNArticleLanguageISOKeyExists(){
		assertTrue("Asserts that the article returned has a LanguageISO key", 
				articleAPI.parsedData.has("languageISO3166_2"));
	}
	
	@Test
	public void testPGNArticleLanguageISODataExists(){
		assertTrue("Asserts that the article returned has the correct languageISO value",
				article.languageString.equals("en-US"));
	}
	
	@Test
	public void testPGNArticleAnimalSoundKeyExists(){
		assertTrue("Asserts that the article returned has a document type id key in article documents data", 
				articleAPI.parsedData.has("animal_sound_filename"));
	}
}