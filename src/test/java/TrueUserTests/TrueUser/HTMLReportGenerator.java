package TrueUserTests.TrueUser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class HTMLReportGenerator {
	String reportLocation;
	String failedTemplatePath = "./FailedReportTemplate.html";
	String successTemplatePath = "./SuccessReportTemplate.html";
	String successTextJPG = "congratulationstext.jpg";
	String succeeImageJPG = "congratulations.jpg";
	//String reportPathString = "./Report.html";
	String suite;
	String environment;
	
	Path reportPath;
	public static StringBuilder currentError = new StringBuilder();
	FileWriter outputSavedHere;
	
	public HTMLReportGenerator(String buildTag){
		try {
			if(!Files.exists(Paths.get("./"+buildTag))){
				new File("./"+buildTag).mkdirs();
				System.out.println("Using Folder created at "+ buildTag);
			}
			
			outputSavedHere = new FileWriter("./"+buildTag+"/" + buildTag + "Report.html");
		} catch (IOException e) {
			System.out.println("Could not create report at " + reportPath);
		}
	}
	
	public void setEnvironment(String e){
		environment = e;
	}
	
	public void setSuite(String s){
		suite = s;
	}
	int FailureCount;
	int TotalCount;
	public void createReport(Result result){
		try{
		String reportIn;
		try {
			if(result.getFailureCount() > 0){
			//if(Boolean.TRUE){
				FailureCount=result.getFailureCount();
				TotalCount=result.getRunCount()+result.getIgnoreCount();
			//	FailureCount=5;
				reportIn = new String(Files.readAllBytes(Paths.get(failedTemplatePath)));
			} else{
				reportIn = new String(Files.readAllBytes(Paths.get(successTemplatePath)));
			}
		} catch (IOException e) {
			reportIn = "";
			e.printStackTrace();
		}
		
		for(Failure f: result.getFailures()){
			String rawDescription = f.getDescription().toString();
		   String testDetail = f.getException().getMessage();
			
			String tempClassHolder;
			// Trims the failure name from TestName(TestClass):Error to TestClass
			try{
				tempClassHolder = rawDescription.substring(rawDescription.indexOf("(")+1, rawDescription.indexOf(")"));
			}catch(Exception e)
			{
				tempClassHolder=rawDescription;
				
			}
	
			// Trims the failure name from TestName(TestClass):Error to TestName
			String failed = f.toString().split("\\(")[0];

			//Gets rid of messaging after Build Info and replaces newlines with newline and space to format
			if(testDetail==null){
				testDetail = "";
			}
		
			if(testDetail.contains("Build info")){
				testDetail = testDetail.substring(0, testDetail.indexOf("Build info")); 
			}
			
			currentError.append("<tr><td width=\"15%\">" + tempClassHolder + "</td><td width=\"15%\">" + failed + "</td><td width=\"50%\">" + f.getTrace().substring(0, f.getTrace().indexOf(")")+1) + "</td></tr>");
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		reportIn = reportIn.replace("<!--TIME-->", dateFormat.format(date));

		reportIn = reportIn.replace("<!--SUITE-->", suite);
		reportIn = reportIn.replace("<!--ENVIRONMENT-->", environment);
		reportIn = reportIn.replace("<!--TotalCount-->", String.valueOf(TotalCount));
		reportIn = reportIn.replace("<!--FailureCount-->", String.valueOf(FailureCount));
		reportIn = reportIn.replace("<!--INSERT_RESULTS -->", currentError.toString());
		
		try {
			outputSavedHere.write(reportIn);
		} catch (IOException e) {
			System.out.println("Could not write to report at " + reportPath);
		}
		try {
			outputSavedHere.flush();
		} catch (IOException e) {
			System.out.println("Could not save the report to " + reportPath);
		}
	
	}catch(Exception e)
		{
		}
	}
	
}
