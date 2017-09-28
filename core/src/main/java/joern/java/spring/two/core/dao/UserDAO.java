package joern.java.spring.two.core.dao;

import java.util.List;

import joern.java.spring.two.core.model.User;

public interface UserDAO {
	
    public List<User> list();
     
    public User get(int id);
     
    public void saveOrUpdate(User user);
     
    public void delete(int id);
}