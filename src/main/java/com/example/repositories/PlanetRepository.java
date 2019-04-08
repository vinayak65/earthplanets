package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.PlanetNames;

@Repository
public interface PlanetRepository extends CrudRepository<PlanetNames, Integer> {

}
