package APIControllers;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DataClasses.CIAuthUser;
import DataClasses.CMSAuthUser;
import DataClasses.MasterAccountAuthUser;
import DataClasses.NoNoAuthUser;
import DataClasses.PGAuthUser;

public class AuthenticationAPIController {
	public String authEnvironment;
	public String authURL;
	public PGAuthUser pgAuthUser;
	public CIAuthUser ciAuthUser;
	public NoNoAuthUser nonoAuthUser;
	public MasterAccountAuthUser masterAuthUser;
	public CMSAuthUser cmsAuthUser;
	
	public AuthenticationAPIController(String environment){
		if(environment.equals("qa")){
			authEnvironment = "authqa";	
			authURL = ".pebblego.com";
		} else if(environment.equals("staging")){
			authEnvironment = "stapi";	
			authURL = ".pivoted.com";
		} else if(environment.equals("authqa")){
			authEnvironment = "authqa";	
			authURL = ".pebblego.com";
		} else{
			authEnvironment = "api";
			authURL = ".pebblego.com";
		}
	}
	
	
	public String getTokenForUsername(String username, String password, String appID, String loginType) throws ClientProtocolException, IOException{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("https://"+authEnvironment + authURL + "/api/v1/auth/check");
		
		ArrayList<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair("username", username));
		formparams.add(new BasicNameValuePair("password", password));
		formparams.add(new BasicNameValuePair("app_id", appID));
		formparams.add(new BasicNameValuePair("loginType", loginType));
		
		//Adds the form parameters and sets the Form as the entity for our post
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams);
		httpPost.setEntity(entity);
		
		HttpResponse response = client.execute(httpPost);
		
		//Handle the response and parse it into a searchable Json object
		JsonObject responseJson = (JsonObject) new JsonParser().parse(EntityUtils.toString(response.getEntity()));
		
		return responseJson.get("token").getAsString();
	}
	
	
	public String getTokenForSQS(String appID, String loginType, String sqs) throws ParseException, IOException{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("https://"+authEnvironment + authURL + "/api/v1/auth/check");
		
		ArrayList<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair("app_id", appID));
		formparams.add(new BasicNameValuePair("loginType", loginType));
		formparams.add(new BasicNameValuePair("sqs", sqs));
		
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams);
		httpPost.setEntity(entity);
		
		HttpResponse response = client.execute(httpPost);

		JsonObject testing = (JsonObject) new JsonParser().parse(EntityUtils.toString(response.getEntity()));
		return testing.get("token").getAsString();
	}
	
	
	public String getTokenForIP(String appID, String loginType, String IP) throws ParseException, IOException{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("https://"+authEnvironment + authURL  + "/api/v1/auth/check");
		
		ArrayList<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair("app_id", appID));
		formparams.add(new BasicNameValuePair("loginType", loginType));
		formparams.add(new BasicNameValuePair("ip", IP));
		
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams);
		httpPost.setEntity(entity);
		
		HttpResponse response = client.execute(httpPost);

		JsonObject testing = (JsonObject) new JsonParser().parse(EntityUtils.toString(response.getEntity()));
		return testing.get("token").getAsString();
	}
	
	public Boolean verifyPGToken(String token, String appID) throws ParseException, IOException{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("https://"+authEnvironment + authURL + "/api/v1/token/check");

		ArrayList<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair("token", token));
		formparams.add(new BasicNameValuePair("app_id", appID));
		
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams);
		httpPost.setEntity(entity);
		
		HttpResponse response = client.execute(httpPost);
		
		String responseString = EntityUtils.toString(response.getEntity());
		
		pgAuthUser = new PGAuthUser();
		
		if(responseString.contains("Unprocessable Entity") || responseString.contains("Token Invalid") || responseString.contains("error")){
			return false;
		}
		else{
			JsonObject data = (JsonObject) new JsonParser().parse(responseString);
			
			JsonObject userData = data.get("user").getAsJsonObject();
			
			try{
				if(userData.get("user_id").isJsonNull()){
					pgAuthUser.setUserID("");
				} else{
					pgAuthUser.setUserID(userData.get("user_id").getAsString());
				}
			} catch(Exception e){
				pgAuthUser.setUserID("");
			}
			
			try{
				if(userData.get("customer_id").isJsonNull()){
					pgAuthUser.setCustomerID("");
				} else{
					pgAuthUser.setCustomerID(userData.get("customer_id").getAsString());
				}
			} catch(Exception e){
				pgAuthUser.setCustomerID("");
			}
			
			try{
				if(userData.get("user_type_id").isJsonNull()){
					pgAuthUser.setUserTypeID("");
				} else{
					pgAuthUser.setUserTypeID(userData.get("user_type_id").getAsString());
				}
			} catch(Exception e){
				pgAuthUser.setUserTypeID("");
			}
			
			try{
				if(userData.get("username").isJsonNull()){
					pgAuthUser.setUsername("");
				} else{
					pgAuthUser.setUsername(userData.get("username").getAsString());
				}
			} catch(Exception e){
				pgAuthUser.setUsername("");
			}
			
			try{
				if(userData.get("user_query_string").isJsonNull()){
					pgAuthUser.setUserQueryString("");
				} else{
					pgAuthUser.setUserQueryString(userData.get("user_query_string").getAsString());
				}
			} catch(Exception e){
				pgAuthUser.setUserQueryString("");
			}
			
			try{
				if(data.get("linked_accounts").isJsonNull()){
					pgAuthUser.setLinkedAccounts(false);
				} else{
					pgAuthUser.setLinkedAccounts(data.get("linked_accounts").getAsBoolean());
				}
			} catch(Exception e){
				pgAuthUser.setLinkedAccounts(false);
			}
			
			return true;
		}
	}
	
	public Boolean verifyCIToken(String token, String appID) throws ParseException, IOException{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("https://"+authEnvironment + authURL + "/api/v1/token/check");

		ArrayList<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair("token", token));
		formparams.add(new BasicNameValuePair("app_id", appID));
		
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams);
		httpPost.setEntity(entity);
		
		HttpResponse response = client.execute(httpPost);
		
		String responseString = EntityUtils.toString(response.getEntity());
		
		ciAuthUser = new CIAuthUser();
		
		if(responseString.contains("Unprocessable Entity") || responseString.contains("Token Invalid") || responseString.contains("error")){
			return false;
		}
		else{
			JsonObject data = (JsonObject) new JsonParser().parse(responseString);
			
			JsonObject userData = data.get("user").getAsJsonObject();
			
			if(userData.get("user_id").isJsonNull()){
				ciAuthUser.setUserID("");
			} else{
				ciAuthUser.setUserID(userData.get("user_id").getAsString());
			}
			
			if(userData.get("user_type_id").isJsonNull()){
				ciAuthUser.setUserTypeID("");
			} else{
				ciAuthUser.setUserTypeID(userData.get("user_type_id").getAsString());
			}
			
			if(userData.get("account_id").isJsonNull()){
				ciAuthUser.setAccountID("");
			} else{
				ciAuthUser.setAccountID(userData.get("account_id").getAsString());
			}
			
			if(userData.get("building_id").isJsonNull()){
				ciAuthUser.setBuildingID("");
			} else{
				ciAuthUser.setBuildingID(userData.get("building_id").getAsString());
			}
			
			if(userData.get("sqs").isJsonNull()){
				ciAuthUser.setBuildingID("");
			} else{
				ciAuthUser.setBuildingID(userData.get("sqs").getAsString());
			}
			
			if(data.get("linked_accounts").isJsonNull()){
				ciAuthUser.setLinkedAccounts(false);
			} else{
				ciAuthUser.setLinkedAccounts(data.get("linked_accounts").getAsBoolean());
			}
			
			return true;
		}
	}
	
	public Boolean verifyNoNoToken(String token, String appID) throws ParseException, IOException{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("https://"+authEnvironment + authURL + "/api/v1/token/check");

		ArrayList<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair("token", token));
		formparams.add(new BasicNameValuePair("app_id", appID));
		
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams);
		httpPost.setEntity(entity);
		
		HttpResponse response = client.execute(httpPost);
		
		String responseString = EntityUtils.toString(response.getEntity());
		
		nonoAuthUser = new NoNoAuthUser();
		
		if(responseString.contains("Unprocessable Entity") || responseString.contains("Token Invalid") || responseString.contains("error")){
			return false;
		}
		else{
			JsonObject data = (JsonObject) new JsonParser().parse(responseString);
			
			JsonObject userData = data.get("user").getAsJsonObject();
			
			if(userData.get("user_id").isJsonNull()){
				nonoAuthUser.setUserID("");
			} else{
				nonoAuthUser.setUserID(userData.get("user_id").getAsString());
			}
			
			if(userData.get("username").isJsonNull()){
				nonoAuthUser.setUsername("");
			} else{
				nonoAuthUser.setUsername(userData.get("username").getAsString());
			}
			
			if(userData.get("user_query_string").isJsonNull()){
				nonoAuthUser.setUserQueryString("");
			} else{
				nonoAuthUser.setUserQueryString(userData.get("user_query_string").getAsString());
			}
			
			if(userData.get("firstname").isJsonNull()){
				nonoAuthUser.setFirstName("");
			} else{
				nonoAuthUser.setFirstName(userData.get("firstname").getAsString());
			}
			
			if(userData.get("lastname").isJsonNull()){
				nonoAuthUser.setLastName("");
			} else{
				nonoAuthUser.setLastName(userData.get("lastname").getAsString());
			}
			
			if(userData.get("created_at").isJsonNull()){
				nonoAuthUser.setCreatedAt("");
			} else{
				nonoAuthUser.setCreatedAt(userData.get("created_at").getAsString());
			}
			
			if(userData.get("deleted").isJsonNull()){
				nonoAuthUser.setDeleted("");
			} else{
				nonoAuthUser.setDeleted(userData.get("deleted").getAsString());
			}
			
			if(userData.get("shared_account").isJsonNull()){
				nonoAuthUser.setSharedAccount("");
			} else{
				nonoAuthUser.setSharedAccount(userData.get("shared_account").getAsString());
			}
			
			if(data.get("linked_accounts").isJsonNull()){
				nonoAuthUser.setLinkedAccounts(false);
			} else{
				nonoAuthUser.setLinkedAccounts(data.get("linked_accounts").getAsBoolean());
			}
			
			return true;
		}
	}
	
	public Boolean verifyCMSToken(String token, String appID) throws ParseException, IOException{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("https://"+authEnvironment + authURL + "/api/v1/token/check");

		ArrayList<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair("token", token));
		formparams.add(new BasicNameValuePair("app_id", appID));
		
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams);
		httpPost.setEntity(entity);
		
		HttpResponse response = client.execute(httpPost);
		
		String responseString = EntityUtils.toString(response.getEntity());
		
		cmsAuthUser = new CMSAuthUser();
		
		if(responseString.contains("Unprocessable Entity") || responseString.contains("Token Invalid") || responseString.contains("error")){
			return false;
		}
		else{
			JsonObject data = (JsonObject) new JsonParser().parse(responseString);
			
			JsonObject userData = data.get("user").getAsJsonObject();
			
			if(userData.get("user_id").isJsonNull()){
				cmsAuthUser.setUserID("");
			} else{
				cmsAuthUser.setUserID(userData.get("user_id").getAsString());
			}
			
			if(userData.get("username").isJsonNull()){
				cmsAuthUser.setUsername("");
			} else{
				cmsAuthUser.setUsername(userData.get("username").getAsString());
			}
			
			if(userData.get("user_query_string").isJsonNull()){
				cmsAuthUser.setUserQueryString("");
			} else{
				cmsAuthUser.setUserQueryString(userData.get("user_query_string").getAsString());
			}
			
			if(userData.get("firstname").isJsonNull()){
				cmsAuthUser.setFirstName("");
			} else{
				cmsAuthUser.setFirstName(userData.get("firstname").getAsString());
			}
			
			if(userData.get("lastname").isJsonNull()){
				cmsAuthUser.setLastName("");
			} else{
				cmsAuthUser.setLastName(userData.get("lastname").getAsString());
			}
			
			if(userData.get("created_at").isJsonNull()){
				cmsAuthUser.setCreatedAt("");
			} else{
				cmsAuthUser.setCreatedAt(userData.get("created_at").getAsString());
			}
			
			if(userData.get("deleted").isJsonNull()){
				cmsAuthUser.setDeleted("");
			} else{
				cmsAuthUser.setDeleted(userData.get("deleted").getAsString());
			}
			
			return true;
		}
	}
	
	public Boolean verifyMasterToken(String token, String appID) throws ParseException, IOException{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("https://"+authEnvironment + authURL + "/api/v1/token/check");

		ArrayList<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair("token", token));
		formparams.add(new BasicNameValuePair("app_id", appID));
		
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams);
		httpPost.setEntity(entity);
		
		HttpResponse response = client.execute(httpPost);
		
		String responseString = EntityUtils.toString(response.getEntity());
		
		masterAuthUser = new MasterAccountAuthUser();
		
		if(responseString.contains("Unprocessable Entity") || responseString.contains("Token Invalid") || responseString.contains("error")){
			return false;
		}
		else{
			JsonObject data = (JsonObject) new JsonParser().parse(responseString);
			
			JsonObject userData = data.get("user").getAsJsonObject();
			
			
			masterAuthUser = generateMasterAccount(userData);
			
			JsonObject linkedAccounts = data.get("linked_accounts").getAsJsonObject();
			
			if(linkedAccounts.has("1")){
				masterAuthUser.setCIUser(generateMasterAccount(linkedAccounts.get("1").getAsJsonObject()));
			}
			
			if(linkedAccounts.has("2")){
				masterAuthUser.setPGOUser(generateMasterAccount(linkedAccounts.get("2").getAsJsonObject()));
			}
			
			if(linkedAccounts.has("3")){
				masterAuthUser.setPGNUser(generateMasterAccount(linkedAccounts.get("3").getAsJsonObject()));
			}
			
			return true;
		}
	}
	
	public MasterAccountAuthUser generateMasterAccount(JsonObject data){
		MasterAccountAuthUser newAccount = new MasterAccountAuthUser();
		
		try{
			if(data.get("user_id").isJsonNull()){
				newAccount.setUserID("");
			} else{
				newAccount.setUserID(data.get("user_id").getAsString());
			}
		} catch(Exception e){
			newAccount.setUserID("");
		}
		
		try{
			if(data.get("master_id").isJsonNull()){
				newAccount.setMasterID("");
			} else{
				newAccount.setMasterID(data.get("master_id").getAsString());
			}
		} catch(Exception e){
			newAccount.setMasterID("");
		}
		
		try{
			if(data.get("customer_id").isJsonNull()){
				newAccount.setCustomerID("");
			} else{
				newAccount.setCustomerID(data.get("customer_id").getAsString());
			}
		} catch(Exception e){
			newAccount.setCustomerID("");
		}
		
		try{
			if(data.get("building_id").isJsonNull()){
				newAccount.setBuildingID("");
			} else{
				newAccount.setBuildingID(data.get("building_id").getAsString());
			}
		} catch(Exception e){
			newAccount.setBuildingID("");
		}
		
		try{
			if(data.get("sqs").isJsonNull()){
				newAccount.setSQS("");
			} else{
				newAccount.setSQS(data.get("sqs").getAsString());
			}
		} catch(Exception e){
			newAccount.setSQS("");
		}
		
		try{
			if(data.get("site").isJsonNull()){
				newAccount.setSite("");
			} else{
				newAccount.setSite(data.get("site").getAsString());
			}
		} catch(Exception e){
			newAccount.setSite("");
		}
		
		try{
			if(data.get("user_type_id").isJsonNull()){
				newAccount.setUserTypeID("");
			} else{
				newAccount.setUserTypeID(data.get("user_type_id").getAsString());
			}
		} catch(Exception e){
			newAccount.setUserTypeID("");
		}
		
		try{
			if(data.get("valid_subscription").isJsonNull()){
				newAccount.setValidSubscription("");
			} else{
				newAccount.setValidSubscription(data.get("valid_subscription").getAsString());
			}
		} catch(Exception e){
			newAccount.setValidSubscription("");
		}
		
		return newAccount;
	}
}
