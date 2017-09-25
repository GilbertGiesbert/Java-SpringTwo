package joern.java.spring2.core.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import joern.java.spring2.core.config.SwaggerConfig;
import joern.java.spring2.core.model.Person;

@RestController
public class SwaggerController {
	
	@RequestMapping(value = SwaggerConfig.API_PATH+"/person", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = Person.class),
    })
    public Person person() {
		
		Person p = new Person();
		p.setName("Peter");
		p.setAge(33);
		return p;
    }

}