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
import joern.java.spring.two.core.model.User;

/**
 * Handles requests for user pages.
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {
     
    @Autowired
    private Dao<User> userDao;
    
    @RequestMapping("/list")
    public ModelAndView userList() throws Exception {
        List<User> listUsers = userDao.list();
        ModelAndView model = new ModelAndView("UserList");
        model.addObject("userList", listUsers);
        return model;
    }
     
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newUser() {
        ModelAndView model = new ModelAndView("UserForm");
        model.addObject("user", new User());
        return model;      
    }
     
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = userDao.get(userId);
        ModelAndView model = new ModelAndView("UserForm");
        model.addObject("user", user);
        return model;
    }
     
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("id"));
        userDao.delete(userId);
        return new ModelAndView("redirect:/users/list");     
    }
     
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute User user) {
        userDao.saveOrUpdate(user);
        return new ModelAndView("redirect:/users/list");
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public ModelAndView saveUserGet(@ModelAttribute User user) {
        userDao.saveOrUpdate(user);
        return new ModelAndView("redirect:/users/list");
    }
}