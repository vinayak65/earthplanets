package com.example.domain;

import javax.persistence.Basic;
import javax.persistence.Id;

public class Traffic {
	
	@Id
	private String id;
	@Basic
	private String planetOrigin;
	@Basic
	private String destination;
	@Basic
	private String trafficDelay;
	@Basic
	private String distance;
	
	private String origin;
	

	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getPlanetOrigin() {
		// TODO Auto-generated method stub
		return planetOrigin;
	}

	public String getDestination() {
		// TODO Auto-generated method stub
		return destination;
	}

	public String getTrafficDelay() {
		// TODO Auto-generated method stub
		return trafficDelay;
	}

	public void setId(String stringCellValue) {
		// TODO Auto-generated method stub
		id = stringCellValue;
	}

	public void setOrigin(String stringCellValue) {
		// TODO Auto-generated method stub
		
	}

	public void setDestination(String stringCellValue) {
		// TODO Auto-generated method stub
		destination =stringCellValue;
	}

	public void setDistance(String stringCellValue) {
		// TODO Auto-generated method stub
		distance = stringCellValue;
	}

	public void setTrafficDelay(String stringCellValue) {
		// TODO Auto-generated method stub
		trafficDelay = stringCellValue;
	}
	
	@Override
    public String toString() {
        return id + " " + planetOrigin+" " + destination+" "+ trafficDelay;
    }

}
