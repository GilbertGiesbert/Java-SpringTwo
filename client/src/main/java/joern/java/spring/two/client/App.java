package joern.java.spring.two.client;

/**
 * Hello world!
 *
 */
public class App {
	
    public static void main( String[] args ) {
    	
    	String accessToken = "";
    	TreasureClient.listAllTreasures(accessToken);
    }
}