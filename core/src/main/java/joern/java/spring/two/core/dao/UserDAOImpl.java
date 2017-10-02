package joern.java.spring.two.core.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import joern.java.spring.two.core.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private static final Logger L = LoggerFactory.getLogger(UserDAOImpl.class);
	
    private SessionFactory sessionFactory;
     
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
    @Override
    @Transactional
    public List<User> list() {      

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        
        Query<User> q = session.createQuery(query);
        List<User> list = q.getResultList();
        
        return list;
    }
 
    @Override
    @Transactional
    public void saveOrUpdate(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }
 
    @Override
    @Transactional
    public void delete(int id) {

    	User u = get(id);
    	if(u != null) {
    		sessionFactory.getCurrentSession().delete(u);
    	}
    }
 
    @Override
    @Transactional
    public User get(int id) {
    	
    	Session session = sessionFactory.getCurrentSession();
    	CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get("id"), id));
        
        Query<User> q = session.createQuery(query);
        User user = null;
        
        try {
        	user = q.getSingleResult();
        }catch(NoResultException e) {
        	L.debug("Failed to get user with id="+id+" from database.", e);
        }
        return user;
    }
}