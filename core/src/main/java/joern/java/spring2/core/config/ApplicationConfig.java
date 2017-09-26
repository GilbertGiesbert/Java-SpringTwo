package joern.java.spring2.core.config;

import java.nio.charset.StandardCharsets;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


import joern.java.spring2.core.model.User;

@Configuration
@EnableWebMvc
@ComponentScan("joern.java.spring2")
public class ApplicationConfig extends WebMvcConfigurerAdapter {
 
	
	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		
	    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setPrefix("/WEB-INF/views/");
	    viewResolver.setSuffix(".jsp");
	    return viewResolver;
	}
	

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
	 
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	    sessionBuilder.addAnnotatedClasses(User.class);
	    return sessionBuilder.buildSessionFactory();
	}

	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    
    
    @Bean
    public MessageSource  messageSource() {
    	
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        
        source.setBasenames(new String[]{
				//"classpath:i18n/srcMainRsrcProps",
				//"/WEB-INF/i18n/messages",
        		//"/WEB-INF/i18n/subfolder/prompts",
        		"i18n/messages"});
        
        //source.setUseCodeAsDefaultMessage(true);
        source.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return source;
    }
}