package com.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long>{

}
