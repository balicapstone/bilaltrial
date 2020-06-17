package Tests;

import static org.junit.Assert.*;

import java.io.IOException;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import APIControllers.AuthenticationAPIController;
import APIControllers.ModuleListAPIController;
import CMSPages.CMSLandingPage;
import UserClasses.User;
import UserClasses.UserInfo;

public class AuthentificationTests {
	public static User user = new User(UserInfo.GARAGESTUDENT);
	public static CMSLandingPage cms;
	public static AuthenticationAPIController authAPI;
	public static ModuleListAPIController moduleAPI;
	
	@BeforeClass
	public static void executeBefore(){
		authAPI = new AuthenticationAPIController(user.getEnvironment());
		moduleAPI = new ModuleListAPIController(user.getEnvironment());
		System.out.println(user.getEnvironment());
	}
	
	@AfterClass
	public static void executeAfter(){
		user.quit();
	}
	
	/*
	 * Login types.

		username
		-pgo/pgn/ci username and password


		sqs
		-pgo/pgn/ci sqs

		ip
		-pgo/pgn/ci ip

		capstone
		-master account username and password

		cappyducky
		-master account sqs

		nononsense
		-nono username and password

		cms
		-cms username and password

		super
		-Will’s super duper qa api login

		App Ids

		1: Capstone Interactive
		2: PebbleGo
		3: PebbleGo Next
		4: pivotEd //Get rid of this and tests that use it
		5: Master Account //Get rid of this and tests that use it
		6: No Nonsense Literacy
		7: CMS

		//Test bad numbers
	 * 
	 */
	
	
	@Test
	public void testUsernamePGO() throws ClientProtocolException, IOException{
		String token = authAPI.getTokenForUsername("nolinkpgo", "test", "2", "username");
		
		assertTrue("Asserts that a PGO token is valid", authAPI.verifyPGToken(token, "2"));
		assertTrue("Asserts that a PGO user returned is correct", authAPI.pgAuthUser.userID.equals("125034"));
		
		assertTrue("Asserts that a NoNonsense user cannot access the CMS", !authAPI.verifyPGToken(token, "7"));
	}
	
	@Test
	public void testUsernamePGN() throws ClientProtocolException, IOException{
		String token = authAPI.getTokenForUsername("nolinkpgn", "test", "3", "username");

		assertTrue("Asserts that a PGN token is valid", authAPI.verifyPGToken(token, "3"));
		assertTrue("Asserts that a PGN user returned is correct", authAPI.pgAuthUser.userID.equals("125036"));
		
		assertTrue("Asserts that a PGN user cannot access the CMS", !authAPI.verifyPGToken(token, "7"));
	}
	
	@Test
	public void testUsernameCI() throws ClientProtocolException, IOException{
		String token = authAPI.getTokenForUsername("nolinkci", "books", "1", "username");
		
		assertTrue("Asserts that a CI token is valid", authAPI.verifyCIToken(token, "1"));
		assertTrue("Asserts that a CI user returned is correct", authAPI.ciAuthUser.userID.equals("62555"));
		
		assertTrue("Asserts that a CI user cannot access the CMS", !authAPI.verifyCIToken(token, "7"));
	}
	
	@Test
	public void testUsernameMasterAccount() throws ClientProtocolException, IOException{
		String token = authAPI.getTokenForUsername("garagemaster", "test", "3", "capstone");
		
		assertTrue("Asserts that a Master token is valid", authAPI.verifyMasterToken(token, "3"));
		assertTrue("Asserts that a Master token has a CI user", authAPI.masterAuthUser.CIUser.userID.equals("18410"));
		assertTrue("Asserts that a Master token has a PGO user", authAPI.masterAuthUser.PGOUser.userID.equals("45474"));
		assertTrue("Asserts that a Master token has a PGN user", authAPI.masterAuthUser.PGNUser.userID.equals("45474"));
		
		assertTrue("Asserts that a Master user cannot access the CMS", !authAPI.verifyMasterToken(token, "7"));
	}
	
	@Test
	public void testUsernameNoNonsense() throws ClientProtocolException, IOException{
		String token = authAPI.getTokenForUsername("TeacherLP", "test", "6", "nononsense");

		assertTrue("Asserts that a NoNonsense token is valid", authAPI.verifyNoNoToken(token, "6"));
		assertTrue("Asserts that a NoNonsense token gives us the correct user", authAPI.nonoAuthUser.username.equals("TeacherLP"));
		
		assertTrue("Asserts that a NoNonsense user cannot access the CMS", !authAPI.verifyNoNoToken(token, "7"));
	}
	
	@Test
	public void testUsernameSuper() throws ClientProtocolException, IOException{
		String token = authAPI.getTokenForUsername("whall@capstonepub.com", "whcap0546", "7", "cms");
		
		assertTrue("Asserts that a CMS user can access the CMS", authAPI.verifyCMSToken(token, "7"));
		assertTrue("Asserts that a CMS token gives us the correct user", authAPI.cmsAuthUser.username.equals("whall@capstonepub.com"));
		
		assertTrue("Asserts that a NoNonsense user cannot access the CMS", !authAPI.verifyCMSToken(token, "2"));
	}
	
	@Test
	public void testSQSPGOSTUDENT() throws ParseException, IOException{
		String token = authAPI.getTokenForSQS("2", "sqs", "4bd11cfa2814d3b160b277101b6c420ec1ad35f70ce92758b50613f4a127d3e0");
		
		assertTrue("Asserts that a PGO Student SQS token is valid", authAPI.verifyPGToken(token, "2"));
		assertTrue("Asserts that a PGO Student SQS token gets the correct user", authAPI.pgAuthUser.userID.equals("40378"));
		
		assertTrue("Asserts that a PGO Student user cannot access the CMS", !authAPI.verifyPGToken(token, "7"));
	}
	
	@Test
	public void testSQSPGNSTUDENT() throws ParseException, IOException{
		String token = authAPI.getTokenForSQS("3", "sqs", "4bd11cfa2814d3b160b277101b6c420ec1ad35f70ce92758b50613f4a127d3e0");
		
		assertTrue("Asserts that a PGN Student SQS token is valid", authAPI.verifyPGToken(token, "3"));
		assertTrue("Asserts that a PGN Student SQS token gets the correct user", authAPI.pgAuthUser.userID.equals("40378"));
		
		assertTrue("Asserts that a PGN user cannot access the CMS", !authAPI.verifyPGToken(token, "7"));
	}
	
	@Test
	public void testSQSPGOTEACHER() throws ParseException, IOException{
		String token = authAPI.getTokenForSQS("2", "sqs", "c372a3d30140531999a9297ef93c98b518c56ed2a5e3d6a3f2312e814d135b7a");
		
		assertTrue("Asserts that a PGO Teacher SQS token is valid", authAPI.verifyPGToken(token, "2"));
		assertTrue("Asserts that a PGO Teacher SQS token gets the correct user", authAPI.pgAuthUser.userID.equals("75790"));
		
		assertTrue("Asserts that a PGO Teacher user cannot access the CMS", !authAPI.verifyPGToken(token, "7"));
	}
	
	@Test
	public void testSQSPGNTEACHER() throws ParseException, IOException{
		String token = authAPI.getTokenForSQS("3", "sqs", "c372a3d30140531999a9297ef93c98b518c56ed2a5e3d6a3f2312e814d135b7a");
		
		assertTrue("Asserts that a PGN Teacher SQS token is valid", authAPI.verifyPGToken(token, "3"));
		assertTrue("Asserts that a PGN Teacher SQS token gets the correct user", authAPI.pgAuthUser.userID.equals("75790"));
		
		assertTrue("Asserts that a PGN Teacher user cannot access the CMS", !authAPI.verifyPGToken(token, "7"));
	}
	
	@Test
	public void testSQSCIStudent() throws ParseException, IOException{
		String token = authAPI.getTokenForSQS("1", "sqs", "edb50af637d123b3525192cd08d5710e92c666194929bbfa3181e9a5518f2b9d");
		
		assertTrue("Asserts that a CI Student SQS token is valid", authAPI.verifyCIToken(token, "1"));
		assertTrue("Asserts that a CI Student SQS token gets the correct user", authAPI.ciAuthUser.userID.equals("62555"));
		
		assertTrue("Asserts that a CI Student user cannot access the CMS", !authAPI.verifyCIToken(token, "7"));
	}
	
	@Test
	public void testSQSMaster() throws ParseException, IOException{
		String token = authAPI.getTokenForSQS("3", "cappyducky", "$2y$10$jXAExJDUZtc7TbKss/uQ6uwozBaNho6VX2EsmpXEaW5hgyMmhRmqq");
		
		assertTrue("Asserts that a Master SQS token is valid for CI", authAPI.verifyMasterToken(token, "3"));
		
		assertTrue("Asserts that a Master token has a CI user", authAPI.masterAuthUser.CIUser.userID.equals("18410"));
		assertTrue("Asserts that a Master token has a PGO user", authAPI.masterAuthUser.PGOUser.userID.equals("45474"));
		assertTrue("Asserts that a Master token has a PGN user", authAPI.masterAuthUser.PGNUser.userID.equals("45474"));
		
		assertTrue("Asserts that a Master user cannot access the CMS", !authAPI.verifyMasterToken(token, "7"));
	}
	
	//Wait to write these until you talk to Tom about which IPs to use
	@Test
	public void testIPPGO() throws ParseException, IOException{
		String token = authAPI.getTokenForIP("2", "ip", "192.186.0.0");
		
		assertTrue("Asserts that a IP token for PGO is valid", authAPI.verifyPGToken(token, "2"));
		assertTrue("Asserts that a IP Token for PGO gets the correct user", authAPI.pgAuthUser.userID.equals("156323"));
		assertTrue("Asserts that an IP token for CMS is not valid", !authAPI.verifyPGToken(token, "7"));		
	}
	
	@Test
	public void testIPPGN() throws ParseException, IOException{
		String token = authAPI.getTokenForIP("3", "ip", "192.186.0.0");
		
		assertTrue("Asserts that a IP token for PGN is valid", authAPI.verifyPGToken(token, "3"));
		assertTrue("Asserts that a IP Token for PGN gets the correct user", authAPI.pgAuthUser.userID.equals("156323"));
		assertTrue("Asserts that an IP token for CMS is not valid", !authAPI.verifyPGToken(token, "7"));	
	}
	
	//Building is IP Building
	@Test
	public void testIPCI() throws ParseException, IOException{
		String token = authAPI.getTokenForIP("1", "ip", "192.186.0.0");
		
		assertTrue("Asserts that a IP token for CI is valid", authAPI.verifyCIToken(token, "1"));
		assertTrue("Asserts that a IP Token for CI gets the correct user", authAPI.ciAuthUser.userID.equals("66396"));
		assertTrue("Asserts that an IP token for CMS is not valid", !authAPI.verifyPGToken(token, "7"));	
	}
}
