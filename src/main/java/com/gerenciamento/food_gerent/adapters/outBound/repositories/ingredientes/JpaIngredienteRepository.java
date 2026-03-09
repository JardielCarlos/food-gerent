package com.gerenciamento.food_gerent.adapters.outBound.repositories.ingredientes;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciamento.food_gerent.adapters.outBound.entities.JpaIngredienteEntity;

public interface JpaIngredienteRepository extends JpaRepository<JpaIngredienteEntity, UUID>{
  
}
