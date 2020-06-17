package TrueUserTests.TrueUser;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.runner.JUnitCore;
import com.google.gson.JsonArray;
import SharedClasses.ContentNode;
import SharedClasses.ContentTreeAPI;
import Tests.PGNModuleVerificationTests;
import Tests.PGOModuleVerificationTests;
import UserClasses.User;

public class ModuleComparisonRunner {
	
	public static String correctEnvironment;
	public static String testingEnvironment;
	public static String module;
	public static String product;
	public static User user1;
	public static User user2;
	public static ContentTreeAPI content1;
	public static ContentTreeAPI content2;
	
	public static JsonArray topLevel1;
	public static JsonArray topLevel2;
	
	public static ArrayList<ContentNode> foundArticles1;
	public static ArrayList<ContentNode> foundArticles2;
	
	public static ArrayList<ContentNode> missingArticles1;
	public static ArrayList<ContentNode> duplicatedArticles1;
	
	public static ArrayList<ContentNode> missingArticles2;
	public static ArrayList<ContentNode> duplicatedArticles2;		
	
	public static ArrayList<ContentNode> differences;
	
	public static StringBuilder currentError = new StringBuilder();
	public static StringBuilder currentSuccess = new StringBuilder();
	
	public static void main(String[] args) throws IOException{	
		
		if(args.length == 0){
			product = "pgn";
			module = "9";
			correctEnvironment = "staging";
			testingEnvironment = "www";
		}
		else if(args.length == 1){
			product = args[0].toString();
			module = "1";
			correctEnvironment = "staging";
			testingEnvironment = "www";
		}
		else if (args.length == 2){
			product = args[0].toString();
			module = args[1].toString();
			correctEnvironment = "staging";
			testingEnvironment = "www";
		}
		else if (args.length == 3){
			product = args[0].toString();
			module = args[1].toString();
			correctEnvironment = args[2].toString();
			testingEnvironment = "www";
		}
		else if (args.length == 4){
			product = args[0].toString();
			module = args[1].toString();
			correctEnvironment = args[2].toString();
			testingEnvironment = args[3].toString();
		}
		
		JUnitCore junit = new JUnitCore(); 
		
		if(product.equals("pgo")){
			junit.run(PGOModuleVerificationTests.class);
		}
		
		if(product.equals("pgn")){
			junit.run(PGNModuleVerificationTests.class);
		}
		
	}
}
