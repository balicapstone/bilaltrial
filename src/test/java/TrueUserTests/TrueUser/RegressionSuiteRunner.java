package TrueUserTests.TrueUser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tools.ant.taskdefs.optional.junit.XMLJUnitResultFormatter;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import Tests.BookReaderTests;
import Tests.PGNGameTests;
import Tests.PGNLoggingTests;
import Tests.PGOGameTests;
import Tests.PGOLoggingTests;
import Tests.PGONEWLoginTests;
import Tests.SuperAdminTests;
import Tests.TeacherHomeScreenTests;
 

public class RegressionSuiteRunner{
	
	//Used in LoginPage to set the environment for tests
	public static String product;
	public static String environment;
	public static String browser;
	public static Boolean headless;
	public static SuiteType suite;
	public static Watcher watcher;
	public static String folderPath;
	public static String buildTag;
	public static Boolean local;
	public static HTMLReportGenerator htmlGenerator;
	
	// testBuildTag pgonew staging chrome HOMEPAGE 
	public static void main(String[] args) throws IOException{
		System.out.println("RegressionSuiteRunner suite is running");
		if(args.length == 0){
			buildTag = "pgo-staging-sample-12555";
			product = "pgo";
			environment = "staging";
			browser = "chrome";
	        suite = SuiteType.ALL;
			headless = true;
		}
		else if(args.length == 1){
			buildTag = args[0].toString();
			product = "pgo";
			environment = "staging";
			browser = "chrome";
			suite = SuiteType.ALL;
			headless = true;
		}
		else if (args.length == 2){
			buildTag = args[0].toString();
			product = args[1].toString();
			environment = "staging";
			browser = "chrome";
			suite = SuiteType.ALL;
			headless = true;
		}
		else if (args.length == 3){
			buildTag = args[0].toString();
			product = args[1].toString();
			environment = args[2].toString();
			browser = "chrome";
			suite = SuiteType.ALL;
			headless = true;
		}
		else if (args.length == 4){
			buildTag = args[0].toString();
			product = args[1].toString();
			environment = args[2].toString();
			browser = args[3].toString();
			suite = SuiteType.ALL;
			headless = false;
		}
		else if(args.length == 5){
			buildTag = args[0].toString();
			product = args[1].toString();
			environment = args[2].toString();
			browser = args[3].toString();
			suite =  SuiteType.valueOf(args[4].toString().toUpperCase());
			headless = false;
		}
		else if(args.length == 6){
			buildTag = args[0].toString();
			product = args[1].toString();
			environment = args[2].toString();
			browser = args[3].toString();
			suite = SuiteType.valueOf(args[4].toString().toUpperCase());
			headless = args[5].toString().equals("headless"); 
		}
		else{
			buildTag = args[0].toString();
			product = args[1].toString();
			environment = args[2].toString();
			browser = args[3].toString();
			suite = SuiteType.valueOf(args[4].toString().toUpperCase());
			headless = args[5].toString().equals("headless"); 
		}
		
		if(buildTag == null){
			buildTag = "LocalTesting" + product + environment;
		}
		
		if(folderPath == null){
			Path localPath = Paths.get("/Users/whall/NewQAProject/NewQAProject/SeleniumScreenshots/");
			
			if(Files.exists(localPath)){
				local = true;
				folderPath = "/Users/whall/NewQAProject/NewQAProject/SeleniumScreenshots/"+buildTag;
				
				if(Files.exists(Paths.get(folderPath))){
					System.out.println("Using Folder created at "+ folderPath);
				}
				
				if(new File("/Users/whall/NewQAProject/NewQAProject/SeleniumScreenshots/"+buildTag).mkdirs()){
					local = true;
					System.out.println("Folder created at "+ "/Users/whall/NewQAProject/NewQAProject/SeleniumScreenshots/" + buildTag);
				}
			}
			else if (new File("./"+buildTag).mkdirs()){
				folderPath = "./"+buildTag;
				local = false;
				System.out.println("folder created at " + "./"+buildTag);
			}
			else{
				System.out.print("Tried but could not create a folder");
			}
		}
		
		JUnitCore junit = new JUnitCore(); 
		
	//	junit.addListener(new ExecutionListener(new XMLJUnitResultFormatter()) {
	//	    @Override
	//	    public void testStarted(Description description) throws Exception {
	//	        formatter.setOutput(new FileOutputStream(new File("./"+buildTag,"TEST-"+description.getDisplayName()+".xml")));
	//	        super.testStarted(description);
	//	    }
	//	});
		
		
	//	AntXMLRunListener runListener = new AntXMLRunListener();
	//	runListener.setOutputStream(new FileOutputStream(new File("./"+buildTag,"junit_report.xml")));
	//	junit.addListener(runListener);
		
		
		
		Result result = null;
		
		if(product.equals("pgo")){		
			switch(suite){
				case ALL:
			      result = junit.run(PGOStudentContentSuite.class);
					//result = junit.run(PGOGameTests.class);
					break;
				case STUDENT:	
					result = junit.run(PGOStudentContentSuite.class);
					break;
				case TEACHER:
					result = junit.run(TeacherHomeScreenTests.class);
					break;
				case SMOKE:
					result = junit.run(PGOSmokeTestSuite.class);
					break;
				case LOGGING:
					result = junit.run(PGOLoggingTests.class);
					break;
				case GAMES:
					result = junit.run(PGOGameTests.class);
					break;
			}
			if(suite==null){
				result = junit.run(PGOTests.class);
			}
		}
		else if(product.equals("pgn")){
			switch(suite){
				case ALL:
					result = junit.run(PGNTests.class);
					break;
				case STUDENT:
					result = junit.run(PGNStudentContent.class);
					break;
				case TEACHER:
					result = junit.run(TeacherHomeScreenTests.class);
					break;
				case SMOKE:
					result = junit.run(PGNSmokeTestSuite.class);
					break;
				case LOGGING:
					result = junit.run(PGNLoggingTests.class);
					break;
				case GAMES:
					result = junit.run(PGNGameTests.class);
					break;
			}	
			
			if(suite==null){
				result = junit.run(PGNTests.class);
			}
		}
		else if(product.equals("ci")){
			switch(suite){
			case ALL:
				result = junit.run(CITests.class);
				break;
			case STUDENT:
				result = junit.run(BookReaderTests.class);
				break;
			case TEACHER:
				result = junit.run(SuperAdminTests.class);
				break;
			case SMOKE:
				result = junit.run(BookReaderTests.class);
				break;
			default:
				result = junit.run(CITests.class);
				break;
			}	
			
			if(suite==null){
				result = junit.run(CITests.class);
			}
		}
		else if(product.equals("pgonew")){		
			switch(suite){
				case HOMEPAGE:
					result = junit.run(PGONewSuite.class);
					break;
			default:
				break;
			}
			if(suite==null){
				result = junit.run(PGONEWLoginTests.class);
			}
		}
		
		System.out.println("*****TEST RUN COMPLETE *****  "+ suite.getClass());
		htmlGenerator = new HTMLReportGenerator(buildTag);
		htmlGenerator.setEnvironment(environment);
		htmlGenerator.setSuite(suite.toString());
		try{
			
			System.out.println("Failure Count : " +result.getFailureCount());
			System.out.println("Total Test Count : " + result.getRunCount());
			System.out.println("Total Time (sec) : " + result.getRunTime()/1000);
			
			htmlGenerator.createReport(result);
		}catch(Exception e)
		{
			
		}
	}
}


