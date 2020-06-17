package Tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import APIControllers.ReadMoreContentAPIController;
import DataClasses.ReadMoreBookData;

public class ReadMoreContentAPITests {
	public ReadMoreContentAPIController readMoreAPI = new ReadMoreContentAPIController();
	String status;
	ReadMoreBookData book;
	
	@Before
	public void executeBefore(){
		book = readMoreAPI.getReadMoreBookData("9781496623690");
	}
	
	@Test
	public void testAPIStatusCodeExists(){
		assertTrue("Asserts that the status code given by the API shows a successful ping of the service", 
				readMoreAPI.statusCode.equals("HTTP/1.1 200 OK"));
	}
	
	@Test
	public void testAPIATOSKeyExists(){
		assertTrue("Asserts that the ATOS key is returned in the Read More Book Data api data",
				readMoreAPI.callData.has("atos"));
	}
	
	@Test
	public void testATOSDataExists(){
		assertTrue("Asserts that the API returns the correct atos data for the supplied isbn",
				book.atos.equals("2.2"));
	}
	
	@Test
	public void testAPIBrandKeyExists(){
		assertTrue("Asserts that the Brand key is returned in the Read More Book Data api data",
				readMoreAPI.callData.has("brand"));
	}
	
	@Test
	public void testBrandDataExists(){
		assertTrue("Asserts that the API returns the correct brand data for the supplied isbn",
				book.brand.equals("Pebble Plus"));
	}
	
	@Test
	public void testAPITitleKeyExists(){
		assertTrue("Asserts that the Title key is returned in the Read More Book Data api data",
				readMoreAPI.callData.has("title"));
	}
	
	@Test
	public void testTitleDataExists(){
		assertTrue("Asserts that the API returns the correct title data for the supplied isbn",
				book.title.equals("Squirrels"));
	}
	
	@Test
	public void testAPIAuthorKeyExists(){
		assertTrue("Asserts that the Author key is returned in the Read More Book Data api data",
				readMoreAPI.callData.has("author"));
	}
	
	@Test
	public void testAuthorDataExists(){
		assertTrue("Asserts that the API returns the correct author data for the supplied isbn",
				book.author.equals("Schuh, Mari"));
	}

	@Test
	public void testAPISeriesKeyExists(){
		assertTrue("Asserts that the Series key is returned in the Read More Book Data api data",
				readMoreAPI.callData.has("series"));
	}
	
	@Test
	public void testSeriesDataExists(){
		assertTrue("Asserts that the API returns the correct series data for the supplied isbn",
				book.series.equals("Backyard Animals"));
	}
	
	@Test
	public void testAPILanguageKeyExists(){
		assertTrue("Asserts that the Language key is returned in the Read More Book Data api data",
				readMoreAPI.callData.has("language"));
	}
	
	@Test
	public void testLanguageDataExists(){
		assertTrue("Asserts that the API returns the correct language data for the supplied isbn",
				book.language.equals("English"));
	}
	
	@Test
	public void testAPICopyrightKeyExists(){
		assertTrue("Asserts that the Copyright key is returned in the Read More Book Data api data",
				readMoreAPI.callData.has("copyright"));
	}
	
	@Test
	public void testCopyrightDataExists(){
		assertTrue("Asserts that the API returns the correct copyright data for the supplied isbn",
				book.copyright.equals("2015"));
	}
	
	@Test
	public void testAPIEpubISBNKeyExists(){
		assertTrue("Asserts that the epub_isbn key is returned in the Read More Book Data api data",
				readMoreAPI.callData.has("epub_isbn"));
	}
	
	@Test
	public void testEpubISBNDataExists(){
		assertTrue("Asserts that the API returns the correct epub_isbn data for the supplied isbn",
				book.epubISBN.equals("9781496623690"));
	}
	
	@Test
	public void testAPIPublisherKeyExists(){
		assertTrue("Asserts that the publisher key is returned in the Read More Book Data api data",
				readMoreAPI.callData.has("publisher"));
	}
	
	@Test
	public void testPublisherDataExists(){
		assertTrue("Asserts that the API returns the correct publisher data for the supplied isbn",
				book.publisher.equals("Capstone Press"));
	}
	
	@Test
	public void testAPIPrintISBNKeyExists(){
		assertTrue("Asserts that the print_isbn key is returned in the Read More Book Data api data",
				readMoreAPI.callData.has("print_isbn"));
	}
	
	@Test
	public void testPrintISBNDataExists(){
		assertTrue("Asserts that the API returns the correct print_isbn data for the supplied isbn",
				book.printISBN.equals("9781491420881"));
	}
	
	@Test
	public void testAPIDescriptionKeyExists(){
		assertTrue("Asserts that the description key is returned in the Read More Book Data api data",
				readMoreAPI.callData.has("description"));
	}
	
	@Test
	public void testDescriptionNDataExists(){
		assertTrue("Asserts that the API returns the correct description data for the supplied isbn",
				book.description.equals("The speedy squirrel scampers across a wooden fence. Facts on habitat, food, life cycle, and threats describe how these furry rodents thrive and survive right in our own backyards!"));
	}
	
	@Test
	public void testAPILexileLabelKeyExists(){
		assertTrue("Asserts that the lexile_label key is returned in the Read More Book Data api data",
				readMoreAPI.callData.has("lexile_label"));
	}
	
	@Test
	public void testLexileLabelDataExists(){
		assertTrue("Asserts that the API returns the correct lexile_label data for the supplied isbn",
				book.lexileLabel.equals("580L"));
	}
	
	@Test
	public void testAPIMaxGradeLevelKeyExists(){
		assertTrue("Asserts that the max_grade_level key is returned in the Read More Book Data api data",
				readMoreAPI.callData.has("max_grade_level"));
	}
	
	@Test
	public void testMaxGradeLevelDataExists(){
		assertTrue("Asserts that the API returns the correct max_grade_level data for the supplied isbn",
				book.maxGradeLevel.equals("4"));
	}
	
	@Test
	public void testAPIMinGradeLevelKeyExists(){
		assertTrue("Asserts that the print_isbn key is returned in the Read More Book Data api data",
				readMoreAPI.callData.has("min_grade_level"));
	}
	
	@Test
	public void testMinGradeLevelDataExists(){
		assertTrue("Asserts that the API returns the correct min_grade_level data for the supplied isbn",
				book.minGradeLevel.equals("1"));
	}
	
	@Test
	public void testAPIAudioRuntimeSecsKeyExists(){
		assertTrue("Asserts that the audio_runtime_secs key is returned in the Read More Book Data api data",
				readMoreAPI.callData.has("audio_runtime_secs"));
	}
	
	@Test
	public void testAudioRuntimeSecsDataExists(){
		assertTrue("Asserts that the API returns the correct audio_runtime_secs data for the supplied isbn",
				book.audioRuntimeSecs.equals("170"));
	}
	
	@Test
	public void testAPIGuidedReadingLevelKeyExists(){
		assertTrue("Asserts that the guided_reading_level key is returned in the Read More Book Data api data",
				readMoreAPI.callData.has("guided_reading_level"));
	}
	
	@Test
	public void testGuidedReadingLevelDataExists(){
		assertTrue("Asserts that the API returns the correct guided_reading_level data for the supplied isbn",
				book.guidedReadingLevel.equals("J"));
	}
	
	@Test
	public void testAPIAcceleratedReaderQuizIDKeyExists(){
		assertTrue("Asserts that the accelerated_reader_quiz_id key is returned in the Read More Book Data api data",
				readMoreAPI.callData.has("accelerated_reader_quiz_id"));
	}
	
	@Test
	public void testAcceleratedReaderQuizIDDataExists(){
		assertTrue("Asserts that the API returns the correct accelerated_reader_quiz_id data for the supplied isbn",
				book.ARReaderQuizID.equals("171175"));
	}
	
	@Test
	public void testNonNumberISBNEntry(){
		book = readMoreAPI.getReadMoreBookData("book");
		
		assertTrue("Asserts that the error message is given for an ISBN without numbers",
				readMoreAPI.entity.equals("{\"errorMessage\":\"Invalid format\"}"));
		assertTrue("Asserts that the status code given for an ISBN without numbers is 422",
				readMoreAPI.statusCode.equals("HTTP/1.1 422 "));
	}
	
	@Test
	public void testWrongNumberISBNEntry(){
		book = readMoreAPI.getReadMoreBookData("10");
		
		assertTrue("Asserts that the error message is given for an incorrect ISBN without numbers",
				readMoreAPI.entity.equals("[]"));
	}
}
