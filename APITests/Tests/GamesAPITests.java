package Tests;

import static org.junit.Assert.*;
import java.io.IOException;
import org.apache.http.ParseException;
import org.junit.Before;
import org.junit.Test;
import com.google.gson.JsonElement;
import APIControllers.GamesAPIController;
import DataClasses.GamesData;
import SpecificGameData.SpecificGameData;

public class GamesAPITests {
	public GamesAPIController gamesAPI = new GamesAPIController();
	
	@Before
	public void executeBefore(){
		try {
			gamesAPI.getGamesJSON("2", "1");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testPGOAnimalsZoom() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "1");
		
		GamesData game = gamesAPI.getGameJSONByName("Zoom");
		 
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("17"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Zoom"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("1"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("1"));
		
		String callout = game.gameJSON.get("config").getAsJsonObject().get("callout").getAsString();
		assertTrue("Asserts that the config we get is for the correct gametype", callout.equals("Click “Start” to begin zooming."));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Zoom Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOAnimalsJigsaw() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "1");
		
		GamesData game = gamesAPI.getGameJSONByName("Jigsaw");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("18"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Jigsaw"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("1"));
		
		String mainTitle = game.gameJSON.get("config").getAsJsonObject().get("main_title").getAsString();
		assertTrue("Asserts that the config we get is for the correct gametype", mainTitle.equals("Map Jumble"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Jigsaw Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOAnimalsQuickMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "1");
		
		GamesData game = gamesAPI.getGameJSONByName("Quick Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("20"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Quick Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("1"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Quick Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOAnimalsMultiMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "1");
		
		GamesData game = gamesAPI.getGameJSONByName("Multi Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("19"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Multi Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("4"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("1"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Multi Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOAnimalsWhack() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "1");
		
		GamesData game = gamesAPI.getGameJSONByName("Whack");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("21"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Whack"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("5"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("1"));
		
		String gameName = game.gameJSON.get("config").getAsJsonObject().get("gameName").getAsString();
		JsonElement pairs = game.gameJSON.get("clueImages").getAsJsonArray().get(0).getAsJsonObject().get("imagePath");
		assertTrue("Asserts that the config we get is for the correct gametype", gameName.equals("Whack an image") && !pairs.isJsonNull());
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Whack Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOAnimalsWordScamble() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "1");
		
		GamesData game = gamesAPI.getGameJSONByName("Word Scramble");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("16"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Word Scramble"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("6"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("1"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Word Scramble Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOScienceZoom() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "2");
		
		GamesData game = gamesAPI.getGameJSONByName("Zoom");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("23"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Zoom"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("1"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("2"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Zoom Game for PGO Animals are valid", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOScienceJigsaw() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "2");
		
		GamesData game = gamesAPI.getGameJSONByName("Jigsaw");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("24"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Jigsaw"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("2"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Jigsaw Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOScienceQuickMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "2");
		
		GamesData game = gamesAPI.getGameJSONByName("Quick Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("26"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Quick Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("2"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Quick Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOScienceMultiMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "2");
		
		GamesData game = gamesAPI.getGameJSONByName("Multi Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("25"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Multi Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("4"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("2"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Multi Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOScienceWhack() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "2");
		
		GamesData game = gamesAPI.getGameJSONByName("Whack");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("27"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Whack"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("5"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("2"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Whack Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOScienceWordScamble() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "2");
		
		GamesData game = gamesAPI.getGameJSONByName("Word Scramble");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("22"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Word Scramble"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("6"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("2"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the World Scramble Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOBiographiesZoom() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "3");
		
		GamesData game = gamesAPI.getGameJSONByName("Zoom");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("29"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Zoom"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("1"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("3"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Zoom Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOBiographiesJigsaw() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "3");
		
		GamesData game = gamesAPI.getGameJSONByName("Jigsaw");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("30"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Jigsaw"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("3"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Jigsaw Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOBiographiesQuickMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "3");
		
		GamesData game = gamesAPI.getGameJSONByName("Quick Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("32"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Quick Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("3"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Quick Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOBiographieseMultiMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "3");
		
		GamesData game = gamesAPI.getGameJSONByName("Multi Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("31"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Multi Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("4"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("3"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Multi Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOBiographiesWhack() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "3");
		
		GamesData game = gamesAPI.getGameJSONByName("Whack");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("33"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Whack"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("5"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("3"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Whack Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOBiographiesWordScamble() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "3");
		
		GamesData game = gamesAPI.getGameJSONByName("Word Scramble");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("28"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Word Scramble"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("6"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("3"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Word Scramble Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOSocialStudiesZoom() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "5");
		
		GamesData game = gamesAPI.getGameJSONByName("Zoom");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("35"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Zoom"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("1"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("5"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Zoom Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOSocialStudiesJigsaw() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "5");
		
		GamesData game = gamesAPI.getGameJSONByName("Jigsaw");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("36"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Jigsaw"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("5"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Jigsaw Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOSocialStudiesQuickMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "5");
		
		GamesData game = gamesAPI.getGameJSONByName("Quick Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("38"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Quick Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("5"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Quick Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOSocialStudiesMultiMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "5");
		
		GamesData game = gamesAPI.getGameJSONByName("Multi Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("37"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Multi Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("4"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("5"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Multi Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOSocialStudiesWhack() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "5");
		
		GamesData game = gamesAPI.getGameJSONByName("Whack");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("39"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Whack"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("5"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("5"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Whack Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOsocialStudiesWordScamble() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "5");
		
		GamesData game = gamesAPI.getGameJSONByName("Word Scramble");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("34"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Word Scramble"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("6"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("5"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Word Scramble Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNStatesZoom() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "6");
		
		GamesData game = gamesAPI.getGameJSONByName("Zoom");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Zoom"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("7"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("6"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Zoom Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNStatesJigsaw() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "6");
		
		GamesData game = gamesAPI.getGameJSONByName("Jigsaw");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Jigsaw"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("8"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("6"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Jigsaw Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNStatesQuickMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "6");
		
		GamesData game = gamesAPI.getGameJSONByName("Quick Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("4"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Quick Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("9"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("6"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Quick Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNStatesMultiMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "6");
		
		GamesData game = gamesAPI.getGameJSONByName("Multi Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("Error"));
	}
	
	@Test
	public void testPGNStateseWhack() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "6");
		
		GamesData game = gamesAPI.getGameJSONByName("Whack");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("Error"));
	}
	
	@Test
	public void testPGNStatesWordScamble() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "6");
		
		GamesData game = gamesAPI.getGameJSONByName("Word Scramble");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("1"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Word Scramble"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("10"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("6"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Word Scramble Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGODinosaursZoom() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "7");
		
		GamesData game = gamesAPI.getGameJSONByName("Zoom");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("41"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Zoom"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("1"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("7"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Zoom Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGODinosaursJigsaw() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "7");
		
		GamesData game = gamesAPI.getGameJSONByName("Jigsaw");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("42"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Jigsaw"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("7"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Jigsaw Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGODinosaursQuickMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "7");
		
		GamesData game = gamesAPI.getGameJSONByName("Quick Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("44"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Quick Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("7"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Quick Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGODinosaursMultiMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "7");
		
		GamesData game = gamesAPI.getGameJSONByName("Multi Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("43"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Multi Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("4"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("7"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Multi Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGODinosaursWhack() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "7");
		
		GamesData game = gamesAPI.getGameJSONByName("Whack");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("45"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Whack"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("5"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("7"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Whack Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGODinosaursWordScamble() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "7");
		
		GamesData game = gamesAPI.getGameJSONByName("Word Scramble");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("40"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Word Scramble"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("6"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("7"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Word Scramble Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOAnimalesZoom() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "8");
		
		GamesData game = gamesAPI.getGameJSONByName("Zoom");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("47"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Zoom"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("1"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("8"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Zoom Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOAnimalesJigsaw() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "8");
		
		GamesData game = gamesAPI.getGameJSONByName("Jigsaw");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("48"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Jigsaw"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("8"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Jigsaw Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOAnimalesQuickMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "8");
		
		GamesData game = gamesAPI.getGameJSONByName("Quick Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("50"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Quick Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("8"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Quick Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOAnimalesMultiMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "8");
		
		GamesData game = gamesAPI.getGameJSONByName("Multi Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("49"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Multi Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("4"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("8"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Multi Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOAnimalesWhack() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "8");
		
		GamesData game = gamesAPI.getGameJSONByName("Whack");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("51"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Whack"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("5"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("8"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Whack Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOAnimalesWordScamble() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "8");
		
		GamesData game = gamesAPI.getGameJSONByName("Word Scramble");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("46"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Word Scramble"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("6"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("8"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Word Scramble Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNScienceZoom() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "9");
		
		GamesData game = gamesAPI.getGameJSONByName("Zoom");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("9"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Zoom"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("7"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("9"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Zoom Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNScienceJigsaw() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "9");
		
		GamesData game = gamesAPI.getGameJSONByName("Jigsaw");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("10"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Jigsaw"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("8"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("9"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Jigsaw Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNScienceQuickMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "9");
		
		GamesData game = gamesAPI.getGameJSONByName("Quick Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("11"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Quick Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("9"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("9"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Quick Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNScienceMultiMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "9");
		
		GamesData game = gamesAPI.getGameJSONByName("Multi Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("Error"));
	}
	
	@Test
	public void testPGNScienceWhack() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "9");
		
		GamesData game = gamesAPI.getGameJSONByName("Whack");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("Error"));
	}
	
	@Test
	public void testPGNScienceWordScamble() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "9");
		
		GamesData game = gamesAPI.getGameJSONByName("Word Scramble");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("8"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Word Scramble"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("10"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("9"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Word Scramble Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNAMHZoom() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "10");
		
		GamesData game = gamesAPI.getGameJSONByName("Zoom");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("Error"));
	}
	
	@Test
	public void testPGNAMHJigsaw() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "10");
		
		GamesData game = gamesAPI.getGameJSONByName("Jigsaw");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("5"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Jigsaw"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("8"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("10"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Jigsaw Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNAMHQuickMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "10");
		
		GamesData game = gamesAPI.getGameJSONByName("Quick Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("6"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Quick Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("9"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("10"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Quick Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNAMHMultiMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "10");
		
		GamesData game = gamesAPI.getGameJSONByName("Multi Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("Error"));
	}
	
	@Test
	public void testPGNAMHWhack() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "10");
		
		GamesData game = gamesAPI.getGameJSONByName("Whack");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("Error"));
	}
	
	@Test
	public void testPGNAMHWordScamble() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "10");
		
		GamesData game = gamesAPI.getGameJSONByName("Word Scramble");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("7"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Word Scramble"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("10"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("10"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Word Scramble Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNSocialStudiesZoom() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "11");
		
		GamesData game = gamesAPI.getGameJSONByName("Zoom");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("13"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Zoom"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("7"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("11"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Zoom Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNSocialStudiesJigsaw() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "11");
		
		GamesData game = gamesAPI.getGameJSONByName("Jigsaw");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("14"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Jigsaw"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("8"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("11"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Jigsaw Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNSocialStudiesQuickMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "11");
		
		GamesData game = gamesAPI.getGameJSONByName("Quick Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("15"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Quick Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("9"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("11"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Quick Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNSocialStudiesMultiMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "11");
		
		GamesData game = gamesAPI.getGameJSONByName("Multi Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("Error"));
	}
	
	@Test
	public void testPGNSocialStudiesWhack() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "11");
		
		GamesData game = gamesAPI.getGameJSONByName("Whack");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("Error"));
	}
	
	@Test
	public void testPGNSocialStudiesWordScamble() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "11");
		
		GamesData game = gamesAPI.getGameJSONByName("Word Scramble");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("12"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Word Scramble"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("10"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("11"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Word Scramble Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOCienciaZoom() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "12");
		
		GamesData game = gamesAPI.getGameJSONByName("Zoom");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("52"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Zoom"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("1"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("12"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Zoom Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOCienciaJigsaw() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "12");
		
		GamesData game = gamesAPI.getGameJSONByName("Jigsaw");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("53"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Jigsaw"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("12"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Jigsaw Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOCienciaQuickMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "12");
		
		GamesData game = gamesAPI.getGameJSONByName("Quick Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("54"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Quick Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("12"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Quick Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOCienciaMultiMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "12");
		
		GamesData game = gamesAPI.getGameJSONByName("Multi Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("55"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Multi Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("4"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("12"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Multi Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOCienciaWhack() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "12");
		
		GamesData game = gamesAPI.getGameJSONByName("Whack");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("56"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Whack"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("5"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("12"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Whack Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOCienciaWordScamble() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "12");
		
		GamesData game = gamesAPI.getGameJSONByName("Word Scramble");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("57"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Word Scramble"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("6"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("12"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Word Scramble Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOBiografiasZoom() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "13");
		
		GamesData game = gamesAPI.getGameJSONByName("Zoom");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("58"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Zoom"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("1"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("13"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Zoom Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOBiografiasJigsaw() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "13");
		
		GamesData game = gamesAPI.getGameJSONByName("Jigsaw");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("59"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Jigsaw"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("13"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Jigsaw Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOBiografiasQuickMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "13");
		
		GamesData game = gamesAPI.getGameJSONByName("Quick Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("60"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Quick Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("13"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Quick Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOBiografiasMultiMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "13");
		
		GamesData game = gamesAPI.getGameJSONByName("Multi Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("61"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Multi Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("4"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("13"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Multi Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOBiografiasWhack() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "13");
		
		GamesData game = gamesAPI.getGameJSONByName("Whack");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("62"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Whack"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("5"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("13"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Whack Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOBiografiasWordScamble() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "13");
		
		GamesData game = gamesAPI.getGameJSONByName("Word Scramble");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("63"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Word Scramble"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("6"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("13"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Word Scramble Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOEstudiosSocialesZoom() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "14");
		
		GamesData game = gamesAPI.getGameJSONByName("Zoom");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("64"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Zoom"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("1"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("14"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Zoom Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOEstudiosSocialesJigsaw() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "14");
		
		GamesData game = gamesAPI.getGameJSONByName("Jigsaw");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("65"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Jigsaw"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("14"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Jigsaw Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOEstudiosSocialesQuickMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "14");
		
		GamesData game = gamesAPI.getGameJSONByName("Quick Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("66"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Quick Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("14"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Quick Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOEstudiosSocialesMultiMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "14");
		
		GamesData game = gamesAPI.getGameJSONByName("Multi Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("67"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Multi Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("4"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("14"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Multi Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOEstudiosSocialesWhack() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "14");
		
		GamesData game = gamesAPI.getGameJSONByName("Whack");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("68"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Whack"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("5"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("14"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Whack Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGOEstudioesSocialesWordScamble() throws ParseException, IOException{
		gamesAPI.getGamesJSON("2", "14");
		
		GamesData game = gamesAPI.getGameJSONByName("Word Scramble");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("2"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("69"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Word Scramble"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("6"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("14"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Word Scramble Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNBiograaphiesZoom() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "15");
		
		GamesData game = gamesAPI.getGameJSONByName("Zoom");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("70"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Zoom"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("7"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("15"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Zoom Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNBiographiesJigsaw() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "15");
		
		GamesData game = gamesAPI.getGameJSONByName("Jigsaw");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("71"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Jigsaw"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("8"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("15"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Jigsaw Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNBiographiesQuickMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "15");
		
		GamesData game = gamesAPI.getGameJSONByName("Quick Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("72"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Quick Match"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("9"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("15"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Quick Match Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testPGNBiographiesMultiMatch() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "15");
		
		GamesData game = gamesAPI.getGameJSONByName("Multi Match");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("Error"));
	}
	
	@Test
	public void testPGNBiographiesWhack() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "15");
		
		GamesData game = gamesAPI.getGameJSONByName("Whack");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("Error"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("Error"));
	}
	
	@Test
	public void testPGNBiographiesWordScamble() throws ParseException, IOException{
		gamesAPI.getGamesJSON("3", "15");
		
		GamesData game = gamesAPI.getGameJSONByName("Word Scramble");
		
		assertTrue("Asserts that the data we get is for the correct application", game.appID.equals("3"));
		assertTrue("Asserts that the data we get is for the correct game based on ID", game.gameID.equals("73"));
		assertTrue("Asserts that the data we get is for the correct game type", game.gameType.equals("Word Scramble"));
		assertTrue("Asserts that the data we get is for the correct game type ID", game.gameTypeID.equals("10"));
		assertTrue("Asserts that the data we get is for the correct module", game.moduleID.equals("15"));
		
		for(SpecificGameData s : game.assetData){
			if(!gamesAPI.verifyImageActive(s.image)){
				System.out.print(s.image);
			}
			assertTrue("Asserts that the assets in the Word Scramble Game Data is correct", gamesAPI.verifyImageActive(s.image));
		}
		assertTrue("Asserts that we are getting back units of asset data", game.assetData.size() > 0);
	}
	
	@Test
	public void testDataForNonExistantModule() throws ParseException, IOException{
		String response = gamesAPI.getGamesJSON("2", "0");		
		
		assertTrue("Asserts that the data we get back for a non existant module is a successful api call", gamesAPI.statusCode.equals("HTTP/1.1 422 "));
		assertTrue("Asserts that the data we get back for a non existant module is an empty body", response.equals("{\"errorMessage\":\"Invalid format\"}"));
	}
	
	@Test
	public void testDataForNullModule() throws ParseException, IOException{
		String response = gamesAPI.getGamesJSON("2", "");		
		
		assertTrue("Asserts that the data we get back for a non existant module is a successful api call", gamesAPI.statusCode.equals("HTTP/1.1 422 "));
		assertTrue("Asserts that the response we get back for a non existant module is a successful api call", response.equals("{\"errorMessage\":\"Invalid format\"}"));
	}
	
	@Test
	public void testDataForNonExistantModuleLargerNumber() throws ParseException, IOException{
		String response = gamesAPI.getGamesJSON("2", "234234235235");	
		
		assertTrue("Asserts that the data we get back for a non existant module is a successful api call", gamesAPI.statusCode.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the data we get back for a non existant module is an empty body", response.equals("[]"));
	}
	
	@Test
	public void testDataForNonExistantModuleString() throws ParseException, IOException{
		String response = gamesAPI.getGamesJSON("2", "xdfdfgjdgfjdfgj");
		
		assertTrue("Asserts that the data we get back for a non existant module is a successful api call", gamesAPI.statusCode.equals("HTTP/1.1 422 "));
		assertTrue("Asserts that the data we get back for a non existant module is an empty body", response.equals("{\"errorMessage\":\"Invalid format\"}"));
	}
	
	/*
	@Test
	public void testDataForNonPGProduct() throws ParseException, IOException{
		gamesAPI.getGamesJSON("asdasdfasdf", "1");
		
		assertTrue("Asserts that the data we get back for a non existant module is a successful api call", gamesAPI.statusCode.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the data we get back for a non existant module is the same module", 
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("module_id").getAsString().equals("1"));
		assertTrue("Asserts that the data we get back for a non existant module is the same module", 
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("module_id").getAsString().equals("1"));
		assertTrue("Asserts that the data we get back for a non existant module is the same module", 
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().get("module_id").getAsString().equals("1"));
		assertTrue("Asserts that the data we get back for a non existant module is the same module", 
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("module_id").getAsString().equals("1"));
		assertTrue("Asserts that the data we get back for a non existant module is the same module", 
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("module_id").getAsString().equals("1"));
		assertTrue("Asserts that the data we get back for a non existant module is the same module", 
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("module_id").getAsString().equals("1"));
	}
	*/
}
