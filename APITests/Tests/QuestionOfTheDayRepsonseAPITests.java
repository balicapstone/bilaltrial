package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import APIControllers.QuestionOfTheDayResponseAPIController;
import DataClasses.QOTDQuestionsData;

public class QuestionOfTheDayRepsonseAPITests {
	public QuestionOfTheDayResponseAPIController qotdAPI = new QuestionOfTheDayResponseAPIController();
	QOTDQuestionsData question;
	
	@Before
	public void executeBefore(){
		question = qotdAPI.getQuestionOfTheDayResponseData("6", "2019-01-01", "2019-12-31");
	}
	
	@Test
	public void testAPIStatus(){
		assertTrue("Asserts that the status code returned by the api call is valid and a 200",
				qotdAPI.status.equals("HTTP/1.1 200 OK"));
	}
	
	@Test
	public void testAnswersKeyExists(){
		assertTrue("Asserts that the 'answers' key is present in the json data", qotdAPI.callData.has("answers"));
	}
	
	@Test
	public void testAnswersIDKeyExists(){
		assertTrue("Asserts that the 'answer_id' key is present in the json data", 
				qotdAPI.callData.get("answers").getAsJsonArray().get(0).getAsJsonObject().has("answer_id"));
	}
	
	@Test
	public void testAnswersIDDataExists(){
		assertTrue("Asserts that the 'answer_id' data is present in the json data and accurate",
				question.answers.get(0).id.equals("36") && 
				question.answers.get(1).id.equals("37") &&
				question.answers.get(2).id.equals("38") &&
				question.answers.get(3).id.equals("39") &&
				question.answers.get(4).id.equals("40") &&
				question.answers.get(5).id.equals("41"));
	}
	
	@Test
	public void testAnswersTextKeyExists(){
		assertTrue("Asserts that the 'answer_text' key is present in the json data", 
				qotdAPI.callData.get("answers").getAsJsonArray().get(0).getAsJsonObject().has("answer_text"));
	}
	
	@Test
	public void testAnswersTextDataExists(){
		assertTrue("Asserts that the 'answer_text' data is present in the json data and accurate",
				question.answers.get(0).answer.equals("Triceratops") && 
				question.answers.get(1).answer.equals("Tyrannosaurus Rex") &&
				question.answers.get(2).answer.equals("Stegosaurus") &&
				question.answers.get(3).answer.equals("Velociraptor") &&
				question.answers.get(4).answer.equals("Velociraptor") &&
				question.answers.get(5).answer.equals("Apatosaurus"));
	}
	
	@Test
	public void testAnswersCountKeyExists(){
		assertTrue("Asserts that the 'answer_text' key is present in the json data", 
				qotdAPI.callData.get("answers").getAsJsonArray().get(0).getAsJsonObject().has("answer_count"));
	}
	
	@Test
	public void testAnswersCountDataExists(){
		assertTrue("Asserts that the 'answer_count' data is present in the json data and accurate",
				question.answers.get(0).answerCount.equals("0") && 
				question.answers.get(1).answerCount.equals("0") &&
				question.answers.get(2).answerCount.equals("0") &&
				question.answers.get(3).answerCount.equals("0") &&
				question.answers.get(4).answerCount.equals("0") &&
				question.answers.get(5).answerCount.equals("0"));
	}
	
	@Test
	public void testQuestionIDKeyExists(){
		assertTrue("Asserts that the 'question_id' key is present in the json data", qotdAPI.callData.has("question_id"));
	}
	
	@Test
	public void testQuestionDataExists(){
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.id.equals("6"));
	}
	
	@Test
	public void testQuestionTextKeyExists(){
		assertTrue("Asserts that the 'question_text' key is present in the json data", qotdAPI.callData.has("question_text"));
	}
	
	@Test
	public void testQuestionTextDataExists(){
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.question.equals("What is your favorite dinosaur?"));
	}
	
	@Test
	public void testPGOScienceQuestionData(){
		question = qotdAPI.getQuestionOfTheDayResponseData("42", "2018-01-01", "2018-12-31");
		
		assertTrue("Asserts that the 'answer_id' data is present in the json data and accurate",
				question.answers.get(0).id.equals("208") && 
				question.answers.get(1).id.equals("209") &&
				question.answers.get(2).id.equals("210") &&
				question.answers.get(3).id.equals("211"));
		assertTrue("Asserts that the 'answer_text' data is present in the json data and accurate",
				question.answers.get(0).answer.equals("A Glacier") && 
				question.answers.get(1).answer.equals("A Cave") &&
				question.answers.get(2).answer.equals("A Mountain") &&
				question.answers.get(3).answer.equals("A Coral Reef"));
		assertTrue("Asserts that the 'answer_count' data is present in the json data and accurate",
				(question.answers.get(0).answerCount != null) && 
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.id.equals("42"));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.question.equals("Which would you want to see most?"));
	}
	
	@Test
	public void testPGOBiographiesQuestionData(){
		question = qotdAPI.getQuestionOfTheDayResponseData("26", "2018-01-01", "2018-12-31");
		
		assertTrue("Asserts that the 'answer_id' data is present in the json data and accurate",
				question.answers.get(0).id.equals("144") && 
				question.answers.get(1).id.equals("145") &&
				question.answers.get(2).id.equals("146") &&
				question.answers.get(3).id.equals("147"));
		assertTrue("Asserts that the 'answer_text' data is present in the json data and accurate",
				question.answers.get(0).answer.equals("George Washington") && 
				question.answers.get(1).answer.equals("Abraham Lincoln") &&
				question.answers.get(2).answer.equals("John F. Kennedy") &&
				question.answers.get(3).answer.equals("Barack Obama"));
		assertTrue("Asserts that the 'answer_count' data is present in the json data and accurate",
				(question.answers.get(0).answerCount != null) && 
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.id.equals("26"));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.question.equals("Who is your favorite president?"));
	}
	
	@Test
	public void testPGOSocialStudiesQuestionData(){
		question = qotdAPI.getQuestionOfTheDayResponseData("55", "2018-01-01", "2018-12-31");
		
		assertTrue("Asserts that the 'answer_id' data is present in the json data and accurate",
				question.answers.get(0).id.equals("260") && 
				question.answers.get(1).id.equals("261") &&
				question.answers.get(2).id.equals("262") &&
				question.answers.get(3).id.equals("263"));
		assertTrue("Asserts that the 'answer_text' data is present in the json data and accurate",
				question.answers.get(0).answer.equals("Statue of Liberty") && 
				question.answers.get(1).answer.equals("Liberty Bell") &&
				question.answers.get(2).answer.equals("Washington Monument") &&
				question.answers.get(3).answer.equals("Thomas Jefferson Memorial"));
		assertTrue("Asserts that the 'answer_count' data is present in the json data and accurate",
				(question.answers.get(0).answerCount != null) && 
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.id.equals("55"));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.question.equals("Which U.S. landmark would you most like to see?"));
	}
	
	@Test
	public void testPGODinosaursQuestionData(){
		question = qotdAPI.getQuestionOfTheDayResponseData("76", "2018-01-01", "2018-12-31");
		
		assertTrue("Asserts that the 'answer_id' data is present in the json data and accurate",
				question.answers.get(0).id.equals("335") && 
				question.answers.get(1).id.equals("336") &&
				question.answers.get(2).id.equals("337"));
		assertTrue("Asserts that the 'answer_text' data is present in the json data and accurate",
				question.answers.get(0).answer.equals("Jurassic") && 
				question.answers.get(1).answer.equals("Cretaceous") &&
				question.answers.get(2).answer.equals("Triassic"));
		assertTrue("Asserts that the 'answer_count' data is present in the json data and accurate",
				(question.answers.get(0).answerCount != null) && 
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.id.equals("76"));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.question.equals("What is your favorite dinosaur time period?"));
	}
	
	@Test
	public void testPGOAnimalesQuestionData(){
		question = qotdAPI.getQuestionOfTheDayResponseData("118", "2018-01-01", "2018-12-31");
		
		assertTrue("Asserts that the 'answer_id' data is present in the json data and accurate",
				question.answers.get(0).id.equals("507") && 
				question.answers.get(1).id.equals("508") &&
				question.answers.get(2).id.equals("509") &&
				question.answers.get(3).id.equals("510"));
		assertTrue("Asserts that the 'answer_text' data is present in the json data and accurate",
				question.answers.get(0).answer.equals("Canguro") && 
				question.answers.get(1).answer.equals("Koala") &&
				question.answers.get(2).answer.equals("Zarigüeya") &&
				question.answers.get(3).answer.equals("Petauro del Azúcar"));
		assertTrue("Asserts that the 'answer_count' data is present in the json data and accurate",
				(question.answers.get(0).answerCount != null) && 
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.id.equals("118"));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.question.equals("¿Cuál es tu marsupial favorito?"));
	}
	
	@Test
	public void testPGOCienciaQuestionData(){
		question = qotdAPI.getQuestionOfTheDayResponseData("232", "2018-01-01", "2018-12-31");
		
		assertTrue("Asserts that the 'answer_id' data is present in the json data and accurate",
				question.answers.get(0).id.equals("924") && 
				question.answers.get(1).id.equals("925") &&
				question.answers.get(2).id.equals("926") &&
				question.answers.get(3).id.equals("978"));
		assertTrue("Asserts that the 'answer_text' data is present in the json data and accurate",
				question.answers.get(0).answer.equals("Venus") && 
				question.answers.get(1).answer.equals("Marte") &&
				question.answers.get(2).answer.equals("Júpiter") &&
				question.answers.get(3).answer.equals("Saturno"));
		assertTrue("Asserts that the 'answer_count' data is present in the json data and accurate",
				(question.answers.get(0).answerCount != null) && 
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.id.equals("232"));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.question.equals("¿Cuál es tu planeta favorito?"));
	}
	
	@Test
	public void testPGOBiografiasQuestionData(){
		question = qotdAPI.getQuestionOfTheDayResponseData("217", "2018-01-01", "2018-12-31");
		
		assertTrue("Asserts that the 'answer_id' data is present in the json data and accurate",
				question.answers.get(0).id.equals("870") && 
				question.answers.get(1).id.equals("871") &&
				question.answers.get(2).id.equals("872") &&
				question.answers.get(3).id.equals("873"));
		assertTrue("Asserts that the 'answer_text' data is present in the json data and accurate",
				question.answers.get(0).answer.equals("Jesse Owens") && 
				question.answers.get(1).answer.equals("Babe Didrikson Zaharias") &&
				question.answers.get(2).answer.equals("Muhammad Ali") &&
				question.answers.get(3).answer.equals("Wilma Rudolph"));
		assertTrue("Asserts that the 'answer_count' data is present in the json data and accurate",
				(question.answers.get(0).answerCount != null) && 
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.id.equals("217"));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.question.equals("¿Qué deportista sería el que más te gustaría conocer?"));
	}
	
	@Test
	public void testPGOEstudioSocialesQuestionData(){
		question = qotdAPI.getQuestionOfTheDayResponseData("231", "2018-01-01", "2018-12-31");
		
		assertTrue("Asserts that the 'answer_id' data is present in the json data and accurate",
				question.answers.get(0).id.equals("921") && 
				question.answers.get(1).id.equals("922") &&
				question.answers.get(2).id.equals("923") &&
				question.answers.get(3).id.equals("982"));
		assertTrue("Asserts that the 'answer_text' data is present in the json data and accurate",
				question.answers.get(0).answer.equals("Bibliotecario") && 
				question.answers.get(1).answer.equals("Trabajador de la construcción") &&
				question.answers.get(2).answer.equals("Enfermera") &&
				question.answers.get(3).answer.equals("Oficial de policía"));
		assertTrue("Asserts that the 'answer_count' data is present in the json data and accurate",
				(question.answers.get(0).answerCount != null) && 
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.id.equals("231"));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.question.equals("¿Qué empleo preferirías tener?"));
	}
	
	@Test
	public void testPGNStatesQuestionData(){
		question = qotdAPI.getQuestionOfTheDayResponseData("63", "2018-01-01", "2018-12-31");
		
		assertTrue("Asserts that the 'answer_id' data is present in the json data and accurate",
				question.answers.get(0).id.equals("283") && 
				question.answers.get(1).id.equals("284") &&
				question.answers.get(2).id.equals("285") &&
				question.answers.get(3).id.equals("286"));
		assertTrue("Asserts that the 'answer_text' data is present in the json data and accurate",
				question.answers.get(0).answer.equals("Santa Fe, New Mexico") && 
				question.answers.get(1).answer.equals("Atlanta, Georgia") &&
				question.answers.get(2).answer.equals("Boston, Massachusetts") &&
				question.answers.get(3).answer.equals("Cheyenne, Wyoming"));
		assertTrue("Asserts that the 'answer_count' data is present in the json data and accurate",
				(question.answers.get(0).answerCount != null) && 
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.id.equals("63"));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.question.equals("Which state’s capital city would you like to visit?"));
	}
	
	@Test
	public void testPGNScienceQuestionData(){
		question = qotdAPI.getQuestionOfTheDayResponseData("130", "2018-01-01", "2018-12-31");
		
		assertTrue("Asserts that the 'answer_id' data is present in the json data and accurate",
				question.answers.get(0).id.equals("554") && 
				question.answers.get(1).id.equals("555") &&
				question.answers.get(2).id.equals("556") &&
				question.answers.get(3).id.equals("557"));
		assertTrue("Asserts that the 'answer_text' data is present in the json data and accurate",
				question.answers.get(0).answer.equals("Atmosphere") && 
				question.answers.get(1).answer.equals("Geosphere") &&
				question.answers.get(2).answer.equals("Biosphere") &&
				question.answers.get(3).answer.equals("Hydrosphere"));
		assertTrue("Asserts that the 'answer_count' data is present in the json data and accurate",
				(question.answers.get(0).answerCount != null) && 
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.id.equals("130"));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.question.equals("Which is your favorite sphere?"));
	}
	
	@Test
	public void testPGNAmericanIndianHistoryQuestionData(){
		question = qotdAPI.getQuestionOfTheDayResponseData("67", "2018-01-01", "2018-12-31");
		
		assertTrue("Asserts that the 'answer_id' data is present in the json data and accurate",
				question.answers.get(0).id.equals("300") && 
				question.answers.get(1).id.equals("301") &&
				question.answers.get(2).id.equals("302") &&
				question.answers.get(3).id.equals("303"));
		assertTrue("Asserts that the 'answer_text' data is present in the json data and accurate",
				question.answers.get(0).answer.equals("Wigwam") && 
				question.answers.get(1).answer.equals("Longhouse") &&
				question.answers.get(2).answer.equals("Hogan") &&
				question.answers.get(3).answer.equals("Tepee"));
		assertTrue("Asserts that the 'answer_count' data is present in the json data and accurate",
				(question.answers.get(0).answerCount != null) && 
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.id.equals("67"));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.question.equals("What type of traditional American Indian dwelling would you like to live in?"));
	}
	
	@Test
	public void testPGNSocialStudiesQuestionData(){
		question = qotdAPI.getQuestionOfTheDayResponseData("158", "2018-01-01", "2018-12-31");
		
		assertTrue("Asserts that the 'answer_id' data is present in the json data and accurate",
				question.answers.get(0).id.equals("647") && 
				question.answers.get(1).id.equals("648") &&
				question.answers.get(2).id.equals("649") &&
				question.answers.get(3).id.equals("650"));
		assertTrue("Asserts that the 'answer_text' data is present in the json data and accurate",
				question.answers.get(0).answer.equals("Tornadoes") && 
				question.answers.get(1).answer.equals("Earthquakes") &&
				question.answers.get(2).answer.equals("Hurricanes") &&
				question.answers.get(3).answer.equals("Floods"));
		assertTrue("Asserts that the 'answer_count' data is present in the json data and accurate",
				(question.answers.get(0).answerCount != null) && 
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.id.equals("158"));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.question.equals("Which natural disaster would be the scariest to encounter?"));
	}
	
	@Test
	public void testPGNBiographiesQuestionData(){
		question = qotdAPI.getQuestionOfTheDayResponseData("183", "2018-01-01", "2018-12-31");
		
		assertTrue("Asserts that the 'answer_id' data is present in the json data and accurate",
				question.answers.get(0).id.equals("739") && 
				question.answers.get(1).id.equals("740") &&
				question.answers.get(2).id.equals("741") &&
				question.answers.get(3).id.equals("742"));
		assertTrue("Asserts that the 'answer_text' data is present in the json data and accurate",
				question.answers.get(0).answer.equals("Telephone") && 
				question.answers.get(1).answer.equals("Cotton gin") &&
				question.answers.get(2).answer.equals("Printing press") &&
				question.answers.get(3).answer.equals("Lightning rod"));
		assertTrue("Asserts that the 'answer_count' data is present in the json data and accurate",
				(question.answers.get(0).answerCount != null) && 
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null) &&
				(question.answers.get(0).answerCount != null));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.id.equals("183"));
		assertTrue("Asserts that the 'question_id' data is present in the json data",
				question.question.equals("Which of these items would you have liked to invent?"));
	}
	
	@Test
	public void testBadDataStatus(){
		question = qotdAPI.getQuestionOfTheDayResponseData("183", "2018-01-01", "2018-13-31");
		
		assertTrue("Asserts that the status code returned by the api call using bad data is valid and a 500",
				qotdAPI.status.equals("HTTP/1.1 500 Internal Server Error"));
	}
	
	@Test
	public void testBadEndDateString(){
		question = qotdAPI.getQuestionOfTheDayResponseData("183", "2018-01-01", "2018-13-31");
		
		assertTrue("asserts that we get an error message when we supply a bad end date", 
				question.question.equals("\"message\":\"Elasticsearch error.\""));
	}
	
	@Test
	public void testWordEndDateString(){
		question = qotdAPI.getQuestionOfTheDayResponseData("183", "2018-01-01", "Bears");
		
		assertTrue("asserts that we get an error message when we supply a bad end date", 
				question.question.equals("\"message\":\"Elasticsearch error.\""));
	}
	
	@Test
	public void testBadStartDateString(){
		question = qotdAPI.getQuestionOfTheDayResponseData("183", "2018-31-01", "2018-1-31");
		  
		assertTrue("asserts that we get an error message when we supply a bad end date", 
				question.question.equals("\"message\":\"Elasticsearch error.\""));
	}
	
	@Test
	public void testWordStartDateString(){
		question = qotdAPI.getQuestionOfTheDayResponseData("183", "Bears", "2018-12-31");
		
		assertTrue("asserts that we get an error message when we supply a bad end date", 
				question.question.equals("\"message\":\"Elasticsearch error.\""));
	}
	
	@Test
	public void testZeroQuestionStatusCode(){
		question = qotdAPI.getQuestionOfTheDayResponseData("0", "2018-01-01", "2018-01-31");
		
		assertTrue("asserts that the status code returned by an api given a value of 0 for question number is valid and is 422", 
				qotdAPI.status.equals("HTTP/1.1 422 Unprocessable Entity"));
	}
	
	@Test
	public void testZeroQuestionString(){
		question = qotdAPI.getQuestionOfTheDayResponseData("0", "2018-01-01", "2018-01-31");
		
		assertTrue("asserts that we get a Record not Found message when we supply a question number of 0", 
				question.question.equals("\"message\":\"Record not Found\""));
	}
	
	@Test
	public void testLargeQuestionString(){
		question = qotdAPI.getQuestionOfTheDayResponseData("1000000000000", "2018-01-01", "2018-01-31");
		
		assertTrue("asserts that we get a Record not Found message when we supply a question number of 0", 
				question.question.equals("\"message\":\"Record not Found\""));
	}
	
	@Test
	public void testWordQuestionString(){
		question = qotdAPI.getQuestionOfTheDayResponseData("Bears", "2018-01-01", "2018-01-31");
		
		assertTrue("asserts that we get a Record not Found message when we supply a question number of 0", 
				question.question.equals("\"message\":\"Record not Found\""));
	}
	
	@Test
	public void testNoDataAPIStatusCode(){
		question = qotdAPI.getQuestionOfTheDayResponseData("", "2018-01-01", "2018-01-31");
		
		assertTrue("asserts that the status code returned by an api given a null value for question number is valid and is 404", 
				qotdAPI.status.equals("HTTP/1.1 404 Not Found"));
	}
	
	@Test
	public void testNullQuestionString(){
		question = qotdAPI.getQuestionOfTheDayResponseData("", "2018-01-01", "2018-01-31");
		
		assertTrue("asserts that we get a Record not Found message when we don't supply a question number", 
				question.question.equals("404 Not Found."));
	}
	
	@Test
	public void testNullBeginDataString(){
		question = qotdAPI.getQuestionOfTheDayResponseData("183", "", "2018-01-31");
		
		assertTrue("asserts that we get a Record not Found message when we don't supply a start date", 
				question.question.equals("404 Not Found."));
	}
	
	@Test
	public void testNullEndDataString(){
		question = qotdAPI.getQuestionOfTheDayResponseData("183", "2018-01-31", "");
		
		assertTrue("asserts that we get a Record not Found message when we don't supply an end date", 
				question.question.equals("404 Not Found."));
	}
}
