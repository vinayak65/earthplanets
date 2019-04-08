package com.example.demo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.;

import com.example.ExcelFileReader;
import com.example.config.HibernateInitializator;
import com.example.domain.PlanetNames;
import com.example.repositories.PlanetNamesRepository;
import com.example.service.PlanetService;

@Controller /* this is sufficient @ResponseBody not required on method level */
/*
  @RequestBody and @RespondeBody both are doing the JSON 
  Update: Ever since Spring 4.x, you usually won't use @ResponseBody on method level,
   but rather @RestController on class level, with the same effect. 
   See Creating REST Controllers with the @RestController annotation
 */
public class GreetingController {

	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    PlanetNamesRepository respository;
 
	@Autowired
	EntityManager entityManager;

	@Autowired
    PlanetService planetService;
	/*
    @RequestMapping(value="/greeting",method=RequestMethod.GET)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }*/
    
    @RequestMapping(value="/loadData",method=RequestMethod.GET)
    public void loadData(@RequestParam(value="name", defaultValue="World") String name) {
    	try {
    	/*	HibernateInitializator temp= new HibernateInitializator();
    		
*/
    		/*SessionFactory tempFactory;
        	
        	tempFactory = temp.getSessionFactory();
        	Session s= tempFactory.openSession();*/
        	
    		ExcelFileReader.readExcel();
    		planetService.saveAll(PlanetNames.list);
		} catch (FileNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			} catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	return ;
        
    }
    /*@RequestMapping(value="/dijkstra",method=RequestMethod.GET)
    public String getView() {
    	return "first/ViewDijkstra";
        
    }*/
    
}