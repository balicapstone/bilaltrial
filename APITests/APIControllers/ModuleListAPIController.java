package APIControllers;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.JsonObject;

public class ModuleListAPIController{
	public JsonObject callData;
	public String statusCode;
	public String environment;
	public String url;
	String token;
	
	
	public ModuleListAPIController(String env){
		if(env.equals("qa")){
			environment = "contentqa";
			url = ".pebblego.com";
		} else if(env.equals("staging")){
			environment = "staging";	
			url = ".pebblego.com";
		} else if(env.equals("authqa")){
			environment = "contentqa";	
			url = ".pebblego.com";
		} else{
			environment = "content";
			url = ".pebblego.com";
		}
	}
	
	public String getStatusCodeForCMSAccess(String token){
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet("https://"+environment+url+"/api/v1/modules/list");
		httpGet.setHeader("Authorization", "Bearer " + token);
	
		
		HttpResponse response = null;
		try {
			response = client.execute(httpGet);
			statusCode = response.getStatusLine().toString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return statusCode;
	}
}
