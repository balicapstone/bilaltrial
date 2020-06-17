package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import APIControllers.GlossaryTermsAPIController;
import DataClasses.GlossaryData;

public class GlossaryTermsAPITests{
	public GlossaryTermsAPIController glossaryAPI = new GlossaryTermsAPIController();
	public ArrayList<GlossaryData> terms;
	
	@Before
	public void executeBefore(){
		terms = glossaryAPI.getGlossaryTerm("6845");
	}
	
	@Test
	public void testStatusCode(){
		assertTrue("Asserts that the glossary API returns a status code of 200", glossaryAPI.statusCode.equals("HTTP/1.1 200 OK"));
	}
	
	@Test
	public void testAPIWordKeyExists(){
		assertTrue("Asserts that the word key exists in the body data sent back by the Glossary API",
				glossaryAPI.callData.getAsJsonArray().get(0).getAsJsonObject().has("word"));
	}
	
	@Test
	public void testWord(){
		assertTrue("Asserts that the glossary term we get back has the correct word", terms.get(0).word.equals("caecilians"));
	}
	
	@Test
	public void testAPIBaseWordKeyExists(){
		assertTrue("Asserts that the base word key exists in the body data sent back by the Glossary API",
				glossaryAPI.callData.getAsJsonArray().get(0).getAsJsonObject().has("base_word"));
	}
	
	@Test
	public void testBaseword(){
		assertTrue("Asserts that the glossary term we get back has the correct baseword", terms.get(0).baseWord.equals("caecilian"));
	}
	
	@Test
	public void testAPITimeFileKeyExists(){
		assertTrue("Asserts that the time file key exists in the body data sent back by the Glossary API",
				glossaryAPI.callData.getAsJsonArray().get(0).getAsJsonObject().has("time_file"));
	}
	
	@Test
	public void testCorrectTimeFile(){
		assertTrue("Asserts that the glossary term we get back has the correct timefile", terms.get(0).timefile.equals("gl_caecilian_6845.xml"));
	}
	
	@Test
	public void testValidTimeFile(){
		assertTrue("Asserts that the glossary term we get back has a valid timefile", glossaryAPI.verifyXMLActive(terms.get(0).timefile));
	}
	
	@Test
	public void testAPIAudioFileKeyExists(){
		assertTrue("Asserts that the audio file key exists in the body data sent back by the Glossary API",
				glossaryAPI.callData.getAsJsonArray().get(0).getAsJsonObject().has("audio_file"));
	}
	
	@Test
	public void testCorrectAudioFile(){
		assertTrue("Asserts that the glossary term we get back has the correct audiofile", terms.get(0).audioFile.equals("gl_caecilian_6845.mp3"));
	}
	
	@Test
	public void testValidAudioFile(){
		assertTrue("Asserts that the glossary term we get back has a valid audiofile", glossaryAPI.verifyAudioActive(terms.get(0).audioFile));
	}
	
	@Test
	public void testAPIDescriptionKeyExists(){
		assertTrue("Asserts that the description key exists in the body data sent back by the Glossary API",
				glossaryAPI.callData.getAsJsonArray().get(0).getAsJsonObject().has("description"));
	}
	
	@Test
	public void testCorrectDescription(){
		assertTrue("Asserts that the glossary term we get back has the correct description", terms.get(0).description.equals("a wormlike amphibian with no legs"));
	}
	
	@Test
	public void testAPIGlossaryIDKeyExists(){
		assertTrue("Asserts that the glossary id key exists in the body data sent back by the Glossary API",
				glossaryAPI.callData.getAsJsonArray().get(0).getAsJsonObject().has("glossary_id"));
	}
	
	@Test
	public void testCorrectID(){
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).glossaryID.equals("6845"));
	}
	
	@Test
	public void testAPIDescriptionTimecodedKeyExists(){
		assertTrue("Asserts that the description timecoded key exists in the body data sent back by the Glossary API",
				glossaryAPI.callData.getAsJsonArray().get(0).getAsJsonObject().has("description_timecoded"));
	}
	
	@Test
	public void testTimeCodeFirstWord(){
		assertTrue("Asserts that the first word for the timecode is correct", terms.get(0).descriptionTimeCode.get(0).equals("a"));
	}

	@Test
	public void testTimeCodeSecondWord(){
		assertTrue("Asserts that the second word for the timecode is correct", terms.get(0).descriptionTimeCode.get(1).equals("wormlike"));
	}
	
	@Test
	public void testTimeCodeThirdWord(){
		assertTrue("Asserts that the third word for the timecode is correct", terms.get(0).descriptionTimeCode.get(2).equals("amphibian"));
	}

	@Test
	public void testTimeCodeFourthWord(){
		assertTrue("Asserts that the fourth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(3).equals("with"));
	}
	
	@Test
	public void testTimeCodeFifthWord(){
		assertTrue("Asserts that the fifth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(4).equals("no"));
	}
	
	@Test
	public void testTimeCodeSixthWord(){
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(5).equals("legs"));
	}
	
	@Test
	public void testIncorrectGlossaryID(){
		terms = glossaryAPI.getGlossaryTerm("123456789");
		
		assertTrue("Asserts that an api call with a bad glossary term ID has a status code of 502", glossaryAPI.statusCode.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that an api call with a bad glossary term ID gets an empty array", glossaryAPI.entity.equals("[]"));
	}
	
	@Test
	public void testNullGlossaryID(){
		terms = glossaryAPI.getGlossaryTerm("");
		
		assertTrue("Asserts that an api call with a bad glossery term ID has a status code of 502", glossaryAPI.statusCode.equals("HTTP/1.1 500 Internal Server Error"));
		assertTrue("Asserts that the glossary term we get back has the correct id", glossaryAPI.entity.equals("{\"errorMessage\":\"Internal Server Error\"}"));
	}
	
	@Test
	public void testGlossaryTermScience(){
		terms = glossaryAPI.getGlossaryTerm("1077");
		
		assertTrue("Asserts that the glossary API returns a status code of 200", glossaryAPI.statusCode.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the glossary term we get back has the correct word", terms.get(0).word.equals("migrate"));
		assertTrue("Asserts that the glossary term we get back has the correct baseword", terms.get(0).baseWord.equals("migrate"));
		assertTrue("Asserts that the glossary term we get back has the correct timefile", terms.get(0).timefile.equals("gl_migrate_1077.xml"));
		assertTrue("Asserts that the glossary term we get back has a valid timefile", glossaryAPI.verifyXMLActive(terms.get(0).timefile));
		assertTrue("Asserts that the glossary term we get back has the correct audiofile", terms.get(0).audioFile.equals("gl_migrate_1077.mp3"));
		assertTrue("Asserts that the glossary term we get back has a valid audiofile", glossaryAPI.verifyAudioActive(terms.get(0).audioFile));
		assertTrue("Asserts that the glossary term we get back has the correct description", 
				terms.get(0).description.equals("to move from one place to another when seasons change or to find food"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).glossaryID.equals("1077"));
		assertTrue("Asserts that the first word for the timecode is correct", terms.get(0).descriptionTimeCode.get(0).equals("to"));
		assertTrue("Asserts that the second word for the timecode is correct", terms.get(0).descriptionTimeCode.get(1).equals("move"));
		assertTrue("Asserts that the third word for the timecode is correct", terms.get(0).descriptionTimeCode.get(2).equals("from"));
		assertTrue("Asserts that the fourth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(3).equals("one"));
		assertTrue("Asserts that the fifth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(4).equals("place"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(5).equals("to"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(6).equals("another"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(7).equals("when"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(8).equals("seasons"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(9).equals("change"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(10).equals("or"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(11).equals("to"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(12).equals("find"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(13).equals("food"));
	}
	
	@Test
	public void testGlossaryTermBiographies(){
		terms = glossaryAPI.getGlossaryTerm("14574");
		
		assertTrue("Asserts that the glossary API returns a status code of 200", glossaryAPI.statusCode.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the glossary term we get back has the correct word", terms.get(0).word.equals("rookie"));
		assertTrue("Asserts that the glossary term we get back has the correct baseword", terms.get(0).baseWord.equals("rookie"));
		assertTrue("Asserts that the glossary term we get back has the correct timefile", terms.get(0).timefile.equals("gl_rookie_14574.xml"));
		assertTrue("Asserts that the glossary term we get back has a valid timefile", glossaryAPI.verifyXMLActive(terms.get(0).timefile));
		assertTrue("Asserts that the glossary term we get back has the correct audiofile", terms.get(0).audioFile.equals("gl_rookie_14574.mp3"));
		assertTrue("Asserts that the glossary term we get back has a valid audiofile", glossaryAPI.verifyAudioActive(terms.get(0).audioFile));
		assertTrue("Asserts that the glossary term we get back has the correct description", 
				terms.get(0).description.equals("a first-year player"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).glossaryID.equals("14574"));
		assertTrue("Asserts that the first word for the timecode is correct", terms.get(0).descriptionTimeCode.get(0).equals("a"));
		assertTrue("Asserts that the second word for the timecode is correct", terms.get(0).descriptionTimeCode.get(1).equals("first-year"));
		assertTrue("Asserts that the third word for the timecode is correct", terms.get(0).descriptionTimeCode.get(2).equals("player"));
	}
	
	@Test
	public void testGlossaryTermSocialStudies(){
		terms = glossaryAPI.getGlossaryTerm("5332");
		
		assertTrue("Asserts that the glossary API returns a status code of 200", glossaryAPI.statusCode.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the glossary term we get back has the correct word", terms.get(0).word.equals("combine"));
		assertTrue("Asserts that the glossary term we get back has the correct baseword", terms.get(0).baseWord.equals("combine"));
		assertTrue("Asserts that the glossary term we get back has the correct timefile", terms.get(0).timefile.equals("gl_combine_5332.xml"));
		assertTrue("Asserts that the glossary term we get back has a valid timefile", glossaryAPI.verifyXMLActive(terms.get(0).timefile));
		assertTrue("Asserts that the glossary term we get back has the correct audiofile", terms.get(0).audioFile.equals("gl_combine_5332.mp3"));
		assertTrue("Asserts that the glossary term we get back has a valid audiofile", glossaryAPI.verifyAudioActive(terms.get(0).audioFile));
		assertTrue("Asserts that the glossary term we get back has the correct description", 
				terms.get(0).description.equals("a large farm machine that is used to gather crops"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).glossaryID.equals("5332"));
		assertTrue("Asserts that the first word for the timecode is correct", terms.get(0).descriptionTimeCode.get(0).equals("a"));
		assertTrue("Asserts that the second word for the timecode is correct", terms.get(0).descriptionTimeCode.get(1).equals("large"));
		assertTrue("Asserts that the third word for the timecode is correct", terms.get(0).descriptionTimeCode.get(2).equals("farm"));
		assertTrue("Asserts that the fourth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(3).equals("machine"));
		assertTrue("Asserts that the fifth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(4).equals("that"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(5).equals("is"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(6).equals("used"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(7).equals("to"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(8).equals("gather"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(9).equals("crops"));
	}
	
	@Test
	public void testGlossaryTermDinosaurs(){
		terms = glossaryAPI.getGlossaryTerm("6907");
		
		assertTrue("Asserts that the glossary API returns a status code of 200", glossaryAPI.statusCode.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the glossary term we get back has the correct word", terms.get(0).word.equals("spike"));
		assertTrue("Asserts that the glossary term we get back has the correct baseword", terms.get(0).baseWord.equals("spike"));
		assertTrue("Asserts that the glossary term we get back has the correct timefile", terms.get(0).timefile.equals("gl_spike_6907.xml"));
		assertTrue("Asserts that the glossary term we get back has a valid timefile", glossaryAPI.verifyXMLActive(terms.get(0).timefile));
		assertTrue("Asserts that the glossary term we get back has the correct audiofile", terms.get(0).audioFile.equals("gl_spike_6907.mp3"));
		assertTrue("Asserts that the glossary term we get back has a valid audiofile", glossaryAPI.verifyAudioActive(terms.get(0).audioFile));
		assertTrue("Asserts that the glossary term we get back has the correct description", 
				terms.get(0).description.equals("a sharp, pointy object; dinosaurs used bony spikes for defense"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).glossaryID.equals("6907"));
		assertTrue("Asserts that the first word for the timecode is correct", terms.get(0).descriptionTimeCode.get(0).equals("a"));
		assertTrue("Asserts that the second word for the timecode is correct", terms.get(0).descriptionTimeCode.get(1).equals("sharp,"));
		assertTrue("Asserts that the third word for the timecode is correct", terms.get(0).descriptionTimeCode.get(2).equals("pointy"));
		assertTrue("Asserts that the fourth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(3).equals("object;"));
		assertTrue("Asserts that the fifth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(4).equals("dinosaurs"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(5).equals("used"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(6).equals("bony"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(7).equals("spikes"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(8).equals("for"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(9).equals("defense"));
	}
	
	@Test
	public void testGlossaryTermAnimales(){
		glossaryAPI.getGlossaryTerm("7715");
		terms = glossaryAPI.getGlossaryObject();
		
		assertTrue("Asserts that the glossary API returns a status code of 200", glossaryAPI.statusCode.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the glossary term we get back has the correct word", terms.get(0).word.equals("crías"));
		assertTrue("Asserts that the glossary term we get back has the correct baseword", terms.get(0).baseWord.equals("cría"));
		assertTrue("Asserts that the glossary term we get back has the correct timefile", terms.get(0).timefile.equals("gl_cra_7715.xml"));
		assertTrue("Asserts that the glossary term we get back has a valid timefile", glossaryAPI.verifyXMLActive(terms.get(0).timefile));
		assertTrue("Asserts that the glossary term we get back has the correct audiofile", terms.get(0).audioFile.equals("gl_cra_7715.mp3"));
		assertTrue("Asserts that the glossary term we get back has a valid audiofile", glossaryAPI.verifyAudioActive(terms.get(0).audioFile));
		assertTrue("Asserts that the glossary term we get back has the correct description", 
				terms.get(0).description.equals("tiburón bebé"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).glossaryID.equals("7715"));
		assertTrue("Asserts that the first word for the timecode is correct", terms.get(0).descriptionTimeCode.get(0).equals("tiburón"));
		assertTrue("Asserts that the second word for the timecode is correct", terms.get(0).descriptionTimeCode.get(1).equals("bebé"));
	}

	@Test
	public void testGlossaryTermCiencia(){
		terms = glossaryAPI.getGlossaryTerm("8429");
		
		assertTrue("Asserts that the glossary API returns a status code of 200", glossaryAPI.statusCode.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the glossary term we get back has the correct word", terms.get(0).word.equals("hábitat"));
		assertTrue("Asserts that the glossary term we get back has the correct baseword", terms.get(0).baseWord.equals("hábitat"));
		assertTrue("Asserts that the glossary term we get back has the correct timefile", terms.get(0).timefile.equals("gl_hbitat_8429.xml"));
		assertTrue("Asserts that the glossary term we get back has a valid timefile", glossaryAPI.verifyXMLActive(terms.get(0).timefile));
		assertTrue("Asserts that the glossary term we get back has the correct audiofile", terms.get(0).audioFile.equals("gl_hbitat_8429.mp3"));
		assertTrue("Asserts that the glossary term we get back has a valid audiofile", glossaryAPI.verifyAudioActive(terms.get(0).audioFile));
		assertTrue("Asserts that the glossary term we get back has the correct description", 
				terms.get(0).description.equals("lugar y condiciones naturales en que vive una planta o un animal"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).glossaryID.equals("8429"));
		assertTrue("Asserts that the first word for the timecode is correct", terms.get(0).descriptionTimeCode.get(0).equals("lugar"));
		assertTrue("Asserts that the second word for the timecode is correct", terms.get(0).descriptionTimeCode.get(1).equals("y"));
		assertTrue("Asserts that the third word for the timecode is correct", terms.get(0).descriptionTimeCode.get(2).equals("condiciones"));
		assertTrue("Asserts that the fourth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(3).equals("naturales"));
		assertTrue("Asserts that the fifth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(4).equals("en"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(5).equals("que"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(6).equals("vive"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(7).equals("una"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(8).equals("planta"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(9).equals("o"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(10).equals("un"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(11).equals("animal"));
	}
	
	@Test
	public void testGlossaryTermBiografias(){
		terms = glossaryAPI.getGlossaryTerm("14364");
		
		assertTrue("Asserts that the glossary API returns a status code of 200", glossaryAPI.statusCode.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the glossary term we get back has the correct word", terms.get(0).word.equals("proteger"));
		assertTrue("Asserts that the glossary term we get back has the correct baseword", terms.get(0).baseWord.equals("proteger"));
		assertTrue("Asserts that the glossary term we get back has the correct timefile", terms.get(0).timefile.equals("gl_proteger_14364.xml"));
		assertTrue("Asserts that the glossary term we get back has a valid timefile", glossaryAPI.verifyXMLActive(terms.get(0).timefile));
		assertTrue("Asserts that the glossary term we get back has the correct audiofile", terms.get(0).audioFile.equals("gl_proteger_14364.mp3"));
		assertTrue("Asserts that the glossary term we get back has a valid audiofile", glossaryAPI.verifyAudioActive(terms.get(0).audioFile));
		assertTrue("Asserts that the glossary term we get back has the correct description", 
				terms.get(0).description.equals("mantener a salvo"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).glossaryID.equals("14364"));
		assertTrue("Asserts that the first word for the timecode is correct", terms.get(0).descriptionTimeCode.get(0).equals("mantener"));
		assertTrue("Asserts that the second word for the timecode is correct", terms.get(0).descriptionTimeCode.get(1).equals("a"));
		assertTrue("Asserts that the third word for the timecode is correct", terms.get(0).descriptionTimeCode.get(2).equals("salvo"));
	}
	
	@Test
	public void testGlossaryTermEstudiosSociales(){
		terms = glossaryAPI.getGlossaryTerm("8203");
		
		assertTrue("Asserts that the glossary API returns a status code of 200", glossaryAPI.statusCode.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the glossary term we get back has the correct word", terms.get(0).word.equals("llanura"));
		assertTrue("Asserts that the glossary term we get back has the correct baseword", terms.get(0).baseWord.equals("llanura"));
		assertTrue("Asserts that the glossary term we get back has the correct timefile", terms.get(0).timefile.equals("gl_llanura_8203.xml"));
		assertTrue("Asserts that the glossary term we get back has a valid timefile", glossaryAPI.verifyXMLActive(terms.get(0).timefile));
		assertTrue("Asserts that the glossary term we get back has the correct audiofile", terms.get(0).audioFile.equals("gl_llanura_8203.mp3"));
		assertTrue("Asserts that the glossary term we get back has a valid audiofile", glossaryAPI.verifyAudioActive(terms.get(0).audioFile));
		assertTrue("Asserts that the glossary term we get back has the correct description", 
				terms.get(0).description.equals("área grande y plana de terreno"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).glossaryID.equals("8203"));
		assertTrue("Asserts that the first word for the timecode is correct", terms.get(0).descriptionTimeCode.get(0).equals("área"));
		assertTrue("Asserts that the second word for the timecode is correct", terms.get(0).descriptionTimeCode.get(1).equals("grande"));
		assertTrue("Asserts that the third word for the timecode is correct", terms.get(0).descriptionTimeCode.get(2).equals("y"));
		assertTrue("Asserts that the fourth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(3).equals("plana"));
		assertTrue("Asserts that the fifth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(4).equals("de"));
		assertTrue("Asserts that the sixth word for the timecode is correct", terms.get(0).descriptionTimeCode.get(5).equals("terreno"));
	}
	
	@Test
	public void testAllGlossaryTermsForAnimalsArticle(){
		terms = glossaryAPI.getGlossaryTerm("1264, 6889, 7017, 14852, 18837");

		
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).glossaryID.equals("1264"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(1).glossaryID.equals("6889"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(2).glossaryID.equals("7017"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(3).glossaryID.equals("14852"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(4).glossaryID.equals("18837"));
		
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).word.equals("forests"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(1).word.equals("mammals"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(2).word.equals("shrubs"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(3).word.equals("predators"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(4).word.equals("snouts"));
	}
	
	@Test
	public void testMultipleGlossaryTermsHaveAllCorrectFields(){
		terms = glossaryAPI.getGlossaryTerm("1264, 6889, 7017, 14852, 18837");

		for(int i = 0; i < terms.size(); i++){
			assertTrue("Asserts that the glossary term we get back from the api has the word data key", 
					glossaryAPI.callData.getAsJsonArray().get(i).getAsJsonObject().has("word"));
			assertTrue("Asserts that the glossary term we get back from the api has the base_word data key", 
					glossaryAPI.callData.getAsJsonArray().get(i).getAsJsonObject().has("base_word"));
			assertTrue("Asserts that the glossary term we get back from the api has the time_file data key", 
					glossaryAPI.callData.getAsJsonArray().get(i).getAsJsonObject().has("time_file"));
			assertTrue("Asserts that the glossary term we get back from the api has the audio_file data key", 
					glossaryAPI.callData.getAsJsonArray().get(i).getAsJsonObject().has("audio_file"));
			assertTrue("Asserts that the glossary term we get back from the api has the description data key", 
					glossaryAPI.callData.getAsJsonArray().get(i).getAsJsonObject().has("description"));
			assertTrue("Asserts that the glossary term we get back from the api has the glossary_id data key", 
					glossaryAPI.callData.getAsJsonArray().get(i).getAsJsonObject().has("glossary_id"));
			assertTrue("Asserts that the glossary term we get back from the api has the description_timecoded data key", 
					glossaryAPI.callData.getAsJsonArray().get(i).getAsJsonObject().has("description_timecoded"));
		}

	}
	
	@Test
	public void testMissingGlossaryTermsInMany(){
		terms = glossaryAPI.getGlossaryTerm("1264, 6889, , 14852, 18837");
		
		assertTrue("Asserts that the glossary term we get back has the correct id", glossaryAPI.entity.equals("{\"message\": \"Internal server error\"}"));
	}
	
	@Test
	public void testOneWordInManyGlossaryTerms(){
		terms = glossaryAPI.getGlossaryTerm("1264, 6889, test, 14852, 18837");
		
		assertTrue("Asserts that the glossary term we get back has the correct id", glossaryAPI.entity.equals("{\"message\": \"Internal server error\"}"));
	}
	
	@Test
	public void testTooManyCommasInManyGlossaryTerms(){
		terms = glossaryAPI.getGlossaryTerm("1264, 6889, 7017, 14852, 18837,");
		
		assertTrue("Asserts that the glossary term we get back has the correct id", glossaryAPI.entity.equals("{\"message\": \"Internal server error\"}"));
	}
	
	@Test
	public void testTooManyCommasWrongPlaceInManyGlossaryTerms(){
		terms = glossaryAPI.getGlossaryTerm("1264, 6889, 7017, 14852,, 18837");
		
		assertTrue("Asserts that the glossary term we get back has the correct id", glossaryAPI.entity.equals("{\"message\": \"Internal server error\"}"));
	}
	
	@Test
	public void testLeadingCommaInManyGlossaryTerms(){
		terms = glossaryAPI.getGlossaryTerm(",1264, 6889, 7017, 14852, 18837");
		
		assertTrue("Asserts that the glossary term we get back has the correct id", glossaryAPI.entity.equals("{\"message\": \"Internal server error\"}"));
	}
	
	@Test
	public void testEmptyBesideOneCommaQueryInManyGlossaryTerms(){
		terms = glossaryAPI.getGlossaryTerm(",");
		
		assertTrue("Asserts that the glossary term we get back has the correct id", glossaryAPI.entity.equals("{\"message\": \"Internal server error\"}"));
	}
	
	@Test
	public void testOneBadGlossaryTermDoesNotBreakQueryFront(){
		terms = glossaryAPI.getGlossaryTerm("3, 6889, 7017, 14852, 18837");

		
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).glossaryID.equals("6889"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(1).glossaryID.equals("7017"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(2).glossaryID.equals("14852"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(3).glossaryID.equals("18837"));
		
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).word.equals("mammals"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(1).word.equals("shrubs"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(2).word.equals("predators"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(3).word.equals("snouts"));
	}
	
	@Test
	public void testOneBadGlossaryTermDoesNotBreakQueryMiddle(){
		terms = glossaryAPI.getGlossaryTerm("1264, 6889, 3, 14852, 18837");

		
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).glossaryID.equals("1264"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(1).glossaryID.equals("6889"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(2).glossaryID.equals("14852"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(3).glossaryID.equals("18837"));
		
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).word.equals("forests"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(1).word.equals("mammals"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(2).word.equals("predators"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(3).word.equals("snouts"));
	}
	
	@Test
	public void testOneBadGlossaryTermDoesNotBreakQueryEnd(){
		terms = glossaryAPI.getGlossaryTerm("1264, 6889, 7017, 14852, 3");

		
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).glossaryID.equals("1264"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(1).glossaryID.equals("6889"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(2).glossaryID.equals("7017"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(3).glossaryID.equals("14852"));
		
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).word.equals("forests"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(1).word.equals("mammals"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(2).word.equals("shrubs"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(3).word.equals("predators"));
	}
	
	@Test
	public void testDuplicateGlossaryTerms(){
		terms = glossaryAPI.getGlossaryTerm("4297, 5293, 15295, 4297, 5293, 15295");

		
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).glossaryID.equals("4297"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(1).glossaryID.equals("5293"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(2).glossaryID.equals("15295"));
		assertTrue("Asserts that the glossary term we get back has the correct id", (terms.size() == 3));
		
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).word.equals("fire escape plan"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(1).word.equals("fire extinguisher"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(2).word.equals("firebreak"));
	}
	
	@Test
	public void testThirtyGlossaryTerms(){
		terms = glossaryAPI.getGlossaryTerm("4297, 5293, 15295, 1766, 2520, 1543, 659, 5880, 6706, 6007, 2768, 2091, 18785, 2578, 6512, "
				+ "2604, 2853, 2847, 3191, 5837, 6375, 16367, 16368, 3056, 16369, 1827, 1828, 1120, 15857, 15697");

		assertTrue("Asserts that the glossary term we get back has the correct id", (terms.size() == 30));
		
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(0).word.equals("saltwater"));
		assertTrue("Asserts that the glossary term we get back has the correct id", terms.get(29).word.equals("Air Force"));
	}
}
