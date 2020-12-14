package com.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.model.Cliente;

public interface ClienteRepository extends  JpaRepository<Cliente, Long>{

}
