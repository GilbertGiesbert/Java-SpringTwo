package joern.java.spring.two.core.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import joern.java.spring.two.core.dao.Dao;
import joern.java.spring.two.core.dao.DaoImpl;
import joern.java.spring.two.core.model.User;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database/database.properties")
public class DatabaseConfig {
	
    private static final Logger L = LoggerFactory.getLogger(DatabaseConfig.class);
    
    @Autowired
    private Environment env;

	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		
        String driverClassName = StringUtils.trimToNull(env.getProperty("database.driverClassName"));
        String url = StringUtils.trimToNull(env.getProperty("database.url"));
        String username = StringUtils.trimToNull(env.getProperty("database.username"));
        String password = StringUtils.trimToNull(env.getProperty("database.password"));
        
        L.debug("driverClassName: "+driverClassName);
        L.debug("url: "+url);
        L.debug("username: "+username);
        L.debug("password not blank: "+StringUtils.isNotBlank(password));
		
	    BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

	    return dataSource;
	}
	
	private Properties getHibernateProperties() {
		
        String dialect = StringUtils.trimToNull(env.getProperty("hibernate.dialect"));
        String showSql = StringUtils.trimToNull(env.getProperty("hibernate.show_sql"));
        String formatSql = StringUtils.trimToNull(env.getProperty("hibernate.format_sql"));
        
        L.debug("dialect: "+dialect);
        L.debug("showSql: "+showSql);
        L.debug("formatSql: "+formatSql);
		
	    Properties properties = new Properties();
	    properties.put("hibernate.dialect", dialect);
	    properties.put("hibernate.show_sql", showSql);
	    properties.put("hibernate.format_sql", formatSql);
	    return properties;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
	 
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	    // sessionBuilder.addAnnotatedClasses(User.class);
	    sessionBuilder.scanPackages("joern.java.spring.two.core.model");
	    sessionBuilder.addProperties(getHibernateProperties());
	    return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
	    return new HibernateTransactionManager(sessionFactory);
	}
	
	@Autowired
	@Bean(name = "userDao")
	public Dao<User> getUserDao(SessionFactory sessionFactory) {
	    return new DaoImpl<User>(User.class, sessionFactory);
	}

}