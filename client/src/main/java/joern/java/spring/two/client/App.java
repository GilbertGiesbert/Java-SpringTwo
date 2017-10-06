package joern.java.spring.two.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import joern.java.spring.two.client.model.AuthTokenData;
import joern.java.spring.two.client.util.SimpleWaitUtil;

/**
 * Hello world!
 *
 */
public class App {
	
	private static final Logger L = LoggerFactory.getLogger(App.class);
	
    public static void main( String[] args ) {
    	
    	AuthTokenData authTokenData = new AuthTokenData();
    	String username = "bill";
    	String password = "abc123";
    	String wrongPassword = "vsevgbt";
    	
    	int i = 1;
    	
    	L.debug(i+++" Should work...");
    	UserClient.requestAllUsers();
    	
    	L.debug(i+++" Should fail...");
    	TreasureClient.requestAllTreasures(authTokenData);
    	
    	L.debug(i+++" Should fail...");
    	TreasureClient.requestRefreshAuthToken(authTokenData);

    	L.debug(i+++" Should fail...");
    	TreasureClient.requestNewAuthToken(username, wrongPassword);
    	
    	L.debug(i+++" Should work...");
    	authTokenData = TreasureClient.requestNewAuthToken(username, password);
    	
    	L.debug(i+++" Should work...");
    	TreasureClient.requestAllTreasures(authTokenData);
    	
    	int accessTokenValiditySeconds = 5;
    	SimpleWaitUtil.doWait(accessTokenValiditySeconds);
    	
    	L.debug(i+++" Should fail...");
    	TreasureClient.requestAllTreasures(authTokenData);
    	
    	L.debug(i+++" Should work...");
    	authTokenData = TreasureClient.requestRefreshAuthToken(authTokenData);
    	
    	L.debug(i+++" Should work...");
    	TreasureClient.requestAllTreasures(authTokenData);
    }
}