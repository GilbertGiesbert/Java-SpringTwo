package joern.java.spring.two.client.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleWaitUtil {
	
	private static final Logger L = LoggerFactory.getLogger(SimpleWaitUtil.class);
	
    public static void doWait(int timeInSeconds) {
    	
    	int timeInMilliseconds = timeInSeconds * 1000;
    	long start = new Date().getTime();
    	long debugTime = start;
    	boolean wait = true;
    	
    	
    	    	
    	while(wait) {
    		
    		long now = new Date().getTime();
    		long currentWaitTime = now - start;
    		wait = currentWaitTime < timeInMilliseconds;
    		
    		if(wait) {
    			
    			if(now > debugTime) {
    				
    				debugTime += 1000;
    				long timeLeft = (timeInMilliseconds - currentWaitTime) / 1000;
    				L.debug("Will wait "+timeLeft+" more seconds.");	
    			}
    		}
    	}
    }
}