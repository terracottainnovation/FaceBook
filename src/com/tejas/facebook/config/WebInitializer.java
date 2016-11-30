package com.tejas.facebook.config;


import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.tejas.facebook.util.ProperyReader;

/**
 * 
 * @author Tejas Mahajan
 *
 */
@Configuration
public class WebInitializer implements WebApplicationInitializer{
	
	@Autowired 
	ProperyReader properyReader;

	static Logger LOGGER  = Logger.getLogger(WebInitializer.class);

	public WebInitializer() {
		 LOGGER.debug("Inside Default constructor of  Web Initializer.");
	}
	@Override
	public void onStartup(ServletContext container) throws ServletException {
		 LOGGER.debug("Setting up Web initializer.");
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		 dispatcherContext.register(FacebookConfiguration.class);
		 Dynamic dispatcherServlet = container.addServlet("fbLocator", new DispatcherServlet(dispatcherContext));
		 dispatcherServlet.setLoadOnStartup(1);
		 dispatcherServlet.addMapping("*.do");
		 FacebookConfiguration.properyReader();
		 
		 MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
	        Set<ObjectName> objectNames = null;
	        String host = null ;
	        String port = null;
			try {
				objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
				        Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
			} catch (MalformedObjectNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		try {
			host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(objectNames != null  && host != null){
		  port = objectNames.iterator().next().getKeyProperty("port");
		  LOGGER.debug("Server is up with URL -> \"http" + "://" + host + ":" + port+"\"");
		}
	}
	
}
