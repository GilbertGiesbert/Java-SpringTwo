package joern.java.spring2.core.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import joern.java.spring2.core.config.SwaggerConfig;
import joern.java.spring2.core.dao.UserDAO;
import joern.java.spring2.core.model.Person;
import joern.java.spring2.core.model.User;

@RestController
public class SwaggerController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
    private UserDAO userDao;
	
	@RequestMapping(value = SwaggerConfig.API_PATH+"/person", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person person() {
		
		Person p = new Person();
		p.setName("Peter");
		p.setAge(33);
		return p;
    }
	
	@RequestMapping(value = SwaggerConfig.API_PATH+"/person/{name}/{age}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person person(@PathVariable String name, @PathVariable int age) {
		
		Person p = new Person();
		p.setName(name);
		p.setAge(age);
		return p;
    }
	
	@RequestMapping(value = SwaggerConfig.API_PATH+"/userList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> userList() {
		return userDao.list();
    }
	
	@RequestMapping(value = SwaggerConfig.API_PATH+"/helloMessage", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String helloMessage(Locale locale) {
		
		String message = "locale="+locale+", message="+messageSource.getMessage("hello", null, locale);
		return message;
    }
	
	@RequestMapping(value = SwaggerConfig.API_PATH+"/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User user(@PathVariable int id) {
		return userDao.get(id);
    }
	
    @ApiOperation(value = "Method to bail out the bad guy.")
    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "Successful bail out jail."),
            @ApiResponse(code = 404, message = "This guy was n't found."),
            @ApiResponse(code = 402, message = "You didn't pay enough.")
    })
	@RequestMapping(value = SwaggerConfig.API_PATH+"/bailOut/{id}/{bail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> bailOut(@PathVariable int id, @PathVariable int bail) {
    	
    	if(bail < 100) {
    		return new ResponseEntity<User>(HttpStatus.PAYMENT_REQUIRED);
    	}
		
		User u = userDao.get(id);
		
		if(u != null) {
			
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
    }

}