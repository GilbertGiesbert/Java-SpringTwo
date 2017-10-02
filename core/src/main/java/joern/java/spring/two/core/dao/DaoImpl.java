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

@Repository
public class DaoImpl<E> implements Dao<E> {
	
	private static final Logger L = LoggerFactory.getLogger(DaoImpl.class);
	
    private SessionFactory sessionFactory;
    
    private Class<E> entityClass;
     
    public DaoImpl() {
    	// don't know why but spring initialization needs no args constructor
    }
    
    public DaoImpl(Class<E> entityClass, SessionFactory sessionFactory) {
    	this.entityClass = entityClass;
        this.sessionFactory = sessionFactory;
    }
 
    @Override
    @Transactional
    public List<E> list() {      

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<E> query = builder.createQuery(entityClass);
        Root<E> root = query.from(entityClass);
        query.select(root);
        
        Query<E> q = session.createQuery(query);
        List<E> list = q.getResultList();
        
        return list;
    }
 
    @Override
    @Transactional
    public void saveOrUpdate(E e) {
        sessionFactory.getCurrentSession().saveOrUpdate(e);
    }
 
    @Override
    @Transactional
    public void delete(int id) {

    	E e = get(id);
    	if(e != null) {
    		sessionFactory.getCurrentSession().delete(e);
    	}
    }
 
    @Override
    @Transactional
    public E get(int id) {
    	
    	Session session = sessionFactory.getCurrentSession();
    	CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<E> query = builder.createQuery(entityClass);
        Root<E> root = query.from(entityClass);
        query.select(root).where(builder.equal(root.get("id"), id));
        
        Query<E> q = session.createQuery(query);
        E e = null;
        
        try {
        	e = q.getSingleResult();
        }catch(NoResultException ex) {
        	L.debug("Failed to get "+entityClass.getSimpleName()+" from database. Check id: "+id, ex);
        }
        return e;
    }
}