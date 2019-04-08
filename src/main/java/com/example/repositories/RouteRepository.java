package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.PlanetNames;
import com.example.domain.Routes;

@Repository
public interface RouteRepository extends CrudRepository<Routes, Integer> {

}
