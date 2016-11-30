package com.tejas.facebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.tejas.facebook.util.ProperyReader;



/**
 * 
 * @author Tejas Mahajan
 *
 */
@Configuration
@ComponentScan("com.tejas.*")
@EnableWebMvc
public class FacebookConfiguration {

	@Bean
	 public InternalResourceViewResolver setupViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setPrefix("/WEB-INF/app/view/");
	    resolver.setSuffix(".html");
	    return resolver;
	 }
	
	@Bean
	public static ProperyReader properyReader(){
		return  new ProperyReader();
	}
}
