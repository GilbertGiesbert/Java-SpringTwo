package joern.java.spring.two.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import joern.java.spring.two.core.dao.Dao;
import joern.java.spring.two.core.model.Treasure;

@RestController
@RequestMapping(value = "/api/treasures")
public class TreasureRestController {
	
    @Autowired
    private Dao<Treasure> treasureDao;
    
    
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Treasure>> listAllTreasures() {
    	
        List<Treasure> list = treasureDao.list();
        if(CollectionUtils.isEmpty(list)){
            return new ResponseEntity<List<Treasure>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Treasure>>(list, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Treasure> getTreasure(@PathVariable("id") int id) {
    	
    	Treasure t = treasureDao.get(id);
        if (t == null) {
            return new ResponseEntity<Treasure>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Treasure>(t, HttpStatus.OK);
    }

}