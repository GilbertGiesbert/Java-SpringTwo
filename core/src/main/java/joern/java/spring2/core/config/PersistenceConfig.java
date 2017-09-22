package joern.java.spring2.core.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import joern.java.spring2.core.dao.UserDAO;
import joern.java.spring2.core.dao.UserDAOImpl;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {
	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("org.postgresql.Driver");
	    // jdbc:postgresql://host:port/database
	    dataSource.setUrl("jdbc:postgresql://localhost:5432/spring2");
	    dataSource.setUsername("postgres");
	    dataSource.setPassword("geheim123");
	    return dataSource;
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
	    return new HibernateTransactionManager(sessionFactory);
	}
	
	@Autowired
	@Bean(name = "userDao")
	public UserDAO getUserDao(SessionFactory sessionFactory) {
	    return new UserDAOImpl(sessionFactory);
	}

}