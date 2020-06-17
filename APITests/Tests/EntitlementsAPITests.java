package Tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;

import APIControllers.AuthenticationAPIController;
import APIControllers.EntitlementsAPIController;

public class EntitlementsAPITests {
	AuthenticationAPIController authAPI;
	EntitlementsAPIController entitlementsAPI;
	String pgoToken;
	String pgnToken;
	String ciToken;
	String pgoResponse;
	String ciResponse;
	
	@Before
	public void executeBefore(){
		authAPI = new AuthenticationAPIController("authqa");
		entitlementsAPI = new EntitlementsAPIController();
		
		try {
			pgoToken =	authAPI.getTokenForUsername("garage", "capdig", "2", "username");
					//authAPI.getTokenForUsername("nolinkpgo", "test", "2", "username");
		} catch (ParseException e) {
			pgoToken = "***Error getting token***";
			System.out.println(pgoToken + "\n");
			e.printStackTrace();
		} catch (IOException e) {
			pgoToken = "***Error getting token***";
			System.out.println(pgoToken + "\n");
			e.printStackTrace();
		}
		
		try {
			pgnToken =	authAPI.getTokenForUsername("garage", "capdig", "3", "username");
			//authAPI.getTokenForUsername("nolinkpgn", "test", "3", "username");
		} catch (ParseException e) {
			pgnToken = "***Error getting token***";
			System.out.println(pgoToken + "\n");
			e.printStackTrace();
		} catch (IOException e) {
			pgnToken = "***Error getting token***";
			System.out.println(pgoToken + "\n");
			e.printStackTrace();
		}
		
		try {
			ciToken =	authAPI.getTokenForUsername("cistudent", "books", "1", "username");
		} catch (ParseException e) {
			ciToken = "***Error getting token***";
			System.out.println(ciToken + "\n");
			e.printStackTrace();
		} catch (IOException e) {
			ciToken = "***Error getting token***";
			System.out.println(ciToken + "\n");
			e.printStackTrace();
		}
		
		pgoResponse = entitlementsAPI.getEntitlementsForApp(pgoToken);
	}
	
	@Test
	public void testStatusCode(){
		assertTrue("Asserts that the status code returned from the Entitlements API is valid and a 200",
				entitlementsAPI.statusCode.equals("HTTP/1.1 200 OK"));
	}
	
	@Test
	public void testOwnedContentKey(){
		assertTrue("Asserts that the data returned from the Entitlements API has an owned content key",
				entitlementsAPI.callData.has("ownedContent"));
	}
	
	@Test
	public void testBannedArticlesKey(){
		assertTrue("Asserts that the data returned from the Entitlements API has an banned articles key",
				entitlementsAPI.callData.has("bannedArticles"));
	}
	
	@Test
	public void testBannedCategoriesKey(){
		assertTrue("Asserts that the data returned from the Entitlements API has a banned categories key",
				entitlementsAPI.callData.has("bannedCategories"));
	}
	
	@Test
	public void testNullToken(){
		pgoResponse = entitlementsAPI.getEntitlementsForApp("");
		
		assertTrue("Asserts that the data returned from the Entitlements API for a null app number is an error",
				pgoResponse.equals("{\"error\":\"Invalid Token\"}"));
	}
	
	@Test
	public void testBasicPGOBannedContentNoTexasNoUSOnly() throws ClientProtocolException, IOException{
		String pgnStudent  = authAPI.getTokenForUsername("pgoonly", "test", "2", "username");
		entitlementsAPI.getEntitlementsForApp(pgnStudent);

		
		assertTrue("Asserts that the data returned from the Entitlements API for a PGN student token for the PGO platform has the correct Banned Articles data",
				entitlementsAPI.entitlements.bannedArticles.size() == 0);	
		assertTrue("Asserts that the data returned from the Entitlements API for a PGN student token for the PGO platform has the correct Banned Articles data",
				entitlementsAPI.entitlements.bannedCategories.size() == 0);	
	}
	

	@Test
	public void testBasicPGNBannedContentNoTexasNoUSOnly() throws ClientProtocolException, IOException{
		String pgnStudent  = authAPI.getTokenForUsername("pgnstudent", "test", "3", "username");
		entitlementsAPI.getEntitlementsForApp(pgnStudent);

		
		assertTrue("Asserts that the data returned from the Entitlements API for a PGN student token for the PGO platform has the correct Banned Articles data",
				entitlementsAPI.entitlements.bannedArticles.equals(new ArrayList<String>(Arrays.asList("9885", "9886", "9887", "9888", "9889", "9890", "9891"))));	
		assertTrue("Asserts that the data returned from the Entitlements API for a PGN student token for the PGO platform has the correct Banned Articles data",
				entitlementsAPI.entitlements.bannedCategories.equals(new ArrayList<String>(Arrays.asList("9981"))));	
	}
	
	@Test
	public void testPGOUSOnlyContent() throws ClientProtocolException, IOException{
		String pgoStudent  = authAPI.getTokenForUsername("nolinkpgo", "test", "2", "username");
		entitlementsAPI.getEntitlementsForApp(pgoStudent);

		
		assertTrue("Asserts that the data returned from the Entitlements API for a PGN student token for the PGO platform has the correct Banned Articles data",
				entitlementsAPI.entitlements.bannedArticles.equals(new ArrayList<String>(Arrays.asList("5103", "5104", "5115", "5116", "5117", "5118", "5119",
				        "5120", "5121", "5122", "5123", "5124", "5125", "5126", "5130", "5131", "5135", "5136", "5138", "5139", "5140", "5143", "5145", "5159",
				        "5205", "5212", "5213", "5214", "5215", "5216", "5217", "5218", "5219", "5220", "5221", "5222", "5223"))));	
		assertTrue("Asserts that the data returned from the Entitlements API for a PGN student token for the PGO platform has the correct Banned Articles data",
				entitlementsAPI.entitlements.bannedCategories.equals(new ArrayList<String>(Arrays.asList("5002", "5009"))));
	}
	
	@Test
	public void testPGNUSOnlyContent() throws ClientProtocolException, IOException{
		String pgnStudent  = authAPI.getTokenForUsername("nolinkpgn", "test", "3", "username");
		entitlementsAPI.getEntitlementsForApp(pgnStudent);

		
		assertTrue("Asserts that the data returned from the Entitlements API for a PGN student token for the PGO platform has the correct Banned Articles data",
				entitlementsAPI.entitlements.bannedArticles.equals(new ArrayList<String>(Arrays.asList("8757", "8758", "8759", "8760", "8761", "8762", "8763",
						"8764", "8768", "8779", "8780", "8781", "8782", "8783", "8784", "8785", "8786", "8787", "8788", "8789", "8790", "8791", "8792", "8793",
						"8794", "8795", "8796", "8797", "8798", "8799", "8800", "8801", "8802", "8803", "8804", "8805", "8806", "8807", "8808", "8809", "8810",
						"8811", "8812", "8813", "8814", "8815", "8816", "8817", "8818", "8819", "8820", "8821", "8822", "8823", "8824", "8825", "8826", "8827",
						"8828", "8829", "8830", "8831", "8832", "8833", "8834", "8835", "8836", "8837", "9885", "9886", "9887", "9888", "9889", "9890", "9891", "8555"))));	
		assertTrue("Asserts that the data returned from the Entitlements API for a PGN student token for the PGO platform has the correct Banned Articles data",
				entitlementsAPI.entitlements.bannedCategories.equals(new ArrayList<String>(Arrays.asList("8849", "8852", "8853", "8857", "8861", "8862", "9981"))));
	}
	
	@Test
	public void testPGNDisplayTexasBannedContent() throws ClientProtocolException, IOException{
		String pgnStudent  = authAPI.getTokenForUsername("AccessStudent", "6ivJjj4X", "3", "username");
		entitlementsAPI.getEntitlementsForApp(pgnStudent);

		
		assertTrue("Asserts that the data returned from the Entitlements API for a PGN student token for the PGO platform has the correct Banned Articles data",
				entitlementsAPI.entitlements.bannedArticles.equals(new ArrayList<String>(Arrays.asList("8555"))));	
		assertTrue("Asserts that the data returned from the Entitlements API for a PGN student token for the PGO platform has the correct Banned Articles data",
				entitlementsAPI.entitlements.bannedCategories.size() == 0);
	}
	
	@Test
	public void testPGODisplayTexasBannedContent() throws ClientProtocolException, IOException{
		String pgnStudent  = authAPI.getTokenForUsername("AccessStudent", "6ivJjj4X", "2", "username");
		entitlementsAPI.getEntitlementsForApp(pgnStudent);

		
		assertTrue("Asserts that the data returned from the Entitlements API for a PGN student token for the PGO platform has the correct Banned Articles data",
				entitlementsAPI.entitlements.bannedArticles.size() == 0);	
		assertTrue("Asserts that the data returned from the Entitlements API for a PGN student token for the PGO platform has the correct Banned Articles data",
				entitlementsAPI.entitlements.bannedCategories.size() == 0);
	}
	
	@Test
	public void testPGOUSOnlyAndDisplayTexasBannedContentListCorrectContent() throws ClientProtocolException, IOException{
		String pgnStudent  = authAPI.getTokenForUsername("garage", "capdig", "2", "username");
		entitlementsAPI.getEntitlementsForApp(pgnStudent);

		
		assertTrue("Asserts that the data returned from the Entitlements API for a PGN student token for the PGO platform has the correct Banned Articles data",
				entitlementsAPI.entitlements.bannedArticles.equals(new ArrayList<String>(Arrays.asList("5103", "5104", "5115", "5116", "5117", "5118", "5119",
						"5120", "5121", "5122", "5123", "5124", "5125", "5126", "5130", "5131", "5135", "5136", "5138", "5139", "5140", "5143",
						"5145", "5159", "5205", "5212", "5213", "5214", "5215", "5216", "5217", "5218", "5219", "5220", "5221", "5222", "5223",
						"133", "133", "383", "384", "385", "386", "430", "431"))));
		assertTrue("Asserts that the data returned from the Entitlements API for a PGN student token for the PGO platform has the correct Banned Articles data",
				entitlementsAPI.entitlements.bannedCategories.equals(new ArrayList<String>(Arrays.asList("5002", "5009", "3712", "3712", "3712"))));
	}
	
	@Test
	public void testPGNUSOnlyAndDisplayTexasBannedContentListCorrectContent() throws ClientProtocolException, IOException{
		String pgnStudent  = authAPI.getTokenForUsername("garage", "capdig", "3", "username");
		entitlementsAPI.getEntitlementsForApp(pgnStudent);

		
		assertTrue("Asserts that the data returned from the Entitlements API for a PGN student token for the PGO platform has the correct Banned Articles data",
				entitlementsAPI.entitlements.bannedArticles.equals(new ArrayList<String>(Arrays.asList("8757", "8758", "8759", "8760", "8761", "8762", "8763",
						"8764", "8768", "8779", "8780", "8781", "8782", "8783", "8784", "8785", "8786", "8787", "8788", "8789", "8790", "8791", "8792", "8793",
						"8794", "8795", "8796", "8797", "8798", "8799", "8800", "8801", "8802", "8803", "8804", "8805", "8806", "8807", "8808", "8809", "8810",
						"8811", "8812", "8813", "8814", "8815", "8816", "8817", "8818", "8819", "8820", "8821", "8822", "8823", "8824", "8825", "8826", "8827",
						"8828", "8829", "8830", "8831", "8832", "8833", "8834", "8835", "8836", "8837"))));
		assertTrue("Asserts that the data returned from the Entitlements API for a PGN student token for the PGO platform has the correct Banned Articles data",
				entitlementsAPI.entitlements.bannedCategories.equals(new ArrayList<String>(Arrays.asList("8849", "8852", "8853", "8857", "8861", "8862"))));

	}
	
	@Test
	public void testCIOwnedContentKey(){
		ciResponse = entitlementsAPI.getEntitlementsForApp(ciToken);
		
		assertTrue("Asserts that the status code returned from the Entitlements API is valid and a 200",
				entitlementsAPI.statusCode.equals("HTTP/1.1 200 OK"));
		assertTrue("Asserts that the data returned from the Entitlements API for a CI account has an owned content key", 
				entitlementsAPI.callData.has("ownedContent"));
	}
	
	@Test
	public void testCIOwnedContentData(){
		ciResponse = entitlementsAPI.getEntitlementsForApp(ciToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a CI account has the correct owned content",
				entitlementsAPI.entitlements.ownedContent.equals(new ArrayList<String>(Arrays.asList("1450012"))));
	}
	
	@Test
	public void testCIBannedArticlesKey(){
		ciResponse = entitlementsAPI.getEntitlementsForApp(ciToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a CI account does not have a banned articles key", 
				!entitlementsAPI.callData.has("bannedArticles"));
	}
	
	@Test
	public void testCIBannedCategoriesKey(){
		ciResponse = entitlementsAPI.getEntitlementsForApp(ciToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a CI account does not have a banned categories key", 
				!entitlementsAPI.callData.has("bannedCategories"));
	}
	
	@Test
	public void testCITokenOnPGOBannedArticles(){
		ciResponse = entitlementsAPI.getEntitlementsForApp(ciToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a CI account does not have banned articles on PGO", 
				entitlementsAPI.entitlements.bannedArticles.size() == 0);
	}
	
	@Test
	public void testCITokenOnPGOBannedCategories(){
		ciResponse = entitlementsAPI.getEntitlementsForApp(ciToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a CI account does not have banned categories on PGO", 
				entitlementsAPI.entitlements.bannedCategories.size() == 0);
	}
	
	@Test
	public void testMasterAccountCIData() throws ClientProtocolException, IOException{
		String masterToken  = authAPI.getTokenForUsername("garagemaster", "test", "1", "capstone");
		entitlementsAPI.getEntitlementsForApp(masterToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a master token for the CI platform has the ownedContent key and books",
				entitlementsAPI.callData.has("ownedContent") && (entitlementsAPI.entitlements.ownedContent.size() > 0));
		assertTrue("Asserts that the data returned from the Entitlements API for a master token for the CI platform does not have the bannedArticles key",
				!entitlementsAPI.callData.has("bannedArticles"));
		assertTrue("Asserts that the data returned from the Entitlements API for a master token for the CI platform does not have the bannedCategories key",
				!entitlementsAPI.callData.has("bannedCategories"));
	}
	
	
	@Test
	public void testMasterAccountPGOOwnedContentKeys() throws ClientProtocolException, IOException{
		String masterToken  = authAPI.getTokenForUsername("garagemaster", "test", "2", "capstone");
		entitlementsAPI.getEntitlementsForApp(masterToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a master token for the PGO platform has the ownedContent key",
				entitlementsAPI.callData.has("ownedContent"));
	}
	
	@Test
	public void testMasterAccountPGOOwnedContentData() throws ClientProtocolException, IOException{
		String masterToken  = authAPI.getTokenForUsername("garagemaster", "test", "2", "capstone");
		entitlementsAPI.getEntitlementsForApp(masterToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a master token for the PGO platform has the ownedContent data",
				entitlementsAPI.entitlements.ownedContent.equals(new ArrayList<String>(Arrays.asList("1", "2", "3", "5", "7", "8", "12", "13", "14"))));
	}
	
	@Test
	public void testMasterAccountPGOOBannedArticlesKeys() throws ClientProtocolException, IOException{
		String masterToken  = authAPI.getTokenForUsername("garagemaster", "test", "2", "capstone");
		entitlementsAPI.getEntitlementsForApp(masterToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a master token for the PGO platform has the bannedArticles key",
				entitlementsAPI.callData.has("bannedArticles"));
	}
	
	@Test
	public void testMasterAccountPGOBannedArticlesData() throws ClientProtocolException, IOException{
		String masterToken  = authAPI.getTokenForUsername("garagemaster", "test", "2", "capstone");
		entitlementsAPI.getEntitlementsForApp(masterToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a master token for the PGO platform has the ownedContent data",
				entitlementsAPI.entitlements.bannedArticles.equals(new ArrayList<String>(Arrays.asList("5103", "5104", "5115", "5116", "5117", "5118", "5119",
				        "5120", "5121", "5122", "5123", "5124", "5125", "5126", "5130", "5131", "5135", "5136", "5138", "5139", "5140", "5143", "5145", "5159",
				        "5205", "5212", "5213", "5214", "5215", "5216", "5217", "5218", "5219", "5220", "5221", "5222", "5223", "133", "133", "383", "384", 
				        "385", "386", "430", "431"))));
	}
	
	@Test
	public void testMasterAccountPGOOBannedCategoriesKeys() throws ClientProtocolException, IOException{
		String masterToken  = authAPI.getTokenForUsername("garagemaster", "test", "2", "capstone");
		entitlementsAPI.getEntitlementsForApp(masterToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a master token for the PGO platform has the bannedCategories key",
				entitlementsAPI.callData.has("bannedCategories"));
	}
	
	@Test
	public void testMasterAccountPGOBannedCategoriesData() throws ClientProtocolException, IOException{
		String masterToken  = authAPI.getTokenForUsername("garagemaster", "test", "2", "capstone");
		entitlementsAPI.getEntitlementsForApp(masterToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a master token for the PGO platform has the ownedContent data",
				entitlementsAPI.entitlements.bannedCategories.equals(new ArrayList<String>(Arrays.asList("5002", "5009", "3712", "3712", "3712"))));
	}
	
	@Test
	public void testMasterAccountPGNOwnedContentKeys() throws ClientProtocolException, IOException{
		String masterToken  = authAPI.getTokenForUsername("garagemaster", "test", "3", "capstone");
		entitlementsAPI.getEntitlementsForApp(masterToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a master token for the PGN platform has the ownedContent key",
				entitlementsAPI.callData.has("ownedContent"));
	}
	
	@Test
	public void testMasterAccountPGNOwnedContentData() throws ClientProtocolException, IOException{
		String masterToken  = authAPI.getTokenForUsername("garagemaster", "test", "3", "capstone");
		entitlementsAPI.getEntitlementsForApp(masterToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a master token for the PGN platform has the ownedContent data",
				entitlementsAPI.entitlements.ownedContent.equals(new ArrayList<String>(Arrays.asList("6", "9", "10", "11", "15"))));
	}
	
	@Test
	public void testMasterAccountPGNBannedArticlesKeys() throws ClientProtocolException, IOException{
		String masterToken  = authAPI.getTokenForUsername("garagemaster", "test", "3", "capstone");
		entitlementsAPI.getEntitlementsForApp(masterToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a master token for the PGN platform has the bannedArticles key",
				entitlementsAPI.callData.has("bannedArticles"));
	}
	
	@Test
	public void testMasterAccountPGNBannedArticlesData() throws ClientProtocolException, IOException{
		String masterToken  = authAPI.getTokenForUsername("garagemaster", "test", "3", "capstone");
		entitlementsAPI.getEntitlementsForApp(masterToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a master token for the PGN platform has the ownedContent data",
				entitlementsAPI.entitlements.bannedArticles.equals(new ArrayList<String>(Arrays.asList("8757", "8758", "8759", "8760", "8761", "8762", "8763",
						"8764", "8768", "8779", "8780", "8781", "8782", "8783", "8784", "8785", "8786", "8787", "8788", "8789", "8790", "8791", "8792", "8793",
						"8794", "8795", "8796", "8797", "8798", "8799", "8800", "8801", "8802", "8803", "8804", "8805", "8806", "8807", "8808", "8809", "8810",
						"8811", "8812", "8813", "8814", "8815", "8816", "8817", "8818", "8819", "8820", "8821", "8822", "8823", "8824", "8825", "8826", "8827",
						"8828", "8829", "8830", "8831", "8832", "8833", "8834", "8835", "8836", "8837"))));
	}
	
	
	@Test
	public void testMasterAccountPGNBannedCategoriesKeys() throws ClientProtocolException, IOException{
		String masterToken  = authAPI.getTokenForUsername("garagemaster", "test", "3", "capstone");
		entitlementsAPI.getEntitlementsForApp(masterToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a master token for the PGN platform has the bannedCategories key",
				entitlementsAPI.callData.has("bannedCategories"));
	}
	
	@Test
	public void testMasterAccountPGNBannedCategoriesData() throws ClientProtocolException, IOException{
		String masterToken  = authAPI.getTokenForUsername("garagemaster", "test", "3", "capstone");
		entitlementsAPI.getEntitlementsForApp(masterToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a master token for the PGN platform has the ownedContent data",
				entitlementsAPI.entitlements.bannedCategories.equals(new ArrayList<String>(Arrays.asList("8849", "8852", "8853", "8857", "8861",
				        "8862"))));
	}
	
	@Test
	public void testNononsenseDataKeys() throws ClientProtocolException, IOException{
		String nononsenseToken  = authAPI.getTokenForUsername("TeacherLP", "test", "6", "nononsense");
		entitlementsAPI.getEntitlementsForApp(nononsenseToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a Nononsense account has the ownedContent key",
				entitlementsAPI.callData.has("ownedContent"));
		assertTrue("Asserts that the data returned from the Entitlements API for a Nononsense account does not have the bannedArticles key",
				!entitlementsAPI.callData.has("bannedArticles"));
		assertTrue("Asserts that the data returned from the Entitlements API for a Nononsense account does not have the bannedCategories key",
				!entitlementsAPI.callData.has("bannedCategories"));
	}
	
	@Test
	public void testNononsenseData() throws ClientProtocolException, IOException{
		String nononsenseToken  = authAPI.getTokenForUsername("NoNoUK", "test", "6", "nononsense");
		entitlementsAPI.getEntitlementsForApp(nononsenseToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a Nononsense account has the ownedContent key",
				entitlementsAPI.entitlements.ownedContent.equals(new ArrayList<String>(Arrays.asList("1", "2"))));
	}
	
	@Test
	public void testNononsenseAssessmentsData() throws ClientProtocolException, IOException{
		String nononsenseToken  = authAPI.getTokenForUsername("TeacherA", "test", "6", "nononsense");
		entitlementsAPI.getEntitlementsForApp(nononsenseToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a Nononsense account has the ownedContent key",
				entitlementsAPI.entitlements.ownedContent.equals(new ArrayList<String>(Arrays.asList("1"))));
	}
	
	@Test
	public void testNononsenseLessonPlanData() throws ClientProtocolException, IOException{
		String nononsenseToken  = authAPI.getTokenForUsername("TeacherLP", "test", "6", "nononsense");
		entitlementsAPI.getEntitlementsForApp(nononsenseToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a Nononsense account has the ownedContent key",
				entitlementsAPI.entitlements.ownedContent.equals(new ArrayList<String>(Arrays.asList("2"))));
	}
	
	@Test
	public void testNononsenseTokenwWithNoEntitlementsData() throws ClientProtocolException, IOException{
		String nononsenseToken  = authAPI.getTokenForUsername("NoNoDont", "test", "6", "nononsense");
		entitlementsAPI.getEntitlementsForApp(nononsenseToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a Nononsense account has the ownedContent key",
				entitlementsAPI.callData.has("ownedContent") && (entitlementsAPI.entitlements.ownedContent.size() == 0));
		assertTrue("Asserts that the data returned from the Entitlements API for a Nononsense account does not have the bannedArticles key",
				!entitlementsAPI.callData.has("bannedArticles"));
		assertTrue("Asserts that the data returned from the Entitlements API for a Nononsense account does not have the bannedCategories key",
				!entitlementsAPI.callData.has("bannedCategories"));
	}
	
	@Test
	public void testNononsenseTokenHasNoBannedArticlesOrBannedCategoriesJSON() throws ClientProtocolException, IOException{
		String nononsenseToken  = authAPI.getTokenForUsername("NoNoDont", "test", "6", "nononsense");
		entitlementsAPI.getEntitlementsForApp(nononsenseToken);
		
		assertTrue("Asserts that the data returned from the Entitlements API for a Nononsense account does not have the bannedArticles key",
				!entitlementsAPI.callData.has("bannedArticles"));
		assertTrue("Asserts that the data returned from the Entitlements API for a Nononsense account does not have the bannedCategories key",
				!entitlementsAPI.callData.has("bannedCategories"));
	}
}
