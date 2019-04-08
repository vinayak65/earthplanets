package com.example.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "Routes")
public class Routes {


	public static List<Routes> list;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;

	//@OneToOne(cascade = {CascadeType.DETACH})
	
	@OneToOne
	@JoinColumn(name="source_id")
	public PlanetNames planetOrigin;
	
	//@OneToOne(cascade = {CascadeType.DETACH})
	@OneToOne
	@JoinColumn(name="destination_id")
	public PlanetNames destination;
	
	@Basic
	public String strdistance;
	
	@Basic
	public String origin;
	@Basic
	public float Distance;
	
	
	
	public Routes(String id, String source, String destination, String distance) {
        this.id = Integer.parseInt(id);
        this.planetOrigin = PlanetNames.NodePlanetFromNodeId(source);
        this.destination = PlanetNames.NodePlanetFromNodeId(destination);
        this.strdistance = distance;
        this.Distance =(float)Float.parseFloat(distance); 
    }
	
	
	public Routes() {
		// TODO Auto-generated constructor stub
	    //this.id = Integer.parseInt(id);
        this.planetOrigin = new PlanetNames();
        this.destination = new PlanetNames();
        this.strdistance = "0";
        this.Distance = 0;
        
    	
	}


	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public PlanetNames getPlanetOrigin() {
		// TODO Auto-generated method stub
		return planetOrigin;
	}

	public PlanetNames getDestination() {
		// TODO Auto-generated method stub
		return destination;
	}

	public float getDistance() {
		// TODO Auto-generated method stub
		return Distance;
	}

	public void setId(String stringCellValue) {
		// TODO Auto-generated method stub
		id = Integer.parseInt(stringCellValue);
	}
	public void setId(int stringCellValue) {
		// TODO Auto-generated method stub
		id = stringCellValue;
	}

	public void setOrigin(String stringCellValue) {
		// TODO Auto-generated method stub
		origin = stringCellValue;
	}

	public void setDestination(String stringCellValue) {
		// TODO Auto-generated method stub
		System.out.println("Destination stringCellValue "+ stringCellValue);
		
		destination = PlanetNames.NodePlanetFromNodeId(stringCellValue);
		System.out.println("Routes destination "+destination);
	}

	public void setPlanetOrigin(String stringCellValue) {
		// TODO Auto-generated method stub
		System.out.println("PlanetOrigin stringCellValue "+ stringCellValue);
		planetOrigin = PlanetNames.NodePlanetFromNodeId(stringCellValue);
		System.out.println("Routes planetOrigin "+planetOrigin);
	}

	public void setDistance(String stringCellValue) {
		// TODO Auto-generated method stub
		strdistance = stringCellValue;
		this.Distance =(float)Float.parseFloat(strdistance);
	}
	public void setDistance(float stringCellValue) {
		// TODO Auto-generated method stub
		//distance = stringCellValue;
		strdistance = stringCellValue+"";
		this.Distance =stringCellValue;
	}
	
	 @Override
	    public String toString() {
	        return id + " " + planetOrigin+" " + destination+" "+ strdistance;
	    }

	 public static  List<Routes> makeCollection(Iterable<Routes> iter) {
		    List<Routes> list = new ArrayList<Routes>();
		    for (Routes item : iter) {
		        list.add(item);
		    }
		    return list;
		}
		
}
