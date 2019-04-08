package com.example.webservice.domain;

import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.domain.PlanetNames;
import com.example.domain.Routes;
import com.example.service.GraphService;
import com.example.service.RoutesService;
import com.example.webservice.schema.GetRouteResponse;
import com.example.webservice.schema.GraphPoint;
import com.example.webservice.schema.SendRouteRequest;

@Endpoint
public class GraphEndPoint {

	@Autowired
	RoutesService routesService;
	
	@Autowired
    GraphService graphService;
	
	
	@PayloadRoot(namespace = "http://www.example.org/GraphService", localPart = "SendRouteRequest")
    @ResponsePayload
	public GetRouteResponse processRouteRequest(@RequestPayload SendRouteRequest request) {

		GetRouteResponse response = new GetRouteResponse();
		
		
		GraphPoint sourceGraphPoint= request.getSendRouteRequest();
		
		GraphPoint destGraphPoint= request.getDestRouteRequest();
		
		String point =  sourceGraphPoint.getName();
		
		String dest = destGraphPoint.getName();
		
		
		PlanetNames pStart  = new PlanetNames( point, point);
    	PlanetNames pEnd  = new PlanetNames( dest, dest);
    	
    	LinkedList<PlanetNames> nodes =   graphService.getNodes(pStart, pEnd, PlanetNames.list, Routes.list);
    	
    	Iterator itr = nodes.iterator();
    	PlanetNames p2,p1 =null;
    	p1 = pStart;
    	float distance = 0;
    	LinkedList<GraphPoint> list =new LinkedList<GraphPoint>();
    	if(itr.hasNext())
    	  p1 = (PlanetNames)itr.next();
    	
    	while(p1!=null && itr.hasNext())
    	{  
    		GraphPoint graphPoint= new GraphPoint();
    		p2 =(PlanetNames) itr.next();
    		graphPoint.setName(p2.planetName);
    		distance += graphService.getDistance(p1,p2);
    		p1 = p2;
    		graphPoint.setDistance(String.valueOf(distance));
    		//PlanetNames p =   nodes.get(1);
    		list.add(graphPoint);
    	}
    	response.setNextRoute(list);
    	
       /*String    graphPoint.setName("Adam");
        graphPoint.setDistance("E1234567");
        response.setGraphPoint(graphPoint);
        */
         return response;
   }

	
	
}
