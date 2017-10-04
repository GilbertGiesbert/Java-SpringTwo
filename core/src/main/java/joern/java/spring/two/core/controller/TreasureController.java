package joern.java.spring.two.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import joern.java.spring.two.core.dao.Dao;
import joern.java.spring.two.core.model.Treasure;
import joern.java.spring.two.core.model.User;

/**
 * Handles requests for treasure pages.
 */
@Controller
@RequestMapping(value = "/treasures")
public class TreasureController {
     
    @Autowired
    private Dao<Treasure> treasureDao;
 
    @RequestMapping("/list")
    public ModelAndView handleRequest() throws Exception {
    	
        List<Treasure> list = treasureDao.list();
        ModelAndView model = new ModelAndView("TreasureList");
        model.addObject("treasureList", list);
        return model;
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newTreasure() {
        ModelAndView model = new ModelAndView("TreasureForm");
        model.addObject("treasure", new Treasure());
        return model;      
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editTreasure(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Treasure treasure = treasureDao.get(id);
        ModelAndView model = new ModelAndView("TreasureForm");
        model.addObject("treasure", treasure);
        return model;
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        treasureDao.delete(id);
        return new ModelAndView("redirect:/treasures/list");     
    }
     
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute Treasure treasure) {
    	treasureDao.saveOrUpdate(treasure);
        return new ModelAndView("redirect:/treasures/list");
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public ModelAndView saveUserGet(@ModelAttribute Treasure treasure) {
    	treasureDao.saveOrUpdate(treasure);
        return new ModelAndView("redirect:/treasures/list");
    }
}