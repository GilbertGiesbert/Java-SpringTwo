package joern.java.spring.two.core.config;

import java.nio.charset.StandardCharsets;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("joern.java.spring.two")
public class AppConfig {
 
	
	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		
	    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setPrefix("/WEB-INF/views/");
	    viewResolver.setSuffix(".jsp");
	    return viewResolver;
	}
	
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