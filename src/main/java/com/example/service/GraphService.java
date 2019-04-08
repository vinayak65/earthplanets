
package com.example.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.PlanetNames;
import com.example.domain.Routes;
import com.example.repositories.PlanetNamesRepository;
import com.example.repositories.RoutesRepository;
import com.example.util.DijkstraAlgo;
import com.example.util.Graph;


@Configuration
@Service
@Transactional
public class GraphService {
	    @Autowired
	    PlanetNamesRepository respository;
	    
	    @Autowired
	    RoutesRepository routesRepo;
	    
	    List<Routes> allRoutes ;
	    List<PlanetNames> allPlanets;
	    DijkstraAlgo dAlgo; 
	    
	    Graph gph;
	 
		@Autowired
		EntityManager entityManager;
		
		public void getRoutes(){
			allRoutes = routesRepo.findAll();
		}
		
		public void getPlanets() {
			
			allPlanets = respository.findAll();
		}
		
		
		private void prepareGraph(List<PlanetNames> lp, List<Routes> lr ) {
		    gph = new Graph( lp,lr);
			
		}
		public float useDijkstra(PlanetNames start, PlanetNames end, List<PlanetNames> list, List<Routes> list2) { 
			float distance = 0;
			getPlanets();
				getRoutes();
				System.out.println("Planet Names "+list.size());
				System.out.println("Routes "+list2.size());
				
				prepareGraph(list,list2);
				System.out.println("Routes gph.getEdges().size() " +gph.getEdges().size());
				System.out.println("Planet Names gph.getEdges().size() " +gph.getVertexes().size());
				dAlgo = new DijkstraAlgo(gph);
				// Lets check from location Loc_1 to Loc_10
				
				for (PlanetNames p : list){
					
					 
					if ( start.getNode() =="" && p.getPlanetName().equals(start.getPlanetName())){
						start = new PlanetNames(p.getNode(),p.getPlanetName());
					}
					if ( start.getPlanetName() =="" && p.getNode().equals(start.getNode())){
						start = new PlanetNames(p.getNode(),p.getPlanetName());
					}
					
					if ( end.getNode() =="" && p.getPlanetName().equals(end.getPlanetName())){
						end = new PlanetNames(p.getNode(),p.getPlanetName());
					}
					if ( end.getPlanetName() =="" && p.getNode().equals(end.getNode())){
						end = new PlanetNames(p.getNode(),p.getPlanetName());
					}
					
					
				}
				
				//PlanetNames names=PlanetNames.NodePlanetFromNodeId("\)
				dAlgo.execute(list.get(0));
		        LinkedList<PlanetNames> path = dAlgo.getPath(end);
		       

		        PlanetNames pElem =null , pElem2=null ;
		        //assertNotNull(path);
		        //assertTrue(path.size() > 0);
		        distance = 0;
                Iterator itr = path.iterator();
                
                if(itr.hasNext())
                pElem = (PlanetNames)itr.next();
                
		        while (pElem!=null && itr.hasNext()) {
		        	//pElem  = (PlanetNames)itr.next();
		             pElem2 = (PlanetNames)itr.next();
		        	 distance += dAlgo.getDistance(pElem,pElem2);
		        	pElem = pElem2;
		            System.out.println("distance "+distance);
		            
		        }
				
			return distance;
		}

		public LinkedList<PlanetNames> getNodes(PlanetNames start, PlanetNames end, List<PlanetNames> list,
				List<Routes> list2) {
		     	float distance = 0;
			    getPlanets();
				getRoutes();
				System.out.println("Get next hop .....  " );
				
				prepareGraph(list,list2);
				//System.out.println("Routes gph.getEdges().size() " +gph.getEdges().size());
				//System.out.println("Planet Names gph.getEdges().size() " +gph.getVertexes().size());
				dAlgo = new DijkstraAlgo(gph);
				// Lets check from location Loc_1 to Loc_10
				
				for (PlanetNames p : list){
					
					if (p.getNode().equals(start.getNode()) || p.getPlanetName().equals(start.getPlanetName())){
						start = new PlanetNames(p.getNode(),p.getPlanetName());
					}
					if (p.getNode().equals(end.getNode()) || p.getPlanetName().equals(end.getPlanetName())){
						end = new PlanetNames(p.getNode(),p.getPlanetName());
					}
				}
				
				//PlanetNames names=PlanetNames.NodePlanetFromNodeId("\)
				dAlgo.execute(list.get(0));
		        LinkedList<PlanetNames> path = dAlgo.getPath(end);
		        return path;
		        
			 
		}
		
		public float getDistance(PlanetNames start, PlanetNames end) {
		     	float distance = 0;
		     	//LinkedList<float> getDistance = new LinkedList<float> ();
		     	//assertNotNull(path);
		        //assertTrue(path.size() > 0);
                	   distance = dAlgo.getDistance(start,end);
		            System.out.println("distance "+distance);
		            return distance; 
		        }
				
	}
		
		

