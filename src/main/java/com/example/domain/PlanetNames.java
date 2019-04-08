package com.example.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PlanetNames")
public class PlanetNames {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;
	
	@Basic
	public String node;

	@Basic
	public String planetName;
	
	public static List<PlanetNames> list;
	
	public String getNode() {
		
		return node;
	}
	public void setNode(String strnode) {
		
		this.node = strnode;
	}
	
	public PlanetNames()
	{
		this.id = 0;
		this.node ="";
		this.planetName = "";
	}

	public PlanetNames(String node, String planet)
	{
		this.id = 0;
		this.node = node;
		this.planetName  = planet;
	}
	public static final PlanetNames NodePlanetFromNodeId(final String nodeid)
    {
		/*
		LinkedList<PlanetNames> ls = new LinkedList<PlanetNames>();
		PlanetNames planet = new PlanetNames();
		planet.node  = "A";
		planet.planetName ="Earth";
		
				
		ls.add(planet);
		
		planet = new PlanetNames();
		planet.node  = "B";
		planet.planetName ="Mecury";
		
				
		ls.add(planet);
        
		planet = new PlanetNames();
		planet.node  = "C";
		planet.planetName ="Venus";
		
				
		ls.add(planet);
		planet = new PlanetNames();
		planet.node  = "D";
		planet.planetName ="Mars";
		
				
		ls.add(planet);
        
		planet = new PlanetNames();
		planet.node  = "E";
		planet.planetName ="Jupiter";
		
				
		ls.add(planet);
		planet = new PlanetNames();
		planet.node  = "F";
		planet.planetName ="Saturn";
		
				
		ls.add(planet);
        
		planet = new PlanetNames();
		planet.node  = "G";
		planet.planetName ="Pluto";
		
				
		ls.add(planet);
		planet = new PlanetNames();
		planet.node  = "H";
		planet.planetName ="Neptune";
		
				
		ls.add(planet);
        
		*/
        PlanetNames returnValue;
        //Search the database and convert into PlanetName from node id.
        // convert from Hooty to Blammy.
        ListIterator<PlanetNames> iterator = list.listIterator();
        PlanetNames pn = null;
        while (iterator.hasNext()) {
        	PlanetNames pnTemp = iterator.next();
        	if (pnTemp.node.equalsIgnoreCase(nodeid))
        	{
        		pn= pnTemp;
        		break;
        	}
            //System.out.println(); 
        }

        
        return pn;
    }
	public static  List<PlanetNames> makeCollection(Iterable<PlanetNames> iter) {
	    List<PlanetNames> list = new ArrayList<PlanetNames>();
	    for (PlanetNames item : iter) {
	        list.add(item);
	    }
	    return list;
	}
	public int getId() {
		
		return id;
	}
	public void setId(String stringCellValue) {
		
		id = Integer.parseInt(stringCellValue);
	}
	public void setId(int stringCellValue) {
		
		id = stringCellValue;
	}

	public String getPlanetName() {
		
		return planetName;
	}

	public void setPlanetNode(String stringCellValue) {
		// TODO Auto-generated method stub
		node = stringCellValue;
	}

	public void setPlanetName(String stringCellValue) {
		// TODO Auto-generated method stub
		planetName = stringCellValue;
	}

	 @Override
	    public String toString() {
	        return node + " " + planetName;
	    }
	 
	 @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((node == null) ? 0 : node.hashCode() +planetName.trim().hashCode());
	        return result;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        PlanetNames other = (PlanetNames) obj;
	        if (node == null) {
	            if (other.node != null)
	                return false;
	        } else if (!node.equals(other.node) && !planetName.trim().equals(other.planetName.trim()))
	        	
	            return false;
	        return true;
	    }
}
