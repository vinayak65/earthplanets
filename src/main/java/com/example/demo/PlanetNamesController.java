package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.domain.PlanetNames;

import com.example.service.PlanetService;

import java.util.List;
import java.util.Map;
@RestController
public class PlanetNamesController {
	    @Autowired
	    PlanetService planetService;

	    
	    @GetMapping("/PlanetNames")
	    public List<PlanetNames> index(){
	        return planetService.findAll();
	    }

	    @GetMapping("/PlanetNames/{id}")
	    public PlanetNames show(@PathVariable String id){
	        int PlanetNamesId = Integer.parseInt(id);
	        
	        return planetService.findOne(PlanetNamesId);
	    }

	    @PostMapping("/PlanetNames/search")
	    public List<PlanetNames> search(@RequestBody Map<String, String> body){
	        String searchTerm = body.get("text");
	        return planetService.getAll();
	    }

	    @PostMapping("/PlanetNames")
	    public PlanetNames create(@RequestBody Map<String, String> body){
	        String title = body.get("node");
	        String content = body.get("planetName");
	        return planetService.save(new PlanetNames(title, content));
	    }

	    @PutMapping("/PlanetNames/{id}")
	    public PlanetNames update(@PathVariable String id, @RequestBody Map<String, String> body){
	        int PlanetNamesId = Integer.parseInt(id);
	        // getting PlanetNames
	        PlanetNames blog = planetService.findOne(PlanetNamesId);
	        blog.setPlanetNode(body.get("node"));
	        blog.setPlanetName(body.get("planetName"));
	        return planetService.save(blog);
	    }

	    @DeleteMapping("/PlanetNames/{id}")
	    public boolean delete(@PathVariable String id){
	        int blogId = Integer.parseInt(id);
	        planetService.delete(blogId);
	        return true;
	    }


	
}
