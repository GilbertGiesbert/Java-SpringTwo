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

public class TreasureClient {
	
	private static final Logger L = LoggerFactory.getLogger(TreasureClient.class);
	
	public static final String REST_SERVICE_BASE_URL = "http://localhost:8080/Spring2";
	
	
	public static void listAllTreasures(String accessToken) {
		
		
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		// String url = REST_SERVICE_BASE_URL+"/treasures/list/?access_token="+accessToken;
		// String url = REST_SERVICE_BASE_URL+"/treasures/list";
		String url = "http://192.168.56.1:8080/spring2/api/treasures/list";
		
		ResponseEntity<List> response = new RestTemplate().exchange(url, HttpMethod.GET, request, List.class);
		
		List<LinkedHashMap<String, Object>> responseList = (List<LinkedHashMap<String, Object>>)response.getBody();
		
        if(responseList != null){
        	
        	L.debug("responseList.size(): "+responseList.size());
            for(LinkedHashMap<String, Object> map : responseList){
            	
            	String treasureInfo = "Treasure: id="+map.get("id")+", name="+map.get("name")+", value="+map.get("value");
            	L.debug(treasureInfo);
                
            }
        }else{
            L.debug("responseList is null");
        }
		
	}

}