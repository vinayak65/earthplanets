package com.example.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Routes;
import com.example.repositories.RoutesRepository;
import com.example.repositories.RoutesRepository;

@Configuration
@Service
@Transactional
public class RoutesService {

	   @Autowired
	    RoutesRepository routesRepo;
	    
	   @Autowired
		EntityManager entityManager;
		
		
		public List<Routes> findAll() {
			return routesRepo.findAll();
		}
		public Routes findOne(Integer id) {
			return routesRepo.findOne(id);
		}
		public List<Routes> getAll() {
		
			return null;
		}
		public Routes save(Routes Routes) {
			
			
			return null;
			
		}
		public List<Routes> saveAll(List<Routes> Routes) {
			// TODO Auto-generated method stub]
			
			return routesRepo.save(Routes);
			
		}
	    
	    
}
