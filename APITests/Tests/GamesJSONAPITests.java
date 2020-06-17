package Tests;

import static org.junit.Assert.assertTrue;

import org.apache.http.ParseException;
import org.junit.Before;
import org.junit.Test;

import APIControllers.GamesAPIController;

public class GamesJSONAPITests {
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
	public void testAPIGamesKeyExists(){
		assertTrue("Asserts that the games key exists in the data sent back by the Games API",
				gamesAPI.callData.has("games"));
	}
	
	@Test
	public void testAPIGamesCount(){
		assertTrue("Asserts that the module PGO Animals gets the correct amount of games in the games JSON Array",
				gamesAPI.callData.get("games").getAsJsonArray().size() == 6);
	}
	
	//Word Scramble Games
	@Test
	public void testAPIWordScambleAppIDField(){
		assertTrue("Asserts that the app_id field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().has("app_id"));
	}
	
	@Test
	public void testAPIWordScambleGameIDField(){
		assertTrue("Asserts that the game_id field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().has("game_id"));
	}
	
	@Test
	public void testAPIWordScambleGameJSONField(){
		assertTrue("Asserts that the game_json field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().has("game_json"));
	}
	
	@Test
	public void testAPIWordScambleGameJSONConfigField(){
		assertTrue("Asserts that the game_json field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().has("config"));
	}
	
	@Test
	public void testAPIWordScambleRoundsField(){
		assertTrue("Asserts that the game_json field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("rounds"));
	}
	
	@Test
	public void testAPIWordScambleGameNameField(){
		assertTrue("Asserts that the gameName field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("gameName"));
	}
	
	@Test
	public void testAPIWordScambleContainerField(){
		assertTrue("Asserts that the container field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("container"));
	}
	
	@Test
	public void testAPIWordScambleAssetsURLField(){
		assertTrue("Asserts that the Assets URL field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("assets_url"));
	}
	
	@Test
	public void testAPIWordScambleScoreTitleField(){
		assertTrue("Asserts that the Score Title field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("scoreTitle"));
	}
	
	@Test
	public void testAPIWordScambleInstructionField(){
		assertTrue("Asserts that the instruction field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("instruction"));
	}
	
	@Test
	public void testAPIWordScambleInstructionTextField(){
		assertTrue("Asserts that the instruction text field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().get("instruction").getAsJsonObject().has("text"));
	}
	
	@Test
	public void testAPIWordScambleComplexityLevelField(){
		assertTrue("Asserts that the Complexity Level field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("complexityLevel"));
	}
	
	@Test
	public void testAPIWordScamblePauseBetweenWordsField(){
		assertTrue("Asserts that the pauseBetweenWords field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("pauseBetweenWords"));
	}
	
	@Test
	public void testAPIWordScambleRewardAnimationTypeField(){
		assertTrue("Asserts that the rewardAnimationType field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("rewardAnimationType"));
	}
	
	@Test
	public void testAPIWordScambleStyleFields(){
		assertTrue("Asserts that the gameTitleColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("gameTitleColor"));
		assertTrue("Asserts that the scoreTitleColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("scoreTitleColor"));
		assertTrue("Asserts that the pictureBorderColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("pictureBorderColor"));
		assertTrue("Asserts that the gameDescriptionColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("gameDescriptionColor"));
		assertTrue("Asserts that the startButtonTextColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("startButtonTextColor"));
		assertTrue("Asserts that the gameWrapperFrameColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("gameWrapperFrameColor"));
		assertTrue("Asserts that the matchedImageLabelColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("matchedImageLabelColor"));
		assertTrue("Asserts that the scoreMatchedImageColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("scoreMatchedImageColor"));
		assertTrue("Asserts that the startButtonBorderColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("startButtonBorderColor"));
		assertTrue("Asserts that the scoreMatchedBorderColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("scoreMatchedBorderColor"));
		assertTrue("Asserts that the scrambledLetterTextColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("scrambledLetterTextColor"));
		assertTrue("Asserts that the suggestionBubbleTextColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("suggestionBubbleTextColor"));
		assertTrue("Asserts that the gameWrapperBackgroundColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("gameWrapperBackgroundColor"));
		assertTrue("Asserts that the scrambledLetterBorderColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("scrambledLetterBorderColor"));
		assertTrue("Asserts that the startButtonBackgroundColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("startButtonBackgroundColor"));
		assertTrue("Asserts that the suggestionBubbleBorderColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("suggestionBubbleBorderColor"));
		assertTrue("Asserts that the scrambledLetterBackgroundColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("scrambledLetterBackgroundColor"));
		assertTrue("Asserts that the gameWrapperScoreBackgroundColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("gameWrapperScoreBackgroundColor"));
		assertTrue("Asserts that the suggestionBubbleBackgroundColor field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("suggestionBubbleBackgroundColor"));
	}
	
	@Test
	public void testAPIWordScambleIterationsTypeField(){
		assertTrue("Asserts that the iterations field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().has("iterations"));
	}
	
	@Test
	public void testAPIWordScambleIterationsImageTypeField(){
		assertTrue("Asserts that the Iterations Image field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("iterations").getAsJsonArray().get(0).getAsJsonObject().has("image"));
	}
	
	@Test
	public void testAPIWordScambleIterationsCorrectTypeField(){
		assertTrue("Asserts that the Iterations Correct field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("iterations").getAsJsonArray().get(0).getAsJsonObject().has("correct"));
	}
	
	@Test
	public void testAPIWordScambleIterationsSuggestionTypeField(){
		assertTrue("Asserts that the Iterations Suggestion field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().get("iterations").getAsJsonArray().get(0).getAsJsonObject().has("suggestion"));
	}
	
	@Test
	public void testAPIWordScambleCustomeSoundsTypeField(){
		assertTrue("Asserts that the Custom Sounds field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().get("game_json").getAsJsonObject().has("customSounds"));
	}
	
	@Test
	public void testAPIWordScambleGameTypeField(){
		assertTrue("Asserts that the Game Type field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().has("game_type"));
	}
	
	@Test
	public void testAPIWordScambleModuleIDTypeField(){
		assertTrue("Asserts that the module_id field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().has("module_id"));
	}
	
	@Test
	public void testAPIWordScambleGameTypeIDTypeField(){
		assertTrue("Asserts that the module_id field is in the wordscramble JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(0).getAsJsonObject().has("game_type_id"));
	}
	
	//Zoom Tests
	@Test
	public void testAPIZoomAppIDField(){
		assertTrue("Asserts that the app_id field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().has("app_id"));
	}
	
	@Test
	public void testAPIZoomGameIDField(){
		assertTrue("Asserts that the game_id field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().has("game_id"));
	}
	
	@Test
	public void testAPIZoomGameJSONField(){
		assertTrue("Asserts that the game_json field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().has("game_json"));
	}
	
	@Test
	public void testAPIZoomGameJSONConfigField(){
		assertTrue("Asserts that the game_json field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().has("config"));
	}
	
	@Test
	public void testAPIZoomGameCalloutField(){
		assertTrue("Asserts that the callout field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("callout"));
	}
	
	@Test
	public void testAPIZoomGameCorrectField(){
		assertTrue("Asserts that the correct field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("correct"));
	}
	
	@Test
	public void testAPIZoomGameTimeoverField(){
		assertTrue("Asserts that the timeOver field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("timeOver"));
	}
	
	@Test
	public void testAPIZoomGameContainerField(){
		assertTrue("Asserts that the container field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("container"));
	}
	
	@Test
	public void testAPIZoomGameCountdownField(){
		assertTrue("Asserts that the countdown field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("countdown"));
	}
	
	@Test
	public void testAPIZoomGameIncorrectField(){
		assertTrue("Asserts that the incorrect field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("incorrect"));
	}
	
	@Test
	public void testAPIZoomGameYourScoreField(){
		assertTrue("Asserts that the yourScore field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("yourScore"));
	}
	
	@Test
	public void testAPIZoomGameAssetURLField(){
		assertTrue("Asserts that the assetURL field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("assets_url"));
	}
	
	
	@Test
	public void testAPIZoomInstructionField(){
		assertTrue("Asserts that the instruction field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("instruction"));
	}
	
	@Test
	public void testAPIZoomInstructionTextField(){
		assertTrue("Asserts that the instruction text field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().get("instruction").getAsJsonObject().has("text"));
	}
	
	@Test
	public void testAPIZoomInstructionSoundField(){
		assertTrue("Asserts that the instruction sound field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().get("instruction").getAsJsonObject().has("sound"));
	}
	
	@Test
	public void testAPIZoomCurrentRoundField(){
		assertTrue("Asserts that the currentRound field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("currentRound"));
	}
	
	@Test
	public void testAPIZoomRewardAnimationTypeField(){
		assertTrue("Asserts that the rewardAnimationType field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("rewardAnimationType"));
	}
	
	@Test
	public void testAPIZoomStyleFields(){
		assertTrue("Asserts that the scoreColor field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("scoreColor"));
		assertTrue("Asserts that the countdownColor field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("countdownColor"));
		assertTrue("Asserts that the backgroundColor field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("backgroundColor"));
		assertTrue("Asserts that the backgroundImage field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("backgroundImage"));
		assertTrue("Asserts that the gameTtitleColor field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("gameTtitleColor"));
		assertTrue("Asserts that the startButtonColor field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("startButtonColor"));
		assertTrue("Asserts that the pictureFrameColor field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("pictureFrameColor"));
		assertTrue("Asserts that the answersButtonsColor field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("answersButtonsColor"));
		assertTrue("Asserts that the countdownSlideColor field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("countdownSlideColor"));
		assertTrue("Asserts that the countdownNumberColor field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("countdownNumberColor"));
		assertTrue("Asserts that the startButtonTextColor field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("startButtonTextColor"));
		assertTrue("Asserts that the startButtonBorderColor field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("startButtonBorderColor"));
		assertTrue("Asserts that the userNotificationsColor field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("userNotificationsColor"));
		assertTrue("Asserts that the answersButtonsTextColor field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("answersButtonsTextColor"));
		assertTrue("Asserts that the answersButtonsBorderColor field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("answersButtonsBorderColor"));
	}
	
	@Test
	public void testAPIZoomIterationsTypeField(){
		assertTrue("Asserts that the iterations field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().has("iterations"));
	}
	
	@Test
	public void testAPIZoomIterationsImageTypeField(){
		assertTrue("Asserts that the iterations image field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("iterations").getAsJsonArray().get(0).getAsJsonObject().has("image"));
	}
	
	@Test
	public void testAPIZoomIterationsAnswersTypeField(){
		assertTrue("Asserts that the iterations answers field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("iterations").getAsJsonArray().get(0).getAsJsonObject().has("answers"));
	}
	
	@Test
	public void testAPIZoomIterationsCorrectTypeField(){
		assertTrue("Asserts that the iterations correct field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("iterations").getAsJsonArray().get(0).getAsJsonObject().has("correct"));
	}
	
	@Test
	public void testAPIZoomIterationsQuestionTypeField(){
		assertTrue("Asserts that the iterations question field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().get("iterations").getAsJsonArray().get(0).getAsJsonObject().has("question"));
	}
	
	public void testAPIZoomCustomeSoundsTypeField(){
		assertTrue("Asserts that the Custom Sounds field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().get("game_json").getAsJsonObject().has("customSounds"));
	}
	
	@Test
	public void testAPIZoomGameTypeField(){
		assertTrue("Asserts that the Game Type field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().has("game_type"));
	}
	
	@Test
	public void testAPIZoomModuleIDTypeField(){
		assertTrue("Asserts that the module_id field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().has("module_id"));
	}
	
	@Test
	public void testAPIZoomGameTypeIDTypeField(){
		assertTrue("Asserts that the module_id field is in the Zoom JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(1).getAsJsonObject().has("game_type_id"));
	}
	
	//Jigsaw Tests
	@Test
	public void testAPIJigsawAppIDField(){
		assertTrue("Asserts that the app_id field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().has("app_id"));
	}
	
	@Test
	public void testAPIJigsawGameIDField(){
		assertTrue("Asserts that the game_id field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().has("game_id"));
	}
	
	@Test
	public void testAPIJigsawGameJSONField(){
		assertTrue("Asserts that the game_json field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().has("game_json"));
	}
	
	@Test
	public void testAPIJigsawGameJSONConfigField(){
		assertTrue("Asserts that the game_json config field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().get("game_json").getAsJsonObject().has("config"));
	}
	
	@Test
	public void testAPIJigsawGameJSONConfigContainerField(){
		assertTrue("Asserts that the config - container field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("container"));
	}
	
	@Test
	public void testAPIJigsawGameJSONConfigAssetsURLField(){
		assertTrue("Asserts that the config - asset_url field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("assets_url"));
	}
	
	@Test
	public void testAPIJigsawGameJSONConfigMainTitleField(){
		assertTrue("Asserts that the config - main_title field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("main_title"));
	}
	
	@Test
	public void testAPIJigsawGameJSONConfigInstructionField(){
		assertTrue("Asserts that the config - instruction field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("instruction"));
	}
	
	@Test
	public void testAPIJigsawInstructionTextField(){
		assertTrue("Asserts that the instruction text field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().get("instruction").getAsJsonObject().has("text"));
	}
	
	@Test
	public void testAPIJigsawInstructionSoundField(){
		assertTrue("Asserts that the instruction sound field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().get("instruction").getAsJsonObject().has("sound"));
	}
	
	@Test
	public void testAPIJigsawGameJSONJigsawField(){
		assertTrue("Asserts that the Jigsaw field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().get("game_json").getAsJsonObject().has("jigsaw"));
	}
	
	@Test
	public void testAPIJigsawGameColumnField(){
		assertTrue("Asserts that the Jigsaw - Col field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().get("game_json").getAsJsonObject().get("jigsaw").getAsJsonArray().get(0).getAsJsonObject().has("col"));
	}
	
	@Test
	public void testAPIJigsawGameImageField(){
		assertTrue("Asserts that the Jigsaw - img field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().get("game_json").getAsJsonObject().get("jigsaw").getAsJsonArray().get(0).getAsJsonObject().has("img"));
	}
	
	@Test
	public void testAPIJigsawGameRowField(){
		assertTrue("Asserts that the Jigsaw - row field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().get("game_json").getAsJsonObject().get("jigsaw").getAsJsonArray().get(0).getAsJsonObject().has("row"));
	}
	
	@Test
	public void testAPIJigsawStyleFields(){
		assertTrue("Asserts that the scoreColor field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("sub_title_color"));
		assertTrue("Asserts that the countdownColor field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("main_title_color"));
		assertTrue("Asserts that the backgroundColor field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("global_background"));
		assertTrue("Asserts that the backgroundImage field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("main_title_border"));
		assertTrue("Asserts that the gameTtitleColor field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("pieces_shadow_color"));
	}
	
	@Test
	public void testAPIJigsawGameTypeField(){
		assertTrue("Asserts that the Game Type field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().has("game_type"));
	}
	
	@Test
	public void testAPIJigsawModuleIDTypeField(){
		assertTrue("Asserts that the module_id field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().has("module_id"));
	}
	
	@Test
	public void testAPIJigsawGameTypeIDTypeField(){
		assertTrue("Asserts that the module_id field is in the Jigsaw JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(2).getAsJsonObject().has("game_type_id"));
	}
	
	//MultiMatch Tests
	@Test
	public void testAPIMultiMatchAppIDField(){
		assertTrue("Asserts that the app_id field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().has("app_id"));
	}
	
	@Test
	public void testAPIMultiMatchGameIDField(){
		assertTrue("Asserts that the game_id field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().has("game_id"));
	}
	
	@Test
	public void testAPIMultiMatchGameJSONField(){
		assertTrue("Asserts that the game_json field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().has("game_json"));
	}
	
	@Test
	public void testAPIMultiMatchGameJSONConfigField(){
		assertTrue("Asserts that the game_json config field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().has("pair"));
	}
	
	@Test
	public void testAPIMultiMatchGameMatchOneField(){
		assertTrue("Asserts that the  pair - matchOne field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("pair").getAsJsonArray().get(0).getAsJsonArray().get(0).getAsJsonObject().has("matchOne"));
	}
	
	@Test
	public void testAPIMultiMatchGameMatchTwoField(){
		assertTrue("Asserts that the  pair - matchTwo field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("pair").getAsJsonArray().get(0).getAsJsonArray().get(0).getAsJsonObject().has("matchTwo"));
	}
	
	@Test
	public void testAPIMultiMatchGameMatchOneNameField(){
		assertTrue("Asserts that the  pair - matchOne - name field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("pair").getAsJsonArray().get(0).getAsJsonArray().get(0).getAsJsonObject().get("matchOne").getAsJsonObject().has("name"));
	}
	
	@Test
	public void testAPIMultiMatchGameMatchOneimageField(){
		assertTrue("Asserts that the  pair - matchOne - name field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("pair").getAsJsonArray().get(0).getAsJsonArray().get(0).getAsJsonObject().get("matchOne").getAsJsonObject().has("image"));
	}
	
	@Test
	public void testAPIMultiMatchGameMatchTwoNameField(){
		assertTrue("Asserts that the  pair - matchTwo - name field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("pair").getAsJsonArray().get(0).getAsJsonArray().get(0).getAsJsonObject().get("matchOne").getAsJsonObject().has("name"));
	}
	
	@Test
	public void testAPIMultiMatchGameMatchTwoimageField(){
		assertTrue("Asserts that the  pair - matchTwo - name field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("pair").getAsJsonArray().get(0).getAsJsonArray().get(0).getAsJsonObject().get("matchOne").getAsJsonObject().has("image"));
	}
	
	@Test
	public void testAPIJigsawConfigFields(){
		assertTrue("Asserts that the marks field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("marks"));
		assertTrue("Asserts that the round field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("round"));
		assertTrue("Asserts that the container field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("container"));
		assertTrue("Asserts that the top_label field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("top_label"));
		assertTrue("Asserts that the assets_url field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("assets_url"));
		assertTrue("Asserts that the main_title field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("main_title"));
		assertTrue("Asserts that the instruction field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("instruction"));
		assertTrue("Asserts that the instruction - text field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().get("instruction").getAsJsonObject().has("text"));
		assertTrue("Asserts that the instruction - matchOneHint field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().get("instruction").getAsJsonObject().has("matchOneHint"));
		assertTrue("Asserts that the instruction - matchTwoHint field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().get("instruction").getAsJsonObject().has("matchTwoHint"));
		assertTrue("Asserts that the bottom_label field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("bottom_label"));
		assertTrue("Asserts that the next_round_text field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("next_round_text"));
		assertTrue("Asserts that the specific_elements field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("specific_elements"));
		assertTrue("Asserts that the top_label_tooltip field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("top_label_tooltip"));
		assertTrue("Asserts that the bottom_label_tooltip field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("bottom_label_tooltip"));
	}
	
	@Test
	public void testAPIMultiMatchGameTypeField(){
		assertTrue("Asserts that the Game Type field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().has("game_type"));
	}
	
	@Test
	public void testAPIMultiMatchModuleIDTypeField(){
		assertTrue("Asserts that the module_id field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().has("module_id"));
	}
	
	@Test
	public void testAPIMultiMatchGameTypeIDTypeField(){
		assertTrue("Asserts that the module_id field is in the MultiMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(3).getAsJsonObject().has("game_type_id"));
	}
	
	//MultiMatch Tests
	@Test
	public void testAPIQuickMatchAppIDField(){
		assertTrue("Asserts that the app_id field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().has("app_id"));
	}
	
	@Test
	public void testAPIQuickMatchGameIDField(){
		assertTrue("Asserts that the game_id field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().has("game_id"));
	}
		
	@Test
	public void testAPIQuickMatchGameJSONField(){
		assertTrue("Asserts that the game_json field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().has("game_json"));
	}
		
	@Test
	public void testAPIQuickMatchGameJSONPairField(){
		assertTrue("Asserts that the game_json - pair field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().has("pair"));
	}
	
	@Test
	public void testAPIQuickMatchGameJSONPairNameField(){
		assertTrue("Asserts that the game_json - pair - name field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("pair").getAsJsonArray().get(0).getAsJsonArray().get(0).getAsJsonObject().has("name"));
	}
	
	@Test
	public void testAPIQuickMatchGameJSONPairImageField(){
		assertTrue("Asserts that the game_json - pair - image field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("pair").getAsJsonArray().get(0).getAsJsonArray().get(0).getAsJsonObject().has("image"));
	}
	
	@Test
	public void testAPIQuickMatchGameJSONPairLabelField(){
		assertTrue("Asserts that the game_json - pair - label field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("pair").getAsJsonArray().get(0).getAsJsonArray().get(0).getAsJsonObject().has("label"));
	}
	
	@Test
	public void testAPIQuickMatchGameJSONPairGameRoundField(){
		assertTrue("Asserts that the game_json - pair - gameRound field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("pair").getAsJsonArray().get(0).getAsJsonArray().get(0).getAsJsonObject().has("gameRound"));
	}
	
	@Test
	public void testAPIQuickMatchGameJSONConfigField(){
		assertTrue("Asserts that the game_json - config field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().has("config"));
	}
	
	@Test
	public void testAPIQuickMatchGameJSONConfigFailsField(){
		assertTrue("Asserts that the game_json - config - fails field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("fails"));
	}
	
	@Test
	public void testAPIQuickMatchGameJSONConfigMatchField(){
		assertTrue("Asserts that the game_json - config - match field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("match"));
	}
	
	@Test
	public void testAPIQuickMatchGameJSONConfigStartField(){
		assertTrue("Asserts that the game_json - config - start field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("start"));
	}
	
	@Test
	public void testAPIQuickMatchGameJSONConfigRoundsField(){
		assertTrue("Asserts that the game_json - config - rounds field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("rounds"));
	}
	
	@Test
	public void testAPIQuickMatchGameJSONConfigYouGotField(){
		assertTrue("Asserts that the game_json - config - youGot field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("youGot"));
	}
	
	@Test
	public void testAPIQuickMatchGameJSONConfigContainerField(){
		assertTrue("Asserts that the game_json - config - container field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("container"));
	}
	
	@Test
	public void testAPIQuickMatchGameJSONConfigAssetURLField(){
		assertTrue("Asserts that the game_json - config - assets_url field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("assets_url"));
	}
	
	@Test
	public void testAPIQuickMatchGameJSONConfigInstructionField(){
		assertTrue("Asserts that the game_json - config - instruction field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("instruction"));
	}
	
	@Test
	public void testAPIQuickMatchGameJSONConfigInstructionTextField(){
		assertTrue("Asserts that the game_json - config - instruction -text field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().get("instruction").getAsJsonObject().has("text"));
	}
	
	@Test
	public void testAPIQuickMatchGameJSONConfigTimeForShowingPairsField(){
		assertTrue("Asserts that the game_json - config - time_for_showing_pairs field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("time_for_showing_pairs"));
	}
	
	@Test
	public void testAPIQuickMatchGameJSONConfigRandomPairsTotalCountField(){
		assertTrue("Asserts that the game_json - config - random_pairs_total_count field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("random_pairs_total_count"));
	}
	
	@Test
	public void testAPIQuickMatchGameJSONConfigMaximumFailuresPerRoundField(){
		assertTrue("Asserts that the game_json - config - maximum_failures_per_round field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("maximum_failures_per_round"));
	}
	
	@Test
	public void testAPIQuickMatchGameJSONConfigIntervalBetweenShowingPairsField(){
		assertTrue("Asserts that the game_json - config - interval_between_showing_pairs field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("interval_between_showing_pairs"));
	}
	
	@Test
	public void testAPIQuickMatchStyleFields(){
		assertTrue("Asserts that the scoreColor field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("cards_background"));
		assertTrue("Asserts that the countdownColor field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("main_part_background"));
		assertTrue("Asserts that the backgroundColor field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("bottom_part_background"));
		assertTrue("Asserts that the backgroundImage field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("start_button_background"));
		assertTrue("Asserts that the gameTtitleColor field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("start_button_text_color"));
	}
	
	@Test
	public void testAPIQuickMatchGameTypeField(){
		assertTrue("Asserts that the Game Type field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().has("game_type"));
	}
	
	@Test
	public void testAPIQuickMatchModuleIDTypeField(){
		assertTrue("Asserts that the module_id field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().has("module_id"));
	}
	
	@Test
	public void testAPIquickMatchGameTypeIDTypeField(){
		assertTrue("Asserts that the module_id field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(4).getAsJsonObject().has("game_type_id"));
	}
	
	//MultiMatch Tests
	@Test
	public void testAPIWhackAppIDField(){
		assertTrue("Asserts that the app_id field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().has("app_id"));
	}
	
	@Test
	public void testAPIWhackMatchGameIDField(){
		assertTrue("Asserts that the game_id field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().has("game_id"));
	}
			
	@Test
	public void testAPIWhackMatchGameJSONField(){
		assertTrue("Asserts that the game_json field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().has("game_json"));
	}
			
	@Test
	public void testAPIWhackMatchGameJSONConfigField(){
		assertTrue("Asserts that the game_json - config - start field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().has("config"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONConfigStartField(){
		assertTrue("Asserts that the game_json - config - start field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("start"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONConfigTimerField(){
		assertTrue("Asserts that the game_json - config - timer field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("timer"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONConfigGameNameField(){
		assertTrue("Asserts that the game_json - config - gameName field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("gameName"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONConfigContainerField(){
		assertTrue("Asserts that the game_json - config - container field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("container"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONConfigAssetsURLField(){
		assertTrue("Asserts that the game_json - config - assets_url field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("assets_url"));
	}
	
	@Test
	public void testAPIWhackGameJSONConfigInstructionField(){
		assertTrue("Asserts that the config - instruction field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("instruction"));
	}
	
	@Test
	public void testAPIWhackInstructionTextField(){
		assertTrue("Asserts that the instruction text field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().get("instruction").getAsJsonObject().has("text"));
	}
	
	@Test
	public void testAPIWhackInstructionSoundField(){
		assertTrue("Asserts that the instruction sound field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().get("instruction").getAsJsonObject().has("sound"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONConfigAssetsRountTitleField(){
		assertTrue("Asserts that the game_json - config - assets_url field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("roundsTitle"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONConfigComplexityLevelField(){
		assertTrue("Asserts that the game_json - config - assets_url field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("complexityLevel"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONConfigGameAssetsURLField(){
		assertTrue("Asserts that the game_json - config - game_assets_url field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("game_assets_url"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONConfigIncorrectAllowedField(){
		assertTrue("Asserts that the game_json - config - incorrectAllowed field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("incorrectAllowed"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONConfigPlayNumberRoundsLField(){
		assertTrue("Asserts that the game_json - config - playNumberRounds field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("playNumberRounds"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONConfigRewardAnimationTypeField(){
		assertTrue("Asserts that the game_json - config - rewardAnimationType field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("config").getAsJsonObject().has("rewardAnimationType"));
	}
	
	@Test
	public void testAPIWatchStyleFields(){
		assertTrue("Asserts that the gameHintColor field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("gameHintColor"));
		assertTrue("Asserts that the gridFrameColor field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("gridFrameColor"));
		assertTrue("Asserts that the roundsTextColor field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("roundsTextColor"));
		assertTrue("Asserts that the wrongDigitColor field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("wrongDigitColor"));
		assertTrue("Asserts that the gameWrapperImage field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("gameWrapperImage"));
		assertTrue("Asserts that the correctDigitColor field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("correctDigitColor"));
		assertTrue("Asserts that the digitsDelimiterColor field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("digitsDelimiterColor"));
		assertTrue("Asserts that the startButtonTextColor field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("startButtonTextColor"));
		assertTrue("Asserts that the startButtonBorderColor field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("startButtonBorderColor"));
		assertTrue("Asserts that the startButtonBackgroundColor field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("startButtonBackgroundColor"));
		assertTrue("Asserts that the roundsScoreCounterBorderColor field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("roundsScoreCounterBorderColor"));
		assertTrue("Asserts that the roundsScoreCounterBackgroundColor field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("styles").getAsJsonObject().has("roundsScoreCounterBackgroundColor"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONConfigClueImagesTypeField(){
		assertTrue("Asserts that the game_json - clueImages field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().has("clueImages"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONConfigClueImagesCategoryTypeField(){
		assertTrue("Asserts that the game_json - clueImages - category field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("clueImages").getAsJsonArray().get(0).getAsJsonObject().has("category"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONConfigClueImagesImagePathTypeField(){
		assertTrue("Asserts that the game_json - clueImages - imagePath field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("clueImages").getAsJsonArray().get(0).getAsJsonObject().getAsJsonObject().has("imagePath"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONConfigAssetImagesTypeField(){
		assertTrue("Asserts that the game_json - assetImages field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().has("assetsImages"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONConfigAssetImagesCategoryTypeField(){
		assertTrue("Asserts that the game_json - assetImages - category field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("assetsImages").getAsJsonArray().get(0).getAsJsonObject().getAsJsonObject().has("category"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONConfigAssetImagesImageNameTypeField(){
		assertTrue("Asserts that the game_json - assetImages - imageName field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("assetsImages").getAsJsonArray().get(0).getAsJsonObject().getAsJsonObject().has("imageName"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONConfigAssetImagesImagePathTypeField(){
		assertTrue("Asserts that the game_json - assetImages - imagePath field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().get("assetsImages").getAsJsonArray().get(0).getAsJsonObject().getAsJsonObject().has("imagePath"));
	}
	
	@Test
	public void testAPIWhackMatchGameJSONCustomSoundsField(){
		assertTrue("Asserts that the game_json - assetImages field is in the Whack JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().get("game_json").getAsJsonObject().has("customSounds"));
	}
	
	@Test
	public void testAPIWhackGameTypeField(){
		assertTrue("Asserts that the Game Type field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().has("game_type"));
	}
	
	@Test
	public void testAPIWhackModuleIDTypeField(){
		assertTrue("Asserts that the module_id field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().has("module_id"));
	}
	
	@Test
	public void testAPIWhackGameTypeIDTypeField(){
		assertTrue("Asserts that the module_id field is in the QuickMatch JSON",
				gamesAPI.callData.get("games").getAsJsonArray().get(5).getAsJsonObject().has("game_type_id"));
	}
}
