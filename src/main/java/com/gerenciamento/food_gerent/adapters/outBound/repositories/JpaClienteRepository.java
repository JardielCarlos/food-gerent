package com.gerenciamento.food_gerent.adapters.outBound.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaClienteEntity;


public interface JpaClienteRepository extends JpaRepository<JpaClienteEntity, UUID> {
  
}
