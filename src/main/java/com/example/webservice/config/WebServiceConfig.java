package com.example.webservice.config;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import com.example.ExcelFileReader;
import com.example.domain.Routes;
import com.example.service.GraphService;
import com.example.service.PlanetService;
import com.example.service.RoutesService;
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	
	@Autowired
    PlanetService planetService;
	
	@Autowired
	RoutesService routesService;
	
	@Autowired
    GraphService graphService;
	

	public WebServiceConfig() {
		// TODO Auto-generated constructor stub
		/*try {
    		ExcelFileReader.ps = planetService;
        		ExcelFileReader.readExcel();
        		
        		routesService.saveAll(Routes.list);
        		
        	} catch (FileNotFoundException ex) {
    			// TODO Auto-generated catch block
    			ex.printStackTrace();
    			} catch (IOException e) {
    		    // TODO Auto-generated catch block
    			e.printStackTrace();
    		}*/
	}
	@Bean
	 public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
	    MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
	    messageDispatcherServlet.setApplicationContext(context);
	    messageDispatcherServlet.setTransformWsdlLocations(true);
	    
	 // TODO Auto-generated constructor stub
	 		try {
	     		ExcelFileReader.ps = planetService;
	         		ExcelFileReader.readExcel();
	         		
	         		routesService.saveAll(Routes.list);
	         		
	         	} catch (FileNotFoundException ex) {
	     			// TODO Auto-generated catch block
	     			ex.printStackTrace();
	     			} catch (IOException e) {
	     		    // TODO Auto-generated catch block
	     			e.printStackTrace();
	     		}
	    
	    return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	  }

	@Bean(name = "graphpoint")

	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema graphpointSchema) {

	  DefaultWsdl11Definition definition = new DefaultWsdl11Definition();

	  definition.setPortTypeName("GraphPointPort");
	  definition.setTargetNamespace("http://www.example.org/GraphService");
	  definition.setLocationUri("/ws");
      definition.setSchema(graphpointSchema);
      return definition;
 
	}


	@Bean
    public XsdSchema graphpointSchema() {
 
	  return new SimpleXsdSchema(new ClassPathResource("graph-point.xsd"));
    }

	
	
	
}
