package com.example.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.PlanetNames;
import com.example.repositories.PlanetNamesRepository;
@Configuration
@Service
@Transactional
public class PlanetService {
	
	 @Autowired
	    PlanetNamesRepository respository;
	 
		@Autowired
		EntityManager entityManager;
		
		
		public List<PlanetNames> findAll() {
			return respository.findAll();
		}
		public PlanetNames findOne(Integer id) {
			return respository.findOne(id);
		}
		public List<PlanetNames> getAll() {
			// TODO Auto-generated method stub
			return null;
		}
		public PlanetNames save(PlanetNames planetNames) {
			// TODO Auto-generated method stub]
			
			return null;
			
		}
		public List<PlanetNames> saveAll(List<PlanetNames> planetNames) {
			// TODO Auto-generated method stub]
			
			return respository.save(planetNames);
			
		}
		public void delete(int blogId) {
			// TODO Auto-generated method stub
			
		}
}
