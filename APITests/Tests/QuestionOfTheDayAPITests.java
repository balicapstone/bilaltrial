package Tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import APIControllers.QuestionOfTheDayAPIController;
import DataClasses.QOTDQuestionsData;

public class QuestionOfTheDayAPITests {
	public QuestionOfTheDayAPIController qotdAPI = new QuestionOfTheDayAPIController();
	String status;
	QOTDQuestionsData question;
	
	@Before
	public void executeBefore(){
		question = qotdAPI.getQuestionOfTheDayData("7");
	}
	
	@Test
	public void testAPIStatusDataExists(){
		String test = qotdAPI.statusCode.toString();
		assertTrue("Asserts that the status code given by the API shows a successful ping of the service", 
				qotdAPI.statusCode.equals("HTTP/1.1 200 OK"));
	}
	
	@Test
	public void testAPIQuestionIDKeyExists(){
		assertTrue("Asserts that the body data returned by Question of the Day API has a question_id Key",
				qotdAPI.callData.has("question_id"));
	}
	
	@Test
	public void testQuestionIDDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct ID", 
				question.id.equals("7"));
	}
	
	@Test
	public void testAPIQuestionTextKeyExists(){
		assertTrue("Asserts that the body data returned by Question of the Day API has a question_id Key",
				qotdAPI.callData.has("question_text"));
	}
	
	@Test
	public void testQuestionTextDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct question text", 
				question.question.equals("Which is the scariest animal?"));
	}
	
	@Test
	public void testAPIAnswersKeyExists(){
		assertTrue("Asserts that the body data returned by Question of the Day API has a answers Key",
				qotdAPI.callData.has("answers"));
	}
	
	@Test
	public void testAPIAnswerImageKeyExists(){
		assertTrue("Asserts that the body data returned by Question of the Day API has a image key in the answers data",
				qotdAPI.callData.get("answers").getAsJsonArray().get(0).getAsJsonObject().has("image"));
	}

	@Test
	public void testAPIAnswerImageDataExists(){
		assertTrue("Asserts that the body data returned by Question of the Day API has an image data point in the answers data",
				question.answers.get(0).image.equals(""));
	}
	
	@Test
	public void testAPIAnswerIDKeyExists(){
		assertTrue("Asserts that the body data returned by Question of the Day API has a answer ID Key in the answers data",
				qotdAPI.callData.get("answers").getAsJsonArray().get(0).getAsJsonObject().has("answer_id"));
	}
	
	@Test
	public void testAnswerOneIDDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct id for the first answer", 
				question.answers.get(0).id.equals("42"));
	}
	
	@Test
	public void testAPIAnswerTextKeyExists(){
		assertTrue("Asserts that the body data returned by Question of the Day API has a answer text Key in the answers data",
				qotdAPI.callData.get("answers").getAsJsonArray().get(0).getAsJsonObject().has("answer_text"));
	}
	
	@Test
	public void testAnswerOneTextDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct text for the first answer", 
				question.answers.get(0).answer.equals("Grizzly Bear"));
	}

	@Test
	public void testAPIAnswerTWOImageDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct image for the second answer",
				question.answers.get(1).image.equals(""));
	}
	
	@Test
	public void testAnswerTwoIDDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct id for the second answer", 
				question.answers.get(1).id.equals("43"));
	}
	
	@Test
	public void testAnswerTwoTextDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct text for the second answer", 
				question.answers.get(1).answer.equals("Lion"));
	}
	
	@Test
	public void testAPIAnswerThreeImageDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct image for the third answer",
				question.answers.get(2).image.equals(""));
	}
	
	@Test
	public void testAnswerThreeIDDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct id for the third answer", 
				question.answers.get(2).id.equals("44"));
	}
	
	@Test
	public void testAnswerThreeTextDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct text for the third answer", 
				question.answers.get(2).answer.equals("Bat"));
	}

	@Test
	public void testAPIAnswerFourImageDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct image for the fourth answer",
				question.answers.get(3).image.equals(""));
	}
	
	@Test
	public void testAnswerFourIDDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct id for the fourth answer", 
				question.answers.get(3).id.equals("45"));
	}
	
	@Test
	public void testAnswerFourTextDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct text for the fourth answer", 
				question.answers.get(3).answer.equals("Shark"));
	}

	@Test
	public void testAPIAnswerFiveImageDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct image for the fifth answer",
				question.answers.get(4).image.equals(""));
	}
	
	@Test
	public void testAnswerFiveIDDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct id for the fifth answer", 
				question.answers.get(4).id.equals("46"));
	}
	
	@Test
	public void testAnswerFiveTextDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct text for the fifth answer", 
				question.answers.get(4).answer.equals("Snake"));
	}

	@Test
	public void testAPIAnswerSixImageDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct image for the sixth answer",
				question.answers.get(5).image.equals(""));
	}
	
	@Test
	public void testAnswerSixIDDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct id for the sixth answer", 
				question.answers.get(5).id.equals("47"));
	}
	
	@Test
	public void testAnswerSixTextDataExists(){
		assertTrue("Asserts that the data returned by the API has the correct text for the sixth answer", 
				question.answers.get(5).answer.equals("Spider"));
	}
	
	@Test
	public void testScienceQuestion(){
		question = qotdAPI.getQuestionOfTheDayData("4");
		status = qotdAPI.statusCode;
		
		assertTrue("Asserts that the status code given by the API shows a successful ping of the service", 
				status.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the data returned by the API has the correct ID", 
				question.id.equals("4"));
		assertTrue("Asserts that the data returned by the API has the correct question text", 
				question.question.equals("What is the biggest tree?"));
		assertTrue("Asserts that the data returned by the API has the correct image for the first answer",
				question.answers.get(0).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the first answer", 
				question.answers.get(0).id.equals("20"));
		assertTrue("Asserts that the data returned by the API has the correct text for the first answer", 
				question.answers.get(0).answer.equals("Oak"));
		assertTrue("Asserts that the data returned by the API has the correct image for the second answer",
				question.answers.get(1).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the second answer", 
				question.answers.get(1).id.equals("21"));
		assertTrue("Asserts that the data returned by the API has the correct text for the second answer", 
				question.answers.get(1).answer.equals("Ash"));
		assertTrue("Asserts that the data returned by the API has the correct image for the third answer",
				question.answers.get(2).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the third answer", 
				question.answers.get(2).id.equals("22"));
		assertTrue("Asserts that the data returned by the API has the correct text for the third answer", 
				question.answers.get(2).answer.equals("Poplar"));
		assertTrue("Asserts that the data returned by the API has the correct image for the fourth answer",
				question.answers.get(3).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the fourth answer", 
				question.answers.get(3).id.equals("23"));
		assertTrue("Asserts that the data returned by the API has the correct text for the fourth answer", 
				question.answers.get(3).answer.equals("Evergreen"));
		assertTrue("Asserts that the data returned by the API has the correct image for the fifth answer",
				question.answers.get(4).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the fifth answer", 
				question.answers.get(4).id.equals("24"));
		assertTrue("Asserts that the data returned by the API has the correct text for the fifth answer", 
				question.answers.get(4).answer.equals("Maple"));
	}
	
	@Test
	public void testBiographiesQuestion(){	
		question = qotdAPI.getQuestionOfTheDayData("26");
		status = qotdAPI.statusCode;
		
		assertTrue("Asserts that the status code given by the API shows a successful ping of the service", 
				status.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the data returned by the API has the correct ID", 
				question.id.equals("26"));
		assertTrue("Asserts that the data returned by the API has the correct question text", 
				question.question.equals("Who is your favorite president?"));
		assertTrue("Asserts that the data returned by the API has the correct image for the first answer",
				question.answers.get(0).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the first answer", 
				question.answers.get(0).id.equals("144"));
		assertTrue("Asserts that the data returned by the API has the correct text for the first answer", 
				question.answers.get(0).answer.equals("George Washington"));
		assertTrue("Asserts that the data returned by the API has the correct image for the second answer",
				question.answers.get(1).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the second answer", 
				question.answers.get(1).id.equals("145"));
		assertTrue("Asserts that the data returned by the API has the correct text for the second answer", 
				question.answers.get(1).answer.equals("Abraham Lincoln"));
		assertTrue("Asserts that the data returned by the API has the correct image for the third answer",
				question.answers.get(2).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the third answer", 
				question.answers.get(2).id.equals("146"));
		assertTrue("Asserts that the data returned by the API has the correct text for the third answer", 
				question.answers.get(2).answer.equals("John F. Kennedy"));
		assertTrue("Asserts that the data returned by the API has the correct image for the fourth answer",
				question.answers.get(3).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the fourth answer", 
				question.answers.get(3).id.equals("147"));
		assertTrue("Asserts that the data returned by the API has the correct text for the fourth answer", 
				question.answers.get(3).answer.equals("Barack Obama"));
	}
	
	@Test
	public void testSocialStudiesQuestion(){
		question = qotdAPI.getQuestionOfTheDayData("52");
		status = qotdAPI.statusCode;
		
		assertTrue("Asserts that the status code given by the API shows a successful ping of the service", 
				status.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the data returned by the API has the correct ID", 
				question.id.equals("52"));
		assertTrue("Asserts that the data returned by the API has the correct question text", 
				question.question.equals("Which holiday do you like best?"));
		assertTrue("Asserts that the data returned by the API has the correct image for the first answer",
				question.answers.get(0).image.equals("game_frogs_1.jpg"));
		assertTrue("Asserts that the data returned by the API has the correct id for the first answer", 
				question.answers.get(0).id.equals("248"));
		assertTrue("Asserts that the data returned by the API has the correct text for the first answer", 
				question.answers.get(0).answer.equals("Halloween"));
		assertTrue("Asserts that the data returned by the API has the correct image for the second answer",
				question.answers.get(1).image.equals("pgoanimals_frogs.jpg"));
		assertTrue("Asserts that the data returned by the API has the correct id for the second answer", 
				question.answers.get(1).id.equals("249"));
		assertTrue("Asserts that the data returned by the API has the correct text for the second answer", 
				question.answers.get(1).answer.equals("Thanksgiving"));
		assertTrue("Asserts that the data returned by the API has the correct image for the third answer",
				question.answers.get(2).image.equals("game_frogs_3.jpg"));
		assertTrue("Asserts that the data returned by the API has the correct id for the third answer", 
				question.answers.get(2).id.equals("250"));
		assertTrue("Asserts that the data returned by the API has the correct text for the third answer", 
				question.answers.get(2).answer.equals("Independence Day"));
		assertTrue("Asserts that the data returned by the API has the correct image for the fourth answer",
				question.answers.get(3).image.equals("game_frogs_5.jpg"));
		assertTrue("Asserts that the data returned by the API has the correct id for the fourth answer", 
				question.answers.get(3).id.equals("251"));
		assertTrue("Asserts that the data returned by the API has the correct text for the fourth answer", 
				question.answers.get(3).answer.equals("Valentine's Day"));
	}
	
	@Test
	public void testDinosaursQuestion(){
		question = qotdAPI.getQuestionOfTheDayData("74");
		status = qotdAPI.statusCode;
		
		assertTrue("Asserts that the status code given by the API shows a successful ping of the service", 
				status.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the data returned by the API has the correct ID", 
				question.id.equals("74"));
		assertTrue("Asserts that the data returned by the API has the correct question text", 
				question.question.equals("What is your favorite long-necked dinosaur?"));
		assertTrue("Asserts that the data returned by the API has the correct image for the first answer",
				question.answers.get(0).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the first answer", 
				question.answers.get(0).id.equals("327"));
		assertTrue("Asserts that the data returned by the API has the correct text for the first answer", 
				question.answers.get(0).answer.equals("Brachiosaurus"));
		assertTrue("Asserts that the data returned by the API has the correct image for the second answer",
				question.answers.get(1).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the second answer", 
				question.answers.get(1).id.equals("328"));
		assertTrue("Asserts that the data returned by the API has the correct text for the second answer", 
				question.answers.get(1).answer.equals("Diplodocus"));
		assertTrue("Asserts that the data returned by the API has the correct image for the third answer",
				question.answers.get(2).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the third answer", 
				question.answers.get(2).id.equals("329"));
		assertTrue("Asserts that the data returned by the API has the correct text for the third answer", 
				question.answers.get(2).answer.equals("Sauroposeidon"));
		assertTrue("Asserts that the data returned by the API has the correct image for the fourth answer",
				question.answers.get(3).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the fourth answer", 
				question.answers.get(3).id.equals("330"));
		assertTrue("Asserts that the data returned by the API has the correct text for the fourth answer", 
				question.answers.get(3).answer.equals("Apatosaurus"));
	}
	
	@Test
	public void testAnimalesQuestion(){
		question = qotdAPI.getQuestionOfTheDayData("118");
		status = qotdAPI.statusCode;
		
		assertTrue("Asserts that the status code given by the API shows a successful ping of the service", 
				status.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the data returned by the API has the correct ID", 
				question.id.equals("118"));
		assertTrue("Asserts that the data returned by the API has the correct question text", 
				question.question.equals("¿Cuál es tu marsupial favorito?"));
		assertTrue("Asserts that the data returned by the API has the correct image for the first answer",
				question.answers.get(0).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the first answer", 
				question.answers.get(0).id.equals("507"));
		assertTrue("Asserts that the data returned by the API has the correct text for the first answer", 
				question.answers.get(0).answer.equals("Canguro"));
		assertTrue("Asserts that the data returned by the API has the correct image for the second answer",
				question.answers.get(1).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the second answer", 
				question.answers.get(1).id.equals("508"));
		assertTrue("Asserts that the data returned by the API has the correct text for the second answer", 
				question.answers.get(1).answer.equals("Koala"));
		assertTrue("Asserts that the data returned by the API has the correct image for the third answer",
				question.answers.get(2).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the third answer", 
				question.answers.get(2).id.equals("509"));
		assertTrue("Asserts that the data returned by the API has the correct text for the third answer", 
				question.answers.get(2).answer.equals("Zarigüeya"));
		assertTrue("Asserts that the data returned by the API has the correct image for the fourth answer",
				question.answers.get(3).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the fourth answer", 
				question.answers.get(3).id.equals("510"));
		assertTrue("Asserts that the data returned by the API has the correct text for the fourth answer", 
				question.answers.get(3).answer.equals("Petauro del Azúcar"));
	}
	
	@Test
	public void testCienciaQuestion(){
		question = qotdAPI.getQuestionOfTheDayData("241");
		status = qotdAPI.statusCode;
		
		assertTrue("Asserts that the status code given by the API shows a successful ping of the service", 
				status.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the data returned by the API has the correct ID", 
				question.id.equals("241"));
		assertTrue("Asserts that the data returned by the API has the correct question text", 
				question.question.equals("¿Qué empleo es el más interesante?"));
		assertTrue("Asserts that the data returned by the API has the correct image for the first answer",
				question.answers.get(0).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the first answer", 
				question.answers.get(0).id.equals("961"));
		assertTrue("Asserts that the data returned by the API has the correct text for the first answer", 
				question.answers.get(0).answer.equals("Geólogo"));
		assertTrue("Asserts that the data returned by the API has the correct image for the second answer",
				question.answers.get(1).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the second answer", 
				question.answers.get(1).id.equals("962"));
		assertTrue("Asserts that the data returned by the API has the correct text for the second answer", 
				question.answers.get(1).answer.equals("Paleontólogo"));
		assertTrue("Asserts that the data returned by the API has the correct image for the third answer",
				question.answers.get(2).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the third answer", 
				question.answers.get(2).id.equals("963"));
		assertTrue("Asserts that the data returned by the API has the correct text for the third answer", 
				question.answers.get(2).answer.equals("Meteorólogo"));
		assertTrue("Asserts that the data returned by the API has the correct image for the fourth answer",
				question.answers.get(3).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the fourth answer", 
				question.answers.get(3).id.equals("983"));
		assertTrue("Asserts that the data returned by the API has the correct text for the fourth answer", 
				question.answers.get(3).answer.equals("Astronauta"));
	}
	
	@Test
	public void testBiografiasQuestion(){
		question = qotdAPI.getQuestionOfTheDayData("213");
		status = qotdAPI.statusCode;
		
		assertTrue("Asserts that the status code given by the API shows a successful ping of the service", 
				status.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the data returned by the API has the correct ID", 
				question.id.equals("213"));
		assertTrue("Asserts that the data returned by the API has the correct question text", 
				question.question.equals("¿A qué persona admiras más?"));
		assertTrue("Asserts that the data returned by the API has the correct image for the first answer",
				question.answers.get(0).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the first answer", 
				question.answers.get(0).id.equals("856"));
		assertTrue("Asserts that the data returned by the API has the correct text for the first answer", 
				question.answers.get(0).answer.equals("Madre Teresa"));
		assertTrue("Asserts that the data returned by the API has the correct image for the second answer",
				question.answers.get(1).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the second answer", 
				question.answers.get(1).id.equals("857"));
		assertTrue("Asserts that the data returned by the API has the correct text for the second answer", 
				question.answers.get(1).answer.equals("Babe Ruth"));
		assertTrue("Asserts that the data returned by the API has the correct image for the third answer",
				question.answers.get(2).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the third answer", 
				question.answers.get(2).id.equals("858"));
		assertTrue("Asserts that the data returned by the API has the correct text for the third answer", 
				question.answers.get(2).answer.equals("Neil Armstrong"));
		assertTrue("Asserts that the data returned by the API has the correct image for the fourth answer",
				question.answers.get(3).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the fourth answer", 
				question.answers.get(3).id.equals("867"));
		assertTrue("Asserts that the data returned by the API has the correct text for the fourth answer", 
				question.answers.get(3).answer.equals("Albert Einstein"));
		assertTrue("Asserts that the data returned by the API has the correct image for the fifth answer",
				question.answers.get(4).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the fifth answer", 
				question.answers.get(4).id.equals("971"));
		assertTrue("Asserts that the data returned by the API has the correct text for the fifth answer", 
				question.answers.get(4).answer.equals("César Chávez"));
	}
	
	@Test
	public void testEstudiosSocialesQuestion(){
		question = qotdAPI.getQuestionOfTheDayData("226");
		status = qotdAPI.statusCode;
		
		assertTrue("Asserts that the status code given by the API shows a successful ping of the service", 
				status.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the data returned by the API has the correct ID", 
				question.id.equals("226"));
		assertTrue("Asserts that the data returned by the API has the correct question text", 
				question.question.equals("¿Qué día festivo te gusta más?"));
		assertTrue("Asserts that the data returned by the API has the correct image for the first answer",
				question.answers.get(0).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the first answer", 
				question.answers.get(0).id.equals("909"));
		assertTrue("Asserts that the data returned by the API has the correct text for the first answer", 
				question.answers.get(0).answer.equals("Noche de brujas"));
		assertTrue("Asserts that the data returned by the API has the correct image for the second answer",
				question.answers.get(1).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the second answer", 
				question.answers.get(1).id.equals("910"));
		assertTrue("Asserts that the data returned by the API has the correct text for the second answer", 
				question.answers.get(1).answer.equals("Acción de Gracias"));
		assertTrue("Asserts that the data returned by the API has the correct image for the third answer",
				question.answers.get(2).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the third answer", 
				question.answers.get(2).id.equals("911"));
		assertTrue("Asserts that the data returned by the API has the correct text for the third answer", 
				question.answers.get(2).answer.equals("Día de la Independencia"));
		assertTrue("Asserts that the data returned by the API has the correct image for the fourth answer",
				question.answers.get(3).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the fourth answer", 
				question.answers.get(3).id.equals("979"));
		assertTrue("Asserts that the data returned by the API has the correct text for the fourth answer", 
				question.answers.get(3).answer.equals("Día de los Enamorados"));
	}
	
	@Test
	public void testPGNStatesQuestion(){
		question = qotdAPI.getQuestionOfTheDayData("64");
		status = qotdAPI.statusCode;
		
		assertTrue("Asserts that the status code given by the API shows a successful ping of the service", 
				status.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the data returned by the API has the correct ID", 
				question.id.equals("64"));
		assertTrue("Asserts that the data returned by the API has the correct question text", 
				question.question.equals("Choose your favorite state bird."));
		assertTrue("Asserts that the data returned by the API has the correct image for the first answer",
				question.answers.get(0).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the first answer", 
				question.answers.get(0).id.equals("288"));
		assertTrue("Asserts that the data returned by the API has the correct text for the first answer", 
				question.answers.get(0).answer.equals("Baltimore oriole (Maryland)"));
		assertTrue("Asserts that the data returned by the API has the correct image for the second answer",
				question.answers.get(1).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the second answer", 
				question.answers.get(1).id.equals("289"));
		assertTrue("Asserts that the data returned by the API has the correct text for the second answer", 
				question.answers.get(1).answer.equals("Cactus wren (Arizona)"));
		assertTrue("Asserts that the data returned by the API has the correct image for the third answer",
				question.answers.get(2).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the third answer", 
				question.answers.get(2).id.equals("290"));
		assertTrue("Asserts that the data returned by the API has the correct text for the third answer", 
				question.answers.get(2).answer.equals("Common loon (Minnesota)"));
		assertTrue("Asserts that the data returned by the API has the correct image for the fourth answer",
				question.answers.get(3).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the fourth answer", 
				question.answers.get(3).id.equals("291"));
		assertTrue("Asserts that the data returned by the API has the correct text for the fourth answer", 
				question.answers.get(3).answer.equals("Chickadee (Maine)"));
	}
	
	@Test
	public void testPGNScienceQuestion(){
		question = qotdAPI.getQuestionOfTheDayData("133");
		status = qotdAPI.statusCode;
		
		assertTrue("Asserts that the status code given by the API shows a successful ping of the service", 
				status.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the data returned by the API has the correct ID", 
				question.id.equals("133"));
		assertTrue("Asserts that the data returned by the API has the correct question text", 
				question.question.equals("Which of the five senses is your favorite?"));
		assertTrue("Asserts that the data returned by the API has the correct image for the first answer",
				question.answers.get(0).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the first answer", 
				question.answers.get(0).id.equals("563"));
		assertTrue("Asserts that the data returned by the API has the correct text for the first answer", 
				question.answers.get(0).answer.equals("Seeing"));
		assertTrue("Asserts that the data returned by the API has the correct image for the second answer",
				question.answers.get(1).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the second answer", 
				question.answers.get(1).id.equals("564"));
		assertTrue("Asserts that the data returned by the API has the correct text for the second answer", 
				question.answers.get(1).answer.equals("Hearing"));
		assertTrue("Asserts that the data returned by the API has the correct image for the third answer",
				question.answers.get(2).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the third answer", 
				question.answers.get(2).id.equals("565"));
		assertTrue("Asserts that the data returned by the API has the correct text for the third answer", 
				question.answers.get(2).answer.equals("Smelling"));
		assertTrue("Asserts that the data returned by the API has the correct image for the fourth answer",
				question.answers.get(3).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the fourth answer", 
				question.answers.get(3).id.equals("566"));
		assertTrue("Asserts that the data returned by the API has the correct text for the fourth answer", 
				question.answers.get(3).answer.equals("Tasting"));
		assertTrue("Asserts that the data returned by the API has the correct image for the fifth answer",
				question.answers.get(4).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the fifth answer", 
				question.answers.get(4).id.equals("567"));
		assertTrue("Asserts that the data returned by the API has the correct text for the fifth answer", 
				question.answers.get(4).answer.equals("Touching"));
	}
	
	@Test
	public void testPGNAmericanIndianHistoryQuestion(){
		question = qotdAPI.getQuestionOfTheDayData("68");
		status = qotdAPI.statusCode;
		
		assertTrue("Asserts that the status code given by the API shows a successful ping of the service", 
				status.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the data returned by the API has the correct ID", 
				question.id.equals("68"));
		assertTrue("Asserts that the data returned by the API has the correct question text", 
				question.question.equals("Which American Indian tribe would you like to learn more about?"));
		assertTrue("Asserts that the data returned by the API has the correct image for the first answer",
				question.answers.get(0).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the first answer", 
				question.answers.get(0).id.equals("304"));
		assertTrue("Asserts that the data returned by the API has the correct text for the first answer", 
				question.answers.get(0).answer.equals("The Inuit"));
		assertTrue("Asserts that the data returned by the API has the correct image for the second answer",
				question.answers.get(1).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the second answer", 
				question.answers.get(1).id.equals("305"));
		assertTrue("Asserts that the data returned by the API has the correct text for the second answer", 
				question.answers.get(1).answer.equals("The Seminole"));
		assertTrue("Asserts that the data returned by the API has the correct image for the third answer",
				question.answers.get(2).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the third answer", 
				question.answers.get(2).id.equals("306"));
		assertTrue("Asserts that the data returned by the API has the correct text for the third answer", 
				question.answers.get(2).answer.equals("The Iroquois"));
		assertTrue("Asserts that the data returned by the API has the correct image for the fourth answer",
				question.answers.get(3).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the fourth answer", 
				question.answers.get(3).id.equals("307"));
		assertTrue("Asserts that the data returned by the API has the correct text for the fourth answer", 
				question.answers.get(3).answer.equals("The Lakota"));
	}
	
	@Test
	public void testPGNSocialStudiesQuestion(){
		question = qotdAPI.getQuestionOfTheDayData("180");
		status = qotdAPI.statusCode;
		
		assertTrue("Asserts that the status code given by the API shows a successful ping of the service", 
				status.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the data returned by the API has the correct ID", 
				question.id.equals("180"));
		assertTrue("Asserts that the data returned by the API has the correct question text", 
				question.question.equals("What is your favorite way to travel?"));
		assertTrue("Asserts that the data returned by the API has the correct image for the first answer",
				question.answers.get(0).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the first answer", 
				question.answers.get(0).id.equals("728"));
		assertTrue("Asserts that the data returned by the API has the correct text for the first answer", 
				question.answers.get(0).answer.equals("By air"));
		assertTrue("Asserts that the data returned by the API has the correct image for the second answer",
				question.answers.get(1).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the second answer", 
				question.answers.get(1).id.equals("729"));
		assertTrue("Asserts that the data returned by the API has the correct text for the second answer", 
				question.answers.get(1).answer.equals("By land"));
		assertTrue("Asserts that the data returned by the API has the correct image for the third answer",
				question.answers.get(2).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the third answer", 
				question.answers.get(2).id.equals("730"));
		assertTrue("Asserts that the data returned by the API has the correct text for the third answer", 
				question.answers.get(2).answer.equals("By water"));
	}
	
	@Test
	public void testPGNBiographisQuestion(){
		question = qotdAPI.getQuestionOfTheDayData("195");
		status = qotdAPI.statusCode;
		
		assertTrue("Asserts that the status code given by the API shows a successful ping of the service", 
				status.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the data returned by the API has the correct ID", 
				question.id.equals("195"));
		assertTrue("Asserts that the data returned by the API has the correct question text", 
				question.question.equals("Which job would you most like to have?"));
		assertTrue("Asserts that the data returned by the API has the correct image for the first answer",
				question.answers.get(0).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the first answer", 
				question.answers.get(0).id.equals("783"));
		assertTrue("Asserts that the data returned by the API has the correct text for the first answer", 
				question.answers.get(0).answer.equals("Painter"));
		assertTrue("Asserts that the data returned by the API has the correct image for the second answer",
				question.answers.get(1).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the second answer", 
				question.answers.get(1).id.equals("784"));
		assertTrue("Asserts that the data returned by the API has the correct text for the second answer", 
				question.answers.get(1).answer.equals("Sculptor"));
		assertTrue("Asserts that the data returned by the API has the correct image for the third answer",
				question.answers.get(2).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the third answer", 
				question.answers.get(2).id.equals("785"));
		assertTrue("Asserts that the data returned by the API has the correct text for the third answer", 
				question.answers.get(2).answer.equals("Author"));
		assertTrue("Asserts that the data returned by the API has the correct image for the fourth answer",
				question.answers.get(3).image.equals(""));
		assertTrue("Asserts that the data returned by the API has the correct id for the fourth answer", 
				question.answers.get(3).id.equals("786"));
		assertTrue("Asserts that the data returned by the API has the correct text for the fourth answer", 
				question.answers.get(3).answer.equals("Architect"));
	}
	
	@Test
	public void testBadQuestionID(){
		question = qotdAPI.getQuestionOfTheDayData("123456789");
		
		assertTrue("Asserts that a bad question id gives a valid status code", qotdAPI.statusCode.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that a bad question gets an empty array", qotdAPI.entity.equals("{}"));
	}

	@Test
	public void testDeactivatedQuestionID(){
		question = qotdAPI.getQuestionOfTheDayData("25");
		
		assertTrue("Asserts that a bad question id gives a valid status code", qotdAPI.statusCode.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that a bad question gets an empty array", qotdAPI.entity.equals("{}"));
	}
	
	@Test
	public void testUnpublishedQuestionID(){
		question = qotdAPI.getQuestionOfTheDayData("57");
		
		assertTrue("Asserts that a bad question id gives a valid status code", qotdAPI.statusCode.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that a bad question gets an empty array", qotdAPI.entity.equals("{}"));
	}
	
}
