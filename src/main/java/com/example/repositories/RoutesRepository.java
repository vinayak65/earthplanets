package com.example.repositories;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.PlanetNames;
import com.example.domain.Routes;

public interface RoutesRepository extends JpaRepository<Routes, Integer> {

	 
}
