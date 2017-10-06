package joern.java.spring.two.client;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import joern.java.spring.two.client.model.AuthTokenData;

public class TreasureClient {
	
	private static final Logger L = LoggerFactory.getLogger(TreasureClient.class);
	
	public static final String REST_SERVICE_BASE_URL = "http://localhost:8080/spring2/api/treasures";
	
	public static final String AUTH_SERVER_BASE_URL = "http://localhost:8080/spring2/oauth/token";
	
	// http://localhost:8080/spring2/oauth/token?grant_type=password&username=bill&password=abc123
	
	public static AuthTokenData requestNewAuthToken(String username, String password) {
		
        String url = AUTH_SERVER_BASE_URL+"?grant_type=password&username="+username+"&password="+password;
        return requestAuthToken(url);
	}
	
	public static AuthTokenData requestRefreshAuthToken(AuthTokenData data) {
		
		String refreshToken = data.get(AuthTokenData.KEY_REFRESH_TOKEN);
        String url = AUTH_SERVER_BASE_URL+"?grant_type=refresh_token&refresh_token="+refreshToken;
        return requestAuthToken(url);
	}
	
	private static AuthTokenData requestAuthToken(String url) {
		
		String trustedClientUsername = "trusted-client";
		String trustedClientPassword = "secret";
		
        String plainClientCredentials= trustedClientUsername+":"+trustedClientPassword;
        String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));
        
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Basic " + base64ClientCredentials);
        
        HttpEntity<String> request = new HttpEntity<String>(headers);
        
        LinkedHashMap<String, Object> responseData = null;
        try {
        	ResponseEntity<Object> response = new RestTemplate().exchange(url, HttpMethod.POST, request, Object.class);
        	responseData = (LinkedHashMap<String, Object>)response.getBody();
        	
        }catch(Exception e) {
        	L.error("Failed to get response data", e);
        	return new AuthTokenData();
        }
        
        
        AuthTokenData authTokenData = new AuthTokenData();
        if(responseData != null) {
        	
        	authTokenData.setData(responseData);
        	L.debug(authTokenData.toString());
        	
        }else {
        	L.debug("response data is null");
        }
        return authTokenData;
		
	}

	public static void requestAllTreasures(AuthTokenData data) {
		
		String accessToken = data.get(AuthTokenData.KEY_ACCESS_TOKEN);
		
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        String url = REST_SERVICE_BASE_URL+"/list/?access_token="+accessToken;
        
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		List<LinkedHashMap<String, Object>> responseData = null;
		try {
			ResponseEntity<List> response = new RestTemplate().exchange(url, HttpMethod.GET, request, List.class);
			responseData = (List<LinkedHashMap<String, Object>>)response.getBody();
			
        }catch(Exception e) {
        	L.error("Failed to get response data", e);
        	return;
        }
		
        if(responseData != null){
        	
        	L.debug("response data size: "+responseData.size());
            for(LinkedHashMap<String, Object> map : responseData){
            	
            	String treasureInfo = "Treasure: id="+map.get("id")+", name="+map.get("name")+", valueInCents="+map.get("valueInCents");
            	L.debug(treasureInfo);
            }
        }else{
            L.debug("response data is null");
        }
	}

}