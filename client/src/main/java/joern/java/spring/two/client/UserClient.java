package joern.java.spring.two.client;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserClient {
	
	private static final Logger L = LoggerFactory.getLogger(UserClient.class);
	
	public static final String REST_SERVICE_BASE_URL = "http://localhost:8080/spring2/api";
	
	
	public static void requestAllUsers() {
		
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        String url = REST_SERVICE_BASE_URL+"/userList";
        
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
            	
            	String userInfo = "User: id="+map.get("id")+", username="+map.get("username")+", password="+map.get("password")+", email="+map.get("email");
            	L.debug(userInfo);
            }
        }else{
            L.debug("response data is null");
        }
	}

}
