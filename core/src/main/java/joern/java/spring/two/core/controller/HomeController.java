package joern.java.spring.two.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for home page.
 */
@Controller
public class HomeController {
	
	
    @RequestMapping("/")
    public ModelAndView handleRequest() throws Exception {
    	return new ModelAndView("Home");  
    }
}
