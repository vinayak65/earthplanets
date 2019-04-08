package com.example.demo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.;
import org.springframework.web.servlet.ModelAndView;
import com.example.ExcelFileReader;
import com.example.config.HibernateInitializator;
import com.example.domain.PlanetNames;
import com.example.domain.Routes;
import com.example.repositories.PlanetNamesRepository;
import com.example.repositories.PlanetRepository;
import com.example.service.GraphService;
import com.example.service.PlanetService;
import com.example.service.RoutesService;
import com.example.util.DijkstraAlgo;

@Controller /* this is sufficient @ResponseBody not required on method level */
/*
  @RequestBody and @RespondeBody both are doing the JSON 
  Update: Ever since Spring 4.x, you usually won't use @ResponseBody on method level,
   but rather @RestController on class level, with the same effect. 
   See Creating REST Controllers with the @RestController annotation
 */
public class PlanetController {

	@Autowired
	PlanetRepository rep;
	
	
	@GetMapping("/index")
	public ModelAndView getAllUsers(Model ft) {
    	ModelAndView md= new ModelAndView();
    	//md.addAttribute("dijkstra", new DijkstraForm());
    	md.addObject("planets", rep.findAll());
        md.setViewName("planet/index");
        return md;
    }
	
	
	@GetMapping("/addplanet")
	/*public String AddUser(PlanetNames planet) {
		return "add-planet";
	}*/
	public ModelAndView AddUser(Model ft) {
    	ModelAndView md= new ModelAndView();
    	//md.addAttribute("dijkstra", new DijkstraForm());
    	md.addObject("planet", new PlanetNames());
        md.setViewName("planet/add-planet");
        return md;
    }
	@PostMapping("/addplanet")
	public String addUser(@Valid PlanetNames planet, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "planet/add-planet";
			
		}
		planet.id=0;
		rep.save(planet);
		Iterable<PlanetNames> ls=rep.findAll();
		//PlanetNames.list=new  LinkedList<PlanetNames>();
		PlanetNames.list = PlanetNames.makeCollection(ls);
			model.addAttribute("planets", rep.findAll());
			return "planet/index";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
	    PlanetNames planet = rep.findOne(id);
	    
	    model.addAttribute("planet", planet);
	    return "planet/update-planet";
	}
	@PostMapping("/update/{id}")
	public String updatePlanet(@PathVariable("id") int id, @Valid PlanetNames planet, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	    	planet.setId(id);
	        return "planet/update-planet";
	    }
	         
	    rep.save(planet);
	    model.addAttribute("planets", rep.findAll());
	    return "planet/index";
	}
	     
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int id, Model model) {
	    PlanetNames planet = rep.findOne(id);
	    
	    //  .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    rep.delete(planet);
	    model.addAttribute("planets", rep.findAll());
	    return "planet/index";
	}
	
}