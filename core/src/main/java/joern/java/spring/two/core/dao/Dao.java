package joern.java.spring.two.core.dao;

import java.util.List;

public interface Dao<E> {
	
    List<E> list();
    
    E get(int id);
     
    void saveOrUpdate(E e);
     
    void delete(int id);
}