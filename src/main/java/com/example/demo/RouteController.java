package com.example.demo;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import com.example.repositories.RouteRepository;
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
public class RouteController {

	@Autowired
	RouteRepository rep;
	@Autowired
	PlanetRepository repPlanet;
	
	@GetMapping("/index2")
	public ModelAndView getAllUsers(Model ft) {
    	ModelAndView md= new ModelAndView();
    	//md.addAttribute("dijkstra", new DijkstraForm());
    	md.addObject("routes", rep.findAll());
        md.setViewName("route/index");
        return md;
    }
	
	
	@GetMapping("/addroute")
	/*public String AddUser(PlanetNames planet) {
		return "add-planet";
	}*/
	public ModelAndView AddUser(Model ft) {
    	ModelAndView md= new ModelAndView();
    	//md.addAttribute("dijkstra", new DijkstraForm());
    	md.addObject("route", new Routes());
        md.setViewName("route/add-route");
        return md;
    }
	@PostMapping("/addroute")
	public String addRoute(@Valid Routes  route, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "route/add-route";
			
		}
		
		route.id=0;
		route.destination = PlanetNames.NodePlanetFromNodeId(route.destination.node);
		route.planetOrigin = PlanetNames.NodePlanetFromNodeId(route.planetOrigin.node);
		
		rep.save(route);
		
		Iterable<Routes> ls=rep.findAll();
		Routes.list = Routes.makeCollection(ls);
		//model.addAttribute("planets", rep.findAll());
			model.addAttribute("routes", rep.findAll());
			return "route/index";
	}

	@GetMapping("/editroute/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
	    Routes route= rep.findOne(id);
	    
	    model.addAttribute("route", route);
	    return "route/update-route";
	}
	@PostMapping("/updateroute/{id}")
	public String updateRoute(@PathVariable("id") int id, @Valid Routes route, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	    	route.setId(id);
	        return "route/update-route";
	    }
		route.destination = PlanetNames.NodePlanetFromNodeId(route.destination.node);
		route.planetOrigin = PlanetNames.NodePlanetFromNodeId(route.planetOrigin.node);
	         
	    rep.save(route);
	    model.addAttribute("routes", rep.findAll());
	    return "route/index";
	}
	     
	
	@GetMapping("/deleteroute/{id}")
	public String deleteUser(@PathVariable("id") int id, Model model) {
	    Routes route = rep.findOne(id);
	    
	    //  .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    rep.delete(route);
	    model.addAttribute("routes", rep.findAll());
	    return "route/index";
	}
	
}